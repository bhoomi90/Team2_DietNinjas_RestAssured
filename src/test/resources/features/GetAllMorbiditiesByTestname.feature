@GetAllMorbiditiesbyTestname

  Feature: Get All Morbidities By Testname

@Morbidity_ByTestname_test1
Scenario Outline: Check if dietician able to morbidity condition by test name
  Given Dietician create a request for morbidity by test with no auth
  When  Dietician send GET http request with endpoint for morbidity by test "<testCaseId>"
  Then Dietician receives with status code <statusCode> for morbidity by test name
  Examples:
    |testCaseId|statusCode|
    |Morbidity_ByTestname_test1_1|401 |

@Morbidity_ByTestname_test2
Scenario Outline: Check if patient is able to retrieve morbidity condition by test name
  Given Login as a user with patient login information as in "<loginTestCaseId>" for morbidity by test name
  And Patient create a request for morbidity by test with patient token
  When  Patient send GET http request with endpoint for morbidity by test "<testCaseId>"
  Then Patient receives with status code <statusCode> for morbidity by test name
  Examples:
  |loginTestCaseId|testCaseId|statusCode|
  |Morbidity_test2| Morbidity_ByTestname_test1_1| 403 |

@Morbidity_ByTestname_test3
Scenario Outline: Check if admin able to retrieve morbidity condition by test name
  Given Login as a user with admin login information as in "<loginTestCaseId>" for morbidity by test name
  And Admin create a request for morbidity by test with admin token
  When Admin send GET http request with endpoint for morbidity by test "<testCaseId>"
  Then Admin receives with status code <statusCode> with details of the patient id for morbidity by test name
  Examples:
    |loginTestCaseId|testCaseId|statusCode|
    |Morbidity_test3|Morbidity_ByTestname_test1_1|200|
    |Morbidity_test3|Morbidity_ByTestname_test1_2|200|
    |Morbidity_test3|Morbidity_ByTestname_test1_3|200|

@Morbidity_ByTestname_test4
Scenario Outline: Check if admin able to retrieve morbidity condition by test name  with invalid method
  Given Login as a user with admin login information as in "<loginTestCaseId>" for morbidity by test name
  And Admin create a request for morbidity by test with admin token
  When Admin send POST http request with endpoint for morbidity by test "<testCaseId>"
  Then Admin receives with status code <statusCode> for morbidity by test name
   Examples:
   |loginTestCaseId|testCaseId|statusCode|
   |Morbidity_test3|Morbidity_ByTestname_test1_1|405|

@Morbidity_ByTestname_test5
Scenario Outline: Check if admin able to retrieve morbidity condition by invalid test name
  Given Login as a user with admin login information as in "<loginTestCaseId>" for morbidity by test name
  And Admin create a request for morbidity by test with admin token
  When Admin send GET http request with endpoint for morbidity by test "<testCaseId>"
  Then Admin receives with status code <statusCode> for morbidity by test name
  Examples:
    |loginTestCaseId|testCaseId|statusCode|
    |Morbidity_test3|Morbidity_ByTestname_test5|404|

@Morbidity_ByTestname_test6
Scenario Outline: Check if admin able to retrieve morbidity condition by test name with invalid endpoint
  Given Login as a user with admin login information as in "<loginTestCaseId>" for morbidity by test name
  And Admin create a request for morbidity by test with admin token
  When Admin send GET http request with invalid endpoint for morbidity by test "<testCaseId>"
  Then Admin receives with status code <statusCode> for morbidity by test name
  Examples:
  |loginTestCaseId|testCaseId|statusCode|
  |Morbidity_test3|Morbidity_ByTestname_test1_1|404|

@Morbidity_ByTestname_test7
Scenario Outline: Check if dietician able to retrieve morbidity condition by test name
  Given Login as a user with dietician login information as in "<loginTestCaseId>" for morbidity by test name
  And Dietician create a request for morbidity by test with dietician token
  When Dietician send GET http request with endpoint for morbidity by test "<testCaseId>"
  Then Dietician receives all morbidities by test name with status code <statusCode>
  Examples:
  |loginTestCaseId|testCaseId|statusCode|
  |Morbidity_test6|Morbidity_ByTestname_test1_1|200|
  |Morbidity_test6|Morbidity_ByTestname_test1_2|200|
  |Morbidity_test6|Morbidity_ByTestname_test1_3|200|

@Morbidity_ByTestname_test8
Scenario Outline: Check if dietician able to retrieve morbidity condition by test name  with invalid method
  Given Login as a user with dietician login information as in "<loginTestCaseId>" for morbidity by test name
  And Dietician create a request for morbidity by test with dietician token
  When Dietician send POST http request with endpoint for morbidity by test "<testCaseId>"
  Then Dietician receives with status code <statusCode> for morbidity by test name
  Examples:
  |loginTestCaseId|testCaseId|statusCode|
  | Morbidity_test8| Morbidity_ByTestname_test1_2  |405 |

@Morbidity_ByTestname_test9
Scenario Outline: Check if dietician able to retrieve morbidity condition by invalid test name
  Given Login as a user with dietician login information as in "<loginTestCaseId>" for morbidity by test name
  And Dietician create a request for morbidity by test with dietician token
  When Dietician send GET http request with endpoint for morbidity by test "<testCaseId>"
  Then Dietician receives with status code <statusCode> for morbidity by test name
  Examples:
  |loginTestCaseId|testCaseId|statusCode|
  |Morbidity_test6|Morbidity_ByTestname_test5|404|

@Morbidity_ByTestname_test10
Scenario Outline: Check if dietician able to retrieve morbidity condition by test name with invalid endpoint
  Given Login as a user with dietician login information as in "<loginTestCaseId>" for morbidity by test name
  And Dietician create a request for morbidity by test with dietician token
  When Dietician send GET http request with invalid endpoint for morbidity by test "<testCaseId>"
  Then Dietician receives with status code <statusCode> for morbidity by test name
  Examples:
  |loginTestCaseId|testCaseId|statusCode|
  |Morbidity_test6| Morbidity_ByTestname_test1_1 |  404     |




