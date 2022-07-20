package opencommerce.fulfillment_service.product_sourcing;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestListStep;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestSourceProductStep;
import common.utilities.DateTimeUtil;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class RequestSourceProductDef {
    @Steps
    RequestSourceProductStep requestSourceProductStep;
    public static String quotationID = "";
    @Steps
    RequestListStep requestListStep;

    public String getTheFirstQuotation() {
        quotationID = requestSourceProductStep.getTheFirstQuotation();
        return quotationID;
    }

    @Given("user go to {string} page")
    public void user_go_to_page(String namePage) {
        requestSourceProductStep.goToPage(namePage);
    }

    @Then("user input link and request quotation")
    public void user_input_link_and_request_quotation(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String tCase = SessionData.getDataTbVal(dataTable, row, "Case");
            requestSourceProductStep.enterProductURLs(link);
            if (tCase.contains("Invalid")) {
                requestSourceProductStep.verifyDisableSubmitRequest();
            } else {
                requestSourceProductStep.submitRequest();
                requestSourceProductStep.waitABit(3000);
                requestListStep.reloadPage();
                OrderVariable.quotationID = getTheFirstQuotation();
            }
        }
    }

    @Then("verify result after request product")
    public void verify_result_after_request_product(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotation = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            String url = SessionData.getDataTbVal(dataTable, row, "Link");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String warehouseProduct = SessionData.getDataTbVal(dataTable, row, "Warehouse product");
            String minimumPrice = SessionData.getDataTbVal(dataTable, row, "Minimum price");
            String expiraton = SessionData.getDataTbVal(dataTable, row, "Expiration");
            String requestAt = SessionData.getDataTbVal(dataTable, row, "Request at");
            switch (type) {
                case "Invalid":
                    requestSourceProductStep.verifyMsgRequestQuotation(msg);
                    break;
                case "Valid":
                    if (quotation.contains("@")) {
                        quotation = OrderVariable.quotationID;
                    }
                    String[] urls = url.split(",");
                    int countLink = urls.length;
                    requestListStep.reloadPage();
                    requestListStep.switchToTabInRequestList(tab);
                    requestListStep.verifyQuotationIsCreatedOnSbase(countLink, tab);
                    if (countLink == 1) {
                        requestListStep.searchQuotationId(quotation);
                        requestListStep.waitSearchResult(quotation);
                        requestListStep.verifyLinkProduct(url);
                    }
                    if(!warehouseProduct.isEmpty()){
                        requestListStep.verifyWarehouseProduct(warehouseProduct);
                    }
                    if(!minimumPrice.isEmpty()){
                        requestListStep.verifyMinimumPrice(minimumPrice);
                    }
                    if(!expiraton.isEmpty()){
                        if(expiraton.contains("@")){
                            expiraton = DateTimeUtil.getDateFormat();
                        }
                        requestListStep.verifyExpiraton(expiraton);
                    }
                    if(requestAt.contains("@")){
                        requestListStep.verifyRequestAt(requestAt);
                    }
                    break;
            }

        }
    }

    @Then("user input data in Contact information then verify data")
    public void user_input_data_in_Contact_information_then_verify_data(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String skype = SessionData.getDataTbVal(dataTable, row, "Skype");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String fb = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");
            requestSourceProductStep.inputEmailToCustomerInfor(email);
            requestSourceProductStep.inputSkypeToCustomerInfor(skype);
            requestSourceProductStep.inputPhoneToCustomerInfor(phone);
            requestSourceProductStep.inputFBToCustomerInfor(fb);
            if (!msg.isEmpty()) {
                requestSourceProductStep.verifyMsgInvalidCustomerInfor(msg);
                requestSourceProductStep.verifyDisableSaveCustomerInfor();
            } else {
                requestSourceProductStep.saveCustomerInfor();
                requestSourceProductStep.verifyDataCustomerInfor(email, skype, phone, fb);
            }
            requestListStep.reloadPage();

        }
    }


}
