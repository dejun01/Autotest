Feature: verify widget race challenge in Homepage

  Scenario: Verify widget race challenge show in dashboard
    Given login and create shop
      | Shop name           | Country | Personal location | Phone       | Business type   | Store type              | Product Category               | Customize your store            |
      |widget race challenge| Vietnam | Vietnam           | 098 1111111 | Print-on-demand | I want a ShopBase store | No thanks, I will decide later | No thanks, don't want to import |
    And verify widget race challenge in dashboard
