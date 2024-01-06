package com.marcuslorenzana.imageobjectdetector.controllers;

import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataModel;
import com.marcuslorenzana.imageobjectdetector.models.ImageMetadataRequest;
import com.marcuslorenzana.imageobjectdetector.services.ImageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controller to handle retrieving all images (optionally by object names), retrieve image by id, and creating an image
 * with objects detected (if enabled).
 */
@RestController
@RequestMapping("api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping()
    public List<ImageMetadataModel> getAllImages(@RequestParam(value = "objects", required = false) String objects) {
        return this.imageService.getAllImages(objects);
    }

    @GetMapping("{id}")
    public ImageMetadataModel getImageById(@PathVariable("id") Long itemId) {
        return this.imageService.getImageById(itemId);
    }

    @PostMapping()
    public ImageMetadataModel createImage(@RequestBody @Valid ImageMetadataRequest image) throws IOException {
        return this.imageService.createImage(image);
    }
}
