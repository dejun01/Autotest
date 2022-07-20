Feature: Roller - One Page Checkout with Paypal smart button
  #env = sbase_onepage_checkout_paypal_smart_button
  Background:
    Given clear all data

  Scenario: Checkout by Paypal smart button - paypal #SB_CHE_PP_18
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Paypal smart button |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | express   | yes                 |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

#    Cmt lại do chưa handle được case này trên server
#  Scenario: Checkout by Paypal smart button - debit or credit card #SB_CHE_PP_19
#    And open shop on storefront
#    And add products "Bikini" to cart then navigate to checkout page
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method ""
#    And input payment information and complete order
#      | Payment method | Payment gateway | Card number      | Expired Date | CVV | Card type | Paypal smart button |
#      | Paypal         | Paypal          | 4170439564464028 | 02/22        | 923 | credit    | yes                 |
#    And get the order information including
#      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
#    Given user login to shopbase dashboard
#    And user navigate to "Orders" screen
#    Given click on order name in unfulfilled orders list
#    Then verify order detail on dashboard
#    And close browser

  Scenario: Checkout by Paypal smart button - paypal with postpurchase item #SB_CHE_PP_20
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Paypal smart button |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | express   | yes                 |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

#    Cmt lại do chưa handle được case này trên server
#  Scenario: Checkout by Paypal smart button - debit or credit card without post purchase item #SB_CHE_PP_21
#    And open shop on storefront
#    And add products "Post Purchase" to cart then navigate to checkout page
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method ""
#    And input payment information and complete order
#      | Payment method | Payment gateway | Card number      | Expired Date | CVV | Card type | Paypal smart button |
#      | Paypal         | Paypal          | 4170439564464028 | 02/22        | 923 | credit    | yes                 |
#    And order product "" with custom option is "" in post purchase offer
#    And get the order information including
#      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
#    Given user login to shopbase dashboard
#    And user navigate to "Orders" screen
#    Given click on order name in unfulfilled orders list
#    Then verify order detail on dashboard
#    And close browser
