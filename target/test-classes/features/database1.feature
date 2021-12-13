Feature: read all data in database
  Background:
    Given user creates a connection with db using "jdbc:postgresql://157.230.48.97:5432/gmibank_db","techprodb_user" and "Techpro_@126"

  #Scenario Outline: read data using column Name
   # Given user provides the query as "<query>" and "<columnName>"
    # And user validates all db Customer Info
    #Examples:
    #|query|columnName|
    #|select * from tp_customer | id|
   # |select * from tp_country |name|
 #   |select * from tp_customer |city|
  #  |select * from tp_customer |address|

  #  Scenario:
   #   Then user prints them on the pdf and close connection

   Scenario Outline: read all customer
     Given user provides the query as "<query>" and "<columnName>" and "<filetype>"
    Then user validates all db Customer Info2
    Examples:
      |query|columnName|filetype|
      |select * from tp_customer |ssn|customerSSN.txt|
      |select * from tp_customer |first_name|customerFirstName.txt|
      |select * from tp_customer |zip_code|customerZipCode.txt|
      |select * from tp_country |name|countrySSN.txt|
      |select * from tp_state   |name|stateNames.txt|
