Feature: Import - export product by CSV
#env = import_export_product_plusbase

  Scenario: Export less than 50 product #SB_PLB_IEP_1
    Given  user login to plusbase dashboard
    And user navigate to "Products" screen
    When  search product PlBase "product"
    And  select checkbox all product inlist
    Then user export product with condition
      | Export            | Export as                                             | Message              |
      | Selected products | CSV for Excel, Numbers, or other spreadsheet programs | Exported 33 products |
    And verify that the content of file downloaded is matched with product information from dashboard
      | Product Id | Variant Id | Handle | Title | Body (Html) | Type | Published | Option Fulfill Value | Custom Option | Option1 Name | Option1 Value | Option2 Name | Option2 Value | Option3 Name | Option3 Value | Variant Sku | Variant Grams | Variant Inventory Tracker | Variant Inventory Qty | Variant Inventory Policy | Variant Fulfillment Service | Variant Price | Variant Compare At Price | Variant Requires Shipping | Variant Taxable | Variant Barcode | Image Src | Image Position | Image Alt Text | Gift Card | Google Shopping / Mpn | Google Shopping / Age Group | Google Shopping / Gender | Google Shopping / Google Product Category | Seo Title | Seo Description | Google Shopping / Adwords Grouping | Google Shopping / Adwords Labels | Google Shopping / Condition | Google Shopping / Custom Product | Google Shopping / Custom Label 0 | Google Shopping / Custom Label 1 | Google Shopping / Custom Label 2 | Google Shopping / Custom Label 3 | Google Shopping / Custom Label 4 | Variant Image | Variant Weight Unit | Variant Tax Code | Cost Per Item | Available On Listing Pages | Available On Sitemap Files |
    And quit browser


  Scenario: Export equal 50 product #SB_PLB_IEP_2 #SB_PLB_IEP_4
    Given  user login to plusbase dashboard
    And user navigate to "Products" screen
    When  select checkbox all product inlist
    Then user export product with condition
      | Export       | Export as                                             | Message              |
      | Current page | CSV for Excel, Numbers, or other spreadsheet programs | Exported 50 products |
    And verify that the content of file downloaded is matched with product information from dashboard
      | Product Id | Variant Id | Handle | Title | Body (Html) | Type | Published | Option Fulfill Value | Custom Option | Option1 Name | Option1 Value | Option2 Name | Option2 Value | Option3 Name | Option3 Value | Variant Sku | Variant Grams | Variant Inventory Tracker | Variant Inventory Qty | Variant Inventory Policy | Variant Fulfillment Service | Variant Price | Variant Compare At Price | Variant Requires Shipping | Variant Taxable | Variant Barcode | Image Src | Image Position | Image Alt Text | Gift Card | Google Shopping / Mpn | Google Shopping / Age Group | Google Shopping / Gender | Google Shopping / Google Product Category | Seo Title | Seo Description | Google Shopping / Adwords Grouping | Google Shopping / Adwords Labels | Google Shopping / Condition | Google Shopping / Custom Product | Google Shopping / Custom Label 0 | Google Shopping / Custom Label 1 | Google Shopping / Custom Label 2 | Google Shopping / Custom Label 3 | Google Shopping / Custom Label 4 | Variant Image | Variant Weight Unit | Variant Tax Code | Cost Per Item | Available On Listing Pages | Available On Sitemap Files |
    And quit browser

  Scenario: Import product file csv incase less than 20MB #SB_PLB_IEP_7 #SB_PLB_IEP_19
    Given user login to plusbase dashboard
    And user navigate to "Products" screen
    When Import plusbase product by csv with file name
      | File name                | Message                                                  |
      | import-plusbase-20MB.csv | We're currently importing your products into your store. |
    Then verify import products status is "Completed" with "50" products
    And user verify content of mail plbase import product
      | Subject    | Content                      |
      | Order #plb | Thank you for your purchase! |
    And quit browser

  Scenario: Import product file csv error #SB_PLB_IEP_9 #SB_PLB_IEP_11
    Given user login to plusbase dashboard
    And user navigate to "Products" screen
    Then import plbase product with csv file and verify error massage
      | File name                    | Message                                                                                               |
      | import-plbase-elder-20MB.csv | Please fix the following errors:\nThe supplied file is too large to be imported. (Limited size: 20MB) |
      | import-plbase-title-null.csv | Please fix the following errors:\nLine 2 : Title can't be blank                                       |
    And quit browser


  Scenario: Import csv sample template #SB_PLB_IEP_10
    Given user login to plusbase dashboard
    And user navigate to "Products" screen
    When import sample csv template
    Then verify that the content of file downloaded is matched with product information from dashboard
      | Product Id | Variant Id | Handle | Title | Body (Html) | Type | Published | Option Fulfill Value | Custom Option | Option1 Name | Option1 Value | Option2 Name | Option2 Value | Option3 Name | Option3 Value | Variant Sku | Variant Grams | Variant Inventory Tracker | Variant Inventory Qty | Variant Inventory Policy | Variant Fulfillment Service | Variant Price | Variant Compare At Price | Variant Requires Shipping | Variant Taxable | Variant Barcode | Image Src | Image Position | Image Alt Text | Gift Card | Google Shopping / Mpn | Google Shopping / Age Group | Google Shopping / Gender | Google Shopping / Google Product Category | Seo Title | Seo Description | Google Shopping / Adwords Grouping | Google Shopping / Adwords Labels | Google Shopping / Condition | Google Shopping / Custom Product | Google Shopping / Custom Label 0 | Google Shopping / Custom Label 1 | Google Shopping / Custom Label 2 | Google Shopping / Custom Label 3 | Google Shopping / Custom Label 4 | Variant Image | Variant Weight Unit | Variant Tax Code | Cost Per Item | Available On Listing Pages | Available On Sitemap Files |
    And quit browser