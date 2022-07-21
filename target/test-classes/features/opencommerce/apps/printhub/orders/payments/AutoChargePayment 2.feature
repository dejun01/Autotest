Feature: Merchant wants to check their auto charge payments correctly
  #shop: au-ph-charge-payments.stag.myshopbase.net
  #acc: shopbase@beeketing.net/123456
  #environment:print_hub_auto_charge_payment

  Scenario Outline: Auto charge payment then verify status of orders #SB_PRH_403 #SB_PRH_404 #SB_PRH_424 #SB_PRH_429 #SB_PRH_431 #SB_PRH_440 #SB_PRH_438
    Given Description: "<Testcase>"
    And clear all data
    And call api to "Activate" payment method in Phub
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    And open shop on storefront
    Then checkout successfully via stripe with cart "Hooded Blanket + Quilt:Hooded Blanket,Youth>2;V-neck T-shirt:White,XS>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And switch to "Awaiting Payment" tab on Manage Orders of Print Hub
    And search order Print Hub
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
    And get information detail in Payments page Phub
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 0.00                |              | false             |
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    Then verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 0.00                | 0.00         | false             |
    And verify updated payments log Print Hub
    And verify data of payment log as "<KEY>"
      | KEY | Details  | Amount | Status |
      | 1   | 2 orders |        | Paid   |
    And redirect to payment details page Print Hub
    And verify data of payment details as "<KEY>"
      | KEY | Free type      | Details  | Amounts | Status of Timeline | Payment method   |
      | 1   | Purchase order | 2 orders |         | Paid               | ShopBase Balance |
    And user navigate to "Manage Orders" screen
    And switch to "Awaiting Shipment" tab on Manage Orders of Print Hub
    And search order Print Hub
    And verify information lineitems of orders with status "" Print Hub as "<KEY>"
      | KEY | Status line       | Product name           | Lineitems                                 | SKU                                              | Is sync |
      | 1   | Awaiting Shipment | Hooded Blanket + Quilt | Hooded Blanket / All over print / Youth   | PH-AOP-HoodedBlanket-Alloverprint-Youth          | true    |
      | 1   | Awaiting Shipment | V-neck T-shirt         | V-neck T-shirt / White / XS               |PH-AP-V-neckT-shirt-White-XS                      | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 1   | 37.97        | 18.97          |
    And close browser
    Examples: <Key>
      | KEY | Testcase                       |
      | 1   | Auto charge successful payment |

  Scenario Outline: Auto charge payment then verify status of orders is hold #SB_PRH_401 #SB_PRH_402 #SB_PRH_413 #SB_PRH_414 #SB_PRH_415 #SB_PRH_416
    Given Description: "<Testcase>"
    And clear all data
    And call api to "Activate" payment method in Phub
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    And open shop on storefront
    Then checkout successfully via stripe with cart "Hooded Blanket + Quilt:Hooded Blanket,Youth>2;V-neck T-shirt:White,XS>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And switch to "Awaiting Payment" tab on Manage Orders of Print Hub
    And search order Print Hub
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product        |
      | 2   | Hold   | V-neck T-shirt |
    And get total cost of order as "<KEY>"
      | KEY | Tab              |
      | 2   | Awaiting Payment |
    And user navigate to "Payments" screen
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 2   | 0.00                | 0.00         | false             |
    And count payment log Print Hub
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    And verify updated payments log Print Hub
    And verify data of payment log as "<KEY>"
      | KEY | Details | Amount | Status |
      | 2   | 1 order |        | Paid   |
    And redirect to payment details page Print Hub
    And verify data of payment details as "<KEY>"
      | KEY | Free type      | Details | Amounts | Status of Timeline | Payment method   |
      | 2   | Purchase order | 1 order |         | Paid               | ShopBase Balance |
    And user navigate to "Manage Orders" screen
    And switch to "Awaiting Shipment" tab on Manage Orders of Print Hub
    And search order Print Hub
    And verify information lineitems of orders with status "" Print Hub as "<KEY>"
      | KEY | Status line       | Product name           | Lineitems                               | SKU                                           | Is sync |
      | 2   | Awaiting Shipment | Hooded Blanket + Quilt | Hooded Blanket / All over print / Youth | PH-AOP-HoodedBlanket-Alloverprint-Youth       | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 2   | 37.97        | 18.97          |
    And close browser
    Examples: <Key>
      | KEY | Testcase              |
      | 2   | Auto charge when hold |

  Scenario Outline: Auto charge payment then verify status of orders is cancel #SB_PRH_407 #SB_PRH_408 #SB_PRH_417 #SB_PRH_418 #SB_PRH_419 #SB_PRH_420 #SB_PRH_421 #SB_PRH_422
    Given Description: "<Testcase>"
    And clear all data
    And call api to "Activate" payment method in Phub
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    And open shop on storefront
    Then checkout successfully via stripe with cart "Hooded Blanket + Quilt:Hooded Blanket,Youth>2;V-neck T-shirt:White,XS>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And switch to "Awaiting Payment" tab on Manage Orders of Print Hub
    And search order Print Hub
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product        |
      | 3   | Cancel | V-neck T-shirt |
    And get total cost of order as "<KEY>"
      | KEY | Tab              |
      | 3   | Awaiting Payment |
    And user navigate to "Payments" screen
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 3   | 0.00                | 0.00         | false             |
    And count payment log Print Hub
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    And verify updated payments log Print Hub
    And verify data of payment log as "<KEY>"
      | KEY | Details | Amount | Status |
      | 3   | 1 order |        | Paid   |
    And redirect to payment details page Print Hub
    And verify data of payment details as "<KEY>"
      | KEY | Free type      | Details | Amounts | Status of Timeline | Payment method   |
      | 3   | Purchase order | 1 order |         | Paid               | ShopBase Balance |
    And user navigate to "Manage Orders" screen
    And switch to "Awaiting Shipment" tab on Manage Orders of Print Hub
    And search order Print Hub
    And verify information lineitems of orders with status "" Print Hub as "<KEY>"
      | KEY | Status line       | Product name           | Lineitems                               | SKU                                            | Is sync |
      | 3   | Awaiting Shipment | Hooded Blanket + Quilt | Hooded Blanket / All over print / Youth | PH-AOP-HoodedBlanket-Alloverprint-Youth        | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 1   | 37.97        | 18.97          |
    And close browser
    Examples: <Key>
      | KEY | Testcase                |
      | 3   | Auto charge when cancel |

  Scenario Outline: Outstanding balance greater than 0 #SB_PRH_409 #SB_PRH_410
    Given Description: "<Testcase>"
    And clear all data
    And call api to "Activate" payment method in Phub
    And wait 5 second
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    And open shop on storefront
    And add products to cart then checkout as "<KEY>"
      | KEY | Product                                                          |
      | 1   | Hooded Blanket + Quilt:Hooded Blanket,Youth>2 |
      | 1   |V-neck T-shirt:White,XS>1                                        |

      | 2   | Hooded Blanket + Quilt:Hooded Blanket,Youth>2 |
      | 2   | V-neck T-shirt:White,XS>1                                        |

      | 3   | Hooded Blanket + Quilt:Hooded Blanket,Youth>2 |
      | 3   | V-neck T-shirt:White,XS>1                                        |
    And checkout by Stripe successfully on one page with "other" user
    And get all information order
    Then user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And get total cost of order as "<KEY>"
      | KEY | Tab              |
      | 1   | Awaiting Payment |
      | 2   | Awaiting Payment |
      | 3   | Awaiting Payment |
    And user navigate to "Payments" screen
    And call api to create next payment or charge payment Print Hub
    And get information detail in Payments page Phub
    And call api to "DeActivate" payment method in Phub
    And wait 5 second
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 56.94               | 0.00         | false             |
      | 2   | 56.94               | 0.00         | false             |
      | 3   | 56.94               | 0.00         | false             |
    And call api with action in order phub as "<KEY>"
      | KEY | Action | SupId |
      | 2   | hold   | 1     |
      | 3   | cancel | 1     |
    And user navigate to "Manage Orders" screen
    And get total cost of order as "<KEY>"
      | KEY | Tab              |
      | 2   | Awaiting Payment |
      | 3   | Awaiting Payment |
    And user navigate to "Payments" screen
    And call api to "Activate" payment method in Phub
    And wait 5 second
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    And verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 56.94               | 0.00         | true              |
      | 2   | 43.96               | 0.00         | true              |
      | 3   | 43.96               | 0.00         | true              |
    And verify data of payment log as "<KEY>"
      | KEY | Details   | Amount | Status  |
      | 1   | 2 orders  | 56.94  | Pending |
      | 2   | 2 orders   | 43.96  | Pending |
      | 3   | 1 order   | 43.96  | Pending |
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Manage Orders" screen
    And user navigate to "Payments" screen
    Then verify data of payment history table as "<KEY>"
      | KEY | Outstanding balance | Next payment | Is enable Pay now |
      | 1   | 0.00                | 0.00         | false             |
      | 2   | 0.00                | 0.00         | false             |
      | 3   | 0.00                | 0.00         | false             |
    And verify data of payment log as "<KEY>"
      | KEY | Details  | Amount | Status |
      | 1   | 2 orders | 56.94  | Paid   |
      | 2   | 2 orders | 43.96  | Paid   |
      | 3   | 1 order  | 43.96  | Paid   |
    And close browser
    Examples: <Key>
      | KEY | Testcase                                                  |
      | 1   | Auto charge successful payment when Outstanding balance>0 |
      | 2   | Auto charge when Outstanding balance>0 hold order         |
#      | 3   | Auto charge when Outstanding balance>0 cancle order       |
