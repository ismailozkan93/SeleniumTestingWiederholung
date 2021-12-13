@api
  Feature: Gmi Bank Api Test
    Scenario: Read all customer
      Given user read all customer and sets response using to api endpoint "https://www.gmibank.com/api/tp-customers"
      And user deserialization customer data json to java pojo
      Then user validates all data

  Scenario: Read all countries you created and validate them from your data set
    Given user goes to "https://www.gmibank.com/api/tp-account-registrations"
    And user validates all data with new data



