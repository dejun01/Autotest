package opencommerce.apps.crosspanda.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.AuthenCPSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.RequestListSteps;

import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

public class RequestListDef {

    @Steps
    RequestListSteps requestListSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;
    @Steps
    AuthenCPSteps authenCPSteps;

    @When("^search quotation in Request list$")
    public void search_quotation_in_Request_list(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotation = SessionData.getDataTbVal(dataTable, row, "Quotation");
            Boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            requestListSteps.searchQuotation(quotation);
            if (isShow) {
                requestListSteps.verifySearchQuotation(quotation);
            } else {
                requestListSteps.verifyNoData();
            }
            authenCPSteps.refreshPage();
        }
    }

    @Given("^verify quotation created in crosspanda \"([^\"]*)\"$")
    public void verify_quotation_in_cross_panda(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String quotationId = (String) SessionData.getDataByKey(key);
            requestListSteps.verifyLinkProductInQuotation(quotationId, link);
        }
    }

    @Given("^save quotation created in crosspanda \"([^\"]*)\"$")
    public void save_quotation_in_cross_panda(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            requestListSteps.searchQuotation(link);
            String quotationId = requestListSteps.getQuotationIDByLinkProduct(link);
            SessionData.saveDataByKey(key, quotationId);
        }
    }

    @Given("^get quotation number as \"([^\"]*)\"$")
    public void get_quotation_number_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String quotationId = SessionData.getDataTbVal(dataTable, row, "Quotation");
            if (quotationId.isEmpty()) {
                quotationId = (String) SessionData.getDataByKey(key);
            }
            String accToken = commonXPandaSteps.getAccessTokenXpanda();
            ArrayList arr = commonXPandaSteps.getSaleOrderNumber(quotationId, accToken);
            ArrayList arrID = commonXPandaSteps.getSaleOrderID(quotationId, accToken);

            for (int i = 0; i < arr.size(); i++) {
                int productId = commonXPandaSteps.getProductIdAPI((int) arr.get(i), accToken);
                String quotationNumber = quotationId + "-P" + productId + "-I" + (int) arrID.get(i);
                int index = i + 1;
                SessionData.addToHashMap(key, "Quotation" + index, quotationNumber);
            }
        }
    }

    @Given("^verify quotation details \"([^\"]*)\"$")
    public void verify_quotation_details(String dataKey, List<List<String>> dataTable) {
        String tmp = "";

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String quotationCPId = SessionData.getDataTbVal(dataTable, row, "QuotationCPId");
            String quotationId = SessionData.getDataTbVal(dataTable, row, "Quotation");
            String minimumPrice = SessionData.getDataTbVal(dataTable, row, "Minimum price");

            String minimumOrderQuantity = SessionData.getDataTbVal(dataTable, row, "Minimum order quantity");
            String minimumOrderValue = SessionData.getDataTbVal(dataTable, row, "Minimum order value");
            String estimatedTime = SessionData.getDataTbVal(dataTable, row, "Estimated time");
            String shippingFee = SessionData.getDataTbVal(dataTable, row, "Shipping fee");
            String expiration = SessionData.getDataTbVal(dataTable, row, "Expiration date");

            String variant = SessionData.getDataTbVal(dataTable, row, "Variant name");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String priceByQuantity = SessionData.getDataTbVal(dataTable, row, "Price by quantity");
            String unitPrice = SessionData.getDataTbVal(dataTable, row, "Unit price");

            String nextQuotationCPId = SessionData.getDataTbVal(dataTable, row + 1, "QuotationCPId");
            if (!quotationCPId.isEmpty())
                quotationId = (String) SessionData.getDataByKey(quotationCPId);
            if (!quotationCPId.equalsIgnoreCase(tmp)) {
                requestListSteps.searchQuotation(quotationId);
                requestListSteps.verifyQuotationExisted(quotationId);
                requestListSteps.verifyInformationQuotationByColName("Quotation", 1, quotationId);
                if (!minimumPrice.isEmpty()) {
                    requestListSteps.verifyInformationQuotationByColName("Minimum price", 1, minimumPrice);
                }
                if (!expiration.isEmpty()) {
                    requestListSteps.verifyInformationQuotationByColName("Expiration", 1, expiration);
                }
                if (!shippingFee.isEmpty()) {
                    requestListSteps.verifyInformationQuotationByColName("Shipping fee", 1, shippingFee.replace(",", "\n"));
                }
                requestListSteps.clickView(quotationId);
                if (!estimatedTime.isEmpty()){
                    requestListSteps.verifyEstimatedTime(estimatedTime);
                }
                if(!minimumOrderQuantity.isEmpty()){
                    requestListSteps.verifyMinimumOrderQuantity(minimumOrderQuantity);
                }
                if(!minimumOrderValue.isEmpty()){
                    requestListSteps.verifyMinimumOrderValue(minimumOrderValue);
                }
            }
            int index = requestListSteps.getRowVariant(variant);
            if (!unitPrice.isEmpty()) {
                requestListSteps.verifyListPriceByQuantity(priceByQuantity, index);
                requestListSteps.enterQuantity(quantity, index);
                requestListSteps.verifyUnitPrice(unitPrice, index);
                requestListSteps.verifyUnitPrice(unitPrice, index);
                requestListSteps.verifySubtotal(unitPrice, quantity, index);
                if (!nextQuotationCPId.equals(quotationCPId)) {
                    requestListSteps.verifySubtotalOfOder();
                    commonXPandaSteps.switchToMenu("Request list");
                }
                tmp = quotationCPId;
            }
        }
    }
}
