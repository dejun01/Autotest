Feature: Conditional logic Pbase
  #env: pbase_conditional_logic

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Conditional logic #SB_PRB_CL_93 #SB_PRB_CL_96 #SB_PRB_CL_101 #SB_PRB_CL_102 #SB_PRB_CL_103 #SB_PRB_CL_104 #SB_PRB_CL_105 #SB_PRO_SFPP_122 #SB_PRO_SFPP_123 #SB_PRO_SFPP_124 #SB_PRO_SFPP_125 #SB_PRO_SFPP_126 #SB_PRO_SFPP_127 #SB_PRO_SFPP_128 #SB_PRO_SFPP_129 #SB_PRO_SFPP_136 #SB_PRO_SFPP_137 #SB_PRO_SFPP_139
    Given Description: "<Test case>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | V-neck T-shirt | Apparel  |
      | 2   | V-neck T-shirt | Apparel  |
      | 3   | V-neck T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name  | Front or back |
      | 1   | V-neck T-shirt | Text       |             | Front         |
      | 1   | V-neck T-shirt | Text       |             | Front         |
      | 1   | V-neck T-shirt | Text       |             | Front         |
      | 1   | V-neck T-shirt | Image      | image 1.jpg | Front         |
      | 1   | V-neck T-shirt | Image      | image 2.png | Front         |

      | 2   | V-neck T-shirt | Image      | image 2.png | Front         |
      | 2   | V-neck T-shirt | Text       |             | Front         |

      | 3   | V-neck T-shirt | Image      | image 2.png | Front         |
      | 3   | V-neck T-shirt | Text       |             | Front         |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type     | Layer name   | Custom name | Value       | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 1   | Droplist       |              | Option 1    | a > b       |                 |                            |               |
      | 1   | Radio          |              | Option 2    | 1111 > 2222 |                 |                            |               |
      | 1   | Picture choice | image 1      | Option 3    |             | Folder          | Thumbnail                  | folder01      |
      | 1   | Text field     | Text layer 1 | Option 4    |             |                 |                            |               |
      | 1   | Text area      | Text layer 2 | Option 5    |             |                 |                            |               |
      | 1   | Image          | image 2      | Option 6    |             |                 |                            |               |
      | 1   | Checkbox       | Text layer 3 | Option 7    |             |                 |                            |               |

      | 2   | Picture choice | image 2      | Option 1    |             | Folder          | Clipart names              | folder01      |
      | 2   | Text field     | Text layer 1 | Option 2    |             |                 |                            |               |

      | 3   | Picture choice | image 2      | Option 1    |             | Group           | Thumbnail                  | group01       |
      | 3   | Text field     | Text layer 1 | Option 2    |             |                 |                            |               |
    And add conditional logic as "<KEY>"
      | KEY | Option   | Condition                           | Then show |
      | 1   | Option 1 | is equal to>a                       | Option 2  |
      | 1   | Option 1 | is equal to>b                       | Option 3  |
      | 1   | Option 2 | is equal to>1111                    | Option 4  |
      | 1   | Option 2 | is equal to>2222                    | Option 5  |
      | 1   | Option 3 | is equal to>IMG03;is equal to>IMG04 | Option 6  |
      | 1   | Option 3 | is equal to>IMG02                   | Option 7  |

      | 2   | Option 1 | is equal to>IMG04                   | Option 2  |
      | 3   | Option 1 | is equal to>folder02                | Option 2  |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify display conditional logic on storefront as "<KEY>"
      | KEY | Value custom option    | Show custom option         |
      | 1   |                        | Option 1;Option 2;Option 4 |
      | 1   | Droplist>b;Image>IMG02 | Option 1;Option 3;Option 7 |
      | 1   | Droplist>b;Image>IMG03 | Option 1;Option 3;Option 6 |
      | 1   | Droplist>b;Image>IMG04 | Option 1;Option 3;Option 6 |
      | 1   | Droplist>b;Image>IMG01 | Option 1;Option 3          |
      | 1   | Droplist>a;Radio>2222  | Option 1;Option 2;Option 5 |

      | 2   | Droplist>IMG02         | Option 1                   |
      | 2   | Droplist>IMG01         | Option 1                   |
      | 2   | Droplist>IMG04         | Option 1;Option 2          |
      | 2   | Droplist>IMG02         | Option 1                   |

      | 3   |                        | Option 1                   |
      | 3   | Droplist>folder02      | Option 1;folder02;Option 2 |
      | 3   | Image>IMG4             | Option 1;folder02;Option 2 |
      | 3   | Droplist>folder01      | Option 1;folder01          |
      | 3   | Image>IMG02            | Option 1;folder01          |
    And quit browser

    Examples:
      | KEY | Test case                                           | Campaign name |
      | 1   | Conditional logic with all type custom option       | CL 01         |
      | 2   | Conditional logic with clipart folder droplist name | CL 02         |
      | 3   | Conditional logic with clipart group thumbnail      | CL 03         |


  Scenario Outline: Edit conditional logic #SB_PRB_CL_86 #SB_PRB_CL_90 #SB_PRB_CL_91 #SB_PRB_CL_92
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And click on Edit personalization btn
    And click button expand on Custom Option
    When delete conditinal logic of CO name "Option 1"
    And edit conditional logic as "<KEY>"
      | KEY | Option   | Condition        | Then show |
      | 1   | Option 2 | is equal to>2222 | Option 3  |
    And delete custom option "Option 6"
    And click Save change on Edit personalize
    And open product details on Storefront from product detail in dashboard
    Then verify display conditional logic on storefront as "<KEY>"
      | KEY | Value custom option               | Show custom option                           |
      | 1   |                                   | Option 1;Option 2;Option 4;Option 5          |
      | 1   | Droplist>b                        | Option 1;Option 2;Option 4;Option 5          |
      | 1   | Droplist>a;Radio>2222;Image>IMG02 | Option 1;Option 2;Option 3;Option 5;Option 7 |

    Examples:
      | KEY | Campaign name |
      | 1   | CL 01         |