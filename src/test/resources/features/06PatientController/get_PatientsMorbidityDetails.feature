@GetPatientsMorbidityDetails

Feature: Get Patients Morbidity Details

Background: Create a valid Patient
  Given Login as a user with dietician login information as in "Morbidity_test6" for creating patient
  And Create a new patient using "NewPatientForMorbidityDetails_test1"

@GetPatientsMorbidityDetails_test1
Scenario Outline: Check dietician able to retrieve patients morbidity details by patient ID
  Given Dietician create GET request  with no auth
  When Dietician send GET http request with endpoint
  Then Dietician recieves 401 unauthorized status code <statusCode>
  Examples:
  |statusCode|
  |401       |

@GetPatientsMorbidityDetails_test2
Scenario Outline: Check admin is able to retrieve patients morbidity details by patient ID
  Given Admin create GET request  with admin auth
  When Admin  send GET http request with endpoint
  Then Admin recieves 403 Forbidden  status code <statusCode>
  Examples:
  |statusCode|
  |403       |

  @GetPatientsMorbidityDetails_test3
Scenario Outline: Check patient is able to retrieve patients morbidity details by field id
  Given Patient create GET request  with patient token
  When Patient send GET http request with endpoint
  Then Patient recieves 200 ok with details of the patient id <statusCode>
  Examples:
  |statusCode|
  |200       |

@GetPatientsMorbidityDetails_test4
Scenario Outline: Check dietician able to retrieve patients morbidity details by patient ID
  Given Dietician create GET request with dietician token
  When Dietician send GET http request with endpoint
  Then Patient recieves 200 ok with details of the patient id <statusCode>
  Examples:
  |statusCode|
  |200       |

@GetPatientsMorbidityDetails_test5
Scenario Outline: Check dietician able to retrieve patients morbidity details by patient ID with invalid method
  Given Dietician create POST request with dietician token
  When Dietician send POST http request with endpoint
  Then Dietician recieves 405 method not allowed statusCode <statusCode>
  Examples:
  |statusCode|
  |405       |

@GetPatientsMorbidityDetails_test5
Scenario Outline: Check dietician able to retrieve patients morbidity details by invalid patient ID
  Given Dietician create GET request with dietician token
  When Dietician send GET http request with endpoint
  Then Dietician recieves 404 not found statusCode <statusCode>
  Examples:
  |statusCode|
  |404       |

@GetPatientsMorbidityDetails_test6
Scenario Outline: Check dietician able to retrieve patients morbidity details by patient ID with invalid endpoint
  Given Dietician create GET request with dietician token
  When Dietician send GET http request with invalid endpoint
  Then Dietician recieves 404 not found statusCode <statusCode>
  Examples:
  |statusCode|
  |404       |
  