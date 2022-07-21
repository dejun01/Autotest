@AbandonedCheckoutEmail
  #sbase_abandoned
Feature: Abandoned checkout 3 emails

  Scenario: turn on setting automatically send abandoned checkout emails
    Given user login to shopbase dashboard
    Then user navigate to "Marketing & Sales>Marketing emails" screen
    Then  select block "abandoned checkouts recovery"
    And Set up the schedule to send abandoned checkout "emails"
      | Notification | Send after  |
      | First email  | 1 minute(s) |
      | Second email | 3 minute(s) |
      | Third email  | 5 day(s)    |
    Then user navigate to "Settings" screen
    And navigate to "Notifications" section in Settings page
    And verify template of abandoned checkout emails
      | Abandoned checkout ordinal        | Subject                                | Content                                                                    |
      | Abandoned checkout - First email  | Did you forget something?              | You added items to your shopping cart and haven't completed your purchase. |
      | Abandoned checkout - Second email | We saved some goodies for you          | The items you left in your cart are flying out of stock.                   |
      | Abandoned checkout - Third email  | A little something to sweeten your day | Your shopping cart has been reserved and is waiting for your return!       |

  Scenario: Create a new abandoned checkout for next time
    Given open shop on storefront
    Then add products "URBAN GIRL T-shirt" to cart then checkout
    And input Customer information
      | Email            | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailtothis.com@ | 2056289809 | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    |



