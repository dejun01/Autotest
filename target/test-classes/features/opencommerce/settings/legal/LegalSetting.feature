Feature: Setting Legal
  #enva: sbase_manage_theme

  Scenario Outline: Check the content of page when update store name #SB_OLS_NVG_12 #SB_OLS_NVG_13
    Given user login to shopbase dashboard by API
    And user navigate to "Settings" screen
    And Click to "Legal" section at Settings screen
    And  input data for page policy "<KEY>"
      | KEY | page              | data                         |
      | 1   | Refund policy     | Refund policy. Store name    |
      | 1   | Privacy policy    | Privacy policy. Store name   |
      | 1   | Terms of Services | Terms of service. Store name |
      | 1   | Shipping policy   | Shipping policy. Store name  |
      | 2   | Refund policy     | template                     |
      | 2   | Privacy policy    | template                     |
      | 2   | Terms of Services | template                     |
      | 2   | Shipping policy   | template                     |

    And verify content of pages on storefront "<KEY>"
      | KEY | page             | type     | content                      |
      | 1   | Refund Policy    | config   | Refund policy. Store name    |
      | 1   | Privacy Policy   | config   | Privacy policy. Store name   |
      | 1   | Terms Of Service | config   | Terms of service. Store name |
      | 1   | Shipping Policy  | config   | Shipping policy. Store name  |
      | 2   | Refund Policy    | template | RETURN                       |
      | 2   | Privacy Policy   | template | This Privacy Policy          |
      | 2   | Terms Of Service | template | OVERVIEW                     |
      | 2   | Shipping Policy  | template | HOW MUCH DOES SHIPPING COST? |
    And quit browser
    Examples:
      | KEY |
      | 1   |
      | 2   |

