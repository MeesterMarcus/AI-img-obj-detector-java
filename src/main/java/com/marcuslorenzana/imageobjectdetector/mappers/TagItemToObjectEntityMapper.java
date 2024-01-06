package com.marcuslorenzana.imageobjectdetector.mappers;

import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiTagItem;

/**
 * Maps a Imagga TagItem to an ObjectEntity
 */
public class TagItemToObjectEntityMapper {
    public static ObjectEntity map(ImaggaApiTagItem tagItem) {
        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setName(tagItem.getTag().getEn());
        return objectEntity;
    }
}
