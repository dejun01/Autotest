Feature:  Claim order scalablepress

  Scenario: Claim
    Given login to ScalablePress with username is "thainguyen@beeketing.com" and pwd is "q@u!d2P1zkdwwfE.zl"
    And claim order by ID from csv file is "/print/claim_sp_test.csv"
      | Email | Reason              | Remark                                                                                 | Preferred Resolution |
      |       | Order Not Delivered | The order has not been delivered and there is no update on the tracking number as well | Credit               |
