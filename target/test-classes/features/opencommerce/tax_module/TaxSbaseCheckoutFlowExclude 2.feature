Feature: Tax Sbase checkout flow with tax exclude
  #sbase_tax_checkout_exclude

  Background:
    Given clear all data
    And open shop on storefront

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

  Scenario: Checkout an order reach threshold for country tax in US, has tax shipping
  """US tax - threshold $0 - 5%"""
    When add products "Bikini" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | 90001    | California |
    Then choose shipping method "Shipping weight"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    Then calculate tax amount
      | Type of tax | Product name | Tax for ship | Tax rate(%) |
      | exclude     | Bikini       |              | 5           |
      | exclude     |              | true         | 5           |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And close browser

  Scenario: Order has items with the same tax rule (region tax), has PPC, checkout with Paypal #SB_SET_TSB_372 #SB_SET_TSB_375
  """Cali tax - threshold $75 - 10%"""
    When add products "Bikini1>1;Bikini2>1" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    Then calculate tax amount
      | Type of tax | Product name | Tax for ship | Tax rate(%) |
      | exclude     | Bikini1      |              | 10          |
      | exclude     | Bikini2      |              | 10          |
      | exclude     | Bikini       |              | 10          |
      | exclude     |              | true         | 10          |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And get the order information including
      | order id |
    Given Access to order detail by order ID
    And verify tax line in order
    And close browser

  Scenario: Order has items with the different tax rules, has tax override, has PPC, checkout with Stripe #SB_SET_TSB_350 #SB_SET_TSB_365 #SB_SET_TSB_369 #SB_SET_TSB_373
  """Cali tax - threshold $75 - 10% /"""
  """Override tax - threshold $90 - 20%"""
    When add products "Tax Override1>1;Bikini2>1" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | Zip code | State      |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | 90001    | California |
    Then choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Tax Override" with custom option is "" in post purchase offer
    Then calculate tax amount
      | Type of tax | Product name  | Tax for ship | Tax rate(%) |
      | exclude     | Tax Override1 |              | 20          |
      | exclude     | Bikini2       |              | 10          |
      | exclude     | Tax Override  |              | 20          |
      | exclude     |               | true         | 10          |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    And close browser

  Scenario: Order has reached the tax threshold after add PPC, checkout via Stripe #SB_SET_TSB_356
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
    Then calculate tax amount
      | Type of tax | Product name | Tax for ship | Tax rate(%) |
      | exclude     | Bikini22     |              | 10          |
      | exclude     | Bikini2      |              | 10          |
      | exclude     |              | true         | 10          |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    Then close browser

  Scenario: Order has reached the tax threshold after add PPC, checkout via Paypal #SB_SET_TSB_377
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
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    |
    And order product "Bikini2" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    Then calculate tax amount
      | Type of tax | Product name | Tax for ship | Tax rate(%) |
      | exclude     | Bikini22     |              | 10          |
      | exclude     | Bikini2      |              | 10          |
      | exclude     |              | true         | 10          |
    Then verify tax on store front
      | Type of tax |
      | exclude     |
    Then close browser
