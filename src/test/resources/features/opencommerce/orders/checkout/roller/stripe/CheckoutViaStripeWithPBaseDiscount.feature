Feature: Checkout with Pbase discount
#  sbase_pbase_hive_discount

  Background:
    Given clear all data

  Scenario: Checkout with Pbase discount percentage
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code            | Discount value | Discount type | POD discount | Free shipping setting |
      | S50_CPAY_TEST_AUTOMATION | 50             | Percentage    | yes          | no                    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

    # Update hive discount (Maximum discount amount for percentage, Flat discount)------------------------------------------
  Scenario: Check out with Pbase discount percentage + Maximum discount amount
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code                 | Discount value | Discount type | POD discount | Free shipping setting | Maximum discount amount |
      | S50Max40_CPAY_TEST_AUTOMATION | 50             | Percentage    | yes          | no                    | 40                      |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Check out with Pbase discount percentage + Maximum discount amount + PPC
    Given open shop on storefront
    And add products "Shopbase Auto" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code                 | Discount value | Discount type | POD discount | Free shipping setting | Maximum discount amount |
      | S50Max40_CPAY_TEST_AUTOMATION | 50             | Percentage    | yes          | no                    | 40                      |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "PPC" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Check out with Pbase flat discount
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code               | Discount value | Discount type | POD discount | Free shipping setting |
      | Flat70_CPAY_TEST_AUTOMATION | 70             | Flat Discount | yes          | no                    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Check out with Pbase flat discount + PPC
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code               | Discount value | Discount type | POD discount | Free shipping setting |
      | Flat70_CPAY_TEST_AUTOMATION | 70             | Flat Discount | yes          | no                    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "PPC" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser
# ----------------------------------------------------------------------------------------------------------------------

  Scenario: Checkout with Pbase discount percentage + PPC
    Given open shop on storefront
    And add products "Shopbase Auto" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code            | Discount value | Discount type | POD discount | Free shipping setting |
      | S50_CPAY_TEST_AUTOMATION | 50             | Percentage    | yes          | no                    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "PPC" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Checkout with Pbase discount percentage + free shipping setting
    Given open shop on storefront
    And add products "Shopbase Auto>10" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code            | Discount value | Discount type | POD discount | Free shipping setting |
      | S50_CPAY_TEST_AUTOMATION | 50             | Percentage    | yes          | yes                   |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Checkout with Pbase discount percentage + PPC + Free shipping setting
    Given open shop on storefront
    And add products "Shopbase Auto>10" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code            | Discount value | Discount type | POD discount | Free shipping setting |
      | S50_CPAY_TEST_AUTOMATION | 50             | Percentage    | yes          | yes                   |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "PPC" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Checkout with Pbase discount free shipping
    Given open shop on storefront
    And add products "Shopbase Auto>1" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code              | Discount value | Discount type | POD discount | Free shipping setting |
      | SFREE_CPAY_TEST_AUTOMATION | 0              | Free shipping | yes          | no                    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

  Scenario: Checkout with Pbase discount free shipping + PPC
    Given open shop on storefront
    And add products "Shopbase Auto>1" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code              | Discount value | Discount type | POD discount | Free shipping setting |
      | SFREE_CPAY_TEST_AUTOMATION | 0              | Free shipping | yes          | no                    |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "PPC" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | subtotal | tax |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify profit of order
      | Product Base info   | Production rate | Staging rate |
      | Short Sleeve Pyjama | 3,4             | 5,6          |
    And close browser

