Feature: Mass fulfill with CSV
#sbase_dashboard

  Background: Clear data
    Given clear all data

  Scenario: Import with at least 1 order, 1 SKU, quantity fulfill equal to quantity of order #SB_ORD_81 #SB_ORD_80 #SB_ORD_79 #SB_ORD_77 #SB_ORD_68
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU         | Status      | Quantity |
      | SHIRT202001 | Unfulfilled | 2        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                         |
      | @OrderName@,SHIRT202001,2,UR111111111CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 1           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 2                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU         | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001 | Fulfilled | 2        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    And quit browser

  Scenario: Import with at least 1 order, 2 SKU, quantity fulfill equal to quantity of order
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2; Victor-fulfill>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU          | Status      | Quantity |
      | SHIRT202001  | Unfulfilled | 3        |
      | VICTOR202001 | Unfulfilled | 3        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                          |
      | @OrderName@,SHIRT202001,2,UR111111111CN,TNT,  |
      | @OrderName@,VICTOR202001,1,UR111111112CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 2                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU          | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001  | Fulfilled | 2        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | VICTOR202001 | Fulfilled | 1        | UR111111112CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    And quit browser

  Scenario: Import with at least 1 order, 1 SKU, quantity fulfill greater than quantity of order
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU         | Status      | Quantity |
      | SHIRT202001 | Unfulfilled | 2        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                         |
      | @OrderName@,SHIRT202001,3,UR111111111CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 1           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 3                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU         | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001 | Fulfilled | 2        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    And quit browser

  Scenario: Import with at least 1 order, 1 SKU, quantity fulfill less than quantity of order #SB_ORD_65
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU         | Status      | Quantity |
      | SHIRT202001 | Unfulfilled | 2        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                         |
      | @OrderName@,SHIRT202001,1,UR111111111CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 1           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 1                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Partially Fulfilled" on order list
    Then Open order details and verify status = "Partially Fulfilled"
      | SKU         | Status      | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001 | Fulfilled   | 1        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | SHIRT202001 | Unfulfilled | 1        |                  |         |              |                                                                                                         |
    And quit browser

  Scenario: Import with at least 1 order, 2 SKU, SKUs are empty #SB_ORD_64
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2; Victor-fulfill>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU          | Status      | Quantity |
      | SHIRT202001  | Unfulfilled | 3        |
      | VICTOR202001 | Unfulfilled | 3        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                              |
      | @OrderName@,,2,UR111111111CN,TNT, |
      | @OrderName@,,1,UR111111112CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Uncheck   | Check        | @OrderName@ |              | 2                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU          | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001  | Fulfilled | 3        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | VICTOR202001 | Fulfilled | 3        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    And quit browser

  Scenario: Import with at least 1 order, 2 SKU, fulfill one of them #SB_ORD_63
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2; Victor-fulfill>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU          | Status      | Quantity |
      | SHIRT202001  | Unfulfilled | 3        |
      | VICTOR202001 | Unfulfilled | 3        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                         |
      | @OrderName@,SHIRT202001,1,UR111111111CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 1           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 1                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Partially Fulfilled" on order list
    Then Open order details and verify status = "Partially Fulfilled"
      | SKU          | Status      | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001  | Fulfilled   | 1        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | SHIRT202001  | Unfulfilled | 2        |                  |         |              |                                                                                                         |
      | VICTOR202001 | Unfulfilled | 2        |                  |         |              |                                                                                                         |
    And quit browser

  Scenario: Import with at least 1 order, 2 SKU, fulfill with group #SB_ORD_79
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2; Victor-fulfill>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU          | Status      | Quantity |
      | SHIRT202001  | Unfulfilled | 3        |
      | VICTOR202001 | Unfulfilled | 3        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                          |
      | @OrderName@,SHIRT202001,1,UR111111111CN,TNT,  |
      | @OrderName@,SHIRT202001,1,UR111111112CN,TNT,  |
      | @OrderName@,VICTOR202001,1,UR111111112CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 1                 | UR111111111CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU          | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001  | Fulfilled | 1        | UR111111111CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | SHIRT202001  | Fulfilled | 2        | UR111111112CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | VICTOR202001 | Fulfilled | 2        | UR111111112CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    And quit browser

  Scenario: Import with at least 1 order, 2 SKU, fulfill and overwrite, SKU overwrite is empty #SB_ORD_78
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2; Victor-fulfill>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU          | Status      | Quantity |
      | SHIRT202001  | Unfulfilled | 3        |
      | VICTOR202001 | Unfulfilled | 3        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                          |
      | @OrderName@,SHIRT202001,2,UR111111111CN,TNT,  |
      | @OrderName@,VICTOR202001,1,UR111111112CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 2                 | UR111111111CN   | TNT              |
    And Click Upload File
    And Import tracking number with value of Overwrite is "Check" and Notification is "Check"
      | Data                              |
      | @OrderName@,,2,UR111111113CN,TNT, |
      | @OrderName@,,1,UR111111114CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Check     | Check        | @OrderName@ |              | 2                 | UR111111113CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU          | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001  | Fulfilled | 2        | UR111111113CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | VICTOR202001 | Fulfilled | 1        | UR111111113CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
    And quit browser

  Scenario: Import with at least 1 order, 2 SKU, fulfill and overwrite #SB_ORD_74
    Given open firstShop on storefront
    Then checkout successfully via stripe with cart "T-shirt-fulfill>2; Victor-fulfill>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to firstShop dashboard
    Given user navigate to "Orders" screen
    Then verify fulfillment status = "Unfulfilled" on order list
    Then Open order details and verify status = "Unfulfilled"
      | SKU          | Status      | Quantity |
      | SHIRT202001  | Unfulfilled | 3        |
      | VICTOR202001 | Unfulfilled | 3        |
    Given user navigate to "Orders" screen
    And Import tracking number with value of Overwrite is "Uncheck" and Notification is "Check"
      | Data                                          |
      | @OrderName@,SHIRT202001,2,UR111111111CN,TNT,  |
      | @OrderName@,VICTOR202001,1,UR111111112CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Uncheck   | Check        | @OrderName@ | SHIRT202001  | 2                 | UR111111111CN   | TNT              |
    And Click Upload File
    And Import tracking number with value of Overwrite is "Check" and Notification is "Check"
      | Data                                          |
      | @OrderName@,SHIRT202001,2,UR111111113CN,TNT,  |
      | @OrderName@,VICTOR202001,1,UR111111114CN,TNT, |
    Then Verify preview popup mass fulfillment with data
      | Order | Fulfillment | Overwrite | Notification | Name        | Lineitem Sku | Lineitem Quantity | Tracking number | Shipping carrier |
      | 1     | 2           | Check     | Check        | @OrderName@ | SHIRT202001  | 2                 | UR111111113CN   | TNT              |
    And Click Upload File
    Then verify fulfillment status = "Fulfilled" on order list
    Then Open order details and verify status = "Fulfilled"
      | SKU          | Status    | Quantity | Tracking numbers | Carrier | Notification | URL                                                                                                     |
      | SHIRT202001  | Fulfilled | 2        | UR111111113CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
      | VICTOR202001 | Fulfilled | 1        | UR111111114CN    | TNT     | Check        | https://www.tnt.com/express/en_gc/site/shipping-tools/track.html?searchType=con&cons=@Tracking numbers@ |
