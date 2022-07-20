@shippingsetting @shippingItemBasedRateCondition
#environment = sbase_shipping1

Feature: Setting Shipping zones Item Based rate rule

  Background: Navigate to setting shipping zones page
    Given clear all data
    And  user login to shopbase dashboard
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone

#contains
  Scenario: Verify shipping item based match rule contains keywords in condition
    Given add shipping zones with country and Price Based rate
      | Zone name              | Countries     | Price based rate |
      | Rest of world shipping | Rest of world | Standard,,,,5    |
    And add item based rate with condition for zone "Rest of world shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords       | Free rate | First item | Each additional item |
      | contains1 | hello1     | Product SKU    | contains  | containSKU     | True      |            |                      |
      | contains2 | hello2     | Product Type   | contains  | containsType   | True      |            |                      |
      | contains3 | hello3     | Product Vendor | contains  | containsVendor | True      |            |                      |
#    And user navigate to "Products>All products" screen
#    And edit information of product on dashboard "Necklace"
#      | Edit item | New Value       |
#      | SKU       | containSKU@     |
#      | Type      | containsType@   |
#      | Vendor    | containsVendor@ |
    And open shop on storefront
    And add products "Necklace" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                     | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | oanhnguyen@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Standard   | 5     |
      | contains1  | 0     |
      | contains2  | 0     |
      | contains3  | 0     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

#not contains
  Scenario: Verify shipping item based match rule does not contains keywords in condition
    Given add shipping zones with country and Price Based rate
      | Zone name              | Countries     | Price based rate |
      | Rest of world shipping | Rest of world | Standard,,,,5    |
    And add item based rate with condition for zone "Rest of world shipping"
      | Rate Name   | Group Name | Condition item | Condition        | Keywords          | Free rate | First item | Each additional item |
      | notcontain1 | hello1     | Product SKU    | does not contain | notContainsSKU    | True      |            |                      |
      | notcontain2 | hello2     | Product Type   | does not contain | notContainsType   | True      |            |                      |
      | notcontain3 | hello3     | Product Vendor | does not contain | notContainsVendor | True      |            |                      |
#    And user navigate to "Products>All products" screen
#    And edit information of product on dashboard "Yonex shoes"
#      | Edit item | New Value    |
#      | SKU       | checkSKU@    |
#      | Type      | checkType@   |
#      | Vendor    | checkVendor@ |
    And open shop on storefront
    And add products "Yonex shoes" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                     | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | oanhnguyen@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules  | Price |
      | Standard    | 5     |
      | notcontain1 | 0     |
      | notcontain2 | 0     |
      | notcontain3 | 0     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

#start with
  Scenario: Verify shipping item based match rule start with keywords in condition
    Given add shipping zones with country and Price Based rate
      | Zone name              | Countries     | Price based rate |
      | Rest of world shipping | Rest of world | Standard,,,,5    |
    And add item based rate with condition for zone "Rest of world shipping"
      | Rate Name | GROUP  | FILTER                                       | EXCLUSION | Free rate | First item | Each additional item |
      | start1    | hello1 | [Product SKU] [starts with] [startSKU]       |           | True      |            |                      |
      | start2    | hello2 | [Product Type] [starts with] [startType]     |           | True      |            |                      |
      | start3    | hello3 | [Product Vendor] [starts with] [startVendor] |           | True      |            |                      |
#    And user navigate to "Products>All products" screen
#    And edit information of product on dashboard "Victor shoes"
#      | Edit item | New Value      |
#      | SKU       | startSKU23@    |
#      | Type      | startType23@   |
#      | Vendor    | startVendor23@ |
    And open shop on storefront
    And add products "Victor shoes" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                     | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | oanhnguyen@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Standard   | 5     |
      | start1     | 0     |
      | start2     | 0     |
      | start3     | 0     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

#end with
  Scenario: Verify shipping item based match rule end with keywords in condition
    Given add shipping zones with country and Price Based rate
      | Zone name              | Countries     | Price based rate |
      | Rest of world shipping | Rest of world | Standard VN,,,,5 |
    And add item based rate with condition for zone "Rest of world shipping"
      | Rate Name | GROUP  | FILTER                                   | EXCLUSION | Free rate | First item | Each additional item |
      | end1      | hello1 | [Product SKU] [ends with] [endSKU]       |           | True      |            |                      |
      | end2      | hello2 | [Product Type] [ends with] [endType]     |           | True      |            |                      |
      | end3      | hello3 | [Product Vendor] [ends with] [endVendor] |           | True      |            |                      |
#    And user navigate to "Products>All products" screen
#    And edit information of product on dashboard "Kawasaki shoes"
#      | Edit item | New Value   |
#      | SKU       | 12endSKU    |
#      | Type      | 12endType   |
#      | Vendor    | 12endVendor |
    And open shop on storefront
    And add products "Kawasaki shoes" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                     | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | oanhnguyen@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Standard VN| 5     |
      | end1       | 0     |
      | end2       | 0     |
      | end3       | 0     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

#equal
  Scenario: Verify shipping item based match rule equal with keywords in condition
    Given add shipping zones with country and Price Based rate
      | Zone name              | Countries     | Price based rate |
      | Rest of world shipping | Rest of world | Standard VN,,,,5 |
    And add item based rate with condition for zone "Rest of world shipping"
      | Rate Name | GROUP  | FILTER                                       | EXCLUSION | Free rate | First item | Each additional item |
      | equal1    | hello1 | [Product SKU] [is equal to] [equalSKU]       |           | True      |            |                      |
      | equal2    | hello2 | [Product Type] [is equal to] [equalType]     |           | True      |            |                      |
      | equal3    | hello3 | [Product Vendor] [is equal to] [equalVendor] |           | True      |            |                      |
      | equal4    | hello4 | [Product Tag] [is equal to] [equalTag]       |           | True      |            |                      |
#    And user navigate to "Products>All products" screen
#    And edit information of product on dashboard "Brave Sword 12"
#      | Edit item | New Value   |
#      | SKU       | equalSKU    |
#      | Type      | equalType   |
#      | Vendor    | equalVendor |
#      | Tags      | equalTag    |
    And open shop on storefront
    And add products "Brave Sword 12" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                     | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | oanhnguyen@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Standard VN| 5     |
      | equal1     | 0     |
      | equal2     | 0     |
      | equal3     | 0     |
      | equal4     | 0     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

#  not equal
  Scenario: Verify shipping item based match rule not equal with keywords in condition
    Given add shipping zones with country and Price Based rate
      | Zone name              | Countries     | Price based rate |
      | Rest of world shipping | Rest of world | Standard VN,,,,5 |
    And add item based rate with condition for zone "Rest of world shipping"
      | Rate Name | GROUP      | FILTER                                              | EXCLUSION       | Free rate      | First item | Each additional item |
      | notequal1 | hello1     | [Product SKU] [is not equal to] [notequalSKU]       |                 | True           |            |                      |
      | notequal2 | hello2     | [Product Type] [is not equal to] [equalType]        |                 | True           |            |                      |
      | notequal3 | hello3     | [Product Vendor] [is not equal to] [notequalVendor] |                 | True           |            |                      |
#    And user navigate to "Products>All products" screen
#    And edit information of product on dashboard "LiNing Shoes"
#      | Edit item | New Value    |
#      | SKU       | equalSKU@    |
#      | Type      | equalType@   |
#      | Vendor    | equalVendor@ |
    And open shop on storefront
    And add products "LiNing Shoes" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                     | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | oanhnguyen@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Standard VN| 5     |
      | notequal1  | 0     |
      | notequal2  | 0     |
      | notequal3  | 0     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
