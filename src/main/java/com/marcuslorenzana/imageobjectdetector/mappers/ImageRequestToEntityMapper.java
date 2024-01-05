package com.marcuslorenzana.imageobjectdetector.mappers;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;

import java.util.List;

public class ImageRequestToEntityMapper {
    public static ImageMetadataEntity map(ImageMetadataRequest metadataRequest, List<ObjectEntity> objectEntityList) {
        ImageMetadataEntity entity = new ImageMetadataEntity();
        entity.setImageSource(metadataRequest.getImageSource());
        entity.setLabel(metadataRequest.getLabel());
        entity.setObjects(objectEntityList);
        return entity;
    }
}
