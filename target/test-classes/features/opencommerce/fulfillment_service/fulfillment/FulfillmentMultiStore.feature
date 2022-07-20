Feature: Fulfillment service multi store
  #fulfill_multi

  Background: Access warehourse page
    Given clear all data
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "Digital Car Tyre Tire"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |

  Scenario: 1. Fulfillment service multi store with user owner store SB_RLSBFF_RLSBFF_23 SB_RLSBFF_RLSBFF_23
    Given open secondShop on storefront
    Then checkout successfully via stripe with cart "Digital Car Tyre Tire"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    When user navigate to "fulfillment>PlusHub>Warehouse" screen
    And get value inventory of product before fulfill
      | Product               |
      | Digital Car Tyre Tire |
    When user move to "Fulfillment" screen
    Then Search and verify fulfillment status = "Unfulfilled" on order of
      | Shop            |
      | the first shop  |
      | the second shop |
    And fulfill order with multi shop
      | Action       | Button fulfill | Button selected order   |
      | Fulfill with | Fulfill orders | Fulfill selected orders |
    When user navigate to "fulfillment>PlusHub>Warehouse" screen
    And get and verify value in inventory after fulfill
      | Product               | Quantity | Status     |
      | Digital Car Tyre Tire | 2        | Processing |
    And redirect to home shopbase
    When user navigate to "Orders" screen
    And verify order detail after fulfillemnt of the first shop
      | Fulfillment status | Group fulfilled       | Product               | SKU    | Quantity | Unit price | Total   | Button cancel      | Button tracking | Email                   | First name | Last name |
      | Processing         | Processing by PlusHub | Digital Car Tyre Tire | PD2021 | 1        | $120.00    | $120.00 | Cancel fulfillment | Add tracking    | shopbase@mailtothis.com | Shop       | Base      |
    When user navigate to "Home>Orders" screen
    When search order of the second shop with fulfillment status = "Processing"
    And switch to the lastest tab of fulfillment service
    And verify order detail after fulfillemnt of the second shop
      | Fulfillment status | Group fulfilled       | Product               | SKU    | Quantity | Unit price | Total   | Button cancel      | Button tracking | Email                   | First name | Last name |
      | Processing         | Processing by PlusHub | Digital Car Tyre Tire | PD2021 | 1        | $120.00    | $120.00 | Cancel fulfillment | Add tracking    | shopbase@mailtothis.com | Shop       | Base      |
    And quit browser

  Scenario: 2. Fulfillment service multi store with user staff store
    Given user login to firstShop dashboard
    When user navigate to "fulfillment>PlusHub>Warehouse" screen
    And get value inventory of product before fulfill
      | Product               | Quantity |
      | Digital Car Tyre Tire | 1        |
    Given user navigate to "Orders" screen
    Then Search and verify fulfillment status = "Unfulfilled" on order of
      | Shop           |
      | the first shop |
    And fulfill order with multi shop
      | Action       | Button fulfill | Button selected order   |
      | Fulfill with | Fulfill orders | Fulfill selected orders |
    Given user navigate to "Orders" screen
    And verify order detail after fulfillemnt of the first shop
      | Fulfillment status | Group fulfilled       | Product               | SKU    | Quantity | Unit price | Total   | Button cancel      | Button tracking | Email                   | First name | Last name |
      | Processing         | Processing by PlusHub | Digital Car Tyre Tire | PD2021 | 1        | $120.00    | $120.00 | Cancel fulfillment | Add tracking    | shopbase@mailtothis.com | Shop       | Base      |
    And user navigate to "Fulfillment>PlusHub>Warehouse" screen
    And get and verify value in inventory after fulfill
      | Product               | Quantity | Status     |
      | Digital Car Tyre Tire | 1        | Processing |

