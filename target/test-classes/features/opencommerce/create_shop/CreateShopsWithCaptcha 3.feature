@signup
@createShop
Feature: Signup and create shop with captcha
  #create_shops_with_captcha

  Scenario: Create new shops with no captcha #SB_AU_CAPTCHA_1
    Given user open list of shops
    And create a new shop
      | shop                   | status |
      | @acon-can-be-deleted-@ | false  |
      | @acon-can-be-deleted-@ | false  |
#      | @acon-can-be-deleted-@ | false  |

  # change_captcha_provider_nov_2021 - show captcha khi tạo 2 shop liên tiếp trong 15p từ cùng 1 IP (Từ 3 shop trở lên cùng 1 IP)
  # add_logic_to_show_captcha_jan_2022 - show captcha khi tạo 2 shop liên tiếp trong 15p từ cùng 1 IP

  Scenario: Create a new shop with captcha #SB_AU_CAPTCHA_6
    Given user open list of shops
    And create a new shop
      | shop                   | status |
      | @acon-can-be-deleted-@ | true  |
    Then close browser

  Scenario: Signup new user with captcha #SB_AU_CAPTCHA_3
    Given user signup ShopBase with shop domain "meta", password "123456" and email "vietanhnguyen@beeketing.net"