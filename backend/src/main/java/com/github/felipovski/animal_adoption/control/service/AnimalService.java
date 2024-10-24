package com.github.felipovski.animal_adoption.control.service;

import com.github.felipovski.animal_adoption.control.dto.AnimalDto;
import com.github.felipovski.animal_adoption.entity.model.Animal;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;
import com.github.felipovski.animal_adoption.entity.repository.AnimalRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAvailableAnimals() {
        return animalRepository.findByStatus(AnimalStatus.AVAILABLE);
    }

    public List<AnimalDto> listAll() {
        return null;
    }

    public AnimalDto findById(Long id) {
        return null;
    }

    public List<AnimalDto> getAnimals(Pageable pageable) {
        Page<Animal> page = animalRepository.findAll(pageable);
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long persistAnimal(@Valid AnimalDto dto) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AnimalDto updateAnimal(Long id, @Valid AnimalDto dto) {

      return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAnimal(Long id) {
        animalRepository.findById(id)
                .ifPresent(animalRepository::delete);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AnimalDto updateStatus(Long id, String status) {
        return null;
    }
}
