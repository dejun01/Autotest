Feature: Verify personalize when setup instant live preview
#   pbase_new_campaign_editor_custom
  @image
  Scenario Outline: AU_CCP_4.1: Create campaign personalize 2D
    Given Description: "<Test case>"
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name      | Value Input   |
      | 01  | Text           | Custom text      | Test text     |
      | 01  | Image          | Custom Image     | Campaign1.png |
      | 01  | Picture choice | Folder_body_left | sample        |
    And verify show button Preview your design on store front "true"
    Given quit browser

    Examples:
      | Test case        | Campaign name           |
      | Test full option | Personalize_full_option |
