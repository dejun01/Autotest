package opencommerce.orders.order;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.ManageOrdersSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.*;
import com.opencommerce.shopbase.dashboard.orders.api.StripeAPI;
import com.opencommerce.shopbase.dashboard.orders.api.TransactionsAPI;
import com.opencommerce.shopbase.dashboard.orders.steps.*;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.customer.steps.CustomerSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.plusbase.steps.OrderPlusBaseStep;
import com.opencommerce.shopbase.storefront.api.InfoAPI;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.apps.crosspanda.fulfilment.MyOrdersDef;
import opencommerce.fulfillment_service.claim.ClaimListDef;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.assertj.core.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.ProductVariable.*;
import static com.opencommerce.shopbase.ProductVariable.variantValueOdoo;
import static com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps.orderState;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Java6Assertions.fail;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.products.steps.PersonalizeProductSteps;
import org.yecht.Data;

public class OrderDef {
    @Steps
    MappingProductSteps mappingProductSteps;
    @Steps
    FulfillWithSbaseOrderSteps fulfillWithSbaseOrderSteps;
    @Steps
    OrderSteps orderSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    CustomerSteps customerSteps;
    @Steps
    StripeAPI stripeAPI;
    @Steps
    TransactionsAPI transactionsAPI;
    @Steps
    InfoAPI infoAPI;
    @Steps
    MyOrdersDef myOrdersDef;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    FulfillWithShopBaseFulfillmentSteps fulfillmentSteps;
    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    ManageOrdersSteps manageOrdersSteps;
    @Steps
    PurchaseOrderDetailSteps purchaseOrderDetailSteps;
    @Steps
    FulfillmentSteps ffSteps;

    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;
    @Steps
    PersonalizeProductSteps personalizeProductSteps;
    @Steps
    OrderPlusBaseStep orderPlusBaseStep;
    int orderQuantityBefore;
    int orderQuantityAfter;
    String statusOrder = "";
    String orderName = "";
    String date = "";
    List<String> listOrderImport = new ArrayList<>();
    List<String> listError = new ArrayList<>();


    @And("^click on order name in unfulfilled orders list$")
    public void clickOrderNameOnListOrders() {
        orderSteps.clickNameOrderOnList(orderNumber);
        orderSteps.verifyDisplayPopupLiveChat();
    }

    @And("^verify order information in order detail$")
    public void verifyOrderInformationInOrderDetail() {
        capturedTransactionID = orderSteps.getOrderCapturedTransactionID();
        orderSteps.verifyTotalOnDB(totalAmt);
        if (!discountCode.isEmpty()) {
            orderSteps.verifyDiscountOnDB(discountCode);
        }
    }

