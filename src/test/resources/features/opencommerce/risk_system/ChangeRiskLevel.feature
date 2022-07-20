Feature: Change risk level
  # sbase_risk_level

# Config Spay Risk Level Management Risk Level on hive-sbase
  # Define x day (aftx, astx)= 21 days >>>> X =10 days = 864000 s
  # Define x-y day (otmrxy) =  22-90 days >>>> XY_OTMR = 50 days = 4320000 s
  # Define x-y day (odrxy: R2-R4) = 20-25 days >>>> XY_ODR1 = 23 days = 1987200 s
  # Define x-y day (odrxy: R5-R6) =  10-15 days >>>> XY_ODR2 = 13 days = 1123200 s

  Scenario: Change risk level to R5
    When update criteria on database
      | criteria | id staging | id production | X      | XY_OTMR | XY_ODR1 | XY_ODR2 | total | value  |
      | aftx     | 89774      | 46764466      | 864000 | 4320000 | 1987200 | 1123200 | 2     | 864000 |
      | otmrxy   | 80729      | 46758253      | 864000 | 4320000 | 1987200 | 1123200 | 100   | 99     |
      | astx     | 89777      | 46758298      | 864000 | 4320000 | 1987200 | 1123200 | 0     | 0      |
    Then verify risk level on database
      | smp risk level |
      | R5             |
    And user login to shopbase dashboard
    And user navigate to "Settings" screen
    Then navigate to "Payment providers" section in Settings page
    And verify risk level on dashboard
      | risk |
      | Low  |

  Scenario: Change risk level to R4
    When update criteria on database
      | criteria | id staging | id production | X      | XY_OTMR | XY_ODR1 | XY_ODR2 | total | value   |
      | aftx     | 89774      | 46764466      | 864000 | 4320000 | 1987200 | 1123200 | 4     | 3974400 |
      | otmrxy   | 80729      | 46758253      | 864000 | 4320000 | 1987200 | 1123200 | 100   | 98      |
      | astx     | 89777      | 46758298      | 864000 | 4320000 | 1987200 | 1123200 | 0     | 0       |
    Then verify risk level on database
      | smp risk level |
      | R4             |
    And user login to shopbase dashboard
    And user navigate to "Settings" screen
    Then navigate to "Payment providers" section in Settings page
    And verify risk level on dashboard
      | risk   |
      | Medium |

  Scenario: Change risk level to R3
    When update criteria on database
      | criteria | id staging | id production | X      | XY_OTMR | XY_ODR1 | XY_ODR2 | total | value   |
      | aftx     | 89774      | 46764466      | 864000 | 4320000 | 1987200 | 1123200 | 0     | 0       |
      | otmrxy   | 80729      | 46758253      | 864000 | 4320000 | 1987200 | 1123200 | 0     | 0       |
      | astx     | 89777      | 46758298      | 864000 | 4320000 | 1987200 | 1123200 | 1     | 1814400 |
      | odrxy    | 89779      | 46758986      | 864000 | 4320000 | 1987200 | 1123200 | 100   | 90      |
    Then verify risk level on database
      | smp risk level |
      | R3             |
    And user login to shopbase dashboard
    And user navigate to "Settings" screen
    Then navigate to "Payment providers" section in Settings page
    And verify risk level on dashboard
      | risk |
      | High |

  Scenario: Change risk level to R2
    When update criteria on database
      | criteria | id staging | id production | X      | XY_OTMR | XY_ODR1 | XY_ODR2 | total | value    |
      | astx     | 89777      | 46758298      | 864000 | 4320000 | 1987200 | 1123200 | 4     | 17280000 |
      | odrxy    | 89779      | 46758986      | 864000 | 4320000 | 1987200 | 1123200 | 100   | 49       |
    Then verify risk level on database
      | smp risk level |
      | R2             |
    And user login to shopbase dashboard
    And user navigate to "Settings" screen
    Then navigate to "Payment providers" section in Settings page
    And verify risk level on dashboard
      | risk          |
      | Elevated High |
