Feature: Checkout Plusbase flow with taxes include
  #plusbase_tax_checkout

   #Settings: tax test mode, product test
   #Shop template tax test mode: Stag: template-tax-plusbase-test.stag.myshopbase.net ; Prod:template-tax-plusbase-test.onshopbase.com

  Background:
    Given clear all data
    Given open shop on storefront

  Scenario: Checkout an order reach threshold for region tax in US
  """TaxCa - threshold $25 - 20%"""
    When add products "(Test product) Auto-cpay1:S" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) |
      | include     | (Test product) Auto-cpay1:S | 20          |
    Then verify tax on store front
      | Type of tax |
      | include     |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  Scenario: Checkout an Order reach threshold for override tax after add item postpurchase
  """OverTaxCa - threshold $65 - 17%"""
    When add products "(Test product) Auto-cpay2:S" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    And order product "(Test product) Auto-cpay1" with custom option is "" in post purchase offer
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) |
      | include     | (Test product) Auto-cpay2:S | 17          |
      | include     | (Test product) Auto-cpay1   | 17          |
    Then verify tax on store front
      | Type of tax |
      | include     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  Scenario: Checkout with order has tax include and discount percentage
  """TaxNewYork  - threshold $75 - 8%"""
  """OverTaxNewYork - threshold $70 - 10%"""
    When add products "(Test product) Auto-cpay1:S>1;(Test product) Auto-cpay2:S>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
    Then choose shipping method ""
    And Apply discount code "Percentage10"
    And Verify apply discount for order
      | Discount code | Discount value | Discount type |
      | PERCENTAGE10  | 3              | Percentage    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
      | include     | (Test product) Auto-cpay2:S | 8           | Percentage    | 10             |
      | include     | (Test product) Auto-cpay1:S | 10          | Percentage    | 10             |
    Then verify tax on store front
      | Type of tax |
      | include     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  Scenario: Checkout with order has tax include and discount fixed amount
  """TaxNewYork  - threshold $75 - 8%"""
  """OverTaxNewYork - threshold $70 - 10%"""
    When add products "(Test product) Auto-cpay2:S>1;(Test product) Auto-cpay1:S>1" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
    Then choose shipping method ""
    And Apply discount code "Fixed5"
    And Verify apply discount for order
      | Discount code | Discount value | Discount type |
      | FIXED5        | 5              | Fixed amount  |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
      | include     | (Test product) Auto-cpay2:S | 8           | Fixed         | 5              |
      | include     | (Test product) Auto-cpay1:S | 10          | Fixed         | 5              |
    Then verify tax on store front
      | Type of tax |
      | include     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  #--------------------Feature tax update

#  Scenario: Checkout an order reach threshold for country tax in US
#  """Auto_US - threshold $10-$200 - 15%"""
#
#    When add products "(Test product) Auto-cpay2:S" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City      | Country       | Zip code | State |
#      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Grandview | United States | 76050    | Texas |
#    Then choose shipping method ""
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) |
#      | include     | (Test product) Auto-cpay2:S | 15          |
#    Then verify tax on store front
#      | Type of tax |
#      | include     |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
#
#
#  Scenario: Order with items reach threshold for tax, add item postpurchase
#  """Auto_Ca - threshold $20-100 - 20%"""
#  """Auto_OverCa - threshold $18-up - 17%"""
#
#    When add products "(Test product) Auto-cpay2:S>1" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City        | Country       | Zip code | State      |
#      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Los Angeles | United States | 90001    | California |
#    Then choose shipping method ""
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | include     | (Test product) Auto-cpay2:S | 20          |               |                |
#    Then verify tax on store front
#      | Type of tax |
#      | include     |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    And order product "(Test product) Auto-cpay1" with custom option is "" in post purchase offer
#    Then calculate tax amount
#      | Type of tax | Product name              | Tax rate(%) | Discount type | Discount value |
#      | include     | (Test product) Auto-cpay1 | 17          | Percentage    | 10             |
#    Then verify tax on store front
#      | Type of tax |
#      | include     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
#
#
#  Scenario: Checkout with order has tax include and discount fixed amount
#  """Auto_NY  - threshold $45-$99 - 8%"""
#  """Auto_OverNY - threshold $20-$100 - 10%"""
#
#    When add products "(Test product) Auto-cpay1:S>2;(Test product) Auto-cpay2:S>1" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
#      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
#    Then choose shipping method ""
#    And Apply discount code "Fixed5"
#    And Verify apply discount for order
#      | Discount code | Discount value | Discount type |
#      | FIXED5        | 5              | Fixed amount  |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | include     | (Test product) Auto-cpay1:S | 10          | Fixed         | 5              |
#      | include     | (Test product) Auto-cpay2:S | 8           | Fixed         | 5              |
#    Then verify tax on store front
#      | Type of tax |
#      | include     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
