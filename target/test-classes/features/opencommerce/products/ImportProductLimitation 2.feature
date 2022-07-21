@dashboardImportProductLimitation @dashboard
Feature: Import Export Product Limitation
  #evn: sbase_dashboard_product_import_limitation

  @dashboardImportProduct
  Scenario: Check import product with shop free and basic #SB_PRO_IMP_74 #SB_PRO_IMP_75 #SB_PRO_IMP_79
    Given user login to shop dashboard by API
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                 | Message                                                  |
      | product-sample-import.csv | We're currently importing your products into your store. |
    Then import product with csv file and verify error massage
      | File name                  | Message                                                                                         |
      | file-with-3000-product.csv | We couldn't process the uploaded file now because its total products exceeded your daily quota. |

    Given user login to basicShop dashboard by API
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                 | Message                                                  |
      | product-sample-import.csv | We're currently importing your products into your store. |
    Then import product with csv file and verify error massage
      | File name                  | Message                                                             |
      | file-with-3000-product.csv | But you can add maximum 3,000 products/day with the current package |
    And close browser


  @dashboardImportProduct
  Scenario: Check import product with shop standart and pro #SB_PRO_IMP_76 #SB_PRO_IMP_77
    Given user login to standartShop dashboard by API
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                 | Message                                                  |
      | product-sample-import.csv | We're currently importing your products into your store. |
    Then import product with csv file and verify error massage
      | File name                    | Message                                                             |
      | import-with-6400-product.csv | But you can add maximum 6,000 products/day with the current package |

    Given user login to proShop dashboard by API
    And user navigate to "Products>All products" screen
    Then Import product by csv with file name
      | File name                 | Message                                                  |
      | product-sample-import.csv | We're currently importing your products into your store. |
    Then import product with csv file and verify error massage
      | File name                     | Message                                                              |
      | import-with-30001-product.csv | But you can add maximum 30,000 products/day with the current package |
    And close browser


  @dashboardImportProductByApi
  Scenario: Check add product by api with status 200 #SB_PRO_IMP_80
    And Add product by api with title "product_test"
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Then import product with csv file and verify error massage
      | File name                  | Message                                                                                                  |
      | file-with-3000-product.csv | But you can add maximum 3,000 products/day with the current package, today you already added 2 products. |


  @dashboardImportProductByApi
  Scenario: Check add product by api when limit product with status 400 and error code "CREATE_PRODUCT_LIMIT_PER_DAY_REACHED" #SB_PRO_PD_32
    And Add product by api with title "test"
    And close browser


  Scenario: Check import product with product first has 501 images or variants #SB_PRO_IMP_68 #SB_PRO_IMP_71 #SB_PRO_IMP_72 #SB_PRO_IMP_73 #SB_PRO_PD_37 #SB_PRO_PD_38 #SB_PRO_PD_39 #SB_PRB_VGM_290
    Given user login to limitImageShop dashboard by API
    And user navigate to "Products>All products" screen
    And Search product "Product limit image variant" on All product screen
    And Open detail product of product "Product limit image variant"
    Then verify button
      | Button             | Status   |
      | Add media from URL | disabled |
      | Add media          | disabled |
      | Add variant        | disabled |
    And edit variant
    Then verify button
      | Button    | Status   | Title                        |
      | Duplicate | disabled | Maximum 500 variants reached |
    And close browser


  @dashboardLimitProductMedia
  Scenario: Check limmit 500 videos #SB_PRB_VGM_188 #SB_PRB_VGM_189 # SB_PRB_VGM_190 #SB_PRB_VGM_192 #SB_PRB_VGM_193 #SB_PRB_VGM_287 #SB_PRB_VGM_288 #SB_PRB_VGM_289 #SB_PRB_VGM_358 #SB_PRB_VGM_304 #SB_PRB_VGM_311 #SB_PRB_VGM_408 #SB_PRB_VGM_412 #SB_PRB_VGM_416
    Given user login to limitImageShop dashboard by API
    And user navigate to "Products>All products" screen
    And Search product "Product limit video" on All product screen
    And Open detail product of product "Product limit video"
    Then verify button
      | Button             | Status   |
      | Add media from URL | disabled |
      | Add media          | disabled |
    And close browser


  Scenario: Check send mail after import #SB_PRO_IMP_103
    And user verify content of mail import product
      | Subject                                                | Content                          |
      | shop-no-confirm-package Product import completed       | Finish importing a product list. |
      | shop-confirm-package-basic Product import completed    | Finish importing a product list. |
      | shop-confirm-package-standart Product import completed | Finish importing a product list. |
      | shop-confirm-package-pro Product import completed      | Finish importing a product list. |