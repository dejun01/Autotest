@createShop
###Note: Các shop trong account "autotemplates@beeketing.net" là các template store trên production >>>> Không được dùng để test

Feature: Create shop
#  sbase_createshop
 # prod_sbase_createshop
 # staging_sbase_createshop
 # dev_sbase_createshop
  Scenario: SB_DB_SIGNUP_2.1 : Verify action Sign up
    Given verify sign up shopbase
      | Email                  | Password | Shopname         | Expected                                                                                                                  |
      |                        |          |                  | Please enter an email address                                                                                             |
      |                        | 12345    | ShopBaseQA       | Please enter an email address                                                                                             |
      | huongle5594@gmail.com  |          | ShopBaseQA       | Please enter a password                                                                                                   |
      | huongle5594@gmail.com  | 12345    |                  | Please enter a store name                                                                                                 |
      | huong @gmail.com       | 123      | abc              | Please enter a valid email address; Password must be at least 5 characters; Your store name must be at least 4 characters |
      | huong @gmail.com       | 123456   | ShopBaseQA       | Please enter a valid email address                                                                                        |
      | shopbase@beeketing.net | 123456   | ShopBaseQA       | This email address has already been used, do you want to Sign in instead?                                                 |
      | @EMAIL@                | 123456   | au-checkout-flow | A store with that name already exists. If you are the owner you can Sign in here.                                         |
      | @EMAIL@                | 1234     |                  | Password must be at least 5 characters; Please enter a store name                                                         |
      | @EMAIL@                | 123456   | abc              | Your store name must be at least 4 characters                                                                             |
    And close browser

  Scenario Outline: Create shop shopbase
    Given login to hive sbase
    And clear shop data as "<Shop id>"
    When login to shopbase
    And choose shop as "<KEY>"
      | KEY | Shop name            |
      | 1   | auto-creatshop-pbase |
      | 2   | au-createshop        |
      | 3   | au-createshop        |
      | 4   | au-createshop        |
      | 5   | au-createshop        |
      | 6   | au-createshop        |
    And input store information as "<KEY>"
      | KEY | Store country | Your personal location | Phone number | Social profile             |
      | 1   | Vietnam       | Vietnam                | 0985676567   | https://www.google.com.vn/ |
      | 2   | Vietnam       | Vietnam                | 0985676111   | https://www.google.com.vn/ |
      | 3   | Vietnam       | Vietnam                | 0374567654   | https://www.google.com.vn/ |
      | 4   | Vietnam       | Vietnam                | 0985676567   | https://www.youtube.com/   |
      | 5   | Estonia       | Vietnam                | 0985676111   |                            |
      | 6   | Estonia       | Vietnam                | 0374567654   | https://www.youtube.com/   |

    And select store type as "<KEY>"
      | KEY | Business type             | Store type | Category/Niche |
      | 1   | Print-on-demand           | PrintBase  |                |
      | 2   | Print-on-demand           | ShopBase   |                |
      | 3   | Dropshipping              | niche      | Decor          |
      | 4   | Dropshipping              | niche      | Clothing       |
      | 5   | Manufacture/Private Label |            | Watches        |
      | 6   | Others                    |            | Software       |

    And customize store as "<KEY>"
      | KEY | Type      | Import content | Store import                        | Answer question                                   | Customize style | Color   | Font  | Type product |
      | 1   | PrintBase | No thanks      |                                     |                                                   | No              |         |       | Campaigns    |
      | 2   | ShopBase  | No thanks      |                                     |                                                   | Yes             | #F31F1F | Arial | Products     |
      | 3   | ShopBase  | No thanks      |                                     |                                                   | Yes             |         |       | Products     |
      | 4   | ShopBase  | No thanks      |                                     |                                                   | Yes             |         |       | Products     |
      | 5   | ShopBase  | Import         | au-import-createshop.onshopbase.com | a newbie and have no experience in selling online | No              |         |       | Products     |
      | 6   | ShopBase  | No thanks      |                                     | a newbie and have no experience in selling online | Yes             |         |       | Products     |

    And user navigate to "Settings" screen
    Then verify store information as "<KEY>"
      | KEY | Phone      | Country | Currency        |
      | 1   | 0985676567 | Vietnam | US dollar (USD) |
      | 2   | 0985676111 | Vietnam | US dollar (USD) |
      | 3   | 0374567654 | Vietnam | US dollar (USD) |
      | 4   | 0985676567 | Vietnam | US dollar (USD) |
      | 5   | 0985676111 | Estonia | Euro (EUR)      |
      | 6   | 0374567654 | Estonia | Euro (EUR)      |

    And verify default shipping
    And verify data clone from template as "<KEY>"
      | KEY | Type      | Shop current                        | Store    | Store template                      | User name template          | Pass template |
      | 1   | PrintBase | auto-creatshop-pbase.onshopbase.com | Template | printbasestore.onshopbase.com       | autotemplates@beeketing.net | autotemplates |
      | 2   | ShopBase  | au-createshop.onshopbase.com        | Template | template-printbase.onshopbase.com   | autotemplates@beeketing.net | autotemplates |
      | 3   | ShopBase  | au-createshop.onshopbase.com        | Template | template-handicraft.onshopbase.com  | autotemplates@beeketing.net | autotemplates |
      | 4   | ShopBase  | au-createshop.onshopbase.com        | Template | design-clothing.onshopbase.com      | autotemplates@beeketing.net | autotemplates |
      | 5   | ShopBase  | au-createshop.onshopbase.com        | Import   | au-import-createshop.onshopbase.com | shopbase3@beeketing.net     | 123456        |
      | 6   | ShopBase  | au-createshop.onshopbase.com        | Template | roller-general.onshopbase.com       | autotemplates@beeketing.net | autotemplates |
    And close browser

    Examples:
      | KEY | Description                                               | Shop id  |
#      | 1   | Create shop printbase - Tool chưa support clear campaigns | 10198473 |
      | 2   | Create shop print-on-demand                               | 10189071 |
      | 3   | Create shop with fallback {Business type; Category}       | 10189071 |
      | 4   | Create shop with fallback {Category}                      | 10189071 |
      | 5   | Create shop with import content                           | 10189071 |
      | 6   | Create shop with fallback {Business type}                 | 10189071 |
