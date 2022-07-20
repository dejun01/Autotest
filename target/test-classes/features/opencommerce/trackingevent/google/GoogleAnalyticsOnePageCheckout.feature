@GoogleAnalyticsTrackingEvent
Feature: Google Analytics Tracking Event on one page checkout

#  prod_sbase_tracking_event_one_page_checkout

#  Data test:
  #  data tracking
  #  one page checkout
  #  shipping fee: 20$
  #  payment method: stripe
  #  product: Product_100 (has price 100$), Product 0 (has price 0$)
  #  Collections: Collection_100 (has product: Product_100)
  #  discount code: DISCOUNT10 (has discount 10%)


  Scenario: Verify Google Analytics Tracking Event on one page checkout #SB_SF_TKE_22 #SB_SF_TKE_24 #SB_SF_TKE_26 #SB_SF_TKE_27
    Given open shop on storefront
    When Search product "Product_100"
    Then Verify tracking event with type is "google" and event is "search"
      | search_term |
      | Product_100 |
    When Select product "Product_100"
    Then Verify tracking event of item with type is "google" and event is "view_item"
      | price |
      | 100   |
    When open page "Product 0"
    Then Verify tracking event of item with type is "google" and event is "view_item"
      | price |
      | 0     |
    When clear tracking event
    And add products "Product_100" to cart
    Then Verify tracking event of item with type is "google" and event is "add_to_cart"
      | price |
      | 100   |
    When clear tracking event
    When click to button checkout
    Then Verify tracking event with type is "google" and event is "begin_checkout"
      | currency |
      | USD      |
    And Verify tracking event of item with type is "google" and event is "begin_checkout"
      | item_brand | item_category  | item_id | item_name   | price | quantity |
      |            | Collection_100 |         | Product_100 | 100   | 1        |
    When clear tracking event
    When Apply discount code "DISCOUNT10"
    Then Verify tracking event with type is "google" and event is "set_checkout_option"
      | checkout_option | checkout_step | value      |
      | use_coupon      | 1             | DISCOUNT10 |
    When clear tracking event
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And verify thank you page
    Then Verify tracking event with type is "google" and event is "add_shipping_info"
      | shop_id | shop_name |
      |         |           |
    And Verify tracking event of item with type is "google" and event is "add_shipping_info"
      | currency | discount | item_brand | item_category  | item_id | item_name   | item_variant | price | quantity |
      | USD      | 10       |            | Collection_100 |         | Product_100 | Variant1     | 100   | 1        |
    Then Verify tracking event with type is "google" and event is "add_payment_info"
      | shop_id | shop_name |
      |         |           |
    And Verify tracking event of item with type is "google" and event is "add_payment_info"
      | currency | discount | item_brand | item_category  | item_id | item_name   | item_variant | price | quantity |
      | USD      | 10       |            | Collection_100 |         | Product_100 | Variant1     | 100   | 1        |
    And Verify tracking event with type is "google" and event is "purchase"
      | coupon     | currency | shipping | value |
      | DISCOUNT10 | USD      | 20       | 110   |
    And Verify tracking event of item with type is "google" and event is "purchase"
      | currency | discount | item_brand | item_category  | item_id | item_name   | item_variant | price | quantity |
      | USD      | 10       |            | Collection_100 |         | Product_100 | Variant1     | 100   | 1        |
    And Verify tracking event with type is "google" and event is "conversion"
      | currency | send_to                           | value |
      | USD      | AW-729676480/SEc3CNe49esBEMD199sC | 90    |
    And Verify tracking event with type is "google" and event is ""
      | page_path           |
      | /checkout/thank_you |