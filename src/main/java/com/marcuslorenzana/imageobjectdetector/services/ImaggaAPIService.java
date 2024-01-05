package com.marcuslorenzana.imageobjectdetector.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.mappers.TagItemToObjectEntityMapper;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiResponse;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiTagItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImaggaAPIService {

    @Value("${app.IMAGGA_API_ENDPOINT}")
    private String imaggaBaseUrl;

    @Value("${app.IMAGGA_API_AUTH}")
    private String imaggaAuth;

    Logger logger = LoggerFactory.getLogger(ImaggaAPIService.class);

    public void retrieveObjectsFromImage(String imageSource) {
        String tagsUrl = getTagsUrl(imageSource);
        try {
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
            logger.info(objectEntities.get(0).toString());
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Unable to process image.");
        }
    }

    private String getTagsUrl(String imageSource) {
        String tagsBaseUrl =  String.format("%s/v2/tags", imaggaBaseUrl);
        return tagsBaseUrl + "?image_url=" + imageSource;
    }

    private List<ImaggaApiTagItem> retrieveTagsFromResponse(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ImaggaApiResponse apiResponse = mapper.readValue(jsonResponse, ImaggaApiResponse.class);
            List<ImaggaApiTagItem> tags = apiResponse.getResult().getTags();
            return tags;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
