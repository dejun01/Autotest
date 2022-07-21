Feature: Set up free shipping rules for PrinBase
#env = pbase_setup_free_shipping_rules

  Background: Delete all shipping zone
    Given user login to shopbase dashboard by API
    When user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And Untick all existing free shipping settings
      | Shipping zones                 |
      | DOMESTIC                       |
      | REST OF WORLD                  |
      | Australia                      |
      | Canada                         |
      | African and American countries |
      | Brazil                         |
      | Euro                           |
      | Some special countries         |

  Scenario: Configuration for free shipping rules in dashboard is failed
    When Tick free shipping rate for zone then input condition value for order then save
      | Zone          | Value   |
      | DOMESTIC      | 80      |
      | REST OF WORLD | @BLANK@ |
#    Then Verify message "Something went wrong, please try again later" is shown
    And Verify block input condition value for order is inactive
      | Shipping zones |
      | DOMESTIC       |
      | REST OF WORLD  |
    When Tick free shipping rate for zone then input condition value for order then save
      | Zone      | Value |
      | Australia | 80    |
      | Canada    | 60    |
#    Then Verify message "Something went wrong, please try again later" is shown
    And Verify block input condition value for order is inactive
      | Shipping zones |
      | Australia      |
      | Canada         |
    When Tick free shipping rate for zone then input condition value for order then save
      | Zone                           | Value |
      | African and American countries | 80    |
      | Brazil                         | abc/# |
#    Then Verify message "Something went wrong, please try again later" is shown
    And Verify block input condition value for order is inactive
      | Shipping zones                 |
      | Brazil                         |
      | African and American countries |

  Scenario: Configure successfully for free shipping rules in dashboard and the rule work on SF #SB_SET_SPP_FS_5 #SB_SET_SPP_FS_3
    When Tick free shipping rate for zone then input condition value for order then save
      | Zone          | Value |
      | DOMESTIC      | 80    |
      | Canada        | 70    |
#    Then Verify message "Saved successfully" is shown
    Then Verify block input condition value for order is active
      | Zone          | Value |
      | DOMESTIC      | 80    |
      | Canada        | 70    |
    And Verify block input condition value for order is inactive
      | Shipping zones                 |
      | REST OF WORLD                  |
      | Australia                      |
      | African and American countries |
      | Brazil                         |
      | Euro                           |
      | Some special countries         |
    Given open shop on storefront
    When add products "T-shirt-freeshipping>4" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules        | Price  |
      | Standard shipping | $11.96 |

    Given open shop on storefront
    When add products "T-shirt-freeshipping" to cart then navigate to checkout page
    Then verify shipping base on Profile rules
      | Base rules        | Price  |
      | Standard shipping | Free   |
    Then verify shipping fee on order summary is "Free"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method   | Price |
      | Standard shipping | Free  |
    And verify thank you page
