@dashboardCommon @dashboard @authentication
Feature: Switch China domain
#sbase_China_domain

  Scenario Outline: Verify switch China domain on SF #SB_SF_78 #SB_SF_79 #SB_SF_80 #SB_SF_81 #SB_SF_82 #SB_SF_83
    Given user login to shopbase dashboard by API
    Then switch server to "Switch to China admin site"
    And switch language to "English"
    When user navigate to screen "<KEY>"
      | KEY | Screen               |
      | 001 | Products             |
      | 002 | Products>Collections |
      | 004 | Online Store         |
      | 005 | Online Store>Pages   |
    And search product or collection or page on dashboard "<KEY>"
      | KEY | Name product        | Name collection | Name page |
      | 001 | Slice Quick & Right |                 |           |
      | 002 |                     | Collection CDN  |           |
      | 005 |                     |                 | about     |
    Then preview storefront from dashboard "<KEY>"
      | KEY | Action                              | Page                  |
      | 001 | Click icon view                     | Product page          |
      | 002 | Click icon view                     | Collection page       |
      | 003 | Click preview theme in sidebar      | Theme in sidebar      |
      | 004 | Click Preview theme in theme editor | Theme in theme editor |
      | 005 | Click icon view                     | Pages                 |
    Then verify show China domain on SF "<KEY>"
      | KEY | Page                |
      | 001 | Slice Quick & Right |
      | 002 | Collection CDN      |
      | 003 | Homepage            |
      | 004 | Homepage            |
      | 005 | about               |
    Then quit browser
    Examples:
      | KEY | Desciption                    |
      | 001 | Preview Product               |
      | 002 | Preview Collection            |
      | 003 | Preview Theme in sidebar      |
      | 004 | Preview Theme in theme editor |
      | 005 | Preview Pages                 |