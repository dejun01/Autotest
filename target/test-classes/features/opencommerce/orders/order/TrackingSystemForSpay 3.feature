Feature: Update tracking system for Spay
 # evn = spay_tracking_system

  Scenario Outline: Mark as fulfill order with shipping career
    Given open shop on storefront
    And add products "t-shirt>4" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    And fulfill the order with "<Fulfilled Items>", "<Tracking number>", "<Shipping carrier>", "<TrackingUrl>" and "<Status after fulfilling>"
    Then verify shippment status on order detail
    Examples:
      | Fulfilled Items         | Tracking number | Shipping carrier | TrackingUrl | Status after fulfilling |
      | t-shirt>4               | testautomoved   | China Post       |             | Fulfilled               |


  Scenario: Mark as fulfill order with post purchase with shipping career
    Given open shop on storefront
    And add products "t-shirt>4" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    And fulfill the order with "t-shirt>3", "testautomoved", "China Post", "" and "Partially Fulfilled"
    And fulfill the order with "t-shirt>1", "LP144111643CN", "China Post", "" and "Fulfilled"
    Then verify shippment status on order detail
