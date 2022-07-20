Feature: As a merchant, I want to create campaign in new editor use to sync layer
  #env: pbase_flow_sync_layer

  Scenario Outline: link layer with base 2D #SB_PRB_ECP_715 #SB_PRB_ECP_717 #SB_PRB_ECP_718 #SB_PRB_ECP_719 #SB_PRB_ECP_720 #SB_PRB_ECP_721 #SB_PRB_ECP_722 #SB_PRB_ECP_723 #SB_PRB_ECP_724 #SB_PRO_DFPP_773 #SB_PRB_ECP_716
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                        | Category |
      | 1   | Unisex T-shirt,Long Sleeve Tee | Apparel  |
    When add more or remove products to campaign as "<KEY>"
      | KEY | Product     | Category | Add more | Remove |
      | 1   | Unisex Tank | Apparel  | true     |        |
    Then verify auto link products is "true"
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name | Front or back |
      | 1   | Unisex T-shirt | Text       |            | Front         |
      | 1   | Unisex T-shirt | Image      | BD_42.png  | Back          |
    Then verify layer detail in products
      | Product         | Layer        | Text          | Color  | Rotation | Opacity |
      | Long Sleeve Tee | Text layer 1 | Add your text | 000000 | 0        | 100     |
      | Unisex Tank     | Text layer 1 | Add your text | 000000 | 0        | 100     |
      | Long Sleeve Tee | BD_42        |               |        | 0        | 100     |
      | Unisex Tank     | BD_42        |               |        | 0        | 100     |
    Then delete layer
      | Product        | Layer |
      | Unisex T-shirt | BD_42 |
    And verify layer in products is display
      | Product         | Layer | Display |
      | Long Sleeve Tee | BD_42 | false   |
      | Unisex Tank     | BD_42 | false   |
    Then unlink product "Unisex T-shirt"
    Then delete layer
      | Product        | Layer        |
      | Unisex T-shirt | Text layer 1 |
    And verify layer in products is display
      | Product         | Layer        | Display |
      | Long Sleeve Tee | Text layer 1 | true    |
      | Unisex Tank     | Text layer 1 | true    |
    Then reunlink product "Unisex T-shirt"
    And verify layer in products is display
      | Product        | Layer        | Display |
      | Unisex T-shirt | Text layer 1 | true    |
    And close browser

    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Sync layer #SB_PRB_ECP_711 #SB_PRB_ECP_714 #SB_PRB_ECP_710
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category    |
      | 1   | Beverage Mug   | Drinkware   |
      | 1   | iPhone 12 Case | Phone Cases |
    Then verify auto link products is "false"
    And add new layer as "<KEY>"
      | KEY | Product      | Layer type |
      | 1   | Beverage Mug | Text       |
    Then edit layer to with action
      | Product      | Layer        | Action               |
      | Beverage Mug | Text layer 1 | Sync to all products |
    And verify layer in products is display
      | Product        | Layer        | Display |
      | Beverage Mug   | Text layer 1 | true    |
      | iPhone 12 Case | Text layer 1 | true    |
    When add more or remove products to campaign as "<KEY>"
      | KEY | Product                    | Category    | Add more | Remove |
      | 1   | Fabric Mask (with filters) | Accessories | true     |        |
    And verify layer in products is display
      | Product                    | Layer        | Display |
      | Fabric Mask (with filters) | Text layer 1 | true    |
    Then edit layer to with action
      | Product                    | Layer        | Action                 |
      | Fabric Mask (with filters) | Text layer 1 | Delete in this product |
    And verify layer in products is display
      | Product        | Layer        | Display |
      | Beverage Mug   | Text layer 1 | true    |
      | iPhone 12 Case | Text layer 1 | true    |
    Then edit layer to with action
      | Product      | Layer        | Action                            |
      | Beverage Mug | Text layer 1 | Sync the update to other products |
    And verify layer in products is display
      | Product                    | Layer        | Display |
      | Fabric Mask (with filters) | Text layer 1 | true    |
    Then edit layer to with action
      | Product      | Layer        | Action                 |
      | Beverage Mug | Text layer 1 | Delete in all products |
    And verify layer in products is display
      | Product                    | Layer        | Display |
      | Beverage Mug               | Text layer 1 | false   |
      | iPhone 12 Case             | Text layer 1 | false   |
      | Fabric Mask (with filters) | Text layer 1 | false   |
    And close browser

    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Delete sync layer with layer custom option #SB_PRB_UXP_19
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product           | Category      |
      | 1   | Landscape Puzzle  | Home & Living |
      | 1   | Portrait Puzzle   | Home & Living |
      | 1   | Pillow Case Cover | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Front or back |
      | 1   | Landscape Puzzle | Text       |               |
    Then edit layer to with action
      | Product          | Layer        | Action               |
      | Landscape Puzzle | Text layer 1 | Sync to all products |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Placeholder | Max length | Default value | Allow the following characters | Text |
      | 1   | Text field | Text layer 1 | Text        | Input text  | 10         | Default       |                                |      |
    Then edit layer to with action
      | Product          | Layer        | Action                 |
      | Landscape Puzzle | Text layer 1 | Delete in this product |
    And verify layer in products is display
      | Product           | Layer        | Display |
      | Portrait Puzzle   | Text layer 1 | true    |
      | Pillow Case Cover | Text layer 1 | true    |
    Then edit layer to with action
      | Product         | Layer        | Action                 |
      | Portrait Puzzle | Text layer 1 | Delete in all products |
    And verify layer in products is display
      | Product           | Layer        | Display |
      | Landscape Puzzle  | Text layer 1 | false   |
      | Portrait Puzzle   | Text layer 1 | false   |
      | Pillow Case Cover | Text layer 1 | false   |
    And close browser

    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Auto sync layer #SB_PRB_ECP_713 #SB_PRO_DFPP_771 #SB_PRO_DFPP_747
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category      |
      | 1   | Landscape Puzzle | Home & Living |
      | 1   | Portrait Puzzle  | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name   |
      | 1   | Landscape Puzzle | Text       | Text layer 1 |
    Then edit layer to with action
      | Product          | Layer        | Action                               |
      | Landscape Puzzle | Text layer 1 | Sync to all products,Auto-sync - Off |
      | Portrait Puzzle  | Text layer 1 | Auto-sync - Off                      |
    Then edit detail layer
      | Product          | Layer        | Text     | Color  | Rotation | Opacity | Location |
      | Landscape Puzzle | Text layer 1 | test 123 | D0021B | 10       | 80      | 1000>500 |
    Then verify layer detail in products
      | Product          | Layer        | Text     | Color  | Rotation | Opacity |
      | Landscape Puzzle | Text layer 1 | test 123 | D0021B | 10       | 80      |
    And close browser

    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Scale to fit the layer with base 2D
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | Unisex T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name   | Front or back | Size layer |
      | 1   | Unisex T-shirt | Text       | Text layer 1 | Front         |            |
      | 1   | Unisex T-shirt | Image      | BD_42.png    | Back          | 1800>2000  |
    Then edit layer to with action
      | Product        | Layer        | Action       | Size width height |
      | Unisex T-shirt | Text layer 1 | Scale to fit | W>2100            |
      | Unisex T-shirt | BD_42        | Scale to fit | H>2400            |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Alignment button the layer #SB_PRB_ECP_707 #SB_PRB_ECP_637 #SB_PRB_ECP_706
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category    |
      | 1   | iPhone 12 Case | Phone Cases |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Location |
      | 1   | iPhone 12 Case | Text       | 100>1000 |
    Then edit layer to with action
      | Product        | Layer        | Front   | Action                              |
      | iPhone 12 Case | Text layer 1 | Raleway | Center vertical,Center horizontally |
    Then verify layer detail in products
      | Product        | Layer        | Location |
      | iPhone 12 Case | Text layer 1 | 440>1426 |
    And close browser

    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Similar to front layers
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | Unisex T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name   | Front or back |
      | 1   | Unisex T-shirt | Text       | Text layer 1 | Front         |
    Then Similar to front layers
      | Front or back | Layer name   | Disable |
      | Back          | Text layer 1 | false   |
    And close browser

    Examples:
      | KEY |
      | 1   |