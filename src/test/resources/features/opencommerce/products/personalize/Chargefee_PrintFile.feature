Feature: Verify charge fee after create printfile
  #sbase_chargefee_printfile

  Scenario: Verify charge fee   #SB_PRO_CFP_355
    Given user login to shopbase dashboard
    And Get balance info by API
    Given open shop on storefront
    And search and select the product "Product have printfile"
    And add product with custom option to cart then checkout as "01"
      | KEY | Custom option               |
      | 01  | Text,Custom text,Test value |
    And checkout by Stripe successfully
    And get all information order
    And user login to shopbase dashboard by API
    Then Verify balance amount as "01"
      | KEY | Total current available | Available to payout | Pending to review |
      | 01  | -0.3                    | 0                   | +0                |
    And Navigate to Balance page
    And Filter balance history by "Content" with value "Print file fee collecting"
    And Verify balance invoice
      | Index | Type | Shop name   | Content                   | Amount | Status  | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Print file fee collecting | $0.30  | Success | @Now@        | @Now@                   |
    And quit browser