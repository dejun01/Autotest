Feature: Fulfill order plusBase
  #env = fulfill_plusbase

  Scenario: Verify info order after fulfill from order detail
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given staff login to shopbase dashboard
    Given user navigate to "ops/orders" screen by url
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And click on order name in unfulfilled orders list
    And fulfill order in Order detail PlusBase
    And Verify product ready to fulfill
      | Tab              | product                                        | shipping method               | shipping fee | estimated shipping time |
      | Ready to fulfill | Product test 2:style 1 red / M / United States | Yun Express Standard shipping | $2.86        | 8-14 business days      |
    And Fulfill order PlusBase
    Then Verify order detail after in fulfill
      | Tab                                    | product                                        |
      | Awaiting stock by PlusHub              | Product test 2:style 1 red / M / United States |
    And close browser

  Scenario: Verify info order after fulfill from order list
    Given staff login to shopbase dashboard
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory of product before checkout
      | Product           |
      | Auto product test |
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,M>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given redirect to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory before fulfill
      | Product                                                                                                                         | Quantity |
      | CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas | 1        |
    When user navigate to "Orders" screen
    And fulfill order with info
      | Action                            | Purchase auto | Button fulfill | Button selected order   |
      | Fulfill with PlusHub              | true          | Fulfill orders | Fulfill selected orders |
    Then Search and verify fulfillment status = "Processing" and Approve status = "approved" on order list
    When user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory after fulfill
      | Product                                                                                                                         | Quantity | Status     |
      | CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas | 1        | Processing |
    And close browser


