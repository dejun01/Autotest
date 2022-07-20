Feature: Add print file from order screen, setting on/off printfile
  #sbase_create_printfile_order
  Scenario: Delete product before execute
    When clear all data
    And  Delete all products by API

  Scenario Outline: Add product have preview before verify
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title          | Description                                                            | Image     | Price |
      | <Product Name> | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | 10    |
    And click button "Create Preview image" in product detail
    And upload image for preview or printfile
      | Upload or Replace | Image           |
      | upload            | ArtworkPhub.png |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer value |
      | 01  | Text       | Test        |
      | 02  | Text       | Test        |
      | 03  | Text       | Test        |
      | 04  | Text       | Test        |
    Then add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Max length |
      | 01  | Text field | Text layer 1 | Custom text | 10         |
      | 02  | Text field | Text layer 1 | Custom text | 10         |
      | 03  | Text field | Text layer 1 | Custom text | 10         |
      | 04  | Text field | Text layer 1 | Custom text | 10         |
    And click button "Save" on toolbar incase product
    And verify image and printfile after create
      | Is preview | Is print file |
      | yes        | no            |
    Examples:
      | KEY | Product Name         |
      | 01  | Product have Order 5 |
      | 02  | Product have Order 6 |
      | 03  | Product have Order 7 |
      | 04  | Product have Order 8 |

  Scenario: Add print file in order,setting off generate PF and turn on toogle popup generate  #SB_PRO_CFP_313 #SB_PRO_ORPF_108 #SB_PRO_ORPF_134 #SB_PRO_ORPF_137 #SB_PRO_ORPF_141 #SB_PRO_ORPF_143 #SB_PRO_ORPF_145 #SB_PRO_ORPF_377 #SB_PRO_CFP_392 #SB_PRO_CFP_352 #SB_PRO_SAT_21 #SB_PRO_SAT_22 #SB_PRO_SAT_34 #SB_PRO_SAT_35 #SB_PRO_SAT_42 #SB_PRO_SAT_44 #SB_PRO_SAT_61
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    Given open shop on storefront
    And search and select the product "Product have Order 5"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option               |
      | 01  | Text,Custom text,Test value |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_1.png |
    And click button "Save" on toolbar incase order
    And Add printfile from order and turn on toogle as "Yes" when setting off generate
    Then verify print file after genarated for line item "Product have Order 5"
      | Is generated | Generate all |
      | true         | Yes          |
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 5"
    And verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify setting generate printfile after create as "false" by API
    And verify custom option after create as "01"
      | KEY | CO name     | CO label    | Type       | Target layer | Max lenght and allow |
      | 01  | Custom text | Custom text | Text field | Text layer 1 | 10                   |
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario: Add print file in order and setting no generate PF  #SB_PRO_CFP_313 #SB_PRO_ORPF_108 #SB_PRO_ORPF_134 #SB_PRO_ORPF_137 #SB_PRO_ORPF_141 #SB_PRO_ORPF_143 #SB_PRO_ORPF_145 #SB_PRO_ORPF_377 #SB_PRO_CFP_392 #SB_PRO_CFP_352
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And open shop on storefront
    And search and select the product "Product have Order 6"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option               |
      | 01  | Text,Custom text,Test value |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_3.png |
    And click button "Save" on toolbar incase order
    And Add printfile from order and turn on toogle as "No" when setting off generate
    Then verify print file after genarated for line item "Product have Order 6"
      | Is generated | Generate all |
      | false        | No           |
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 6"
    Then verify image and printfile after create
      | Is preview | Is print file |
      | yes        | yes           |
    And verify custom option after create as "01"
      | KEY | CO name     | CO label    | Type       | Target layer | Max lenght and allow |
      | 01  | Custom text | Custom text | Text field | Text layer 1 | 10                   |
    And verify setting generate printfile after create as "true" by API
    And quit browser

  Scenario: Add print file in order and on generate PF #SB_PRO_CFP_312 #SB_PRO_ORPF_108 #SB_PRO_ORPF_134 #SB_PRO_ORPF_137 #SB_PRO_ORPF_141 #SB_PRO_ORPF_143 #SB_PRO_ORPF_145 #SB_PRO_ORPF_377 #SB_PRO_CFP_395 #SB_PRO_CFP_352 #SB_PRO_SAT_20 #SB_PRO_SAT_23 #SB_PRO_SAT_33 #SB_PRO_SAT_36 #SB_PRO_SAT_41 #SB_PRO_SAT_43 #SB_PRO_SAT_47 ##SB_PRO_SAT_60
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    And open shop on storefront
    And search and select the product "Product have Order 7"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option               |
      | 01  | Text,Custom text,Test value |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_4.png |
    And click button "Save" on toolbar incase order
    Then Add prinfile from order when setting on generate
      | PrintFile Type     | Status generate |
      | Product have Order | Yes             |
    And wait 5 second
    Then verify print file after genarated for line item "Product have Order 7"
      | Is generated | Generate all |
      | true         | Yes          |
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 7"
    And verify custom option after create as "01"
      | KEY | CO name     | CO label    | Type       | Target layer | Max lenght and allow |
      | 01  | Custom text | Custom text | Text field | Text layer 1 | 10                   |
    And verify setting generate printfile after create as "false" by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario: Add print file in order,setting on generate and genereate only this order #SB_PRO_ORPF_108 #SB_PRO_ORPF_134 #SB_PRO_ORPF_137 #SB_PRO_ORPF_141 #SB_PRO_ORPF_143 #SB_PRO_ORPF_145 #SB_PRO_ORPF_377 #SB_PRO_CFP_395 #SB_PRO_CFP_352 #SB_PRO_SAT_SB_PRO_SAT_49 #SB_PRO_SAT_53
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    And open shop on storefront
    And search and select the product "Product have Order 8"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option               |
      | 01  | Text,Custom text,Test value |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_4.png |
    And click button "Save" on toolbar incase order
    Then Add prinfile from order when setting on generate
      | PrintFile Type     | Status generate |
      | Product have Order | No              |
    And wait 5 second
    Then verify print file after genarated for line item "Product have Order 8"
      | Is generated | Generate all |
      | true         | No           |
    And user navigate to "Products>All products" screen
    And open product details in dashboard or editor campaign "Product have Order 8"
    And verify custom option after create as "01"
      | KEY | CO name     | CO label    | Type       | Target layer | Max lenght and allow |
      | 01  | Custom text | Custom text | Text field | Text layer 1 | 10                   |
    And verify setting generate printfile after create as "false" by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario: Add product custom option only before verify
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title           | Description                                                            | Image     | Price |
      | Product CO Only | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | 10    |
    And add new custom option with data
      | Custom option   | Name | Type           | Label          | Allow the following characters | Values                 | Hide option | Add another option |
      | Custom Option 1 | C01  | Radio buttons  | Radio          |                                | 1,2                    |             | yes                |
      | Custom Option 2 | C02  | Droplist       | Drop           |                                | a,b                    |             | yes                |
      | Custom Option 3 | C03  | Picture choice | Picture Choice |                                | customer_signature.jpg |             | yes                |
      | Custom Option 4 | C04  | Text field     | Text 2         | Characters,Numbers,Emoji       |                        |             | yes                |
      | Custom Option 5 | C05  | Image          | Image 1        |                                |                        |             | yes                |
      | Custom Option 6 | C06  | Checkbox       | Checkbox       |                                | Hoa,Co                 |             | no                 |
    And quit browser

  Scenario: Verify custom option only on add print file order #SB_PRO_ORPF_114 #SB_PRO_ORPF_116 #SB_PRO_ORPF_118 #SB_PRO_ORPF_119 #SB_PRO_ORPF_128 #SB_PRO_ORPF_127 #SB_PRO_ORPF_129 #SB_PRO_ORPF_130 #SB_PRO_ORPF_131 #SB_PRO_ORPF_132 #SB_PRO_ORPF_134
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    And open shop on storefront
    And search and select the product "Product CO Only"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option                               |
      | 01  | Text,Text 2,Test 12345;Image,Image,icon.png |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    Then verify screen Printfile
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_2.png |
    And add layer personalize as"01"
      | KEY | Layer type | Layer name    | Layer value | Font | Color | Location | Rotate | Opacity |
      | 01  | Text       | Text layer 1  | Test        | 20   |       | 210>200  | 30     | 100     |
      | 01  | Text       | Text layer 2  | Test        | 20   |       | 210>200  | 30     | 100     |
      | 01  | Text       | Text layer 3  | Test        | 20   |       | 210>200  | 30     | 100     |
      | 01  | Image      | Image Layer 1 | BD_3.png    | 200  |       | 110>132  |        | 80      |
      | 01  | Image      | Image Layer 2 | BD_2.png    | 200  |       | 110>132  |        | 80      |
    And click button expand on Custom Option
    And verify lock edit custom option as "<KEY>"
      | KEY | CO name | CO label       | Type           | Value | isDisable |
      | 01  | CO1     | Radio          | Radio buttons  | 1,2   | true      |
      | 01  | C02     | Drop           | Droplist       |       | true      |
      | 01  | CO3     | Picture Choice | Picture choice |       | true      |
      | 01  | CO4     | Text 2         | Text field     |       | true      |
      | 01  | CO5     | Image 1        | Image          |       | true      |
      | 01  | CO6     | Checkbox       | Checkbox       | a,b   | true      |
    And edit custom option for personalize product
      | Layer name   | Custom name    |
      | Text layer 1 | Radio          |
      | Text layer 2 | Drop           |
      | BD_2         | Picture Choice |
      | Text layer 3 | Text 2         |
      | BD_3         | Image 1        |
    And click button "Save" on toolbar incase order
    And click button create generate printfile
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "false"
    And quit browser

  Scenario: Check validate upload image on Add print file Screen #SB_PRO_ORPF_115
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title     | Description                                                            | Image     | Price |
      | Product 1 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | 10    |
    And add new custom option with data
      | Custom option   | Name | Type       | Label  | Allow the following characters | Values | Hide option | Add another option |
      | Custom Option 1 | C0   | Text field | Text 2 | Characters,Numbers,Emoji       |        |             | no                 |
    And open product details on Storefront from product detail in dashboard
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option          |
      | 01  | Text,Text 2,Test value |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And click add print file on order detail
    When upload image for preview or printfile
      | Upload or Replace | Image           | Error image                                             |
      | upload            | image/120MB.png | This file is too large. The allowed size is under 60 MB |
      | upload            | image/39.png    |                                                         |
    And quit browser

  Scenario: Create print file with case product and verify button Add print file in order detail #SB_PRO_ORPF_112
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And setting generate print file as "true"
    And wait 5 second
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title             | Description                                                            | Image     |
      | Product printfile | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg |
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
    And open product details on Storefront from product detail in dashboard
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option        |
      | 01  | Image,Image,icon.png |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    And Access to order detail by order ID
    And verify button Add print file
