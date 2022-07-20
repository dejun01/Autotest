Feature: Shipping fee
  ##shipping_fee

  Scenario Outline: Shipping fee of campaign in Catalog
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    And verify shipping fee in catalog screen as "<KEY>"
      | KEY | Product        | Category       | Show price with shipping | Shipping address | Shipping |
      | 01  | Ladies T-shirt | Apparel        | true                     | US               | 10.78   |
      | 01  | AOP Hoodie     | All Over Print | true                     | US               | 22.98    |
      | 02  | Ladies T-shirt | Apparel        | true                     | International    | 14.28    |
      | 02  | AOP Hoodie     | All Over Print | true                     | International    | 25.48    |

    And close browser
    Examples:
      | KEY | Testcase                                         |
      | 01  | Verify show price with shipping is US            |
      | 02  | Verify show price with shipping is International |

  Scenario Outline: Verify shipping fee in pricing
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category       |
      | 01  | Ladies T-shirt | Apparel        |
      | 01  | AOP Hoodie     | All Over Print |
      | 02  | Ladies T-shirt | Apparel        |
      | 02  | AOP Hoodie     | All Over Print |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name | Color | Front or back |
      | 01  | Ladies T-shirt | Text       |            | White | Front         |
      | 01  | AOP Hoodie     | Image      | 39.png     |       | Back          |
      | 01  | AOP Hoodie     | Text       |            |       | Front         |
      | 02  | Ladies T-shirt | Text       |            | White | Front         |
      | 02  | AOP Hoodie     | Image      | 39.png     |       | Back          |
      | 02  | AOP Hoodie     | Text       |            |       | Front         |
    And choose shipping zones as "<KEY>"
      | KEY | With shipping fee to | Address       |
      | 01  | true                 | US            |
      | 02  | true                 | International |
    And verify shipping fee in pricing of variant as "<KEY>"
      | KEY | Product        | Variant                           | Shipping fee |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - XS       | 5.99         |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - S        | 5.99         |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - M        | 5.99         |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - L        | 5.99         |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - XL       | 5.99         |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - 2XL      | 5.99         |
      | 01  | Ladies T-shirt | Ladies T-shirt - White - 3XL      | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - S   | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - M   | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - L   | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - XL  | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - 2XL | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - 3XL | 6.99         |
      | 01  | AOP Hoodies    | AOP Hoodie - All over print - 4XL | 6.99         |

      | 02  | Ladies T-shirt | Ladies T-shirt - White - XS       | 8.99         |
      | 02  | Ladies T-shirt | Ladies T-shirt - White - S        | 8.99         |
      | 02  | Ladies T-shirt | Ladies T-shirt - White - M        | 8.99         |
      | 02  | Ladies T-shirt | Ladies T-shirt - White - L        | 8.99         |
      | 02  | Ladies T-shirt | Ladies T-shirt - White - XL       | 8.99         |
      | 02  | Ladies T-shirt | Ladies T-shirt - White - 2XL      | 8.99         |
      | 02  | Ladies T-shirt | Ladies T-shirt - White - 3XL      | 8.99         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - S   | 9.49         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - M   | 9.49         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - L   | 9.49         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - XL  | 9.49         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - 2XL | 9.49         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - 3XL | 9.49         |
      | 02  | AOP Hoodies    | AOP Hoodie - All over print - 4XL | 9.49         |

    Examples:
      | KEY | Testcase                                         |
      | 01  | Verify show price with shipping is US            |
      | 02  | Verify show price with shipping is International |