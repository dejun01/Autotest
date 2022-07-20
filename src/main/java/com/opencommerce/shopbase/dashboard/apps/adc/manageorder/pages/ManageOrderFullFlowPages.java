package com.opencommerce.shopbase.dashboard.apps.adc.manageorder.pages;

import common.SBasePageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.productListAdded;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;

public class ManageOrderFullFlowPages extends SBasePageObject {
    private final String CLASS_EXPANDING = "is-expanding";
    private final String CLASS_ACTIVE_VALUE = "router-link-active";
    private final String STYLE_DISPLAY_NONE = "display: none";
    private final String BLOCK_ORDER_DETAIL = "//tr[descendant::*[text()[normalize-space()='%s']]]";
    private String aliStandardShipping = "AliExpress Standard Shipping";
    private String aliPremiumdShipping = "AliExpress Premium Shipping";
    private String epacketShipping = "ePacket";
    private String dhlShipping = "DHL";
    private String emsShipping = "EMS";

    public ManageOrderFullFlowPages(WebDriver driver) {
        super(driver);
    }

    public void expandAnOrderInList(String orderNumber) {
        searchOrderInListADC(orderNumber);
        String xpathParent = String.format(BLOCK_ORDER_DETAIL, orderNumber);
        String attribute = getAttributeValue(xpathParent, "class");
        if (!attribute.equals(CLASS_EXPANDING)) {
            waitElementToBeVisible(xpathParent);
            clickOnElement(xpathParent);
            waitForEverythingComplete(60);
        }
    }

    public void searchOrderInListADC(String orderNumber) {
        waitElementToBeVisible("//div[@id='app-list']//div[contains(@class,'table-responsive')]");
        enterInputFieldThenEnter("Search orders", orderNumber, 1);
        waitUntilInvisibleLoading(5);
    }

    public void clickIntoMapProductButton(String orderNumber, String prodVar) {
        String prodName = "", variantName = "";
        if (prodVar.contains(":")) {
            prodName = prodVar.split(":")[0];
            variantName = prodVar.split(":")[1];
        } else {
            prodName = prodVar;
        }
        String btnMapProduct = "(//tr[@class='is-expanding']//a[text()[normalize-space()='" + orderNumber + "']]/following::*[text()[normalize-space()='" + prodName + "']]/following::*[text()[normalize-space()='" + variantName + "']]/following::button//span[normalize-space()='Map Product'])[1]";
        waitElementToBeVisible(btnMapProduct);
        clickOnElement(btnMapProduct);
        waitForEverythingComplete();
    }

    public void verifyShopbaseOrderDisplay() {
        String xpath = "//span[normalize-space()='All orders']";
        String classValue = getAttributeValue(xpath, "class");
        assertThat(classValue.contains(CLASS_ACTIVE_VALUE));
    }

    public void verifyProductName(String orderNumber, String productName) {
        String xpath = "(//a[normalize-space()='%s']/following::a[text()[normalize-space()=\"%s\"]])[1]";
        assertThat(isElementVisible(String.format(xpath, orderNumber, productName), 5)).isEqualTo(true);
    }

    public void verifyVariant(String orderNumber, String expectedVariant, String productName) {
        String xpath = "(//a[normalize-space()='" + orderNumber + "']/following::*[text()[normalize-space()=\"" + productName + "\"]]//following::p[not(@class='product-price')])[1]";
        verifyElementText(xpath, expectedVariant);
    }

    public void verifyCostAndQuanity(String orderNumber, String expectedCost, String productName) {
        String xpath = "(//a[normalize-space()='%s']/following::*[text()[normalize-space()=\"%s\"]]//following::p[@class='product-price']//span)[1]";
        verifyElementText(String.format(xpath, orderNumber, productName), expectedCost);
    }

    public void verifyTotalOrder(String orderNumber, String expextTotal) {
        String xpath = BLOCK_ORDER_DETAIL + "//td[last()]//span";
        verifyElementText(String.format(xpath, orderNumber), expextTotal);
    }

    public void verifyProductCost(String orderNumber, String expectProductCost) {
        String xpath = BLOCK_ORDER_DETAIL + "//td[last()]//span";
        verifyElementText(String.format(xpath, orderNumber), expectProductCost);
    }

