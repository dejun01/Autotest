@dashboardProduct @productCollection
Feature: ProductCollection
  #sbase_dashboard_product_collection
  Background: Login dashboard
    Given user login to shopbase dashboard by API
    Given Delete all products by API
    And user navigate to "Products>Collections" screen
    And Delete all collection

  Scenario: Check duplicate collection #SB_PRO_PC_166 #SB_PRO_PC_167
    And Create collection with data
      | Title             | Collection type |
      | Name collection 5 | Manual          |
      | Name collection 6 | Manual          |
      | Name collection 7 | Manual          |
      | Name collection 8 | Manual          |
    And user navigate to "Products>Collections" screen
    Then verify list collection in dashboard
    And open shop on storefront
    And open collection list on Store front
    And verify number collections on Store front
    And quit browser


  @addProductToManualCollection
  Scenario: Verify product added to manual collection #SB_PRO_PC_96 #SB_PRO_PC_104 #SB_PRO_PC_103 #SB_PRO_PC_102 #SB_PRO_PC_101 #SB_PRO_PC_100 #SB_PRO_PC_99 #SB_PRO_PC_98 #SB_PRO_PC_93 #SB_PRO_PC_82 #SB_PRO_PC_105 #SB_PRO_PC_162
    Given Create collection with data
      | Title             | Collection type |
      | Manual collection | Manual          |
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title          | Image     | Price |
      | product_test_1 | Logo1.jpg | 1     |
      | product_test_2 | Logo1.jpg | 2     |
      | product_test_3 | Logo1.jpg | 3     |
      | product_test_4 | Logo1.jpg | 4     |
    And user navigate to "Products>All products" screen
    And add collection to product
      | Product name   | Collection type | Collection title  |
      | product_test_4 | Manual          | Manual collection |
    Given user navigate to "Products>Collections" screen
    And add product to collection
      | Product name                                 | Collection type | Collection title  |
      | product_test_1,product_test_2,product_test_3 | Manual          | Manual collection |
    And user navigate to "Products>Collections" screen
    And search collection with title "Manual collection"
    Then verify sort products display correctly
      | Sort type         | Name Product                                                |
      | Product title Z-A | product_test_4,product_test_3,product_test_2,product_test_1 |
      | Product title A-Z | product_test_1,product_test_2,product_test_3,product_test_4 |
      | Highest price     | product_test_4,product_test_3,product_test_2,product_test_1 |
      | Lowest price      | product_test_1,product_test_2,product_test_3,product_test_4 |
      | Newest            | product_test_4,product_test_3,product_test_2,product_test_1 |
      | Oldest            | product_test_1,product_test_2,product_test_3,product_test_4 |
    Then verify list product in collection on store front
      | Product        | Image status |
      | product_test_1 | loaded       |
      | product_test_2 | loaded       |
      | product_test_3 | loaded       |
      | product_test_4 | loaded       |
    And quit browser


  Scenario: Verify sort manual type in manual collection #SB_PRO_PC_110 #SB_PRO_PC_109 #SB_PRO_PC_108 #SB_PRO_PC_107 #SB_PRO_PC_106 #SB_PRO_PC_115 #SB_PRO_PC_116 #SB_PRO_SP_117 #SB_PRO_SP_121 #SB_PRO_SP_123 #SB_PRO_SP_124 #SB_PRO_PC_160 #SB_PRO_PC_150 #SB_PRO_PC_151
    Given Create collection with data
      | Title             | Collection type |
      | Manual collection | Manual          |
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title          | Product availability                                                               |
      | product_test_1 | Online store listing pages--true; Search Engine Bot Crawlers, Sitemap files--true  |
      | product_test_2 | Online store listing pages--true; Search Engine Bot Crawlers, Sitemap files--false |
      | product_test_3 | Online store listing pages--false; Search Engine Bot Crawlers, Sitemap files--true |
      | product_test_4 |                                                                                    |
    Then user navigate to "Products>All products" screen
    When add action to products
      | List product   | Action                    |
      | product_test_4 | Make products unavailable |
    Given user navigate to "Products>Collections" screen
    And add product to collection
      | Product name                                                | Collection type | Collection title  |
      | product_test_1,product_test_2,product_test_3,product_test_4 | Manual          | Manual collection |
    Given user navigate to "Products>Collections" screen
    And search collection with title "Manual collection"
    And verify product status in collection
      | Product        | Status      | Icon Online store listing pages | Icon Search Engine Bot Crawlers, Sitemap files |
      | product_test_1 | Available   | green                           | green                                          |
      | product_test_2 | Available   | green                           | white                                          |
      | product_test_3 | Available   | white                           | green                                          |
      | product_test_4 | Unavailable | white                           | white                                          |
    When update product in manual sort
      | Sort type | Product                       | Action           | Position | List product                                                |
      | Manual    | product_test_2,product_test_4 | Move to top      |          | product_test_2,product_test_4,product_test_1,product_test_3 |
      |           | product_test_2,product_test_1 | Move to bottom   |          | product_test_4,product_test_3,product_test_2,product_test_1 |
      |           | product_test_2                | Move to position | 2        | product_test_4,product_test_2,product_test_3,product_test_1 |
    And quit browser


  @addProductToAutoCollection
  Scenario: Verify product auto added to auto collection #SB_PRO_PC_81 #SB_PRO_PC_84 #SB_PRO_PC_85 #SB_PRO_PC_86 #SB_PRO_PC_87 #SB_PRO_PC_88 #SB_PRO_PC_89 #SB_PRO_PC_90 #SB_PRO_PC_83
    And Create collection with data
      | Title                   | Collection type | Conditions                                       |
      | @Auto collection check@ | Automated       | all conditions,Product tag,is equal to,autocheck |
    And Verify conditions and value
      | Conditions                                 | Value         |
      | Compare at price,is equal to,autocheck123# | 0             |
      | Weight,is equal to,autocheck123#           | 0             |
      | Inventory stock,is equal to,autocheck123#  | 0             |
      | Product price,is equal to,autocheck123#    | 0             |
      | Product title,is equal to,autocheck123#    | autocheck123# |
      | Product type,is equal to,autocheck123      | autocheck123  |
      | Product vendor,is equal to,autocheck123    | autocheck123  |
      | Variant title,is equal to,autocheck123     | autocheck123  |
      | Product tag,is equal to,autocheck          | autocheck     |
    And Edit coditions with empty value
      | Conditions                        | Message                                |
      | Product tag,is equal to,autocheck | There is 1 error with this collection: |
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title             | Description     |
      | @Prod collection@ | This is product |
    And user navigate to "Products>All products" screen
    And add collection to product
      | Product name      | Collection type | Collection title        | Collection conditions |
      | @Prod collection@ | Auto            | @Auto collection check@ | Tags,autocheck        |
    Given user navigate to "Products>Collections" screen
    And add product to collection
      | Product name      | Collection type | Collection title        | Collection conditions                  |
      | @Prod collection@ | Auto            | @Auto collection check@ | Product title,contains,Prod collection |
    Then user navigate to "Products>Collections" screen
    And verify product added or deleted in collection
      | Product name      | Collection title        |
      | @Prod collection@ | @Auto collection check@ |
    And quit browser


  @addProductToAutoCollection
  Scenario: Update rule collection and verify product auto added to auto collection #SB_PRO_PC_97 #SB_PRO_PC_95 #SB_PRO_PC_94 #SB_PRO_PC_92 #SB_PRO_SP_113 #SB_PRO_SP_114 #SB_PRO_SP_115
    And Create collection with data
      | Title                   | Collection type | Conditions                                       |
      | @Auto collection check@ | Automated       | all conditions,Product tag,is equal to,autocheck |
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title             | Tags      |
      | @Prod collection@ | autocheck |
    Then user navigate to "Products>Collections" screen
    And verify product added or deleted in collection
      | Product name      | Collection title        |
      | @Prod collection@ | @Auto collection check@ |
    Given user navigate to "Products>Collections" screen
    And update rule Automated collection and verify product with conditions
      | Collection title        | Collection conditions       |
      | @Auto collection check@ | Product title,contains,test |
    Then user navigate to "Products>Collections" screen
    And verify product added or deleted in collection
      | Product name      | Collection title        |
      | @Prod collection@ | @Auto collection check@ |
    Given user navigate to "Products>All products" screen
    And verify collection deleted in product
      | Product name      | Collection title |
      | @Prod collection@ |                  |
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title          |
      | @test product@ |
    Then user navigate to "Products>Collections" screen
    And verify product added or deleted in collection
      | Product name   | Collection title        |
      | @test product@ | @Auto collection check@ |
    Given user navigate to "Products>All products" screen
    And Edit product "@test product@" with data
      | Title           |
      | Prod collection |
    Then user navigate to "Products>Collections" screen
    And verify product added or deleted in collection
      | Product name    | Collection title        |
      | Prod collection | @Auto collection check@ |
    And quit browser


  @SortProductInAutoCollection
  Scenario: Verify sort product in auto collection #SB_PRO_SP_118 #SB_PRO_SP_119 #SB_PRO_SP_120 #SB_PRO_SP_122 #SB_PRO_PC_112 #SB_PRO_PC_161
    And Create collection with data
      | Title           | Collection type | Conditions                                         |
      | Auto collection | Automated       | all conditions,Product title,contains,product_test |
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title          | Image     | Description     | Price |
      | product_test_1 | Logo1.jpg | This is product | 1     |
      | product_test_2 | Logo1.jpg | This is product | 2     |
      | product_test_3 | Logo1.jpg | This is product | 3     |
      | product_test_4 | Logo1.jpg | This is product | 4     |
    Then user navigate to "Products>Collections" screen
    And search collection with title "Auto collection"
    Then verify sort products display correctly
      | Sort type         | Name Product                                                |
      | Product title Z-A | product_test_4,product_test_3,product_test_2,product_test_1 |
      | Product title A-Z | product_test_1,product_test_2,product_test_3,product_test_4 |
      | Highest price     | product_test_4,product_test_3,product_test_2,product_test_1 |
      | Lowest price      | product_test_1,product_test_2,product_test_3,product_test_4 |
      | Newest            | product_test_4,product_test_3,product_test_2,product_test_1 |
      | Oldest            | product_test_1,product_test_2,product_test_3,product_test_4 |
    Then verify list product in collection on store front
      | Product        | Image status |
      | product_test_1 | loaded       |
      | product_test_2 | loaded       |
      | product_test_3 | loaded       |
      | product_test_4 | loaded       |
    And quit browser


  Scenario: Verify sort manual type in auto collection #SB_PRO_PC_136 #SB_PRO_IPST_7
    And Create collection with data
      | Title           | Collection type | Conditions                                         | Page title                                                                                                         | Meta description                                                                                                                                                                                                                                                                                                                                           |
      | Auto collection | Automated       | all conditions,Product title,contains,product_test | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. |
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title          |
      | product_test_1 |
      | product_test_2 |
      | product_test_3 |
      | product_test_4 |
    Given user navigate to "Products>Collections" screen
    And search collection with title "Auto collection"
    When update product in manual sort
      | Sort type | Product                       | Action           | Position | List product                                                |
      | Manual    | product_test_2,product_test_4 | Move to top      |          | product_test_2,product_test_4,product_test_1,product_test_3 |
      |           | product_test_2,product_test_1 | Move to bottom   |          | product_test_4,product_test_3,product_test_2,product_test_1 |
      |           | product_test_2                | Move to position | 2        | product_test_4,product_test_2,product_test_3,product_test_1 |


  Scenario: Check image thumbnail product #SB_PRB_TC_1 #SB_PRB_TC_2 #SB_PRB_TC_4 #SB_PRB_TC_9 #SB_PRB_TC_10
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title           | Image                               |
      | product_variant | Logo1.jpg,FB1.jpg,red.png,slide.jpg |
    Then Add a new product variant with multi option set
      | Option set | Option value   |
      | Size       | S, M           |
      | Color      | Red A, Green A |
      | Material   | Cotton, polyme |
    When update images for a variant
      | Variant        | Position Image |
      | S,Red A,Cotton | 3              |
    And user navigate to "Products>Collections" screen
    And Create collection with data
      | Title            | Collection type | Conditions                                               |
      | Smart collection | Automated       | all conditions,Product title,is equal to,product_variant |
    And Setup product thumbnail on collection
      | Option name | Option value |
      | Size        | S            |
      | Color       | Red A        |
      | Material    | Cotton       |
    Then verify list product in collection on store front
      | Product         | Image status |
      | product_variant | loaded       |
    And quit browser

  Scenario: Verify number product of collection when Add to collection from dashboard #SB_PRO_PL_42
    Given Create collection with data
      | Title              | Collection type |
      | Product Collection | Manual          |
    Then user navigate to "Products>All products" screen
    And Add a new product with data
      | Title                 |
      | product_collection_1  |
      | product_collection_2  |
      | product_collection_3  |
      | product_collection_4  |
      | product_collection_5  |
      | product_collection_6  |
      | product_collection_7  |
      | product_collection_8  |
      | product_collection_9  |
      | product_collection_10 |
    And select "10" products
    Then add product to collection from dashboard
      | Action            | Name collection    |
      | Add to collection | Product Collection |
    And click to button "Save"
    Then user navigate to "Products>Collections" screen
    And search collection with title "Product Collection"
    And verify collection has "10" products
    And quit browser








