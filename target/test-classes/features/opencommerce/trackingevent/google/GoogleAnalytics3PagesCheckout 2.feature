@GoogleAnalyticsTrackingEvent
Feature: Google Analytics Tracking Event 3 Page checkout

#  prod_sbase_tracking_event_3_pages_checkout

#  Data test:
  #  data tracking
  #  three page checkout
  #  shipping fee: 20$
  #  payment method: stripe
  #  product: Product_100 (has price 100$), Product 0 (has price 0$)
  #  Collections: Collection_100 (has product: Product_100)
  #  discount code: DISCOUNT10 (has discount 10%)


  Scenario: Verify Google Analytics Tracking Event #SB_SF_TKE_21 #SB_SF_TKE_23 #SB_SF_TKE_28 #SB_SF_TKE_29
    Given open shop on storefront
    When Search product "Product_100"
    Then Verify tracking event with type is "google" and event is "search"
      | event_category | event_label |
      | engagement     | Product_100 |
    When Select product "Product_100"
    Then Verify tracking event of item with type is "google" and event is "view_item"
      | brand      | category       | id | name        | price |
      | Vendor_100 | Collection_100 |    | Product_100 | 100   |
    And open page "Product 0"
    Then Verify tracking event of item with type is "google" and event is "view_item"
      | brand    | name      | price |
      | vendor 0 | Product 0 | 0     |
    When clear tracking event
    And add products "Product_100" to cart
    Then Verify tracking event of item with type is "google" and event is "add_to_cart"
      | brand      | category       | id | name        | price | quantity |
      | Vendor_100 | Collection_100 |    | Product_100 | 100   | 1        |
    When clear tracking event
    When click to button checkout
    Then Verify tracking event with type is "google" and event is "begin_checkout"
      | currency | value  |
      | USD      | 100.00 |
    And Verify tracking event of item with type is "google" and event is "begin_checkout"
      | brand | category       | id | name        | price | quantity |
      |       | Collection_100 |    | Product_100 | 100   | 1        |
    When clear tracking event
    When Apply discount code "DISCOUNT10"
    Then Verify tracking event with type is "google" and event is "set_checkout_option"
      | checkout_option | checkout_step | value      |
      | use_coupon      | 1             | DISCOUNT10 |
    When Complete fill customer information
    Then Verify tracking event with type is "google" and event is "set_checkout_option"
      | checkout_step |
      | 1             |
    Then Verify tracking event with type is "google" and event is "checkout_progress"
      | checkout_step | coupon     | currency | value |
      | 2             | DISCOUNT10 | USD      | 90    |
    And Verify tracking event of item with type is "google" and event is "checkout_progress"
      | brand | category       | id | name        | price | quantity |
      |       | Collection_100 |    | Product_100 | 100   | 1        |
    When clear tracking event
    When click to button "Continue to payment method"
    Then Verify tracking event with type is "google" and event is "set_checkout_option"
      | checkout_option | checkout_step | value                  |
      | shipping_method | 2             | International Shipping |
    Then Verify tracking event with type is "google" and event is "checkout_progress"
      | checkout_step | coupon     | currency | value |
      | 3             | DISCOUNT10 | USD      | 90    |
    And Verify tracking event of item with type is "google" and event is "checkout_progress"
      | brand | category       | id | name        | price | quantity |
      |       | Collection_100 |    | Product_100 | 100   | 1        |
    When Complete select payment method
    Then Verify tracking event with type is "google" and event is "set_checkout_option"
      | checkout_option | checkout_step | value  |
      | payment_method  | 3             | stripe |
    And Verify tracking event with type is "google" and event is "purchase"
      | coupon     | currency | shipping | value |
      | DISCOUNT10 | USD      | 20       | 90    |
    And Verify tracking event of item with type is "google" and event is "purchase"
      | brand | category       | id | name        | price | quantity |
      |       | Collection_100 |    | Product_100 | 100   | 1        |
    And Verify tracking event with type is "google" and event is "conversion"
      | send_to                           | value | currency |
      | AW-729676480/SEc3CNe49esBEMD199sC | 90    | USD      |
    And Verify tracking event with type is "google" and event is ""
      | page_path           |
      | /checkout/thank_you |
