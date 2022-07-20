#  env: template_store
Feature: Template store

  Scenario: Remove all theme
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then remove all themes

  Scenario: verify add template when logged in #SB_TPL_TSN_73 #SB_TPL_TSN_83 #SB_TPL_TSN_84
    Given open template_store on storefront
    When click on button "Login" template store
    And user login to template store
    Then verify show Preview template
    When click button "Add template to your store" on preview detail
    Then do action in popup
      | Action          | Shop | Status |
      | Add to my store | shop | true   |
    Then verify add template successfully

  Scenario: verify add template when not log in #SB_TPL_TSN_94
    Given open template_store on storefront
    Then verify show Preview template
    When click button "Get this template" on preview detail
    And user login to template store
    And select shop shopname
    Then verify add template successfully

  Scenario: verify filter template #SB_TPL_TSN_67 #SB_TPL_TSN_68 #SB_TPL_TSN_69 #SB_TPL_TSN_77 #SB_TPL_TSN_80 #SB_TPL_TSN_81
    Given login to hive sbase
    When filter template active and visible
    Then get detail information first template store
    Then get detail information template store
      | industry      | theme  |
      | Coasters      |        |
      |               | Inside |
      | Home & Living | Roller |
    Given open template_store on storefront
    When click on button view all template store
    And verify filter template
      | action | industry      | theme  |
      | check  | Coasters      |        |
      | check  |               | Inside |
      | check  | Home & Living | Roller |
      | search |               |        |

  Scenario: verify template store detail #SB_TPL_TSN_72 #SB_TPL_TSN_78
    Given open template_store on storefront
    When search and click template store
    Then verify detail template


