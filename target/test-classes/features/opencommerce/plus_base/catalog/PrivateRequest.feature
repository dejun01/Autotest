Feature: Private request
  #plusbase_private_request

  Background: Access catalog page
    Given user login to shopbase dashboard
    And user navigate to "plusbase/private-request" screen by url

  Scenario Outline: Verify when input one link request private success
    When Click button "Create product request"
    And Input link request product and verify
      | link                                             | Request link                                     |
      | https://www.aliexpress.com/item/32904343937.html | https://www.aliexpress.com/item/32904343937.html |
    And Search product in private product list by "<KEY>"
      | KEY | product                             |
      | 1   | BONA New Arrival Classics Style Men |
    Then Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                     | link                                             | status     |
      | 1   | BONA New Arrival Classics Style Men Running Shoes Suede Mesh Men Athletic Shoes Outdoor Jogging Shoes Comfortable Free Shipping | https://www.aliexpress.com/item/32904343937.html | Processing |
    And close browser
    Examples: <KEY>
      | KEY |
      | 1   |

  Scenario Outline: Verify when input more link request private success
    When Click button "Create product request"
    And Input link request product and verify
      | KEY | link                                                  | Request link                                          |
      | 2   | https://www.aliexpress.com/item/1005003275056964.html | https://www.aliexpress.com/item/1005003275056964.html |
      | 3   | https://www.aliexpress.com/item/1005003803716733.html | https://www.aliexpress.com/item/1005003803716733.html |
    And Search product in private product list by "<KEY>"
      | KEY | product                                |
      | 2   | Women's Pajamas Set Autumn And Winter  |
      | 3   | Sexy Sheath Women's Dress Black Halter |
    Then Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                      | link                                                  | status     |
      | 2   | Women's Pajamas Set Autumn And Winter Flannel Warm Women's Pajamas Long-Sleeved Trouser Suits Women's Pajamas Cute Home Service  | https://www.aliexpress.com/item/1005003275056964.html | Processing |
      | 3   | Sexy Sheath Women's Dress Black Halter Sleeveless Club Wear Bodycon Solid Mini Vestidos Woman Dresses Summer Clothes For Dresses | https://www.aliexpress.com/item/1005003803716733.html | Processing |
    And close browser
    Examples: <KEY>
      | KEY |
      | 2   |
      | 3   |

  Scenario Outline: Verify redirect to private detail when click on product name with product have status = Available
    When Click button "Create product request"
    And Input link request product and verify
      | link                                                  | Request link                                          |
      | https://www.aliexpress.com/item/1005002895221104.html | https://www.aliexpress.com/item/1005002895221104.html |
    And click "Available" tab in private request list
    And Search product in private product list by "<KEY>"
      | KEY | product                                                 |
      | 4   | (Test product)17cm One Piece Anime Figure Four Emperors |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                                 | link                                                  | status    |
      | 4   | (Test product)17cm One Piece Anime Figure Four Emperors Shanks Straw Hat Luffy Action Figure One Piece Sabo Ace Sanji Roronoa Zoro Figurine | https://www.aliexpress.com/item/1005002895221104.html | Available |
    Then CLick on product name and verify button Import to your store
      | ProductName                                                                                                                                 | Link                                                  | BTImportToStore      | Status | Variant                                         | Processing time | Product cost |
      | (Test product)17cm One Piece Anime Figure Four Emperors Shanks Straw Hat Luffy Action Figure One Piece Sabo Ace Sanji Roronoa Zoro Figurine | https://www.aliexpress.com/item/1005002895221104.html | Import to your store | Enable | Select variants to preview product cost & image | 1 day           | $1.00        |
    And close browser
    Examples: <KEY>
      | KEY |
      | 4   |

  Scenario Outline: Verify redirect to private detail when click on product name with product have status = Processing
    When Click button "Create product request"
    And Input link request product and verify
      | link                                                  | Request link                                          |
      | https://www.aliexpress.com/item/1005002166279243.html | https://www.aliexpress.com/item/1005002166279243.html |
    And Search product in private product list by "<KEY>"
      | KEY | product                               |
      | 5   | Vintage Floral Elephant Print T-Shirt |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                     | link                                                  | status     |
      | 5   | Vintage Floral Elephant Print T-Shirt Oversize Women T-Shirts Harajuku Streetwear Short Sleeve T-Shirt Aesthetic Women Pulovers | https://www.aliexpress.com/item/1005002166279243.html | Processing |
    And close browser
    Examples: <KEY>
      | KEY |
      | 5   |

  Scenario Outline: Verify product is Expired
    And click "Expired" tab in private request list
    And Search product in private product list by "<KEY>"
      | KEY | product                                 |
      | 6   | (Test) sweater women turtleneck leopard |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                      | link                                               | status  |
      | 6   | (Test) sweater women turtleneck leopard knitted sweater animal print winter thick female pullovers casual tops oversized sweater | https://www.aliexpress.com/item/4000155061299.html | Expired |
    Then CLick on product name and verify button Import to your store
      | ProductName                                                                                                                      | Link                                               | BTImportToStore | Status  | Variant                                         | Processing time | Product cost |
      | (Test) sweater women turtleneck leopard knitted sweater animal print winter thick female pullovers casual tops oversized sweater | https://www.aliexpress.com/item/4000155061299.html | disabled        | Expired | Select variants to preview product cost & image | 1 days          | $1.00        |
    Examples: <KEY>
      | KEY |
      | 6   |

  Scenario Outline: Verify product is No Result
    And click "No Result" tab in private request list
    And Search product in private product list by "<KEY>"
      | KEY | product                          |
      | 7   | CNYISHE 2021 Autumn Flare Sleeve |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                      | link                                                  | status    |
      | 7   | CNYISHE 2021 Autumn Flare Sleeve Women Bodycon Dress Female Sexy V-cut Tight Party Dresses Casual Elegant Sundress Vestidos Robe | https://www.aliexpress.com/item/1005003207683493.html | No Result |
    Then CLick on product name and verify button Import to your store
      | ProductName                                                                                                                      | Link                                                  | BTImportToStore | Status    | Variant                                         | Processing time | Product cost |
      | CNYISHE 2021 Autumn Flare Sleeve Women Bodycon Dress Female Sexy V-cut Tight Party Dresses Casual Elegant Sundress Vestidos Robe | https://www.aliexpress.com/item/1005003207683493.html | disabled        | No Result | Select variants to preview product cost & image | 1 days          | $1.00        |
    And close browser
    Examples: <KEY>
      | KEY |
      | 7   |

  Scenario Outline: Verify import to store success if product in status: Available
    And user navigate to "products" screen by url
    And Search product "(Test product)17cm One Piece Anime Figure Four Emperors Shanks Straw Hat Luffy Action Figure One Piece Sabo Ace Sanji Roronoa Zoro Figurine" and get quantity product
    And user navigate to "plusbase/private-request" screen by url
    When Click button "Create product request"
    And Input link request product and verify
      | link                                                  | Request link                                          |
      | https://www.aliexpress.com/item/1005002895221104.html | https://www.aliexpress.com/item/1005002895221104.html |
    And user navigate to "plusbase/private-request" screen by url
    And click "Available" tab in private request list
    And Search product in private product list by "<KEY>"
      | KEY | product                                        |
      | 8   | (Test product)17cm One Piece Anime Figure Four |
    And Verify info product after request success by "<KEY>"
      | KEY | ProductName                                                                                                                                 | link                                                  | status    |
      | 8   | (Test product)17cm One Piece Anime Figure Four Emperors Shanks Straw Hat Luffy Action Figure One Piece Sabo Ace Sanji Roronoa Zoro Figurine | https://www.aliexpress.com/item/1005002895221104.html | Available |
    And CLick on product name and verify button Import to your store
      | ProductName                                                                                                                                 | Link                                                  | BTImportToStore      | Status    | Variant                                         | Processing time | Product cost |
      | (Test product)17cm One Piece Anime Figure Four Emperors Shanks Straw Hat Luffy Action Figure One Piece Sabo Ace Sanji Roronoa Zoro Figurine | https://www.aliexpress.com/item/1005002895221104.html | Import to your store | Available | Select variants to preview product cost & image | 1 days          | $1.00        |
    And Import to store
    Then Verify product after in import success
      | Product                                               |
      | (Test product)17cm One Piece Anime Figure Four Emp... |
    And user navigate to "products" screen by url
    Then Search product and verify display product imported
    And close browser
    Examples: <KEY>
      | KEY |
      | 8   |