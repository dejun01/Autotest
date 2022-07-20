package opencommerce.plus_base.settings;

import com.opencommerce.shopbase.plusbase.steps.PaymentProvidersSteps;
import com.opencommerce.shopbase.plusbase.steps.ShippingSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import static com.opencommerce.shopbase.SettingsVariables.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ShippingDef {
    @Steps
    PaymentProvidersSteps paymentProvidersSteps;
    @Steps
    ShippingSteps shippingSteps;

    public static String afterRate = "";
    public static Double total = 0.00d;
    public static Double payment_fee = 0.00d;
    public static Double processing_fee = 0.00d;
    public static Double handing_fee = 0.00d;
    public static Double base_cost = 0.00d;
    public static String sku;

    @And("Setting shipping zone and verify")
    public void settingShippingZoneAndVerify(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shipping = SessionData.getDataTbVal(dataTable, row, "shipping");
            String beforeRate = SessionData.getDataTbVal(dataTable, row, "free shipping rate setting");
            afterRate = SessionData.getDataTbVal(dataTable, row, "free shipping rate after setting");
            shippingSteps.editRate(shipping, beforeRate);
            paymentProvidersSteps.clickBTSaveChanges();
            assertThat(shippingSteps.getValueAfterEdit(shipping)).isEqualTo(afterRate);
        }
    }

    @Then("verify shipping free")
    public void verifyShippingFree(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sBaseRules = SessionData.getDataTbVal(dataTable, row, "Base rules");
            String sPrice = SessionData.getDataTbVal(dataTable, row, "Price");
            paymentProvidersSteps.verifyShippingPrice(sBaseRules,sPrice);
        }
    }
    @And("creat profile with zone as {string}")
    public void creatProfileWithZoneAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet())  {
            String zoneName = SessionData.getDataTbVal(dataTable, row, "Zone name");
            countries = SessionData.getDataTbVal(dataTable, row, "Countries");
            shippingSteps.clickBtnCreateShippingZone();
            shippingSteps.enterZoneName(zoneName);
            shippingSteps.searchThenChooseCountries(countries);
            shippingSteps.clickBtnDone();
        }
    }

    @And("add rates for zone as {string}")
    public void addRatesForZoneAs(String dataKey, List<List<String>> dataTable ) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            rateName = SessionData.getDataTbVal(dataTable, row, "Rate name");
            price = SessionData.getDataTbVal(dataTable, row, "Price");
            additionalCondition = SessionData.getDataTbVal(dataTable, row, "Additional condition");
            shippingSteps.clickBtnAddRate();
            shippingSteps.enterRateName(rateName);
            shippingSteps.enterPrice(price);
            if (!additionalCondition.isEmpty()) {
                shippingSteps.enterCondition(additionalCondition);
            }
        }
        shippingSteps.clickBtnDone();
    }

    @And("add product rules for Custom Profile as {string}")
    public void addProductRulesForCustomProfileAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            profileName = SessionData.getDataTbVal(dataTable, row, "Profile name");
            shippingSteps.addProductRules(sku);
            shippingSteps.enterProfileName(profileName);
        }
    }


    @And("click button to create new profile")
    public void clickButtonToCreateNewProfile() {
        shippingSteps.creatANewProfile();
    }

    @And("click  Save changes button")
    public void clickButtonSaveChanges() {
        shippingSteps.clickBtnSaveChangesProfile(profileName);
    }

}
