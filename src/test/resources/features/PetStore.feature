Feature: PetStore API Tests

  Background:
    Given the PetStore API is available
    And a pet is created

  Scenario: Create a new pet
    Then the response status code should be 200
    * the response header "Content-Type" should be "application/json"
    * the response time should be less than 2000 milliseconds
    * the response body field "name" should be the pet's name

  Scenario: Get pet by ID
    When I get the pet by ID
    Then the response status code should be 200

  Scenario: Delete a pet
    When I delete the pet
    Then the response status code should be 200

  Scenario: Update pet name and status
    When I update the pet's name to "luna" and status to "sold"
    Then the response status code should be 200

  Scenario: Upload pet image
    When I upload an image for the pet
    Then the response status code should be 200