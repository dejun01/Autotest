Feature: ADC Orders

  Scenario: delete all products
    Given clear all data
    When user login to shopbase dashboard
    And user navigate to "Products" screen
    Given Select all product and delete
      | Product name | Action                   |
      |              | Delete selected products |


  Scenario Outline: SB_ADC_OL_4.2 placing order with mapped item
    #1. Crawl product from Aliexpress then import to ShopBase
    Given user login to shopbase dashboard
    When user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    And user navigate to "Import List" screen
    And delete all products from import list of ADC
    And import product from AliExpress to ADC app
      | Product KEY | AliExpress Product                                    |
      | 1           | https://www.aliexpress.com/item/1005001949799221.html |
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
    And get all information order
    #3. Open ADC app
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
    #7. verify that SB order is refunded and restock line items successfully if ADC order has status as "Awaiting Payment" (SB_ADC_OL_4.7)
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    Then verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
    When switch to the first tab
    Then refresh page
    And expand order "" in list ADC
    Then verify order detail in manage order
      | ADC Order Status |
      | Awaiting Payment |
    Examples:
      | Refunded item  | Restock item | Refund shipping | Reason for refund  | Status   | Net payment |
      | Yonex Racket>1 | true         | @full shipping@ | Damaged in transit | Refunded | $0.00       |



