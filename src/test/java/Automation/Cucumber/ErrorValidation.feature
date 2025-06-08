@tag
Feature: Validating the error message of login page 

    @ErrorValidation    
    Scenario Outline: Negetive scenarios of login
      Given I landend on Ecommerce Page  
      When Logged in with email <email> and password <password>
      Then "Incorrect email or password." message is displayed

  Examples:
      | email                  | password      |  
      | taniya09@yopmail.com    | Tani@2613    | 