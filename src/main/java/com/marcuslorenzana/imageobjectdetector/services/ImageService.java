package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.mappers.ImageMetadataMapper;
import com.marcuslorenzana.imageobjectdetector.mappers.ImageRequestToModelMapper;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataModel;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;
import com.marcuslorenzana.imageobjectdetector.repositories.ImageMetadataEntityRepository;
import com.marcuslorenzana.imageobjectdetector.repositories.ObjectEntityRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.marcuslorenzana.imageobjectdetector.utilities.ImaggaAPIUtils.generateLabelFromFilename;

/**
 * Service to handle business logic of CRUD operations on ImageMetadataEntity. Interacts with ImaggaAPIService
 * and the ImageMetadataEntity as well as the ObjectEntity repositories.
 */
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

    /**
     * Retrieve all images, optionally by object names.
     *
     * @param objects: string
     * @return List<ImageMetadataEntity>
     */
    @Transactional
    public List<ImageMetadataModel> getAllImages(String objects) {
        List<ImageMetadataEntity> images;
        if (objects != null && !objects.isEmpty()) {
            List<String> objectNames = List.of(objects.split(","));
            images = this.imageMetadataEntityRepository.findByObjectNames(objectNames);
        } else {
            images = this.imageMetadataEntityRepository.findAll();
        }
        return ImageMetadataMapper.INSTANCE.entitiesToModels(images);
    }

    /**
     * Retrieve an image by id.
     *
     * @param id: long
     * @return ImageMetadataEntity
     */
    @Transactional
    public ImageMetadataModel getImageById(long id) {
        Optional<ImageMetadataEntity> entity = this.imageMetadataEntityRepository.findById(id);
        if (entity.isPresent()) {
            return ImageMetadataMapper.INSTANCE.entityToModel(entity.get());
        } else {
            logger.info("Could not find the image with ID " + id + " in the DB.");
            return null;
        }
    }


    /**
     * Creates an image and evaluates it for any objects, then saves to the repository.
     *
     * @param image: ImageMetadataRequest
     * @return ImageMetadataEntity
     */
    @Transactional
    public ImageMetadataModel createImage(ImageMetadataRequest image) throws IOException {
        ImageMetadataEntity entity = null;
        if (image.getLabel() == null || image.getLabel().isEmpty()) {
            String generatedLabel = generateLabelFromFilename(image.getImageSource());
            image.setLabel(generatedLabel);
        }
        if (image.getEnableObjectDetection()) {
            entity = this.imaggaAPIService.retrieveObjectsFromImage(image);
            List<ObjectEntity> objectEntities = this.objectEntityRepository.saveAllAndFlush(entity.getObjects());
            entity.setObjects(objectEntities);
        } else {
            ImageMetadataModel model = ImageRequestToModelMapper.map(image);
            entity = ImageMetadataMapper.INSTANCE.modelToEntity(model);
        }

        if (!image.getDryRun()) {
            this.imageMetadataEntityRepository.saveAndFlush(entity);
        }
        return ImageMetadataMapper.INSTANCE.entityToModel(entity);
    }
}
