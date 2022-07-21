Feature: Import campaign from shop template
  #manual_desing_campaign

  Background: : Delete all product live
    Given  delete all campaigns by API
    When  user login to shopbase dashboard by API
    And user navigate to "Library>Custom Art" screen
    And get Custom Art name on Custom Art list

  Scenario:  Import campaign from shop tem #SB_PRB_MDC_35
    And click Import to store button with campaign "<customArtName>"
    And user navigate to "Campaigns" screen
    Then verify camp exist as "1"
      | KEY | Label name | Campaign name   |
      | 1   | Available  | <customArtName> |
    And open shop on storefront
    And search and select the product first "<customArtName>"
    Then verify show Image processing manual design on store front after execute as "true"


  Scenario:  Edit campaign from shop tem
    And click Edit from sample button with campaign "<customArtName>"
    And click to button "Save"
    And input infor to create description for campaign editor
      | Title           |
      | Camp Custom Art |
    And click to button Launch campaign
    And login to hive-pbase
    And redirect to campaign "Camp Custom Art" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Camp Custom Art"
    And open product details on Storefront from product detail in dashboard
    Then verify show Image processing manual design on store front after execute as "true"
    And quit browser

  Scenario Outline: Edit campaign manual design with add more base #SB_PRB_MDC_37
    And click Edit from sample button with campaign "<customArtName>"
    And add more base product
    When add more products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And click to button "Save"
    And input infor to create description for campaign editor
      | Title          | Description                              | Tags | Photo Guide |
      | <CampaignName> | Manual Desgin Camp add more base product | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And redirect to campaign "<CampaignName>" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "<CampaignName>"
    And verify product information in dashboard
      | Title          | Description                              |Tags |
      | <CampaignName> | Manual Desgin Camp add more base product |new  |
    And open product details on Storefront from product detail in dashboard
    Then verify show Image processing manual design on store front after execute as "true"
    Examples:
      | CampaignName                             |
      | Manual Desgin Camp add more base product |


  Scenario Outline: Edit form sample products with don't add more base #SB_PRB_MDC_36
    Given  user login to shopbase dashboard by API
    And user navigate to "Library>Custom Art" screen
    And click Edit from sample button with campaign "<customArtName>"
    And click to button "Save"
    And input infor to create description for campaign editor
      | Title          | Description            | Tags | Photo Guide |
      | <CampaignName> | Manual Desgin Campaign | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And redirect to campaign "<CampaignName>" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "<CampaignName>"
    And verify product information in dashboard
      | Title                  | Description            | Tags |
      | Manual Desgin Campaign | Manual Desgin Campaign | new  |
    And open product details on Storefront from product detail in dashboard
    Then verify show Image processing manual design on store front after execute as "true"
    And quit browser
    Examples:
      | CampaignName           |
      | Manual Desgin Campaign |

