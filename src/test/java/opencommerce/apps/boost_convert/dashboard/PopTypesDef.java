package opencommerce.apps.boost_convert.dashboard;

import com.opencommerce.shopbase.storefront.steps.apps.boost_convert.BoostConvertShopSteps;
import com.opencommerce.shopbase.dashboard.apps.boostconvert.steps.PopTypesSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class PopTypesDef {
    @Steps
    PopTypesSteps popTypesSteps;
    @Steps
    BoostConvertShopSteps shopifySteps;

    @When("^user turn \"([^\"]*)\" \"([^\"]*)\"$")
    public void user_if_toggle_of_type_is(String status, String typeNoti) {
        popTypesSteps.waitABit(5000);
        boolean expStatus = shopifySteps.convertStatus(status);
        popTypesSteps.turnOnOffNotiType(typeNoti, expStatus);
        popTypesSteps.waitABit(5000);

    }

    @Given("^change setting sales notifications of BoostConvert as \"([^\"]*)\"$")
    public void change_setting_sales_notifications_of_BoostConvert_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String sProductName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String sTime = SessionData.getDataTbVal(dataTable, row, "Time");
            String trigger = SessionData.getDataTbVal(dataTable, row, "Trigger");
            String customPage = SessionData.getDataTbVal(dataTable, row, "Custom pages");
            System.out.println(sTitle + sProductName + sTime + customPage);
            popTypesSteps.clickSettings("Sales notifications");
            popTypesSteps.enterTitle(sTitle);
            popTypesSteps.enterProductName(sProductName);
            popTypesSteps.enterTime(sTime);
            popTypesSteps.triggerOnPages(trigger, customPage);
            popTypesSteps.saveSetting();
        }

    }

    @Given("^change checkout notification settings of BoostConvert as \"([^\"]*)\"$")
    public void change_checkout_notification_settings_of_BoostConvert_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sMessage = SessionData.getDataTbVal(dataTable, row, "Message");
            popTypesSteps.clickSettings("Checkout notifications");
            popTypesSteps.enterMessage(sMessage);
            popTypesSteps.saveSetting();
        }
    }

    @When("^reset to default sales notification settings of BoostCovert$")
    public void reset_to_default_sales_notification_settings_of_BoostCovert() {
        popTypesSteps.clickSettings("Sales notifications");
        popTypesSteps.clickResetToDefault();
        popTypesSteps.triggerOnPages("","");
        popTypesSteps.saveSetting();

    }


}
