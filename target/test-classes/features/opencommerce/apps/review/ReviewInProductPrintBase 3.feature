Feature: Import product review into product admin pbase

# Env:
#  staging_pbase_review_in_product_admin
#  prod_pbase_review_in_product_admin
#  dev_pbase_review_in_product_admin

#  Theme: Inside, Roller
#  Products: PrintBase multiple 2D products 6, PrintBase Blanket B102, PrintBase Blanket B105
#  Setting: uncheck show sticky button in theme editor


  Scenario: Delete all reviews #SB_SF_RV_13
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    Then user delete all reviews
    And verify deleted all reviews


  Scenario Outline: verify import review with Ali Express #SB_SF_RV_4 #SB_SF_RV_5 #SB_SF_RV_RDBIR_82
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    Then open product "PrintBase multiple 2D products 6" on Dashboard PrintBase
    Then open Import Reviews popup
    When import AliExpress reviews into product admin
      | Product name | URLs AliExpress                                                                                     | Message                  |
      |              |                                                                                                     | Link is required         |
      |              | https://www.aliexpress.com                                                                          | Invalid Ali Express link |
      |              | https://www.aliexpress.com/item/32717103110.html,https://www.aliexpress.com/item/4001172559566.html | Success                  |
    And setup AliExpress filter
      | Import reviews from star | Maximum number of reviews per link | Country | Keep original date |
      | 4 stars and above        | 5                                  | All     | true               |
    Then click "Check reviews" button on popup
    Then verify shared reviews on All reviews page dashboard "<KEY>"
      | KEY | Min number of star | Number of reviews by product | Product                          | Number of reviews by status | Status    |
      | 001 | 4                  | 5                            | PrintBase multiple 2D products 6 | 5                           | Published |
    And user navigate to "Campaigns" screen
    Then open product "PrintBase multiple 2D products 6" on Dashboard PrintBase
    Then verify Reviews Analytics "<KEY>"
      | KEY | Total reviews | Avg rating | Rating |
      | 001 | 5             |            | 4,5    |
    Given open shop on storefront
    Then search and select the product "PrintBase multiple 2D products 6"
    And verify shared review on SF "<KEY>"
      | KEY | Number of reviews | Min number of star | Reviews Type    |
      | 001 | 5                 | 4                  | Product reviews |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: verify import review with file CSV #SB_SF_RV_1 #SB_SF_RV_2 #SB_SF_RV_3
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    Then open product "PrintBase Blanket B102" on Dashboard PrintBase
    Then open Import Reviews popup
    And import reviews from a spreadsheet into Product admin with cvs file is "front/review_import.csv"
    And click "Check reviews" button on popup
    Then verify auto filter reviews by product "PrintBase Blanket B102"
    Then verify review show on Dashboard
      | Review             | Is show | Product name           | Status    |
      | Normal             | true    | PrintBase Blanket B102 | Published |
      | Email null         | false   |                        |           |
      | Review body null   | false   |                        |           |
      | Rating invalid     | false   |                        |           |
      | Customer name null | false   |                        |           |
      | Has product handle | true    | PrintBase Blanket B102 | Published |
    And user navigate to "Campaigns" screen
    Then open product "PrintBase Blanket B102" on Dashboard PrintBase
    Then verify Reviews Analytics "<KEY>"
      | KEY | Total reviews | Avg rating | Rating |
      | 1   | 2             | 4.5/5      |        |
    When open shop on storefront
    And search and select the product "PrintBase Blanket B102"
    And verify active tab "Product reviews" on SF
    Then verify reviews are show on SF as "<KEY>"
      | KEY | Title              | Show review on SF | Rating | Content | Your name | Type            |
      | 1   | Normal             | true              | 4      | good    | Ha Vu     | Product reviews |
      | 1   | Has product handle | true              | 5      | good    | Ha Vu     | Product reviews |
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: verify import review with shared reviews #SB_SF_RV_6 #SB_SF_RV_7 #SB_SF_RV_8 #SB_SF_RV_9 #SB_SF_RV_10 #SB_SF_RV_11 #SB_SF_RV_12 #SB_SF_RV_13 #SB_SF_RV_14
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    Then open product "PrintBase Blanket B105" on Dashboard PrintBase
    Then open Import Reviews popup
    And import shared reviews in product admin "<KEY>"
      | KEY | Condition                        | Number of shared reviews | Message |
      | 001 | higher than and equal to 3 stars | 15                       |         |
    And click "Check reviews" button on popup
    Then verify shared reviews on All reviews page dashboard "<KEY>"
      | KEY | Min number of star | Number of reviews by product | Product                | Number of reviews by status | Status    |
      | 001 | 3                  | 15                           | PrintBase Blanket B105 | 22                          | Published |
    And user navigate to "Campaigns" screen
    Then open product "PrintBase Blanket B105" on Dashboard PrintBase
    Then verify Reviews Analytics "<KEY>"
      | KEY | Total reviews | Avg rating | Rating |
      | 1   | 15            |            | 3,4,5  |
    Given open shop on storefront
    Then search and select the product "PrintBase Blanket B105"
    And verify shared review on SF "<KEY>"
      | KEY | Number of reviews | Min number of star | Reviews Type    |
      | 001 | 15                | 3                  | Product reviews |
    Then close browser
    Examples:
      | KEY |
      | 001 |