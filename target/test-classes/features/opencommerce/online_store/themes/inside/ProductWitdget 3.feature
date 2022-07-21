Feature: Verify product widgets
    # sbase_inside_settings2
    #prod_sbase_inside_settings2
    #staging_sbase_inside_settings2
    #dev_sbase_inside_settings2

  #  Data test:
#  Product:Shirt

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme

  Scenario: Verify product widgets on Home page #SB_OLS_THE_INS_16
    When choose preview page "Homepage"
    And verify product widgets on Homepage Theme editor
    Then open shop on storefront
    And add products "Shirt" to cart
#    And verify product widgets on Homepage on SF

  Scenario: Verify product widgets on Product page #SB_OLS_THE_INS_19
    When choose preview page "Product page"
    And verify product widgets on Product page Theme editor
    Then open shop on storefront
    And add products "Shirt" to cart
    And search and select the product "Shirt"
#    And verify product widgets on Product page on SF

  Scenario: Verify product widgets on Collection page #SB_OLS_THE_INS_29
    When choose preview page "Collection pages"
    And verify product widgets on Collection pages Theme editor
    Then open shop on storefront
    And add products "Shirt" to cart
    Then open page "/collections/collection-shirt"
#    And verify product widgets on Collection pages on SF

  Scenario: Verify product widgets on Cart page #SB_OLS_THE_INS_21
    When choose preview page "Cart page"
    And verify product widgets on Cart page Theme editor
    Then open shop on storefront
    And add products "Shirt" to cart
#    And verify product widgets on Cart page on SF


