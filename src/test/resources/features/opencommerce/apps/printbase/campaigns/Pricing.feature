Feature: Pricing
  As an operator, I would like to apply the same formula to set price for multiple items from Scalablepress so that I don't need to set up each item independently
  #  print_on_demand_mycampaign

  Scenario Outline: PH_CTL_API_PRI_1.1: Verify to calculate base product variant
#    Variant = color,size
    When verify to calculate pricing by API as "<KEY>"
      | KEY | Base product       | Variants                        | Number of second side |
      | 1   | AutoUnisex T-Shirt | White,2XL                       | 0                     |
      | 2   | AutoUnisex T-Shirt | White,S;White,M;Black,S;Black,M | 0                     |
      | 3   | AutoUnisex T-Shirt | White,S;White,M;Black,S;Black,M | 1                     |
      | 4   | Beverage Mug       |                                 | 0                     |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
