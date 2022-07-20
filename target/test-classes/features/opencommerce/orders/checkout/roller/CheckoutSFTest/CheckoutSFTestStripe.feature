Feature: Roller - Checkout via Stripe
#  prod_sbase_checkout_sftest2

  Background:
    Given clear all data

  Scenario: Checkout via stripe successfully
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Then Verify transaction details of corresponding gateway after checkout successfully
      | User name                                                                                                   |
      | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser


  Scenario: Checkout via Stripe with adding item from post-purchase
    Given open shop on storefront
    And add products "Post Purchase " to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Bikini" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Then Verify transaction details of corresponding gateway after checkout successfully
      | User name                                                                                                   |
      | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Stripe without adding item from post-purchase
    Given open shop on storefront
    And add products "Post Purchase " to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Then Verify transaction details of corresponding gateway after checkout successfully
      | User name                                                                                                   |
      | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Stripe successfully with 3DS
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS |
      | Stripe          | Credit Card    | 4000000000003220 | Shopbase        | 11/22        | 113 | Visa      | yes |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Then Verify transaction details of corresponding gateway after checkout successfully
      | User name                                                                                                   |
      | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Stripe unsuccessfully with 3DS
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status | Error message                                                                                              |
      | Stripe          | Credit Card    | 4000000000003220 | Shopbase        | 11/22        | 113 | Visa      | yes | fail       | We are unable to authenticate your payment method. Please choose a different payment method and try again. |


  Scenario: Verify input data on Payment method 1
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    And input Customer information
      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
    And choose shipping method ""
    When verify payment method information with "Stripe"
      | Case                  | Card Number      | Cardholder name | MM/YY | CVV | Expected                                                                          |
      | all fields empty      |                  |                 |       |     | Your payment details couldn’t be verified. Check your card details and try again. |
      | Card Number empty     |                  | Shopbase        | 12/22 | 111 | Your card number is incomplete.                                                   |
      | Card Number invalid   | 4242424242424241 | Shopbase        | 12/22 | 111 | Your card number is invalid.                                                      |
      | Card Number invalid   | 4242             | Shopbase        | 12/22 | 111 | Your card number is incomplete.                                                   |
      | MM/YY empty           | 4242424242424242 | Shopbase        |       | 111 | Your card's expiration date is incomplete.                                        |
      | MM/YY invalid         | 4242424242424242 | Shopbase        | 00/00 | 111 | Your card's expiration date is incomplete.                                        |
      | MM/YY invalid         | 4242424242424242 | Shopbase        | 11    | 111 | Your card's expiration date is incomplete.                                        |
      | MM/YY invalid         | 4242424242424242 | Shopbase        | 11/11 | 111 | Your card's expiration year is in the past.                                       |
      | CVV empty             | 4242424242424242 | Shopbase        | 12/22 |     | Your card's security code is incomplete.                                          |
      | Cardholder name empty | 4242424242424242 |                 | 12/22 | 111 | Please enter your name as it appears on the card.                                 |
    And close browser

  Scenario: Verify input data on Payment method 2
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    And input Customer information
      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
    And choose shipping method ""
    When verify payment method information with "Stripe"
      | Case               | Card Number      | Cardholder name | MM/YY | CVV | Expected                                                                           |
      | Expired Card       | 4000000000000069 | Shopbase        | 12/22 | 111 | Your card has expired.                                                             |
      | Incorrect CVV      | 4000000000000127 | Shopbase        | 12/22 | 111 | CVC number is invalid.                                                             |
      | Processing Error   | 4000000000000119 | Shopbase        | 12/22 | 111 | An error occurred while processing your card. Try again in a little bit.           |
      | Card decline       | 4000000000000002 | Shopbase        | 12/22 | 111 | The card has been declined for an unknown reason. Please contact your card issuer. |
      | Insufficient funds | 4000000000009995 | Shopbase        | 12/22 | 111 | Your card has insufficient funds.                                                  |
      | Lost card          | 4000000000009987 | Shopbase        | 12/22 | 111 | Your payment has been declined because the card is reported lost.                  |
      | Stolen card        | 4000000000009979 | Shopbase        | 12/22 | 111 | Your payment has been declined because the card is reported stolen.                |
    And close browser

  Scenario: Switch payment method from Paypal to Stripe
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    And verify payment method when switch payment method from PayPal to Stripe
    And verify thank you page
    And close browser