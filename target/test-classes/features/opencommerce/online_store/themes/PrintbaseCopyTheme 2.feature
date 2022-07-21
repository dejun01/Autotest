Feature: Copy theme in Manage Theme
#  Check Copy theme
#  env: pbase_copy_theme

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen

  Scenario Outline: verify copy theme #SB_OLS_THE_7
    When do actions with theme on block More themes "<KEY>"
      |KEY|Action     |Shop name        |Shop domain        |Theme id     |Theme name      |Message                           |
      |001|Copy theme |                 |                   |             |Roller to copy  |Your theme has been copied        |
      |002|Copy theme |                 |                   |             |Inside to copy  |Your theme has been copied        |
      |003|Copy theme |                 |                   |0000321      |                |Cannot copy theme                 |
      |004|Copy theme |                 |                   |             |                |Theme ID is required              |
      |005|Copy theme |shopnamecopy1    |shopdomain1        |             |Roller          |Your theme has been copied        |
      |006|Copy theme |shopnamecopy2    |shopdomain2        |             |Bassy           |Cannot copy theme                 |
      |007|Copy theme |shopnamecopy3    |shopdomain3        |             |Inside to copy  |Your theme has been copied        |
      |008|Copy theme |shopnamecopy4    |shopdomain4        |             |Roller          |Cannot copy theme                 |

    And verify created theme "<KEY>"
      | KEY | Theme name       | Last saved |
      | 001 | Roller to copy   | Just now   |
      | 002 | Inside to copy   | Just now   |
      | 005 | Roller           | Just now   |
      | 007 | Inside to copy   | Just now   |
    And do actions with theme on block More themes "<KEY>"
      | KEY | Action | Theme name     |
      | 001 | Remove | Roller to copy |
      | 002 | Remove | Inside to copy |
      | 005 | Remove | Roller         |
      | 007 | Remove | Inside to copy |
    Examples:
      |KEY|Description                                                  |
      |001|Copy id Roller theme in the same printbase                   |
      |002|Copy id Inside theme in the same printbase                   |
      |003|Copy id theme does not exist                                 |
      |004|Id theme empty                                               |
      |005|Copy id Roller theme to another shop, same account, printbase|
      |006|Copy id Bassy theme to another shop, same account, shopbase  |
      |007|Copy id Inside theme to another shop, same account, shopbase |
      |008|Copy id Roller theme to another shop, another account        |
