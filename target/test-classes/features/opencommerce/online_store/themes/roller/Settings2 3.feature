Feature: Settings - Roller Theme - Global setting
    # sbase_roller_settings2
    #prod_sbase_roller_settings2
    # staging_sbase_roller_settings2
    #dev_sbase_roller_settings2


  Scenario: Pre-condition_Create oder #SB_OLS_THE_ROL_99
    Given open shop on storefront
    And add products "Test 2" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And input card information of Stripe and complete order
      | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
    And verify thank you page then get all information

  Scenario Outline: Verify show sort in search result #SB_OLS_THE_ROL_100 #SB_OLS_THE_ROL_101 #SB_OLS_THE_ROL_102 #SB_OLS_THE_ROL_103 #SB_OLS_THE_ROL_104 #SB_OLS_THE_ROL_105 #SB_OLS_THE_ROL_106 #SB_OLS_THE_ROL_107 #SB_OLS_THE_ROL_108 #SB_OLS_THE_ROL_109
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And user change search settings as "<KEY>"
      | KEY | Enable sort | Default logic |
      | 1   | true        | Most viewed   |
      | 2   | false       | Best selling  |
      | 3   | true        | Best selling  |
      | 4   | true        | Newest        |
    Then open shop on storefront
    And open page "/search"
    And verify show sort on Store front as "<KEY>"
      | KEY | Key search | Show sort on search result page | Default logic | Param           |
      | 1   | Test       | true                            | Most Viewed   | sort=viewed     |
      | 1   | Contact    | false                           | false         |                 |
      | 2   | Test       | false                           |               | sort=sold       |
      | 3   | Test       | true                            | Best Selling  | sort=sold       |
      | 4   | Test       | true                            | Newest        | sort=created_at |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |

 # Scenario: Verify sort result on store front
 #   Given open shop on storefront
 #   When open page "/search"
 #   And input key search with "test"
 #   Then choose sort option and verify sort result as
 #     | Sort option  | Sort result      | Products handle      |
 #     | Most viewed  | &sort=viewed     | test-2,test-1,test-3 |
 #     | Best selling | &sort=sold       | test-2,test-1,test-3 |
 #     | Newest       | &sort=created_at | test-3,test-2,test-1 |
