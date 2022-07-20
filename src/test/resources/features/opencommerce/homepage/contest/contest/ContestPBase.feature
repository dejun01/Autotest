Feature:  config contest in Hive

  #sbase_contest_pbase

  Scenario Outline: verify display contest in pbase and increate point after checkout an order #SB_CT_46 #SB_CT_55
    Given user login to shopbase dashboard
#    And verify contest display in homepage "PrintBase"
#    And get contest point in current
    When checkout an order on storefront
      | Product name   | Quantity   |
      | <Product name> | <Quantity> |
    And verify point of contest in pbase
      | Product name   | Quantity   | Action   |
      | <Product name> | <Quantity> | <Action> |
    Examples:
      | Product name   | Quantity | Action   |
      | Unisex T-shirt | 1        | Checkout |

#  Scenario Outline: verify display contest in pbase and decreate point after cancel an order
#    Given get contest point in current
#    And checkout an order on storefront
#      | Product name   | Quantity   |
#      | <Product name> | <Quantity> |
#    Given get contest point in current
#    And cancel order on hive-pbase
#      | Order name  | Buyer | Seller |
#      | @OrderName@ | true  | true   |
#    And verify point of contest in pbase
#      | Product name   | Quantity   | Action   |
#      | <Product name> | <Quantity> | <Action> |
#    Examples:
#      | Product name   | Quantity | Action |
#      | Unisex T-shirt | 5        | Cancel |

