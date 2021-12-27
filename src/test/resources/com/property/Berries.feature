Feature: Test Anil

  Scenario: Get berries
    Given I've berries endpoint
    When I get berries
    Then I see berries