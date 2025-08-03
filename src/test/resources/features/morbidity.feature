@morbidity

Feature: GET all morbidities
  @Morbidity_test1
  Scenario Outline: Check dietician able to retrieve all morbidities details
    Given Dietician create a request for morbidity with no auth
    When  Dietician send GET http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |statusCode|
    |401       |

  @Morbidity_test2
  Scenario Outline: Check if patient is able to retrieve all morbidities details
    Given Login as a user with patient login information as in "<testCaseId>"
    And Patient create a request for morbidity with patient token
    When Patient send GET http request with endpoint for morbidity
    Then Patient receives with status code <statusCode>
    Examples:
    |testCaseId|statusCode|
    |Morbidity_test2  |403   |

  @Morbidity_test3
  Scenario Outline: Check if admin able to retrieve all morbidities details
    Given Login as a user with admin login information as in "<testCaseId>"
    And Admin create a request for morbidity with admin token
    When Admin send GET http request with endpoint for morbidity
    Then Admin receives with status code <statusCode> with details of the patient id
    Examples:
   |testCaseId|statusCode|
   |Morbidity_test3 |200 |

  @Morbidity_test4
  Scenario Outline: Check if admin able to retrieve all morbidities details with invalid method
    Given Login as a user with admin login information as in "<testCaseId>"
    And Admin create a request for morbidity with admin token
    When Admin send POST http request with endpoint for morbidity
    Then Admin receives with status code <statusCode>
    Examples:
    |testCaseId|statusCode|
    |Morbidity_test4| 405 |

  @Morbidity_test5
  Scenario Outline: Check if admin able to retrieve all morbidities details with invalid endpoint
  #  Given Admin create a request for morbidity with admin token
    Given Login as a user with admin login information as in "<testCaseId>"
    And Admin create a request for morbidity with admin token
    When Admin send GET http request with invalid endpoint for morbidity
    Then Admin receives with status code <statusCode>
    Examples:
    |testCaseId|statusCode|
    |Morbidity_test5| 404 |

  @Morbidity_test6
  Scenario Outline: Check if dietician able to retrieve all morbidities details
    Given Login as a user with dietician login information as in "<testCaseId>"
    And Dietician create a request for morbidity with dietician token
    When Dietician send GET http request with endpoint for morbidity
    Then Dietician receives all morbidities with status code <statusCode>
    Examples:
    |testCaseId|statusCode|
    |Morbidity_test6| 200 |

  @Morbidity_test7
  Scenario Outline: Check dietician able to retrieve all morbidities details with invalid method
    Given Login as a user with dietician login information as in "<testCaseId>"
    And Dietician create a request for morbidity with dietician token
    When Dietician send POST http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |testCaseId|statusCode|
    |Morbidity_test7| 405 |
  @Morbidity_test8
  Scenario Outline: Check dietician able to retrieve all morbidities details with invalid endpoint
    Given Login as a user with dietician login information as in "<testCaseId>"
    And Dietician create a request for morbidity with dietician token
    When Dietician send GET http request with invalid endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |testCaseId|statusCode|
    |Morbidity_test8|404  |