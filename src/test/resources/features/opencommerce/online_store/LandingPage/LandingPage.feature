Feature: Landing page - Create Landing Page

  # environment=dev_create_landingpage
  #staging_create_landingpage
  #prod_create_landingpage
  #prodtest_create_landingpage

  Scenario: Verify delete pages
    Given user login to shopbase dashboard
    When user navigate to "Online Store>Landing Pages" screen
    And remove all pages

  Scenario Outline: Verify create landing pages
    Given user login to shopbase dashboard
    When user navigate to "Online Store>Landing Pages" screen
    And create landing page as "<KEY>"
      | KEY | Select a template     | Page title     | Publish this page | SEO Description   | SEO Title   | Open Graph Image        | Total section |
      | 1   | Blank page            | Landing Page 1 | true              | SEO Description 1 | SEO Title 1 | front/Galleryimage.jpeg |               |
      | 2   | Template landing page | Landing Page 2 | false             | SEO Description 1 | SEO Title 2 |                         | 7             |
    And verifyLandingPage "<KEY>"
      | KEY | Page title     | Publish this page | SEO Description   | SEO Title   | Open Graph Image        |
      | 1   | Landing Page 1 | true              | SEO Description 1 | SEO Title 1 | front/Galleryimage.jpeg |
      | 2   | Landing Page 2 | false             | SEO Description 1 | SEO Title 2 |                         |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
