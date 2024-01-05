package com.marcuslorenzana.imageobjectdetector.repositories;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMetadataEntityRepository extends JpaRepository<ImageMetadataEntity, Long> {
    // For example, a method to find all images containing a specific object
    List<ImageMetadataEntity> findByObjects_Name(String objectName);
}