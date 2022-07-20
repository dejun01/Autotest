Feature: Check upload artwork
##prod_pbase_upload_artwork
  Scenario: Delete all artwork
#    When  delete all artwork by API
    Given clear all data
    When user login to shopbase dashboard by API
    And user navigate to "Library>Artworks" screen
    And delete all artwork


  Scenario Outline: Check format file upload
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Library>Artworks" screen
    And add new artwork as "<KEY>"
      | KEY | Artwork       | Error message                           |
      | 01  | artwork01.jpg |                                         |
      | 02  | artwork02.png |                                         |
      | 03  | artwork03.psd |                                         |
      | 04  | f03.png       |                                         |
      | 05  | artwork04.psd | Image file size limit exceeded (1500MB) |
    And search and verify name artwork in Artwork library as "<KEY>"
      | KEY | Name artwork |
      | 01  | artwork01    |
      | 02  | artwork02    |
      | 03  | artwork03    |
      | 04  | f03          |
    Then verify artwork in Artwork library as "<KEY>"
      | KEY | Image expected              | Image actual  |
      | 01  | /phub/artwork/artwork01.jpg | artwork01.png |
      | 02  | /phub/artwork/artwork02.png | artwork02.png |
      | 03  | /phub/artwork/artwork03.psd | artwork03.png |
      | 04  | /phub/artwork/f03.png       | f03.png       |

    And quit browser
    Examples:
      | KEY | Testcase                              |
      | 01  | Upload artwork with file  .jpg        |
      | 02  | Upload artwork with file .png         |
      | 03  | Upload artwork with file .psd         |
      | 04  | Upload artwork tranfer                |
      | 05  | Upload artwork with file .psd > 1.5GB |











