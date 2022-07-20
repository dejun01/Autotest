package opencommerce.plus_base;

import com.opencommerce.shopbase.plusbase.steps.FreeSubscriptionSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;

public class FreeSubscriptionDef {
    @Steps
    FreeSubscriptionSteps freesubSteps;
    int number = 0;
    String date = "";


    @Then("Verify text subcription in home with")
    public void verifyBanner(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String title = SessionData.getDataTbVal(data, row, "Title");
            freesubSteps.verifyTitleBanner(title);
            String tips = SessionData.getDataTbVal(data,row, "Tips");
            freesubSteps.verifyTips(tips);
        }
    }


    @Then("get order number default and verify banner shop activated")
    public void getOrderNumberDefault() {
        number = freesubSteps.getNumberOrder();
        freesubSteps.verifyLinkTextGetTip();
        date = freesubSteps.getDateTrialOnBanner();
        if (number==10){
            freesubSteps.verifyTextCompletedOrder();
        }else {
            freesubSteps.verifyLinkTextSeeMoreDetail();
            freesubSteps.verifyButtonBilling();
        }
    }


    @And("Search and verify payment status = {string} on order list")
    public void searchAndVerifyPaymentStatusOnOrderList(String status ) {
        freesubSteps.searchOrder(orderNumber);
        freesubSteps.verifyStatusOrder(orderNumber,status);
    }

    @And("Select order authorized")
    public void selectOrderAuthorized() {
        freesubSteps.tickSelect();
    }

    @Then("Verify information banner after approved")
    public void verifyInformationBannerAfterApproved() {
        if (freesubSteps.getNumberOrder() ==10){
            freesubSteps.verifyClaimSubs();
            freesubSteps.verifyCongratulation();
        } if (date.isEmpty()){
            assertThat(freesubSteps.getNumberOrder()).isEqualTo(number);
        } else {
            assertThat(freesubSteps.getNumberOrder()).isEqualTo(number+1);
        }
    }

    @And("cancel order plusbase")
    public void cancelOrderPlusbase() {
        freesubSteps.selectOrder(orderNumber);
        freesubSteps.clickDropAction("More actions");
        freesubSteps.clickDropAction("Cancel order");
        freesubSteps.clickDropAction("Cancel");
        freesubSteps.clickOrder();
    }

    @Then("Verify information banner after cancel")
    public void verifyInformationBannerAfterCancel() {
        assertThat(freesubSteps.getNumberOrder()).isEqualTo(number);
    }

    @And("^verify date trial shop (active|unactive) with$")
    public void verifyDateTrialShopUnactiveWith(String type, List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String mess = SessionData.getDataTbVal(data, row, "Message").replace("@date", date);
            boolean flag = "unactive".equals(type) ? true : false;
            freesubSteps.verifyDateTrialShop(mess, flag);
        }
    }
    @And("Search and verify fulfillment status = {string}")
    public void searchAndVerifyFulfillmentStatus(String status) {
        freesubSteps.searchOrder(orderNumber);
        freesubSteps.verifyStatusOrder(orderNumber,status);

    }
}