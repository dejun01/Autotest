Feature: As a merchant, I want to caculate price shipping in rule shipping zone
  #Shop : au-ph-separate-shipping.stag.myshopbase.net
  #environment: staging_print_hub_separate_shipping
  Scenario Outline: Verify status of Orders when sync to app Print Hub
    Given Description: "<Testcase>"
    Given open shop on storefront
    When search and select the product "<Product name>"
    And add campaign to cart as "<KEY>"
      | KEY | Products               | Color          | Size  | Quantity |
      | 1   | All Over Print T-Shirt | All over print | S     | 1        |
      | 1   | All Over Print T-Shirt | All over print | 4XL   | 1        |
      | 1   | Quilt                  | All over print | Queen | 1        |
      | 1   | Beverage Mug           | white          | 11oz  | 2        |

      | 2   | All Over Print T-Shirt | All over print | S     | 1        |
      | 2   | All Over Print T-Shirt | All over print | 4XL   | 1        |
      | 2   | Quilt                  | All over print | Queen | 1        |
      | 2   | Beverage Mug           | white          | 11oz  | 2        |

      | 3   | All Over Print T-Shirt | All over print | S     | 1        |
      | 3   | All Over Print T-Shirt | All over print | 4XL   | 1        |
      | 3   | Quilt                  | All over print | Queen | 1        |
      | 3   | Beverage Mug           | white          | 11oz  | 2        |

      | 4   | All Over Print T-Shirt | All over print | S     | 1        |
      | 4   | All Over Print T-Shirt | All over print | 4XL   | 1        |
      | 4   | Quilt                  | All over print | Queen | 1        |
      | 4   | Beverage Mug           | white          | 11oz  | 2        |
    And click to button checkout
    When input Customer information as "<KEY>"
      | KEY | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | 1   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |

      | 2   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |

      | 3   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |

      | 4   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Vietnam       | Ha Noi       | 1111     |            | 2025550147 |

    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    And user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab              |
      | 1   | All              |
      | 2   | Awaiting Payment |
      | 3   | Awaiting Payment |
      | 4   | Awaiting Payment |
      | 5   | All              |
    And search order Print Hub
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product      |
      | 2   | Hold   | Beverage Mug |
      | 3   | Cancel | Beverage Mug |
    And open order detail on admin shop
    Then verify information order in Print Hub as "<KEY>"
      | KEY | Tab              | Status           | Quantity supplier | Shipping price |
      | 1   | All              | Awaiting Payment |                   | 30.95          |
      | 2   | Awaiting Payment | Partially        |                   | 20.97          |
      | 3   | Awaiting Payment | Partially        |                   | 20.97          |
      | 4   | All              | Awaiting Payment |                   | 37.95          |
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab              |
      | 1   | Awaiting Payment |
      | 2   | On Hold          |
      | 3   | Cancelled        |
      | 4   | Awaiting Payment |
    And open order detail on admin shop
    Then verify information order in Print Hub as "<KEY>"
      | KEY | Tab              | Status           | Quantity supplier | Shipping price |
      | 1   | All              | Awaiting Payment |                   | 30.95          |
      | 2   | On Hold          | Partially        |                   | 9.98           |
      | 4   | Awaiting Payment | Awaiting Payment |                   | 37.95          |

    And close browser
    Examples: <Key>
      | KEY | Product name   | Testcase                       |
      | 1   | Shipping_price | check with shipping address US |
      | 2   | Shipping_price | check with order hold 1 part   |
      | 3   | Shipping_price | check with order cancel 1 part |
      | 4   | Shipping_price | check with shipping address VN |