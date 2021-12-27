Feature: In order to verify the user experience
  I want to test user journeys of property search

  Background:
  Given I am on zoopla homepage

    @smoke
  Scenario: Register for daily email updates on a rental property search and change the frequency of an existing email update
    When I select my search type "Rent"
    When I search for Rent a property with below criteria
      | SearchLocation | MinPrice | MaxPrice   | Bedrooms |
      | London         | £800 pcm | £1,000 pcm | 1+       |
    And I select to create email alerts
    And I register
    When I set "Daily summary emails" the email alerts
    Then I see email alerts are saved
    When I change the existing alerts frequency "Instant property alerts"
    Then I see alerts frequency set to "Instant property alerts"

  Scenario Outline: Search for a particular property in the house prices search and confirm that it appears as the first result
    When I select my search type "Rent"
    When I search for Rent a property with below criteria
      | SearchLocation | MinPrice | MaxPrice   | Bedrooms |
      | <Location>     | £800 pcm | £1,000 pcm | 1+       |
    Then I see the first result matches <Location> in the search

    Examples:
      | Location     |
      | Sidcup, DA14 |

  Scenario: Search houses for sale including the key word “garage” and check that results have garages
    When I select my search type "SALE"
    When I search a property for sale with below criteria
      | SearchLocation | MinPrice | MaxPrice | Bedrooms | Keywords | distance | PropertyType |
      | Dartford, Kent | £300,000 | £400,000 | 3+       | garage   |          | Houses       |
    Then I see the search "garage" keyword appears in the description of the property

  Scenario: Save a search for property within 15 minutes drive of SE1 2LH as a registered user
    And I sign in with valid details
    When I select my search type "Sale"
    When I search a property for sale with below criteria
      | SearchLocation | MinPrice | MaxPrice | Bedrooms | Keywords | distance        | PropertyType |
      | SE1 2LH        | £300,000 | £500,000 |          |          | Within 15 miles | Houses       |
    And I save my search
    Then I see the search is saved
