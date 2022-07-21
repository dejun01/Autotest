Feature: Call-to-Action button settings
  #Stag: staging_us_setting_cta
  #Prod: prod_us_setting_cta


  Scenario Outline: Verify Call-to-Action button settings #SB_BUS_CTA_1 #SB_BUS_CTA_2 #SB_BUS_CTA_3 #SB_BUS_CTA_4 #SB_BUS_CTA_5 #SB_BUS_CTA_6 #SB_BUS_CTA_7 #SB_BUS_CTA_8 #SB_BUS_CTA_9 #SB_BUS_CTA_10 #SB_BUS_CTA_11 #SB_BUS_CTA_12 #SB_BUS_CTA_13 #SB_BUS_CTA_14 #SB_BUS_CTA_15
    Then open page "/admin/apps/boost-upsell/settings"
    And wait page "Settings" show
    Then Setting Call-to-Action button as "<KEY>"
      | KEY | Prepurchase Offers  | Incart Offers       | Quantity Discounts | Bundles             | Accessories         |
      | 001 | Go to Checkout page | Go to Checkout page |                    | Go to Checkout page | Go to Checkout page |
      | 002 | Go to Cart page     | Go to Cart page     | Go to Cart page    | Go to Cart page     | Go to Cart page     |
      | 003 | Continue shopping   | Continue shopping   | Continue shopping  | Continue shopping   | Continue shopping   |
    And open shop on storefront
    Then Verify Call-to-Action for Prepurchase Offers "<KEY>"
      | KEY | Action              | Product              | URL                            |
      | 001 | Go to Checkout page | Real Clover Necklace | /checkouts                     |
      | 002 | Go to Cart page     | Real Clover Necklace | /cart                          |
      | 003 | Continue shopping   | Real Clover Necklace | /products/real-clover-necklace |
    Then Verify Call-to-Action for In-cart Offers"<KEY>"
      | KEY | Action              | Product             | URL        |
      | 001 | Go to Checkout page | Quick Banana Slicer | /checkouts |
      | 002 | Go to Cart page     | Quick Banana Slicer | /cart      |
      | 003 | Continue shopping   | Quick Banana Slicer | /cart      |
    Then Verify Call-to-Action for Quantity Discounts Offers "<KEY>"
      | KEY | Action            | Product             | URL                            |
      | 002 | Go to Cart page   | Quick Banana Slicer | /cart                          |
      | 003 | Continue shopping | Quick Banana Slicer | /products/quick-banana-slicer-1 |
    Then Verify Call-to-Action for Bundles Offers "<KEY>"
      | KEY | Action              | Product             | URL                            |
      | 001 | Go to Checkout page | Quick Banana Slicer | /checkouts                     |
      | 002 | Go to Cart page     | Quick Banana Slicer | /cart                          |
      | 003 | Continue shopping   | Quick Banana Slicer | /products/quick-banana-slicer-1 |
    Then Verify Call-to-Action for Accessories Offers "<KEY>"
      | KEY | Action              | Product             | URL                            |
      | 001 | Go to Checkout page | Quick Banana Slicer | /checkouts                     |
      | 002 | Go to Cart page     | Quick Banana Slicer | /cart                          |
      | 003 | Continue shopping   | Quick Banana Slicer | /products/quick-banana-slicer-1 |
    Then close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |