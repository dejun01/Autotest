Feature: Payment Methods in CrossPanda
  #crosspanda_payment

  Scenario Outline: DSC_PM_1.1: Verify UI Payment Methods
    Given Description: "<Testcase>"
    Given login to crosspanda dashboard
    And user navigate to "Payment methods" screen in CrossPanda
    When verify UI when payment methods hasn't card as "<KEY>"
      | KEY |
      | 1   |
    Then user add new card payment methods for CrossPanda as "<KEY>"
      | KEY | Cardholder Name | Card number         | Expiration date | CVC | First Name | Last Name | Address               | Apartment | City        | Country       | State  | ZIP/Postal code |
      | 2   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           |
      | 3   | Ha Na           | 4242 4242 4242 4242 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           |
    And verify add card successfully as "<KEY>"
      | KEY | Card type      | Exp date | Default |
      | 2   | Visa **** 1111 | 02/22    | true    |
      | 3   | Visa **** 4242 | 02/22    | false   |
    Then close browser
    Examples:
      | KEY | Testcase                                      |
      | 1   | Verify UI Payment Method when hasn't card     |
      | 2   | Verify UI Payment Method when has card        |
      | 3   | Verify UI Payment Method has more than 1 card |

  Scenario Outline: Validate card data
    Given Description: "<Testcase>"
    Given login to crosspanda dashboard
    And user navigate to "Payment methods" screen in CrossPanda
    And user add new card payment methods for CrossPanda as "<KEY>"
      | KEY | Cardholder Name | Card number         | Expiration date | CVC | First Name | Last Name | Address               | Apartment | City        | Country       | State  | ZIP/Postal code | Message                                     |
      | 1   |                 | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | The cardholder name field is required.      |
      | 2   | Ha Na           |                     | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | The card number field is required.          |
      | 3   | Ha Na           | 4111 1111 1111 1111 |                 | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | The card expiry field is required.          |
      | 4   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         |     | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | The card cvc field is required.             |
      | 5   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 |            | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | The first name field is required.           |
      | 6   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         |           | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | The last name field is required.            |
      | 7   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        |                       | OCGoup    | Springfield | United States | Alaska | 65804           | The address field is required.              |
      | 8   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    |             | United States | Alaska | 65804           | The city field is required.                 |
      | 9   | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield |               |        | 65804           | The country field is required.              |
      | 10  | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States |        | 65804           | The state field is required.                |
      | 11  | Ha Na           | 4111 1111 1111 1111 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska |                 | The zip/postal code field is required.      |
      | 12  | Ha Na           | 3546 5657 6879 8099 | 02 / 22         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | Card declined                               |
      | 13  | Ha Na           | 4111 1111 1111 1111 | 02 / 11         | 222 | Ha         | Na        | 2527  Twin House Lane | OCGoup    | Springfield | United States | Alaska | 65804           | Your card's expiration year is in the past. |
    Then close browser
    Examples:
      | KEY | Testcase                 |
      | 1   | Empty Cardholder name    |
      | 2   | Empty Card number        |
      | 3   | Empty Expiration date    |
      | 4   | Empty CVC                |
      | 5   | Empty First name         |
      | 6   | Empty Last name          |
      | 7   | Empty Address            |
      | 8   | Empty City               |
      | 9   | Empty Country            |
      | 10  | Empty State              |
      | 11  | Empty Zipcode            |
      | 12  | Card number invalid      |
      | 13  | Expiratiton date invalid |

  Scenario Outline: Update information for card
    Given Description: "<Test case>"
    Given login to crosspanda dashboard
    And user navigate to "Payment methods" screen in CrossPanda
    And change card default as "<KEY>" then verify card default
      | KEY | Card type      | Default |
      | 1   | Visa **** 4242 | true    |
    Then delete all card payment methods in app CrossPanda
    Then close browser
    Examples:
      | KEY | Test case           |
      | 1   | Change card default |