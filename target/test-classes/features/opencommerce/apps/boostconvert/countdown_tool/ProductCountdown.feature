Feature: Product countdown sf next

#  Env:
#  prod_copt_product_countdown
#  staging_copt_product_countdown

#  Theme: Inside, Roller
#  Products:
  #  Hats
  #  Bracelet : collection "Coasters"
  #  Pineapple Earrings


  Scenario Outline: Change setting product Countdown #SB_SF_BC_23 #SB_SF_BC_24 #SB_SF_BC_26 #SB_SF_BC_27 #SB_SF_BC_29 #SB_SF_BC_30 #SB_SF_BC_31 #SB_SF_BC_33 #SB_SF_BC_35 #SB_SF_BC_38 #SB_SF_BC_39 #SB_SF_BC_40 #SB_SF_BC_41 #SB_SF_BC_43
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Countdown Tools>Product countdown" screen
    When turn on product countdown of BoostCovert is "<Is on>"
    And change product countdown settings of BoostCovert as "<KEY>"
      | KEY | Setting items left in stock       | Quantity of items | Trigger                          | Trigger pages              |
      | 1   | Show your actual number of stocks |                   | Show for all products            |                            |
      | 2   | Percentage of items left in stock | 10,20             | Show for some products I specify | Collection>Coasters        |
      | 3   | Number of items left in stock     | 50,120            | Show for some products I specify | Product>Pineapple Earrings |
    And open shop on storefront
    Then verify product countdown is shown on product page as "<KEY>"
      | KEY | Product name       | is show | Setting items left in stock       | Quantity of items |
      | 1   | Hats               | true    | Show your actual number of stocks |                   |
      | 2   | Hats               | false   | Percentage of items left in stock | 10,20             |
      | 2   | Bracelet           | true    | Percentage of items left in stock | 10,20             |
      | 3   | Pineapple Earrings | true    | Number of items left in stock     | 50,120            |
      | 3   | Bracelet           | false   | Number of items left in stock     | 50,120            |
      | 4   | Pineapple Earrings | false   | Number of items left in stock     | 50,120            |
    And close browser
    Examples:
      | KEY | Is on |
      | 1   | true  |
      | 2   | true  |
      | 3   | true  |
      | 4   | false |
