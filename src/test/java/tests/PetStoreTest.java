package tests;

import models.info.ApiResponse;
import models.pet.Pet;
import models.pet.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import service.PetService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DataGenerator;

import java.io.File;

import static Assertions.ConditionUtils.*;


public class PetStoreTest {
    private static PetService petService;
    private Pet pet;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        // adding logs for HTTP request & response
        // RestAssured.filters(new ResponseLoggingFilter());
        // RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        petService = new PetService();
    }

    @BeforeEach
    void init() {
        pet = DataGenerator.generateNewPet();
    }

    @Test
    void createPet() {
        petService.createPet(pet)
                // Condition validations using ConditionUtils
                .should(statusCode(200))
                .should(header("Content-Type", "application/json"))
                .should(bodyField("status", pet.getStatus().toString()))
                .should(responseTime(2000))
                // Custom validations using AssertableResponse Class
                .assertStatusCode(200)
                .assertHeader("Content-Type", "application/json")
                .assertBody("name", pet.getName());
    }

    @Test
    void getPetById() {
        int petId = petService.createPet(pet)
                .should(statusCode(200))
                .as(Pet.class).getId();

        petService.getPetById(petId)
                .should(statusCode(200));

    }

    @Test
    void deletePet() {
        int petId = petService.createPet(pet)
                .should(statusCode(200))
                .as(Pet.class).getId();

        ApiResponse info = petService.deletePet(petId).as(ApiResponse.class);
        Assertions.assertEquals(200, info.getCode());
    }
    @Test
    void updatePetNameStatus() {
        int petId = petService.createPet(pet)
                .should(statusCode(200))
                .as(Pet.class).getId();

        ApiResponse info = petService.updatePetNameStatus(petId,"asdasd", Status.sold)
                .should(statusCode(200))
                .as(ApiResponse.class);

        Assertions.assertEquals(200, info.getCode());
    }

    @Test
    void uploadPetImage(){
        File file = new File("src/test/resources/images/image1.jpg");

        int petId = petService.createPet(pet)
                .assertStatusCode(200)
                .as(Pet.class).getId();

        petService.uploadImage(petId, file).assertStatusCode(200);
    }
}
