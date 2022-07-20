Feature: Campaign personalize of print base
# print_on_demand_campaign_personalize

  @image
  Scenario Outline: AU_CCP_4.1: Create campaign personalize 2D #SB_PRB_PPC_97 #SB_PRB_PPC_93 #SB_PRB_PPC_85 #SB_PRB_PPC_68
    Given Description: "<Test case>"
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name | Value Input | Crop Image |
      | 01  | Text           | Text Dog    | a           |            |
      | 01  | Image          | Image       | icon.png    |            |
      | 01  | Picture choice | Picture     | sample      |            |
      | 02  | Text           | Custom text | Test text   |            |
      | 03  | Text           | Custom text | Test text   |            |
      | 04  | Text           | Custom text | Test text   |            |
      | 05  | Text           | Custom text | Test text   |            |
      | 06  | Text           | Custom text | Test text   |            |
      | 07  | Text           | Custom text | Test text   |            |
      | 08  | Text           | Custom text | Test text   |            |
      | 09  | Text           | Custom text | Test text   |            |
      | 09  | Image          | Image       | icon.png    | false      |
      | 10  | Image          | Image       | icon.png    |            |
    And verify show button Preview your design on store front "<is Preview>"
    And verify image mockup with image "<Image>" and "<Percent>" percent
    Given quit browser

    Examples:
      | KEY | Test case                             | Campaign name                      | is Preview | Image                                                                                            | Percent |
      | 01  | Test full option                      | Personalize_full_option            | true       | /live_preview/Personalize_full_option.png;/live_preview/Personalize_full_option_stag.png         |         |
      | 02  | Test layer text same name             | Personalize_same_name              | true       | /live_preview/Personalize_same_name.png;/live_preview/Personalize_same_name_stag.png             |         |
      | 03  | Test layer text under line            | Personalize_Text_Underline         | true       |                                                                                                  |         |
      | 05  | Test layer text special character     | Personalize_layer_speci_character  | true       |                                                                                                  |         |
      | 06  | Test group layer                      | Personalize_group_layer            | true       |                                                                                                  |         |
      | 07  | Test layer text rotate                | Personalize_rotate                 | false      |                                                                                                  |         |
      | 08  | Test layer font special               | Personalize_font_special           | true       |                                                                                                  |         |
      | 09  | Test both layer text and image effect | Persionalize_text_and_image_effect | false      |                                                                                                  |         |
      | 10  | Test all layer is image               | Personalize_layer_all_image        | true       | /live_preview/Personalize_layer_all_image.png;/live_preview/Personalize_layer_all_image_stag.png |         |

  Scenario Outline:Campaign pictrue choice with layer in group #SB_PRB_PPC_106
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name | Value Input |
      | 01  | Picture choice | Picture     | 13b         |
    And verify show button Preview your design on store front "<is Preview>"
    And verify image mockup with image "<Image>" and "<Percent>" percent
    Given quit browser

    Examples:
      | KEY | Campaign name     | is Preview | Image                                                                        | Percent |
      | 01  | PC_layer_in_group | true       | /live_preview/PC_layer_in_group.png;/live_preview/PC_layer_in_group_stag.png | 0.5     |

  Scenario Outline: Check live preview with base and color #SB_PRB_PPC_99
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    And select style of product on storefront as "<KEY>"
      | KEY | Style          | Color  | Size |
      | 01  | Unisex T-shirt | Purple | L    |
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type | Custom name | Value Input      |
      | 01  | Text | CO Text     | Test personalize |
    And verify show button Preview your design on store front "<is Preview>"
    And verify image mockup with image "/live_preview/Personalize_base_01.png;/live_preview/Personalize_base_01_stag.png" and "<Percent>" percent
    And close live preview popup
    And select style of product on storefront as "<KEY>"
      | KEY | Style          | Color | Size |
      | 01  | V-neck T-shirt | White | L    |
    And verify show button Preview your design on store front "<is Preview>"
    And verify image mockup with image "/live_preview/Personalize_base_02.png;/live_preview/Personalize_base_02_stag.png" and "<Percent>" percent
    Given quit browser

    Examples:
      | KEY | Campaign name    | is Preview | Percent |
      | 01  | Personalize_base | true       | 0.5     |

  Scenario Outline: Check live preview with text aligment with layer PSD  #SB_PRB_PPC_120 #SB_PRB_PPC_121 #SB_PRB_PPC_122
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type | Custom name     | Value Input |
      | 01  | Text | Left aligment   | 2222        |
      | 01  | Text | Right aligment  | 2222        |
      | 01  | Text | Center aligment | 2222        |
    And verify show button Preview your design on store front "<is Preview>"
    And verify image mockup with image "<Image>" and "<Percent>" percent

    Examples:
      | KEY | Campaign name     | is Preview | Image                                                                            | Percent |
      | 01  | Text_aligment_PSD | true       | /live_preview/Text_aligment_PSD_pro.png;/live_preview/Text_aligment_PSD_stag.png | 0.5     |