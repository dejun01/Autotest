Feature: Shipping profile rules
  #env = sbase_shipping_profile_printbase

#  - Shop template auto stag:
#  acc: nganmai@beeketing.net
#  shop: shippingprofilerules.stag.myshopbase.net - 10074016

#  - Shop template auto prod:
#  acc: linhnguyen@beeketing.net
#  shop: au-shipping-profile-rules.onshopbase.com - 10213851

#  - Nhờ dev config trong consul "/printbase/conf.toml"
#  shipping_profile_test_mode_shop_ids={10213851=["10255366"]} # {templateShopId=["pbaseShopId","pbaseShopId"]}

  Background: clear all data
    Given clear all data

  Scenario: verify shipping fee when order have 2 item with different group, different rate name
     """Pre-condition: setting shipping rules as shown below for shipping address = US"
     | Profile       | Rate name         | First item | Each additional item |
     | xx1_VN_Rule21 | Economic Shipping | 7          | 2                    |
     | xx1_VN_Rule21 | Expedite Shipping | 7          | 4                    |
     | xx2_VN_Rule6  | Economic          | 7          | 3                    |
     | xx2_VN_Rule6  | Expedite          | 8          | 6                    |
     => Standard Shipping = Total MIN first item price of each item + Total addtional item price of profile have min first item price
        Fast Shipping = Total MAX first item price of each item + Total addtional item price of profile have max first item price
         | Standard Shipping   = 7+2+7+3 = $19.00 |
         | Fast Shipping       = 7+4+8+6 = $25.00 |"""
    Given open shop on storefront
    When add products "Rule21>2;Rule6>2" to cart then navigate to checkout page
    And input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | United States | California | 90001    |
    Then verify shipping fee on order summary is "$19.00"
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Standard Shipping   | $19.00 |
      | Fast Shipping       | $25.00 |
    And close browser

  Scenario: verify shipping fee will be changed when the shipping address is changed
      """Pre-condition: setting shipping rules as shown below for item = "Test regions"
  | Profile                                           | Zone         |Rate name      | First item | Each additional item |
  | xx_region1_limited-edition-emto-b150618-rug-small | Russia       | Russia        | 10         | 2                    |
  | xx_region2_limited-edition-emto-b150618-rug-small | California   | Cali Express  | 10         | 2                    |
  | xx_region2_limited-edition-emto-b150618-rug-small | California   | Cali Standard | 5          | 2                    |
  | xx_region2_limited-edition-emto-b150618-rug-small | United state | US shipping   | 7          | 2                    |
  => Shipping fee will be changed if buyer change shipping address to the other zone"""
    Given open shop on storefront
    When add products "Test regions>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Cali Express        | $12.00 |
      | Cali Standard       | $7.00  |
    Then verify shipping fee on order summary is "$7.00"

    When input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | United States | Hawaii     | 96737    |
    Then verify shipping base on Profile rules
      | Base rules      | Price  |
      | US shipping     | $9.00  |
    Then verify shipping fee on order summary is "$9.00"

    When input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | Russia        | Altai Krai | 659408   |
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Russia              | $12.00 |
    Then verify shipping fee on order summary is "$12.00"

    When input Customer information
      | City      | Country     | State             | Zip code |
      | Murdoch   | Australia   | Western Australia | 6001     |
    Then verify the message along with non shippable products is displayed
      | Product not shippable | Message                                                                                                                |
      | Test regions          | The following items don’t ship to your location. Please replace them with another products and place your order again. |
    And close browser

  Scenario: verify shipping fee when order have 2 item with same group, same rate name
    """ Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile       | Rate name         | First item | Each additional item |
  | xx1_VN_Rule21 | Economic Shipping | 7          | 2                    |
  | xx1_VN_Rule21 | Expedite Shipping | 7          | 4                    |
  | xx1_VN_Rule22 | Economic Shipping | 6          | 2                    |
  | xx1_VN_Rule22 | Expedite Shipping | 7          | 5                    |
  => Show the same rate name
     Shipping fee = Highest item price (or 1st fee of item have highest total fee) + Total Additional item(s) price of each item
      | Economic Shipping   = 7+2+2+2 = $13.00 |
      | Expedite Shipping   = 7+5+4+4 = $20.00 |"""
    Given open shop on storefront
    When add products "Rule21>2;Rule22>2" to cart then navigate to checkout page
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | David      | Murphy    | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |
    Then verify shipping fee on order summary is "$13.00"
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Economic Shipping   | $13.00 |
      | Expedite Shipping   | $20.00 |
    And choose shipping method "Expedite Shipping"
    Then verify shipping fee on order summary is "$20.00"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And verify shipping method after add PPC
      | Shipping method   | Price  |
      | Expedite Shipping | $20.00 |
    And verify thank you page
    And get the order information including
      | order id | shipping method | shipping fee |

    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify shipping fee in order
