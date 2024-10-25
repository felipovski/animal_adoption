package com.github.felipovski.animal_adoption.control.service;

import com.github.felipovski.animal_adoption.control.dto.AnimalDto;
import com.github.felipovski.animal_adoption.control.dto.AnimalResponseDto;
import com.github.felipovski.animal_adoption.control.exception.custom.AnimalNotFoundByIdException;
import com.github.felipovski.animal_adoption.control.mapper.AnimalMapper;
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

    private final AnimalMapper animalMapper;

    public AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    public List<Animal> getAvailableAnimals() {
        return animalRepository.findByStatus(AnimalStatus.AVAILABLE);
    }

    public List<AnimalResponseDto> listAll() {
        return animalMapper.toDtoList(animalRepository.findAll());
    }

    public AnimalResponseDto findById(Long id) {
        return animalRepository.findById(id)
                .map(animalMapper::toDto)
                .orElse(null);
    }

    public List<AnimalResponseDto> getAnimals(Pageable pageable) {
        Page<Animal> page = animalRepository.findAll(pageable);
        return animalMapper.toDtoList(page.stream().toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long persistAnimal(@Valid AnimalDto dto) {
        var animal = animalMapper.fromDto(dto);
        var persisted = animalRepository.save(animal);
        return persisted.getId();
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AnimalResponseDto updateAnimal(Long id, @Valid AnimalDto dto) {

        var entity = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundByIdException::new);
        entity.setName(dto.name());
        entity.setCategory(dto.category());
        entity.setStatus(dto.status());
        entity.setDescription(dto.description());
        entity.setBirthDate(dto.birthDate());
        entity.setImageUrl(dto.imageUrl());

        return animalMapper.toDto(animalRepository.save(entity));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAnimal(Long id) {
        animalRepository.findById(id)
                .ifPresent(animalRepository::delete);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AnimalResponseDto updateStatus(Long id, String status) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setStatus(AnimalStatus.getByStatus(status));
                    var updated = animalRepository.save(animal);
                    return animalMapper.toDto(updated);
                })
                .orElseThrow(AnimalNotFoundByIdException::new);
    }
}
