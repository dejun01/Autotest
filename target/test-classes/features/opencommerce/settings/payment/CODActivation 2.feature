Feature: COD payment - Dashboard
#sbase_cod_activation

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And deactivate "Cash On Delivery (COD)"
    And deactivate "Bank Transfer"

  Scenario: Activating Cash On Delivery (COD) #SB_SET_PMS_BT_18 #SB_SET_PMS_BT_17 #SB_SET_PMS_COD_5 #SB_SET_PMS_COD_6 #SB_SET_PMS_COD_7 #SB_SET_PMS_COD_14 #SB_SET_PMS_COD_15 #SB_SET_PMS_COD_16 #SB_SET_PMS_COD_17
    Given Go to "Cash On Delivery (COD)" section
#  send nothing
    When Add "" to 'Additional details' field and active "Cash on Delivery (COD)"
    Then Verify "Cash on Delivery (COD)" is active

  #Send 200+ characters and save
    When Change Additional details field to "0123456789 123456789 223456789 323456789 423456789 523456789 623456789 723456789 823456789 9234567890123456789 123456789 223456789 323456789 423456789 523456789 623456789 723456789 823456789 923456789 x2"
    Then Verify error message "Additional details must be lest than 200 characters" will be shown
    And Verify status of "Cash on Delivery (COD)" when back to Payment Providers page

  #edit and save
    When Change Additional details field to "Something new, something old"
    Then Verify "Cash on Delivery (COD)" is active
    And Verify The 'Additional details' field is "Something new, something old"

  #delete and save
    When Change Additional details field to ""
    Then Verify "Cash on Delivery (COD)" is active

  #active normally Bank Transfer
    Given Go to "Bank Transfer" section
    When Add "" to 'Additional details' field and active "Bank Transfer"
    Then Verify "Bank Transfer" is active
    Then Verify "Cash on Delivery (COD)" is active

    And close browser


