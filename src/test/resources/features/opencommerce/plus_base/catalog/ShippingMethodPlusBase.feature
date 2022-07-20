Feature: Shipping Method PlusBase
  #plusbase_shipping_fee
  Background: Clear data
    Given clear all data

  Scenario: Verify shipping fee in checkout page after import product plusbase of 2 products #SPF_14 #SPF_9
    Given user login to shopbase dashboard
    When user navigate to "plusbase/catalog" screen by url
    Given get shipping fee on catalog detail and calculate shipping fee
      | Product                                           | Variant            | Is First item | Quantity |
      | (Test product) (Dont update) Product Automation 1 | Color:Beige/Size:S | False         | 2        |
      |                                                   | Color:Beige/Size:M | True          | 2        |
      | (Test product) (Dont update) Product Automation 2 | Color:Beige/Size:M | True          | 2        |
    Given open shop on storefront
    And add products "(Test product) (Dont update) Product Automation 1:S>2;(Test product) (Dont update) Product Automation 1:M>2;(Test product) (Dont update) Product Automation 2:M>2" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And verify shipping fee PlusBase in Shipping method page when checkout
    And close browser

  Scenario: Verify shipping fee in checkout page after import product plusbase of 1 product #SPF_13 #SPF_12 #SPF_9
    Given user login to shopbase dashboard
    When user navigate to "plusbase/catalog" screen by url
    Given get shipping fee on catalog detail and calculate shipping fee
      | Product                                           | Variant            | Is First item | Quantity |
      | (Test product) (Dont update) Product Automation 1 | Color:Beige/Size:S | False         | 2        |
      |                                                   | Color:Beige/Size:M | True          | 2        |
    Given open shop on storefront
    And add products "(Test product) (Dont update) Product Automation 1:S>2;(Test product) (Dont update) Product Automation 1:M>2" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And verify shipping fee PlusBase in Shipping method page when checkout
    And close browser

  Scenario: Verify shipping fee in checkout page after markup shipping fee
    Given calculate shipping fee after mark up shipping fee
      | First item | Additional item | Quantity |
      | 0          | 10.39           | 2        |
      | 14.99      | 13.55           | 3        |
    Given open shop on storefront
    And add products "(Test product) (Ko xoa)French Vintage Midi Dress Women:S>2;(Test product) (Ko xoa)French Vintage Midi Dress Women:M>3" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And verify shipping fee PlusBase in Shipping method page when checkout
    And close browser

  Scenario: Verify shipping fee in checkout page after markup shipping fee for 1 product variant #SPF_11
    Given calculate shipping fee after mark up shipping fee
      | First item | Additional item | Quantity |
      | 14.99      | 13.55           | 4        |
    Given open shop on storefront
    And add products "(Test product) (Ko xoa)French Vintage Midi Dress Women:M>4" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And verify shipping fee PlusBase in Shipping method page when checkout
    And close browser
