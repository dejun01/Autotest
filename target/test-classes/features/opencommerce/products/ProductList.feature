@dashboardProduct @dashboard
Feature: Product Listing
#  sbase_dashboard_product_list
  Background: : Login dashboard
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  @dashboardProductListing
  Scenario: Verify status product in product list #SB_PRO_PL_19 #SB_PRO_PL_20 #SB_PRO_PL_30 #SB_PRO_PL_31 #SB_PRO_PL_33 #SB_PRO_PL_59 #SB_PRO_PL_62 #SB_PRO_PL_64 #SB_PRO_PL_65 #SB_PRO_PL_66 #SB_PRO_PL_67 #SB_PRO_SP_101 #SB_PRO_SP_102 #SB_PRO_SP_103 #SB_PRO_SP_104 #SB_PRO_SP_110 #SB_PRO_SP_111 #SB_PRO_SP_112
    And Delete all products
    And Add a new product with data
      | Title      | Product type | Vendor    | Product availability                                                                |
      | product_01 | Shirt        | Printbase | Online store listing pages--false; Search Engine Bot Crawlers, Sitemap files--false |
      | product_02 | Footwear     | Shopbase  | Online store listing pages--false; Search Engine Bot Crawlers, Sitemap files--true  |
      | product_03 | Footwear     | Shopbase  | Online store listing pages--false; Search Engine Bot Crawlers, Sitemap files--true  |
      | product_04 | Shoes        | Shopbase  | Online store listing pages--false; Search Engine Bot Crawlers, Sitemap files--true  |
    And user navigate to "Products>All products" screen
    Then verify list product by created time "product_04,product_03,product_02,product_01"
    And verify total products by filter condition
      | Filter by                                 | Condition filter | Condition                                 | Total product       |
      | Available on Online Store                 | No               | Available on Online Store                 | 4 products selected |
      | Available on Search Engine, Sitemap files | Yes              | Available on Search Engine, Sitemap files | 3 products selected |
    When add action to products
      | List product          | Action                    |
      | product_02,product_03 | Make products unavailable |
      | product_04            | Delete selected products  |
    Then Verify data of product on product list screen
      | Title      | Type     | Vendor    | Status      | Is exist |
      | product_01 | Shirt    | Printbase | Available   |          |
      | product_02 | Footwear | Shopbase  | Unavailable |          |
      | product_03 | Footwear | Shopbase  | Unavailable |          |
      | product_04 | Shoes    | Shopbase  |             | false    |
    And quit browser


  @dashboardProductListing
  Scenario: Select actions and verify product detail #SB_PRO_PL_26 #SB_PRO_PL_27 #SB_PRO_PL_28 #SB_PRO_PL_35 #SB_PRO_PL_36 #SB_PRO_PL_37 #SB_PRO_PL_38 #SB_PRO_PL_40 #SB_PRO_PL_41
    And Delete all products
    Then Import product by csv with file name
      | File name                    | Message                                                          |
      | import_with_209_products.csv | We're currently process to import your products into your store. |
    And Wait list product imported with total "209 products"
    Then verify product detail when users have action with multiple products
      | Number of product unselected | Action                    | Tab                  | Total product         |
      |                              | Make products unavailable | Unavailable products | 209 products selected |
      |                              | Make products available   | Available products   | 209 products selected |
      | 1                            | Make products unavailable | Unavailable products | 208 products selected |
    And verify product detail when users have action with multiple products
      | Number of product unselected | Action            | Tags |
      |                              | Add tags>test     | test |
      |                              | Remove tags>test  |      |
      | 1                            | Add tags>tag_1    |      |
      | 1                            | Remove tags>tag_1 |      |
    And quit browser


  Scenario: verify field collection in product detail #SB_PRO_PL_42 #SB_PRO_PL_44 #SB_PRO_PL_45 #SB_PRO_PL_46 #SB_PRO_PC_119 #SB_PRO_PC_120 #SB_PRO_PL_47 #SB_PRO_PL_48 #SB_PRO_PL_49 #SB_PRO_PL_50 #SB_PRO_PL_51 #SB_PRO_PL_53 #SB_PRO_PL_43 SB_PRO_PL_49 SB_PRO_PL_50 #SB_PRO_PL_68
    And verify product detail when users have action with multiple products
      | Number of product unselected | Action                                                                           | Collection                                                |
      |                              | Add to collection>Manual collection,Manual collection 1,Manual collection 2      | Manual collection,Manual collection 1,Manual collection 2 |
      |                              | Remove from collection>Manual collection,Manual collection 1,Manual collection 2 |                                                           |
      | 1                            | Add to collection>Manual collection                                              |                                                           |
      | 1                            | Remove from collection>Manual collection                                         |                                                           |
    When Search product "product_120" on All product screen
    And verify product detail when users have action with multiple products
      | Action                                                                      | Collection                                                |
      | Add to collection>Manual collection,Manual collection 1,Manual collection 2 | Manual collection,Manual collection 1,Manual collection 2 |
    When Search product "product_120" on All product screen
    And verify product detail when users have action with multiple products
      | Action                                                                           | Collection |
      | Remove from collection>Manual collection,Manual collection 1,Manual collection 2 |            |
    And select all products in the first 2 pages
    Then add action to products
      | Action                              |
      | Add to collection>Manual collection |
    Then verify total products by filter condition
      | Filter by  | Value             | Total product         |
      | Collection | Manual collection | 100 products selected |
    And quit browser


  @dashboardProductListing
  Scenario: Verify filter product in list product #SB_PRO_PL_21 #SB_PRO_PL_54 #SB_PRO_PL_55 #SB_PRO_PL_56 #SB_PRO_PL_57 #SB_PRO_PL_58 #SB_PRO_PL_60 #SB_PRO_PL_61 #SB_PRO_PL_69 #SB_PRO_PL_70 #SB_PRO_PL_71 #SB_PRO_PL_72 #SB_PRO_PL_73 #SB_PRO_PL_74 #SB_PRO_PL_75 #SB_PRO_PL_76 #SB_PRO_PL_77 #SB_PRO_PL_78 #SB_PRO_PL_79 #SB_PRO_PL_80 #SB_PRO_PL_81 #SB_PRO_PL_82 #SB_PRO_PL_83 #SB_PRO_PL_84
    And verify total products by filter condition
      | Filter by                  | Condition filter   | Value                | Total product         |
      | Variant compare at price   | Less than          | 86                   | 120 products selected |
      | Variant barcode            | Contains           | test                 | 62 products selected  |
      | Product type               |                    | Shirts               | 139 products selected |
      | Product tags               |                    | mens t-shirt example | 100 products selected |
      | Cost per item              | More than          | 10                   | 48 products selected  |
      | Variant inventory tracker  | No                 |                      | 40 products selected  |
      | Variant inventory quantity | More than          | 26                   | 143 products selected |
      | Image                      | Has image          |                      | 53 products selected  |
      | Variant price              | More than          | 50                   | 159 products selected |
      | Variant name               | Contains           | Lithograph           | 80 products selected  |
      | Variant image              | Has image          |                      | 50 products selected  |
      | Image                      | Doesn't have image |                      | 156 products selected |
      | Variant SKU                | Contains           | AOP                  | 51 products selected  |
      | Vendors                    |                    | Acme                 | 60 products selected  |
    And close browser

