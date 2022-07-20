Feature: Editor campaign
  #pbase_new_campaign_editor_3D
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Select product main #SB_PRB_ECP_750
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product                       | Category |
      | 01  | Unisex T-shirt,Ladies T-shirt | Apparel  |
    And add new layer as "01"
      | KEY | Product        | Layer type |
      | 01  | Unisex T-shirt | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title         | Product main   | Color code main |
      | Campaign test | Unisex T-shirt | #080808         |
    Then click to button Launch campaign
    And search campaign in dashboard with name "Campaign test"
    And open product details on Storefront from product detail in dashboard
    And verify product information from pod on storefront as "01"
      | KEY | Color |
      | 01  | Black |
    And verify style incase multi product from pod on storefront as "01"
      | KEY | Style          | Verify Style   |
      | 01  | Unisex T-Shirt | Unisex T-shirt |
    And quit browser


  Scenario: No select product main #SB_PRB_ECP_749
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product                       | Category |
      | 01  | Ladies T-shirt,Unisex T-shirt | Apparel  |
    And add new layer as "01"
      | KEY | Product        | Layer type |
      | 01  | Ladies T-shirt | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title         | Color code main |
      | Campaign test | #080808         |
    And verify product main
      | Product main   |
      | Ladies T-shirt |
    Then click to button Launch campaign
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "Campaign test"
    And open product details on Storefront from product detail in dashboard
    And verify product information from pod on storefront as "01"
      | KEY | Color |
      | 01  | Black |
    And verify style incase multi product from pod on storefront as "01"
      | KEY | Style          | Verify Style   |
      | 01  | Ladies T-shirt | Ladies T-shirt |
    And quit browser


  Scenario: Select product main #SB_PRB_ECP_751
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product                       | Category |
      | 01  | Ladies T-shirt,Unisex T-shirt | Apparel  |
    And add new layer as "01"
      | KEY | Product        | Layer type |
      | 01  | Ladies T-shirt | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title         | Product main   | Color code main |
      | Campaign test | Unisex T-shirt | #080808         |
    Then click to button Launch campaign
    And search campaign in dashboard with name "Campaign test"
    And open product details on Storefront from product detail in dashboard
    And verify product information from pod on storefront as "01"
      | KEY | Color |
      | 01  | Black |
    And verify style incase multi product from pod on storefront as "01"
      | KEY | Style          | Verify Style   |
      | 01  | Unisex T-Shirt | Unisex T-shirt |
    And quit browser


  Scenario: Create camp before add media
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
    And choose color for product in editor Dashboard as "01"
      | KEY | Product        | Color | Size |
      | 01  | Unisex T-shirt | Black | S    |
    And add new layer as "01"
      | KEY | Product        | Layer type |
      | 01  | Ladies T-shirt | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title          | Description    | Tags        |
      | Campaign media | Campaign Media | ladies,test |
    Then click to button Launch campaign
    And verify result after implement action as "01"
      | KEY | Label name | Campaign name  |
      | 01  | Available  | Campaign media |
    And quit browser

  Scenario: Verify media on Pbase #SB_PRB_VGM_194 #SB_PRB_VGM_195 #SB_PRB_VGM_200 #SB_PRB_VGM_209 #SB_PRB_VGM_210 #SB_PRB_VGM_213
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And  open product details in dashboard or editor campaign "Campaign media"
    When Upload media for product or campaign
      | Title          | Image             | Image url                                                               | Error image |
      | Campaign media |                   | https://img.btdmp.com/10121/10121539/products/1631085811591460e945.jpeg |             |
      |                |                   | https://www.youtube.com/watch?v=xPFckIKt8zg                             |             |
      |                |                   | https://24hstore.vn/upload_images/images/2019/11/14/anh-gif-2-min.gif   |             |
      |                | anh-gif-1-min.gif |                                                                         |             |
      |                | Logo1.jpg         |                                                                         |             |
      |                | red.png           |                                                                         |             |
#      |               | Video_chuan.mp4   |                                                                         |             |  #đang lỗi
    Then Verify media product on store front
    And quit browser

  Scenario: Verify error message upload image product #SB_PRB_VGM_201 #SB_PRB_VGM_218 #SB_PRB_VGM_225
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    When  open product details in dashboard or editor campaign "Campaign media"
    Then Upload media for product or campaign
      | Title         | Image                | Error image                                                                                                          |
      | @NameProduct@ | Video Sai Format.avi | There is a file that we don’t support this file type. Please try again with PNG, JPG, JPEG, GIF, WEBP, GIF, MP4, MOV |
      |               | phub/image/120MB.png | 120MB.png: This file is too large. The allowed size is under 20 MB.                                                  |
    And quit browser

  Scenario: Create shop and verify add campaign #SB_PRB_ECP_766
    Given login to shopbase dashboard
    And create a shop with name "@shop-printbase-@"
    And Input information merchant
      | First name | Last name | Store country | Your personal location | Phone number | Business           | Print base |
      | Test       | OCG       | Vietnam       | Vietnam                | 0984533888   | shopbase@beeketing | true       |
    And user navigate to "Catalog" screen
    When add products to campaign as "01"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
    And add new layer as "01"
      | KEY | Product        | Layer type | Layer value | Font | Front or back |
      | 01  | Unisex T-shirt | Text       | Test value  | 210  | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title       |
      | New campain |
    And click to button Launch campaign
    Then verify campaign created as "01"
      | KEY | Campaign name | Status |
      | 01  | New campain   | LIVE   |
    And quit browser