    public int getNumberOfItemInOrder(String orderNumber) {
        String xpath = "(//a[normalize-space()='%s']/following::tr[1]//div[@class='product-details'])";
        return countElementByXpath(String.format(xpath, orderNumber));
    }

    public void verifyTotalCost(String orderNumber, String expect) {
        String xpath = "(//a[normalize-space()='%s']/following::span[contains(text(),'Total Cost:')]//span)[1]";
        verifyElementText(String.format(xpath, orderNumber), expect);
    }

    public boolean isOrderNotNeedToMap(String orderNumber, String productName) {
        String xpath = "(//a[normalize-space()='%s']/following::*[text()[normalize-space()=\"" + productName + "\"]]/following::div[@class='actions']/div/span)[1]";
        String styleElement = getAttributeValue(String.format(xpath, orderNumber), "style");
        return styleElement.contains(STYLE_DISPLAY_NONE);

    }

    public void verifyShippingMethod(String orderNumber, String status, String productName) {
        String expStatus = status.split(">")[0];
        String expColor = status.split(">")[1];
        String xpathStatus = String.format(BLOCK_ORDER_DETAIL + "//span[normalize-space()='" + expStatus + "']", orderNumber);
        String xpathBtnShipping = String.format("(//a[normalize-space()='%s']/following::*[text()[normalize-space()=\"%s\"]]/following::button[contains(@class,'shipping-method')])[1]", orderNumber, productName);
        String xpathMessage = String.format("(//a[normalize-space()='%s']/following::*[text()[normalize-space()=\"%s\"]]/following::span[@data-label='Missing Shipping method info. Click to change.'])[1]", orderNumber, productName);
        assertThat(isElementVisible(xpathStatus, 5)).isEqualTo(true);
        verifyColor(xpathStatus, "color", expColor);
        if (expStatus.equals("Missing info") && isAwaitingOrder() && isOrderNotNeedToMap(orderNumber, productName)) {
            hoverOnElement(xpathBtnShipping);
            assertThat(isElementVisible(xpathMessage, 5)).isEqualTo(true);
        }
    }

    public boolean isAwaitingOrder() {
        String tabNameActive = getTextValue("//nav[@class='s-tabs-nav']//li[@class='is-active']//a");
        return tabNameActive.contains("Awaiting Orders");
    }

    public boolean isOrderMapped(String orderNumber, String status) {
        String xpath = BLOCK_ORDER_DETAIL + "//span[text()='" + status + "']";
        return isElementVisible(String.format(xpath, orderNumber), 5);
    }

    public void verifyMappingStatusColor(String orderNumber, String status, String expColor) {
        String xpath = BLOCK_ORDER_DETAIL + "//span[text()='%s']";
        verifyColor(String.format(xpath, orderNumber, status), "color", expColor);
    }

    public void verifyOrderStatus(String orderNumber, String orderStatus) {
        String expColor = "", expStatus = "";
        if (orderStatus.contains(">")) {
            expStatus = orderStatus.split(">")[0];
            expColor = orderStatus.split(">")[1];
        } else if (orderStatus.equals("Awaiting Payment")) {
            expStatus = "Awaiting Payment";
            expColor = "#d49502";
        } else if (orderStatus.equals("Failed")) {
            expStatus = orderStatus;
            expColor = "#525c64";
        }
        if (!expStatus.isEmpty() && !expColor.isEmpty()) {
            String xpath = "(" + String.format(BLOCK_ORDER_DETAIL, orderNumber) + "//span[normalize-space()='" + expStatus + "'])[1]";
            assertThat(isElementVisible(xpath, 5)).isEqualTo(true);
            verifyColor(xpath, "color", expColor);
        }
    }

    public String getProductAliInfo(String orderNumber, String name, String productName) {
        String xpath = "(//a[normalize-space()='%s']/following::*[text()[normalize-space()=\"%s\"]]/following::div[@class='product-ali-info']//p[text()='%s']//following-sibling::p[1])[1]";
        return getTextValue(String.format(xpath, orderNumber, productName, name));
    }

    private String xPathAliOrderInfo(String parentXpath, String orderName, String prodName) {
        return "(" + "//span[contains(@class,'order-name')]//a[contains(text(),'" + orderName + "')]/following::a[normalize-space()='" + prodName + "']";
    }

