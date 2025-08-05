@ExistingPatientWithDietician
Feature: Put Operation [Add New Reports with/without Vitals for existing Patient]

Background: Dietician bearer token was set, and the Dietician successfully created a patient account

@ExistingPatientWithDietician_001
Scenario Outline: Check dietician able to add new reports with vitals for existing patient with valid data 
Given Dietician creates PUT request by entering valid data. ( Mandatory and additional details) into the form-data key and value fields valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 200 ok and with Existingd response body. 
	Examples:
		| scenarioName    |
		| Existing patient firstname and DP |
		| Existing Patient Contactnumber and SP|
		| Existing Patient DOB and Weight|


@ExistingPatientWithDietician_002
Scenario Outline: Check dietician able to add new reports without vitals for existing patient with valid data
Given Dietician creates PUT request by entering valid data into the form-data key and value fields except valid vitals data and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 200 ok and with Existingd response body. 	
		Examples:
		| scenarioName    |
		| Existing Patient Email |
		| Existing Patient FoodPreference |
		| Existing Patient CuisineCategory |


@ExistingPatientWithDietician_003
Scenario Outline: Check dietician able to add only new vitals without reports for existing patient with report
Given Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 200 ok and with Existingd response body. 
	Examples:
		| scenarioName    |
		| Existing Patient with empty mandatory fields |

@ExistingPatientWithDietician_004
Scenario Outline: Check dietician able to add only new vitals without reports for existing patient without report
Given Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 200 ok and with Existingd response body. 
	Examples:
		| scenarioName                               |                errorMessage              |
		| Existing Patient with invalid contact number |  Contact number should contain 10 digits |

@ExistingPatientWithDietician_005
Scenario Outline: Check dietician able to add new reports for existing patient only with valid mandatory details
Given Dietician creates PUT request by entering valid data ( Mandatory only) into the form-data key and value fields and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 200 ok and with Existingd response body. 

@ExistingPatientWithDietician_006
Scenario Outline: Check dietician able to add new reports for existing patient only with valid additional details
Given Dietician creates PUT request by entering valid data ( Additional details only) into the form-data key and value fields and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 200 ok and with Existingd response body. 

@ExistingPatientWithDietician_007
Scenario Outline: Check dietician able to add new reports  for existing patient with invalid data
Given Dietician creates PUT request by entering invalid data ( Additional details only) into the form-data key and value fields and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 400 Bad request
Examples:
		| scenarioName    |
		| Existing Patient with empty mandatory fields |
	
@ExistingPatientWithDietician_008
Scenario Outline: Check dietician able to add new reports  for existing patient with invalid data
Given Dietician creates PUT request by entering invalid data ( Additional details only) into the form-data key and value fields and valid patient ID "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 404 not found

@ExistingPatientWithDietician_009
Scenario Outline: Check dietician able to add new reports for existing patient  with valid data and invalid method
Given Dietician creates POST request by entering valid data into the form-data key and value fields and valid patient ID "<scenarioName>"
When Dietician send POST http request with endpoint for Existing patient
Then Dietician recieves 405 method not allowed for existing patient
Examples:
		| scenarioName                         | errorMessage |
		| Existing patient with invalid method |  HTTP method is not supported for this endpoint |

Scenario Outline: Check dietician able to add new reports for existing patient with valid data and invalid endpoint
Given Dietician creates POST request by entering valid data into the form-data key and value fields and valid patient ID "<scenarioName>"
When Dietician sent PUT http request with invalid endpoint for Existing patient
Then Dietician recieves 404 not found for Existing patient
	Examples:
		| scenarioName                         | errorMessage |
		| Existing patient with invalid endPoint |Endpoint not supported|

Scenario Outline: Check dietician able to add new reports for existing patient  with valid data and invalid content type
Given Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type "<scenarioName>"
When Dietician send PUT http request with endpoint for Existing patient
Then Dietician recieves 415 unsupported media type
Examples:
		| scenarioName                         | errorMessage |
		| Existing patient with invalid contentType |  An unexpected error occurred |