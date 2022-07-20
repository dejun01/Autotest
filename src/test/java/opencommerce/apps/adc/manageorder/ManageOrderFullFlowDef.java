package opencommerce.apps.adc.manageorder;

import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps.ManageOrderFullFlowSteps;
import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps.MappingProductsSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.*;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.*;
import static java.util.Arrays.asList;
import static opencommerce.products.dashboard.ProductDetailDef.nameProductSbase;
import static common.utilities.LoadObject.*;

public class ManageOrderFullFlowDef {
    @Steps
    ProductSteps productSteps;
    @Steps
    MappingProductsSteps mappingProductsSteps;
    @Steps
    ManageOrderFullFlowSteps manageOrderFullFlowSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    OrderSteps orderSteps;
    @Steps
    LoginDashboardSteps loginStep;
    private HashMap<String, String> lstStatusAfterPlace;
    List<String> lstOrder = new ArrayList<>();
    private String orderKey;
    String shop = LoadObject.getProperty("shop");

    @Then("^place an order with multiple item on Shopbase$")
    public void place_order_with_multiple_items_on_shopbase(List<List<String>> dataTable) {
        String items = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            ArrayList<String> orderInfo = new ArrayList<>();
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order Key");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product Name");

            productSteps.addMultipleProductsToCart(productName);
            if (row < (dataTable.size() - 1)) {
//              if it isn't last row then add regrex ">"
                items = items + productName + ">";
                orderInfo.add(items);
            } else {
//              else it is last row then not add regrex ">"
                orderInfo.add(items + productName);
            }
            // if it isn't last row then put into orderMap.
            if (row == (dataTable.size() - 1)) {
                enterShippingAddressThenPayment();
                String orderNumber = thankyouSteps.getOrderNumber();
                orderInfo.add(orderNumber);
                ordersMap.put(orderKey, orderInfo);
                thankyouSteps.clickOnContinueShoppingButton();
            }
        }
        mappingProductsSteps.switchToTheFirstTab();
    }

    private String productList(String productKeyList) {
        String productKey = "", productName = "";
        String productList = productKeyList;
        String[] prods = productKeyList.split(";");

        for (String prod : prods) {
            if (prod.contains(">")) {
                productKey = prod.trim().split(">")[0];
                productName = getImportedProdListToAdc(productKey);
            } else {
                productKey = prod;
                productName = getImportedProdListToAdc(productKey);
            }
            productList = productList.replace(productKey, productName);
        }
        System.out.println("productList = " + productList);
        return productList;
    }

    @Then("^place order on Shopbase$")
    public void place_order_on_shopbase(List<List<String>> dataTable) {
        String orderKey = "", productKey = "", prodNameAndQuantity = "";
        String item = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            orderKey = SessionData.getDataTbVal(dataTable, row, "Order KEY");
            productKey = SessionData.getDataTbVal(dataTable, row, "Product KEY");
            prodNameAndQuantity = SessionData.getDataTbVal(dataTable, row, "Product name>Quantity");
            if (!productKey.isEmpty()) {
                item = productList(productKey);
            } else {
                item = prodNameAndQuantity;
            }
//            loginStep.openShop(shop);
            productSteps.addMultipleProductsToCart(item);
            productSteps.goToCart();
            cartSteps.clickButtonCheckoutOnCart();
            enterShippingAddressThenPayment();
            orderList.put(orderKey, asList(orderNumber, item));
            System.out.println("order number = " + orderNumber);
        }
    }

    public void enterShippingAddressThenPayment() {
        customerInformationSteps.enterCustomerInformationOnSF();
        customerInformationSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.enterPaymentMethodByStripe();
        paymentMethodSteps.clickBtnCompleteOrder();
        orderNumber = thankyouSteps.getOrderNumber();
    }

    @Then("^verify click on order name in ADC app should be redirected to ShopBase order$")
    public void verify_mapping_adc_with_shopbase() {
        manageOrderFullFlowSteps.clickIntoOrderNumberHyperlink(orderNumber);
        manageOrderFullFlowSteps.switchToLastestTab();
        manageOrderFullFlowSteps.verifyOrderPageDisplay();
        mappingProductsSteps.switchToTheFirstTab();
    }

    @Then("^click on order name in ADC app should be redirected to ShopBase order$")
    public void click_on_order_name() {
        manageOrderFullFlowSteps.clickIntoOrderNumberHyperlink(orderNumber);
        manageOrderFullFlowSteps.verifyOrderPageDisplay();
    }

    private String getProdVar() {
        String prodVar = "";
        Set<Map.Entry<String, List<String>>> setHashMap = productListAdded.entrySet();
        for (Map.Entry<String, List<String>> i : setHashMap) {
            if (i.getKey().contains(","))
                prodVar = i.getKey().replace(",", " / ");
            else
                prodVar = i.getKey();
        }
        return prodVar;
    }

    private String getQuantity() {
        String quantity = "";
        List<String> priceAndQuantity = new ArrayList<>();

        Set<Map.Entry<String, List<String>>> setHashMap = productListAdded.entrySet();
        for (Map.Entry<String, List<String>> i : setHashMap) {
            priceAndQuantity = i.getValue();
            for (String value : priceAndQuantity) {
                if (!isContainCurrencySymbol(value)) {
                    quantity = value;
                }
            }
        }
        return quantity;
    }

    @Then("^verify order detail in manage order$")
    public void verify_order_detail_in_manage_order(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product Name");
            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "Shipping Method");
            String mappingStatus = SessionData.getDataTbVal(dataTable, row, "Mapping Status");
            String orderStatus = SessionData.getDataTbVal(dataTable, row, "ADC Order Status");
            String msg = SessionData.getDataTbVal(dataTable, row, "Shipping Address");
            String productCost = SessionData.getDataTbVal(dataTable, row, "Product Cost");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer");
            String aliOrder = SessionData.getDataTbVal(dataTable, row, "AliExpress Order");
            String warningOrder = SessionData.getDataTbVal(dataTable, row, "Order Warning");
            String prodVar = getProdVar();
            String quantity = getQuantity();

            manageOrderFullFlowSteps.verifyProductDisplayed(productName);
            manageOrderFullFlowSteps.verifyProductCost(prodVar, quantity, productCost);
            manageOrderFullFlowSteps.verifyCustomerName(customer);
            manageOrderFullFlowSteps.verifyShippingAddressIsInvalid(msg);
            manageOrderFullFlowSteps.verifyShippingMethod(shippingMethod);
            manageOrderFullFlowSteps.verifyMappingStatus(mappingStatus);
            manageOrderFullFlowSteps.verifyOrderStatus(orderStatus);
            manageOrderFullFlowSteps.verifyAliExpressOrderNumber(aliOrder);
            manageOrderFullFlowSteps.verifyWarningOrder(warningOrder);
        }
    }

    @Then("^expand order \"([^\"]*)\" in list ADC$")
    public void expand_order_in_list_ADC(String orderKey) {
        if (!orderKey.isEmpty()) {
            orderNumber = orderList.get(orderKey).get(0);
        }
        manageOrderFullFlowSteps.expandAnOrderInList(orderNumber);
    }

    @When("^open Map product screen of \"([^\"]*)\"$")
    public void open_map_product_screen_of_something(String orderKey) {
        String productName = "";
        if (!orderKey.isEmpty()) {
            productName = ordersMap.get(orderKey).get(0);
            orderNumber = ordersMap.get(orderKey).get(1);
        }
        manageOrderFullFlowSteps.clickOnMapProductButton(orderNumber, productName);
    }

    @When("open mapping product screen of {string}")
    public void openMappingProductScreenOf(String prodVar) {
        if (prodVar.matches("@(.*)@")) {
            prodVar = nameProductSbase;
        }
        manageOrderFullFlowSteps.clickOnMapProductButton(orderNumber, prodVar);
    }

    @When("^select shipping method")
    public void select_shipping_method(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productKey = SessionData.getDataTbVal(dataTable, row, "Product KEY");
            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
            manageOrderFullFlowSteps.selectShippingMethod(productKey, shippingMethod);
            manageOrderFullFlowSteps.verifySelectShippingMethodSuccessfully(shippingMethod);
        }
    }

    @When("^place order on ADC app and verify")
    public void place_order_on_ADC_app_and_verify(List<List<String>> dataTable) {
        List<String> lstOrder = new ArrayList<>();
        lstStatusAfterPlace = new HashMap<>();
        float sumProductCost = 0, sumShippingCost = 0, totalCost = 0, incomtotal = 0;
        int totalItems = 0;
        int itemUncheck = 0;
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            orderKey = SessionData.getDataTbVal(dataTable, row, "Order KEY");
            String uncheckedOrder = SessionData.getDataTbVal(dataTable, row, "Unchecked Order");
            String adcStatus = SessionData.getDataTbVal(dataTable, row, "ADC Order's Status");
            String tabName = SessionData.getDataTbVal(dataTable, row, "Tab Name");
            String items = SessionData.getDataTbVal(dataTable, row, "Total Items");


            String orderNumber = orderList.get(orderKey).get(0);
            manageOrderFullFlowSteps.expandAnOrderInList(orderNumber);
            sumProductCost = sumProductCost + manageOrderFullFlowSteps.getDataBeforePlaceOrder("Product Cost:");
            sumShippingCost = sumShippingCost + manageOrderFullFlowSteps.getDataBeforePlaceOrder("Shipping Cost:");
            totalCost = totalCost + manageOrderFullFlowSteps.getDataBeforePlaceOrder("Total Cost:");

            if (!uncheckedOrder.isEmpty()) {
                itemUncheck = row - 1;
            }

            if (!tabName.isEmpty())
                adcStatus = adcStatus + ">" + tabName;
            lstStatusAfterPlace.put(orderNumber, adcStatus);

            if (!items.isEmpty()) {
                totalItems = Integer.parseInt(items);
            }
        }

        manageOrderFullFlowSteps.searchThenSelectOrderNumber(lstOrder);
        manageOrderFullFlowSteps.markAsFulfilledProducts("Action");
        manageOrderFullFlowSteps.selectOption("");
        manageOrderFullFlowSteps.verifySumTotalItemsPO(totalItems + " items");
        manageOrderFullFlowSteps.verifySumProductCostPO(String.format(java.util.Locale.US, "%.2f", sumProductCost));
        manageOrderFullFlowSteps.verifySumShippingPO(String.format(java.util.Locale.US, "%.2f", sumShippingCost));
        manageOrderFullFlowSteps.verifyTotalCostPO(String.format(java.util.Locale.US, "%.2f", totalCost));

        if (itemUncheck != 0) {
            String orderRemove = lstOrder.get(itemUncheck);
            manageOrderFullFlowSteps.uncheckOrderOnPlaceOrder(orderRemove);
            lstOrder.remove(itemUncheck);
        }
        manageOrderFullFlowSteps.markAsFulfilledProducts("Confirm");
        manageOrderFullFlowSteps.verifyPlaceOrderSuccess(lstOrder);
        manageOrderFullFlowSteps.markAsFulfilledProducts("Close");
    }

    @When("place order from ADC to AliExpress")
    public void placeOrderFromADCToAliExpress(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order KEY");

            String orderNumber = orderList.get(orderKey).get(0);
            lstOrder.add(orderNumber);
            if (lstOrder.size() == 1) { // place single order
                manageOrderFullFlowSteps.expandAnOrderInList(orderNumber);
                manageOrderFullFlowSteps.clickOnOrderBtn(orderNumber);
            } else { // place multiple orders
                manageOrderFullFlowSteps.selectMultipleOrder(lstOrder);
                manageOrderFullFlowSteps.markAsFulfilledProducts("Action");
                manageOrderFullFlowSteps.selectOption("Place order");
                manageOrderFullFlowSteps.verifyOrderInfoIsDisplayedCorrectly();
                manageOrderFullFlowSteps.markAsFulfilledProducts("Confirm");
                manageOrderFullFlowSteps.verifyPlaceOrderSuccess(lstOrder);
                manageOrderFullFlowSteps.markAsFulfilledProducts("Close");
            }
            manageOrderFullFlowSteps.markAsFulfilledProducts("Confirm");
            manageOrderFullFlowSteps.markAsFulfilledProducts("Close");
        }
    }

    @When("^open Shipping Address popup and verify")
    public void shipping_address_popup(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            System.out.println("row = " + row);
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String firstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String lastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String company = SessionData.getDataTbVal(dataTable, row, "Company");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String apartment = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String zipcode = SessionData.getDataTbVal(dataTable, row, "ZIP/Postal Code");
            boolean editData = convertStatus(SessionData.getDataTbVal(dataTable, row, "Edit data"));

            manageOrderFullFlowSteps.openShippingAddressPopup(orderNumber);
            if (editData) {
                manageOrderFullFlowSteps.enterFirstName(firstName);
                manageOrderFullFlowSteps.enterLastName(lastName);
                manageOrderFullFlowSteps.enterAddress(address);
                manageOrderFullFlowSteps.enterPhoneNumber(phone);
                manageOrderFullFlowSteps.enterZipCode(zipcode);
                manageOrderFullFlowSteps.enterCountry(country, state);
                manageOrderFullFlowSteps.focusOutTextbox();
                manageOrderFullFlowSteps.enterCity(city);
                manageOrderFullFlowSteps.focusOutTextbox();
                manageOrderFullFlowSteps.enterCompany(company);
                manageOrderFullFlowSteps.enterApartment(apartment);
                manageOrderFullFlowSteps.saveShippingAddress();
            } else {
                manageOrderFullFlowSteps.verifyShippingAddress("First name", firstName);
                manageOrderFullFlowSteps.verifyShippingAddress("Last name", lastName);
                manageOrderFullFlowSteps.verifyShippingAddress("Phone number", phone);
                manageOrderFullFlowSteps.verifyShippingAddress("Address", address);
                manageOrderFullFlowSteps.verifyShippingAddress("Country", country);
                manageOrderFullFlowSteps.verifyShippingAddress("State/Province/Region", state);
                manageOrderFullFlowSteps.verifyShippingAddress("City", city);
                manageOrderFullFlowSteps.verifyShippingAddress("ZIP/Postal Code", zipcode);
            }
            addressShippingInfo.put("Country", country);
            addressShippingInfo.put("State", state);
            if (message.equals("Saved successfully")) {
                manageOrderFullFlowSteps.verifyToastMsgDisplayed(message);
                manageOrderFullFlowSteps.verifyShippingAddressIsInvalid("Shipping address is invalid", false);
            } else {
                manageOrderFullFlowSteps.verifyErrorMsgDisplayed(message);
                manageOrderFullFlowSteps.closePopupEditShippingAddressWithoutSaving();
            }
        }
    }

    @When("^fulfill order from ADC and verify$")
    public void fulfill_order_from_adc_and_verify() {
        manageOrderFullFlowSteps.clickMaskAsFullfillButton();
        manageOrderFullFlowSteps.markAsFulfilledProducts("Confirm");
    }

    @And("^switch to \"([^\"]*)\" tab in ADC$")
    public void switch_to_something_tab_in_adc(String tabName) {
        manageOrderFullFlowSteps.selectTabName(tabName);
    }

    @When("^cancel order and verify$")
    public void cancel_order_and_verify(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String isCancelDisplay = SessionData.getDataTbVal(dataTable, row, "Cancel Button Display");
            String orders = SessionData.getDataTbVal(dataTable, row, "Cancel Order");
            String statusSB = SessionData.getDataTbVal(dataTable, row, "Shopbase Order's Status");
            String statusAfterCancel = SessionData.getDataTbVal(dataTable, row, "Shopbase Order's Status After Cancel");
            String tabBeforeCancel = SessionData.getDataTbVal(dataTable, row, "Tab Before Cancel");
            String tabAfterCancel = SessionData.getDataTbVal(dataTable, row, "Tab After Cancel");

            String order;
            if (orders.isEmpty())
                order = orderNumber;
            else order = ordersMap.get(orders).get(1);

            manageOrderFullFlowSteps.selectTabName(tabBeforeCancel);
            manageOrderFullFlowSteps.searchOrderInListADC(order);
            manageOrderFullFlowSteps.clickIntoOrderNumberHyperlink(order);
            manageOrderFullFlowSteps.switchToLastestTab();
            manageOrderFullFlowSteps.verifyShopbaseStatus(statusSB);
            if (Boolean.parseBoolean(isCancelDisplay)) {
                orderSteps.openCancelOrderPopup();
                orderSteps.confirmCancelOrder();
                orderSteps.verifyStatusOrders(statusAfterCancel);
            }
            manageOrderFullFlowSteps.switchToTheFirstTab();
            if (!tabAfterCancel.isEmpty()) {
                manageOrderFullFlowSteps.verifyItemExistInCurrentTab(order, tabAfterCancel);
            }
        }
    }

    @Then("^open order detail from ADC and refund$")
    public void open_order_detail_from_adc_and_refund(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String order = SessionData.getDataTbVal(dataTable, row, "Order");
            String item = SessionData.getDataTbVal(dataTable, row, "Refund Item");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            boolean isRestock = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is Restock"));
            String tabName = SessionData.getDataTbVal(dataTable, row, "ADC Order's Status");

            String orderNumber = ordersMap.get(order).get(1);
            manageOrderFullFlowSteps.selectTabName("All");
            manageOrderFullFlowSteps.searchOrderInListADC(orderNumber);
            manageOrderFullFlowSteps.clickIntoOrderNumberHyperlink(orderNumber);
            manageOrderFullFlowSteps.switchToLastestTab();

            orderSteps.navigateToRefundScreen();
            orderSteps.enterRefundedItemQuantity(item, Integer.parseInt(quantity));
            orderSteps.isRestockedItem(quantity, isRestock);
            orderSteps.clickOnRefundButton();

            manageOrderFullFlowSteps.switchToTheFirstTab();
            manageOrderFullFlowSteps.verifyItemExistInCurrentTab(orderNumber, tabName);
        }
    }

    @And("^switch to the first tab$")
    public void switch_to_the_first_tab() {
        manageOrderFullFlowSteps.switchToTheFirstTab();
    }

    @And("select the tab {string} then verify order info")
    public void selectTheTabThenVerifyOrderInfo(String arg0) {

    }

    @Then("verify ShopBase order status")
    public void verifyShopBaseOrderStatus(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sbOrderStatus = SessionData.getDataTbVal(dataTable, row, " ShopBase Order Status");
            manageOrderFullFlowSteps.clickIntoOrderNumberHyperlink(orderNumber);
            orderSteps.verifyStatusOrders(sbOrderStatus);
        }
    }

    @And("^click on order \"([^\"]*)\" to redirect ShopBase order detail$")
    public void clickOnOrderToRedirectShopBaseOrderDetail(String orderKey) {
        if (!orderKey.isEmpty())
            orderNumber = orderList.get(orderKey).get(0);
        manageOrderFullFlowSteps.clickIntoOrderNumberHyperlink(orderNumber);
    }

    @And("click on {string} button of the product {string}")
    public void clickOnButtonOfTheProduct(String btnName, String prodName) {
        manageOrderFullFlowSteps.markAsFulfilledProducts(btnName, prodName);
    }

    @And("open Shipping Address popup then verify customer information is displayed correctly")
    public void openShippingAddressPopupThenVerifyCustomerInformationIsDisplayedCorrectly(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String cpfOrCnpj = SessionData.getDataTbVal(dataTable, row, "CPF/CNPJ number");

            manageOrderFullFlowSteps.openShippingAddressPopup(orderNumber);
            manageOrderFullFlowSteps.verifyShippingAddress("CPF number", cpfOrCnpj);
        }
    }


}

