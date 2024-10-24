package com.github.felipovski.animal_adoption.boundary;

import com.github.felipovski.animal_adoption.control.service.AnimalService;
import com.github.felipovski.animal_adoption.control.dto.AnimalDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/animals")
@Tag(name = "animals")
public class AnimalController {

    Logger logger = LoggerFactory.getLogger(AnimalController.class);

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Operation(summary = "Returns all the animals from the database")
    @GetMapping
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = AnimalDto.class))))
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        return ResponseEntity.ok(animalService.listAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AnimalDto>> filterAnimals(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(animalService.getAnimals(pageable));
    }

    @Operation(summary = "Returns an animal for a given identifier")
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimalDto.class)))
    @ApiResponse(responseCode = "204", description = "The animal is not found for a given identifier")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable Long id) {
        var dto = animalService.findById(id);
        if (Objects.nonNull(dto)) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Creates a valid hero")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "The URI of the created hero on the header")
    @Transactional
    public ResponseEntity<URI> createAnimal(@RequestBody AnimalDto dto) {
        Long id = animalService.persistAnimal(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates an exiting animal")
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "The updated animal", content = @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = AnimalDto.class)))
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable Long id, @RequestBody AnimalDto dto) {
        var updated = animalService.updateAnimal(id, dto);
        logger.debug("Hero updated with new valued {}", updated);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Updates an exiting animal status")
    @PutMapping("/{id}/status")
    @ApiResponse(responseCode = "200", description = "The updated animal", content = @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = AnimalDto.class)))
    public ResponseEntity<AnimalDto> updateStatus(@PathVariable Long id, @RequestParam String status) {

        return ResponseEntity.ok(animalService.updateStatus(id, status));
    }

    @Operation(summary = "Deletes an exiting animal")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        logger.debug("Animal deleted with %d{}", id);
        return ResponseEntity.noContent().build();
    }
}