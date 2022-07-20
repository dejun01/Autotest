package com.opencommerce.shopbase.dashboard.orders.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.ImportOrderByAPIPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.JavascriptExecutor;
import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class OrderSteps extends ScenarioSteps {

    OrderPage orderPage;

    ImportOrderByAPIPage importOrderByAPIPage;
    String lineitemName = "";
    String lineitemQuantity = "";
    String lineitemPrice = "";
    String lineitemSKU = "";
    public static String orderState = "";

    @Step
    public void logInfor(String s) {
        System.out.println(s);
    }

    @Step
    public String getFinancialStatusOfOrder() {
        return orderPage.getFinancialStatus();
    }

    @Step
    public void verifyStatusOrders(String statusOfOrder) {
        if (!statusOfOrder.isEmpty()) {
            orderPage.verifyStatus(statusOfOrder);
        }
    }

    public String getFulfillmentStatus() {
        return orderPage.getFulfillmentStatus();
    }

    @Step
    public void clickNameOrderOnList(String orderName) {
        orderPage.clickOrderName(orderName);
    }

    @Step
    public void enterFieldWithLabel(String label, String value) {
        orderPage.waitUntilTheFieldDisplayed(label);
        orderPage.inputTheFieldSlowly(label, value);
    }

    @Step
    public void fulfilledOrder(String sItems, String sTrackingNumber, String sCarrier, String sTrackingUrl) {
        if (!sItems.isEmpty()) {
            chooseItemFulfilled(sItems);
        }
        if (!sCarrier.isEmpty()) {
            searchThenSelectShippingCarrier(sCarrier);
            if (sCarrier.equals("Other")) {
                orderPage.verifyTrackingUrlTextboxDisplayed();
            }
        }
        if (!sTrackingNumber.isEmpty()) {
            enterFieldWithLabel("Tracking number", sTrackingNumber);
        }
        if (!sTrackingUrl.isEmpty()) {
            enterFieldWithLabel("Tracking URL", sTrackingUrl);
        }
        orderPage.clickBtn("Fulfill items");
        waitABit(5000);
    }

    private void searchThenSelectShippingCarrier(String sCarrier) {
        orderPage.searchThenSelectTheCarrier(sCarrier);
    }

    @Step
    public void chooseItemFulfilled(String sItems) {
        if (!sItems.isEmpty()) {
            String[] items = sItems.split(",");
            for (int i = 0; i < items.length; i++) {
                chooseProductAndQuantityFulfill(items[i]);
            }
        }
    }

    @Step
    public void chooseProductAndQuantityFulfill(String item) {
        String[] data = item.split(">");
        String productName = data[0];
        String quantity = data[1];
        String product = "";
        String variant = "";
        if (productName.contains(":")) {
            product = productName.split(":")[0];
            variant = productName.split(":")[1];
        } else {
            product = productName;
            variant = orderPage.getProductVariant(productName);
        }
        orderPage.inputQuantityWithProduct(product, variant, quantity);
    }


    @Step
    public void verifyTimeLine(String sTimeline) {
        orderPage.verifyTimeLine(sTimeline);
    }

    @Step
    public void clickOnMarkAsFullfilledButton() {
        orderPage.clickOnMarkAsFullfulledButton();
    }

    @Step
    public void viewOrderStatusPage() {
        orderPage.clickBtn("More actions");
        orderPage.selectViewOrderStatusPage();
    }

    @Step
    public void clickOnTrackingNumber(String trackingNum) {
        orderPage.clickLinkTextWithLabel(trackingNum);
        orderPage.switchToLatestTab();
        orderPage.waitForEverythingComplete();
    }

    @Step
    public void verifyTrackingNumberLinkToThirdParty(String carrier, String thirdParty, String trackingNumber) {
        String currentURL = orderPage.getCurrentUrl();
        String trackingLink = "";
        if (!carrier.equalsIgnoreCase("other")) {
            trackingLink = thirdParty.replace("{tracking_number}", trackingNumber).replace("\u200B", "");
            assertThat(currentURL).contains(trackingLink);
        } else {
            trackingLink = thirdParty;
            assertThat(currentURL).contains(trackingLink);
        }
    }

    @Step
    public void openCancelOrderPopup() {
        orderPage.openCancelOrderPopup();
    }

    @Step
    public void clickActionOrder(String action) {
        orderPage.clickActionOrder(action);
    }

    @Step
    public List<Integer> getOrderList(String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/orders.json?access_token=" + accessToken;
        JsonPath js = orderPage.getAPI(url);
        return js.getList("orders.id");
    }

    @Step
    public void verifyOrderScannedInOrderList() {
        if (accesstoken.isEmpty()) {
            accesstoken = orderPage.getAccessTokenShopBase();
        }
        List<Integer> listOfOrder = getOrderList(accesstoken);
        if (listOfOrder.size() > 0) {
            isOrderScanned = true;
        }
        System.out.println("isOrderScanned = " + isOrderScanned);
    }

    @Step
    public void removeAllOrder() {
        if (accesstoken.isEmpty()) {
            accesstoken = orderPage.getAccessTokenShopBase();
        }
        List<Integer> listOfOrder = getOrderList(accesstoken);
        System.out.println(listOfOrder.size());
        if (listOfOrder.size() > 0) {
            for (int i = 0; i < listOfOrder.size(); i++) {
                System.out.println(i);
                System.out.println("Start delete " + listOfOrder.get(i));
                deleteOrder(listOfOrder.get(i).toString(), accesstoken);
            }
        }
    }

    @Step
    public void deleteOrder(String orderID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/orders/" + orderID + ".json?access_token=" + accessToken;
        orderPage.deleteAPI(url);
    }

    public void clickOnMoreActionBtn() {
        orderPage.clickOnMoreActionBtn();
    }

    public void inputShippingAmount() {
        String shipping = orderPage.getShippingAmount();
        orderPage.inputShippingAmount(shipping);
    }

    public void confirmCancelOrder() {
        orderPage.confirmCancelOrder();
        orderPage.waitUntilInvisibleLoading(7);
    }

    public void verifyCancelStatus() {
        orderPage.verifyCancelStatus();
    }

    @Step
    public String getQuantityOfProduct() {
        return orderPage.getQuantityOfProduct();
    }

    @Step
    public void verifyQuantity(int before, int after) {
        orderPage.verifyQuantity(before, after);
    }

    @Step
    public void navigateToRefundScreen() {
        orderPage.navigateToRefundScreen();
    }

    public String getProductVariant(String productName) {
        return orderPage.getProductVariant(productName);
    }

    @Step
    public void enterRefundedItemQuantity(String productVariant, int quantity) {
        if (productVariant.contains(":")) {
            refundedProduct = productVariant.split(":")[0];
            refundedVariant = productVariant.split(":")[1];
        } else {
            refundedProduct = productVariant;
            refundedVariant = getProductVariant(refundedProduct);
        }
        orderPage.enterRefundedItemQuantity(refundedProduct, refundedVariant, quantity);
    }

    @Step
    public String getPriceOfItem() {
        String price = orderPage.getPriceOfItem().split("\\$")[1];
        if (price.contains(",")) {
            price = orderPage.getPriceOfItem().split("\\$")[1].replace(",", "");
        }
        return price;
    }

    @Step
    public Float getPriceOfRefundedItem() {
        float priceOfLineitem = Float.parseFloat(getPriceOfItem());
        return priceOfLineitem;
    }

    @Step
    public void waitUntilRefundButtonUpdated(String expectedRefund) {
        orderPage.waitUntilRefundButtonUpdated(expectedRefund);
    }

    @Step
    public boolean expectedRefund(String expectedRefund) {
        waitUntilRefundButtonUpdated(expectedRefund);
        return Float.parseFloat(orderPage.actualRefund()) == Float.parseFloat(expectedRefund);
    }

    @Step
    public String getRefundAmount(long quantity, float price, float shipping) {
        float expectedRefund = quantity * price + shipping;
        return String.valueOf(expectedRefund);
    }

    @Step
    public void enterRefundedShipping(float refundedShipping) {
        String _refundedShipping = String.valueOf(refundedShipping);
        orderPage.enterRefundedShipping(_refundedShipping);
        if (refundedShipping != 0) {
            orderPage.waitUntilRefundButtonUpdated(_refundedShipping);
        }
    }

    @Step
    public void enterReasonForRefund(String reasonForRefund) {
        orderPage.enterInputFieldWithLabel("Reason for refund", reasonForRefund);
    }

    @Step
    public void clickOnRefundButton() {
        orderPage.clickOnRefundButton();
    }

    @Step
    public void verifyOrderDetailAfterRefundSuccessfully(String status, String netPayment) {
        orderPage.verifyOrderDetailAfterRefundSuccessfully(status, netPayment);
    }

    @Step
    public boolean isDiscount() {
        return orderPage.isDiscount();
    }

    @Step
    public String getDiscountOnSF() {
        return orderPage.getDiscountOnSF();
    }

    @Step
    public void verifyDiscountOnDB(String discountSF) {
        orderPage.verifyDiscountOnDB(discountSF);
    }

    @Step
    public void verifyTippingOnDB(double tippingSF) {
        orderPage.verifyTippingOnDB(tippingSF);
    }

    @Step
    public String getShippingSF() {
        return orderPage.getShippingOnSF();
    }

    @Step
    public String getTaxSF() {
        return orderPage.getTaxOnSF();
    }

    @Step
    public void verifyShippingOnDB(String shippingSF) {
        orderPage.verifyShippingOnDB(shippingSF);
    }

    @Step
    public String getTotalOnSF() {
        return orderPage.getTotalOnSF();
    }

    @Step
    public void verifyTotalOnDB(String totalSF) {
        orderPage.verifyTotalOnDB(totalSF);
    }

    @Step
    public String getOrderCapturedTransactionID() {
        return orderPage.getTransactionID();
    }

    @Step
    public String getAddressOnSF() {
        return orderPage.getAddressOnSF();
    }

    @Step
    public void verifyAddressOnDB(String typeOfAddress, String addressSF) {
        orderPage.verifyAddressOnDB(typeOfAddress, addressSF);
    }

    @Step
    public String updateMerchantEmailAddress(String currentExportTime) {
        return orderPage.updateEmailForMerchant(currentExportTime);
    }

    @Step
    public void selectPagination(int rowPerPage) {
        if (rowPerPage > 50)
            orderPage.selectPagination(rowPerPage);
    }

    @Step
    public void selectAllOrders() {
        orderPage.selectAllOrders();
    }

    @Step
    public void clickOnExportButton() {
        orderPage.clickOnExportButton();
    }


    @Step
    public void exportOrders(int number) {
        orderPage.selectRadioButtonWithLabel("Selected " + number + " orders", true);
        orderPage.waitForEverythingComplete();
        orderPage.waitToOrdersListToAppear();
    }

    @Step
    public void selectOptionToExportOrder(String option) {
        orderPage.selectRadioButtonWithLabel(option, true);
        orderPage.waitForEverythingComplete();
        orderPage.waitToOrdersListToAppear();
    }


    @Step
    public void exportToFile(int number) {
        orderPage.exportToFile(number);
    }

    @Step
    public void verifyFileName(String fileName) {
        orderPage.verifyFileName(fileName);
    }


    @Step
    public void verifyReceivedEmail(String emailAddress, String subject) {
        orderPage.verifyReceivedMail(emailAddress, subject);
    }

    @Step
    public int getTheNumberOfUnfulfilledOrdersInSideBarMenu() {
        return Integer.parseInt(orderPage.getTheNumberOfUnfulfilledOrdersInSideBarMenu());
    }


    @Step
    public void verifyShowWarningForChargeBack(String warning) {
        orderPage.verifyShowWarningForChargeBack(warning);
    }

    @Step
    public void submitEvidence(String evidence) {
        orderPage.clickBtn("Submit response");
        orderPage.waitForEverythingComplete();
        orderPage.enterInputFieldWithLabel("Additional evidence or statements:", evidence);
        orderPage.clickBtn("Submit envidence now");
    }

    @Step
    public void editNotesThenVerify(String note) {
        orderPage.clickBtn("Edit", 1);
        orderPage.waitForEverythingComplete();
        orderPage.enterTextAreaWithLabel("Leave a comment...", note);
        orderPage.clickBtn("Save");
        orderPage.waitForTextToAppear(note);
    }

    @Step
    public void editContactInformationThenVerify(String contactInformation) {
        orderPage.clickBtn("Edit", 2);
        orderPage.waitForEverythingComplete();
        orderPage.enterInputFieldWithLabel("Email address", contactInformation);
        orderPage.clickBtn("Save");
//        orderPage.waitForEverythingComplete(10);
        orderPage.waitForTextToAppear(contactInformation);
    }

    @Step
    public void editFirstName(String firstName) {
        orderPage.enterInputFieldWithLabel("First name", firstName);
    }

    @Step
    public void editLastName(String lastName) {
        orderPage.enterInputFieldWithLabel("Last name", lastName);
    }

    @Step
    public void editCompany(String company) {
        orderPage.enterInputFieldWithLabel("Company", company);
    }

    @Step
    public void editPhoneNumer(String phoneNumber) {
        orderPage.enterInputFieldWithLabel("Phone number", phoneNumber);
    }

    @Step
    public void editAddress(String address) {
        orderPage.enterInputFieldWithLabel("Address", address);
    }

    @Step
    public void editApartment(String apartment) {
        orderPage.enterInputFieldWithLabel("Apartment, suite, etc... (optional)", apartment);
    }

    @Step
    public void editCity(String city) {
        orderPage.enterInputFieldWithLabel("City", city);
    }


    @Step
    public void editZipCode(String zipcode) {
        orderPage.enterInputFieldWithLabel("ZIP/Postal Code", zipcode);
    }

    @Step
    public void editShippingAddress(String firstName, String lastName, String company, String phoneNumber, String address, String apartment, String city, String country, String state, String region, String zipcode) {
        orderPage.clickOnEditShippingAddress();
        editFirstName(firstName);
        editLastName(lastName);
        editCompany(company);
        editPhoneNumer(phoneNumber);
        editAddress(address);
        editApartment(apartment);
        editCity(city);
        selectCountryAndRegion(country, state, region);
        editZipCode(zipcode);
        orderPage.clickBtn("Save");
        orderPage.waitForEverythingComplete();
    }

    private void selectCountryAndRegion(String country, String state, String region) {
        if (!country.isEmpty()) {
            orderPage.selectCustomDdWithLabel("Country", country);
        }
        if (!state.isEmpty()) {
            orderPage.selectDdlWithLabel("State", state);
        }
        if (!region.isEmpty()) {
            enterFieldWithLabel("Region", region);
        }
    }

    @Step
    public void editTagsThenVerify(String tags) {
        orderPage.editTags(tags);
        String actualTag = orderPage.getTagList();
        assertThat(actualTag).isEqualTo(tags);
    }

    @Step
    public void saveChanges() {
//        orderPage.clickBtn("Save");
        orderPage.clickBTSaveChange();
        orderPage.verifyMsgDisplayed();
        orderPage.waitForEverythingComplete();

    }

    @Step
    public JsonObject applyDiscountForOrder(String description, String valueType, float value, float amount) {
        JsonObject requestParam = new JsonObject();
        JsonObject appliedDiscount = new JsonObject();

        requestParam.add("applied_discount", appliedDiscount);
        appliedDiscount.addProperty("description", description);
        appliedDiscount.addProperty("value_type", valueType);
        appliedDiscount.addProperty("value", value);
        appliedDiscount.addProperty("amount", amount);

        return requestParam;

    }

    @Step
    public JsonObject requestBodyCreateOrder(long variant_id, int quantity, String applied_discount, String descriptionDraftOrder, String valueType, double valueDraftOrder, double amountDraftOrder) {
        JsonObject requestParam = new JsonObject();
        JsonObject draftOrder = new JsonObject();
        JsonArray lineItemsList = new JsonArray();
        JsonObject lineItem = new JsonObject();
        JsonObject appliedDiscount = new JsonObject();

        requestParam.add("draft_order", draftOrder);
        lineItemsList.add(lineItem);
        lineItem.addProperty("variant_id", variant_id);
        lineItem.addProperty("quantity", quantity);

        if (applied_discount.contains(">")) {
            String descriptionLineItem = applied_discount.split(">")[0];
            Float amountLineItem = Float.parseFloat(applied_discount.split(">")[1].replace("$", ""));
            appliedDiscount.addProperty("description", descriptionLineItem);
            appliedDiscount.addProperty("amount", amountLineItem);
        }

        lineItem.add("applied_discount", appliedDiscount);
        requestParam.add("line_items", lineItemsList);

        return requestParam;
    }

    @Step
    public JsonObject draftOrder(long variant_id, int quantity, String applied_discount, String description, String valueType, float value, float amount) {
        JsonObject requestParam = new JsonObject();
        JsonObject draftOrder = new JsonObject();

        requestParam.add("draft_order", draftOrder);
        draftOrder.add("line_items", requestBodyCreateOrder(variant_id, quantity, applied_discount, description, valueType, value, amount));
        draftOrder.add("applied_discount", applyDiscountForOrder(description, valueType, value, amount));
        return requestParam;
    }

    @Step
    public void downloadOrders() {
        orderPage.downloadOrders();
    }

    @Step
    public String openFileDownloaded() {
        return orderPage.getPathFileDownloaded();
    }

    @Step
    public HashMap<String, String> readingOrderInfoBasedOnHeader(String filePath, String header) {
        String value = "";
        try {
            waitABit(2000);
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                value = csvRecord.get(header);
                expectedOrderInfo.put(header, value);
                exportOrderList.add(value);
            }
        } catch (Exception e) {
            logInfor("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        return expectedOrderInfo;
    }

    @Step
    public ArrayList<String> readingCustomerInfor(String filePath) throws IOException {
        ArrayList<String> info = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withHeader("Id", "FirstName", "LastName", "Gender", "Age").withIgnoreHeaderCase().withTrim());
        String line = br.readLine(); //ignoring

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            for (int i = 0; i < fields.length; i++) {
                info.add(fields[i]);
                System.out.println("info download = " + info);
            }
        }
        return info;
    }

    @Step
    public void clickOnTheOrderInList(String orderName) {
        orderPage.clickOnTheOrderInList(orderName);
    }

    @Step
    public void clickOnViewFullAnalysis() {
        orderPage.clickOnViewFullAnalysis();
    }

    @Step
    public void verifyIndicator(String indicator, String color) {
        orderPage.verifyIndicator(indicator, color);
    }

    @Step
    public String getOrderStatusInOrderList(String orderName) {
        String orderStatus = "";
        orderStatus = orderPage.getOrderStatusInOrderList(orderName);
        return orderStatus;
    }

    @Step
    public void compareOrderStatusWithPaypalPaymentStatus(String orderStatus, String paymentStatus) {
        orderPage.compareOrderStatusWithPaypalPaymentStatus(orderStatus, paymentStatus);
    }

    @Step
    public void verifyProductInformation(int index, String productName, String quantity, String amount) {
        orderPage.verifyProductName(productName, index);
        orderPage.verifyQuantityOfProduct(quantity, index);
        orderPage.verifyTotalPriceOfProduct(amount, index);
    }

    public void verifyShippingCarrierDisplayed(String carrier) {
        orderPage.verifyShippingCarrierDisplayed(carrier);
    }

    public void verifyTotalOrder(String totalAmt) {
        orderPage.verifyTotalOrder(totalAmt);
    }

    public void verifyOrderStatus(String status) {
        orderPage.verifyOrderStatus(status);
    }

    public void verifyPaidByCustomer(String status) {
        orderPage.verifyPaidByCustomer(status);
    }

    @Step
    public void verifyOrderTimeline(String paymentGateway, String customerName, String customerEmail, String totalAmount, String cardType, String endingCardNo) {
        List<String> timelines = orderPage.getTimelineContent();
        String orderTimelineSendingEmail = "Order confirmation email was sent to " + customerName + " (" + customerEmail + ")";
        String orderTimelineCustomerPlaceOrder = customerName + " placed this order on Online Store (checkout";
        String orderTimelinePaymentProcessed = orderTimelinePaypalProcessedByPaymentGateway(totalAmount, cardType, endingCardNo, paymentGateway);
        String orderTimelineTrxID = orderTimelineTrxIDByGateway(paymentGateway);

        verifyOrderTimelineByLineItem(timelines, orderTimelineSendingEmail);
        verifyOrderTimelineByLineItem(timelines, orderTimelineCustomerPlaceOrder);
        verifyOrderTimelineByLineItem(timelines, orderTimelinePaymentProcessed);
        verifyOrderTimelineByLineItem(timelines, orderTimelineTrxID);
    }

    @Step
    public void verifyEUOrderTimeLine(String paymentGateway, String totalAmount, String paymentMethod) {
        String accountName = "Stripe-1";
        List<String> actualEUTimelines = orderPage.getTimelineContent();
        String expectEUTimeline = "A " + totalAmount + " USD payment was processed via Stripe " + accountName + " method " + paymentMethod;
        String orderTimelineTrxID = orderTimelineTrxIDByGateway(paymentGateway);

        verifyOrderTimelineByLineItem(actualEUTimelines, expectEUTimeline);
        verifyOrderTimelineByLineItem(actualEUTimelines, orderTimelineTrxID);
    }

    @Step
    public void verifyOrderTimeline(String content) {
        List<String> timelines = orderPage.getTimelineContent();
        verifyOrderTimelineByLineItem(timelines, content);
    }

    @Step
    public void verifyOrderTimelineByLineItem(List<String> timelines, String lineItem) {
        System.out.println(lineItem);
        if (!lineItem.isEmpty()) {
            assertThat(timelines, hasItem(containsString(lineItem)));
        }
    }

    private String orderTimelineTrxIDByGateway(String paymentGateway) {
        String orderTimelineTrxID = "";
        if (!"Shopbase Payments".equalsIgnoreCase(paymentGateway) && !"Test Gateway".equalsIgnoreCase(paymentGateway)) {
            orderTimelineTrxID = "The " + paymentGateway + " transaction ID is";
        }
        if ("COD".equalsIgnoreCase(paymentGateway) || "COD-Paid".equalsIgnoreCase(paymentGateway) || "Oceanpayment".equalsIgnoreCase(paymentGateway) || "Asia-Bill".equalsIgnoreCase(paymentGateway) || "Bank Transfer".equalsIgnoreCase(paymentGateway) || "Bank Transfer-Paid".equalsIgnoreCase(paymentGateway)) {
            orderTimelineTrxID = "The transaction ID is";
        }
        return orderTimelineTrxID;
    }

    private String orderTimelinePaypalProcessedByPaymentGateway(String totalAmount, String cardType, String endingCardNo, String paymentGateway) {
        String orderTimelinePaymentProcessed = "";
        switch (paymentGateway) {
            case "Paypal":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed via Paypal-Express";
                break;
            case "Unlimint":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed on the card ending in " + endingCardNo + " via " + paymentGateway;
                break;
            case "Asia-Bill":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed via " + paymentGateway;
                break;
            case "COD":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment is pending on Cash on Delivery (COD).";
                break;
            case "Bank Transfer":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment is pending on Bank Transfer.";
                break;
            case "COD-Paid":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed on Cash on Delivery (COD)";
                break;
            case "Bank Transfer-Paid":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed on Bank Transfer";
                break;
            case "Oceanpayment":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed via Ocean-Payment-1 OceanPayment-1";
                break;
            default:
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed on the " + cardType + " ending in " + endingCardNo + " via " + paymentGateway;
        }
        return orderTimelinePaymentProcessed;
    }

    @Step
    public String orderEUTimelinePaypalProcessedByPaymentGateway(String totalAmount, String cardType, String endingCardNo, String paymentGateway, String paymentMethod) {
        String orderTimelinePaymentProcessed = "";
        switch (paymentMethod) {
            case "Bancontact":
            case "SEPA Credit Card":
            case "iDEAL":
            case "giropay":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed via Stripe Stripe-1 method" + paymentMethod;
                break;
            case "Credit Card":
                orderTimelinePaymentProcessed = "A " + totalAmount + " USD payment was processed on the " + cardType + " ending in " + endingCardNo + " via " + paymentGateway;
        }
        return orderTimelinePaymentProcessed;
    }

