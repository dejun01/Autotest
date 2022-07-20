Feature: Fulfill with PlusHub with need mapping
  #env = need_mapping_sbff

  Scenario: Verify order after mapping product success can to fulfill
    Given user login to shopbase dashboard
    Then user navigate to "fulfillment/dropship/list" screen by url
    And move to "Need mapping" tab in fulfillment page and get count
#    Given open shop on storefront
#    Then checkout successfully with cart "product_test_need_mapping:S>3;product_test_need_mapping:M>3"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    Given redirect to shopbase dashboard
#    Then user navigate to "fulfillment/dropship/list" screen by url
#    And move to "Need mapping" tab in fulfillment page and verify count
    And Search by order and verify info order
      | TAB          | ORDER   | PRODUCT                   | VARIANT   | WAREHOUSE PRODUCT | WAREHOUSE VARIANT | QUANTITY | SHIPPING | BASE COST PER ITEM | ERROR | TRACKING NUMBER |
      | Need mapping | @Order@ | product_test_need_mapping | S / Black |                   |                   | 3        |          |                    |       |                 |
      | Need mapping | @Order@ | product_test_need_mapping | M / Black |                   |                   | 3        |          |                    |       |                 |
    And Mapping product in tab need mapping
      | Line Item |
      | S         |
    And Mapping product with info
      | Product Name odoo                                                      | Variant>quantity   | Product Name sbase | VariantSB>quantitySB |
      | CNYISHE Elegant Satin Dresses for Women Solid Red Black Midi Dress 202 | Size>S;Color>Black | OrderMapped        | Size>S;Color>Black   |
    And Verify not display order in tab
    And move to "To fulfill" tab in fulfillment page and get count
    And Search by order and verify info order
      | ORDER   | PRODUCT                                                                     | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | product_test_need_mapping - S / Black;product_test_need_mapping - M / Black | CNYISHE Elegant... | 3        | $1.00              |       |
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                                      | Attribute>Value    |
      | CNYISHE Elegant Satin Dresses for Women Solid Red Black Midi Dress 202 | Size>S;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser

  Scenario: Verify order after mapping product success but can not to fulfill
    Given user login to shopbase dashboard
    Then user navigate to "fulfillment/dropship/list" screen by url
    And move to "Cannot Fulfill" tab in fulfillment page and get count
    Given open shop on storefront
    Then checkout successfully via stripe with cart "product_test_need_mapping:M>3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State  | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 99502    | Alaska | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given redirect to shopbase dashboard
    Then user navigate to "fulfillment/dropship/list" screen by url
    And move to "Need mapping" tab in fulfillment page and get count
    And Verify auto filter order created over 6h and turn off
    And Search by order and verify info order
      | ORDER   | PRODUCT   | WAREHOUSE PRODUCT | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | M / Black |                   | 3        |                    |       |
    And Mapping product in tab need mapping
      | Line Item |
      | M         |
    And Mapping product with info
      | Product Name odoo                                                      | Variant>quantity   | Product Name sbase | VariantSB>quantitySB |
      | CNYISHE Elegant Satin Dresses for Women Solid Red Black Midi Dress 202 | Size>S;Color>Black | OrderMapped        | Size>S;Color>Black   |
    And Verify not display order in tab
    And move to "Cannot Fulfill" tab in fulfillment page and verify count
    And Search by order and verify info order
      | ORDER   | PRODUCT | WAREHOUSE PRODUCT | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ |         |                   | 3        |                    |       |
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                                      | Attribute>Value    |
      | CNYISHE Elegant Satin Dresses for Women Solid Red Black Midi Dress 202 | Size>L;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser
