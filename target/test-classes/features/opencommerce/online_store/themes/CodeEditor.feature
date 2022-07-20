Feature: Code editor

# Env:
#  prod_sbase_code_editor
#  prodtest_sbase_code_editor
#  staging_sbase_code_editor



  Scenario: Delete all themes
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    Then delete all themes on block More themes


  Scenario Outline: Edit and Publish theme code
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    Then click "Explore more templates" button on dashboard
    And create new theme "<KEY>"
      | KEY | Theme  |
      | 001 | Roller |
    And verify created theme "<KEY>"
      | KEY | Theme name | Last saved | Base theme |
      | 001 | Roller     | Just now   | Roller     |
    When do actions with theme on block More themes "<KEY>"
      | KEY | Action  | Theme name |
      | 001 | Publish | Roller     |
    And do actions with block Current theme "<KEY>"
      | KEY | Action    |
      | 001 | Edit code |
    And open file "pages/Cart.vue"
    Then verify current version is "Original"
    When edit code of file store theme "<KEY>"
      | KEY | Code                                  |
      | 001 | <template>Edited Cart page</template> |
    And click on button "Save"
    And click on button "Publish"
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Edit and Save theme code
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    Then click "Explore more templates" button on dashboard
    And create new theme "<KEY>"
      | KEY | Theme  |
      | 001 | Inside |
    And verify created theme "<KEY>"
      | KEY | Theme name | Last saved | Base theme |
      | 001 | Inside     | Just now   | Inside     |
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action    | Theme name |
      | 001 | Edit code | Inside     |
    And open file "pages/Home.vue"
    Then verify current version is "Original"
    When edit code of file store theme "<KEY>"
      | KEY | Code                                  |
      | 001 | <template>Edited Home page</template> |
    Then save file and verify log file revision
    Then click on back icon to navigate Themes page
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action  | Theme name |
      | 001 | Preview | Inside     |
    Then verify text on storefront "<KEY>"
      | KEY | Text             | isShow |
      | 001 | Edited Home page | false  |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Preview theme code
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    Then click "Explore more templates" button on dashboard
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action    | Theme name |
      | 001 | Edit code | Inside     |
    And click on button "Preview"
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Search and Roll back file revision
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    Then click "Explore more templates" button on dashboard
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action    | Theme name |
      | 001 | Edit code | Roller     |
    And search and select file "pages/Cart.vue"
    Then verify current version is "Current"
    Then roll back to the "Original" version
    And click on button "Save"
    And refresh page
    Then verify text on dashboard "<KEY>"
      | KEY | File           | Text                   | isShow |
      | 001 | pages/Cart.vue | cart.continue_shopping | true   |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: : Delete file
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action    | Theme name |
      | 001 | Edit code | Inside     |
    Then do action with file "<KEY>"
      | KEY | Action | File                 |
      | 001 | Delete | pages/AllReviews.vue |
    And refresh page
    And verify show file "<KEY>"
      | KEY | File                 | isShow |
      | 001 | pages/AllReviews.vue | false  |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: : Verify rename and move files
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action    | Theme name |
      | 001 | Edit code | Inside     |
    Then do action with file "<KEY>"
      | KEY | Action                           | File           |
      | 001 | Rename/Move>pages/DmcaEdited.vue | pages/Dmca.vue |
      | 001 | Rename/Move>sections/Home.vue    | pages/Home.vue |
    Then verify show file "<KEY>"
      | KEY | File                 | isShow |
      | 001 | pages/Dmca.vue       | false  |
      | 001 | pages/DmcaEdited.vue | true   |
      | 001 | pages/Home.vue       | false  |
      | 001 | sections/Home.vue    | true   |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Verify publish theme code editor
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    And user click Customize on current theme
    When user change color setting as "<KEY>"
      | KEY | Page content text | Announcement bar background |
      | 001 | #B54040           | #CFE3A6                     |
    Given open page "/cart"
    Then verify text on storefront "<KEY>"
      | KEY | Text             | isShow |
      | 001 | Edited Cart page | true   |
    And verify UI by css as "<KEY>"
      | KEY | Description                 | Element                              | CSS value                |
      | 001 | Announcement bar background | //section[@class="announcement-bar"] | background-color>#cfe3a6 |
      | 001 | Page text content           | //main                               | color>#b54040            |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Verify preview theme code editor
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action  | Theme name |
      | 001 | Preview | Inside     |
    Then verify text on storefront "<KEY>"
      | KEY | Text             | isShow |
      | 001 | Edited Home page | true   |
    Examples:
      | KEY |
      | 001 |