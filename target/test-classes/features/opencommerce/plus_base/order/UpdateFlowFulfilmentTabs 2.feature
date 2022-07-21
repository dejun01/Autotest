Feature: Update flow fulfillment tabs
  #fulfill_plusbase

  Scenario: Verify order after fulfill order have incoming stock
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,S>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given staff login to shopbase dashboard
    Given user navigate to "ops/orders" screen by url
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And click on order name in unfulfilled orders list
    And Fulfill order PlusBase
    And move to "To fulfill" tab in fulfillment page and get count
    And Verify auto filter order created over 6h and turn off
    And Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 4        | $1.00              |       |
    And Select ordedr fulfill
    And move to "Awaiting stock" tab in fulfillment page and get count
    Then Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 4        |                    |       |
    And close browser

  Scenario: Verify order after fulfill order have available stock
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,M>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given staff login to shopbase dashboard
    Given user navigate to "ops/orders" screen by url
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And click on order name in unfulfilled orders list
    And Fulfill order PlusBase
    And move to "To fulfill" tab in fulfillment page and get count
    And Verify auto filter order created over 6h and turn off
    And Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 1        | $1.00              |       |
    And Select ordedr fulfill
    And move to "Processing" tab in fulfillment page and get count
    Then Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 1       |                    |       |
    And close browser

  Scenario: Verify order after fulfill order have available stock and done DO out
    Given open plusbase on storefront
    And checkout of order fulfillment successfully via stripe with cart "CNYISHE Elegant Solid Puff Sleeve Slim T-Shirt Women Tee Tops Autumn Fashion Sexy Backless Skinny Crop Top Female Shirts Blusas:White,M>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get all information order
    Given staff login to shopbase dashboard
    Given user navigate to "ops/orders" screen by url
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Then Search and verify payment status = "Paid" and Approve status = "approved" on order list
    And click on order name in unfulfilled orders list
    And Fulfill order PlusBase
    And move to "To fulfill" tab in fulfillment page and get count
    And Verify auto filter order created over 6h and turn off
    And Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 1        | $1.00              |       |
    And Select ordedr fulfill
    And move to "Processing" tab in fulfillment page and get count
    Then Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 1       |                    |       |
    And verify DO "out" created in odoo with "1" DO
    And Verify status of Delivery Order on Odoo
      | Order name   | Customer | Status   |
      | @ordernumber |          | assigned |
    And validate DO on Odoo
      | Order name   | Owner |
      | @ordernumber |       |
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status |
      | @ordernumber |       | done   |
    Given redirect to shopbase dashboard
    Given user navigate to "fulfillment/dropship/list" screen by url
    And move to "Fulfilled" tab in fulfillment page and get count
    And Search by order and verify info order
      | ORDER   | PRODUCT            | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | CNYISHE Elegant... | CNYISHE Elegant... | 1        | $1.00              |       |

