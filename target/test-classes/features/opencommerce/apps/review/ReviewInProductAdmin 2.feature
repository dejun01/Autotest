Feature: Import product review into product admin sbase

#env:
#  staging_sbase_review_in_product_admin
#  prod_sbase_review_in_product_admin
#  dev_sbase_review_in_product_admin

#  Theme: Inside, Roller
#  Products: Slice Quick & Right
#  Setting: uncheck show sticky button in theme editor


  Scenario: Delete all reviews
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    Then user delete all reviews
    And verify deleted all reviews


  Scenario Outline: verify import review with Ali Express #SB_SF_RV_43 #SB_SF_RV_44 #SB_SF_RV_PRIPA_140 #SB_SF_RV_PRIPA_162
    Given user login to shopbase dashboard by API
    And user navigate to "Products" screen
    Then open product "Slice Quick & Right" on Dashboard
    Then open Import Reviews popup
    When import AliExpress reviews into product admin
      | Product name | URLs AliExpress                                                                                     | Message                  |
      |              |                                                                                                     | Link is required         |
      |              | https://www.aliexpress.com                                                                          | Invalid Ali Express link |
      |              | https://www.aliexpress.com/item/32717103110.html,https://www.aliexpress.com/item/4001172559566.html | Success                  |
    And setup AliExpress filter
      | Import reviews from star | Maximum number of reviews per link | Country | Translation to English |
      | 4 stars and above        | 5                                  | All     | true                   |
    Then click "Check reviews" button on popup
    Then verify shared reviews on All reviews page dashboard "<KEY>"
      | KEY | Min number of star | Number of reviews by product | Product             | Number of reviews by status | Status    |
      | 001 | 4                  | 5                            | Slice Quick & Right | 5                           | Published |
    And user navigate to "Products" screen
    Then open product "Slice Quick & Right" on Dashboard
    Then verify Reviews Analytics "<KEY>"
      | KEY | Total reviews | Avg rating | Rating |
      | 001 | 5             |            | 4,5    |
    Given open shop on storefront
    Then search and select the product "Slice Quick & Right"
    And verify shared review on SF "<KEY>"
      | KEY | Number of reviews | Min number of star | Reviews Type    |
      | 001 | 5                 | 4                  | Product reviews |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: verify import review with file CSV #SB_SF_RV_45 #SB_SF_RV_PRIPA_108 #SB_SF_RV_PRIPA_135 #SB_SF_RV_PRIPA_137
    Given user login to shopbase dashboard by API
    And user navigate to "Products" screen
    Then open product "Cairbull Cycling Helmet" on Dashboard
    Then open Import Reviews popup
    And import reviews from a spreadsheet into Product admin with cvs file is "front/review_import.csv"
    And click "Check reviews" button on popup
    Then verify auto filter reviews by product "Cairbull Cycling Helmet"
    Then verify review show on Dashboard
      | Review             | Is show | Product name            | Status    |
      | Normal             | true    | Cairbull Cycling Helmet | Published |
      | Email null         | false   |                         |           |
      | Review body null   | false   |                         |           |
      | Rating invalid     | false   |                         |           |
      | Customer name null | false   |                         |           |
      | Has product handle | true    | Cairbull Cycling Helmet | Published |
    And user navigate to "Products" screen
    Then open product "Cairbull Cycling Helmet" on Dashboard
    Then verify Reviews Analytics "<KEY>"
      | KEY | Total reviews | Avg rating | Rating |
      | 1   | 2             | 4.5/5      |        |
    When open shop on storefront
    And search and select the product "Cairbull Cycling Helmet"
    And verify active tab "Product reviews" on SF
    Then verify reviews are show on SF as "<KEY>"
      | KEY | Title              | Show review on SF | Rating | Content | Your name | Type            |
      | 1   | Normal             | true              | 4      | good    | Ha Vu     | Product reviews |
      | 1   | Has product handle | true              | 5      | good    | Ha Vu     | Product reviews |
    Examples:
      | KEY |
      | 1   |