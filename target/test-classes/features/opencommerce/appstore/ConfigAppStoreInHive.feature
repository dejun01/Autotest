Feature:  Config App/Feature store in hive

  #sbase_apps_store
  Scenario: Create  a Collection
    Given login to hive sbase
    When user navigate to "Apps & Features>Collection" on hive page
    Then Edit a collections in hive
      | Active | Name       | Slug     | Description | Priority | Display Type | Type App        | Icon              | Message             |
      | true   | Collection | slug     | desc of     | 1        | Logo         | New Apps        | contest/icon1.png | Success             |
      | false  | Collection | slug     | desc of     | 1        | Big Image    | Trending Now    |                   | Success             |
      | false  | Collection | slug     |             | 1        | Big Image    | Most Downloaded |                   | Success             |
      | true   |            | slug-    | desc of     | 1        | Big Image    | Most Downloaded |                   | name is null        |
      | true   | Trending   | slug-    |             | 1        | Logo         | Trending Now    |                   | name is exist       |
      | true   | Collection |          |             | 1        | Big Image    | New Apps        |                   | slug is null        |
      | true   | Collection | trending |             | 1        | Logo         | Most Downloaded |                   | slug is exist       |
      | true   | Collection | abc slug |             | 1        | Big Image    | Trending Now    |                   | slug is invalid     |
      | true   | Collection | slug-    |             | a        | Logo         | New Apps        |                   | Priority is invalid |
      | true   | Collection | slug-    |             |          | Big Image    | Most Downloaded |                   | Priority is null    |

  Scenario: Create  a category
    Given login to hive sbase
    When user navigate to "Apps & Features>Category" on hive page
    Then Edit a category in hive
      | Active | Name          | Slug           | Description | Priority | Icon              | Background            | Message             |
      | true   | Category      | slug           | desc of     | 1        | contest/icon1.png | contest/inContest.jpg | Success             |
      | false  | Category      | slug           |             | 1        |                   |                       | Success             |
      | true   |               | slug           |             | 1        |                   |                       | name is null        |
      | true   | Place to sell | slug           |             | 1        |                   |                       | name is exist       |
      | true   | Category      |                |             | 1        |                   |                       | slug is null        |
      | true   | Category      | places-to-sell |             | 1        |                   |                       | slug is exist       |
      | true   | Category      | abc slug       |             | 1        |                   |                       | slug is invalid     |
      | true   | Category      | slug           |             | a        |                   |                       | Priority is invalid |
      | true   | Category      | slug           |             |          |                   |                       | Priority is null    |

#  Scenario: Edit  a apps list
#    Given login to hive sbase
#    When user navigate to "Apps & Features>Apps List" on hive page
#    Then Edit a apps list in hive
#      | Name | Slug     | Short-Description | App Logo                | App Image               | Category Type | Collection Type | CTA Text 1 | CTA Text 2 | CTA Link   | Priority | Search Term | Get Support Link          | Website URL               | App Screenshot          | Content | Approve   | Message             |
#      | apps | slug     | Description       | /contest/preContest.jpg | /contest/preContest.jpg | Place to sell |                 | before     | after      | admin/apps | 1        | search      | https://www.shopbase.com/ | https://www.shopbase.com/ | /contest/preContest.jpg | content | Published | Success             |
#      |      | slug     |                   |                         |                         |               |                 |            |            |            | 1        |             |                           |                           |                         |         | Published | name is null        |
#      | abc  | slug     |                   |                         |                         |               |                 |            |            |            | 1        |             |                           |                           |                         |         | Published | name is exist       |
#      | apps |          |                   |                         |                         |               |                 |            |            |            | 1        |             |                           |                           |                         |         | Published | slug is null        |
#      | apps | abc      |                   |                         |                         |               |                 |            |            |            | 1        |             |                           |                           |                         |         | Published | slug is exist       |
#      | apps | abc slug |                   |                         |                         |               |                 |            |            |            | 1        |             |                           |                           |                         |         | Published | slug is invalid     |
#      | apps | slug     |                   |                         |                         |               |                 |            |            |            | a        |             |                           |                           |                         |         | Published | Priority is invalid |
#      | apps | slug     |                   |                         |                         |               |                 |            |            |            |          |             |                           |                           |                         |         | Published | Priority is null    |


  Scenario: Create  a feature list
    Given login to hive sbase
    When user navigate to "Apps & Features>Features List" on hive page
    Then Edit a feature list in hive
      | Name    | Slug     | Description | Logo             | Image            | Category Type | Collection Type | CTA Text    | CTA Link        | Priority | Search Term | Get Support Link              | Website URL               | Content | Feature Screenshot | Approve   | Message             |
      | feature | slug     | desc of     | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ | 1        | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | Success             |
      | feature | slug     | desc of     | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ | 1        | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | Success             |
      |         | slug     |             | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ | 1        | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | name is null        |
      | abc     | slug     |             | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ | 1        | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | name is exist       |
      | feature | abc slug |             | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ | 1        | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | slug is invalid     |
      | feature | slug     |             | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ | a        | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | Priority is invalid |
      | feature | slug     |             | /front/logo2.jpg | /front/logo2.jpg | Place to sell |                 | Install now | www.google.com/ |          | feature     | https://help.shopbase.com/en/ | https://www.shopbase.com/ |         | /front/logo2.jpg   | Published | Priority is null    |