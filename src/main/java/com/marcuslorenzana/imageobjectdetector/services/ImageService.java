package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;
import com.marcuslorenzana.imageobjectdetector.repositories.ImageMetadataEntityRepository;
import com.marcuslorenzana.imageobjectdetector.repositories.ObjectEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final ImaggaAPIService imaggaAPIService;

    private final ImageMetadataEntityRepository imageMetadataEntityRepository;

    private final ObjectEntityRepository objectEntityRepository;

    public ImageService(ImaggaAPIService imaggaAPIService,
                        ImageMetadataEntityRepository imageMetadataEntityRepository,
                        ObjectEntityRepository objectEntityRepository) {
        this.imaggaAPIService = imaggaAPIService;
        this.imageMetadataEntityRepository = imageMetadataEntityRepository;
        this.objectEntityRepository = objectEntityRepository;
    }

    public List<ImageMetadataEntity> getAllImages(String objects) {
        List<ImageMetadataEntity> images;
        if (objects != null && !objects.isEmpty()) {
            List<String> objectNames = List.of(objects.split(","));
            images = this.imageMetadataEntityRepository.findByObjectNames(objectNames);
        } else {
            images = this.imageMetadataEntityRepository.findAll();
        }

        logger.info(images.toString());
        return images;
    }

    public ImageMetadataEntity getImageById(long id) {
        Optional<ImageMetadataEntity> entity = this.imageMetadataEntityRepository.findById(id);
        if (entity.isPresent()) {
            return entity.get(); // If the entity is present, return it
        } else {
            logger.info("Could not find the image with ID " + id + " in the DB.");
            return null;
        }
    }


    @Transactional
    public ImageMetadataEntity createImage(ImageMetadataRequest image) {
        ImageMetadataEntity entity = this.imaggaAPIService.retrieveObjectsFromImage(image);
        List<ObjectEntity> objectEntities = this.objectEntityRepository.saveAllAndFlush(entity.getObjects());
        List<ObjectEntity> objectEntitiesCommitted = this.objectEntityRepository.findAll();
        logger.info("lets see if they are there...");
        logger.info(objectEntitiesCommitted.toString());
        entity.setObjects(objectEntities);
        this.imageMetadataEntityRepository.saveAndFlush(entity);
        return entity;
    }
}
