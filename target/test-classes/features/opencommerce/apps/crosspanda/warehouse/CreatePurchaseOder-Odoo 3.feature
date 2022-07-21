Feature: Create Purchase order - Odoo

  Scenario: Create order
    And update order line item of quotation "<KEY>"
      | KEY | Quotation | Variant                      | Quantity | New quantity | New price | Validity   |
      | 1   |           | Nerazzurri Ladies (S, Black) | 1        | 2            | 20        | 2019-11-24 |
    And add order line item of quotation "<KEY>"
      | KEY | Quotation | Product name      | Variant              | Quantity | Price |
      | 1   |           | Nerazzurri Ladies | Size: S,Color: Black | 10       | 15    |
      | 1   |           | Nerazzurri Ladies | Size: S,Color: Black | 100      | 12    |
      | 1   |           | Nerazzurri Ladies | Size: S,Color: Black | 200      | 10    |
      | 1   |           | Nerazzurri Ladies | Size: S,Color: Red   | 10       | 15    |
