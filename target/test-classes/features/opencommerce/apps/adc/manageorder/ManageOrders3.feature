@ManageAdcOrders
Feature: Manage Orders
#env = sbase_adc1

  Scenario: delete all products
    Given clear all data
    When user login to shopbase dashboard
    And user navigate to "Products" screen
    And Delete all products

  Scenario: SB_ADC_OL_4.2 placing order with mapped item 1 #SB_ADC_MO_22 #SB_ADC_MO_5 #SB_ADC_MO_1
    #1. Crawl product from Aliexpress then import to ShopBase
    When user login to shopbase dashboard
    Then user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    And user navigate to "Import List" screen
    And delete all products from import list of ADC
    And import product from AliExpress to ADC app
      | Product KEY | AliExpress Product                                |
      | 1           | https://vi.aliexpress.com/item/4001268363436.html |
    Then select tab "Product" then edit product information in import list
      | New title |
      | Yonex Bag |
    Then select tab "Variants" then edit product information in import list
      | Column | New value       |
      | Price  | Multiply by = 2 |
    And import product from ADC app to Store
      | Product KEY |
      | 1           |
    #2. Place order with the above imported product
    Given open storefront shop on a new tab
    And place order on Shopbase
      | Order KEY | Product KEY |
      | Order 1   | 1           |
#    3. Open ADC app
    Given Switch to the first tab
    When user navigate to "Manage Orders" screen
    And expand order "" in list ADC
    #4. verify order detail in Awaiting Order tab
    And verify order detail in manage order
      | Product Name | Product Cost        | Mapping Status | ADC Order Status |
      | Yonex Bag    | @Ali product price@ | Mapped         | Awaiting Order   |
    When place order from ADC to AliExpress
      | Order KEY |
      | Order 1   |
    #5. Switch to Awaiting Payment tab to verify place order successfully
    And switch to "Awaiting Payment" tab in ADC
    And expand order "" in list ADC
    Then verify order detail in manage order
      | ADC Order Status | AliExpress Order |
      | Awaiting Payment | @Ali order@      |
    Then verify ShopBase order status
      | ShopBase Order Status |
      | Processing            |
    #6. verify that SB order is not canceled if ADC order has status as "Awaiting Payment"
    And open cancel order popup then cancel fully order
    And verify the toast message is displayed "Cannot cancel a paid and fulfilled order"
    And verify financial status of order is "Paid"

  Scenario Outline: SB_ADC_OL_4.2 placing order with mapped item 2 #SB_ADC_MO_26 #SB_ADC_MO_16
    #1. Crawl product from Aliexpress then import to ShopBase
#    When user navigate to screen "/admin/apps/alidropcon/import-list" by API
    Given user login to shopbase dashboard
    When user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    And user navigate to "Import List" screen
    And delete all products from import list of ADC
    And import product from AliExpress to ADC app
      | Product KEY | AliExpress Product                                 |
      | 1           | https://www.aliexpress.com/item/4001298503925.html |
    Then select tab "Product" then edit product information in import list
      | New title    |
      | Victor Shoes |
    Then select tab "Variants" then edit product information in import list
      | Column | New value       |
      | Price  | Multiply by = 2 |
    And import product from ADC app to Store
      | Product KEY |
      | 1           |
    #2. Place order with the above imported product
    Given open storefront shop on a new tab
    And place order on Shopbase
      | Order KEY | Product KEY |
      | Order 1   | 1           |
    And get the order information including
      | order number | total amount | shipping fee |
    #3. Open ADC app
    Given Switch to the first tab
    When user navigate to "Manage Orders" screen
    And expand order "" in list ADC
    When place order from ADC to AliExpress
      | Order KEY |
      | Order 1   |
    #4. Switch to Awaiting Payment tab to verify place order successfully
    And switch to "Awaiting Payment" tab in ADC
    And expand order "" in list ADC
    And click on order name in ADC app should be redirected to ShopBase order
    #5. verify that SB order is refunded and restock line items successfully if ADC order has status as "Awaiting Payment" (SB_ADC_OL_4.7)
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    When switch to the first tab
    Then refresh page
    And expand order "" in list ADC
    Then verify order detail in manage order
      | ADC Order Status |
      | Awaiting Payment |
    Examples:
      | Refunded item  | Restock item | Refund shipping | Reason for refund |
      | Victor Shoes>1 | true         | @full shipping@ | Damaged           |