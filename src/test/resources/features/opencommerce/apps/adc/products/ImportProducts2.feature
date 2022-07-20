@mappingADC
Feature: Import list ADC
  #env = sbase_adc_import_products

  Background: delete all products
    Given user login to shopbase dashboard
#    And config runtime env
    Then user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    And user navigate to "Import List" screen
    And delete all products from import list of ADC

  @ADC_Customize_Product
  Scenario: Customize products in Import list then import them to ShopBase dashboard #SB_ADC_IP_2 #SB_ADC_IP_3 #SB_ADC_IP_4 #SB_ADC_IP_5 #SB_ADC_IP_6 #SB_ADC_IP_7 #SB_ADC_IP_12
    #1. Customize AliExpress product before importing to ShopBase dashboard
    And import product from AliExpress to ADC app
      | Product KEY | AliExpress Product                                 |
      | 1           | https://www.aliexpress.com/item/4000045852166.html |
    Then select tab "Product" then edit product information in import list
      | New title      |
      | Brave Sword 12 |
    Then select tab "Description" then edit product information in import list
      | New description |
      | Product         |
    Then select tab "Variants" then edit product information in import list
      | Column | New value             |
      | Price  | Multiply by = 2       |
      | Price  | Add to  = 3           |
      | Price  | Set new price = 89.99 |

    #2. Importing the above customized Ali product to ShopBase then verify
    And refresh page
    Then import product from ADC app to Store
      | Product KEY |
      | 1           |
    And verify that product information is displayed correctly on the product detail page


