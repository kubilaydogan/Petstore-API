package Assertions.Conditions;

import Assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AllArgsConstructor
public class ResponseTimeCondition implements Condition {
    private final long maxResponseTime;

    @Override
    public void check(ValidatableResponse response) {
        long responseTime = response.extract().time();
        assertTrue(responseTime <= maxResponseTime, "Response time exceeded");
    }
}
