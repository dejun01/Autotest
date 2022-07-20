Feature: Check action Tool bar of Personalize
  #  sbase_edit_printfile_in_order

  Scenario: Check generate printfile when create order with CO text field
    And open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                 | Custom option               |
      | 01  | Printfile CO text field | Text,CO text,Text Pritnfile |
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
#    And input information order into "order_printfile.csv" file csv
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    And verify print file after genarated for line item "Personalize CO area"
      | Action  | Is generated |
      | Preview | true         |

#  Scenario: Check generate printfile when create order with CO text field
#And write information order in "order_printfile.csv" file csv
#    Given Access to order detail by order ID
