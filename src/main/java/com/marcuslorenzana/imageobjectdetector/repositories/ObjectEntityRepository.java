package com.marcuslorenzana.imageobjectdetector.repositories;

import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to interfact with ObjectEntity data.
 */
@Repository
public interface ObjectEntityRepository extends JpaRepository<ObjectEntity, Long> {

}
