package com.marcuslorenzana.imageobjectdetector.mappers;

import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiTagItem;

public class TagItemToObjectEntityMapper {
    public static ObjectEntity map(ImaggaApiTagItem tagItem) {
        ObjectEntity objectEntity = new ObjectEntity();
        // Assuming you want to map the English name of the tag to the name of the ObjectEntity
        objectEntity.setName(tagItem.getTag().getEn());
        return objectEntity;
    }
}
