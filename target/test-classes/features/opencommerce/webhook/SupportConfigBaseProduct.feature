Feature: Support config base product

  Scenario: Get data of base product
    Given login to hive-pbase
    Then Get data of base product

  Scenario: Input data for base product
    Given login to hive-pbase
    Then Input data for base product
      | Color               | Size | Weight | Cost  |
      | White               | S    | 0.32   | 7.49  |
      | White               | M    | 0.38   | 7.49  |
      | White               | L    | 0.44   | 7.49  |
      | White               | XL   | 0.48   | 7.99  |
      | White               | 2XL  | 0.52   | 9.49  |
      | White               | 3XL  | 0.66   | 9.49  |
      | White               | 4XL  | 1      | 11.49 |
      | White               | 5XL  | 1      | 12.49 |
      | Natural             | S    | 0.36   | 7.49  |
      | Natural             | M    | 0.41   | 7.49  |
      | Natural             | L    | 0.47   | 7.49  |
      | Natural             | XL   | 0.5    | 7.99  |
      | Natural             | 2XL  | 0.56   | 9.49  |
      | Natural             | 3XL  | 0.66   | 9.49  |
      | Natural             | 4XL  | 1      | 11.49 |
      | Natural             | 5XL  | 1      | 12.49 |
      | Black               | S    | 0.31   | 7.49  |
      | Black               | M    | 0.31   | 7.49  |
      | Black               | L    | 0.38   | 7.49  |
      | Black               | XL   | 0.44   | 7.99  |
      | Black               | 2XL  | 0.53   | 9.49  |
      | Black               | 3XL  | 0.66   | 9.49  |
      | Black               | 4XL  | 1      | 11.49 |
      | Black               | 5XL  | 1      | 12.49 |
      | Charcoal            | S    | 0.25   | 7.49  |
      | Charcoal            | M    | 0.31   | 7.49  |
      | Charcoal            | L    | 0.38   | 7.49  |
      | Charcoal            | XL   | 0.44   | 7.99  |
      | Charcoal            | 2XL  | 0.54   | 9.49  |
      | Charcoal            | 3XL  | 0.65   | 9.49  |
      | Charcoal            | 4XL  | 1      | 11.49 |
      | Charcoal            | 5XL  | 1      | 12.49 |
      | Ash                 | S    | 0.31   | 7.49  |
      | Ash                 | M    | 0.31   | 7.49  |
      | Ash                 | L    | 0.38   | 7.49  |
      | Ash                 | XL   | 0.44   | 7.99  |
      | Ash                 | 2XL  | 0.51   | 9.49  |
      | Ash                 | 3XL  | 0.66   | 9.49  |
      | Ash                 | 4XL  | 1.08   | 11.49 |
      | Ash                 | 5XL  | 1.16   | 12.49 |
      | Sports Grey         | S    | 0.31   | 7.49  |
      | Sports Grey         | M    | 0.31   | 7.49  |
      | Sports Grey         | L    | 0.38   | 7.49  |
      | Sports Grey         | XL   | 0.44   | 7.99  |
      | Sports Grey         | 2XL  | 0.5    | 9.49  |
      | Sports Grey         | 3XL  | 0.64   | 9.49  |
      | Sports Grey         | 4XL  | 1.08   | 11.49 |
      | Sports Grey         | 5XL  | 1.16   | 12.49 |
      | Navy                | S    | 0.31   | 7.49  |
      | Navy                | M    | 0.38   | 7.49  |
      | Navy                | L    | 0.38   | 7.49  |
      | Navy                | XL   | 0.44   | 7.99  |
      | Navy                | 2XL  | 0.56   | 9.49  |
      | Navy                | 3XL  | 0.66   | 9.49  |
      | Navy                | 4XL  | 1      | 11.49 |
      | Navy                | 5XL  | 1      | 12.49 |
      | Royal Blue          | S    | 0.31   | 7.49  |
      | Royal Blue          | M    | 0.31   | 7.49  |
      | Royal Blue          | L    | 0.38   | 7.49  |
      | Royal Blue          | XL   | 0.44   | 7.99  |
      | Royal Blue          | 2XL  | 0.54   | 9.49  |
      | Royal Blue          | 3XL  | 0.66   | 9.49  |
      | Royal Blue          | 4XL  | 1      | 11.49 |
      | Royal Blue          | 5XL  | 1      | 12.49 |
      | Sapphire            | S    | 0.25   | 7.49  |
      | Sapphire            | M    | 0.31   | 7.49  |
      | Sapphire            | L    | 0.38   | 7.49  |
      | Sapphire            | XL   | 0.44   | 7.99  |
      | Sapphire            | 2XL  | 0.58   | 9.49  |
      | Sapphire            | 3XL  | 0.61   | 9.49  |
      | Sapphire            | 4XL  | 1      | 11.49 |
      | Sapphire            | 5XL  | 1      | 12.49 |
      | Light Blue          | S    | 0.31   | 7.49  |
      | Light Blue          | M    | 0.31   | 7.49  |
      | Light Blue          | L    | 0.38   | 7.49  |
      | Light Blue          | XL   | 0.44   | 7.99  |
      | Light Blue          | 2XL  | 0.57   | 9.49  |
      | Light Blue          | 3XL  | 0.66   | 9.49  |
      | Light Blue          | 4XL  | 1      | 11.49 |
      | Light Blue          | 5XL  | 1      | 12.49 |
      | Indigo Blue         | S    | 0.31   | 7.49  |
      | Indigo Blue         | M    | 0.38   | 7.49  |
      | Indigo Blue         | L    | 0.38   | 7.49  |
      | Indigo Blue         | XL   | 0.44   | 7.99  |
      | Indigo Blue         | 2XL  | 0.56   | 9.49  |
      | Indigo Blue         | 3XL  | 0.66   | 9.49  |
      | Indigo Blue         | 4XL  | 1      | 11.49 |
      | Indigo Blue         | 5XL  | 1      | 12.49 |
      | Purple              | S    | 0.25   | 7.49  |
      | Purple              | M    | 0.31   | 7.49  |
      | Purple              | L    | 0.25   | 7.49  |
      | Purple              | XL   | 0.44   | 7.99  |
      | Purple              | 2XL  | 0.56   | 9.49  |
      | Purple              | 3XL  | 0.65   | 9.49  |
      | Purple              | 4XL  | 1      | 11.49 |
      | Purple              | 5XL  | 1      | 12.49 |
      | Red                 | S    | 0.25   | 7.49  |
      | Red                 | M    | 0.31   | 7.49  |
      | Red                 | L    | 0.38   | 7.49  |
      | Red                 | XL   | 0.44   | 7.99  |
      | Red                 | 2XL  | 0.56   | 9.49  |
      | Red                 | 3XL  | 0.66   | 9.49  |
      | Red                 | 4XL  | 1      | 11.49 |
      | Red                 | 5XL  | 1      | 12.49 |
      | Maroon              | S    | 0.25   | 7.49  |
      | Maroon              | M    | 0.31   | 7.49  |
      | Maroon              | L    | 0.38   | 7.49  |
      | Maroon              | XL   | 0.44   | 7.99  |
      | Maroon              | 2XL  | 0.57   | 9.49  |
      | Maroon              | 3XL  | 0.66   | 9.49  |
      | Maroon              | 4XL  | 1      | 11.49 |
      | Maroon              | 5XL  | 1      | 12.49 |
      | Cardinal Red        | S    | 0.25   | 7.49  |
      | Cardinal Red        | M    | 0.31   | 7.49  |
      | Cardinal Red        | L    | 0.38   | 7.49  |
      | Cardinal Red        | XL   | 0.44   | 7.99  |
      | Cardinal Red        | 2XL  | 0.5    | 9.49  |
      | Cardinal Red        | 3XL  | 0.68   | 9.49  |
      | Cardinal Red        | 4XL  | 1      | 11.49 |
      | Cardinal Red        | 5XL  | 1      | 12.49 |
      | Gold                | S    | 0.31   | 7.49  |
      | Gold                | M    | 0.38   | 7.49  |
      | Gold                | L    | 0.38   | 7.49  |
      | Gold                | XL   | 0.44   | 7.99  |
      | Gold                | 2XL  | 0.58   | 9.49  |
      | Gold                | 3XL  | 0.63   | 9.49  |
      | Gold                | 4XL  | 1      | 11.49 |
      | Gold                | 5XL  | 1      | 12.49 |
      | Sand                | S    | 0.31   | 7.49  |
      | Sand                | M    | 0.38   | 7.49  |
      | Sand                | L    | 0.38   | 7.49  |
      | Sand                | XL   | 0.44   | 7.99  |
      | Sand                | 2XL  | 0.56   | 9.49  |
      | Sand                | 3XL  | 0.65   | 9.49  |
      | Sand                | 4XL  | 1      | 11.49 |
      | Sand                | 5XL  | 1      | 12.49 |
      | Orange              | S    | 0.31   | 7.49  |
      | Orange              | M    | 0.31   | 7.49  |
      | Orange              | L    | 0.38   | 7.49  |
      | Orange              | XL   | 0.44   | 7.99  |
      | Orange              | 2XL  | 0.56   | 9.49  |
      | Orange              | 3XL  | 0.66   | 9.49  |
      | Orange              | 4XL  | 1      | 11.49 |
      | Orange              | 5XL  | 1      | 12.49 |
      | Forest Green        | S    | 0.31   | 7.49  |
      | Forest Green        | M    | 0.31   | 7.49  |
      | Forest Green        | L    | 0.38   | 7.49  |
      | Forest Green        | XL   | 0.44   | 7.99  |
      | Forest Green        | 2XL  | 0.58   | 9.49  |
      | Forest Green        | 3XL  | 0.66   | 9.49  |
      | Forest Green        | 4XL  | 1      | 11.49 |
      | Forest Green        | 5XL  | 1      | 12.49 |
      | Dark Chocolate      | S    | 0.25   | 7.49  |
      | Dark Chocolate      | M    | 0.31   | 7.49  |
      | Dark Chocolate      | L    | 0.38   | 7.49  |
      | Dark Chocolate      | XL   | 0.44   | 7.99  |
      | Dark Chocolate      | 2XL  | 0.56   | 9.49  |
      | Dark Chocolate      | 3XL  | 0.66   | 9.49  |
      | Dark Chocolate      | 4XL  | 1      | 11.49 |
      | Dark Chocolate      | 5XL  | 1      | 12.49 |
      | Irish Green         | S    | 0.25   | 7.49  |
      | Irish Green         | M    | 0.31   | 7.49  |
      | Irish Green         | L    | 0.38   | 7.49  |
      | Irish Green         | XL   | 0.44   | 7.99  |
      | Irish Green         | 2XL  | 0.56   | 9.49  |
      | Irish Green         | 3XL  | 0.66   | 9.49  |
      | Irish Green         | 4XL  | 1      | 11.49 |
      | Irish Green         | 5XL  | 1      | 12.49 |
      | Garnet              | S    | 0.31   | 7.49  |
      | Garnet              | M    | 0.38   | 7.49  |
      | Garnet              | L    | 0.38   | 7.49  |
      | Garnet              | XL   | 0.44   | 7.99  |
      | Garnet              | 2XL  | 0.55   | 9.49  |
      | Garnet              | 3XL  | 0.61   | 9.49  |
      | Garnet              | 4XL  | 1      | 11.49 |
      | Garnet              | 5XL  | 1      | 12.49 |
      | Yellow Haze         | S    | 0.25   | 7.49  |
      | Yellow Haze         | M    | 0.31   | 7.49  |
      | Yellow Haze         | L    | 0.38   | 7.49  |
      | Yellow Haze         | XL   | 0.44   | 7.99  |
      | Yellow Haze         | 2XL  | 0.56   | 9.49  |
      | Yellow Haze         | 3XL  | 0.6    | 9.49  |
      | Yellow Haze         | 4XL  | 1      | 11.49 |
      | Yellow Haze         | 5XL  | 1      | 12.49 |
      | Violet              | S    | 0.25   | 7.49  |
      | Violet              | M    | 0.31   | 7.49  |
      | Violet              | L    | 0.38   | 7.49  |
      | Violet              | XL   | 0.44   | 7.99  |
      | Violet              | 2XL  | 0.56   | 9.49  |
      | Violet              | 3XL  | 0.62   | 9.49  |
      | Violet              | 4XL  | 1      | 11.49 |
      | Violet              | 5XL  | 1      | 12.49 |
      | Tennessee Orange    | S    | 0.25   | 7.49  |
      | Tennessee Orange    | M    | 0.31   | 7.49  |
      | Tennessee Orange    | L    | 0.38   | 7.49  |
      | Tennessee Orange    | XL   | 0.44   | 7.99  |
      | Tennessee Orange    | 2XL  | 0.54   | 9.49  |
      | Tennessee Orange    | 3XL  | 0.64   | 9.49  |
      | Tennessee Orange    | 4XL  | 1      | 11.49 |
      | Tennessee Orange    | 5XL  | 1      | 12.49 |
      | Old Gold            | S    | 0.25   | 7.49  |
      | Old Gold            | M    | 0.31   | 7.49  |
      | Old Gold            | L    | 0.38   | 7.49  |
      | Old Gold            | XL   | 0.44   | 7.99  |
      | Old Gold            | 2XL  | 0.55   | 9.49  |
      | Old Gold            | 3XL  | 0.58   | 9.49  |
      | Lime                | S    | 0.25   | 7.49  |
      | Lime                | M    | 0.31   | 7.49  |
      | Lime                | L    | 0.38   | 7.49  |
      | Lime                | XL   | 0.44   | 7.99  |
      | Lime                | 2XL  | 0.51   | 9.49  |
      | Lime                | 3XL  | 0.65   | 9.49  |
      | Lime                | 4XL  | 1      | 11.49 |
      | Lime                | 5XL  | 1      | 12.49 |
      | Light Pink          | S    | 0.25   | 7.49  |
      | Light Pink          | M    | 0.31   | 7.49  |
      | Light Pink          | L    | 0.38   | 7.49  |
      | Light Pink          | XL   | 0.44   | 7.99  |
      | Light Pink          | 2XL  | 0.51   | 9.49  |
      | Light Pink          | 3XL  | 0.63   | 9.49  |
      | Light Pink          | 4XL  | 1      | 11.49 |
      | Light Pink          | 5XL  | 1      | 12.49 |
      | Heliconia           | S    | 0.25   | 7.49  |
      | Heliconia           | M    | 0.31   | 7.49  |
      | Heliconia           | L    | 0.38   | 7.49  |
      | Heliconia           | XL   | 0.44   | 7.99  |
      | Heliconia           | 2XL  | 0.53   | 9.49  |
      | Heliconia           | 3XL  | 0.65   | 9.49  |
      | Heliconia           | 4XL  | 1      | 11.49 |
      | Heliconia           | 5XL  | 1      | 12.49 |
      | Daisy               | S    | 0.31   | 7.49  |
      | Daisy               | M    | 0.31   | 7.49  |
      | Daisy               | L    | 0.38   | 7.49  |
      | Daisy               | XL   | 0.44   | 7.99  |
      | Daisy               | 2XL  | 0.58   | 9.49  |
      | Daisy               | 3XL  | 0.64   | 9.49  |
      | Daisy               | 4XL  | 1      | 11.49 |
      | Daisy               | 5XL  | 1      | 12.49 |
      | Carolina Blue       | S    | 0.25   | 7.49  |
      | Carolina Blue       | M    | 0.31   | 7.49  |
      | Carolina Blue       | L    | 0.38   | 7.49  |
      | Carolina Blue       | XL   | 0.44   | 7.99  |
      | Carolina Blue       | 2XL  | 0.56   | 9.49  |
      | Carolina Blue       | 3XL  | 0.67   | 9.49  |
      | Carolina Blue       | 4XL  | 1      | 11.49 |
      | Carolina Blue       | 5XL  | 1      | 12.49 |
      | Azalea              | S    | 0.25   | 7.49  |
      | Azalea              | M    | 0.31   | 7.49  |
      | Azalea              | L    | 0.38   | 7.49  |
      | Azalea              | XL   | 0.44   | 7.99  |
      | Azalea              | 2XL  | 0.56   | 9.49  |
      | Azalea              | 3XL  | 0.61   | 9.49  |
      | Azalea              | 4XL  | 1      | 11.49 |
      | Azalea              | 5XL  | 1      | 12.49 |
      | Kiwi                | S    | 0.25   | 7.49  |
      | Kiwi                | M    | 0.31   | 7.49  |
      | Kiwi                | L    | 0.38   | 7.49  |
      | Kiwi                | XL   | 0.44   | 7.99  |
      | Kiwi                | 2XL  | 0.52   | 9.49  |
      | Kiwi                | 3XL  | 0.62   | 9.49  |
      | Kiwi                | 4XL  | 1      | 11.49 |
      | Kiwi                | 5XL  | 1      | 12.49 |
      | Military Green      | S    | 0.25   | 7.49  |
      | Military Green      | M    | 0.31   | 7.49  |
      | Military Green      | L    | 0.38   | 7.49  |
      | Military Green      | XL   | 0.44   | 7.99  |
      | Military Green      | 2XL  | 0.54   | 9.49  |
      | Military Green      | 3XL  | 0.63   | 9.49  |
      | Military Green      | 4XL  | 1      | 11.49 |
      | Military Green      | 5XL  | 1      | 12.49 |
      | Sky                 | S    | 0.25   | 7.49  |
      | Sky                 | M    | 0.31   | 7.49  |
      | Sky                 | L    | 0.38   | 7.49  |
      | Sky                 | XL   | 0.44   | 7.99  |
      | Sky                 | 2XL  | 0.55   | 9.49  |
      | Sky                 | 3XL  | 0.61   | 9.49  |
      | Sky                 | 4XL  | 1      | 11.49 |
      | Sky                 | 5XL  | 1      | 12.49 |
      | Antique Cherry Red  | S    | 0.31   | 7.49  |
      | Antique Cherry Red  | M    | 0.31   | 7.49  |
      | Antique Cherry Red  | L    | 0.38   | 7.49  |
      | Antique Cherry Red  | XL   | 0.44   | 7.99  |
      | Antique Cherry Red  | 2XL  | 0.54   | 9.49  |
      | Antique Cherry Red  | 3XL  | 0.66   | 9.49  |
      | Antique Cherry Red  | 4XL  | 1.08   | 11.49 |
      | Antique Cherry Red  | 5XL  | 1.16   | 12.49 |
      | Antique Irish Green | S    | 0.31   | 7.49  |
      | Antique Irish Green | M    | 0.31   | 7.49  |
      | Antique Irish Green | L    | 0.38   | 7.49  |
      | Antique Irish Green | XL   | 0.44   | 7.99  |
      | Antique Irish Green | 2XL  | 0.56   | 9.49  |
      | Antique Irish Green | 3XL  | 0.65   | 9.49  |
      | Antique Jade Dome   | S    | 0.25   | 7.49  |
      | Antique Jade Dome   | M    | 0.31   | 7.49  |
      | Antique Jade Dome   | L    | 0.38   | 7.49  |
      | Antique Jade Dome   | XL   | 0.44   | 7.99  |
      | Antique Jade Dome   | 2XL  | 0.54   | 9.49  |
      | Antique Jade Dome   | 3XL  | 0.64   | 9.49  |
      | Antique Jade Dome   | 4XL  | 1.08   | 11.49 |
      | Antique Jade Dome   | 5XL  | 1.16   | 12.49 |
      | Antique Orange      | S    | 0.25   | 7.49  |
      | Antique Orange      | M    | 0.31   | 7.49  |
      | Antique Orange      | L    | 0.38   | 7.49  |
      | Antique Orange      | XL   | 0.44   | 7.99  |
      | Antique Orange      | 2XL  | 0.55   | 9.49  |
      | Antique Orange      | 3XL  | 0.63   | 9.49  |
      | Antique Sapphire    | S    | 0.25   | 7.49  |
      | Antique Sapphire    | M    | 0.31   | 7.49  |
      | Antique Sapphire    | L    | 0.38   | 7.49  |
      | Antique Sapphire    | XL   | 0.44   | 7.99  |
      | Antique Sapphire    | 2XL  | 0.55   | 9.49  |
      | Antique Sapphire    | 3XL  | 0.65   | 9.49  |
      | Berry               | S    | 0.25   | 7.49  |
      | Berry               | M    | 0.31   | 7.49  |
      | Berry               | L    | 0.38   | 7.49  |
      | Berry               | XL   | 0.44   | 7.99  |
      | Berry               | 2XL  | 0.53   | 9.49  |
      | Berry               | 3XL  | 0.61   | 9.49  |
      | Blackberry          | S    | 0.31   | 7.49  |
      | Blackberry          | M    | 0.38   | 7.49  |
      | Blackberry          | L    | 0.38   | 7.49  |
      | Blackberry          | XL   | 0.44   | 7.99  |
      | Blackberry          | 2XL  | 0.58   | 9.49  |
      | Blackberry          | 3XL  | 0.66   | 9.49  |
      | Brown Savana        | S    | 0.31   | 7.49  |
      | Brown Savana        | M    | 0.31   | 7.49  |
      | Brown Savana        | L    | 0.38   | 7.49  |
      | Brown Savana        | XL   | 0.44   | 7.99  |
      | Brown Savana        | 2XL  | 0.5    | 9.49  |
      | Brown Savana        | 3XL  | 0.64   | 9.49  |
      | Coral Silk          | S    | 0.25   | 7.49  |
      | Coral Silk          | M    | 0.31   | 7.49  |
      | Coral Silk          | L    | 0.38   | 7.49  |
      | Coral Silk          | XL   | 0.44   | 7.99  |
      | Coral Silk          | 2XL  | 0.54   | 9.49  |
      | Coral Silk          | 3XL  | 0.61   | 9.49  |
      | Coral Silk          | 4XL  | 1      | 11.49 |
      | Coral Silk          | 5XL  | 1      | 12.49 |
      | Electric Green      | S    | 0.31   | 7.49  |
      | Electric Green      | M    | 0.31   | 7.49  |
      | Electric Green      | L    | 0.38   | 7.49  |
      | Electric Green      | XL   | 0.44   | 7.99  |
      | Electric Green      | 2XL  | 0.51   | 9.49  |
      | Electric Green      | 3XL  | 0.61   | 9.49  |
      | Electric Green      | 4XL  | 1      | 11.49 |
      | Electric Green      | 5XL  | 1      | 12.49 |
      | Gravel              | S    | 0.25   | 7.49  |
      | Gravel              | M    | 0.31   | 7.49  |
      | Gravel              | L    | 0.31   | 7.49  |
      | Gravel              | XL   | 0.44   | 7.99  |
      | Gravel              | 2XL  | 0.51   | 9.49  |
      | Gravel              | 3XL  | 0.63   | 9.49  |
      | Gravel              | 4XL  | 1      | 11.49 |
      | Gravel              | 5XL  | 1      | 12.49 |
      | Heather Red         | S    | 0.31   | 7.49  |
      | Heather Red         | M    | 0.38   | 7.49  |
      | Heather Red         | L    | 0.38   | 7.49  |
      | Heather Red         | XL   | 0.44   | 7.99  |
      | Heather Red         | 2XL  | 0.57   | 9.49  |
      | Heather Red         | 3XL  | 0.63   | 9.49  |
      | Heather Red         | 4XL  | 1.08   | 11.49 |
      | Heather Red         | 5XL  | 1.16   | 12.49 |
      | Heather Sapphire    | S    | 0.31   | 7.49  |
      | Heather Sapphire    | M    | 0.31   | 7.49  |
      | Heather Sapphire    | L    | 0.38   | 7.49  |
      | Heather Sapphire    | XL   | 0.44   | 7.99  |
      | Heather Sapphire    | 2XL  | 0.56   | 9.49  |
      | Heather Sapphire    | 3XL  | 0.64   | 9.49  |
      | Heather Sapphire    | 4XL  | 1.08   | 11.49 |
      | Heather Sapphire    | 5XL  | 1.16   | 12.49 |
      | Hthr Military Green | S    | 0.31   | 7.49  |
      | Hthr Military Green | M    | 0.31   | 7.49  |
      | Hthr Military Green | L    | 0.38   | 7.49  |
      | Hthr Military Green | XL   | 0.44   | 7.99  |
      | Hthr Military Green | 2XL  | 0.56   | 9.49  |
      | Hthr Military Green | 3XL  | 0.68   | 9.49  |
      | Hthr Military Green | 4XL  | 1.08   | 11.49 |
      | Hthr Military Green | 5XL  | 1.16   | 12.49 |
      | Ice Grey            | S    | 0.25   | 7.49  |
      | Ice Grey            | M    | 0.31   | 7.49  |
      | Ice Grey            | L    | 0.38   | 7.49  |
      | Ice Grey            | XL   | 0.44   | 7.99  |
      | Ice Grey            | 2XL  | 0.52   | 9.49  |
      | Ice Grey            | 3XL  | 0.6    | 9.49  |
      | Ice Grey            | 4XL  | 1      | 11.49 |
      | Ice Grey            | 5XL  | 1      | 12.49 |
      | Lilac               | S    | 0.31   | 7.49  |
      | Lilac               | M    | 0.38   | 7.49  |
      | Lilac               | L    | 0.38   | 7.49  |
      | Lilac               | XL   | 0.44   | 7.99  |
      | Lilac               | 2XL  | 0.56   | 9.49  |
      | Lilac               | 3XL  | 0.63   | 9.49  |
      | Midnight            | S    | 0.31   | 7.49  |
      | Midnight            | M    | 0.38   | 7.49  |
      | Midnight            | L    | 0.44   | 7.49  |
      | Midnight            | XL   | 0.44   | 7.99  |
      | Midnight            | 2XL  | 0.56   | 9.49  |
      | Midnight            | 3XL  | 0.65   | 9.49  |
      | Russet              | S    | 0.31   | 7.49  |
      | Russet              | M    | 0.38   | 7.49  |
      | Russet              | L    | 0.38   | 7.49  |
      | Russet              | XL   | 0.44   | 7.99  |
      | Russet              | 2XL  | 0.55   | 9.49  |
      | Russet              | 3XL  | 0.63   | 9.49  |
      | Sunset              | S    | 0.31   | 7.49  |
      | Sunset              | M    | 0.38   | 7.49  |
      | Sunset              | L    | 0.38   | 7.49  |
      | Sunset              | XL   | 0.44   | 7.99  |
      | Sunset              | 2XL  | 0.58   | 9.49  |
      | Sunset              | 3XL  | 0.63   | 9.49  |
      | Tropical Blue       | S    | 0.25   | 7.49  |
      | Tropical Blue       | M    | 0.31   | 7.49  |
      | Tropical Blue       | L    | 0.38   | 7.49  |
      | Tropical Blue       | XL   | 0.44   | 7.99  |
      | Tropical Blue       | 2XL  | 0.53   | 9.49  |
      | Tropical Blue       | 3XL  | 0.6    | 9.49  |
      | Tropical Blue       | 4XL  | 1      | 11.49 |
      | Tropical Blue       | 5XL  | 1      | 12.49 |
      | Turf Green          | S    | 0.25   | 7.49  |
      | Turf Green          | M    | 0.31   | 7.49  |
      | Turf Green          | L    | 0.38   | 7.49  |
      | Turf Green          | XL   | 0.44   | 7.99  |
      | Turf Green          | 2XL  | 0.55   | 9.49  |
      | Turf Green          | 3XL  | 0.61   | 9.49  |
      | Tweed               | S    | 0.31   | 7.49  |
      | Tweed               | M    | 0.31   | 7.49  |
      | Tweed               | L    | 0.38   | 7.49  |
      | Tweed               | XL   | 0.44   | 7.99  |
      | Tweed               | 2XL  | 0.55   | 9.49  |
      | Tweed               | 3XL  | 0.64   | 9.49  |
      | Tweed               | 4XL  | 1.08   | 11.49 |
      | Tweed               | 5XL  | 1.16   | 12.49 |
      | Cobalt              | S    | 0.31   | 7.49  |
      | Cobalt              | M    | 0.31   | 7.49  |
      | Cobalt              | L    | 0.38   | 7.49  |
      | Cobalt              | XL   | 0.44   | 7.99  |
      | Cobalt              | 2XL  | 0.54   | 9.49  |
      | Cobalt              | 3XL  | 0.61   | 9.49  |
      | Dark Heather        | S    | 0.31   | 7.49  |
      | Dark Heather        | M    | 0.31   | 7.49  |
      | Dark Heather        | L    | 0.38   | 7.49  |
      | Dark Heather        | XL   | 0.44   | 7.99  |
      | Dark Heather        | 2XL  | 0.56   | 9.49  |
      | Dark Heather        | 3XL  | 0.63   | 9.49  |
      | Dark Heather        | 4XL  | 0.83   | 11.49 |
      | Dark Heather        | 5XL  | 0.89   | 12.49 |
      | Mint Green          | S    | 0.31   | 7.49  |
      | Mint Green          | M    | 0.31   | 7.49  |
      | Mint Green          | L    | 0.38   | 7.49  |
      | Mint Green          | XL   | 0.5    | 7.99  |
      | Mint Green          | 2XL  | 0.54   | 9.49  |
      | Mint Green          | 3XL  | 0.64   | 9.49  |
      | Mint Green          | 4XL  | 1      | 11.49 |
      | Mint Green          | 5XL  | 1      | 12.49 |
      | Safety Green        | S    | 0.31   | 7.49  |
      | Safety Green        | M    | 0.31   | 7.49  |
      | Safety Green        | L    | 0.38   | 7.49  |
      | Safety Green        | XL   | 0.5    | 7.99  |
      | Safety Green        | 2XL  | 0.56   | 9.49  |
      | Safety Green        | 3XL  | 0.61   | 9.49  |
      | Safety Green        | 4XL  | 1.08   | 11.49 |
      | Safety Green        | 5XL  | 1.16   | 12.49 |
      | Safety Orange       | S    | 0.31   | 7.49  |
      | Safety Orange       | M    | 0.31   | 7.49  |
      | Safety Orange       | L    | 0.38   | 7.49  |
      | Safety Orange       | XL   | 0.44   | 7.99  |
      | Safety Orange       | 2XL  | 0.54   | 9.49  |
      | Safety Orange       | 3XL  | 0.63   | 9.49  |
      | Safety Orange       | 4XL  | 1.08   | 11.49 |
      | Safety Orange       | 5XL  | 1.16   | 12.49 |
      | Cornsilk            | S    | 0.36   | 7.49  |
      | Cornsilk            | M    | 0.41   | 7.49  |
      | Cornsilk            | L    | 0.47   | 7.49  |
      | Cornsilk            | XL   | 0.52   | 7.99  |
      | Cornsilk            | 2XL  | 0.56   | 9.49  |
      | Cornsilk            | 3XL  | 0.63   | 9.49  |
      | Neon Blue           | S    | 0.36   | 7.49  |
      | Neon Blue           | M    | 0.41   | 7.49  |
      | Neon Blue           | L    | 0.47   | 7.49  |
      | Neon Blue           | XL   | 0.52   | 7.99  |
      | Neon Blue           | 2XL  | 0.58   | 9.49  |
      | Neon Blue           | 3XL  | 0.67   | 9.49  |
      | Safety Pink         | S    | 0.36   | 7.49  |
      | Safety Pink         | M    | 0.41   | 7.49  |
      | Safety Pink         | L    | 0.47   | 7.49  |
      | Safety Pink         | XL   | 0.52   | 7.99  |
      | Safety Pink         | 2XL  | 0.58   | 9.49  |
      | Safety Pink         | 3XL  | 0.68   | 9.49  |
      | Texas Orange        | S    | 0.36   | 7.49  |
      | Texas Orange        | M    | 0.41   | 7.49  |
      | Texas Orange        | L    | 0.47   | 7.49  |
      | Texas Orange        | XL   | 0.5    | 7.99  |
      | Texas Orange        | 2XL  | 0.56   | 9.49  |
      | Texas Orange        | 3XL  | 0.68   | 9.49  |
      | Neon Green          | S    | 0.36   | 7.49  |
      | Neon Green          | M    | 0.41   | 7.49  |
      | Neon Green          | L    | 0.47   | 7.49  |
      | Neon Green          | XL   | 0.52   | 7.99  |
      | Neon Green          | 2XL  | 0.58   | 9.49  |
      | Neon Green          | 3XL  | 0.67   | 9.49  |
      | Graphite Heather    | S    | 0.35   | 7.49  |
      | Graphite Heather    | M    | 0.38   | 7.49  |
      | Graphite Heather    | L    | 0.45   | 7.49  |
      | Graphite Heather    | XL   | 0.5    | 7.99  |
      | Graphite Heather    | 2XL  | 0.56   | 9.49  |
      | Graphite Heather    | 3XL  | 0.63   | 9.49  |
      | Graphite Heather    | 4XL  | 1      | 11.49 |
      | Graphite Heather    | 5XL  | 1      | 12.49 |
      | Hthr Rdnt Orchid    | S    | 0.25   | 7.49  |
      | Hthr Rdnt Orchid    | M    | 0.36   | 7.49  |
      | Hthr Rdnt Orchid    | L    | 0.41   | 7.49  |
      | Hthr Rdnt Orchid    | XL   | 0.47   | 7.99  |
      | Hthr Rdnt Orchid    | 2XL  | 0.52   | 9.49  |
      | Hthr Rdnt Orchid    | 3XL  | 1.17   | 9.49  |
      | Hthr Rdnt Orchid    | 4XL  | 2      | 11.49 |
      | Hthr Rdnt Orchid    | 5XL  | 0.82   | 12.49 |
      | Heather Navy        | S    | 0.25   | 7.49  |
      | Heather Navy        | M    | 0.36   | 7.49  |
      | Heather Navy        | L    | 0.41   | 7.49  |
      | Heather Navy        | XL   | 0.47   | 7.99  |
      | Heather Navy        | 2XL  | 0.52   | 9.49  |
      | Heather Navy        | 3XL  | 1.17   | 9.49  |
      | Heather Navy        | 4XL  | 2      | 11.49 |
      | Heather Navy        | 5XL  | 0.82   | 12.49 |
    Then refresh page
    Then Get data of base product