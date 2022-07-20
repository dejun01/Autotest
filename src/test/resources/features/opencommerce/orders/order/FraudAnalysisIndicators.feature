@FraudAnalysisIndicator
Feature: Fraud Analysis Indicator
  #env = sbase_billing_checking
  Background:
    Given clear all data

  @fraudanaindicator
  Scenario: Verify the fraud indicators when buyer tries only 1 time to complete order
     ### Go shopping ----------------------------------------------------------------------------------------------------
    Given open shop on storefront
    And add products "Shirt>1" to cart then checkout
    And input Customer information
      | Email                 | First Name | Last Name | Address           | City         | Country       | State    | Zip code | Phone      | Saved | Expected |
      | tester@mailtothis.com | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | New York | 10001    | 2025550141 | true  | success  |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4000000000000044 | John Doe        | 12/22        | 111 |
    And verify thank you page
      ### Check Fraud indicator of order ---------------------------------------------------------------------------------
    Given user login to shopbase dashboard
    Given user navigate to "Orders>All orders" screen
    And click on order name in unfulfilled orders list
    Given Check indicator content and color
      | Indicators                                                                                        | Color | Class value                            |
      | Card Verification Value (CVV) is correct                                                          | green | fraud-indicator fraud-indicator__trust |
      | Billing address or credit card's address wasn't available                                         | gray  | fraud-indicator fraud-indicator__none  |
      | Billing address ZIP or postal code isn't available to match with credit card's registered address | gray  | fraud-indicator fraud-indicator__none  |
      | There was 1 payment attempt                                                                       | green | fraud-indicator fraud-indicator__trust |
      | Location of IP address used to place the order is                                                 | gray  | fraud-indicator fraud-indicator__none  |
      | Billing country matches the country from which the order was placed                               | green | fraud-indicator fraud-indicator__trust |
      ### If run on server 2 indicators below are always red. If testing on local, 1 indicator is always green and the rest one is always gray
      | The IP address used to place the order is a high risk internet connection                         | red   | fraud-indicator fraud-indicator__risk  |
      | Customer used a web proxy to fake their IP address                                                | red   | fraud-indicator fraud-indicator__risk  |

  Scenario: Verify the fraud indicators when buyer tries 6 times to complete order and Billing country does not match the country from which the order was placed
    ### Go shopping ----------------------------------------------------------------------------------------------------
    Given open shop on storefront
    And add products "Shirt>1" to cart then checkout
    And input Customer information
      | Email                  | First Name | Last Name | Address    | City  | Country | Zip code | Phone      |
      | tester1@mailtothis.com | Jone       | Doe       | 360 Xa Dan | Hanoi | Vietnam | 10000    | 0978735311 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4000000000000069 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000127 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000069 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000127 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000069 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000044 | John Doe        | 12/22        | 113 |
    And verify thank you page
    ### Check Fraud indicator of order ---------------------------------------------------------------------------------
    Given user login to shopbase dashboard
    Given user navigate to "Orders>All orders" screen
    Then click on order name in unfulfilled orders list
    Given Check indicator content and color
      | Indicators                                                                                        | Color | Class value                            |
      | Card Verification Value (CVV) is correct                                                          | green | fraud-indicator fraud-indicator__trust |
      | Billing address or credit card's address wasn't available                                         | gray  | fraud-indicator fraud-indicator__none  |
      | Billing address ZIP or postal code isn't available to match with credit card's registered address | gray  | fraud-indicator fraud-indicator__none  |
      | There was 6 payments attempt                                                                      | red   | fraud-indicator fraud-indicator__risk  |
      | Location of IP address used to place the order is                                                 | gray  | fraud-indicator fraud-indicator__none  |
      | The billing address is listed as                                                                  | red   | fraud-indicator fraud-indicator__risk  |
      ### If run on server 2 indicators below are always red. If testing on local, 1 indicator is always green and the rest one is always gray
      | The IP address used to place the order is a high risk internet connection                         | red   | fraud-indicator fraud-indicator__risk  |
      | Customer used a web proxy to fake their IP address                                                | red   | fraud-indicator fraud-indicator__risk  |

  Scenario: Verify the fraud indicators when buyer tries 3 times to complete order
    ### Go shopping ----------------------------------------------------------------------------------------------------
    Given open shop on storefront
    And add products "Shirt>1" to cart then checkout
    And input Customer information
      | Email                  | First Name | Last Name | Address           | City         | Country       | State    | Zip code | Phone      |
      | tester2@mailtothis.com | Shane      | Caffrey   | 100 Wilshire Blvd | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4000000000000127 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000069 | John Doe        | 12/22        | 113 |
      | Credit Card    | 4000000000000044 | John Doe        | 12/22        | 113 |
    And verify thank you page
    ### Check Fraud indicator of order ---------------------------------------------------------------------------------
    Given user login to shopbase dashboard
    Given user navigate to "Orders>All orders" screen
    Then click on order name in unfulfilled orders list
    Given Check indicator content and color
      | Indicators                                                                                        | Color | Class value                            |
      | Card Verification Value (CVV) is correct                                                          | green | fraud-indicator fraud-indicator__trust |
      | Billing address or credit card's address wasn't available                                         | gray  | fraud-indicator fraud-indicator__none  |
      | Billing address ZIP or postal code isn't available to match with credit card's registered address | gray  | fraud-indicator fraud-indicator__none  |
      | There was 3 payments attempt                                                                      | gray  | fraud-indicator fraud-indicator__none  |
      | Location of IP address used to place the order is                                                 | gray  | fraud-indicator fraud-indicator__none  |
      | Billing country matches the country from which the order was placed                               | green | fraud-indicator fraud-indicator__trust |
      ### If run on server 2 indicators below are always red. If testing on local, 1 indicator is always green and the rest one is always gray
      | The IP address used to place the order is a high risk internet connection                         | red   | fraud-indicator fraud-indicator__risk  |
      | Customer used a web proxy to fake their IP address                                                | red   | fraud-indicator fraud-indicator__risk  |
    
    And open url "<string>"