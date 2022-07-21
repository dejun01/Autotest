Feature: Tipping options - Configure on dashboard
#env = tipping_option

  Background: Navigate to Payment Setting page
    Given clear all data
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen

  Scenario: Verify UI of tipping module on Dashboard
    And user choose not show tipping options
    Then verify UI of tipping module when not show tipping options
    And user choose show tipping options
    Then verify UI of tipping module when show tipping options
    Then verify input field tipping options without saving
      | Case          | Option 1 | Option 2 | Option 3 | Expected 1                               | Expected 2                                 | Expected 3                                 |
      | Wrong input 1 | -20      |          |          | Value must be greater than or equal to 0 |                                            |                                            |
      | Wrong input 2 |          | -30.123  |          |                                          | Value must be greater than or equal to 0   |                                            |
      | Wrong input 3 |          | -30.123  | -100.001 |                                          | Value must be greater than or equal to 0   | Value must be greater than or equal to 0   |
      | Wrong input 4 | -1       | -50      | 101      | Value must be greater than or equal to 0 | Value must be greater than or equal to 0   | Value must be smaller than or equal to 100 |
      | Wrong input 5 | 100      | 120      | 0        |                                          | Value must be smaller than or equal to 100 |                                            |
    Then verify input field tipping options with saving
      | Case          | Option 1 | Option 2 | Option 3   | Expected 1 | Expected 2 | Expected 3 |
      | Saving case 1 | 10       | 20       | 30         | 10         | 20         | 30         |
      | Saving case 2 | -10      | -20      | -100.00001 | 10         | 20         | 30         |
      | Saving case 3 | -1.123   | 20.123   | 50.55555   | 10         | 20.12      | 50.56      |
      | Saving case 4 | 120      | 20       | 0          | 10         | 20         | 0          |
      | Saving case 5 | 20.00001 | 30.12345 | 100.00001  | 20         | 30.12      | 100        |

  Scenario Outline: Verify UI of tipping module on StoreFront
    And user choose show tipping options
    Then input "<Option 1>","<Option 2>","<Option 3>" for each "<Case>" then save
      | Case                              | Expected 1 | Expected 2 | Expected 3 | Expected on SF 1 | Expected on SF 2 | Expected on SF 3 | Expected No Of Tipping on SF |
      | Dont have any tipping option      | 0          | 0          | 0          | 0                | 0                | 0                | 0                            |
      | Have only 1 tipping option case 1 | 10         | 0          | 0          | 10               | 0                | 0                | 1                            |
      | Have only 1 tipping option case 2 | 0          | 50         | 50         | 50               | 0                | 0                | 1                            |
      | Have 2 tipping option case 1      | 10         | 20         | 0          | 10               | 20               | 0                | 2                            |
      | Have 2 tipping option case 2      | 30         | 30         | 50         | 30               | 50               | 0                | 2                            |
      | Have 3 tipping option case 1      | 10         | 30         | 50         | 10               | 30               | 50               | 3                            |
      | Have 3 tipping option case 2      | 10.1       | 30.68      | 50.11      | 10.1             | 30.68            | 50.11            | 3                            |
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method "Standard shipping"
    When user choose to enable tipping
    Then verify tipping value on StoreFront
    Examples:
      | Case                              | Option 1 | Option 2 | Option 3     |
      | Dont have any tipping option      | 0        | 0        | 0            |
      | Have only 1 tipping option case 1 | 10       | 0        | 0            |
      | Have only 1 tipping option case 2 | 0        | 50.001   | 50.001       |
      | Have 2 tipping option case 1      | 10       | 20       | 0            |
      | Have 2 tipping option case 2      | 30       | 30       | 50           |
      | Have 3 tipping option case 1      | 10       | 30       | 50           |
      | Have 3 tipping option case 2      | 10.1     | 30.677   | 50.111111111 |

  Scenario Outline: Checkout contains tipping with or without discount
    And user choose show tipping options
    Then input "<Option 1>","<Option 2>","<Option 3>" for each "<Case>" then save
      | Case                     | Expected 1 | Expected 2 | Expected 3 | Expected on SF 1 | Expected on SF 2 | Expected on SF 3 | Expected No Of Tipping on SF |
      | Add tip without discount | 20         | 30         | 50         | 20               | 30               | 50               | 3                            |
      | Add tip with discount    | 25         | 45         | 65         | 25               | 45               | 65               | 3                            |
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method "Rick Kid Shipping"
    And apply discount code for for each "<Case>"
      | Case                     | Discount code |
      | Add tip without discount |               |
      | Add tip with discount    | DISCOUNT_20   |
    When user choose to enable tipping
    Then verify tipping value on StoreFront
    Then click to add 1st tipping option
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    And redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser
    Examples:
      | Case                     | Option 1 | Option 2 | Option 3 |
      | Add tip without discount | 20       | 30       | 50       |
      | Add tip with discount    | 25       | 45       | 65       |

  Scenario Outline: Checkout contains tipping with subtotal = 0
    And user choose show tipping options
    Then input "<Option 1>","<Option 2>","<Option 3>" for each "<Case>" then save
      | Case                      | Expected 1 | Expected 2 | Expected 3 | Expected on SF 1 | Expected on SF 2 | Expected on SF 3 | Expected No Of Tipping on SF |
      | Add tip with subtotal = 0 | 10         | 30         | 60         | 10               | 30               | 60               | 3                            |
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method "Standard shipping"
    And apply discount code for for each "<Case>"
      | Case                      | Discount code |
      | Add tip with subtotal = 0 | DISCOUNT_100  |
    When user choose to enable tipping
    Then verify tipping value on StoreFront
    And click on Complete order button
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee | discount value |
    And redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser
    Examples:
      | Case                      | Option 1 | Option 2 | Option 3 |
      | Add tip with subtotal = 0 | 10       | 30       | 60       |