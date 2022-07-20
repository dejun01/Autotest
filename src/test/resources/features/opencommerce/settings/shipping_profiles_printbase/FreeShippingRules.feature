Feature: Free shipping rules for PrintBase
  #env = pbase_checkout_shipping_free

  Background: clear all data
    Given clear all data

  Scenario: Order will be not applied free shipping rule if the order doesnt reach the free shipping threshold
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>3" to cart then navigate to checkout page
    And input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | $9.97 |
    Then verify shipping fee on order summary is "$9.97"
    And close browser

  Scenario: Order will be not applied free shipping rule if the order doesnt reach the free shipping threshold after apply discount #SB_SET_SPP_FS_2 #SB_SET_SPP_FS_1
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>4" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | Free  |
    Then verify shipping fee on order summary is "Free"
    And input discount code is "discount"
    Then verify shipping base on Profile rules
      | Base rules        | Price  |
      | Standard Shipping | $11.96 |
    And close browser

  Scenario: Checkout with PayPal: Order will be applied free shipping rule if the order reach the free shipping threshold
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>4" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | Free  |
    Then verify shipping fee on order summary is "Free"
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Is Printbase shop |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    | true              |
    And order product "free-shipping" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And verify shipping method after add PPC
      | Shipping method   | Price |
      | Standard shipping | Free  |
    And verify thank you page

  Scenario: Order will be applied free shipping rule if the order reach the free shipping threshold after apply discount #SB_SET_SPP_FS_4
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>5" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | Free  |
    Then verify shipping fee on order summary is "Free"
    And input discount code is "discount"
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | Free  |
    Then verify shipping fee on order summary is "Free"
    And close browser

  Scenario: Order will be not applied free shipping rule if the order reach the free shipping threshold after add PPC
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>3" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | $9.97 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "free-shipping" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method   | Price  |
      | Standard shipping | $11.96 |
    Then verify shipping fee on order summary is "$11.96"
    And verify thank you page

  Scenario: Checkout with Paypal: Order will be not applied free shipping rule if the order reach the free shipping threshold after add PPC
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>3" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | $9.97 |
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Is Printbase shop |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    | true              |
    And order product "free-shipping" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And verify shipping method after add PPC
      | Shipping method   | Price  |
      | Standard shipping | $11.96 |
    And verify thank you page

  Scenario: Order will be applied free shipping discount with code although the order doesnt reach the free shipping threshold #SB_SET_SPP_FS_6
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "free-shipping>3" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | $9.97 |
    And input discount code is "free_shipping"
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard Shipping | Free  |
    And close browser

  Scenario: Check Ip croatia #SB_SET_SPP_FS_7
  """Pre-condition: setting free shipping rule for shipping address = US"""
    Given open shop on storefront
    When add products "CheckIpcroatia>3" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | Croatia | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price  |
      | Standard Shipping | $28.47 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page

  Scenario: Change shipping fee on checkout page when add item more to cart #SB_SET_SPP_SPR_3
    Given open shop on storefront
    When add products "free-shipping" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price |
      | Standard shipping | $5.99 |

    Given open shop on storefront
    When add products "CheckIpcroatia" to cart then navigate to checkout page
    Then verify shipping base on Profile rules
      | Base rules        | Price  |
      | Standard shipping | $12.98 |
    Then verify shipping fee on order summary is "$12.98"
    And close browser