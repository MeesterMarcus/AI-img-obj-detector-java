package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.models.ImageMetadata;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final ImaggaAPIService imaggaAPIService;

    public ImageService(ImaggaAPIService imaggaAPIService) {
        this.imaggaAPIService = imaggaAPIService;
    }

    public List<ImageMetadata> getAllImages(String objects) {
        List<ImageMetadata> images = new ArrayList<>();
        return images;
    }

    public ImageMetadata getImageById(String itemId) {
        return null;
    }

    public ImageMetadata createImage(ImageMetadata image) {
        this.imaggaAPIService.retrieveObjectsFromImage((image.getImageSource()));
        return image;
    }
}
