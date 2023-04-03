Feature: Login

  @Sanity
  Scenario: Login with valid credentials
    Given User is on the login page
    When User enters valid username "standard_user" and password "secret_sauce"
    And User clicks on Login button
    Then User should be redirected to the dashboard page
    And Close browser
