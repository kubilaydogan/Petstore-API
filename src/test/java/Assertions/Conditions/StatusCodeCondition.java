package Assertions.Conditions;

import Assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {
    private final int expectedStatusCode;

    @Override
    public void check(ValidatableResponse response) {
        int statusCode = response.extract().statusCode();
        assertEquals(expectedStatusCode, statusCode);
    }
}
