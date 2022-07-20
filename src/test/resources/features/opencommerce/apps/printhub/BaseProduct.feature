Feature: Verify base product
#dev_pbase_baseproduct
  Background: Login dashboard
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen

  Scenario: Verify orders of campaigns which create before change base cost
    Given click to button "New campaign" on campaign screen
    And add base product to campaign
      | Category | Base product   |
      | Apparel  | Ladies T-shirt |
    And add design to campaign with artwork
      | Base Product   | Color                   | Size | Artwork Front | Artwork back |
      | Ladies T-shirt | White,Black,Sports Grey | S,M  | Campaign1.png |              |
    And add description to campaign
      | Title      | Description      | Show Product Detail | Enable size chart | Tags   |
      | @Campaign1@ | check campaign 1 | true                | false             | ladies |
    And setup price to campaign
      | Base product   | Sale price | Compare at Price |
      | Ladies T-shirt | 30         | 40               |
    And click to button "Launch" on campaign screen
    And user navigate to "All Campaigns" screen
    And verify campaign created on dashboard
    |Campaign name|Base Product|Product Variant|
    |             |            |               |
