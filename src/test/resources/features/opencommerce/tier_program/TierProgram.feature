Feature: Tier program

  # sbase_tier
  Scenario: validate edit Tier #SB_PN_TP_4 #SB_PN_TP_3 #SB_PN_TP_2 #SB_PN_TP_1
    Given login to hive sbase
    When verify tier program list page
    Then user edit tier
      | Tier | Tier name    | Threshold | Threshold keep | Cycle | Discount | Messages                                                                 |
      | 1    | Newbie       | 210       | 0              | 45    | 10       | Current threshold need to be smaller than keeping tier threshold         |
      | 1    | Newbie       | 0         | 1              | 30    | 0        | Success                                                                  |

      | 2    | Newbie       | 200       | 1000           | 30    | 0        | Tier name is already use on another tier                                 |
      | 2    | Specialist   | 1100      | 1000           | 30    | 0        | Current threshold need to be smaller than keeping tier threshold         |
      | 2    | Specialist   | 200       | 3100           | 30    | 0        | Current keeping threshold need to be smaller than next tier threshold    |
      | 2    | Specialist   | 1200      | 1000           | 30    | 0        | Current threshold need to be smaller than keeping tier threshold         |
      | 2    | Specialist   | 1         | 200            | 30    | 0        | Current threshold need to be larger than previous keeping tier threshold |
      | 2    | Specialist   | 200       | 1000           | 30    | 0        | Success                                                                  |

      | 3    | Newbie       | 3000      | 5000           | 30    | 20       | Tier name is already use on another tier                                 |
      | 3    | Professional | 3000      | 100            | 30    | 20       | Current threshold need to be smaller than keeping tier threshold         |
      | 3    | Professional | 7000      | 5000           | 30    | 20       | Current threshold need to be smaller than keeping tier threshold         |
      | 3    | Professional | 3000      | 15000          | 30    | 20       | Current keeping threshold need to be smaller than next tier threshold    |
      | 3    | Professional | 200       | 5000           | 30    | 20       | Current threshold need to be larger than previous keeping tier threshold |
      | 3    | Professional | 3000      | 5000           | 30    | 20       | Success                                                                  |

      | 4    | Newbie       | 12000     | 15000          | 45    | 0        | Tier name is already use on another tier                                 |
      | 4    | Expert       | 12000     | 10000          | 45    | 0        | Current threshold need to be smaller than keeping tier threshold         |
      | 4    | Expert       | 15001     | 15000          | 45    | 0        | Current threshold need to be smaller than keeping tier threshold         |
      | 4    | Expert       | 12000     | 300001         | 45    | 0        | Current keeping threshold need to be smaller than next tier threshold    |
      | 4    | Expert       | 1000      | 15000          | 45    | 0        | Current threshold need to be larger than previous keeping tier threshold |
      | 4    | Expert       | 12000     | 15000          | 45    | 0        | Success                                                                  |

      | 5    | Newbie       | 30000     | 32000          | 60    | 0        | Tier name is already use on another tier                                 |
      | 5    | Master       | 4900      | 32000          | 60    | 0        | Current threshold need to be larger than previous keeping tier threshold |
      | 5    | Master       | 33000     | 32000          | 60    | 0        | Current threshold need to be smaller than keeping tier threshold         |
      | 5    | Master       | 30000     | 7500           | 60    | 0        | Current threshold need to be smaller than keeping tier threshold         |
      | 5    | Master       | 30000     | 32000          | 60    | 0        | Success                                                                  |

  Scenario: check tier of user #SB_PN_TP_5
    Given get tier information of user by api
    And get tier list by api
    When checkout an order and verify update Bcoin and Available coint
      | Product name | Quantity | Size   | Base Product | Action |
      | Quilt        | 5        | Single | Gold         | Order  |
#      | Unisex T-shirt | 1        | Silver       | Order  |
    Then login to hive pbase
    And cancel order on hive-pbase and verify Tier
      | Product name | Base Product | Action |
      | Quilt        | Gold         | Cancel |

  Scenario: change bcoin and available bcoin in user #SB_PN_TP_5
    Given login to hive sbase
    Then change bcoin and available bcoin of user in hive
      | Type            | Action | Quantity |
      | Available BCoin | +      | 50       |
      | Available BCoin | -      | 20       |
      | BCoin           | +      | 200      |
      | BCoin           | -      | 50       |

  Scenario: Verify Tier Program on the Dashboard #SB_PN_TP_13 #SB_PN_TP_12 #SB_PN_TP_11 #SB_PN_TP_10
    Given user login to shopbase dashboard
    And user navigate to "Tier Program" screen
    Then get tier list by api
    Then verify user tier information
    When Verify list Tier Benefits
      | Tier | Benefits                                                                                                                                                                                                                                                         |
      | 1    | Prior support from a Customer Success Manager                                                                                                                                                                                                                    |
      | 2    | Prior support from a Customer Success Manager,Invitations to private events                                                                                                                                                                                      |
      | 3    | Prior support from a Customer Success Manager,Invitations to private events,Special gifts on various occasions                                                                                                                                                   |
      | 4    | Prior support from a Customer Success Manager,Invitations to private events,Special gifts on various occasions,In-depth market-insight reports                                                                                                                   |
      | 5    | Prior support from a Customer Success Manager,Invitations to private events,Special gifts on various occasions,In-depth market-insight reports,Free sample on selected products,Invitations to PrintBase exclusive programs,Priority to build requested features |
    Then Verify display Redeem Rewards popup

  Scenario: Check Redeem Rewards #SB_PN_TP_14
    Given user login to shopbase dashboard
    And user navigate to "Tier Program" screen
    Then Redeem Rewards form Redeem Rewards popup












