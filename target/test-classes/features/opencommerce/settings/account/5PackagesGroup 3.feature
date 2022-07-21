@setting
Feature: Update region settings for package group & layout for 5 packages
  #5_packages

#  Scenario: Signup using China domain #SB_SET_ACC_5PG_2 #SB_SET_ACC_5PG_3 #SB_SET_ACC_5PG_4
#    Given login to hive sbase
#    And get package id in hive
#    Then create a shop with new account and password with domain
#      | Shop       | Email   | Password | Domain |
#      | @Shopname@ | @Email@ | 123456   | China  |
#    Then verify package "China"

  Scenario: Signup using Vietnam domain #SB_SET_ACC_5PG_11 #SB_SET_ACC_5PG_12
    Given login to hive sbase
    And get package id in hive
    Then create a shop with new account and password with domain
      | Shop       | Email   | Password | Domain  |
      | @Shopname@ | @Email@ | 123456   | Vietnam |
    Then verify package "Vietnam"

  Scenario: Signup using specific email #SB_SET_ACC_5PG_11
    Given login to hive sbase
    And get package id in hive
    Then create a shop with new account and password with domain
      | Shop       | Email           | Password | Domain  |
      | @Shopname@ | @andres.nguyen@ | 123456   | Vietnam |
    Then verify package "Vietnam"

  Scenario: Signup using specific domain email #SB_SET_ACC_5PG_12
    Given login to hive sbase
    And get package id in hive
    Then create a shop with new account and password with domain
      | Shop       | Email      | Password | Domain  |
      | @Shopname@ | chinaemail | 123456   | Vietnam |
    Then verify package "China"