Feature: Export transaction in SBase
#export_dispute
#fs : export_order_dispute

  Scenario: Export current page
    Given user login to shopbase dashboard
    When user navigate "Orders" screen
    And choice option export with
      | Exports      | Export template       |
      | Current page | Transaction histories |
    And verify that the header of file downloaded is matched with order information from dashboard
      | Transaction Id | Order | Name | Kind | Gate Way | Created At | Status | Amount | Currency | Card Type | Domain | Open Dispute Date | Dispute Reason | Dispute Due Date | Dispute Status |
    And close browser

  Scenario Outline: Export all in all store #SB_ORD_89
    Given staff login to shopbase dashboard
    When user navigate "Orders" screen
    And choice option export with
      | Exports    | Export template       | Multi store |
      | All orders | Transaction histories | <Stores>    |
    And staff login to email and open email with subject contains shop
    And verify that send mail to merchant with file name "transactions_export_yyyyMMdd"
    And close browser

    Examples:
      | Stores     |
      |            |
#      | All stores |

  Scenario: Export 1 order dispute #SB_ORD_82
    Given staff login to shopbase dashboard
    When user navigate "Orders" screen
#    And Filter order with
#      | Filter label                  | Item filter |
#      | Chargeback and inquiry status | Any         |
    And select first order dispute
    And choice option export with
      | Exports          | Export template       |
      | Selected 1 order | Transaction histories |
    And verify that the content of file downloaded of order dispute
      | Kind | Open Dispute Date | Dispute Reason | Dispute Due Date | Dispute Status |
    And close browser

  Scenario: Export order dispute #SB_ORD_83
    Given staff login to shopbase dashboard
    When user navigate "Orders" screen
#    And Filter order with
#      | Filter label                  | Item filter |
#      | Chargeback and inquiry status | Any         |
    And choice option export with
      | Exports        | Export template       |
      | Current search | Transaction histories |
    And staff login to email and open email with subject contains shop
    And verify that send mail to merchant with file name "transactions_export_yyyyMMdd"
    And close browser
