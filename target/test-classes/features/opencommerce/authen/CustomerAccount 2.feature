@checkoutFlow @shippingMethod
Feature: Customer Account - Roller
#  env = sbase_roller_checkout

  Background: open shop on storefront
    Given clear all data
    Given open shop on storefront

  Scenario: Sign up an account on Storefront base on theme
    Then verify customer sign up account to login shop
      | Case             | Email                   | First name | Last name | Password | Repeat password | Result                            |Status    |
      | email blank      |                         | ng         | ng        | 1        | 1               |                                   |False     |
      | email invalid    | email                   | nga        | ng        | 1        | 1               |                                   |False     |
      | firstname blank  | nganguyen@beeketing.net |            | ng        | 1        | 1               |                                   |False     |
      | lastname blank   | nganguyen@beeketing.net | ng         |           | 1        | 1               |                                   |False     |
      | pass blank       | nganguyen@beeketing.net | ng         | ng        | 1        |                 |                                   |False     |
      | pass blank       | nganguyen@beeketing.net | ng         | ng        |          | 1               |                                   |False     |
      | not match 2 pass | nganguyen@beeketing.net | ng         | ng        | 22       | 11              | Passwords must be identical.      |False     |
      | mail existed     | test@mailtothis.com     | ng         | ng        | 222      | 222             | Email already exist               |False     |
      | success          | @EMAIL@                 | Nga        | Nguyen    | 123456   | 123456          | You are logged in!                |True      |
    And close browser


  Scenario: Sign in by an account on storefront base on theme
    And verify customer sign in to shop
      | Case                | Email                  | Password | Result                                                                |Status    |
      | notmatch email pass | tes@maildrop.cc        | 123456   | You did not sign in correctly or your account is temporarily disabled |False     |
      | blank pass          | tes@maildrop.cc        |          |                                                                       |False     |
      | blank mail          |                        | 1234     |                                                                       |False     |
      | success             | linhnk1@mailtothis.com | 123456   | You are logged in!                                                    |True      |
    And close browser


  Scenario: Verify user change customer information successfully when user not logged in
    And add products "Lamp;Necklace" to cart then checkout
    And input Customer information
      | Case    | Email                  | Last Name | Address    | Apartment | City   | Country | State | Zip code | Phone          | Expected |
      | Sucesss | thotran@mailtothis.com | hehe      | 190 xa dan | haiduong  | Ha noi | Vietnam |       | 1000     | (+84)987724095 | success  |
    Then click change contact
    And input Customer information
      | Case    | Email           | Last Name | Address    | Apartment | City   | Country | Zip code | Phone      | Expected |
      | Sucesss | tho@yopmail.com | tho       | 360 Xa Dan | Hoang Mai | Ha noi | Vietnam | 1000     | 0336618988 | success  |
    And checkout successfully at shipping method page
    Then close browser

  Scenario: Verify user change customer information successfully when user already logged in
    And customer login to shop with username is "mydt@mailtothis.com" and password is "123456"
    And add products "Necklace" to cart then checkout
    Then verify customer information
      | Email               | First Name | Last Name | Address         | Apartment | City   | Country | Zip code |
      | mydt@mailtothis.com | My         | DT        | 360 Trung Phung |           | Ha noi | Vietnam | 10000    |
    And close browser
