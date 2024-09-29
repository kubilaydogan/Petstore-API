package BDD_example;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import models.pet.Pet;
import models.pet.Status;
import service.PetService;
import utils.DataGenerator;
import java.io.File;
import static Assertions.ConditionUtils.*;

public class PetStoreSteps {
    private static PetService petService;
    private Pet pet;
    private int petId;

    @Given("the PetStore API is available")
    public void thePetStoreAPIIsAvailable() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        petService = new PetService();
    }

    @Given("a pet is created")
    public void aPetIsCreated() {
        pet = DataGenerator.generateNewPet();
        petId = petService.createPet(pet)
                .should(statusCode(200))
                .as(Pet.class).getId();
    }

    @When("I get the pet by ID")
    public void iGetThePetByID() {
        petService.getPetById(petId).should(statusCode(200));
    }

    @When("I delete the pet")
    public void iDeleteThePet() {
        petService.deletePet(petId).should(statusCode(200));
    }

    @When("I update the pet's name to {string} and status to {string}")
    public void iUpdateThePetSNameToAndStatusTo(String name, String status) {
        petService.updatePetNameStatus(petId, name, Status.valueOf(status))
                .should(statusCode(200));
    }

    @When("I upload an image for the pet")
    public void iUploadAnImageForThePet() {
        File file = new File("src/test/resources/images/image1.jpg");
        petService.uploadImage(petId, file).assertStatusCode(200);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        petService.createPet(pet).should(statusCode(statusCode));
    }

    @Then("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String header, String value) {
        petService.createPet(pet).should(header(header, value));
    }

    @Then("the response body field {string} should be the pet's name")
    public void theResponseBodyFieldShouldBeThePetS(String field) {
        petService.createPet(pet).should(bodyField(field, pet.getName()));
    }

    @Then("the response time should be less than {int} milliseconds")
    public void theResponseTimeShouldBeLessThanMilliseconds(int time) {
        petService.createPet(pet).should(responseTime(time));
    }
}