Feature: Update Contact Us page - PrintBase - theme Roller
#pbase_contactus_page
  Scenario Outline: Verify contact us page for PrintBase #SB_OLS_1 #SB_OLS_2 #SB_OLS_3 #SB_OLS_4 #SB_OLS_5 #SB_OLS_6 #SB_OLS_7 #SB_OLS_8 #SB_OLS_9 #SB_OLS_10
    Given open page "/pages/contact-us"
    When input data for Contact us page as "<KEY>"
      | KEY | Name     | Email                 | Phone      | Issue type                                   | Order number | Message         | Message sucess                                                       |
#      | 1   | My Duong | myduong@beeketing.net | 0123456789 |                                              | 1234567      | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I want to cancel my order                    | 1234567      |                 |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I want to claim my order                     |              | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | My order has not arrived after 45 days       |              | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I want to cancel my order                    |              | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I want to update my order                    |              | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I have a question about a product/collection |              | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | I have not received my confirmation email    |              | This is Message |                                                                      |
      | 1   | My Duong | myduong@beeketing.net | 0123456789 | Other reasons                                | 1234567      |                 |                                                                      |
      | 2   | My Duong | myduong@beeketing.net | 0123456789 | I have not received my order                 |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 3   | My Duong | myduong@beeketing.net | 0123456789 | Other reasons                                |              | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 4   | My Duong | myduong@beeketing.net | 0123456789 | I want to claim my order                     | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 5   | My Duong | myduong@beeketing.net | 0123456789 | My order has not arrived after 45 days       | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 6   | My Duong | myduong@beeketing.net | 0123456789 | I want to cancel my order                    | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 7   | My Duong | myduong@beeketing.net | 0123456789 | I want to update my order                    | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 8   | My Duong | myduong@beeketing.net | 0123456789 | I have a question about a product/collection | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
      | 9   | My Duong | myduong@beeketing.net | 0123456789 | I have not received my confirmation email    | 1234567      | This is Message | Thanks for contacting us. We'll get back to you as soon as possible. |
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