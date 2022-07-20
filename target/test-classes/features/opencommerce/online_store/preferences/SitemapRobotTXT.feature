Feature: Sitemap + Robots.txt
#  enviromment stag:  sbase_redirect_url

  Scenario: Verify Robots.txt file #SB_SF_25 #SB_SF_26 #SB_SF_27 #SB_SF_28 #SB_SF_29 #SB_SF_30 #SB_SF_31 #SB_SF_32 #SB_SF_33
    Given open tool on storefront
    Then verify status and sitemap in Robots.txt file structure
      |Status     |URL                      |
      |Disallow   |/admin                   |
      |Disallow   |/login                   |
      |Disallow   |/cart                    |
      |Disallow   |/my-account              |
      |Disallow   |/search                  |
      |Disallow   |/policies                |
      |Disallow   |/checkouts               |
      |Disallow   |/orders                  |
      |Disallow   |/*theme_preview_id*      |

  Scenario Outline: Edit robots.txt #SB_OLS_PFR_21 #SB_OLS_PFR_24 #SB_OLS_PFR_39 #SB_OLS_PFR_29 #SB_OLS_PFR_28 #SB_OLS_PFR_25
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Preferences" screen
    And verify navigate to Learn more screen as "<KEY>"
      | KEY |
      | 1   |
    Then verify navigate to Edit Robots.txt screen
    And verify edit robots.txt file as "<KEY>"
      | KEY |Content                                                  |Method                      |Action                      |Message                                                                                        |
      | 1   |User-agent: * Sitemap: {primary_domain}/sitemap.xml      |Input                       |Save                        |Upload robots.txt success                                                                      |
      | 2   |                                                         |Input                       |Save                        |You seemed to falsely edit the file. Please review the syntax! Empty file is not allowed also. |
      | 3   |User-agent: *                                            |Input                       |Save                        |You seemed to falsely edit the file. Please review the syntax! Empty file is not allowed also. |
      | 4   |Sitemap: {primary_domain}/sitemap.xml                    |Input                       |Save                        |You seemed to falsely edit the file. Please review the syntax! Empty file is not allowed also. |
      | 5   |                                                         |Reset to ShopBase's default |Save                        |Upload robots.txt success                                                                      |
      | 6   |User-agent                                               |Input                       |Cancel                      |                                                                                               |
    When copy and redirect to file robot text in SF
    Then verify robots.txt file in SF as "<KEY>"
      | KEY |Content                                                  |
      | 1   |User-agent: * Sitemap: {primary_domain}/sitemap.xml      |
      | 2   |User-agent: * Sitemap: {primary_domain}/sitemap.xml      |
      | 3   |User-agent: * Sitemap: {primary_domain}/sitemap.xml      |
      | 4   |User-agent: * Sitemap: {primary_domain}/sitemap.xml      |
      | 5   |Default                                                  |
      | 6   |Default                                                  |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |
      | 6   |




    


