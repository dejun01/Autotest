Feature: Verify sync on SF after edit clipart
#EVM: pbase_sync_clipart_folder

  Scenario:  Verify sync clipart folder #SB_PRB_QPC_289 #SB_PRB_QPC_290 #SB_PRB_QPC_291 #SB_PRB_QPC_292 #SB_PRB_QPC_293 #SB_PRB_QPC_295 #SB_PRB_QPC_296 #SB_PRB_QPC_297 #SB_PRB_QPC_318 #SB_PRB_QPC_319 SB_PRB_QPC_320 #SB_PRB_QPC_332
    Given user login to shopbase dashboard
    And user navigate to "Library>Cliparts" screen
    And open folder clipart "Clipart 1" detail
    And click delete all image
    Then add image clipart folder
      | Image          |
      | green.png      |
      | logoheader.jpg |
      | Logo1.jpg      |
      | dark.jpeg      |
    And sort clipart and verify after sort
      | Clipart    | Action           | Position | List clipart                |
      | logoheader | Move to top      |          | logoheader,dark,Logo1,green |
      | dark       | Move to bottom   |          | logoheader,Logo1,green,dark |
      | green      | Move to position | 2        | logoheader,green,Logo1,dark |
    Given open shop on storefront
    And search and select the product "Cam clipart"
    Then verify information picture choice display as image Thumbnail on SF as "01"
      | KEY | Name image                  |
      | 01  | logoheader,green,Logo1,dark |



