Feature: test shipping service availability in carts

  Background:
    Given the following mocked shipping services:
      | code     | carrier    | level         |
      | Chrono10 | Chronopost | j+1 avant 13h |
    Given a current Cart

  Scenario: empty cart should have no shipping service
    Then shipping service for the current cart should be empty

  Scenario: filled cart with no shipping address should have no shipping service
    When the following products are added to the current cart:
      | sku  | name  | quantity |
      | aSku | aName | 1        |
    Then shipping service for the current cart should be empty

  Scenario: empty cart with a shipping address should have no shipping service
    When "Full Name", "Addresse Line 1", "City", "Zip Code" in "FR" is set as the shipping address of current cart
    Then shipping service for the current cart should be empty

  Scenario: filled cart with a shipping address should have default shipping service
    When "fullName", "line1", "city", "zipCode" in "FR" is set as the shipping address of current cart
    And the following products are added to the current cart:
      | sku  | name  | quantity |
      | aSku | aName | 1        |
    Then the shipping services available for the current cart should be:
      | code     | label                    |
      | Chrono10 | Chronopost j+1 avant 13h |
