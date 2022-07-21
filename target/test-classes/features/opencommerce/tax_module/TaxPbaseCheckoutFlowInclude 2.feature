Feature: Tax Pbase checkout flow with tax include
  #pbase_tax_checkout_include

  Background:
    Given clear all data
    Given open shop on storefront

  Scenario: Checkout an order didnt reach threshold for tax in UK
  """UK tax - threshold $100 - 10%"""
    When add products "Bikini" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country        | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United Kingdom | 90001    |
    Then verify tax on store front
      | Type of tax |
      | no tax      |
    Then close browser

  Scenario: Checkout an order reach threshold for tax in US (include shipping fee)
  """US tax - threshold $30 - 5%"""
    When add products "Bikini" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | 90001    | California |
    Then choose shipping method "Fast Shipping"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page
    Then calculate tax amount
      | Type of tax | Product name | Tax rate(%) |
      | include     | Bikini       | 5           |
    Then verify tax on store front
      | Type of tax |
      | include     |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    And verify tax line in order
    And close browser

  Scenario: Order has items with the same tax rule, have PPC, checkout with Paypal
  """Cali tax - threshold $75 - 10%"""
    When add products "Bikini1>1;Bikini2>1" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | 90001    | California |
    Then verify tax on store front
      | Type of tax |
      | include     |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Is Printbase shop |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    | true              |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    Then verify thank you page
    Then calculate tax amount
      | Type of tax | Product name | Tax rate(%) |
      | include     | Bikini1      | 10          |
      | include     | Bikini2      | 10          |
      | include     | Bikini       | 10          |
    Then verify tax on store front
      | Type of tax |
      | include     |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    And verify tax line in order
    And close browser

  Scenario: Order has items with the different tax rules, have PPC, checkout with Stripe
  """Cali tax - threshold $75 - 10%"""
  """Override tax - threshold $90 - 20%"""
    When add products "Tax Override>1;Bikini2>1" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Bikini" with custom option is "" in post purchase offer
    Then verify thank you page
    Then calculate tax amount
      | Type of tax | Product name | Tax rate(%) |
      | include     | Tax Override | 20          |
      | include     | Bikini2      | 10          |
      | include     | Bikini       | 10          |
    Then verify tax on store front
      | Type of tax |
      | include     |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    And verify tax line in order
    And close browser

  Scenario: Order has reached the tax threshold after add PPC
  """UK tax - threshold $100 - 10%"""
    When add products "Bikini22" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country        | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United Kingdom | 90001    |
    Then verify tax on store front
      | Type of tax |
      | no tax      |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Bikini2" with custom option is "" in post purchase offer
#    Then Verify alert line for tax threshold in PPC popup
    Then verify thank you page
    Then verify tax on store front
      | Type of tax |
      | include     |
    And close browser

  Scenario: Order has reached the tax threshold after add PPC, checkout via Paypal
  """UK tax - threshold $100 - 10%"""
    When add products "Bikini22" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country        | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United Kingdom | 90001    |
    Then verify tax on store front
      | Type of tax |
      | no tax      |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Is Printbase shop |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    | true              |
    And order product "Bikini2" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    Then verify thank you page
    Then calculate tax amount
      | Type of tax | Product name | Tax rate(%) |
      | include     | Bikini22     | 10          |
      | include     | Bikini2      | 10          |
    Then verify tax on store front
      | Type of tax |
      | include     |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    And verify tax line in order