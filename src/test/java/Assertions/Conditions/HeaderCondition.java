package Assertions.Conditions;

import Assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
public class HeaderCondition implements Condition {
    private final String headerName;
    private final String expectedValue;

    @Override
    public void check(ValidatableResponse response) {
        String headerValue = response.extract().header(headerName);
        assertEquals(expectedValue, headerValue);
    }
}
