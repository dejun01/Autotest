@FacebookPixelTrackingEvent
Feature: Facebook Pixel Tracking Event One page checkout

#  prod_sbase_tracking_event_one_page_checkout

  #  Data test:
  #  data tracking
  #  one page checkout
  #  shipping fee: 20$
  #  payment method: stripe
  #  product: Product_100 (has price 100$), Product 0 (has price 0$)
  #  Collections: Collection_100 (has product: Product_100)
  #  discount code: DISCOUNT10 (has discount 10%)



  Scenario: Verify Facebook Pixel Tracking Event #SB_SF_TKE_31 #SB_SF_TKE_33
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
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And verify thank you page
    Then Verify tracking event with type is "fb" and event is "AddShippingInfo"
      | shop_id | shop_name |
      |         |           |
    Then Verify tracking event with type is "fb" and event is "SelectShippingMethod"
      | method                 |
      | International Shipping |
    Then Verify tracking event with type is "fb" and event is "AddPaymentInfo"
      | type        |
      | credit_card |
    And Verify tracking event with type is "fb" and event is "Purchase"
      | content_brand | content_category | content_ids | content_type  | currency | num_items | value |
      | []            |                  |             | product_group | USD      | 1         | 90    |

