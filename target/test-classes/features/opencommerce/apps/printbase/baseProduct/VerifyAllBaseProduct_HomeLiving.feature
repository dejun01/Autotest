Feature: Create new campaign editor
#   pbase_new_campaign_editor_custom
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign base product
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product              | Category      |
      | 01  | Star Ornament        | Home & Living |
      | 02  | Round Ornament       | Home & Living |
      | 03  | Heart Ornament       | Home & Living |
      | 04  | Bath Mat             | Home & Living |
      | 05  | Door Mat             | Home & Living |
      | 06  | Moon Lamp            | Home & Living |
      | 07  | Sticker              | Home & Living |
      | 08  | Pillow Case Cover    | Home & Living |
      | 09  | Landscape Canvas     | Home & Living |
      | 10  | Area Rug             | Home & Living |
      | 11  | Hooded Blanket       | Home & Living |
      | 12  | Landscape Poster     | Home & Living |
      | 13  | Portrait Poster      | Home & Living |
      | 14  | Porch Banner         | Home & Living |
      | 15  | Portrait House Flag  | Home & Living |
      | 16  | Landscape House Flag | Home & Living |
      | 17  | Portrait Canvas      | Home & Living |
      | 18  | Heart Puzzle         | Home & Living |
      | 19  | Quilt                | Home & Living |
      | 20  | Sherpa Blanket       | Home & Living |
      | 21  | Square Canvas        | Home & Living |
      | 22  | Bedding Set          | Home & Living |
      | 23  | Landscape Puzzle     | Home & Living |
      | 24  | Portrait Puzzle      | Home & Living |
      | 25  | Round Carpet         | Home & Living |
      | 26  | Shower Curtain       | Home & Living |
    And get position base product in editor
    And add layer base product as "<KEY>"
      | KEY | Product              | Layer type | Layer name               | Front or back |
      | 01  | Star Ornament        | Image      | Star Ornament.psd        |               |
      | 02  | Round Ornament       | Image      | Round Ornament.psd       |               |
      | 03  | Heart Ornament       | Image      | Heart Ornament.psd       |               |
      | 04  | Bath Mat             | Image      | Bath Mat.psd             |               |
      | 05  | Door Mat             | Image      | Door Mat.psd             |               |
      | 06  | Moon Lamp            | Image      | Moon Lamp.psd            |               |
      | 07  | Sticker              | Image      | Sticker.psd              |               |
      | 08  | Pillow Case Cover    | Image      | Pillow Case Cover.psd    |               |
      | 09  | Landscape Canvas     | Image      | Landscape Canvas.psd     |               |
      | 10  | Area Rug             | Image      | Area Rug.psd             |               |
      | 11  | Hooded Blanket       | Image      | Hooded Blanket.psd       |               |
      | 12  | Landscape Poster     | Image      | Landscape Poster.psd     |               |
      | 13  | Portrait Poster      | Image      | Portrait Poster.psd      |               |
      | 14  | Porch Banner         | Image      | Porch Banner.psd         |               |
      | 15  | Portrait House Flag  | Image      | Portrait House Flag.psd  |               |
      | 16  | Landscape House Flag | Image      | Landscape House Flag.psd |               |
      | 17  | Portrait Canvas      | Image      | Portrait Canvas.psd      |               |
      | 18  | Heart Puzzle         | Image      | Heart Puzzle.psd         |               |
      | 19  | Quilt                | Image      | Quilt.psd                |               |
      | 20  | Sherpa Blanket       | Image      | Sherpa Blanket.psd       |               |
      | 21  | Square Canvas        | Image      | Square Canvas.psd        |               |
      | 22  | Bedding Set          | Image      | Bedding Set.psd          |               |
      | 23  | Landscape Puzzle     | Image      | Landscape Puzzle.psd     |               |
      | 24  | Portrait Puzzle      | Image      | Portrait Puzzle.psd      |               |
      | 25  | Round Carpet         | Image      | Round Carpet.psd         |               |
      | 26  | Shower Curtain       | Image      | Shower Curtain.psd       |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
