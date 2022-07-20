Feature: Product conditional
#sbase_dashboard_product
  Background: Login dashboard
    Given clear all data

  Scenario: Add product have conditional logic  #SB_PRO_DFPP_860 #SB_PRO_DFPP_740 #SB_PRO_DFPP_861 #SB_PRO_DFPP_863 #SB_PRO_DFPP_865 #SB_PRO_DFPP_867 #SB_PRO_DFPP_869 #SB_PRO_DFPP_870 #SB_PRO_DFPP_873 #SB_PRO_DFPP_874 #SB_PRO_DFPP_875 #SB_PRO_DFPP_877 #SB_PRO_DFPP_878 #SB_PRO_DFPP_879 #SB_PRO_DFPP_881 #SB_PRO_DFPP_883 #SB_PRO_DFPP_885 #SB_PRO_DFPP_886 #SB_PRO_DFPP_887 #SB_PRO_DFPP_772 #SB_PRO_DFPP_774
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title         | Description                                                            |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And add new custom option with data
      | Custom option   | Name | Type           | Label          | Allow the following characters | Values                 | Hide option | Add another option |
      | Custom Option 1 | C01  | Radio buttons  | Radio          |                                | 1,2                    |             | yes                |
      | Custom Option 2 | C02  | Droplist       | Drop           |                                | a,b                    |             | yes                |
      | Custom Option 3 | C03  | Picture choice | Picture Choice |                                | customer_signature.jpg |             | yes                |
      | Custom Option 4 | C04  | Text area      | Text 1         | Characters                     |                        | yes         | yes                |
      | Custom Option 5 | C05  | Text field     | Text 2         | Characters,Numbers,Emoji       |                        |             | yes                |
      | Custom Option 6 | C06  | Image          | Image 1        |                                |                        |             | yes                |
      | Custom Option 7 | C07  | Checkbox       | Checkbox       |                                | Hoa,Co                 |             | no                 |
    And add condtional logic
      | Custom option | Condition   | Value              | Show value  |
      | C01           | is equal to | 1                  | C02         |
      | C02           | is equal to | a                  | C03         |
      | C03           | is equal to | customer_signature | C04>C05>C06 |
    And verify conditional logic on custom option product
      | CustomName | CustomName Conditional | Show conditional logic |
      | C01        |                        | false                  |
      | C02        | C01                    | true                   |
      | C03        | C02                    | true                   |
      | C04        | C03                    | true                   |
      | C05        | C03                    | true                   |
      | C06        | C03                    | true                   |
    And quit browser

  Scenario: Add product have create printfile and conditional logic #SB_PRO_DFPP_860 #SB_PRO_DFPP_740 #SB_PRO_DFPP_859
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And click button "Create Print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And add layer personalize as"01"
      | KEY | Layer type | Layer name | Layer value | Font | Color | Location | Rotate | Opacity |
      | 01  | Text       |            | Test        | 20   |       | 30>40    | 30     | 100     |
      | 01  | Image      |            | BD_1.png    |      |       | 60>100   |        |         |
    And add custom options for campaign
    And setup custom option for product in create printfile as "01"
      | KEY | Layer type     | Layer name   | Custom name     | Font    | Placeholder | Max length | Default value | Text       | Value     |
      | 01  | Radio          |              | Custom Radio    |         |             |            |               |            | a > b     |
      | 01  | Picture choice | BD_1         | Custom Picture  |         |             |            |               |            | Logo1.jpg |
      | 01  | Droplist       |              | Custom Droplist |         |             |            |               |            | Nam > Anh |
      | 01  | Text field     | Text layer 1 | Custom Text     | Raleway | Input text  | 10         | text          |            |           |
      | 01  | Text area      | Text layer 1 | Custom area     | Raleway | Input text  | 10         | area          |            |           |
      | 01  | Image          | BD_1         | Custom Image    |         |             |            |               | Test image |           |
      | 01  | Checkbox       |              | Custom checkbox |         |             |            |               |            | 1,2       |
    And add conditional logic as "01"
      | KEY | Option          | Condition         | Then show                                            |
      | 01  | Custom Radio    | is equal to>a     | Custom Picture                                       |
      | 01  | Custom Picture  | is equal to>Logo1 | Custom Droplist                                      |
      | 01  | Custom Droplist | is equal to>Nam   | Custom Text;Custom area;Custom Image;Custom checkbox |
    And click button "Save" on toolbar
    And click button create generate printfile
    Then verify image and printfile after create
      | Is preview | Is print file |
      | no         | yes           |
    And verify conditional logic on custom option product
      | CustomName      | CustomName Conditional | Show conditional logic |
      | Custom Radio    |                        | false                  |
      | Custom Picture  | Custom Radio           | true                   |
      | Custom Droplist | Custom Picture         | true                   |
      | Custom Text     | Custom Droplist        | true                   |
      | Custom area     | Custom Droplist        | true                   |
      | Custom Image    | Custom Droplist        | true                   |
      | Custom checkbox | Custom Droplist        | true                   |
    And quit browser


  Scenario: verify product duplicate and add conditional logic #SB_PRO_DFPP_740 #SB_PRO_DFPP_900 #SB_PRO_DFPP_901 #SB_PRO_DFPP_872
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Search product "Product Duplicate" on All product screen
    And Open detail product of product "Product Duplicate"
    And Duplicate product
    Then Information of created product "Copy of Product Duplicate" display correctly
      | Description                                                            |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And verify conditional logic on custom option product
      | CustomName | CustomName Conditional | Show conditional logic |
      | C01        |                        | false                  |
      | C02        | C01                    | true                   |
      | C03        | C02                    | true                   |
      | C04        | C03                    | true                   |
      | C05        | C03                    | true                   |
      | C06        | C03                    | true                   |
    And Duplicate product
    Then Information of created product "Copy of Copy of Product Duplicate" display correctly
      | Description                                                            |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And verify conditional logic on custom option product
      | CustomName | CustomName Conditional | Show conditional logic |
      | C01        |                        | false                  |
      | C02        | C01                    | true                   |
      | C03        | C02                    | true                   |
      | C04        | C03                    | true                   |
      | C05        | C03                    | true                   |
      | C06        | C03                    | true                   |
    And quit browser


  Scenario: verify Duplicate product have add conditional logic and create printfile #SB_PRO_DFPP_741 #SB_PRO_DFPP_740 #SB_PRO_DFPP_900 #SB_PRO_DFPP_901
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    When Search product "Product Duplicate printfile" on All product screen
    And Open detail product of product "Product Duplicate printfile"
    And verify conditional logic on custom option product
      | CustomName | CustomName Conditional | Show conditional logic |
      | C01        |                        | false                  |
      | C02        | C01                    | true                   |
      | C03        | C02                    | true                   |
      | C04        | C03                    | true                   |
      | C05        | C03                    | true                   |
      | C06        | C03                    | true                   |

    And Duplicate product
    Then Information of created product "Copy of Product Duplicate printfile" display correctly
      | Description                                                            |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And verify conditional logic on custom option product
      | CustomName | CustomName Conditional | Show conditional logic |
      | C01        |                        | false                  |
      | C02        | C01                    | true                   |
      | C03        | C02                    | true                   |
      | C04        | C03                    | true                   |
      | C05        | C03                    | true                   |
      | C06        | C03                    | true                   |
    And quit browser


