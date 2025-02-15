Feature: Login functionality

  Scenario Outline: Login with invalid credentials
    Given I visit the login page
    When I login with username "<username>" and password "<password>"
    Then I should see the "<expected_result>" error message

    Examples:
      | username                 | password      | expected_result                     |
      | invalid_username            | invalid_password  | Epic sadface: Username and password do not match any user in this service  |
      |  | secret_sauce  | Epic sadface: Username is required |
      | standard_user             |  | Epic sadface: Password is required |
      | locked_out_user          | secret_sauce  | Epic sadface: Sorry, this user has been locked out. |
