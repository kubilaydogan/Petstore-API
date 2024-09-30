# [Petstore Sample API](https://petstoresampleapi.apimatic.dev/v/1_0_5#/http/how-to-get-started)


## About the Structure

### MODELS FOLDER
* Contains classes that represent the data structures used.
* It is common practice to keep POJO files under the models folder.
```
models
├── info
│   └── Response.java
└── pet
    ├── Pet.java
    ├── Category.java
    ├── Tag.java
    └── Status.java
```

**Tag.java**
```java
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @JsonProperty("id")
    private int id;
    
    @JsonProperty("name")
    private String name;
}
```

The annotations used in the `Tag` class are from the **Lombok** and **Jackson** libraries.

#### Lombok Annotations:
1. **`@Builder`**: Generates a builder pattern for the class, allowing for more readable and maintainable object creation.
2. **`@Data`**: A convenient shortcut annotation that bundles the features of `@ToString`, `@EqualsAndHashCode`, `@Getter`/`@Setter`, and `@RequiredArgsConstructor` together. It generates all the boilerplate code that is normally associated with simple POJOs.
3. **`@NoArgsConstructor`**: Generates a no-argument constructor.
4. **`@AllArgsConstructor`**: Generates a constructor with one parameter for each field in the class.

#### Jackson Annotations:
1. **`@JsonProperty`**: Used to specify the JSON property name for the field. This is useful when the JSON field name does not match the Java field name or when you want to ensure the correct mapping between JSON and Java objects.


**Pet.java**
```java
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    @JsonProperty("name")
    @NotNull
    private String name;                // Required field

    @JsonProperty("photoUrls")
    @NotNull
    private List<String> photoUrls;

    @JsonProperty("id")
    private int id;                     // Optional field

    @JsonProperty("category")
    private Category category;          // Optional field

    @JsonProperty("tags")
    private List<Tag> tags;

    @JsonProperty("status")
    private Status status;
}
```

> **Note**: <br>
> The `@NotNull` annotation is used to specify that the field is required and cannot be null.<br>
> To use @NotNull annotation, you need to add the following dependency to your project:
> ```xml
> <dependency>
>    <groupId>jakarta.validation</groupId>
>    <artifactId>jakarta.validation-api</artifactId>
>    <version>3.1.0</version>
> </dependency>


#### @Builder
* The `@Builder` annotation is used to generate a builder pattern for the class. 
* It provides a way to create objects without having to remember the order of the parameters.
* It also allows you to create objects with only the required fields, making the code more readable and maintainable.
* The `@Builder` annotation is used in conjunction with the `@AllArgsConstructor` annotation to generate a builder pattern with all the fields in the class.

**Sample of using the builder pattern to create a `Pet` object:**
```java
Pet pet = Pet.builder()                // Starts the building process | Starts building a Pet object 
              .name("dog")
              .photoUrls(Arrays.asList("url1", "url2"))
              .category(Category.builder().id(1).name("dog").build())
              .tags(Arrays.asList(Tag.builder().id(1).name("tag1").build()))
              .status(Status.AVAILABLE)
              .build();                // Completes the building process | Completes building a Pet object
``` 

### SERVICE FOLDER
* Contains classes that interact with the API endpoints.
* It is common practice to keep service classes under the service folder.
```
service
└── PetService.java
```

```java
public class PetService {

    public AssertableResponse createPet(Pet pet) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .header("api_key", "12345")
                .body(pet)
                .post("")
                .then());
    }

    // ...

    @SneakyThrows
    public AssertableResponse uploadImage(int petId, File file) {
        return new AssertableResponse(given()
                .contentType(ContentType.MULTIPART)
                .header("api_key", "12345")
                .pathParams("petId", petId)
                .multiPart("additionalMetadata", "sdfsdhhhh")
                .multiPart("file", "myFile", Files.readAllBytes(file.toPath()))
                .when()
                .post("/{petId}/uploadImage")
                .then());
    }
}
```

**Notes:**

1. **`@SneakyThrows`** annotation is used to suppress checked exceptions without the need to catch or rethrow them. It is a feature of the Lombok library that allows you to handle checked exceptions in a more concise way.
2. **`AssertableResponse`** is a custom class designed to wrap the response in an `AssertableResponse` object by calling `.then()`, which allows for further assertions and validations on the response.

<img src="/src/test/resources/images/validatableResponse.png" width="400px"></img>

