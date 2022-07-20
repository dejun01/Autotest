#checkout_buy_with_paypal

Feature: Buy With Paypal
  Background:
    Given clear all data
    And open shop on storefront

  Scenario: Checkout successfully with "Buy with Paypal" button without PPC #SB_CHE_BPP_3 #SB_CHE_BPP_2
    Given search and select the "Shirt" products
    When user click on Buy with Paypal button and checkout with email "buyer@shopbase.com" and password "123456@a"
    And verify thank you page
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout successfully with "Buy with Paypal" button with PPC #SB_CHE_BPP_4
    Given search and select the "Post Purchase" products
    When user click on Buy with Paypal button and checkout with email "buyer@shopbase.com" and password "123456@a"
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page
#    And get the order information including
#      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
#    And Access to order detail by order ID
#    And verify order detail on dashboard

  Scenario: Checkout successfully with "Buy with Paypal" button with Custom options items
    Given search and select the "Custom options" products
    When user click on Buy with Paypal button and checkout with email "buyer@shopbase.com" and password "123456@a"
    And verify thank you page