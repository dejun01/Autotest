#sbase_update_quotation_detail
Feature: Update Quotation Detail

  Scenario: Verify request update quotation
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005002219461552.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Submitted request"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002219461552.html |
    And verify result after request product
      | Type  | Quotation ID | Link                                                  | Tab               |
      | Valid | @Quotation@  | https://www.aliexpress.com/item/1005002219461552.html | Submitted request |
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005002219461552.html |
    And update validity of quotation
      | Quotation ID | Validity |
      | @Quotation@  | @day@    |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And user request update quotation
      | reason              |
      | update product cost |
    Then Verify SO display status "Updating"
    And update order line item of quotation
      | Quotation ID | Email                  | Variant                                 | Quantity | New quantity | New price | Validity | Based on total | Purchase order lead time | Minimum quantity | Status   |
      | @Quotation@  | shopbase@beeketing.net | (Automation)Request update (Black, XXL) | 1        | 10           | 13        | @day@    | true           | 4                        | 1                | complete |
    And user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    Then Verify SO display status "Available"
    And close browser

  Scenario: Verify renew quotation
    And update quotation expired
      | Product URL                                           | Email                  | Validity   | Status   |
      | https://www.aliexpress.com/item/1005003169153563.html | shopbase@beeketing.net | 2020-12-12 | complete |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Expired" in Request list
    Then search quotation in Request list quotation
      | Product URL                                           | Is show |
      | https://www.aliexpress.com/item/1005003169153563.html | true    |
    And user renew quotation
    Then Verify SO display status "Updating"
    And update order line item of quotation
      | Product URL                                           | Email                  | Variant                  | Quantity | New quantity | New price | Validity | Based on total | Minimum quantity | Purchase order lead time | Status   |
      | https://www.aliexpress.com/item/1005003169153563.html | shopbase@beeketing.net | (Automation)Renew update | 1        | 10           | 12        | @day@    | true           | 1                | 4                        | complete |
    And user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Product URL                                           | Is show |
      | https://www.aliexpress.com/item/1005003169153563.html | true    |
    Then Verify SO display status "Available"
    And update order line item of quotation
      | Product URL                                           | Email                  | Variant                  | Quantity | New quantity | New price | Validity | Based on total | Minimum quantity | Purchase order lead time | Status   |
      | https://www.aliexpress.com/item/1005003169153563.html | shopbase@beeketing.net | (Automation)Renew update | 1        | 10           | 13        | @day@    | true           | 1                | 4                        | complete |
    And close browser

  Scenario: Verify markup pricing SO in Odoo
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005003171187148.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship" screen by url
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005003171187148.html |
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005003266531223.html |
    And update validity of quotation
      | Quotation ID | Validity |
      | @Quotation@  | @day@    |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And verify quotation detail
      | Name product                                                                                                                                      | Quantity-Price  | Variant            |
      | [Test production]Cutenew Lightning Print Asymmetrical Diagonal Collar Playsuits Women Fashion Sleeveless Skinny Stretch Jumpsuits Lady Streetwear | >= 1 item-$1.00 | Color:MULTI/Size:S |
    And update markup pricing
      | Quotation ID | Product cost | Domestic shipping |
      | @Quotation@  | 0            | 0                 |
      | @Quotation@  | 5            | 10                |
    And automatic cost calculation
      | Quotation ID | Automatic calculate cost |
      | @Quotation@  | true                     |
    And user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And verify quotation detail
      | Name product                                                                                                                                      | Quantity-Price   | Variant            |
      | [Test production]Cutenew Lightning Print Asymmetrical Diagonal Collar Playsuits Women Fashion Sleeveless Skinny Stretch Jumpsuits Lady Streetwear | >= 1 item-$16.20 | Color:MULTI/Size:S |
    And close browser

  Scenario: Verify price USD when config price RMB on Odoo
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005002219461552.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "Submitted request"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002219461552.html |
    And verify result after request product
      | Type  | Quotation ID | Link                                                  | Tab               |
      | Valid | @Quotation@  | https://www.aliexpress.com/item/1005002219461552.html | Submitted request |
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005002219461552.html |
    And update validity of quotation
      | Quotation ID | Validity |
      | @Quotation@  | @day@    |
    And sent by email quotation
      | Quotation ID |
      | @Quotation@  |
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    And user request update quotation
      | reason              |
      | update product cost |
    Then Verify SO display status "Updating"
    And update order line item of quotation
      | Quotation ID | Email                  | Variant                                 | Quantity | New quantity | New price | Validity | Based on total | Purchase order lead time | Minimum quantity | Status   | RMB Price|
      | @Quotation@  | shopbase@beeketing.net | (Automation)Request update (Black, XXL) | 1        | 10           | 13        | @day@    | true           | 4                        | 1                | complete |     62   |
    And user navigate to "fulfillment/dropship" screen by url
    Then user switch to tab "Quotation created" in Request list
    Then search quotation in Request list quotation
      | Quotation ID | Is show |
      | @Quotation@  | true    |
    Then Verify SO display Price USD "$10.00"
    And close browser