    public String getAliOrderInfo(String orderNumber, String orderStatus, String productName, String criteria) {
        if (orderStatus.equalsIgnoreCase("Awaiting Order")) {
            return getText("(//span[contains(@class,'order-name')]//a[contains(text(),'" + orderNumber + "')]/following::*[normalize-space()='" + productName + "']/following::*[normalize-space()='" + criteria + ":']/following-sibling::p)[1]");
        }
        return getText("(//span[contains(@class,'order-name')]//a[contains(text(),'" + orderNumber + "')]/following::*[normalize-space()='" + productName + "']/following::*[normalize-space()='" + criteria + ":']/following-sibling::a)[1]");
    }

    final String xpathWarningAddress = BLOCK_ORDER_DETAIL + "//span[@data-label ='%s']";

    public void verifyShippingAddress(String orderNumber, String msg, boolean isDisplay) {
        boolean isValidShippingAddress = isElementVisible(String.format(xpathWarningAddress, orderNumber, msg), 5);
        assertThat(isValidShippingAddress).isEqualTo(isDisplay);
    }

    public boolean isMissingShippingAddress(String orderNumber, String msg) {
        return isElementVisible(String.format(xpathWarningAddress, orderNumber, msg), 5);
    }

    public void verifyWarningOrder(String orderNumber, String warningMessage) {
        String xpath = "(//a[normalize-space()='%s']//following::div[@class='order-note text-red'])[1]";
        String expWarning = getTextValue(String.format(xpath, orderNumber));
        assertThat(expWarning.equals(warningMessage));
    }

    public float getShippingCost(String orderNumber) {
        String xpath = "(//a[normalize-space()='%s']//following::span[contains(text(),'Shipping Cost')]//span)[1]";
        String shippingValue = getTextValue(String.format(xpath, orderNumber)).replace("$", "");
        return Float.valueOf(shippingValue);
    }

    String xpathInput = "//label[text()='%s']//parent::div//following-sibling::div//input";

    public void enterValueAndVerifyMessageInAddressPopup(String label, String value, String expectMsg) {
        clearTextFieldUsingBackSpaceKey(String.format(xpathInput, label)); // clear text method in common not working for this case
        enterInputFieldWithLabel(label, value);
        String xpathMessage = "//label[text()='" + label + "']//parent::div//following-sibling::div//div[@class='s-form-item__error']";
        verifyElementText(String.format(xpathMessage, label), expectMsg);
    }

    public void clearTextFieldUsingBackSpaceKey(String _xPath) {
        while (!getTextValue(_xPath).isEmpty()) {
            $(_xPath).sendKeys(Keys.BACK_SPACE);
        }
    }

    public void selectTabName(String tabName) {
        String xpath = "//*[contains(@class,'tabs-nav')]//li//span[contains(text(),'" + tabName + "')]";
        clickOnElement(xpath);
        waitElementToBeVisible("//div[@id='app-list']//div[contains(@class,'table-responsive')]", 60);
    }

    public void openShippingAddressPopup(String orderNumber) {
        clickOnElement("(//tr[descendant::*[text()[normalize-space()='" + orderNumber + "']]]//div[@class='order-icons']//i)[1]");
    }

    public void submitPopup() {
        clickOnElement("//div[@class='s-modal-footer']//button[normalize-space()='Save']");
    }

    public void submitPopupShipping(int resOrder) {
//        String xPath = "( //div[@class='shipping-method-select']//*[text()[normalize-space()='Save']])";
//        int count = 0;
//        count = countElementByXpath(xPath);
//        for (int i = 1; i <= count; i++) {
//            String _xPath = xPath + "[" + i + "]";
//            if (isElementVisible(_xPath, 1)) {
//                clickOnElement(_xPath);
//                i = count;
//            }
//        }
        String saveBtn = xPathBtn("", "Save", resOrder);
        if (isElementVisible(saveBtn, 7)) {
            clickOnElement(saveBtn);
        }
    }

    public void clickButtonPlaceOnOrder(String orderNumber) {
        String xpath = "(//a[normalize-space()='" + orderNumber + "']/following::*[@class='order-summary']//span[text()[normalize-space()='Order']])";
        clickOnElement(xpath);
    }

    String xpathCol = "//tfoot//td[@class='col-%s']";

    String xpathItemValid = "//span[@class='type--normal'][not(text()[normalize-space()='Shipping Method'])]//ancestor::div[@class='product-shipping-info']//preceding-sibling::div[@class='product-info']";

