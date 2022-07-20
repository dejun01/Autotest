Feature: HomePage - Inside Theme - Featured Product, Gallery Image section

#prod_inside_featuredproduct
#staging_inside_featuredproduct
#dev_inside_featuredproduct

  Background:
    Given user login to shopbase dashboard
    And user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

  Scenario Outline: Verify Feature product #SB_OLS_THE_INS_6
    And change Feature product settings as "<KEY>"
      | KEY | Product name          | Product image alignment | Show full product description |
      | 1   | MC White Lace Dress   | Left                    | true                          |
      | 2   | MC Velvet Party Dress | Right                   | false                         |
    Then open shop on storefront
    And verify Feature product settings as "<KEY>"
      | KEY | Product name          | Product image alignment | Show full product description |
      | 1   | MC White Lace Dress   | Left                    | true                          |
      | 2   | MC Velvet Party Dress | Right                   | false                         |
    And verify add to cart product name as "<ProductAddCart>"
    And verify buy now product name as "<ProductBuynow>"
    Examples:
      | KEY | ProductAddCart      | ProductBuynow         |
      | 1   | MC White Lace Dress |                       |
      | 2   |                     | MC Velvet Party Dress |

  Scenario Outline: Verify Gallery Image section #SB_OLS_THE_INS_5
    When change Gallery Image settings theme inside as "<KEY>"
      | KEY | Headline               | Content alignment | Full width section | No spacing | Image                   | Alt text        |
      | 1   | Headline Gallery Image | Left              | true               | true       | front/Galleryimage.jpeg | Gallery Image 1 |
      | 2   | Gallery Image          | Center            | false              | false      |                         |                 |
    Then open shop on storefront
    And verify Gallery Image settings as "<KEY>"
      | KEY | Headline               | Content alignment | Full width section | No spacing | Image                   | Alt text        |
      | 1   | Headline Gallery Image | Left              | true               | true       | front/Galleryimage.jpeg | Gallery Image 1 |
      | 2   | Gallery Image          | Center            | false              | false      |                         |                 |
    Examples:
      | KEY |
      | 1   |
      | 2   |