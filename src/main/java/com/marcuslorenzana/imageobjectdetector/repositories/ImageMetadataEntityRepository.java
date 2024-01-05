package com.marcuslorenzana.imageobjectdetector.repositories;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for interacting with ImageMetadataEntity data.
 */
public interface ImageMetadataEntityRepository extends JpaRepository<ImageMetadataEntity, Long> {
    @Query("SELECT DISTINCT ime FROM ImageMetadataEntity ime JOIN ime.objects obj WHERE obj.name IN :names")
    List<ImageMetadataEntity> findByObjectNames(@Param("names") List<String> names);
}