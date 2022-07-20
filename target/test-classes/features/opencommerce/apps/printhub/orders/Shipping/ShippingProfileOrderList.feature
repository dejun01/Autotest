Feature: Shipping profile order list
#shipping_profile
  Scenario: 1. Vefify message set shipping profile dont support address on order list
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "product shipping profile"
      | Email                   | First Name | Last Name | Address           | Country   | City    | Zip code | State             | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Australia | Murdoch | 90002    | Western Australia | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shop dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And verify order shipping profile
      | Screen     | Dont Support address | Message                                            | Error            |
      | Order list | true                 | Shipping not available for the customer's location | Item has 1 error |
    And close browser

  Scenario: 2. Vefify message set shipping profile support support address on order list
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "product shipping profile"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shop dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And verify order shipping profile
      | Screen     | Dont Support address | Message | Error |
      | Order list | false                |         |       |
    And close browser

  Scenario: 3. Vefify message set shipping profile dont support address on import order
    Given user login to shop dashboard
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    And Import order printHub has shipping profile
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,90002,WA,AU,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing|
    And verify order shipping profile
      | Screen       | Dont Support address | Message                                           | Error            |
      | Order import | true                 | These items don't ship to the customer's location | Item has 1 error |
    And close browser

  Scenario: 4. Vefify message set shipping profile support address on import order
    Given user login to shop dashboard
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    And Import order printHub has shipping profile
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    And verify order shipping profile
      | Screen       | Dont Support address | Message | Error |
      | Order import | false                |         |       |
    And close browser
