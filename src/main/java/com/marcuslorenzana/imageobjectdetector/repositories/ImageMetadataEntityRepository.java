package com.marcuslorenzana.imageobjectdetector.repositories;

import com.marcuslorenzana.imageobjectdetector.entities.ImageMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ImageMetadataEntityRepository extends JpaRepository<ImageMetadataEntity, Long> {
    @Query("SELECT DISTINCT ime FROM ImageMetadataEntity ime JOIN ime.objects obj WHERE obj.name IN :names")
    List<ImageMetadataEntity> findByObjectNames(@Param("names") List<String> names);
}