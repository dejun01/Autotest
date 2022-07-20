Feature: Merchant wants to check their manual charge payments correctly
  #shop: au-ph-charge-payments.stag.myshopbase.net
  #acc: shopbase@beeketing.net/123456
  #environment:staging_print_hub_manual_charge_payment

  Scenario Outline: Manual charge payment then verify status of orders #SB_PRH_388 #SB_PRH_389 #SB_PRH_390 #SB_PRH_393 #SB_PRH_395 #SB_PRH_397 #SB_PRH_398 #SB_PRH_425 #SB_PRH_427 #SB_PRH_428 #SB_PRH_437
    Given Description: "<Testcase>"
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Multiple product:Unisex T-Shirt,White,S>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And get total cost of order as "<KEY>"
      | KEY | Tab              |
      | 1   | Awaiting Payment |
    And user navigate to "Payments" screen
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 0.00                | 0.00         | false             |
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    And count payment log Print Hub
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 0.00                | 17.96        | false             |
    And view detail payment of next payment Print Hub
    And verify data of payment details as "<KEY>"
      | KEY | Free type      | Details | Amounts | Status of Timeline | Payment method   |
      | 1   | Purchase order | 1 order | 17.96   | Created            | ShopBase Balance |
    And paid payment at detail payment Print Hub
    And redirect from payment details to payments page Print Hub
#    Then verify data of payment history table as "<KEY>"
#      | KEY | Outstanding balance | Next payment | Is enable Pay now |
#      | 1   | 0.00                | 0.00         | false             |
    And verify updated payments log Print Hub
#    And verify data of payment log as "<KEY>"
#      | KEY | Details | Amount | Status |
#      | 1   | 1 order | 24.94  | Paid   |
#    And redirect to payment details page Print Hub
#    And verify data of payment details as "<KEY>"
#      | KEY | Free type      | Details | Amounts | Status of Timeline | Payment method   |
#      | 1   | Purchase order | 1 order | 24.94   | Paid               | ShopBase Balance |
    And user navigate to "Manage Orders" screen
    And switch to "Awaiting Shipment" tab on Manage Orders of Print Hub
    And search order Print Hub
    And verify information lineitems of orders with status "Awaiting Shipment" Print Hub as "<KEY>"
      | KEY | Product name     | Lineitems                  | SKU                         | Is sync |
      | 1   | Multiple product | Unisex T-shirt / White / S | PH-AP-UnisexT-shirt-White-S | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 1   | 14.97        | 9.97           |
    And close browser
    Examples: <Key>
      | KEY | Testcase                         |
      | 1   | Manual charge successful payment |

  Scenario Outline: Verify payment log after charge order success #SB_PRH_394 #SB_PRH_399 #SB_PRH_400 #SB_PRH_423 #SB_PRH_426 #SB_PRH_430 #SB_PRH_436 #SB_PRH_443 #SB_PRH_444 #SB_PRH_445
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/payment/list" screen by url
#    And redirect from payment details to payments page Print Hub
    Then verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 0.00                | 0.00         | false             |
    And verify data of payment log as "<KEY>"
      | KEY | Details | Amount | Status |
      | 1   | 1 order | 17.96  | Paid   |
    And redirect to payment details page Print Hub
    And verify data of payment details as "<KEY>"
      | KEY | Free type      | Details | Amounts | Status of Timeline | Payment method   |
      | 1   | Purchase order | 1 order | 17.96  | Paid               | ShopBase Balance |
    Examples: <Key>
      | KEY |
      | 1   |