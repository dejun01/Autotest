Feature: Hold Order
  #sb_hold_order

  Scenario: Verify action Hold order / Release order in Orders list and Order detail #SB_ORD_SB-HO-_1 #SB_ORD_SB-HO-_2 #SB_ORD_SB-HO-_7
    Given user login to shopbase dashboard
    And user navigate to "orders" screen by url
    And search order by text "#Test-1012"
    And select order in Orders list
    And "Hold orders" order in Order list
    And select the tab "On Hold" in Orders list
    And verify order list after "hold" order
    And select the tab "All" in Orders list
    And search order by text "#Test-1012" then select
    And verify order detail after "hold" order
      | Product                                         |
      | Wholesale 10 discs D9 8.5 GB Gold>Default Title |
    And user navigate to "fulfillment/dropship/list" screen by url
    And verify order "#Test-1012" in Fulfill order page after "hold" order
    And user navigate to "orders" screen by url
    And search order by text "#Test-1012" then select
    And "Release order" order in Order detail
    And verify order detail after "release" order
      | Product                                         |
      | Wholesale 10 discs D9 8.5 GB Gold>Default Title |
    And user navigate to "orders" screen by url
    And select the tab "On Hold" in Orders list
    And search order by text "#Test-1012" on tab "On Hold"
    And verify order list after "release" order
    And user navigate to "fulfillment/dropship/list" screen by url
    And verify order "#Test-1012" in Fulfill order page after "release" order
    And close browser

  Scenario: Verify cannot hold order is fulfilled #SB_ORD_SB-HO-_3
    Given user login to shopbase dashboard
    And user navigate to "orders" screen by url
    And search order by text "#Test-1013"
    And select order in Orders list
    And "Hold orders" order in Order list
    And verify cannot hold order in Orders list
    And select the tab "On Hold" in Orders list
    And search order by text "#Test-1013" on tab "On Hold"
    And verify order list after "hold failed" order
    And close browser

  Scenario: Verify hold order has at least 1 line item unfulfilled #SB_ORD_SB-HO-_4
    Given user login to shopbase dashboard
    And user navigate to "orders" screen by url
    And search order by text "#Test-1015"
    And select order in Orders list
    And "Hold orders" order in Order list
    And select the tab "On Hold" in Orders list
    And verify order list after "hold" order
    And user navigate to "fulfillment/dropship/list" screen by url
    And verify order "#Test-1015" in Fulfill order page after "hold" order
    And user navigate to "orders" screen by url
    And search order by text "#Test-1015" then select
    And "Release order" order in Order detail
    And user navigate to "fulfillment/dropship/list" screen by url
    And verify order "#Test-1015" in Fulfill order page after "release" order
    And close browser

  Scenario: Verify action hold multi orders #SB_ORD_SB-HO-_5 #SB_ORD_SB-HO-_7
    Given user login to shopbase dashboard
    And user navigate to "orders" screen by url
    And search order by "Line item name" criteria with keyword "Professinal S966 GPS Sport Smart Watch"
    And select all orders
    And "Hold orders" order in Order list
    And user navigate to "fulfillment/dropship/list" screen by url
    And verify order "#Test-1007" in Fulfill order page after "hold" order
    And user navigate to "orders" screen by url
    And select the tab "On Hold" in Orders list
    And select all orders
    And "Release orders" order in Order list
    And verify order list after "release" order
    And close browser

  Scenario: Verify hold order has at least 1 line item processing / awaiting stock SB_ORD_SB-HO-_6
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Hydration 5L/2L Backpack;Wholesale 10 discs D9 8.5 GB Gold"
      | Email                   | First Name | Last Name | Address          | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane | United States | Los Angeles | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "orders" screen by url
    And search order by text "@order" then select
    Then fulfill order in Order detail
      | Lineitem                                        | Fulfillment Service |
      | Wholesale 10 discs D9 8.5 GB Gold>Default Title | PlusHub             |
    And user navigate to "orders" screen by url
    And search order by text "@order"
    And select order in Orders list
    And "Hold orders" order in Order list
    And select the tab "All" in Orders list
    And search order by text "@order" then select
    And verify order detail after "hold" order
      | Product                                  |
      | Hydration 5L/2L Backpack>5 L Grey Colors |
    And cancel fulfillment in Order detail
      | Product                                         |
      | Wholesale 10 discs D9 8.5 GB Gold>Default Title |
    And verify order detail after "cancel fulfillment" order
      | Product                                         |
      | Wholesale 10 discs D9 8.5 GB Gold>Default Title |
    And close browser