@dashboardCloneProduct @dashboard
Feature: Clone Campaign Verify Data
#clone_campaign

  Background:
    Given user login to firstShop dashboard by API
    And get shop id

  @dashboardCheckCampaignsDetail
  Scenario: Verify campaign Printbase - #SB_PRO_CP_69 #SB_PRO_CP_70 #SB_PRB_PPC_105 #SB_PRO_IPST_11 #SB_PRO_IPST_12 #SB_PRO_IPST_14 #SB_PRO_IPST_15
    Given user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    When Search product "Shoes testing" on All product screen
    And Open detail product of product "Shoes testing"
    And Get number of image of source shop
    Given user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    When Search product "Shoes testing" on All product screen
    And Open detail product of product "Shoes testing"
    And Get number of image of des shop
    And Verify number of image of both shop
    And user navigate to "Campaigns>All campaigns" screen
    When Search product "Mug Personalize Testing" on All product screen
    And Open detail product of product "Mug Personalize Testing"
    Given Information of created product "Mug Personalize Testing" display correctly
      | Product type | Vendor   | Tags      |
      | selling      | Shopbase | mug, test |
    And Information of created variants display correctly on campaign detail page
      | Variant                   | Price | SKU                          |
      | Beverage Mug, 11oz, white | 19.49 | PB-AP-BeverageMug-white-11oz |
      | Beverage Mug, 11oz, black | 19.49 | PB-AP-BeverageMug-black-11oz |
      | Beverage Mug, 15oz, white | 23.99 | PB-AP-BeverageMug-white-15oz |
      | Beverage Mug, 15oz, black | 23.99 | PB-AP-BeverageMug-black-15oz |
    And Verify image link
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |
    Then Verify all product variants on storefront
      | Variant Label            | Variant    | Price      | Compare at price | Vendor   | SKU                          | Product type | Tags      |
      | Size: 11oz; Color: White | 11oz,White | $19.49 USD | $34.49 USD       | ShopBase | PB-AP-BeverageMug-white-11oz | selling      | mug, test |
      | Size: 11oz; Color: Black | 11oz,Black | $19.49 USD | $34.49 USD       | ShopBase | PB-AP-BeverageMug-black-11oz | selling      | mug, test |
      | Size: 15oz; Color: White | 15oz,White | $23.99 USD | $38.99 USD       | ShopBase | PB-AP-BeverageMug-white-15oz | selling      | mug, test |
      | Size: 15oz; Color: Black | 15oz,Black | $23.99 USD | $38.99 USD       | ShopBase | PB-AP-BeverageMug-black-15oz | selling      | mug, test |
    And verify show button Preview your design on store front "true"
    And Verify image custom option on storefront


