Feature: Manage Orders Phub

  Scenario Outline: Verify status of Orders when sync to app Print Hub
    Given Description: "<Testcase>"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Multiple product:Unisex T-shirt,White,M>1"
      | Email                   | First Name | Last Name | Address              | Country       | City        | Zip code | State    | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 2527 Twin House Lane | United States | Springfield | 65804    | Missouri | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab              |
      | 1   | Awaiting Payment |
    And search order Print Hub
    And verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Status line      | Product name     | Lineitems                  | SKU                         | Is sync |
      | 1   | Awaiting Payment | Multiple product | Unisex T-shirt / White / M | PH-AP-UnisexT-shirt-White-M | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 1   | 6.49         | 4.99           |
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    Then verify order detail after mapped or charge
      | Tab               | Variants                   | SKU                              | Cost     | Status            | Product cost | Shipping | Total Cost |
      | Awaiting Shipment | Unisex T-shirt / White / M | SKU: PH-AP-UnisexT-shirt-White-M | 6.49 x 1 | Awaiting Shipment | 6.49         | 4.99     | 11.48      |
    Examples: <Key>
      | KEY | Status           | Testcase                                       |
      | 1   | Awaiting Payment | Orders only contains products belong Print Hub |

  Scenario: Verify mapping product in Order app Phub
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Cairbull Cycling Helmet"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    And search order Print Hub
    And Mapping product in order
    Then Mapping product in order with base product "Beverage Mug"
      | Front or back     | Name image                 |
      | add-artwork-front | file_artwork_correctly.png |
    And Select all option value
      | Option value  |
      | Default color |
      | Default size  |
    Then Change value option
      | Option value  | Change option mapped |
      | Default color | black                |
      | Default size  | 11oz                 |
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab              | Variants     | SKU                               | Cost     | Status           | Product cost | Shipping | Total Cost |
      | Awaiting Payment | black / 11oz | SKU: PH-AP-BeverageMug-black-11oz | 4.49 x 1 | Awaiting Payment | 4.49         | 5.99     | 10.48      |
    Then redirect to shopbase dashboard
    And user login to shopbase dashboard by API
    Then user navigate to "Products>All products" screen
    And Search product "Cairbull Cycling Helmet" on All product screen
    And Open detail product of product "Cairbull Cycling Helmet"
    And remove mapping fulfillment services "PrintHub"