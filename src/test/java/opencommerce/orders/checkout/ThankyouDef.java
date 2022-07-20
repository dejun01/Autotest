package opencommerce.orders.checkout;

import com.opencommerce.shopbase.storefront.api.InfoAPI;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencsv.exceptions.CsvValidationException;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import org.junit.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;


public class ThankyouDef {
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    InfoAPI infoAPI;

    @Given("^verify thank you page$")
    public void verify_thank_you_page() {
        thankyouSteps.verifyThankYouPage();
        orderNumber = thankyouSteps.getOrderNumber();
    }

    @Given("^verify thank you page then get all information$")
    public void verify_thank_you_page_then_get_all_information() {
        thankyouSteps.verifyThankYouPage();
        orderCurrency = orderSummarySteps.getOrderCurrency();
        discountCode = orderSummarySteps.getDiscountCode();
        discountAmount = orderSummarySteps.getDiscountAmount();
        shippingFee = orderSummarySteps.getShippingFee();
        listProduct = orderSummarySteps.getProductList();
        customerEmail = thankyouSteps.getCustomerEmail();
        customerName = thankyouSteps.getCustomerName();
        subTotal = thankyouSteps.getSubTotal();
        totalAmt = orderSummarySteps.getTotalAmt();
        orderNumber = thankyouSteps.getOrderNumber();
        listOrder.add(orderNumber);
        System.out.println("List Product: " + listProduct);
        thankyouSteps.logInfor("List Product: " + listProduct);
        System.out.println(listOrder);
        System.out.println(totalAmt);
        String shippingAddressInThankUPage = thankyouSteps.getShippingAddressOnSF();
        System.out.println("shipping address ts = " + shippingAddressInThankUPage);
        String billingAddressInThankYouPage = thankyouSteps.getBillingAddressOnSF();
        if (!paymentGateway.equalsIgnoreCase("Paypal")) {
            //verify shipping address
            thankyouSteps.verifyAddressInThankYouPage(shippingAddress, shippingAddressInThankUPage);
            //verify billing address
            if (billingAddress.equals("Same as shipping address")) {
                thankyouSteps.verifyAddressInThankYouPage(billingAddressInThankYouPage, shippingAddress);
            } else {
                thankyouSteps.verifyAddressInThankYouPage(billingAddressInThankYouPage, billingAddress);
            }
        }
        listOfUsedPaymentAccount.add(infoAPI.getPaymentMethodIDAfterCheckout(checkoutToken));
        System.out.println(listOfUsedPaymentAccount);
    }

    @Given("^get all information order$")
    public void get_all_information_order() {
        thankyouSteps.verifyThankYouPage();
        listOrder.add(orderNumber);
        orderCurrency = orderSummarySteps.getOrderCurrency();
        discountCode = orderSummarySteps.getDiscountCode();
        discountAmount = orderSummarySteps.getDiscountAmount();
        if (!discountAmount.isEmpty())
            float_discountAmount = getPrice(discountAmount);
        shippingFee = orderSummarySteps.getShippingFee();
        listProduct = orderSummarySteps.getProductList();
        customerEmail = thankyouSteps.getCustomerEmail();
        customerName = thankyouSteps.getCustomerName();
        subTotal = thankyouSteps.getSubTotal();
        if (!subTotal.isEmpty())
            float_subTotal = getPrice(subTotal);
        totalAmt = orderSummarySteps.getTotalAmt();
        float_totalAmt = getPrice(totalAmt);
        orderNumber = thankyouSteps.getOrderNumber();
        orderId = thankyouSteps.getOrderId();
        checkoutToken = thankyouSteps.getCheckoutToken();
        shippingAddress = thankyouSteps.getShippingAddressOnSF();
        totalTax = thankyouSteps.getTotalTax();
    }

