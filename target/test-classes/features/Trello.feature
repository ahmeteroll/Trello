Feature: Trello Scenarios

  Scenario: user creates board on trello
    Given user has authentication information to trello
    When user creates new board
    Then new board is created

  Scenario: user adds two cards to board
    Given user has request information to create card
    When user adds two cards
    Then cards are added to board

  Scenario: user updates card information
    Given user has request information to update card
    When user sends update request tıo update a card
    Then card is updated

  Scenario: user deletes created cards
    Given user has request information to delete card
    When user sends request to delete cards
    Then cards are deleted

  Scenario: user deletes board
    Given user has request information to delete the board
    When user sends delete request to delete the board
    Then board is deleted
    