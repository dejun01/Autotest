package opencommerce.apps.boost_convert.dashboard;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.steps.SettingSteps;

import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


import java.util.List;

public class SettingDef {
    @Steps
    SettingSteps settingSteps;
    @Steps
    ProductSteps productSteps;

    @Then("^change settings of BoostConvert as \"([^\"]*)\"$")
    public void change_setting_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sLayout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String sTheme = SessionData.getDataTbVal(dataTable, row, "Theme settings");
            String sDesktopPosition = SessionData.getDataTbVal(dataTable, row, "Desktop position");
            String sMobilePosition = SessionData.getDataTbVal(dataTable, row, "Mobile position");
            boolean sShowOnMobile = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show on mobile"));
            String sDisplayTime = SessionData.getDataTbVal(dataTable, row, "Display time");
            String sMaximumPerPage = SessionData.getDataTbVal(dataTable, row, "Maximum per page");
            boolean sDisplayNotification = Boolean.getBoolean(SessionData.getDataTbVal(dataTable, row, "Display notification in random order"));
            String sDelayTimeBetweenNotification = SessionData.getDataTbVal(dataTable, row, "Delay time between notifications");
            String sDelayTimeShowPopUp = SessionData.getDataTbVal(dataTable, row, "Delay time to show popup after loading");
            Boolean sRandomizeDelayTime = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Randomize delay time"));
            Boolean sRepeatSaleNotification = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, " Repeat sales notification"));
            String sOnlyDispalySynced = SessionData.getDataTbVal(dataTable, row, " Only dispaly synced");
            settingSteps.selectLayOut(sLayout);
            settingSteps.settingTheme(sTheme);
            settingSteps.selectDesktopPosition(sDesktopPosition);
            settingSteps.selectMobilePosition(sMobilePosition);
            settingSteps.checkShowOnMobile(sShowOnMobile);
            settingSteps.inputDislayTime(sDisplayTime);
            settingSteps.inputMaximumPerPage(sMaximumPerPage);
            settingSteps.checkDisplayNotiofication(sDisplayNotification);
            settingSteps.inputDelayTime(sDelayTimeBetweenNotification);
            settingSteps.inputDelayTimeShowPopUp(sDelayTimeShowPopUp);
            settingSteps.checkRanDomizeDelayTime(sRandomizeDelayTime);
            settingSteps.checkRepeatSale(sRepeatSaleNotification);
            settingSteps.inputOnlyDisplaySynced(sOnlyDispalySynced);
            settingSteps.clickButtonSave();
        }

    }

    @And("^verify settings sales notification on ShopBase as \"([^\"]*)\"$")
    public void verifyShowNotificationOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sLayout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String sBasictheme = SessionData.getDataTbVal(dataTable, row, "Basic theme");
            String sdesktopPosition = SessionData.getDataTbVal(dataTable, row, "Desktop position");
            productSteps.searchAndSelectProduct("Apple");
            settingSteps.verifyShowNoti();
            settingSteps.verifyLayout(sLayout);
            settingSteps.verifyBasicTheme(sBasictheme);
            settingSteps.verifydestopPosition(sdesktopPosition);
        }
    }
}
