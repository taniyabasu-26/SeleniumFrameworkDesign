@tag
Feature:  Purchase order from the Ecommerce site
  
  Background:
    Given I landend on Ecommerce Page  

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with email <email> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples:
      | email                  | password      |  productName      |
      | taniya09@yomail.com    | Tani@2613     |  ADIDAS ORIGINAL  | 
      