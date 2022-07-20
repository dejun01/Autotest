package com.opencommerce.shopbase.hive.pbaseorder.steps;

import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign.CampaignPage;
import com.opencommerce.shopbase.hive.pbaseorder.pages.PBaseOrderPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Java6Assertions;
import org.junit.Assert;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class PBaseOrderStep {

    PBaseOrderPage pBaseOrderPage;
    CampaignPage campaignPage;

    public static String orderID;
    public static Integer currentVersion;
    public static Integer version;

    @Step
    public void openHivePbase(String linkhive) {
        pBaseOrderPage.openUrl(linkhive);
        pBaseOrderPage.maximizeWindow();
    }

    @Step
    public void signInHivePbase(String email, String pwd) {
        pBaseOrderPage.enterInputFieldWithLabel("Username", email);
        pBaseOrderPage.enterInputFieldWithLabel("Password", pwd);
        pBaseOrderPage.Login();
        pBaseOrderPage.verifyLoginSS();
    }

    @Step
    public void navigateToPBase(int orderId) {
        String url = LoadObject.getProperty("orderDetail");
        pBaseOrderPage.navigateToUrl(url + orderId + "/edit");
        pBaseOrderPage.waitForPageLoad();
    }

    @Step
    public void approvedOrder() {
        pBaseOrderPage.waitForEnableBtnApproved();
        pBaseOrderPage.clickBtn("Approve");
        pBaseOrderPage.verifyApprovedSuccess();
    }

    @Step
    public void clickBtnRefundOrderPBase() {
        pBaseOrderPage.clickBtnRefundOrderPBase();
    }

    @Step
    public void inputRefundOrder(String refundLineitem) {
        if (refundLineitem.contains(";")) {
            String[] item = refundLineitem.split(";");
            for (int i = 0; i <= item.length; i++) {
                pBaseOrderPage.inputRefundOrder(item[i]);
            }
        } else {
            pBaseOrderPage.inputRefundOrder(refundLineitem);
        }
    }

    @Step
    public void refundOrderPBase() {
        pBaseOrderPage.clickBtn("refund");
    }

    @Step
    public void checkRefundforSeller() {
        pBaseOrderPage.checkRefundforSeller();
    }

    @Step
    public void checkRefundforBuyer() {
        pBaseOrderPage.checkRefundforBuyer();
    }

    @Step
    public void clickBtnCancelOrderPBase() {
        pBaseOrderPage.clickBtnCancelOrderPBase();
    }

    @Step
    public void cancelOrderPBase() {
        pBaseOrderPage.clickBtn("cancel");
        pBaseOrderPage.verifyCancleOrderSuccessfully();
    }

    @Step
    public void clickBtnManualFulfillPBase() {
        pBaseOrderPage.clickBtnManualFulfillPBase();
    }

    @Step
    public void inputManualFulfillOrder(String fulfillLineitem) {
        if (fulfillLineitem.contains(";")) {
            String[] item = fulfillLineitem.split(";");
            for (int i = 0; i <= item.length; i++) {
                pBaseOrderPage.inputFulfillOrder(item[i]);
            }
        } else {
            pBaseOrderPage.inputFulfillOrder(fulfillLineitem);
        }
    }

    @Step
    public void inputTrackingNumberPod(String trackingNumber) {
        pBaseOrderPage.inputTrackingNumberPod(trackingNumber);
    }

    @Step
    public void inputTrackingUrlPod(String trackingUrl) {
        pBaseOrderPage.inputTrackingUrlPod(trackingUrl);
    }


    public void markAsFulfillPod() {
        pBaseOrderPage.clickBtn("Mark as fulfilled");
    }

    public void verifyTKNOrder(String tkn) {
        int index = pBaseOrderPage.getIndexColum("Tracking No");
        String act = pBaseOrderPage.verifyTKNOrder(index);
        assertThat(act).isEqualTo(tkn);
    }

    @Step
    public void enterOrderCondition(String condition, String value) {
        switch (condition) {
            case "Order Name":
                pBaseOrderPage.enterOrderName(value);
                break;
            case "Domain":
                pBaseOrderPage.enterOrderDomain(value);
                break;
        }

    }

    @Step
    public void clickFilter(String name) {
        pBaseOrderPage.clickFilter(name);

    }

    @Step
    public void clickBTApproved() {
        pBaseOrderPage.clickBTApproved();

    }

    @Step
    public String getTextOrderNameDetail() {
        return pBaseOrderPage.getTextOrderNameDetail();

    }

    @Step
    public void verifyDisplayBTManualFulfill() {
        pBaseOrderPage.verifyDisplayBTManualFulfill();
    }

    @Step
    public void veryDisableBTApproved() {
        pBaseOrderPage.veryDisableBTApproved();
    }


    @Step
    public void verifyInfOrder(List<String> orderList, String fulfillmentStatus, String paymentStatus, String approvedDate) {
        for (String orderName : orderList) {
            pBaseOrderPage.verifyPaymentStatusOrder(orderName, paymentStatus);
            pBaseOrderPage.verifyFulfillmentStatusOrder(orderName, fulfillmentStatus);
            pBaseOrderPage.verifyApprovedDateOrder(orderName, approvedDate);
        }
    }

    @Step
    public String getTextError() {
        String[] text = pBaseOrderPage.getTextError().split("\n");
        return text[1].trim();

    }

    @Step
    public void clickOrderApproved(List<String> orderNameList) {
        for (String orderName : orderNameList) {
            pBaseOrderPage.clickOrderApproved(orderName);
        }

    }

    @Step
    public void approvedMultiOrder() {
        pBaseOrderPage.approvedMultiOrder();
    }

    @Step
    public void accPage(String page) {
        pBaseOrderPage.accPage(page);
    }

    @Step
    public void clickOrderDetail(String orderNumber) {
        pBaseOrderPage.clickOrderDetail(orderNumber);

    }

    @Step
    public void clickBTCaculate() {
        pBaseOrderPage.clickBTCaculate();
    }

    @Step
    public void confirmApproved() {
        pBaseOrderPage.confirmApproved();
    }

    @Step
    public void chooseQuantity(int quantity) {
        pBaseOrderPage.chooseQuantity(quantity);
    }

    @Step
    public void navigateToEditUserPage(String linkHive, String userId) {
        pBaseOrderPage.navigateToUrl(linkHive + "app/shopuser/" + userId + "/edit");
    }

    @Step
    public void selectAction(String type, String action, String quantity) {
        pBaseOrderPage.chooseAction(type, action);
        pBaseOrderPage.inputQuantity(type, quantity);
    }

    @Step
    public void clickBtnUpdate() {
        pBaseOrderPage.clickBtn("Update");
    }

    @Step
    public void verifyBcoinAndAvailableBcoin(HashMap<String, String> pre, HashMap<String, String> cur, String type, String action, String quantity) {
        for (String i : pre.keySet()) {
            for (String j : cur.keySet()) {
                if (type.equalsIgnoreCase(i) && i.equalsIgnoreCase(j)) {
                    switch (action) {
                        case "+":
                            assertThat(Double.parseDouble(cur.get(type))).isEqualTo(Double.parseDouble(pre.get(type)) + Double.parseDouble(quantity));
                            break;
                        case "-":
                            assertThat(Double.parseDouble(cur.get(type))).isEqualTo(Double.parseDouble(pre.get(type)) - Double.parseDouble(quantity));
                            break;
                    }
                }
            }
        }
    }

    public void navigateToCancelOrderPBase(int orderId) {
        String url = LoadObject.getProperty("cancelOrder");
        pBaseOrderPage.navigateToUrl(url + orderId + "/cancel?isPHub=0");
        pBaseOrderPage.waitForPageLoad();
    }

    public void getOrderID() {
        orderID = pBaseOrderPage.getOrderID();
    }

    public void navigateToOrderDetail() {
        pBaseOrderPage.navigateToOrderDetail(orderID);
    }

    public void getVersionOrder() {
        try {
            currentVersion = pBaseOrderPage.getVersionOrder();
            System.out.println(currentVersion);
            version = currentVersion + 1;
            System.out.println(version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyVersion() {
        assertThat(currentVersion).isNotNull();
    }

    public void verifyCampVersion() {
        assertThat(version).isEqualTo(currentVersion);
    }

    public void navigateToOrderDetailInHive(String typeOrder, int orderId) {
        String url = LoadObject.getProperty("hive");
        switch (typeOrder) {
            case "Phub":
                url = url + "/phub-order/";
                break;
            case "Pbase":
                url = url + "/pbase-order/";
                break;
        }
        url = url + orderId + "/edit";
        pBaseOrderPage.navigateToUrl(url);
    }

    public Integer getVersionInOrder() throws Exception {
        return currentVersion = pBaseOrderPage.getVersionOrder();
    }
}
