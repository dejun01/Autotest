Feature: check denmensions campaign
 #environment": prod_pbase_check_demensions_campaign

  Scenario Outline: check denmensions template of the base product
    And dowload template campaign as "<KEY>"
      | KEY   | Title   |
      | <KEY> | <Title> |
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY   | Product   | Category   |
      | <KEY> | <Product> | <Category> |

    And add artwork to campaign as "<KEY>"
      | KEY   | Title   |
      | <KEY> | <Title> |
    Then verify demension artwork with message "Print quality: High"


    Examples: <KEY>
      | KEY | Product                    | Title                      | Category       |
      | 1   | Short Socks                | Short Socks                | All Over Print |
      | 2   | Long Socks                 | Long Socks                 | All Over Print |
      | 3   | AOP Sweatshirt             | AOP Sweatshirt             | All Over Print |
      | 4   | Kid Sweatshirt             | Kid Sweatshirt             | All Over Print |
      | 5   | AOP Sweater                | AOP Sweater                | All Over Print |
      | 6   | Kid Hoodie                 | Kid Hoodie                 | All Over Print |
      | 7   | AOP Kid T-Shirt            | AOP Kid T-Shirt            | All Over Print |
      | 8   | Women's Briefs             | Women's Briefs             | All Over Print |
      | 9   | Men's Boxer Briefs         | Men's Boxer Briefs         | All Over Print |
      | 10  | Short Sleeve Pyjama        | Short Sleeve Pyjama        | All Over Print |
      | 11  | AOP Hawaii Shirt           | AOP Hawaii Shirt           | All Over Print |
      | 12  | AOP Beach Shorts           | AOP Beach Shorts           | All Over Print |
      | 13  | AOP Long Pants             | AOP Long Pants             | All Over Print |
#      | 14  | Leggings                   | Leggings                   | All Over Print | temaplate lỗi
      | 15  | Baseball Jersey            | Baseball Jersey            | All Over Print |
      | 16  | Tank Top                   | Tank Top                   | All Over Print |
      | 17  | Maxi Dress                 | Maxi Dress                 | All Over Print |
      | 18  | Hollow Out Tank Top        | Hollow Out Tank Top        | All Over Print |
      | 19  | Polo Shirt Black           | Polo Shirt Black           | All Over Print |
#      | 20  | Polo Shirt White           | Polo Shirt White           | All Over Print |
#      | 21  | AOP T-Shirt                | AOP T-Shirt                | All Over Print |temaplate lỗi
      | 22  | New Multi Piece T-Shirt    | New Multi Piece T-Shirt    | All Over Print |
      | 23  | AOP Hoodie                 | AOP Hoodie                 | All Over Print |
      | 24  | New Multi Piece Hoodie     | New Multi Piece Hoodie     | All Over Print |
      | 25  | AOP Zip Hoodie             | AOP Zip Hoodie             | All Over Print |
      | 26  | New Multi Piece Zip Hoodie | New Multi Piece Zip Hoodie | All Over Print |
      | 27  | 3/4 Sleeve Raglan Tee      | 3/4 Sleeve Raglan Tee      | Apparel        |
      | 28  | Baby One Piece             | Baby One Piece             | Apparel        |
      | 29  | Long Sleeve Baby One Piece | Long Sleeve Baby One Piece | Apparel        |
      | 30  | Hollow Out Tank Top 2      | Hollow Out Tank Top 2      | Apparel        |
      | 31  | Star Ornament              | Star Ornament              | Home & Living  |
      | 32  | Round Ornament             | Round Ornament             | Home & Living  |
      | 33  | Heart Ornament             | Heart Ornament             | Home & Living  |
      | 34  | Bath Mat                   | Bath Mat                   | Home & Living  |
#      | 35  | Fleece Blanket          | Fleece Blanket          | Home & Living |temaplate lỗi
      | 36  | Door Mat                   | Door Mat                   | Home & Living  |
      | 37  | Moon Lamp                  | Moon Lamp                  | Home & Living  |
