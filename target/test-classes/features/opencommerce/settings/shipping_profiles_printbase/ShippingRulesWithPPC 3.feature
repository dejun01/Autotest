Feature: Shipping profile rules with PPC
  #env = sbase_shipping_profile_printbase

  Background: clear data
    Given clear all data

  Scenario: Add PPC have the same rate name with order's shipping method
    """Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile        | Rate name         | First item (1st price) | Each additional item (2nd price) |
  | xx1_VN_Rule21  | Economic Shipping | 7                      | 2                                |
  | xx1_VN_Rule21  | Expedite Shipping | 7                      | 4                                |
  | xx1_VN_RulePPC1| Expedite Shipping | 6                      | 2                                |
  => Add 2nd price of rate have the same name with Shipping method of order"""
    Given open shop on storefront
    When add products "RulePPC1>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping fee on order summary is "$8.00"
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Expedite Shipping   | $8.00  |

    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Rule21" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method   | Price  |
      | Expedite Shipping | $12.00 |
    And verify thank you page
    And get the order information including
      | order id | shipping method | shipping fee |

    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify shipping fee in order
#    Then verify profit of order
#      | Product Base info                          | Production rate | Staging rate |
#      | shirt 01 test  Long Sleeve Tee , White , S | 3,4             | 5,6          |
#      | mug-00  Beverage Mug , white , 11oz        | 3,4             | 5,6          |

  Scenario: Add PPC when item of order have only one rate not same
    """Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile        | Rate name         | First item (1st price)| Each additional item |
  | xx1_VN_Rule21  | Economic Shipping | 7                     | 2                    |
  | xx1_VN_Rule21  | Expedite Shipping | 7                     | 4                    |
  | xx1_VN_RulePPC2| Expedite          | 6                     | 2                    |
  => Add additional price of rate have min 1st price"""
    Given open shop on storefront
    When add products "RulePPC2>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping fee on order summary is "$8.00"
    Then verify shipping base on Profile rules
      | Base rules | Price  |
      | Expedite   | $8.00  |

    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Rule21" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method | Price  |
      | Expedite        | $10.00 |
    And verify thank you page

  Scenario: Add PPC have different rate name, same group with order's shipping method (high fee)
  """ Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile       | Rate name         | First item (1st price) | Each additional item (2nd price)|
  | xx1_VN_Rule21 | Economic Shipping | 7                      | 2                               |
  | xx1_VN_Rule21 | Expedite Shipping | 7                      | 4                               |
  | xx1_VN_Rule5  | Economic          | 5                      | 3                               |
  | xx1_VN_Rule5  | Expedite          | 8                      | 6                               |
  => Add 2nd price of rate have highest 1st price"""
    Given open shop on storefront
    When add products "Rule5>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping fee on order summary is "$8.00"
    Then verify shipping base on Profile rules
      | Base rules | Price  |
      | Economic   | $8.00  |
      | Expedite   | $14.00 |

    And choose shipping method "Expedite"
    Then verify shipping fee on order summary is "$14.00"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |

    And order product "Rule21" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method   | Price  |
      | Expedite          | $18.00 |
    And verify thank you page

  Scenario: Add PPC have different rate name, different group with order's shipping method (low fee)
  """Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile       | Rate name         | First item | Each additional item |
  | xx1_VN_Rule21 | Economic Shipping | 7          | 2                    |
  | xx1_VN_Rule21 | Expedite Shipping | 7          | 4                    |
  | xx2_VN_Rule6  | Economic          | 7          | 3                    |
  | xx2_VN_Rule6  | Expedite          | 8          | 6                    |
  => Add 1st price of rate have lowest 1st price"""
    Given open shop on storefront
    When add products "Rule6>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping fee on order summary is "$10.00"
    Then verify shipping base on Profile rules
      | Base rules | Price  |
      | Economic   | $10.00 |
      | Expedite   | $14.00 |

    And choose shipping method "Economic"
    Then verify shipping fee on order summary is "$10.00"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Rule21" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method | Price  |
      | Economic        | $17.00 |
    And verify thank you page


