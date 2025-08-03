@CreatePatientWithDietician
Feature: Post Operation [create patient]

Background: Set dietician bearer token

@CreatePatientWithDietician_001
Scenario Outline: Check dietician able to create patient with valid data and token 
Given Dietician creates POST request by entering valid data with "<scenarioName>". ( Mandatory and additional details) into the form-data key and value fields.
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)

	Examples:
		| scenarioName    |
		| Create patient  |

@CreatePatientWithDietician_002
Scenario Outline: Check dietician able to create patient only with valid mandatory details
Given Dietician creates POST request only by valid mandatory details into the form-data key and value fields with "<scenarioName>".
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)
	Examples:
		| scenarioName    |
		| Create patient with mandatory fields only  |
		
@CreatePatientWithDietician_003
Scenario Outline: Check dietician able to create patient only with valid additional details
Given Dietician creates POST request only by valid additional details into the form-data key and value fields with "<scenarioName>".
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 400 Bad request after create patient request
	Examples:
		| scenarioName    |
		| Create patient with valid additional details only |

@CreatePatientWithDietician_004		
Scenario Outline: Check dietician able to create patient with invalid data (mandatory details)
Given Dietician creates POST request only by invalid mandatory details into the form-data key and value fields with "<scenarioName>".
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 400 Bad request after create patient request with "<errorMessage>"
	Examples:
		|           scenarioName                     |            errorMessage                               |
		| Create patient with invalid firstname      |          FirstName should only contain Alphabets      |
		| Create patient with invalid lastname       |          LastName should only contain Alphabets       |
		| Create patient with invalid contactnumber  | Contact number should contain 10 digits               |
		| Create patient with contactnumber more than 10 digits | Contact number should contain 10 digits    |
		| Create patient with contactnumber less than 10 digits | Contact number should contain 10 digits    |
		| Create patient with invalid email          |  Email should start with a letter                     |
		| Create patient with invalid allergy        |  walnut, pistachio, sesame, hazelnut, pecan, cashew   |
		| Create patient with invalid FoodPreference | VEGAN, VEGETARIAN, JAIN, EGGETARIAN, NONVEG           |
		| Create patient with invalid CuisineCategory| Invalid Cuisine Category                              |
#		| Create patient with invalid DateOfBirth    | |
#		
#@CreatePatientWithDietician_005 
#Scenario Outline: Check dietician able to create patient with valid mandatory fields and invalid data (additional details)
#Given Dietician creates POST request only by invalid additional details into the form-data key and value fields with "<scenarioName>".
#When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
#Then Dietician recieves 400 Bad request after create patient request
#	Examples:
#		|           scenarioName                     |            errorMessage                               |
#		| Create patient with invalid weight in vitals | |
#		| Create patient with invalid height in vitals | |

@CreatePatientWithDietician_006		
Scenario Outline: Check dietician able to create patient with valid data and invalid method
Given Dietician creates PUT request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 405 method not allowed after create patient request with "<errorMessage>"
	Examples:
		|           scenarioName                     |            errorMessage                               |
		| Create patient with invalid method PUT     | HTTP method is not supported for this endpoint        |

@CreatePatientWithDietician_007
Scenario Outline: Check dietician able to create patient with valid data and invalid endpoint
Given Dietician creates POST request with valid token by entering valid data into the form-data key and value fields with "<scenarioName>".
When Dietician send POST http request with invalid endpoint to create patient with "<scenarioName>"
Then Dietician recieves 404 not found after create patient request with "<errorMessage>"
	Examples:
		|           scenarioName                     |            errorMessage                  |
		| Create patient with invalid endPoint       |      Endpoint not supported.             |

@CreatePatientWithDietician_008		
Scenario Outline: Check dietician able to create patient with valid data and invalid content type
Given Dietician creates POST request by entering valid data into the form-data key and value fields and invalid content type with "<scenarioName>"
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 415 unsupported media type after create patient request with "<errorMessage>"
	Examples:
		|           scenarioName                     |            errorMessage         |
    | Create patient with invalid contentType    |    unexpected error             |