Feature: Post country api
Scenario Outline:Create a country
   Given creates "<idJson>" and "<NameJson>"
   And user saves the countries to correspondet files with new data
  Examples:
    |idJson  |NameJson|
    |name    |Frans |

Scenario: Read new Country
  Given user sets the response using api en point "https://www.gmibank.com/api/tp-countries"