Feature: Request product
#staging_crosspanda_request_product

  Scenario: Search quotation in Request list
    Given Description: "<Test case>"
    Given login to crosspanda dashboard
    And user navigate to "Request list" screen in CrossPanda
    When search quotation in Request list
      | Quotation                                                        | Is show |
      |                                                                  | true    |
      | SO258961                                                         | true    |
      | Q100009                                                          | false   |
      | Sparkly Boho Evening Dress Long Off Shoulder Lace Up Bling Bling | true    |

  Scenario Outline: Request products
    Given Description: "<Test case>"
    Given login to crosspanda dashboard
    And user navigate to "Request products" screen in CrossPanda
    When verify contact information
      | Facebook                            | Skype | Phone | Other |
      | https://www.facebook.com/crosspanda |       |       |       |
    When request product in CrossPanda "<KEY>"
      | KEY | CaseLink  | Email   | Message                      | ProductURL                                                                                                                                                                                                |
      | 0   |           | @BLANK@ | Please enter a product link. |                                                                                                                                                                                                           |
      | 1   |           |         | Please enter a valid url.    | htttts:///crosspanda.com/products/cross                                                                                                                                                                   |
      | 2   | Duplicate |         |                              | https://www.aliexpress.com/item/32890457085.html,https://www.aliexpress.com/item/32890457085.html                                                                                                         |
      | 3   | One       |         |                              | https://www.aliexpress.com/item/4000079934427.html                                                                                                                                                        |
      | 4   | One       |         |                              | https://tiki.vn/dam-nu-co-tru-hoa-la-haint-boutique-28-p30686837.html                                                                                                                                     |
      | 5   | One       |         |                              | https://www.aliexpress.com/item/4001065281927.html,https://www.aliexpress.com/item/4000903301189.html,https://www.aliexpress.com/item/32831433875.html,https://www.aliexpress.com/item/4000938839361.html |
    And verify quotation created in crosspanda "<KEY>"
      | KEY | Link                                                                                                                                                                                                      |
      | 2   | https://www.aliexpress.com/item/32890457085.html                                                                                                                                                          |
      | 3   | https://www.aliexpress.com/item/4000079934427.html                                                                                                                                                        |
      | 4   | https://tiki.vn/dam-nu-co-tru-hoa-la-haint-boutique-28-p30686837.html                                                                                                                                     |
      | 5   | https://www.aliexpress.com/item/4001065281927.html,https://www.aliexpress.com/item/4000903301189.html,https://www.aliexpress.com/item/32831433875.html,https://www.aliexpress.com/item/4000938839361.html |
    And I should be able to see SO with name "<KEY>"
      | KEY | Email | Quotation | ProductURL                                                                                                                                                                                                |
      | 2   |       |           | https://www.aliexpress.com/item/32890457085                                                                                                                                                               |
      | 3   |       |           | https://www.aliexpress.com/item/4000079934427.html                                                                                                                                                        |
      | 4   |       |           | https://tiki.vn/dam-nu-co-tru-hoa-la-haint-boutique-28-p30686837.html                                                                                                                                     |
      | 5   |       |           | https://www.aliexpress.com/item/4001065281927.html,https://www.aliexpress.com/item/4000903301189.html,https://www.aliexpress.com/item/32831433875.html,https://www.aliexpress.com/item/4000938839361.html |
    And That sale order "<KEY>" contains product
      | KEY | Email | Quotation | ProductURL                                                                                                                                                                                                |
      | 2   |       |           | https://www.aliexpress.com/item/32890457085.html                                                                                                                                                          |
      | 3   |       |           | https://www.aliexpress.com/item/4000079934427.html                                                                                                                                                        |
      | 4   |       |           | https://tiki.vn/dam-nu-co-tru-hoa-la-haint-boutique-28-p30686837.html                                                                                                                                     |
      | 5   |       |           | https://www.aliexpress.com/item/4001065281927.html,https://www.aliexpress.com/item/4000903301189.html,https://www.aliexpress.com/item/32831433875.html,https://www.aliexpress.com/item/4000938839361.html |
    And cancel SO on Odoo "<KEY>"
      | KEY | Quotation |
      | 2   |           |
      | 3   |           |
      | 4   |           |
      | 5   |           |
    And delete SO on Odoo "<KEY>"
      | KEY | Quotation |
      | 2   |           |
      | 3   |           |
      | 4   |           |
      | 5   |           |
    And close browser
    Examples:
      | KEY | Test case                           |
#      | 0   | Link product is null                |
#      | 1   | Link product invalid                |
#      | 2   | Duplicate link product              |
      | 3   | Link product is get from AliExpress |
#      | 4   | Link product is non-ali link        |
#      | 5   | More than 3 links product           |