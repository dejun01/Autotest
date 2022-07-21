Feature: Product in catalog
#plusbase_dashboard

  Background: Access catalog page
    Given user login to shopbase dashboard
    When user navigate to "plusbase/catalog" screen by url

  Scenario: 1.verify search product name
    Given search product = "(Test product) Automation Dress Fesher Hide Double" in catalog
    Then verify info product with
      | Product                                            | Product cost    | Selling price   | Product in list |
      | (Test product) Automation Dress Fesher Hide Double | $15.00 - $35.00 | $30.00 - $40.00 | yes             |
    And move to product detail with product name = "(Test product) Automation Dress Fesher Hide Double"
    And user navigate to "plusbase/catalog" screen by url
    When search product = "(NOUTest product) Automation Dress Fesher Hide Double" in catalog
    Then verify info product with
      | Product                                               | Product in list |
      | (NOUTest product) Automation Dress Fesher Hide Double | no              |
    And close browser

  Scenario Outline: 2. Verify filter for product cost, selling price
    Given filter "<Label tab>" with conditions = "<Conditions>" and value = "<Value>"
    Then verify "<Label tab>" with conditions = "<Conditions>" and value = "<Value>"
    And clear all filter
    And close browser
    Examples:
      | Label tab     | Conditions                    | Value |
      | Product cost  | Product cost is greater than  | 10    |
      | Product cost  | Product cost is less than     | 1     |
      | Selling price | Selling price is greater than | 10    |
      | Selling price | Selling price is less than    | 1     |

  Scenario: 3. Verify filter with tag
    Given filter "Tag" with value = "Plusbase Product"
    Then verify info product with
      | Product                                 | Product cost    | Selling price   | Product in list |
      | (Test product) Puff Sleeve Women Summer | $10.00 - $11.00 | $10.00 - $12.00 | yes             |
    And Removed tag with value = "Plusbase Product"
    And close browser

  Scenario: 4. verify product belong to category, sub-category
    Given click to category name with
      | Name  |
      | Shoes |
    And verify info product with
      | Product                                 | Product cost    | Selling price   | Product in list |
      | (Test product) Puff Sleeve Women Summer | $10.00 - $11.00 | $10.00 - $12.00 | yes             |
    When click to sub-category name with
      | Name  |
      | Shoes |
    And verify info product with
      | Product                                 | Product cost   | Selling price   | Product in list |
      | (Test product) Puff Sleeve Women Summer | $5.00 - $10.00 | $10.00 - $20.00 | yes             |
    And close browser

  Scenario: 5. verfiry change product name in odo
    Given search product = "[Plusbase] product automation" in catalog
    Then verify info product with
      | Product                       | Product cost   | Selling price   | Product in list |
      | [Plusbase] product automation | $2.00 - $15.00 | $10.00 - $20.00 | yes             |
    When change product name to "[Plusbase] product automation" from "[Plusbase] product automation 2"
    Given search product = "[Plusbase] product automation 2" in catalog
    Then verify info product with
      | Product                         | Product cost   | Selling price   | Product in list |
      | [Plusbase] product automation 2 | $2.00 - $15.00 | $10.00 - $20.00 | yes             |
    When change product name to "[Plusbase] product automation 2" from "[Plusbase] product automation"
    Given search product = "[Plusbase] product automation" in catalog
    Then verify info product with
      | Product                       | Product cost   | Selling price   | Product in list |
      | [Plusbase] product automation | $2.00 - $15.00 | $10.00 - $20.00 | yes             |


