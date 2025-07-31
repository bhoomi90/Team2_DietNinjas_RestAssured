@morbidity

Feature: GET all morbidities
  @test1
  Scenario Outline: Check dietician able to retrieve all morbidities details
    Given Dietician create a GET request for morbidity with no auth
    When  Dietician send GET http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |statusCode|
    |401       |

  @test2
  Scenario Outline: Check patient is able to retrieve all morbidities details
    Given Patient create a valid GET request for morbidity with patient token
    When Patient send GET http request with endpoint for morbidity
    Then Patient receives with status code <statusCode>
    Examples:
    |statusCode|

  @test3
  Scenario Outline: Check admin able to retrieve all morbidities details
    Given Admin create a valid GET request for morbidity with admin token
    When Admin send GET http request with endpoint for morbidity
    Then Admin receives with status code <statusCode> with details of the patient id
    Examples:
    |statusCode|

  @test4
  Scenario Outline: Check admin able to retrieve all morbidities details with invalid method
    Given Admin create a valid POST request for morbidity with admin token
    When Admin send POST http request with endpoint for morbidity
    Then Admin receives with status code <statusCode>
    Examples:
    |statusCode|

  @test5
  Scenario Outline: Check admin able to retrieve all morbidities details with invalid endpoint
    Given Admin create a valid GET request for morbidity with admin token
    When Admin send GET http request with invalid endpoint for morbidity
    Then Admin receives with status code <statusCode>
    Examples:
    |statusCode|

  @test6
  Scenario Outline: Check dietician able to retrieve all morbidities details
    Given Dietician create a valid GET request for morbidity with dietician token
    When Dietician send GET http request with endpoint for morbidity
    Then Admin receives with status code <statusCode> with details of the patient id
    Examples:
    |statusCode|

  @test7
  Scenario Outline: Check dietician able to retrieve all morbidities details with invalid method
    Given Dietician create a valid POST request for morbidity with dietician token
    When Dietician send POST http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |statusCode|

  @test8
  Scenario Outline: Check dietician able to retrieve all morbidities details with invalid endpoint
    Given Dietician create POST request for morbidity with dietician token
    When Dietician send POST http request with endpoint for morbidity
    Then Dietician receives with status code <statusCode>
    Examples:
    |statusCode|