package com.github.felipovski.animal_adoption.control.dto;

import com.github.felipovski.animal_adoption.entity.model.enums.AnimalCategory;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;

import java.time.Instant;

public record AnimalResponseDto(

        Long id,
        String name,
        String description,
        String imageUrl,
        AnimalCategory category,
        Instant birthDate,
        AnimalStatus status

) {
}
