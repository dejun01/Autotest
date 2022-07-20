@AbandonedCheckoutEmail
  #sbase_abandoned
  #Check timeline after 3h run AbandonedCheckout3Email.feature
Feature: Verify timeline of Abandoned checkout 3 emails

  Scenario: Verify the timeline and content of abandoned checkout email #SB_ORD_ABC_2

    Given user login to shopbase dashboard
    Then user navigate to "Orders>Abandoned checkouts" screen
    And select the newest abandoned checkout
    And verify the email log should be shown in timeline
      | Case                  | Timeline                                                                                  |
      | First email was sent  | Email "Did you forget something?" was sent to {{customer.name}} ({{customer.email}}).     |
      | Second email was sent | Email "We saved some goodies for you" was sent to {{customer.name}} ({{customer.email}}). |
      | Third was scheduled   | Email "A little something to sweeten your day" was scheduled to be sent                   |
    Then choose cancel email with subject "A little something to sweeten your day"
    And click on "Send a cart recovery email" button to send email manually
      | Message                             |
      | Email was sent to {{email address}} |
    And open mailbox with the subject then verify the content
      | Abandoned checkout ordinal        | Subject                                | Content                                                                    |
      | Abandoned checkout - First email  | Did you forget something?              | You added items to your shopping cart and haven't completed your purchase. |
      | Abandoned checkout - Second email | We saved some goodies for you          | The items you left in your cart are flying out of stock.                   |
      | Abandoned checkout - Third email  | A little something to sweeten your day | Your shopping cart has been reserved and is waiting for your return!       |