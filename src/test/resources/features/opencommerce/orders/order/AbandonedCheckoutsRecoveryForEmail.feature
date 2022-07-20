Feature: Abandoned checkouts recovery analytics for email

  Background:
    Given clear all data

  Scenario: Verify chart 'Abandoned checkouts recovery'
    #pre-condition: existing an abandoned checkout containing scheduled email
    Given user login to shopbase dashboard
    Then user navigate to "Analytics" screen
    And get data including: EMAIL SENT, EMAIL CLICKED, ORDER COMPLETED and revenue before
    # merchant sent mail manually to buyer
    Then user navigate to "Orders>Abandoned checkouts" screen
    Then select the newest abandoned checkout
    Then choose cancel email with subject "Did you forget something?"
    And click on "Send a cart recovery email" button to send email manually
      | Message                                            |
      | Email was sent to superQA@mailinator.girl-viet.com |
    # buyer clicks
    Given open the url "mailinator.com" in a new tab
    And open mailbox of buyer with subject
      | Email                            | Subject                   |
      | superQA@mailinator.girl-viet.com | Did you forget something? |
    Then click on button "Complete your purchase" from email
    # buyer checkouts successfully
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4000000000000044 | John Doe        | 12/22        | 111 |
    And verify thank you page then get all information
    # switch to original tab to verify analytics
    And close all tabs without original tab
    And user navigate to "Analytics" screen
    And verify chart Abandoned checkouts recovery by "Email"
      | Email sent | Email clicked | Order completed | Revenue      | Recovery rate              |
      | +1         | +1            | +1              | +total order | order completed/email sent |


  Scenario: Create a new abandoned checkout for next time
    Given open shop on storefront
    Then add products "URBAN GIRL T-shirt" to cart then checkout
    And input Customer information
      | Email                  | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | superQA@mailtothis.com | 2056289809 | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    And choose shipping method ""