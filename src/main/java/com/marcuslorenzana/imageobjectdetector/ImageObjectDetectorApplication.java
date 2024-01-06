package com.marcuslorenzana.imageobjectdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application is a REST API for storing and detecting images alongside any objects that are detected within
 * them using the Imagga API.
 *
 * @author Marcus Lorenzana
 */
@SpringBootApplication
public class ImageObjectDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageObjectDetectorApplication.class, args);
    }

}
