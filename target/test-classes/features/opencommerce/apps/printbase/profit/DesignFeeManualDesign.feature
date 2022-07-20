Feature: Calculate profit to show at order detail when order with campaign manual design
  #prod_pbase_design_fee
  # staging_pbase_design_fee

  Scenario: Calculate profit with order campaign manual design #SB_PRB_MC_CDF_PB_1 #SB_PRB_MC_CDF_PB_2 #SB_PRB_MC_CDF_PB_4 #SB_PRB_MC_CDF_PB_6 #SB_PRB_MC_CDF_PB_7 #SB_PRB_MC_CDF_PB_9
    Given Description: "<Testcase>"
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                    | Style          | Custom option  |
      | 01  | Manual campaign:White,XS>2 | V-neck T-shirt | Text,Text,Test |
      | 01  | Campaign normal:White,S>1  |                |                |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail on admin Pbase
    And verify profit of order detail
      | Product Base info                 | Number lineitem manual design | Production rate | Staging rate |
      | V-neck T-shirt>2;Unisex T-shirt>1 | 1                             | 3,4             | 3,4          |
    And quit browser

  Scenario: Calculate profit with order campaign mutil lineitem manual design #SB_PRB_MC_CDF_PB_3 #SB_PRB_MC_CDF_PB_5
    Given Description: "<Testcase>"
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                    | Style          | Custom option   |
      | 01  | Manual campaign:White,XS>2 | V-neck T-shirt | Text,Text,Test  |
      | 01  | Manual campaign:White,XS>2 | V-neck T-shirt | Text,Text,Test1 |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail on admin Pbase
    And verify profit of order detail
      | Product Base info | Number lineitem manual design | Production rate | Staging rate |
      | V-neck T-shirt>4  | 2                             | 3,4             | 3,4          |
    And quit browser

  Scenario: Calculate profit with campaign manual dont input value #SB_PRB_MC_CDF_PB_10 #SB_PRB_MC_CDF_PB_11
    Given Description: "<Testcase>"
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                    | Style          | Custom option  |
      | 01  | Manual campaign:White,XS>1 | V-neck T-shirt | Text,Text,Test |
      | 01  | Manual campaign:White,L>1  | V-neck T-shirt |                |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail on admin Pbase
    And verify profit of order detail
      | Product Base info | Number lineitem manual design | Production rate | Staging rate |
      | V-neck T-shirt>2  | 2                             | 3,4             | 3,4          |
    And quit browser