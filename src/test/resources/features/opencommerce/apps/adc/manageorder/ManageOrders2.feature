@ManageAdcOrders
Feature: Manage Orders
#env = sbase_adc1

  Scenario: delete all products
    Given clear all data
    When user login to shopbase dashboard
    And user navigate to "Products" screen
    And Delete all products

  Scenario: Check ADC order is canceled fully when SB order is canceled and ADC order is having status as Awaiting order #SB_ADC_MO_25 #SB_ADC_MO_21 #SB_ADC_MO_20 #SB_ADC_MO_19
    Given user login to shopbase dashboard
    When user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    And user navigate to "Import List" screen
    And delete all products from import list of ADC
    And import product from AliExpress to ADC app
      | Product KEY | AliExpress Product                                    |
      | Product 1   | https://www.aliexpress.com/item/1005001586525625.html |
      | Product 2   | https://www.aliexpress.com/item/1005001972108111.html |
    When click on checkbox of product with KEY then Import to Store
      | Product KEY |
      | Product 1   |
      | Product 2   |
    #2. Place order with the above imported product
    Given open storefront shop on a new tab
    And place order on Shopbase
      | Order KEY | Product KEY             |
      | Order 1   | Product 1>2;Product 2>3 |
    Given Switch to the first tab
    When user navigate to "Manage Orders" screen
    And expand order "Order 1" in list ADC
    And select shipping method
      | Product KEY | Shipping method              |
      | Product 1   | EMS                          |
      | Product 2   | AliExpress Standard Shipping |
    When click on order "Order 1" to redirect ShopBase order detail
    And open cancel order popup then cancel fully order and restock items
    And  Switch to the first tab
    And switch to "Canceled" tab in ADC
    And expand order "Order 1" in list ADC
    Then verify order detail in manage order
      | ADC Order Status |
      | Canceled         |