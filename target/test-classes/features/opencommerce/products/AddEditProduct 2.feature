@dashboardProduct @dashboard
  #sbase_dashboard_product
Feature: Product

  Background: Login dashboard
    Given clear all data

  @dashboardProductAddProduct
  Scenario: Verify max length of Title, validate require Title, Add new product #SB_PRO_PL_22 #SB_PRO_PL_23 #SB_PRO_SP_85 #SB_PRO_SP_86
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    Then Verify max length of product title is "255"
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight | Error message        |
      |               | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | Title can't be blank |
      | @NameProduct@ |                                                                        |           |              |        |                  |       |                  |               |          |          |                                          |          |        |                      |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |                      |
    And user navigate to "Products>All products" screen
    When Search product "@NameProduct@" on All product screen
    And Open detail product of product "@NameProduct@"
    And Information of created product "@NameProduct@" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "@NameProduct@" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And quit browser


  @dashboardProductCollectProductToCollection
  Scenario: Collect product to collection, Duplicate product #SB_PRO_PD_14 #SB_PRO_PC_117 #SB_PRO_PC_118 #SB_PRO_SP_105 #SB_PRO_IPST_8 #SB_PRB_VGM_315
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                  | Description (optional) | Collection type |
      | @NameManualCollection@ | Collection of clothes  | Manual          |
    Then user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight | Page title                                                                                                         | Meta description                                                                                                                                                                                                                                                                                                                                           |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. |
    And Collect the product to collection
    Then Duplicate product
    And Information of created product "@Copy of NameProduct@" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight | Page title                                                                                                         | Meta description                                                                                                                                                                                                                                                                                                                                           |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. |
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |
    Then Verify this product "@Copy of NameProduct@" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             | Collection             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 | @NameManualCollection@ |
    When Switch to the first tab
    Then user navigate to "Products>All products" screen
    When Search product "@NameProduct@" on All product screen
    And Open detail product of product "@NameProduct@"
    Then Verify collection of product
    Then user navigate to "Products>All products" screen
    When Search product "@Copy of NameProduct@" on All product screen
    And Open detail product of product "@Copy of NameProduct@"
    Then Verify collection of product
    Given user navigate to "Products>Collections" screen
    When Search collection "@NameManualCollection@" on Collections screen
    And Open detail collection of collection "@NameManualCollection@"
    Then Verify product of collection
    And quit browser


  @dashboardProductEditProduct
  Scenario: Edit product #SB_PRO_PD_16 #SB_PRO_PD_17 #SB_PRO_PD_18 #SB_PRO_PD_19 #SB_PRO_PD_20 #SB_PRO_PD_21 #SB_PRO_PD_22 #SB_PRO_PD_23 #SB_PRO_PD_24 #SB_PRO_PD_25 #SB_PRO_PD_26
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And Edit product "@NameProduct@" with data
      | Title                | Description                                                            | Image     | Product type         | Vendor         | Tags             | Price | Compare at price | Cost per item | SKU         | Barcode         | Inventory policy                         | Quantity | Weight | Error message        |
      |                      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt                | Uniqlo         | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B    | B654321A        | ShopBase tracks this product's inventory | 100      | 50     | Title can't be blank |
      | @NameProductUpdated@ |                                                                        |           |                      |                |                  |       |                  |               |             |                 |                                          |          |        |                      |
      | @NameProductUpdated@ | Description Updated                                                    | Logo1.jpg | Product type Updated | Vendor Updated | tag4             | 20    | 40               | 10            | SKU Updated | Barcode Updated | ShopBase tracks this product's inventory | 200      | 100    |                      |
    And Information of created product "@NameProduct@" display correctly
      | Description         | Product type         | Vendor         | Tags                   | Price | Compare at price | Cost per item | SKU         | Barcode         | Quantity | Weight |
      | Description Updated | Product type Updated | Vendor Updated | tag1, tag2, tag3, tag4 | 20    | 40               | 10            | SKU Updated | Barcode Updated | 200      | 100    |
    Then Verify this product "@NameProduct@" on storefront
      | Description         | Price      | Compare at price | Vendor         | SKU         | Product type         | Tags                   |
      | Description Updated | $20.00 USD | $40.00 USD       | Vendor Updated | SKU Updated | Product type Updated | tag1, tag2, tag3, tag4 |
    And quit browser


  @dashboardProductSearchEngineListingPreview
  Scenario: Verify Search engine listing preview #SB_PRO_PD_27 #SB_PRO_PD_28 #SB_PRO_PD_29 #SB_PRO_PD_30 #SB_PRO_IPST_1 #SB_PRO_IPST_2
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    Then Verify default data of Search engine listing preview
      | Page title    | Meta description                                                       | URL and handle |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | @NameProduct@  |
    Then Edit Search engine listing preview with data
      | Page title                                                                                                         | URL and handle   | Meta description                                                                                                                                                                                                                                                                                                                                           |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | @handle-updated@ | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. |
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | URL and handle   | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | @handle-updated@ | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |
    And Verify Search engine listing preview on Storefront
    And quit browser


  Scenario: Verify message upload image product #SB_PRO_SP_93 #SB_PRO_SP_94 #SB_PRO_SP_95 #SB_PRB_VGM_175 #SB_PRB_VGM_176 #SB_PRB_VGM_177 #SB_PRB_VGM_182 #SB_PRB_VGM_186 #SB_PRB_VGM_187 #SB_PRB_VGM_191 #SB_PRB_VGM_259 #SB_PRB_VGM_260 #SB_PRB_VGM_262 #SB_PRB_VGM_261 #SB_PRB_VGM_274 #SB_PRB_VGM_275 #SB_PRB_VGM_266 #SB_PRB_VGM_263 #SB_PRB_VGM_278 #SB_PRB_VGM_279 #SB_PRB_VGM_276 #SB_PRB_VGM_293 #SB_PRB_VGM_294 #SB_PRB_VGM_295 #SB_PRB_VGM_296 #SB_PRB_VGM_297 #SB_PRB_VGM_302 #SB_PRB_VGM_303 #SB_PRB_VGM_290 #SB_PRB_VGM_307 #SB_PRB_VGM_308 #SB_PRB_VGM_310 #SB_PRB_VGM_484 #SB_PRB_VGM_485
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Upload media for product or campaign
      | Title         | Image             | Image url                                                               | Error image |
      | @NameProduct@ |                   | https://img.btdmp.com/10121/10121539/products/1631085811591460e945.jpeg |             |
      |               |                   | https://www.youtube.com/watch?v=xPFckIKt8zg                             |             |
      |               |                   | https://24hstore.vn/upload_images/images/2019/11/14/anh-gif-2-min.gif   |             |
      |               | anh-gif-1-min.gif |                                                                         |             |
      |               | Logo1.jpg         |                                                                         |             |
      |               | red.png           |                                                                         |             |
