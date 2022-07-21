Feature: Update Contact Us page - ShopBase- theme Inside
#sbase_contactus_page
  Scenario Outline: Verify contact us page for ShopBase #SB_OLS_11 #SB_OLS_12 #SB_OLS_13 #SB_OLS_14 #SB_OLS_15 #SB_OLS_16 #SB_OLS_17
    Given set customer email
    Then open page "/pages/contact-us"
    When input data for Contact us page as "<KEY>"
      | KEY | Name     | Email                 | Phone      | Issue type                                   | Order number | Message         | Message sucess                                                       |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I have not received my order                 | 1234567      |                 |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 |                                              |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 2   | My Duong | myduong@beeketing.net | 0123456789 | I have not received my order                 | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 3   | My Duong | myduong@beeketing.net | 0123456789 | I want to claim my order                     |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 4   | My Duong | myduong@beeketing.net | 0123456789 | I want to cancel my order                    |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 5   | My Duong | myduong@beeketing.net | 0123456789 | I want to update my order                    |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 6   | My Duong | myduong@beeketing.net | 0123456789 | I have a question about a product/collection |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 7   | My Duong | myduong@beeketing.net | 0123456789 | I have not received my confirmation email    |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 8   | My Duong | myduong@beeketing.net | 0123456789 | Other reasons                                |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
    Then verify send mail to mailbiscuit.com as "<KEY>"
      | KEY | Issue type                                   | Order number | Message         |
      | 2   | I have not received my order                 | 1234567      | This is Message |
      | 3   | I want to claim my order                     |              | This is Message |
      | 4   | I want to cancel my order                    |              | This is Message |
      | 5   | I want to update my order                    |              | This is Message |
      | 6   | I have a question about a product/collection |              | This is Message |
      | 7   | I have not received my confirmation email    |              | This is Message |
      | 8   | Other reasons                                |              | This is Message |
    When close browser
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
