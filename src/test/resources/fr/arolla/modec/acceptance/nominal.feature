Feature: default cart

  Background:
    Given the default background
    Then the following products should be in the catalog, and no others:
      | SKU           | name    | description     | weight |
      | 7612345678900 | bike    | A default bike  | 10.0   |
      | 7612345678101 | feather | A light product | 0.1    |

  Scenario: E2E with intermediate checks
    Given a new cart is created
    When product "7612345678900" is added to this cart
    Then cart content should contain:
      | name | SKU           | quantity |
      | bike | 7612345678900 | 1        |
    When "Jeanne Martin", "1 rue de Maronniers", "75009", "Paris" in "FR" is set as the shipping address of this cart
    And "Jean Martin" with email address "jean.martin@arolla.fr" is set as the recipient
    Then the shipping services available for this cart should be:
      | code     | label                    |
      | Chrono10 | Chronopost j+1 avant 13h |
    When order is validated
    Then the historical orders for recipient "jean.martin@arolla.fr" should be:
      | creation date | status  |
      | 2018-10-08    | created |
    When delivery is validated
    Then the historical orders for recipient "jean.martin@arolla.fr" should be:
      | creation date | status         |
      | 2018-10-08    | in preparation |
