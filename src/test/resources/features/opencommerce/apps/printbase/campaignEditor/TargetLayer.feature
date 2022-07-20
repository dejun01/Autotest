Feature: As a merchant, I want to create campaign in new editor use to target layer
  #env: pbase_flow_target_layer

  Scenario Outline: Custom option select target layer for all product #SB_PRB_ECP_672
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                    | Category      |
      | 1   | Bedding Set,Quilt,Area Rug | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type |
      | 1   | Bedding Set | Text       |
    Then Sync layer
      | Product     | Layer        |
      | Bedding Set | Text layer 1 |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Target layer |
      | 1   | Text field | Text layer 1 | text        | true         |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Custom option no select target layer 1 #SB_PRO_DFPP_748
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category      |
      | 1   | Quilt,Area Rug | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product | Layer type |
      | 1   | Quilt   | Text       |
    Then Sync layer
      | Product | Layer        |
      | Quilt   | Text layer 1 |
    And add custom options for campaign
    Then select layer for every product
      | Product  | Target layer | Layer        | Font    |
      | Quilt    | false        | Text layer 1 | Raleway |
      | Area Rug | false        | Text layer 1 |         |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Custom option no select target layer 2
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category      |
      | 1   | Quilt,Area Rug | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product | Layer type |
      | 1   | Quilt   | Text       |
    Then Sync layer
      | Product | Layer        |
      | Quilt   | Text layer 1 |
    And add custom options for campaign
    Then select layer for every product
      | Product | Target layer | Layer              | Choose layer for all product |
      | Quilt   | false        | Text layer 1>Quilt | Yes                          |
    Then verify layer selected in all product
      | Layer        |
      | Text layer 1 |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Custom option no select target layer 3 #SB_PRB_ECP_742
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                          | Category      |
      | 1   | Landscape Puzzle,Portrait Puzzle | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type |
      | 1   | Landscape Puzzle | Text       |
      | 1   | Portrait Puzzle  | Text       |
    And add custom options for campaign
    Then select layer for every product
      | Product          | Target layer | Layer                         | Font    |
      | Landscape Puzzle | false        | Text layer 1>Landscape Puzzle | Raleway |
      | Portrait Puzzle  | false        | Text layer 1>Portrait Puzzle  |         |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Custom option no select target layer 4
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                          | Category      |
      | 1   | Landscape Puzzle,Portrait Puzzle | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type |
      | 1   | Landscape Puzzle | Text       |
      | 1   | Portrait Puzzle  | Text       |
    And add custom options for campaign
    Then select layer for every product
      | Product          | Target layer | Layer                         | Choose layer for all product |
      | Landscape Puzzle | false        | Text layer 1>Landscape Puzzle | Yes                          |
#    Then verify layer selected in all product
#      | Product         | Layer        |
#      | Portrait Puzzle | Text layer 1 |
    Then verify layer selected in all product
      | Layer        |
      | Text layer 1 |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Custom option no select target layer 5
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                       | Category |
      | 1   | Unisex T-shirt,Ladies T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Front or back |
      | 1   | Unisex T-shirt | Text       | Front         |
    Then Sync layer
      | Product        | Layer        |
      | Unisex T-shirt | Text layer 1 |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name | Front or back |
      | 1   | Unisex T-shirt | Image      | BD_42.png  | Back          |
    And add custom options for campaign
    Then select layer for every product
      | Product        | Target layer | Layer                       | Choose layer for all product | Font    |
      | Unisex T-shirt | false        | Text layer 1>Unisex T-shirt | Yes                          | Raleway |
    And click to button "Save"
    And click button expand on Custom Option
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name | Custom name | Target layer | Choose layer for all product | Font    |
      | 1   | Image      | BD_42.png  | image       | false        | Yes                          | Raleway |
    And close browser
    Examples:
      | KEY |
      | 1   |