Feature: Check export order
  #sb_personalize_export_order
#  Scenario: Delete product before execute
#    When clear all data

  Scenario: Export order with All orders of Orders with Print files #SB_PRB_test_32
    Given user login to shopbase dashboard by API
    And user navigate to "Orders>All orders" screen
    And click to button "Export order"
    And click to radio "All orders"
    And click to radio "Orders with Print files"
    Then click to button "Export to file"
    And verify that the content of file downloaded is matched with product information from dashboard
      |Shop Domain  |	Name |	Email | Financial Status |	Paid At |	Fulfillment Status |	Fulfilled At |	Buyer Accepts Marketing |	Currency |	Subtotal Price |	Shipping |	Tipping |	Total Tax |	Total Price |	Discount Codes |	Discount Amount |	Shipping Method |	Created At |	Lineitem Quantity|	Lineitem Variant Id|	Lineitem Name|	Lineitem Price|	Lineitem Compare At Price|	Lineitem Sku|	Lineitem Requires Shipping|	Lineitem Taxable|	Lineitem Fulfillment Status|	Lineitem Fulfillable Quantity|	Billing Name|	Billing Street|	Billing Address1|	Billing Address2|	Billing Company|	Billing City|	Billing Zip|	Billing Province|	Billing Country|	Billing Phone|	Shipping Name|	Shipping Street|	Shipping Address1|	Shipping Address2|	Shipping Company|	Shipping City|	Shipping Zip|	Shipping Province|	Shipping Country|	Shipping Phone|	Notes|	Note Attributes|	Cancelled At|	Payment Method|	Payment Reference|	Refund Amount|	Vendor|	Id|	Tags|	Risk Level|	Source|	Lineitem Discount|	Phone|	Source Identifier|	Referring Site|	Landing Site|	Utm Source|	Utm Medium|	Utm Campaign|	Lineitem Profit|	Open Dispute Date|	Dispute Reason |	Dispute Due Date|	Dispute Status|	Lineitem Print File|
    And quit browser


  Scenario: Export order with Current search of Orders with Print files #SB_PRB_test_33
    Given user login to shopbase dashboard by API
    And user navigate to "Orders>All orders" screen
    And click to button "Export order"
    And click to radio "Current search"
    And click to radio "Orders with Print files"
    Then click to button "Export to file"
    And verify that the content of file downloaded is matched with product information from dashboard
      |Shop Domain  |	Name |	Email | Financial Status |	Paid At |	Fulfillment Status |	Fulfilled At |	Buyer Accepts Marketing |	Currency |	Subtotal Price |	Shipping |	Tipping |	Total Tax |	Total Price |	Discount Codes |	Discount Amount |	Shipping Method |	Created At |	Lineitem Quantity|	Lineitem Variant Id|	Lineitem Name|	Lineitem Price|	Lineitem Compare At Price|	Lineitem Sku|	Lineitem Requires Shipping|	Lineitem Taxable|	Lineitem Fulfillment Status|	Lineitem Fulfillable Quantity|	Billing Name|	Billing Street|	Billing Address1|	Billing Address2|	Billing Company|	Billing City|	Billing Zip|	Billing Province|	Billing Country|	Billing Phone|	Shipping Name|	Shipping Street|	Shipping Address1|	Shipping Address2|	Shipping Company|	Shipping City|	Shipping Zip|	Shipping Province|	Shipping Country|	Shipping Phone|	Notes|	Note Attributes|	Cancelled At|	Payment Method|	Payment Reference|	Refund Amount|	Vendor|	Id|	Tags|	Risk Level|	Source|	Lineitem Discount|	Phone|	Source Identifier|	Referring Site|	Landing Site|	Utm Source|	Utm Medium|	Utm Campaign|	Lineitem Profit|	Open Dispute Date|	Dispute Reason |	Dispute Due Date|	Dispute Status|	Lineitem Print File|
    And quit browser


  Scenario: Export order with Current page of Orders with Print files #SB_PRB_test_30
    Given user login to shopbase dashboard by API
    And user navigate to "Orders>All orders" screen
    And click to button "Export order"
    And click to radio "Current page"
    And click to radio "Orders with Print files"
    Then click to button "Export to file"
    And verify that the content of file downloaded is matched with product information from dashboard
      |Shop Domain  |	Name |	Email | Financial Status |	Paid At |	Fulfillment Status |	Fulfilled At |	Buyer Accepts Marketing |	Currency |	Subtotal Price |	Shipping |	Tipping |	Total Tax |	Total Price |	Discount Codes |	Discount Amount |	Shipping Method |	Created At |	Lineitem Quantity|	Lineitem Variant Id|	Lineitem Name|	Lineitem Price|	Lineitem Compare At Price|	Lineitem Sku|	Lineitem Requires Shipping|	Lineitem Taxable|	Lineitem Fulfillment Status|	Lineitem Fulfillable Quantity|	Billing Name|	Billing Street|	Billing Address1|	Billing Address2|	Billing Company|	Billing City|	Billing Zip|	Billing Province|	Billing Country|	Billing Phone|	Shipping Name|	Shipping Street|	Shipping Address1|	Shipping Address2|	Shipping Company|	Shipping City|	Shipping Zip|	Shipping Province|	Shipping Country|	Shipping Phone|	Notes|	Note Attributes|	Cancelled At|	Payment Method|	Payment Reference|	Refund Amount|	Vendor|	Id|	Tags|	Risk Level|	Source|	Lineitem Discount|	Phone|	Source Identifier|	Referring Site|	Landing Site|	Utm Source|	Utm Medium|	Utm Campaign|	Lineitem Profit|	Open Dispute Date|	Dispute Reason |	Dispute Due Date|	Dispute Status|	Lineitem Print File|
    And quit browser

