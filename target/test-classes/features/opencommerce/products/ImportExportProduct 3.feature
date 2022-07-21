@dashboardImportExportProduct @dashboard
  #evn: sbase_dashboard_product
Feature: Import Export Product

  Background: Login dashboard
    Given user login to shopbase dashboard by API

  @dashboardProductExportSelectProduct
  Scenario: Check send mail export product when select product #SB_PRO_IMP_65 #SB_PRO_PL_24 #SB_PRO_PL_63
    And user navigate to "Products>All products" screen
    And select all products in the first 2 pages
    Then user export product with condition
      | Export            | Export as                                             | Message                                                                               |
      | Selected products | CSV for Excel, Numbers, or other spreadsheet programs | Exported 100 products successfully. Your export will be delivered by email to @email@ |
    And quit browser


  @dashboardImportProduct
  Scenario: Check import product #SB_PRO_IMP_59 #SB_PRO_IMP_60 #SB_PRO_IMP_61 #SB_PRO_IMP_66 #SB_PRO_IMP_81 #SB_PRO_SP_107 #SB_PRO_SP_108 #SB_PRO_SP_109 #SB_PRO_IMP_95 #SB_PRO_IMP_100 #SB_PRO_IMP_102 #SB_PRO_IPST_6 #SB_PRO_PV_175
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                 | Message                                                  |
      | product-sample-import.csv | We're currently importing your products into your store. |
    When Search product "Example T-Shirt" on All product screen
    And Open detail product of product "Example T-Shirt"
    And Information of created product "Example T-Shirt" display correctly
      | Description                                                             | Product type | Vendor | Tags                 |
      | Made from 100% heavy weight cotton, this is the ultimate basic T-shirt. | Shirts       | Acme   | mens t-shirt example |
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | URL and handle  | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | example-t-shirt | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |
    And Information of "3" created variants display correctly on product variant detail page
      | Product name    | Option set | Option     | Number image | Price | Compare at price | Cost per item | SKU             | Barcode | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Example T-Shirt | Size       | Lithograph | 4            | 25    | 35               | 10            |                 | Lit25   | ShopBase tracks this product's inventory | 1        | Yes               | 3629   | kg          |
      | Example T-Shirt | Size       | Small      | 6            | 19.99 | 24.99            | 10            | example-shirt-s |         | ShopBase tracks this product's inventory | 1        | Yes               | 200    | kg          |
      | Example T-Shirt | Size       | Medium     | 3            | 19.99 | 24.99            | 10            | example-shirt-m |         | ShopBase tracks this product's inventory | 1        | Yes               | 200    | kg          |
    Then Verify this product "Example T-Shirt" on storefront
      | Description                                                             | Price      | Compare at price | Product type |
      | Made from 100% heavy weight cotton, this is the ultimate basic T-shirt. | $25.00 USD | $35.00 USD       | Shirts       |
    And quit browser


  @dashboardImportProductWithVariantInventoryPolicyIsShopbase
  Scenario: Check import product with Variant Inventory Policy is shopbase #SB_PRO_IMP_62
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                                     | Message                                                  |
      | import-variant-inventory-tracker-shopbase.csv | We're currently importing your products into your store. |
    When Search product "test 1" on All product screen
    And Open detail product of product "test 1"
    And Click Edit variants and verify Inventory policy is "ShopBase tracks this product's inventory"
    And quit browser


  @dashboardImportErrorProduct
  Scenario: Check import error product #SB_PRO_IMP_55 #SB_PRO_IMP_56 #SB_PRO_IMP_57 #SB_PRO_IMP_63 #SB_PRO_IMP_67
    And user navigate to "Products>All products" screen
    Then import product with csv file and verify error massage
      | File name                     | Error message                                                                                                                |
      | import-without-handle.csv     | Please fix the following errors:\nLine 2 : Handle can't be blank                                                             |
      | file-elder-20MB.csv           | Please fix the following errors:\nThe supplied file is too large to be imported. (Limited size: 20MB)                        |
      | import-file-deny-continue.csv | Please fix the following errors:\nLine 2 : Inventory Policy is not included in the list                                      |
      | file-with-3000-product.csv    | We couldn't process the uploaded file now because its total products exceeded your daily quota.                              |
      | file-wrong-template.csv       | Please fix the following errors:\nOnly CSV files in correct format are supported at the time. Verify the file and try again. |
    And quit browser


  @dashboardImportProductWithSizeElder15MBAndLesser20MB
  Scenario: Check import product with size greater than 15MB and less than 20MB #SB_PRO_IMP_58
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                        | Message                                                          |
      | file-with-elder-3000-product.csv | We're currently process to import your products into your store. |
    And Search product "NEW YORK QUILT - 1220" on All product screen
    And Verify has product with "NEW YORK QUILT - 1220" title
