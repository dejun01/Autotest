@shippingsetting @shippingItemBasedRate
#environment = sbase_shipping_itembased

Feature: Item-based rates
# Pre-condition: setting shipping rule on dashboard
#https://docs.google.com/spreadsheets/d/1761AxV06ihlxNbZ-P0zKsxFby5iZGlS5dH1bzyNssSA/edit#gid=702419343
#setting shipping rules as shown below for country = United States, Vietnam
#| Name        | Group    | Apply for                     | First item | Each additional item |
#| Rule1       | standard | Product SKU contains Rule1    | 5          | 1                    |
#| Rule2       | standard | Product SKU contains Rule2    | 6          | 2                    |
#| Rule3       | standard | Product SKU contains Rule3    | 7          | 3                    |
#| Rule4       | standard | Product SKU contains Rule4    | 5          | 4                    |
#| Rule5       | standard | Product SKU contains Rule5    | 7          | 1                    |
#| Rule6       | standard | Product SKU contains Rule6    | 8          | 6                    |
#| Freeship    | standard | Product SKU contains freeship | 0          | 0                    |
#| All product | standard |                               | 8          | 8                    |

  Background: clear data
    Given clear all data

  Scenario: Shipping Fee will be calculated correctly = First Item price + additional item 1 * the highest additional price
    Given open shop on storefront
    When add products "Rule4>4" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule4      | 17    |
    Given close browser

  Scenario: The rate that has the highest price of first item will be selected first
    Given open shop on storefront
    When add products "Rule2>2;Rule4>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule2      | 16    |
    Given close browser

  Scenario: Select a rate with the highest price of Additional Item
    Given open shop on storefront
    When add products "Rule3>2;Rule2Rule4>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule3      | 18    |
    Given close browser

  Scenario: There is no filter, all products will be applied this rate
    Given open shop on storefront
    When add products "Rule4>2;All product>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule4      | 25    |
    Given close browser

  Scenario:  The price of first item of all rates are the same, select a rate with the highest price of Additional Item
    Given open shop on storefront
    When add products "Rule3Rule5>2;Rule4>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule3      | 18    |
    Given close browser

  Scenario:  If both all products rates & specified products/collection rates meet the condition, select specified products rates
    Given open shop on storefront
    When add products "Rule3>2;Rule5>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule3      | 12    |
    Given close browser

  Scenario:  If the cart meet both fee rule & free ship rule, select fee rate
    Given open shop on storefront
    When add products "Rule5>2;Rule2AndFreeship>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Price rules
      | Base rules | Price |
      | Rule5      | 12    |
    Given close browser