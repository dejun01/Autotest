#pbase_mapping_orders
@dashboardProduct @dashboard
Feature: Mapping order

  Background: Login dashboard
    Given clear all data
    Given user login to shopbase dashboard
    Then user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title          | Price |
      | @product_test@ | 10.1  |
    Given open shop on storefront
    Then checkout successfully via stripe with cart "@product_test@" with mapping Phub
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then redirect to shopbase dashboard
    Then user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub
    And Mapping product in order
    Then Mapping product in order with base product "Neck Gaiter New"
      | Front or back     | Name image                 |
      | add-artwork-front | file_artwork_correctly.png |

  @dashboardOrderMapping
  Scenario: Create new order unmapping product #SB_PRH_377 #SB_PRH_378 #SB_PRH_379 #SB_PRH_380 #SB_PRH_381 #SB_PRH_382 #SB_PRH_383 #SB_PRH_384
    Then Change value option
      | Option value  | Change option mapped |
      | Default color | All over print       |
      | Default size  | 1pcs                 |
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab       | Variants      | SKU  | Status    | Cost     | Product cost | Shipping | Total Cost |
      | In Review | Default Title | SKU: | In Review | 0.00 x 1 | 0.00         | 0.00     | 0.00       |

  @dashboardOrderMapping
  Scenario: Create new order mapping product #SB_PRH_385 #SB_PRH_386
    And Select all option value
      | Option value  |
      | Default color |
      | Default size  |
    Then Change value option
      | Option value  | Change option mapped |
      | Default color | All over print       |
      | Default size  | 1pcs                 |
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab              | Variants              | SKU                                         | Cost     | Status           | Product cost | Shipping | Total Cost |
      | Awaiting Payment | All over print / 1pcs | SKU: PH-AOP-NeckGaiterNew-Alloverprint-1pcs | 4.99 x 1 | Awaiting Payment | 4.99         | 2.99     | 7.98       |
    And Click edit mapping in order
    Then Mapping product in order with base product "Beverage Mug"
      | Front or back     | Name image                 |
      | add-artwork-front | file_artwork_correctly.png |
    Then Change value option
      | Option value  | Change option mapped |
      | Default color | black                |
      | Default size  | 11oz                 |
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab              | Variants     | SKU                               | Cost     | Status           | Product cost | Shipping |
      | Awaiting Payment | black / 11oz | SKU: PH-AP-BeverageMug-black-11oz | 4.49 x 1 | Awaiting Payment | 4.49         | 5.99     |
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    Then verify order detail after mapped or charge
      | Tab               | Variants     | SKU                               | Cost     | Status            | Product cost | Shipping |
      | Awaiting Shipment | black / 11oz | SKU: PH-AP-BeverageMug-black-11oz | 4.49 x 1 | Awaiting Shipment | 4.49         | 5.99     |