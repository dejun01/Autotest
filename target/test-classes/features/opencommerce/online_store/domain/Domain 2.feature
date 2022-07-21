@ForceAddDomain
Feature: Force_add domain

  #sbase_domain
  Background:
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Domains" screen

  Scenario: Remove domain #SB_OLS_DM_55 #SB_OLS_DM_58 #SB_OLS_DM_60 #SB_OLS_DM_61 #SB_OLS_DM_54
    Then remove all domain from list domain
    Then verify primary domain on storefront

  Scenario: verify connect, redirect domain #SB_OLS_DM_41 #SB_OLS_DM_42 #SB_OLS_DM_43 #SB_OLS_DM_44 #SB_OLS_DM_45 #SB_OLS_DM_46 #SB_OLS_DM_47 #SB_OLS_DM_48 #SB_OLS_DM_49 #SB_OLS_DM_50  #SB_OLS_DM_51 #SB_OLS_DM_52 #SB_OLS_DM_53 #SB_OLS_DM_62 #SB_OLS_DM_59
    When user connect domain
      | Domain Production      | Domain Staging       | Error msg                                                                                            |
      | lala.sbaseprod.tkabc   | lala.sbasestag.tkabc | You must enter your full domain name and extension (except .TK, .ML, .GA, .CF, .GQ), e.g shopase.com |
      | shopbase.sbprod.tk     | shopbase.sbstag.tk   | Domains cannot contain keywords: shopbase, printbase, plusbase.                                      |
      | printbase.sbprod.tk    | printbase.sbstag.tk  | Domains cannot contain keywords: shopbase, printbase, plusbase.                                      |
      | plusbase.sbprod.tk     | plusbase.sbstag.tk   | Domains cannot contain keywords: shopbase, printbase, plusbase.                                      |
      | www.longki-clothes.com | www.front-stag.club  |                                                                                                      |
      | www.longki-clothes.com | www.front-stag.club  | there is 1 error\nA store with that domain name already exists.                                      |
      | www.clothespeter.com   | www.front-stag.xyz   |                                                                                                      |
    When verify to enable redirection domain is "false"
    When verify to enable redirection domain is "true"
    Then verify primary domain is
      | Domain Production      | Domain Staging      |
      | www.longki-clothes.com | www.front-stag.club |
    When user change primary domain to an connected domain is
      | Domain Production    | Domain Staging     |
      | www.clothespeter.com | www.front-stag.xyz |
    Then close browser

  Scenario: Verify url move key from dashboard #SB_OLS_DM_56 #SB_OLS_DM_57
    Then verify domain navigate from dashboard
      | Url Input                                   | Url Output                 |
      | ?from-dashboard=1                           | /                          |
      | /products/white-lace-dress?from-dashboard=1 | /products/white-lace-dress |
      | /collections/dress?from-dashboard=1         | /collections/dress         |
      | /pages/contact-us?from-dashboard=1          | /pages/contact-us          |
      | /pages/all-reviews?from-dashboard=1         | /pages/all-reviews         |

