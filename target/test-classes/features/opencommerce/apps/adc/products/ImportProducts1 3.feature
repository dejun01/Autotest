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

  @ADC_UI
  Scenario: Verify UI when when list of products is empty
    Then verify import list of ADC is empty
#    And verify "Install import product extension" button is displayed and working properly
    Then close browser

  @ADC_Crawl
  Scenario: Import products directly from AliExpress #SB_ADC_IP_8 #SB_ADC_IP_9
    And import product from AliExpress to ADC app
      | Product KEY | Testcase                                  | AliExpress Product                                 | Message                                                                                         |
      | 1           | Enter invalid url                         | https://www.aliexpress.com/Phone-Case/32978.html   | Please Enter an AliExpress product URL, such as https://www.aliexpress.com/items/123456789.html |
      | 2           | Enter valid url                           | https://www.aliexpress.com/item/32965802185.html   |                                                                                                 |
      | 3           | Enter url product is exist on Import list | https://www.aliexpress.com/item/32965802185.html   | This product has already been in Import list                                                    |
      | 4           | Enter valid url                           | https://www.aliexpress.com/item/4000586586232.html |                                                                                                 |
    And import product from ADC app to Store
      | Product KEY |
      | 4           |

  @ADC
  Scenario: Import/ remove multiple products in import list #SB_ADC_IP_10 #SB_ADC_IP_11 #SB_ADC_IP_13
    Given import product from AliExpress to ADC app
      | Product KEY | Testcase        | AliExpress Product                                 |
      | 1           | Enter valid url | https://www.aliexpress.com/item/4000562041498.html |
      | 2           | Enter valid url | https://www.aliexpress.com/item/4000543474966.html |
      | 3           | Enter valid url | https://www.aliexpress.com/item/4000381101052.html |
      | 4           | Enter valid url | https://www.aliexpress.com/item/32944560275.html   |
    When click on checkbox of product with KEY then Import to Store
      | Product KEY |
      | 1           |
      | 2           |
    And click on checkbox of product with KEY then Remove from list
      | Product KEY |
      | 3           |
      | 4           |


