@AbandonedCheckoutNotification
Feature: Abandoned checkout SMS

#Check timeline and sms on twilio after 3h
  Scenario: Verify the timeline and content of abandoned checkout email
    Given user login to shopbase dashboard
    Then user navigate to "Orders>Abandoned checkouts" screen
    And select the newest abandoned checkout
    And verify the sms log should be shown in timeline
      | Case                       | Timeline                                                       |
      | First email was sent       | First text message was sent to {{customer.name}} (2056289809). |
      | Second email was scheduled | Second text message  was scheduled to be sent on {{date.time}} |
    Given login to twilio dashboard
    Then navigate to programmable SMS dashboard to view all message log
    And verify the sms should be sent
      | SMS                 | To Phone number | Message |
      | First text message  | 2056289809      |         |
      | Second text message | 2056289809      |         |


  Scenario: Create a new abandoned checkout for next time
    Given user login to shopbase dashboard
    Then select the newest created shop
    And create a shop "@shop-aut-can-be-deleted-@"
    Then create a new shop with data
      | Store country | Personal country | Phone number P1 | Phone Number P2 | Phone Number P2 |
      | United States | United States    | +1              | 2025550141      | Vietnam         |
    And user navigate to "Settings" screen
    And navigate to "Checkout" section in Settings page
    And Set up the schedule to send abandoned checkout "text message"
      | Notification        | Send after   | Message                                                                                                |
      | First text message  | 30 minute(s) | {{ .shop.name }} First SMS! {{ .abandoned_checkout.url }}                                              |
      | Second text message | 5 hour(s)    | {{ .shop.name }}: Second SMS! {{ .abandoned_checkout.url }}. Reply STOP{{ .opt_out_code }} to opt out. |
      | Third text message  | unchecked    |                                                                                                        |
    Given open created shop
    And add products "URBAN GIRL T-shirt" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Saved | Expected |
      | thuntt_john@mailtothis.com | 2056289809 | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | true  | success  |





