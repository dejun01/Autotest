Feature: Edit Custom option
#pbase_mutilple_artwork
  Scenario Outline: Create campaign customize layer #SB_PRB_SFMA_255 #SB_PRB_SFMA_256 #SB_PRB_SFMA_304 #SB_PRB_test_157 #SB_PRB_test_158 #SB_PRB_test_159 #SB_PRB_test_160 #SB_PRB_test_161 #SB_PRB_test_162 #SB_PRB_test_189 #SB_PRB_test_190
    Given user login to shopbase dashboard by API
    And delete all campaigns by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name | Front or back |
      | 01  | Unisex T-shirt | Add text   |            | Front         |
      | 01  | Unisex T-shirt | Add text   |            | Front         |
    And add group layer
      | Front or back | Name group  | Name group new | Layer name                |
      | Front         | New group 1 | Group 1        | Text layer 1,Text layer 2 |
    Then setup custom option
      | Layer type | Layer name                | Custom name | Value | Font      |
      | Radio      | Text layer 1;Text layer 2 | Radio       | 1,2   | Open Sans |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type  | Custom name | Value Input |
      | 01  | Radio | Radio       | 2           |
    And quit browser
    Examples:
      | KEY | Campaign name            |
      | 01  | Campaign customize layer |


  Scenario Outline: Create campaign customize group #SB_PRB_SFMA_266 #SB_PRB_SFMA_282 #SB_PRB_test_167 #SB_PRB_test_170 #SB_PRB_test_178
    Given user login to shopbase dashboard by API
    And delete all campaigns by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name | Front or back |
      | 01  | Unisex T-shirt | Add text   |            | Front         |
      | 01  | Unisex T-shirt | Add text   |            | Front         |
    And add group layer
      | Front or back | Name group  | Name group new | Layer name   |
      | Front         | New group 1 | Group 1        | Text layer 1 |
      | Front         | New group 1 | Group 2        | Text layer 2 |
    And setup customize group layer
      | Option type | Label    | Default group |
      | Droplist    | Droplist | Group 2       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type     | Custom name | Value Input |
      | 01  | Droplist | Droplist    | Group 1     |
    And quit browser
    Examples:
      | KEY | Campaign name            |
      | 01  | campaign customize group |


  Scenario Outline: Edit campaign customize group #SB_PRB_test_171 #SB_PRB_test_172 #SB_PRB_test_174 #SB_PRB_test_176
    Given user login to shopbase dashboard by API
    And delete all campaigns by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Front or back |
      | 01  | Unisex T-shirt | Add text   | Front         |
      | 01  | Unisex T-shirt | Add text   | Front         |
      | 01  | Unisex T-shirt | Add text   | Front         |
    And add group layer
      | Front or back | Name group  | Name group new | Layer name   |
      | Front         | New group 1 | Group 1        | Text layer 1 |
      | Front         | New group 1 | Group 2        | Text layer 2 |
      | Front         | New group 1 | Group 3        | Text layer 3 |
    And edit group layer
      | Front or back | Name group           | Layer name   | Action    | Is exit group | Is exit layer |
      | Front         | Group 1              | Text layer 1 | Delete    | false         | false         |
      | Front         | Group 2              | Text layer 2 | Ungroup   | false         | true          |
      | Front         | Group 3              | Text layer 3 | Duplicate | true          | true          |
      | Front         | Duplicate of Group 3 | Text layer 3 |           | true          | true          |
    And setup customize group layer
      | Option type | Label    | Default group |
      | Droplist    | Droplist | Group 3       |
    And edit customize group or custom option
      | Name     | Action |
      | Droplist | Delete |
    And quit browser
    Examples:
      | KEY |
      | 01  |
