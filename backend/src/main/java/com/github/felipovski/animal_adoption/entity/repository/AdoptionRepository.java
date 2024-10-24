package com.github.felipovski.animal_adoption.entity.repository;

import com.github.felipovski.animal_adoption.entity.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
