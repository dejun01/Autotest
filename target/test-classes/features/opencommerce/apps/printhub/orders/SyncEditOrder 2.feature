Feature: Sync edit order phub
#  evn: sync_edit_order

  Scenario: Verify update quantity printhub #SB_ORD_EO_115 #SB_ORD_EO_114 #SB_ORD_EO_113
    Given open shop on storefront
    And checkout of order fulfillment successfully via stripe with cart "(Auto)Summer Backless Dress"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    When user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub in tab "Awaiting Payment"
    Then user navigate to "Orders" screen
    And Search and select order in order list
    And Edit order in order detail
      | Option edit     | Quantity | Button name  | Status order |
      | Adjust quantity | 4        | Send invoice | Pending      |
    Then user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub in tab "In Review"
    And Verify order has been edited
    Then user navigate to "Orders" screen
    And Search and select order in order list
    Then Buy more products after updating order
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And call api to create next payment or charge payment Print Hub
    And call api to create next payment or charge payment Print Hub
    Then redirect to shopbase dashboard
    When user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub in tab "Awaiting Shipment"
    Then user navigate to "Orders" screen
    And Search and select order in order list
    And Edit order in order detail
      | Option edit     | Quantity | Button name  |
      | Adjust quantity | 6        | Send invoice |
    And close browser


  Scenario: Verify update variant printhub #SB_ORD_EO_116
    Given open shop on storefront
    And checkout of order fulfillment successfully via stripe with cart "(Auto)Summer Backless Dress"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    When user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub in tab "Awaiting Payment"
    Then user navigate to "Orders" screen
    And Search and select order in order list
    And Edit order in order detail
      | Option edit    | Quantity | Button name  | Status order | Product name    | Variant replace    | Change product                                     |
      | Change variant |          | Send invoice | Pending      | CNYISHE Fashion | Color:dress,Size:L | CNYISHE Fashion Floral Mesh Sheer Long Sleeve Body |
    Then user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub in tab "In Review"
    And Verify order has been edited
    And close browser

  Scenario: Verify hold order printhub #SB_ORD_EO_113 #SB_ORD_EO_114
    Given open shop on storefront
    And checkout of order fulfillment successfully via stripe with cart "(Auto)Summer Backless Dress"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    When user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "Awaiting Payment"
    Then user navigate to "Orders" screen
    And Search and select order in order list
    And Edit order in order detail
      | Option edit     | Quantity | Button name  | Status order |
      | Adjust quantity | 4        | Send invoice | Pending      |
    Then user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "In Review"
#    And search order Print Hub in tab "In Review"
    And Verify order has been edited
    And user "Hold" order in tab "In Review"
    And user "Unhold" order in tab "On Hold"
    And search order Print Hub in tab "In Review"
    Then user navigate to "Orders" screen
    And Search and select order in order list
    Then Buy more products after updating order
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then redirect to shopbase dashboard
    When user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "Awaiting Payment"
#    And search order Print Hub in tab "Awaiting Payment"
    And close browser








