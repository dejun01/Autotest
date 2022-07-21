Feature: Catalog detail
#env = plbase_catalog_detail
  Scenario: Verify data product detail when check Base on
    Given user login to plusbase dashboard
    When user navigate to "Catalog" screen
    When move to "Collections" screen in plusbase
    Then search product PlBase "Dress 2021 summer" then select product
    Then Verify data of product on catalog detail screen
      | Product name      | Tags | Variants           | Processing time | Product cost | Selling price | Profit margin | Description | About this product      | Ship to        |
      | Dress 2021 summer | HKT  | Color/White;Size/M | 5 days          | $4.00        | $5.00         | 1.00          | Dress 2021  | Dress 2021 summer style | United Kingdom |
      | Dress 2021 summer | HKT  | Color/White;Size/L | 5 days          | $2.00        | $5.00         | 1.00          | Dress 2021  | Dress 2021 summer style | United Kingdom |
#    And Select ship to "United Kingdom"
#    And Verify Shipping method
#      | Shipping method                 | Shipping fee | Estimated delivery time |
#      | Yun Express Electronic shipping | $2           | 8-12 business days      |
#      | Yun Express Standard shipping   | $2.86        | 8-12 business days      |
   And import to import list page

  Scenario: Verify data product detail when uncheck Base on
    Given user login to plusbase dashboard
    When user navigate to "Catalog" screen
    When move to "Collections" screen in plusbase
    Then search product PlBase "2020 New Laptop Usb" then select product
    Then Verify data of product on catalog detail screen
      | Product name        | Tags | Variants                     | Processing time | Product cost | Selling price | Profit margin | Description         | About this product  | Ship to            |
      | 2020 New Laptop Usb | SJ   | Color/Black;Ships From/China | 5 days          | $4.00        | $5.00         | 1.00          | 2020 New Laptop Usb | 2020 New Laptop Usb | United States (US) |
      | 2020 New Laptop Usb | SJ   | Color/Red;Ships From/China   | 5 days          | $2.00        | $5.00         | 3.00          | 2020 New Laptop Usb | 2020 New Laptop Usb | United States (US) |
#    And Select ship to "United Kingdom"
#    And Verify Shipping method
#      | Shipping method                 | Shipping fee | Estimated delivery time |
#      | Yun Express Electronic shipping | $2           | 8-12 business days      |
#      | Yun Express Standard shipping   | $2.86        | 8-12 business days      |
    And import to import list page
