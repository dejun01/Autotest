#sbase_quotation_detail
Feature: Quotation Detail

  Scenario: Verify shipping method in quotation detail #SB_SBFF_FindProducts_7
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID      | Is show |
      | shipping standard | true    |
    Then verify shipping method default
      | Shipping method               |
      | Yun Express Standard shipping |
    Then open popup select shipping method in quotation detail
    Then verify shipping method on popup Select shipping method
      | Variant            | Country        | Estimated delivery time | Shipping company                | Is show |
      | Color:White/Size:M | United States  | 8-12 business days      | Yun Express Electronic shipping | true    |
      |                    |                | 8-14 business days      | Yun Express Standard shipping   | true    |
      |                    |                | 8-10 business days      | Yun Express Fast Shipping       | true    |
      | Color:White/Size:S |                | 8-12 business days      | Yun Express Electronic shipping | true    |
      |                    |                | 8-14 business days      | Yun Express Standard shipping   | true    |
      |                    |                | 8-10 business days      | Yun Express Fast Shipping       | true    |
      | Color:White/Size:M | United Kingdom | 6-10 business days      | Yun Express Electronic shipping | true    |
      |                    |                | 6-12 business days      | Yun Express Standard shipping   | true    |
      |                    |                | 5-8 business days       | Yun Express Fast Shipping       | true    |
      | Color:White/Size:S |                | 6-10 business days      | Yun Express Electronic shipping | true    |
      |                    |                | 6-12 business days      | Yun Express Standard shipping   | true    |
      |                    |                | 5-8 business days       | Yun Express Fast Shipping       | true    |
      | Color:White/Size:S | Zambia         | 8-10 business days      | Yun Express Electronic shipping | false   |
    And close browser

  Scenario: Verify shipping method in quotation detail of product is electronic #SB_SBFF_FindProducts_7
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID        | Is show |
      | shipping electronic | true    |
    Then verify shipping method default
      | Shipping method                 |
      | Yun Express Electronic shipping |
    Then open popup select shipping method in quotation detail
    Then verify shipping method on popup Select shipping method
      | Variant                         | Country        | Estimated delivery time | Shipping company                | Is show |
      | Color:light brown/Size:One Size | United States  | 8-12 business days      | Yun Express Electronic shipping | true    |
      | Color:light brown/Size:One Size |                | 8-14 business days      | Yun Express Standard shipping   | false   |
      | Color:light brown/Size:One Size | United Kingdom | 6-10 business days      | Yun Express Electronic shipping | true    |
      | Color:light brown/Size:One Size |                | 8-14 business days      | Yun Express Standard shipping   | false   |
    And close browser

  Scenario: Verify quotation detail with quotation is check Base on #SB_SBFF_FindProducts_6
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005003380840651.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Then user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID                                          | Is show |
      | https://www.aliexpress.com/item/1005003380840651.html | false   |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Submitted request"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005003380840651.html |
    And verify result after request product
      | Type  | Quotation ID | Link                                                  | Tab               |
      | Valid | @Quotation@  | https://www.aliexpress.com/item/1005003380840651.html | Submitted request |
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005003380840651.html |
    And update order line item of quotation
      | Quotation ID | email                  | Variant                                                        | Quantity | New quantity | New price | Validity | Based on total | Purchase order lead time | Minimum quantity | RMB Price |
      | @Quotation@  | shopbase@beeketing.net | (Test) 2021 Autumn Office Lady Single-Breasted Clothing (Blue) | 1        | 10           | 13        | @day@    | true           | 4                        | 1                | 1         |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And verify quotation detail
      | Name product                                            | Quantity-Price    | Variant                                          | Purchase order lead time | Expiration date |
      | (Test) 2021 Autumn Office Lady Single-Breasted Clothing | >= 10 items-$0.16 | Color:Blue/Size:One Size,Color:Red/Size:One Size | 4 days                   |                 |
    And close browser

  Scenario: Verify quotation detail with quotation is not check Base on #SB_SBFF_FindProducts_5
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005003380840651.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Submitted request"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005003380840651.html |
    And verify result after request product
      | Type  | Quotation ID | Link                                                  | Tab               |
      | Valid | @Quotation@  | https://www.aliexpress.com/item/1005003380840651.html | Submitted request |
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005003380840651.html |
    And update order line item of quotation
      | Quotation ID | email                  | Variant                                                        | Quantity | New quantity | New price | Validity | Based on total | Purchase order lead time | Minimum quantity |RMB Price |
      | @Quotation@  | shopbase@beeketing.net | (Test) 2021 Autumn Office Lady Single-Breasted Clothing (Blue) | 1        | 10           | 13        | @day@    | false          | 4                        | 1                |1         |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And verify quotation detail
      | Name product                                            | Quantity-Price    | Variant                  | Purchase order lead time | Expiration date |
      | (Test) 2021 Autumn Office Lady Single-Breasted Clothing | >= 10 items-$0.16 | Color:Blue/Size:One Size | 4 days                   |                 |
    And verify variant are disabled in quotation detail
      | Variant                 | Is disabled |
      | Color:Red/Size:One Size | true        |
    And close browser

  Scenario: Verify quotation detail with quotation has many line price #SB_SBFF_FindProducts_5
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005003380840651.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID    | Is show |
      | many line price | true    |
    And verify quotation detail
      | Name product                                 | Quantity-Price                      | Variant             | Purchase order lead time | Expiration date |
      | Aachoae Geometric Print A Line Vintage Dress | >= 1 item-$13.40;>= 10 items-$12.00 | Color:Print/Size:S  | 5 days                   | May 5, 2027     |
      |                                              | >= 1 item-$14.00                    | Color:Print/Size:M  |                          |                 |
      |                                              | >= 1 item-$14.60                    | Color:Print/Size:L  |                          |                 |
      |                                              | >= 1 item-$15.00                    | Color:Print/Size:XS |                          |                 |
    And close browser

  Scenario: Verify quotation detail with quotation is expired #SB_SBFF_FindProducts_3
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005003380840651.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Submitted request"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  | Msg                                                                                          |
      | https://www.aliexpress.com/item/1005003380840651.html | Request products successfully. We will send you the details via email shopbase@beeketing.net |
    And verify result after request product
      | Type  | Quotation ID | Link                                                  | Tab               |
      | Valid | @Quotation@  | https://www.aliexpress.com/item/1005003380840651.html | Submitted request |
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005003380840651.html |
    And update order line item of quotation
      | Quotation ID | email                  | Variant                                                        | Quantity | New quantity | New price | Validity   | Based on total | Purchase order lead time | Minimum quantity |RMB Price |
      | @Quotation@  | shopbase@beeketing.net | (Test) 2021 Autumn Office Lady Single-Breasted Clothing (Blue) | 1        | 10           | 15        | 2019-05-05 | false          | 4                        | 1                |1         |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "Expired" in Request list
    And search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And verify quotation detail
      | Name product                                            | Quantity-Price    | Variant                  | Purchase order lead time | Expiration date | Expired |
      | (Test) 2021 Autumn Office Lady Single-Breasted Clothing | >= 10 items-$0.16 | Color:Blue/Size:One Size | 4 days                   | May 5, 2019     | true    |
    And close browser










