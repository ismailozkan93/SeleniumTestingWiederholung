Feature: delete
Scenario Outline: Delete a country
Given user deletes a country using endpoint "<endpoint>" and delete "<id>"
Examples:
|endpoint|id|
|https://www.gmibank.com/api/tp-countries|/113840 |