@presetShopTemplatePrintBase
Feature: Preset shop template print base
#pbase_preset_shop_template

  Scenario: verify UI
    Given user login to shopbase dashboard by API
    And user navigate to "Settings" screen
    And Click to "General" section at Settings screen
    Then verify store name
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then Verify UI of the Payment providers page

  Scenario Outline: verify mail contact
    Given open shop on storefront
    And search and select the product "PrintBase Blanket B106"
    And add campaign to cart as "<KEY>"
      | KEY |
      | 1   |
    And click to button checkout
    And Verify one page checkout
      | Title            |
      | Shipping address |
      | Billing address  |
      | Shipping method  |
      | Payment method   |
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then verify email on thank you page
      | Mail                             |
      | mailto:huyennguyen@beeketing.net |
      | mailto:support@shops-support.com |
    Examples:
      | KEY |
      | 1   |

  Scenario: Verify information campaign detail
    Given open shop on storefront
    Then verify information campaign on Storefront
      | Campaign name                    | Price  | Size                              | Color       |
      | PrintBase Quilt Q101             | $45.99 | Single,Twin,Queen,King,Super King |             |
      | PrintBase Blanket B101           | $59.99 | Large,X-Large                     |             |
      | PrintBase multiple 2D products 1 | $20.49 | S,M,L,XL,2XL,3XL                  | White,Black |