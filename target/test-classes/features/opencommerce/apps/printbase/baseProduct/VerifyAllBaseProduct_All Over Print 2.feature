Feature: Create new campaign editor
#   pbase_new_campaign_editor_custom
#  Scenario: Delete all product live
#    When delete all campaigns by API

  Scenario Outline: Create new campaign base product
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                    | Category       |
      | 01  | Short Socks                | All Over Print |
      | 02  | Long Socks                 | All Over Print |
      | 03  | AOP Sweatshirt             | All Over Print |
      | 04  | Kid Sweatshirt             | All Over Print |
      | 05  | AOP Sweater                | All Over Print |
      | 06  | Kid Hoodie                 | All Over Print |
      | 07  | AOP Kid T-Shirt            | All Over Print |
      | 08  | Women's Briefs             | All Over Print |
      | 09  | Men's Boxer Briefs         | All Over Print |
      | 10  | AOP Hawaii Shirt           | All Over Print |
      | 11  | AOP Beach Shorts           | All Over Print |
      | 12  | AOP Long Pants             | All Over Print |
      | 13  | Baseball Jersey            | All Over Print |
      | 14  | Tank Top                   | All Over Print |
      | 15  | Maxi Dress                 | All Over Print |
      | 16  | Hollow Out Tank Top        | All Over Print |
      | 17  | Polo Shirt Black           | All Over Print |
      | 18  | Polo Shirt White           | All Over Print |
      | 19  | New Multi Piece T-Shirt    | All Over Print |
      | 20  | AOP Hoodie                 | All Over Print |
      | 21  | New Multi Piece Hoodie     | All Over Print |
      | 22  | AOP Zip Hoodie             | All Over Print |
      | 23  | New Multi Piece Zip Hoodie | All Over Print |
    And get position base product in editor
    And add layer base product as "<KEY>"
      | KEY | Product                    | Layer type | Layer name                     | Front or back |
      | 01  | Short Socks                | Image      | Short Socks.psd                |               |
      | 02  | Long Socks                 | Image      | Long Socks.psd                 |               |
      | 03  | AOP Sweatshirt             | Image      | AOP Sweatshirt.psd             |               |
      | 04  | Kid Sweatshirt             | Image      | Kid Sweatshirt.psd             |               |
      | 05  | AOP Sweater                | Image      | AOP Sweater.psd                |               |
      | 06  | Kid Hoodie                 | Image      | Kid Hoodie.psd                 |               |
      | 07  | AOP Kid T-Shirt            | Image      | AOP Kid T-Shirt.psd            |               |
      | 08  | Women's Briefs             | Image      | Women's Briefs.psd             |               |
      | 09  | Men's Boxer Briefs         | Image      | Men's Boxer Briefs.psd         |               |
      | 10  | AOP Hawaii Shirt           | Image      | AOP Hawaii Shirt.psd           |               |
      | 11  | AOP Beach Shorts           | Image      | AOP Beach Shorts.psd           |               |
      | 12  | AOP Long Pants             | Image      | AOP Long Pants.psd             |               |
      | 13  | Baseball Jersey            | Image      | Baseball Jersey.psd            |               |
      | 14  | Tank Top                   | Image      | Tank Top.psd                   |               |
      | 15  | Maxi Dress                 | Image      | Maxi Dress.psd                 |               |
      | 16  | Hollow Out Tank Top        | Image      | Hollow Out Tank Top.psd        |               |
      | 17  | Polo Shirt Black           | Image      | Polo Shirt Black.psd           |               |
      | 18  | Polo Shirt White           | Image      | Polo Shirt White.psd           |               |
      | 19  | New Multi Piece T-Shirt    | Image      | New Multi Piece T-Shirt.psd    |               |

      | 20  | AOP Hoodie                 | Image      | AOP Hoodie Front.psd           | Front         |
      | 20  | AOP Hoodie                 | Image      | AOP Hoodie Back.psd            | Back          |

      | 21  | New Multi Piece Hoodie     | Image      | New Multi Piece Hoodie.psd     |               |

      | 22  | AOP Zip Hoodie             | Image      | AOP Zip Hoodie Front.psd       | Front         |
      | 22  | AOP Zip Hoodie             | Image      | AOP Zip Hoodie Back.psd        | Back          |

      | 23  | New Multi Piece Zip Hoodie | Image      | New Multi Piece Zip Hoodie.psd |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                                          |
      | 01  | PB-AOP-ShortSocks-Alloverprint-Onesize       |
      | 02  | PB-AOP-LongSocks-Alloverprint-OneSize        |
      | 03  | PB-AOP-AOPSweatshirt-Alloverprint-S          |
      | 04  | PB-AOP-KidSweatshirt-Alloverprint-S          |
      | 05  | PB-AOP-AOPSweater-Alloverprint-S             |
      | 06  | PB-AOP-KidHoodie-Alloverprint-S              |
      | 07  | PB-AOP-AOPKidT-Shirt-Alloverprint-S          |
      | 08  | PB-AOP-Women'sBriefs-Alloverprint-S          |
      | 09  | PB-AOP-Men'sBoxerBriefs-Alloverprint-S       |
      | 10  | PB-AOP-AOPHawaiiShirt-Alloverprint-S         |
      | 11  | PB-AOP-AOPBeachShorts-Alloverprint-S         |
      | 12  | PB-AOP-AOPLongPants-Alloverprint-S           |
      | 13  | PB-AOP-BaseballJersey-Alloverprint-S         |
      | 14  | PB-AOP-TankTop-Alloverprint-S                |
      | 15  | PB-AOP-MaxiDress-Alloverprint-S              |
      | 16  | PB-AOP-HollowOutTankTop-Alloverprint-S       |
      | 17  | PB-AOP-PoloShirtBlack-Alloverprint-S         |
      | 18  | PB-AOP-PoloShirtWhite-Alloverprint-S         |
      | 19  | PB-AOP-NewMultiPieceT-Shirt-Alloverprint-S   |
      | 20  | PB-AOP-AOPHoodie-Alloverprint-S              |
      | 21  | PB-AOP-NewMultiPieceHoodie-Alloverprint-S    |
      | 22  | PB-AOP-AOPZipHoodie-Alloverprint-S           |
      | 23  | PB-AOP-NewMultiPieceZipHoodie-Alloverprint-S |

    And open product details in dashboard or editor campaign "<Campaign name>"
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color          | Size     |
      | 01  | <Campaign name> | All over print | One size |
      | 02  | <Campaign name> | All over print | One size |
      | 03  | <Campaign name> | All over print | S        |
      | 05  | <Campaign name> | All over print | S        |
      | 06  | <Campaign name> | All over print | S        |
      | 07  | <Campaign name> | All over print | S        |
      | 08  | <Campaign name> | All over print | S        |
      | 09  | <Campaign name> | All over print | S        |
      | 10  | <Campaign name> | All over print | S        |
      | 11  | <Campaign name> | All over print | S        |
      | 12  | <Campaign name> | All over print | S        |
      | 13  | <Campaign name> | All over print | S        |
      | 14  | <Campaign name> | All over print | S        |
      | 15  | <Campaign name> | All over print | S        |
      | 16  | <Campaign name> | All over print | S        |
      | 17  | <Campaign name> | All over print | S        |
      | 18  | <Campaign name> | All over print | S        |
      | 19  | <Campaign name> | All over print | S        |
      | 20  | <Campaign name> | All over print | S        |
      | 21  | <Campaign name> | All over print | S        |
      | 22  | <Campaign name> | All over print | S        |
      | 23  | <Campaign name> | All over print | S        |
    And quit browser
    Examples:
      | KEY | Testcase                              | Campaign name              | Description                | Tags |
      | 01  | Create Cam Short Socks                | Short Socks                | Short Socks                | test |
      | 02  | Create Cam Long Socks                 | Long Socks                 | Long Socks                 | test |
      | 03  | Create Cam AOP Sweatshirt             | AOP Sweatshirt             | AOP Sweatshirt             | test |
      | 04  | Create Cam Kid Sweatshirt             | Kid Sweatshirt             | Kid Sweatshirt             | test |
      | 05  | Create Cam AOP Sweater                | AOP Sweater                | AOP Sweater                | test |
      | 06  | Create Cam Kid Hoodie                 | Kid Hoodie                 | Kid Hoodie                 | test |
      | 07  | Create Cam AOP Kid T-Shirt            | AOP Kid T-Shirt            | AOP Kid T-Shirt            | test |
      | 08  | Create Cam Women's Briefs             | Women's Briefs             | Women's Briefs             | test |
      | 09  | Create Cam Men's Boxer Briefs         | Men's Boxer Briefs         | Men's Boxer Briefs         | test |
      | 10  | Create Cam AOP Hawaii Shirt           | AOP Hawaii Shirt           | AOP Hawaii Shirt           | test |
      | 11  | Create Cam AOP Beach Shorts           | AOP Beach Shorts           | AOP Beach Shorts           | test |
      | 12  | Create Cam AOP Long Pants             | AOP Long Pants             | AOP Long Pants             | test |
      | 13  | Create Cam Baseball Jersey            | Baseball Jersey            | Baseball Jersey            | test |
      | 14  | Create Cam Tank Top                   | Tank Top                   | Tank Top                   | test |
      | 15  | Create Cam Maxi Dress                 | Maxi Dress                 | Maxi Dress                 | test |
      | 16  | Create Cam Hollow Out Tank Top        | Hollow Out Tank Top        | Hollow Out Tank Top        | test |
      | 17  | Create Cam Polo Shirt Black           | Polo Shirt Black           | Polo Shirt Black           | test |
      | 18  | Create Cam Polo Shirt White           | Polo Shirt White           | Polo Shirt White           | test |
      | 19  | Create Cam New Multi Piece T-Shirt    | New Multi Piece T-Shirt    | New Multi Piece T-Shirt    | test |
      | 20  | Create Cam AOP Hoodie                 | AOP Hoodie                 | AOP Hoodie                 | test |
      | 21  | Create Cam New Multi Piece Hoodie     | New Multi Piece Hoodie     | New Multi Piece Hoodie     | test |
      | 22  | Create Cam AOP Zip Hoodie             | AOP Zip Hoodie             | AOP Zip Hoodie             | test |
      | 23  | Create Cam New Multi Piece Zip Hoodie | New Multi Piece Zip Hoodie | New Multi Piece Zip Hoodie | test |





