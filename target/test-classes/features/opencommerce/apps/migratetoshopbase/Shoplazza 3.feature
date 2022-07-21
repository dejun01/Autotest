Feature:  migrate shoplazza to shopbase

#sbase_migrate_to_shopbase
  Background:
    Given user login to shopbase dashboard
    And navigate to "Products" tab
    And delele all product in dashboard
    And navigate to "Apps" tab
    And select app "Migrate To ShopBase" on Apps list
    When navigate to "Import From URLs" in migrate app

  Scenario: import product from Shoplazza by urls
    And import product from "Shoplazza" by enter URLs
      | URLs                                                             |
      | https://www.petkitshop.com/products/flippity-floppy-fish-cat-toy |
    When navigate to "Products" tab
    Then Verify data of product on product list screen
      | Title                                                                                     | Type | Vendor | Status |
      | Flopping Fish for Dogs, Fish Cat Toy, Flopping Floppy Fish Cat toy , Dancing Fish Cat Toy |      |        |        |

  Scenario: import product from Shoplazza by file csv
    And import product from "Shoplazza" by file csv is "Shoplazza.csv"
      | URLs in file                                                         |
      | https://www.petkitshop.com/products/jumping-activation-ball-for-dogs |
    When navigate to "Products" tab
    Then Verify data of product on product list screen
      | Title                            | Type | Vendor | Status |
      | Jumping Activation Ball for Dogs |      |        |        |