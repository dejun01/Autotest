package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.settings.steps.PaymentsSettingSteps;
import com.opencommerce.shopbase.storefront.steps.shop.TrackingEventSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.settings.steps.LegalSettingSteps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LegalDef {

    @Steps
    LegalSettingSteps legalSettingSteps;

    @Steps
    PaymentsSettingSteps paymentsSettingSteps;

    @Steps
    TrackingEventSteps trackingEventSteps;

    @And("update information of shop {string}")
    public void updateInformationOfShop(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String storeName = SessionData.getDataTbVal(dataTable, row, "Store name");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String customerEmail = SessionData.getDataTbVal(dataTable, row, "Customer Email");

            legalSettingSteps.updateData(storeName, "Store name");
            legalSettingSteps.updateData(address, "Address");
            legalSettingSteps.updateData(city, "City");
            legalSettingSteps.updateData(customerEmail, "Customer Email");
            legalSettingSteps.updateCountry(country, "Country");
        }
        paymentsSettingSteps.clickBtnSaveChanges();
    }

    @And("^input data for page policy \"([^\"]*)\"$")
    public void inputDataForPagePolicy(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String data = SessionData.getDataTbVal(dataTable, row, "data");
            String page = SessionData.getDataTbVal(dataTable, row, "page");
            if (data.trim().equals("template")) {
                legalSettingSteps.clickButtonCreateFromTemplate(page);
            } else {
                legalSettingSteps.inputIntoPagePolicy(page, data);
            }
        }
        paymentsSettingSteps.clickBtnSaveChanges();
    }

    @And("^verify content of pages on storefront \"([^\"]*)\"$")
    public void verifyContentOfPagesOnStorefront(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "page");
            String type = SessionData.getDataTbVal(dataTable, row, "type");
            String content = SessionData.getDataTbVal(dataTable, row, "content");

            String url = page.toLowerCase(Locale.ROOT).replace(" ", "-");
            String domain = LoadObject.getProperty("shop");
            String urlPage = domain + "/policies/" + url;
            legalSettingSteps.openUrl(urlPage);

            if (type.equals("template")) {
                legalSettingSteps.verifyContentPageTemplate(page, content);
            } else {
                legalSettingSteps.verifyContentPage(page, content.trim());
            }
        }
    }
}







