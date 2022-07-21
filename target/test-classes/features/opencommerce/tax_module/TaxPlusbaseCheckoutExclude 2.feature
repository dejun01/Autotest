Feature: Checkout Plusbase flow with taxes exclude
  #plusbase_tax_checkout_exclude

  #Settings: tax test mode, product test
  #Shop template tax test mode: Stag: template-tax-plusbase-test.stag.myshopbase.net ; Prod:template-tax-plusbase-test.onshopbase.com

  Background:
    Given clear all data
    Given open shop on storefront

  Scenario: Checkout an Order reach threshold after add item post-purchase, order have both discount code and discount for item ppc
  """OverTaxNewYork - threshold $70 - 10%"""
  """TaxNewYork - threshold $75 - 8%"""
    When add products "(Test product) Auto-cpay2:S" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
      | @mailtothis.com@ | 2056289809 | New York   | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
    Then choose shipping method ""
    And Apply discount code "Percentage10"
    And Verify apply discount for order
      | Discount code | Discount value | Discount type |
      | PERCENTAGE10  | 5              | Percentage    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    And order product "(Test product) Auto-cpay1" with custom option is "" in post purchase offer
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
      | exclude     | (Test product) Auto-cpay2:S | 8           | Percentage    | 10             |
      | exclude     | (Test product) Auto-cpay1   | 10          |               |                |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  Scenario: Checkout with order has tax exclude and discount freeship
  """TaxUS - threshold $0 - 15%"""
    When add products "(Test product) Auto-cpay1:S" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    And Apply discount code "Freeship"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) |
      | exclude     | (Test product) Auto-cpay1:S | 15          |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  Scenario: Checkout with order has tax exclude and discount buy x get y
  """TaxNewYork  - threshold $75 - 8%"""
  """OverTaxNewYork - threshold $70 - 10%"""
    When add products "(Test product) Auto-cpay1:S>1;(Test product) Auto-cpay2:S>1" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
    Then choose shipping method ""
    And Apply discount code "Buyxgety"
    And Verify apply discount for order
      | Discount code | Discount value | Discount type |
      | BUYXGETY      | 5              | Buy x get y   |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
      | exclude     | (Test product) Auto-cpay1:S | 10          |               |                |
      | exclude     | (Test product) Auto-cpay2:S | 8           | Percentage    | 10             |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


  Scenario: Checkout an Order change tax rate after add item postpurchase with Paypal
  """OverTaxCa - threshold $65 - 17%"""
    When add products "(Test product) Auto-cpay2:S" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name  | Address            | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | California | 814 Mission Street | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    |
    And order product "(Test product) Auto-cpay1" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    Then calculate tax amount
      | Type of tax | Product name                | Tax rate(%) |
      | exclude     | (Test product) Auto-cpay2:S | 17          |
      | exclude     | (Test product) Auto-cpay1   | 17          |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser


#--------------------Feature tax update

