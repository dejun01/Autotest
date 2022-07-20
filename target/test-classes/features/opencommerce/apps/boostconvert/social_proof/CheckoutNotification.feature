Feature: Checkout Notification

#  Env:
#  prod_copt_product_countdown
#  staging_copt_product_countdown

#  Theme: Inside, Roller
#  Shipping zone: United States
#  Payment method
#  Products: Candles


  Scenario: Delete notification
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Social Proof>Notification list" screen
    And delete all sales notification of Copt
    Given confirm list sales notification have 0 notification


  Scenario: Precondition - Create order
    Given open shop on storefront
    When add multiple products "Candles" to cart then checkout
    And checkout by Stripe successfully
    And close browser
    Then verify sales notification synced on Copt by API

  Scenario Outline: Verify Message setting for Checkout Notification #SB_SF_BC_46 #SB_SF_BC_47 #SB_SF_BC_48 #SB_SF_BC_49
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Social Proof>Pop types" screen
    And user turn "off" "Sales notifications"
    And user turn "<Status checkout noti>" "Checkout notifications"
    When change checkout notification settings of BoostConvert as "<KEY>"
      | KEY | Message                                                                                  |
      | 1   | {{purchased_number}} customer(s) has bought this {{product_title}} in the last 24 hours. |
      | 2   | 20 khach hang da mua san pham nay trong 24 gio.                                          |
    And wait 5 second
    And open shop on storefront
    Then verify checkout notifications of BoostConvert on Shopbase page as "<KEY>"
      | KEY | Product added | is show | Message                                                                                  |
      | 1   | Candles       | true    | {{purchased_number}} customer(s) has bought this {{product_title}} in the last 24 hours. |
      | 2   | Candles       | true    | 20 khach hang da mua san pham nay trong 24 gio.                                          |
      | 3   | Candles       | false   |                                                                                          |
    Examples:
      | KEY | Status checkout noti |
      | 1   | on                   |
      | 2   | on                   |
      | 3   | off                  |

