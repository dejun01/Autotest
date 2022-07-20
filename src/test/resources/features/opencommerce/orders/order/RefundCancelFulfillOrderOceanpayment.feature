Feature: Refund, cancel and fulfill order oceanpayment

  #sbase_checkout_oceanpayment
  Scenario Outline: refund order detail including status, net payment after refunding an order #SB_CHE_OCP_5 #SB_CHE_OCP_6 #SB_CHE_OCP_7
    Given  open shop on storefront
    When checkout successfully via OceanPayment with cart "Bikini>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And get all information order
    Given user login to shopbase dashboard
    When open order detail dashboard by ID
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    And verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
    And close browser
    Examples:
      | Refunded item | Restock item | Refund shipping | Reason for refund  | Status             | Net payment |
      | Bikini>5      | true         | $5.00           | Damaged in transit | Refunded           | $0.00       |
      | Bikini>5      | true         | $0.00           | Damaged in transit | Partially refunded | $5.00       |

#  Scenario: Verify cancel an order oceanpayment successfully
#    Given user login to shopbase dashboard
#    And user navigate to "Products" screen
#    And get quantity of products "shirt" before cancelling
#    Given open shop on storefront
#    When checkout successfully via OceanPayment with cart "shirt>5"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
#    And get all information order
#    Given user login to shopbase dashboard by API
#    When open order detail dashboard by ID
#    Then open cancel popup to verify information is displayed correctly
#    And cancel fully order and restock items
#    And user navigate to "Products>All products" screen
#    Then get quantity of products "shirt" after cancelling or refunding
#    And close browser

  Scenario Outline: Fulfill an order oceanpayment successfully #SB_CHE_OCP_8 #SB_CHE_OCP_9
    Given open shop on storefront
    When checkout successfully via OceanPayment with cart "shirt>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    And get all information order
    Given user login to shopbase dashboard
    When open order detail dashboard by ID
    And fulfill the order with "<Fulfill item>", "<Tracking number>", "<Shipping carrier>", "<TrackingUrl>" and "<Status>"
    Examples:
      | Fulfill item | Tracking number | Shipping carrier    | TrackingUrl | Status              |
      | shirt>5      | test            | China EMS (ePacket) |             | Fulfilled           |
      | shirt>2      | test            | China EMS (ePacket) |             | Partially Fulfilled |
