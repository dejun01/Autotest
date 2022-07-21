Feature: ETA Delivery Time for Printbase
#  printbase_ETA_Delivery_Time

  Background:
    Given clear all data

  Scenario: ETA Delivery Time for Printbase when Min shipping time < Max shipping time
  """Pre-condition: setting shipping rules as shown below for shipping address = US"
     | Profile       | Rate name         | First item | Each additional item | Min shipping time | Max shipping time |
     | xx1_US_Rule01 | Economic Shipping | 7          | 2                    | 5                 |  7                |"""
    Given set shipping delivery time
      | Min shipping time | Max shipping time |
      | 5                 | 7                 |
    Given open shop on storefront
    And search and select the product ""
    Then verify ETA shipping time on "product page"
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    Then verify ETA shipping time on "checkout page"
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then verify ETA shipping time on "order status page"

  Scenario: ETA Delivery Time for Printbase when Min shipping time = Max shipping time
  """Pre-condition: setting shipping rules as shown below for shipping address = US"
     | Profile       | Rate name         | First item | Each additional item | Min shipping time | Max shipping time |
     | xx1_US_Rule02 | Economic Shipping | 7          | 2                    | 5                 |  5                |"""
    Given set shipping delivery time
      | Min shipping time | Max shipping time |
      | 5                 | 5                 |
    Given open shop on storefront
    And search and select the product ""
    Then verify ETA shipping time on "product page"
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    Then verify ETA shipping time on "checkout page"
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then verify ETA shipping time on "order status page"

  Scenario: ETA Delivery Time for Printbase when Min shipping time is null
  """Pre-condition: setting shipping rules as shown below for shipping address = US"
     | Profile       | Rate name         | First item | Each additional item | Min shipping time | Max shipping time |
     | xx1_US_Rule03 | Economic Shipping | 7          | 2                    |                   |  7                |"""
    Given set shipping delivery time
      | Min shipping time | Max shipping time |
      |                   | 7                 |
    Given open shop on storefront
    And search and select the product ""
    Then verify ETA shipping time on "product page"
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    Then verify ETA shipping time on "checkout page"
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then verify ETA shipping time on "order status page"