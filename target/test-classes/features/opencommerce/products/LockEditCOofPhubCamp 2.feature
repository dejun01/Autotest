Feature: Product LockEditCOofPhubCamp.feature

  Background: Login dashboard
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  Scenario Outline: Lock to edit custom option of Phub camp #SB_PRO_PD_48 #SB_PRO_PD_49 #SB_PRO_PD_52
    Given Description: "<Testcase>"
    When Search product "product_01" on All product screen
    And Open detail product of product "product_01"
    And verify lock edit custom option as "<KEY>"
      | KEY | CO name         | CO label        | Type           | Value | isDisable |
      | 01  | Custom option 1 | Custom option 1 | Text field     |       | true      |
      | 01  | Custom option 2 | Custom option 2 | Text area      |       | true      |
      | 01  | Custom option 3 | Custom option 3 | Image          |       | true      |
      | 01  | Custom option 4 | Custom option 4 | Picture choice |       | true      |
      | 01  | Custom option 5 | Custom option 5 | Radio buttons  | 1,2   | true      |
      | 01  | Custom option 6 | Custom option 6 | Droplist       | a,b   | true      |
      | 01  | Custom option 7 | Custom option 7 | Checkbox       |       | true      |

    Examples:
      | KEY | Testcase                   |
      | 01  | Lock to edit custom option |

  Scenario Outline: Edit personalization #SB_PRO_PD_54
    Given Description: "<Testcase>"
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product     | Category |
      | 01  | Unisex Tank | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value  | Font | Color | Front or back |
      | 01  | Unisex Tank | Text       |            | Text layer 1 | 210  |       | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And user navigate to "Products>All products" screen
    When Search product "product_02" on All product screen
    And Open detail product of product "product_02"
    And click Create Personalization
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name     |
      | 01  | Text field | Text layer 1 | Custom option 1 |
    And click Save change on Edit personalize
    And user navigate to "Products>All products" screen
    When Search product "product_02" on All product screen
    And Open detail product of product "product_02"
    And verify lock edit custom option as "<KEY>"
      | KEY | CO name         | CO label        | Type       | isDisable |
      | 01  | Custom option 1 | Custom option 1 | Text field | true      |
    Examples:
      | KEY | Testcase                       | Campaign name |
      | 01  | Edit personalization camp phub | product_02    |

  Scenario: verify show Button Create Personalization on product detail of Phub camp #SB_PRO_PD_55 #SB_PRO_PD_56
    When Search product "product_03" on All product screen
    And Open detail product of product "product_03"
    And verify show button Personalization on product detail of Phub camp
      | Name button            |
      | Create personalization |
    And user navigate to "Products>All products" screen
    When Search product "product_04" on All product screen
    And Open detail product of product "product_04"
    And verify show button Personalization on product detail of Phub camp
      | Name button          |
      | Edit personalization |
