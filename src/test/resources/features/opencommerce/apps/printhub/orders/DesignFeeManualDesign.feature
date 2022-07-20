Feature: Calculate profit to show at order detail when order with campaign manual design
  #sbase_design_fee

  Scenario: Check design fee with order campaign manual design #SB_PRB_MC_CDF_PB_19 #SB_PRB_MC_CDF_PB_21 ##SB_PRB_MC_CDF_PB_25 #SB_PRB_MC_CDF_PB_31
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                    | Custom option                                                |
      | 01  | Manual campaign:White,XS>2 | Text,Text 1,Test;Text,Text 2,Test 12345;Image,Image,icon.png |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab       | Variants                    | Design fee | SKU                               | Cost     | Status    | Product cost | Shipping | Total Cost |
      | In Review | V-neck T-shirt / White / XS | 3.50       | SKU: PH-AP-V-neckT-shirt-White-XS | 9.49 x 2 | In Review | 18.98        | 0.00     | 22.48      |
    And select action Cancel order on Shopbase dashboard
    Then verify order detail after mapped or charge
      | Tab       | Variants                    | Design fee | SKU                               | Cost     | Status    | Product cost | Shipping | Total Cost |
      | Cancelled | V-neck T-shirt / White / XS | 3.50       | SKU: PH-AP-V-neckT-shirt-White-XS | 9.49 x 2 | Cancelled | 18.98        | 0.00     | 22.48      |
    And quit browser


  Scenario: Check design fee with order campaign multi base manual design #SB_PRB_MC_CDF_PB_22
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                                   | Style          | Custom option                                                |
      | 01  | Manual campaign:V-neck T-shirt,White,XS>2 | V-neck T-shirt | Text,Text 1,Test;Text,Text 2,Test 12345;Image,Image,icon.png |
      | 01  | Manual campaign:Ladies T-Shirt,White,S>2  | Ladies T-shirt | Text,Text 1,Test;Text,Text 2,Test 12345;Image,Image,icon.png |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab       | Base product   | Variants                    | Design fee | SKU                               | Cost     | Status    | Product cost | Shipping | Total Cost |
      | In Review | V-neck T-shirt | V-neck T-shirt / White / XS | 7.00       | SKU: PH-AP-V-neckT-shirt-White-XS | 9.49 x 2 | In Review | 30.56        | 0.00     | 37.56      |
      | In Review | Ladies T-shirt | Ladies T-shirt / White / S  | 7.00       | SKU: PH-AP-LadiesT-shirt-White-S  | 5.79 x 2 | In Review | 30.56        | 0.00     | 37.56      |
    And quit browser


  Scenario: Check design fee with order campaign manual design and campaign normal #SB_PRB_MC_CDF_PB_23 #SB_PRB_MC_CDF_PB_24 #SB_PRB_MC_CDF_PB_29 #SB_PRB_MC_CDF_PB_27 #SB_PRB_MC_CDF_PB_26
    Given open shop on storefront
    And add product with custom option to cart then checkout as "01"
      | KEY | Product                                   | Custom option                                                |
      | 01  | Manual campaign:V-neck T-shirt,White,XS>1 | Text,Text 1,Test;Text,Text 2,Test 12345;Image,Image,icon.png |
      | 01  | Campaign normal:White,S>1                 |                                                              |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab              | Base product   | Variants                    | Design fee | SKU                               | Cost     | Status           | Product cost | Shipping | Total Cost |
      | Awaiting Payment | Unisex T-shirt | Unisex T-shirt / White / S  | 0.00       | SKU: PH-AP-UnisexT-shirt-White-S  | 4.99 x 1 | Awaiting Payment | 4.99         | 5.99     | 10.98      |
      | In Review        | V-neck T-shirt | V-neck T-shirt / White / XS | 3.50       | SKU: PH-AP-V-neckT-shirt-White-XS | 9.49 x 1 | In Review        | 9.49         | 5.99     | 18.98      |
    And select action Hold order on Shopbase dashboard
    Then verify order detail after mapped or charge
      | Tab     | Base product   | Variants                    | Design fee | SKU                               | Cost     | Status  | Product cost | Shipping | Total Cost |
      | On Hold | V-neck T-shirt | V-neck T-shirt / White / XS | 3.50       | SKU: PH-AP-V-neckT-shirt-White-XS | 9.49 x 1 | On Hold | 14.48        | 5.99     | 23.97      |
      |         | Unisex T-shirt | Unisex T-shirt / White / S  | 3.50       | SKU: PH-AP-UnisexT-shirt-White-S  | 4.99 x 1 | On Hold | 14.48        | 5.99     | 23.97      |
    And quit browser