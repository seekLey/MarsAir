Feature: MarsAir flight search application

  Background: Test the Flight search application
    Given launch the browser
    And verify the page Title

  Scenario Outline: Search flight to Mars via MarsAir application -- validation message 1
    Given Verify the form fields on Home page
    When Select <departure> and <returning>
    Then click on search
    And Verify the validation1 <message>

    Examples:
      | departure | returning | message                                     |
      | "July"    | "July"    | "Sorry, there are no more seats available." |


  Scenario Outline: Search flight to Mars via MarsAir application -- validation message 2
    Given Verify the form fields on Home page
    When Select <departure> and <returning>
    Then click on search
    And Verify the validation2 <message>

    Examples:
      | departure | returning  | message                                                           |
      | "July"    | "December" | "Unfortunately, this schedule is not possible. Please try again." |


  Scenario Outline: Search flight to Mars via MarsAir application -- validation message 3
    Given Verify the form fields on Home page
    When Select <departure> and <returning>
    Then click on search
    And Verify the validation3 <message>

    Examples:
      | departure | returning                       | message                                             |
      | "July"    | "December (two years from now)" | "Seats available!Call now on 0800 MARSAIR to book!" |

  Scenario Outline: To verify Promotional code percentage
    Given Select <departure> and <returning>
    Then enter promotion <code>
    Then click on search
    And Verify the promo code <message>

    Examples:
      | departure | returning                       | code          | message                                            |
      | "July"    | "December (two years from now)" | "JJ2-OPQ-428" | "Promotional code JJ2-OPQ-428 used: 20% discount!" |

  Scenario Outline: To verify error message on invalid Promo code
    Given Select <departure> and <returning>
    Then enter promotion <code>
    Then click on search
    And Verify the promo code <message>

    Examples:
      | departure | returning                       | code         | message                               |
      | "July"    | "December (two years from now)" | "JJ2-PQ-428" | "Sorry, code JJ2-PQ-428 is not valid" |


  Scenario Outline: To verify validation message if "departure" and "return" date is less than 1 year
    Given Select <departure> and <returning>
    Then click on search
    And verify the validation <message>

    Examples:
      | departure | returning  | message                                                           |
      | "July"    | "December" | "Unfortunately, this schedule is not possible. Please try again." |

  Scenario Outline: US : Link to Home Page - To verify "Book a ticket to the red planet now!" message on the Home Page
    Given Verify <message> on the screen

    Examples:
      | message                                |
      | "Book a ticket to the red planet now!" |