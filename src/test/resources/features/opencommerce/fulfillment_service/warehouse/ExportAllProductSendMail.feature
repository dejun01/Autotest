#env = fulfillment_service_export_all
Feature: Export all product in inventory

  Scenario: Export all product
    Given staff login to shop dashboard
    When user navigate to "Fulfillment>Dropship>Warehouse" screen
    And export product "All products" in inventory
    Then verify noti export equal "Your export will be delivered to email: " with email "@email"
    And staff login to email and open email with subject contains shop
    And verify that send mail to merchant with file name "inventory_export_yyyyMMdd"