package com.github.felipovski.animal_adoption.entity.model;

import com.github.felipovski.animal_adoption.entity.model.enums.AnimalCategory;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "url_image")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnimalCategory category;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnimalStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AnimalCategory getCategory() {
        return category;
    }

    public void setCategory(AnimalCategory category) {
        this.category = category;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus animalStatus) {
        this.status = animalStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
