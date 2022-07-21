@dashboardSDK
Feature: SDK Sync Shopify
# sbase_sdk_sync_order

  Background:
    Given user login to shopbase dashboard

  Scenario: Shopbase dashboard connect to Shopify
    When user navigate to "Settings" screen
    And Click to "Sales channel" section at Settings screen
    And Click to sale channel "Shopify"
    And verify show button connect sale channel
    And connect to Shopify shop
    And connect to another Shopify shop
    Then verify connect Sale channel information
      | Index | Shop name | Status |
      | 1     | @shop2@   | Active |
      | 2     | @shop1@   | Active |
    And disconnect account on list
      | Index |
      | 1     |
    Then verify connect Sale channel information
      | Index | Shop name | Status |
      | 1     | @shop1@   | Active |
    And quit browser

  Scenario: Sync order from Shopify
    Given open product "Sync0906" on Shopify SF on new tab
    And checkout product Shopify without verify
    And get all information order Shopify
#    Given login to Shopify dashboard on new tab
#    And navigate to store Shopify dashboard
#    And navigate to "Orders" on Shopify side bar menu
#    And create order in shopify with
#      | Product  | Variant | Customer |
#      | Sync0906 | L       | Tho Tran |
#    And get all information order Shopify
    Then switch to the first tab
    And user navigate to "Orders" screen
    And click to order detail sync from Shopify
    And get order detail information on dashboard
    And verify order detail sync from Sales channel
    When user navigate to "Settings" screen
    And Click to "Sales channel" section at Settings screen
    And Click to sale channel "Shopify"
    And disconnect account on list
      | Index |
      | 1     |
    And verify show button connect sale channel
    And quit browser

  Scenario: Sync product from Shopify
    Given user navigate to "Products>All products" screen
    Then Verify data of product on product list screen
      | Title    | Type | Vendor | Status      |
      | Sync0906 |      |        | Unavailable |
    When Search product "Sync0906" on All product screen
    And Open detail product of product "Sync0906"
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU | Inventory |
      | L       | 15.5  |     | N/A       |
      | M       | 15.5  |     | N/A       |
      | S       | 15.5  |     | N/A       |
    Given user navigate to "Products>All products" screen
    And delete product name = "Sync0906"
    And quit browser








