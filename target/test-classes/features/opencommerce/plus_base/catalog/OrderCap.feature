Feature: Order cap
#  evn: order_cap

  Scenario: Verify product set Unavailable in odoo #ORC-SU-4 #ORC-SU-6 #ORC-SU-12
    Given user set Unavailable product in odoo
      | Product name              | Set unavaiable |
      | (Test product) product111 | false          |
    Then user login to plusbase dashboard
    And user navigate to "Catalog" screen
    And search product = "(Test product) product111" in catalog
    And CLick on product and verify information in catalog detail
      | Product name              | BTImportToYourStore |
      | (Test product) product111 | enable              |
    Given user set Unavailable product in odoo
      | Product name              | Set unavaiable |
      | (Test product) product111 | true           |
    And redirect to plusbase dashboard
    When user navigate to "Catalog" screen
    Given search product = "(Test product) product111" in catalog
    Then Verify info product out of stock in catalog list
      | Product name              | Status       |
      | (Test product) product111 | Out of stock |
    And CLick on product and verify information in catalog detail
      | Product name              | BTImportToYourStore |
      | (Test product) product111 | disable             |
    And close browser


  Scenario Outline: Verify product out of stock in private request #ORC-SU-7 #ORC-SU-5
    Given user set Unavailable product in odoo
      | Product name                      | Set unavaiable |
      | (Test product) Dress Women Summer | false          |
    Then user login to plusbase dashboard
    And user navigate to "Catalog>Private request" screen
    When Click button "Create product request"
    And Input link request product and verify
      | link                                                  | Request link                                          |
      | https://www.aliexpress.com/item/1005002460518123.html | https://www.aliexpress.com/item/1005002460518123.html |
    And click "Available" tab in private request list
    And Search product in private product list by "<KEY>"
      | KEY | product                           |
      | 4   | (Test product) Dress Women Summer |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                       | link                                                  | status    |
      | 4   | (Test product) Dress Women Summer | https://www.aliexpress.com/item/1005002460518123.html | Available |
    Then CLick on product name and verify button Import to your store
      | ProductName                       | Link                                                  | BTImportToStore      | Status | Variant                                         | Processing time | Product cost |
      | (Test product) Dress Women Summer | https://www.aliexpress.com/item/1005002460518123.html | Import to your store | Enable | Select variants to preview product cost & image | 1 day           | $1.00        |
    Given user set Unavailable product in odoo
      | Product name                      | Set unavaiable |
      | (Test product) Dress Women Summer | true           |
    And redirect to plusbase dashboard
    And user navigate to "Catalog>Private request" screen
    And Search product in private request list
      | Product name                      |
      | (Test product) Dress Women Summer |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                       | link                                                  | status       |
      | 4   | (Test product) Dress Women Summer | https://www.aliexpress.com/item/1005002460518123.html | Out of stock |
    Then verify product in private detail
      | Product name                      | Status       | Link                                                  | BTImportToYourStore |
      | (Test product) Dress Women Summer | Out of stock | https://www.aliexpress.com/item/1005002460518123.html | disabled            |
    And close browser
    Examples: <KEY>
      | KEY |
      | 4   |
