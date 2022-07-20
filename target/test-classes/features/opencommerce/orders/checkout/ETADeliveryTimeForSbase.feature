Feature: ETA Delivery Time for Sbase
#  sbase_ETA_Delivery_Time

  Scenario: ETA Delivery Time for Sbase
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    And add shipping zones with country and Price Based rate
      | Zone name                | Countries                   | Price based rate     |
      | Several country shipping | China,Vietnam,United States | Standard,,,,10,5,8   |

    Given open shop on storefront
    And search and select the product "Slice Quick & Right"
    Then verify ETA shipping time on "product page"
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    Then verify ETA shipping time on "checkout page"
