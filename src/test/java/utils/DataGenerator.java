package utils;

import com.github.javafaker.Faker;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Status;
import models.pet.Tag;
import java.util.Arrays;

/*
{
        "id": 777,
        "name": "...",
        "photoUrls": [
        "...",
        "..."
        ],
        "category": {
            "id": 100,
            "name": "..."
        },
        "tags": [
            {
                "id": 200,
                "name": "..."
            },
            {
                "id": 300,
                "name": "..."
            }
        ],
        "status": "available"
        }
*/
public class DataGenerator {
    private static final Faker faker = new Faker();

    private static int getRandomId() {
        return faker.number().numberBetween(10, 1000);
    }

    public static Pet generateNewPet() {
        return Pet.builder()    // Starts the building process | Starts building a Pet object
                .id(getRandomId())
                .name(faker.pokemon().name())
                .photoUrls(Arrays.asList("photoUrl1", "photoUrl2"))
                .category(
                        Category.builder()
                                .id(getRandomId())                  // Sets the Category ID
                                .name(faker.animal().name())        // Sets the Category name
                                .build()
                )
                .tags(
                        Arrays.asList(
                                Tag.builder()
                                        .id(getRandomId())
                                        .name(faker.superhero().name())
                                        .build()
                        )
                )
                .status(Status.values()[faker.random().nextInt(Status.values().length)])    // [available, pending, sold][0, 1, 2]
                .build();  // Completes the building process | Finalizes the Pet object
    }

    public static void main(String[] args) {
        System.out.println(generateNewPet());
    }
}