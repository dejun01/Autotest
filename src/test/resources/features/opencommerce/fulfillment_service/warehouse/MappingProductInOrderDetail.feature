Feature: Mapping product in order detail
  #env = sbase_fulfillment_service

  Scenario: Verify order after mapping product success
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T-shirt:M>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Mapping product in order detail
    And Mapping product with info
      | Product Name odoo                                | Variant>quantity   | Product Name sbase | VariantSB>quantitySB |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black | T-shirt            | Size>S;Color>White   |
    And Fulfill order
    And get info DO out on odoo and verify
    Given redirect to shopbase dashboard
    And user navigate to "Fulfillment" screen
    And move to "Dropship" screen
    And move to "Warehouse" screen
    And Acc page "Inventory"
    And Choose product odoo mapping has info
      | Name Product oDoo                                | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser


  Scenario: Verify order after change product mapping success
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T-shirt:M>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Mapping product in order detail
    And Mapping product with info
      | Product Name odoo                                | Variant>quantity   | Product Name sbase | VariantSB>quantitySB |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black | T-shirt            | Size>M;Color>White   |
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Edit mapping product
      | Name product on sBase | Attribute>Value    |
      | T-shirt               | Color>White;Size>M |
    And user navigate to "Fulfillment" screen
    And move to "Dropship" screen
    And move to "Warehouse" screen
    And Acc page "Inventory"
    And Choose product odoo mapping has info
      | Name Product oDoo                                | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser


  Scenario: Verify with option of product SBase bigger option of product odoo not mapping
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T-shirt:L>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Mapping product in order detail
    And Search product mapping "Grwibeou HDMI to VGA Adapter Cable Male To"
    Then Verify product mapping
      | Error                                                                                                                      |
      | *The number of store product options cannot be more than the number of warehouse product options. Please select other one. |
    And close browser

  Scenario: Veridy mapping success when product Sbase no option
    Given open shop on storefront
    Then checkout successfully via stripe with cart "not_variant>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Mapping product in order detail
    And Mapping product with info
      | Product Name odoo                                | Variant>quantity   | Product Name sbase | VariantSB>quantitySB |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>S;Color>Black | not_variant        |                      |
    And user navigate to "Fulfillment" screen
    And move to "Dropship" screen
    And move to "Warehouse" screen
    And Acc page "Inventory"
    And Choose product odoo mapping has info
      | Name Product oDoo                                | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser


  Scenario: Verify display list product odoo
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T-shirt:M>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Fulfillment" screen
    And user navigate to "Dropship" screen
    And click on "Quotation created" tab
    And get list product odoo in quotation created
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Mapping product in order detail
    Then verify display list product odoo
    And close browser

