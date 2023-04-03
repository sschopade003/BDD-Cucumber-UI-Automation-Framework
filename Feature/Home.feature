Feature: Dashboard

  @Regression
  Scenario: Add item to cart and remove it
    Given User is on the homepage
    And User adds the item to the cart
    And User goes to the cart
    And User removes the item from the cart
    Then User continue with shopping
    And Close browser
