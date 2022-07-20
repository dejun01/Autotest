@dashboardCloneProduct @dashboard
Feature: Clone Product Verify Data
#evn: clone_product
  Background:
    Given user login to firstShop dashboard by API
    And get shop id

  @dashboardCheckProductFromAnotherShop
  Scenario: Check product from another shop #SB_PRO_IMP_104
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "5" products
    And verify import process
    And quit browser

  @dashboardCheckProductDetail
  Scenario: Check mapping between product of source shop and des shop #SB_PRO_CP_84 #SB_PRO_CP_85 #SB_PRO_IPST_10
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    When Search product "Sample Analytics Testing" on All product screen
    And Open detail product of product "Sample Analytics Testing"
    Given Information of created product "Sample Analytics Testing" display correctly
      | Description                                                                                                            | Product type   | Vendor  | Tags    |
      | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Sample Testing | Testing | testing |
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |
    And Information of created variants display correctly on product detail page
      | Variant   | Price | SKU         | Inventory |
      | S, Cyan   | 10    | test::12345 | N/A       |
      | S, Purple | 10    | test::12345 | N/A       |
      | S, Grey   | 10    | test::12345 | N/A       |
      | M, Cyan   | 10    | test::12345 | N/A       |
      | M, Purple | 10    | test::12345 | N/A       |
      | M, Grey   | 10    | test::12345 | N/A       |
      | L, Cyan   | 10    | test::12345 | N/A       |
      | L, Purple | 10    | test::12345 | N/A       |
      | L, Grey   | 10    | test::12345 | N/A       |
    And Verify image link
    Then Verify all product variants on storefront
      | Variant Label          | Variant  | Price      | Compare at price | Description                                                                                                            | Vendor  | SKU         | Product type   | Tags    |
      | Size: S; Color: Cyan   | S,Cyan   | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: S; Color: Purple | S,Purple | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: S; Color: Grey   | S,Grey   | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: M; Color: Cyan   | M,Cyan   | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: M; Color: Purple | M,Purple | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: M; Color: Grey   | M,Grey   | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: L; Color: Cyan   | L,Cyan   | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: L; Color: Purple | L,Purple | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
      | Size: L; Color: Grey   | L,Grey   | $10.00 USD | $20.00 USD       | This sample is deserved for analytic testing. About its detail is fake and easy to analyse and belong to testing owner | Testing | test::12345 | Sample Testing | testing |
    Then quit browser


  @dashboardCheckProductDetail
  Scenario: Verify product mapping Printhub #SB_PRO_IPST_9 #SB_PRB_VGM_316
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    When Search product "Compare Image Mug" on All product screen
    And Open detail product of product "Compare Image Mug"
    And Get number of image of source shop
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    When Search product "Compare Image Mug" on All product screen
    And Open detail product of product "Compare Image Mug"
    And Get number of image of des shop
    And Verify number of image of both shop
    And user navigate to "Products>All products" screen
    When Search product "Sample Testing Part 3" on All product screen
    And Open detail product of product "Sample Testing Part 3"
    Given Information of created product "Sample Testing Part 3" display correctly
      | Product type | Vendor   | Tags         |
      | selling      | Shopbase | mug, testing |
    And Information of created variants display correctly on product detail page
      | Variant                   | Price | SKU                          | Inventory |
      | Beverage Mug, 11oz, white | 19.49 | PH-AP-BeverageMug-white-11oz | N/A       |
      | Beverage Mug, 11oz, black | 19.49 | PH-AP-BeverageMug-black-11oz | N/A       |
      | Beverage Mug, 15oz, white | 23.99 | PH-AP-BeverageMug-white-15oz | N/A       |
      | Beverage Mug, 15oz, black | 23.99 | PH-AP-BeverageMug-black-15oz | N/A       |
    And Verify image link
    Then Verify data of Search engine listing preview
      | Page title        | Length page title           | Meta description                                | Length meta description      |
      | Title tags in SEO | 17/ 70 suggested characters | This meta description has an appropriate length | 47/ 320 suggested characters |
    Then Verify all product variants on storefront
      | Variant Label            | Variant    | Price      | Compare at price | Vendor   | SKU                          | Product type | Tags         |
      | Size: 11oz; Color: White | 11oz,White | $19.49 USD | $34.49 USD       | Shopbase | PH-AP-BeverageMug-white-11oz | selling      | mug, testing |
      | Size: 11oz; Color: Black | 11oz,Black | $19.49 USD | $34.49 USD       | Shopbase | PH-AP-BeverageMug-black-11oz | selling      | mug, testing |
      | Size: 15oz; Color: White | 15oz,White | $23.99 USD | $38.99 USD       | Shopbase | PH-AP-BeverageMug-white-15oz | selling      | mug, testing |
      | Size: 15oz; Color: Black | 15oz,Black | $23.99 USD | $38.99 USD       | Shopbase | PH-AP-BeverageMug-black-15oz | selling      | mug, testing |
    And quit browser


  @dashboardCheckArtworkLink
  Scenario: Verify artwork link of both shop
    Given user login to firstShop dashboard by API
    And user navigate to "Apps" screen
    And select Print Hub on Apps list
    And user navigate to "Library>Artworks" screen
    And Get number of image of artwork source shop
    Given user login to secondShop dashboard by API
    And user navigate to "Apps" screen
    And select Print Hub on Apps list
    And user navigate to "Library>Artworks" screen
    And Get number of image of artwork des shop
    And Verify number of artwork of both shop
    And Verify artwork link
