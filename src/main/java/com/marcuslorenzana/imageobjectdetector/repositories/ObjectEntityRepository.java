package com.marcuslorenzana.imageobjectdetector.repositories;

import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectEntityRepository extends JpaRepository<ObjectEntity, Long> {
    List<ObjectEntity> findByName(String name);
}
