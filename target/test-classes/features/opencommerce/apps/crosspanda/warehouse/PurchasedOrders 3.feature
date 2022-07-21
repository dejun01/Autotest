#https://docs.google.com/spreadsheets/d/1l_MOvxLnfdLzuxzNd6XfqK6DHeYuQ9AO-NhwCpOvk_I/edit#gid=1984760370
# staging_crosspanda_purchased_orders

Feature: Purchased orders

#  Scenario: Search purchased order
#    Given login to crosspanda dashboard
#    When user navigate to "Purchased orders" screen in CrossPanda
#    Then search purchased number then verify result
#      | Purchase number | Tab         | Is show |
#      |                 | Draft order | true    |
#      | PO16472         | Draft order | true    |
#      | PO00000         | Draft order | false   |
#    And close browser

  Scenario Outline: Purchased order then verify information of PO
    Given Description: "<Test case>"
    Given config user is Deferred Payment as "<KEY>"
      | KEY | Deferred Payment |
      | 1   | true             |
      | 2   | true             |
      | 3   | false            |
      | 4   | false            |
      | 5   | false            |
    Given login to crosspanda dashboard
    When user navigate to "Request list" screen in CrossPanda
    And close smart bar in CrossPanda
    And select quotation and purchased quotation as "<KEY>"
      | KEY | Quotation | Variant>Quantity                 | Type order  | Is PO first | Is pay | Payment method                                             | Invoice name | PO  | Created date | Total amount |
      | 1   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | true        | false  |                                                            | I1           | PO1 | Date 1       | T1           |
      | 2   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | true        | true   | CREDIT CARD>Ending in **** 4242                            | I2           | PO2 | Date 2       | T2           |
      | 3   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | false  |                                                            | I3           |     |              | T3           |
      | 4   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | CREDIT CARD>Ending in **** 4242                            | I4           | PO4 | Date 4       | T4           |
      | 5   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Personal Bank Account in USA>Bank Transfers | I5           | PO5 | Date 5       | T5           |
    And user navigate to "Purchased orders" screen in CrossPanda
    Then verify infor PO as "<KEY>"
      | KEY | Tab         | Is PO | Purchased date | Purchase number | Product                                               | Quantity | Expected date of arrival | Total amount | Make payment |
      | 1   | New         | true  | Date 1         | PO1             | Womens Casual Long Sleeve V Plunge Evening Party O... | 3        | E1                       | T1           | true         |
      | 2   | New         | true  | Date 2         | PO2             | Womens Casual Long Sleeve V Plunge Evening Party O... | 3        | E2                       | T2           | false        |
      | 3   | New         | false |                |                 |                                                       | 3        | E3                       | T3           |              |
      | 4   | New         | true  | Date 4         | PO4             | Womens Casual Long Sleeve V Plunge Evening Party O... | 3        | E4                       | T4           | false        |
      | 5   | Draft order | true  | Date 5         | PO5             | Womens Casual Long Sleeve V Plunge Evening Party O... | 3        | E5                       | T5           | true         |
    And user navigate to "Payment history" screen in CrossPanda
    And verify status invoice PO in Payment history as "<KEY>"
      | KEY | Invoice name | Detail | Created | Status   | Amount | Is pay |
      | 1   | I1           | PO1    | Date 1  | OPEN     | T1     | true   |
      | 2   | I2           | PO2    | Date 2  | PAID     | T2     | false  |
      | 4   | I4           | PO4    | Date 4  | PAID     | T4     | false  |
      | 5   | I5           | PO5    | Date 5  | CHECKING | T5     | true   |
    And close browser
    And verify PO with name "<KEY>" on Odoo
      | KEY | PO  | Email | Invoice name | Is sync |
      | 1   | PO1 |       | I1           | true    |
      | 2   | PO2 |       | I2           | true    |
      | 4   | PO4 |       | I4           | true    |
      | 5   | PO5 |       | I5           | false   |
    Then approve order and assign onwer for DO on Odoo as "<KEY>"
      | KEY | PO  | Invoice name | Email |
      | 1   | PO1 | I1           |       |
    Given login to crosspanda dashboard
    When user navigate to "Purchased orders" screen in CrossPanda
    Then verify infor PO as "<KEY>"
      | KEY | Tab        | Is PO | Purchased date | Purchase number | Product                                               | Quantity | Expected date of arrival | Total amount | Make payment |
      | 1   | On the way | true  | Date 1         | PO1             | Womens Casual Long Sleeve V Plunge Evening Party O... | 3        |                          | T1           | true         |
    Examples:
      | KEY | Test case                                                         |
      | 1   | User deferred payment - Purchase order but not pay                |
      | 3   | Non-User deferred payment - Purchase order and not pay            |
      | 5   | Non-User deferred payment - Purchase order and pay with transfers |
