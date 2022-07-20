@CheckoutBrazil
Feature: Checkout flow with Brazil
  #env: sbase_brazil

  Background: clear data
    Given clear all data

  Scenario: Check validation when select country = Brazil #SB_CHE_CB_5 #SB_CHE_CB_3
    Given open shop on storefront
    When add products "Yonex" to cart then checkout
    And input Customer information
      | Case                                   | Email               | First Name | Last Name | Address        | City           | Country | State  | CPF/CNPJ number | Zip code  | Phone          | Expected                                                            |
      | CPF/CNPJ less than 11 digits           | cpay@mailtothis.com | Sofia      | L Castro  | Rua Belém 1477 | Telêmaco Borba | Brazil  | Paraná | 123456          | 84264-310 | (42) 6096-6823 | CPF only contains 11 digits and CNPJ number only contains 14 digits |
      | CPF/CNPJ between 12 digit to 13 digits | cpay@mailtothis.com | Sofia      | L Castro  | Rua Belém 1477 | Telêmaco Borba | Brazil  | Paraná | 123456789123    | 84264-310 | (42) 6096-6823 | CPF only contains 11 digits and CNPJ number only contains 14 digits |
      | CPF/CNPJ is empty                      | cpay@mailtothis.com | Sofia      | L Castro  | Rua Belém 1477 | Telêmaco Borba | Brazil  | Paraná | @BLANK@         | 84264-310 | (42) 6096-6823 | Please enter a CPF/CNPJ number.                                     |
      | valid CPF/CNPJ                         | cpay@mailtothis.com | Sofia      | L Castro  | Rua Belém 1477 | Telêmaco Borba | Brazil  | Paraná | 12345678912     | 84264-310 | (42) 6096-6823 |                                                                     |

  Scenario: cpf/ cnpj is displayed correctly in order details, customer detail and ADC app #SB_CHE_CB_1 #SB_CHE_CB_6
    Given open shop on storefront
    And add products "Yonex>4" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address        | City           | Country | State  | CPF/CNPJ number | Zip code  | Phone      |
      | @mailtothis.com@ | Sofia      | L Castro  | Rua Belém 1477 | Telêmaco Borba | Brazil  | Paraná | 723.950.680-04  | 84264-310 | 0944193269 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page

    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And verify shipping address is displayed correctly in order detail
    And verify that edit shipping address on the order successfully
      | CPF/CNPJ Number |
      | 616.481.461-80  |

    When user navigate to "Customers" screen
    And search customer by email then select
    And verify cpf or cnpj is displayed correctly in customer detail

    When user navigate to "Apps" screen
    And select app "Ali Dropship Connector" on Apps list
    When user navigate to "Manage Orders" screen
    And expand order "" in list ADC
    And open Shipping Address popup then verify customer information is displayed correctly
      | CPF/CNPJ number |
      | 723.950.680-04  |

  Scenario: export order #SB_CHE_CB_12
    Given open shop on storefront
    And add products "Victor>2" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address        | City           | Country | State  | CPF/CNPJ number | Zip code  | Phone      |
      | @mailtothis.com@ | Sofia      | L Castro  | Rua Belém 1477 | Telêmaco Borba | Brazil  | Paraná | 723.950.680-04  | 84264-310 | 0944193269 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page

    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    Given search order by order name criteria
    Then select all orders
    And open export popup then select export type "Selected 1 order"
    And select export template Customize export fields
      | Name | Shipping CPF or CNPJ number |
    Then click on Export to file button
    And verify that the content of file downloaded is matched with order information from dashboard
      | Name | Shipping CPF or CNPJ number |




