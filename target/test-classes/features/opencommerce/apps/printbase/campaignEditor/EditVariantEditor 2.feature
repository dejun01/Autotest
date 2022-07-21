Feature: Edit variant editor
#pbase_edit_variant_editor
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign editor with base 2D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
#    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY   | Product        | Category |
      | <KEY> | Ladies T-shirt | Apparel  |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY | Product | Color              |
      | 01  |         | White              |
      | 02  |         | White,Black        |
      | 03  |         | Sports Grey        |
      | 04  |         | White,Dark Heather |
    And choose size for products in editor Dashboard as "<KEY>"
      | KEY | Size |
      | 01  | S    |
      | 02  | M    |
      | 03  | S,M  |
      | 04  | S,M  |
    And add new layer as "<KEY>"
      | KEY   | Product        | Layer type | Layer name | Layer value | Font | Color | Front or back |
      | <KEY> | Ladies T-shirt | Text       | Test layer | Test value  | 210  |       | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to set individual price
    Then verify variant infomation of product in pricing as "<KEY>"
      | KEY | variant                           |
      | 01  | Ladies T-shirt - White - S        |

      | 02  | Ladies T-shirt - White - M        |
      | 02  | Ladies T-shirt - Black - M        |

      | 03  | Ladies T-shirt - Sports Grey - S  |
      | 03  | Ladies T-shirt - Sports Grey - M  |

      | 04  | Ladies T-shirt - White - S        |
      | 04  | Ladies T-shirt - White - M        |
      | 04  | Ladies T-shirt - Dark Heather - S |
      | 04  | Ladies T-shirt - Dark Heather - M |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                               |
      | 01  | PB-AP-LadiesT-shirt-White-S       |

      | 02  | PB-AP-LadiesT-shirt-White-M       |
      | 02  | PB-AP-LadiesT-shirt-Black-M       |

      | 03  | PB-AP-LadiesT-shirt-SportsGrey-S  |
      | 03  | PB-AP-LadiesT-shirt-SportsGrey-M  |

      | 04  | PB-AP-LadiesT-shirt-White-S       |
      | 04  | PB-AP-LadiesT-shirt-White-M       |
      | 04  | PB-AP-LadiesT-shirt-DarkHeather-S |
      | 04  | PB-AP-LadiesT-shirt-DarkHeather-M |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color        | Size |
      | 01  | <Campaign name> |              |      |

      | 02  | <Campaign name> | White        |      |
      | 02  | <Campaign name> | Black        |      |

      | 03  | <Campaign name> |              | S    |
      | 03  | <Campaign name> |              | M    |

      | 04  | <Campaign name> | White        | S    |
      | 04  | <Campaign name> | White        | M    |
      | 04  | <Campaign name> | Dark Heather | S    |
      | 04  | <Campaign name> | Dark Heather | M    |
    And quit browser
    Examples:
      | KEY | Testcase                                   | Campaign name         |
      | 01  | Create Campaign with 1 size, 1 size        | 1 size, 1 color       |
      | 02  | Create Campaign with 1 size, N color       | 1 size, N color       |
      | 03  | Create Campaign with N size, 1 color       | N size, 1 color       |
      | 04  | Create Campaign with some size, some color | some size, some color |
