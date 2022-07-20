@notification @emailContentNotification
  Feature: email content notification

    @orderConfirmation
    Scenario: SB_DAS_SET_9.5.1, 9.5.2 - Verify send email "Order confirmation" after checkout successfully with stripe
      Given open shop on storefront
      And add products "Necklace" to cart then checkout
      And checkout by Stripe successfully
      Then login to "jone_doe@mailtothis.com" and verify send email "Order confirmation" after checkout successfully
      And close browser

    @orderCanceled
    Scenario: SB_DAS_SET_9.5.20, 9.5.21 - Verify send email "Order canceled" after cancel successfully
      Given open shop on storefront
      And add products "Necklace" to cart then checkout
      And checkout by Stripe successfully
      When user login to shopbase dashboard
      And user navigate to "Orders" screen
      And click on order name in unfulfilled orders list
      And open cancel order popup then cancel fully order and restock items
      Then login to "jone_doe@mailtothis.com" and verify send email "Order canceled" after cancel successfully
      And close browser

    @orderRefund
    Scenario: SB_DAS_SET_9.5.22, 9.5.23 - Verify send email "Order refund" after refund successfully
      Given open shop on storefront
      And add products "Necklace" to cart then checkout
      And checkout by Stripe successfully
      When user login to shopbase dashboard
      And user navigate to "Orders" screen
      And click on order name in unfulfilled orders list
      And refund order with "Necklace>1", "$20.00" and "Damaged in transit"
      And verify order detail including "Refunded", "$0.00" and quantity of item after refunding
      Then login to "jone_doe@mailtothis.com" and verify send email "Order refund" after refund successfully
      And close browser

#    @abandonedCheckout
#    Scenario: SB_DAS_SET_9.5.30, 9.5.31 - Verify send email "Abandoned checkout" after refund successfully
#      Given open shop on storefront
#      And add products "Necklace" to cart then checkout
#      And enter customer information
#      And get the abandoned checkout details on storefront
#      Then close browser
#      Given user login to shopbase dashboard
#      Then user navigate to "Orders>Abandoned checkouts" screen
#      Then send a cart recovery email
#      Then login to "jone_doe@mailtothis.com" and verify send email "Abandoned checkouts"
#      And close browser

    @shippingConfirmation
    Scenario: SB_DAS_SET_9.5.36, 9.5.37 - Verify send email "Shipping confirmation" after order is fulfilled
      Given open shop on storefront
      And add products "Necklace" to cart then checkout
      And checkout by Stripe successfully
      Given user login to shopbase dashboard
      Given user navigate to "Orders" screen
      And click on order name in unfulfilled orders list
      Then fulfill the order with "Necklace>1", "testFulfill", "China EMS (ePacket)", "" and "Fulfilled"
      Then login to "jone_doe@mailtothis.com" and verify send email "Shipping confirmation" after order is fulfilled
      And close browser

#    @shippingeDelaymails
#    Scenario: Verify send mail "Delayed shipping" to customer if their order has not been fulfilled after a period of time
#      Given open shop on storefront
#      Then add products "Necklace" to cart
#      And checkout by Stripe on storefront successfully
#      Given user login to shopbase dashboard
#      Then user navigate to "Orders" screen
#      And check that exist the latest unfulfilled order in orders list
#      And login to customer's email and open email with subject "Order export is ready"
#      And Wait to verify that send mail to customer when their order has not been shipped after "3 days"