package com.github.felipovski.animal_adoption;

import com.github.felipovski.animal_adoption.control.dto.AnimalDto;
import com.github.felipovski.animal_adoption.control.mapper.AnimalMapper;
import com.github.felipovski.animal_adoption.control.service.AnimalService;
import com.github.felipovski.animal_adoption.entity.model.Animal;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalCategory;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;
import com.github.felipovski.animal_adoption.entity.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AnimalMapper animalMapper;

    @InjectMocks
    private AnimalService animalService;

    private static Animal mockAnimal;

    static {
        mockAnimal = new Animal();
        mockAnimal.setId(1L);
        mockAnimal.setStatus(AnimalStatus.AVAILABLE);
        mockAnimal.setName("Stálin");
        mockAnimal.setCategory(AnimalCategory.DOG);
        mockAnimal.setImageUrl("imageUrl");
        mockAnimal.setDescription("description");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAvailableAnimals() {

        when(animalRepository.findByStatus(AnimalStatus.AVAILABLE)).thenReturn(List.of(mockAnimal));

        List<Animal> animals = animalService.getAvailableAnimals();

        assertEquals(1, animals.size());
        assertEquals("Stálin", animals.get(0).getName());
    }

    @Test
    void testFindById() {
        AnimalDto mockAnimalDto = new AnimalDto("Channel", "Amistoso", "url", AnimalCategory.CAT, Instant.now(), AnimalStatus.AVAILABLE);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(mockAnimal));
        when(animalMapper.toDto(mockAnimal)).thenReturn(mockAnimalDto);

        var animalDto = animalService.findById(1L);

        assertNotNull(animalDto);
        assertEquals("Channel", animalDto.name());
    }

    @Test
    void testPersistAnimal() {
        AnimalDto mockAnimalDto = new AnimalDto("Stálin", "description", "url", AnimalCategory.DOG, Instant.now(), AnimalStatus.AVAILABLE);

        when(animalMapper.fromDto(mockAnimalDto)).thenReturn(mockAnimal);
        when(animalRepository.save(mockAnimal)).thenReturn(mockAnimal);

        Long savedId = animalService.persistAnimal(mockAnimalDto);

        assertEquals(1L, savedId);
    }

    @Test
    void testGetAnimalsPaged() {
        var now = Instant.now();
        AnimalDto mockAnimalDto = new AnimalDto("Stálin", "description", "url", AnimalCategory.DOG, now, AnimalStatus.AVAILABLE);

        Page<Animal> animalPage = new PageImpl<>(List.of(mockAnimal));
        when(animalRepository.findAll(any(Pageable.class))).thenReturn(animalPage);
        when(animalMapper.toDtoList(animalPage.stream().toList())).thenReturn(List.of(mockAnimalDto));

        List<AnimalDto> animals = animalService.getAnimals(Pageable.ofSize(10));

        assertEquals(1, animals.size());
        assertEquals("Stálin", mockAnimalDto.name());
        assertEquals("description", mockAnimalDto.description());
        assertEquals(AnimalCategory.DOG, mockAnimalDto.category());
        assertEquals(now, mockAnimalDto.birthDate());
        assertEquals(AnimalStatus.AVAILABLE, mockAnimalDto.status());
    }

    @Test
    void testDeleteAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(mockAnimal));

        animalService.deleteAnimal(1L);

        verify(animalRepository, times(1)).delete(mockAnimal);
    }

    @Test
    void testUpdateAnimalStatus() {
        AnimalDto updatedAnimalDto = new AnimalDto("Stálin", "Meu pequeno ditador", "url", AnimalCategory.DOG, Instant.now(), AnimalStatus.ADOPTED);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(mockAnimal));
        when(animalRepository.save(mockAnimal)).thenReturn(mockAnimal);
        when(animalMapper.toDto(mockAnimal)).thenReturn(updatedAnimalDto);

        AnimalDto result = animalService.updateStatus(1L, "ADOPTED");

        assertNotNull(result);
        assertEquals(AnimalStatus.ADOPTED, result.status());
    }
}
