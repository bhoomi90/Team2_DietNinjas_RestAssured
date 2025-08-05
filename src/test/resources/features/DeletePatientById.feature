@DeletePatientById

Feature: Delete Patient By Id

Background: Create a valid Patient
  Given Login as a user with dietician login information as in "Morbidity_test6" for creating patient
  And Create a new patient using "NewPatientForDeletion_test1"

@DeletePatientById_test1
Scenario Outline: Check if dietician able to delete patient by ID
  Given Dietician create DELETE request with no auth
  When Dietician send DELETE http request with endpoint
  Then Dietician receives status code <statusCode>
  Examples:
  |statusCode|
  |401       |

@DeletePatientById_test2
Scenario Outline: Check if admin is able to delete patient by patient ID
  Given Login as a user with admin login information as in "<testCaseId>"
  And Admin create a request for delete with admin token
  When Admin send DELETE http request with endpoint for delete patient
  Then Admin receives status code <statusCode>
  Examples:
  |testCaseId|statusCode|
  | LT_001   |   403    |

@DeletePatientById_test3
Scenario Outline: Check if patient is able to delete patient by patient ID
  Given Login as a user with patient login information as in "<testCaseId>" for deletion
  And  Patient create a request for delete with patient token
  When Patient send DELETE http request with endpoint for delete patient
  Then Patient receives status code <statusCode>
  Examples:
  |testCaseId|statusCode|
  | Morbidity_test2| 403 |

@DeletePatientById_test4
Scenario Outline: Check if dietician able to delete patient by id with dietician bearer token
  Given Login as a user with dietician login information as in "<testCaseId>" for deletion
  And Dietician create a request for deletion with dietician token
  When Dietician send DELETE http request with endpoint for delete patient
  Then Dietician receives status code <statusCode>
  Examples:
  |testCaseId|statusCode|
  | Morbidity_test6|   200 |

@DeletePatientById_test5
Scenario Outline: Check if dietician able to delete patient by id with invalid method with dietician bearer token
  Given Login as a user with dietician login information as in "<testCaseId>" for deletion
  And Dietician create a request for deletion with dietician token
  When Dietician send POST http request with endpoint for delete patient
  Then Dietician receives status code <statusCode>
  Examples:
  |testCaseId|statusCode|
  | Morbidity_test6 | 405|

@DeletePatientById_test6
Scenario Outline: Check if dietician able to delete patient by invalid id with dietician bearer token
  Given Login as a user with dietician login information as in "<testCaseId>" for deletion
  And Dietician create a request for deletion with dietician token
  When Dietician send DELETE http request with endpoint for delete patient by invalid patient id
  Then Dietician receives status code <statusCode>
  Examples:
  |testCaseId|statusCode|
  |Morbidity_test6|404  |

@DeletePatientById_test7
Scenario Outline: Check if dietician able to delete patient by id with invalid endpoint with dietician bearer token
  Given Login as a user with dietician login information as in "<testCaseId>" for deletion
  And Dietician create a request for deletion with dietician token
  When Dietician send DELETE http request with endpoint for delete patient by invalid endpoint
  Then Dietician receives status code <statusCode>
  Examples:
  |testCaseId|statusCode|
  | Morbidity_test6 | 404|