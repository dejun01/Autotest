Feature: Settings - Inside Theme - Global setting
    # sbase_inside_settings
    #prod_sbase_inside_settings
    #staging_sbase_inside_settings
    #dev_sbase_inside_settings

  Scenario: Pre-condition_Create oder #SB_OLS_THE_INS_24
    Given open shop on storefront
    And add products "Test 2" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
#    And verify thank you page then get all information

  Scenario Outline: Verify show sort in search result #SB_OLS_THE_INS_25 #SB_OLS_THE_INS_28 #SB_OLS_THE_INS_27 #SB_OLS_THE_INS_26
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And user change header layout as "<KEY>"
      | KEY | Layout  |
      | 1   | Inline  |
      | 2   | Rich    |
      | 3   | Inline  |
      | 4   | Minimal |
    And user change search settings as "<KEY>"
      | KEY | Enable sort | Default logic |
      | 1   | true        | Most viewed   |
      | 2   | false       | Best selling  |
      | 3   | true        | Best selling  |
      | 4   | true        | Newest        |
    And wait 5 second
    Then open shop on storefront
    And open page "/search"
    And verify show sort on Store front as "<KEY>"
      | KEY | Key search | Show sort on modal search | Show sort on search result page | Default logic | Param           |
      | 1   | Contact    | false                     | false                           |               |                 |
      | 1   | Test       | true                      | true                            | Most viewed   | sort=viewed     |
      | 2   | Test       | false                     | false                           |               | sort=sold       |
      | 3   | Test       | true                      | true                            | Best selling  | sort=sold       |
      | 4   | Test       | true                      | true                            | Newest        | sort=created_at |

    Then choose sort option and verify sort result as "<KEY>"
      | KEY | Sort option  | Sort result      | Products handle      | Key search |
      | 4   | Most viewed  | sort=viewed     | test-2               | &q=Test    |
      | 4   | Best selling | sort=sold       | test-2               | &q=Test    |
      | 4   | Newest       | sort=created_at | test-3,test-2,test-1 | &q=Test    |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |

  Scenario Outline: Verify global setting #SB_OLS_THE_INS_25
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And user change global settings as "<KEY>"
      | KEY | Show sort in collection page | Pagination    | Product image display    | Content alignment | Show sales banner | Show Add to cart button | Shape | Enable back to top icon |
      | 1   | true                         | Lazy load     | Keep default image ratio | Center            | true              | true                    | Sharp | false                   |
      | 2   | false                        | Paging number | Square ratio             | Left              | false             | false                   | Round | true                    |
      | 3   | true                         | Paging number | Portraits                | Left              | true              | true                    | Cut   | true                    |
    Then open shop on storefront
    And verify global setting on storefront as "<KEY>"
      | KEY | Page                | Show sort in collection page | Pagination    | Product image display    | Content alignment | Show sales banner | Show Add to cart button | Shape | Enable back to top icon |
      | 1   | /                   |                              |               | Keep default image ratio | Center            | true              | true                    | Sharp | false                   |
      | 1   | /collections/summer | true                         | Lazy load     | Keep default image ratio | Center            | true              | true                    | Sharp | false                   |
      | 2   | /                   |                              |               | Square ratio             | Left              | false             | false                   | Round | true                    |
      | 2   | /collections/summer | false                        | Paging number | Square ratio             | Left              | false             | false                   | Round | true                    |
      | 3   | /                   |                              |               | Portraits                | Left              | true              | true                    | Cut   | true                    |
      | 3   | /collections/summer | true                         | Paging number | Portraits                | Left              | true              | true                    | Cut   | true                    |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
