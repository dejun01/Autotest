Feature: Edit UI Language
#  sbase_edit_locale

  Background:
    Given user login to shopbase dashboard by API

  Scenario: Check preferances - language dropdown list
    And user navigate to "Online Store>Preferences" screen
    Then verify language dropdown
      | Theme or App        | Count language supported |
      | Boost Upsell App    | 6                        |
      | Product Reviews App | 6                        |
      | Boost Convert App   | 6                        |
      | Bassy Theme         | 103                      |
      | Roller Theme        | 103                      |
      | Inside Theme        | 103                      |
    And verify language selected on edit pack
      | Theme or App | Language |
      | Roller Theme | Spanish  |

  Scenario: Verify edit language action
    And user navigate to "Online Store" screen
    And user direct to edit language pack
    Then verify language dropdown
      | Theme or App        | Count language supported | Page      |
      | Boost Upsell App    | 6                        | Edit pack |
      | Boost Convert App   | 6                        | Edit pack |
      | Product Reviews App | 6                        | Edit pack |
      | Bassy Theme         | 103                      | Edit pack |
      | Roller Theme        | 103                      | Edit pack |
      | Inside Theme        | 103                      | Edit pack |
    And verify sort action
      | Column         | Sort |
      | Phrase         | A-Z  |
      | English Phrase | Z-A  |
      | Key            | A-Z  |
    And verify key displayed on page
      | Row |
      | 10  |
      | 20  |
      | 50  |
      | 100 |
    And verify language selected on edit pack
      | Theme or App      | Language | Key             | Phrase      | Edit key                 |
      | Roller Theme      | German   | no result       |             |                          |
      | Roller Theme      | German   | share           | Teilen      |                          |
      | Roller Theme      | German   | share           |             | No translation available |
      | Inside Theme      | Spanish  | language        | Language    | Lugar                    |
      | Boost Convert App | Spanish  | timer_countdown | left to buy | Se acabo el tiempo       |
    And verify language edited on SF
      | Language | Edit key           | Product          |
      | Spanish  | Lugar              | /products/tes2-1 |
      | Spanish  | Se acabo el tiempo | /products/tes2-1 |
