#sbase_request_list
Feature: Request List

  Scenario: Verify quotation tab Submitted request in Request list #SB_SBFF_FindProducts_1
    Given user login to shopbase dashboard
    And delete SO on Odoo with
      | Product URL                                           | Email                   |
      | https://www.aliexpress.com/item/1005002540272590.html | shopbase1@beeketing.net |
    And user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Submitted request"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002540272590.html |
    And verify result after request product
      | Type  | Quotation ID | Tab               | Link                                                  | Product                                               | Minimum price | Expiration | Request at |
      | Valid | @Quotation@  | Submitted request | https://www.aliexpress.com/item/1005002540272590.html | Dress Women Mini Sheath Black Solid Korean Style Sexy | $1.00         |            | @day@      |
    And close browser

  Scenario: Verify quotation tab Quotation created in Request list #SB_SBFF_FindProducts_2
    Given user login to shopbase dashboard
    And delete SO on Odoo with
      | Product URL                                           | Email                   |
      | https://www.aliexpress.com/item/1005002263382413.html | shopbase1@beeketing.net |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Quotation created"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002263382413.html |
    And update order line item of quotation
      | Quotation ID | email | Variant                                               | Quantity | New quantity | New price | Validity | Based on total | Purchase order lead time | Minimum quantity |
      | @Quotation@  |       | Skirts Women Split Elegant Office Ladies Spring Mujer | 1        | 0            | 3         | @day@    | true           | 4                        | 1                |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    And verify result after request product
      | Type  | Quotation ID | Tab               | Link                                                  | Product                                               | Minimum price | Expiration | Request at |
      | Valid | @Quotation@  | Quotation created | https://www.aliexpress.com/item/1005002263382413.html | Skirts Women Split Elegant Office Ladies Spring Mujer | $1.00         | @day@      | @day@      |
    And close browser

  Scenario: Verify quotation tab Expired in Request list #SB_SBFF_FindProducts_3
    Given user login to shopbase dashboard
    And delete SO on Odoo with
      | Product URL                                           | Email                   |
      | https://www.aliexpress.com/item/1005003512177775.html | shopbase1@beeketing.net |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Expired"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005003512177775.html |
    And update order line item of quotation
      | Quotation ID | email | Variant                                                       | Quantity | New quantity | New price | Validity   | Based on total | Purchase order lead time | Minimum quantity |
      | @Quotation@  |       | Korean Plaid Skirts High Waist Pleated Skirt Short Micro Skirt | 1        | 10           | 1        | 2021-03-03 | true           | 4                        | 1                |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    And verify result after request product
      | Type  | Quotation ID | Tab     | Link                                                  | Product                                                         | Minimum price | Expiration  | Request at |
      | Valid | @Quotation@  | Expired | https://www.aliexpress.com/item/1005003512177775.html | Korean Plaid Skirts High Waist Pleated Skirt Short Micro Skirt | $1.00        | Mar 3, 2021 | @day@      |
    And close browser

  Scenario: Verify quotation tab No result in Request list #SB_SBFF_FindProducts_4
    Given user login to shopbase dashboard
    And delete SO on Odoo with
      | Product URL                                           | Email                   |
      | https://www.aliexpress.com/item/1005002705274894.html | shopbase1@beeketing.net |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "No result"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002705274894.html |
    And update order line item of quotation
      | Quotation ID | email | Variant                                                      | Quantity | New quantity | New price | Validity   | Based on total | Purchase order lead time | Minimum quantity |
      | @Quotation@  |       | Sexy Upskirt Solid Color Soft Silk Lotus Leaf Pendulum       | 1        | 10           | 1         | 2021-03-03 | true           | 4                        | 1                |
    And cancel quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "No result" in Request list
    And verify result after request product
      | Type  | Quotation ID | Tab       | Link                                                  | Product                                               | Minimum price | Expiration  | Request at |
      | Valid | @Quotation@  | No result | https://www.aliexpress.com/item/1005002705274894.html | Sexy Upskirt Solid Color Soft Silk Lotus Leaf Pendulum | $1.00        | Mar 3, 2021 | @day@      |
    And close browser

  Scenario: Verify result when search source url
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID | Source url                                            | Is show |
      |              | https://www.aliexpress.com/item/1005003490823475.html | true    |
    And close browser

  Scenario: Verify result when search product name
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID | Product name                                     | Is show |
      |              | (AutoTest) Women Sandals Fashion Wedges Platform | true    |
    And close browser

  Scenario: Verify result when search key is not exist
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID | Is show |
      | AFSFDSGDG    | false   |
    And close browser


