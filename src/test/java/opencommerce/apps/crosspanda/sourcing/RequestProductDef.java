package opencommerce.apps.crosspanda.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.RequestListSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.RequestProductSteps;

import common.utilities.LoadObject;
import common.utilities.SessionData;

import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RequestProductDef {
    @Steps
    RequestProductSteps requestProductSteps;
    @Steps
    RequestListSteps requestListSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;

    @When("^verify contact information$")
    public void verify_contact_information(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String fb = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String skype = SessionData.getDataTbVal(dataTable, row, "Skype");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String other = SessionData.getDataTbVal(dataTable, row, "Other");
            requestProductSteps.verifyFB(fb);
            requestProductSteps.verifySkype(skype);
            requestProductSteps.verifyPhone(phone);
            requestProductSteps.verifyOther(other);
        }
    }

    @When("^request product in CrossPanda \"([^\"]*)\"$")
    public void request_product_in_CrossPanda(String dataKey, List<List<String>> dataTable) {


        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String url = SessionData.getDataTbVal(dataTable, row, "ProductURL");
            String caseLink = SessionData.getDataTbVal(dataTable, row, "CaseLink");
            String[] urls = url.split(",");
            String quotationId = "";
            if (email.isEmpty()) {
                email = LoadObject.getProperty("xp.email");
            } else if (email.equalsIgnoreCase("@BLANK@")) {
                email = "";
            }
            requestProductSteps.enterProductURL(url);
            requestProductSteps.confirmRequest();
            if (!msg.isEmpty()) {
                requestListSteps.verifyMsg(msg);
            } else {
                requestListSteps.verifyRequestSuccess(email);
                for (int i = 1; i <= url.length(); i++) {
                    quotationId = requestListSteps.getQuotationID(i);
                    SessionData.saveDataByKey(key + i, quotationId);

                }
                if (caseLink.equalsIgnoreCase("Duplicate")) {
                    assertThat(requestListSteps.countItem(quotationId)).isEqualTo(1);
                } else {
                    assertThat(requestListSteps.countItem(quotationId)).isEqualTo(urls.length);
                }
            }
        }
    }

}
