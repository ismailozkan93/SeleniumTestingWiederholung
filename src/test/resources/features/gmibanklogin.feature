#each feature contains one feature
#Feature files use Gherkin language - business language
Feature: Gmibank Login
  @wer
  #A feature may have given different specific scenarios
  #Scenarios use Given-When-Then structure
  Scenario Outline: TC01 logins Gmibank page
    Given TC01 goes to "https://www.gmibank.com"
    And   user writes valid "<Username>" and valid "<password>"
    Then verify the login with new page
 Examples:
   |Username| password    |
   |group16Employee| Employee12 |
   |group16Admin| Admin12   |

