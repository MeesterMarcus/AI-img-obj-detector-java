package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.mappers.ImageRequestToEntityMapper;
import com.marcuslorenzana.imageobjectdetector.mappers.TagItemToObjectEntityMapper;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiTagItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.marcuslorenzana.imageobjectdetector.utilities.ImaggaAPIUtils.getTagsUrl;
import static com.marcuslorenzana.imageobjectdetector.utilities.ImaggaAPIUtils.retrieveTagsFromResponse;

@Service
public class ImaggaAPIService {

    @Value("${app.IMAGGA_API_AUTH}")
    private String imaggaAuth;

    Logger logger = LoggerFactory.getLogger(ImaggaAPIService.class);

    /**
     * Uses Imagga API to retrieve objects from an image.
     *
     * @param imageRequestData: ImageMetadataRequest
     * @return ImageMetadataEntity
     */
    public ImageMetadataEntity retrieveObjectsFromImage(ImageMetadataRequest imageRequestData) throws IOException {
        String tagsUrl = getTagsUrl(imageRequestData.getImageSource());
        URL urlObject = new URL(tagsUrl);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestProperty("Authorization", imaggaAuth);
        int responseCode = connection.getResponseCode();
        logger.info("\n Sending 'GET' request to URL: " + tagsUrl);
        logger.info("Response Code: " + responseCode);
        BufferedReader connectionInput = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String jsonResponse = connectionInput.readLine();
        connectionInput.close();
        List<ImaggaApiTagItem> tagItems = retrieveTagsFromResponse(jsonResponse);
        List<ObjectEntity> objectEntities = null;
        if (tagItems != null) {
            objectEntities = tagItems.stream()
                    .map(TagItemToObjectEntityMapper::map)
                    .toList();
        }
        return ImageRequestToEntityMapper.map(imageRequestData, objectEntities);
    }
}
