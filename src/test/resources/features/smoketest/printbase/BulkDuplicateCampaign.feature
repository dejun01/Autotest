Feature: Smoke test for function Bulk duplicate campaign
  #prod_pbase_smoke_bulk_duplicate_campaign


  Scenario Outline: AU_CP_4.1_ Bulk duplicate campaign
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And bulk duplicate campaign as "<KEY>"
      | KEY   | Campaign name     | Artwork   |
      | <KEY> | <Campaign origin> | <Artwork> |
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-M | 30    |
      | 02  | PB-AP-UnisexTank-White-S | 30    |
      | 02  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style       | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | Unisex Tank | White | M    | 30         | 40               |
      | 02  | <Campaign name> | Unisex Tank | White | S    | 30         | 40               |
      | 02  | <Campaign name> | Unisex Tank | White | S    | 30         | 39.49            |
    And quit browser
    Examples:
      | KEY | Campaign origin      | Campaign name | Artwork  | Testcase                          |
      | 01  | Campaign 2D 2 side   | BD_6          | BD_6.png | Bulk duplicate 2D from 2 side     |
      | 02  | Campaign 2D one size | BD_5          | BD_5.png | Bulk duplicate 2D from 1 side     |
      | 02  | Campaign edit price  | BD_8          | BD_8.png | Bulk duplicate 2D from edit price |
