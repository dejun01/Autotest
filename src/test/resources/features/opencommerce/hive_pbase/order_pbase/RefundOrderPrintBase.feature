Feature: Refund order pbase
#hive_pbase
  Background: Clear data
    Given clear all data
    Given open shop on storefront

  Scenario: Verify refund order in hive pbase #SB_OPB_27
    Then checkout of order fulfillment successfully via stripe with cart "Campaign refund:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Refund      |
    And refund order on hive pbase
      | Order name   | Quantity | Withdraw seller balance | Send mail |
      | @order name@ | 1        | false                   | fase      |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Partially refunded" on order list
    And verify order detail after refund with status is "Partially refunded"
    And close browser

  Scenario Outline: Verify refund order has discount freeship in hive pbase
    Then checkout of order fulfillment successfully via stripe with cart "Campaign refund:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | <Discount> |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Refund      |
    And refund order on hive pbase
      | Order name   | Quantity | Withdraw seller balance | Send mail |
      | @order name@ | 1        | false                   | fase      |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Partially refunded" on order list
    And verify order detail after refund with status is "Partially refunded"
    And close browser

    Examples:
      | Discount    |
      | Discount 30 |
      | freeship    |

  Scenario: verify Withdraw seller balance #SB_OPB_20
    Then checkout of order fulfillment successfully via stripe with cart "Campaign refund:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount    |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Discount 30 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Refund      |
    And refund order on hive pbase
      | Order name   | Quantity | Withdraw seller balance | Send mail |
      | @order name@ | 1        | true                    | fase      |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Partially refunded" on order list
    And verify order detail after refund with status is "Partially refunded"
    And close browser

  Scenario: verify dont choice quantity refund #SB_OPB_26 #SB_OPB_25 #SB_OPB_24
    Then checkout of order fulfillment successfully via stripe with cart "Campaign refund:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount    |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Discount 30 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify payment status is "Authorized" on order list
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Refund      |
    And refund order on hive pbase
      | Order name   | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @order name@ | 1        | false                   | fase      | true            |
    Given redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Partially refunded" on order list
    And verify order detail after refund with status is "Partially refunded"
    And close browser

  Scenario: verify send mail refund order
    Then checkout of order fulfillment successfully via stripe with cart "Campaign refund:White,S"
      | Email                                     | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount    |
      | automation-staff@mailinator.girl-viet.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Discount 30 |
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    Given view order detail on hive
      | Payment status | Button name |
      | Paid           | Refund      |
    And refund order on hive pbase
      | Order name   | Quantity | Withdraw seller balance | Send mail |
      | @order name@ | 1        | true                    | true      |
    Given verify email send to buyer with
      | Subject             | Email                                     |
      | Refund notification | automation-staff@mailinator.girl-viet.com |

