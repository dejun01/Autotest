Feature: Roller - One Page Checkout with Braintree
  #env = sbase_onepage_checkout_braintree
  Background:
    Given clear all data

  Scenario: Checkout success by Braintree (10. Successful Step Up Authentication)
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001091 | Shop Base       | 01/23        | 113 | Visa      | yes | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout success by Braintree with post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001091 | Shop Base       | 01/23        | 113 | Visa      | yes | 1234         |
    And order product "Bikini" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout success by Braintree without post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001091 | Shop Base       | 01/23        | 113 | Visa      | yes | 1234         |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in case 1. Successful Frictionless Authentication
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001026 | Shop Base       | 01/23        | 113 | Visa      | no  | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in case 3. Attempts Stand-In Frictionless Authentication
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001026 | Shop Base       | 01/23        | 113 | Visa      | no  | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in case 6. Authentication Not Available on Lookup
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001059 | Shop Base       | 01/23        | 113 | Visa      | no  | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in case 7. Error on Lookup
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001067 | Shop Base       | 01/23        | 113 | Visa      | no  | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in case 8. Timeout on cmpi_lookup Transaction
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001075 | Shop Base       | 01/23        | 113 | Visa      | no  | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in case 9. Bypassed Authentication
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Braintree       | Credit Card    | 4000000000001083 | Shop Base       | 01/23        | 113 | Visa      | no  | 1234         |
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Braintree in Case 11. Failed Step Up Authentication
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status                    | Error message                                                                      |
      | Braintree       | Credit Card    | 4000000000001109 | Shop Base       | 01/23        | 113 | Visa      | yes | 1234         | Failed Step Up Authentication | 3D-Authentication failed. Cannot authorise this card. (status: challenge_required) |
    And close browser

  Scenario: Checkout by Braintree in Case 12. Step Up Authentication is Unavailable
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status                            | Error message                                                                      |
      | Braintree       | Credit Card    | 4000000000001117 | Shop Base       | 01/23        | 113 | Visa      | yes | 1234         | Step Up Authentication is Unavailable | 3D-Authentication failed. Cannot authorise this card. (status: challenge_required) |
    And close browser

  Scenario: Verify input data on Payment method
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    When verify payment method information with "Braintree"
      | Case                                                            | Card Number      | Cardholder name | MM/YY | CVV | Expected                                                                                                                                                              |
      | All fields are empty                                            |                  |                 |       |     | Your card number is incomplete.,Please enter your name as it appears on the card.,Your card's expiration date is incomplete.,Your card's security code is incomplete. |
      | Card number is not exist                                        | 2222222222222222 | Shop Base       | 01/23 | 111 | Your card number is invalid.                                                                                                                                          |
      | Expired date in the past                                        | 4000000000000002 | Shop Base       | 01/11 | 111 | Your card's expiration year is invalid                                                                                                                                |
      | CVV has invalid format                                          | 4000000000000002 | Shop Base       | 01/23 | 11  | Your card's security code is invalid.                                                                                                                                 |
      | Case 2: Failed Frictionless Authentication                      | 4000000000001018 | Shop Base       | 01/23 | 100 | 3D-Authentication failed. Cannot authorise this card. (status: authenticate_failed)                                                                                   |
      | Case 4: Unavailable Frictionless Authentication from the Issuer | 4000000000001034 | Shop Base       | 01/23 | 100 | 3D-Authentication failed. Cannot authorise this card. (status: authenticate_unable_to_authenticate)                                                                   |
      | Case 5: Rejected Frictionless Authentication by the Issuer      | 4000000000001042 | Shop Base       | 01/23 | 111 | 3D-Authentication failed. Cannot authorise this card. (status: authenticate_rejected)                                                                                 |
    And close browser