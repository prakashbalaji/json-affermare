Feature: Authors

  Background: :
    Given I setup data
    Then I start server

  Scenario: Verify collection for present of items
    Then I make a GET to "authors/all.json"
    Then I verify that the following authors are present
      | id  | name   |
      | 123 | Fowler |
      | 124 | Beck   |

  Scenario: Verify collection for items not present
    Then I make a GET to "authors/all.json"
    Then I verify that the following authors are not present
      | id  | name  |
      | 125 | Vijay |

  #Scenario: Verify collection for items not present
  #  Then I make a GET to "authors/all.json"
  #  Then I verify that the following authors are not present
  #    | id  | name  |
  #    | 124 | Vijay |
  #    | 125 | Beck  |

  Scenario: Verify collection for empty set
    Then I make a GET to "authors/empty.json"
    Then I verify that no authors are present


  Scenario: Verify collection for count
    Then I make a GET to "authors/all.json"
    Then I verify that the number of authors are "2"

  Scenario: Verify a item
    Then I make a GET to "authors/123.json"
    Then I verify that the following author is present
      | id  | name   |
      | 123 | Fowler |


  Scenario: Verify a item with associated collection
    Then I make a GET to "authors/123.json"
    Then I verify that the following author is present
      | id  | name   |
      | 123 | Fowler |
    Then I verify that author has the following "phone_numbers"
      | type   | number    |
      | office | 987654321 |
      | home   | 123456789 |



