Feature: Verify CO on Storefront
  #pbase_custom_option_validate

  Scenario Outline:  CO on store front #SB_PRO_SFPP_64 #SB_PRO_SFPP_69 #SB_PRO_SFPP_71 #SB_PRO_SFPP_72 #SB_PRO_SFPP_73 #SB_PRO_SFPP_74 #SB_PRO_SFPP_75 #SB_PRO_SFPP_76 #SB_PRO_SFPP_77 #SB_PRO_SFPP_91 #SB_PRO_SFPP_120 #SB_PRO_SFPP_100 #SB_PRO_SFPP_115 #SB_PRO_SFPP_116
    Given open shop on storefront
    And search and select the product "Custom option"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name  | Value Input   | Show CO |
      | 01  | Droplist       | CO Droplist  | 4567          |         |
      | 01  | Text           | CO Text Full | asdg123%^^    |         |
      | 01  | Radio          | CO Radio     | test          |         |
      | 01  | Text           | CO Area Full | anvhd         |         |
      | 01  | Picture choice | CO Picture   | 39            |         |
      | 01  | Image          | CO Image     | Campaign1.png |         |
      | 01  | Checkbox       | CO Checkbox  |               | false   |
    And verify show button Preview your design on store front "true"
    And close browser
    Examples:
      | KEY |
      | 01  |

  Scenario: Verify CO on store front with text field #SB_PRB_PPC_46 #SB_PRB_PPC_47 #SB_PRB_PPC_48 #SB_PRB_PPC_49 #SB_PRB_PPC_50 #SB_PRB_PPC_51 #SB_PRB_PPC_52 #SB_PRB_PPC_54 #SB_PRB_PPC_55 #SB_PRB_PPC_57 #SB_PRB_PPC_58 #SB_PRB_PPC_59 #SB_PRB_PPC_60 #SB_PRB_PPC_61 #SB_PRO_SFPP_91
    Given open shop on storefront
    And search and select the product "Custom option text"
    Then verify noti custom option on SF
      | Option type | Custom Option                        | Message noti                                                                |
      | Text        | CO Text Character>Test custom option |                                                                             |
      | Text        | CO Text Character>123456             | Your custom text has invalid characters. We only accept characters.         |
      | Text        | CO Text Character>@##%$%$            | Your custom text has invalid characters. We only accept characters.         |
      | Text        | CO Text Number> abc                  | Your custom text has invalid characters. We only accept numbers.            |
      | Text        | CO Text Number>@##%$%$               | Your custom text has invalid characters. We only accept numbers.            |
      | Text        | CO Text Number>123abc                | Your custom text has invalid characters. We only accept numbers.            |
      | Text        | CO Text Special>123                  | Your custom text has invalid characters. We only accept special characters. |
      | Text        | CO Text Special>abc                  | Your custom text has invalid characters. We only accept special characters. |
      | Text        | CO Text Special>@##%$%$23            | Your custom text has invalid characters. We only accept special characters. |
    And close browser
