Feature: SignInPlusBase
#env= plbase_sign_in

  Scenario: Verify log in from PlBase landing platform
    Given open app store page
    Then User click Get started
    Then Verify display "Start your free 14-day trial of PlusBase" page

  Scenario: Verify user sign up PlBase dashboard
    Given redirect to sign up PlBase
    Then verify customer sign up account to login PlBase shop
      | Email                  | Password | Shopname         | Expected                                                                                                                  |
      |                        |          |                  | Please enter an email address                                                                                             |
      |                        | 12345    | ShopBaseQA       | Please enter an email address                                                                                             |
      | hiennguyen@gmail.com   |          | ShopBaseQA       | Please enter a password                                                                                                   |
      | hiennguyen@gmail.com   | 12345    |                  | Please enter a store name                                                                                                 |
      | hiennguyen @gmail.com  | 123      | abc              | Please enter a valid email address; Password must be at least 5 characters; Your store name must be at least 4 characters |
      | hiennguyen @gmail.com  | 123456   | ShopBaseQA       | Please enter a valid email address                                                                                        |
      | shopbase@beeketing.net | 123456   | ShopBaseQA       | This email address has already been used, do you want to Sign in instead?                                                 |
      | @EMAIL@                | 123456   | au-checkout-flow | A store with that name already exists. If you are the owner you can Sign in here.                                         |
      | @EMAIL@                | 1234     |                  | Password must be at least 5 characters; Please enter a store name                                                         |
      | @EMAIL@                | 123456   | abc              | Your store name must be at least 4 characters                                                                             |
      | @EMAIL@                | 123456   | @SHOPNAME@       | Select a shop                                                                                                             |

  Scenario Outline: Verify user sign in PlBase dashboard
    Given login to plusbase dashboard
    And User sign in to PlusBase dashboard with email "<email>" and password "<password>"
    Then verify sign in status "<status>"
    Examples:
      | email                  | password | status                               |
      |                        |          | Please enter your email and password |
      |                        | 123456   | Please enter your email and password |
      | shopbase@beeketing.net |          | Please enter your email and password |
      | shopbasebeeketing.net  | 123456   | Email or password is not valid       |
      | shopbasebeeketing.net  | 123      | Email or password is not valid       |
      | @email@                | @pass@   | Select a shop                        |
