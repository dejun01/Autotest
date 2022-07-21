  #sbase_auto_generate_sku_product
  Feature: Verify auto generate varriant sku

    Background: Login dashboard
      Given user login to shopbase dashboard by API
      And Delete all products by API
      And user navigate to "Online Store>Preferences" screen


    @dashboardProduct
    Scenario: Verify auto generate SKU when choose Auto number at the end if detect duplicate SKU #SB_PRO_PV_231 #SB_PRO_PV_233 #SB_PRO_PV_234 #SB_PRO_PV_235
      When Setting automatically generate SKU
        | Enable | Template                                                                                    | Setting                                                                                       |
        | true   | {{Product title}}-{{Product type}}-{{Product vendor}}-{{Option value 1}}-{{Option value 2}} | Auto number>>true--Remove spaces>>false--Only use maximum>>false--Use the first letter>>false |
      And user navigate to "Products>All products" screen
      Given Add a new product with data
        | Title        | Product type | Vendor | Variant                                |
        | Product test | Shirt        | Uniqlo | Size:S--Style:Ladies--Color:Red, Green |
      Then Verify all product variants on storefront
        | Variant Label | Variant | SKU                                    |
        | Color: Red    | Red     | Product test-Shirt-Uniqlo-S-Ladies     |
        | Color: Green  | Green   | Product test-Shirt-Uniqlo-S-Ladies-001 |
      And Switch to the first tab
      And Information of "2" created variants display correctly on product variant detail page
        | Option           | SKU                                    |
        | S; Ladies; Red   | Product test-Shirt-Uniqlo-S-Ladies     |
        | S; Ladies; Green | Product test-Shirt-Uniqlo-S-Ladies-001 |


    Scenario: Verify auto generate SKU when choose Remove spaces in variable value if any #SB_PRO_PV_238 #SB_PRO_PV_240 SB_PRO_PV_241 #SB_PRO_PV_243
      When Setting automatically generate SKU
        | Enable | Template                                                                                                       | Setting                                                                                       |
        | true   | {{Product title}}-{{Product type}}-{{Product vendor}}-{{Option value 1}}-{{Option value 2}}-{{Option value 3}} | Auto number>>false--Remove spaces>>true--Only use maximum>>false--Use the first letter>>false |
      And user navigate to "Products>All products" screen
      Given Add a new product with data
        | Title        | Product type | Vendor   | Variant                                |
        | Product Test | Shirt        | Uniqlo 1 | Size:S--Style:Ladies--Color:Red, Green |
      Then Verify all product variants on storefront
        | Variant Label | Variant | SKU                                      |
        | Color: Red    | Red     | ProductTest-Shirt-Uniqlo1-S-Ladies-Red   |
        | Color: Green  | Green   | ProductTest-Shirt-Uniqlo1-S-Ladies-Green |
      And Switch to the first tab
      And Information of "2" created variants display correctly on product variant detail page
        | Option           | SKU                                      |
        | S; Ladies; Red   | ProductTest-Shirt-Uniqlo1-S-Ladies-Red   |
        | S; Ladies; Green | ProductTest-Shirt-Uniqlo1-S-Ladies-Green |


    Scenario: Verify auto generate SKU when choose Only use maximum first 10 characters of each variable #SB_PRO_PV_237
      When Setting automatically generate SKU
        | Enable | Template                                                                                                       | Setting                                                                                       |
        | true   | {{Product title}}-{{Product type}}-{{Product vendor}}-{{Option value 1}}-{{Option value 2}}-{{Option value 3}} | Auto number>>false--Remove spaces>>false--Only use maximum>>true--Use the first letter>>false |
      And user navigate to "Products>All products" screen
      Given Add a new product with data
        | Title        | Product type | Vendor        | Variant                                |
        | Product Test | Shirt 123456 | Uniqlo 987654 | Size:S--Style:Ladies--Color:Red, Green |
      Then Verify all product variants on storefront
        | Variant Label | Variant | SKU                                             |
        | Color: Red    | Red     | Product Te-Shirt 1234-Uniqlo 987-S-Ladies-Red   |
        | Color: Green  | Green   | Product Te-Shirt 1234-Uniqlo 987-S-Ladies-Green |
      And Switch to the first tab
      And Information of "2" created variants display correctly on product variant detail page
        | Option           | SKU                                             |
        | S; Ladies; Red   | Product Te-Shirt 1234-Uniqlo 987-S-Ladies-Red   |
        | S; Ladies; Green | Product Te-Shirt 1234-Uniqlo 987-S-Ladies-Green |


    Scenario: Verify auto generate SKU when choose Use the first letter for each word, except Created time #SB_PRO_PV_236
      When Setting automatically generate SKU
        | Enable | Template                                                                                                       | Setting                                                                                       |
        | true   | {{Product title}}-{{Product type}}-{{Product vendor}}-{{Option value 1}}-{{Option value 2}}-{{Option value 3}} | Auto number>>false--Remove spaces>>false--Only use maximum>>false--Use the first letter>>true |
      And user navigate to "Products>All products" screen
      Given Add a new product with data
        | Title        | Product type | Vendor | Variant                                |
        | Product Test | Shirt        | Uniqlo | Size:S--Style:Ladies--Color:Red, Green |
      Then Verify all product variants on storefront
        | Variant Label | Variant | SKU          |
        | Color: Red    | Red     | PT-S-U-S-L-R |
        | Color: Green  | Green   | PT-S-U-S-L-G |
      And Switch to the first tab
      And Information of "2" created variants display correctly on product variant detail page
        | Option           | SKU          |
        | S; Ladies; Red   | PT-S-U-S-L-R |
        | S; Ladies; Green | PT-S-U-S-L-G |


    Scenario: Verify auto generate SKU when Uncheck setting #SB_PRO_PV_241 #SB_PRO_PV_243
      When Setting automatically generate SKU
        | Enable | Template                                                                                                       | Setting                                                                                        |
        | true   | {{Product title}}-{{Product type}}-{{Product vendor}}-{{Option value 1}}-{{Option value 2}}-{{Option value 3}} | Auto number>>false--Remove spaces>>false--Only use maximum>>false--Use the first letter>>false |
      And user navigate to "Products>All products" screen
      Given Add a new product with data
        | Title        | Product type | Vendor | Variant                                |
        | Product Test | Shirt        | Uniqlo | Size:S--Style:Ladies--Color:Red, Green |
      Then Verify all product variants on storefront
        | Variant Label | Variant | SKU                                      |
        | Color: Red    | Red     | Product Test-Shirt-Uniqlo-S-Ladies-Red   |
        | Color: Green  | Green   | Product Test-Shirt-Uniqlo-S-Ladies-Green |
      And Switch to the first tab
      And Information of "2" created variants display correctly on product variant detail page
        | Option           | SKU                                      |
        | S; Ladies; Red   | Product Test-Shirt-Uniqlo-S-Ladies-Red   |
        | S; Ladies; Green | Product Test-Shirt-Uniqlo-S-Ladies-Green |