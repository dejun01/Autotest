Feature:Setting
    #get API >< numbers in dashboard

  Background:
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list

  Scenario: Reset Theme setting to default
    And user navigate to "Settings" screen

  Scenario Outline: Setting and verify show on storefront
    And user navigate to "Social Proof>Setting" screen
    And change settings of BoostConvert as "<KEY>"
      | KEY | Layout | Theme settings                          | Desktop position | Mobile position | Show on mobile | Display time | Maximum per page | Display notification in random order | Delay time between notifications | Delay time to show popup after loading | Randomize delay time | Repeat sales notification | Only dispaly synced |
      | 01  | 1      | Basic theme>Dark Theme                  | Bottom left      | Bottom          | true           | 20           | 30               | True                                 | 10                               | 3                                      | false                | true                      | 60>day              |
      | 02  | 2      | Basic theme>Dark Theme                  | Bottom right     | Bottom          | true           | 5            | 30               | True                                 | 10                               | 3                                      | false                | true                      | 60>day              |
      | 03  | 1      | Basic theme>Dark Theme                  | Top left         | Top             | true           | 5000         | 30               | True                                 | 10                               | 3                                      | false                | true                      | 60>day              |
      | 04  | 1      | Basic theme>Dark Theme                  | Top right        | Top             | true           | 5000         | 30               | True                                 | 10                               | 3                                      | false                | true                      | 60>day              |
      | 05  | 2      | Customize theme>#FFFFFF,#55586C,#55586C | Bottom left      | Bottom          | false          | 5000         | 30               | True                                 | 10                               | 3                                      | false                | true                      | 60>day              |
    Then open shop on storefront
    And verify settings sales notification on ShopBase as "<KEY>"
      | KEY | Layout          | Basic theme | Desktop position |
      | 01  | basicRoundedAll | dark        | bottom-left      |
      | 02  | basic           | dark        | bottom-right     |
      | 03  | basicRoundedAll | dark        | top-left         |
      | 04  | basicRoundedAll | dark        | top-right        |
      | 05  | basic           | customize   | bottom-left      |
    Examples:
      | KEY |
      | 01  |
      | 02  |
      | 03  |
      | 04  |


