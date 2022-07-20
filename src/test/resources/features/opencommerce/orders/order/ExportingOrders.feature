Feature: Exporting orders
#sbase_dashboard

  Scenario: Verify content of downloaded file should be matched with data dashboard #SB_ORD_85 #SB_ORD_84
    ## Place an order
    Given open firstShop on storefront
    And add products "T-shirt-fulfill>4" to cart then checkout
    And apply discount code
      | Discount code | Discount value | Discount type |
      | DISCOUNT_50   | 50             | Percentage    |
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    Then get all information after checkout
    Given staff login to firstShop dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And get the order information
    Then user navigate to "Orders" screen
    And search order by order name criteria
    And implement export "Current search" orders
    And verify that the content of file downloaded is matched with order information from dashboard
      | Name | Email | Financial Status | Fulfillment Status | Subtotal Price | Shipping | Total Tax | Total Price | Discount Codes | Discount Amount | Shipping Method | Lineitem Quantity | Lineitem Price | Lineitem Sku | Shipping Address1 | Shipping Address2 | Shipping Name | Shipping City | Shipping Zip | Shipping Province | Shipping Country | Shipping Phone |
    And close browser

  @exportOrders
  Scenario: Verify sent mail to merchant when export more than 50 orders #SB_ORD_89
    Given staff login to firstShop dashboard
    Then user navigate to "Orders" screen
    And implement export "Selected 100 orders" orders
    And staff login to email and open email with subject contains firstShop
    And verify that send mail to merchant with file name "order_export_yyyyMMdd"


