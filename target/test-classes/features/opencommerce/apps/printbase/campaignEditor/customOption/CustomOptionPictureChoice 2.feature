Feature: Custom option picture choice
#EVM: pbase_custom_option_picture_choice
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Delete all clipart folder
    When Delete all folders by API

  Scenario Outline: Create clipart folder #SB_PRB_ECP_698 #SB_PRB_ECP_734 #SB_PRO_DFPP_817 #SB_PRB_ECP_663 #SB_PRB_ECP_665 #SB_PRB_ECP_732
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY   | Product        | Category |
      | <KEY> | Ladies T-shirt | Apparel  |
    And add custom options for campaign
    And setup custom option picture choice
      | KEY   | Layer type     |
      | <KEY> | Picture choice |
    And Click Add a clipart folder link as "<KEY>"
      | KEY   | Layer type     |
      | <KEY> | Picture choice |
    And create info clipart folder as "<KEY>"
      | KEY | Folder           | Group   | Image         |
      | 01  | ClipartFolder001 | T-shirt | phub/BD_2.png |
      | 02  | ClipartFolder002 |         | phub/BD_2.png |
      | 03  | ClipartFolder003 | T-shirt | phub/BD_2.png |
      | 04  | ClipartFolder004 | T-shirt | phub/BD_2.png |
      | 05  | ClipartFolder005 | T-shirt | phub/BD_2.png |
      | 06  | ClipartFolder006 | T-shirt | phub/BD_2.png |
    And edit info clipart folder as "<KEY>"
      | KEY | CurrenImage | newNameImage | CurentThumbnail | Image | newThumbnail  |
      | 03  | BD_8        | image        |                 |       |               |
      | 04  | BD_8        | image        | phub/BD_4.png   | image |               |
      | 05  | BD_8        | image        | phub/BD_4.png   | image |               |
      | 06  | BD_8        | image        | phub/BD_4.png   | image | phub/BD_1.png |
    And delete info clipart folder as "<KEY>"
      | KEY | ImageThumbnail | ImageClipart |
      | 05  | image          |              |
    And click Save changes button as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify infomation clipart folder as "<KEY>"
      | KEY | Name             | Number image |
      | 01  | ClipartFolder001 | 1            |
      | 02  | ClipartFolder002 | 1            |
      | 03  | ClipartFolder003 | 1            |
      | 04  | ClipartFolder004 | 1            |
      | 05  | ClipartFolder005 | 1            |
      | 06  | ClipartFolder006 | 1            |
    And quit browser
    Examples:
      | KEY | Testcase                                          |
      | 01  | Create clipart folder with all field valid        |
      | 02  | Create clipart folder with require field          |
      | 03  | Create clipart folder with change name image      |
      | 04  | Create clipart folder with upload image thumbnail |
      | 05  | Create clipart folder with delete image thumbnail |
      | 06  | Create clipart folder with change image Thumbnail |


  Scenario Outline: Edit clipart folder #SB_PRB_ECP_743 #SB_PRB_ECP_734
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY   | Product        | Category |
      | <KEY> | Ladies T-shirt | Apparel  |
    And add custom options for campaign
    And setup custom option picture choice
      | KEY   | Layer type     |
      | <KEY> | Picture choice |
    And click to Add a clipart folder
