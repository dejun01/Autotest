@FacebookPixelTrackingEvent
Feature: Facebook Pixel Tracking Event 3 page checkout

#  prod_sbase_tracking_event_3_pages_checkout

   #  Data test:
  #  data tracking
  #  three page checkout
  #  shipping fee: 20$
  #  payment method: stripe
  #  product: Product_100 (has price 100$), Product 0 (has price 0$)
  #  Collections: Collection_100 (has product: Product_100)
  #  discount code: DISCOUNT10 (has discount 10%)



  Scenario: Verify Facebook Pixel Tracking Event #SB_SF_TKE_30 #SB_SF_TKE_32
    Given open shop on storefront
    When Search product "Product_100"
    Then Verify tracking event with type is "fb" and event is "Search"
      | search_string |
      | Product_100   |
    When Select product "Product_100"
    Then Verify tracking event with type is "fb" and event is "ViewContent"
      | content_brand | content_category | content_ids | content_name | content_type  | currency | value |
      | Vendor_100    | Collection_100   |             | Product_100  | product_group | USD      | 100   |
    When clear tracking event
    And add products "Product_100" to cart
    Then Verify tracking event with type is "fb" and event is "AddToCart"
      | content_brand | content_category | content_ids | content_name | content_type  | currency | value |
      | Vendor_100    | Collection_100   |             | Product_100  | product_group | USD      | 100   |
    And clear tracking event
    And click icon cart in header
    Then Verify tracking event with type is "fb" and event is "CartDrawerIconClicked"
      | shop_id | shop_name |
      |         |           |
    And clear tracking event
    And close mini cart
    When clear tracking event
    When click to button checkout
    Then Verify tracking event with type is "fb" and event is "CheckoutButtonClicked"
      | content_brand | content_category | content_ids | content_type  | currency | num_items | value |
      | []            |                  |             | product_group | USD      | 1         | 100   |
    Then Verify tracking event with type is "fb" and event is "InitiateCheckout"
      | content_brand | content_category | content_ids | content_type  | currency | num_items | value |
      | []            |                  |             | product_group | USD      | 1         | 100   |
    When clear tracking event
    When Apply discount code "DISCOUNT10"
    Then Verify tracking event with type is "fb" and event is "UseCouponCode"
      | coupon     |
      | DISCOUNT10 |
    When Complete fill customer information
    Then Verify tracking event with type is "fb" and event is "AddShippingInfo"
      | shop_id | shop_name |
      |         |           |
    When clear tracking event
    When click to button "Continue to payment method"
    Then Verify tracking event with type is "fb" and event is "SelectShippingMethod"
      | method                 |
      | International Shipping |
    When Complete select payment method
    Then Verify tracking event with type is "fb" and event is "AddPaymentInfo"
      | type        |
      | credit_card |
    And Verify tracking event with type is "fb" and event is "Purchase"
      | content_brand | content_category | content_ids | content_type  | currency | num_items | value |
      | []            |                  |             | product_group | USD      | 1         | 90    |