    @Given("^Open order details and verify status = \"([^\"]*)\"(| of the first shop| of the second shop)$")
    public void verify_status_of_order(String status, String shopType, List<List<String>> dataTable) {
        switch (shopType) {
            case " of the first shop":
                orderSteps.clickNameOrderOnList(orderNameList.get(0));
                break;
            case " of the second shop":
                orderSteps.clickNameOrderOnList(orderNameList.get(1));
                break;
            default:
                orderSteps.clickNameOrderOnList(orderNumber);
        }

        orderSteps.verifyStatusOrders(status);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String statusItem = SessionData.getDataTbVal(dataTable, row, "Status");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String trackingNumbers = SessionData.getDataTbVal(dataTable, row, "Tracking numbers");
            String carrier = SessionData.getDataTbVal(dataTable, row, "Carrier");
            String notification = SessionData.getDataTbVal(dataTable, row, "Notification");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");

            orderSteps.verifyLineItem(sku, statusItem, quantity);

            if (!"Unfulfilled".equals(statusItem)) {
                if (!"".equals(trackingNumbers)) {
                    orderSteps.verifyTrackingNumbers(sku, statusItem, quantity, trackingNumbers);
                }

                if (!"".equals(carrier)) {
                    orderSteps.verifyCarrier(carrier);
                }

                if (!"".equals(notification)) {
                    orderSteps.verifyTrackingNotification(notification);
                }

                if (!"".equals(url)) {
                    orderSteps.verifyURL(sku, statusItem, quantity, url, trackingNumbers);
                }
            }
        }
    }

    @Then("^Search and verify fulfillment status = \"([^\"]*)\" on order list(| of the first shop| of the second shop)$")
    public void searchAndVerifyFulfillmentStatus(String status, String shopType) {
        switch (shopType) {
            case " of the first shop":
                orderSteps.searchOrderNameOnOrderList(orderNameList.get(0));
                orderSteps.verifyFulfillmentStatusOnOrderList(orderNameList.get(0), status);
                break;
            case " of the second shop":
                orderSteps.searchOrderNameOnOrderList(orderNameList.get(1));
                orderSteps.verifyFulfillmentStatusOnOrderList(orderNameList.get(1), status);
                break;
            default:
                orderSteps.searchOrderNameOnOrderList(orderNumber);
                orderSteps.verifyFulfillmentStatusOnOrderList(orderNumber, status);

        }

    }

    @Then("^verify fulfillment status = \"([^\"]*)\" on order list(| of the first shop| of the second shop)$")
    public void verifyFulfillmentStatus(String status, String shopType) {
        String orderName = orderNumber;
        if (" of the first shop".equals(shopType)) {
            orderName = orderNameList.get(0);
        }
        if (" of the second shop".equals(shopType)) {
            orderName = orderNameList.get(1);
        }
        orderSteps.verifyFulfillmentStatusOnOrderList(orderName, status);
    }

    @Given("^Select the order in order list to view detail$")
    public void clickOnTheOrderInList() {
        orderSteps.clickOnTheOrderInList(orderNumber);
    }

    @Given("^Verify order detail after checkout successfully$")
    public void verifyOderDetailCheckoutSuccessFully() {
        int i = 1;
        for (String products : listProduct) {

            String[] _number = products.split("\\|");
            String productName = _number[0];
            String _price = _number[1];
            String _quantity = _number[2];

            thankyouSteps.logInfor("Product name : " + productName + " has price: " + _price + " Quantity :" + _quantity + " Total :" + totalAmt);
            orderSteps.verifyProductInformation(i, productName, _quantity, _price);
            i++;
        }
        orderSteps.verifyTotalOrder(totalAmt);
        if (!isOnPostPurchase) {
            orderSteps.verifyPaidByCustomer(totalAmt);
            orderSteps.verifyOrderStatus("Paid");
        }
    }

    @Then("^fulfill the order with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void fulfill_the_order(String fulfilledItems, String trackingNumber, String
            shippingCarrier, String trackingURL, String status) {
        orderSteps.clickOnMarkAsFullfilledButton();
        orderSteps.fulfilledOrder(fulfilledItems, trackingNumber, shippingCarrier, trackingURL);
        statusOrder = status;
        orderSteps.verifyStatusOrders(status);
    }

    @And("^verify that the \"([^\"]*)\", \"([^\"]*)\" on thank you page navigate to \"([^\"]*)\"$")
    public void verify_that_the_tracking_number_on_thank_you_page_navigate_to(String carrier, String
            trackingNumber, String thirdParty) {
        if (statusOrder.equals("Fulfilled")) {
            orderSteps.viewOrderStatus();
            orderSteps.verifyShippingCarrierNotDisplayed(carrier);
        } else {
            orderSteps.viewOrderStatusPage();
            orderSteps.verifyShippingCarrierDisplayed(carrier);
            orderSteps.clickOnTrackingNumber(trackingNumber);
            orderSteps.verifyTrackingNumberLinkToThirdParty(carrier, thirdParty, trackingNumber);
        }
    }

    @And("^click the specific order \"([^\"]*)\"$")
    public void click_specific_order(String orderNumber) {
        orderSteps.clickNameOrderOnList(orderNumber);
    }

    @Then("^open cancel order popup then cancel fully order and restock items$")
    public void cancel_all_order_and_restock_items() {
        orderSteps.openCancelOrderPopup();
        orderSteps.inputShippingAmount();
        orderSteps.confirmCancelOrder();
        orderSteps.verifyCancelStatus();
    }

    private Float calculateRefundedShipping(String refundAmount) {
        float refundedShipping = 0;
        boolean isContainsCurrencySymbol = orderSteps.isContainCurrencySymbol(refundAmount);
        if (isContainsCurrencySymbol) {
            refundedShipping = Float.parseFloat(refundAmount.split("\\p{Sc}")[1]);
        } else if (refundAmount.matches("@(.*)@")) {
            refundedShipping = Float.parseFloat(shippingFee.split("\\p{Sc}|\\s", 3)[1]);
        }
        return refundedShipping;
    }

    @And("^refund order with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void refund_order(String _refundedItem, String _isRestock, String _refundedShipping, String _reasonForRefund) {
        isRestock = Boolean.parseBoolean(_isRestock);

        if (!_refundedItem.isEmpty()) {
            if (_refundedItem.contains(">")) {
                String[] refunded = _refundedItem.split(">");
                refundedProduct = refunded[0];
                refundedQuantity = Integer.parseInt(refunded[1]);
            }
        }
        sRefundedShipping = calculateRefundedShipping(_refundedShipping);

        // get data before refunding
        originalPrice = orderSteps.getOriginalPriceBeforeRefunding(refundedProduct);

        if (!discountAmount.isEmpty()) {
            discountedPrice = orderSteps.getDiscountedPriceBeforeRefunding(refundedProduct);
        }
        originalQuantity = orderSteps.getOriginalQuantityBeforeRefunding(refundedProduct);

        //refunding an order
        orderSteps.navigateToRefundScreen();
        orderSteps.enterRefundedShipping(sRefundedShipping);
        orderSteps.enterRefundedItemQuantity(refundedProduct, refundedQuantity);
        orderSteps.isRestockedItem(String.valueOf(refundedQuantity), isRestock);
        orderSteps.enterReasonForRefund(_reasonForRefund);
        float price = orderSteps.getPriceOfRefundedItem();
        refundAmount = orderSteps.getRefundAmount(refundedQuantity, price, sRefundedShipping);

        boolean check = orderSteps.expectedRefund(refundAmount);
        if (check) {
            orderSteps.clickOnRefundButton();
        }
        // get data after refunding
        financialStatus = orderSteps.getFinancialStatusOfOrder();
        if (!financialStatus.equals("Paid") && !financialStatus.equals("Authorized")) {
            netPayment = orderSteps.getOrderInfor("Net Payment");
        }
        remainingQuantity = originalQuantity - refundedQuantity;

        // only roller theme product name is uppercase
        productListAfterRefunding = (HashMap<String, List<String>>) productListAdded.clone();
        if (remainingQuantity > 0) {
            productListAfterRefunding.replace(refundedProduct + ":" + refundedVariant, asList(String.valueOf(remainingQuantity), originalPrice));
        } else {
            productListAfterRefunding.remove(refundedProduct + ":" + refundedVariant);
        }
        if (!isRestock) {
            orderSteps.verifyFulfillmentGroupAfterRefund(refundedProduct, refundedQuantity, isRestock);
        }
    }

    @And("^verify order detail including \"([^\"]*)\", \"([^\"]*)\" and quantity of item after refunding$")
    public void verify_order_after_refunding(String status, String netPayment) {
        if (netPayment.equalsIgnoreCase("Paid by customer\t- Refunded")) {
            // If order status = Paid, Paid by customer always equal to Total order
            double paidByCustomer = convertPriceToFloat(totalAmt);
            double refunded = convertPriceToFloat(refundAmount);
            netPayment = "$" + roundOff(paidByCustomer - refunded);
        }
        orderSteps.verifyTheProductIsRestocked(refundedProduct, isRestock);
        orderSteps.verifyOriginalPriceAfterRefunding(refundedProduct, originalPrice);
        if (!discountAmount.isEmpty()) {
            orderSteps.verifyDiscountedPriceAfterRefunding(refundedProduct, discountedPrice);
        }
        orderSteps.verifyOrderDetailAfterRefundSuccessfully(status, netPayment);
    }

    private float convertPriceToFloat(String s) {
        return orderSteps.convertPriceToFloat(s);
    }

    private String roundOff(double d) {
        return String.format("%1.2f", d);
    }

    @Given("^verify that edit the order's information successfully$")
    public void verify_that_edit_the_order_s_information_successfully(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sNote = SessionData.getDataTbVal(dataTable, row, "Notes");
            String sContactInformation = SessionData.getDataTbVal(dataTable, row, "CONTACT INFORMATION");
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String sCompany = SessionData.getDataTbVal(dataTable, row, "Company");
            String sPhoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sApartment = SessionData.getDataTbVal(dataTable, row, "Apartment, suite, etc... (optional)");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sRegion = SessionData.getDataTbVal(dataTable, row, "Region");
            String sZipcode = SessionData.getDataTbVal(dataTable, row, "ZIP/Postal Code");
            String sTags = SessionData.getDataTbVal(dataTable, row, "Tags");


            orderSteps.editNotesThenVerify(sNote);
            orderSteps.editContactInformationThenVerify(sContactInformation);
            orderSteps.editShippingAddress(sFirstName, sLastName, sCompany, sPhoneNumber, sAddress, sApartment, sCity, sCountry, sState, sRegion, sZipcode);
            orderSteps.verifyAddressOnDB("SHIPPING ADDRESS", sFirstName + " " + sLastName + " " + sCompany + " " + sAddress + " " + sApartment + " " + sCity + " " + sZipcode + " " + sRegion + sState + " " + sCountry + " " + sPhoneNumber);
            orderSteps.editTagsThenVerify(sTags);
            orderSteps.saveChanges();
        }
    }


    @And("^implement export \"([^\"]*)\" orders$")
    public void export_order(String option) {
        int number = 0;

        switch (option) {
            case "Current search":
                orderSteps.clickOnExportButton();
                orderSteps.selectOptionToExportOrder(option);
                break;
            case "Current page":
                orderSteps.clickOnExportButton();
                orderSteps.selectOptionToExportOrder(option);
                break;
            case "All orders":
                orderSteps.clickOnExportButton();
                orderSteps.selectOptionToExportOrder(option);
                break;
            default:
                if (option.startsWith("Selected")) {
                    number = Integer.parseInt(option.split("Selected ")[1].split("orders")[0].trim());
                    orderSteps.selectPagination(number);
                    orderSteps.selectAllOrders();
                    orderSteps.clickOnExportButton();
                    orderSteps.exportOrders(number);
                }

        }
        orderSteps.exportToFile(number);
    }

    ///--------------------------

    public int numberOfUnfulfillOrdersBefore, numberOfUnfulfillOrdersAfter, quantity;
    public String quantityBeforeCancel, discount;
    public String addressSF;
    public String shippingSF;
    public static String discountSF;
    public String totalSF, description, value_type;
    public String emailAddress;
    public long variant_id;
    public float value, amount;
    public String refundAmount = "", netPayment = "";
    public boolean isRestock;
    public float sRefundedShipping = 0;
    public String refundedProduct;
    public String financialStatus;
    public String fulfilledProduct;
    public String fulfilledStatus;
    int originalQuantity, refundedQuantity, remainingQuantity, fulfilledQuantity = 0;
    public HashMap<String, List<String>> productListAfterRefunding = new HashMap<>();
    public String quantityOrderAffectBeforeCheckout;


    @And("^get quantity of products \"([^\"]*)\" before (cancelling|refunding)$")
    public void quantity_of_product_before_cancel(String productName, String type) {
        productDetailSteps.searchProduct(productName);
        productDetailSteps.chooseProduct(productName);
        quantityBeforeCancel = orderSteps.getQuantityOfProduct();
    }


    @Then("^get quantity of products \"([^\"]*)\" after cancelling or refunding$")
    public void quantity_of_product_after_cancel(String productName) {
        productDetailSteps.searchProduct(productName);
        productDetailSteps.chooseProduct(productName);
        String quantityAfterCancel = orderSteps.getQuantityOfProduct();
        int after = Integer.parseInt(quantityAfterCancel);
        int before = Integer.parseInt(quantityBeforeCancel);
        orderSteps.verifyQuantity(before, after);

    }

    @And("^get order detail on storefront$")
    public void get_order_detail_on_storefront() {
        addressSF = orderSteps.getAddressOnSF();
        shippingSF = orderSteps.getShippingSF();
        totalSF = orderSteps.getTotalOnSF();
        if (orderSteps.isDiscount()) {
            discountSF = orderSteps.getDiscountOnSF();
        }
    }

    @Then("^verify order detail on dashboard$")
    public void verify_order_detail_on_DB() {
        String customerName = orderSteps.getCustomerName();
        int i = 1;
        if (!isTippingAdded) {
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
        } else {
            orderSteps.verifyTippingOnDB(tippingAmountAdded);
            orderSteps.verifyTotalOrder(totalAmt);
            orderSteps.verifyPaidByCustomer(totalAmt);
        }

        if (paymentGateway.equalsIgnoreCase("COD") || paymentGateway.equalsIgnoreCase("Bank Transfer")) {
            orderState = "Pending";
        } else {
            orderState = "Paid";
        }

        orderSteps.verifyOrderStatus(orderState);
        orderSteps.verifyShippingOnDB(shippingFee);
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

        if (discountSF != null) {
            orderSteps.verifyDiscountOnDB(discountCode);
        }

        if (float_totalAmt > 0) {
            if ((isOnPostPurchase && (paymentGateway.equalsIgnoreCase("PayPal") | paymentGateway.equalsIgnoreCase("Unlimint") | paymentGateway.equalsIgnoreCase("Asia-Bill")))) {
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, "$" + paidTotalAmtByPaypal, cardType, endingCardNo);
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, "$" + roundOffTo2DecPlaces(float_totalAmt), cardType, endingCardNo);
            } else if (isOnPostPurchase && paymentGateway.equalsIgnoreCase("Oceanpayment")) {
                orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, "$" + paidTotalAmtByPaypal, cardType, endingCardNo);
            } else if (isOnPostPurchase && paymentGateway.equalsIgnoreCase("Stripe") && (paymentMethod.equalsIgnoreCase("Bancontact") | paymentMethod.equalsIgnoreCase("iDEAL") | paymentMethod.equalsIgnoreCase("giropay"))) {
                orderSteps.verifyEUOrderTimeLine(paymentGateway, "$" + paidTotalAmtByPaypal, paymentMethod);
                orderSteps.verifyEUOrderTimeLine(paymentGateway, "$" + paidTotalAmtByPaypal, paymentMethod);
            } else {
                if ((paymentMethod.equalsIgnoreCase("SEPA Direct Debit")) | paymentMethod.equalsIgnoreCase("Bancontact") | paymentMethod.equalsIgnoreCase("iDEAL") | paymentMethod.equalsIgnoreCase("giropay")) {
                    orderSteps.verifyEUOrderTimeLine(paymentGateway, totalAmt.split("\\s")[0], paymentMethod);
                } else {
                    orderSteps.verifyOrderTimeline(paymentGateway, customerName, customerEmail, totalAmt.split("\\s")[0], cardType, endingCardNo);
                }
            }
        }

    }

    @Then("^implement update merchant's email address$")
    public void update_merchant_email_address() {
        long currentTime = System.currentTimeMillis();
        String currentExportTime = Long.toString(currentTime);
        emailAddress = orderSteps.updateMerchantEmailAddress(currentExportTime);
    }


    @And("verify that send mail to merchant with file name \"([^\"]*)\"$")
    public void verify_file_name(String fileName) {
        orderSteps.verifyFileName(fileName);
    }

    @And("^get the number of initial unfulfilled orders in sidebar menu$")
    public void verify_the_number_of_unfulfilled_orders_in_sidebar_menu() {
        numberOfUnfulfillOrdersBefore = orderSteps.getTheNumberOfUnfulfilledOrdersInSideBarMenu();
    }

    @And("^verify the number of unfulfilled orders in sidebar menu after checking out$")
    public void verify_the_number_of_unfulfilled_orders_in_sidebar_menu_after() {
        numberOfUnfulfillOrdersAfter = orderSteps.getTheNumberOfUnfulfilledOrdersInSideBarMenu();
        numberOfUnfulfillOrdersAfter = numberOfUnfulfillOrdersBefore + 1;
    }

    @And("^verify the number of \"([^\"]*)\" in sidebar menu after mark as fulfilled$")
    public void verify_the_number_of_unfulfilled_orders_in_sidebar_menu_after_mark_as_fulfilled(String numberOfUnfulfilled) {
        int fulfilled = Integer.parseInt(numberOfUnfulfilled);
        numberOfUnfulfillOrdersAfter = numberOfUnfulfillOrdersAfter + fulfilled;
    }

    @And("^verify that order details should be shown the warning \"([^\"]*)\"$")
    public void verify_show_warning_in_order_details(String warning) {
        orderSteps.verifyShowWarningForChargeBack(warning);
        orderSteps.verifyTimeLine(warning);
    }

    @Given("^submit chargeback response with the evidence \"([^\"]*)\"$")
    public void submit_chargeback_response_with_the_evidence(String evidence) {
        orderSteps.submitEvidence(evidence);
    }


    @Given("^create a new draft order by API with data$")
    public void creat_a_new_draft_order(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variant_id = Long.parseLong(SessionData.getDataTbVal(dataTable, row, "Variant ID"));
            quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            discount = SessionData.getDataTbVal(dataTable, row, "Applied discount");

            orderSteps.requestBodyCreateOrder(variant_id, quantity, discount, description, value_type, value, amount);
        }
    }

    @Then("^apply discount code for entire line items of the draft order$")
    public void apply_discount_code_for_entire_line_items_of_the_draft_order(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            description = SessionData.getDataTbVal(dataTable, row, "Description");
            value_type = SessionData.getDataTbVal(dataTable, row, "Value type");
            value = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Value").replace("$", " "));
            amount = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Amount").replace("$", " "));

            orderSteps.applyDiscountForOrder(description, value_type, value, amount);
        }
    }

    @Given("^verify that the content of file downloaded is matched with order information from dashboard$")
    public void verify_the_content_of_file_downloaded(List<String> dataTable) {
        FILE_PATH_ORDER_DOWNLOADED = orderSteps.openFileDownloaded();
        for (String column : dataTable) {
            orderSteps.readingOrderInfoBasedOnHeader(FILE_PATH_ORDER_DOWNLOADED, column);
        }
        orderSteps.verifyThatDownloadedFileMatchingTemplate(actualOrderInfo, expectedOrderInfo);
    }

    @Given("^Check indicator content and color$")
    public void verifyIndicator(List<List<String>> dataTable) {
        orderSteps.clickOnViewFullAnalysis();
        String indicator = "";
        String color = "";
        String classValue = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            indicator = SessionData.getDataTbVal(dataTable, row, "Indicators");
            classValue = SessionData.getDataTbVal(dataTable, row, "Class value");

            orderSteps.verifyIndicator(indicator, classValue);
        }
    }

    @Given("^Compare order status in sbase and paypal dashboard$")
    public void compareOrderStatus() {
        String orderStatus = orderSteps.getOrderStatusInOrderList(orderNumber);
        orderSteps.compareOrderStatusWithPaypalPaymentStatus(orderStatus, paymentStatus);
    }

    @And("^search orders by the below mentioned criteria then verify search function working properly$")
    public void searchOrdersByTheBelowMentionedCriteriaThenVerifySearchFunctionWorkingProperly(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _criteria = SessionData.getDataTbVal(dataTable, row, "Criteria");
            String _expectedValue = "";
            switch (_criteria) {
                case "Order name":
                    _expectedValue = orderNumber;
                    break;
                case "Customer name":
                    _expectedValue = customerName;
                    break;
                default:
                    _expectedValue = customerEmail;
                    break;
            }
            orderSteps.clickSelectDropDown(_criteria);
            orderSteps.enterTextThenEnter(_expectedValue);
            orderSteps.selectTheNewestOrders();
            orderSteps.verifyOrderInfor(_criteria, _expectedValue);
            orderSteps.backToListOfOrders();
        }
    }

    @Then("^click on the customer name link text should be redirected to customer detail page$")
    public void clickOnTheCustomerNameLinkTextShouldBeRedirectedToCustomerDetailPage() {
        orderQuantityBefore = Integer.parseInt(orderSteps.countOrderQuantityInOrderDetail());
        orderSteps.clickOnTheCustomerName();
        customerSteps.verifyCustomerNamePresent(customerName);
    }

    @And("^search order by text \"([^\"]*)\" then select$")
    public void searchOrderByTextThenSelect(String text) {
        if (text.contains("@")) {
            text = orderNumber;
        }
        orderSteps.enterTextThenEnter(text);
        orderSteps.clickNameOrderOnList(text);
        orderSteps.verifyOrderInfor("order name", text);
        customerName = orderSteps.getCustomerName();
    }

    @And("^search order by text \"([^\"]*)\"$")
    public void searchOrderByText(String text) {
        if (text.contains("@")) {
            text = orderNumber;
        }
        orderSteps.enterTextThenEnter(text);
    }

    @Given("search order by text {string} on tab {string}")
    public void search_order_by_text_on_tab(String orderName, String tab) {
        if (orderName.contains("@")) {
            orderName = orderNumber;
        }
        int index = orderSteps.getIndexTab(tab);
        orderSteps.enterOrderThenEnter(orderName, index);
    }

    @Given("select the tab {string} in Orders list")
    public void select_the_tab_in_Orders_list(String tab) {
        orderSteps.selectTabInOrdersList(tab);
        orderSteps.refreshPage();
    }


    @Then("go to {string} page from More action")
    public void go_to_page_from_More_action(String page) {
        orderSteps.clickOnMoreActionBtn();
        orderSteps.clickOnBtnInMoreAction(page);
    }

    @Given("get tracking number of order")
    public void get_tracking_number_of_order() {
        orderSteps.clickbutton("Edit tracking");
        trackingNumber = orderSteps.getTrackingNumberLineItem();
        orderSteps.refreshPage();
        orderSteps.waitForShowDataOrder();
    }


    @And("^click on the number of orders per customer link text should be redirected to list of orders filtered by the customer$")
    public void clickOnTheNumberOfOrdersPerCustomerLinkTextShouldBeRedirectedToListOfOrdersFilteredByTheCustomer() {
//        orderSteps.clickOnTheOrderQuantityFromOrderDetail();
//        orderQuantityAfter = orderSteps.countTotalOrdersInOrderList().size();
        orderQuantityAfter = Integer.parseInt(orderSteps.CountRecentOrderInCustomarDetail());
        orderSteps.verifyOrderQuantity(orderQuantityBefore, orderQuantityAfter);
    }

    @And("^verify order fulfilled should be hidden \"([^\"]*)\" option$")
    public void verifyOrderFulfilledShouldBeHiddenOption(String optionName) {
        orderSteps.clickOnMoreActionBtn();
        orderSteps.verifyIsOptionPresent(optionName, false);
    }

    @And("^verify refund order page$")
    public void verifyRefundOrderPageAfterRefundingOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _message = SessionData.getDataTbVal(dataTable, row, "Message");
            String _remainingItems = SessionData.getDataTbVal(dataTable, row, "Remaining items");
            String _refundedShipping = SessionData.getDataTbVal(dataTable, row, "Refunded shipping");
            String _availableForRefund = SessionData.getDataTbVal(dataTable, row, "Available for refund");
            // Available for refund = net payment = total order - refunded
            if (netPayment != null) {
                _availableForRefund = netPayment;
            } else {
                _availableForRefund = totalAmt;
            }
            orderSteps.navigateToRefundScreen();
            orderSteps.verifyMsgIsDisplayed(_message);
            orderSteps.verifyRemainingItems(_remainingItems);
            orderSteps.verifyRefundedShipping(_refundedShipping);
            orderSteps.verifyAvailableForRefund(_availableForRefund);
        }
    }

    public Float parsePriceToFloat(String price) {
        if (!price.isEmpty() || price == null) {
            return orderSteps.parseFloat(price);
        }
        return Float.valueOf(0);
    }

    private String roundOffTo2DecPlaces(float val) {
        return String.format("%.2f", val);
    }

    @Then("^open cancel popup to verify information is displayed correctly$")
    public void openCancelPopupToVerifyInformationIsCorrect() {
        String warning = "All payments will be voided. Canceling an order can’t be undone.";

        // If an order is refunded, expected subtotal (which displayed on cancel popup) = original subtotal - refunded amount + refunded shipping
        float originalSubtotal = orderSteps.getSubTotal();
        String expectedSubtotal = roundOffTo2DecPlaces(originalSubtotal - parsePriceToFloat(refundAmount) + sRefundedShipping);
        float originalShipping = parsePriceToFloat(shippingFee);
        String remainingShipping = roundOff(originalShipping - sRefundedShipping);
        orderSteps.openCancelOrderPopup();
        orderSteps.verifyMsgIsDisplayed(warning);
        orderSteps.verifyReturnedProductOnCancelPopup(refundedProduct);
        if (!productListAfterRefunding.isEmpty()) {
            orderSteps.verifyRemainingProductOnCancelPopup(productListAfterRefunding);    // order have refunded
        } else {
            orderSteps.verifyRemainingProductOnCancelPopup(productListAdded);
        }

        if (!discountCode.isEmpty()) {
            orderSteps.verifyInformationOnCancelPopup("Discount " + discountCode, "Applied");
        }
        orderSteps.verifyInformationOnCancelPopup("Subtotal", expectedSubtotal);
        orderSteps.verifyRemainingShipping(remainingShipping);
        if (!netPayment.isEmpty()) {
            orderSteps.verifyInformationOnCancelPopup("Total available to refund", netPayment);
        } else {
            orderSteps.verifyInformationOnCancelPopup("Total available to refund", totalAmt);
        }
        if (paymentGateway.equalsIgnoreCase("Oceanpayment")) {
            paymentGateway = "Ocean Payment 1";
        }
        orderSteps.verifyMsgIsDisplayed("Refund with: " + paymentGateway);
    }

    @And("^cancel fully order and restock items$")
    public void cancelCancelFullyOrderAndRestockItems() {
        orderSteps.inputShippingAmount();
        orderSteps.confirmCancelOrder();
        orderSteps.verifyCancelStatus();
    }

    @Then("^refund order on Shopbase dashboard$")
    public void refund_order_on_Shopbase_dashboard_as(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String product = SessionData.getDataTbVal(dataTable, row, "Product:Variant>Quantity");
            String orderName = "";
            if (xOrderName.contains("@")) {
                orderName = myOrdersDef.getOrderName(xOrderName);
            }
            orderSteps.enterTextThenEnter(orderName);
            orderSteps.selectTheNewestOrder();
            orderSteps.navigateToRefundScreen();
            String[] listProduct = product.split(";");
            for (String sProduct : listProduct) {
                String[] iProduct = sProduct.split(">");
                int quantity = Integer.parseInt(iProduct[1]);
                orderSteps.enterRefundedItemQuantity(iProduct[0], quantity);
            }
            orderSteps.clickOnRefundButton();
        }
    }

    @Then("^fulfill order on Shopbase dashboard$")
    public void fulfill_order_on_Shopbase_dashboard_as(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String product = SessionData.getDataTbVal(dataTable, row, "Product:Variant>Quantity");
            String tkm = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String orderName = "";
            if (xOrderName.contains("@")) {
                orderName = myOrdersDef.getOrderName(xOrderName);
            }
            orderSteps.enterTextThenEnter(orderName);
            orderSteps.selectTheNewestOrder();
            orderSteps.clickOnMarkAsFullfilledButton();
            orderSteps.fulfilledOrder(product, tkm, "", "");
        }
    }

    @Then("^cancel order on Shopbase dashboard$")
    public void cancel_order_on_Shopbase_dashboard_as(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String orderName = "";
            if (xOrderName.contains("@")) {
                orderName = myOrdersDef.getOrderName(xOrderName);
            }
            orderSteps.enterTextThenEnter(orderName);
            orderSteps.selectTheNewestOrder();
            orderSteps.openCancelOrderPopup();
            orderSteps.confirmCancelOrder();
        }
    }

    @Then("^select action (.*) order on Shopbase dashboard$")
    public void select_action_order(String action) {
        orderSteps.clickActionOrder(action);
    }

    @And("^Import tracking number with value of Overwrite is \"([^\"]*)\" and Notification is \"([^\"]*)\"$")
    public void importTrackingNumberWithData(String overwrite, String notification, List<List<String>> dataTable) throws IOException {
        StringBuilder data = new StringBuilder();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            data.append("\n").append(SessionData.getDataTbVal(dataTable, row, "Data"));
        }
        orderSteps.importTrackingNumber(overwrite, notification, data.toString(), orderNameList);
    }

    @And("^Click Upload File$")
    public void clickUploadFile() {
        orderSteps.clickUploadFile();
    }


    @Then("^Verify preview popup mass fulfillment with data$")
    public void verifyPreviewPopupMassFulfillmentWithData(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String order = SessionData.getDataTbVal(dataTable, row, "Order");
            String fulfillment = SessionData.getDataTbVal(dataTable, row, "Fulfillment");
            String overwrite = SessionData.getDataTbVal(dataTable, row, "Overwrite");
            String notification = SessionData.getDataTbVal(dataTable, row, "Notification");
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String lineitemSku = SessionData.getDataTbVal(dataTable, row, "Lineitem Sku");
            String lineitemQuantity = SessionData.getDataTbVal(dataTable, row, "Lineitem Quantity");
            String TrackingNumber = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String ShippingCarrier = SessionData.getDataTbVal(dataTable, row, "Shipping carrier");
            String domain = SessionData.getDataTbVal(dataTable, row, "Domain");

            orderSteps.verifyOrder(order);
            orderSteps.verifyFulfillment(fulfillment);
            orderSteps.verifyOverwrite(overwrite);
            orderSteps.verifyNotification(notification);

            if (name.contains("@OrderName@")) {
                name = orderNumber;
            } else if (name.contains("@OrderName1@")) {
                name = orderNameList.get(0);
            } else if (name.contains("@OrderName2@")) {
                name = orderNameList.get(1);
            }
            orderSteps.verifyContentPopupPreview("Name", name);
            orderSteps.verifyContentPopupPreview("Lineitem Sku", lineitemSku);
            orderSteps.verifyContentPopupPreview("Lineitem Quantity", lineitemQuantity);
            orderSteps.verifyContentPopupPreview("Tracking number", TrackingNumber);
            orderSteps.verifyContentPopupPreview("Shipping carrier", ShippingCarrier);

            if (!domain.isEmpty()) {
                domain = LoadObject.getProperty(domain + "Shop");
            }
            orderSteps.verifyContentPopupPreview("Domain", domain);
        }
    }

    @And("^search order by (order name|customer email) criteria$")
    public void searchingOrderByOrderNameCriteria(String criteria) {
        String value = "order name".equals(criteria) ? orderNumber : customerEmail;
        orderSteps.searchKeyOnOrderList(value);
    }

    @And("^get the order information$")
    public void getTheOrderInformation() {
        orderSteps.getOrderInfo();
    }

    @Given("^Verify transaction details of corresponding gateway after checkout successfully$")
    public void verifyTrxDetails(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String userName = SessionData.getDataTbVal(dataTable, row, "User name");
            String password = SessionData.getDataTbVal(dataTable, row, "Password");
            switch (paymentGateway) {
                case "Stripe":
                case "ShopBase Payments":
                    verifyStripeTrxDetails(userName);
                    break;
                case "Paypal":
                    verifyPayPalTrxDetails();
                    break;
            }
        }
    }

    private void verifyStripeTrxDetails(String userName) {
        String transactionsApiUrl = getTransactionApiUrl();
        String chargeTrxId = transactionsAPI.getStripeChargedID(transactionsApiUrl);

        HashMap<String, String> chargeInfo = stripeAPI.getChargeInfoStripe(chargeTrxId, userName);
        HashMap<String, String> orderInfo = getOrderInfo();

        for (String i : chargeInfo.keySet()) {
            for (String j : orderInfo.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    System.out.println(chargeInfo.get(i) + " <> " + orderInfo.get(j));
                    assertThat(chargeInfo.get(i).trim()).isEqualToIgnoringCase(orderInfo.get(j).trim());
                }
            }
        }
    }

    private String getTransactionApiUrl() {
        orderId = infoAPI.getOrderID(checkoutToken);
        return transactionsAPI.getUrl(orderId);
    }

    private HashMap<String, String> getOrderInfo() {
        HashMap<String, String> orderInfo = new HashMap<>();
        //Total amount
        DecimalFormat numberFormat = new DecimalFormat("0.#");
        String _totalAmt = String.valueOf(numberFormat.format(Double.parseDouble((totalAmt.split("(?<=\\D)", 2)[1]).split("\\s")[0]) * 100));
        //Get shop id from transaction api
        String transactionUrl = getTransactionApiUrl();
        String shopID = transactionsAPI.getShopID(transactionUrl);
        //Get order ID
        String _orderID = infoAPI.getOrderID(checkoutToken).toString().trim();

        //Add Order details into hashmap
        orderInfo.put("Amount", _totalAmt);
        orderInfo.put("Email", customerEmail);

        if ("Same as shipping address".equalsIgnoreCase(billingAddress)) {
            orderInfo.put("Billing address", shippingAddress);
            orderInfo.put("Billing name", shippingAddress.split("\\s", 3)[0] + " " + shippingAddress.split("\\s", 3)[1]);
        } else {
            orderInfo.put("Billing address", billingAddress);
            orderInfo.put("Billing name", billingAddress.split("\\s", 3)[0] + " " + billingAddress.split("\\s", 3)[1]);
        }
        orderInfo.put("Shipping address", shippingAddress);

        orderInfo.put("Checkout token", checkoutToken);
        orderInfo.put("Shop ID", shopID);
        orderInfo.put("Order ID", _orderID);
        orderInfo.put("Customer email", customerEmail);
//        orderInfo.put("Checkout ID", );
//        orderInfo.put("Billing name", billingAddress.split("\\s", 3)[0] + " " + billingAddress.split("\\s", 3)[1]);

        return orderInfo;
    }

    private void verifyPayPalTrxDetails() {

    }

    @Given("^Access to order detail by order ID$")
    public void accessToOrderDetailByID() {
        String orderID = infoAPI.getOrderID(checkoutToken).toString().trim();
        System.out.println("Order ID :" + orderID);
        if (accesstoken.isEmpty())
            accesstoken = loginStep.getAccessTokenShopBase(shopDomain);
        orderSteps.accessOrderDetailById(orderID);
    }

    @And("verify the toast message is displayed {string}")
    public void verifyTheToastMessageIsDisplayed(String toastMsg) {
        orderSteps.verifyToastMsgIsDisplayed(toastMsg);
    }

    @And("verify financial status of order is {string}")
    public void verifyFinancialStatusOfOrderIs(String financialStatus) {
        orderSteps.verifyFinancialStatusIs(financialStatus);
    }

    @And("open cancel order popup then cancel fully order")
    public void openCancelOrderPopupThenCancelFullyOrder() {
        orderSteps.openCancelOrderPopup();
        orderSteps.inputShippingAmount();
        orderSteps.clickOnCancelBtn();
    }

    @Given("^verify dispute warning message in order details$")
    public void verifyDisputeMessageInOrderDetail() {
        orderSteps.verifyDisputeWarningMessage(disputeType, totalAmt);
        orderSteps.clickOnSubmitResponseBtn();
    }

    @Given("^verify initial information of chargeback response form$")
    public void verifyInformationOfFields() {
        orderSteps.verifyChargeBackReason(endingCardNo);
        orderSteps.verifyChargebackAmount("$", totalAmt);

        orderSteps.verifyValueOfProductDescription((listProduct.get(0).split("\\|"))[0]);
        orderSteps.verifyValueOfCustomerName(customerName);
        orderSteps.verifyValueOfCustomerEmail(customerEmail);
        orderSteps.verifyValueOfShippingAddress(shippingAddressHashMap);
        orderSteps.verifyValueOfBillingAddress(billingAddressHashMap);
    }

    @Given("^submit response for dispute order$")
    public void submitDisputeResponse(List<List<String>> dataTable) {
        orderSteps.clickOnSubmitResponseBtn();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String disputeType = SessionData.getDataTbVal(dataTable, row, "Dispute type");
            String productDescription = SessionData.getDataTbVal(dataTable, row, "Product description");
            String customerName = SessionData.getDataTbVal(dataTable, row, "Customer name");
            String customerEmail = SessionData.getDataTbVal(dataTable, row, "Customer email");
            boolean isCustomerSignatureUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Customer signature"));
            String billingAddress = SessionData.getDataTbVal(dataTable, row, "Billing address");
            boolean isReceiptUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Receipt"));
            boolean isCustomerCommunicationUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Customer communication"));
            String shippingAddress = SessionData.getDataTbVal(dataTable, row, "Shipping address");
            String dateOfShipment = SessionData.getDataTbVal(dataTable, row, "Date of shipment");
            String packageCarrier = SessionData.getDataTbVal(dataTable, row, "Package carrier");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            boolean isProofOfShippingUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Proof of shipping"));
            String proofOfAccess = SessionData.getDataTbVal(dataTable, row, "Proof of access or downloading product");
            disputeEvidence = SessionData.getDataTbVal(dataTable, row, "Additional evidence or statements text");
            boolean isAdditionalEvidenceOrStatementsFileUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Additional evidence or statements file"));

            disputeResponseInfoExpected.put("Dispute type", disputeType);
            disputeResponseInfoExpected.put("Product description", productDescription);
            disputeResponseInfoExpected.put("Customer name", customerName);
            disputeResponseInfoExpected.put("Customer email", customerEmail);
            disputeResponseInfoExpected.put("Customer signature", Boolean.toString(isCustomerSignatureUpload));
            disputeResponseInfoExpected.put("Billing address", billingAddress);
            disputeResponseInfoExpected.put("Receipt", Boolean.toString(isReceiptUpload));
            disputeResponseInfoExpected.put("Customer communication", Boolean.toString(isCustomerCommunicationUpload));
            disputeResponseInfoExpected.put("Shipping address", shippingAddress);
            disputeResponseInfoExpected.put("Package carrier", packageCarrier);
            disputeResponseInfoExpected.put("Tracking number", trackingNumber);
            disputeResponseInfoExpected.put("Proof of shipping", Boolean.toString(isProofOfShippingUpload));
            disputeResponseInfoExpected.put("Proof of access or downloading product", proofOfAccess);
            disputeResponseInfoExpected.put("Additional evidence or statements text", disputeEvidence);
            disputeResponseInfoExpected.put("Additional evidence or statements file", Boolean.toString(isAdditionalEvidenceOrStatementsFileUpload));

            //Submit form with new info
            orderSteps.inputValueForProductDescription(productDescription);
            orderSteps.inputValueForCustomerName(customerName);
            orderSteps.inputValueForCustomerEmail(customerEmail);
            if (isCustomerSignatureUpload) {
                orderSteps.uploadFileForCustomerSignature("customer_signature.jpg");
            }
            orderSteps.inputValueForBillingAddress(billingAddress);
            if (isReceiptUpload) {
                orderSteps.uploadFileForReceipt("receipt.jpg");
            }
            if (isCustomerCommunicationUpload) {
                orderSteps.uploadFileForCustomerCommunication("customer_communication.jpg");
            }
            orderSteps.inputValueForShippingAddress(shippingAddress);
            if (!dateOfShipment.isEmpty()) {
                orderSteps.selectDateOfShipment();
                //get date of shipment after selecting date time from calendar
                disputeResponseInfoExpected.put("Date of shipment", orderSteps.getDateOfShipmentAfterClickingOnNow());
            }
            orderSteps.inputValueForPackageCarrier(packageCarrier);
            orderSteps.inputValueForTrackingNumber(trackingNumber);
            if (isProofOfShippingUpload) {
                orderSteps.uploadFileForProofOfShipping("proof_of_shipping.jpg");
            }
            orderSteps.inputValueForProofOfAccessOrDownloadingProduct(proofOfAccess);
            orderSteps.inputValueForAdditionalEvidenceOrStatements(disputeEvidence);
            if (isAdditionalEvidenceOrStatementsFileUpload) {
                orderSteps.uploadFileForAdditionalEvidenceOrStatements("additional_evidence_or_statements.jpg");
            }
            orderSteps.clickOnSubmitEvidenceNowBtn();
        }
    }

    @Given("^verify message and response after dispute has been decided$")
    public void verifyMessageAfterDisputeDecided() {
        switch (disputeEvidence) {
            case "winning_evidence":
                orderSteps.verifyMessageAfterDisputeIsWon(disputeType, totalAmt);
                break;
            case "losing_evidence":
                orderSteps.verifyMessageWhenDisputeIsLost(disputeType, totalAmt);
                break;
            default:
                orderSteps.verifyMessageWhenDisputeInProcessing(disputeType);
                verifyDisputeResponseAfterSubmitting();
                orderSteps.clickOnBreadcrumbInChargeBackForm();
                break;
        }
    }

    @Given("^verify dispute response after submitting$")
    public void verifyDisputeResponseAfterSubmitting() {
        HashMap<String, String> disputeResponseInfoActual = new HashMap<>();
        String secretKey = LoadObject.getProperty("secretKey");
        String connectedId = LoadObject.getProperty("connectedId");
        String transactionsApiUrl = getTransactionApiUrl();
        String chargeTrxId = transactionsAPI.getStripeChargedID(transactionsApiUrl);
        String disputeTrxId = stripeAPI.getDisputeId(chargeTrxId, secretKey, connectedId);
        HashMap<String, String> disputeResponseInfoFromStripe = stripeAPI.getDisputeResponse(disputeTrxId, secretKey, connectedId);

        orderSteps.clickOnViewResponseBtn();

        disputeResponseInfoActual.put("Dispute type", disputeType);
        disputeResponseInfoActual.put("Product description", orderSteps.getInputtedValueOfProductDescription().trim());
        disputeResponseInfoActual.put("Customer name", orderSteps.getInputtedValueOfCustomerName().trim());
        disputeResponseInfoActual.put("Customer email", orderSteps.getInputtedValueOfCustomerEmail().trim());
        disputeResponseInfoActual.put("Customer signature", orderSteps.getInputtedValueOfCustomerSignature().trim());
        disputeResponseInfoActual.put("Billing address", orderSteps.getInputtedValueOfBillingAddress().trim());
        disputeResponseInfoActual.put("Receipt", orderSteps.getInputtedValueOfReceipt().trim());
        disputeResponseInfoActual.put("Customer communication", orderSteps.getInputtedValueOfCustomerCommunication().trim());
        disputeResponseInfoActual.put("Shipping address", orderSteps.getInputtedValueOfShippingAddress().trim());
        disputeResponseInfoActual.put("Package carrier", orderSteps.getInputtedValueOfPackageCarrier().trim());
        disputeResponseInfoActual.put("Tracking number", orderSteps.getInputtedValueOfTrackingNumber().trim());
        disputeResponseInfoActual.put("Proof of shipping", orderSteps.getInputtedValueOfProofOfShipping().trim());
        disputeResponseInfoActual.put("Proof of access or downloading product", orderSteps.getInputtedValueOfProofOfAccessOrDownloadingProduct().trim());
//        disputeResponseInfoActual.put("Additional evidence or statements text", orderSteps.getInputtedValueOfAdditionalEvidenceOrStatements().trim());
        disputeResponseInfoActual.put("Additional evidence or statements file", orderSteps.getInputtedValueOfAdditionalEvidenceOrStatementsUpload().trim());

        for (String i : disputeResponseInfoExpected.keySet()) {
            for (String j : disputeResponseInfoActual.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    System.out.println(disputeResponseInfoExpected.get(i) + " <> " + disputeResponseInfoActual.get(j));
                    if (disputeResponseInfoExpected.get(i).equalsIgnoreCase("true")) {
                        assertThat(disputeResponseInfoActual.get(j)).isNotEmpty();
                    } else {
                        assertThat(disputeResponseInfoExpected.get(i).trim()).isEqualToIgnoringCase(disputeResponseInfoActual.get(j).trim());
                    }
                }
            }
        }

        for (String i : disputeResponseInfoExpected.keySet()) {
            for (String j : disputeResponseInfoFromStripe.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    System.out.println(disputeResponseInfoExpected.get(i) + " <> " + disputeResponseInfoFromStripe.get(j));
                    if (disputeResponseInfoExpected.get(i).equalsIgnoreCase("true")) {
                        assertThat(disputeResponseInfoFromStripe.get(j)).isNotEmpty();
                    } else {
                        assertThat(disputeResponseInfoExpected.get(i).trim()).isEqualToIgnoringCase(disputeResponseInfoFromStripe.get(j).trim());
                    }
                }
            }
        }


    }

    @And("^verify shipping address is displayed correctly in order detail$")
    public void verifyCpfCnpjIsDisplayedCorrectlyInOrderDetail() {
        orderSteps.verifyAddressWithCountryBrazil("SHIPPING ADDRESS", shippingAddress);
    }

    @And("^select export template (Customize export fields|Orders)$")
    public void selectExportTemplateCustomizeExportFields(String template, List<String> dataTable) {
        orderSteps.selectExportTemplate(template);
        for (String field : dataTable) {
            orderSteps.addFields(field);
        }
    }

    @And("^open export popup then select export type \"([^\"]*)\"$")
    public void selectExportType(String exportType) {
        orderSteps.clickOnExportButton();
        orderSteps.selectOptionToExportOrder(exportType);
    }

    @Then("select all orders")
    public void selectAllOrders() {
        orderSteps.selectAllOrders();
    }

    @Given("select order in Orders list")
    public void select_order_in_Orders_list() {
        orderSteps.selectOrderInOrdersList();
    }

    @Then("^click on Export to file button$")
    public void clickOnExportToFileButton() {
        orderSteps.exportToFile(1);
    }

    @And("^verify that edit shipping address on the order successfully$")
    public void verifyThatEditShippingAddressOnTheSuccessfully(List<List<String>> dataTable) {
        orderSteps.clickOnEditShippingAddressBtn();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String cpfOrCnpj = SessionData.getDataTbVal(dataTable, row, "CPF/CNPJ Number");
            orderSteps.enterCpfOrCnpj(cpfOrCnpj);
        }
        orderSteps.clickSaveBtnOnPU();
    }

    @And("back to the page")
    public void backToThePage() {
        orderSteps.backToThePage();
    }

    @Given("search order PBase {string} then select order")
    public void search_order_PBase_then_select_order(String order) {
        orderSteps.searchOrderPbase(orderNumber);
        orderSteps.selectTheNewestOrder();
    }

    @Given("verify fulfillment status of order")
    public void verify_fulfillment_status_of_order(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            orderSteps.verifyFulfillmentGroup(productName, variant, sku, quantity);
        }
    }

    float _discount = 0;
    float _shippingfeeForSeller = 0;
    float total = 0;
    float _subtotal = 0;

    @Given("verify payment status of order")
    public void verify_payment_status_of_order(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String itemSubtotal = SessionData.getDataTbVal(dataTable, row, "item subtotal");
            boolean shippingFreeship = "yes".equals(SessionData.getDataTbVal(dataTable, row, "Shipping freeship")) ? true : false;
            boolean flagDiscount = "yes".equals(SessionData.getDataTbVal(dataTable, row, "Discount code>freeship").split(">")[0]) ? true : false;
            boolean flagDiscountFreeship = "yes".equals(SessionData.getDataTbVal(dataTable, row, "Discount code>freeship").split(">")[1]) ? true : false;
            boolean flagDiscountPPC = "yes".equals(SessionData.getDataTbVal(dataTable, row, "Discount PPC")) ? true : false;
            String productPPC = SessionData.getDataTbVal(dataTable, row, "Product post purchase");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            _discount = orderSteps.roundTwoDecimalPlaces(orderSteps.getDiscountOrder(flagDiscount, flagDiscountFreeship, flagDiscountPPC, productPPC));
            if (_discount > 0) {
                orderSteps.verifyPaymentGroup("Discount", _discount);
            }
            float _shippingfeeForBuyer = 0;
            if (!shippingFreeship) {
                _shippingfeeForBuyer = orderSteps.getShippingForBuyer("Shipping");
            } else {
                orderSteps.verifyContentShippingInPaymentStatus(content);
                _shippingfeeForSeller = orderSteps.getShippingForSeller("Shipping");
            }

            orderSteps.verifyItemSubTotalInPaymentStatus(itemSubtotal);
            _subtotal = orderSteps.getSubTotalInFulfillmentGroup();
            orderSteps.verifyPaymentGroup("Subtotal", orderSteps.roundTwoDecimalPlaces(_subtotal));
            float tax = orderSteps.getPriceTax("Tax");
            total = _subtotal + _shippingfeeForBuyer - _discount + tax;
            orderSteps.verifyPaymentGroup("Total", orderSteps.roundTwoDecimalPlaces(total));
            orderSteps.verifyPaidByCustomerInPaymentGroup("Paid by customer", 0);
        }
    }


    @Given("verify your profit of order Pbase")
    public void verify_your_profit_of_order_Pbase() {
        orderSteps.showCalculationYourProfit();
        float _refund = 0;
        float _revenue = _subtotal - _discount - _refund;
        orderSteps.verifyProfitGroup("Subtotal", _subtotal);
        orderSteps.verifyProfitGroup("Discount", _discount);
        orderSteps.verifyProfitGroup("Refunded", _refund);
        orderSteps.verifyProfitGroup("Revenue", orderSteps.roundTwoDecimalPlaces(_revenue));
        if (_shippingfeeForSeller > 0) {
            orderSteps.verifyProfitGroup("Shipping fee", _shippingfeeForSeller);
        }
        orderSteps.verifyBaseCostYourProfit("Basecost");
        float baseCost = orderSteps.getBaseCostYourProfit("Basecost");
        int paymentFeePercent = Integer.parseInt(LoadObject.getProperty("paymentFeePercent"));
        int processingFeePercent = Integer.parseInt(LoadObject.getProperty("processingFeePercent"));
        float _paymentFee = orderSteps.roundTwoDecimalPlaces((paymentFeePercent * total / 100));
        float _processingFee = orderSteps.roundTwoDecimalPlaces(processingFeePercent * (_revenue - baseCost - _shippingfeeForSeller - _paymentFee) / 100);
        float _handlingFee = orderSteps.roundTwoDecimalPlaces(_paymentFee + _processingFee);
        orderSteps.openHandlingFeeInYourProfit();
        orderSteps.verifyProfitGroup("Payment fee", _paymentFee);
        orderSteps.verifyProfitGroup("Processing fee", _processingFee);
        orderSteps.verifyProfitGroup("Handling fee", _handlingFee);
        float profit = _revenue - baseCost - _shippingfeeForSeller - _handlingFee;
        orderSteps.verifyProfitInYourProfit(orderSteps.roundTwoDecimalPlaces(profit));
    }

    @Then("fulfill order in Order detail")
    public void fulfill_order_in_Order_detail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String lineitem = SessionData.getDataTbVal(dataTable, row, "Lineitem");
            String fulfillmentService = SessionData.getDataTbVal(dataTable, row, "Fulfillment Service");
            String plb = SessionData.getDataTbVal(dataTable, row, "Plusbase");
            int index = orderSteps.getIndexLineItemGroup(lineitem);
            if (!plb.isEmpty()) {
                orderPlusBaseStep.clickShopBaseFulfillment(index);
                orderPlusBaseStep.removeFilterFulfill();
            } else {
                orderSteps.fulfillWith(fulfillmentService, index);
            }
            fulfillmentSteps.fulfillOrder();
            if (!plb.isEmpty()) {
                orderPlusBaseStep.clickButton("Confirm");
            } else {
                fulfillmentSteps.payOrder();
            }
            fulfillmentSteps.wailToFulfillDone();
            orderSteps.waitForShowDataOrder();
        }
    }

    @Then("verify order detail ShopBase after fulfill via PlusHub")
    public void verify_order_detail_ShopBase_after_fulfill_via_ShopBase_Fulfillment(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            int countGroup = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Count group"));
            String statusGroup = SessionData.getDataTbVal(dataTable, row, "Status group");
            String lineitem = SessionData.getDataTbVal(dataTable, row, "Lineitem");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity lineitem");
            boolean isCancelFul = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Cancel fulfillment"));
            boolean isAddTKN = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Add tracking"));
            boolean isFulfillWith = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Fulfill with"));
            boolean isMarkAsFulfilled = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Mark as fulfilled"));
            boolean isTrackShipment = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Track shipment"));
            boolean isEditTracking = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Edit tracking"));
            String tkn = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String plb = SessionData.getDataTbVal(dataTable, row, "Plusbase");
            orderSteps.refreshPage();
            orderSteps.waitForShowDataOrder();
            orderSteps.verifyCountGroup(countGroup);
            int index = orderSteps.getIndexLineItemGroup(lineitem);
            orderSteps.verifyStatusGroup(statusGroup, index);
            if (lineitem.contains(">")) {
                String lineitems[] = lineitem.split(">");
                orderSteps.verifyQuantityInFulfillmentStatus(lineitems[0], quantity);
            }
            orderSteps.isShowCancelFulfillment(isCancelFul, index);
            orderSteps.isShowAddTKN(isAddTKN, index);
            orderSteps.isFulfillWith(isFulfillWith, index);
            orderSteps.isMarkAsFulfilled(isMarkAsFulfilled, index);
            orderSteps.isTrackShipment(isTrackShipment, index);
            orderSteps.isEditTracking(isEditTracking, index);
            if (plb.isEmpty()) {
                orderSteps.clickbutton("Edit tracking");
                String act = orderSteps.getTrackingNumberLineItem();
                orderSteps.clickbutton("Cancel");
                if (!tkn.isEmpty()) {
                    if (tkn.contains("@")) {
                        tkn = trackingNumber;
                    }
                    assertThat(act).isEqualTo(tkn);
                }
            }
        }
    }

    @Then("verify order detail after approved claim resend")
    public void verify_order_detail_after_approved_claim_resend(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            int countGroup = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Count group"));
            String statusGroup = SessionData.getDataTbVal(dataTable, row, "Status group");
            String lineitem = SessionData.getDataTbVal(dataTable, row, "Lineitem");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            int index = orderSteps.getIndexGroupResend();
            orderSteps.verifyStatusGroup(statusGroup, index);
            orderSteps.verifyItemClaim(lineitem, index);
            orderSteps.verifyClaimId(ClaimListDef.claimNew, index);
        }
    }

    int numberOrderCurrent = 0;

    @And("get total order default with {string}")
    public void getTotalOrderDefaultWith(String type) {
        orderSteps.clickOut("Total orders");
        numberOrderCurrent = orderSteps.getTotalOrderDefaultCurrentStore("Total orders");
    }

    @Then("verify number order export")
    public void verifyNumberOrderExport() {
        if (numberOrderCurrent > 50) {
            String emailStaff = LoadObject.getProperty("user.name");
            productListSteps.verifyReceivedEmail(emailStaff, "Order export is ready");
            orderSteps.verifyFileName("order_export_yyyyMMdd");
        }
        FILE_PATH_ORDER_DOWNLOADED = orderSteps.openFileDownloaded();
        orderSteps.readingOrderInfoBasedOnHeader(FILE_PATH_ORDER_DOWNLOADED, "Name");
        int count = exportOrderList.size();
        assertThat(numberOrderCurrent).isEqualTo(count);
    }

    @And("implement export {string} orders with {string}")
    public void implementExportOrdersWith(String typeExport, String typeStore) {
        orderSteps.clickOnExportButton();
        orderSteps.selectOptionToExportOrder(typeExport);
        orderSteps.exportToFile(1);
    }

    @And("^verify order detail after (fulfillemnt|fulfillemnt of the first shop|fulfillemnt of the second shop)")
    public void verifyOrderDetailAfterFulfillemnt(String option, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String status = SessionData.getDataTbVal(dataTable, row, "Fulfillment status");
            String groupFulfilled = SessionData.getDataTbVal(dataTable, row, "Group fulfilled");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String unitPrice = SessionData.getDataTbVal(dataTable, row, "Unit price");
            String total = SessionData.getDataTbVal(dataTable, row, "Total");
            String buttonCancel = SessionData.getDataTbVal(dataTable, row, "Button cancel");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String firstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String lastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String timelineFulfilled = SessionData.getDataTbVal(dataTable, row, "Timeline fulfilled");
            String orderName = "fulfillemnt of the first shop".equals(option) ? orderNameList.get(0) : orderNumber;

            if (!"fulfillemnt of the second shop".equals(option)) {
                orderSteps.verifyFulfillmentStatusOnOrderList(orderName, status);
                orderSteps.clickNameOrderOnList(orderName);
            }

            orderSteps.verifyFulfillmentStatus(status);
            orderSteps.verifyTimeline(email, firstName, lastName, timelineFulfilled, status);
            if (quantity.contains("@quantity")) {
                quantity = quantity.replace("@quantity", itemWarehouse);
                total = Integer.toString(Integer.parseInt(quantity) * Integer.parseInt(unitPrice.replace("$", "")));
            }
            orderSteps.verifyGroupFulfill(groupFulfilled, product, SKU, quantity, unitPrice, buttonCancel, status);
            orderSteps.verifyTotal(total, product);
        }
    }

    @And("^cancel order fulfill with shopbase (.*)$")
    public void cancelOrderFulfillWithShopbaseFulfillment(String option, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String cancelButton = SessionData.getDataTbVal(dataTable, row, "Button cancel");
            String textConfirm = SessionData.getDataTbVal(dataTable, row, "Text confirm");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String buttonConfirm = SessionData.getDataTbVal(dataTable, row, "Button confirm");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String timeline = SessionData.getDataTbVal(dataTable, row, "Timeline");
            orderSteps.clickbutton(cancelButton);
            orderSteps.verifyPopupCancel(cancelButton, textConfirm, product, description, orderNumber);
            orderSteps.clickbutton(buttonConfirm);
            if ("fulfillment".equals(option)) {
                orderSteps.refreshPage();
                orderSteps.verifyOrderAfterCancel(status, product, cancelButton, timeline);
            }
        }
    }

    @And("Get account name")
    public void getAccountName() {
        accountName = orderSteps.getAccountName();
    }

    @When("open order detail dashboard by ID")
    public void openOrderDetailDashboardByID() {
        String _orderID = infoAPI.getOrderID(checkoutToken).toString().trim();
        System.out.println("accesstoken 2 = " + accesstoken);
        System.out.println("Order ID :" + _orderID);
        orderSteps.accessOrderDetailById(_orderID);

    }

    //    -----manual payment method-----
    @When("^Change order \"([^\"]*)\" status to Paid$")
    public void ChangeOrderStatusToPaid(String manualPM) {
        orderSteps.markAsPaidOrder();
        orderState = "Paid";
        paymentGateway = manualPM + "-Paid";
    }

    @And("^verify state of block Mark as paid is (hidden|shown)$")
    public void VerifyExistedBtnMarkAsPaid(String expectedState) {
        orderSteps.checkExistedMarkAsPageBtn(expectedState);
    }

    @And("^verify status btn Refund item is (hidden|shown)$")
    public void VerifyStatusBtnRefund(String expected_state) {
        orderSteps.checkExistedRefundBtn(expected_state);
    }

    @Then("verify order on dashboard with discount {string}")
    public void verifyOrderOnDashboardWithDiscount(String discount) {
        int i = 1;
        for (String products : listProduct) {

            String[] _number = products.split("\\|");
            String productName = _number[0];
            String _price = _number[1];
            String _quantity = _number[2];

            thankyouSteps.logInfor("Product name : " + productName + " has price: " + _price + " Quantity :" + _quantity + " Total :" + totalAmt);
            if (!discountAmount.isEmpty() && !discount.equalsIgnoreCase("Free shipping")) {
                orderSteps.verifyProductInformation(i, productName, _quantity, _price, discountAmount);
            } else {
                orderSteps.verifyProductInformation(i, productName, _quantity, _price);
            }
            i++;
        }
        if (discount.equalsIgnoreCase("Free shipping")) {
            orderSteps.verifyFreeShippingOnDB(shippingFee, discountAmount);
        } else {
            if (!discountAmount.isEmpty()) {
                orderSteps.verifyDiscountOnDB(discountCode);
            }
        }
    }

    @And("^refund order with amount \"([^\"]*)\"$")
    public void refundOrderWithAmount(String amount) {
        orderSteps.clickToRefundButton();
        orderSteps.inputRefundAmount(amount);
        orderSteps.clickToRefundButton();
    }

    @Given("^remove all order$")
    public void removeAllOrder() {
        orderSteps.removeAllOrder();
    }

    @And("verify scan order from previous run")
    public void verifyOrderScannedInOrderList() {
        orderSteps.verifyOrderScannedInOrderList();
    }

    @Then("Search order by line item name and verify")
    public void searchOrderByLineItemNameAndVerify(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productNameSB = SessionData.getDataTbVal(dataTable, row, "ProductName");
            String expect = SessionData.getDataTbVal(dataTable, row, "Expect");
            if (productNameSB.contains("@")) {
                productNameSB = OrderVariable.productName;
            }
            if (expect.contains("@")) {
                expect = orderNumber;
            }
            orderSteps.enterTextThenEnter(productNameSB);
            orderSteps.verifyInforOrder(expect);
        }
    }

    @And("Expand order")
    public void expandOrder() {
        orderSteps.xpandOrder();
    }

    @And("Verify info order line items")
    public void verifyInfoOrderLineItems(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productNameSB = SessionData.getDataTbVal(dataTable, row, "ProductNameSB");
            String variantSB = SessionData.getDataTbVal(dataTable, row, "VariantSB");
            String skusb = SessionData.getDataTbVal(dataTable, row, "SKUSB");
            String quantitySB = SessionData.getDataTbVal(dataTable, row, "QuantitySB");
            String productNameMapping = SessionData.getDataTbVal(dataTable, row, "ProductNameMapping");
            String variantMapping = SessionData.getDataTbVal(dataTable, row, "VariantMapping");
            String skuMapping = SessionData.getDataTbVal(dataTable, row, "SKUMapping");
            String quantityMapping = SessionData.getDataTbVal(dataTable, row, "QuantityMapping");
            String fulfillmentStatus = SessionData.getDataTbVal(dataTable, row, "FulfillmentStatus");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "TrackingNumber");
            String displayButton = SessionData.getDataTbVal(dataTable, row, "DisplayButton");
            if (!productNameSB.isEmpty()) {
                orderSteps.verifyInfoOder(productNameSB, variantSB, skusb, quantitySB);
            }
            if (!productNameMapping.isEmpty()) {
                orderSteps.verifyInfoOder(productNameMapping, variantMapping, skuMapping, quantityMapping);
            }
            orderSteps.verifyFulfillmentStatusOrder(fulfillmentStatus);
            orderSteps.verifyTrackingNumberOrder(trackingNumber);
            orderSteps.verifyBT(displayButton);
        }
    }

    @And("Change mapping product")
    public void changeMappingProduct(List<List<String>> dataTable) {
        orderSteps.clickAction("Change mapping");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            nameProductOdoo = SessionData.getDataTbVal(dataTable, row, "ProductNameMapping");
            variantValueOdoo = SessionData.getDataTbVal(dataTable, row, "Variant>quantity");
            nameProductSbase = SessionData.getDataTbVal(dataTable, row, "Product Name sbase");
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "VariantSB>quantitySB");
            fulfillWithSbaseOrderSteps.searchByProductName(nameProductOdoo);
            mappingProductSteps.clearData();
            if (!variantValueSbase.isEmpty()) {
                mappingProductSteps.mappingOrder(variantValueSbase, variantValueOdoo);
            } else {
                mappingProductSteps.mappingVariantOdoo(variantValueOdoo);
            }
            mappingProductSteps.clickSave();
        }
    }

    @And("Replace product for lineitem")
    public void replaceProductForLineitem(List<List<String>> dataTable) {
        orderSteps.clickAction("Replace Inventory Item");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "ProductName");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant>Quantity");
            String status = SessionData.getDataTbVal(dataTable, row, "Apply this change to all");
            orderSteps.searchProductName(productName);
            orderSteps.replayProduct(variant, status);
        }
    }


    @And("choice option export with")
    public void choiceExportTemplate(List<List<String>> data) {
        orderSteps.clickOnExportButton();
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String exports = SessionData.getDataTbVal(data, row, "Exports");
            String exportTemplate = SessionData.getDataTbVal(data, row, "Export template");
            String multiStore = SessionData.getDataTbVal(data, row, "Multi store");
            if (!multiStore.isEmpty()) {
                orderSteps.clickChoiceStore(multiStore);
            }
            orderSteps.choiceExportTemplate(exportTemplate);
            orderSteps.selectOptionToExportOrder(exports);
        }
        orderSteps.exportToFile(0);
    }

    @And("verify that the header of file downloaded is matched with order information from dashboard")
    public void verifyThatTheHeaderOfFileDownloadedIsMatchedWithOrderInformationFromDashboard(List<String> data) {
        FILE_PATH_ORDER_DOWNLOADED = orderSteps.openFileDownloaded();
        List<String> act = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_ORDER_DOWNLOADED));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            for (String header : csvParser.getHeaderMap().keySet()) {
                act.add(header);
            }
            orderSteps.verifyHeader(act, data);
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }

    @And("move tab {string}")
    public void moveTab(String tabName) {
        orderSteps.moveTab(tabName);
    }

    @And("select first order dispute")
    public void selectFirstOrderDispute() {
        orderSteps.selectFirstOrderDispute();
    }

    @And("verify that the content of file downloaded of order dispute")
    public void verifyThatTheContentOfFileDownloadedOfOrderDispute(List<String> dataTable) {
        FILE_PATH_ORDER_DOWNLOADED = orderSteps.openFileDownloaded();
        for (String column : dataTable) {
            orderSteps.readingOrderInfoBasedOnHeader(FILE_PATH_ORDER_DOWNLOADED, column);
        }
        orderSteps.verifyContentFile(expectedOrderInfo);
    }

    @And("search and verify order list with fulfillment status = {string}")
    public void searchAndVerifyOrderListWithFulfillmentStatus(String status) {
        orderSteps.searchOrderNameOnOrderList(orderNumber);
        orderSteps.verifyFulfillmentStatusOnOrderList(orderNumber, status);
    }

    @Then("^cancel first order in Order dashboard$") //for customer analytics
    public void cancelFirstOrderInOrderDashboard() {
        orderSteps.cancelFirstOrderInOrderDashboard();
    }

    @And("^click on (order name) in All orders list$")
    public void clickOnOrderNameInAllOrdersList(String orderName) {
        orderName = LoadObject.getProperty("order");
        orderSteps.enterTextThenEnter(orderName);
        orderSteps.clickNameOrderOnList(orderName);
        orderSteps.verifyDisplayPopupLiveChat();


    }

    @Then("click on the number of orders per customer should be redirected to list of orders filtered by the customer {string}")
    public void clickOnTheNumberOfOrdersPerCustomerShouldBeRedirectedToListOfOrdersFilteredByTheCustomer(String customerName) {
        orderSteps.clickOnTheOrderQuantityFromOrderDetail();
        orderSteps.verifyCustomerOrder(customerName);
    }

    @And("verify print file after genarated for line item {string}")
    public void verifyPrintFileAfterGenaratedForLineItem(String lineItem, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _isPrintFileDone = SessionData.getDataTbVal(dataTable, row, "Is generated");
            String _isAction = SessionData.getDataTbVal(dataTable, row, "Action");
            String _isGenerateAll = SessionData.getDataTbVal(dataTable, row, "Generate all");
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Boolean _isSetting = printbaseAPI.getStatusPrintFile(accessToken);
            clickGeneratePrintFileOnLineItem(_isSetting, _isGenerateAll);
            String namePrintfileExpected = "";
            String namePrintfileActual;
            Boolean isPrintfileDone = true;
            String prod_sku[] = lineItem.split(">");
            String sku = "";
            if (_isPrintFileDone.equalsIgnoreCase("false"))
                isPrintfileDone = false;
            if (prod_sku.length == 2)
                sku = prod_sku[1];
            orderSteps.verifyPrintFileGeneratedDone(prod_sku[0], sku, isPrintfileDone);
            if (isPrintfileDone) {
                orderSteps.clickIconDotInOrder(prod_sku[0], sku);
                namePrintfileActual = orderSteps.getFormatPrintfileName(prod_sku[0], sku);
                if (!namePrintfileExpected.isEmpty())
                    assertThat(namePrintfileActual).contains(namePrintfileExpected);
                orderSteps.clickActionOfLineItemPrinfile(prod_sku[0], sku, _isAction);
                orderSteps.verifyLinkPreviewOfPrintfile(namePrintfileExpected);
            }

        }
    }

    @And("Import order sbase by csv")
    public void importOrderSbaseByCsv(List<List<String>> dataTable) throws IOException {
        orderSteps.clickBT("Import");
        StringBuilder data = new StringBuilder();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            data.append("\n").append(SessionData.getDataTbVal(dataTable, row, "Data"));
            String error = SessionData.getDataTbVal(dataTable, row, "error");
            orderName = manageOrdersSteps.randomOrderName();
            date = DateTimeUtil.getTodayFormatDateConvert();
            orderSteps.importOrderByCSV(data.toString(), orderName, date);
            orderSteps.clickBT("Upload File");
            if (!error.isEmpty()) {
                orderSteps.verifyError(error);
            } else {
                orderSteps.clickBT("Upload File");
                orderSteps.clickBT("OK");
            }
        }
    }

    @Then("Search and verify order import had sync app printHub success")
    public void searchAndVerifyOrderImportHadSyncAppPrintHubSuccess(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "tab");
            purchaseOrderDetailSteps.accToTab(tab);
            orderSteps.searchOrderAndVerrify(orderNameList);
        }
    }

    @And("Search and Verify info order after import in order list")
    public void verifyInfoOrderAfterImportInOrderList(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderDate = SessionData.getDataTbVal(dataTable, row, "ORDER DATE");
            String productName = SessionData.getDataTbVal(dataTable, row, "PRODUCT NAME");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String error = SessionData.getDataTbVal(dataTable, row, "ERROR");
            orderSteps.searchOrderAndVerrifyInfo(orderNameList, orderDate, productName, sku, error);
        }
    }

    @And("Search order import and verify info order import detail")
    public void searchOrderImportAndVerifyInfoOrderImportDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "title");
            String timeline = SessionData.getDataTbVal(dataTable, row, "Timeline");
            orderSteps.searchAndClickOrderNameAndVerrifyTitle(orderNameList, title);
            orderSteps.verifyTimelineWithOrderImport(timeline);

        }
    }

    @And("click add print file on order detail")
    public void clickAddPrintFileOnOrderDetail() {
        orderSteps.clickAddPrintFileOnOder();
    }

    @And("refund line personal in order with")
    public void cancelLinePersonalInOrderWith(List<List<String>> data) {
        orderSteps.moveToPage("Refund item");
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            orderSteps.refundLineOfOrder(product, quantity);
        }
    }

    @And("move to order detail page")
    public void moveToOrderDetailPage() {
        orderSteps.clickNameOrderOnList(orderNumber);
    }

    public static String claim = "";

    @Then("verify not display {string}")
    public void verifyNotDisplay(String text) {
        orderSteps.clickOnMoreActionBtn();
        orderSteps.verifyNotDisplayTextName(text);
    }

    @Then("verify  display {string} and click")
    public void verifyDisplayAndDisplay(String text) {
        orderSteps.clickOnMoreActionBtn();
        switch (text) {
            case "File a claim":
                orderSteps.verifyDisplayTextFileAClaim(text);
                orderSteps.clickText(text);
                break;
            case "View claim(s)":
                orderSteps.verifyDisplayTextViewClaim(text);
                orderSteps.clickText(text);
                break;
        }
    }

    @Then("Redirect on {string}")
    public void redirectOn(String page) {
        orderSteps.verifyRedirectNewClaim(page);
    }

    @Then("Redirect on {string} list Page and verify display order in input search")
    public void redirectOnListPageAndVerifyDisplayOrderInInputSearch(String page) {
        orderSteps.verifyRedirectClaimList(page, ClaimListDef.orderName);
    }

    @Then("Redirect on Claim detail")
    public void redirectOnClaimDetail() {
        claim = orderSteps.getTextClaimName();
        orderSteps.clickClaimName();
        orderSteps.verifyRedirectOnClaimDetailPage(claim);

    }

    @And("Search claim have name {string}")
    public void searchClaimHaveName(String claimName) {
        claim = claimName;
        orderSteps.searchClaim(claimName);
    }

    @And("verify text log time line and click view invoice")
    public void verifyTextLogTimeLineAndClickViewInvoice(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String log = SessionData.getDataTbVal(dataTable, row, "log");
            orderSteps.verifyTextRefundOrder(log);
        }
    }

    @Then("Redirect on claim detail with info")
    public void redirectOnClaimDetailWithInfo() {
    }

    @And("Filter order with")
    public void filterOrderWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String filterLabel = SessionData.getDataTbVal(data, row, "Filter label");
            String item = SessionData.getDataTbVal(data, row, "Item filter");
            orderSteps.filterMore(filterLabel, item);
        }
    }

    @Then("verify shipping fee in order")
    public void verifyShippingFeeInOrder() {
        orderSteps.verifyShippingFeeInOrder();
        orderSteps.verifyShippingMethodInOrder();
    }

    @And("verify tax line in order")
    public void verifyTaxLineInOrder() {
        orderSteps.verifyTaxLineInOrder();
    }

    String trackingNumberFulfillOrder = "";
    String trackingNumberDetailOrder = "";

    @And("switch to {string} tab and search order after pay")
    public void SwitchToTabAndSearchOrderAfterPay(String tabName) {
        ffSteps.moveTab(tabName);
//        orderSteps.switchTab(tabName);
        ffSteps.searchOrderName(orderNumber);
//        orderSteps.searchOrder(orderNumber);
        ffSteps.clickOrderName(orderNumber);
//        orderSteps.clickNameOrderOnList(orderNumber);
    }


    @And("verify order detail after cancel fulfillment with status {string}")
    public void verifyOrderDetailAfterCancelFulfillmentWithStatus(String status) {
        orderSteps.verifyStatusOfOrderAfterCancelFulfillment(status);
    }

    @Then("Verify tracking number order in order detail")
    public void verifyTrackingNumberOrderInOrderDetail() {
        orderSteps.verifyTrackingNumberInOrderDetail();
    }

    @And("verify order status change from {string} to {string} when capture payment")
    public void verifyOrderStatusChangeWhenCapturePayment(String statusBeforeCapture, String statusAfterCapture) {
        orderSteps.verifyOrderStatus(statusBeforeCapture);
        orderSteps.clickOnCapturePaymentButton();
        orderSteps.clickOnAcceptButton();
        orderSteps.refreshPage();
        orderSteps.verifyOrderStatus(statusAfterCapture);
    }

    @And("verify order status change from {string} to {string} when capturing a partial order equal to {string} dollar")
    public void verifyOrderStatusChangeFromToWhenCapturingAPartialOrder(String statusBeforeCapture, String statusAfterCapture, String captureAmount) {
        orderSteps.verifyOrderStatus(statusBeforeCapture);
        orderSteps.clickOnCapturePaymentButton();
        orderSteps.inputCapturePaymentAmount(captureAmount);
        orderSteps.clickOnAcceptButton();
        orderSteps.refreshPage();
        orderSteps.verifyOrderStatus(statusAfterCapture);
    }

    @Then("verify {string} detail after get tracking number")
    public void verifyDetailAfterGetTrackingNumber(String Page) {
        orderSteps.verifyFulfillOrderAfterGetTN(Page);
        ffSteps.verifyStatusOfBtnTrackingNumber(true);
    }

    @And("Add tag and verify to line items in order detail")
    public void addTagAndVerifyToLineItemsInOrderDetail(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String textTag = SessionData.getDataTbVal(data, row, "Text input tag line item");
            orderSteps.addTagToLineItem(textTag);
            orderSteps.verifyTagLineItem();
        }
    }

    @And("Verify tag line item in order detail")
    public void verifyTagLineItemInOrderDetail() {
        orderSteps.verifyTagLineItem();
    }

    @And("verify print file no genarate for line item {string}")
    public void verifyPrintFileNoGenarateForLineItem(String lineItem, List<List<String>> dataTable) {
        orderSteps.verifyOrderStatus("Paid");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _isPrintFileDone = SessionData.getDataTbVal(dataTable, row, "Is generated");
            Boolean isPrintfileDone = true;
            String prod_sku[] = lineItem.split(">");
            String sku = "";
            if (_isPrintFileDone.equalsIgnoreCase("false"))
                isPrintfileDone = false;
            if (prod_sku.length == 2)
                sku = prod_sku[1];
            orderSteps.verifyPrintFileNoGenerate(prod_sku[0], sku, isPrintfileDone);
        }
    }

    public void clickGeneratePrintFileOnLineItem(Boolean setting, String status) {
        String orderStatus = orderSteps.getOrderStatusOnOrderDetail();
        int n = 1;
        while (!orderStatus.equals("Paid") && orderSteps.isGenerateOnLineItemExist() && n < 16) {
            orderSteps.clickGeneratePrintFileOnOrder();
            if (setting && status.equals("Yes")) {
                personalizeProductSteps.clickAndVerifyToggleOnPopupCreatePrintFile(true);
                orderSteps.clickButtonGenerateOnPopUpPFOrder();
            } else if (!setting) {
                personalizeProductSteps.selectRadioOnPopupPFOrder(status);
                orderSteps.clickButtonGenerateOnPopUpPFOrder();
            }
            myCampaignSteps.waitABit(20000);
            n++;
            myCampaignSteps.refreshPage();
            orderStatus = orderSteps.getOrderStatusOnOrderDetail();
        }
    }


    @And("click Edit printfile in order")
    public void clickEditPrintfileInOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String lineItem = SessionData.getDataTbVal(dataTable, row, "Line item");
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Boolean _isSetting = printbaseAPI.getStatusPrintFile(accessToken);
            clickGeneratePrintFileOnLineItem(_isSetting, "Yes");
            String prod_sku[] = lineItem.split(">");
            String sku = "";
            if (prod_sku.length == 2)
                sku = prod_sku[1];
            orderSteps.clickActionOfLineItemPrinfile(prod_sku[0], sku, "Edit");
        }
    }

    @Then("get tracking number of order on {string} page")
    public void getTrackingNumberOfOrderOnPage(String page) {
        orderSteps.getTrackingNumber(page);
    }

    @And("verify data of order detail after get tracking number")
    public void verifyDataOfOrderDetailAfterGetTrackingNumber() {
        orderSteps.verifyOrderDetailAfterGetTrackingNumber();
        orderSteps.clickbutton("Edit tracking");
        trackingNumber = orderSteps.getTrackingNumberLineItem();
        orderSteps.refreshPage();
        orderSteps.waitForShowDataOrder();

    }

    @Then("verify fulfill order detail after get tracking number")
    public void verifyFulfillOrderDetailAfterGetTrackingNumber() {
        orderSteps.getTrackingNumber("Fulfill order");
        Assertions.assertThat(trackingNumberFulfillOrder).isEqualTo(trackingNumberDetailOrder);
    }

    @Given("verify data order detail of product Ali")
    public void verify_data_order_detail_of_product_Ali(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String shipping = SessionData.getDataTbVal(data, row, "Shipping");
            String productCost = SessionData.getDataTbVal(data, row, "Product cost");
            if (!shipping.isEmpty()) {
                shippingFee = shipping;
                orderSteps.verifyShippingFeeInOrder();
            }
            orderSteps.verifyProductCost(productCost);

        }
    }

    @Given("{string} order in Order list")
    public void order_in_Order_list(String action) {
        orderSteps.clickBtnActionInOrdersList();
        if (action.equals("Hold orders")) {
            orderSteps.selectAction(action);
            orderSteps.clickConfirmHoldOrder();
        }
        if (action.equals("Release orders")) {
            orderSteps.selectAction(action);
            orderSteps.waitABit(2000);
        }
    }

    @Given("verify order list after {string} order")
    public void verify_order_list_after_order(String action) {
        switch (action) {
            case "hold":
                orderSteps.verifyOrderShow(true);
                break;
            default:
                orderSteps.verifyOrderShow(false);

        }
    }

    @Given("verify order detail after {string} order")
    public void verify_order_detail_after_order(String action, List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String lineItem = SessionData.getDataTbVal(data, row, "Product");
            int index = orderSteps.getIndexLineItemGroup(lineItem);
            switch (action) {
                case "hold":
                    orderSteps.verifyStatusOrdersAfterHold(index, "On hold");
                    orderSteps.verifyButtonIsShow("Release order");
                    break;
                default:
                    orderSteps.verifyButtonIsShow("Hold order");
            }
        }

    }

    @Given("{string} order in Order detail")
    public void order_in_Order_detail(String action) {
        orderSteps.holdReleaseOrder(action);
    }

    @Given("verify cannot hold order in Orders list")
    public void verify_cannot_hold_order_in_Orders_list() {
        orderSteps.verifyPopupCanNotHold();
    }

    @Given("search order by {string} criteria with keyword {string}")
    public void search_order_by_criteria_with_keyword(String criteria, String keyword) {
        orderSteps.selectCriteria(criteria);
        orderSteps.searchOrderNameOnOrderList(keyword);
    }

    @Then("cancel fulfillment in Order detail")
    public void cancel_fulfillment_in_Order_detail(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String lineItem = SessionData.getDataTbVal(data, row, "Product");
            orderSteps.clickbutton("Cancel fulfillment");
            orderSteps.clickbutton("Confirm");
        }
    }

    @And("enter filter tag and collection")
    public void enterFilterTagAndCollection(List<List<String>> data) {
        int number = 0;
        orderSteps.clickOnExportButton();
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String tag = SessionData.getDataTbVal(data, row, "Tag");
            String collection = SessionData.getDataTbVal(data, row, "Collection");
            String option = SessionData.getDataTbVal(data, row, "Option");
            String multiStore = SessionData.getDataTbVal(data, row, "Multi store");
            if (!multiStore.isEmpty()) {
                orderSteps.clickChoiceStore(multiStore);
            }
            orderSteps.clickAddProductFilter();
            if (tag.isEmpty()) {
                orderSteps.inputFilterCollection(collection);
                orderSteps.selectOptionToExportOrder(option);
                orderSteps.clickbutton("Apply");
            } else if (collection.isEmpty()) {
                orderSteps.inputFilterTag(tag);
                orderSteps.selectOptionToExportOrder(option);
                orderSteps.clickbutton("Apply");
            } else {
                orderSteps.inputFilterCollection(collection);
                orderSteps.inputFilterTag(tag);
                orderSteps.clickbutton("Apply");
                orderSteps.selectOptionToExportOrder(option);
            }
            orderSteps.exportToFile(number);
        }

    }

    @And("Edit order in order detail")
    public void editOrderInOrderDetail(List<List<String>> data) {
        orderSteps.clickEditOrder();
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String optionEdit = SessionData.getDataTbVal(data, row, "Option edit");
            String quantity = SessionData.getDataTbVal(data, row, "Quantity");
            String buttonName = SessionData.getDataTbVal(data, row, "Button name");
            String statusOrder = SessionData.getDataTbVal(data, row, "Status order");
            String productName = SessionData.getDataTbVal(data, row, "Product name");
            String variantPlace = SessionData.getDataTbVal(data, row, "Variant replace");
            String changeProduct = SessionData.getDataTbVal(data, row, "Change product");
            orderSteps.clickOptionEdit(optionEdit);
            if (optionEdit.contains("Adjust quantity")) {
                orderSteps.enterQuantity(quantity);
            } else if (optionEdit.contains("Change variant")) {
                orderSteps.changeProduct(productName, changeProduct);
                ffSteps.choiceVariant(variantPlace);
            }
            orderSteps.updateProduct(buttonName);
            orderSteps.verifyStatusOrderAfterUpdate(statusOrder);
        }
    }

    @And("Sort {string} order list by field {string}")
    public void sortOrderByField(String sortType, String field) {
        orderSteps.sortOrderListByField(sortType, field);
    }

    @And("compare quantity of orders open chargeback inquiry with tab Chargebacks & inquiries")
    public void compareQuantityOfOrdersOpenChargebackInquiryWithTabChargebacksInquiries() {
        String quantityOfOrderFiltered = orderSteps.getQuantityOfOrderList();
        String quantityOfOrderOnTabChargebackInquiry = orderSteps.getQuantityOfOrderOnTab("Chargebacks & inquiries");
        assertThat(quantityOfOrderFiltered).isEqualTo(quantityOfOrderOnTabChargebackInquiry);
    }

    @Then("set up new fraud filters")
    public void setUpNewFraudFilters(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String sName = SessionData.getDataTbVal(data, row, "Name");
            String sAction = SessionData.getDataTbVal(data, row, "Action");
            String sRuleName = SessionData.getDataTbVal(data, row, "Rule Name");
            String sCondition = SessionData.getDataTbVal(data, row, "Rule Condition");
            String sValue = SessionData.getDataTbVal(data, row, "Value");

            orderSteps.addFraudFilter();
            orderSteps.enterFraudRuleName(sName);
            orderSteps.chooseRuleAction(sAction);
            orderSteps.selectRule(sRuleName, sCondition, sValue);
        }
        orderSteps.clickbutton("Save changes");
    }

    @And("delete {string} fraud filter")
    public void delete(String fraudFilter) {
        orderSteps.searchFraudFilter(fraudFilter);
        orderSteps.deleteFraudFilter(fraudFilter);
    }

    @And("get quantity of order affect of rule {string}")
    public void getQuantityOfOrderAffect(String filterName) {
        orderSteps.searchFraudFilter(filterName);
        quantityOrderAffectBeforeCheckout = orderSteps.getQuantityOfOrderAffect(filterName);
    }

    @Then("^Compare \"([^\"]*)\" of order affect when order (meet|does not meet) the rule \"([^\"]*)\"$")
    public void compareQuantityOfOrderAffect(int increase, String filterName) {
        orderSteps.searchFraudFilter(filterName);
        String quantityOrderAffectAfterCheckout = orderSteps.getQuantityOfOrderAffect(filterName);
        int before = Integer.parseInt(quantityOrderAffectBeforeCheckout);
        int after = Integer.parseInt(quantityOrderAffectAfterCheckout);
        orderSteps.verifyQuantityOfOrderAffect(before,after, increase);
    }

}