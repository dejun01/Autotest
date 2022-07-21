Feature: Data Layer 3Page checkout

#  staging_sbase_datalayer3page
#  prod_sbase_datalayer3page


  Scenario: Verify DataLayer Tracking Event #SB_SF_TKE_41
    Given open shop on storefront
    When Search product "Product_100"
    When Select product "Product_100"
    Then Verify tracking event of item with type is "dataLayer" and event is "view_item"
      | item_brand | item_category  | item_id | item_name   | item_variant | price |
      | Vendor_100 | Collection_100 |         | Product_100 | Variant1     | 100   |
    When open page "Product 0"
    Then Verify tracking event of item with type is "dataLayer" and event is "view_item"
      | item_brand | item_name | price |
      | vender 0   | Product 0 | 0     |
    When clear tracking event
    And add products "Product_100" to cart
    Then Verify tracking event of item with type is "dataLayer" and event is "add_to_cart"
      | item_brand | item_category  | item_id | item_name   | item_variant | price | quantity |
      | Vendor_100 | Collection_100 |         | Product_100 | Variant1     | 100   | 1        |
    When clear tracking event
    When click to button checkout
    And Verify tracking event of item with type is "dataLayer" and event is "begin_checkout"
      | item_brand | item_category  | item_id | item_name   | item_variant | price | quantity |
      |            | Collection_100 |         | Product_100 | Variant1     | 100   | 1        |
    When Apply discount code "DISCOUNT10"
    When Complete fill customer information
    When clear tracking event
    When click to button "Continue to payment method"
    When Complete select payment method
    And Verify tracking event with type is "dataLayer" and event is "purchase"
      | coupon     | currency | shipping | tax | value |
      | DISCOUNT10 | USD      | 20       |     | 110   |
    And Verify tracking event of item with type is "dataLayer" and event is "purchase"
      | item_brand | item_category  | item_id | item_name   | price | quantity |
      |            | Collection_100 |         | Product_100 | 100   | 1        |