Feature: Calculate profit to show at order detail and balance when checkout by Paypal
#  print_on_demand_profit

  Scenario Outline: Calculate profit with orders paid by paypal
    Given Description: "<Testcase>"
    And open shop on storefront
    And add products to cart then checkout as "<KEY>"
      | KEY | Product                                  |
      | 1   | Profit_paypal:Unisex T-shirt,Black,M>1   |
      | 1   | Profit_paypal:Long Sleeve Tee,White,XL>1 |

    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Paypal smart button |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | normal    | no                  |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail on admin Pbase
    And verify order detail when method shipping free "false" in Pbase as "<KEY>"
      | KEY | Sub total | Shipping | Discount amount |
      | 1   | 42.98     | 9.98     | 0               |
    And clear all data
    And close browser

    Examples: <Key>
      | KEY | Testcase                                    |
      | 1   | Calculate profit with orders paid by paypal |