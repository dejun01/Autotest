Feature: Inside- Checkout via Printbase testmode
#  sbase_pbase_checkout_testmode


  Scenario: Checkout with Printbase testmode successfully via Credit Cart without post-purchase item
    Given open shop on storefront
    And add products "1:S" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4000000000000259 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number |


  Scenario: Checkout with Printbase testmode successfully via Credit Cart with post-purchase item
    Given open shop on storefront
    And add products "Shopbase Auto:S" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And choose payment method "Card"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "1" with custom option is "" in post purchase offer
    And get the order information including
      | order number |

  Scenario: Checkout with Printbase testmode successfully via Paypal without post-purchase item
    Given open shop on storefront
    And add products "1:S" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | standard  |
    And get the order information including
      | order number |


  Scenario: Checkout with Printbase testmode successfully via Paypal with post-purchase item
    Given open shop on storefront
    And add products "Shopbase Auto:S" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | standard  |
    And order product "1" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number |

