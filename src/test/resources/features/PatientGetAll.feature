@GetallPatients
Feature: Get all Patient records

@GetAllPatientswithAdmin
Scenario: Check admin is able to retrieve patients morbidity details by patient ID 
Given Set bearer token in header
Given Admin create GET request for get all patient
When Admin send GET http request with endpoint for get all patient
Then Admin recieves 403 Forbidden

@GetAllPatientswithPatient
Scenario: Check patient is able to retrieve patients morbidity details by patient ID
Given Set patient token
Given Patient create GET request 
When Patient send GET http request with endpoint
Then Patient recieves 403 Forbidden

@GetAllPatientswithdietician
Scenario: Check dietician able to retrieve all patients
Given Set dietician token
Given Dietician create GET request 
When Dietician send GET http request with endpoint
Then Dietician recieves 200 ok with response body

@GetAllpatientwithInvalidmethod
Scenario: Check dietician able to retrieve all patient with invalid method
Given Set dietician token
Given Dietician create PUT request 
When Dietician send PUT http request with endpoint
Then Dietician recieves 405 method not allowed

@GetAllpatientwithInvalidEndpoint
Scenario: Check dietician able to retrieve all patient with invalid endpoint
Given Set dietician token
Given Dietician create GET request with invalid endpoint
When Dietician send GET http request with invalid endpoint
Then Dietician recieves 404 not found

@GetAllpatientwithNoAuth
Scenario: Check dietician able to retrieve patients morbidity details by patient ID
Given Dietician create GET request with no auth
When Dietician send GET http request with endpoint with no auth
Then Dietician recieves 401 unauthorized