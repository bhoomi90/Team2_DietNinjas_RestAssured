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