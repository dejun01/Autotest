Feature: Customization

#  Env:
#  staging_sbase_review
#  prod_sbase_review
#  dev_sbase_review

#  Theme: Inside, Roller
#  Setting: uncheck show sticky button in theme editor
#  Products: Slice Quick & Right
#  Reviews page: /pages/all-reviews


  Scenario: Delete review
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user click "All reviews" on menu
    When user delete all reviews
    Then verify deleted all reviews
    And open page "/pages/all-reviews"
    And verify review deleted on SF

  Scenario: Write review by API #SB_SF_RV_19
    When write review by API
      | Rating | Title     | Review                   | Image                                                                             | Your name   | Your email               | Product             |
      | 3      | Review 1  | 1  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 1  | shopbase1@beeketing.net  | Slice Quick & Right |
      | 4      | Review 2  | 2  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 2  | shopbase2@beeketing.net  | Slice Quick & Right |
      | 4      | Review 3  | 3  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 3  | shopbase3@beeketing.net  | Slice Quick & Right |
      | 4      | Review 4  | 4  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 4  | shopbase4@beeketing.net  | Slice Quick & Right |
      | 4      | Review 5  | 5  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 5  | shopbase5@beeketing.net  | Slice Quick & Right |
      | 4      | Review 6  | 6  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 6  | shopbase6@beeketing.net  | Slice Quick & Right |
      | 4      | Review 7  | 7  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 7  | shopbase7@beeketing.net  | Slice Quick & Right |
      | 4      | Review 8  | 8  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 8  | shopbase8@beeketing.net  | Slice Quick & Right |
      | 4      | Review 9  | 9  Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 9  | shopbase9@beeketing.net  | Slice Quick & Right |
      | 3      | Review 10 | 10 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 10 | shopbase10@beeketing.net | Slice Quick & Right |
      | 3      | Review 11 | 11 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 11 | shopbase11@beeketing.net | Slice Quick & Right |
      | 3      | Review 12 | 12 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 12 | shopbase12@beeketing.net |                     |
      | 3      | Review 13 | 13 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 13 | shopbase13@beeketing.net | Slice Quick & Right |
      | 4      | Review 14 | 14 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 14 | shopbase14@beeketing.net | Slice Quick & Right |
      | 4      | Review 15 | 15 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 15 | shopbase15@beeketing.net | Slice Quick & Right |
      | 4      | Review 16 | 16 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 16 | shopbase16@beeketing.net | Slice Quick & Right |
      | 4      | Review 17 | 17 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 17 | shopbase17@beeketing.net | Slice Quick & Right |
      | 4      | Review 18 | 18 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 18 | shopbase18@beeketing.net | Slice Quick & Right |
      | 4      | Review 19 | 19 Great product quality | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 19 | shopbase19@beeketing.net | Slice Quick & Right |
      | 5      | Review 20 | Review test top rated    | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase 20 | shopbase20@beeketing.net | Slice Quick & Right |
      | 4      | @AUTO@    | Review test most recent  | https://img.btdmp.com/review-files/10080496/2021/1/22/Anogpv-8-30-26-download.jpg | shopbase    | shopbase@beeketing.net   | Slice Quick & Right |


  Scenario Outline: Setting customization #SB_SF_RV_18
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user click "Customization" on menu
    When Customization review app on Dashboard "<KEY>"
      | KEY | Font   | Style | Carousel background | Widget background | Primary color | Star color | Date fomat | Display review title | Display review body | Display author name | Display date posted | Display author avatar | Display ratings | Widget Layout | Image position | Reviews per page | Default review logic | Review card layout | Number of reviews | Review logic | Heading      | Display review carousel in checkout page | Link widgets | Link carousel |
      | 1   | Futura | Light | #F5F6F7             | #9B9B9B           | #D0021A       | #F8E71C    | yyyy/MM/dd | true                 | true                | true                | true                | true                  | true            | List          | Above content  | 10               | Most recent          | Classic            | 10                | Most recent  | Real reviews | true                                     | true         | true          |
      | 2   | Arial  | Light | #9B9B9B             | #9EC2ED           | #4A90E2       | #000000    | dd/MM/yyyy | true                 | true                | false               | true                | true                  | false           | Masonry       | Above content  | 5                | Most recent          | Spotlight          | 18                | Most recent  | Review1      | false                                    | true         | false         |
      | 3   | Cabin  | Dark  | #9EC2ED             | #F5F6F7           | #AC2D2D       | #4A90E2    | MM/dd/yyyy | true                 | true                | true                | false               | false                 | true            | List          | Below content  | 18               | Top rated            | Classic            | 5                 | Featured     | Review2      | true                                     | false        | false         |
      | 4   | Futura | Light | #F5F6F7             | #9B9B9B           | #D0021A       | #F8E71C    | MM/dd/yyyy | true                 | true                | true                | true                | true                  | true            | List          | Above content  | 10               | Most recent          | Classic            | 10                | Most recent  | Real reviews | true                                     | true         | true          |
      | 5   | Futura | Light | #F5F6F7             | #9B9B9B           | #D0021A       | #4A90E2    | MM/dd/yyyy | false                | false               | true                | true                | true                  | true            | List          | Above content  | 10               | Most recent          | Classic            | 10                | Most recent  | Real reviews | true                                     | true         | true          |
    Then open shop on storefront
    And verify style of reviews on storefront as "<KEY>"
      | KEY | Page                        | Navigate to chekout page | Is display review | Display review title | Display review body | Display author name | Display date posted | Display author avatar | Display ratings | Font   | Style | Background | Primary color | Star color | Date format | Layout    | Image position | Reviews per page | Number of reviews | Review logic            | Heading      | Show link all review |
      | 1   | /                           | false                    | true              | true                 | true                | true                | true                | true                  | true            | Futura | Light | #F5F6F7    |               | #F8E71C    | yyyy/MM/dd  | Classic   |                |                  | 10                | Review test most recent | Real reviews | true                 |
      | 1   | /products/test              | true                     | true              | true                 | true                | true                | true                | true                  | true            |        | Light | #F5F6F7    |               | #F8E71C    | yyyy/MM/dd  | Classic   |                |                  | 10                | Review test most recent |              | false                |
      | 1   | /products/slice-quick-right | false                    | true              | true                 | true                | true                | true                | true                  | true            | Futura | Light | #9B9B9B    | #D0021A       | #F8E71C    | yyyy/MM/dd  | List      | Above content  | 10               |                   | Review test most recent |              | true                 |
      | 1   | /pages/all-reviews          | false                    | true              | true                 | true                | true                | true                | true                  | true            | Futura | Light | #9B9B9B    | #D0021A       | #F8E71C    | yyyy/MM/dd  | List      | Above content  | 10               |                   | Review test most recent |              | false                |
      | 2   | /                           | false                    | true              | true                 | true                | false               | true                | true                  | false           | Arial  | Light | #9B9B9B    |               | #000000    | dd/MM/yyyy  | Spotlight |                |                  | 18                | Review test most recent | Review1      | false                |
      | 2   | /products/test              | true                     | false             |                      |                     |                     |                     |                       |                 |        |       |            |               |            |             |           |                |                  |                   |                         |              | false                |
      | 2   | /products/slice-quick-right | false                    | true              | true                 | true                | false               | true                | true                  | false           | Arial  | Light | #9EC2ED    | #4A90E2       | #000000    | dd/MM/yyyy  | Masonry   | Above content  | 5                |                   | Review test most recent |              | true                 |
      | 2   | /pages/all-reviews          | false                    | true              | true                 | true                | false               | true                | true                  | false           | Arial  | Light | #9EC2ED    | #4A90E2       | #000000    | dd/MM/yyyy  | Masonry   | Above content  | 5                |                   | Review test most recent |              | false                |
      | 3   | /                           | false                    | false             | true                 | true                |                     |                     |                       |                 |        |       |            |               |            |             |           |                |                  |                   |                         |              |                      |
      | 3   | /products/test              | true                     | false             | true                 | true                |                     |                     |                       |                 |        |       |            |               |            |             |           |                |                  |                   |                         |              |                      |
      | 3   | /products/slice-quick-right | false                    | true              | true                 | true                | true                | false               | false                 | true            | Cabin  | Dark  | #F5F6F7    | #AC2D2D       | #4A90E2    |             | List      | Below content  | 18               |                   | Review test top rated   |              | false                |
      | 3   | /pages/all-reviews          | false                    | true              | true                 | true                | true                | false               | false                 | true            | Cabin  | Dark  | #F5F6F7    | #AC2D2D       | #4A90E2    |             | List      | Below content  | 18               |                   | Review test top rated   |              | false                |
      | 4   | /                           | false                    | true              | true                 | true                | true                | true                | true                  | true            | Futura | Light | #F5F6F7    |               | #F8E71C    | MM/dd/yyyy  | Classic   |                |                  | 10                | Review test most recent | Real reviews | true                 |
      | 4   | /products/test              | true                     | true              | true                 | true                | true                | true                | true                  | true            |        | Light | #F5F6F7    |               | #F8E71C    | MM/dd/yyyy  | Classic   |                |                  | 10                | Review test most recent |              | false                |
      | 4   | /products/slice-quick-right | false                    | true              | true                 | true                | true                | true                | true                  | true            | Futura | Light | #9B9B9B    | #D0021A       | #F8E71C    | MM/dd/yyyy  | List      | Above content  | 10               |                   | Review test most recent |              | true                 |
      | 4   | /pages/all-reviews          | false                    | true              | true                 | true                | true                | true                | true                  | true            | Futura | Light | #9B9B9B    | #D0021A       | #F8E71C    | MM/dd/yyyy  | List      | Above content  | 10               |                   | Review test most recent |              | false                |
      | 5   | /                           | false                    | true              | false                | false               | true                | true                | true                  | true            | Futura | Light | #F5F6F7    |               | #4A90E2    |             | Classic   |                |                  | 10                |                         | Real reviews | true                 |
      | 5   | /products/test              | true                     | true              | false                | false               | true                | true                | true                  | true            |        | Light | #F5F6F7    |               | #4A90E2    |             | Classic   |                |                  | 10                |                         |              | false                |
      | 5   | /products/slice-quick-right | false                    | true              | false                | false               | true                | true                | true                  | true            | Futura | Light | #9B9B9B    | #D0021A       | #4A90E2    |             | List      | Above content  | 10               |                   |                         |              | true                 |
      | 5   | /pages/all-reviews          | false                    | true              | false                | false               | true                | true                | true                  | true            | Futura | Light | #9B9B9B    | #D0021A       | #4A90E2    |             | List      | Above content  | 10               |                   |                         |              | false                |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |


  Scenario Outline: Setting notifications
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user click Settings on menu
    When setting notifications as "<KEY>"
      | KEY | Notifications | Only send notification when review under 3 star |
      | 1   | true          | false                                           |
      | 2   | true          | true                                            |
      | 3   | false         | true                                            |
      | 4   | true          | true                                            |
    And open shop on storefront
    And open page "Slice Quick & Right"
    And write reviews as "<KEY>"
      | KEY | Rating | Title                            | Review                | Image                 | Your name  | Your email              | Type           | Result  | Message                      |
      | 1   | 4      | Review 1_send notification       | Great product quality | front/imagereview.png | shopbase 1 | shopbase1@beeketing.net | Product review | Success | Thank you for your feedback! |
      | 2   | 2      | Review 2_send notification       | very bad              | front/imagereview.png | shopbase 2 | shopbase2@beeketing.net | Product review | Success | Thank you for your feedback! |
      | 3   | 5      | Review 3_don't send notification | Good                  | front/imagereview.png | shopbase 3 | shopbase3@beeketing.net | Product review | Success | Thank you for your feedback! |
      | 4   | 3      | Review 4_don't send notification | Good                  | front/imagereview.png | shopbase 3 | shopbase3@beeketing.net | Product review | Success | Thank you for your feedback! |
    Then open yopmail
    And verify email notification as "<KEY>"
      | KEY | Review title                     | Is show |
      | 1   | Review 1_send notification       | true    |
      | 2   | Review 2_send notification       | true    |
      | 3   | Review 3_don't send notification | false   |
      | 4   | Review 4_don't send notification | false   |
    Examples:
      | KEY | Description                                                                    |
      | 1   | Turn on notication, send notification when receiving all review                |
      | 2   | Turn on notication, send notification when receiving bad review (under 3 star) |
      | 3   | Turn off notication                                                            |
      | 4   | Turn off notication,send notification when receiving bad review (under 3 star) |

  Scenario Outline: Setting store review #SB_SF_RV_20
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user click "Customization" on menu
    When setting store review as "<KEY>"
      | KEY | Show store review |
      | 1   | false             |
      | 2   | true              |
    Then open shop on storefront
    And open page "Slice Quick & Right"
    And verify show store review as "<KEY>"
      | KEY | Show store review |
      | 1   | false             |
      | 2   | true              |
    And verify UI by css as "<KEY>"
      | KEY | Description  | Element                                                                                        | CSS value                                                                                                                               |
      | 1   | Content      | //div[@class='rv-widget__filter app-flex app-items-center app-justify-end']                    | justify-content>flex-end;align-items>center;display>flex;                                                                               |
      | 2   | Content      | //div[@class='rv-widget__filter-widget rv-widget__filter-widget-new rv-flex rv-a-item-center'] | justify-content>space-between;padding>8px 0;margin>24px 0;border-top>1px solid;border-bottom>1px solid;align-items>center;display>flex; |
      | 2   | Store review | //div[@class='rv-widget__review-type']//span[contains(text(),'Store reviews')]                 | margin-left>80px;                                                                                                                       |
    Examples:
      | KEY |
      | 1   |
      | 2   |