#    Then verify profit of order
#      | Product Base info                          | Production rate | Staging rate |
#      | shirt 01 test  Long Sleeve Tee , White , S | 3,4             | 5,6          |
#      | mug-00  Beverage Mug , white , 11oz        | 3,4             | 5,6          |

  Scenario: only show the same rate name when order have 2 item same 1 rate name
    """Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile       | Rate name         | First item | Each additional item |
  | xx1_VN_Rule21 | Economic Shipping | 7          | 2                    |
  | xx1_VN_Rule21 | Expedite Shipping | 7          | 4                    |
  | xx1_VN_Rule3  | Economic Shipping | 7          | 5                    |
  | xx1_VN_Rule3  | Expedite          | 8          | 2                    |
  => Only show the same rate name
     Shipping fee = Highest item price (or 1st fee of item have highest total fee) + Total Additional item(s) price of each item
      |Economic Shipping = 7+5+2+2 = $16 |"""
    Given open shop on storefront
    When add products "Rule21>2;Rule3>2" to cart then navigate to checkout page
    And input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Economic Shipping   | $16.00 |
    Then verify shipping fee on order summary is "$16.00"
    And close browser

  Scenario: verify shipping fee when order have 2 item with different group, same rate name
    """ Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile       | Rate name         | First item | Each additional item |
  | xx1_VN_Rule21 | Economic Shipping | 7          | 2                    |
  | xx1_VN_Rule21 | Expedite Shipping | 7          | 4                    |
  | xx2_VN_Rule4  | Economic Shipping | 5          | 3                    |
  | xx2_VN_Rule4  | Expedite Shipping | 8          | 6                    |
  => Show the same rate name
     Shipping fee = Total first item price of each item + Total addtional item price of each item
      | Economic Shipping   = 7+2+5+3 = $17.00 |
      | Expedite Shipping   = 7+4+8+6 = $25.00 |"""
    Given open shop on storefront
    When add products "Rule21>2;Rule4>2" to cart then navigate to checkout page
    And input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Economic Shipping   | $17.00 |
      | Expedite Shipping   | $25.00 |
    Then verify shipping fee on order summary is "$17.00"
    And close browser

  Scenario: verify shipping fee when order have 2 item with same group, different rate name
    """ Pre-condition: setting shipping rules as shown below for shipping address = US"
  | Profile       | Rate name         | First item | Each additional item |
  | xx1_VN_Rule21 | Economic Shipping | 7          | 2                    |
  | xx1_VN_Rule21 | Expedite Shipping | 7          | 4                    |
  | xx1_VN_Rule5  | Economic          | 5          | 3                    |
  | xx1_VN_Rule5  | Expedite          | 8          | 6                    |
  => Standard Shipping = Max of (lowest 1st item price in all profile of order's item) + Total Additional item price of profile have lowest 1st item price of each item
     Fast Shipping = Max of (highest 1st item price in all profile of order's item) + Total Additional item price of profile have highest 1st item price of each item
      | Standard Shipping   = 7+2+3+3 = $15.00 |
      | Fast Shipping       = 7+4+8+6 = $22.00 |"""
    Given open shop on storefront
    When add products "Rule21>2;Rule5>2" to cart then navigate to checkout page
    And input Customer information
      | City        | Country       | State      | Zip code |
      | Los Angeles | United States | California | 90001    |
    Then verify shipping base on Profile rules
      | Base rules          | Price  |
      | Standard Shipping   | $15.00 |
      | Fast Shipping       | $22.00 |
    Then verify shipping fee on order summary is "$15.00"
    And close browser



