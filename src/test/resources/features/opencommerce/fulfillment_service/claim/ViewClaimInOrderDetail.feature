Feature: View claim order in order detail
  #env =sbase_claim

  Scenario Outline: Verify with order not yet fulfill with sbase
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product unmap>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And  verify the number of unfulfilled orders in sidebar menu after checking out
    Then click on order name in unfulfilled orders list
    And fulfill the order with "<Fulfilled Items>", "<Tracking number>", "<Shipping carrier>", "<TrackingUrl>" and "<Status after fulfilling>"
    Then verify not display "File a claim"
    And close browser
    Examples:
      | Fulfilled Items | Tracking number | Shipping carrier    | TrackingUrl | Status after fulfilling |
      | Product unmap>4 | testFulfill     | China EMS (ePacket) |             | Fulfilled               |

  Scenario: Verify with order fulfill with PlusHub
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    And verify  display "File a claim" and click
    Then Redirect on "New claim"
    And close browser

  Scenario: Verify with order have one line item fulfill with PlusHub
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    Then verify  display "View claim(s)" and click
    Then Redirect on "Claims" list Page and verify display order in input search
    Then Redirect on Claim detail
    And close browser




