Feature: Inside- Checkout via EU Stripe
#  sbase_checkout_EU_stripe_inside

  Background: clear data
    Given clear all data

  Scenario: Checkout via EU stripe successfully with Credit Cart without post-purchase item
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Belgium       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,Bancontact,SEPA Direct Debit"
    And choose payment method "Credit Card"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with Credit Cart with post-purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Belgium       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,Bancontact,SEPA Direct Debit"
    And choose payment method "Credit Card"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Bikini" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with Bancontact without post-purchase item
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Belgium       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,Bancontact,SEPA Direct Debit"
    And choose payment method "Bancontact"
    Then complete order on Stripe page
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with Bancontact with post-purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Belgium       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,Bancontact,SEPA Direct Debit"
    And choose payment method "Bancontact"
    Then complete order on Stripe page
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with SEPA Direct Debit without post-purchase item
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Belgium       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,Bancontact,SEPA Direct Debit"
    And choose payment method "SEPA Direct Debit"
    Then enter IBAN number "NL39 RABO 0300 0652 64" and complete order
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with SEPA Direct Debit with post-purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Belgium       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,Bancontact,SEPA Direct Debit"
    And choose payment method "SEPA Direct Debit"
    Then enter IBAN number "NL39 RABO 0300 0652 64" and complete order
    And order product "Bikini" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser


  Scenario: Checkout via EU stripe successfully with iDEAL without post-purchase item
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Netherlands   | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,iDEAL,SEPA Direct Debit"
    And choose payment method "iDEAL"
    Then choose "ABN Amro" bank
    Then complete order on Stripe page
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with iDEAL with post-purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Netherlands   | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,iDEAL,SEPA Direct Debit"
    And choose payment method "iDEAL"
    Then choose "ABN Amro" bank
    Then complete order on Stripe page
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with giropay without post-purchase item
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Germany       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,giropay,SEPA Direct Debit"
    And choose payment method "giropay"
    Then complete order on Stripe page
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via EU stripe successfully with giropay without post-purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Germany       | Santa Monica | 10000    | 2025550147 |
    And choose shipping method ""
    Then verify EU payment method has "Credit Card,giropay,SEPA Direct Debit"
    And choose payment method "giropay"
    Then complete order on Stripe page
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser