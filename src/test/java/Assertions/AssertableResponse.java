package Assertions;

import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.Matchers.equalTo;

@RequiredArgsConstructor
public class AssertableResponse {

    public final ValidatableResponse response;

    // The `as` method convert the response body to the desired type.
    public <T> T as(Class<T> tClass) {
        return response.extract().body().as(tClass);
    }

    public AssertableResponse should(Condition condition) {
        condition.check(response);
        return this;
    }

    // Method to assert status code
    public AssertableResponse assertStatusCode(int statusCode) {
        response.statusCode(statusCode);
        return this;
    }

    // Method to assert a specific header
    public AssertableResponse assertHeader(String headerName, String expectedValue) {
        response.header(headerName, expectedValue);
        return this;
    }

    // Method to assert a specific value in the response body
    public AssertableResponse assertBody(String path, Object expectedValue) {
        response.body(path, equalTo(expectedValue));
        return this;
    }
}


