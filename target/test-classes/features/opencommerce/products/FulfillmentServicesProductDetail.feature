Feature: Update product detail to show PlusHub
#env= sbase_fulfillment_service
  Scenario: Verify block PlusHub not mapping
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Then Search product "Summer Backless Dress" on All product screen
    And Open detail product of product "Summer Backless Dress"
    Then verify display block "PlusHub" in product detail
    And Mapping product in product detail
    And Mapping product with info
      | Product Name odoo     | Variant>quantity   | Product Name sbase    | VariantSB>quantitySB |
      | Summer Backless Dress | Color>green;Size>S | Summer Backless Dress | Color>green;Size>S   |
    Then verify display status "Mapped" in product detail

  Scenario: Verify block PlusHub mapped
    Given user login to shopbase dashboard
    When user navigate to "Fulfillment>Dropship>Warehouse" screen
    And user get availablestock in warehouse of "Summer Backless Dress"
    And user navigate to "Products>All products" screen
    Then Search product "Summer Backless Dress" on All product screen
    And Open detail product of product "Summer Backless Dress"
    Then Verify display info product warehouse in product detail
      | Product warehouse     | Stock |
      | Summer Backless Dress | 0     |

  Scenario: Verify edit mapping for warehouse item
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Then Search product "Summer Backless Dress" on All product screen
    And Open detail product of product "Summer Backless Dress"
    And click to "Edit mapping" from Actions
    Then Verify redirect page "Product Mapping"

  Scenario: Verify request more stock for product with quotation not expire
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Then Search product "Summer Backless Dress" on All product screen
    And Open detail product of product "Summer Backless Dress"
    And click to "Request more stock" from Actions
    Then Verify redirect to "Quotation" detail

  Scenario: Verify request more stock for product with quotation expire
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Then Search product "Summer Green Romper" on All product screen
    And Open detail product of product "Summer Green Romper"
    And click to "Request more stock" from Actions
    Then Verify redirect to "Request source product" page

  Scenario: Verify remove mapping for warehouse item
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Then Search product "Summer Backless Dress" on All product screen
    And Open detail product of product "Summer Backless Dress"
    And click to "Remove mapping" from Actions
    Then user redirect to "Map product" page
    Then Verify display button "Remove" and "Edit mapping"
    Then Removed product mapping







