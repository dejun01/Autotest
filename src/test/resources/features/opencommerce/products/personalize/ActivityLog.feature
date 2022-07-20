Feature: Check activity log
#sb_personalize_activity_log

  Scenario: Add product
    When clear all data
    And Delete all products by API
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title   | Description                                                            | Image     |
      | T-shirt | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg |
    And quit browser

  Scenario: Check activity log when create preview image
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product "T-shirt" on Dashboard
    And click button "Create Preview image" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And add layer personalize as"01"
      | KEY | Layer type |
      | 01  | Text       |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "01"
      | KEY | Layer type | Layer name   | Custom name | Font    | Placeholder | Max length | Default value | Text |
      | 01  | Text field | Text layer 1 | Custom text | Raleway | Input text  | 10         | text          |      |
    And click button "Save" on toolbar
    Then user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And click to button "View all"
    And verify activity log
      | CATEGORY | ACTIVITY             | DETAILS                       |
      | Product  | Update preview image | Update Preview image: T-shirt |
    And quit browser

  Scenario: Check activity log when create print file
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product "T-shirt" on Dashboard
    And click button "Create print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
    And click button "Save" on toolbar
    Then user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And click to button "View all"
    And verify activity log
      | CATEGORY | ACTIVITY          | DETAILS                    |
      | Product  | Update print file | Update Print file: T-shirt |
    And quit browser


  Scenario: Check activity log when update preview image
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product "T-shirt" on Dashboard
    And click to Edit "Preview Images" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | upload            | BD_1.png |
    And click button "Save" on toolbar
    Then user navigate to "Products>Settings" screen
    And navigate to "Account" section in Settings page
    And click to button "View all"
    And verify activity log
      | CATEGORY | ACTIVITY             | DETAILS                       |
      | Product  | Update preview image | Update Preview image: T-shirt |
    And quit browser

  Scenario: Check activity log when update print file
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product "T-shirt" on Dashboard
    And click to Edit "Print Files" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image    |
      | replace           | BD_1.png |
    And click button "Save" on toolbar
    Then user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And click to button "View all"
    And verify activity log
      | CATEGORY | ACTIVITY          | DETAILS                    |
      | Product  | Update print file | Update Print file: T-shirt |
    And quit browser


  Scenario: Check activity log when delete preview image
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product "T-shirt" on Dashboard
    And click to Edit "Preview Images" in product detail
    And click to Delete in Update Preview image or Update Print file image screen
    And click button "Save" on toolbar
    And click to button "Leave page"
    Then user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And click to button "View all"
    And verify activity log
      | CATEGORY | ACTIVITY                                  | DETAILS                                                     |
      | Product  | Delete Preview image;Update preview image | Delete Preview image: T-shirt;Update Preview image: T-shirt |
    And quit browser


  Scenario: Check activity log when delete print file
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And open product "T-shirt" on Dashboard
    And click to Edit "Print Files" in product detail
    And click to Delete in Update Preview image or Update Print file image screen
    And click button "Save" on toolbar
    And click to button "Leave page"
    Then user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And click to button "View all"
    And verify activity log
      | CATEGORY | ACTIVITY          | DETAILS                    |
      | Product  | Delete Print file | Delete Print file: T-shirt |
    And quit browser
