Feature: Data Layer 1Page checkout

#  staging_sbase_datalayer1page

  Scenario: Verify DataLayer Tracking Event #SB_SF_TKE_42
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
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And verify thank you page
    And Verify tracking event with type is "dataLayer" and event is "purchase"
      | coupon     | currency | shipping | tax | value |
      | DISCOUNT10 | USD      | 20       |     | 110   |
    And Verify tracking event of item with type is "dataLayer" and event is "purchase"
      | item_brand | item_category  | item_id | item_name   | price | quantity |
      |            | Collection_100 |         | Product_100 | 100   | 1        |