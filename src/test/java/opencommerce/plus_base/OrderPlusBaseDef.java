package opencommerce.plus_base;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillmentSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.plusbase.steps.OrderPlusBaseStep;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.balance.steps.BalanceSteps;

import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps.orderState;


public class OrderPlusBaseDef {
    @Steps
    ShippingMethodSteps shippingMethodSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    FulfillmentSteps orderStep;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    OrderSteps orderSteps;
    String status = "";
    @Steps
    BalanceSteps balanceSteps;

    @Steps
    OrderPlusBaseStep orderPlusBaseSteps;

    int originalQuantity, refundedQuantity, remainingQuantity = 0;

    @And("verify order with fulfillment status = {string}")
    public void verifyOrderWithFulfillmentStatus(String status, List<List<String>> data) {
        orderPlusBaseSteps.clickOrder(orderNumber);
        orderPlusBaseSteps.verifyStatus(status);
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String sku = SessionData.getDataTbVal(data, row, "SKU");
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            orderPlusBaseSteps.verifyLineItem(product, sku, quantity);
        }
    }

    float discount = 0;
    float pendingToReview = 0;
    float profit = 0;
    boolean isPPCDiscount = false;
    String productPPC = "";
    float subtotalOrder = 0;
    float tax = 0.00f;
    @And("verify paid by customer of order")
    public void verifyPaidByCustomerOfOrder(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            String discountName = SessionData.getDataTbVal(data, row, "Discount name");
            boolean discountFreeship = "yes".equals(SessionData.getDataTbVal(data, row, "Freeship")) ? true : false;
            boolean shippingFreeship = "yes".equals(SessionData.getDataTbVal(data, row, "Shipping freeship")) ? true : false;
            boolean flagPPC = "yes".equals(SessionData.getDataTbVal(data, row, "Post purchase")) ? true : false;
            productPPC = SessionData.getDataTbVal(data, row, "Product PPC");
            isPPCDiscount = "yes".equals(SessionData.getDataTbVal(data, row, "PPC discount")) ? true : false;
            subtotalOrder = covertToFloat(subTotal);
            if (isPPCDiscount) {
                subtotalOrder += orderPlusBaseSteps.getValueDiscountPPC(productPPC);
            }
            orderPlusBaseSteps.verifyPaymentGroup("Subtotal", quantity, covertToString(subtotalOrder));
            String shipping = orderPlusBaseSteps.getShippingInPaymentGroup(discountFreeship);
            orderPlusBaseSteps.verifyShippingMethod("Shipping", shippingFreeship, shipping);
            discount = orderPlusBaseSteps.getDiscount(discountName, discountFreeship, flagPPC, productPPC);
            if(!discountName.isEmpty()) {
                orderPlusBaseSteps.verifyPaymentGroup("Discount", discountName, covertToString(discount));
            }
            tax = orderPlusBaseSteps.getTax();
            float total = subtotalOrder + covertToFloat(shipping) - discount + tax;
            orderPlusBaseSteps.verifyTotal(round(total));
            orderPlusBaseSteps.verifyPaymentGroup("Paid by customer", "", "$0.00");
        }
    }

    private Float covertToFloat(String val) {
        return orderPlusBaseSteps.covertToFloat(val);
    }

    private String covertToString(float val) {
        return Float.toString(val);
    }


    @And("verify your profit of order with Is adjusted = {string}")
    public void verifyYourProfitOfOrder(String isAdjusted) {
        int paymentFeePercent = Integer.parseInt(LoadObject.getProperty("paymentFeePercent"));
        int processingFeePercent = Integer.parseInt(LoadObject.getProperty("processingFeePercent"));
        orderPlusBaseSteps.clickOpenProfitGroup("Show calculation");
        orderPlusBaseSteps.clickOpenHanldingFee();

        float revenue = subtotalOrder - discount;
        orderPlusBaseSteps.verifyProfitGroup("Subtotal", covertToString(subtotalOrder));
        orderPlusBaseSteps.verifyProfitGroup("Discount", covertToString(discount));
        orderPlusBaseSteps.verifyProfitGroup("Refund", "$0.00");
        orderPlusBaseSteps.verifyProfitGroup("Revenue", covertToString(revenue));
        float baseCost = orderPlusBaseSteps.getValueProfitGroup("Product cost");
        float shippingFee = orderPlusBaseSteps.getShippingFee("Shipping fee");
        float total = orderPlusBaseSteps.getTotal();

        float adjusted = 0;
        if(Boolean.parseBoolean(isAdjusted)) {
            adjusted = orderPlusBaseSteps.getAdjustedValue();
            shippingFee = 0;
        }

        float paymentFee = round((total * paymentFeePercent) / 100);
        orderPlusBaseSteps.verifyHandlingFeeGroup("Payment fee", paymentFee);
        float processingFee = round((revenue - baseCost + adjusted - paymentFee - shippingFee) * processingFeePercent / 100);
        orderPlusBaseSteps.verifyHandlingFeeGroup("Processing fee", processingFee);
        float handlingFee = paymentFee + processingFee;
        orderPlusBaseSteps.verifyHandlingFeeGroup("Handling fee", handlingFee);

        profit = revenue - baseCost - handlingFee - shippingFee + adjusted;
        orderPlusBaseSteps.verifyProfit("Profit", profit);
    }

    private float round(float value) {
        return orderPlusBaseSteps.round(value);
    }

    @And("verify balance of order with status = {string}")
    public void verifyBalanceOfOrderWithStatus(String status) {
        if(!orderPlusBaseSteps.isData()){
            orderPlusBaseSteps.refreshPage();
        }
        orderPlusBaseSteps.verifyValueBalance("Total", pendingToReview);
        String shopname = LoadObject.getProperty("shop");
        balanceSteps.selectStore(shopname);
        orderPlusBaseSteps.moveToViewHistory(orderNumber, status);
        orderPlusBaseSteps.verifyInvoiceDetail(profit);
    }

    @And("^get balance before (checkout|refund) order$")
    public void getBalanceBeforeCheckoutOrder(String type) {
        if(!orderPlusBaseSteps.isData()){
            orderPlusBaseSteps.refreshPage();
        }
        pendingToReview = orderPlusBaseSteps.getValueBalance("Total");
    }

    @Then("Search and verify payment status = {string} and Approve status = {string} on order list")
    public void searchAndVerifyPaymentStatusAndApproveStatusOnOrderList(String paymentStatus, String approveStatus) {
        orderPlusBaseSteps.searchOrderName(orderNumber);
        status = approveStatus;
        orderPlusBaseSteps.verifyStatus(orderNumber, paymentStatus, approveStatus);
    }

    @Given("search and select order on plusbase")
    public void search_and_select_order_on_plusbase() {
        orderPlusBaseSteps.searchOrderName(orderNumber);
        orderPlusBaseSteps.clickOrder(orderNumber);
    }

    @Then("Search multi orders and verify payment status = {string} and Approve status = {string} on order list")
    public void searchMultiOrdersAndVerifyFulfillmentStatusAndApproveStatusOnOrderPbaseList(String paymentStatus, String approveStatus) {
        for (String orderName : orderNameList) {
            orderPlusBaseSteps.searchOrderName(orderName);
            orderPlusBaseSteps.verifyStatus(orderName, paymentStatus, approveStatus);
        }
    }

    @And("Choose order actions")
    public void chooseOrderActions() {
        orderPlusBaseSteps.chooseOrder(orderNumber);

    }

    @And("Choose multi order actions")
    public void chooseMultiOrderActions() {
            orderPlusBaseSteps.chooseOrder(orderNameList.get(0));
            orderPlusBaseSteps.chooseOrder(orderNameList.get(1));
    }

    @And("{string} order")
    public void order(String action) {
            orderPlusBaseSteps.clickBTAction(action);
    }

    @Then("verify order detail on store")
    public void verifyOrderDetailOnStore() {
        String customerName = orderSteps.getCustomerName();
        int i = 1;
        for (String products : listProduct) {

            String[] _number = products.split("\\|");
            String productName = _number[0];
            String _price = _number[1];
            String _quantity = _number[2];

            thankyouSteps.logInfor("Product name : " + productName + " has price: " + _price + " Quantity :" + _quantity + " Total :" + totalAmt);
            if (!discountAmount.isEmpty()) {
                orderSteps.verifyProductInformation(i, productName, _quantity, _price, discountAmount);
            } else {
                orderSteps.verifyProductInformation(i, productName, _quantity, _price);
            }
            i++;
        }


        if (!paymentGateway.equalsIgnoreCase("COD")) {
            orderState = "Paid";
        } else {
            orderState = "Pending";
        }
        orderSteps.verifyOrderStatus(orderState);
        orderSteps.verifyShippingOnDB(shippingFee);
        orderPlusBaseSteps.verifyOrderStatusApproved(status);
        if (!paymentGateway.equalsIgnoreCase("PayPal")) {
            //verify shipping address
            System.out.println("shippingAddress: " + shippingAddress);
            orderSteps.verifyAddressOnDB("SHIPPING ADDRESS", shippingAddress);
            //verfiy billing address
            if (billingAddress.isEmpty()) {
                billingAddress = "Same as shipping address";
            }
            orderSteps.verifyAddressOnDB("Billing address", billingAddress);
        }
        if (float_totalAmt > 0) {
            if (isOnPostPurchase && (paymentGateway.equalsIgnoreCase("PayPal") | paymentGateway.equalsIgnoreCase("Unlimint") | paymentGateway.equalsIgnoreCase("Asia-Bill"))) {
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, "$" + paidTotalAmtByPaypal, cardType, endingCardNo);
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, "$" + roundOffTo2DecPlaces(float_totalAmt), cardType, endingCardNo);
            } else if (isOnPostPurchase && paymentGateway.equalsIgnoreCase("Oceanpayment")) {
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, "$" + paidTotalAmtByPaypal, cardType, endingCardNo);
            } else {
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, totalAmt.replace("USD","").trim(), cardType, endingCardNo);

            }
        }

    }

    private String roundOffTo2DecPlaces(float val) {
        return String.format("%.2f", val);
    }

    @Given("go to {string} page from More action in PlusBase")
    public void go_to_page_from_More_action_in_PlusBase(String page) {
        trackingNumber = orderPlusBaseSteps.getTrackingNumberLineItem();
        orderSteps.clickOnMoreActionBtn();
        orderSteps.clickOnBtnInMoreAction(page);
    }

    @Then("fulfill order in Order detail PlusBase")
    public void fulfill_order_in_Order_detail_PlusBase() {
        orderPlusBaseSteps.clickButton("Fulfill order");
    }

    @Then("Verify display info order list")
    public void verifyInfoOrderList(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String infoAction = SessionData.getDataTbVal(dataTable, row, "Info Action");
            String tableName = SessionData.getDataTbVal(dataTable, row, "Table Name");
            String nameTab = SessionData.getDataTbVal(dataTable, row, "Name Tab");
            orderPlusBaseSteps.verifyBTAction(infoAction);
            orderPlusBaseSteps.verifyTableName(tableName);
            orderPlusBaseSteps.verifyNameTab(nameTab);
        }
    }

    @Then("Verify not edit info in order detail of merchant store")
    public void verifyOrderDetailOfMerchantStore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String notEditInfo = SessionData.getDataTbVal(dataTable, row, "Not Edit Info");
            orderPlusBaseSteps.verifyNotDisplayEdit(notEditInfo);
        }
    }

    @Then("Search and verify payment status = {string} and fulfillment status = {string} on order list plusbase")
    public void searchAndVerifyPaymentStatusAndFulfillmentStatusOnOrderListPlusbase(String paymentStatus, String fulfillStatus) {
        orderPlusBaseSteps.searchOrderName(orderNumber);
        orderPlusBaseSteps.verifyStatusOnStoreMerchant(orderNumber, paymentStatus, fulfillStatus);
    }

    @And("Get info order before refund")
    public void getInfoOrderBeforeRefund() {
        originalPrice = orderSteps.getOriginalPriceBeforeRefunding(refundedProduct);

        if (!discountAmount.isEmpty()) {
            discountedPrice = orderSteps.getDiscountedPriceBeforeRefunding(refundedProduct);
        }
        originalQuantity = orderSteps.getOriginalQuantityBeforeRefunding(refundedProduct);
    }

    @And("Refund order")
    public void refundOrder() {
        orderPlusBaseSteps.clickBTRefundOrder();
    }

    @And("Fulfill order PlusBase")
    public void fulfillOrderPlusBase() {
        orderSteps.fulfillOrderPlusBase();
    }

    @And("fulfill order with info")
    public void fulfillOrderWithInfo(List<List<String>>dataTable) {
        orderSteps.clickOutPage();
        orderStep.searchOrderName(orderNumber);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String buttonName = SessionData.getDataTbVal(dataTable, row, "Action");
            boolean purchaseAuto = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Purchase auto"));
            String buttonFulfill = SessionData.getDataTbVal(dataTable, row, "Button fulfill");
            String notification = SessionData.getDataTbVal(dataTable, row, "Notification");
            String buttonSelectedOrder = SessionData.getDataTbVal(dataTable, row, "Button selected order");
            orderStep.clickButton(buttonName);
            orderStep.clickButton(buttonFulfill);
            if(!purchaseAuto) {
                orderStep.verifyTurnOffAutoPurchase(notification, buttonSelectedOrder);
            } else {
                orderSteps.fulfillmentOrderPlusBase(buttonSelectedOrder);
            }

        }
    }

    @Then("Search and verify fulfillment status = {string} and Approve status = {string} on order list")
    public void searchAndVerifyFulfillmentStatusAndApproveStatusOnOrderList(String fulfillmentStatus, String approveStatus) {
        orderSteps.searchOrderNameOnOrderList(orderNumber);
        status = approveStatus;
        orderPlusBaseSteps.verifyStatusFulfilment(orderNumber, fulfillmentStatus, approveStatus);

    }

    @Then("Search multi order and verify payment status = {string} and fulfillment status = {string} on order list plusbase")
    public void searchMultiOrderAndVerifyPaymentStatusAndFulfillmentStatusOnOrderListPlusbase(String paymentStatus, String fulfillmentStatus) {
        for (String orderName : orderNameList) {
            orderSteps.searchOrderNameOnOrderList(orderName);
            orderPlusBaseSteps.verifyStatusOnStoreMerchant(orderName, paymentStatus, fulfillmentStatus);
        }
    }

    float currentProfit = 0;
    float discountOrder = 0;
    @Then("verify Order detail with")
    public void verifyOrderDetailWith(List<List<String>> data) {
        orderSteps.clickNameOrderOnList(orderNumber);
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String status = SessionData.getDataTbVal(data, row, "Paymnet status");
            String product = SessionData.getDataTbVal(data, row, "Product");
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            orderPlusBaseSteps.verifyProduct(product, quantity);
            orderPlusBaseSteps.verifyStatus(status);
        }
        currentProfit = orderPlusBaseSteps.getProfit("Profit");
        orderPlusBaseSteps.clickOpenProfitGroup("Show calculation");
        discountOrder = orderPlusBaseSteps.getValueProfitGroup("Discount");
        orderPlusBaseSteps.clickMoreAction("More actions");
        orderPlusBaseSteps.clickMoreAction("Approve order");
        orderPlusBaseSteps.clickButton("Confirm");
        orderPlusBaseSteps.clickMoreAction("Refund order");
    }

    HashMap<String, Float> dataBeforeRefundForSeller = new HashMap<>();
    HashMap<String, Float> dataRefundForBuyer = new HashMap<>();
    boolean isWithdrawSellerBalance = true;
    float in = 0;
    float out = 0;
    @When("Refund order plusbase with")
    public void refundOrderPlusbaseWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String domain = SessionData.getDataTbVal(data, row, "Domain").replace("@domain@", LoadObject.getProperty("shop"));
            String orderName = SessionData.getDataTbVal(data, row, "Order name").replace("@order name@", orderNumber);
            String product = SessionData.getDataTbVal(data, row, "Product");
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            isWithdrawSellerBalance = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Withdraw seller balance"));
            boolean isSendMail = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Send mail"));
            boolean isChoiceQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Choice quantity"));
            orderPlusBaseSteps.verifyProduct(product, quantity);
            orderPlusBaseSteps.verifyInfo(domain);
            orderPlusBaseSteps.verifyInfo(orderName);
            if(isChoiceQuantity) {
                orderPlusBaseSteps.choiceQuantity(quantity, product);
            } else {
                orderPlusBaseSteps.EnterValueByLabel("Request to refund for buyer", "Refund selling price");
            }
            orderPlusBaseSteps.checkCheckbox("Do not withdraw from Seller's balance", isWithdrawSellerBalance);
            orderPlusBaseSteps.checkCheckbox("Send notification to the buyer", isSendMail);
        }
        orderPlusBaseSteps.EnterShippingFee("Refund shipping fee");
        orderPlusBaseSteps.EnterValueByLabel("Request to refund for buyer", "Refund payment fee");
        dataBeforeRefundForSeller = orderPlusBaseSteps.getAmountBeforeRefund("Request to refund for seller");
        dataRefundForBuyer = orderPlusBaseSteps.amountRefund("Request to refund for buyer");
        orderPlusBaseSteps.focusOut();
        orderPlusBaseSteps.clickMoreAction("Refund");

    }

    @Then("verify refund order plusbase")
    public void verifyRefundOrderPlusbase(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String status = SessionData.getDataTbVal(data, row, "Payment status");
            String product = SessionData.getDataTbVal(data, row, "Product");
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            boolean discountFreeship = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Discount freeship"));
            orderPlusBaseSteps.verifyFulfillmentGroup(product, quantity);
            orderPlusBaseSteps.verifyStatus(status);

            float refundSellingPrice = dataRefundForBuyer.get("Refund selling price");
            float refundShippingFeeBuyer = dataRefundForBuyer.get("Refund shipping fee");
            float PaymentFeeBuyer = dataRefundForBuyer.get("Refund payment fee");

            float refundForBuyer = refundSellingPrice + refundShippingFeeBuyer - PaymentFeeBuyer;
            out = refundSellingPrice;


            float refundProductCost = dataRefundForSeller.get("Refund product cost");
            float refundShippingFeeSeller = dataRefundForSeller.get("Refund shipping fee");
            float refundProcessingFee = dataRefundForSeller.get("Refund processing fee");
            float refundPaymentFee = dataRefundForSeller.get("Refund payment fee");
            in = refundProductCost + refundShippingFeeSeller + refundProcessingFee + refundPaymentFee;

            orderPlusBaseSteps.verifyRefundedCustomer("Refunded to customer", refundForBuyer);
            orderPlusBaseSteps.verifyNetPayment("Net Payment");
            orderPlusBaseSteps.clickOpenProfitGroup("Show calculation");
            orderPlusBaseSteps.clickOpenHanldingFee();
            orderPlusBaseSteps.verifyRefundProfitGroup("Refunded", out);
            if(discountFreeship) {
                float price = discountOrder - refundShippingFeeSeller;
                orderPlusBaseSteps.verifyRefundProfitGroup("Discount", price);
            }
            orderPlusBaseSteps.verifyRefundProfitGroup("Product cost", dataBeforeRefundForSeller.get("Refund product cost"));
            orderPlusBaseSteps.verifyRefundProfitGroup("Payment fee", dataBeforeRefundForSeller.get("Refund payment fee"));
            orderPlusBaseSteps.verifyRefundProfitGroup("Processing fee", dataBeforeRefundForSeller.get("Refund processing fee"));
            float handlingFee = dataBeforeRefundForSeller.get("Refund payment fee") + dataBeforeRefundForSeller.get("Refund processing fee");
            orderPlusBaseSteps.verifyRefundProfitGroup("Handling fee", handlingFee);

            out = isWithdrawSellerBalance ? 0 : out;
            float profit = currentProfit + in - out;
            orderPlusBaseSteps.verifyProfitAfterRefund("Profit", profit);
        }
    }

    @And("verify balance of order after refund with status = {string}")
    public void verifyBalanceOfOrderAfterRefundWithStatus(String status) {
        orderPlusBaseSteps.moveToBalance();
        orderPlusBaseSteps.verifyValueBalance("Pending to review", pendingToReview);
        orderPlusBaseSteps.moveToViewHistory(orderNumber, status);
        orderPlusBaseSteps.verifyInvoiceHistory(in, out, isWithdrawSellerBalance);
    }

    @And("input payment information and complete order plusBase")
    public void inputPaymentInformationAndCompleteOrderPlusBase(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String paymentMethod = SessionData.getDataTbVal(data, row, "Payment method");
            String paymentGateway = SessionData.getDataTbVal(data, row, "Payment gateway");
            String code = SessionData.getDataTbVal(data, row, "code");
            orderPlusBaseSteps.choosePaymentMethod(paymentMethod,code);
        }
    }

    @And("^choose shipping method order plusbase \"([^\"]*)\"$")
    public void chooseShippingMethodOrderPlusbase(String shippingMethod) {
        float_totalAmt = orderSummarySteps.getTotalPrice();
        shippingFeeBeforeDiscount = shippingMethodSteps.getShippingFeeBeforeDiscount();
        if (!onePageCheckoutSteps.isOnePage()) {
            if (float_totalAmt > 0) {
                if (!shippingMethod.equalsIgnoreCase("none")) {
                    shippingMethodSteps.chooseShippingMethod(shippingMethod);
                    orderPlusBaseSteps.clickContinueToPaymentMethod();
                    paymentMethodSteps.verifyPaymentMethodDisplayed();
                } else {
                    shippingMethodSteps.verifyNoShippingDisplayed();
                }
            } else {
                shippingMethodSteps.clickOnCompleteOrderBtn();
            }
        } else {
            if (!shippingMethod.equalsIgnoreCase("none")) {
                shippingMethodSteps.chooseShippingMethod(shippingMethod);
            }
        }
    }

    @And("Select order fulfillment status = unfulfill")
    public void selectOrderFulfillmentStatusUnfulfill() {
        orderPlusBaseSteps.selectOrder();
    }

    @And("Search and verify status order store template by \"([^\"]*)\"$")
    public void searchAndVerifyStatusOrderStoreTemplate(String key, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String fulfillmentStatus = SessionData.getDataTbVal(dataTable, row, "Fulfillment status");
            String paymentStatus = SessionData.getDataTbVal(dataTable, row, "Payment status");
            orderPlusBaseSteps.searchOrder(orderName);
            orderPlusBaseSteps.verifyStatusOnStoreTemplate(orderName,paymentStatus,fulfillmentStatus);

        }
    }

    @And("Search and verify status order store merchant by \"([^\"]*)\"$")
    public void searchAndVerifyStatusOrderStoreMerchant(String key, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String fulfillmentStatus = SessionData.getDataTbVal(dataTable, row, "Fulfillment status");
            String paymentStatus = SessionData.getDataTbVal(dataTable, row, "Payment status");
            orderPlusBaseSteps.searchOrder(orderName);
            orderPlusBaseSteps.verifyStatusOnStoreMerchant(orderName,paymentStatus,fulfillmentStatus);

        }
    }

    @Given("get tracking number of order plusbase")
    public void get_tracking_number_of_order_plusbase() {
        orderPlusBaseSteps.getTrackingNumber();
    }

}