//    @Step
//    public List<String> getTrxID() {
//        return orderPage.getTrxID();
//    }

    @Step
    public String getCustomerName() {
        return orderPage.getCustomerName();
    }

    public Float parseFloat(String price) {
        return Float.parseFloat(orderPage.price(price));
    }

    public String roundOffTo2DecPlaces(float val) {
        return String.format("%.2f", val);
    }

    @Step
    public void verifyProductInformation(int index, String productName, String quantity, String productPrice, String discountAmount) {
        Float discountedPrice = parseFloat(productPrice) - parseFloat(discountAmount);
        orderPage.verifyProductName(productName, index);
        orderPage.verifyQuantityOfProduct(quantity, index);
        orderPage.verifyTotalPriceOfProduct((discountedPrice) + "0", index);
    }

    public Float getPaymentRate() {
        return orderPage.getPaymentRate();
    }

    public Float getProcessingFee() {
        return orderPage.getProcessingFee();
    }

    public Float getPaymentFee() {
        return orderPage.getPaymentFee();
    }

    public Float getBaseCost() {
        return orderPage.getBaseCost();
    }

    public Float getSubTotal() {
        return orderPage.getSubToTal();
    }

    public Float getProcessingRate() {
        return orderPage.getProcessingRate();
    }

    public Float getProfit() {
        return orderPage.getProfit();
    }

    @Step
    public void enterTextThenEnter(String criteria) {
        orderPage.enterTextToTextbox(criteria);
        waitABit(7000);
    }

    @Step
    public void verifyOrderInfor(String criteria, String expectedCriteria) {
        String actualCriteria = "";

        switch (criteria.toLowerCase()) {
            case "order name":
                actualCriteria = orderPage.getOrderNumber();
                break;
            case "customer name":
                actualCriteria = orderPage.getCustomerName();
                break;
            default:
                actualCriteria = orderPage.getCustomerEmail();
                break;
        }
        assertThat(actualCriteria).isEqualTo(expectedCriteria);
    }

    public void backToListOfOrders() {
        orderPage.clickBackToOrderList();
        orderPage.waitUntilLoadingOrdersListCompletely();
    }

    @Step
    public void selectTheNewestOrder() {
        orderPage.clickTheNewestOrder();
        orderPage.waitForEverythingComplete();
    }

    @Step
    public void navigateToProductDetail() {

        orderPage.navigateToProductDetail();
    }

    public void clickOnTheCustomerName() {
        orderPage.clickOnTheCustomerNameLinkText();
    }

    public void clickOnTheOrderQuantityFromOrderDetail() {
        int orderQuantity = Integer.parseInt(orderPage.countOrderQuantityInOrderDetail().replace("orders", "").replace("order", "").trim());
        if (orderQuantity != 1) {
            orderPage.clickOnOrderQuantityLinkText();
        }
    }

    @Step
    public String countOrderQuantityInOrderDetail() {
        return orderPage.countOrderQuantityInOrderDetail().replace("orders", "").replace("order", "").trim();
    }

    // working properly when order quantity < 50