#     | 2   | User deferred payment - Purchase order and pay by card            |
#     | 4   | Non-User deferred payment - Purchase order and pay                |

  Scenario Outline: Purchase order with payment methods
    Given Description: "<Test case>"
    Given config user is Deferred Payment as "<KEY>"
      | KEY | Deferred Payment |
      | 1   | false            |
    Given login to crosspanda dashboard
    When user navigate to "Request list" screen in CrossPanda
    And close smart bar in CrossPanda
    And select quotation and purchased quotation as "<KEY>"
      | KEY | Quotation | Variant>Quantity                 | Type order  | Is PO first | Is pay | Payment method                                                 | Invoice name | PO  | Created date | Total amount |
      | 1   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Personal Bank Account in USA>Bank Transfers     | I1           | PO1 | Date 1       | T1           |
      | 2   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Personal Bank Account in USA>Paypal Transfers   | I2           | PO2 | Date 2       | T2           |
      | 3   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Personal Bank Account in USA>Payoneer Transfers | I3           | PO3 | Date 3       | T3           |
      | 4   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Business Bank Account in USA>Paypal Transfers   | I4           | PO4 | Date 4       | T4           |
      | 5   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Business Bank Account in USA>Payoneer Transfers | I5           | PO5 | Date 5       | T5           |
      | 6   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Bank account in Vietnam>Bank Transfers          | I6           | PO6 | Date 6       | T6           |
      | 7   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Bank account in Vietnam>Paypal Transfers        | I7           | PO7 | Date 7       | T7           |
      | 8   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | MONEY TRANSFER>Other>Paypal Transfers                          | I8           | PO8 | Date 8       | T8           |
      | 9   | Q1691     | Green/XXXL>1;Green/L>1;Green/M>1 | Basic order | false       | true   | PAYPAL>PayPal                                                  | I9           | PO9 | Date 9       | T9           |
    And verify status invoice PO in Payment history as "<KEY>"
      | KEY | Invoice name | Detail | Created | Status   | Amount | Is pay |
      | 1   | I1           | PO1    | Date 1  | CHECKING | T1     | true   |
      | 2   | I2           | PO2    | Date 2  | CHECKING | T2     | true   |
      | 3   | I3           | PO3    | Date 3  | CHECKING | T3     | true   |
      | 4   | I4           | PO4    | Date 4  | CHECKING | T4     | true   |
      | 5   | I5           | PO5    | Date 5  | CHECKING | T5     | true   |
      | 6   | I6           | PO6    | Date 6  | CHECKING | T6     | true   |
      | 7   | I7           | PO7    | Date 7  | CHECKING | T7     | true   |
      | 8   | I8           | PO8    | Date 8  | CHECKING | T8     | true   |
      | 9   | I9           | PO9    | Date 9  | PAID     | T9     | true   |
    And user navigate to "Purchased orders" screen in CrossPanda
    Then verify infor PO as "<KEY>"
      | KEY | Tab         | Is PO | Purchased date | Purchase number | Total amount | Make payment |
      | 1   | Draft order | true  | Date 1         | PO1             | T1           | true         |
      | 2   | Draft order | true  | Date 2         | PO2             | T2           | true         |
      | 3   | Draft order | true  | Date 3         | PO3             | T3           | true         |
      | 4   | Draft order | true  | Date 4         | PO4             | T4           | true         |
      | 5   | Draft order | true  | Date 5         | PO5             | T5           | true         |
      | 6   | Draft order | true  | Date 6         | PO6             | T6           | true         |
      | 7   | Draft order | true  | Date 7         | PO7             | T7           | true         |
      | 8   | Draft order | true  | Date 8         | PO8             | T8           | true         |
    Then close browser
    And verify PO with name "<KEY>" on Odoo
      | KEY | PO  | Email | Invoice name | Is sync |
      | 1   | PO1 |       | I1           | false   |
      | 2   | PO2 |       | I2           | false   |
      | 3   | PO3 |       | I3           | false   |
      | 4   | PO4 |       | I4           | false   |
      | 5   | PO5 |       | I5           | false   |
      | 6   | PO6 |       | I6           | false   |
      | 7   | PO7 |       | I7           | false   |
      | 8   | PO8 |       | I8           | false   |
    Examples:
      | KEY | Test case                                                                      |
      | 1   | Payment with MONEY TRANSFER: Personal Bank Account in USA - Bank Transfers     |
      | 2   | Payment with MONEY TRANSFER: Personal Bank Account in USA - Paypal Transfers   |
      | 3   | Payment with MONEY TRANSFER: Personal Bank Account in USA - Payoneer Transfers |
      | 4   | Payment with MONEY TRANSFER: Business Bank Account in USA - Paypal Transfers   |
      | 5   | Payment with MONEY TRANSFER: Business Bank Account in USA - Payoneer Transfers |
      | 6   | Payment with MONEY TRANSFER: Bank account in Vietnam - Bank Transfers          |
      | 7   | Payment with MONEY TRANSFER: Bank account in Vietnam - Paypal Transfers        |
      | 8   | Payment with MONEY TRANSFER: Other - Paypal Transfers                          |
#      | 9   | Payment with PAYPAL                                                            |

  Scenario Outline: Approve order
    Then approve order and assign onwer for DO on Odoo as "<KEY>"
      | KEY | PO                    | Invoice | Email |
      | 1   | Q1691-JIL2ZIUS-PO2449 | I1      |       |
    And verify PO detail on Odoo as "<KEY>"
      | KEY | PO                    | Invoice | Email | Product                                                                                       | Quantity |
      | 1   | Q1691-JIL2ZIUS-PO2449 |         |       | [3XLG] Womens Casual Long Sleeve V Plunge Evening Party Oversized Maxi Polka Dot Dress (XXXL) | 1        |
      | 1   | Q1691-JIL2ZIUS-PO2449 |         |       | [LG] Womens Casual Long Sleeve V Plunge Evening Party Oversized Maxi Polka Dot Dress (L)      | 1        |
      | 1   | Q1691-JIL2ZIUS-PO2449 |         |       | [MG] Womens Casual Long Sleeve V Plunge Evening Party Oversized Maxi Polka Dot Dress (M)      | 1        |
    Examples:
      | KEY |
      | 1   |
