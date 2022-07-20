Feature: Import Ali product to store
  #plusbase_import_ali_to_store

  Scenario: Verify data product private when request link not Ali #SB_PLB_CTL_PR_17
    Given user login to shop dashboard
    Then user navigate to "plusbase/create-product-request" screen by url
    And user input request product link
      | Link                                    |
      | https://plusbasesku.stag.myshopbase.net |
    And wait to product is crawl and verify data
      | Link                                    | Product name          | Product cost | Status     | Ali product |
      | https://plusbasesku.stag.myshopbase.net | Untitled product name | Update later | Processing | false       |
    And go to product private detail "https://plusbasesku.stag.myshopbase.net"
    And get quotation name on Private product
    And cancel quotation
      | Quotation ID |
      | @quotation   |
    And delete SO on Odoo
      | Quotation  |
      | @quotation |
    And delete product on Odoo
      | Product                                 |
      | https://plusbasesku.stag.myshopbase.net |
    And close browser

  Scenario: Verify data product private when request link Ali #SB_PLB_CTL_PR_18 #SB_PLB_CTL_PR_27
    Given user login to shop dashboard
    Then user navigate to "plusbase/create-product-request" screen by url
    And user input request product link
      | Link                                                  |
      | https://www.aliexpress.com/item/1005003109083850.html |
    And wait to product is crawl and verify data
      | Link                                                  | Product name                                                                                                                   | Status    | Ali product |
      | https://www.aliexpress.com/item/1005003109083850.html | 2021 Casual autumn winter Pile collar thick maxi weater pullovers dress Women basic loose sweater female turtleneck long dress | Available | true        |
    And go to product private detail "2021 Casual autumn winter Pile collar thick maxi weater pullovers dress Women basic loose sweater female turtleneck long dress"
    And verify product cost variant and shipping information
      | Variant                       | Product cost | Ship to        | Is country | Fist item | Additional item | Count Country |
      | Color:Beige/Size:One Size     | $29.53       | Australia      | true       | $3.22     | $2.62           | 4             |
      | Color:Dark Grey/Size:One Size | $29.53       | Canada         | true       | $9.53     | $8.93           |               |
      | Color:black/Size:One Size     |              | United Kingdom | true       | $2.59     | $1.59           |               |
      | Color:Aubum/Size:One Size     |              | United States  | true       | $2.59     | $1.59           |               |
      |                               |              | Vietnam        | false      |           |                 |               |
      |                               |              | France         | false      |           |                 |               |
    And get quotation name on Private product
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email | Product URL                                           |
      | @Quotation@  | draft            |       | https://www.aliexpress.com/item/1005003109083850.html |
    And cancel quotation
      | Quotation ID |
      | @quotation   |
    And delete SO on Odoo
      | Quotation  |
      | @quotation |
    And delete product on Odoo
      | Product                                                                                                                        |
      | 2021 Casual autumn winter Pile collar thick maxi weater pullovers dress Women basic loose sweater female turtleneck long dress |
    And close browser

  Scenario: Verify country when request product Ali not support Canada #SB_PLB_CTL_PR_24
    Given user login to shop dashboard
    Then user navigate to "plusbase/create-product-request" screen by url
    And user input request product link
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002659030502.html |
    And wait to product is crawl and verify data
      | Link                                                  | Product name                                                                                                           | Status    | Ali product |
      | https://www.aliexpress.com/item/1005002659030502.html | 2021 Sexy Womens Shorts Shiny Elastic High Waist Shiny Faux PU Leather Short Pants Slim Hot Dance Clubwear Mini Shorts | Available | true        |
    And go to product private detail "2021 Sexy Womens Shorts Shiny Elastic High Waist Shiny Faux PU Leather Short Pants Slim Hot Dance Clubwear Mini Shorts"
    And verify product cost variant and shipping information
      | Variant | Product cost | Ship to        | Is country | Fist item | Additional item |
      |         |              | Australia      | failed     |           |                 |
      |         |              | Canada         | failed     |           |                 |
      |         |              | United Kingdom | true       |           |                 |
      |         |              | United States  | true       |           |                 |
    And get quotation name on Private product
    And cancel quotation
      | Quotation ID |
      | @quotation   |
    And delete SO on Odoo
      | Quotation  |
      | @quotation |
    And delete product on Odoo
      | Product                                                                                                                |
      | 2021 Sexy Womens Shorts Shiny Elastic High Waist Shiny Faux PU Leather Short Pants Slim Hot Dance Clubwear Mini Shorts |
    And close browser

  Scenario: Verify data product when 2 user request 1 link ali, link used to sourcing #SB_PLB_CTL_PR_28
    Given user login to shop dashboard
    Then user navigate to "plusbase/create-product-request" screen by url
    And user input request product link
      | Link                                                  |
      | https://www.aliexpress.com/item/1005003352765759.html |
    And wait to product is crawl and verify data
      | Link                                                  | Product name                                                                                                            | Status    | Ali product |
      | https://www.aliexpress.com/item/1005003352765759.html | Only Plus Blue Plaid Woolen Slim Dress Winter Women Half-high Collar A-line Office Lady Dresses Elegant Casual Vestidos | Available | No          |
    And go to product private detail "Only Plus Blue Plaid Woolen Slim Dress Winter Women Half-high Collar A-line Office Lady Dresses Elegant Casual Vestidos"
    And verify product cost variant and shipping information
      | Variant                   | Product cost | Ship to        | Is country | Fist item | Additional item |
      | Color:Beige/Size:One Size | $30.78       | Australia      | true       | $2.59     | $1.59           |
      | Color:black/Size:One Size | $30.78       | Canada         | true       | $38.14    | $38.04          |
      |                           |              | United Kingdom | true       | $2.59     | $1.59           |
      |                           |              | United States  | true       | $2.59     | $1.59           |
      |                           |              | Viet nam       | failed     |           |                 |
      |                           |              | France         | failed     |           |                 |
    And get quotation name on Private product
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                     | Product URL                                           |
      | @Quotation@  | draft            | trangnguyen@beeketing.net | https://www.aliexpress.com/item/1005003352765759.html |
    And cancel quotation
      | Quotation ID |
      | @quotation   |
    And delete SO on Odoo
      | Quotation  |
      | @quotation |
    And close browser
    Given user1 login to plusbase1 dashboard
    And go to product private detail "Only Plus Blue Plaid Woolen Slim Dress Winter Women Half-high Collar A-line Office Lady Dresses Elegant Casual Vestidos"
    And verify product cost variant and shipping information
      | Variant                   | Product cost | Ship to   | Is country | Fist item | Additional item |
      | Color:Beige/Size:One Size | $30.78       | Australia | true       | $2.59     | $1.59           |

  Scenario: Verify data product after sourcing
    Given user login to shop dashboard
    Then user navigate to "plusbase/private-request" screen by url
    And go to product private detail "Autumn Winter Elegant Vintage Dress Women French Lace Puff Sleeve Party Midi Dress Female Korean Style Slim Fairy Dress 2021"
    And verify product cost variant and shipping information
      | Variant               | Product cost | Ship to       | Is country | Fist item | Additional item |
      | Color:Apricot/Size:S  | $30.78       | United States | true       | $8.25     | $5.69           |
      | Color:Apricot/Size:M  | $34.10       |               |            |           |                 |
      | Color:Apricot/Size:L  | $30.50       |               |            |           |                 |
      | Color:Apricot/Size:XL | $30.70       |               |            |           |                 |
    And close browser

  Scenario: Verify data product in Import list #SB_PLB_CTL_PR_29
    Given user login to shop dashboard
    Then user navigate to "plusbase/import-products" screen by url
    And verify data pricing product in Import list
      | Product                                   | Ship to        | Shipping method   | Variant        | Cost   | First item | Additional item | Profit margin |
      | Colorfaith New 2020 Women's Autumn Winter | United Kingdom | Standard Shipping | black/One Size | $29.07 | 2.59       | 1.59            | - $27.98      |
      |                                           | United States  |                   | gray/One Size  | $29.07 | 2.59       | 1.59            | - $27.98      |
      |                                           | Canada         |                   | Pink/One Size  | $29.07 | 2.59       | 1.59            | - $27.98      |
      |                                           | Australia      |                   |                |        |            |                 |               |
    And close browser

  Scenario: Verify order of product Ali is not sourcing #SB_PLB_CTL_PR_30 #SB_PLB_CTL_PR_31
    Given open shop on storefront
    And checkout of order fulfillment successfully via stripe with cart "(Test product) Winter Warm Hooded Pajama Sets Double>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shop dashboard
    And user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    And verify data order detail of product Ali
      | Shipping | Product cost |
      | $2.59    | $46.67       |
    And close browser

  Scenario: Verify order is create after product Ali is sourcing #SB_PLB_CTL_PR_32
    Given open shop on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shop dashboard
    And user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    And verify data order detail of product Ali
      | Shipping | Product cost |
      |          |              |
    And close browser

  Scenario: Verify data product on SF after sourcing product Ali if not enough variant #SB_PLB_CTL_PR_35
    Given user login to shop dashboard
    Then user navigate to "plusbase/create-product-request" screen by url
    And user input request product link
      | Link                                                  |
      | https://www.aliexpress.com/item/1005001928275043.html |
    And wait to product is crawl and verify data
      | Link                                                  | Product name                                                                                                         | Status    | Ali product |
      | https://www.aliexpress.com/item/1005001928275043.html | Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean | Available | true        |
    And go to product private detail "Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean"
    And get quotation name on Private product
    And click button import to store
    And Add to store product Ali
      | Product                                                                                                              | Enable for buyers | Selling price |
      | Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean | true              | 15            |
    And update order line item of quotation
      | Quotation ID | email | Variant                                                                                                                            | Quantity | New quantity | New price | Validity | Based on total | Purchase order lead time | Minimum quantity |
      | @Quotation@  |       | Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean (Rose Red, S) | 1        | 1            | 3         | @day@    | false          | 4                        | 1                |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    And cancel quotation
      | Quotation ID |
      | @quotation   |
    And delete SO on Odoo
      | Quotation  |
      | @quotation |
    And delete product on Odoo
      | Product                                                                                                              |
      | Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean |
    And open shop on storefront
    And verify product on storefont
      | Product                                                                                                              | Variant | Disable |
      | Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean |         | true    |
      |                                                                                                                      |         | false   |
    And redirect to shopbase dashboard
    Then user navigate to "products" screen by url
    And delete product name = "Harajuku Punk Y2K Denim Mini Pleated Skirt Ladies Summer High Waist Jeans Shorts Skirts Women Ruffles Fashion Korean"
    And close browser