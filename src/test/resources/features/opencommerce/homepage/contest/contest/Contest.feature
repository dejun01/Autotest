Feature:  contest in pbase

  #sbase_contest

  Scenario Outline:  create a contest successfully in hive #SB_CT_4
    Given  login to hive sbase
    When user navigate to "Tools>Contest Tool" on hive page
    Then create a contest successfully in hive
      | Message   | Name   | Shop Type   | Region   | Start Time   | End Time   | Metrics Type   | Prize   | Pre Header   | Pre Sub-text   | Pre button   | Pre Link   | Pre image   | Template   | In Header   | In progress bar   | In link   | After Header 1   | After Sub-text 1   | After Image 1   | After Header 2   | After Sub-text 2   | After Image 2   | After Link   | Icon 1   | Icon 2   | Box Color   |
      | <Message> | <Name> | <Shop Type> | <Region> | <Start Time> | <End Time> | <Metrics Type> | <Prize> | <Pre Header> | <Pre Sub-text> | <Pre button> | <Pre Link> | <Pre image> | <Template> | <In Header> | <In progress bar> | <In link> | <After Header 1> | <After Sub-text 1> | <After Image 1> | <After Header 2> | <After Sub-text 2> | <After Image 2> | <After Link> | <Icon 1> | <Icon 2> | <Box Color> |
    Examples:
      | Message | Name    | Shop Type | Region                      | Start Time | End Time | Metrics Type | Prize                        | Pre Header         | Pre Sub-text | Pre button         | Pre Link                                               | Pre image               | Template   | In Header          | In progress bar | In link                                                | After Header 1                                       | After Sub-text 1                                              | After Image 1             | After Header 2                                                                       | After Sub-text 2                                                 | After Image 2 | After Link                                                        | Icon 1 | Icon 2 | Box Color |
      | Success | Contest | ShopBase  | Vietnam,International,China | Today      |          | GMV          | 50>1,100>2,150>3,200>4,250>5 | Contest>20>#363E43 |              | 12>#E7DAD2>#0093ED | https://www.printbase.com/mothers-day-sales-challenge/ | /contest/preContest.jpg | Template 1 | Contest>20>#363E43 | #FFFFFF>#FFFFFF | https://www.printbase.com/mothers-day-sales-challenge/ | Congratulation! you've made it to the end>14>#57616A | Thank you for joining! See you in the next contest>14>#57616A | /contest/afterContest.png | Mother's Day Contest>24>#363E43Congratulation! you've Succesfully finish the contest | Congratulation! you've Succesfully finish the contest>14>#57616A |               | https://www.printbase.com/mothers-day-sales-challenge/>12>#0093ED |        |        | #E4724E   |

  Scenario: verify display contest in shop shopbase and increate point after checkout an order #SB_CT_78 #SB_CT_76 #SB_CT_45 #SB_CT_52 #SB_CT_57
    Given  user login to shopbase dashboard
    And verify contest display in homepage
    And get contest point in current
    When open shop on storefront
    When checkout successfully via OceanPayment with cart "MC Turn Back Time Halter Bikini Set>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
    Then verify point of contest
      | Action   |
      | Checkout |
    And refund order on Shopbase dashboard

#  Scenario Outline: verify decrease point contest after refund or cancel order
#    Given get contest point in current
#    When open shop on storefront
#    And checkout successfully via OceanPayment with cart "MC Turn Back Time Halter Bikini Set"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Expired Date | CVV |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Oceanpayment    | Oceanpayment   | 5454545454545454 | 11/22        | 113 |
#    And get all information order
#    Given user login to shopbase dashboard
#    When open order detail dashboard by ID
#    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
#    And verify order detail including "<Status>", "<Net payment>" and quantity of item after refunding
#    Examples:
#      | Refunded item | Restock item | Refund shipping | Reason for refund  | Status             | Net payment |
#      | Bikini>5      | true         | $5.00           | Damaged in transit | Refunded           | $0.00       |