#  Scenario: Order with items that do not meet the threshold of tax
#    When add products "(Test product) Auto-cpay2>5;(Test product) Auto-cpay1>1" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
#      | @mailtothis.com@ | 2056289809 | New York   | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
#    Then choose shipping method ""
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    Then verify tax on store front
#      | Type of tax |
#      | no tax      |
#    And close browser
#
#  Scenario: Order with items reach threshold for region tax, order have percentage discount
#  """Auto_OverNY - threshold 20$-100$ - 10%"""
#  """Auto_NY - threshold 45-99$ - 8%"""
#
#    When add products "(Test product) Auto-cpay2:S>2" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
#      | @mailtothis.com@ | 2056289809 | New York   | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
#    Then choose shipping method ""
#    And Apply discount code "Percentage10"
#    And Verify apply discount for order
#      | Discount code | Discount value | Discount type |
#      | PERCENTAGE10  | 10             | Percentage    |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    And order product "" with custom option is "" in post purchase offer
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | exclude     | (Test product) Auto-cpay2:S | 8           | Percentage    | 10             |
#    Then verify tax on store front
#      | Type of tax |
#      | exclude     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
#
#  Scenario: Order with items reach threshold for tax, item pre-purchase reach threshold for country tax, item PPC threshold of override tax
#  """Auto_US - threshold 10$-200$ - 15%"""
#  """Auto_OverTexas - threshold 18-200$ - 10%"""
#
#    When add products "(Test product) Auto-cpay2:S>1" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City      | Country       | Zip code | State |
#      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Grandview | United States | 76050    | Texas |
#    Then choose shipping method ""
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    And order product "(Test product) Auto-cpay1" with custom option is "" in post purchase offer
#    And complete payment for post purchase item
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | exclude     | (Test product) Auto-cpay2:S | 15          |               |                |
#      | exclude     | (Test product) Auto-cpay1   | 5           | Percentage    | 10             |
#    Then verify tax on store front
#      | Type of tax |
#      | exclude     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
#
#
#  Scenario: Order with items reach threshold for tax, order have fixed amount discount
#  """Auto_Ca - threshold $20-100 - 20%"""
#  """OverTaxCa - threshold $18-up - 17%"""
#
#    When add products "(Test product) Auto-cpay1:S>1;(Test product) Auto-cpay2:S>2" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City        | Country       | Zip code | State      |
#      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | Los Angeles | United States | 90001    | California |
#    Then choose shipping method ""
#    And Apply discount code "Fixed5"
#    And Verify apply discount for order
#      | Discount code | Discount value | Discount type |
#      | FIXED5        | 5              | Fixed         |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | exclude     | (Test product) Auto-cpay1:S | 17          | Fixed         | 5              |
#      | exclude     | (Test product) Auto-cpay2:S | 20          | Fixed         | 5              |
#    Then verify tax on store front
#      | Type of tax |
#      | exclude     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
#
#
#  Scenario: Checkout with order has tax exclude and discount buy x get y, only item y reach threshold for tax
#  """TaxNewYork  - threshold $75 - 8%"""
#  """OverTaxNewYork - threshold $70 - 10%"""
#
#    When add products "(Test product) Auto-cpay1:S>6;(Test product) Auto-cpay2:S>2" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name | Address            | City     | Country       | Zip code | State    |
#      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | New York | United States | 10001    | New York |
#    Then choose shipping method ""
#    And Apply discount code "Buyxgety"
#    And Verify apply discount for order
#      | Discount code | Discount value | Discount type |
#      | BUYXGETY      | 5              | Buy x get y   |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/25        | 113 | Visa      |
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | exclude     | (Test product) Auto-cpay2:S | 8           | Percentage    | 10             |
#    Then verify tax on store front
#      | Type of tax |
#      | exclude     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
#
#
#  Scenario: Order with items reach threshold for tax, have item postpurchase, checkout with Paypal
#  """OverTaxCa - threshold $65 - 17%"""
#
#    When add products "(Test product) Auto-cpay2:S" to cart then navigate to checkout page
#    And input Customer information
#      | Email            | Phone      | First Name | Last Name  | Address            | City        | Country       | Zip code | State      |
#      | @mailtothis.com@ | 2056289809 | David      | California | 814 Mission Street | Los Angeles | United States | 90001    | California |
#    Then choose shipping method ""
#    Then calculate tax amount
#      | Type of tax | Product name                | Tax rate(%) | Discount type | Discount value |
#      | exclude     | (Test product) Auto-cpay2:S | 20          |               |                |
#    Then verify tax on store front
#      | Type of tax |
#      | exclude     |
#    And input payment information and complete order
#      | Payment method | Payment gateway | Paypal account     | Password | Card type |
#      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    |
#    And order product "(Test product) Auto-cpay1" with custom option is "" in post purchase offer
#    And complete payment for post purchase item
#    Then calculate tax amount
#      | Type of tax | Product name              | Tax rate(%) | Discount type | Discount value |
#      | exclude     | (Test product) Auto-cpay1 | 17          | Percentage    | 10             |
#    Then verify tax on store front
#      | Type of tax |
#      | exclude     |
#    And get the order information including
#      | order id |
#    Given Access to order detail by order ID
#    And verify tax line in order
#    And close browser