    public float getIncomeOnPlaceOrder() {
        String xpathVariant = "//p[not(@class='product-price')]";
        List<String> lstProductName = getListProductNameValid(false);
        List<String> lstVariant = getListText(xpathItemValid + xpathVariant);

        float incomeItem = 0;
        for (int i = 0; i < lstProductName.size(); i++) {
            float item = Float.parseFloat(productListAdded.get(lstProductName.get(i).toUpperCase() + ":" + lstVariant.get(i)).get(1).replace("$", ""));
            incomeItem = incomeItem + (item);
        }
        return incomeItem;

    }

    public void verifyIncomeOnPlaceOrder(String colName, float expect) {
        verifyElementText(String.format(xpathCol, colName), "$" + expect);
    }

    /**
     * Get Product name of items which selected shipping service
     *
     * @author Van
     */
    public List<String> getListProductNameValid(boolean isAfterPlace) {
        String xpath = "//a";
        if (isAfterPlace)
            xpathItemValid = "//ancestor::div[@class='product-shipping-info']//preceding-sibling::div[@class='product-info']//a";
        return getListText(xpathItemValid);
    }

    public void verifySumDataOnPlaceOrder(String colName, String expect) {
        verifyElementText(String.format(xpathCol, colName), expect);
    }

    public String getDataBeforePlaceOrder(String value) {
        return price(getTextValue("//div[@class='order-summary']//span[text()[normalize-space()='" + value + "']]//span"));
    }


    public void checkAndSelectShippingMethod(String orderNumber, String shippingName, String producName) {
        String xpath;
        if (!shippingName.isEmpty()) {
            xpath = String.format("(//a[text()[normalize-space()=\"%s\"]]//ancestor::div[@class='product-info']//following-sibling::div[@class='product-shipping-info']//button)[last()]", producName);
            clickOnElement(xpath);
            selectShipping(shippingName);
        } else {
            xpath = "//button[not(@disabled='disabled')]//span[@class='type--normal'][text()[normalize-space()='Shipping Method']]";
            clickOnElement(String.format(xpath, orderNumber) + "[1]");
            selectShipping("");
        }

    }

    public List<String> countServiceShiping(String producName) {
        String btnShipping = String.format("(//a[text()[normalize-space()='%s']]//ancestor::div[@class='product-info']//following-sibling::div[@class='product-shipping-info']//button)[last()]", producName);
        String serviceName = "//div[@class='shipping-method-select']//span[@class='s-control-label']";
        clickOnElement(btnShipping);
        List<String> lstServiceName = getListText(serviceName);
        lstServiceName.removeAll(Collections.singleton(""));
        return lstServiceName;
    }

    public void selectShipping(String shippingName) {
        String xpathShippingValue = "(//div[@class='shipping-method-select']//span[normalize-space()='%s'])[last()]";
        int resOrder = 1;
        if (!shippingName.isEmpty()) {
            if (shippingName.equalsIgnoreCase(emsShipping)) {
                clickOnElement(String.format(xpathShippingValue, emsShipping));
            } else if (shippingName.equalsIgnoreCase(epacketShipping)) {
                clickOnElement(String.format(xpathShippingValue, epacketShipping));
            } else if (shippingName.equalsIgnoreCase(dhlShipping)) {
                clickOnElement(String.format(xpathShippingValue, dhlShipping));
            }
            resOrder = 3;
        }
        submitPopupShipping(resOrder);
    }

    public void verifyPlaceOrderSuccess(List<String> orders) {
        String xpath = "//div[text()[normalize-space()='%s']]//parent::td//following-sibling::td//div[@class='revise']";
        waitUntilElementInvisible("//tbody//td[@class='col-result']//img", 10);
        for (int i = 0; i < orders.size(); i++) {
            assertThat(!isElementVisible(String.format(xpath, orders.get(i)), 3)).isEqualTo(true);
            ;
        }
    }

    public void verifyItemStaus(String status, int count) {
        String expStatus = status.split(">")[0];
        String expColor = status.split(">")[1];
        String xpath = "(//div[@class='product-shipping-info']//span[text()[normalize-space()='" + expStatus + "']])[%s]";
        for (int i = 0; i < count; i++) {
            assertThat(isElementVisible(String.format(xpath, i), 3)).isEqualTo(true);
            ;
            verifyColor(String.format(xpath, i), "color", expColor);
        }
    }

