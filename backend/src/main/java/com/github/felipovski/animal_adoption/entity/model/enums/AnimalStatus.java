package com.github.felipovski.animal_adoption.entity.model.enums;

public enum AnimalStatus {
    AVAILABLE, ADOPTED;

    public static AnimalStatus getByStatus(String status) {
        return AnimalStatus.valueOf(status);
    }
}
