@ManageAdcOrders
Feature: Manage Orders
#env = sbase_adc

  Background: clear data
    Given clear all data

  Scenario: verify UI then check validation when placing order with unmapped item #SB_ADC_MO_15 #SB_ADC_MO_14 #SB_ADC_MO_13 #SB_ADC_MO_12 #SB_ADC_MO_11 #SB_ADC_MO_10 #SB_ADC_MO_9 #SB_ADC_MO_8 #SB_ADC_MO_6 #SB_ADC_MO_4 #SB_ADC_MO_3 #SB_ADC_MO_2
    #1. Place an order
    Given open shop on storefront
    And checkout successfully via stripe with cart "yonex racket>2"
      | Email                     | First Name | Last Name | Address         | Country       | City     | Zip code | State    | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | adcsupport@mailtothis.com | Emma       | Watson    | 538 Late Avenue | United States | Oklahoma | 73109    | Oklahoma | 2025550141 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then verify thank you page
   #2. Open ADC app
    When user navigate to screen "/admin/apps/alidropcon/fulfill-orders" by API
    And expand order "" in list ADC
    #3. verify order detail
    And verify order detail in manage order
      | Product Name | Product Cost | Customer    | Shipping Address            | Shipping Method | Mapping Status | ADC Order Status | AliExpress Order | Tracking Number | Order Warning                                                |
      | yonex racket | -            | Emma Watson | Shipping address is invalid | Missing info    | Need to map    | Awaiting Order   | -                | -               | * Please config all red notices information to fulfill order |
    #4. Check validation of Shipping address popup
    When open Shipping Address popup and verify
      | Edit data | Test case                                                           | First name | Last name | Phone number | Address             | Country       | State    | City          | ZIP/Postal Code | Message                                                                 | Warning icon Shipping address |
      | No        | Initial value address matching with ShopBase address                | Emma       | Watson    | 2025550141   | 538 Late Avenue     | United States | Oklahoma |               | 73109           |                                                                         |                               |
      | Yes       | Leave First name field blank                                        | @BLANK@    | Watson    | 2025550141   | 538 Late Avenue     | United States | Oklahoma | Oklahoma city | 73109           | Please enter first name                                                 |                               |
      | Yes       | Leave Last name field blank                                         | Emma       | @BLANK@   | 2025550141   | 538 Late Avenue     | United States | Oklahoma | Oklahoma city | 73109           | Please enter last name                                                  |                               |
      | Yes       | Leave Address field blank                                           | Emma       | Watson    | 2025550141   | @BLANK@             | United States | Oklahoma | Oklahoma city | 73109           | Please enter address                                                    |                               |
      | Yes       | Leave Postal Code field blank                                       | Emma       | Watson    | 2025550141   | 538 Late Avenue     | United States | Oklahoma | Oklahoma city | @BLANK@         | Please enter postal code                                                |                               |
      | Yes       | Leave Phone number field blank                                      | Emma       | Watson    | @BLANK@      | 538 Late Avenue     | United States | Oklahoma | Oklahoma city | 73109           | Please enter phone number                                               |                               |
#      | Yes       | Enter value which is not alphabet letters                           | Phạm       | Kiều      | 2025550141   | Xã Đàn              | United States | Oklahoma | Oklahoma city | 73109           | Please enter Latin only                                                 |                               |
      | Yes       | Enter invalid format in phone number                                | Emma       | Watson    | Phonenumber  | 538 Late Avenue     | United States | Oklahoma | Oklahoma city | 73109           | Please enter Numbers and/or hyphens '-' and/or forward slashes '/' only |                               |
      | Yes       | Enter value less than 5 or more than 16 characters for phone number | Emma       | Watson    | 033          | 538 Late Avenue     | United States | Oklahoma | Oklahoma city | 73109           | Please enter 5-16 characters                                            |                               |
      | Yes       | Updating address successfully                                       | John       | Hall      | 716-897-1358 | 2168  Jarvis Street | United States | New York | Buffalo       | 14211           | Saved successfully                                                      | No                            |
    Then verify click on order name in ADC app should be redirected to ShopBase order

  Scenario Outline: Can not cancel SB order, Refund SB order successfuly if ADC order has status as "Fulfilled" #SB_ADC_MO_27 #SB_ADC_MO_23
    When open shop on storefront
    Then checkout successfully via stripe with cart "Hoodie"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard
    When user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    When user navigate to "Manage Orders" screen
    Then expand order "" in list ADC
    And click on "Mark as fulfilled" button of the product "Hoodie"
    And switch to "Fulfilled" tab in ADC
    And click on order "" to redirect ShopBase order detail
    # cannot cancel SB order
    And verify order fulfilled should be hidden "Cancel order" option
    # refund SB order successfully
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    Then verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
    Examples:
      | Refunded item | Restock item | Refund shipping | Reason for refund  | Status   | Net payment |
      | Hoodie>1      | true         | @full shipping@ | Damaged in transit | Refunded | $0.00       |

  Scenario Outline: Verify ADC order is canceled fully when SB order is refunded fully #SB_ADC_MO_24
    When open shop on storefront
    Then checkout successfully via stripe with cart "Shirt"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
#    When user navigate to screen "/admin/apps/alidropcon" by API
    When user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    And verify text "Welcome to Ali Dropship Connector" is displayed
    When user navigate to "Manage Orders" screen
    And switch to "Canceled" tab in ADC
    And expand order "" in list ADC
    Then verify order detail in manage order
      | ADC Order Status |
      | Canceled         |
    And close browser
    Examples:
      | Refunded item | Restock item | Refund shipping | Reason for refund  |
      | Shirt>1       | true         | @full shipping@ | Damaged in transit |