    public void checkOrderExistInListTab(String orderNumber) {
        searchOrderInListADC(orderNumber);
        String xpathParent = "//div[@class='empty-state']";
        assertThat(!isElementVisible(xpathParent, 5)).isEqualTo(true);
        ;
    }


    public void searchThenSelectOrderNumber(List<String> listOrder) {
        waitClearAndTypeThenEnter(xPathInputFieldWithLabel("", "Search orders", 1), "");
        waitABit(10000);
        for (String item : listOrder) {
            String xpath = "//tr[descendant::*[text()[normalize-space()='" + item + "']]]//span[@class='s-check']";
            String xpathStatus = "//tr[descendant::*[text()[normalize-space()='" + item + "']]]//input";
            verifyCheckedThenClick(xpathStatus, xpath, true);
        }
    }

    public void searchThenSelectOrderNumber(String orderNumber) {
        waitClearAndTypeThenEnter(xPathInputFieldWithLabel("", "Search orders", 1), "");
        waitForEverythingComplete(10000);
        String xpath = "//tr[descendant::*[text()[normalize-space()='" + orderNumber + "']]]//span[@class='s-check']";
        String xpathStatus = "//tr[descendant::*[text()[normalize-space()='" + orderNumber + "']]]//input";
        verifyCheckedThenClick(xpathStatus, xpath, true);
    }

    public void selectOption(String option) {
        String xpath = "//span[text()[normalize-space()='" + option + "']]";
        clickOnElement(xpath);
    }

    public void uncheckOrderInPO(String order) {
        String xpath = "(//td//div[text()[normalize-space()='" + order + "']]//preceding::td//span[@class='s-check'])[last()]";
        String xpathStatus = "(//td//div[text()[normalize-space()='" + order + "']]//preceding::td//input)[last()]";
        waitABit(5000);
        verifyCheckedThenClick(xpathStatus, xpath, false);

    }

    public boolean isMissingShipping(String productName) {
        String xpath = "(//a[normalize-space()=\"" + productName + "\"]//following::div[@class='product-shipping-info']//button)[last()]";
        if (getTextValue(xpath).equals("Shipping Method"))
            return true;
        else
            return false;
    }

    public void saveShippingAddress(boolean isSaved) {
        String xPath = "//div[contains(@class,'s-modal-header')]//*[normalize-space()='Edit shipping address']/following::div[contains(@class,'modal-footer')]//span[normalize-space()='%s']";
        String btn = "";
        if (isSaved) {
            btn = String.format(xPath, "Save");
        } else {
            btn = String.format(xPath, "Cancel");
        }
        scrollIntoElementView(btn);
        clickOnElement(btn);
        waitForEverythingComplete();
    }

    public void verifyErrorMsgDisplayed(String msg) {
        String xpath = "//div[contains(@class,'s-form-item is-error')]//div[text()[normalize-space()=\"" + msg + "\"]]";
        verifyElementPresent(xpath, true);
    }

    public void verifyToastMsgDisplayed() {
    }

    public String getProductByOrderNumber(String orderNumber) {
        return getText("(//span[contains(@class,'order-name')]//a[contains(text(),'" + orderNumber + "')]/following::div[@class='product-details']//span)[1]");
    }

