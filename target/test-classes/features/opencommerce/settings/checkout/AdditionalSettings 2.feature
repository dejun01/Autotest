Feature: Additional  setting
#env = tipping_option

  Scenario Outline:  verify setting additional setting work on sf #SB_SF_TOS_108, #SB_SF_TOS_110, #SB_SF_TOS_114, #SB_SF_TOS_116, #SB_SF_TOS_120, #SB_SF_TOS_130, #SB_SF_TOS_132, #SB_SF_TOS_145, #SB_SF_TOS_147
    Given user login to shopbase dashboard by API
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    When setting additional with "<KEY>"
      | KEY | isShowLegal | Manual confirm | Auto confirm |
      | 1   | true        | Yes            |              |
      | 2   | true        |                | Yes          |
      | 3   | false       |                |              |
    And close browser
    Given open shop on storefront
    Given add products "Bikini" and switch to cart
    Then click button checkout
    Then wait 5 second
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And verify tos checkout with "<KEY>"
      | KEY | ShowCheckboxTos |
      | 1   | Manual          |
      | 2   | Auto            |
      | 3   | No              |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |