Feature: Cancel order payment status is paid
#order_cancel
  Background: Clear data
    Given open shop on storefront

  Scenario: Cancel order has shipping fee #SB_OPB_21 #SB_OPB_30 #SB_OPB_29
    Then checkout of order fulfillment successfully via stripe with cart "Campaign cancel:White,XS"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Cancel      |
    And cancel order on hive pbase
      | Quantity | Withdraw seller balance | Send mail |
      | 1        | false                   | fase      |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Partially refunded" on order list
    And verify order detail after cancel with status is "Partially refunded"
    And close browser

  Scenario: Cancel order shipping fee is free ship
    Then checkout of order fulfillment successfully via stripe with cart "Campaign cancel:White,XS>8"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Cancel      |
    And cancel order on hive pbase
      | Quantity | Withdraw seller balance | Send mail |
      | 8        | false                   | fase      |
    Given redirect to shopbase dashboard
    And user navigate "Orders" screen
    Then verify payment status is "Partially refunded" on order list
    And verify order detail after cancel with status is "Partially refunded"
    And close browser

  Scenario Outline: Cancel order has discount #SB_OPB_23
    Then checkout of order fulfillment successfully via stripe with cart "Campaign cancel:White,XS"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount   |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | <Discount> |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Cancel      |
    And cancel order on hive pbase
      | Quantity | Withdraw seller balance | Send mail |
      | 1        | false                   | fase      |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Partially refunded" on order list
    And verify order detail after cancel with status is "Partially refunded"
    And quit browser

    Examples:
      | Discount    |
      | Discount 30 |
      | freeship    |

  Scenario: Cancel order withdraw seller balance #SB_OPB_20 #SB_OPB_28
    Then checkout of order fulfillment successfully via stripe with cart "Campaign cancel:White,XS"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Cancel      |
    And cancel order on hive pbase
      | Quantity | Withdraw seller balance | Send mail |
      | 1        | true                    | fase      |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Partially refunded" on order list
    And verify order detail after cancel with status is "Partially refunded"
    And close browser

  Scenario: verify send mail cancel order payment status = paid
    Then checkout of order fulfillment successfully via stripe with cart "Campaign cancel:White,XS"
      | Email                                     | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | automation-staff@mailinator.girl-viet.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Cancel      |
    And cancel order on hive pbase
      | Quantity | Withdraw seller balance | Send mail |
      | 1        | true                    | true      |
    Given verify email send to buyer with
      | Subject                              | Email                                     |
      | Order @order name@ has been canceled | automation-staff@mailinator.girl-viet.com |
