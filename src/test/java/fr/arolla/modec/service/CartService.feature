Feature: test shipping service availability in carts

  Background:
    Given the following mocked shipping services:
      | code     | carrier    | level         |
      | Chrono10 | Chronopost | j+1 avant 13h |
      | laposte  | La Poste   | Standard      |
    Given the following mocked products:
      | sku           | name  | description     | weight |
      | 7612345678900 | Heavy | A heavy product | 1      |
      | 7612345678101 | Light | A light product | 0.1    |
    Given a current Cart

  Scenario: empty cart should have no shipping service
    Then shipping service for the current cart should be empty

  Scenario: filled cart with no shipping address should have no shipping service
    When the following products are added to the current cart:
      | sku           | name  | quantity |
      | 7612345678900 | Heavy | 1        |
    Then shipping service for the current cart should be empty

  Scenario: empty cart with a shipping address should have no shipping service
    When "Full Name", "Addresse Line 1", "City", "Zip Code" in "FR" is set as the shipping address of current cart
    Then shipping service for the current cart should be empty

  Scenario: heavy cart should have default shipping service
    When "fullName", "line1", "city", "zipCode" in "FR" is set as the shipping address of current cart
    And the following products are added to the current cart:
      | sku           | name  | quantity |
      | 7612345678900 | Heavy | 1        |
    Then the shipping services available for the current cart should be:
      | code     | label                    |
      | Chrono10 | Chronopost j+1 avant 13h |


  Scenario: light cart should have laposte shipping service
    When "fullName", "line1", "city", "zipCode" in "FR" is set as the shipping address of current cart
    And the following products are added to the current cart:
      | sku           | name  | quantity |
      | 7612345678101 | Light | 1        |
    Then the shipping services available for the current cart should be:
      | code     | label                    |
      | laposte  | La Poste Standard        |
