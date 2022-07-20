Feature: Verify CO Dashborad
  #pbase_custom_option_validate

  Background:
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |

  Scenario Outline: Verify in custom option detail with type text field #SB_PRB_ECP_653 #SB_PRB_ECP_695
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Front or back |
      | 01  | Unisex Tank | Text       | Front         |
    And add custom options for campaign
    And change option layer and verify custom option
      | Type       | Layer         | Label | Font    | Placeholder | Max length | Default value | Allow the following characters | Noti for Default value                                               |
      | Text field |               |       |         |             |            |               |                                |                                                                      |
      | Text field | Add your text | Text  | Raleway | Input text  | 0          |               |                                |                                                                      |
      | Text field | Add your text | Text  | Raleway | Input text  | 256        |               |                                |                                                                      |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | test          | Characters                     |                                                                      |
      | Text field | Add your text | Text  | Raleway | Input text  | 5          | test custom   | Characters                     | Max length must be less than 5                                       |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | 1234          | Characters                     | Your default value has invalid characters. We only accept characters |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | 1234abc       | Characters                     | Your default value has invalid characters. We only accept characters |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | @##$          | Characters                     | Your default value has invalid characters. We only accept characters |
    And quit browser
    Examples:
      | KEY |
      | 01  |

  Scenario Outline: Verify in custom option detail with type text field 02 #SB_PRO_DFPP_805
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Front or back |
      | 01  | Unisex Tank | Text       | Front         |
    And add custom options for campaign
    And change option layer and verify custom option
      | Type       | Layer         | Label | Font    | Placeholder | Max length | Default value  | Allow the following characters | Noti for Default value                                                       |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | 1234           | Numbers                        |                                                                              |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | 123 acb        | Numbers                        | Your default value has invalid characters. We only accept numbers            |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | @##$#          | Numbers                        | Your default value has invalid characters. We only accept numbers            |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | ~!@#$%^&*().,? | Special Characters             |                                                                              |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | test           | Special Characters             | Your default value has invalid characters. We only accept special characters |
      | Text field | Add your text | Text  | Raleway | Input text  | 15         | 1233@#         | Special Characters             | Your default value has invalid characters. We only accept special characters |
    And quit browser
    Examples:
      | KEY |
      | 01  |


  Scenario Outline: Verify in custom option with text area #SB_PRB_ECP_635
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer value | Front or back |
      | 01  | Unisex Tank | Text       | Test        | Front         |
    And add custom options for campaign
    And change option layer and verify custom option
      | Type      | Layer         | Label | Font    | Placeholder | Max length | Default value | Allow the following characters | Noti for Default value                                                       |
      | Text area |               |       |         |             |            |               |                                |                                                                              |
      | Text area | Add your text | Text  | Raleway | Input text  | 0          |               |                                |                                                                              |
      | Text area | Add your text | Text  | Raleway | Input text  | 256        |               |                                |                                                                              |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | test          | Characters                     |                                                                              |
      | Text area | Add your text | Text  | Raleway | Input text  | 5          | test custom   | Characters                     | Max length must be less than 5                                               |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | 1234          | Characters                     | Your default value has invalid characters. We only accept characters         |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | 1234abc       | Characters                     | Your default value has invalid characters. We only accept characters         |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | 1233@#        | Special Characters             | Your default value has invalid characters. We only accept special characters |
    And quit browser
    Examples:
      | KEY |
      | 01  |

  Scenario Outline: Verify in custom option with text area 02 #SB_PRB_ECP_696
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer value | Front or back |
      | 01  | Unisex Tank | Text       | Test        | Front         |
    And add custom options for campaign
    And change option layer and verify custom option
      | Type      | Layer         | Label | Font    | Placeholder | Max length | Default value  | Allow the following characters | Noti for Default value                                                       |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | 1234           | Numbers                        |                                                                              |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | 123 acb        | Numbers                        | Your default value has invalid characters. We only accept numbers            |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | @##$#          | Numbers                        | Your default value has invalid characters. We only accept numbers            |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | ~!@#$%^&*().,? | Special Characters             |                                                                              |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | test           | Special Characters             | Your default value has invalid characters. We only accept special characters |
      | Text area | Add your text | Text  | Raleway | Input text  | 15         | 1233@#         | Special Characters             | Your default value has invalid characters. We only accept special characters |
    And quit browser
    Examples:
      | KEY |
      | 01  |