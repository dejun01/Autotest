Feature: Refund order
#env = sbase_dashboard

  Background:
    Given clear all data

  Scenario Outline: Verify order detail including status, net payment after refunding an order #SB_ORD_29 #SB_ORD_30 #SB_ORD_31 #SB_ORD_34 #SB_ORD_36 #SB_ORD_37
    # Pre-condition: shipping fee = 10$
    Given open shop on storefront
    Then checkout successfully via stripe with cart "<Product item>"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And refund order with "<Product item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    And verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
    And close browser
    Examples:
      | Product item | Restock item | Refund shipping | Reason for refund  | Status             | Net payment |
#      | t-shirt>5    | true         | $10.00          | Damaged in transit | Refunded           | $0.00       |
#      | t-shirt>5    | true         | $0.00           | Damaged in transit | Partially refunded | $10.00      |
      | t-shirt>1    | false        | $0.00           | Damaged in transit | Partially refunded | $10.00      |


  Scenario Outline: Verify refund an order applying discount and the same order refunded multiple times #SB_ORD_32 #SB_ORD_33 #SB_ORD_35
    # Pre-condition: create an order on storefront with shipping fee = 10$
    Given open shop on storefront
    And add products "t-shirt>4" to cart then checkout
    And apply discount code
      | Discount code | Discount value | Discount type |
      | DISCOUNT_50   | 50             | Percentage    |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
    And verify thank you page then get all information
    # Refund order on dashboard then verify
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    Then verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
    And verify refund order page
      | Message                                              | Remaining items | Refunded shipping                | Available for refund |
      | Some items in this order have already been refunded. | t-shirt>3       | $6.00 has already been refunded. | <Net payment>        |
    And close browser
    Examples:
      | Refunded item | Restock item | Refund shipping | Reason for refund  | Status             | Net payment                 |
      | t-shirt>1     | true         | $6.00           | Damaged in transit | Partially refunded | Paid by customer	- Refunded |

