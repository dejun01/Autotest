Feature: Create shop
  # createshop_smoketest
  # prod_sbase_createshop_smoketest
  # staging_sbase_createshop_smoketest

  Scenario Outline: Create shop
    When login to shopbase
    Given create a shop with name "@shop-aut-can-be-deleted-@"
    And input store information as "<KEY>"
      | KEY | Store country | Your personal location | Phone number | Social profile             |
      | 1   | Vietnam       | Vietnam                | 0985676567   | https://www.google.com.vn/ |
      | 2   | Estonia       | Vietnam                | 0985676567   | https://www.shopbase.com/  |

    And select store type as "<KEY>"
      | KEY | Business type   | Store type | Category/Niche |
      | 1   | Print-on-demand | PrintBase  |                |
      | 2   | Dropshipping    | niche      | Watches        |
    And customize store as "<KEY>"
      | KEY | Type      | Import content | Store import | Answer question                                   | Customize style | Color | Font | Type product |
      | 1   | PrintBase | No thanks      |              | a newbie and have no experience in selling online | No              |       |      | Campaigns    |
      | 2   | ShopBase  | No thanks      |              | a newbie and have no experience in selling online | Yes             |       |      | Products     |

    And user navigate to "Settings" screen
    Then verify store information as "<KEY>"
      | KEY | Phone      | Country | Currency        |
      | 1   | 0985676567 | Vietnam | US dollar (USD) |
      | 2   | 0985676567 | Estonia | Euro (EUR)      |

    And verify show theme default

    Examples:
      | KEY | Description           |
      | 1   | Create shop printbase |
      | 2   | Create shop shopbase  |