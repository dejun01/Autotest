Feature: Import order printHub
  #env = import_order_printHub

  Background: Login dashboard
    Given user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
  Scenario: Verify import by file CSV order success #SB_PRH_6
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    Then Verify order in order list and verify lineItems in order
      | tab              | date | status           | Product name               | Lineitems                                  | SKU     | Total Cost |
      | All              | date | Awaiting Payment | Basic Tracker Water Bottle | Tracker Bottle / All over print / One size | example | $18.48     |
      | Awaiting Payment | date | Awaiting Payment | Basic Tracker Water Bottle | Tracker Bottle / All over print / One size | example | $18.48     |
    And close browser

  Scenario: Verify import by file CSV order fail #SB_PRH_1 #SB_PRH_2 #SB_PRH_3 #SB_PRH_4
    And Import order printHub by file CSV fail
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    Then Verify display error "CSV errors found"
    And close browser

  Scenario: Verify import by file CSV order switch to tab In Review #SB_PRH_5
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example-all-over-print-product,Basic Tracker Water Bottle,New Tumbler 30oz,All over print,30z,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    Then Verify order in order list and verify lineItems in order
      | tab       | date | status    | Product name               | Lineitems                               | SKU                            | Total Cost |
      | In Review | date | In Review | Basic Tracker Water Bottle | New Tumbler 30oz / All over print / 30z | example-all-over-print-product | $0.00      |
    And close browser


  Scenario: Verify delete order in tab All and tab Awaiting Payment #SB_PRH_7
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    And Search order in tab "Awaiting Payment"
    And Delete order
    Then Verify not display order printHub "Could not found any orders matching"
      | tab              |
      | All              |
      | Awaiting Payment |
    And close browser

  Scenario: Verify delete order in tab All and tab In Review #SB_PRH_8
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example-all-over-print-product,Basic Tracker Water Bottle,New Tumbler 30oz,All over print,30z,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    And Search order in tab "In Review"
    And Delete order
    Then Verify not display order printHub "Could not found any orders matching"
      | tab       |
      | All       |
      | In Review |
    And close browser


  Scenario: Verify order switch tab Awaiting Shipment when pay order #SB_PRH_9 #SB_PRH_10 #SB_PRH_11
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    And Search order in tab "Awaiting Payment"
    And Payment order printHub in tab "Awaiting Payment"
    Then Verify order in order list and verify lineItems in order
      | tab               | date | status            | Product name               | Lineitems                                  | SKU     | Total Cost |
      | Awaiting Shipment | date | Awaiting Shipment | Basic Tracker Water Bottle | Tracker Bottle / All over print / One size | example | $18.48     |
    And close browser


