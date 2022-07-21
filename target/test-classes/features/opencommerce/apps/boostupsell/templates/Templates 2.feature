Feature: Template upsell
#  As merchant, I want verify Templates upsell work
#envi: us_templates

  Scenario Outline: Detele template
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Templates" screen
    Then delete template "<KEY>"
      | KEY | Title           | Message                             |
      | 001 | Auto template 1 | Delete template offer successfully! |
      | 002 | Auto template 2 | Delete template offer successfully! |
    And verify template on template list "<KEY>"
      | KEY | Title           | isExist |
      | 001 | Auto template 1 | false   |
      | 002 | Auto template 2 | fasle   |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |


  Scenario Outline: Create new template
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Templates" screen
    Then click Create Template button
    And input data to create template "<KEY>"
      | KEY | Title           | Description                                      | Offers                                                             | Image                 | Product Types               | Business Types | Templates Gallery | Featured Template | Message                              |
      | 001 | Auto template 1 | Checked template gallery and featured template   | Accessory-rule 2,Quantity-rule 1,Post-rule 2,Pre-rule 1,Pre-rule 3 | front/imagereview.png | Unisex Tank,Long Sleeve Tee | Dropshipping   | True              | True              | Added offer template successfully    |
      | 002 | Auto template 2 | Unchecked template gallery and featured template | Post-rule 1                                                        |                       |                             | Dropshipping   | False             | False             | Added offer template successfully    |
      | 003 |                 | Create template failed                           | Pre-rule 1                                                         | front/imagereview.png | Unisex Tank                 | Dropshipping   | True              | True              | Some fields on your form are invalid |
    Then verify template on template list "<KEY>"
      | KEY | Title           | isExist |
      | 001 | Auto template 1 | true    |
      | 002 | Auto template 2 | true    |
    Given user navigate to "Templates Gallery" screen
    Then verify template on template gallery page "<KEY>"
      | KEY | Title           | Description                                      | Image                 | Product Types               | Business Types | isShow |
      | 001 | Auto template 1 | Checked template gallery and featured template   | front/imagereview.png | Unisex tank,Long sleeve tee | Dropshipping   | true   |
      | 002 | Auto template 2 | Unchecked template gallery and featured template | front/imagereview.png | Post-rule 1                 | Dropshipping   | fasle  |
    Then verify template's offers on template gallery page "<KEY>"
      | KEY | Offers name      | Strategy   | Type          |
      | 001 | Accessory-rule 2 | Cross-sell | Accessory     |
      | 001 | Quantity-rule 1  | Cross-sell | Quantity      |
      | 001 | Post-rule 2      | Up-sell    | Post-purchase |
      | 001 | Pre-rule 1       | Up-sell    | Pre-purchase  |
      | 001 | Pre-rule 3       | Up-sell    | Pre-purchase  |
    And close browser
    Examples:
      | KEY | Description                                      |
      | 001 | Checked template gallery and featured template   |
      | 002 | Unchecked template gallery and featured template |
      | 003 | Create template failed                           |


  Scenario Outline: verify use template
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Templates Gallery" screen
    Then open template "<KEY>"
      | KEY | Title                |
      | 001 | Auto shared template |
    And click on Use Template button
    Then verify use template "<KEY>"
      | KEY | Type          | Offer name              | Products/Collections | Status   | Priority |
      | 001 | accessory     | Accessory-rule shared 1 | Specific by rules    | Active   | 1        |
      | 001 | quantity      | Quantity-rule shared 1  | Specific by rules    | Active   | 1        |
      | 001 | post-purchase | Post-rule shared 1      | Specific by rules    | Inactive | 1        |
      | 001 | pre-purchase  | Pre-rule shared 1       | Specific by rules    | Active   | 2        |
      | 001 | pre-purchase  | Pre-rule shared 2       | Specific by rules    | Active   | 1        |
    And delete offers "<KEY>"
      | KEY | Offers name                                            | Type      |
      | 001 | Accessory-rule shared 1                                | Accessory |
      | 001 | Quantity-rule shared 1                                 | Quantity  |
      | 001 | Post-rule shared 1,Pre-rule shared 1,Pre-rule shared 2 | Upsell    |
    And close browser
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Edit template
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Templates" screen
    Then open template "<KEY>"
      | KEY | Title           |
      | 001 | Auto template 1 |
    And input data to create template "<KEY>"
      | KEY | Title                  | Description                                           | Offers                 | Image            | Product Types | Business Types               | Templates Gallery | Featured Template | Message                             |
      | 001 | Auto template edited 1 | Checked template gallery and featured template edited | Pre-rule 1,Post-rule 2 | front/Banner.jpg | Unisex Hoodie | Dropshipping,Print on Demand | True              | false             | All changes were successfully saved |
    And user navigate to "Templates" screen
    Then verify template on template list "<KEY>"
      | KEY | Title                  | isExist |
      | 001 | Auto template 1        | false   |
      | 001 | Auto template edited 1 | true    |
    And close browser
    Examples:
      | KEY |
      | 001 |