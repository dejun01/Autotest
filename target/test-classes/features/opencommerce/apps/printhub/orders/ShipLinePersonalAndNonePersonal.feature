Feature: Ship line personal and none personal
#ship_personal_and_none_personal
  Background: Access shop frontend
    Given open shopbase on storefront

  Scenario Outline: Verify order has 1 line personal and 1 line none personal. Cancel line personal
    Then checkout of order fulfillment successfully via stripe with cart "campaign personal;campaign none personal"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "In Review"
    And verify info order with
      | Product                | Status    | Total   | Is show |
      | campaign personal      | In Review | <Total> | true    |
      | campaign none personal | In Review | <Total> | true    |
    And user navigate to "orders" screen by url
    And move to order detail page
    And refund line personal in order with
      | Product           | Quantity |
      | campaign personal | 1        |
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "Awaiting Payment"
    And Search order in tab "In Review"
    And verify info order with
      | Product                | Status           | Total   | Is show |
      | campaign none personal | Awaiting Payment | <Total> | true    |
    And switch to "Cancelled" tab on Manage Orders of Print Hub
    And verify info order with
      | Product           | Status    | Total   | Is show |
      | campaign personal | Cancelled | <Total> | true    |

    Examples:
      | Total  |
      | $51.97 |

  Scenario Outline: Verify order has 1 line personal and 1 line none personal. Approved line personal
    Then checkout of order fulfillment successfully via stripe with cart "campaign personal;campaign none personal"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "In Review"
    And verify info order with
      | Product                | Status    | Total   | Is show |
      | campaign personal      | In Review | <Total> | true    |
      | campaign none personal | In Review | <Total> | true    |
    Given login to hive-pbase
    And redirect to order detail on hive-pbase
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And Search order in tab "Awaiting Payment"
    And verify info order with
      | Product                | Status           | Total  | Is show |
      | campaign personal      | Awaiting Payment | <Total> | true    |
      | campaign none personal | Awaiting Payment | <Total> | true    |

    Examples:
      | Total  |
      | $51.97 |