package com.marcuslorenzana.imageobjectdetector.mappers;

import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.models.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ObjectMapper {

    ImageMetadataMapper INSTANCE = Mappers.getMapper(ImageMetadataMapper.class);

    ObjectModel entityToModel(ObjectEntity entity);

    ObjectEntity modelToEntity(ObjectModel model);

    List<ObjectModel> entitiesToModels(List<ObjectEntity> entities);

    List<ObjectEntity> modelsToEntities(List<ObjectModel> models);
}
