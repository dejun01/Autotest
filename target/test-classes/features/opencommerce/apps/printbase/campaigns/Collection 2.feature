Feature: CampaignCollection
  #clone_campaign
  Background: Login dashboard
    Given user login to firstShop dashboard by API
    And user navigate to "Campaigns>Collections" screen
    And Delete all collection

  Scenario: Check image thumbnail campaign #SB_PRB_TC_5 #SB_PRB_TC_6 #SB_PRB_TC_8 #SB_PRB_TC_11
    And user navigate to "Campaigns>All campaigns" screen
    When Search product "Shoes testing" on All product screen
    And Open detail product of product "Shoes testing"
    When update images for a variant
      | Variant                              | Position Image |
      | Slip On Shoes,All over print,Woman 5 | 3              |
    And user navigate to "Campaigns>Collections" screen
    And Create collection with data
      | Title            | Collection type | Conditions                                             |
      | Smart collection | Automated       | all conditions,Product title,is equal to,Shoes testing |
    And Setup product thumbnail on collection
      | Option name | Option value   |
      | Style       | Slip On Shoes  |
      | Color       | All over print |
      | Size        | Woman 5        |
    Then verify list product in collection on store front
      | Product       | Image status |
      | Shoes testing | loaded       |