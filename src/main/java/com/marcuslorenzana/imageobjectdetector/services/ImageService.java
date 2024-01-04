package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.models.ImageMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Value("${app.IMAGGA_API_ENDPOINT}")
    private String imaggaBaseUrl;

    @Value("${app.IMAGGA_API_AUTH}")
    private String imaggaAuth;

    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public List<ImageMetadata> getAllImages(String objects) {
        logger.info(imaggaBaseUrl);
        logger.info(imaggaAuth);
        logger.info(objects);
        List<ImageMetadata> images = new ArrayList<>();
        return images;
    }

    public ImageMetadata getImageById(String itemId) {
        logger.info(itemId);
        return null;
    }

    public ImageMetadata createImage(ImageMetadata image) {
        logger.info(image.toString());
        return image;
    }
}
