Feature:
  #env = game_hall

  Background:
    Given clear all data
#    And login to dashboard

  Scenario: AE dianzi
    And login to P3 dashboard
    Then switch language to "中文"
    Then navigate to "投注管理" tab
    Then select a game hall
    Then



