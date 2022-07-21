Feature: Select language in dashboard

  #sbase_translate_language
  Scenario Outline: verify language in sign in sign up page
    Given change and verify translate language is "<Language>" in page is "<Page>" as "<KEY>"
      | KEY | Element                                                            | English                                  | China           |
      | 1   | //div[@class='unite-ui-login__title']//p                           | Sign in                                  | 登录              |
      | 1   | //div[@class='unite-ui-login-collapse-main']//label[@for='email']  | Email                                    | 电子邮件            |
      | 1   | //div[@class='unite-ui-login-collapse-main']//label[@for='title']  | Password                                 | 密码              |
      | 1   | //div[@class='unite-ui-login__item--form-action']//a               | Forgot password?                         | 忘记密码？           |
      | 1   | //div[@class='unite-ui-login__item--form-action']//button          | Sign in                                  | 登录              |
      | 1   | //div[@class='unite-ui-sign-up-account']//p                        | Don't have an account? Sign up.          | 还没有账户？ 注册.      |

      | 2   | //div[@class='unite-ui-login__title']//p                           | Sign in                                  | 登录              |
      | 2   | //div[@class='unite-ui-login-collapse-main']//label[@for='email']  | Email                                    | 电子邮件            |
      | 2   | //div[@class='unite-ui-login-collapse-main']//label[@for='title']  | Password                                 | 密码              |
      | 2   | //div[@class='unite-ui-login__item--form-action']//a               | Forgot password?                         | 忘记密码？           |
      | 2   | //div[@class='unite-ui-login__item--form-action']//button          | Sign in                                  | 登录              |
      | 2   | //div[@class='unite-ui-sign-up-account']//p                        | Don't have an account? Sign up.          | 还没有账户？ 注册.      |

      | 3   | //div[@class='unite-ui-login__title']//h1                          | Start your free 14-day trial of ShopBase | 免费试用ShopBase14天 |
      | 3   | //div[@class='js-unite-ui-login__screen']//label[@for='email']     | Email                                    | 电子邮件            |
      | 3   | //div[@class='js-unite-ui-login__screen']//label[@for='password']  | Password                                 | 密码              |
      | 3   | //div[@class='js-unite-ui-login__screen']//label[@for='shop_name'] | Shop name                                | 商店名称            |
      | 3   | //button[@type='submit']//span                                     | Sign up                                  | 注册              |
      | 3   | //div[@class='ui-login-event']//p                                  | Already have an account? Sign in         | 已经有一个账号？ 登录     |

      | 4   | //div[@class='unite-ui-login__title']//h1                          | Start your free 14-day trial of ShopBase | 免费试用ShopBase14天 |
      | 4   | //div[@class='js-unite-ui-login__screen']//label[@for='email']     | Email                                    | 电子邮件            |
      | 4   | //div[@class='js-unite-ui-login__screen']//label[@for='password']  | Password                                 | 密码              |
      | 4   | //div[@class='js-unite-ui-login__screen']//label[@for='shop_name'] | Shop name                                | 商店名称            |
      | 4   | //button[@type='submit']//span                                     | Sign up                                  | 注册              |
      | 4   | //div[@class='ui-login-event']//p                                  | Already have an account? Sign in         | 已经有一个账号？ 登录     |
    Examples:
      | KEY | Language | Page    |
      | 1   | Chinese  | Sign in |
#      | 2   | English  | Sign in |
      | 3   | Chinese  | Sign up |
