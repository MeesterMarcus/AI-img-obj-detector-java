package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;
import com.marcuslorenzana.imageobjectdetector.repositories.ImageMetadataEntityRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final ImaggaAPIService imaggaAPIService;

    private final ImageMetadataEntityRepository imageMetadataEntityRepository;

    public ImageService(ImaggaAPIService imaggaAPIService, ImageMetadataEntityRepository imageMetadataEntityRepository) {
        this.imaggaAPIService = imaggaAPIService;
        this.imageMetadataEntityRepository = imageMetadataEntityRepository;
    }

    public List<ImageMetadataEntity> getAllImages(String objects) {
        List<ImageMetadataEntity> images = this.imageMetadataEntityRepository.findAll();
        logger.info(images.toString());
        return images;
    }

    public ImageMetadataRequest getImageById(String itemId) {
        return null;
    }

    public ImageMetadataRequest createImage(ImageMetadataRequest image) {
        this.imaggaAPIService.retrieveObjectsFromImage((image.getImageSource()));
        return image;
    }
}
