Feature: Zip code and State for matching
  #env = sbase_roller_checkout
  Background:
    Given open shop on storefront
    When add products "Wallet" to cart then navigate to checkout page

  Scenario: Verify state dropdown list for specific countries #SB_CHE_CHEN_9
    Then select country and verify state field
      | Country     | State           | Message                         |
      | Japan       | Prefecture      | Please enter a prefecture.      |
      | Australia   | State/Territory | Please enter a state/territory. |
      | New Zealand | Region          | Please enter a region.          |
      | Canada      | Province        | Please enter a province.        |
      | Hungary     | County          | Please enter a county.          |
      | Luxembourg  | Canton          | Please enter a canton.          |
      | Poland      | Voivodeship     | Please enter a voivodeship.     |

  Scenario: Verify zip code and state for matching #SB_CHE_CHEN_17 #SB_CHE_CHEN_16 #SB_CHE_CHEN_15 #SB_CHE_CHEN_14 #SB_CHE_CHEN_13 #SB_CHE_CHEN_10 #SB_CHE_CHEN_38
    Then verify zip code and state for matching
      | Country        | Zip code | State      | Message                                                            |
      | United States  | 90045    | California |                                                                    |
      | United States  | 90045    | Alabama    | Please enter a valid ZIP / postal code for Alabama, United States. |
      | Canada         | T9S0A1   | Alberta    |                                                                    |
      | Canada         | T9S0A1   | Manitoba   | Please enter a valid ZIP / postal code for Manitoba, Canada.       |
      | New Zealand    | 7843     | West Coast |                                                                    |
      | New Zealand    | 7843     | Auckland   | Please enter a valid ZIP / postal code for Auckland, New Zealand.  |
      | United Kingdom | LE100AD  | England    |                                                                    |
      | United Kingdom | LE100AD  | Wales      | Please enter a valid ZIP / postal code for Wales, United Kingdom.  |
      | Australia      | 3000     | Victoria   |                                                                    |
      | Australia      | 3000     | Tasmania   | Please enter a valid ZIP / postal code for Tasmania, Australia.    |
      | Germany        | 34454    | Hessen     |                                                                    |
      | Germany        | 34454    | Bremen     | Please enter a valid ZIP / postal code for Bremen, Germany.        |
      | France         | 20100    | Corse      |                                                                    |
      | France         | 20100    | Guyane     | Please enter a valid ZIP / postal code for Guyane, France.         |

  Scenario: Checkout successfully with zip code and region for matching #SB_CHE_CHEN_11
    When input Customer information
      | Email            | First Name | Last Name | Address | Country | City      | Zip code | State   | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Ath | Canada  | Athabasca | T9S0A1   | Alberta | 2025550147 |
    And choose shipping method ""
    And input billing address
      | First Name | Last Name | Address | Country | City        | Zip code | State  | Phone      |
      | Buyer      | Nguyen    | 360 Bad | Germany | Bad Arolsen | 34454    | Hessen | 2025550150 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And verify address on thank you page


