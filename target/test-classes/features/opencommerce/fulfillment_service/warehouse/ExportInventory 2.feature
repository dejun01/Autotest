Feature: Export product in inventory
#env = fulfillment_service

  Background: Access inventory page
    Given clear all data
    And user login to shopbase dashboard
    When user navigate to "Fulfillment>Dropship>Warehouse" screen

  Scenario: Export product with current page
    And export product "Current page" in inventory
    Then verify noti export equal "Make download link successfully" with email ""
    And close browser

  Scenario: Export product when search
    Given search product with "Digital Car Tire Tyre Air"
    When get product inventory default
    And export product "Current search & filter" in inventory
    Then verify noti export equal "Make download link successfully" with email ""
    And verify that the content of file downloaded is matched with product information from dashboard
      | Product Name | Variant Name | Purchased | Sold | Available Stock | Incoming | Wait To Fulfill | Need To Purchase |
    And close browser

  Scenario: Export when select product
    Given search product with "Digital Car Tire Tyre Air"
    When select product export
    And get product inventory default
    And export product "Selected products" in inventory
    Then verify noti export equal "Make download link successfully" with email ""
    And verify that the content of file downloaded is matched with product information from dashboard
      | Product Name | Variant Name | Purchased | Sold | Available Stock | Incoming | Wait To Fulfill | Need To Purchase |

