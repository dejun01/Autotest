@AbandonedCheckoutUnsubscribeEmail
  #sbase_unsubscribe_abandoned_email
Feature: Unsubscribe abandoned checkout 3 emails

  Scenario: Create a new abandoned checkout for next time
    Given open shop on storefront
    Then add products "Shirt" to cart then navigate to checkout page
    And input Customer information
      | Abandoned Email       | First Name | Last Name | Address       | City    | Country       | State | Zip code | Phone      |
      | unsubscribe_abandoned | Emma       | Watson    | 1600 W Loop S | Houston | United States | Texas | 77027    | 2056289809 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | Shopbase        | 11/22        | 113 | Visa      |
    And close browser
    Given user login to shopbase dashboard
    Then user navigate to "Orders>Abandoned checkouts" screen
    And search then select abandoned checkout by customer email
    # buyer clicks to unsubscribe
    Given open the url "mailinator.com" in a new tab
    And open mailbox of buyer with subject
      | Subject                   |
      | Did you forget something? |
    Then click to unsubscribe from email


