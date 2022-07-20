Feature: Refund order plusbase
#refund_plusbase

  Background: Access shop om storefront
    Given open shop on storefront

  Scenario: 1. Refund order has shipping fee
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 1        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 1        | false                   | false     | true            |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 1        | false             |
    Given redirect to shopbase dashboard
    And verify balance of order after refund with status = "Paid"


  Scenario: 2. Refund order has shipping freeship
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 5        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 5        | false                   | false     | true            |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 5        | false             |
    Given redirect to shopbase dashboard
    And verify balance of order after refund with status = "Paid"

  Scenario: 3. Refund order has discount freeship
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | freeship |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 1        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 1        | false                   | false     | true            |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 1        | true              |
    Given redirect to shopbase dashboard
    And verify balance of order after refund with status = "Paid"

  Scenario: 4. Refund order has discount # freeship
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount    |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Discount 30 |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 1        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 1        | false                   | false     | true            |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 1        | false             |
    Given redirect to shopbase dashboard
    And verify balance of order after refund with status = "Paid"

  Scenario: 5. Refund order Withdraw seller balance
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 1        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 1        | true                    | false     | true            |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 1        | false             |
    Given redirect to shopbase dashboard
    And verify balance of order after refund with status = "Paid"

  Scenario: 6. Refund order dont choice quantity refund
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 1        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 1        | true                    | false     | false           |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 1        | false             |
    Given redirect to shopbase dashboard
    And verify balance of order after refund with status = "Paid"

  Scenario: 7. Refund order send mail refund order
    Then checkout of order fulfillment successfully via stripe with cart "Knitted skirt"
      | Email                                     | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | automation-staff@mailinator.girl-viet.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status is "Authorized" on order list
    And get balance before refund order
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status is "Authorized" on order list
    Then verify Order detail with
      | Paymnet status | Product       | Quantity | Is button refund |
      | Authorized     | Knitted skirt | 1        | yes              |
    When Refund order plusbase with
      | Domain   | Order name   | Product       | Quantity | Withdraw seller balance | Send mail | Choice quantity |
      | @domain@ | @order name@ | Knitted skirt | 1        | false                   | true      | true            |
    Then verify refund order plusbase
      | Payment status     | Product       | Quantity | Discount freeship |
      | Partially refunded | Knitted skirt | 1        | false             |
    Given verify email send to buyer with
      | Subject             | Email                                     |
      | Refund notification | automation-staff@mailinator.girl-viet.com |