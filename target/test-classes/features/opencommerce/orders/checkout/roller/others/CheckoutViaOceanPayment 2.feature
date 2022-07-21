Feature: Roller - Checkout via OceanPayment
#  sbase_checkout_oceanpayment
  Background:

    Given clear all data

  Scenario: checkout via ocean payment successfully #SB_CHE_OCP_3
    Given  open shop on storefront
    When checkout successfully via OceanPayment with cart "Bikini>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And get all information order
    Given user login to shopbase dashboard
    When open order detail dashboard by ID
    Then verify order detail on dashboard
    And close browser

#  Scenario: Checkout via Oceanpayment without adding post purchase item #SB_CHE_OCP_4
#    Given open shop on storefront
#    When checkout successfully via OceanPayment with cart "Post Purchase>1"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
#    And get all information order
#    And order product "" with custom option is "" in post purchase offer
#    Given user login to shopbase dashboard
#    Given open order detail dashboard by ID
#    Then verify order detail on dashboard
#    And close browser

  Scenario: Checkout via Oceanpayment with adding post purchase item #SB_CHE_OCP_4
    Given open shop on storefront
    When checkout successfully via OceanPayment with cart "Post Purchase>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get all information order
    Given user login to shopbase dashboard
    Given open order detail dashboard by ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Oceanpayment when cancelling payment
    Given open shop on storefront
    When checkout successfully via OceanPayment with cart "Post Purchase>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV | Cancel |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 | Yes    |
    Then close browser

  Scenario: Checkout via Oceanpayment successfully when cancelling payment for post purchase item
    Given open shop on storefront
    When checkout successfully via OceanPayment with cart "Post Purchase>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And order product "Bikini" with custom option is "" in post purchase offer
    Then cancel payment for post-purchase item then verify checkout detail
    And get all information order
    Given user login to shopbase dashboard
    Given open order detail dashboard by ID
    Then verify order detail on dashboard
    And close browser