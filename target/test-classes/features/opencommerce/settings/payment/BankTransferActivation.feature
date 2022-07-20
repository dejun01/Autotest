Feature: Bank Transfer payment - Dashboard
#sbase_cod_activation

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And deactivate "Cash On Delivery (COD)"
    And deactivate "Bank Transfer"

  Scenario: Activating Bank Transfer #SB_SET_PMS_BT_4 #SB_SET_PMS_BT_5 #SB_SET_PMS_BT_6 #SB_SET_PMS_BT_7 #SB_SET_PMS_BT_14 #SB_SET_PMS_BT_15 #SB_SET_PMS_BT_16 #SB_SET_PMS_BT_19
    Given Go to "Bank Transfer" section
#  send nothing
    When Add "" to 'Additional details' field and active "Bank Transfer"
    Then Verify "Bank Transfer" is active

  #Send 200+ characters and save
    When Change Additional details field to "0123456789 123456789 223456789 323456789 423456789 523456789 623456789 723456789 823456789 9234567890123456789 123456789 223456789 323456789 423456789 523456789 623456789 723456789 823456789 923456789 x2"
    Then Verify error message "Additional details must be lest than 200 characters" will be shown
    And Verify status of "Bank Transfer" when back to Payment Providers page

  #edit and save
    When Change Additional details field to "Something new, something old"
    Then Verify "Bank Transfer" is active
    And Verify The 'Additional details' field is "Something new, something old"

  #delete and save
    When Change Additional details field to ""
    Then Verify "Bank Transfer" is active

  #active normally Cash on Delivery (COD)
    Given Go to "Cash On Delivery (COD)" section
    When Add "" to 'Additional details' field and active "Cash on Delivery (COD)"
    Then Verify "Cash on Delivery (COD)" is active
    Then Verify "Bank Transfer" is active

    And close browser


