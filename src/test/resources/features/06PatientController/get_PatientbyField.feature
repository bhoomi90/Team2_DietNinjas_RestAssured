Feature: Get Operation [Retrieve Patient File by FileId]

  Background:
    Given API base URI is set

  @GetAuthCheck
  Scenario Outline: Authorization checks for retrieving patient file by FileId
    Given Request is prepared with <authType> token
    When User sends GET request to <endpoint>
    Then API should return status code <statusCode>
    Then Response message should indicate <message>

    Examples:
      | authType      | endpoint                      | statusCode | message          |
      | no_auth       | /files/validFileId            | 401        | unauthorized     |
      | admin_token   | /files/validFileId            | 403        | forbidden        |
      | patient_token | /files/validFileId            | 200        | file details     |
      | dietician_token | /files/validFileId          | 200        | file details     |

  @GetInvalidCases
  Scenario Outline: Invalid GET request cases for retrieving patient file
    Given Dietician is authenticated with valid token
    When Dietician sends <method> request to <endpoint>
    Then API should return status code <statusCode>
    Then Response message should indicate <message>

    Examples:
      | method | endpoint                       | statusCode | message               |
      | POST   | /files/validFileId             | 405        | method not allowed    |
      | GET    | /files/invalidFileId           | 404        | file not found        |
      | GET    | /files/validFileId/wrong-path  | 404        | endpoint not found    |