Feature: Bulk Update delete CO

#env:sbase_bulk_update_delete_CO
#au-sbase-bulk-update-deleteco.onshopbase.com
  #prod_sbase_bulk_update_delete_CO
  Background: Navigate to "Products>All products" screen
    Given clear all data

  Scenario: Precondition >> Create product of shopbase
    Given user login to shopbase dashboard by API
    And Delete all products by API
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title           | Description                                                            |
      | Product CO full | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And add new custom option with data
      | Custom option   | Name        | Type          | Label       | Allow the following characters              | Hide option | Values | Add another option |
      | Custom Option 1 | CO text     | Text field    | Text 1      | Characters,Numbers                          |             |        | yes                |
      | Custom Option 2 | CO area     | Text area     | Text 2      | Characters,Numbers,Special Characters,Emoji |             |        | yes                |
      | Custom Option 3 | CO Image    | Image         | Image 1     |                                             |             |        | yes                |
      | Custom Option 4 | CO droplist | Droplist      | CO droplist |                                             |             | a,b    | yes                |
      | Custom Option 5 | CO radio    | Radio buttons | CO radio    |                                             |             | 11,22  | no                 |
    And quit browser


  Scenario:Precondtion >> Create campaign personalize
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product     | Category |
      | 01  | Unisex Tank | Apparel  |
    And add new layer as "01"
      | KEY | Product     | Layer type | Layer name | Front or back |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Text       |            | Front         |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "01"
      | KEY | Layer type | Layer name   | Custom name |
      | 01  | Text field | Text layer 1 | Custom text |
      | 01  | Text field | Text layer 2 | CO text     |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title       | Description   | Tags |
      | Campaign CO | Custom option | test |
    And click to button Launch campaign
    And quit browser

  Scenario: Check khi delete CO text File #SB_PRB_BUP_14 #SB_PRB_BUP_6 #SB_PRB_BUP_3 #SB_PRB_BUP_2 #SB_PRB_BUP_1
    Given user login to shopbase dashboard by API
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value |
      | Product title | contains   | CO    |
    And Execute an action bulk update for product "Delete custom options"
      | Value               | Message                      |
      | is equal to>CO text | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "2"
    And quit browser

  Scenario Outline: Check khi delete CO text area in products  #SB_PRB_BUP_7 #SB_PRB_BUD_3 #SB_PRB_BUD_2 #SB_PRB_BUD_1
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title      | Description                                                            |
      | Product CO | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And add new custom option with data
      | Custom option   | Name    | Type       | Label  | Allow the following characters              | Hide option | Add another option |
      | Custom Option 1 | CO text | Text field | Text 1 | Characters,Numbers                          |             | yes                |
      | Custom Option 2 | CO area | Text area  | Text 2 | Characters,Numbers,Special Characters,Emoji |             | no                 |

    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value      |
      | Product title | contains   | Product CO |
    And Execute an action bulk update for product "Delete custom options"
      | Value               | Message                      |
      | is equal to>CO area | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "2"
    When user navigate to "Products>All products" screen
    Given search campaign in dashboard with name "<Product name>"
    And Information of created product "<Product name>" display correctly
      | Custom option |
      | CO area>false |
    Then verify show custom option on store front as "01"
      | KEY | Type | Custom option | Show CO |
      | 01  | Text | Text 2        | false   |
    And quit browser
    Examples:
      | Product name    |
      | Product CO full |

  Scenario: Check khi delete CO Image #SB_PRB_BUP_8
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title          | Description                                                            |
      | Product normal | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value   |
      | Product title | contains   | Product |
    And Execute an action bulk update for product "Delete custom options"
      | Value                | Message                      |
      | is equal to>CO Image | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "1"
    Given open shop on storefront
    And search and select the product "Product CO full"
    Then verify show custom option on store front as "01"
      | KEY | Type  | Custom option | Show CO |
      | 01  | Image | CO Image      | false   |
    And quit browser

  Scenario: Check khi delete CO droplist  #SB_PRB_BUP_10 #SB_PRB_BUP_5
    Given user login to shopbase dashboard by API
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value           |
      | Product title | contains   | Product CO full |
    And Execute an action bulk update for product "Delete custom options"
      | Value                   | Message                      |
      | is equal to>CO droplist | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "1"
    Given open shop on storefront
    And search and select the product "Product CO full"
    Then verify show custom option on store front as "01"
      | KEY | Type     | Custom option | Show CO |
      | 01  | Droplist | CO droplist   | false   |
    And quit browser

  Scenario: Check khi delete CO Radio #SB_PRB_BUP_9
    Given user login to shopbase dashboard by API
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value           |
      | Product title | contains   | Product CO full |
    And Execute an action bulk update for product "Delete custom options"
      | Value                | Message                      |
      | is equal to>CO radio | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "1"
    And quit browser

  Scenario: Check version of order after delete CO Campaign #SB_PRB_BUP_25
    And open shop on storefront
    And search and select the product "Campaign CO"
    And add product with custom option to cart then checkout as "01"
      | KEY | Product     | Custom option            |
      | 01  | Campaign CO | Text,Custom text,test CO |

    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given login to hive-pbase
    And redirect to order Phub detail on hive-pbase
    Then verify version campaign in order is "1"
    And quit browser
