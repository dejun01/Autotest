Feature: Calculate profit to show at order detail and balance when checkout by Stripe
#  print_on_demand_profit

  Scenario Outline: Calculate profit with orders paid by Stripe #SB_ORD_CAL_PROFIT_1 #SB_ORD_CAL_PROFIT_2 #SB_ORD_CAL_PROFIT_3 #SB_ORD_CAL_PROFIT_4 #SB_ORD_CAL_PROFIT_5
    Given Description: "<Testcase>"
    Given open shop on storefront
    And add products to cart then checkout as "<KEY>"
      | KEY | Product                                  |
#      | 5   | Test profit:Unisex T-shirt,Black,M>1     |
#      | 5   | Test profit:Long Sleeve Tee,White,XL>1   |
#      | 5   | Test profit:Quilt,All over print,Queen>1 |

      | 1   | Test profit:Unisex T-shirt,Black,M>1     |
      | 1   | Test profit:Long Sleeve Tee,White,XL>1   |
      | 1   | Test profit:Quilt,All over print,Queen>1 |

      | 2   | Test profit:Unisex T-shirt,Black,M>1     |
      | 2   | Test profit:Long Sleeve Tee,White,XL>1   |
      | 2   | Test profit:Quilt,All over print,Queen>1 |
      | 2   | profit_line:White,XS>1                   |

      | 3   | Test profit:Unisex T-shirt,Black,M>1     |
      | 3   | Test profit:Long Sleeve Tee,White,XL>1   |
      | 3   | Test profit:Quilt,All over print,Queen>1 |
      | 3   | profit_line:White,XS>14                  |

      | 4   | Test profit:Unisex T-shirt,Black,M>1     |
      | 4   | Test profit:Long Sleeve Tee,White,XL>1   |
      | 4   | Test profit:Quilt,All over print,Queen>1 |
      | 4   | profit_line:White,XS>14                  |

    And apply discount code as "<KEY>"
      | KEY | Discount code   |
      | 2   | Discount profit |
      | 4   | Discount profit |
    When input Customer information as "<KEY>"
      | KEY | Email                    | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | 5   | shopbase1@mailtothis.com | Shop       | Base      | 130 Trung phung   | Vietnam       | Ha Noi       | 1111     |            | 2025550147 |
      | 1   | shopbase@mailtothis.com  | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
      | 2   | shopbase@mailtothis.com  | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
      | 3   | shopbase@mailtothis.com  | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
      | 4   | shopbase@mailtothis.com  | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |

    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail on admin Pbase
    And verify order detail when method shipping free "<Shipping Free>" in Pbase as "<KEY>"
      | KEY | Sub total | Shipping | Discount amount |
#      | 5   | 94.97     | 26.97    | 0               |
      | 1   | 94.97     | 11.47    | 0               |
      | 2   | 117.96    | 21.96    | 23.6            |
      | 3   | 416.83    | 47.83    | 0               |
      | 4   | 416.83    | 47.83    | 83.37           |
    And clear all data
    And quit browser

    Examples: <Key>
      | KEY | Testcase                                                         | Shipping Free |
#      | 5   | Calculate profit with orders paid when ship REST OF WORLD        |               |
      | 1   | Calculate profit with orders paid by Stripe                      |               |
      | 2   | Calculate profit with orders paid with discount 20%              |               |
      | 3   | Calculate profit with orders paid with freeship                  | true          |
      | 4   | Calculate profit with orders paid with freeship and discount 20% | true          |


