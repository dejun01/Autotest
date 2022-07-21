Feature: Shipping rules
  #env = sbase_shipping_profile

  Background: clear data
    Given clear all data

  Scenario: Shipping fee should be calculated correctly = First item price + additional item price when the order including 1 lineitem
  """Pre-condition: setting shipping rules as shown below for country = United States
  | Profile | Apply for                        | Rate name| First item | Each additional item |
  | Blanket | Product SKU matches regex Blanket| Standard | 8         | 7                     |"""

    Given open shop on storefront on storefront
    When add products "Blanket>2" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Standard   | 15    |
    Given close browser

  Scenario: The order including 2 lineitems belong to 2 different profiles, rate name the same
  """ Pre-condition: setting shipping rules as shown below for country = United States
    | Profile       | Apply for                               | Rate name | First item | Each additional item |
    | T-shirt       | Product SKU matches regex Shirt         | Standard  | 8          | 7                    |
    | Unisex Hoodie | Product SKU matches regex Unisex-Hoodie | Standard  | 10         | 8                    |"""

    Given open shop on storefront
    When add products "T-shirt>2;Unisex Hoodie>1" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Standard   | 25    |
    Given close browser

  Scenario: The order including 2 lineitems belong to 2 different profiles, rate names not the same
  """Pre-condition: setting shipping rules as shown below for country = United States
      | Profile | Apply for                         | Rate name | First item | Each additional item |
      | T-shirt | Product SKU matches regex Shirt   | Standard  | 8          | 7                    |
      | Tumbler | Product SKU matches regex Tumbler | Economy   | 10         | 8                    |"""

    Given open shop on storefront
    When add products "T-shirt>2;Tumbler>1" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Shipping   | 25    |
    Given close browser

  Scenario: Items not matching with any Custom profiles, it should be called back to General profile
  """ setting shipping rules as shown below for country = United States
      | Profile | Apply for                        | Rate name | First item | Each additional item |
      | Canvas  | Product SKU matches regex Canvas | Standard  | 8          | 7                    |
      | General |                                  | Shipping  | 10         | 10                   |"""

    Given open shop on storefront
    When add products "Yonex" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Shipping   | 10    |
    Given close browser

  Scenario: Checkout with shipping country not belong to any profiles
  """setting shipping rules as shown below for country = United States
      | Profile | Apply for                        | Zone          | Rate name | First item | Each additional item |
      | Canvas  | Product SKU matches regex Canvas | United States | Standard  | 8          | 7                    |
      | General |                                  | United States | Shipping  | 10         | 10                   |"""

    Given open shop on storefront
    When add products "Canvas" to cart then checkout
    And input Customer information
      | Email                           | First Name | Last Name | Address | City  | Country | State | Zip code | Phone      |
      | shipping_profile@mailtothis.com | shipping   | profile   | XA DAN  | Hanoi | Vietnam |       | 10000    | 0944193269 |
    Then verify the message along with non shippable products is displayed
      | Product not shippable | Message                                                                                                                |
      | Canvas                | The following items don’t ship to your location. Please replace them with another products and place your order again. |

  Scenario: Items is excluded from certain Custom profile, it not fallbacked to General profile
  """setting shipping rules as shown below for country United States
      | Profile    | Apply for                            | Exclude                                 | Zone          | Rate name | First item | Each additional item |
      | Phone Case | Product SKU matches regex Phone-Case | Product SKU matches regex Phone-Case-11 | United States | Standard  | 5          | 4                    |
      | General    |                                      |                                         | United States | Shipping  | 10         | 10                   |"""

    Given open shop on storefront
    When add products "Phone Case:11" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify the message along with non shippable products is displayed
      | Product not shippable | Message                                                                                                                |
      | Phone Case            | The following items don’t ship to your location. Please replace them with another products and place your order again. |







