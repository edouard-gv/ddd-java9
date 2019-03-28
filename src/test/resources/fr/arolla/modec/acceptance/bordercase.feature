Feature: border cases

  Background:
    Given now is "2018-10-08T13:00:00+01:00"
    Given "fr_FR" as default locale
    Given the following catalog:
      | SKU           | name | description    | weight |
      | 7612345678900 | bike | A default bike | 10.0   |
    Given the following shipping services:
      | code     | carrier    | level         |
      | Chrono10 | Chronopost | j+1 avant 13h |

  Scenario: no shipping service if no addresses
    Given a new cart is created
    And product "7612345678900" is added to this cart
    Then the shipping services available for this cart should be:
      | code | label |

  Scenario: cannot finalize order with no user
    Given a new cart is created
    And product "7612345678900" is added to this cart
    And "Jeanne Martin", "1 rue de Maronniers", "75009", "Paris" in "FR" is set as the shipping address of this cart
    Then order should not be validated
