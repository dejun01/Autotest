Feature: Onboarding steps for PlusBase
#env = plbase_onboarding_steps

  Scenario: Verify show popup after entered the dashboard of store for the first time
    Given login to hive sbase
    And clear shop data of shopid "@shopId"
    Given user login to shopPlusBase dashboard
    Then Click button "Start now"
    And User add your contact
      | Country | Personnal location | Contact    | Socical profile |
      | Vietnam | Vietnam            | 0983240334 | shopbase.com    |
    And User choose your business type is "Dropshipping" and store type is "PlusBase"
    And User chooses plbase product category
    And User customize store with primary color is "<Primary color>" and font is "<Font>"
    Then Click Skip this step
      | Steps                                                              | Description                                                                                              | Button              |
      | Next step: Find product                                            | Find and import products you want to sell from catalog.                                                  | Find product        |
      | Next step: Add your own domain to strengthen your brand            | Your current domain is @shops@. Add or buy a custom domain to help customers remember your online store. | Add domain          |
      | Next step: Install tracking                                        | Track and optimize all your Facebook or Google Ads campaigns.                                            | Install tracking    |
      | Next step: Customize theme                                         | Choose a theme and add a logo, product slideshow, and colors that match your brand.                      | Customize theme     |
      | Next step: Create a product feed to start your marketing campaigns | Run your Google and Facebook ads by adding a new product feed.                                           | Create product feed |
    And close browser
