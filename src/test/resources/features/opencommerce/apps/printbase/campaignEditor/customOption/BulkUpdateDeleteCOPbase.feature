Feature: Bulk update delete CO pbase

#env:pbase_bulk_update_delete_CO
#au-pbase-bulk-updates-deleteco.onshopbase.com
  Background: Login dashboard
    Given clear all data

  Scenario: Precondition: delete Campaign
    When delete all campaigns by API

  Scenario Outline: Precondition >> Create campaign Conditional logic
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | V-neck T-shirt | Apparel  |
      | 2   | V-neck T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name  | Front or back |
      | 1   | V-neck T-shirt | Text       |             | Front         |
      | 1   | V-neck T-shirt | Text       |             | Front         |

      | 2   | V-neck T-shirt | Text       |             | Front         |
      | 2   | V-neck T-shirt | Text       |             | Front         |
      | 2   | V-neck T-shirt | Text       |             | Front         |
      | 2   | V-neck T-shirt | Image      | image 1.png | Front         |
      | 2   | V-neck T-shirt | Image      | 39.png      | Front         |

    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type     | Layer name   | Custom name | Value       | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 1   | Radio          |              | Option 1    | a > b       |                 |                            |               |
      | 1   | Text field     | Text layer 1 | Option 2    |             |                 |                            |               |
      | 1   | Text field     | Text layer 2 | Option 3    |             |                 |                            |               |

      | 2   | Droplist       |              | Option a    | a > b       |                 |                            |               |
      | 2   | Radio          |              | Option b    | 1111 > 2222 |                 |                            |               |
      | 2   | Picture choice | 39           | Option c    |             | Folder          | Thumbnail                  | Folder 01     |
      | 2   | Text field     | Text layer 1 | Option d    |             |                 |                            |               |
      | 2   | Text area      | Text layer 2 | Option e    |             |                 |                            |               |
      | 2   | Image          | image 1      | Option f    |             |                 |                            |               |
      | 2   | Checkbox       | Text layer 3 | Option g    |             |                 |                            |               |
    And click button expand on Custom Option
    And add conditional logic as "<KEY>"
      | KEY | Option   | Condition                    | Then show         |
      | 1   | Option 1 | is equal to>a                | Option 2;Option 3 |

      | 2   | Option a | is equal to>a                | Option b          |
      | 2   | Option a | is equal to>b                | Option c          |
      | 2   | Option b | is equal to>1111             | Option d          |
      | 2   | Option b | is equal to>2222             | Option e          |
      | 2   | Option c | is equal to>artwork_34_1     | Option f          |
      | 2   | Option c | is equal to>artwork_34_new_1 | Option g          |

    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Campaign name |
      | 1   | CL 01         |
      | 2   | CL 02         |


  Scenario Outline: Check khi delete CO in conditional logic #SB_PRB_BUD_15 #SB_PRB_BUD_5 #SB_PRB_BUD_18 #SB_PRB_BUD_19
    Given user login to shopbase dashboard
    And user navigate to "Campaigns>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value          |
      | Product title | contains   | <Product name> |
    And Execute an action bulk update for product "Delete custom options"
      | Value                | Message                      |
      | is equal to>Option 1 | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "1"
    And user navigate to "Campaigns>All campaigns" screen
    And search product or campaign or orders "<Product name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Product name>"
    Then Verify this product "<Product name>" on storefront
      | Custom option | Show Custom |
      | Option 1      | false       |
      | Option 2      | true        |
      | Option 3      | true        |
    And quit browser
    Examples:
      | Product name |
      | CL 01        |

  Scenario Outline: Check khi delete CO text area in products  #SB_PRB_BUD_19 #SB_PRB_BUD_15
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value          |
      | Product title | contains   | <Product name> |
    And Execute an action bulk update for product "Delete custom options"
      | Value                | Message                      |
      | is equal to>Option b | Your bulk update was created |
    Then verify quantity of product been updated  after bulk update is "1"
    And user navigate to "Campaigns>All campaigns" screen
    And search product or campaign or orders "<Product name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Product name>"
    Then Verify this product "<Product name>" on storefront
      | Custom option | Show Custom |
      | Option a      | true        |
      | Option b      | true        |
      | Option d      | true        |
    Then verify display conditional logic on storefront as "1"
      | KEY | Value custom option | Show custom option         |
      | 1   |                     | Option a                   |
      | 1   | Droplist>b;Image>11 | Option a;Option c;Option f |
      | 1   | Droplist>b;Image>12 | Option a;Option c;Option g |

    And quit browser
    Examples:
      | Product name |
      | CL 02        |

  Scenario: Check version of order after delete CO Campaign #SB_PRB_BUD_23
    And open shop on storefront
    And search and select the product "CL 01"
    And add product with custom option to cart then checkout as "01"
      | KEY | Product | Custom option                               | Size |
      | 01  | CL 01   | Text,Option 2,test CO;Text,Option 3,test CO | M    |
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given login to hive-pbase
    And redirect to order Pbase detail on hive-pbase
    Then verify version campaign in order is "1"
    And quit browser
