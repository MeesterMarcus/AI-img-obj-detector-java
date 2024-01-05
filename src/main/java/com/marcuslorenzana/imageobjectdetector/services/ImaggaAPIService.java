package com.marcuslorenzana.imageobjectdetector.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            logger.info(jsonResponse);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Unable to process image.");
        }
    }

    private String getTagsUrl(String imageSource) {
        String tagsBaseUrl =  String.format("%s/v2/tags", imaggaBaseUrl);
        return tagsBaseUrl + "?image_url=" + imageSource;
    }
}
