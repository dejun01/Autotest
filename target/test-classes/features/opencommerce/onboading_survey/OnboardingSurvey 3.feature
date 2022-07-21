Feature: create new shop and onboarding survey

#  prod_onboarding_survey


  Scenario Outline: verify onboarding survey #SB_OBD_10 #SB_OBD_9 #SB_OBD_8 #SB_OBD_6 #SB_OBD_7 #SB_OBD_5
    Given login to hive sbase
#    Given login to hive sbase by google
    And clear shop data as "shopId"
    Given user login dashboard and select shop "shopName"
    Then user add your contact "<KEY>"
      | KEY | Store country | Personal location | Contact    | Social profile         | Message                    |
      | 1   | Hong Kong     | Hong Kong         | 25277666   |                        |                            |
      | 2   | Andorra       | Andorra           | 301144     | shopbase@beeketing.net |                            |
      | 3   | Andorra       | Andorra           | 301144     |                        |                            |
      | 4   | Vietnam       | Vietnam           |            |                        | Enter a valid phone number |
      | 4   |               |                   | 0983240334 | shopbase@beeketing.net |                            |
    And  User choose your business type is "<Business type>" and store type is "<Store type>"
    Then select store to clone "<KEY>"
      | KEY | isShow | Selected store | Action                            |
      | 1   | true   |                | No thanks, I don't want to import |
      | 2   | true   |                | No thanks, I don't want to import |
      | 3   | true   |                | No thanks, I don't want to import |
      | 4   | fail   |                | No thanks, I don't want to import |
    And customized onboarding questions "<KEY>"
      | KEY | Country   | Store type       |
      | 1   | Hong Kong | General Dropship |
      | 2   | Andorra   | Print-on-demand  |
      | 3   | Andorra   | White Label      |
      | 4   | Việt Nam  | PrintBase        |
    Then verify created shop
    Then close browser
    Examples:
      | KEY | Business type   | Store type               |
      | 1   | Dropshipping    | I want a ShopBase store  |
      | 2   | Print On Demand | I want a ShopBase store  |
      | 3   | Others          |                          |
      | 4   | Print On Demand | I want a PrintBase store |


  Scenario Outline: verify data default for PrintBase
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Pages" screen
    And verify page created on dashboard "<KEY>"
      | KEY | Title                    | Status  |
      | 1   | Contact us               | Visible |
      | 1   | FAQs                     | Visible |
      | 1   | Order Tracking           | Visible |
      | 1   | Product Details & Sizing | Visible |
    When user navigate to "Themes" screen
    And disable password in dashboard
    And open shop on storefront
    And verify footer on store front "<KEY>"
      | KEY | Shop type | Show payment method icons | Type         | Content                                                                                                                  |
      | 1   | Printbase | true                      | Contact info | CONTACT INFO>Email us: support@shops-support.com>Phone: 1-408-899-8879>548 Market St #14148, San Francisco, CA 94104 USA |
      | 1   | Printbase | true                      | Menu         | SUPPORT>Contact us,FAQs,Sizing                                                                                           |
      | 1   | Printbase | true                      | Menu         | POLICIES>Privacy policy,Terms of Service,Shipping policy,Refund policy,Billing Terms and Conditions                      |
    Examples:
      | KEY |
      | 1   |