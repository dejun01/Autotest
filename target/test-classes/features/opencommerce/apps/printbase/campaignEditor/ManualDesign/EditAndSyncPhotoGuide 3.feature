Feature: Edit photo guide for camp manual design
  #pbase_edit_syn_photoguide

  Scenario:  Edit and syn photo guide to manual design #SB_PRB_MC_UMN_25
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And  open product details in dashboard or editor campaign "Draft PhotoGuide"
    And click to button "Continue"
    And edit photo guide
      | Title        | Type  | Content  |
      | Photo Guide3 | Image | BD_2.png |
    And click Save Draft Camp
    And user navigate to "Catalog>Campaigns" screen
    And open product details in dashboard or editor campaign "Complete Photo Guide"
    And open product details on Storefront from product detail in dashboard
    Then verify show PhotoGuide on store front after execute as "01"
      | KEY | Custom Name | Type  |
      | 01  | Image       | Image |
    And quit browser

  Scenario: Check limmit 500 videos incase add image  #SB_PRB_VGM_203 SB_PRB_VGM_227 #SB_PRB_VGM_208 #SB_PRB_VGM_220
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And  open product details in dashboard or editor campaign "Camp limit video"
    And verify message on camp detail
      | Image             | Image url                                                               | Error message                                                                                                        | Save changes |
      | anh-gif-1-min.gif |                                                                         | Exceed the media limit. Please upload maximum 500 medias per product.                                                |              |
      |                   | https://img.btdmp.com/10121/10121539/products/1631085811591460e945.jpeg | Fail to save product, please contact ShopBase for more information: you can’t add more than 500 images for a product | Yes          |
    And quit browser
