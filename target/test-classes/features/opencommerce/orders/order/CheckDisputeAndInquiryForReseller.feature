Feature: Check dispute and inquiry for Spay Reseller
#  env = spay_reseller_dispute

  Background:
    Given clear all data

  Scenario: Dispute flow - Processing dispute scenario
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000000259 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list |
    Given Access to order detail by order ID
    Then verify dispute warning message in order details
    Then verify initial information of chargeback response form
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Chargeback   | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | processing_evidence                    | yes                                    |
    Then verify message and response after dispute has been decided

  Scenario: Dispute flow - Winning dispute scenario
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000000259 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list |
    Given Access to order detail by order ID
    Then verify dispute warning message in order details
    Then verify initial information of chargeback response form
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Chargeback   | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | winning_evidence                       | yes                                    |
    Then verify message and response after dispute has been decided


  Scenario: Dispute flow - Losing dispute scenario
    Given open shop on storefront
    And  add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000002685 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list |
    Given Access to order detail by order ID
    Then verify dispute warning message in order details
    Then verify initial information of chargeback response form
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Chargeback   | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | losing_evidence                        | yes                                    |
    Then verify message and response after dispute has been decided

  Scenario: Dispute flow - Processing inquiry scenario
    Given open shop on storefront
    And  add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000001976 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list |
    Given Access to order detail by order ID
    Then verify dispute warning message in order details
    Then verify initial information of chargeback response form
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Inquiry      | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | processing_evidence                    | yes                                    |
    Then verify message and response after dispute has been decided


  Scenario: Dispute flow - Winning inquiry scenario
    Given open shop on storefront
    And  add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000001976 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list |
    Given Access to order detail by order ID
    Then verify dispute warning message in order details
    Then verify initial information of chargeback response form
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Inquiry      | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | winning_evidence                       | yes                                    |
    Then verify message and response after dispute has been decided


  Scenario: Dispute flow - Losing inquiry scenario
    Given open shop on storefront
    And  add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000001976 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list |
    Given Access to order detail by order ID
    Then verify dispute warning message in order details
    Then verify initial information of chargeback response form
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Inquiry      | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | losing_evidence                        | yes                                    |
    Then verify message and response after dispute has been decided