#    And click delete image
    And create info clipart folder as "<KEY>"
      | KEY | Folder          | Group              | Image         |
      | 01  | ClipartFolder01 | Ladies T-shirt     | phub/BD_1.png |
      | 02  | ClipartFolder02 |                    | phub/BD_8.png |
      | 03  | ClipartFolder03 | Low Top Shoes      | phub/BD_8.png |
      | 04  | ClipartFolder04 | Leather Boots New  | phub/BD_8.png |
      | 05  | ClipartFolder05 | Skinny Tumbler     | phub/BD_8.png |
      | 06  | ClipartFolder06 | iPhone 8 Plus Case | phub/BD_8.png |
    And edit info clipart folder as "<KEY>"
      | KEY | CurrenImage | newNameImage | CurentThumbnail | Image | newThumbnail  |
      | 01  | BD_1        | image        | phub/BD_4.png   |       |               |
      | 02  | BD_8        | image        | phub/BD_4.png   | image |               |
      | 03  | BD_8        | image        |                 |       |               |
      | 04  | BD_8        | image        | phub/BD_4.png   | image |               |
      | 05  | BD_8        | image        | phub/BD_4.png   | image |               |
      | 06  | BD_8        | image        | phub/BD_4.png   | image | phub/BD_1.png |
    And delete info clipart folder as "<KEY>"
      | KEY | ImageThumbnail |
      | 05  | image          |
    And click Save changes button as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify infomation clipart folder as "<KEY>"
      | KEY | Name            | Number image |
      | 01  | ClipartFolder01 | 1            |
      | 02  | ClipartFolder02 | 1            |
      | 03  | ClipartFolder03 | 1            |
      | 04  | ClipartFolder04 | 1            |
      | 05  | ClipartFolder05 | 1            |
      | 06  | ClipartFolder06 | 1            |
    And quit browser
    Examples:
      | KEY | Testcase                                        |
      | 01  | Edit clipart folder with all field valid        |
      | 02  | Edit clipart folder with require field          |
      | 03  | Edit clipart folder with change name image      |
      | 04  | Edit clipart folder with upload image thumbnail |
      | 05  | Edit clipart folder with delete image thumbnail |
      | 06  | Edit clipart folder with change image Thumbnail |

  Scenario Outline: Custom option picture choice #SB_PRB_ECP_744 #SB_PRB_ECP_743 #SB_PRB_ECP_741 #SB_PRB_ECP_740 #SB_PRB_ECP_739 #SB_PRB_ECP_738 #SB_PRB_ECP_737 #SB_PRB_ECP_736 #SB_PRB_ECP_735 #SB_PRB_ECP_734 #SB_PRB_ECP_731 #SB_PRB_ECP_729 #SB_PRB_ECP_728 #SB_PRO_DFPP_773 #SB_PRB_ECP_762
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY   | Product        | Category |
      | <KEY> | V-neck T-shirt | Apparel  |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY   | Product | Color       |
      | <KEY> |         | White,Black |
    And choose size for products in editor Dashboard as "<KEY>"
      | KEY   | Size |
      | <KEY> | S,M  |
    And add new layer as "<KEY>"
      | KEY   | Product        | Layer type | Layer name     | Front or back |
      | <KEY> | V-neck T-shirt | Image      | Layertrung.psd | Front         |
    And add custom options for campaign
    And setup custom option picture choice
      | KEY   | Layer type     | Layer name | Custom name    |
      | <KEY> | Picture choice | image05    | Picture choice |
    And choose custom option picture choice as "<KEY>"
      | KEY | NewClipart | Folder           | EditClipart | DeleteImage | show cliparts                         |
      | 01  | true       |                  |             |             |                                       |
      | 02  |            | ClipartFolder03  |             |             |                                       |
      | 03  |            | ClipartFolder    | true        | true        |                                       |
      | 04  |            | Clipart Folder 1 |             |             | Show as a droplist with Clipart names |
      | 05  | true       |                  |             |             |                                       |
    And create info clipart folder as "<KEY>"
      | KEY | Folder           | Group   | Image         |
      | 01  | ClipartFolder    | T-shirt | phub/BD_4.png |
      | 03  | Clipart Folder 1 | Legs    | phub/BD_8.png |
      | 05  | Clipart Folder 2 | Legs    | phub/BD_1.png |
    And click Save changes button as "<KEY>"
      | KEY |
      | 01  |
      | 03  |
      | 05  |
    Then verify infomation clipart folder as "<KEY>"
      | KEY | Name             | Number image |
      | 01  | ClipartFolder    | 1            |
      | 03  | Clipart Folder 1 | 1            |
      | 05  | Clipart Folder 2 | 1            |
    And choose Group for Camp as "<KEY>"
      | KEY | Group | show cliparts                         |
      | 05  | Legs  |                                       |
      | 06  | Legs  | Show as a droplist with Clipart names |
    And click to button "Save"
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open camp detail
      | Title           |
      | <Campaign name> |
    And open product details on Storefront from product detail in dashboard
    Then verify information picture choice display as image Thumbnail on SF as "<KEY>"
      | KEY | NameCO         | Folder           | Name image |
      | 01  | Picture choice |                  | BD_4       |
      | 02  | Picture choice |                  | BD_4       |
      | 03  | Picture choice |                  | BD_8       |
      | 05  |                | Clipart Folder 1 | BD_8       |
      | 05  |                | Clipart Folder 2 | BD_1       |
    Then verify information picture choice display as checklist on SF as "<KEY>"
      | KEY | NameCO         | Folder           | Image1 | Image2 |
      | 04  | Picture choice |                  | BD_8   |        |
      | 06  |                | Clipart Folder 1 |        | BD_8   |
      | 06  |                | Clipart Folder 2 |        | BD_1   |
    And quit browser
    Examples:
      | KEY | Testcase                                                                      | Campaign name                                       |
      | 01  | Create camp with CO-PC and create new clipart folder                          | Create new clipart folder                           |
      | 02  | Create camp with CO-PC and choose any clipart folder                          | Choose any clipart folder                           |
      | 03  | Create camp with CO-PC and edit clipart folder and display as image Thumbnail | Edit clipart folder and display as imageg Thumbnail |
      | 04  | Create camp with CO-PC and chooose folder and display as name clipart         | Display as name clipart                             |
      | 05  | Create camp with CO-PC and choose Group and display as image Thumbnail        | choose Group and display as image Thumbnail         |
      | 06  | Create camp with CO-PC and choose Group and display as name clipart           | choose Group and display as name clipart            |


  Scenario Outline: Validate Custom option picture choice #SB_PRB_ECP_752 #SB_PRB_ECP_753 #SB_PRB_ECP_754 #SB_PRB_ECP_755 #SB_PRB_ECP_756 #SB_PRB_ECP_661 #SB_PRB_ECP_761
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY   | Product        | Category |
      | <KEY> | Unisex T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY   | Product        | Layer type | Layer name     | Front or back |
      | <KEY> | Unisex T-shirt | Image      | Layertrung.psd | Front         |
      | <KEY> | Unisex T-shirt | Text       |                | Front         |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY   | Layer type | Layer name   | Custom name |
      | <KEY> | Text field | Text layer 1 | Option 1    |
    And setup custom option picture choice as "<KEY>"
      | KEY | Layer type     | Layer name | Custom name    | Notification                                                                    | Folder          |
      | 01  | Picture choice |            | Picture choice | SELECT to let buyers customize any layer / IGNORE to create a Conditional logic | ClipartFolder05 |
      | 02  | Picture choice | image05    | Picture choice |                                                                                 | ClipartFolder05 |
      | 03  | Picture choice | image05    | Picture choice |                                                                                 | ClipartFolder05 |
    And choose custom option picture choice as "<KEY>"
      | KEY   | Custom option  | Folder          |
      | <KEY> | Picture choice | ClipartFolder06 |
    And add conditional logic as "<KEY>"
      | KEY | Option         | Condition        | Then show | Message                                             |
      | 01  | Picture choice | is equal to>BD_8 | Option 1  | Please select target layer or set Conditional logic |
      | 02  | Picture choice | is equal to>BD_8 | Option 1  |                                                     |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign

    Examples:
      | KEY | Testcase                                                           | Campaign name |
      | 01  | Create camp with CO-PC no select layer and no add conditinal logic | PC 01         |
      | 02  | Create camp with CO-PC select layer and add conditinal logic       | PC 02         |
      | 03  | Create camp with CO-PC select layer and no add conditinal logic    | PC 03         |

  Scenario: Create clipart folder with 10 image
    Given user login to shopbase dashboard by API
    And user navigate to "Library>Cliparts" screen
    And click to button "Create folder"
    And create info clipart folder as "01"
      | KEY | Folder          | Group         | Image          |
      | 01  | ClipartFolder10 | groupFolder00 | phub/BD_1.png  |
      | 01  |                 |               | phub/BD_2.png  |
      | 01  |                 |               | phub/BD_3.png  |
      | 01  |                 |               | phub/BD_4.png  |
      | 01  |                 |               | phub/BD_5.png  |
      | 01  |                 |               | phub/BD_6.png  |
      | 01  |                 |               | phub/BD_7.png  |
      | 01  |                 |               | phub/BD_8.png  |
      | 01  |                 |               | phub/BD_9.png  |
      | 01  |                 |               | phub/BD_10.png |
    And click to button "Save changes"
    And quit browser

  Scenario: Verify number image clipart of campaign with picture choice on SF #SB_PRB_ECP_764
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
    And add new layer as "01"
      | KEY | Product        | Layer type | Layer name     | Front or back |
      | 01  | Unisex T-shirt | Image      | Layertrung.psd | Front         |
      | 01  | Unisex T-shirt | Text       |                | Front         |
    And add custom options for campaign
    And setup custom option picture choice as "01"
      | KEY | Layer type     | Layer name | Custom name    | Folder          |
      | 01  | Picture choice | image05    | Picture choice | ClipartFolder10 |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title                   |
      | Campaign picture choice |
    And click to button Launch campaign
    And search product or campaign or orders "Campaign picture choice" at list page in dashboard
    And open product details in dashboard or editor campaign "Campaign picture choice"
    And open product details on Storefront from product detail in dashboard
    And  verify number image clipart on SF as "01"
      | KEY | Number |
      | 01  | 10     |
    And quit browser




