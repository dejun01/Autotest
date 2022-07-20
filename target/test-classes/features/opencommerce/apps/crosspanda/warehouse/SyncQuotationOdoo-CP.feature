Feature: SyncQuotationOdoo-CP
#staging_crosspanda_request_product
  Scenario: sync
    And sync quotation from Odoo to crosspanda

  Scenario Outline: Sync quotation Odoo-CP with Non-Based on the total number of all variants
    Given Description: "<Test case>"
    Given login to crosspanda dashboard
    And user navigate to "Request products" screen in CrossPanda
    Then request product in CrossPanda "<KEY>"
      | KEY | QuotationCPId | Email | Message | ProductURL                                         |
      | 1   | QCP-01        |       |         | https://www.aliexpress.com/item/4000178443158.html |
      | 2   | QCP-02        |       |         | https://www.aliexpress.com/item/4000178443158.html |
#    And get quotation number as "<KEY>"
#      | KEY | Quotation |
#      | 1   |           |
#      | 2   |           |
#    And wait 5 second
#    And I should be able to see SO with name "<KEY>"
#      | KEY | Quotation | Email | ProductURL                                         |
#      | 1   |           |       | https://www.aliexpress.com/item/4000178443158.html |
#      | 2   |           |       | https://www.aliexpress.com/item/4000178443158.html |
    And update order line item of quotation "<KEY>"
      | KEY | Quotation | Variant                    | Quantity | New quantity | New price | Validity   | Based on total |
      | 1   |           | Handmade Beauty (XL, Blue) | 1        | 2            | 20        | 2021-05-05 | true           |
      | 2   |           | Handmade Beauty (XL, Blue) | 1        | 5            | 20        | 2021-05-05 | false          |
    And add order line item of quotation "<KEY>"
      | KEY | Quotation | Product name    | Variant               | Quantity | Price |
      | 1   |           | Handmade Beauty | Size: XS,Color: Blue  | 5        | 15    |
      | 1   |           | Handmade Beauty | Size: XS,Color: Blue  | 10       | 12    |
      | 1   |           | Handmade Beauty | Size: XS,Color: Blue  | 15       | 10    |

      | 2   |           | Handmade Beauty | Size: XS,Color: Blue  | 10       | 19    |
      | 2   |           | Handmade Beauty | Size: XS,Color: Blue  | 15       | 17    |
      | 2   |           | Handmade Beauty | Size: XL,Color: Black | 10       | 18    |
      | 2   |           | Handmade Beauty | Size: XL,Color: Black | 15       | 16    |
    And confirm quotation "<KEY>"
      | KEY | Quotation | Partner id |
      | 1   |           | P-01       |
      | 2   |           | P-02       |
    And sync quotation from Odoo to crosspanda
    Given login to crosspanda dashboard
    And user navigate to "Request list" screen in CrossPanda
    And switch to tab "Quote created"
    And close smart bar in CrossPanda
    And verify quotation details "<KEY>"
      | KEY | QuotationCPId | Quotation | Product name    | Minimum price | Expiration date | Shipping fee            | Estimated time | Minimum order quantity | Minimum order value | Variant name | Price by quantity                                         | Quantity | Unit price |
      | 1   | QCP-01        |           | Handmade Beauty | $10.00        | May 05, 2021    | US: $0.00,Non-US: $0.00 | 0 day          | 1 item                 | $0.00               | XL/Blue      | >= 2 Items,20;>= 5 Items,15;>= 10 Items,12;>= 15 Items,10 | 2        | 20         |
      | 1   | QCP-01        |           | Handmade Beauty |               |                 |                         |                |                        |                     | XL/Black     | >= 2 Items,20;>= 5 Items,15;>= 10 Items,12;>= 15 Items,10 | 5        | 15         |
      | 1   | QCP-01        |           | Handmade Beauty |               |                 |                         |                |                        |                     | XS/Blue      |                                                           |          |            |
      | 1   | QCP-01        |           | Handmade Beauty |               |                 |                         |                |                        |                     | XS/Black     |                                                           |          |            |

      | 2   | QCP-02        |           | Handmade Beauty | $16.00        | May 05, 2021    | US: $0.00,Non-US: $0.00 | 0 day          | 1 item                 | $0.00               | XS/Blue      | >= 5 Items,20;>= 10 Items,19;>= 15 Items,17               | 10       | 19         |
      | 2   | QCP-02        |           | Handmade Beauty |               |                 |                         |                |                        |                     | XL/Black     | >= 5 Items,18;>= 10 Items,18;>= 15 Items,16               | 15       | 16         |

    And close browser
#    When add order line item of purchase "<KEY>"
#      | KEY | Purchase order id | Product name            | Variant              | Quantity | Price |
#      | 1   | PO-01             | Spring Women Dress 2020 | Size: S,Color: Black | 10       | 15    |
#      | 1   | PO-01             | Spring Women Dress 2020 | Size: M,Color: Black | 10       | 15    |
#    And confirm purchase order "<KEY>"
#      | KEY | Purchase order id |
#      | 1   | PO-O1             |
#    And assign product to owner as "<KEY>"
#      | KEY | Purchase order id | Owner id | Picking id |
#      | 1   | PO-O1             | P-01     | Pk-01      |
#    And validate purchase order as "<KEY>"
#      | KEY | Purchase order id | Owner id | Picking id |
#      | 1   | PO-O1             | P-01     | Pk-01      |
    Examples:
      | KEY | Test case                                                                 |
      | 1   | Sync quotation Odoo-CP with Non-Based on the total number of all variants |
      | 2   | Sync quotation Odoo-CP with Based on the total number of all variants     |