//    @Step
//    public int countTotalOrdersInOrderList() {
//        orderPage.selectAllOrders();
//        int orderQuantity = orderPage.countOrderQuantityInOrderList();
//        return orderQuantity;
//    }
//    @Step
//    public List<String> countTotalOrdersInOrderList() {
//        return orderPage.countOrderQuantityInOrderList();
//    }

    public void verifyOrderQuantity(int orderQuantityBefore, int orderQuantityAfter) {
        assertThat(orderQuantityBefore).isEqualTo(orderQuantityAfter);
    }

    public void verifyIsOptionPresent(String optionName, boolean isPresent) {
        orderPage.verifyOptionNotPresent(optionName, isPresent);
    }

    public void verifyMsgIsDisplayed(String message) {
        orderPage.waitForEverythingComplete(8);
        orderPage.verifyTextPresent(message, true);
    }

    public void verifyRemainingItems(String remainingItems) {
        if (remainingItems.contains(">")) {
            String remain[] = remainingItems.split(">");
            String productName = remain[0];
            String quantity = remain[1];
            orderPage.verifyProductQuantityInRefundPage(productName, quantity);
        }
    }

    public void verifyRefundedShipping(String refundedShipping) {
        orderPage.waitForEverythingComplete(8);
        orderPage.verifyRefundedShipping(refundedShipping);
    }

    @Step
    public String getOrderInfor(String infor) {
        return orderPage.getOrderInfor(infor);
    }

    @Step
    public void verifyAvailableForRefund(String netPayment) {
    }

    public float convertPriceToFloat(String s) {
        return Float.parseFloat(orderPage.price(s));
    }

    @Step
    public void isRestockedItem(String quantity, boolean isRestock) {
        int _quantity = Integer.parseInt(quantity);
        quantity += _quantity > 1 ? " items" : " item";
        orderPage.clickCheckbox("Restock " + quantity, isRestock);
    }

    @Step
    public String getOriginalPriceBeforeRefunding(String productName) {
        return orderPage.getOriginalPrice(productName).trim();
    }

    @Step
    public String getDiscountedPriceBeforeRefunding(String productName) {
        return orderPage.getDiscountedPrice("Unfulfilled", productName);
    }

    @Step
    public void verifyTheProductIsRestocked(String productName, boolean isRestock) {
        orderPage.verifyTheProductIsRestocked(productName, isRestock);
    }

    @Step
    public void verifyDiscountedPriceAfterRefunding(String productName, String expectedDiscountedPrice) {
        String actualDiscountPrice = orderPage.getDiscountedPrice("Removed items", productName);
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountedPrice);
    }

    @Step
    public void verifyOriginalPriceAfterRefunding(String productName, String expectedOriginalPrice) {
        String actualOriginalPrice = orderPage.getOriginalPrice("Removed items", productName);
        assertThat(actualOriginalPrice).isEqualTo(expectedOriginalPrice);
    }

    public void verifyInformationOnCancelPopup(String criteria, String expectedValue) {
        String actualValue = orderPage.getInformationOnCancelPopup(criteria);
        assertThat(actualValue).isEqualTo(orderPage.price(expectedValue));
    }

    public void verifyGatewayToRefund(String paymentGateway) {
    }

    public void verifyRemainingShipping(String expectedRemainingShipping) {
        String _expectedRemainingShipping = "Shipping ($" + expectedRemainingShipping + " remaining)";
        String _actualRemainingShipping = orderPage.getRemainingShipping();
        assertThat(_actualRemainingShipping).isEqualTo(_expectedRemainingShipping);
    }

    public int getOriginalQuantityBeforeRefunding(String productName) {
        return orderPage.getOriginalQuantityBeforeRefunding(productName);
    }

    public void verifyReturnedProductOnCancelPopup(String refundedItem) {
        if (refundedItem != null) {
            List<String> returnedProductList = orderPage.getReturnedProductOnCancelPopup();
            assertThat(returnedProductList).contains(refundedItem);
        } else {
            orderPage.verifyTextPresent("Already returned", false);
        }
    }

    public String getChargePayment() {
        String status = orderPage.getChargePayment();
        int n = 0;
        while (!status.equalsIgnoreCase("Paid")) {
            waitABit(10000);
            status = orderPage.getChargePayment();
            n++;
            if (n == 20)
                break;
        }
        return status;
    }

    public boolean isContainCurrencySymbol(String str) {
        Pattern pattern = Pattern.compile("\\p{Sc}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public void verifyRemainingProductOnCancelPopup(HashMap<String, List<String>> remainingProduct) {
        String remainingProd = "";
        String remainingVariant = "";
        String expectedOriginalPrice = "", expectedQuantity = "";
        String actualOriginalPrice = "", actualQuantity = "";

        Set<Map.Entry<String, List<String>>> set = remainingProduct.entrySet();
        for (Map.Entry<String, List<String>> i : set) {
            String prodVariant = i.getKey();
            List<String> priceAndQuantity = i.getValue();

            if (prodVariant.contains(":")) {
                remainingProd = prodVariant.split(":")[0];
                remainingVariant = prodVariant.split(":")[1];
            } else {
                remainingProd = prodVariant;
                remainingVariant = "Default Title";
            }
            actualQuantity = getActualQuantityRemainingOnCancelPopup(remainingProd, remainingVariant);
            actualOriginalPrice = getActualOriginalPriceRemainingOnCancelPopup(remainingProd, remainingVariant);

            for (String value : priceAndQuantity) {
                if (isContainCurrencySymbol(value)) {
                    expectedOriginalPrice = value;
                }
                if (!isContainCurrencySymbol(value))
                    expectedQuantity = value;
            }
        }
        assertThat(actualQuantity).isEqualTo(expectedQuantity);
        assertThat(actualOriginalPrice).isEqualTo(expectedOriginalPrice.replace("USD", "").trim());

        System.out.println("actualQuantity = " + actualQuantity);
        System.out.println("expectedQuantity = " + expectedQuantity);

    }

    private String getActualOriginalPriceRemainingOnCancelPopup(String prodName, String variantName) {
        return orderPage.getActualOriginalPriceRemainingOnCancelPopup(prodName, variantName);
    }

    private String getActualQuantityRemainingOnCancelPopup(String prodName, String variantName) {
        return orderPage.getActualQuantityRemainingOnCancelPopup(prodName, variantName);
    }


    public Float getDiscount() {
        return orderPage.getDiscount();
    }

    public Float getShipping() {
        return orderPage.getShipping();
    }

    @Step
    public void importTrackingNumber(String overwrite, String notification, String data, List<String> orderNameList) throws IOException {
        orderPage.clickImportTrackingNumber();
        orderPage.importFileCVSImportTrackingNumber(data, orderNameList);
        orderPage.selectElement(overwrite, "Update the existing fulfillments", "update");
        orderPage.selectElement(notification, "Send a notification email to the customer", "send mail");
        orderPage.clickUploadFile();
    }

    @Step
    public void clickUploadFile() {
        orderPage.clickUploadFile();
        orderPage.clickBtnOK();
        orderPage.refreshPage();
    }

    @Step
    public void searchOrderNameOnOrderList(String orderNumber) {
        orderPage.searchOrderNameOnOrderList(orderNumber);
    }

    @Step
    public void verifyFulfillmentStatusOnOrderList(String orderNumber, String status) {
        orderPage.verifyFulfillmentStatusOnOrderList(orderNumber, status);
    }

    @Step
    public void verifyOrder(String order) {
        orderPage.verifyOrder(order);
    }

    @Step
    public void verifyFulfillment(String fulfillment) {
        orderPage.verifyFulfillment(fulfillment);
    }

    @Step
    public void verifyOverwrite(String overwrite) {
        orderPage.verifyOverwrite(overwrite);
    }

    @Step
    public void verifyNotification(String notification) {
        orderPage.verifyNotification(notification);
    }

    @Step
    public void verifyContentPopupPreview(String label, String lineitemSku) {
        orderPage.verifyContentPopupPreview(label, lineitemSku);
    }

    @Step
    public void verifyLineitemQuantity(String lineitemQuantity) {
        orderPage.verifyLineitemQuantity(lineitemQuantity);
    }

    @Step
    public void verifyTrackingNumber(String trackingNumber) {
        orderPage.verifyTrackingNumber(trackingNumber);
    }

    @Step
    public void verifyShippingCarrier(String shippingCarrier) {
        orderPage.verifyShippingCarrier(shippingCarrier);
    }

    @Step
    public void verifyDomain(String shopDomain) {
        orderPage.verifyDomain(shopDomain);
    }


    @Step
    public void verifyLineItem(String sku, String statusItem, String quantity) {
        orderPage.verifyLineItem(sku, statusItem, quantity);
    }

    @Step
    public void verifyTrackingNumbers(String sku, String statusItem, String quantity, String trackingNumbers) {
        orderPage.clickEditTracking(sku, statusItem, quantity);
        orderPage.verifyTrackingNumbers(trackingNumbers);
    }

    @Step
    public void verifyCarrier(String carrier) {
        orderPage.verifyCarrier(carrier);
    }

    @Step
    public void verifyURL(String sku, String statusItem, String quantity, String url, String trackingNumbers) {
        orderPage.verifyURL(sku, statusItem, quantity, url, trackingNumbers);
    }

    @Step
    public void verifyTrackingNotification(String notification) {
        orderPage.verifyTrackingNotification(notification);
        orderPage.closePopupEditTracking();
    }

    public void lineItemInfo() {
        // get lineitem info from checkout detail
        Set<Map.Entry<String, List<String>>> setHashMap = productListAdded.entrySet();

        for (Map.Entry<String, List<String>> lineitem : setHashMap) {
            productName = lineitem.getKey().split(":")[0].trim();
            lineitemName = lineitem.getKey().replace(":", " - ");
            lineitemQuantity = lineitem.getValue().get(1);
            lineitemPrice = lineitem.getValue().get(0);
        }
        // get lineitem sku from order detail
        lineitemSKU = orderPage.getLineitemSKU(productName);
    }

    public void shippingAddressInfo() {
        List<String> shippingAddress = orderPage.geAddressInfo("Shipping address");
        address = shippingAddress.get(0);
        apartment = shippingAddress.get(1);
        city = shippingAddress.get(2);
        zipcode = shippingAddress.get(3);
        state = shippingAddress.get(4);
        country = shippingAddress.get(5);
        phoneNumber = shippingAddress.get(6);
    }

    public String getSubtotal() {
        return orderPage.getSubtotal();
    }

    public String price(String val) {
        String _price = orderPage.price(val);
        float var1 = _price.isEmpty() ? 0 : Float.parseFloat(_price);
        int var2 = (int) var1;
        if (var1 - var2 == 0) {
            return String.valueOf(var2);
        }
        return _price;
    }

    @Step
    public HashMap<String, String> getOrderInfo() {
        actualOrderInfo.put("Name", orderNumber);
        actualOrderInfo.put("Email", customerEmail);
        actualOrderInfo.put("Financial Status", getFinancialStatusOfOrder().replace("Partially refunded", "partially_refunded"));
        actualOrderInfo.put("Fulfillment Status", getFulfillmentStatus());
        actualOrderInfo.put("Currency", orderCurrency);
        actualOrderInfo.put("Subtotal Price", price(getSubtotal()));
        actualOrderInfo.put("Shipping", price(shippingFee));
        actualOrderInfo.put("Total Tax", totalTax);
        actualOrderInfo.put("Total Price", price(totalAmt));
        actualOrderInfo.put("Discount Codes", discountCode);
        actualOrderInfo.put("Discount Amount", price(discountAmount));
        actualOrderInfo.put("Shipping Method", shippingMethod);

        lineItemInfo();
        actualOrderInfo.put("Lineitem Quantity", lineitemQuantity);
        actualOrderInfo.put("Lineitem Price", price(lineitemPrice));
        actualOrderInfo.put("Lineitem Sku", lineitemSKU);

        for (String row : shippingAddressHashMap.keySet()) {
            actualOrderInfo.put(row, shippingAddressHashMap.get(row));
        }

        return actualOrderInfo;
    }

    public void verifyThatDownloadedFileMatchingTemplate(HashMap<String, String> actualOrderInfo, HashMap<String, String> expectedOrderInfo) {
        for (String i : actualOrderInfo.keySet()) {
            if (expectedOrderInfo.get(i) != null) {
                assertThat(actualOrderInfo.get(i).trim()).isEqualToIgnoringCase(expectedOrderInfo.get(i).trim());
            }
        }
    }

    public void accessOrderDetailById(String orderId) {
        String shop = LoadObject.getProperty("shop");
        String shopId = LoadObject.getProperty("shop_id");
        String scope = "JTVCJTIyaG9tZSUyMiwlMjJyZWFkX3Byb2R1Y3RzJTIyLCUyMndyaXRlX3Byb2R1Y3RzJTIyLCUyMnJlYWRfcHJvZHVjdF9saXN0aW5ncyUyMiwlMjJyZWFkX2ludmVudG9yeSUyMiwlMjJ3cml0ZV9pbnZlbnRvcnklMjIsJTIycmVhZF9mdWxmaWxsbWVudHMlMjIsJTIycmVhZF9jdXN0b21lcnMlMjIsJTIyd3JpdGVfY3VzdG9tZXJzJTIyLCUyMmRhc2hib2FyZHMlMjIsJTIycmVhZF9hbmFseXRpY3MlMjIsJTIycmVhZF9zYWxlcyUyMiwlMjJyZWFkX21hcmtldGluZ19ldmVudHMlMjIsJTIyd3JpdGVfbWFya2V0aW5nX2V2ZW50cyUyMiwlMjJhcHBzJTIyLCUyMnNldHRpbmdzJTIyLCUyMnJlYWRfdGhlbWVzJTIyLCUyMnJlYWRfcGF5bWVudF9wcm92aWRlcnMlMjIsJTIyd3JpdGVfcGF5bWVudF9wcm92aWRlcnMlMjIsJTIyd3JpdGVfdGhlbWVzJTIyLCUyMnJlYWRfY29udGVudCUyMiwlMjJyZWFkX3BhZ2VzJTIyLCUyMndyaXRlX3BhZ2VzJTIyLCUyMndyaXRlX2NvbnRlbnQlMjIsJTIycmVhZF9wcmVmZXJlbmNlcyUyMiwlMjJ3cml0ZV9wcmVmZXJlbmNlcyUyMiwlMjJkb21haW5zJTIyLCUyMnJlYWRfb3JkZXJzJTIyLCUyMnJlYWRfYWxsX29yZGVycyUyMiwlMjJleHBvcnRfb3JkZXJzJTIyLCUyMnZpZXdfYXNzaWduZWRfc3RhZmZzJTIyJTVE";
        String url = "";
        if (!orderPage.isPrintBaseStore()) {
            url = "https://" + shop + "/admin/orders/" + orderId + "?access_token=" + accesstoken + "&scopes=" + scope;
        } else {
            url = "https://" + shop + "/admin/pod/orders/" + orderId + "?shop_ids=" + shopId + "&access_token=" + accesstoken + "&scopes=" + scope;
        }
        orderPage.openUrl(url);
        orderPage.waitUntilOrderDetailLoadingCompletely();
    }

    @Step
    public void verifyFinancialStatusIs(String financialStatus) {
        String actualFinancialStatus = getFinancialStatusOfOrder();
        assertThat(actualFinancialStatus).isEqualTo(financialStatus);
    }

    @Step
    public void clickOnCancelBtn() {
        orderPage.clickCancelBtn("Cancel order", 2);
    }

    @Step
    public void verifyToastMsgIsDisplayed(String toastMsg) {
        orderPage.waitUntilTheMsgDisplayed(toastMsg);
    }

    //Dispute response form --------------------------------------------------------------------------------------------
    @Step
    public void verifyDisputeWarningMessage(String disputeType, String totalAmt) {
        orderPage.verifyDisputeWarningMessage(disputeType, totalAmt);
    }

    @Step
    public void verifyChargeBackReason(String endingCardNo) {
        orderPage.verifyChargeBackReason(endingCardNo);
    }

    @Step
    public void verifyChargebackAmount(String currencySymbol, String chargebackAmt) {
        if (disputeType.equals("chargeback")) {
            orderPage.verifyChargebackAmount(currencySymbol, chargebackAmt);
        }
    }

    @Step
    public void verifyValueOfProductDescription(String productName) {
        orderPage.verifyValueOfEachField("Product description", productName);
    }

    @Step
    public void verifyValueOfCustomerName(String customerName) {
        orderPage.verifyValueOfEachField("Customer name", customerName);
    }

    @Step
    public void verifyValueOfCustomerEmail(String customerEmail) {
        orderPage.verifyValueOfEachField("Customer email", customerEmail);
    }

    @Step
    public void verifyValueOfBillingAddress(HashMap<String, String> billingAddress) {
        orderPage.verifyValueOfEachField("Billing address", billingAddress);
    }

    @Step
    public void verifyValueOfShippingAddress(HashMap<String, String> shippingAddress) {
        orderPage.verifyValueOfEachField("Shipping address", shippingAddress);
    }

    @Step
    public void inputValueForProductDescription(String value) {
        orderPage.inputValueByFieldLabel("Product description", value);
    }

    @Step
    public void inputValueForCustomerName(String value) {
        orderPage.inputValueByFieldLabel("Customer name", value);
    }

    @Step
    public void inputValueForCustomerEmail(String value) {
        orderPage.inputValueByFieldLabel("Customer email", value);
    }

    @Step
    public void inputValueForBillingAddress(String value) {
        orderPage.inputValueByFieldLabel("Billing address", value);
    }

    @Step
    public void inputValueForShippingAddress(String value) {
        orderPage.inputValueByFieldLabel("Shipping address", value);
    }

    @Step
    public void inputValueForPackageCarrier(String value) {
        orderPage.inputValueByFieldLabel("Package carrier", value);
    }

    @Step
    public void inputValueForTrackingNumber(String value) {
        orderPage.inputValueByFieldLabel("Tracking number", value);
    }

    @Step
    public void inputValueForProofOfAccessOrDownloadingProduct(String value) {
        orderPage.inputValueByFieldLabel("Proof of access or downloading product", value);
    }

    @Step
    public void inputValueForAdditionalEvidenceOrStatements(String value) {
        orderPage.inputValueByFieldLabel("Additional evidence or statements", value);
    }

    @Step
    public void uploadFileForCustomerSignature(String value) {
        orderPage.uploadFileByFieldLabel("Customer signature", value);
    }

    @Step
    public void uploadFileForReceipt(String value) {
        orderPage.uploadFileByFieldLabel("Receipt", value);
    }

    @Step
    public void uploadFileForCustomerCommunication(String value) {
        orderPage.uploadFileByFieldLabel("Customer communication", value);
    }

    @Step
    public void uploadFileForProofOfShipping(String value) {
        orderPage.uploadFileByFieldLabel("Proof of shipping", value);
    }

    @Step
    public void uploadFileForAdditionalEvidenceOrStatements(String value) {
        orderPage.uploadFileByFieldLabel("Additional evidence or statements", value);
    }

    @Step
    public void selectDateOfShipment() {
        orderPage.selectDateOfShipment();
    }

    @Step
    public void clickOnSubmitResponseBtn() {
        orderPage.clickOnSubmitResponseBtn();
    }

    @Step
    public void clickOnSubmitEvidenceNowBtn() {
        orderPage.clickOnSubmitEvidenceNowBtn();
    }

    @Step
    public void verifyMessageWhenDisputeInProcessing(String disputeType) {
        orderPage.verifyMessageAfterDisputeDecided(disputeType, "Processing", "");
    }

    @Step
    public void verifyMessageWhenDisputeIsLost(String disputeType, String chargeBackAmount) {
        orderPage.verifyMessageAfterDisputeDecided(disputeType, "losing_evidence", chargeBackAmount);
    }

    @Step
    public void verifyMessageAfterDisputeIsWon(String disputeType, String chargeBackAmount) {
        orderPage.verifyMessageAfterDisputeDecided(disputeType, "winning_evidence", chargeBackAmount);
    }

    @Step
    public String getDateOfShipmentAfterClickingOnNow() {
        return orderPage.getDateOfShipmentAfterClickingOnNow();
    }

    @Step
    public String getInputtedValueOfProductDescription() {
        return orderPage.getValueByFieldLabel("Product description");
    }

    @Step
    public String getInputtedValueOfCustomerName() {
        return orderPage.getValueByFieldLabel("Customer name");
    }

    @Step
    public String getInputtedValueOfCustomerEmail() {
        return orderPage.getValueByFieldLabel("Customer email");
    }

    @Step
    public String getInputtedValueOfBillingAddress() {
        return orderPage.getValueByFieldLabel("Billing address");
    }

    @Step
    public String getInputtedValueOfShippingAddress() {
        return orderPage.getValueByFieldLabel("Shipping address");
    }

    @Step
    public String getInputtedValueOfPackageCarrier() {
        return orderPage.getValueByFieldLabel("Package carrier");
    }

    @Step
    public String getInputtedValueOfTrackingNumber() {
        return orderPage.getValueByFieldLabel("Tracking number");
    }

    @Step
    public String getInputtedValueOfProofOfAccessOrDownloadingProduct() {
        return orderPage.getValueByFieldLabel("Proof of access or downloading product");
    }

    @Step
    public String getInputtedValueOfAdditionalEvidenceOrStatements() {
        return orderPage.getValueByFieldLabel("Additional evidence or statements");
    }

    @Step
    public String getInputtedValueOfCustomerSignature() {
        return orderPage.getTextByFieldLabel("Customer signature");
    }

    @Step
    public String getInputtedValueOfReceipt() {
        return orderPage.getTextByFieldLabel("Receipt");
    }

    @Step
    public String getInputtedValueOfCustomerCommunication() {
        return orderPage.getTextByFieldLabel("Customer communication");
    }

    @Step
    public String getInputtedValueOfProofOfShipping() {
        return orderPage.getTextByFieldLabel("Proof of shipping");
    }

    @Step
    public String getInputtedValueOfAdditionalEvidenceOrStatementsUpload() {
        return orderPage.getInputtedValueOfAdditionalEvidenceOrStatementsUpload();
    }

    @Step
    public void clickOnViewResponseBtn() {
        orderPage.clickOnViewResponseBtn();
    }

    @Step
    public void clickOnBreadcrumbInChargeBackForm() {
        orderPage.clickOnBreadcrumbInChargeBackForm();
    }
    //------------------------------------------------------------------------------------------------------------------

    public void verifyAddressWithCountryBrazil(String typeOfShippingAddress, String shippingSF) {
        orderPage.verifyAddressWithCountryBrazil(typeOfShippingAddress, shippingSF);
    }

    @Step
    public void selectExportTemplate(String template) {
        orderPage.selectRadioButtonWithLabel(template, true);
    }

    @Step
    public void addFields(String fields) {
        orderPage.addFieldToTemplate(fields);
    }

    @Step
    public void selectExportType(String exportType) {
        orderPage.selectRadioButtonWithLabel(exportType, true);
    }

    @Step
    public void clickOnEditShippingAddressBtn() {
        orderPage.clickOnEditShippingAddress();
    }

    @Step
    public void enterCpfOrCnpj(String cpfOrCnpj) {
        if (!cpfOrCnpj.isEmpty()) {
            orderPage.enterInputFieldWithLabel("CPF/CNPJ Number", cpfOrCnpj);
        }
    }

    @Step
    public void backToThePage() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.history.go(-1)");
        waitABit(5000);
        getDriver().navigate().refresh();


    }

    @Step
    public String getCountryNameByCodeFromFile(String file, String country) {
        return orderPage.getCountryCodeOrNameFromFile(file, country, "name");
    }

    public void verifyProductNameInFulfillmentStatus(String productName) {
        String act = orderPage.getProductNameInFulfillmentStatus();
        assertThat(act).isEqualTo(productName);
    }

    public void verifyFulfillmentGroup(String productName, String variant, String sku, String quantity) {
//        orderPage.verifyFulfillmentGroup(productName, variant);
//        orderPage.verifyFulfillmentGroup(productName, sku);
        orderPage.getQuantityInFulfillmentStatus(productName, quantity);
    }

    public void verifyQuantityInFulfillmentStatus(String productName, String quantity) {
        orderPage.getQuantityInFulfillmentStatus(productName, quantity);
    }

    public void verifySubTotalInFulfillmentStatus(String subtotal) {
        String act = orderPage.getSubTotalInFulfillmentStatus();
        assertThat(act).isEqualTo(subtotal);
    }

    public void clickOnHandlingFeeExpandArrow() {
        orderPage.clickOnHandlingFeeExpandArrow();
    }

    public void searchOrderPbase(String order) {
        orderPage.searchOrderPbase(order);
    }

    public void verifyItemSubTotalInPaymentStatus(String itemSubtotal) {
        String act = orderPage.getItemSubTotalInPaymentStatus();
        assertThat(act).isEqualTo(itemSubtotal);
    }

    public void verifyShippingMethodInPaymentStatus(String shippingMethod) {
        String act = orderPage.getShippingMethodInPaymentStatus();
        assertThat(act).contains(shippingMethod);
    }

    public void verifySubTotalInPaymentStatus(float subtotal) {
        float actSubTotal = orderPage.getSubToTal();
        assertThat(actSubTotal).isEqualTo(subtotal);
    }

    public void verifyProfitGroup(String label, float val) {
        float act = orderPage.getValInProfitGroup(label);
        assertThat(act).isEqualTo(val);
    }

    public void verifyBaseCostYourProfit(String label) {
        float act = orderPage.getValInProfitGroup(label);
        assertThat(act).isGreaterThan(0);
    }

    public float getBaseCostYourProfit(String label) {
        return orderPage.getValInProfitGroup(label);
    }

    public float roundTwoDecimalPlaces(float number) {
        return orderPage.roundTwoDecimalPlaces(number);
    }

    public void openHandlingFeeInYourProfit() {
        orderPage.openHandlingFeeInYourProfit();
    }

    public void verifyDiscountInPaymentStatus(float actShipFee) {
        float actDiscount = orderPage.getDiscountInPaymentStatus();
        assertThat(actDiscount).isEqualTo(actShipFee);
    }

    public String getPaymentStatus() {
        return orderPage.getPaymentStatus();
    }

    public void verifyPaidByCustomerInPaymentStatus(float total) {
        float actPaid = orderPage.getPaidByCustomerInPaymentStatus();
        assertThat(actPaid).isEqualTo(total);

    }

    public boolean isShippingFeeInYourProfit() {
        return orderPage.isShippingFeeInYourProfit();
    }

    public float getShippingFeeInYourProfit() {
        return orderPage.getShippingFeeInYourProfit();
    }

    public void verifyProfitInYourProfit(float profit) {
        float act = orderPage.getProfitInYourProfit();
        assertThat(act).isBetween(profit - 0.01f, profit + 0.01f);
    }

    public void verifyContentShippingInPaymentStatus(String content) {
        String act = orderPage.getContentShippingInPaymentStatus();
        assertThat(act).isEqualTo(content);
    }

    public void verifyNotShowShippingFeeInYourProfit() {
        orderPage.verifyNotShowShippingFeeInYourProfit();
    }

    public float getPriceProductInFulfillmentStatus(String productName) {
        return orderPage.getPriceProductInFulfillmentStatus(productName);
    }

    public float getPricePPDiscountInFulfillmentStatus(String productPP) {
        return orderPage.getPricePPDiscountInFulfillmentStatus(productPP);
    }

    public float getPriceShippingFreeInPaymentStatus() {
        return orderPage.getPriceShippingFreeInPaymentStatus();
    }

    public void verifyShippingFeeInYourProfit(float shippingFee) {
        float act = orderPage.getShippingFeeInYourProfit();
        assertThat(act).isEqualTo(shippingFee);
    }

    @Step
    public String CountRecentOrderInCustomarDetail() {
        return orderPage.CountRecentOrderInCustomarDetail().replace("orders", "").trim();
    }

    public void clickOnBtnInMoreAction(String page) {
        orderPage.clickOnBtnInMoreAction(page);
    }

    public String getTrackingNumberLineItem() {
        return orderPage.getTrackingNumberLineItem();
    }

    public void focusOut() {
        orderPage.focusOut();
    }

    public void clickSaveBtnOnPU() {
        orderPage.clickBtn("Save");
        orderPage.waitForEverythingComplete(60);
    }

    public void showCalculationYourProfit() {
        orderPage.showCalculationYourProfit();
    }

    public String getContentShippingInPaymentStatus() {
        return orderPage.getContentShippingInPaymentStatus();
    }

    @Step
    public int getTotalOrderDefaultCurrentStore(String label) {
        return orderPage.getTotalOrderDefaultCurrentStore(label);
    }

    @Step
    public void clickOut(String label) {
        orderPage.clickOut(label);
    }

    public void verifyFulfillmentStatus(String status) {
        orderPage.verifyFulfillmentStatus(status);
    }

    public void verifyGroupFulfill(String groupFulfilled, String product, String sku, String quantity, String unitPrice, String buttonCancel, String status) {
        orderPage.verifyGroupFulfill(groupFulfilled, product, sku, quantity, unitPrice, buttonCancel, status);
    }

    public void verifyTotal(String total, String product) {
        orderPage.verifyTotal(total, product);
    }

    public void verifyTimeline(String email, String firstName, String lastName, String text, String status) {
        orderPage.verifyTimeline(email, firstName, lastName, text, status);
    }

    public void clickbutton(String buttonName) {
        orderPage.clickButton(buttonName);
    }

    public void verifyPopupCancel(String title, String text, String product, String discription, String orderName) {
        orderPage.verifyTitle(title);
        orderPage.verifyTextConfirm(text, orderName);
        orderPage.verifyProduct(product);
        orderPage.verifyDiscription(discription);
    }

    public void verifyOrderAfterCancel(String status, String product, String cancelButton, String timeline) {
        orderPage.verifyStatus(status);
        orderPage.verifyStatusProduct(status, product);
        orderPage.verifyButtonCancelFulfillment(cancelButton);
        orderPage.verifyTimelineCancel(timeline);
    }

    @Step
    public int getIndexLineItemGroup(String lineitem) {
        return orderPage.getIndexLineItemGroup(lineitem);
    }

    @Step
    public void fulfillWith(String fulfillmentService, int index) {
        orderPage.selectFulfillmentService(index);
        orderPage.fulfillWith(fulfillmentService, index);
    }

    @Step
    public void verifyCountGroup(int countGroup) {
        int act = orderPage.countGroup();
        assertThat(act).isEqualTo(countGroup);
    }

    @Step
    public void verifyStatusGroup(String statusGroup, int index) {
        String act = orderPage.getStatusGroup(index);
        assertThat(act).contains(statusGroup);
    }

    @Step
    public void isShowCancelFulfillment(boolean isCancelFul, int index) {
        orderPage.isShowButtonInOrderDetail("Cancel fulfillment", index, isCancelFul);
    }

    @Step
    public void isShowAddTKN(boolean isAddTKN, int index) {
        orderPage.isShowButtonInOrderDetail("Add tracking", index, isAddTKN);
    }

    @Step
    public void isFulfillWith(boolean isFulfillWith, int index) {
        orderPage.isShowButtonInOrderDetail("Fulfill with", index, isFulfillWith);
    }

    @Step
    public void isMarkAsFulfilled(boolean isMarkAsFulfilled, int index) {
        orderPage.isShowButtonInOrderDetail("Mark as fulfilled", index, isMarkAsFulfilled);
    }

    @Step
    public void isTrackShipment(boolean isTrackShipment, int index) {
        orderPage.isShowButtonInOrderDetail("Track shipment", index, isTrackShipment);
    }

    @Step
    public void isEditTracking(boolean isEditTracking, int index) {
        orderPage.isShowButtonInOrderDetail("Edit tracking", index, isEditTracking);
    }

    public void refreshPage() {
        orderPage.refreshPage();
        orderPage.waitForPageLoad();
    }

    @Step
    public void verifyDisplayPopupLiveChat() {
        orderPage.verifyDisplayPopupLiveChat();
    }

    @Step
    public String getAccountName() {
        return orderPage.getAccountName();
    }

    //  -----COD-----
    @Step
    public void checkExistedRefundBtn(String expectedState) {
        orderPage.checkExistRefundBtn(expectedState);
    }

    @Step
    public void checkExistedMarkAsPageBtn(String expectedState) {
        orderPage.checkShownMarkAsPaidBtn(expectedState);
    }

    @Step
    public void markAsPaidOrder() {
        orderPage.clickMarkAsPaidBtn();
        orderPage.clickMarkAsPaidBtnOnPopUp();
    }

    @Step
    public void clickBTTrackingNumber() {
        orderPage.clickBTTrackingNumber();
        orderPage.switchToLatestTab();
        orderPage.waitForEverythingComplete();
    }

    public void waitForShowDataOrder() {
        orderPage.waitForShowDataOrder();
    }

    public int getIndexGroupResend() {
        return orderPage.getIndexGroupResend();
    }

    public void verifyItemClaim(String lineitem, int index) {
        String[] value = lineitem.split(">");
        String productName = orderPage.getProductName(index);
        String variant = orderPage.getVariantName(index);
        assertThat(productName).isEqualTo(value[0]);
        assertThat(variant).isEqualTo(value[1]);
    }

    public void verifyClaimId(String claimNew, int index) {
        String claim = orderPage.getClaim(index);
        assertThat(claim).isEqualTo(claimNew);
    }

    public float getShippingForSeller(String label) {
        return orderPage.getShippingForSeller(label);
    }

    public float getDiscountOrder(boolean _flagDiscount, boolean _discountFreeship, boolean _discountPPC, String product) {
        float discount = 0;
        if (_flagDiscount && _discountFreeship) {
            discount = orderPage.getValInPaymentGroup("Shipping");
        }
        if (_flagDiscount && !_discountFreeship) {
            discount = orderPage.removeCurrency(discountAmount);
        }
        if (_discountPPC) {
            discount += orderPage.getDiscountPPC(product);
        }
        return discount;
    }

    public void verifyPaymentGroup(String label, float val) {
        float act = orderPage.getValInPaymentGroup(label);
        assertThat(act).isEqualTo(val);
    }

    public float getPriceTax(String label) {
        return orderPage.getValInPaymentGroup(label);
    }

    public float getShippingForBuyer(String label) {
        return orderPage.getValInPaymentGroup(label);
    }

    public void verifyPaidByCustomerInPaymentGroup(String label, float val) {
        float act = orderPage.getPaidByCustomer(label);
        assertThat(act).isEqualTo(val);
    }

    public float getSubTotalInFulfillmentGroup() {
        return orderPage.getSubTotalInFulfillmentGroup();
    }

    public void verifyFreeShippingOnDB(String shipSF, String discountSF) {
        orderPage.verifyFreeShippingOnDB(shipSF, discountSF);
    }

    public void verifyShippingCarrierNotDisplayed(String carrier) {
        String carriers = carrier.toLowerCase().trim().replace(" ", "-");
        orderPage.verifyShippingCarrierNotDisplayed(carriers);
    }

    public void viewOrderStatus() {
        orderPage.clickBtn("More actions");
        orderPage.selectViewOrderStatus();

    }

    public void clickToRefundButton() {
        orderPage.clickToRefundButton();
    }

    public void inputRefundAmount(String amount) {
        orderPage.inputRefundAmount(amount);
    }

    public void clickOutPage() {
        orderPage.clickOutPage();
    }

    public void fulfillmentOrderPlusBase(String buttonSelectedOrder) {
        orderPage.choiceOrderFulfill();
        orderPage.clickBtn(buttonSelectedOrder);

    }

    public void fulfillOrderPlusBase() {
        orderPage.clickBTFulfill("Fulfill order");

    }

    public void verifyInforOrder(String expect) {
        assertThat(orderPage.getOrderNumber()).isEqualTo(expect);
    }

    public void xpandOrder() {
        orderPage.xpanOrder();
    }

    public void verifyInfoOder(String productNameSB, String variantSB, String skusb, String quantitySB) {
        assertThat(orderPage.getInfoOrderSB(productNameSB, "Variant:")).isEqualTo(variantSB);
        assertThat(orderPage.getInfoOrderSB(productNameSB, "SKU:")).isEqualTo(skusb);
        assertThat(orderPage.getInfoOrderSB(productNameSB, "Quantity:")).isEqualTo(quantitySB);
    }

    public void verifyFulfillmentStatusOrder(String fulfillmentStatus) {
        assertThat(orderPage.getStatusFulfillOrder()).isEqualTo(fulfillmentStatus);
    }

    public void verifyTrackingNumberOrder(String trackingNumber) {
        assertThat(orderPage.getTrackingNumber()).isEqualTo(trackingNumber);
    }

    public void verifyBT(String displayButton) {
        if (displayButton.contains(",")) {
            String[] buttonDisplay = displayButton.split(",");
            for (String button : buttonDisplay) {
                orderPage.verifyDisplayBT(button);
            }
        } else {
            orderPage.verifyDisplayBT(displayButton);
        }
    }

    public void clickAction(String action) {
        orderPage.clickBtn(action);
    }

    public void searchProductName(String productName) {
        orderPage.searchByproductName(productName);
    }

    public void replayProduct(String variants, String status) {
        String[] subVariant = variants.split(">");
        for (String variant : subVariant) {
            orderPage.chooseVariant(variant);
        }
        orderPage.chooseApplyThisChange(status);
        orderPage.clickBtn("Save");
    }

    public void choiceExportTemplate(String template) {
        orderPage.selectRadioButtonWithLabel(template, true);
    }

    public void verifyHeader(List<String> act, List<String> exp) {
        orderPage.verifyHeader(act, exp);
    }

    public void clickChoiceStore(String type) {
        orderPage.clickChoiceStore();
        orderPage.checkCheckboxWithLabel(type, true);
        orderPage.clickChoiceStore();

    }

    public void moveTab(String tabName) {
        orderPage.moveTab(tabName);
    }

    public void selectFirstOrderDispute() {
        orderPage.selectFirstOrderDispute();
    }

    public void verifyContentFile(HashMap<String, String> data) {
        orderPage.verifyContentFile(data);
    }

    public void cancelFirstOrderInOrderDashboard() { //for customer analytics
        orderPage.openFirstOrderInOrderDashboard();
        orderPage.cancelOrder();
    }

    public void searchKeyOnOrderList(String value) {
        orderPage.searchKeyOnOrderList(value);
    }

    public void selectTheNewestOrders() {
        orderPage.selectTheNewstOrders();
    }

    public void clickSelectDropDown(String criteria) {
        orderPage.clickSelectDropDown(criteria);
    }

    @Step
    public void verifyPrintFileGeneratedDone(String product, String sku, boolean bol) {
        orderPage.verifyPrintFileGeneratedDone(product, sku, bol);
    }

    @Step
    public void clickActionOfLineItemPrinfile(String product, String sku, String action) {
        orderPage.verifyPrintFileGeneratedDone(product, sku, true);
        if (!action.isEmpty())
            orderPage.clickActionOfLineItemPrinfile(product, sku, action);
    }

    @Step
    public String getFormatPrintfileName(String product, String sku) {
        return orderPage.getFormatPrintfileName(product, sku);
    }

    @Step
    public void verifyLinkPreviewOfPrintfile(String fileName) {
        if (!fileName.isEmpty())
            orderPage.verifyLinkPreviewOfPrintfile(fileName);
    }

    public void verifyCustomerOrder(String customerName) {
        List<String> listCustomer = orderPage.getListCustomer();
        for (String customer : listCustomer) {
            assertThat(customer).isEqualTo(customerName);
        }
    }

    public void verifyDesignFee(Float designFeeEx) {
        Float designFeeAc = orderPage.getDesignFee();
        assertThat(designFeeAc.toString()).isEqualTo(designFeeEx.toString());
    }

    @Step
    public void clickAddPrintFileOnOder() {
        orderPage.clickAddPrintAFileOnOrderDetail();
    }


    public void importOrderByCSV(String data, String orderName, String date) throws IOException {
        orderPage.importOrder(data, orderName, date);
    }

    public void searchOrderAndVerrify(List<String> orderNameList) {
        for (String nameOrder : orderNameList) {
            importOrderByAPIPage.searchOrder(nameOrder);
            assertThat(orderPage.getOrder()).isEqualTo(nameOrder);
        }
    }

    public void searchOrderAndVerrifyInfo(List<String> orderNameList, String orderDate, String productName, String sku, String error) {
        for (String nameOrder : orderNameList) {
            orderPage.enterTextToTextbox(nameOrder);
            waitABit(7000);
            assertThat(orderPage.getOrder()).isEqualTo(nameOrder);
        }
    }

    public void clickBT(String anImport) {
        orderPage.clickBtn(anImport);
    }

    public void searchAndClickOrderNameAndVerrifyTitle(List<String> orderNameList, String title) {
        for (String nameOrder : orderNameList) {
            orderPage.enterTextToTextbox(nameOrder);
            waitABit(7000);
            orderPage.clickOrderName(nameOrder);
            assertThat(orderPage.getTitleOrderImport()).isEqualTo(title);

        }
    }

    public void verifyError(String error) {
        assertThat(orderPage.getError()).isEqualTo(error);
    }

    public void verifyTimelineWithOrderImport(String timeline) {
        assertThat(orderPage.getTimlineImport()).isEqualTo(timeline);
    }

    public void moveToPage(String name) {
        orderPage.moveToPage(name);
    }

    public void refundLineOfOrder(String product, String quantity) {
        orderPage.enterRefundedQuantity(product, quantity);
        orderPage.refundItemQuantity("Refund");
    }

    @Step
    public void verifyNotDisplayTextName(String text) {
        orderPage.verifyNotDisplayTextName(text);
    }

    @Step
    public void verifyDisplayTextFileAClaim(String text) {
        orderPage.verifyDisplayText(text);
    }

    @Step
    public void verifyDisplayTextViewClaim(String text) {
        orderPage.verifyDisplayText(text);
    }

    @Step
    public void clickText(String text) {
        orderPage.clickText(text);
    }

    @Step
    public void verifyRedirectNewClaim(String page) {
        assertThat(orderPage.getTextNewClaim()).isEqualTo(page);
    }

    @Step
    public void verifyRedirectClaimList(String page, String orderName) {
        assertThat(orderPage.getTextClaimList()).isEqualTo(page);
        assertThat(orderPage.getTextOrderNameInInput()).isEqualTo(orderName);
    }

    @Step
    public void searchClaim(String claim) {
        orderPage.enterInputFieldWithLabel("Search order, claim", claim);
    }

    @Step
    public void verifyRedirectOnClaimDetailPage(String claim) {
        assertThat(orderPage.getTextHeaderClaimDetailPage()).isEqualTo("Claim" + " " + claim);
    }

    @Step
    public void verifyTextRefundOrder(String log) {
        for (String textLogTimeLine : orderPage.getTextRefundOrder()) {
            assertThat(textLogTimeLine).isEqualTo(log);
            orderPage.clickViewInvoice(textLogTimeLine);
        }
    }

    @Step
    public String getTextClaimName() {
        return orderPage.getTextClaimName();
    }

    @Step
    public void clickClaimName() {
        orderPage.clickClaimName();
    }

    public void verifyShippingFeeInOrder() {
        String actFee = orderPage.getShippingFeeInOrder();
        assertThat(actFee).isEqualTo(shippingFee);
    }

    public void verifyShippingMethodInOrder() {
        String actMethod = orderPage.getShippingMethodInPaymentStatus();
        assertThat(actMethod).isEqualTo(shippingMethod);
    }

    @Step
    public void searchOrder(String orderNumber) {
        orderPage.enterInputFieldWithLabel("Search orders", orderNumber);
    }

    @Step
    public void switchTab(String tabName) {
        orderPage.switchToTab(tabName);
        orderPage.waitForEverythingComplete();
    }

    @Step
    public void getTrackingNumber(String page) {
        orderPage.GetTrackingNumberOnShopbase(page);
    }

    public void verifyFulfillOrderAfterGetTN(String Page) {
        orderPage.GetTrackingNumberOnShopbase(Page);
    }

    public String getTrackingNumberaOrderDetail() {
        return orderPage.getTNOnOrderDetail();
    }

    @Step
    public void verifyOrderDetailAfterGetTrackingNumber() {
        orderPage.verifyOrderDetailAfterGetTrackingNumber();
    }

    public void verifyStatusOfOrderAfterCancelFulfillment(String status) {
        orderPage.verifyStatusOfOrderAfterCancelFulfillment(status);
    }

    public void filterMore(String label, String item) {
        orderPage.clickFilterLabel("More filters");
        orderPage.clickFilterLabel(label);
        orderPage.clickCheckboxFilter(label, item, true);
        orderPage.clickFilterLabel("Apply");
    }

