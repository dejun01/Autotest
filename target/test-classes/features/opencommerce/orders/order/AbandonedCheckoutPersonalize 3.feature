@AbandonedCheckoutPersonalize
Feature: Abandoned Checkout Personalize
  #evn: sbase_abandoned_personalize


  Scenario:  Setting send email-image default after checkout product normal
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product            |
      | 01  | Product normal:M>1 |
    And input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
   And quit browser
    And wait 50000 second
    And open mailbox of buyer with subject
      | Subject  | Content                      | Image product |
      | Email 01 | Thank you for your purchase! |               |
    And quit browser