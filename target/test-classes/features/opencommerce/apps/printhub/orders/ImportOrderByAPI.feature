Feature: Import order by API
  #env = phub_import_by_API

  Scenario: Verify create order by API success
    Given user login to shopbase dashboard
    When Create order phub by API
      | name       | shipping_name | shipping_address_1         | shipping_address_2 | shipping_city | shipping_zip | shipping_province | shipping_country | shipping_phone | line_items | line_name | reference_id | lineitem_quantity | lineitem_sku | product_name | product_id     | color | size | artwork_front_url                                                                              | artwork_back_url                                                                               | mockup_front_url                                                                                                | mockup_back_url                                                                                                 |
      | Order auto | shipping fee  | 130 Trung Phung Dong Da HN | Trung Phung HN     | California    | 90001        | CA                | US               | 0349035275     |            | Line 1    | test ref     | 5                 | SKU-001      | Trang order  | Unisex T-shirt | White | S    | https://pb-stag.btdmp.com/pbase/shop-id/10057112/artworks/bc5890361ebc2d6997eb37c663ba931c.png | https://pb-stag.btdmp.com/pbase/shop-id/10057112/artworks/bc5890361ebc2d6997eb37c663ba931c.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png |
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    Then Verify information order
      | tab              | date | status           |
      | All              | date | Awaiting Payment |
      | Awaiting Payment | date | Awaiting Payment |
    And close browser


  Scenario: Verify create order by API with data fail
    Given user login to shopbase dashboard
    When Create order phub by API
      | name            | shipping_name | shipping_address_1         | shipping_address_2 | shipping_city | shipping_zip | shipping_province | shipping_country | shipping_phone | line_items | line_name | reference_id | lineitem_quantity | lineitem_sku | product_name | product_id     | color | size | artwork_front_url | artwork_back_url | mockup_front_url                                                                                                | mockup_back_url                                                                                                 |
      | Order auto fail | shipping fee  | 130 Trung Phung Dong Da HN | Trung Phung HN     | California    | 90001        | CA                | US               | 0349035275     |            | Line 1    | test ref     | 5                 | SKU-001      | Trang order  | Unisex T-shirt | White | S    |                   |                  | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png |
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    Then Verify information order
      | tab       | date | status    |
      | All       | date | In Review |
      | In Review | date | In Review |
    And close browser


  Scenario: Verify delete order by API success
    Given user login to shopbase dashboard
    When Create order phub by API
      | name       | shipping_name | shipping_address_1         | shipping_address_2 | shipping_city | shipping_zip | shipping_province | shipping_country | shipping_phone | line_items | line_name | reference_id | lineitem_quantity | lineitem_sku | product_name | product_id     | color | size | artwork_front_url                                                                              | artwork_back_url                                                                               | mockup_front_url                                                                                                | mockup_back_url                                                                                                 |
      | Order auto | shipping fee  | 130 Trung Phung Dong Da HN | Trung Phung HN     | California    | 90001        | CA                | US               | 0349035275     |            | Line 1    | test ref     | 5                 | SKU-001      | Trang order  | Unisex T-shirt | White | S    | https://pb-stag.btdmp.com/pbase/shop-id/10057112/artworks/bc5890361ebc2d6997eb37c663ba931c.png | https://pb-stag.btdmp.com/pbase/shop-id/10057112/artworks/bc5890361ebc2d6997eb37c663ba931c.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png |
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    Then Verify information order
      | tab              | date | status           |
      | All              | date | Awaiting Payment |
      | Awaiting Payment | date | Awaiting Payment |
    When Delete order phub by API
    Then Verify not display order "Could not found any orders matching"
      | tab              |
      | All              |
      | Awaiting Payment |
    And close browser


  Scenario: Verify not delete order payed
    Given user login to shopbase dashboard
    When Create order phub by API
      | name       | shipping_name | shipping_address_1         | shipping_address_2 | shipping_city | shipping_zip | shipping_province | shipping_country | shipping_phone | line_items | line_name | reference_id | lineitem_quantity | lineitem_sku | product_name | product_id     | color | size | artwork_front_url                                                                              | artwork_back_url                                                                               | mockup_front_url                                                                                                | mockup_back_url                                                                                                 |
      | Order auto | shipping fee  | 130 Trung Phung Dong Da HN | Trung Phung HN     | California    | 90001        | CA                | US               | 0349035275     |            | Line 1    | test ref     | 5                 | SKU-001      | Trang order  | Unisex T-shirt | White | S    | https://pb-stag.btdmp.com/pbase/shop-id/10057112/artworks/bc5890361ebc2d6997eb37c663ba931c.png | https://pb-stag.btdmp.com/pbase/shop-id/10057112/artworks/bc5890361ebc2d6997eb37c663ba931c.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png | https://assets-dev.boostflow.com/phub/phub-rendered-v2/1607070288_5fc9f2503ec295xkjs15838240185e673c92710d5.png |
    And user navigate to "apps/print-hub/manage-orders/import" screen by url
    And Payment order in tab "Awaiting Payment"
    When Delete order phub by API
    Then Verify information order
      | tab               | date | status            |
      | Awaiting Shipment | date | Awaiting Shipment |
    And close browser