//    public void verifyTrachkingNumberInOrderDetail() {
//        orderPage.verifyTrachkingNumberInOrderDetail();
//    }

    public void verifyTrackingNumberInOrderDetail() {
        orderPage.verifyTrackingNumberInOrderDetail();
    }

    public void verifyTaxLineInOrder() {
        orderPage.verifyTaxLineInOrder();
    }

    public void verifyFulfillmentGroupAfterRefund(String product, int refundedQuantity, boolean isRestock) {
        orderPage.verifyFulfillmentGroupAfterRefund(product, refundedQuantity, isRestock);
    }

    public String addTagToLineItem(String textTag) {
        orderPage.clickIconAddTag();
        orderPage.clickAddTagToLineItem();
        orderPage.InputTextTagItem(textTag);
        clickbutton("Confirm");
        return textTag;
    }

    @Step
    public void verifyTagLineItem() {
        orderPage.verifyTagLineItem();
    }

    public void inputCapturePaymentAmount(String amount) {
        orderPage.enterInputFieldWithLabel("Please input", amount);
    }

    @Step
    public void clickOnCapturePaymentButton() {
        orderPage.clickBtn("Capture payment");
    }

    @Step
    public void clickOnAcceptButton() {
        orderPage.clickOnAcceptButton();
    }

    @Step
    public void verifyPrintFileNoGenerate(String product, String sku, boolean bol) {
        orderPage.verifyPrintFileNoGenerate(product, sku, bol);
    }

    @Step
    public String getOrderStatusOnOrderDetail() {
        String status = orderPage.getOrderStatusOnDetail();
        return status;
    }

    @Step
    public void clickGeneratePrintFileOnOrder() {
        orderPage.clickGeneratePrintFileOnOrder();
    }

    @Step
    public void clickButtonGenerateOnPopUpPFOrder() {
        orderPage.clickButtonGenerateOnLineItmeOrder();
    }

    @Step
    public String xpathVerifyGenerateOnLineItem() {
        String xpath = orderPage.xpathVerifyGenerateOnLineItem();
        return xpath;
    }

    public boolean isGenerateOnLineItemExist() {
        return orderPage.isGenerateOnLineItemExist();
    }

    @Step
    public void clickIconDotInOrder(String product, String sku) {
        orderPage.clickInconDotInOrder(product, sku);
    }

    public void verifyProductCost(String productCost) {
        orderPage.clickShowcalculation();
        String act = orderPage.getProductCost();
        assertThat(act).isEqualTo(productCost);
    }


    public void selectOrderInOrdersList() {
        orderPage.selectOrderInOrdersList();
    }

    public void clickBtnActionInOrdersList() {
        orderPage.clickBtnActionInOrdersList();
    }

    public void selectAction(String action) {
        orderPage.selectAction(action);
    }

    public void verifyOrderShow(boolean isShow) {
        orderPage.verifyOrderShow(isShow);
    }

    public void selectTabInOrdersList(String tab) {
        orderPage.selectTabInOrdersList(tab);
    }

    public void verifyStatusOrdersAfterHold(int index, String status) {
        String act = orderPage.getStatusOrdersAfterHold(index);
        assertThat(act).isEqualTo(status);
    }

    public void verifyButtonIsShow(String button) {
        orderPage.verifyButtonIsShow(button);
    }

    public void holdReleaseOrder(String action) {
        orderPage.clickBtn(action);
    }

    public void clickConfirmHoldOrder() {
        orderPage.clickBtn("Hold orders");
    }

    public void verifyPopupCanNotHold() {
        orderPage.verifyPopupCanNotHold();
        orderPage.clickBtn("Cancel");
    }

    public void selectCriteria(String criteria) {
        orderPage.clickCriteria();
        orderPage.selectCriteria(criteria);
    }

    @Step
    public void verifyOrderInformation(String expect) {
        assertThat(orderPage.getOrderNameFirst()).isEqualTo(expect);
    }

    @Step
    public void notResult(String expect) {
        orderPage.verifyNotResult(expect);
    }


    @Step
    public void clickEditOrder() {
        clickbutton("More actions");
        clickbutton("Edit order");
    }

    @Step
    public void clickOptionEdit(String optionEdit) {
        orderPage.selectOptionEditProduct(optionEdit);
    }

    @Step
    public void enterQuantity(String quantity) {
        orderPage.enterQuantityEdit(quantity);
    }

    @Step
    public void searchOrderInOrderList() {
        orderPage.searchOrderNameOnOrderList(orderNumber);
        orderPage.clickOrderName(orderNumber);
    }

    @Step
    public void byMoreItem() {
        orderPage.clickButton("More actions");
        orderPage.clickButton("View order status page");
        orderPage.switchToLatestTab();
        orderPage.byMoreItemInStatusPage();
    }

    @Step
    public void changeProduct(String productName, String changeProduct) {
        orderPage.searchProduct(productName, changeProduct);
    }

    @Step
    public void updateProduct(String buttonName) {
        clickbutton("Confirm");
        orderPage.clickbuttonSendInvoice(buttonName);
    }

    @Step
    public void clickAddProductFilter() {
        orderPage.clickAddProductFilter();
    }

    @Step
    public void inputFilterCollection(String collection) {
        orderPage.inputFilterCollection(collection);
        orderPage.selectCheckbox(collection);
    }

    public void inputFilterTag(String tag) {
        orderPage.inputFilterTag(tag);
        orderPage.selectCheckbox(tag);
    }

    public void verifyStatusOrderAfterUpdate(String statusOrder) {
        orderPage.verifyStatusOrderAfterUpdate(statusOrder);
    }

    public void sortOrderListByField(String sortType, String field) {
        orderPage.sortOrderListByField(sortType, field);
    }

    public String getQuantityOfOrderList() {
        return orderPage.getQuantityOfOrderList();
    }

    public String getQuantityOfOrderOnTab(String tab) {
        return orderPage.getQuantityOfOrderOnTab(tab);
    }

    public int getIndexTab(String tab) {
        int index = orderPage.getIndexTab(tab);
        return index;
    }

    public void enterOrderThenEnter(String orderName, int index) {
        orderPage.enterOrderThenEnter(orderName, index);
    }

    public void searchFraudFilter(String fraudFilterName) {
        orderPage.searchFraudFilter(fraudFilterName);
    }

    @Step
    public void addFraudFilter() {
        orderPage.clickBtn("Add filter");
    }

    @Step
    public void deleteFraudFilter(String sFraudFilter) {
        orderPage.deleteFraudFilter(sFraudFilter);
    }

    @Step
    public void chooseRuleAction(String action) {
        orderPage.chooseRuleAction(action);
    }

    @Step
    public void selectRule(String sRuleName, String sRuleCondition, String sValue) {
        orderPage.selectRuleName(sRuleName);
        orderPage.selectRuleCondition(sRuleCondition);
        orderPage.inputValue(sValue);
    }

    @Step
    public void enterFraudRuleName(String sName) {
        orderPage.waitUntilTheFieldDisplayed("Name");
        orderPage.enterInputFieldWithLabel("Name", sName);
    }

    @Step
    public String getQuantityOfOrderAffect(String filterName) {
        return orderPage.getQuantityOfOrderAffect(filterName);
    }

    @Step
    public void verifyQuantityOfOrderAffect(int before, int after, int increase) {
        assertThat(before + increase).isEqualTo(after);
    }
}