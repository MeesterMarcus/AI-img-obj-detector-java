package com.marcuslorenzana.imageobjectdetector.mappers;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMetadataMapper {

    ImageMetadataMapper INSTANCE = Mappers.getMapper(ImageMetadataMapper.class);

    ImageMetadataModel entityToModel(ImageMetadataEntity entity);

    ImageMetadataEntity modelToEntity(ImageMetadataModel model);

    List<ImageMetadataModel> entitiesToModels(List<ImageMetadataEntity> entities);

    List<ImageMetadataEntity> modelsToEntities(List<ImageMetadataModel> models);
}
