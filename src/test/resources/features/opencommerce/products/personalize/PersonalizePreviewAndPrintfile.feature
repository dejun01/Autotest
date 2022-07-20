@dashboardProduct @dashboard
  #sbase_create_personalize_product
Feature: Personalize Product Dashboard

  Background: Login dashboard
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Image     |
      | @NameProduct@ | Logo1.jpg |


  Scenario Outline: Create preview and verify sync layer printfile #SB_PRO_SBP_IEPF_64 #SB_PRO_SBP_IEPF_66 #SB_PRO_SBP_IEPF_67 #SB_PRO_SBP_IEPF_71 #SB_PRO_SBP_IEPF_72 #SB_PRO_SBP_IEPF_92 #SB_PRO_SBP_IEPF_93 #SB_PRO_SBP_IEPF_94 #SB_PRO_SBP_IEPF_95 #SB_PRO_SBP_IEPF_96 #SB_PRO_SBP_IEPF_99 #SB_PRO_SBP_IEPF_113 #SB_PRO_SBP_IEPF_114
    And click button "Create Preview" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer value  | Font | Location | Opacity | Size layer |
      | 01  | Text       | Top left     | 20   | 0>0      | 100     | 120>50     |
      | 01  | Text       | Top right    | 20   | 150>0    | 70      | 120>50     |
      | 02  | Text       | Buttom left  | 30   | 0>150    | 80      | 120>50     |
      | 02  | Text       | Buttom right | 30   | 150>0    | 90      | 120>50     |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name   |
      | 01  | Text field | Text layer 1 | Custom text 1 |
      | 01  | Text field | Text layer 2 | Custom text 2 |
      | 02  | Text field | Text layer 1 | Custom text 1 |
      | 02  | Text field | Text layer 2 | Custom text 2 |
    And click button "Next, create Print file" on toolbar
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And verify quantity of layer in editor
    And verify layer in personalize product as "<KEY>"
      | KEY | Layer name   | Size | Location | Opacity |
      | 01  | Text layer 1 | 20   | 0>0      | 100     |
      | 01  | Text layer 2 | 20   | 150>0    | 70      |
      | 02  | Text layer 1 | 30   | 0>150    | 80      |
      | 02  | Text layer 2 | 30   | 150>0    | 90      |
    And edit detail layer as "<KEY>"
      | KEY | Layer name   | Font | Location | Rotate | Opacity |
      | 01  | Text layer 1 | 30   | 20>50    | 30     | 80      |
      | 02  | Text layer 1 | 40   | 0>20     | 30     | 100     |
    And click button "Update Preview" on toolbar
    And verify layer in personalize product as "<KEY>"
      | KEY | Layer name   | Size | Location | Rotate | Opacity |
      | 01  | Text layer 1 | 20   | 0>0      | 20     | 80      |
      | 02  | Text layer 1 | 30   | 0>150    | 30     | 100     |
    And click button "Save" on toolbar
    And click save product
    Then verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify custom option after create as "<KEY>"
      | KEY | CO name       | CO label      | Type       | Target layer |
      | 01  | Custom text 1 | Custom text 1 | Text field | Text layer 1 |
      | 01  | Custom text 2 | Custom text 2 | Text field | Text layer 2 |
      | 02  | Custom text 1 | Custom text 1 | Text field | Text layer 1 |
      | 02  | Custom text 2 | Custom text 2 | Text field | Text layer 2 |
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type | Custom name | Value Input |
      | 01  | Text | Custom text | Test 1      |
      | 01  | Text | Custom text | Test 2      |
      | 02  | Text | Custom text | Test 3      |
      | 02  | Text | Custom text | Test 4      |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY |
      | 01  |
      | 02  |


  Scenario Outline: Create printfile and verify sync layer preview #SB_PRO_SBP_IEPF_68 #SB_PRO_SBP_IEPF_69 #SB_PRO_SBP_IEPF_70 #SB_PRO_SBP_IEPF_73 #SB_PRO_SBP_IEPF_74 #SB_PRO_SBP_IEPF_75 #SB_PRO_SBP_IEPF_76 #SB_PRO_SBP_IEPF_89 #SB_PRO_SBP_IEPF_90 #SB_PRO_SBP_IEPF_91 #SB_PRO_SBP_IEPF_100 #SB_PRO_SBP_IEPF_101 #SB_PRO_SBP_IEPF_102 #SB_PRO_SBP_IEPF_103 #SB_PRO_SBP_IEPF_115
    And click button "Create Print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer value  | Font | Location | Opacity | Size layer |
      | 01  | Text       | Top left     | 20   | 0>0      | 100     | 120>50     |
      | 01  | Text       | Top right    | 20   | 150>0    | 70      | 120>50     |
      | 02  | Text       | Buttom left  | 30   | 0>150    | 80      | 120>50     |
      | 02  | Text       | Buttom right | 30   | 150>0    | 90      | 120>50     |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name   |
      | 01  | Text field | Text layer 1 | Custom text 1 |
      | 01  | Text field | Text layer 2 | Custom text 2 |
      | 02  | Text field | Text layer 1 | Custom text 1 |
      | 02  | Text field | Text layer 2 | Custom text 2 |
    And click button "Next, create Preview" on toolbar
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And verify quantity of layer in editor
    And verify layer in personalize product as "<KEY>"
      | KEY | Layer name   | Size | Location | Opacity |
      | 01  | Text layer 1 | 20   | 0>0      | 100     |
      | 01  | Text layer 2 | 20   | 150>0    | 70      |
      | 02  | Text layer 1 | 30   | 0>150    | 80      |
      | 02  | Text layer 2 | 30   | 150>0    | 90      |

    And click button "Save" on toolbar
    Then verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify custom option after create as "<KEY>"
      | KEY | CO name       | CO label      | Type       | Target layer |
      | 01  | Custom text 1 | Custom text 1 | Text field | Text layer 1 |
      | 01  | Custom text 2 | Custom text 2 | Text field | Text layer 2 |
      | 02  | Custom text 1 | Custom text 1 | Text field | Text layer 1 |
      | 02  | Custom text 2 | Custom text 2 | Text field | Text layer 2 |
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type | Custom name | Value Input |
      | 01  | Text | Custom text | Test 1      |
      | 01  | Text | Custom text | Test 2      |
      | 02  | Text | Custom text | Test 3      |
      | 02  | Text | Custom text | Test 4      |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY |
      | 01  |
      | 02  |


  Scenario Outline: Verify sync layer frint file to preview in order #SB_PRO_SBP_IEPF_106 #SB_PRO_SBP_IEPF_107 #SB_PRO_SBP_IEPF_108 #SB_PRO_SBP_IEPF_109 #SB_PRO_SBP_IEPF_110
    And add new custom option with data
      | Custom option   | Name | Label | Allow the following characters |
      | Custom Option 1 | Text | Text  | Characters                     |
    And open product details on Storefront from product detail in dashboard
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option  |
      | 01  | Text,Text,Test |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_4.png |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer value  | Font | Location | Opacity |
      | 01  | Text       | Top left     | 40   | 30>30    | 100     |
      | 02  | Text       | Top right    | 40   | 190>30   | 70      |
      | 03  | Text       | Buttom left  | 30   | 20>320   | 80      |
      | 04  | Text       | Buttom right | 30   | 180>320  | 90      |
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer name   |
      | 01  | Text layer 1 |
      | 02  | Text layer 1 |
      | 03  | Text layer 1 |
      | 04  | Text layer 1 |
    And click button "Next, create Preview" on toolbar
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And verify quantity of layer in editor
    And verify layer in personalize product as "<KEY>"
      | KEY | Layer name   | Size | Location | Rotate | Opacity |
      | 01  | Text layer 1 | 40   | 30>30    | 30     | 100     |
      | 02  | Text layer 1 | 40   | 190>30   | 30     | 70      |
      | 03  | Text layer 1 | 30   | 20>320   | 30     | 80      |
      | 04  | Text layer 1 | 30   | 180>320  | 50     | 90      |
    And quit browser

    Examples:
      | KEY |
      | 01  |
      | 02  |
      | 03  |
      | 04  |