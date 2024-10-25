package com.github.felipovski.animal_adoption.control.mapper;

import com.github.felipovski.animal_adoption.control.dto.AnimalDto;
import com.github.felipovski.animal_adoption.control.dto.AnimalResponseDto;
import com.github.felipovski.animal_adoption.entity.model.Animal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
 
    AnimalResponseDto toDto(Animal entity);

    Animal fromDto(AnimalDto dto);

    List<AnimalResponseDto> toDtoList(List<Animal> entities);
}