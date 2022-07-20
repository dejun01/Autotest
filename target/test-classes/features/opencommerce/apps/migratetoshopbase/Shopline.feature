Feature:  migrate shoplazza to shopbase

#sbase_migrate_to_shopbase
  Background:
    Given user login to shopbase dashboard
    And navigate to "Products" tab
    And delele all product in dashboard
    And navigate to "Apps" tab
    And select app "Migrate To ShopBase" on Apps list
    When navigate to "Import From URLs" in migrate app

  Scenario: import product from Shopline by urls
    And import product from "Shopline" by enter URLs
      | URLs                                                     |
      | https://www.lifewarehouse.net/products/moai-phone-holder |
    When navigate to "Products" tab
    Then Verify data of product on product list screen
      | Title             | Type | Vendor | Status |
      | Moai Phone Holder |      |        |        |

  Scenario: import product from Shopline by file csv
    And import product from "Shopline" by file csv is "Shopline.csv"
      | URLs in file                                                        |
      | https://www.lifewarehouse.net/products/led-light-collapsible-mirror |
    When navigate to "Products" tab
    Then Verify data of product on product list screen
      | Title                        | Type | Vendor | Status |
      | LED Light Collapsible Mirror |      |        |        |