Feature: Update more filter
#env = sbase_more_filter

  Scenario Outline: Search order by customer address successfully
    Given clear all data
    And open shop on storefront
    Then checkout successfully via stripe with cart "t-shirt>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then user login to shopbase dashboard
    And user navigate to "orders" screen by url
    And Filter order by condition
      | IsText | Filter button|condition            | value                    |
      | 1      | More filters |Customer             | shopbase@mailtothis.com  |
    And search order by "Customer address" as "<KEY>"
      |KEY | Data filter          |   Expect        |
      | 1  | California           |  @orderNumber@  |
      | 2  | 90401                |  @orderNumber@  |
      | 3  | United States        |  @orderNumber@  |
      | 4  | California           |  @orderNumber@  |
      | 5  | 100 Wilshire Blvd    |  @orderNumber@  |
      | 6  | 123123               |  Count not find orders matchhing  |
    And close browser
    Examples: <KEY>
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |
      | 6   |
