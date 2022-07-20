Feature: As a merchant, I want to manage orders of my store. Orders need have status and info orders correctly
#environment: prod_order_campaign_fulfillment
  Scenario Outline: Fulfillment orders campaign in production enviroment #SB_PRB_CL_107
    Given open shop on storefront
    And add product with custom option to cart then checkout as "<KEY>"
      | KEY | Product              | Custom option                             |
      | 1   | Iphone 12            |                                           |
      | 2   | Beverage mug         |                                           |
      | 3   | Face mark            |                                           |
      | 4   | Quilt                |                                           |
      | 5   | Unisex t-shirt       | Text,Text,hihi haha;Image,Iamge,BD_41.png |
      | 6   | Tank top             | Text,Text 1,hihi haha                     |
      | 7   | Shining Tumbler 20oz |                                           |
      | 8   | Two-sided Mug        | Picture choice,Picture,3D_5               |
      | 9   | Leather Boots        |                                           |
      | 10  | Baseball Cap         | Text,Text,Mu 555                          |
      | 11  | Polo Shirt Black     |                                           |
    When input Customer information
      | Email                               | First Name | Last Name | Address         | Country | City  | Zip code | Phone      |
      | stag_staff@mailinator.girl-viet.com | Shop       | Base      | 130 Trung Phung | Vietnam | HaNoi | 90001    | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    And Login order detail in hive page
    And verify order detail in hive page as "<KEY>"
      | KEY | Artwork status   | Personalize status | Image mockup                  | Image artwork                | Percent Image |
      | 1   | ARTWORK_RENDERED | NO                 | prod_mockup_iphone12,1        | prod_artwork_iphone12        | 0.5           |
      | 2   | ARTWORK_RENDERED | NO                 | prod_mockup_beverageMug,1     | prod_artwork_beverageMug     | 0.5           |
      | 3   | ARTWORK_RENDERED | NO                 | prod_mockup_FaceMark,1        | prod_artwork_FaceMark        | 0.5           |
      | 4   | ARTWORK_RENDERED | NO                 | prod_mockup_quilt,1           | prod_artwork_quilt           | 0.5           |
      | 5   | ARTWORK_RENDERED | YES                | prod_mockup_unisex,1          | prod_artwork_unisex          | 0.5           |
      | 6   | ARTWORK_RENDERED | YES                | prod_mockup_tank_top,1        | prod_artwork_tank_top        | 0.5           |
      | 7   | ARTWORK_RENDERED | NO                 | prod_mockup_shining_tumbler,1 | prod_artwork_shining_tumbler | 0.5           |
      | 8   | ARTWORK_RENDERED | YES                | prod_mockup_two_mug,1         | prod_artwork_two_mug         | 0.5           |
      | 9   | ARTWORK_RENDERED | YES                | prod_mockup_Boots,1           | prod_artwork_Boots           | 0.5           |
      | 10  | ARTWORK_RENDERED | YES                | prod_mockup_Baseball_Cap,1    | prod_artwork_Baseball_Cap    | 0.5           |
      | 11  | ARTWORK_RENDERED | YES                | prod_mockup_polo,2            | prod_artwork_polo            | 0.5           |
    And close browser

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |
      | 6   |
      | 7   |
      | 8   |
      | 9   |
      | 10  |
      | 11  |