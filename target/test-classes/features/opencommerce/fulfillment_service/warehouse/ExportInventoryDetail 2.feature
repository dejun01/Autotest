Feature: Export inventory detail
  #env = sbase_fulfillment_service

  Scenario: Verify export product move when not search by condition and choose current page have variant lesser 50
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Click on view inventory detail "CNYISHE 2021 Sexy Fire Print Bodysuits Women Fashion Party Off the Shoulder Jumpsuits Skinny Bodycon Black Women Rompers Female"
      | Variant           |
      | Black / S / China |
      | Black / M / China |
      | Black / L / China |
    And get info variant inventory
    And Export variant by "Current page"
    Then verify noti export equal "Make download link successfully"
    And Verify info file export
      | Product Name | Product Variant Name | Date | Type | Reference | Quantity |
    And close browser


  Scenario: Verify export product move when search by condition and choose current page have variant lesser 50
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Click on view inventory detail "CNYISHE 2021 Sexy Fire Print Bodysuits Women Fashion Party Off the Shoulder Jumpsuits Skinny Bodycon Black Women Rompers Female"
      | Variant           |
      | Black / S / China |
      | Black / M / China |
      | Black / L / China |
    And search in product move
      | condition         |
      | Black / S / China |
    And get info variant inventory buy search
    And Export variant by "Current page"
    Then verify noti export equal "Make download link successfully"
    And Verify info file export
      | Product Name | Product Variant Name | Date | Type | Reference | Quantity |
    And close browser


#  Scenario: Verify send mail when export choose current page have variant over 50 product
#    Given user login to shopbase dashboard
#    Given user navigate to "fulfillment/dropship/warehouse" screen by url
#    And Click on view inventory detail "CNYISHE Sexy Sleeveless Mesh Sheer Skinny Bodysuits Rompers Women Jumpsuits Fashion Solid Black Red Bodies Ladies Tops Overalls"
#      | Variant |
#      |         |
#      |         |
#    And Choose export "100" subvariant
#    And Export variant by "Current page"
#    Then Verify noti "Your export will be delivered to email: shopbase@beeketing.net"
#    And close browser