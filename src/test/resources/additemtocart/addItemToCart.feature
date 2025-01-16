Feature: Verify item can be added to cart on eBay



  Scenario: User adds a book to the shopping cart

    Given the user is on the eBay homepage

    When the user searches for "book"

    And the user clicks on the first item in the search results

    And the user clicks on the "Add to cart" button

    Then the shopping cart should display "1" item