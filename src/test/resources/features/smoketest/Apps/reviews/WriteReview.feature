  # sbase_review_smoketest
  #prod_sbase_review_smoketest
  #staging_sbase_review_smoketest
Feature:Write review

  Scenario Outline: Write reviews
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user click Settings on menu
    And update Reviews settings as "<KEY>"
      | KEY | Auto-publish reviews | Minimum star | Auto-block reviews |
      | 1   | false                | 2 stars      |                    |
      | 2   | true                 | 3 stars      |                    |
      | 3   | true                 | 4 stars      | bad                |
    And open shop on storefront
    And search and select the product "<Product name>"
    When write reviews as "<KEY>"
      | KEY | Rating | Title        | Review                | Image                 | Your name | Your email      | Type           | Result  | Message                      |
      | 1   | 5      | Review 1     | Great product quality | front/imagereview.png | name1     | name1@gmail.com | Product review | Success | Thank you for your feedback! |
      | 2   | 3      | Review 2     | bad                   | front/imagereview.png | name2     | name2@gmail.com | Store review   | Success | Thank you for your feedback! |
      | 2   | 1      | Review 3     | Low shipping          | front/imagereview.png | name3     | name3@gmail.com | Product review | Success | Thank you for your feedback! |
      | 3   | 5      | Review 4 bad | Low shipping          | front/imagereview.png | name4     | name4@gmail.com | Product review | Success | Thank you for your feedback! |
      | 3   | 5      | Review 5     | bad                   | front/imagereview.png | name5     | name5@gmail.com | Store review   | Success | Thank you for your feedback! |
      | 3   | 5      | Review 6     | Great product quality | front/imagereview.png | name6     | name6@gmail.com | Product review | Success | Thank you for your feedback! |
    Then verify reviews are show on SF as "<KEY>"
      | KEY | Title        | Show review on SF | Rating | Content               | Your name | Type            |
      | 1   | Review 1     | false             |        |                       |           | Product reviews |
      | 2   | Review 2     | true              | 3      | bad                   | name2     | Store reviews   |
      | 2   | Review 3     | false             |        |                       |           | Product reviews |
      | 3   | Review 4 bad | false             |        |                       |           | Product reviews |
      | 3   | Review 5     | false             |        |                       |           | Store reviews   |
      | 3   | Review 6     | true              | 5      | Great product quality | name6     | Product reviews |
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "All reviews" screen
    And verify list review on dashboard as "<KEY>"
      | KEY | Title        | Content               | Status      | Your name | Rating |
      | 1   | Review 1     | Great product quality | Unpublished | name1     | 5      |
      | 2   | Review 2     | bad                   | Published   | name2     | 3      |
      | 2   | Review 3     | Low shipping          | Unpublished | name3     | 1      |
      | 3   | Review 4 bad | Low shipping          | Unpublished | name4     | 5      |
      | 3   | Review 5     | bad                   | Unpublished | name5     | 5      |
      | 3   | Review 6     | Great product quality | Published   | name6     | 5      |

    Examples:
      | KEY | Product name        |
      | 1   | V-neck T-shirt      |
      | 2   | V-neck T-shirt      |
      | 3   | MC White Lace Dress |
