package com.github.felipovski.animal_adoption.entity.repository;

import com.github.felipovski.animal_adoption.entity.model.Animal;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByStatus(AnimalStatus status);

    Page<Animal> findAll(Pageable pageable);
}
