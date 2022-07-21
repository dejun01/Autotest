Feature: Mass fulfill with CSV Multi Store
#sbase_dashboard

  Scenario: Checkout - Prepare data SB_ORD_FOD_88 SB_ORD_FOD_87 SB_ORD_FOD_86 SB_ORD_FOD_85
    Given open firstShop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "Shirt>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given open secondShop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "Victor-fulfill>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to secondShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list of the second shop
    Then Open order details and verify status = "Unfulfilled" of the second shop
      | SKU          | Status      | Quantity |
      | VICTOR202001 | Unfulfilled | 2        |
    Given redirect to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list of the first shop
    Then Open order details and verify status = "Unfulfilled" of the first shop
      | SKU         | Status      | Quantity |
      | SHIRT202001 | Unfulfilled | 2        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                                     |
      | @Domain1@,@OrderName1@,SHIRT202001,2,UR111111111CN,TNT,  |
      | @Domain2@,@OrderName2@,VICTOR202001,2,UR222222222CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name         | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier | Domain |
      | 2     | 2           | Uncheck   | Check        | @OrderName1@ | SHIRT202001  | 2                 | UR111111111CN   | TNT              | first  |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list of the first shop
    Then Open order details and verify status = "Fulfilled" of the first shop
      | SKU         | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001 | Fulfilled | 2        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    Then redirect to secondShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Fulfilled" on order list of the second shop
    Then Open order details and verify status = "Fulfilled" of the second shop
      | SKU          | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | VICTOR202001 | Fulfilled | 2        | UR222222222CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    