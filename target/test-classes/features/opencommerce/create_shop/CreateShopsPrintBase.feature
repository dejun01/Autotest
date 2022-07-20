@createShopPrintBase
Feature: Create shop print base
# pbase_createshop
  #prod_pbase_createshop
  #staging_pbase_createshop

  Scenario: Create new shop print base and verify data default
    When login to shopbase
    Then verify message "Please enter a store name" if store name is empty
    And create a shop with name "@shop-printbase-@"
    Given Input information merchant
      | Store country | Your personal location | Phone number | Business                   | Value prod                                                                          | Value stag                                                                          |
      | Vietnam       | Vietnam                | 0984533888   | https://www.google.com.vn/ | I have experience in selling on other eCommerce platforms (Shopify, Woocommerce...) | I have experience in selling on other eCommerce platforms (Shopify, Woocommerce...) |
    And user navigate to "Online Store>Pages" screen
    Then Verify data default Pages
      | Title                    |
      | About Us                 |
      | Contact us               |
      | FAQs                     |
      | Order Tracking           |
      | Product Details & Sizing |
