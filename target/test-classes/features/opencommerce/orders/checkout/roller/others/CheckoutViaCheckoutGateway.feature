Feature: Roller - Checkout with Checkout.com
  #env = sbase_checkout_checkout_com
  Background:
    Given clear all data

  Scenario: Checkout success by Checkout.com #SB_CHE_CC_8 #SB_CHE_CC_9
    Given open shop on storefront
    And add products "Shirt" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   |
    And verify thank you page then get all information

    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout success by Checkout.com with post purchase item #SB_CHE_CC_10
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   |
    And order product "Shirt" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout success by Checkout.com without post purchase item #SB_CHE_CC_11
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Checkout.com in case 1. Declined
    Given open shop on storefront
    And add products "Declined" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status | Error message            |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Declined   | Declined - Do Not Honour |
    And close browser

  Scenario: Checkout by Checkout.com in Case 2. Invalid payment
    Given open shop on storefront
    And add products "Invalid payment" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status      | Error message       |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Invalid payment | Invalid Transaction |
    And close browser

  Scenario: Checkout by Checkout.com in Case 3. Invalid card number
    Given open shop on storefront
    And add products "Invalid card number" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status          | Error message       |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Invalid card number | Invalid Card Number |
    And close browser

  Scenario: Checkout by Checkout.com in Case 4. Insufficient funds
    Given open shop on storefront
    And add products "Insufficient funds" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status         | Error message      |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Insufficient funds | Insufficient Funds |
    And close browser

  Scenario: Checkout by Checkout.com in Case 5. Bad track data
    Given open shop on storefront
    And add products "Bad track data" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status     | Error message  |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Bad track data | Bad Track Data |
    And close browser

  Scenario: Checkout by Checkout.com in Case 6. Restricted card
    Given open shop on storefront
    And add products "Restricted card" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status      | Error message   |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Restricted card | Restricted Card |
    And close browser

  Scenario: Checkout by Checkout.com in Case 7. Security violation
    Given open shop on storefront
    And add products "Security violation" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status         | Error message      |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Security violation | Security Violation |
    And close browser

  Scenario: Checkout by Checkout.com in Case 8. Response received too late / timeout
    Given open shop on storefront
    And add products "Timeout" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status                           | Error message                        |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Response received too late / timeout | Response Received Too Late / Timeout |
    And close browser

  Scenario: Checkout by Checkout.com in Case 9. Expired card - Pick up
    Given open shop on storefront
    And add products "Expired card - Pick up" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status             | Error message          |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Expired card - Pick up | Expired Card - Pick Up |
    And close browser

  Scenario: Checkout by Checkout.com in Case 10. Payment blocked due to risk
    Given open shop on storefront
    And add products "Payment blocked due to risk" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status                  | Error message |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Payment blocked due to risk | Risk Blocked  |
    And close browser

  Scenario: Checkout by Checkout.com in Case 11. Gateway Reject – Post code failed
    Given open shop on storefront
    And add products "Gateway Reject – Post code failed" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status                        | Error message                     |
      | Checkout.com    | Checkout.com   | 4242424242424242 | Shop Base       | 01/23        | 100 | Visa      | yes | Checkout1!   | Gateway Reject – Post code failed | Gateway Reject - Post code failed |
    And close browser

  Scenario: Verify input data on Payment method #SB_CHE_CC_12 #SB_CHE_CC_13 #SB_CHE_CC_14 #SB_CHE_CC_15
    Given open shop on storefront
    And add products "Shirt" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    When verify payment method information with "Checkout.com"
      | Case                     | Card Number      | Cardholder name | MM/YY | CVV | Expected                                                                                                                                                              |
      | All fields are empty     |                  |                 |       |     | Your card number is incomplete.,Please enter your name as it appears on the card.,Your card's expiration date is incomplete.,Your card's security code is incomplete. |
      | Card number is not exist | 2222222222222222 | Shop Base       | 01/23 | 111 | Your card number is invalid.                                                                                                                                          |
      | Expired date in the past | 4000000000000002 | Shop Base       | 01/11 | 111 | Your card's expiration is invalid.                                                                                                                                    |
      | CVV has invalid format   | 4000000000000002 | Shop Base       | 01/23 | 11  | Your card's security code is invalid.                                                                                                                                 |
    And close browser