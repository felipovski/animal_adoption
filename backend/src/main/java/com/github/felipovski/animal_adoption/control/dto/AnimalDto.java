package com.github.felipovski.animal_adoption.control.dto;

import com.github.felipovski.animal_adoption.entity.model.enums.AnimalCategory;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record AnimalDto(

        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String imageUrl,
        @NotNull
        AnimalCategory category,
        @NotNull
        Instant birthDate,
        @NotNull
        AnimalStatus status

) {
}
