package com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps;

import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.pages.ManageOrderFullFlowPages;
import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static common.utilities.LoadObject.*;

import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ManageOrderFullFlowSteps extends ScenarioSteps {
    OrderPage orderPage;
    ManageOrderFullFlowPages manageOrderFullFlowPages;
    float productCost;

    @Step
    public void expandAnOrderInList(String orderNumber) {
        manageOrderFullFlowPages.waitForPageLoad();
        manageOrderFullFlowPages.expandAnOrderInList(orderNumber);
    }

    @Step
    public void searchOrderInListADC(String orderNumber) {
        manageOrderFullFlowPages.searchOrderInListADC(orderNumber);
    }

    @Step
    public void clickOnMapProductButton(String orderNumber, String prodVar) {
        mappingProdVar = prodVar;
        manageOrderFullFlowPages.waitForPageLoad();
        manageOrderFullFlowPages.clickIntoMapProductButton(orderNumber, prodVar);
    }

    @Step
    public void clickIntoOrderNumberHyperlink(String orderNumber) {
        manageOrderFullFlowPages.clickOnTheOrderName(orderNumber); //method clickLinkTextWithLabel not working
        manageOrderFullFlowPages.switchToTheLastestTab();
        manageOrderFullFlowPages.waitForEverythingComplete();
    }

    @Step
    public void verifyMappingInADCWithShopbaseOrder(String orderNumber) {
        int numItem = manageOrderFullFlowPages.getNumberOfItemInOrder(orderNumber);
        float totalCost;
        for (int i = 1; i <= numItem; i++) {
            manageOrderFullFlowPages.switchToTheLastestTab();
            String productName = orderPage.getProductName(i);
            String totalOrder = orderPage.getTotal();
            String quatityItem = orderPage.getQuantityProductForMultipleItem(i).replace("×", "x");
            String productVariant = orderPage.getProductVariant(orderPage.getProductName(i));
            manageOrderFullFlowPages.switchToTheFirstTab();
            manageOrderFullFlowPages.verifyProductName(orderNumber, productName);
            manageOrderFullFlowPages.verifyVariant(orderNumber, productVariant, productName);
            manageOrderFullFlowPages.verifyCostAndQuanity(orderNumber, quatityItem, productName);
            manageOrderFullFlowPages.verifyTotalOrder(orderNumber, totalOrder);
        }
    }

    @Step
    public void verifyOrderPageDisplay() {
        manageOrderFullFlowPages.verifyShopbaseOrderDisplay();
    }

    @Step
    public void switchToLastestTab() {
        manageOrderFullFlowPages.switchToTheLastestTab();
    }

    public String getSelectedShippingMethod(String orderName) {
        return getOrderInfo(orderName, "Shipping Method");
    }

    public String getOrderInfo(String orderName, String column) {
        return manageOrderFullFlowPages.getOrderInfo(orderName, column);
    }

    @Step
    public void verifyShippingMethod(String shippingMethod) {
        String actualShippingMethod = getSelectedShippingMethod(orderNumber);
        if (!shippingMethod.isEmpty())
            assertThat(actualShippingMethod).isEqualTo(shippingMethod);
    }

    @Step
    public void verifyMappingStatus(String mappingStatus) {
        if (!mappingStatus.isEmpty()) {
            String actualMapping = getOrderInfo(orderNumber, "Mapping Status");
            assertThat(actualMapping).isEqualTo(mappingStatus);
        }
    }

    @Step
    public void verifyTotalCost(String orderNumber, String productName) {
        float expTotalCost;
        float shippingCost = manageOrderFullFlowPages.getShippingCost(orderNumber);
        if (manageOrderFullFlowPages.isOrderNotNeedToMap(orderNumber, productName)) {
            expTotalCost = productCost + shippingCost;
            manageOrderFullFlowPages.verifyTotalCost(orderNumber, "$" + expTotalCost);
        }
    }

    @Step
    public void verifyOrderStatus(String orderStatus) {
        if (!orderStatus.isEmpty()) {
            String actualOrderStatus = manageOrderFullFlowPages.getOrderInfo(orderNumber, "Status");
            assertThat(actualOrderStatus).isEqualTo(orderStatus);
        }
    }

    @Step
    public void verifyAliExpressOrderNumber(String aliOrder) {
        if (!aliOrder.isEmpty()) {
            String orderStatus = manageOrderFullFlowPages.getOrderInfo(orderNumber, "Status");
            String actualAliOrder = manageOrderFullFlowPages.getAliOrderInfo(orderNumber, orderStatus, nameProductImportToStore, "AliExpress Order");
            if (!aliOrder.matches("@(.*)@")) {
                assertThat(actualAliOrder).isEqualTo(aliOrder);
            } else {
                assertThat(actualAliOrder.matches("(\\d+)")).isTrue(); // verify Ali order
            }
        }
    }

    @Step
    public void verifyTrackingNumber(String orderNumber, String trackingNumber, String productName) {
//        String actValue = manageOrderFullFlowPages.getProductAliInfo(orderNumber, "Tracking Number:", productName);
//        if (!existTrackingNumber) {
//            assertThat(actValue.equals("-"));
//        } else {
//            assertThat(!actValue.equals("-"));
//        }
    }

    @Step
    public void verifyShippingAddressIsInvalid(String msg) {
        if (!msg.isEmpty())
            manageOrderFullFlowPages.verifyShippingAddress(orderNumber, msg, true);
    }

    @Step
    public void verifyShippingAddressIsInvalid(String msg, boolean isDisplayed) {
        if (!msg.isEmpty())
            manageOrderFullFlowPages.verifyShippingAddress(orderNumber, msg, isDisplayed);
    }

    @Step
    public void verifyWarningOrder(String warningMessage) {
        if (!warningMessage.isEmpty()) {
            boolean isMissingShippingAdd = manageOrderFullFlowPages.isMissingShippingAddress(orderNumber, warningMessage);
            String shippingMethod = getSelectedShippingMethod(orderNumber);
            String mappingStatus = getOrderInfo(orderNumber, "Mapping Status");
            if (isMissingShippingAdd || shippingMethod.equals("Missing info") || mappingStatus.equals("Need to map")) {
                manageOrderFullFlowPages.verifyWarningOrder(orderNumber, warningMessage);
            }
        }
    }

    public String roundOffTo2DecPlaces(float val) {
        return String.format("%.2f", val);
    }

    @Step
    public void verifyProductCost(String prodVar, String quantity, String productCost) {
        String originalCostAndQuantity = "", expectedCostAndQuantity = "", expectedCost = "";
        if (!productCost.isEmpty()) {
            String actualCost = manageOrderFullFlowPages.getProductCost(orderNumber, prodVar);

            switch (productCost) {
                case "@Product Cost@":
                    expectedCostAndQuantity = roundOffTo2DecPlaces(Float.parseFloat(aliCost)) + " x " + quantity;
                    break;
                case "@Ali product price@":
                    expectedCost = roundOffTo2DecPlaces(prodVarAliInfo.get(prodVar).get(0));
                    expectedCostAndQuantity = expectedCost + " x " + quantity;
                    break;
                case "-":
                    expectedCostAndQuantity = productCost;
                    break;
                default:
                    if (isContainCurrencySymbol(actualCost)) {
                        actualCost = actualCost.split("\\p{Sc}", 2)[1];
                    }
                    break;
            }
            assertThat(actualCost).isEqualTo(expectedCostAndQuantity);
        }

    }

    @Step
    public void verifyAddressPopupWithLabel(String label, String editData, String value, String expectMessage) {
        //label = first name, edit data = yes, value = "", expectMessage = "";
        if (expectMessage.isEmpty() && !editData.equals("No")) {
            expectMessage = "Please enter ";
            switch (label) {
                case "ZIP/Postal Code":
                    expectMessage += "postal code";
                    break;
                case "State/Province/Region":
                    expectMessage += "region";
                    break;
                default:
                    expectMessage += label.toLowerCase();
                    break;
            }
        }
        if (editData.equals("No")) {
            manageOrderFullFlowPages.verifyTextInputField(label, value);
        } else {
            if (!value.isEmpty() && label.equalsIgnoreCase("ZIP/Postal Code")) {
                expectMessage = "Please enter Latin letters/numbers/spaces/hyphens only,";
            }
            manageOrderFullFlowPages.enterValueAndVerifyMessageInAddressPopup(label, value, expectMessage);
        }
    }

    @Step
    public void verifyErrorMsgDisplayed(String msg) {
        if (!msg.isEmpty())
            manageOrderFullFlowPages.verifyErrorMsgDisplayed(msg);
    }

    @Step
    public void enterFirstName(String sFirstName) {
        if (!sFirstName.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("First name", sFirstName, 1);
        }
    }

    @Step
    public void enterLastName(String sLastName) {
        if (!sLastName.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("Last name", sLastName, 1);
        }
    }

    @Step
    public void enterAddress(String sAddress) {
        if (!sAddress.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("Address", sAddress, 1);
        }
    }

    @Step
    public void enterZipCode(String sZipcode) {
        if (!sZipcode.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("ZIP/Postal Code", sZipcode, 1);
        }
    }

    @Step
    public void enterCity(String sCity) {
        if (!sCity.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldThenEnter("City", sCity, 1);
        }
    }

    @Step
    public void enterCountry(String sCountry, String sState) {
        manageOrderFullFlowPages.enterInputFieldThenEnter("Country", sCountry, 1);
        manageOrderFullFlowPages.enterInputFieldThenEnter("State/Province/Region", sState, 1);
    }

    @Step
    public void enterCompany(String sCompany) {
        if (!sCompany.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("Company", sCompany, 1);
        }
    }

    @Step
    public void enterPhoneNumber(String sCompany) {
        if (!sCompany.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("Phone number", sCompany, 1);
        }
    }

    @Step
    public void enterApartment(String sApratment) {
        if (!sApratment.isEmpty()) {
            manageOrderFullFlowPages.enterInputFieldWithLabel("Apartment, suite, etc... (optional)", sApratment, 1);
        }
    }

    @Step
    public void saveShippingAddress() {
        manageOrderFullFlowPages.saveShippingAddress(true);
    }

    @Step
    public void markAsFulfilledProducts(String buttonName) {
        if (buttonName.isEmpty()) {
            manageOrderFullFlowPages.submitPopup();
        } else {
            manageOrderFullFlowPages.clickBtn(buttonName);
        }
        manageOrderFullFlowPages.waitUntilInvisibleLoading(10);
    }

    @Step
    public void markAsFulfilledProducts(String btnName, String prodName) {
        manageOrderFullFlowPages.markAsFulfilledItem(btnName, prodName);
        manageOrderFullFlowPages.clickBtn("Confirm");
    }

    @Step
    public void clickMaskAsFullfillButton() {
        manageOrderFullFlowPages.clickBtn("Mark as fulfilled");
    }

    @Step
    public void selectTabName(String tabName) {
        if (tabName.contains(">")) {
            String[] tabNames = tabName.split(">");
            for (String tab : tabNames) {
                manageOrderFullFlowPages.selectTabName(tab);
            }
        } else manageOrderFullFlowPages.selectTabName(tabName);

    }

    @Step
    public void openShippingAddressPopup(String orderNumber) {
        manageOrderFullFlowPages.openShippingAddressPopup(orderNumber);
    }

    @Step
    public void focusOutTextbox() {
        manageOrderFullFlowPages.clickLinkTextWithLabel("Edit shipping address");
    }

    @Step
    public void clickOnOrderBtn(String orderNumber) {
        manageOrderFullFlowPages.clickButtonPlaceOnOrder(orderNumber);
    }

    @Step
    public void verifySumTotalItemsPO(String totalProduct) {
        manageOrderFullFlowPages.verifySumDataOnPlaceOrder("product", totalProduct);
    }

    @Step
    public void verifySumIncomePO(float incomtotal) {
        manageOrderFullFlowPages.verifyIncomeOnPlaceOrder("income", incomtotal);
    }

    @Step
    public void verifySumProductCostPO(String productCost) {
        manageOrderFullFlowPages.verifySumDataOnPlaceOrder("product-cost", "$" + productCost);
    }

    @Step
    public float getDataBeforePlaceOrder(String value) {
        return Float.parseFloat(manageOrderFullFlowPages.getDataBeforePlaceOrder(value));
    }

    @Step
    public void checkAndSelectShippingMethod(String orderNumber, String shippingName, String productName) {
        if (manageOrderFullFlowPages.isMissingShipping(orderNumber)) {
            manageOrderFullFlowPages.checkAndSelectShippingMethod(orderNumber, shippingName, productName);
        }
    }

    @Step
    public void verifySumShippingPO(String shippingCost) {
        manageOrderFullFlowPages.verifySumDataOnPlaceOrder("shipping-fee", "$" + shippingCost);
    }

    @Step
    public void verifyTotalCostPO(String totalCost) {
        manageOrderFullFlowPages.verifySumDataOnPlaceOrder("result", "Total: $" + totalCost);
    }

    @Step
    public void verifyPlaceOrderSuccess(List<String> orders) {
        manageOrderFullFlowPages.verifyPlaceOrderSuccess(orders);
    }

    @Step
    public void verifyStatusOrders(String orderNumber, String status) {
        String tabName = "";
        if (status.contains(">")) {
            tabName = status.split(">")[1];
            status = status.split(">")[0];
        }
        manageOrderFullFlowPages.selectTabName(tabName);
        manageOrderFullFlowPages.expandAnOrderInList(orderNumber);
        manageOrderFullFlowPages.verifyOrderStatus(orderNumber, status);
    }

    @Step
    public List<String> getItemValid() {
        return manageOrderFullFlowPages.getListProductNameValid(true);
    }

    @Step
    public void verifyShopbaseStatus(String expect) {
        orderPage.verifyStatus(expect);
    }

    @Step
    public void switchToTheFirstTab() {
        manageOrderFullFlowPages.switchToTheFirstTab();
    }

    @Step
    public void verifyItemExistInCurrentTab(String orderNumber, String tabName) {
        String[] listTab;
        if (tabName.contains(">")) {
            listTab = tabName.split(">");
            for (String tab : listTab) {
                manageOrderFullFlowPages.selectTabName(tab);
                manageOrderFullFlowPages.checkOrderExistInListTab(orderNumber);
            }
        } else {
            manageOrderFullFlowPages.selectTabName(tabName);
            manageOrderFullFlowPages.checkOrderExistInListTab(orderNumber);
        }

    }

    @Step
    public void searchThenSelectOrderNumber(List<String> listOrder) {
        manageOrderFullFlowPages.searchThenSelectOrderNumber(listOrder);
    }

    public void searchThenSelectOrderNumber(String orderNumber) {
        manageOrderFullFlowPages.searchThenSelectOrderNumber(orderNumber);
    }

    @Step
    public void selectOption(String option) {
        manageOrderFullFlowPages.selectOption(option);
    }

    @Step
    public float getIncomeOnPlaceOrder() {
        return manageOrderFullFlowPages.getIncomeOnPlaceOrder();
    }

    @Step
    public void uncheckOrderOnPlaceOrder(String order) {
        manageOrderFullFlowPages.uncheckOrderInPO(order);
    }

    @Step
    public List<String> getListServiceShipping(String producName) {
        return manageOrderFullFlowPages.countServiceShiping(producName);
    }

    public void closePopupEditShippingAddressWithoutSaving() {
        manageOrderFullFlowPages.saveShippingAddress(false);
    }

    @Step
    public void verifyToastMsgDisplayed(String message) {
        manageOrderFullFlowPages.waitForTextToAppear(message, 7000);
    }

    public void verifyProductDisplayed(String expectedItem) {
        if (!expectedItem.isEmpty()) {
            String actualItem = manageOrderFullFlowPages.getProductByOrderNumber(orderNumber);
            assertThat(actualItem).isEqualTo(expectedItem);
        }
    }

    public void verifyCustomerName(String customer) {
        if (!customer.isEmpty()) {
            String actualCustomer = getOrderInfo(orderNumber, "Customer");
            assertThat(actualCustomer).isEqualTo(customer);
        }
    }

    public HashMap<String, String> getOrderInfo() {
        HashMap<String, String> orderInfo = new HashMap<>();
        String orderNo = manageOrderFullFlowPages.getOrderInfoOnPopup(orderNumber, "Order No.");
        String income = manageOrderFullFlowPages.getOrderInfoOnPopup(orderNumber, "Income");
        String productCost = manageOrderFullFlowPages.getOrderInfoOnPopup(orderNumber, "Product Cost");
        String country = manageOrderFullFlowPages.getOrderInfoOnPopup(orderNumber, "Country");
        String shippingFee = manageOrderFullFlowPages.getOrderInfoOnPopup(orderNumber, "Shipping fee");
        orderInfo.put("Income", income);
        orderInfo.put("Product Cost", productCost);
        orderInfo.put("Country", country);
        orderInfo.put("Shipping fee", shippingFee);
        return orderInfo;
    }

    public void verifyOrderInfoIsDisplayedCorrectly() {
        HashMap<String, String> actualOrderInfo = getOrderInfo();
        HashMap<String, String> expectedOrderInfo = new HashMap<>();
        expectedOrderInfo.put("Income", "");
    }

    @Step("#Select specific shipping carrier as requirement, in case the shipping method not existing select another because shipping could be changed toward Aliexpress")
    public void selectShippingMethod(String productKey, String shippingMethod) {
        String prodName = getImportedProdListToAdc(productKey);
        manageOrderFullFlowPages.selectShippingMethodByProductName(orderNumber, prodName, shippingMethod);
        manageOrderFullFlowPages.clickOnSaveBtnPopUp();
        shippingCarrierList.put(orderNumber + ":" + prodName, shippingMethod);
        System.out.println(" shippingCarrierList = " + shippingCarrierList);
    }

    @Step
    private boolean isAvailableShippingCarrier(String shippingMethod) {
        return manageOrderFullFlowPages.isAvailableShippingCarrier(shippingMethod);
    }

    @Step
    public void verifySelectShippingMethodSuccessfully(String expectedShippingMethod) {
        String actualShippingMethod = manageOrderFullFlowPages.getOrderInfo(orderNumber, "Shipping Method");
        assertThat(actualShippingMethod.contains(expectedShippingMethod)).isTrue();
    }

    public void selectMultipleOrder(List<String> lstOrder) {
        for (String orderNumber : lstOrder) {
            manageOrderFullFlowPages.checkCheckboxWithLabel(orderNumber);
        }
    }

    public void clickOnSaveChangesBtn() {
        manageOrderFullFlowPages.clickBtn("Save changes");
    }

    @Step
    public void verifyShippingAddress(String field, String expectedValue) {
        if (!expectedValue.isEmpty()) {
            String actualVal = manageOrderFullFlowPages.getDataByLabel(field);
            assertThat(actualVal).isEqualTo(expectedValue);
        }
    }
}