#      | 4   | English  | Sign up |

  Scenario Outline: verify translate language during create shop
    Given login dashboard email is "<Email>" and password is "<Password>" as "<KEY>"
      | KEY |
      | 1   |
      | 2   |
    And user enter shopname is "<Shopname>" and verify language is "<Language>" as "<KEY>"
      | KEY | Element                                  | English        | China  |
      | 1   | //p[contains(@class,'text-select-shop')] | Select a shop  | 选择一个商店 |
      | 1   | //button[@type='button']//span           | Add a new shop | 添加新商店  |

      | 2   | //p[contains(@class,'text-select-shop')] | Select a shop  | 选择一个商店 |
      | 2   | //button[@type='button']//span           | Add a new shop | 添加新商店  |
    And user enter information and verify language is "<Language>" as "<KEY>"
      | KEY | Element                                                                 | English                                                                        | China                                |
      | 1   | //div[@class='p-font-bold pointer p-bar-desc']                          | Add your contact                                                               | 添加您的联系信息                             |
      | 1   | (//div[@class='p-bar-desc'])[1]                                         | Choose your business model                                                     | 选择您的商业模型                             |
      | 1   | //div[@class='p-desc-final p-bar-desc']                                 | Customize your store                                                           | 自定义您的商店                              |
      | 1   | //div[@class='survey__content type--center s-py24']//h1                 | Let's get to know each other                                                   | 让我们互相认识                              |
      | 1   | (//label[@for='asf_country'])[1]                                        | Your store country                                                             | 您的店铺所在国家/地区                                |
      | 1   | (//label[@for='asf_country'])[2]                                        | Your personal location                                                         | 您的位置                                 |
      | 1   | //label[@for='phone_user']                                              | How should we contact you?                                                     | 我们如何与您联系？                            |
      | 1   | //div[@class='s-form-item' and descendant::input[@type='email']]//label | Your personal social profile (optional)                                        | 您的个人资料（可选）                           |
      | 1   | //p[@class='phone-help-text']                                           | We’ll inform you of emergency cases on your store.                             | 我们会通知您店里发生的紧急情况                 |
      | 1   | //button[@class='s-button is-primary']//span                            | Next                                                                           | 下一步                                  |
      | 1   | //div[contains(@class,'survey__action')]/p                              | By clicking "Next", you agree to ShopBase Terms of Services and Privacy policy | 点击“下一步”即表示您同意ShopBase 服务条款 和 隐私权政策", |

      | 2   | //div[@class='p-font-bold pointer p-bar-desc']                          | Add your contact                                                               | 添加您的联系信息                             |
      | 2   | (//div[@class='p-bar-desc'])[1]                                         | Choose your business model                                                     | 选择您的商业模型                             |
      | 2   | //div[@class='p-desc-final p-bar-desc']                                 | Customize your store                                                           | 自定义您的商店                              |
      | 2   | //div[@class='survey__content type--center s-py24']//h1                 | Let's get to know each other                                                   | 让我们互相认识                              |
      | 2   | (//label[@for='asf_country'])[1]                                        | Your store country                                                             | 您的店铺所在国家/地区                                 |
      | 2   | (//label[@for='asf_country'])[2]                                        | Your personal location                                                         | 您的位置                                 |
      | 2   | //label[@for='phone_user']                                              | How should we contact you?                                                     | 我们如何与您联系？                            |
      | 2   | //div[@class='s-form-item' and descendant::input[@type='email']]//label | Your personal social profile (optional)                                        | 您的个人资料（可选）                           |
      | 2   | //p[@class='phone-help-text']                                           | We’ll inform you of emergency cases on your store.                             | 我们会通知您店里发生的紧急情况                        |
      | 2   | //button[@class='s-button is-primary']//span                            | Next                                                                           | 下一步                                  |
      | 2   | //div[contains(@class,'survey__action')]/p                              | By clicking "Next", you agree to ShopBase Terms of Services and Privacy policy | 点击“下一步”即表示您同意ShopBase 服务条款 和 隐私权政策", |

    Then verify translate language is "<Language>" and choose Business type is "<Business Type>" as "<KEY>"
      | KEY | Element                                          | English                      | China      |
      | 1   | //div[@class='pointer p-bar-desc']               | Add your contact             | 添加您的联系信息   |
      | 1   | //div[@class='p-font-bold pointer p-bar-desc']   | Choose your business model   | 选择您的商业模型   |
      | 1   | //div[@class='p-desc-final p-bar-desc']          | Customize your store         | 自定义您的商店    |
      | 1   | //div[@class='survey__your-type-store']//h1      | What is your business model? | 您的业务模式是什么？ |
      | 1   | (//div[@class='survey__your-type-store']//li)[1] | Print On Demand              | 定制产品       |
      | 1   | (//div[@class='survey__your-type-store']//li)[2] | General Dropshipping         | 杂货铺       |
      | 1   | (//div[@class='survey__your-type-store']//li)[3] | Niche Dropshipping           | 利基店         |
      | 1   | (//div[@class='survey__your-type-store']//li)[4] | Others                       | 其它         |

      | 2   | //div[@class='pointer p-bar-desc']               | Add your contact             | 添加您的联系信息   |
      | 2   | //div[@class='p-font-bold pointer p-bar-desc']   | Choose your business model   | 选择您的商业模型   |
      | 2   | //div[@class='p-desc-final p-bar-desc']          | Customize your store         | 自定义您的商店    |
      | 2   | //div[@class='survey__your-type-store']//h1      | What is your business model? | 您的业务模式是什么？ |
      | 2   | (//div[@class='survey__your-type-store']//li)[1] | Print On Demand              | 定制产品       |
      | 2   | (//div[@class='survey__your-type-store']//li)[2] | General Dropshipping         | 杂货铺       |
      | 2   | (//div[@class='survey__your-type-store']//li)[3] | Niche Dropshipping           | 利基店         |
      | 2   | (//div[@class='survey__your-type-store']//li)[4] | Others                       | 其它         |

    And verify translate language is "<Language>" in select store type is "<Store type>" of Business type is "<Business Type>" as "<KEY>"
      | KEY | Element                                       | English                                                 | China            |
      | 1   | //div[@class='survey-step-title']             | Pick a Print On Demand platform that works best for you | 选择一定制产品个最适合您的平台      |
      | 1   | //button[contains(@class,'is-primary')]//span | I want a PrintBase store                                | 我想要一个PrintBase商店 |
      | 1   | //button[contains(@class,'is-outline')]//span | I want a ShopBase store                                 | 我想要一个ShopBase商店  |

      | 2   | //div[@class='survey-step-title']             | Pick a Print On Demand platform that works best for you | 选择一定制产品个最适合您的平台      |
      | 2   | //button[contains(@class,'is-primary')]//span | I want a PrintBase store                                | 我想要一个PrintBase商店 |
      | 2   | //button[contains(@class,'is-outline')]//span | I want a ShopBase store                                 | 我想要一个ShopBase商店  |


    Examples:
      | KEY | Language | Email                              | Password        | Shopname                | Business Type | Store type       |
#      | 1   | English  | translate@mailinator.girl-viet.com | BdA/2k8LdRXavNK | shop-aut-can-be-deleted | Print On Demand | I want a PrintBase store |
      | 2   | Chinese  | translate@mailinator.girl-viet.com | BdA/2k8LdRXavNK | shop-aut-can-be-deleted | 定制产品          | 我想要一个PrintBase商店 |
