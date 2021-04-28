@tag
Feature: GLAMS Component Job Creation
  To create GLAMS Component Job

  @tag1
  Scenario: GLAMS User is able to create New Job
    Given I have List of Requests
    And I pass the required headers
      | Field         | Value                         |
      | APIName       | Glams - Component Job Details |
      | PartNumber    | BLI-5902-01                   |
      | ComponentName | V7700                         |
    When I post the request
    Then Status Code should be 200
    And Response should display
      | Field          |
      | PartNumber     |       
      | ComponentName |       
      | Strength       |       
