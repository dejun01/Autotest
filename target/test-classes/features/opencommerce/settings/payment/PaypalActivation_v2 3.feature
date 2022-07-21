#environment = sbase_gateway_activation

Feature: Activating PayPal gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow #SB_SET_PMS_PP_25 #SB_SET_PMS_PP_21 #SB_SET_PMS_PP_22 #SB_SET_PMS_PP_23 #SB_SET_PMS_PP_24 #SB_SET_PMS_PP_26
  # Activate PayPal Express gateway successfully
    Then activate "PayPal" gateway successfully in "Activate" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | Aeh4GpfSwN1xsoUBCqs85cjE95HEyuij_ePjpHG060nZ6hExulKGjNHcflZkzMFutnEpiLr1pS76PUBC | EPvncdTSFu_ia2oUpwtnHwjqENKcpOWrvhOEr8GGuj8YBn9MH5_Wr808nWUyXpu1zcKRjnqU04OQ3T4Y |
    Then verify "PayPal" gateway info after "Activating"
  # Edit PayPal Express gateway successfully in Edit mode
    Then activate "PayPal" gateway successfully in "Edit" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | Aeh4GpfSwN1xsoUBCqs85cjE95HEyuij_ePjpHG060nZ6hExulKGjNHcflZkzMFutnEpiLr1pS76PUBC | EPvncdTSFu_ia2oUpwtnHwjqENKcpOWrvhOEr8GGuj8YBn9MH5_Wr808nWUyXpu1zcKRjnqU04OQ3T4Y |
    Then verify "PayPal" gateway info after "Editing"
  # Deactivate PayPal Express gateway successfully
    Then deactivate gateway "PayPal" by account name ""
    Then verify "PayPal" gateway info after "Deactivating"
  # Reactivate PayPal Express gateway successfully
    Then activate "PayPal" gateway successfully in "Reactivate" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | Aeh4GpfSwN1xsoUBCqs85cjE95HEyuij_ePjpHG060nZ6hExulKGjNHcflZkzMFutnEpiLr1pS76PUBC | EPvncdTSFu_ia2oUpwtnHwjqENKcpOWrvhOEr8GGuj8YBn9MH5_Wr808nWUyXpu1zcKRjnqU04OQ3T4Y |
    Then verify "PayPal" gateway info after "Activating"
  #Remove account PayPal Express gateway successfully
    Then remove gateway account "PayPal" by account name ""
    And close browser

  Scenario: Activation Validation #SB_SET_PMS_PP_32 #SB_SET_PMS_PP_28 #SB_SET_PMS_PP_29 #SB_SET_PMS_PP_30 #SB_SET_PMS_PP_31 #SB_SET_PMS_PP_33
    Then validate activation input for "PayPal" in "Activate" mode
      | Case                           | Account name                  | Client ID                                                                        | Client Secret Key                                                                | Message | Notice Message                                           |
      | Client ID is not exist         | PayPal Express_Validate_input | abc                                                                              | EOU0iGeg1J7DEzcD9tVYBzjLD7ia_tWSQ9g9Ia-E-nK5DGenuZwXGJAtEGOY7m6pwgDD95uV0bGJGaCW |         | The API credentials are invalid. Please check them again |
      | Client Secret Key is not exist | PayPal Express_Validate_input | AfL2WyCQuIis451KPSSrSIgAAfFyiqFDgz5FPYAk4N8som1mrPVlgtK4CFZamgA8UP9GQzFfOGW6ELoP | abc                                                                              |         | The API credentials are invalid. Please check them again |
    And close browser

    @debug_activate
    Scenario: Activating PayPal gateway by PCP flow if FS paypal_updates_0521 is approved
        # Activate PayPal Express gateway successfully by PCP flow
    Given click on Sign in/Sign up with PayPal button
    When verify the message is displayed
      | Text                                                                                                        |
      | One integration for all your online payment needs.                                                          |
      | Enable a seamless buying experience for your customers that drives conversion and loyalty                   |
      | Accept PayPal payments with simplified on-boarding, adaptable integration and easy account setup            |
      | Access to 377M+ PayPal customers around the globe*, with local currency support for better money management |
      | Peace of mind for you and your customers with buyer and seller protection on eligible sales                 |
#    Then activate PayPal gateway successfully by PCP flow
#      | Email                    | Password   |
#      | shopbase+2@beeketing.net | June1$2020 |
#    Then verify PayPal gateway info after "Activating" by PCP flow