#      |               | Video_chuan.mp4   |                                                                         |             |  #đang lỗi
    Then Verify media product on store front
    And quit browser

  Scenario: Verify error message upload image product #SB_PRB_VGM_178 #SB_PRB_VGM_179 #SB_PRB_VGM_186 #SB_PRB_VGM_263
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Upload media for product or campaign
      | Title         | Image                | Image url | Error image                                                                                                          |
      | @NameProduct@ | Video Sai Format.avi |           | There is a file that we don’t support this file type. Please try again with PNG, JPG, JPEG, GIF, WEBP, GIF, MP4, MOV |
      |               | phub/image/120MB.png |           | 120MB.png: This file is too large. The allowed size is under 20 MB.                                                  |
    And quit browser


  Scenario: verify custom option
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    When Add a new product with data
      | Title         | Description                                                            |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And add new custom option with data
      | Custom option   | Name            | Type       | Label   | Allow the following characters              | Hide option | Add another option |
      | Custom Option 1 | Custom Option 1 | Text field | Text 1  | Characters,Numbers                          |             | yes                |
      | Custom Option 2 | Custom Option 2 | Image      | Image 1 |                                             |             | yes                |
      | Custom Option 3 | Custom Option 3 | Text area  | Text 2  | Characters,Numbers,Special Characters,Emoji |             | yes                |
      | Custom Option 4 | Custom Option 4 | Image      | Image 2 |                                             | yes         | no                 |
    And quit browser


  Scenario: verify custom option #SB_PRO_PD_2 #SB_PRO_PD_8 #SB_PRO_SP_90 #SB_PRO_SP_91 #SB_PRO_SP_92 #SB_PRO_PG_SF_2 #SB_PRO_PD_1 #SB_PRO_PD_3 #SB_PRO_PD_4 #SB_PRO_PD_5 #SB_PRO_PD_6 #SB_PRO_PD_7 #SB_PRO_PD_8 #SB_PRO_PD_9 #SB_PRO_PD_10 #SB_PRO_PD_11 #SB_PRO_PD_12 #SB_PRO_PD_13
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    When Add a new product with data
      | Title         | Description                                                            |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And add new custom option with data
      | Custom option   | Name            | Type          | Label   | Allow the following characters              | Values | Hide option | Add another option |
      | Custom Option 1 | Custom Option 1 | Radio buttons | Radio   |                                             | 1,2    |             | yes                |
      | Custom Option 2 | Custom Option 2 | Text area     | Text 1  | Characters,Numbers                          |        | yes         | yes                |
      | Custom Option 3 | Custom Option 3 | Image         | Image 1 |                                             |        |             | yes                |
      | Custom Option 4 | Custom Option 4 | Droplist      | Drop    |                                             | a,b    |             | yes                |
      | Custom Option 5 | Custom Option 5 | Text field    | Text 2  | Characters,Numbers,Special Characters,Emoji |        |             | yes                |
      | Custom Option 6 | Custom Option 6 | Image         | Image 2 |                                             |        |             | no                 |
    And add condtional logic
      | Custom option   | Condition   | Value | Show value      |
      | Custom Option 1 | is equal to | 2     | Custom Option 3 |
      | Custom Option 4 | is equal to | b     | Custom Option 6 |
    Then input value custom option
      | Custom option | Value     |
      | Radio         | 2         |
      | Drop          | b         |
      | Image 1       | Logo1.jpg |
      | Text 2        | test      |
      | Image 2       | green.png |
    Then verify value after add to card
      | Custom option   | Value     |
      | Custom Option 1 | 2         |
      | Custom Option 3 | logo1.jpg |
      | Custom Option 3 | b         |
      | Custom Option 5 | test      |
      | Custom Option 1 | green.png |

  @dashboardFilfterProductListonSF
  Scenario: verify list product when filter on SF #SB_PRO_PG_SF_3 #SB_PRO_PG_SF_6 #SB_PRO_PC_163 #SB_PRO_PC_164
    Given open shop on storefront
    Then verify filter list product on storefront
      | Product                                                                                         | Tag       | Vendor | Collection        | Handle collection  | Total product | Image Variant |
      | New Orleans Saints Men Running Yeezy Boost Shoes Sport Sneakers, Custom Shoes For Men And Women |           | Yeezy  |                   |                    | 12            |               |
      | New Orleans Saints Men Running Yeezy Boost Shoes Sport Sneakers, Custom Shoes For Men And Women | yeezy tag |        |                   |                    | 12            |               |
      | Product image variant                                                                           |           |        |                   |                    |               | loaded        |
      | Electro Owl Tapestry                                                                            |           |        | Filter collection | /filter-collection | 9             |               |
