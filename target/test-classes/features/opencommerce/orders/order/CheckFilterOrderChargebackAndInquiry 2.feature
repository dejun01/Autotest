Feature: Check filter chargeback and inquiry for Spay Reseller
#  env = spay_reseller_dispute

  Background:
    Given clear all data

  Scenario: Check filter tool chargeback and inquiry Spay Reseller with order inquiry
    #create order
    Given open shop on storefront
    When add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Inquiry    | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550976 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000001976 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number |
    #check filter
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And Filter order with
      | Filter label        | Item filter  |
      | Chargeback status   | Open         |
      | Inquiry status      | Open         |
    Then verify orders in list order
      | Order        |
      | order number |
    And compare quantity of orders open chargeback inquiry with tab Chargebacks & inquiries

  Scenario: Check filter tab chargeback and inquiry Spay Reseller with order chargeback
    #create order
    Given open shop on storefront
    When add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Chargeback | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550259 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000000259 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number |
    #check tab
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    When select the tab "Chargebacks & inquiries" in Orders list
    And Sort "descending" order list by field "Due Date"
    Then verify orders in list order
      | Order        |
      | order number |
    Given Access to order detail by order ID
    Given submit response for dispute order
      | Dispute type | Product description        | Customer name         | Customer email                | Customer signature | Billing address        | Receipt | Customer communication | Shipping address        | Date of shipment | Package carrier         | Tracking number | Proof of shipping | Proof of access or downloading product        | Additional evidence or statements text | Additional evidence or statements file |
      | Chargeback   | Product description update | Customer name updated | customeremail_update@mail.com | yes                | Billing address update | yes     | yes                    | Shipping address update | now              | Package carrier updated | Tracking1       | yes               | Proof of access or downloading product update | winning_evidence                       | yes                                    |
    And user navigate to "Orders" screen
    When select the tab "Chargebacks & inquiries" in Orders list
    And Sort "descending" order list by field "Due Date"
    Then verify orders in list order
      | Order |
      |       |

