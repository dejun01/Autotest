Feature: Navigation
  #sbase_redirect_url

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Navigation" screen

  Scenario: Add a new navigation menu #SB_OLS_NVG_8
    And user create new navigation menu
      | Old Title  | Title      | Name         | Link 1    | Link 2        | Legal template      |
      |            |            | Page content | Pages     | About Us      |                     |
      |            | Intro page | Page content | Pages     | About Us      |                     |
      | Intro page | Intro page | Legal        | Policies  | Refund policy | Refund to customers |
      | Intro page | Intro page | Home         | Home page |               |                     |
    Then verify menu added on dashboard
      | Title      | is Displayed |
      | Intro page | true         |
    And customize theme to show menu on store front
      | Title      |
      | Intro page |
    And verify navigation menu on store front
      | Title      | Name         | Link 1    | Link 2        | Legal template      |
      | Intro page | Page content | Pages     | About Us      |                     |
      | Intro page | Legal        | Policies  | Refund policy | Refund to customers |
      | Intro page | Home         | Home page |               |                     |

  Scenario: Edit menu #SB_OLS_NVG_4 #SB_OLS_NVG_5 #SB_OLS_NVG_6
    And user create new navigation menu
      | Old Title   | Old name     | Title       | Name        | Link 1   | Link 2        | Legal template |
      | Intro page  | Page content | Page update | New arrival | Products | dress         |                |
      | Page update | Legal        | Page update | Legal       | Policies | Refund policy | RETURN         |
      | Page update | Home         | Page update |             |          |               |                |
    Then verify menu added on dashboard
      | Title       | is Displayed |
      | Page update | true         |
    And customize theme to show menu on store front
      | Title       |
      | Page update |
    And verify navigation menu on store front
      | Title       | Name        | Link 1   | Link 2        | Legal template |
      | Page update | New arrival | Products | dress         |                |
      | Page update | Legal       | Policies | Refund policy | RETURN         |

  Scenario: Delete menu #SB_OLS_NVG_9 #SB_OLS_NVG_10
    And user delete navigation menu
      | Title       |
      | Main menu   |
      | Page update |
    Then verify menu added on dashboard
      | Title       | is Displayed |
      | Page update | false        |

  Scenario: Page locks
    And user click on "Page locks" button
    Then verify page locks checked on SF
      | Pages                | Link pages      | Title           | is Show |
      | All products page    | collections/all |                 | false   |
      | All products page    | collections/all | Products        | true    |
      | All collections page | collections     |                 | false   |
      | All collections page | collections     | Collection list | true    |
      | Search page          | search          |                 | false   |
      | Search page          | search          | Search          | true    |

  Scenario: Create URL redirect
    And user click on "URL Redirects" button
    Then verify URL redirect created on dashboard
      | Redirect from   | Redirect to                                | Message               | Error                       | is Correct domain |
      | /invalid-domain | domain sai dinh dang                       | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain-sai dinh-dang                      | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain^sai^dinh^dang                      | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain-sai-định-dạng                      | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain{sai-dinh-dang}                     | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain<sai-dinh-dang>                     | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain\sai-dinh-dang\                     | there is 1 error      | Target is invalid           | false             |
      | /invalid-domain | /domain`sai`dinh`dang                      | there is 1 error      | Target is invalid           | false             |
      | /fake-domain    | /products/garmin-forerunner-220-gps-sports | Your redirect created |                             | true              |
      | /fake-domain    | /products/running-waist-bag                | there is 1 error      | Path has already been taken | false             |
    And verify URL redirect created on SF
      | Redirect from | Redirect to                                |
      | /fake-domain  | /products/garmin-forerunner-220-gps-sports |

  Scenario: Edit URL redirect
    And user click on "URL Redirects" button
    Then verify URL redirect created on dashboard
      | Old redirect | Redirect from   | Redirect to                 | is Correct domain |
      | /fake-domain | /expired-domain | /products/running-waist-bag | true              |
    And verify URL redirect created on SF
      | Redirect from   | Redirect to                 |
      | /expired-domain | /products/running-waist-bag |

  Scenario: Delete URL redirect
    And user click on "URL Redirects" button
    And verify Url redirect deleted on dashboard
      | Redirect from   | Action     |
      | /expired-domain | Delete     |
    And verify URL redirect created on SF
      | Redirect from   | Redirect to |
      | /expired-domain |             |
