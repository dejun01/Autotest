Feature: Fulfill current orders in quotation detail
  #fulfill_now_quotation_detail

  Scenario: Pre-condition Clear data fulfill current order case 1
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/quotations" screen by url
    And click "Quotation created" tab
    Then search quotation in Request list quotation
      | Quotation ID      | Is show |
      | shipping standard | true    |
    Then clear data fulfill current order
    Then verify undisplay tab Fulfill current orders in quotation detail
    Then close browser

  Scenario: Pre-condition Clear data fulfill current order case 2
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/quotations" screen by url
    And click "Quotation created" tab
    Then search quotation in Request list quotation
      | Quotation ID        | Is show |
      | shipping electronic | true    |
    Then clear data fulfill current order
    Then verify undisplay tab Fulfill current orders in quotation detail
    Then close browser

  Scenario: Pre-condition Checkout order
    Given open shop on storefront
    Then checkout successfully with cart "(Test)Autumn Korean:S>2;(Test)Autumn Korean:M>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given open shop1 on storefront
    Then checkout successfully with cart "(Test)Autumn Korean:S>2;(Test)Autumn Korean:M>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And close browser

  Scenario: Verify data when fulfill current orders in quotation detail with product mapped one store
    Given open shop on storefront
    Then checkout successfully with cart "MC Velvet Party Dress:S>2;MC Velvet Party Dress:M>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/quotations" screen by url
    And get count "Unfulfilled" inventory of product warehouse
      | Product                                                                                                                          | Variant       | Result |
      | CNYISHE Printed Polka Dot Tight Bodysuit Rompers Women Jumpsuits Autumn Hipster Outfits Streetwear Body Suits for Women Overalls | Navy Blue / M | 1      |
      | CNYISHE Printed Polka Dot Tight Bodysuit Rompers Women Jumpsuits Autumn Hipster Outfits Streetwear Body Suits for Women Overalls | Navy Blue / S | 1      |
    And click "Quotation created" tab
    Then search quotation in Request list quotation
      | Quotation ID      | Is show |
      | shipping standard | true    |
    And get ordered quantity in Fulfill currder orders tab
      | Variant     |
      | Navy Blue/M |
      | Navy Blue/S |
    And verify data "Unfulfilled" inventory of product warehouse after update
    And verify information variant in Fulfill order current
      | Variant>unitPrice>subTotal                      | Total items | Total baseCost  | Subtotal | QuantityOrderUnfulfilled |
      | Navy Blue/M>$2.50>$5.00;Navy Blue/S>$2.00>$4.00 | 4 items     | $4.50 base cost | US $9.00 | 1                        |
    And get shipping fee of order by API
    And click button "Fulfill now"
    And Verify info in review tab
      | items wil be fulfilled | item can't be fulfilled | WAREHOUSE ITEM>QUANTITY>COST                                                      | TOTAL | Auto purchase |
      | 0                      | 2                       | CNYISHE Printed...,S / Navy Blue>2>$4.00;CNYISHE Printed...,M / Navy Blue>2>$5.00 | $9.00 | true          |
    And Verify info in Make a payment tab
      | Charge shipping & purchasing order PlusHub | Shipping fee | Product cost | Total | Shipping Method               |
      |                                            |              | $4.00        |       | Yun Express Standard shipping |
    Then verify undisplay tab Fulfill current orders in quotation detail
    And close browser

  Scenario: Verify when fulfill now in quotation detail with product mapped more store
    Given user login to shopbase dashboard
    When user navigate to "fulfillment/dropship/quotations" screen by url
    And get count "Unfulfilled" inventory of product warehouse
      | Product                  | Variant | Result |
      | (Test)NormalKoreanAutumn | White   | 1      |
      | (Test)NormalKoreanAutumn | Black   | 1      |
    And click "Quotation created" tab
    Then search quotation in Request list quotation
      | Quotation ID        | Is show |
      | shipping electronic | true    |
    And get ordered quantity in Fulfill currder orders tab
      | Variant |
      | White   |
      | Black   |
    And verify data "Unfulfilled" inventory of product warehouse after update
    And verify information variant in Fulfill order current
      | Variant>unitPrice>subTotal          | Total items | Total baseCost  | Subtotal | QuantityOrderUnfulfilled |
      | White>$1.50>$6.00;Black>$1.50>$6.00 | 8 items     | $3.00 base cost | US $12.00 | 2                        |
    And click button "Fulfill now"
    And Verify info in review tab
      | items wil be fulfilled | item can't be fulfilled | WAREHOUSE ITEM>QUANTITY>COST                                      | TOTAL | Auto purchase | Fulfill |
      | 0                      | 4                       | (Test)NormalKor...,White>4>$6.00;(Test)NormalKor...,Black>4>$6.00 | $12.00 | true          | true    |
    Then verify undisplay tab Fulfill current orders in quotation detail
    And close browser
