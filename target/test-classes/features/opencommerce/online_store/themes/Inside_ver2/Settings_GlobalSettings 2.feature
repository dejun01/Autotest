Feature: Settings - Inside v2 Theme - Global setting

    #insidev2_global_setting

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And navigate to tab "Settings"
    And select setting section "Global Settings"

  Scenario Outline: Verify show sort in search result
    And change search settings as "<KEY>"
      | KEY | Enable sort | Default logic |
      | 1   | true        | Most viewed   |
      | 2   | false       | Best selling  |
      | 3   | true        | Best selling  |
      | 4   | true        | Newest        |
    And navigate to tab "Sections"
    And change header layout as "<KEY>"
      | KEY | Layout  |
      | 1   | Inline  |
      | 2   | Rich    |
      | 3   | Inline  |
      | 4   | Minimal |

    Given open page "/search"
    And verify show sort on Store front as "<KEY>"
      | KEY | Key search | Show sort on modal search | Show sort on search result page | Default logic | Param           |
      | 1   | Test       | true                      | true                            | Most viewed   | sort=viewed     |
      | 2   | Test       | false                     | false                           |               | sort=sold       |
      | 3   | Test       | true                      | true                            | Best selling  | sort=sold       |
      | 4   | Test       | true                      | true                            | Newest        | sort=created_at |
    Then choose sort option and verify sort result as "<KEY>"
      | KEY | Sort option  | Sort result     | Products handle      | Key search |
      | 4   | Most viewed  | sort=viewed     | test-2               | &q=Test    |
      | 4   | Best selling | sort=sold       | test-2               | &q=Test    |
      | 4   | Newest       | sort=created_at | test-3,test-2,test-1 | &q=Test    |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |

  Scenario Outline: Verify global setting
    And change global settings as "<KEY>"
      | KEY | Show sort in collection page | Pagination    | Product image display    | Content alignment | Show sales banner | Show Add to cart button | Shape | Enable back to top icon |
      | 1   | true                         | Lazy load     | Keep default image ratio | Center            | true              | true                    | Sharp | false                   |
      | 2   | false                        | Paging number | Square ratio             | Left              | false             | false                   | Round | true                    |
      | 3   | true                         | Paging number | Portraits                | Left              | true              | true                    | Cut   | true                    |
    Then open shop on storefront
    And verify global setting on storefront as "<KEY>"
      | KEY | Page                | Show sort in collection page | Pagination    | Product image display    | Content alignment | Show sales banner | Show Add to cart button | Shape | Enable back to top icon |
      | 1   | /                   |                              |               | Keep default image ratio | Center            | true              | true                    | Sharp | false                   |
      | 1   | /collections/summer | true                         | Lazy load     | Keep default image ratio | Center            | true              | true                    | Sharp | false                   |
      | 2   | /                   |                              |               | Square ratio             | Left              | false             | false                   | Round | true                    |
      | 2   | /collections/summer | false                        | Paging number | Square ratio             | Left              | false             | false                   | Round | true                    |
      | 3   | /                   |                              |               | Portraits                | Left              | true              | true                    | Cut   | true                    |
      | 3   | /collections/summer | true                         | Paging number | Portraits                | Left              | true              | true                    | Cut   | true                    |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
