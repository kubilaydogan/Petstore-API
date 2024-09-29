package Assertions;

import Assertions.Conditions.BodyFieldCondition;
import Assertions.Conditions.HeaderCondition;
import Assertions.Conditions.ResponseTimeCondition;
import Assertions.Conditions.StatusCodeCondition;
import io.restassured.RestAssured;
import models.pet.Pet;
import org.junit.jupiter.api.Test;
import service.PetService;
import utils.DataGenerator;

public class ConditionUtils {
    public static StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }

    public static BodyFieldCondition bodyField(String jsonPath, Object expectedValue) {
        return new BodyFieldCondition(jsonPath, expectedValue);
    }

    public static HeaderCondition header(String headerName, String expectedValue) {
        return new HeaderCondition(headerName, expectedValue);
    }

    public static ResponseTimeCondition responseTime(int expectedTime) {
        return new ResponseTimeCondition(expectedTime);
    }


    @Test
    void unitTest() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        PetService petService = new PetService();
        Pet pet = DataGenerator.generateNewPet();
        petService.createPet(pet)
                .should(statusCode(200))
                .should(header("Content-Type", "application/json"))
                .should(bodyField("status", pet.getStatus().toString()))
                .should(responseTime(500));
    }
}