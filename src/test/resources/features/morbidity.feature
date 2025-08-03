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
    |MT_001  |403       |

  @Morbidity_test3
  Scenario Outline: Check admin able to retrieve all morbidities details
    Given Admin create a request for morbidity with admin token
    When Admin send GET http request with endpoint for morbidity
    Then Admin receives with status code <statusCode> with details of the patient id
    Examples:
    |statusCode|
    |200       |

  @Morbidity_test4
  Scenario Outline: Check admin able to retrieve all morbidities details with invalid method
    Given Admin create a request for morbidity with admin token
    When Admin send POST http request with endpoint for morbidity
    Then Admin receives with status code <statusCode>
    Examples:
    |statusCode|

  @Morbidity_test5
  Scenario Outline: Check admin able to retrieve all morbidities details with invalid endpoint
    Given Admin create a request for morbidity with admin token
    When Admin send GET http request with invalid endpoint for morbidity
    Then Admin receives with status code <statusCode>
    Examples:
    |statusCode|

  @Morbidity_test6
  Scenario Outline: Check dietician able to retrieve all morbidities details
    Given Dietician create a request for morbidity with dietician token
    When Dietician send GET http request with endpoint for morbidity
    Then Admin receives with status code <statusCode> with details of the patient id
    Examples:
    |statusCode|


  @Morbidity_test7
  Scenario Outline: Check dietician able to retrieve all morbidities details with invalid method
    Given Dietician create a request for morbidity with dietician token
    When Dietician send POST http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |statusCode|

  @Morbidity_test8
  Scenario Outline: Check dietician able to retrieve all morbidities details with invalid endpoint
    Given Dietician create a request for morbidity with dietician token
    When Dietician send POST http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |statusCode|