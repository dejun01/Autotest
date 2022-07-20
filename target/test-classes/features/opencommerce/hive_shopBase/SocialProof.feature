Feature: Social Proof
  #sbase_social_proof

  Scenario: Create Social Proof #SB_LDP_SP_13 #SB_LDP_SP_12 #SB_LDP_SP_11 #SB_LDP_SP_10 #SB_LDP_SP_9 #SB_LDP_SP_8 #SB_LDP_SP_7 #SB_LDP_SP_6 #SB_LDP_SP_5 #SB_LDP_SP_4 #SB_LDP_SP_1
    Given login to hive sbase
    When verify social proof list page
    Then create social proof
      | Name              | Display time | Waiting time | Delay time | Title    | Content  | Time | Link                  | Image           | Messages                                                 |
      |                   | 3            | 4            | 8          | T1       | C1       | 5    | https://google.com.vn | front/logo2.jpg |                                                          |
      | PrintBase Compare | 6            | 5            | 13         | T9       | C9       | 5    | https://google.com.vn | front/logo2.jpg | Unique index duplicate entry name, this name had existed |
      | Social proof2     |              | 5            | 7          | T2       | C2       | 5    | https://google.com.vn | front/logo2.jpg |                                                          |
      | Social proof3     | -8           | 5            | 6          | T3       | C3       | 5    | https://google.com.vn | front/logo2.jpg | This value should be positive.                           |
      | Social proof4     | 5            |              | 9          | T4       | C4       | 5    | https://google.com.vn | front/logo2.jpg |                                                          |
      | Social proof5     | 5            | -5           | 10         | T5       | C5       | 5    | https://google.com.vn | front/logo2.jpg | This value should be positive.                           |
      | Social proof6     | 5            | 8            |            | T6       | C6       | 5    | https://google.com.vn | front/logo2.jpg |                                                          |
      | Social proof7     | 5            | 8            | -8         | T6       | C6       | 5    | https://google.com.vn | front/logo2.jpg | Wrong delay time, please check                           |
      | Social proof8     | 5            | 8            | 10         |          | C7       | 5    | https://google.com.vn | front/logo2.jpg |                                                          |
      | Social proof9     | 4            | 5            | 11         | T8       |          | 5    | https://google.com.vn | front/logo2.jpg |                                                          |
      | Social proof10    | 5            | 5            | 12         | T9       | C9       |      | https://google.com.vn | front/logo2.jpg |                                                          |
      | Social proof12    | 5            | 5            | 12         | T9       | C9       | 5    |                       | front/logo2.jpg |                                                          |
    And close browser

  Scenario: Edit and Delete and display Social Proof #SB_LDP_SP_19 #SB_LDP_SP_18 #SB_LDP_SP_17 #SB_LDP_SP_3 #SB_LDP_SP_2
    Given login to hive sbase
    When verify social proof list page
    Then create social proof
      | Name              | Display time | Waiting time | Delay time | Title    | Content  | Time | Link                  | Image           | Messages                                                 |
      | @Social proof@    | 5            | 5            | 15         | customer | purchase | 5    | https://google.com.vn | front/logo2.jpg | has been successfully created.                           |
    Then edit social proof
      | Name             | Display time | Waiting time | Delay time | Title | Content   | Time           | Link                                        | Messages                       |
      | Social proof MKT | 12           | 12           | 12         | buyer | purchased | 12 minutes ago | https://www.youtube.com/watch?v=kjYW63CVbsE | has been successfully updated. |
    And delete social proof
    Then open shop on storefront
    When verify display Social Proof
    And login to hive sbase
    And verify view and click after social proof display




