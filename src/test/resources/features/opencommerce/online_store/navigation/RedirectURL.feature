Feature: Redirect URL
  #sbase_redirect_url

  Background:
    Given user login to shopbase dashboard by API

  Scenario: verify create redirect URL product #SB_OLS_RDU_14 #SB_OLS_RDU_15 #SB_OLS_RDU_16
    And user navigate to "Products" screen
    Then user change url product "redirect" and check redirect on Storefront
      | Create a URL redirect |
      | true                  |
      | false                 |

  Scenario: verify create redirect URL collection #SB_OLS_RDU_17 #SB_OLS_RDU_18 #SB_OLS_RDU_19
    And user navigate to "Products>Collections" screen
    Then user change url collection "collections" and check redirect on Storefront
      | Create a URL redirect |
      | true                  |
      | false                 |

  Scenario: verify create redirect URL pages #SB_OLS_RDU_20 #SB_OLS_RDU_21 #SB_OLS_RDU_22
    And user navigate to "Online Store>Pages" screen
    Then user change url pages "about" and check redirect on Storefront
      | Create a URL redirect |
      | true                  |
      | false                 |

  Scenario:  check edit redirect on URL redirect URL page #SB_OLS_RDU_23 #SB_OLS_RDU_24 #SB_OLS_RDU_25 #SB_OLS_RDU_26 #SB_OLS_RDU_27 #SB_OLS_RDU_28 #SB_OLS_RDU_29
    And user navigate to "Online Store>Navigation" screen
    Then user edit redirect on URL redirect URL page and check apply on Storefornt
    And user create new redirect URL on URL redirect page
      | Create a URL redirect                    |
      | /products/shirt                          |
      | /search?sort=created_at&q=white20t-shirt |
    And  verify redirect product with product handle
      | Case  | Product handle | Expected       |
      | true  | dress          | Redirect       |
      | false | dressabc       | Page not found |