#    And search product or campaign or orders "<Campaign name>" at list page in dashboard
#    And open product details in dashboard or editor campaign "<Campaign name>"
#    And verify product information in dashboard
#      | Tags   | Description   |
#      | <Tags> | <Description> |
#    And verify variant information of product or campaign details in dashboard as "<KEY>"
#      | KEY | SKU                                              |
#      | 01  | PB-AOP-StarOrnament-Alloverprint-1pcs            |
#      | 02  | PB-AOP-RoundOrnament-Alloverprint-1pcs           |
#      | 03  | PB-AOP-HeartOrnament-Alloverprint-1pcs           |
#      | 04  | PB-AOP-BathMat-Alloverprint-Medium               |
#      | 05  | PB-AOP-DoorMat-Alloverprint-Onesize              |
#      | 06  | PB-AP-MoonLamp-White-4inch                       |
#      | 07  | PB-AOP-Sticker-Glossy-1pcs                       |
#      | 08  | PB-AOP-PillowCaseCover-Alloverprint-Onesize      |
#      | 09  | PB-AOP-LandscapeCanvas-White-12x8in              |
#      | 10  | PB-AOP-AreaRug-Alloverprint-Small                |
#      | 11  | PB-AOP-HoodedBlanket-Alloverprint-Youth          |
#      | 12  | PB-AOP-LandscapePoster-Alloverprint-S            |
#      | 13  | PB-AOP-PortraitPoster-Alloverprint-S             |
#      | 14  | PB-AOP-PorchBanner-Alloverprint-1set             |
#      | 15  | PB-AOP-PortraitHouseFlag-Alloverprint-30x40inch  |
#      | 16  | PB-AOP-LandscapeHouseFlag-Alloverprint-40x30inch |
#      | 17  | PB-AOP-PortraitCanvas-White-8x12in               |
#      | 18  | PB-AOP-HeartPuzzle-Alloverprint-Onesize          |
#      | 19  | PB-AOP-Quilt-Alloverprint-Single                 |
#      | 20  | PB-AOP-SherpaBlanket-Alloverprint-Large          |
#      | 21  | PB-AOP-SquareCanvas-White-8x8in                  |
#      | 22  | PB-AOP-BeddingSet-Alloverprint-Twin              |
#      | 23  | PB-AOP-LandscapePuzzle-Alloverprint-300pieces    |
#      | 24  | PB-AOP-PortraitPuzzle-Alloverprint-300pieces     |
#      | 25  | PB-AOP-RoundCarpet-Alloverprint-Small            |
#      | 26  | PB-AOP-ShowerCurtain-Alloverprint-Small          |
#    And open product details on Storefront from product detail in dashboard as "<KEY>"
#      | KEY |
#      | 01  |
#      | 02  |
#      | 03  |
#      | 04  |
#      | 05  |
#      | 06  |
#      | 07  |
#      | 08  |
#      | 09  |
#      | 10  |
#      | 11  |
#      | 12  |
#      | 13  |
#      | 14  |
#      | 15  |
#      | 16  |
#      | 17  |
#      | 18  |
#      | 19  |
#      | 20  |
#      | 21  |
#      | 22  |
#      | 23  |
#      | 24  |
#      | 25  |
#      | 26  |
#    #    And open product details on Storefront from product detail in dashboard
#    Then verify product information from pod on storefront as "<KEY>"
#      | KEY | Product name    | Color          | Size       |
#      | 01  | <Campaign name> | All over print | 1 pcs      |
#      | 02  | <Campaign name> | All over print | 1 pcs      |
#      | 03  | <Campaign name> | All over print | 1 pcs      |
#      | 04  | <Campaign name> | All over print | Medium     |
#      | 05  | <Campaign name> | All over print | One size   |
#      | 06  | <Campaign name> | White          | 4 inch     |
#      | 07  | <Campaign name> | Glossy         | 1 pcs      |
#      | 08  | <Campaign name> | All over print | One size   |
#      | 09  | <Campaign name> | White          | 12x8in     |
#      | 10  | <Campaign name> | All over print | Small      |
#      | 11  | <Campaign name> | All over print | Youth      |
#      | 12  | <Campaign name> | All over print | S          |
#      | 13  | <Campaign name> | All over print | S          |
#      | 14  | <Campaign name> | All over print | 1 set      |
#      | 15  | <Campaign name> | All over print | 30x40 inch |
#      | 16  | <Campaign name> | All over print | 40x30 inch |
#      | 17  | <Campaign name> | White          | 8x12 in    |
#      | 18  | <Campaign name> | All over print | One size   |
#      | 19  | <Campaign name> | All over print | Single     |
#      | 20  | <Campaign name> | All over print | Large      |
#      | 21  | <Campaign name> | White          | 8x8in      |
#      | 22  | <Campaign name> | All over print | Twin       |
#      | 23  | <Campaign name> | All over print | 300 pieces |
#      | 24  | <Campaign name> | All over print | 300 pieces |
#      | 25  | <Campaign name> | All over print | Small      |
#      | 26  | <Campaign name> | All over print | Small      |
    And quit browser
    Examples:
      | KEY | Campaign name        | Description          | Tags |
#      | 01  |  Star Ornament        | Star Ornament        | test |
#      | 02  |  Round Ornament       | Round Ornament       | test |
      | 03  |  Heart Ornament       | Hert Ornament        | test |
      | 04  |  Bath Mat             | Bath Mat             | test |
      | 05  | Door Mat New         | Door Mat New         | test |
      | 06  | Moon Lamp            | Moon Lamp            | test |
      | 07  | Sticker              | Sticker              | test |
      | 08  | Pillow Case Cover    | Pillow Case Cover    | test |
      | 09  | Landscape Canvas     | Landscape Canvas     | test |
      | 10  | Area Rug             | Area Rug             | test |
      | 11  | Hooded Blanket       | Hooded Blanket       | test |
      | 12  | Landscape Poster     | Landscape Poster     | test |
      | 13  | Portrait Poster      | Portrait Poster      | test |
      | 14  | Porch Banner         | Porch Banner         | test |
      | 15  | Portrait House Flag  | Portrait House Flag  | test |
      | 16  | Landscape House Flag | Landscape House Flag | test |
      | 17  | Portrait Canvas      | Portrait Canvas      | test |
      | 18  | Heart Puzzle         | Heart Puzzle         | test |
      | 19  | Quilt                | Quilt                | test |
      | 20  | Sherpa Blanket       | Sherpa Blanket       | test |
      | 21  | Square Canvas        | Square Canvas        | test |
      | 22  | Bedding Set          | Bedding Set          | test |
      | 23  | Landscape Puzzle     | Landscape Puzzle     | test |
      | 24  | Portrait Puzzle      | Portrait Puzzle      | test |
      | 25  | Round Carpet         | Round Carpet         | test |
      | 26  | Shower Curtain       | Shower Curtain       | test |