    @Given("^verify thank you page then get complete time$")
    public void verify_thank_you_page_then_get_complete_time() {
        verify_thank_you_page_then_get_all_information();
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.HOUR, -7);
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        completedOrderTime = dateFormat.format(date);
    }

    @Given("^verify custom option should be shown below product detail on thankyou page$")
    public void verify_custom_option_on_thankyou_page(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String detail = SessionData.getDataTbVal(dataTable, row, "Detail");
            String customOptionName = detail.split(":")[0];
            String input = detail.split(":")[1].trim();
            thankyouSteps.verifyCustomOptionDetail(customOptionName, input);
        }
    }

    @Given("^verify product is \"([^\"]*)\" have (\\d+) items in order$")
    public void verify_product_is_in_order_with_price(String product, int quanity) {
        thankyouSteps.verifyExistProduct(product);
        thankyouSteps.verifyQuantityOfProductInOrder(product, quanity);
    }

    @Given("^Click on shop name HyperLink to back to home page$")
    public void clickOnShopNameHyperLink() {
        thankyouSteps.clickOnShopNameHyperLink();
    }

    public float getPrice(String sPrice) {
        return (orderSummarySteps.getPrice(sPrice));
    }

    @And("verify information in page thank")
    public void verifyInformationInPageThank(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shipping = SessionData.getDataTbVal(dataTable, row, "Shipping");
            if (!shipping.isEmpty()) {
                Assert.assertEquals(shipping, shippingFee);
            }
        }
    }

    @Then("verify email on thank you page")
    public void verifyEmailOnThankYouPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String mail = SessionData.getDataTbVal(dataTable, row, "Mail");
            thankyouSteps.verifyEmail(mail);
        }
    }

    @Then("get all information after checkout")
    public void getAllInformationAfterCheckout() {
        thankyouSteps.verifyThankYouPage();
        orderCurrency = orderSummarySteps.getOrderCurrency();
        discountCode = orderSummarySteps.getDiscountCode();
        discountAmount = orderSummarySteps.getDiscountAmount();
        shippingFee = orderSummarySteps.getShippingFee();
        listProduct = orderSummarySteps.getProductList();
        customerEmail = thankyouSteps.getCustomerEmail();
        customerName = thankyouSteps.getCustomerName();
        subTotal = thankyouSteps.getSubTotal();
        totalAmt = orderSummarySteps.getTotalAmt();
        orderNumber = thankyouSteps.getOrderNumber();
        listOrder.add(orderNumber);
    }

    @And("verify shipping method after add PPC")
    public void verifyShippingMethodAfferAddPCC(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String eMethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
            String ePrice = SessionData.getDataTbVal(dataTable, row, "Price");
            System.out.println(eMethod);
            System.out.println(ePrice);

            thankyouSteps.verifyShippingMethod(eMethod);
            thankyouSteps.verifyShippingPrice(ePrice);
        }
    }

    @And("verify block redirect and scan order result")
    public void verifyBlockRedirect() {
        thankyouSteps.verifyExamplePage();
        thankyouSteps.verifyScanOrderSuccess();
    }


    @And("get the order information including")
    public void getTheOrderInformationIncluding(List<String> dataTable) {
        thankyouSteps.verifyThankYouPage();
        for (String column : dataTable) {
            switch (column) {
                case "order number":
                    orderNumber = thankyouSteps.getOrderNumber();
                    break;
                case "order id":
                    orderId = thankyouSteps.getOrderId();
                    break;
                case "total amount":
                    totalAmt = orderSummarySteps.getTotalAmt();
                    break;
                case "customer name":
                    customerName = thankyouSteps.getCustomerName();
                    break;
                case "customer email":
                    customerEmail = thankyouSteps.getCustomerEmail();
                    break;
                case "product list":
                    listProduct = orderSummarySteps.getProductList();
                    break;
                case "discount code":
                    discountCode = orderSummarySteps.getDiscountCode();
                    break;
                case "shipping fee":
                    shippingFee = orderSummarySteps.getShippingFee();
                    break;
                case "shipping method":
                    shippingMethod = thankyouSteps.getShippingMethod();
                    break;
                case "subtotal":
                    subTotal = thankyouSteps.getSubTotal();
                    break;
                case "payment account list":
                    Integer paymentID = infoAPI.getPaymentMethodIDAfterCheckout(checkoutToken);
                    thankyouSteps.addPaymentAccountToList(paymentID);
                    break;
                case "discount value":
                    discountAmount = orderSummarySteps.getDiscountAmount();
                    break;
                case "tax" :
                    taxAmount = orderSummarySteps.getTaxAmount();
                    break;
                default:
                    fail();
            }
        }
    }

    @And("verify address on thank you page")
    public void verifyAddressOnThankYouPage() {
        String shippingAddressInThankUPage = thankyouSteps.getShippingAddressOnSF();
        String billingAddressInThankYouPage = thankyouSteps.getBillingAddressOnSF();
        //verify shipping address
        thankyouSteps.verifyAddressInThankYouPage(shippingAddress, shippingAddressInThankUPage);
        //verify billing address
        thankyouSteps.verifyAddressInThankYouPage(billingAddressInThankYouPage, billingAddress);
    }


    @And("input information order into {string} file csv")
    public void inputInformationOrderIntoFileCsv(String fileName) {
        thankyouSteps.addOrderIdIntoCSV(fileName);
    }

    @And("write information order in {string} file csv")
    public void writeInformationOrderInFileCsv(String fileName) throws CsvValidationException, ParseException, IOException {
        thankyouSteps.readListOrderInCSV(fileName);

    }
}
