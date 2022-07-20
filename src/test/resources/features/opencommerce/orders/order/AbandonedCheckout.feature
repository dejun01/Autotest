@AbandonedCheckoutDetail
Feature: Abandoned checkout
  #env = sbase_abandoned_detail

  Background: create an abandoned checkout
    Given clear all data
    Given open shop on storefront
    Then add products "URBAN GIRL T-shirt" to cart then checkout
    And input Customer information
      | Email                      | First Name | Last Name | Address       | City    | Country       | State | Zip code | Phone      |
      | @mailinator.girl-viet.com@ | Emma       | Watson    | 1600 W Loop S | Houston | United States | Texas | 77027    | 2056289809 |
    And choose shipping method ""
    And get checkout url
    Given user login to shopbase dashboard
    Then user navigate to "Orders>Abandoned checkouts" screen

  Scenario: Verify abandoned checkout detail
    And search then select abandoned checkout by customer email
    And verify that abandoned checkouts details on dashboard should be the same as storefront
    And close browser

  Scenario: Verify abandoned recover cart link can checkout successfully #SB_ORD_ABC_32
    And search then select abandoned checkout by customer email
    When open recovery link in a new tab
#    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page

    Then switch to the first tab
    Then user navigate to "Orders" screen
    And verify that the abandoned checkout should be convert to order

    Then user navigate to "Orders>Abandoned checkouts" screen
    And search abandoned checkout by customer email
    And verify recover status is "Recovered"
    And close browser

  Scenario: verify search abandoned checkout by customer name, customer email, checkout id #SB_ORD_ABC_1
    And search then select abandoned checkout by customer email
    Then back to the list of abandoned checkouts
    And search checkouts by the below mentioned criteria then verify search function working properly
      | Criteria         |
      | customer name    |
      | checkout id      |
      | shipping address |


