package com.github.felipovski.animal_adoption;

import com.github.felipovski.animal_adoption.control.dto.AnimalDto;
import com.github.felipovski.animal_adoption.control.service.AnimalService;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalCategory;
import com.github.felipovski.animal_adoption.entity.model.enums.AnimalStatus;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AnimalAdoptionApplication.class)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimalControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AnimalService animalService;

    public static PostgreSQLContainer<?> getPostgresContainer() {
        return postgresContainer;
    }

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    public static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeAll
    public static void setup() {
        RestAssured.port = 8083;
    }

    @Test
    @Order(1)
    public void testGetAllAnimals() {
        var dtoList = given()
                .port(port)
                .when()
                .get("/animals")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(new TypeRef<List<AnimalDto>>() {
                });

        assertEquals(0, dtoList.size());
    }

    @Test
    @Order(2)
    public void testGetAnimalById() {
        Long animalId = animalService.persistAnimal(new AnimalDto("Stálin", "Pequeno ditador", "url", AnimalCategory.DOG, Instant.now(), AnimalStatus.AVAILABLE));

        var animalDto = given()
                .port(port)
                .pathParam("id", animalId)
                .when()
                .get("/animals/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(AnimalDto.class);

        assertEquals("Stálin", animalDto.name());
        assertEquals("Pequeno ditador", animalDto.description());
        assertEquals(AnimalStatus.AVAILABLE, animalDto.status());
        assertEquals(AnimalCategory.DOG, animalDto.category());

    }

    @Test
    @Order(3)
    public void testCreateAnimal() {
        AnimalDto newAnimal = new AnimalDto("Channel", "Gato esperto", "url", AnimalCategory.CAT, Instant.now(), AnimalStatus.ADOPTED);

        var uri = given()
                .port(port)
                .contentType("application/json")
                .body(newAnimal)
                .when()
                .post("/animals")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .header("Location");

        assertNotNull(uri);
    }

    @Test
    @Order(4)
    public void testUpdateAnimal() {
        Long animalId = animalService.persistAnimal(new AnimalDto("Bode", "Late feito bode", "url", AnimalCategory.DOG, Instant.now(), AnimalStatus.AVAILABLE));

        AnimalDto updatedAnimal = new AnimalDto("Bode", "Acha que é um bode", "url", AnimalCategory.DOG, Instant.now(), AnimalStatus.ADOPTED);

        var dto = given()
            .port(port)
            .contentType("application/json")
            .body(updatedAnimal)
            .pathParam("id", animalId)
            .when()
            .put("/animals/{id}")
            .then()
            .statusCode(HttpStatus.OK.value())
                .extract().as(AnimalDto.class);

        assertEquals(AnimalStatus.ADOPTED, dto.status());
        assertEquals("Acha que é um bode", dto.description());
    }

    @Test
    @Order(5)
    public void testGetAllAnimals_ShouldReturn3Animals() {
        var dtoList = given()
                .port(port)
                .when()
                .get("/animals")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(new TypeRef<List<AnimalDto>>() {
                });

        assertEquals(3, dtoList.size());
    }

    @Test
    @Order(6)
    public void testDeleteAnimal() {
        given()
            .port(port)
            .pathParam("id", 1)
            .when()
            .delete("/animals/{id}")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Order(7)
    public void testGetAllAnimals_ShouldReturn2Animals() {
        var dtoList = given()
                .port(port)
                .when()
                .get("/animals")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(new TypeRef<List<AnimalDto>>() {
                });

        assertEquals(2, dtoList.size());
    }
}
