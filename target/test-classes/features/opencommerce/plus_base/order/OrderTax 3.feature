Feature: Fulfill order plusBase
  #env = fulfill_plusbase

  Scenario: Approve order tax
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    # login to template store to approve the order then verify
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And click on order name in unfulfilled orders list
    And verify order detail including
      | total | paid by customer |
    And verify tax amount in order
      | Type of tax | Tax amount |
      | US tax 5%   | $1.25      |