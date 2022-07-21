Feature: Fraud Rule
  #env = order_in_fraud_rule

  Background:
    Given clear all data
    Given user login to firstShop dashboard by API

  Scenario Outline: Verify fraud filter when order ShopBase meet rule "AU-The number of items in an order is greater than 10 and less than 50" with action Allow order
    Given user navigate to "Orders>Fraud filter" screen
    And get quantity of order affect of rule "<rule name>"
    Given open secondShop on storefront
    And add products "Shirt>11" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Super      | Tester    | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page then get all information
    Given user login to firstShop dashboard by API
    Given user navigate to "Orders>Fraud filter" screen
    Then Compare "<increase change>" of order affect when order meet the rule "<rule name>"
    And user navigate to "Orders" screen
    And open order detail dashboard by ID
    Then verify status order status is "Paid"
    And close browser
    Examples:
      | rule name                                                              | increase change |
      | AU-The number of items in an order is greater than 10 and less than 50 | 1               |

  Scenario Outline: Verify fraud filter when order ShopBase meet rule "AU-3D secure required is equal to false" with action Cancel order
    Given user navigate to "Orders>Fraud filter" screen
    And get quantity of order affect of rule "<rule name>"
    Given open secondShop on storefront
    And add products "Shirt" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Super      | Tester    | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page then get all information
    Given user login to firstShop dashboard by API
    Given user navigate to "Orders>Fraud filter" screen
    Then Compare "<increase change>" of order affect when order meet the rule "<rule name>"
    And user navigate to "Orders" screen
    And open order detail dashboard by ID
    Then verify status order status is "Cancelled"
    And close browser
    Examples:
      | rule name                               | increase change |
      | AU-3D secure required is equal to false | 1               |

  Scenario Outline: Verify fraud filter when order PrintBase meet rule "AU-The number of items in an order is greater than 50" with action Review order
    Given user navigate to "Orders>Fraud filter" screen
    And get quantity of order affect of rule "<rule name>"
    Given open secondShop on storefront
    And add products "Shirt>51" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Super      | Tester    | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page then get all information
    Given user login to firstShop dashboard by API
    Given user navigate to "Orders>Fraud filter" screen
    Then Compare "<increase change>" of order affect when order meet the rule "<rule name>"
    And user navigate to "Orders" screen
    And open order detail dashboard by ID
    Then verify status order status is "Paid"
    And close browser
    Examples:
      | rule name                                             | increase change |
      | AU-The number of items in an order is greater than 50 | 1               |
