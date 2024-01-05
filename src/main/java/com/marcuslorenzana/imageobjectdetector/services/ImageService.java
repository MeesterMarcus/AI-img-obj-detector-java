package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;
import com.marcuslorenzana.imageobjectdetector.repositories.ImageMetadataEntityRepository;
import com.marcuslorenzana.imageobjectdetector.repositories.ObjectEntityRepository;
import jakarta.transaction.Transactional;
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

    public ImageMetadataRequest getImageById(String itemId) {
        return null;
    }

    @Transactional
    public ImageMetadataEntity createImage(ImageMetadataRequest image) {
        ImageMetadataEntity entity = this.imaggaAPIService.retrieveObjectsFromImage(image);
        List<ObjectEntity> objectEntities =this.objectEntityRepository.saveAllAndFlush(entity.getObjects());
        List<ObjectEntity> objectEntitiesCommitted = this.objectEntityRepository.findAll();
        logger.info("lets see if they are there...");
        logger.info(objectEntitiesCommitted.toString());
        entity.setObjects(objectEntities);
        this.imageMetadataEntityRepository.saveAndFlush(entity);
        return entity;
    }

//    private List<ObjectEntity> retrieveUniqueObjects(List<ObjectEntity> objectEntities) {
//        List<ObjectEntity> uniqueObjects = new ArrayList<>();
//        for (ObjectEntity newObject : objectEntities) {
//            List<ObjectEntity> existingObject = objectEntityRepository.findByName(newObject.getName());
//            logger.info("testing 12");
//            logger.info(existingObject.toString());
//            // Object already exists, associate the existing one
//            uniqueObjects.addAll(existingObject); // Add the existing object to the list
//        }
//        return objectEntities;
//    }
}
