Feature: Free Subscription
#  evn: plus_subscription

  Scenario: Verify banner freesub  #SB_RPLS_FSCPLB_38 #SB_RPLS_FSCPLB_40 #SB_RPLS_FSCPLB_42 #SB_RPLS_FSCPLB_44 #SB_RPLS_FSCPLB_45 #SB_RPLS_FSCPLB_46
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Slip On Silicone Strainer"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Plus       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    Then get order number default and verify banner shop activated
    And verify date trial shop active with
      | Message                                                                          |
      | The trial will end at @date. So your subscription will be calculated from @date. |
    Given admin login to shopTemplate dashboard
    And user navigate to "Orders" screen
    And Search and verify payment status = "Authorized" on order list
    And Select order authorized
    And "Approve order" order
    And Search and verify payment status = "Paid" on order list
    And redirect to plusbase dashboard
    Then Verify information banner after approved
    And redirect to shopTemplate dashboard
    And user navigate to "Orders" screen
    And cancel order plusbase
    And user navigate to "Orders" screen
    And Search and verify payment status = "Partially refunded" on order list
    And redirect to plusbase dashboard
    Then Verify information banner after cancel
    And close browser








