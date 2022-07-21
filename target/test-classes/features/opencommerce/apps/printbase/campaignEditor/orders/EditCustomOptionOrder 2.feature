Feature: Edit Custom option
#pbase_order_edit_custom_option

  Scenario:Order camp
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                                 | Custom option                                                                                                                                          | Is crop image |
      | 01  | Test Order Edit Custom Option:White,S>1 | Text,Edit custom text,Sky;Image,Edit custom image,Campaign1.png;Picture choice,Picture folder,Car 3;Picture Group,Picture Group,Hair Girl>Hair Girl 22 | true          |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And quit browser

  Scenario: get version
    Given user login to shopbase dashboard
    And user navigate to "Orders>All orders" screen
    And get orderID
    Given login to hive-pbase
    When user navigate "Customer Support" screen
    And Click to "PBase Order"
    And get current version
    And quit browser

  Scenario Outline:Edit campaign and order
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And search product or campaign or orders "Test Order Edit Custom Option" at list page in dashboard
    And open camp detail
      | Title                         |
      | Test Order Edit Custom Option |
    And click on Edit personalization btn
    And edit custom option
      | Custom option     | Custom name       | Font               | Placeholder | Max length | Default value |
      | Edit custom text  | Edit custom text  | Roboto Mono Italic | Input text  | 16         | Thuy Linh     |
      | Edit custom area  | Edit custom area  | Roboto Mono Italic | Input text  | 16         | Nguyen Linh   |
      | Edit custom image | Edit custom image |                    |             |            |               |
    And click on Save Change btn
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                                 | Custom option                                                                                                                                          | Is crop image |
      | 01  | Test Order Edit Custom Option:White,S>1 | Text,Edit custom text,Sky;Image,Edit custom image,Campaign1.png;Picture choice,Picture folder,Car 3;Picture Group,Picture Group,Hair Girl>Hair Girl 22 | true          |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And quit browser

    Examples:
      | KEY | Testcase     |
      | 01  | Verify order |

  Scenario: verify version
    Given user login to shopbase dashboard
    And user navigate to "Orders>All orders" screen
    And get orderID
    Given login to hive-pbase
    When user navigate "Customer Support" screen
    And Click to "PBase Order"
    And verify version camp
    And quit browser