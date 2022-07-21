#environment = sbase_shipping
Feature: Setting Shipping zones by country

  Scenario: Precondition-Create shipping zones = rest of world
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    And add shipping zones with country and Price Based rate
      | Zone name     | Countries     | Price based rate |
      | Rest of world | Rest of world | Standard,,,,10   |

#    Shipping rules rest of world
  Scenario Outline: Verify checkout in several countries with shipping zones = rest of world
    Given Description: "<Case>"
    And open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    When input Customer information as "<KEY>"
      | KEY | Email                           | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone        | Saved | Expected |
      | 1   | shippingcountry1@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan   | 130       | Hanoi       | Vietnam       |            | 10000    | 0944193269   | false | success  |
      | 2   | shippingcountry2@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606   | false | success  |
      | 3   | shippingcountry3@mailtothis.com | Jone       | Doe       | Beijing            | 814       | Beijing     | China         | Anhui      | 100000   | 954-358-6292 | false | success  |
    And choose shipping method "Standard"
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    Examples:
      | KEY | Case                   |
      | 1   | checkout Vietnam       |
      | 2   | checkout United States |
      | 3   | checkout China         |

  Scenario Outline: Verify checkout in several countries with shipping zones = rest of world with China
    Given Description: "<Case>"
    And open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    When input Customer information as "<KEY>"
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

#    3 shipping rule 3 country US, VN, CN
  Scenario: Precondition - Create shipping zones are specific country
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    And add shipping zones with country and Price Based rate
      | Zone name        | Countries     | Price based rate |
      | China shipping   | China         | Standard,,,,10   |
      | Vietnam shipping | Vietnam       | Standard,,,,10   |
      | US shipping      | United States | Standard,,,,10   |

  Scenario Outline: Checkout in country contain shipping zone
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
    Then verify thank you page
    Examples:
      | KEY | Case                   |
      | 1   | checkout Vietnam       |
      | 2   | checkout United States |

  Scenario Outline: Checkout in country contain shipping zone with China
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
    Then verify thank you page
    Examples:
      | KEY | Case                   |
      | 3   | checkout China         |

  Scenario: Checkout in country not contain shipping zone
    When open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    And verify country "Japan" is "not supported" in Customer Information page
