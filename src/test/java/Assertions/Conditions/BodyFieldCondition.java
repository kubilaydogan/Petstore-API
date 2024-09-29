package Assertions.Conditions;

import Assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
public class BodyFieldCondition implements Condition {
    private final String jsonPath;
    private final Object expectedValue;

    @Override
    public void check(ValidatableResponse response) {
        Object actualValue = response.extract().path(jsonPath);
        assertEquals(expectedValue, actualValue);
    }
}
