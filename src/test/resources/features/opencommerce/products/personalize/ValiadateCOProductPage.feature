@dashboardProduct @dashboard
  #  sbase_toolbar_personalize
Feature: Personalize Product Dashboard - validate

  Background: Login dashboard
    When clear all data
    And Delete all products by API
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    When Add a new product with data
      | Title         | Description                                                            |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |


  Scenario: verify custom option text field
#    And click button "Create custom option only" in product detail
    And  verify custom option in product page
      | Name   | Type       | Label | Placeholder | Max length | Default value | Allow the following characters | Noti for Default value                                               |
      | Test 1 | Text field |       |             | 0          |               |                                |                                                                      |
      | Test 2 | Text field | Text  | Input text  | 0          |               |                                |                                                                      |
      | Test 3 | Text field | Text  | Input text  | 15         | test          | Characters                     |                                                                      |
      | Test 4 | Text field | Text  | Input text  | 15         | 1234          | Characters                     | Your default value has invalid characters. We only accept characters |
      | Test 5 | Text field | Text  | Input text  | 15         | 1234abc       | Characters                     | Your default value has invalid characters. We only accept characters |
      | Test 6 | Text field | Text  | Input text  | 15         | @##$          | Characters                     | Your default value has invalid characters. We only accept characters |
    And quit browser

  Scenario: verify custom option text field 02
    And  verify custom option in product page
      | Name   | Type       | Layer         | Label | Max length | Default value  | Allow the following characters | Noti for Default value                                                       |
      | Test 1 | Text field | Add your text | Text  | 256        | 1234           | Numbers                        |                                                                              |
      | Test 2 | Text field | Add your text | Text  | 15         | 123 acb        | Numbers                        | Your default value has invalid characters. We only accept numbers            |
      | Test 3 | Text field | Add your text | Text  | 15         | @##$#          | Numbers                        | Your default value has invalid characters. We only accept numbers            |
      | Test 4 | Text field | Add your text | Text  | 15         | ~!@#$%^&*().,? | Special Characters             |                                                                              |
      | Test 5 | Text field | Add your text | Text  | 15         | test           | Special Characters             | Your default value has invalid characters. We only accept special characters |
      | Test 6 | Text field | Add your text | Text  | 15         | 1233@#         | Special Characters             | Your default value has invalid characters. We only accept special characters |
    And quit browser