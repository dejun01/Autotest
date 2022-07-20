@dashboard @Notifications
Feature: Notifications Setting
#  sbase_notification
  @listNotifications
  Scenario: SB_DAS_SET_9.1.1 -> SB_DAS_SET_9.1.21 - Verify list notification screen
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Notifications" section at Settings screen
    When click into link or button
      | Case                                 | Link Button                     | Expected                        |
      | Button Customize                     | Customize                       | Customize email templates       |
      | Link Order confirmation              | Order confirmation              | Order confirmation              |
      | Link Order canceled                  | Order canceled                  | Order canceled                  |
      | Link Order refund                    | Order refund                    | Order refund                    |
#      | Link Draft order invoice             | Draft order invoice             | Draft order invoice             |
#      | Link Abandoned checkout              | Abandoned checkout              | Abandoned checkout              |
#      | Link "checkout settings"             | "checkout settings".            | Checkout                        |
      | Link Fulfillment request             | Fulfillment request             | Fulfillment request             |
      | Link Shipping confirmation           | Shipping confirmation           | Shipping confirmation           |
      | Link Shipping update                 | Shipping update                 | Shipping update                 |
      | Link Shipment out for delivery       | Shipment out for delivery       | Shipment out for delivery       |
      | Link Shipment delivered              | Shipment delivered              | Shipment delivered              |
      | Link Customer account activation     | Customer account activation     | Customer account activation     |
      | Link Customer account welcome        | Customer account welcome        | Customer account welcome        |
      | Link Customer account password reset | Customer account password reset | Customer account password reset |
      | Link New order                       | New order                       | New order                       |
      | Button Add Recipient                 | Add Recipient                   | Add an order notification       |

  @customizeNotifications
  Scenario: SB_DAS_SET_9.2.17,SB_DAS_SET_9.2.18 - Verify customize notification layout
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Notifications" section at Settings screen
    When Click to Customize button
    Then Verify customize notification layout with show logo
      | Logo width | Color   |
      | 100        | #1A00ED |
      | 180        | #0093ed |
    When Remove logo
    Then Verify customize notification layout with show shop name
    And close browser

  @editNotificationTemplate
  Scenario: SB_DAS_SET_9.3.1 => SB_DAS_SET_9.3.53 - Verify edit template
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Notifications" section at Settings screen
    Then edit template and verify email as
      | Template                        | Edit subject                                                                                                                                 | Subject after edit                                        | Subject after revert                                  | Subject email                                         |
      | Order confirmation              | Order {{.order_name}} confirmed abc                                                                                                          | Order #9999 confirmed abc                                 | Order #9999 confirmed                                 | Order #9999 confirmed                                 |
      | Order canceled                  | Order {{ .order_name }} has been canceled abc                                                                                                | Order #1234 has been canceled abc                         | Order #1234 has been canceled                         | Order #1234 has been canceled                         |
      | Order refund                    | Refund notification abc                                                                                                                      | Refund notification abc                                   | Refund notification                                   | Refund notification                                   |
#      | Abandoned checkout              | Complete your Purchase abc                                                                                                                   | Complete your Purchase abc                                | Complete your Purchase                                | Complete your Purchase                                |
      | Fulfillment request             | Order fulfillment request for {{ .shop.name }} abc                                                                                           | Order fulfillment request for auto-test-notification2 abc | Order fulfillment request for auto-test-notification2 | Order fulfillment request for auto-test-notification2 |
      | Shipping confirmation           | A shipment from order {{ .order_name }} is on the way abc                                                                                    | A shipment from order #9999 is on the way abc             | A shipment from order #9999 is on the way             | A shipment from order #9999 is on the way             |
      | Shipping update                 | Shipping update for order {{ .order_name }} abc                                                                                              | Shipping update for order #9999 abc                       | Shipping update for order #9999                       | Shipping update for order #9999                       |
      | Shipment out for delivery       | A shipment from order {{ .order_name }} is out for delivery abc                                                                              | A shipment from order #1234 is out for delivery abc       | A shipment from order #1234 is out for delivery       | A shipment from order #1234 is out for delivery       |
      | Shipment delivered              | A shipment from order {{ .order_name }} has been delivered abc                                                                               | A shipment from order #1234 has been delivered abc        | A shipment from order #1234 has been delivered        | A shipment from order #1234 has been delivered        |
      | Customer account activation     | Customer account activation abc                                                                                                              | Customer account activation abc                           | Customer account activation                           | Customer account activation                           |
      | Customer account welcome        | Customer account confirmation abc                                                                                                            | Customer account confirmation abc                         | Customer account confirmation                         | Customer account confirmation                         |
      | Customer account password reset | Customer account password reset abc                                                                                                          | Customer account password reset abc                       | Customer account password reset                       | Customer account password reset                       |
      | New order                       | [{{ .shop.name }}] Order {{ .order_name }} placed {{ if .customer.first_name }}by {{ .customer.first_name }}{{ else }}recently {{ end }} abc | [auto-test-notification2] Order #9999 placed by Bob abc   | [auto-test-notification2] Order #9999 placed by Bob   | [auto-test-notification2] Order #9999 placed by Bob   |

  @emailAddress
  Scenario: verify Add recipients with Email address
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Notifications" section at Settings screen
    Then verify add Email address and Send test notification
      | Email Address          |
      | lily123@mailtothis.com |

  @staffAccount
  Scenario: verify Add recipients with Staff account
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Notifications" section at Settings screen
    Then verify add Staff account and Send test notification
      | Staff Account                 |
      | lily@mailinator.girl-viet.com |

  @emailNewOrder
  Scenario: verify email "New order" after checkout successfully
    Given open shop on storefront
    When add products "Necklace" to cart then checkout
    And checkout by Stripe successfully
    And verify thank you page
    Then verify send email "New order" after checkout successfully
      | Email                         |
      | lily123@mailtothis.com        |
      | lily@mailinator.girl-viet.com |

  @deleteRecipients
  Scenario: verify delete recipients
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Notifications" section at Settings screen
    Then delete recipients
      | Email                         |
      | lily123@mailtothis.com        |
      | lily@mailinator.girl-viet.com |