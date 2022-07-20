@dashboardProduct @dashboard
  #sbase_create_printfile_product
Feature: Add product have print file and setting on/off generate

  Scenario: Delete product before execute
    When clear all data
    And  Delete multi products by API

  Scenario: Create print file with case product no order and setting on generate #SB_PRO_CFP_302 #SB_PRO_CFP_304 #SB_PRO_SBP_IEPF_63 #SB_PRO_SBP_IEPF_83
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    And wait 5 second
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg |
    And click button "Create Print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image     |
      | upload            | shirt.jpg |
    And add layer personalize as"01"
      | KEY | Layer type | Layer value | Location |
      | 01  | Image      | 3D_3.png    | 60>100   |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "01"
      | KEY | Layer type | Layer name | Custom name  | Text       |
      | 01  | Image      | 3D_3       | Custom Image | Test image |
    And click button "Save" on toolbar incase product
    Then Add prinfile when setting on generate print file
      | PrintFile Type   |
      | Product No Order |
    And click save product
    And verify image and printfile after create
      | Is preview | Is print file |
      | no         | yes           |
    And verify custom option after create as "01"
      | KEY | CO name      | CO label     | Type  | Target layer |
      | 01  | Custom Image | Custom Image | Image | 3D_3         |
    And verify setting generate printfile after create as "false" by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario Outline: Add product have preview before verify #SB_PRO_SBP_IEPF_65 #SB_PRO_SBP_IEPF_82
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title          | Description                                                            | Image     | Price |
      | <Product Name> | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | 10    |
    And click button "Create Preview image" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | 3D_5.png |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer value |
      | 01  | Text       | Test 1      |
      | 02  | Text       | Test 2      |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name   | Font    |
      | 01  | Text field | Text layer 1 | Custom text 1 | Raleway |
      | 02  | Text field | Text layer 1 | Custom text 2 | Raleway |
    And click button "Save" on toolbar incase product
    Then verify image and printfile after create
      | Is preview | Is print file |
      | yes        | no            |
    And quit browser
    Examples:
      | KEY | Product Name         |
      | 01  | Product have Order 1 |
      | 02  | Product have Order 2 |

  Scenario: Create print file with case product have order and setting on generate  #SB_PRO_CFP_307 #SB_PRO_CFP_318 #SB_PRO_CFP_319
    And user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    Given open shop on storefront
    And search and select the product "Product have Order 1"
    And add product with custom option to cart then checkout as "01" other
      | KEY | Custom option           |
      | 01  | Text,Custom text 1,Text |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 1"
    And click button "Create print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_2.png |
    And add layer personalize as"01"
      | KEY | Layer type | Layer value | Location |
      | 01  | Image      | 3D_3.png    | 60>100   |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "01"
      | KEY | Layer type | Layer name | Custom name    |
      | 01  | Image      | 3D_3       | Custom Image 1 |
    And click button "Save" on toolbar incase product
    Then Add prinfile when setting on generate print file
      | PrintFile Type     | Status generate |
      | Product have Order | Yes             |
    And verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify setting generate printfile after create as "false" by API
    And verify custom option after create as "01"
      | KEY | CO name        | CO label       | Type       | Target layer |
      | 01  | Custom Image 1 | Custom Image 1 | Image      | 3D_3         |
      | 01  | Custom text 1  | Custom text 1  | Text field | Text layer 1 |
    And Access to order detail by order ID
    And verify print file after genarated for line item "Product have Order 1"
      | Is generated | Generate all |
      | true         | Yes          |
    And user navigate to "Online Store>Preferences" screen
    And  setting generate print file as "false"
    And quit browser

  Scenario: Create print file with case product have order,setting on generate and no generate #SB_PRO_CFP_307 #SB_PRO_CFP_318 #SB_PRO_CFP_319
    And user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    Given open shop on storefront
    And search and select the product "Product have Order 2"
    And add product with custom option to cart then checkout as "02" other
      | KEY | Custom option           |
      | 02  | Text,Custom text 2,Text |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 2"
    And click button "Create print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_2.png |
    And add layer personalize as"02"
      | KEY | Layer type | Layer value | Location |
      | 02  | Image      | 3D_4.png    | 60>100   |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "02"
      | KEY | Layer type | Layer name | Custom name    |
      | 02  | Image      | 3D_4       | Custom Image 2 |
    And click button "Save" on toolbar incase product
    Then Add prinfile when setting on generate print file
      | PrintFile Type     | Status generate |
      | Product have Order | No              |
    And verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify setting generate printfile after create as "false" by API
    And verify custom option after create as "02"
      | KEY | CO name        | CO label       | Type       | Target layer |
      | 02  | Custom Image 2 | Custom Image 2 | Image      | 3D_4         |
      | 02  | Custom text 2  | Custom text 2  | Text field | Text layer 1 |
    And Access to order detail by order ID
    And verify print file after genarated for line item "Product have Order 2"
      | Is generated | Generate all |
      | false        | No           |
    And user navigate to "Online Store>Preferences" screen
    And  setting generate print file as "false"
    And quit browser

  Scenario: Create print file with case product no order,setting off generate and turn on toggle #SB_PRO_SBP_IEPF_77 #SB_PRO_SBP_IEPF_78 #SB_PRO_SBP_IEPF_79 #SB_PRO_SBP_IEPF_84
    Given Description: "Turn off generate printfile on popup"
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And wait 2 second
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg |
    And click button "Create Print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_1.png |
    And add layer personalize as"01"
      | KEY | Layer type | Layer value | Font | Location | Rotate | Opacity |
      | 01  | Text       | Test        | 20   | 30>40    | 30     | 100     |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "01"
      | KEY | Layer type | Layer name   | Custom name | Font    | Placeholder | Max length | Default value |
      | 01  | Text field | Text layer 1 | Custom text | Raleway | Input text  | 10         | text          |
    And click button "Save" on toolbar incase product
    Then config generate printfile after save printfile
      | Turn on toggle | Product has order |
      | Yes            | No                |
    And verify image and printfile after create
      | Is preview | Is print file |
      | no         | yes           |
    And verify setting generate printfile after create as "false" by API
    And verify custom option after create as "01"
      | KEY | CO name     | CO label    | Type       | Target layer | Max lenght and allow |
      | 01  | Custom text | Custom text | Text field | Text layer 1 | 16                   |
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario: Create print file with case product no order and setting off generate #SB_PRO_CFP_305
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And wait 2 second
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg |
    And click button "Create Print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_5.png |
    And add layer personalize as"02"
      | KEY | Layer type | Layer value |
      | 02  | Image      | artwork.png |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "02"
      | KEY | Layer type | Layer name | Custom name  | Text       |
      | 02  | Image      | artwork    | Custom Image | Test image |
    And click button "Save" on toolbar incase product
    Then config generate printfile after save printfile
      | Turn on toggle | Product has order |
      | Yes            | No                |
    And verify image and printfile after create
      | Is preview | Is print file |
      | no         | yes           |
    And verify setting generate printfile after create as "false" by API
    And verify custom option after create as "02"
      | KEY | CO name      | CO label     | Type  | Target layer |
      | 02  | Custom Image | Custom Image | Image | artwork      |
    And quit browser

  Scenario Outline: Add product have preview before verify incase default setting off generate
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Price |
      | <ProductName> | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | 20    |
    And click button "Create Preview image" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | 3D_5.png |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer value |
      | 01  | Text       | Test        |
      | 02  | Image      | icon.png    |
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name  |
      | 01  | Text field | Text layer 1 | Custom text  |
      | 02  | Image      | icon         | Custom Image |
    And click button "Save" on toolbar incase product
    Then verify image and printfile after create
      | Is preview | Is print file |
      | yes        | no            |
    And quit browser
    Examples:
      | KEY | ProductName          |
      | 01  | Product have Order 3 |
      | 02  | Product have Order 4 |

  Scenario: Create print file with case product have order,setting off generate and turn on generate on popup #SB_PRO_CFP_310 #SB_PRO_CFP_318 #SB_PRO_SBP_IEPF_85 #SB_PRO_SBP_IEPF_86
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And wait 5 second
    Given open shop on storefront
    And search and select the product "Product have Order 3"
    And add product with custom option to cart then checkout as "01" other
      | KEY | Custom option               |
      | 01  | Text,Custom text,Test value |
    And checkout by Stripe successfully
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 3"
    And click button "Create print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image      |
      | upload            | AW3D_1.png |
    And add layer personalize as"01"
      | KEY | Layer type | Layer value   |
      | 01  | Image      | Campaign1.png |
    Then setup custom option
      | Layer type | Layer name | Custom name  |
      | Image      | Campaign1  | Custom Image |
    And click button "Save" on toolbar incase product

    Then config generate printfile after save printfile
      | Turn on toggle | Product has order |
      | Yes            | Yes               |
    And verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify setting generate printfile after create as "false" by API
    And verify custom option after create as "01"
      | KEY | CO name      | CO label     | Type  | Target layer |
      | 01  | Custom Image | Custom Image | Image | Campaign1    |
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And verify print file after genarated for line item "Product have Order 3"
      | Is generated | Generate all |
      | true         | Yes          |
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario: Create print file with case product have order and setting off generate  #SB_PRO_CFP_306 #SB_PRO_CFP_318
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And wait 5 second
    Given open shop on storefront
    And search and select the product "Product have Order 4"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option                 | Is crop image |
      | 01  | Image,Custom Image,AW3D_0.png | true          |
    And checkout by Stripe successfully
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 4"
    And click button "Create print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image      |
      | upload            | AW3D_1.png |
    And add layer personalize as"01"
      | KEY | Layer type | Layer value |
      | 01  | Text       | Test        |
    Then setup custom option
      | Layer type | Layer name   | Custom name | Font              |
      | Text field | Text layer 1 | Custom text | Lilly Mae Regular |
    And click button "Save" on toolbar incase product
    Then config generate printfile after save printfile
      | Turn on toggle | Product has order |
      | No             | Yes               |
    And verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify setting generate printfile after create as "true" by API
    And verify custom option after create as "01"
      | KEY | CO name     | CO label    | Type       | Target layer |
      | 01  | Custom text | Custom text | Text field | Text layer 1 |
    And Access to order detail by order ID
    And verify print file no genarate for line item "Product have Order 4"
      | Is generated |
      | true         |
    And quit browser


