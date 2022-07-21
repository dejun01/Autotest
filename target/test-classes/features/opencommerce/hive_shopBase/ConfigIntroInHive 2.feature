Feature: config intro in hive
  #sbase_auth_banners_intro

  Scenario: create intro banner in hive #MK_API_2 #MK_API_4 #MK_API_6 #MK_API_10 #MK_API_16 #MK_API_14 #MK_API_18 #MK_API_20
    Given login to hive sbase
    When user navigate to "Tools>Auth Banner Intro" on hive page
    And create auth banner intro
      | Name      | Enable | Region            | Domain                       | Condition              | URL                           | Background color (HEX) | Link                      | Alt text            | Image                 | Message |
      |           | true   | USG               | shopbase.net.cn              | Contains               | utm_source=ads                | #EBF7FD                | https://www.shopbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg |         |
      | testauto1 | false  |                   | shopbase.com                 | Equals                 | utm_source=ads                | #EBF7FD                | https://www.shopbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg |         |
      | testauto2 | talse  | China,Vietnam     |                              | Contains               | utm_source=fb&utm_source1=ads | #EBF7FD                | https://www.shopbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg |         |
      | testauto3 | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Contains               |                               | #EBF7FD                | https://www.shopbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg |         |
      | testauto4 | false  | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals                 | utm_source=fb&utm_medium=post |                        | https://www.shopbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg |         |
      | testauto5 | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Contains               | utm_source=fb&utm_medium=post | #EBF7FD                | ShopBase                  | Welcome to Shopbase | proof_of_shipping.jpg |         |
      | testauto6 | false  | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Contains,Equals,Equals | utm_source=fb&utm_medium=post | #EBF7FD                | https://www.shopbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg | success |
    Then delete intro
    And close browser

  Scenario: edit intro banner in hive #MK_API_3 #MK_API_19 #MK_API_21 #MK_API_22
    Given login to hive sbase
    When user navigate to "Tools>Auth Banner Intro" on hive page
    Then edit a intro in hive
      | Name  | Enable | Region            | Domain                       | Condition | URL                          | Background color (HEX) | Link                       | Alt text            | Image                                | Message                       |
      | intro | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals    | utm_source=ads&utm_intro=abc | #EBF7FD                | https://www.shopbase.com/  | Welcome to Shopbase | proof_of_shipping.jpg                | has been successfully updated |
      | intro | false  | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals    | utm_source=facebook          | #EBF7FD                | https://www.shopbase.com/  | Welcome to Shopbase | proof_of_shipping.jpg                | has been successfully updated |
      | intro | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals    | utm_source=ads&utm_intro=abc | #000000                | https://www.shopbase.com/  | Welcome to Shopbase | proof_of_shipping.jpg                | has been successfully updated |
      | intro | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals    | utm_source=ads&utm_intro=abc | #EBF7FD                | https://www.printbase.com/ | Welcome to Shopbase | proof_of_shipping.jpg                | has been successfully updated |
      | intro | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals    | utm_source=ads&utm_intro=abc | #EBF7FD                | https://www.printbase.com/ | Shopbase            | proof_of_shipping.jpg                | has been successfully updated |
      | intro | true   | USG,China,Vietnam | shopbase.net.cn,shopbase.com | Equals    | utm_source=ads&utm_intro=abc | #EBF7FD                | https://www.printbase.com/ | Welcome to Shopbase | SpayResellerBusinessRegistration.jpg | has been successfully updated |
    And close browser