Feature: Affiliate only on production

#prod_sbase_affiliate

#  Scenario: Sign up Affiliate on ShopBase landing page #SB_PN_AT_36
#    Given user open link sign up affiliate on shopbase.com
#    When  sign up account affiliate
#      | Email   | Password | Message                                                                   |
#      |         |          | Please enter an email address,Please enter a password                     |
#      | huongle | 123      | Please enter a valid email address,Password must be at least 5 characters |
#      | @EMAIL@ | 123456   | Successfully                                                              |

  Scenario Outline: verify redirect link during having cookie #SB_PN_AT_37 #SB_PN_AT_35 #SB_PN_AT_34
    Then  verify redirect link with cookie
      | Link   | Clear cookie   |
      | <Link> | <Clear cookie> |
    Examples:
      | Link          | Clear cookie |
      | printbase.co/ | false        |
      | printbase.co/ | true         |










