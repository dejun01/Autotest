Feature: Mapping ADC product
  #env = sbase_adc_mapping

  Background: clear import list
    Then user navigate to screen "/admin/apps/alidropcon/import-list" by API
    And delete all products from import list of ADC

  Scenario: Mapping product successfully #SB_ADC_MP_7 #SB_ADC_MP_1
    #1. Create a new ShopBase(SB) product then order it
    And user navigate to screen "/admin/products" by API
    Given Select all product and delete
      | Product name | Action                   |
      | Shirt        | Delete selected products |
    Given Add a new product with data
      | Title | Price |
      | Shirt | 20    |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Red, Green   |
    Given open storefront shop on a new tab
    Then checkout successfully via stripe with cart "Shirt:S,Green>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    When switch to the first tab
#    #2. Mapping SB product with Ali product
    Then user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    When user navigate to "Manage Orders" screen
    Then expand order "" in list ADC
    And open mapping product screen of "Shirt:S / Green"
    When import the AliExpress link "https://vi.aliexpress.com/item/1005003516318418.html"
    Then map product with option values
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Red, Green   |
    And verify order detail in manage order
      | Mapping Status | Product Cost   |
      | Mapped         | @Product Cost@ |
    And close browser

  Scenario: Check validation mapping product #SB_ADC_MP_3 #SB_ADC_MP_6
    #1. Create a new ShopBase(SB) product then order it
    When user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title | Price |
      | Shoes | 25    |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | 37, 38       |
      | Color      | Green, Red   |
    Given open storefront shop on a new tab
    Then checkout successfully via stripe with cart "Shoes:37,Green>2"
      | Email                      | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | mappingadc1@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    When switch to the first tab
    Then user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    When user navigate to "Manage Orders" screen
    Then expand order "" in list ADC
    And open mapping product screen of "Shoes:37 / Green"
    When import the AliExpress link "https://www.aliexpress.com/item/4001201578250.html"
    And select duplicate Ali option set then verify the message is displayed "Duplicate option"
    And close browser

  Scenario: verify that the error message is displayed when the number of Ali options is less than Sbase option #SB_ADC_MP_4
    When user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title | Price |
      | Yonex | 25    |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Green, Red   |
    Given open storefront shop on a new tab
    Then checkout successfully via stripe with cart "Yonex:S,Green>3"
      | Email                      | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | mappingadc2@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    When switch to the first tab
    Then user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    When user navigate to "Manage Orders" screen
    Then expand order "" in list ADC
    And open mapping product screen of "Yonex:S / Green"
    When import the AliExpress link "https://www.aliexpress.com/item/4000505938957.html"
    And verify the message is display in mapping screen "*The number of AliExpress product options cannot be less than the number of Shopbase product options."


