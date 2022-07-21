#environment = sbase_gateway_activation

Feature: Activating PayPal Pro gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow #SB_SET_PMS_PPP_18 #SB_SET_PMS_PPP_21 #SB_SET_PMS_PPP_22 #SB_SET_PMS_PPP_23 #SB_SET_PMS_PPP_24 #SB_SET_PMS_PPP_25
  # Activate PayPal Pro gateway successfully
    Then activate "PayPal Pro" gateway successfully in "Activate" mode
      | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     |
      | PayPal                 | shopbase              | shopbase            | June1$2020              | Yes       | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |
    Then verify "PayPal Pro" gateway info after "Activating"
  # Edit PayPal Pro gateway successfully in Edit mode
    Then activate "PayPal Pro" gateway successfully in "Edit" mode
      | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     |
      | PayPal                 | shopbase              | shopbase            | June1$2020              | Yes       | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |
    Then verify "PayPal Pro" gateway info after "Editing"
  # Deactivate PayPal Pro gateway successfully
    Then deactivate gateway "PayPal Pro" by account name ""
    Then verify "PayPal Pro" gateway info after "Deactivating"
  # Reactivate PayPal Pro gateway successfully
    Then activate "PayPal Pro" gateway successfully in "Reactivate" mode
      | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     |
      | PayPal                 | shopbase              | shopbase            | June1$2020              | Yes       | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |
    Then verify "PayPal Pro" gateway info after "Activating"
  #Remove account PayPal Pro gateway successfully
    Then remove gateway account "PayPal Pro" by account name ""
    And close browser

  Scenario: Activation Validation in case of blank without 3Ds #SB_SET_PMS_PPP_17 #SB_SET_PMS_PPP_26 #SB_SET_PMS_PPP_27 #SB_SET_PMS_PPP_28
    Then validate activation input for "PayPal Pro" in "Activate" mode
      | Case                            | Account name | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | Cardinal API Key                     | Cardinal Org Unit ID     | Message           | Notice Message                                           |
      | PayPal Manager Partner blank    | PayPal-Pro   |                        | profitteam            | haduongapi          | 5z5YFtZG                | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | PayPal Manager Vendor blank     | PayPal-Pro   | PayPal                 |                       | haduongapi          | 5z5YFtZG                | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | PayPal Manager User blank       | PayPal-Pro   | PayPal                 | profitteam            |                     | 5z5YFtZG                | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | PayPal Manager Password blank   | PayPal-Pro   | PayPal                 | profitteam            | haduongapi          |                         | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |

  Scenario: Activation Validation in case of blank of 3Ds #SB_SET_PMS_PPP_29
    Then validate activation input for "PayPal Pro" in "Activate" mode
      | Case                            | Account name | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     | Message           | Notice Message                                           |
      | Cardinal API ID blank           | PayPal-Pro   | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG                | Yes       |                          | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | Cardinal API Key blank          | PayPal-Pro   | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG                | Yes       | 5e3d172f031e732e0843a5d4 |                                      | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | Cardinal Org Unit ID blank      | PayPal-Pro   | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG                | Yes       | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac |                          | Field is required |                                                          |

  Scenario: Activation Validation in case of invalid #SB_SET_PMS_PPP_30
    Then validate activation input for "PayPal Pro" in "Activate" mode
      | Case                            | Account name | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     | Message           | Notice Message                                           |
      | Invalid PayPal Manager Partner  | PayPal-Pro   | PayPal1                | profitteam            | haduongapi          | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |
      | Invalid PayPal Manager Vendor   | PayPal-Pro   | PayPal                 | profitteam1           | haduongapi          | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |
      | Invalid PayPal Manager User     | PayPal-Pro   | PayPal                 | profitteam            | haduongapi1         | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |
      | Invalid PayPal Manager Password | PayPal-Pro   | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG1               | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |

  Scenario: Activation Validation in Edit mode in case of blank 1 #SB_SET_PMS_PPP_19
    Given activate "PayPal Pro" gateway successfully in "Activate" mode
      | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     |
      | PayPal                 | shopbase              | shopbase            | June1$2020              | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |
    Then validate activation input for "PayPal Pro" in "Edit" mode
      | Case                            | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     | Message           | Notice Message                                           |
      | PayPal Manager Partner blank    |                        | profitteam            | haduongapi          | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | PayPal Manager Vendor blank     | PayPal                 |                       | haduongapi          | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | PayPal Manager User blank       | PayPal                 | profitteam            |                     | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | PayPal Manager Password blank   | PayPal                 | profitteam            | haduongapi          |                         | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |

  Scenario: Activation Validation in Edit mode in case of blank 2 #SB_SET_PMS_PPP_20
    Given activate "PayPal Pro" gateway successfully in "Activate" mode
      | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     |
      | PayPal                 | shopbase              | shopbase            | June1$2020              | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |
    Then validate activation input for "PayPal Pro" in "Edit" mode
      | Case                            | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     | Message           | Notice Message                                           |
      | Cardinal API ID blank           | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG                | Yes       |                          | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | Cardinal API Key blank          | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG                | Yes       | 5e3d172f031e732e0843a5d4 |                                      | 5e3d172f6bcd0a178c1a8630 | Field is required |                                                          |
      | Cardinal Org Unit ID blank      | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG                | Yes       | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac |                          | Field is required |                                                          |

#  Scenario: Activation Validation in Edit mode in case of invalid #SB_SET_PMS_PPP_31 #SB_SET_PMS_PPP_32
#    Given activate "PayPal Pro" gateway successfully in "Activate" mode
#      | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     |
#      | PayPal                 | shopbase              | shopbase            | June1$2020              | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |
#    Then validate activation input for "PayPal Pro" in "Edit" mode
#      | Case                            | PayPal Manager Partner | PayPal Manager Vendor | PayPal Manager User | PayPal Manager Password | 3D secure | Cardinal API ID          | Cardinal API Key                     | Cardinal Org Unit ID     | Message           | Notice Message                                           |
#      | Invalid PayPal Manager Partner  | PayPal1                | profitteam            | haduongapi          | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |
#      | Invalid PayPal Manager Vendor   | PayPal                 | profitteam1           | haduongapi          | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |
#      | Invalid PayPal Manager User     | PayPal                 | profitteam            | haduongapi1         | 5z5YFtZG                | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |
#      | Invalid PayPal Manager Password | PayPal                 | profitteam            | haduongapi          | 5z5YFtZG1               | No        | 5e3d172f031e732e0843a5d4 | 48172558-6bb5-4b6a-992f-fc3f160191ac | 5e3d172f6bcd0a178c1a8630 |                   | The API credentials are invalid. Please check them again |