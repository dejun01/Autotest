Feature: COD-Checkout
#sbase_cod_checkout

  Background:
    Given clear all data
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And choose manual payment method is "COD"

  Scenario Outline: Verify checkout flow when using COD method without post purchase item
    When order product "" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    And user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
#    And user navigate to "Orders" screen
#    And click the specific order "#1047"

    And verify state of block Mark as paid is shown
    And verify status btn Refund item is hidden

    When Change order "COD" status to Paid
    Then verify order detail on dashboard
    And verify state of block Mark as paid is hidden
    And verify status btn Refund item is shown

    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    And verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
    And close browser
    Examples:
      | Refunded item | Restock item | Refund shipping | Reason for refund  | Status             | Net payment |
      | Bikini>1      | true         | $10.00          | Damaged in transit | Refunded            | $0.00      |


  Scenario: Verify checkout flow when using COD method with post purchase item
    And order product "MC 2" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    And user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And verify state of block Mark as paid is shown
    And verify status btn Refund item is hidden

    And close browser

