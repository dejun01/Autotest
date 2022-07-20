Feature:Action Delete, Clone, Hide in CO
  #pbase_new_campaign_editor_custom

  Background:
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |

  Scenario: change custom option with Picture choice layer
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category      |
      | 1   | Quilt          | Home & Living |
      | 2   | Ladies T-shirt | Apparel       |
    And add new layer for base product as "<KEY>"
      | KEY | Product        | Layer type  | Layer name         |
      |     | Quilt          | Image layer | artwork_ladies.jpg |
      |     | Ladies T-shirt | Text layer  | artwork_ladies.jpg |
    And add new layer
      | Front or back | Layer type  | Layer name         |
      | Front side    | Image layer | artwork_ladies.jpg |
    And add custom option as "<KEY>"
      | KEY | Product        | Type           | Layer          | Label          | Drop image        | Notification |
      |     | Quilt          | Picture choice | artwork_ladies | Picture choice | BD_3.png;BD_8.png |              |
      |     | Ladies T-shirt | Picture choice | artwork_ladies | Picture choice | BD_3.png;BD_8.png |              |
    And add custom options with base product "Ladies T-shirt"
    And change option layer
      | Type           | Layer          | Label          | Drop image        | Notification      |
      | Picture choice |                |                |                   | Field is required |
      | Picture choice | artwork_ladies | Picture choice | BD_3.png;BD_8.png |                   |
    And edit layer custon option
      | Layer name     | Option | Image or text field |
      | Picture choice | Show   | false               |
      | Picture choice | Hide   | true                |
      | Picture choice | Delete | false               |
    And close browser


  Scenario: verify sync layer with layer text #SB_PRB_ECP_675
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product        | Category |
      | Ladies T-shirt | Apparel  |
    And add new layer
      | Front or back | Layer type  | Layer name         |
      | Front side    | Image layer | artwork_ladies.jpg |
      | Front side    | Text layer  | Add your text      |
    And add custom options with base product "Ladies T-shirt"
    And change option layer
      | Type       | Layer         | Label | Font    | Placeholder | Max length | Default value | Allow the following characters | Text          | Notification |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | Text center   | Characters,Numbers             | This is shirt |              |
    When add more products to campaign
      | Product                | Category |
      | Premium Ladies T-shirt | Apparel  |
    And verify layer have synced
      | Product                | Layer         | Layer display |
      | Premium Ladies T-shirt | Add your text | true          |
    And add custom options with base product "Premium Ladies T-shirt"
    And verify custon option have synced
      | Layer | Layer display |
      | Text  | true          |
    And close browser


  Scenario: verify sync layer with image PSD  #SB_PRB_ECP_722
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product        | Category |
      | Ladies T-shirt | Apparel  |
    And add new layer
      | Front side | Layer type  | Layer name         |
      | Front side | Image layer | artwork_ladies.psd |
    And add custom options with base product "Ladies T-shirt"
    And change option layer
      | Type       | Layer         | Label | Font    | Placeholder | Max length | Default value | Allow the following characters | Text   | Notification |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | Text center   | Characters,Numbers             | abc123 |              |
    And add option
      | Label | Add option |
      | Text  | above      |
    And change option layer
      | Type  | Layer     | Label | Notification |
      | Image | Capture_1 | Image |              |
    When add more products to campaign
      | Product                | Category |
      | Premium Ladies T-shirt | Apparel  |
    And verify layer have synced
      | Product                | Layer          | Layer display |
      | Premium Ladies T-shirt | artwork_ladies | true          |
    And close browser


  Scenario: 1.verify sync custon option with mutilple base product #SB_PRB_ECP_675
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product                | Category |
      | Ladies T-shirt         | Apparel  |
      | Premium Ladies T-shirt | Apparel  |
    And add new layer
      | Product                | Layer type  | Layer name         |
      | Ladies T-shirt         | Image layer | Capture_1.jpg      |
      |                        | Text layer  | Add your text      |
      |                        | Image layer | artwork_ladies.psd |
      | Premium Ladies T-shirt | Image layer | Capture_1.jpg      |
      |                        | Text layer  | Add your text      |
    And add custom options with base product "Ladies T-shirt"
    And change option layer
      | Type  | Layer     | Label | Notification |
      | Image | Capture_1 | Image |              |
    And add option
      | Label | Add option |
      | Image | above      |
    And change option layer
      | Type       | Layer         | Label | Font    | Placeholder | Max length | Default value | Allow the following characters | Text   | Notification |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | Text center   | Characters,Numbers             | abc123 |              |
    When add more products to campaign
      | Product        | Category |
      | Unisex T-shirt | Apparel  |
    And verify layer have synced
      | Product        | Layer          | Layer display |
      | Unisex T-shirt | artwork_ladies | true          |
    And close browser


  Scenario: 2.verify sync custon option with mutilple base product #SB_PRB_ECP_675
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product                | Category |
      | Ladies T-shirt         | Apparel  |
      | Premium Ladies T-shirt | Apparel  |
    And add new layer
      | Front side | Product                | Layer type  | Layer name         |
      | Front side | Ladies T-shirt         | Image layer | artwork_ladies.psd |
      | Front side |                        | Image layer | Capture_1.jpg      |
      | Front side |                        | Text layer  | Add your text      |
      | Front side | Premium Ladies T-shirt | Image layer | artwork_ladies.psd |
    And add custom options with base product "Ladies T-shirt"
    And change option layer
      | Type  | Layer     | Label | Notification |
      | Image | Capture_1 | Image |              |
    And add option
      | Label | Add option |
      | Image | above      |
    And change option layer
      | Type       | Layer         | Label | Font    | Placeholder | Max length | Default value | Allow the following characters | Text   | Notification |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | Text center   | Characters,Numbers             | abc123 |              |
    When add more products to campaign
      | Product        | Category |
      | Unisex T-shirt | Apparel  |
    And verify layer have synced
      | Product        | Layer         | Layer display |
      | Unisex T-shirt | Capture_1     | true          |
      | Unisex T-shirt | Add your text | true          |
    And close browser
