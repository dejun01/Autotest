Feature: Mapping product-variant
#env = sbase_fulfillment_service

  Scenario: Verify data product of order after mapping success
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Color>Black;Size>S |
    And Select store mapping
    And Mapping product
    And Mapping product in order have info
      | Name product on sBase   | Attribute>Value     |
      | MC Vintage Elvish Dress | Size>XL;Color>White |
    And user navigate to "Warehouse" screen
    And Acc page "Inventory"
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Color>Black;Size>S |
    And Select store mapping
    And  Verify data product mapping success
      | data product Sbase  | data product oDoo  |
      | Size>XL;Color>White | Color>Black;Size>S |
    And close browser

  Scenario: Verify mapping product fail if attribute in Odoo less attribute in sBase
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Color>Black;Size>S |
    And Select store mapping
    And Mapping product
    And Mapping products in order
      | Name product on sBase | Attribute>Value |
      | over_variant          |                 |
    Then Display text error "*The number of store product options cannot be more than the number of warehouse product options. Please select other one."
    And close browser

  Scenario: Verify info variant mapping after edit success and change location option value
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Size>M;Color>Black |
    And Select store mapping
    And Edit product mapping
      | Name product on sBase   | Attribute>Value      |
      | MC Vintage Elvish Dress | Size>XXL;Color>White |
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Size>M;Color>Black |
    And Select store mapping
    Then Verify data product mapping success
      | data product Sbase   | data product oDoo  |
      | Size>XXL;Color>White | Size>M;Color>Black |
    And close browser


  Scenario: Verify when remove product in store
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Size>S;Color>Black |
    And Select store mapping
    And get all product store before remove
    And Removed product mapping
    Then Verify not display product removed
    And close browser

  Scenario: Verify mapping success when mapping product sBase not variant
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Color>Black;Size>S |
    And Select store mapping
    And Mapping product
    And Mapping product in order have info
      | Name product on sBase | Attribute>Value |
      | Not_Variant           |                 |
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Choose product odoo mapping has info
      | Name Product oDoo                                            | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece Skirts Sets | Size>S;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser
