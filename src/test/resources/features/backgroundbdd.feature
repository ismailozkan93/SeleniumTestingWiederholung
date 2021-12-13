Feature: Understanding Background
  @tp
  Background:
    Given TC01 goes to "https://www.gmibank.com"
    And   user writes valid "group16Employee" and valid "Employee12"


  Scenario: TC01 clicks on Manage Customer
    And After success login goes to My Operations and ManageCustomer click
    Then verify with text of Customer

  Scenario: TC01 clicks on Manage Accounts
    And goes to to My Operations and ManageAccounts click
    Then verify with text of Accounts
