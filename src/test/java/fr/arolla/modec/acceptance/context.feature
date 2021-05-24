Feature: default contexts

  Scenario: default background
    Given the default background
    Then now should be "2018-10-08T13:00:00+01:00"
    Then "fr_FR" should be the default locale
    Then the following products should be in the catalog, and no others:
    | SKU           | name    | description     | weight |
    | 7612345678900 | bike    | A default bike  | 10.0   |
    | 7612345678101 | feather | A light product | 0.1    |
    Then following shipping services should be declared, and no others:
    | code     | label                      |
    | Chrono10 | Chronopost j+1 avant 13h   |
    | laposte  | La Poste Standard          |
