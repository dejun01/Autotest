Feature: Buy domain
#env = plbase_buy_domain
  Background: Access to domain
    Given user login to plusbase dashboard
    And user navigate to "Online Store>Domains" screen

  Scenario: Verify connect domain with domain not buy from ShopBase
    When connect domain not buy from ShopBase domainsb
    And close browser

  Scenario: Verify connect domain with domain buy from PlusBase, redirect domain
    When connect domain buy from PlusBase domainplb
    Then verify domain connect success with
      | Title Primary  | Status    | Message                                        | Title Domain | Status Generate |
      | Primary domain | Connected | Your domain @domain was connected successfully | Domains      | Generating SSL  |
    When user connect domain
      | Domain Production          | Domain Production Test | Domain Staging             | Error msg                                                                                                       |
      | domaintest.sbaseprod.tkabc |                        | domaintest.sbasestag.tkabc | You must enter your full domain name and extension (except .TK, .ML, .GA, .CF, .GQ), e.g shopase.com            |
      | shopbase.sbprod.tk         |                        | shopbase.sbstag.tk         | Domains cannot contain keywords: shopbase, printbase, plusbase.                                                 |
      | printbase.sbprod.tk        |                        | printbase.sbstag.tk        | Domains cannot contain keywords: shopbase, printbase, plusbase.                                                 |
      | plusbase.sbprod.tk         |                        | plusbase.sbstag.tk         | Domains cannot contain keywords: shopbase, printbase, plusbase.                                                 |
      | qa-ocg.xyz                 |                        | domaintestt1.sbstag.tk     | there is 1 error\nA store with that domain name already exists.                                                 |
      | qa-ocg2.xyz                |                        | domaintestt3.sbstag.tk     | External domains purchased from other sources cannot be connected. Please read our domain policy and try again. |
    When user change primary domain to an connected domain is
      | Domain Production | Domain Production Test | Domain Staging         |
      |                   |                        | domaintestt1.sbstag.tk |
    When verify to enable redirection domain is "false"
    When verify to enable redirection domain is "true"
    And remove domain just connected
      | Title Domain | Btn Name |
      | Domains      | Remove   |
    And close browser

