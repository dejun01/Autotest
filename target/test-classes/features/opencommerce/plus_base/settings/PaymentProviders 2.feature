Feature: Setting payment providers
#env = setting_checkout_plusbase

  Scenario: Verify display all data clone from template store
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then Verify display all data clone from template store
      | information                                       |
      | Statement descriptor,Brand name,Payment test mode |
    And close browser

  Scenario: Verify Statement descriptor after edit
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And Get "Statement descriptor" before edit
    And Edit payment providers
      | type                 | note |
      | Statement descriptor | name |
    Then Verify payment providers after edit
      | type                 | note |
      | Statement descriptor | name |
    And close browser

  Scenario: Verify phone number after edit
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And Get "Phone number" before edit
    And Edit payment providers
      | type         | note  |
      | Phone number | phone |
    Then Verify payment providers after edit
      | type         | note  |
      | Phone number | phone |
    And close browser

  Scenario: Verify brand name after edit
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And Get "Brand name" before edit
    And Edit payment providers
      | type       | note      |
      | Brand name | brandname |
    Then Verify payment providers after edit
      | type       | note      |
      | Brand name | brandname |
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test)Clip On Silicone Strainer:Red"
      | Email                  | First Name | Last Name | Address           | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | PlusBase@beeketing.net | PlusBase   | Auto      | 100 Wilshire Blvd | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    And add products "(Electronic)Dress for Women 2021 Summer:Beige>1" to cart then checkout Plusbase
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
#    And choose shipping method ""
#    And Checkout by paypal and verify brand name
#      | Payment method | Payment gateway | Paypal account     | Password | Card type |
#      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    |
    And close browser

  Scenario: Verify when edit brand name is empty
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And Get "Brand name" before edit
    And Edit payment providers
      | type       | note |
      | Brand name |      |
    Then Verify payment providers after edit
      | type       | note |
      | Brand name |      |
    And close browser


