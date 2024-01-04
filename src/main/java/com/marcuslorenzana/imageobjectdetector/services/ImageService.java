package com.marcuslorenzana.imageobjectdetector.services;

import com.marcuslorenzana.imageobjectdetector.models.ImageMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Value("${app.IMAGGA_API_ENDPOINT}")
    private String imaggaBaseUrl;

    @Value("${app.IMAGGA_API_AUTH}")
    private String imaggaAuth;

    public List<ImageMetadata> getAllImages(String objects) {
        System.out.println(imaggaBaseUrl);
        System.out.println(imaggaAuth);
        System.out.println(objects);
        List<ImageMetadata> images = new ArrayList<>();
        return images;
    }

    public ImageMetadata getImageById(String itemId) {
        System.out.println(itemId);
        return null;
    }

    public ImageMetadata createImage(ImageMetadata image) {
        System.out.println(image);
        return image;
    }
}
