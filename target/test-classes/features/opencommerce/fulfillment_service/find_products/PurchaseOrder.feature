Feature: Purchase order
  #env = sbase_fulfillment_service

  Scenario: Verify unit price and subtotal of variants with quotation is not check Baseon
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    And click "Quotation created" tab
    And select quotation the first id
    And input ordered quantity for variants
      | Variant>quantity    | Product Name                                                                                                                    |
      | Black/M>5;Black/S>5 | CNYISHE Sexy Sleeveless Mesh Sheer Skinny Bodysuits Rompers Women Jumpsuits Fashion Solid Black Red Bodies Ladies Tops Overalls |
    Then verify information of Purchase order after input ordered quantity
      | Variant>unitPrice>subTotal                | Total items | Total baseCost  | Subtotal  |
      | Black/M>$3.00>$15.00;Black/S>$5.00>$25.00 | 10 items    | $8.00 base cost | US $40.00 |
    And close browser


  Scenario: Verify unit price and subtotal of variants with quotation is check Baseon
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    And click "Quotation created" tab
    And select quotation the second id
    And input ordered quantity for variants
      | Variant>quantity    | Product Name                                                                                                                     |
      | black/M>2;black/L>3 | 2019 Winter Fleece Sweatershirt Sherpa Fleece Oversized Long Fluffy Autumn Winter Warm Wear Female Hoodies Overcoat |
    And verify information of Purchase order after input ordered quantity
      | Variant>unitPrice>subTotal              | Total items | Total baseCost  | Subtotal  |
      | black/M>$1.00>$2.00;black/L>$1.00>$3.00 | 5 items     | $2.00 base cost | US $5.00 |
    And close browser


  Scenario: Verify PO is created when purchase stock
    Given user login to shopbase dashboard
    And Navigate to Balance page
    And Get balance info
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then get purchase order
    Given user navigate to "fulfillment/dropship" screen by url
    And click "Quotation created" tab
    And select quotation the first id
    And input ordered quantity for variants
      | Variant>quantity    | Product Name                                                                                                                    |
      | Black/M>5;Black/S>5 | CNYISHE Sexy Sleeveless Mesh Sheer Skinny Bodysuits Rompers Women Jumpsuits Fashion Solid Black Red Bodies Ladies Tops Overalls |
    Then verify information of Purchase order after input ordered quantity
      | Variant>unitPrice>subTotal                | Total items | Total baseCost  | Subtotal  |
      | Black/M>$3.00>$15.00;Black/S>$5.00>$25.00 | 10 items    | $8.00 base cost | US $40.00 |
    And Created PO
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then Search purchase order and Verify info purchase order
      | Condition       | Tab      |
      | PURCHASE NUMBER | Incoming |
    And Click on Purchase Order Number
    Then Verify info page PO detail
      | Variant>unitPrice>subTotalariant                  | Link                                                  |
      | Black / M>5>$3.00>$15.00;Black / S>5>$5.00>$25.00 | https://www.aliexpress.com/item/1005002767560427.html |
    And Navigate to Balance page
    And Filter balance history by "Shop Domain" with value "@shop"
    Given Verify balance invoice
      | Index | Type | Shop name         | Content                             | Amount | Status  | Created date | Latest transaction date |
      | 1     | OUT  | au_purchase_order | Purchase order PlusHub | $40.00 | Success | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop              | Content                             | Detail                  | Type | Amount | Created date |
      | 1     | au_purchase_order | Purchase order PlusHub | Paid for Purchase Order | OUT  | $40.00 | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content                                     | Amount | Status | Date  |
      | 1     | OUT  | Purchase product CNYISHE Sexy Sleeveless... | $40.00 | Paid   | @Now@ |
    And close browser

