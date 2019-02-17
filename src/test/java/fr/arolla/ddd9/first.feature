Feature: default cart
  Background
    Given "fr_FR" as default locale
    Given the following catalog:
      | name  | description | EAN |
      | bike  | A default bike  | 8018417209109 |
    Given the following shipping services:
      | name | carrier | level |
      | Chrono10 | Chronopost | j+1 avant 13h |

  Scenario: list product
    When product catalog is called
    Then should be returned a product list containing:
      | bike | 8018417209109 |

  Scenario: add product to new cart
    Given a new cart is created
    And product 8018417209109 is added to this cart
    When cart content is asked
    Then should be returned a cart item list containing:
      | bike | 8018417209109 |

  Scenario: get default
    Given a new cart is created
    And product 8018417209109 is added to this cart
    And "75009 Paris" in "FR" is set as the shipping address of this cart
    When the shipping services available for this cart is asked
    Then should be returned the following shopping services:
      | Chrono10 | Chronopost j+1 avant 13h |
