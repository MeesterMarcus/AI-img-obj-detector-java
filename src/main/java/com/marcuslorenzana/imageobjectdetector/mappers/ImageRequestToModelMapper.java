package com.marcuslorenzana.imageobjectdetector.mappers;

import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataModel;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;

/**
 * Maps an ImageMetadataRequest model to a ImageMetadataEntity
 */
public class ImageRequestToModelMapper {
    public static ImageMetadataModel map(ImageMetadataRequest metadataRequest) {
        ImageMetadataModel model = new ImageMetadataModel();
        model.setImageSource(metadataRequest.getImageSource());
        model.setLabel(metadataRequest.getLabel());
        return model;
    }
}
