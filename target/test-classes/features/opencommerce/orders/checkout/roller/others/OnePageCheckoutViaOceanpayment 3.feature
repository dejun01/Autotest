Feature: Roller - One page checkout via OceanPayment

  #sbase_oceanpayment_onepage
  Background:
    Given clear all data

  Scenario: one page checkout via ocean payment successfully #SB_CHE_OCP_1
    Given  open shop on storefront
    When checkout successfully via OceanPayment with cart "Bikini"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And get all information order
    Given user login to shopbase dashboard
    When open order detail dashboard by ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Onge page checkout via Oceanpayment with adding post purchase item #SB_CHE_OCP_2
    Given open shop on storefront
    When checkout successfully via OceanPayment with cart "Post Purchase"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get all information order
    Given user login to shopbase dashboard
    Given open order detail dashboard by ID
    Then verify order detail on dashboard
    And close browser

