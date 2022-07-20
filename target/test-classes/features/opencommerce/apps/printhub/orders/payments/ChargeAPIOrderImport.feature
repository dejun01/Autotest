Feature: Change API order import
  #charge_manual_order_import

  Scenario: Charge order acitve balance
    And call api to create next payment or charge payment Print Hub for import order
    And call api to create next payment or charge payment Print Hub for import order
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    And Search order in tab "Awaiting Payment"
    And verify order import in phub
    And call api to create next payment or charge payment Print Hub for import order
    And user navigate to "Payments" screen
    And verify info payment first charge
      | Button view       | Outstanding balance | Next Payment | Fee type       | Details | Amount (USD) | Status  | Flag activate balance |
      | View next payment | $0.00               | $18.48       | Imported order | 1 order | $18.48 USD   | Created | true                  |
    And call api to create next payment or charge payment Print Hub for import order
    And user navigate to "Order import" screen
    And Search order in tab "Awaiting Shipment"
    And verify order import in phub
    And user navigate to "Payments" screen
    And verify info payment second charge
      | Button view       | Outstanding balance | Next Payment | Fee type       | Details | Amount (USD) | Status | Flag activate balance |
      | View next payment | $0.00               | $0.00        | Imported order | 1 order | $18.48 USD   | paid   | true                  |
    And close browser

  Scenario: Charge order deactive balance
    And call api to create next payment or charge payment Print Hub for import order for fistshop
    And call api to create next payment or charge payment Print Hub for import order for fistshop
    And user login to firstShop dashboard
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Payments" screen
    And get outstanding balance default
    And user navigate to "Order import" screen
    And Import order printHub by file CSV
      | Data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | @OrderName@,SINAHAPA#112-2336123-1029036,1,example,Basic Tracker Water Bottle,Tracker Bottle,All over print,One size,Annabeth baudendistel,2565 NARRAGANSET DR,,FLORISSANT,63033-2224,MO,US,0,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing,https://drive.google.com/file/d/1YBNmKlAAwq95mHGIP8MNJBTlm7xrGkBg/view?usp=sharing |
    And Search order in tab "Awaiting Payment"
    And verify order import in phub
    And call api to create next payment or charge payment Print Hub for import order for fistshop
    And user navigate to "Payments" screen
    And verify info payment first charge
      | Button view       | Outstanding balance | Next Payment | Fee type       | Details | Amount (USD) | Status  | Flag activate balance |
      | View next payment | $0.00               | $18.48       | Imported order | 1 order | $18.48 USD   | Created | false                 |
    And call api to create next payment or charge payment Print Hub for import order for fistshop
    And user navigate to "Order import" screen
    And Search order in tab "Awaiting Payment"
    And verify order import in phub
    And user navigate to "Payments" screen
    And verify info payment second charge
      | Button view       | Outstanding balance | Next Payment | Fee type       | Details | Amount (USD) | Status  | Flag activate balance |
      | View next payment | $18.48              | $0.00        | Imported order | 1 order | $18.48 USD   | pending | false                 |
    And close browser