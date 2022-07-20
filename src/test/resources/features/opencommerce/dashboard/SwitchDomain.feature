@dashboardCommon @dashboard @authentication
Feature: Auto change language when switching to CN domain
#sbase_switch_domain

  Scenario: Verify Auto change language when switching to CN domain #SB_AU_CN_DOMAIN_58 #SB_AU_CN_DOMAIN_59
    Given user login to shopbase dashboard
    Then switch server to "Switch to China admin site"
    And verify text on dashboard "主页" is displayed
    Then switch server to "切换到美国后台版"
    And verify text on dashboard "HOME" is displayed
    And close browser