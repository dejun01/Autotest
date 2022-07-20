@checkoutFlow @customerInfor
Feature: Roller - Customer Information
  #env = sbase_roller_checkout
  Background:
    Given clear all data
    Given open shop on storefront

  Scenario: Verify input data in customer information page 1
    When add products "Lamp" to cart then checkout
    When input Customer information
      | Case              | Email               | First Name | Last Name | Address    | Apartment | City   | Country | State | Zip code | Phone      | Expected                    |
      | emai empty        |                     | Tho        | Tran      | 360 Xa Dan | Hoang Mai | Ha noi | Vietnam |       | 1000     | 123-45-678 | Please enter an email.      |
      | email wrong fomat | tho.com             | Tho        | Tran      | 360 Xa Dan | Hoang Mai | Ha noi | Vietnam |       | 1000     | 123-45-678 | Please enter a valid email. |
      | address empty     | thotran@yopmail.com | @BLANK@    | Tran      | @BLANK@    | Hoang Mai | Ha noi | Vietnam |       | 1000     | 12345678   | Please enter an address.    |


  Scenario:  Verify input data in customer information page 2
    When add products "Lamp" to cart then checkout
    And input Customer information
      | Lastname empty   | thotran@yopmail.com | Tho | @BLANK@ | 360 Xa Dan | Hoang Mai | Ha noi  | Vietnam       |  | 1000    | 12345678   | Please enter a last name.         |
      | zip code empty   | thotran@yopmail.com | Tho | Tran    | 360 Xa Dan | Hoang Mai | Ha noi  | United States |  | @BLANK@ | 12345678   | Please enter a ZIP / postal code. |
      | phone code empty | thotran@yopmail.com | Tho | Tran    | 360 Xa Dan | Hoang Mai | Ha noi  | United States |  | 1000    | @BLANK@    | Please enter a phone number.      |
      | city empty       | thotran@yopmail.com | Tho | Tran    | 360 Xa Dan | Hoang Mai | @BLANK@ | United States |  | @BLANK@ | 12345678   | Please enter a city.              |
      | Success          | thotran@yopmail.com | Tho | Tran    | 360 Xa Dan | Hoang Mai | Ha noi  | Vietnam       |  | 1000    | 0944193269 | success                           |

  Scenario: SB_SF_CHE_1.4: Verify enter invalid email show warning + Check save customer information for next time
    And add products "Lamp" to cart then navigate to checkout page
#    Comment until the FS invalid_email_warning is approved
#    And enter the field in customer information form
#      | Email                    |
#      | op.fdfd.fdfdfd@gmail.com |
#    And verify warning message "This email address could be wrong. Please re-check again."
    And input Customer information
      | Email                      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | thuntt_john@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And choose shipping method "International Shipping"
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    And open shop on storefront
    And add products "Lamp" to cart then navigate to checkout page
    Then verify customer information
      | Email                      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | thuntt_john@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And close browser


#  Comment lại do feature chưa bật
#  Scenario: Verify Palceholder of Customer Information on new Checkout Page
#    Given open shop on storefront
#    And add products "t-shirt>2" to cart then navigate to checkout page
#    When input Customer information
#      | Country       |
#      | United States |
#    Then Verify special character of placeholder in Customer Information
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Expected                           |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | @BLANK@  | California | 2025550147 | Please enter a ZIP / postal code.  |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | Vietnam       | Santa Monica |          |            | 2025550147 |                                    |
#    And choose shipping method ""
#    And verify special character of placeholder in billing address