    public String getProductCost(String orderNumber, String prodVar) {
        String prodName = prodVar.split(":")[0];
        String varName = prodVar.split(":")[1];
        String prodCost = getText("(//span[contains(@class,'order-name')]//a[contains(text(),'" + orderNumber + "')]/following::*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + prodName.toLowerCase() + "')]/following::*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + varName.toLowerCase() + "')]/following-sibling::*[@class='product-price']/span)[1]");
        if (prodCost.equals("-")) {
            return prodCost;
        }
        return price(prodCost);
    }

    public String getProductCost(String orderNumber, String prodName, String varName) {
        return getText("(//span[contains(@class,'order-name')]//a[contains(text(),'" + orderNumber + "')]/following::*[normalize-space()='" + prodName + "']/following::p[@class='product-price']//span)[1]");
    }

    // get row position by order name
    public int indexOrder(String orderName) {
        int index = 1;
        String actualOrderName = "";
        while (index <= 20) {
            actualOrderName = getText("//tbody//tr[" + index + "]//td//a");
            if (actualOrderName.equals(orderName))
                break;
            index++;
        }
        return index;
    }

    // get column position by header
    public int indexColumn(String header) {
        int index = 1;
        String actualHeader = "";
        while (index <= 7) {
            actualHeader = getText("(//thead//tr//th[" + index + "]//span)[1]");
            if (actualHeader.equalsIgnoreCase(header))
                break;
            index++;
        }
        return index;
    }

    public String getOrderInfo(String orderNumber, String header) {
        int indexOder = indexOrder(orderNumber);
        int indexColumn = indexColumn(header);
        return getText("//tbody//tr[" + indexOder + "][@class='is-expanding']//td[" + indexColumn + "]");
    }

    public String getOrderInfoOnPopup(String orderNumber, String header) {
        String attribute = "";
        String value = "";
        switch (header) {
            case "Income":
                attribute = "col-income";
                break;
            case "Product Cost":
                attribute = "col-product-cost";
                break;
            case "Country":
                attribute = "col-country";
                break;
            case "Shipping Method":
                value = getText("//td[div[text()[normalize-space()='" + orderNumber + "']]]/following-sibling::td[@class='col-shipping-method']//span[text()]");
                return value;
            case "Shipping fee":
                attribute = "col-shipping-fee";
                break;
            case "Estimated shipping time":
                attribute = "col-estimated-shipping-time";
                break;
            default:
                fail();
        }
        value = getText("//td[div[text()[normalize-space()='" + orderNumber + "']]]/following-sibling::td[@class='" + attribute + "']//div");
        return value;
    }


    public void selectShippingMethodByProductName(String orderNumber, String prodName, String shippingMethod) {
        String iconDropDown = "(//a[text()[normalize-space()='" + orderNumber + "']]/following::a[text()[normalize-space()='" + prodName + "']]/following::*[@class='product-shipping-info']//i)[1]";
        String shippingCarrierList = "(//div[@class='shipping-method-select']//span[text()[normalize-space()='" + shippingMethod + "']])[%d]";
        String shippingCarrier = "//div[@class='shipping-method-select']//span[text()[normalize-space()='" + shippingMethod + "']]";
        String shippingCarrierIsVisible = "";
        int count = countElementByXpath(shippingCarrier);

        // in case the specific shipping not existing, select another
        clickOnElement(iconDropDown);
        waitUntilInvisibleLoading(10);
        for (int i = 1; i <= count; i++) {
            shippingCarrierIsVisible = String.format(shippingCarrierList, i);
            if (isElementVisible(shippingCarrierIsVisible, 7)) {
                clickOnElement(shippingCarrierIsVisible);
                break;
            }
        }
    }

    public void clickOnSaveBtnPopUp() {
        String saveBtnList = "//button[preceding-sibling::*[text()[normalize-space()='Save']] or descendant-or-self::*[text()[normalize-space()='Save']]]";
        String saveBtnOnPU = "(//button[preceding-sibling::*[text()[normalize-space()='Save']] or descendant-or-self::*[text()[normalize-space()='Save']]])[%d]";
        String clickableSave = "";

        int count = countElementByXpath(saveBtnList);
        for (int i = 1; i <= count; i++) {
            clickableSave = String.format(saveBtnOnPU, i);
            if (isElementVisible(clickableSave, 7)) {
                clickOnElement(clickableSave);
                break;
            }
        }
    }

    public boolean isAvailableShippingCarrier(String shippingMethod) {
        return true;
    }

    public void markAsFulfilledItem(String btnName, String prodName) {
        clickOnElement("(//div[@class='product-details']//p[normalize-space()='" + prodName + "']/following::button//span[text()[normalize-space()='" + btnName + "']])[1]");
    }

    public void clickOnTheOrderName(String orderNumber) {
        String orderName = "(//tr[@class='is-expanding']//a[text()[normalize-space()='" + orderNumber + "']])|(//a[text()[normalize-space()='" + orderNumber + "']])";
        waitUntilElementVisible(orderName, 8);
        clickOnElement(orderName);
    }

    public String getDataByLabel(String field) {
        String inputData = xPathInputFieldWithLabel("", field, 1);
        return getTextValue(inputData);
    }
}
