Feature: Update Signal For Fraud Indicator
  #env = order_in_fraud_rule

  Scenario Outline: Setting fraud filter
    Given user login to shopbase dashboard
    Given user navigate to "Orders>Fraud filter" screen
#    Given open url "https://q-payment-test.onshopbase.com/admin/fraud-filters?access_token=91b53b5bb06bf63a2240d5477317154b9be10ec20130a98aadc429260f32345e&scopes=JTVCJTIyYWRtaW4vKiUyMiU1RA%3D%3D&shop_data=JTdCJTdE"
    And delete "<fraud filter>" fraud filter
    Then set up new fraud filters
      | Name                                   | Action       | Rule Name          | Rule Condition | value |
      | Au-3D secure required is equal to true | Cancel order | 3D secure required | is equal to    | true  |
    Examples:
      | fraud filter                           |
      | AU-3D secure required is equal to true |