Feature: Inventory detail
  #env = sbase_fulfillment_service


  Scenario: Verify product move of inventory when open inventory detail
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    And click "Quotation created" tab
    And select quotation the first id
    And input ordered quantity for variants
      | Variant>quantity    | Product Name                                                                                                                    |
      | Black/M>5;Black/S>5 | CNYISHE Sexy Sleeveless Mesh Sheer Skinny Bodysuits Rompers Women Jumpsuits Fashion Solid Black Red Bodies Ladies Tops Overalls |
    And Created PO
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then get purchase order
    And Acc page "Inventory"
    And Click on view inventory detail
    And verify variant of inventory
    And click on detail variant and verify information variant in product detail
      | variant   | type | status     |
      | Black / S | IN   | Processing |
      | Black / M | IN   | Processing |
    And close browser


  Scenario: verify search variant for condition: variant and reference
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    And click "Quotation created" tab
    And select quotation the first id
#    And select tab "Stock products" in quotation detail
    And input ordered quantity for variants
      | Variant>quantity    | Product Name                                                                                                                    |
      | Black/M>5;Black/S>5 | CNYISHE Sexy Sleeveless Mesh Sheer Skinny Bodysuits Rompers Women Jumpsuits Fashion Solid Black Red Bodies Ladies Tops Overalls |
    Then verify information of Purchase order after input ordered quantity
      | Variant>unitPrice>subTotal                | Total items | Total baseCost  | Subtotal  |
      | Black/S>$5.00>$25.00;Black/M>$3.00>$15.00 | 10 items    | $8.00 base cost | US $40.00 |
    And Created PO
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then get purchase order
    And Acc page "Inventory"
    And Click on view inventory detail
    And search in product move and verify variant of inventory
      | condition  | DATE | TYPE | REFERENCE   | QUANTITY    | STATUS     |
      | variant    |      |      |             |             |            |
      | subVariant | date | IN   | orderNumber | total items | Processing |
    And close browser







