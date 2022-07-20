#environment = sbase_shipping
Feature: Setting Shipping zones by country

  #    1 shipping rules - 3 country US, VN, CN
  Scenario: Setup 1 shipping rule have several countries
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    And add shipping zones with country and Price Based rate
      | Zone name                | Countries                   | Price based rate |
      | Several country shipping | China,Vietnam,United States | Standard,,,,10   |

  Scenario Outline: Verify checkout with 1 shipping rule have several country
    Given Description: "<Case>"
    When open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    And input Customer information as "<KEY>"
      | KEY | Email                           | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone        | Saved | Expected |
      | 1   | shippingcountry1@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan   | 130       | Hanoi       | Vietnam       |            | 10000    | 0944193269   | false | success  |
      | 2   | shippingcountry2@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606   | false | success  |

    And choose shipping method "Standard"
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    Examples:
      | KEY | Case                   |
      | 1   | checkout Vietnam       |
      | 2   | checkout United States |

  Scenario Outline: Verify checkout with 1 shipping rule have several country with China
    Given Description: "<Case>"
    When open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    And input Customer information as "<KEY>"
      | KEY | Email                           | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone        | Saved | Expected |
      | 3   | shippingcountry3@mailtothis.com | Jone       | Doe       | Beijing            | 814       | Beijing     | China         | Anhui      | 100000   | 954-358-6292 | false | success  |

    And choose shipping method "Standard"
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    Examples:
      | KEY | Case                   |
      | 3   | checkout China         |

  Scenario: Verify countries is setup in 1 shipping rule
    When open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    Then verify country "Japan" is "not supported" in Customer Information page
    And verify country "China,Vietnam,United States" is "supported" in Customer Information page
    And close browser