@UpdatePatientWithDietician
Feature: Put Operation [update patient]

Background: Generate and set bearer token before update patient and created patient successfully
#Scenario Outline: Set Dietician Bearer token
#Given Set Bearer Token with "<tokenName>"
#	Examples:
#	| tokenName|
#	| dietician_token |

@UpdatePatientWithDietician_001
Scenario Outline: Check dietician able to update patient with valid data, patient id and token 
Given Dietician creates PUT request by entering valid data. ( Mandatory and additional details) into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 200 ok and with updated response body after update patient request. 
	Examples:
		| scenarioName    |
		| Update patient firstname and DP |
		| Update Patient Contactnumber and SP|
		| Update Patient DOB and Weight|

@UpdatePatientWithDietician_002		
Scenario Outline: Check dietician able to update patient only with valid mandatory details
Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 200 ok and with updated response body after update patient request. 
	Examples:
		| scenarioName    |
		| Update Patient Email |
		| Update Patient FoodPreference |
		| Update Patient CuisineCategory |

@UpdatePatientWithDietician_003		
Scenario Outline: Check dietician able to update patient with mandatory fields empty and only with valid additional details
Given Dietician creates PUT request by entering only valid additional details into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 400 Bad request after update patient request
	Examples:
		| scenarioName    |
		| Update Patient with empty mandatory fields |
	
@UpdatePatientWithDietician_004	
Scenario Outline: Check dietician able to update patient with invalid data
Given Dietician creates PUT request by entering only invalid additional details into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 400 Bad request after update patient request with "<errorMessage>"
	Examples:
		| scenarioName                               |                errorMessage              |
		| Update Patient with invalid contact number |  Contact number should contain 10 digits |
		
@UpdatePatientWithDietician_005
Scenario Outline: Check dietician able to update patient with valid data and invalid patient id in path parameter
Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient with invalid patientID
Then Dietician recieves 404 not found after update patient request with "<errorMessage>"
	Examples:
		| scenarioName                         | errorMessage |
		|Update patient with invalid patientId | Patient not found with patientId |

@UpdatePatientWithDietician_006		
Scenario Outline: Check dietician able to update patient with existing file by not attaching new file 
Given Dietician creates PUT request by not attaching any file into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 200 ok and with updated response body after update patient request. 
	Examples:
		| scenarioName                         |
		| Update Patient without attach file |

@UpdatePatientWithDietician_007		
Scenario Outline: Check dietician able to update patient with valid data and invalid method
Given Dietician create PUT request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Dietician send POST http request with endpoint to update patient
Then Dietician recieves 405 method not allowed after update patient request with "<errorMessage>"
	Examples:
		| scenarioName                         | errorMessage |
		| Update patient with invalid method |  HTTP method is not supported for this endpoint |

@UpdatePatientWithDietician_008		
Scenario Outline: Check dietician able to update patient with valid data and invalid endpoint
Given Dietician create PUT request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Dietician sent PUT http request with invalid endpoint to update patient
Then Dietician recieve 404 not found after update patient request with "<errorMessage>"
	Examples:
		| scenarioName                         | errorMessage |
		| Update patient with invalid endPoint |Endpoint not supported|

@UpdatePatientWithDietician_009		
Scenario Outline: Check dietician able to update patient with valid data, patient id and invalid content type
Given Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type with "<scenarioName>"
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 415 unsupported media type after update patient request with "<errorMessage>"
	Examples:
		| scenarioName                         | errorMessage |
		| Update patient with invalid contentType |  An unexpected error occurred |