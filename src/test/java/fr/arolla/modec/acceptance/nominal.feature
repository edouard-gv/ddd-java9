Feature: default cart

  Background:
    Given now is "2018-10-08T13:00:00+01:00"
    Given "fr_FR" as default locale
    Given the following catalog:
      | name | description    | SKU  | weight |
      | bike | A default bike | sku1 | 10     |
    Given the following shipping services:
      | code     | carrier    | level         |
      | Chrono10 | Chronopost | j+1 avant 13h |

  Scenario: E2E with intermediate checks
    Then product catalog should contain:
      | name | SKU  |
      | bike | sku1 |
    Given a new cart is created
    And product "sku1" is added to this cart
    Then cart content should contain:
      | name | SKU  | quantity |
      | bike | sku1 | 1        |
    Given "Jeanne Martin", "1 rue de Maronniers", "75009", "Paris" in "FR" is set as the shipping address of this cart
    And "Jean Martin" with email address "jean.martin@arolla.fr" is set as the recipient
    Then the shipping services available for this cart should be:
      | code     | label                    |
      | Chrono10 | Chronopost j+1 avant 13h |
    Given order is validated
    Then the historical orders for recipient "jean.martin@arolla.fr" should be:
      | creation date | status  |
      | 2018-10-08    | created |
    Given delivery is validated
    Then the historical orders for recipient "jean.martin@arolla.fr" should be:
      | creation date | status         |
      | 2018-10-08    | in preparation |
