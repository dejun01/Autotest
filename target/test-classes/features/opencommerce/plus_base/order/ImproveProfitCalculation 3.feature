Feature: Improve UI profit calculation in order detail
#  evn: plb_profit_catulation

  Scenario: Verify banner freesub  #SB_RPLS_FSCPLB_38 #SB_RPLS_FSCPLB_40 #SB_RPLS_FSCPLB_42 #SB_RPLS_FSCPLB_44 #SB_RPLS_FSCPLB_45 #SB_RPLS_FSCPLB_46
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Slip On Silicone Strainer"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Plus       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard