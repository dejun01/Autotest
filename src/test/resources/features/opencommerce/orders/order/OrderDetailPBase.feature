#sbase_order_detail_pbase
Feature: Order Detail PBase

  Background: Access shop in storefront
    Given open shop on storefront

  Scenario: Verify order detail PBase with order has shipping fee #SB_OPB_33
    Then checkout of order fulfillment successfully via stripe with cart "Unisex T-shirt:Unisex T-shirt,White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Then user login to shopbase dashboard
    Then user navigate to "Orders" screen
    Then search order PBase "@order@" then select order
    And verify fulfillment status of order
      | Product        | Variant                    | SKU                         | Quantity |
      | Unisex T-shirt | Unisex T-shirt / White / S | PB-AP-UnisexT-shirt-White-S | 1        |
    And verify payment status of order
      | item subtotal | Discount code>freeship | Discount PPC | Shipping freeship |
      | 1 item        | no>no                  | no           | no                |
    And verify your profit of order Pbase
    And close browser

  Scenario: Verify order detail Pbase with order has shipping freeship
    Then checkout of order fulfillment successfully via stripe with cart "Unisex T-shirt:Unisex T-shirt,White,S>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Then user login to shopbase dashboard
    Then user navigate to "Orders" screen
    Then search order PBase "@order@" then select order
    And verify fulfillment status of order
      | Product        | Variant                    | SKU                         | Quantity |
      | Unisex T-shirt | Unisex T-shirt / White / S | PB-AP-UnisexT-shirt-White-S | 5        |
    And verify payment status of order
      | item subtotal | Shipping method   | Discount code>freeship | Discount PPC | Shipping freeship | Content |
      | 5 items       | Standard shipping | no>no                  | no           | yes               | Free    |
    And verify your profit of order Pbase
    And close browser

  Scenario: Verify order detail Pbase with order has discount freeship and shipping fee #SB_OPB_34 #SB_OPB_36
    Then checkout of order fulfillment successfully via stripe with cart "Unisex T-shirt:Unisex T-shirt,White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Freeship |
    Then user login to shopbase dashboard
    Then user navigate to "Orders" screen
    Then search order PBase "@order@" then select order
    And verify fulfillment status of order
      | Product        | Variant                    | SKU                         | Quantity |
      | Unisex T-shirt | Unisex T-shirt / White / S | PB-AP-UnisexT-shirt-White-S | 1        |
    And verify payment status of order
      | item subtotal | Discount code>freeship | Discount PPC | Shipping freeship |
      | 1 item        | yes>yes                | no           | no                |
    And verify your profit of order Pbase
    And close browser

  Scenario: Verify order detail Pbase with order has discount not freeship and shipping fee #SB_OPB_35
    Then checkout of order fulfillment successfully via stripe with cart "Unisex T-shirt:Unisex T-shirt,White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount   |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Discount30 |
    Then user login to shopbase dashboard
    Then user navigate to "Orders" screen
    Then search order PBase "@order@" then select order
    And verify fulfillment status of order
      | Product        | Variant                    | SKU                         | Quantity |
      | Unisex T-shirt | Unisex T-shirt / White / S | PB-AP-UnisexT-shirt-White-S | 1        |
    And verify payment status of order
      | item subtotal | Discount code>freeship | Discount PPC | Shipping freeship |
      | 1 item        | yes>no                 | no           | no                |
    And verify your profit of order Pbase
    And close browser

  Scenario: Verify order detail PBase with order has discount not freeship, freeship, post purchase discount
    Then checkout of order fulfillment successfully via stripe with cart "PrintBase multiple 2D products 4:Unisex T-shirt,White,S>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount   | post purchase |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Discount30 | PP discount   |
    Then user login to shopbase dashboard
    Then user navigate to "Orders" screen
    Then search order PBase "@order@" then select order
    And verify fulfillment status of order
      | Product                          | Variant                                 | SKU                                     | Quantity |
      | PrintBase multiple 2D products 4 | Unisex T-shirt / White / S              | PB-AP-UnisexT-shirt-White-S             | 5        |
      | PP discount                      | Sherpa Blanket / All over print / Large | PB-AOP-SherpaBlanket-Alloverprint-Large | 1        |
    And verify payment status of order
      | item subtotal | Shipping method   | Discount code>freeship | Discount PPC | Shipping freeship | Product post purchase | Content |
      | 6 items       | Standard shipping | yes>no                 | yes          | yes               | PP discount           | Free    |
    And verify your profit of order Pbase
    And close browser

  Scenario: Verify order detail PBase with order has discount freeship, shipping fee, post purchase discount #SB_OPB_37 #SB_OPB_39
    Then checkout of order fulfillment successfully via stripe with cart "PrintBase multiple 2D products 4:Unisex T-shirt,White,S>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount | post purchase |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | Freeship | PP discount   |
    Then user login to shopbase dashboard
    Then user navigate to "Orders" screen
    Then search order PBase "@order@" then select order
    And verify fulfillment status of order
      | Product                          | Variant                                 | SKU                                     | Quantity |
      | PrintBase multiple 2D products 4 | Unisex T-shirt / White / S              | PB-AP-UnisexT-shirt-White-S             | 2        |
      | PP discount                      | Sherpa Blanket / All over print / Large | PB-AOP-SherpaBlanket-Alloverprint-Large | 1        |
    And verify payment status of order
      | item subtotal | Discount code>freeship | Discount PPC | Shipping freeship | Product post purchase |
      | 3 items       | yes>yes                | yes          | no                | PP discount           |
    And verify your profit of order Pbase