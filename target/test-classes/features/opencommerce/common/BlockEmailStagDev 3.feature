@dashboardCommon @blockEmailStagDev
Feature: Block Email

  Scenario: Add staff account with disposable email
    Given user login to shopbase dashboard
    Then user navigate to "Settings" screen
    Then Click to "Account" section at Settings screen
    Then add staff account with email
      """
      yopmail.com
      """

  Scenario: SB_DB_AUTHFLOW_3.3: Verify block disposable email addresses
    Given user signup ShopBase with shop domain "au-test-authen", password "123456" and email
    """
    yopmail.com

    """