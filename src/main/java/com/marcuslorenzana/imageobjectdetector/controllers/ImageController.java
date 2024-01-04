package com.marcuslorenzana.imageobjectdetector.controllers;

import com.marcuslorenzana.imageobjectdetector.models.ImageMetadata;
import com.marcuslorenzana.imageobjectdetector.services.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping()
    public List<ImageMetadata> getAllImages(@RequestParam(value="objects", required = false) String objects) {
        return this.imageService.getAllImages(objects);
    }

    @GetMapping("{id}")
    public ImageMetadata getImageById(@PathVariable("id") String itemId) {
        return this.imageService.getImageById(itemId);
    }

    @PostMapping()
    public ImageMetadata createImage(@RequestBody ImageMetadata image) {
        return this.imageService.createImage(image);
    }
}
