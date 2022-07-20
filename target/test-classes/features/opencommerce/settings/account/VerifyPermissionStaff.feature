Feature: Verify permission of account staff
# sbase_permission_staff
  Scenario: Verify account staff permission have Full access #SB_SET_AAS_4
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then verify permission of account staff
      | account   | access      |
      | thank son | Full access |
    And quit browser
    Given staff1 login to shopbase dashboard
    Then Verify staff main permission
      | name permission | access |
      | Home            | true   |
      | Orders          | true   |
      | Products        | true   |
      | Analytics       | true   |
      | Discounts       | true   |
      | Customers       | true   |
      | Apps            | true   |
      | Settings        | true   |

    And verify Sub permission
      | main         | sub               | access |
      | Online Store | Themes            | true   |
      | Online Store | Pages             | true   |
      | Online Store | Navigation        | true   |
      | Online Store | Preferences       | true   |
      | Online Store | Domains           | true   |
      | Analytics    | Sale Report       | true   |
      | Settings     | Payment providers | true   |
      | Settings     | Sales channel     | true   |
    And verify permission edit Order
      | main        | sub               | access |
      | Edit orders | Refund item       | true   |
      | Edit orders | Mark as fulfilled | true   |
    And verify permission export Order
      | main          | sub           | access |
      | Export orders | Export orders | true   |
    And verify Sub permission of Balance
      | main    | sub | access |
      | Balance | Pay | true   |

  Scenario: Verify staff account access to the Not authorized #SB_SET_AAS_5
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then verify permission of account staff
      | account   | access         |
      | thanh son | Limited access |
    And quit browser
    Given staff2 login to shopbase dashboard
    Then Verify staff main permission
      | name permission | access |
      | Home            | false   |
      | Discounts       | false   |
      | Customers       | false   |
    And verify Sub permission
      | main         | sub               | access |
      | Online Store | Pages             | false   |
      | Online Store | Domains           | false   |
      | Settings     | Payment providers | false   |
    And verify permission edit Order
      | main        | sub               | access |
      | Edit orders | Refund item       | false   |
      | Edit orders | Mark as fulfilled | false   |
    And verify Sub permission of Balance
      | main    | sub | access |
      | Balance | Pay | false   |


  Scenario: Verify staff account access to the authorized #SB_SET_AAS_6
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then verify permission of account staff
      | account   | access         |
      | thanh son | Limited access |
    And quit browser
    Given staff2 login to shopbase dashboard
    Then Verify staff main permission
      | name permission | access |
      | Orders          | true   |
      | Products        | true   |
      | Analytics       | true   |
      | Apps            | true   |
      | Settings        | true   |
    And verify Sub permission
      | main         | sub           | access |
      | Online Store | Themes        | true   |
      | Online Store | Navigation    | true   |
      | Online Store | Preferences   | true   |
      | Analytics    | Sale Report   | true   |
      | Settings     | Sales channel | true   |
    And verify permission export Order
      | main          | sub           | access |
      | Export orders | Export orders | true   |

