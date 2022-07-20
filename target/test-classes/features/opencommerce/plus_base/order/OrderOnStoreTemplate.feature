Feature: Order on store template
  #env = fulfill_plusbase

  Scenario: Approve a order success with order capture payment success
    Given open plusbase on storefront
    Then checkout successfully with cart "(Test product) Automation Dress Fesher Hide Double>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given staff login to shop dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And search and select order on plusbase
    Then verify order detail on store
    And close browser

  Scenario: Approve multi order success with order capture payment success
    Given open plusbase on storefront
    Then checkout successfully via stripe with cart "Product test 3>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given open plusbase on storefront
    Then checkout successfully via stripe with cart "Product test 2>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search multi order and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    And Choose multi order actions
    And "Approve order" order
    Then Search multi orders and verify payment status = "Authorized" and Approve status = "approving" on order list
    And close browser

  Scenario: Approve fail with order capture payment fail
    Given open plusbase on storefront
    And add products "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country | City        | Zip code | State | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Germany | Los Angeles | 90001    |       | 2025550141 |
    And choose shipping method ""
#    And choose shipping method order plusbase ""
    And input payment information and complete order plusBase
      | Payment method    | Payment gateway   | code                   |
      | SEPA Direct Debit | SEPA Direct Debit | DE62370400440532013001 |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Payment in process" and Approve status = "unapproved" on order list
    And close browser

  Scenario: Verify hold a order success and approve order in status on hold
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Hold" order
    Then Search and verify payment status = "Authorized" and Approve status = "on_hold" on order list
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And close browser

  Scenario: Verify hold bulk order success
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    And Choose multi order actions
    And "Hold" order
    Then Search multi orders and verify payment status = "Authorized" and Approve status = "on_hold" on order list
    And close browser