#      | 38  | Quilt Bedding Set          | Quilt Bedding Set          | Home & Living |
      | 39  | Sticker                    | Sticker                    | Home & Living  |
      | 40  | Pillow Case Cover          | Pillow Case Cover          | Home & Living  |
      | 41  | Landscape Canvas           | Landscape Canvas           | Home & Living  |
      | 42  | Area Rug                   | Area Rug                   | Home & Living  |
      | 43  | Hooded Blanket             | Hooded Blanket             | Home & Living  |
      | 44  | Landscape Poster           | Landscape Poster           | Home & Living  |
      | 45  | Portrait Poster            | Portrait Poster            | Home & Living  |
      | 46  | Porch Banner               | Porch Banner               | Home & Living  |
      | 47  | Portrait House Flag        | Portrait House Flag        | Home & Living  |
      | 48  | Landscape House Flag       | Landscape House Flag       | Home & Living  |
      | 49  | Portrait Canvas            | Portrait Canvas            | Home & Living  |
      | 50  | Heart Puzzle               | Heart Puzzle               | Home & Living  |
      | 51  | Quilt                      | Quilt                      | Home & Living  |
      | 52  | Sherpa Blanket             | Sherpa Blanket             | Home & Living  |
      | 53  | Square Canvas              | Square Canvas              | Home & Living  |
      | 54  | Bedding Set                | Bedding Set                | Home & Living  |
      | 55  | Landscape Puzzle           | Landscape Puzzle           | Home & Living  |
      | 56  | Portrait Puzzle            | Portrait Puzzle            | Home & Living  |
      | 57  | Round Carpet               | Round Carpet               | Home & Living  |
      | 58  | Shower Curtain             | Shower Curtain             | Home & Living  |
      | 59  | Sleep Mask                 | Sleep Mask                 | Accessories    |
      | 60  | Heart Necklace With Box    | Heart Necklace With Box    | Accessories    |
      | 61  | Mouse Pad                  | Mouse Pad                  | Accessories    |
      | 62  | Wooden Keyring Gift        | Wooden Keyring Gift        | Accessories    |
      | 63  | Large Mouse Pad            | Large Mouse Pad            | Accessories    |
      | 64  | Face Mask                  | Face Mask                  | Accessories    |
      | 65  | Cotton Face Mask           | Cotton Face Mask           | Accessories    |
      | 66  | Neck Gaiter New            | Neck Gaiter New            | Accessories    |
      | 67  | Leather Boots New          | Leather Boots New          | Shoes          |
      | 68  | Slip On Shoes              | Slip On Shoes              | Shoes          |
      | 69  | YZY Shoes                  | YZY Shoes                  | Shoes          |
      | 70  | Low Top Shoes              | Low Top Shoes              | Shoes          |
      | 71  | Shining Tumbler 20oz       | Shining  Tumbler  20oz     | Drinkware      |
      | 72  | Tracker Bottle             | Tracker Bottle             | Drinkware      |
      | 73  | New Tumbler 30oz           | New Tumbler 30oz           | Drinkware      |
      | 74  | Two-sided Mug New          | Two-sided Mug New          | Drinkware      |
      | 75  | Beverage Mug               | Shining  Tumbler  20oz     | Drinkware      |
      | 76  | Tumbler 20oz               | Tumbler 20oz               | Drinkware      |
      | 77  | Skinny Tumbler             | Skinny Tumbler             | Drinkware      |
      | 78  | iPhone 12 Case             | iPhone 12 Case             | Phone Cases    |
      | 79  | iPhone 12 Pro Case         | iPhone 12 Case             | Phone Cases    |
      | 80  | iPhone 12 Pro Max Case     | iPhone 12 Pro Max Case     | Phone Cases    |
      | 81  | iPhone 11 Case             | iPhone 11 Case             | Phone Cases    |
      | 82  | iPhone 11 Pro Case         | iPhone 11 Pro Case         | Phone Cases    |
      | 83  | iPhone 11 Pro Max Case     | iPhone 11 Pro Max Case     | Phone Cases    |


