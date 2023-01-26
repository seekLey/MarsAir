Feature: MarsAir flight search application

  Background: Test the Flight search application
    Given launch the browser
    And verify the page Title

  Scenario Outline: Search flight to Mars via MarsAir application
    Given Verify the form fields on Home page
    When Select <departure> and <returning>
    Then click on search
    And Verify the validation <message>

    Examples:
      | departure | returning | message                                     |
      | "July"    | "July"    | "Sorry, there are no more seats available." |
#      | "July"    | "December"                      | "Unfortunately, this schedule is not possible. Please try again." |
#      | "July"    | "December (two years from now)" | "Seats available!Call now on 0800 MARSAIR to book!"               |
