#Author: Priyanka
Feature: Login functionality of Hudl.com

  Scenario: Validate the login page of Hudl.com with valid email and password
    Given The user is on the Hudl website page
    When The user navigates to the login page
    And Enter the valid email
    And Enter the valid password
    And Click on the login
    Then The Homepage should be displayed
    And User must logout

  Scenario Outline: Validate the login functionality for invalid email and password
    Given The user is on the Hudl website page
    When The user navigates to the login page
    And Enter the invalid email <email>
    And Enter the invalid password <password>
    And Click on the login
    Then The login page should display error with fail status

    Examples: 
      | email                    | password     |
      | 'test@gmailcom'          | 'Password@1' |
      | 'priyanka.omg@gmail.com' | 'Password1'  |

  Scenario Outline: Validate if the links in the Login page are accessible
    Given The user is on the Hudl website page
    When The user navigates to the login page
    And Clicks on the link <link>
    Then The user should navigate to the desired page <page>

    Examples: 
      | link                          | page                                               |
      | 'Sign up'                     | 'https://www.hudl.com/register/signup'             |
      | 'Need help?'                  | 'https://www.hudl.com/login/help#'                 |
      | 'Log In with an Organization' | 'https://www.hudl.com/app/auth/login/organization' |

  Scenario: Validate the navigation from login page to Website page
    Given The user is on the Hudl website page
    When The user navigates to the login page
    And Clicks on the back arrow in the login page
    Then The user is on the Hudl website page
    When The user navigates to the login page
    And The user clicks on the Hudl logo in the login page
    Then The user is on the Hudl website page