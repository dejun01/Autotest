Feature: Purchase order detail
  #env = sbase_fulfillment_service

  Scenario: Verify purchase order detail after created purchase order
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    Given get item inventory of product inventory
      | Product                            | Variant             |
      | CNYISHE Sexy Sleeveless Mesh Sheer | Black/M>5;Black/S>5 |
    And Acc page "Purchase orders"
    Then get purchase order
    And Click on tab "Find products"
    And click "Quotation created" tab
    And select quotation the first id
    And select tab "Stock products" in quotation detail
    And input ordered quantity for variants
      | Variant>quantity    | Product Name                                                                                                                    |
      | Black/M>5;Black/S>5 | CNYISHE Sexy Sleeveless Mesh Sheer Skinny Bodysuits Rompers Women Jumpsuits Fashion Solid Black Red Bodies Ladies Tops Overalls |
    Then verify information of Purchase order after input ordered quantity
      | Variant>unitPrice>subTotal                | Total items | Total baseCost  | Subtotal  |
      | Black/S>$5.00>$25.00;Black/M>$3.00>$15.00 | 10 items    | $8.00 base cost | US $40.00 |
    And Created PO
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And verify item inventory of product inventory after purchase
      | Product                            | Variant             |
      | CNYISHE Sexy Sleeveless Mesh Sheer | Black/M>5;Black/S>5 |
    And Acc page "Purchase orders"
    Then Search purchase order and Verify info purchase order
      | Condition       | Tab      |
      | PURCHASE NUMBER | Incoming |
    And Click on Purchase Order Number
    Then Verify info page PO detail
      | Variant>unitPrice>subTotalariant                  | Link                                                  |
      | Black / S>5>$5.00>$25.00;Black / M>5>$3.00>$15.00 | https://www.aliexpress.com/item/1005002767560427.html |
    And Click view invoice detail
    Then Verify page invoice detail
      | page           | amount  | type | content        | status |
      | Invoice detail | -$40.00 | OUT  | Purchase Order | Paid   |
    And close browser

  Scenario: Verify purchase order detail after created done DO
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    And Click on tab "Incoming"
    Then Search purchase order and verify info purchase orders
      | Condition | Total items | Subtotal | Expected Date Of Arrival |
      | PO        | 10          | $40.00   | Jun 12, 2021             |
    And Click on Purchase Order Number
    Then Verify info page PO detail
      | Variant>unitPrice>subTotalariant                  | Link                                                  |
      | Black / S>2>$5.00>$10.00;Black / M>5>$3.00>$15.00 | https://vi.aliexpress.com/item/1005002767560427.html |
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    And Click on tab "In Warehouse"
    Then Search purchase order and Verify info purchase order in stock to warehouse
      | Condition | Total items | Subtotal | Expected Date Of Arrival | Actual Date Of Arrival |
      | PO        | 3           | $15.00   | Jun 12, 2021             | Jun 09, 2021           |
    And Click on Purchase Order Number
    Then Verify info page PO detail tab
      | Variant>unitPrice>subTotalariant | Link                                                  |
      | Black / S>3>$5.00>$15.00         | https://www.aliexpress.com/item/1005002767560427.html |
    And Click view invoice detail
    Then Verify page invoice detail
      | page           | amount  | type | content        | status |
      | Invoice detail | -$40.00 | OUT  | Purchase Order | Paid   |
    And close browser

  Scenario: Verify data inventory warehouse after cancel PO #SB_RLSBFF_RLSBFF-Warehouse_17
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then get purchase order
    When user navigate to "fulfillment/dropship/quotations" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID        | Is show |
      | shipping electronic | true    |
    And get count inventory of product warehouse
      | Product                | Variant         | Purchased | Incoming | Available stock | Unfulfilled | Awaiting stock | Processing | Fulfilled | Need to purchase | Quantity | Result |
      | Sexy Dress Korean Chic | Coat / One Size | yes       | yes      |                 |             |                |            |           |                  | 0        | 1      |
    And input ordered quantity for variants
      | Variant>quantity | Product Name           |
      | Coat/One Size>1  | Sexy Dress Korean Chic |
    And Created PO
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then Search purchase order and Verify info purchase order
      | Condition       | Tab      |
      | PURCHASE NUMBER | Incoming |
    And verify DO "in" created in odoo with "1" DO
    And cancel DO Purchase order in Odoo
      | Owner                  | State  |
      | shopbase@beeketing.net | cancel |
    And get count inventory of product warehouse
      | Product                | Variant         | Purchased | Incoming | Available stock | Unfulfilled | Awaiting stock | Processing | Fulfilled | Need to purchase | Quantity | Result |
      | Sexy Dress Korean Chic | Coat / One Size | yes       | yes      |                 |             |                |            |           |                  | -1       | 2      |
    And verify data inventory of product warehouse

