Feature: Support config base product

  Scenario: Get data of base product
    Given login to hive-pbase
    Then Get data of base product

  Scenario: Input data for base product
    Given login to hive-pbase
    Then Input data for base product
      | Color              | Size | Weight | Cost  |
      | Ash                | XS   | 0.29   | 9.49  |
      | Ash                | S    | 0.31   | 9.49  |
      | Ash                | M    | 0.34   | 9.49  |
      | Ash                | L    | 0.38   | 9.49  |
      | Ash                | XL   | 0.42   | 9.49  |
      | Ash                | 2XL  | 0.5    | 11.99 |
      | Ash                | 3XL  | 0.49   | 13.49 |
      | Asphalt            | XS   | 0.29   | 9.49  |
      | Asphalt            | S    | 0.31   | 9.49  |
      | Asphalt            | M    | 0.34   | 9.49  |
      | Asphalt            | L    | 0.38   | 9.49  |
      | Asphalt            | XL   | 0.42   | 9.49  |
      | Asphalt            | 2XL  | 0.48   | 11.99 |
      | Asphalt            | 3XL  | 0.49   | 13.49 |
      | Athletic Heather   | XS   | 0.29   | 9.49  |
      | Athletic Heather   | S    | 0.31   | 9.49  |
      | Athletic Heather   | M    | 0.34   | 9.49  |
      | Athletic Heather   | L    | 0.35   | 9.49  |
      | Athletic Heather   | XL   | 0.42   | 9.49  |
      | Athletic Heather   | 2XL  | 0.48   | 11.99 |
      | Athletic Heather   | 3XL  | 0.49   | 13.49 |
      | Black              | XS   | 0.29   | 9.49  |
      | Black              | S    | 0.31   | 9.49  |
      | Black              | M    | 0.33   | 9.49  |
      | Black              | L    | 0.38   | 9.49  |
      | Black              | XL   | 0.38   | 9.49  |
      | Black              | 2XL  | 0.41   | 11.99 |
      | Black              | 3XL  | 0.52   | 13.49 |
      | Black Marble       | XS   | 0.3    | 9.49  |
      | Black Marble       | S    | 0.33   | 9.49  |
      | Black Marble       | M    | 0.36   | 9.49  |
      | Black Marble       | L    | 0.38   | 9.49  |
      | Black Marble       | XL   | 0.44   | 9.49  |
      | Black Marble       | 2XL  | 0.5    | 11.99 |
      | Brown              | XS   | 0.29   | 9.49  |
      | Brown              | S    | 0.31   | 9.49  |
      | Brown              | M    | 0.34   | 9.49  |
      | Brown              | L    | 0.38   | 9.49  |
      | Brown              | XL   | 0.42   | 9.49  |
      | Brown              | 2XL  | 0.48   | 11.99 |
      | Brown              | 3XL  | 0.49   | 13.49 |
      | Canvas Red         | XS   | 0.29   | 9.49  |
      | Canvas Red         | S    | 0.31   | 9.49  |
      | Canvas Red         | M    | 0.34   | 9.49  |
      | Canvas Red         | L    | 0.38   | 9.49  |
      | Canvas Red         | XL   | 0.37   | 9.49  |
      | Canvas Red         | 2XL  | 0.48   | 11.99 |
      | Canvas Red         | 3XL  | 0.49   | 13.49 |
      | Charcoal Marble    | XS   | 0.29   | 9.49  |
      | Charcoal Marble    | S    | 0.31   | 9.49  |
      | Charcoal Marble    | M    | 0.35   | 9.49  |
      | Charcoal Marble    | L    | 0.38   | 9.49  |
      | Charcoal Marble    | XL   | 0.43   | 9.49  |
      | Charcoal Marble    | 2XL  | 0.5    | 11.99 |
      | Deep Heather       | XS   | 0.29   | 9.49  |
      | Deep Heather       | S    | 0.31   | 9.49  |
      | Deep Heather       | M    | 0.34   | 9.49  |
      | Deep Heather       | L    | 0.38   | 9.49  |
      | Deep Heather       | XL   | 0.42   | 9.49  |
      | Deep Heather       | 2XL  | 0.4    | 11.99 |
      | Deep Heather       | 3XL  | 0.49   | 13.49 |
      | Dk Grey Heather    | XS   | 0.3    | 9.49  |
      | Dk Grey Heather    | S    | 0.38   | 9.49  |
      | Dk Grey Heather    | M    | 0.34   | 9.49  |
      | Dk Grey Heather    | L    | 0.38   | 9.49  |
      | Dk Grey Heather    | XL   | 0.42   | 9.49  |
      | Dk Grey Heather    | 2XL  | 0.48   | 11.99 |
      | Dk Grey Heather    | 3XL  | 0.52   | 13.49 |
      | Heather Brown      | XS   | 0.29   | 9.49  |
      | Heather Brown      | S    | 0.31   | 9.49  |
      | Heather Brown      | M    | 0.33   | 9.49  |
      | Heather Brown      | L    | 0.38   | 9.49  |
      | Heather Brown      | XL   | 0.42   | 9.49  |
      | Heather Brown      | 2XL  | 0.43   | 11.99 |
      | Heather Brown      | 3XL  | 0.52   | 13.49 |
      | Heather Green      | XS   | 0.29   | 9.49  |
      | Heather Green      | S    | 0.31   | 9.49  |
      | Heather Green      | M    | 0.34   | 9.49  |
      | Heather Green      | L    | 0.36   | 9.49  |
      | Heather Green      | XL   | 0.39   | 9.49  |
      | Heather Green      | 2XL  | 0.45   | 11.99 |
      | Heather Green      | 3XL  | 0.49   | 13.49 |
      | Heather Navy       | XS   | 0.29   | 9.49  |
      | Heather Navy       | S    | 0.31   | 9.49  |
      | Heather Navy       | M    | 0.34   | 9.49  |
      | Heather Navy       | L    | 0.36   | 9.49  |
      | Heather Navy       | XL   | 0.42   | 9.49  |
      | Heather Navy       | 2XL  | 0.48   | 11.99 |
      | Heather Navy       | 3XL  | 0.52   | 13.49 |
      | Navy               | XS   | 0.29   | 9.49  |
      | Navy               | S    | 0.31   | 9.49  |
      | Navy               | M    | 0.34   | 9.49  |
      | Navy               | L    | 0.38   | 9.49  |
      | Navy               | XL   | 0.42   | 9.49  |
      | Navy               | 2XL  | 0.48   | 11.99 |
      | Navy               | 3XL  | 0.52   | 13.49 |
      | Navy Marble        | XS   | 0.29   | 9.49  |
      | Navy Marble        | S    | 0.31   | 9.49  |
      | Navy Marble        | M    | 0.34   | 9.49  |
      | Navy Marble        | L    | 0.38   | 9.49  |
      | Navy Marble        | XL   | 0.42   | 9.49  |
      | Navy Marble        | 2XL  | 0.48   | 11.99 |
      | Red                | XS   | 0.29   | 9.49  |
      | Red                | S    | 0.31   | 9.49  |
      | Red                | M    | 0.34   | 9.49  |
      | Red                | L    | 0.38   | 9.49  |
      | Red                | XL   | 0.41   | 9.49  |
      | Red                | 2XL  | 0.48   | 11.99 |
      | Red                | 3XL  | 0.52   | 13.49 |
      | Steel Blue         | XS   | 0.29   | 9.49  |
      | Steel Blue         | S    | 0.31   | 9.49  |
      | Steel Blue         | M    | 0.34   | 9.49  |
      | Steel Blue         | L    | 0.35   | 9.49  |
      | Steel Blue         | XL   | 0.42   | 9.49  |
      | Steel Blue         | 2XL  | 0.48   | 11.99 |
      | Steel Blue         | 3XL  | 0.49   | 13.49 |
      | Team Purple        | XS   | 0.29   | 9.49  |
      | Team Purple        | S    | 0.31   | 9.49  |
      | Team Purple        | M    | 0.34   | 9.49  |
      | Team Purple        | L    | 0.38   | 9.49  |
      | Team Purple        | XL   | 0.42   | 9.49  |
      | Team Purple        | 2XL  | 0.48   | 11.99 |
      | Team Purple        | 3XL  | 0.49   | 13.49 |
      | True Royal         | XS   | 0.29   | 9.49  |
      | True Royal         | S    | 0.31   | 9.49  |
      | True Royal         | M    | 0.34   | 9.49  |
      | True Royal         | L    | 0.38   | 9.49  |
      | True Royal         | XL   | 0.42   | 9.49  |
      | True Royal         | 2XL  | 0.48   | 11.99 |
      | True Royal         | 3XL  | 0.52   | 13.49 |
      | White              | XS   | 0.29   | 9.49  |
      | White              | S    | 0.31   | 9.49  |
      | White              | M    | 0.33   | 9.49  |
      | White              | L    | 0.35   | 9.49  |
      | White              | XL   | 0.37   | 9.49  |
      | White              | 2XL  | 0.48   | 11.99 |
      | White              | 3XL  | 0.52   | 13.49 |
      | White Marble       | XS   | 0.29   | 9.49  |
      | White Marble       | S    | 0.31   | 9.49  |
      | White Marble       | M    | 0.34   | 9.49  |
      | White Marble       | L    | 0.39   | 9.49  |
      | White Marble       | XL   | 0.43   | 9.49  |
      | White Marble       | 2XL  | 0.5    | 11.99 |
      | Dark Grey          | XS   | 0.32   | 9.49  |
      | Dark Grey          | S    | 0.31   | 9.49  |
      | Dark Grey          | M    | 0.34   | 9.49  |
      | Dark Grey          | L    | 0.38   | 9.49  |
      | Dark Grey          | XL   | 0.42   | 9.49  |
      | Dark Grey          | 2XL  | 0.5    | 11.99 |
      | Dark Grey          | 3XL  | 0.49   | 13.49 |
      | Heather Red        | XS   | 0.29   | 9.49  |
      | Heather Red        | S    | 0.31   | 9.49  |
      | Heather Red        | M    | 0.34   | 9.49  |
      | Heather Red        | L    | 0.38   | 9.49  |
      | Heather Red        | XL   | 0.42   | 9.49  |
      | Heather Red        | 2XL  | 0.48   | 11.99 |
      | Heather Red        | 3XL  | 0.49   | 13.49 |
      | Heather True Royal | XS   | 0.29   | 9.49  |
      | Heather True Royal | S    | 0.31   | 9.49  |
      | Heather True Royal | M    | 0.34   | 9.49  |
      | Heather True Royal | L    | 0.38   | 9.49  |
      | Heather True Royal | XL   | 0.42   | 9.49  |
      | Heather True Royal | 2XL  | 0.48   | 11.99 |
      | Heather True Royal | 3XL  | 0.5    | 13.49 |
      | Kelly              | XS   | 0.32   | 9.49  |
      | Kelly              | S    | 0.31   | 9.49  |
      | Kelly              | M    | 0.34   | 9.49  |
      | Kelly              | L    | 0.38   | 9.49  |
      | Kelly              | XL   | 0.42   | 9.49  |
      | Kelly              | 2XL  | 0.48   | 11.99 |
      | Kelly              | 3XL  | 0.49   | 13.49 |
      | Maroon Marble      | XS   | 0.3    | 9.49  |
      | Maroon Marble      | S    | 0.31   | 9.49  |
      | Maroon Marble      | M    | 0.34   | 9.49  |
      | Maroon Marble      | L    | 0.39   | 9.49  |
      | Maroon Marble      | XL   | 0.44   | 9.49  |
      | Maroon Marble      | 2XL  | 0.49   | 11.99 |
      | Neon Blue          | XS   | 0.29   | 9.49  |
      | Neon Blue          | S    | 0.31   | 9.49  |
      | Neon Blue          | M    | 0.34   | 9.49  |
      | Neon Blue          | L    | 0.38   | 9.49  |
      | Neon Blue          | XL   | 0.42   | 9.49  |
      | Neon Blue          | 2XL  | 0.48   | 11.99 |
      | Neon Blue          | 3XL  | 0.49   | 13.49 |
      | Vintage Black      | XS   | 0.29   | 9.49  |
      | Vintage Black      | S    | 0.31   | 9.49  |
      | Vintage Black      | M    | 0.34   | 9.49  |
      | Vintage Black      | L    | 0.38   | 9.49  |
      | Vintage Black      | XL   | 0.42   | 9.49  |
      | Vintage Black      | 2XL  | 0.48   | 11.99 |
      | Vintage Black      | 3XL  | 0.51   | 13.49 |
      | Neon Pink          | XS   | 0.29   | 9.49  |
      | Neon Pink          | S    | 0.31   | 9.49  |
      | Neon Pink          | M    | 0.34   | 9.49  |
      | Neon Pink          | L    | 0.38   | 9.49  |
      | Neon Pink          | XL   | 0.42   | 9.49  |
      | Neon Pink          | 2XL  | 0.48   | 11.99 |
      | Neon Pink          | 3XL  | 0.52   | 13.49 |
      | Neon Green         | XS   | 0.29   | 9.49  |
      | Neon Green         | S    | 0.31   | 9.49  |
      | Neon Green         | M    | 0.34   | 9.49  |
      | Neon Green         | L    | 0.38   | 9.49  |
      | Neon Green         | XL   | 0.42   | 9.49  |
      | Neon Green         | 2XL  | 0.48   | 11.99 |
      | Neon Green         | 3XL  | 0.49   | 13.49 |
      | Neon Yellow        | XS   | 0.29   | 9.49  |
      | Neon Yellow        | S    | 0.31   | 9.49  |
      | Neon Yellow        | M    | 0.34   | 9.49  |
      | Neon Yellow        | L    | 0.38   | 9.49  |
      | Neon Yellow        | XL   | 0.42   | 9.49  |
      | Neon Yellow        | 2XL  | 0.48   | 11.99 |
      | Neon Yellow        | 3XL  | 0.52   | 13.49 |
      | True Royal Marble  | XS   | 0.29   | 9.49  |
      | True Royal Marble  | S    | 0.31   | 9.49  |
      | True Royal Marble  | M    | 0.37   | 9.49  |
      | True Royal Marble  | L    | 0.38   | 9.49  |
      | True Royal Marble  | XL   | 0.42   | 9.49  |
      | True Royal Marble  | 2XL  | 0.48   | 11.99 |
      | Asphalt Slub       | XS   | 0.3    | 9.49  |
      | Asphalt Slub       | S    | 0.31   | 9.49  |
      | Asphalt Slub       | M    | 0.34   | 9.49  |
      | Asphalt Slub       | L    | 0.38   | 9.49  |
      | Asphalt Slub       | XL   | 0.42   | 9.49  |
      | Asphalt Slub       | 2XL  | 0.48   | 11.99 |
      | Black Mineral Wash | XS   | 0.3    | 9.49  |
      | Black Mineral Wash | S    | 0.31   | 9.49  |
      | Black Mineral Wash | M    | 0.34   | 9.49  |
      | Black Mineral Wash | L    | 0.38   | 9.49  |
      | Black Mineral Wash | XL   | 0.42   | 9.49  |
      | Black Mineral Wash | 2XL  | 0.48   | 11.99 |
      | Black Slub         | XS   | 0.3    | 9.49  |
      | Black Slub         | S    | 0.31   | 9.49  |
      | Black Slub         | M    | 0.34   | 9.49  |
      | Black Slub         | L    | 0.38   | 9.49  |
      | Black Slub         | XL   | 0.42   | 9.49  |
      | Black Slub         | 2XL  | 0.48   | 11.99 |
      | Deep Teal          | XS   | 0.3    | 9.49  |
      | Deep Teal          | S    | 0.31   | 9.49  |
      | Deep Teal          | M    | 0.34   | 9.49  |
      | Deep Teal          | L    | 0.38   | 9.49  |
      | Deep Teal          | XL   | 0.42   | 9.49  |
      | Deep Teal          | 2XL  | 0.48   | 11.99 |
      | Deep Teal          | 3XL  | 0.49   | 13.49 |
      | Maroon             | XS   | 0.3    | 9.49  |
      | Maroon             | S    | 0.31   | 9.49  |
      | Maroon             | M    | 0.34   | 9.49  |
      | Maroon             | L    | 0.38   | 9.49  |
      | Maroon             | XL   | 0.42   | 9.49  |
      | Maroon             | 2XL  | 0.48   | 11.99 |
      | Maroon             | 3XL  | 0.49   | 13.49 |
      | White Slub         | XS   | 0.3    | 9.49  |
      | White Slub         | S    | 0.31   | 9.49  |
      | White Slub         | M    | 0.34   | 9.49  |
      | White Slub         | L    | 0.38   | 9.49  |
      | White Slub         | XL   | 0.42   | 9.49  |
      | White Slub         | 2XL  | 0.48   | 11.99 |
      | Orange             | XS   | 0.3    | 9.49  |
      | Orange             | S    | 0.31   | 9.49  |
      | Orange             | M    | 0.34   | 9.49  |
      | Orange             | L    | 0.38   | 9.49  |
      | Orange             | XL   | 0.42   | 9.49  |
      | Orange             | 2XL  | 0.48   | 11.99 |
      | Orange             | 3XL  | 0.49   | 13.49 |
      | Silver             | XS   | 0.32   | 9.49  |
      | Silver             | S    | 0.38   | 9.49  |
      | Silver             | M    | 0.41   | 9.49  |
      | Silver             | L    | 0.38   | 9.49  |
      | Silver             | XL   | 0.48   | 9.49  |
      | Silver             | 2XL  | 0.51   | 11.99 |
      | Silver             | 3XL  | 0.5    | 13.49 |
      | Denim Slub         | XS   | 0.29   | 9.49  |
      | Denim Slub         | S    | 0.31   | 9.49  |
      | Denim Slub         | M    | 0.34   | 9.49  |
      | Denim Slub         | L    | 0.38   | 9.49  |
      | Denim Slub         | XL   | 0.42   | 9.49  |
      | Denim Slub         | 2XL  | 0.48   | 11.99 |
      | Heather Cardinal   | XS   | 0.29   | 9.49  |
      | Heather Cardinal   | S    | 0.31   | 9.49  |
      | Heather Cardinal   | M    | 0.34   | 9.49  |
      | Heather Cardinal   | L    | 0.38   | 9.49  |
      | Heather Cardinal   | XL   | 0.42   | 9.49  |
      | Heather Cardinal   | 2XL  | 0.48   | 11.99 |
      | Heather Cardinal   | 3XL  | 0.5    | 13.49 |
      | Hthr Grass Grn     | XS   | 0.29   | 9.49  |
      | Hthr Grass Grn     | S    | 0.31   | 9.49  |
      | Hthr Grass Grn     | M    | 0.34   | 9.49  |
      | Hthr Grass Grn     | L    | 0.38   | 9.49  |
      | Hthr Grass Grn     | XL   | 0.42   | 9.49  |
      | Hthr Grass Grn     | 2XL  | 0.48   | 11.99 |
      | Hthr Grass Grn     | 3XL  | 0.5    | 13.49 |
      | Navy Slub          | XS   | 0.29   | 9.49  |
      | Navy Slub          | S    | 0.31   | 9.49  |
      | Navy Slub          | M    | 0.34   | 9.49  |
      | Navy Slub          | L    | 0.38   | 9.49  |
      | Navy Slub          | XL   | 0.42   | 9.49  |
      | Navy Slub          | 2XL  | 0.48   | 11.99 |
      | Stone Marble       | XS   | 0.29   | 9.49  |
      | Stone Marble       | S    | 0.31   | 9.49  |
      | Stone Marble       | M    | 0.34   | 9.49  |
      | Stone Marble       | L    | 0.38   | 9.49  |
      | Stone Marble       | XL   | 0.42   | 9.49  |
      | Stone Marble       | 2XL  | 0.48   | 11.99 |
      | Black Heather      | XS   | 0.29   | 9.49  |
      | Black Heather      | S    | 0.31   | 9.49  |
      | Black Heather      | M    | 0.34   | 9.49  |
      | Black Heather      | L    | 0.38   | 9.49  |
      | Black Heather      | XL   | 0.42   | 9.49  |
      | Black Heather      | 2XL  | 0.48   | 11.99 |
      | Black Heather      | 3XL  | 0.5    | 13.49 |
      | Heather Stone      | XS   | 0.29   | 9.49  |
      | Heather Stone      | S    | 0.31   | 9.49  |
      | Heather Stone      | M    | 0.34   | 9.49  |
      | Heather Stone      | L    | 0.38   | 9.49  |
      | Heather Stone      | XL   | 0.42   | 9.49  |
      | Heather Stone      | 2XL  | 0.48   | 11.99 |
      | Heather Stone      | 3XL  | 0.5    | 13.49 |
      | Solid Black Slub   | XS   | 0.29   | 9.49  |
      | Solid Black Slub   | S    | 0.31   | 9.49  |
      | Solid Black Slub   | M    | 0.34   | 9.49  |
      | Solid Black Slub   | L    | 0.38   | 9.49  |
      | Solid Black Slub   | XL   | 0.42   | 9.49  |
      | Solid Black Slub   | 2XL  | 0.48   | 11.99 |
      | Heather Slate      | XS   | 0.29   | 9.49  |
      | Heather Slate      | S    | 0.31   | 9.49  |
      | Heather Slate      | M    | 0.34   | 9.49  |
      | Heather Slate      | L    | 0.38   | 9.49  |
      | Heather Slate      | XL   | 0.42   | 9.49  |
      | Heather Slate      | 2XL  | 0.48   | 11.99 |
      | Heather Slate      | 3XL  | 0.5    | 13.49 |
      | Olive Slub         | XS   | 0.29   | 9.49  |
      | Olive Slub         | S    | 0.31   | 9.49  |
      | Olive Slub         | M    | 0.34   | 9.49  |
      | Olive Slub         | L    | 0.38   | 9.49  |
      | Olive Slub         | XL   | 0.42   | 9.49  |
      | Olive Slub         | 2XL  | 0.48   | 11.99 |
      | Forest Marble      | XS   | 0.29   | 9.49  |
      | Forest Marble      | S    | 0.31   | 9.49  |
      | Forest Marble      | M    | 0.34   | 9.49  |
      | Forest Marble      | L    | 0.38   | 9.49  |
      | Forest Marble      | XL   | 0.42   | 9.49  |
      | Forest Marble      | 2XL  | 0.48   | 11.99 |
      | Military Green     | XS   | 0.29   | 9.49  |
      | Military Green     | S    | 0.31   | 9.49  |
      | Military Green     | M    | 0.34   | 9.49  |
      | Military Green     | L    | 0.38   | 9.49  |
      | Military Green     | XL   | 0.42   | 9.49  |
      | Military Green     | 2XL  | 0.48   | 11.99 |
      | Military Green     | 3XL  | 0.5    | 13.49 |
      | Cardinal           | XS   | 0.29   | 9.49  |
      | Cardinal           | S    | 0.31   | 9.49  |
      | Cardinal           | M    | 0.34   | 9.49  |
      | Cardinal           | L    | 0.38   | 9.49  |
      | Cardinal           | XL   | 0.42   | 9.49  |
      | Cardinal           | 2XL  | 0.48   | 11.99 |
      | Cardinal           | 3XL  | 0.49   | 13.49 |
      | Hthr Deep Teal     | XS   | 0.29   | 9.49  |
      | Hthr Deep Teal     | S    | 0.31   | 9.49  |
      | Hthr Deep Teal     | M    | 0.34   | 9.49  |
      | Hthr Deep Teal     | L    | 0.38   | 9.49  |
      | Hthr Deep Teal     | XL   | 0.42   | 9.49  |
      | Hthr Deep Teal     | 2XL  | 0.48   | 11.99 |
      | Hthr Deep Teal     | 3XL  | 0.49   | 13.49 |
      | Forest             | xsml | 0.22   | 9.49  |
      | Forest             | sml  | 0.27   | 9.49  |
      | Forest             | med  | 0.28   | 9.49  |
      | Forest             | lrg  | 0.32   | 9.49  |
      | Forest             | xlg  | 0.35   | 9.49  |
      | Forest             | xxl  | 0.35   | 11.99 |
      | Forest             | xxxl | 0.43   | 13.49 |
      | Heather Dust       | xsml | 0.26   | 9.49  |
      | Heather Dust       | sml  | 0.26   | 9.49  |
      | Heather Dust       | med  | 0.28   | 9.49  |
      | Heather Dust       | lrg  | 0.32   | 9.49  |
      | Heather Dust       | xlg  | 0.34   | 9.49  |
      | Heather Dust       | xxl  | 0.34   | 11.99 |
      | Heather Dust       | xxxl | 0.48   | 13.49 |
      | Light Pink         | XS   | 0.3    | 9.49  |
      | Light Pink         | S    | 0.38   | 9.49  |
      | Light Pink         | M    | 0.42   | 9.49  |
      | Light Pink         | L    | 0.46   | 9.49  |
      | Light Pink         | XL   | 0.51   | 9.49  |
      | Light Pink         | 2XL  | 0.56   | 11.99 |
      | Light Pink         | 3XL  | 0.61   | 13.49 |
      | Kiwi               | XS   | 0.3    | 9.49  |
      | Kiwi               | S    | 0.38   | 9.49  |
      | Kiwi               | M    | 0.42   | 9.49  |
      | Kiwi               | L    | 0.46   | 9.49  |
      | Kiwi               | XL   | 0.51   | 9.49  |
      | Kiwi               | 2XL  | 0.56   | 11.99 |
      | Kiwi               | 3XL  | 0.61   | 13.49 |


      | White              | XS   | 0.29   | 9.49  |
      | White              | S    | 0.31   | 9.49  |
      | White              | M    | 0.33   | 9.49  |
      | White              | L    | 0.35   | 9.49  |
      | White              | XL   | 0.37   | 9.49  |
      | White              | 2XL  | 0.48   | 11.99 |
      | White              | 3XL  | 0.52   | 13.49 |
      | Black              | XS   | 0.29   | 9.49  |
      | Black              | S    | 0.31   | 9.49  |
      | Black              | M    | 0.33   | 9.49  |
      | Black              | L    | 0.38   | 9.49  |
      | Black              | XL   | 0.38   | 9.49  |
      | Black              | 2XL  | 0.41   | 11.99 |
      | Black              | 3XL  | 0.52   | 13.49 |
      | Asphalt            | XS   | 0.29   | 9.49  |
      | Asphalt            | S    | 0.31   | 9.49  |
      | Asphalt            | M    | 0.34   | 9.49  |
      | Asphalt            | L    | 0.38   | 9.49  |
      | Asphalt            | XL   | 0.42   | 9.49  |
      | Asphalt            | 2XL  | 0.48   | 11.99 |
      | Asphalt            | 3XL  | 0.49   | 13.49 |
      | Ash                | XS   | 0.29   | 9.49  |
      | Ash                | S    | 0.31   | 9.49  |
      | Ash                | M    | 0.34   | 9.49  |
      | Ash                | L    | 0.38   | 9.49  |
      | Ash                | XL   | 0.42   | 9.49  |
      | Ash                | 2XL  | 0.5    | 11.99 |
      | Ash                | 3XL  | 0.49   | 13.49 |
      | Navy               | XS   | 0.29   | 9.49  |
      | Navy               | S    | 0.31   | 9.49  |
      | Navy               | M    | 0.34   | 9.49  |
      | Navy               | L    | 0.38   | 9.49  |
      | Navy               | XL   | 0.42   | 9.49  |
      | Navy               | 2XL  | 0.48   | 11.99 |
      | Navy               | 3XL  | 0.52   | 13.49 |
      | True Royal         | XS   | 0.29   | 9.49  |
      | True Royal         | S    | 0.31   | 9.49  |
      | True Royal         | M    | 0.34   | 9.49  |
      | True Royal         | L    | 0.38   | 9.49  |
      | True Royal         | XL   | 0.42   | 9.49  |
      | True Royal         | 2XL  | 0.48   | 11.99 |
      | True Royal         | 3XL  | 0.52   | 13.49 |
      | Red                | XS   | 0.29   | 9.49  |
      | Red                | S    | 0.31   | 9.49  |
      | Red                | M    | 0.34   | 9.49  |
      | Red                | L    | 0.38   | 9.49  |
      | Red                | XL   | 0.41   | 9.49  |
      | Red                | 2XL  | 0.48   | 11.99 |
      | Red                | 3XL  | 0.52   | 13.49 |
      | Canvas Red         | XS   | 0.29   | 9.49  |
      | Canvas Red         | S    | 0.31   | 9.49  |
      | Canvas Red         | M    | 0.34   | 9.49  |
      | Canvas Red         | L    | 0.38   | 9.49  |
      | Canvas Red         | XL   | 0.37   | 9.49  |
      | Canvas Red         | 2XL  | 0.48   | 11.99 |
      | Canvas Red         | 3XL  | 0.49   | 13.49 |
      | Light Pink         | XS   | 0.3    | 9.49  |
      | Light Pink         | S    | 0.38   | 9.49  |
      | Light Pink         | M    | 0.42   | 9.49  |
      | Light Pink         | L    | 0.46   | 9.49  |
      | Light Pink         | XL   | 0.51   | 9.49  |
      | Light Pink         | 2XL  | 0.56   | 11.99 |
      | Light Pink         | 3XL  | 0.61   | 13.49 |
      | Kiwi               | XS   | 0.3    | 9.49  |
      | Kiwi               | S    | 0.38   | 9.49  |
      | Kiwi               | M    | 0.42   | 9.49  |
      | Kiwi               | L    | 0.46   | 9.49  |
      | Kiwi               | XL   | 0.51   | 9.49  |
      | Kiwi               | 2XL  | 0.56   | 11.99 |
      | Kiwi               | 3XL  | 0.61   | 13.49 |
      | Team Purple        | XS   | 0.29   | 9.49  |
      | Team Purple        | S    | 0.31   | 9.49  |
      | Team Purple        | M    | 0.34   | 9.49  |
      | Team Purple        | L    | 0.38   | 9.49  |
      | Team Purple        | XL   | 0.42   | 9.49  |
      | Team Purple        | 2XL  | 0.48   | 11.99 |
      | Team Purple        | 3XL  | 0.49   | 13.49 |
      | Kelly              | XS   | 0.32   | 9.49  |
      | Kelly              | S    | 0.31   | 9.49  |
      | Kelly              | M    | 0.34   | 9.49  |
      | Kelly              | L    | 0.38   | 9.49  |
      | Kelly              | XL   | 0.42   | 9.49  |
      | Kelly              | 2XL  | 0.48   | 11.99 |
      | Kelly              | 3XL  | 0.49   | 13.49 |
      | Orange             | XS   | 0.3    | 9.49  |
      | Orange             | S    | 0.31   | 9.49  |
      | Orange             | M    | 0.34   | 9.49  |
      | Orange             | L    | 0.38   | 9.49  |
      | Orange             | XL   | 0.42   | 9.49  |
      | Orange             | 2XL  | 0.48   | 11.99 |
      | Orange             | 3XL  | 0.49   | 13.49 |
      | Brown              | XS   | 0.29   | 9.49  |
      | Brown              | S    | 0.31   | 9.49  |
      | Brown              | M    | 0.34   | 9.49  |
      | Brown              | L    | 0.38   | 9.49  |
      | Brown              | XL   | 0.42   | 9.49  |
      | Brown              | 2XL  | 0.48   | 11.99 |
      | Brown              | 3XL  | 0.49   | 13.49 |
      | Athletic Heather   | XS   | 0.29   | 9.49  |
      | Athletic Heather   | S    | 0.31   | 9.49  |
      | Athletic Heather   | M    | 0.34   | 9.49  |
      | Athletic Heather   | L    | 0.35   | 9.49  |
      | Athletic Heather   | XL   | 0.42   | 9.49  |
      | Athletic Heather   | 2XL  | 0.48   | 11.99 |
      | Athletic Heather   | 3XL  | 0.49   | 13.49 |
      | Black Marble       | XS   | 0.3    | 9.49  |
      | Black Marble       | S    | 0.33   | 9.49  |
      | Black Marble       | M    | 0.36   | 9.49  |
      | Black Marble       | L    | 0.38   | 9.49  |
      | Black Marble       | XL   | 0.44   | 9.49  |
      | Black Marble       | 2XL  | 0.5    | 11.99 |
      | Black Marble       | 3XL  | 0      | 0     |
      | Charcoal Marble    | XS   | 0.29   | 9.49  |
      | Charcoal Marble    | S    | 0.31   | 9.49  |
      | Charcoal Marble    | M    | 0.35   | 9.49  |
      | Charcoal Marble    | L    | 0.38   | 9.49  |
      | Charcoal Marble    | XL   | 0.43   | 9.49  |
      | Charcoal Marble    | 2XL  | 0.5    | 11.99 |
      | Charcoal Marble    | 3XL  | 0      | 0     |
      | Deep Heather       | XS   | 0.29   | 9.49  |
      | Deep Heather       | S    | 0.31   | 9.49  |
      | Deep Heather       | M    | 0.34   | 9.49  |
      | Deep Heather       | L    | 0.38   | 9.49  |
      | Deep Heather       | XL   | 0.42   | 9.49  |
      | Deep Heather       | 2XL  | 0.4    | 11.99 |
      | Deep Heather       | 3XL  | 0.49   | 13.49 |
      | Dk Grey Heather    | XS   | 0.3    | 9.49  |
      | Dk Grey Heather    | S    | 0.38   | 9.49  |
      | Dk Grey Heather    | M    | 0.34   | 9.49  |
      | Dk Grey Heather    | L    | 0.38   | 9.49  |
      | Dk Grey Heather    | XL   | 0.42   | 9.49  |
      | Dk Grey Heather    | 2XL  | 0.48   | 11.99 |
      | Dk Grey Heather    | 3XL  | 0.52   | 13.49 |
      | Heather Brown      | XS   | 0.29   | 9.49  |
      | Heather Brown      | S    | 0.31   | 9.49  |
      | Heather Brown      | M    | 0.33   | 9.49  |
      | Heather Brown      | L    | 0.38   | 9.49  |
      | Heather Brown      | XL   | 0.42   | 9.49  |
      | Heather Brown      | 2XL  | 0.43   | 11.99 |
      | Heather Brown      | 3XL  | 0.52   | 13.49 |
      | Heather Green      | XS   | 0.29   | 9.49  |
      | Heather Green      | S    | 0.31   | 9.49  |
      | Heather Green      | M    | 0.34   | 9.49  |
      | Heather Green      | L    | 0.36   | 9.49  |
      | Heather Green      | XL   | 0.39   | 9.49  |
      | Heather Green      | 2XL  | 0.45   | 11.99 |
      | Heather Green      | 3XL  | 0.49   | 13.49 |
      | Heather Navy       | XS   | 0.29   | 9.49  |
      | Heather Navy       | S    | 0.31   | 9.49  |
      | Heather Navy       | M    | 0.34   | 9.49  |
      | Heather Navy       | L    | 0.36   | 9.49  |
      | Heather Navy       | XL   | 0.42   | 9.49  |
      | Heather Navy       | 2XL  | 0.48   | 11.99 |
      | Heather Navy       | 3XL  | 0.52   | 13.49 |
      | Navy Marble        | XS   | 0.29   | 9.49  |
      | Navy Marble        | S    | 0.31   | 9.49  |
      | Navy Marble        | M    | 0.34   | 9.49  |
      | Navy Marble        | L    | 0.38   | 9.49  |
      | Navy Marble        | XL   | 0.42   | 9.49  |
      | Navy Marble        | 2XL  | 0.48   | 11.99 |
      | Navy Marble        | 3XL  | 0      | 0     |
      | Steel Blue         | XS   | 0.29   | 9.49  |
      | Steel Blue         | S    | 0.31   | 9.49  |
      | Steel Blue         | M    | 0.34   | 9.49  |
      | Steel Blue         | L    | 0.35   | 9.49  |
      | Steel Blue         | XL   | 0.42   | 9.49  |
      | Steel Blue         | 2XL  | 0.48   | 11.99 |
      | Steel Blue         | 3XL  | 0.49   | 13.49 |
      | White Marble       | XS   | 0.29   | 9.49  |
      | White Marble       | S    | 0.31   | 9.49  |
      | White Marble       | M    | 0.34   | 9.49  |
      | White Marble       | L    | 0.39   | 9.49  |
      | White Marble       | XL   | 0.43   | 9.49  |
      | White Marble       | 2XL  | 0.5    | 11.99 |
      | White Marble       | 3XL  | 0      | 0     |
      | Dark Grey          | XS   | 0.32   | 9.49  |
      | Dark Grey          | S    | 0.31   | 9.49  |
      | Dark Grey          | M    | 0.34   | 9.49  |
      | Dark Grey          | L    | 0.38   | 9.49  |
      | Dark Grey          | XL   | 0.42   | 9.49  |
      | Dark Grey          | 2XL  | 0.5    | 11.99 |
      | Dark Grey          | 3XL  | 0.49   | 13.49 |
      | Heather Red        | XS   | 0.29   | 9.49  |
      | Heather Red        | S    | 0.31   | 9.49  |
      | Heather Red        | M    | 0.34   | 9.49  |
      | Heather Red        | L    | 0.38   | 9.49  |
      | Heather Red        | XL   | 0.42   | 9.49  |
      | Heather Red        | 2XL  | 0.48   | 11.99 |
      | Heather Red        | 3XL  | 0.49   | 13.49 |
      | Heather True Royal | XS   | 0.29   | 9.49  |
      | Heather True Royal | S    | 0.31   | 9.49  |
      | Heather True Royal | M    | 0.34   | 9.49  |
      | Heather True Royal | L    | 0.38   | 9.49  |
      | Heather True Royal | XL   | 0.42   | 9.49  |
      | Heather True Royal | 2XL  | 0.48   | 11.99 |
      | Heather True Royal | 3XL  | 0.5    | 13.49 |
      | Maroon Marble      | XS   | 0.3    | 9.49  |
      | Maroon Marble      | S    | 0.31   | 9.49  |
      | Maroon Marble      | M    | 0.34   | 9.49  |
      | Maroon Marble      | L    | 0.39   | 9.49  |
      | Maroon Marble      | XL   | 0.44   | 9.49  |
      | Maroon Marble      | 2XL  | 0.49   | 11.99 |
      | Maroon Marble      | 3XL  | 0      | 0     |
      | Neon Blue          | XS   | 0.29   | 9.49  |
      | Neon Blue          | S    | 0.31   | 9.49  |
      | Neon Blue          | M    | 0.34   | 9.49  |
      | Neon Blue          | L    | 0.38   | 9.49  |
      | Neon Blue          | XL   | 0.42   | 9.49  |
      | Neon Blue          | 2XL  | 0.48   | 11.99 |
      | Neon Blue          | 3XL  | 0.49   | 13.49 |
      | Vintage Black      | XS   | 0.29   | 9.49  |
      | Vintage Black      | S    | 0.31   | 9.49  |
      | Vintage Black      | M    | 0.34   | 9.49  |
      | Vintage Black      | L    | 0.38   | 9.49  |
      | Vintage Black      | XL   | 0.42   | 9.49  |
      | Vintage Black      | 2XL  | 0.48   | 11.99 |
      | Vintage Black      | 3XL  | 0.51   | 13.49 |
      | Neon Pink          | XS   | 0.29   | 9.49  |
      | Neon Pink          | S    | 0.31   | 9.49  |
      | Neon Pink          | M    | 0.34   | 9.49  |
      | Neon Pink          | L    | 0.38   | 9.49  |
      | Neon Pink          | XL   | 0.42   | 9.49  |
      | Neon Pink          | 2XL  | 0.48   | 11.99 |
      | Neon Pink          | 3XL  | 0.52   | 13.49 |
      | Neon Green         | XS   | 0.29   | 9.49  |
      | Neon Green         | S    | 0.31   | 9.49  |
      | Neon Green         | M    | 0.34   | 9.49  |
      | Neon Green         | L    | 0.38   | 9.49  |
      | Neon Green         | XL   | 0.42   | 9.49  |
      | Neon Green         | 2XL  | 0.48   | 11.99 |
      | Neon Green         | 3XL  | 0.49   | 13.49 |
      | Neon Yellow        | XS   | 0.29   | 9.49  |
      | Neon Yellow        | S    | 0.31   | 9.49  |
      | Neon Yellow        | M    | 0.34   | 9.49  |
      | Neon Yellow        | L    | 0.38   | 9.49  |
      | Neon Yellow        | XL   | 0.42   | 9.49  |
      | Neon Yellow        | 2XL  | 0.48   | 11.99 |
      | Neon Yellow        | 3XL  | 0.52   | 13.49 |
      | True Royal Marble  | XS   | 0.29   | 9.49  |
      | True Royal Marble  | S    | 0.31   | 9.49  |
      | True Royal Marble  | M    | 0.37   | 9.49  |
      | True Royal Marble  | L    | 0.38   | 9.49  |
      | True Royal Marble  | XL   | 0.42   | 9.49  |
      | True Royal Marble  | 2XL  | 0.48   | 11.99 |
      | True Royal Marble  | 3XL  | 0      | 0     |
      | Asphalt Slub       | XS   | 0.3    | 9.49  |
      | Asphalt Slub       | S    | 0.31   | 9.49  |
      | Asphalt Slub       | M    | 0.34   | 9.49  |
      | Asphalt Slub       | L    | 0.38   | 9.49  |
      | Asphalt Slub       | XL   | 0.42   | 9.49  |
      | Asphalt Slub       | 2XL  | 0.48   | 11.99 |
      | Asphalt Slub       | 3XL  | 0      | 0     |
      | Black Mineral Wash | XS   | 0.3    | 9.49  |
      | Black Mineral Wash | S    | 0.31   | 9.49  |
      | Black Mineral Wash | M    | 0.34   | 9.49  |
      | Black Mineral Wash | L    | 0.38   | 9.49  |
      | Black Mineral Wash | XL   | 0.42   | 9.49  |
      | Black Mineral Wash | 2XL  | 0.48   | 11.99 |
      | Black Mineral Wash | 3XL  | 0      | 0     |
      | Black Slub         | XS   | 0.3    | 9.49  |
      | Black Slub         | S    | 0.31   | 9.49  |
      | Black Slub         | M    | 0.34   | 9.49  |
      | Black Slub         | L    | 0.38   | 9.49  |
      | Black Slub         | XL   | 0.42   | 9.49  |
      | Black Slub         | 2XL  | 0.48   | 11.99 |
      | Black Slub         | 3XL  | 0      | 0     |
      | Deep Teal          | XS   | 0.3    | 9.49  |
      | Deep Teal          | S    | 0.31   | 9.49  |
      | Deep Teal          | M    | 0.34   | 9.49  |
      | Deep Teal          | L    | 0.38   | 9.49  |
      | Deep Teal          | XL   | 0.42   | 9.49  |
      | Deep Teal          | 2XL  | 0.48   | 11.99 |
      | Deep Teal          | 3XL  | 0.49   | 13.49 |
      | Maroon             | XS   | 0.3    | 9.49  |
      | Maroon             | S    | 0.31   | 9.49  |
      | Maroon             | M    | 0.34   | 9.49  |
      | Maroon             | L    | 0.38   | 9.49  |
      | Maroon             | XL   | 0.42   | 9.49  |
      | Maroon             | 2XL  | 0.48   | 11.99 |
      | Maroon             | 3XL  | 0.49   | 13.49 |
      | White Slub         | XS   | 0.3    | 9.49  |
      | White Slub         | S    | 0.31   | 9.49  |
      | White Slub         | M    | 0.34   | 9.49  |
      | White Slub         | L    | 0.38   | 9.49  |
      | White Slub         | XL   | 0.42   | 9.49  |
      | White Slub         | 2XL  | 0.48   | 11.99 |
      | White Slub         | 3XL  | 0      | 0     |
      | Silver             | XS   | 0.32   | 9.49  |
      | Silver             | S    | 0.38   | 9.49  |
      | Silver             | M    | 0.41   | 9.49  |
      | Silver             | L    | 0.38   | 9.49  |
      | Silver             | XL   | 0.48   | 9.49  |
      | Silver             | 2XL  | 0.51   | 11.99 |
      | Silver             | 3XL  | 0.5    | 13.49 |
      | Denim Slub         | XS   | 0.29   | 9.49  |
      | Denim Slub         | S    | 0.31   | 9.49  |
      | Denim Slub         | M    | 0.34   | 9.49  |
      | Denim Slub         | L    | 0.38   | 9.49  |
      | Denim Slub         | XL   | 0.42   | 9.49  |
      | Denim Slub         | 2XL  | 0.48   | 11.99 |
      | Denim Slub         | 3XL  | 0      | 0     |
      | Heather Cardinal   | XS   | 0.29   | 9.49  |
      | Heather Cardinal   | S    | 0.31   | 9.49  |
      | Heather Cardinal   | M    | 0.34   | 9.49  |
      | Heather Cardinal   | L    | 0.38   | 9.49  |
      | Heather Cardinal   | XL   | 0.42   | 9.49  |
      | Heather Cardinal   | 2XL  | 0.48   | 11.99 |
      | Heather Cardinal   | 3XL  | 0.5    | 13.49 |
      | Hthr Grass Grn     | XS   | 0.29   | 9.49  |
      | Hthr Grass Grn     | S    | 0.31   | 9.49  |
      | Hthr Grass Grn     | M    | 0.34   | 9.49  |
      | Hthr Grass Grn     | L    | 0.38   | 9.49  |
      | Hthr Grass Grn     | XL   | 0.42   | 9.49  |
      | Hthr Grass Grn     | 2XL  | 0.48   | 11.99 |
      | Hthr Grass Grn     | 3XL  | 0.5    | 13.49 |
      | Navy Slub          | XS   | 0.29   | 9.49  |
      | Navy Slub          | S    | 0.31   | 9.49  |
      | Navy Slub          | M    | 0.34   | 9.49  |
      | Navy Slub          | L    | 0.38   | 9.49  |
      | Navy Slub          | XL   | 0.42   | 9.49  |
      | Navy Slub          | 2XL  | 0.48   | 11.99 |
      | Navy Slub          | 3XL  | 0      | 0     |
      | Stone Marble       | XS   | 0.29   | 9.49  |
      | Stone Marble       | S    | 0.31   | 9.49  |
      | Stone Marble       | M    | 0.34   | 9.49  |
      | Stone Marble       | L    | 0.38   | 9.49  |
      | Stone Marble       | XL   | 0.42   | 9.49  |
      | Stone Marble       | 2XL  | 0.48   | 11.99 |
      | Stone Marble       | 3XL  | 0      | 0     |
      | Black Heather      | XS   | 0.29   | 9.49  |
      | Black Heather      | S    | 0.31   | 9.49  |
      | Black Heather      | M    | 0.34   | 9.49  |
      | Black Heather      | L    | 0.38   | 9.49  |
      | Black Heather      | XL   | 0.42   | 9.49  |
      | Black Heather      | 2XL  | 0.48   | 11.99 |
      | Black Heather      | 3XL  | 0.5    | 13.49 |
      | Heather Stone      | XS   | 0.29   | 9.49  |
      | Heather Stone      | S    | 0.31   | 9.49  |
      | Heather Stone      | M    | 0.34   | 9.49  |
      | Heather Stone      | L    | 0.38   | 9.49  |
      | Heather Stone      | XL   | 0.42   | 9.49  |
      | Heather Stone      | 2XL  | 0.48   | 11.99 |
      | Heather Stone      | 3XL  | 0.5    | 13.49 |
      | Solid Black Slub   | XS   | 0.29   | 9.49  |
      | Solid Black Slub   | S    | 0.31   | 9.49  |
      | Solid Black Slub   | M    | 0.34   | 9.49  |
      | Solid Black Slub   | L    | 0.38   | 9.49  |
      | Solid Black Slub   | XL   | 0.42   | 9.49  |
      | Solid Black Slub   | 2XL  | 0.48   | 11.99 |
      | Solid Black Slub   | 3XL  | 0      | 0     |
      | Heather Slate      | XS   | 0.29   | 9.49  |
      | Heather Slate      | S    | 0.31   | 9.49  |
      | Heather Slate      | M    | 0.34   | 9.49  |
      | Heather Slate      | L    | 0.38   | 9.49  |
      | Heather Slate      | XL   | 0.42   | 9.49  |
      | Heather Slate      | 2XL  | 0.48   | 11.99 |
      | Heather Slate      | 3XL  | 0.5    | 13.49 |
      | Olive Slub         | XS   | 0.29   | 9.49  |
      | Olive Slub         | S    | 0.31   | 9.49  |
      | Olive Slub         | M    | 0.34   | 9.49  |
      | Olive Slub         | L    | 0.38   | 9.49  |
      | Olive Slub         | XL   | 0.42   | 9.49  |
      | Olive Slub         | 2XL  | 0.48   | 11.99 |
      | Olive Slub         | 3XL  | 0      | 0     |
      | Forest Marble      | XS   | 0.29   | 9.49  |
      | Forest Marble      | S    | 0.31   | 9.49  |
      | Forest Marble      | M    | 0.34   | 9.49  |
      | Forest Marble      | L    | 0.38   | 9.49  |
      | Forest Marble      | XL   | 0.42   | 9.49  |
      | Forest Marble      | 2XL  | 0.48   | 11.99 |
      | Forest Marble      | 3XL  | 0      | 0     |
      | Military Green     | XS   | 0.29   | 9.49  |
      | Military Green     | S    | 0.31   | 9.49  |
      | Military Green     | M    | 0.34   | 9.49  |
      | Military Green     | L    | 0.38   | 9.49  |
      | Military Green     | XL   | 0.42   | 9.49  |
      | Military Green     | 2XL  | 0.48   | 11.99 |
      | Military Green     | 3XL  | 0.5    | 13.49 |
      | Cardinal           | XS   | 0.29   | 9.49  |
      | Cardinal           | S    | 0.31   | 9.49  |
      | Cardinal           | M    | 0.34   | 9.49  |
      | Cardinal           | L    | 0.38   | 9.49  |
      | Cardinal           | XL   | 0.42   | 9.49  |
      | Cardinal           | 2XL  | 0.48   | 11.99 |
      | Cardinal           | 3XL  | 0.49   | 13.49 |
      | Hthr Deep Teal     | XS   | 0.29   | 9.49  |
      | Hthr Deep Teal     | S    | 0.31   | 9.49  |
      | Hthr Deep Teal     | M    | 0.34   | 9.49  |
      | Hthr Deep Teal     | L    | 0.38   | 9.49  |
      | Hthr Deep Teal     | XL   | 0.42   | 9.49  |
      | Hthr Deep Teal     | 2XL  | 0.48   | 11.99 |
      | Hthr Deep Teal     | 3XL  | 0.49   | 13.49 |

    Then refresh page
    Then Get data of base product