Feature: Abandoned checkout - Log fail reason
#sbase_check_fail_reason_abandoned_checkout

  Background:
    Given clear all data

  Scenario Outline: Log fail reason into timeline of Abandoned checkout for Stripe #SB_ORD_ABC_20 #SB_ORD_ABC_18 #SB_ORD_ABC_17 #SB_ORD_ABC_16 #SB_ORD_ABC_15 #SB_ORD_ABC_14 #SB_ORD_ABC_13 #SB_ORD_ABC_12
    Given open stripe.shop on storefront
    Then add products "Bikini" to cart then navigate to checkout page
    Given input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number   | Cardholder name | Expired Date | CVV | Card type | 3DS   | 3Ds status   | Error message   | Key   |
      | Stripe          | Credit Card    | <Card number> | Shopbase        | 11/22        | 113 | Visa      | <3DS> | <3Ds status> | <Error message> | <Key> |
    And get checkout url
    Given user login to stripe.shop dashboard
    Given user navigate to "Orders>Abandoned checkouts" screen
    And search then select abandoned checkout by customer email
    Then verify fail reason captured in timeline
    And close browser
    Examples:
      | Key | Error code         | Card number      | 3DS | 3Ds status | Error message                                                                                              |
      | 1   | EXPIRED_CARD       | 4000000000000069 |     |            | Your card has expired.                                                                                     |
      | 1   | FRAUDULENT         | 4100000000000019 |     |            | Your card was declined.                                                                                    |
      | 1   | INCORRECT_CVC      | 4000000000000127 |     |            | CVC number is invalid.                                                                                     |
      | 1   | INSUFFICIENT_FUNDS | 4000000000009995 |     |            | Your card has insufficient funds.                                                                          |
      | 1   | LOST_CARD          | 4000000000009987 |     |            | Your payment has been declined because the card is reported lost.                                          |
      | 1   | PROCESSING_ERROR   | 4000000000000119 |     |            | An error occurred while processing your card. Try again in a little bit.                                   |
      | 1   | STOLEN_CARD        | 4000000000009979 |     |            | Your payment has been declined because the card is reported stolen.                                        |
      | 3   | Fail 3DS           | 4000000000003220 | yes | fail       | We are unable to authenticate your payment method. Please choose a different payment method and try again. |

#  Scenario Outline: Log fail reason to gateway timeline of Abandoned checkout for Spay
#    Given open spay.shop on storefront
#    Then add products "Bikini" to cart then checkout
#    Given input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And input payment information and complete order
#      | Payment gateway   | Payment method | Card number   | Cardholder name | Expired Date | CVV | Card type | 3DS   | 3Ds status   | Error message   | Key   |
#      | ShopBase Payments | Credit Card    | <Card number> | Shopbase        | 11/22        | 113 | Visa      | <3DS> | <3Ds status> | <Error message> | <Key> |
#    And get checkout url
#    And user login to spay.shop dashboard by API
#    Given user navigate to "Orders>Abandoned checkouts" screen
#    And search then select abandoned checkout by customer email
#    Then verify fail reason captured in timeline
#    And close browser
#    Examples:
#      | Key | Error code         | Card number      | 3DS | 3Ds status | Error message                                                                                              |
#      | 1   | EXPIRED_CARD       | 4000000000000069 |     |            | Your card has expired.                                                                                     |
#      | 1   | FRAUDULENT         | 4100000000000019 |     |            | Your card was declined.                                                                                    |
#      | 1   | INCORRECT_CVC      | 4000000000000127 |     |            | CVC number is invalid.                                                                                     |
#      | 1   | INSUFFICIENT_FUNDS | 4000000000009995 |     |            | Your card has insufficient funds.                                                                          |
#      | 2   | LOST_CARD          | 4000000000009987 |     |            | Your payment has been declined because the card is reported lost.                                          |
#      | 1   | PROCESSING_ERROR   | 4000000000000119 |     |            | An error occurred while processing your card. Try again in a little bit.                                   |
#      | 1   | STOLEN_CARD        | 4000000000009979 |     |            | Your payment has been declined because the card is reported stolen.                                        |
#      | 3   | Fail 3DS           | 4000000000003220 | yes | fail       | We are unable to authenticate your payment method. Please choose a different payment method and try again. |

  Scenario Outline: Log fail reason to gateway timeline of Abandoned checkout for Paypal-Pro #SB_ORD_ABC_29 #SB_ORD_ABC_28 #SB_ORD_ABC_27 #SB_ORD_ABC_17
    Given open paypalPro.shop on storefront
    Then add products "Bikini" to cart then navigate to checkout page
    Given input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number   | Cardholder name | Expired Date | CVV | Card type | 3DS   | 3DS password   | Error message   | Key   |
      | Paypal-Pro      | PayPal Pro     | <Card number> | Shopbase        | 11/22        | 113 | Visa      | <3DS> | <3DS password> | <Error message> | <Key> |
    And get checkout url
    Given user login to paypalPro.shop dashboard
    Given user navigate to "Orders>Abandoned checkouts" screen
    And search then select abandoned checkout by customer email
    Then verify fail reason captured in timeline
    And close browser
    Examples:
      | Key | Error code              | Card number      | 3DS | 3DS password | Error message                                                                                                                         |
      | 3   | cmpi_authenticate error | 4000000000000093 | yes | 1234         | General Error (Processor error - An error occurred during authorization. If these errors persist, contact merchant service provider). |
      | 3   | Failed Signature        | 4000000000000010 | yes | 1234         | 3D-Authentication failed. Cannot authorise this card.                                                                                 |
      | 2   | CARD_DECLINE            | 4000000000001018 |     |              | Your card was declined. Please contact your card issuer for more information. (Error code: CARD_DECLINE)                              |

  Scenario Outline: Log fail reason to gateway timeline of Abandoned checkout for cardpay #SB_ORD_ABC_31 #SB_ORD_ABC_30
    Given open cardpay.shop on storefront
    Then add products "Bikini" to cart then navigate to checkout page
    Given input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number   | Cardholder name | Expired Date | CVV | Card type | 3DS   | 3Ds status   | Error message   | Key   |
      | Unlimint        | Cardpay        | <Card number> | Shopbase        | 11/22        | 113 | Visa      | <3DS> | <3Ds status> | <Error message> | <Key> |
    And get checkout url
#    Given user login to cardpay.shop dashboard by API
    Given user login to cardpay.shop dashboard
    Given user navigate to "Orders>Abandoned checkouts" screen
    And search then select abandoned checkout by customer email
    Then verify fail reason captured in timeline
    And close browser
    Examples:
      | Key | Error code                              | Card number      | 3DS | 3Ds status | Error message                                                                                                     |
      | 1   | Declined by 3-D Secure                  | 4000000000000002 | yes | failure    | Oops! This card is declined by 3-D Secure. Please try again with another card to complete payment for your order. |
      | 1   | Declined by bank (reason not specified) | 5555555555554477 |     |            | Oops! This card is declined by issuing bank. Please contact your bank for help.                                   |
      | 1   | Declined by bank (reason not specified) | 5555555555554444 | yes | success    | Oops! This card is declined by issuing bank. Please contact your bank for help.                                   |
