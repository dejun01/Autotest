Feature:Seller can input billing coupon on ShopBaseDashboard
#  sbase_billing_dashboard

  @couponTypePercentageOnSubscription @ShopInFreeTrial
  Scenario: Seller input Coupon type is percentage on subscription with shop in free trial #SB_SET_ACC_BILL_1 #SB_SET_ACC_BILL_2 #SB_SET_ACC_BILL_8 #SB_SET_ACC_BILL_12 #SB_SET_ACC_BILL_13 #SB_SET_ACC_BILL_14 #SB_SET_ACC_BILL_15
    Given user login to firstShop dashboard by API
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Seller pick a plan for your store is "Monthly" and input coupon is "Percentage on subscription" with verify plan with the first shop dashboard
      | Coupon      | Discount | Month |
      |             | 0        | 1     |
      | datatest10  | 10       | 1     |
      | datatest100 | 100      | 1     |


  @couponTypeFreeTrialExtend @ShopInFreeTrial
  Scenario: Seller input Coupon type is free trial extend with shop in free trial #SB_SET_ACC_BILL_3 #SB_SET_ACC_BILL_22 #SB_SET_ACC_BILL_23 #SB_SET_ACC_BILL_24 #SB_SET_ACC_BILL_25
    Given user login to firstShop dashboard by API
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Seller pick a plan for your store is "Monthly" and input coupon is "Free trial extend" with verify plan with the first shop dashboard
      | Coupon        | Discount | Month |
      | datatest10day | 10       | 1     |


  @couponTypePercentageOnSubscription @ShopOutOfFreeTrial
  Scenario: Seller input Coupon type is percentage on subscription with shop out of free trial #SB_SET_ACC_BILL_4 #SB_SET_ACC_BILL_15
    Given user login to secondShop dashboard by API
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Seller pick a plan for your store is "Monthly" and input coupon is "Percentage on subscription" with verify plan with the second shop dashboard
      | Coupon      | Discount | Month |
      |             | 0        | 1     |
      | datatest10  | 10       | 1     |
      | datatest100 | 100      | 1     |


  @couponTypeFreeTrialExtend @ShopOutOfFreeTrial
  Scenario: Seller input Coupon type is free trial extend with shop out of free trial #SB_SET_ACC_BILL_5
    Given user login to secondShop dashboard by API
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Seller pick a plan for your store is "Monthly" and input coupon is "Free trial extend" with verify plan with the second shop dashboard
      | Coupon        | Discount | Month |
      | datatest10day | 10       | 1     |


  @inputCouponFail
  Scenario: Input coupon fail #SB_SET_ACC_BILL_7 #SB_SET_ACC_BILL_9 #SB_SET_ACC_BILL_10 #SB_SET_ACC_BILL_11
    Given user login to secondShop dashboard by API
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Seller choose a plan for your store is "Monthly"
      | Coupon          | Discount | Limit | Error                    |
      | datatestdisable | 10       | 1     | Invalid or expired code. |
#      | datatestlimitredeemed     | 1        | 1     | Invalid or expired code.                  |
#      | DatatestAutoLimitTimeShop | 10       | 1     | You can only use this coupon code 1 time. |













