package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.fulfillment;

import common.BFlowPageObject;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class MyOrdersPage extends BFlowPageObject {
    public int MAX_RETRY_TIME = 10;

    public MyOrdersPage(WebDriver driver) {
        super(driver);
    }

    String xpathOderDetail = "(//div[contains(@class,'list-order')]//div[contains(@class,'order-detail')])[%d]";


    public int countOrderSearched() {
        return countElementByXpath(String.format(xpathOderDetail, 1));
    }

    public void clickBtnFulfillOrder() {
        String xpath = "//button//span[normalize-space()='Re-fulfill order']";
        if (isElementExist(xpath)) {
            clickBtn(String.format(xpathOderDetail, 1), "Re-fulfill order", 1);
        } else {
            clickBtn(String.format(xpathOderDetail, 1), "Fulfill order", 1);
        }
    }

    public void waitFulfillDone() {
        waitUntilElementVisible("//button[child::span[text()='Close']]", 30);
    }

    public String getTheFirstOrderNumber() {
        return getText(String.format(xpathOderDetail, 1) + "//div[contains(@class,'order-no')]//span");
    }

    public boolean isOrderExist(String orderNumber) {
        String xpath = String.format(xpathOderDetail, 1) + "//div[contains(@class,'order-no')]/span[text()='" + orderNumber + "']";
        return isElementExist(xpath);
    }

    public void verifyOrderExist(String orderNumber, boolean isExist) {
        assertThat(isOrderExist(orderNumber)).isEqualTo(isExist);
    }

    public int getLineItem(String productName, String variant) {
        int index = 0;
        String xpath;
        if (!variant.isEmpty()) {
            xpath = String.format(xpathOderDetail, 1) + "//*[@class='products']/div[ descendant-or-self::*[contains(text(),'" + productName + "')] and descendant-or-self::*[contains(text(),'" + variant + "')]]";
        } else {
            xpath = String.format(xpathOderDetail, 1) + "//*[@class='products']/div[ descendant-or-self::*[contains(text(),'" + productName + "')]]";
        }
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::div") + 1;
        }
        return index;
    }

    String xpathProductInOrder = "(" + String.format(xpathOderDetail, 1) + "//*[@class='products']//div[@class='product'])[%d]";

    public void verifyProductDetail(String label, String value, int index) {
        String xpath = String.format(xpathProductInOrder, index) + "//div[@class='product-container']//*[text()[normalize-space()='" + label + "']]//span";
        verifyElementText(xpath, value);
    }

    public String verifyShippingName(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'shipping-cost-estimate')])[" + index + "]//b";
        String actShippingName;
        if (isElementExist(xpath)) {
            actShippingName = getText(xpath);
        } else {
            actShippingName = getText("(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'name-shipping')])[" + index + "]");
        }
        return actShippingName;
    }

    public void verifyStatusOrder(String status, int index) {
        List<String> expstatus = Arrays.asList(status.split(","));
        String xpath = String.format(xpathProductInOrder, index) + "//*[contains(@class,'order-status')]/span";
        int i = 1;
        while (!getListText(xpath).containsAll(expstatus)) {
            waitABit(5000);
            refreshPage();
            i++;
            if (i == 10) {
                break;
            }
        }

        assertThat(getListText(xpath)).containsAll(expstatus);
    }

    public void clickMapProduct(int index) {
        clickBtn(String.format(xpathProductInOrder, index), "Map Product|Change mapping", 1);
    }

    public String getCrossPandaOrder(int index) {
        return getText("(" + String.format(xpathProductInOrder, index) + "//div[contains(@class,'order-track')]//*[preceding-sibling::*[text()='CrossPanda order']]");
    }

    String sectionMapping = "(//div[contains(@class,'section-current-mapping')]//div[@class='sub-head-row'])[%d]";

    public void selectOption(String option, int index) {
        int i = 1;
        String xpath = String.format(sectionMapping, index) + "//div[@class='input-head']";
        selectDdlCrossPanda(xpath, option);
        while (!isSelectedOption(option, index)) {
            hoverOnElement("");
            selectDdlCrossPanda(xpath, option);
            i++;
            if (i == 5) {
                break;
            }
        }
    }

    public boolean isSelectedOption(String option, int index) {
        String xpath = "(//div[contains(@class,'section-current-mapping')]//div[@class='sub-head-row'])[" + index + "]//div[@class='input-head']//div[@title='" + option + "']";
        return isElementExist(xpath);
    }

    public void selectValueOption(String values, int index) {
        String vl[] = values.split(",");
        for (int i = 0; i < vl.length; i++) {
            String xpath = "(" + String.format(sectionMapping, index) + "//div[@class='input-element']/div[contains(@class,'ant-select')])[" + (i + 1) + "]";
            selectDdlCrossPanda(xpath, vl[i]);
            int time = 1;
            while (!isSelectedOptionValue(values, index, i)) {
                selectDdlCrossPanda(xpath, vl[i]);
                time++;
                if (time == 5) {
                    break;
                }
            }
        }
    }

    public boolean isSelectedOptionValue(String option, int index, int i) {
        String xpath = "((//div[contains(@class,'section-current-mapping')]//div[@class='sub-head-row'])[" + index + "]//div[@class='input-element']/div[contains(@class,'ant-select')])[" + (i + 1) + "]//div[contains(@class,'row-select-variant')]//p[text()='" + option + "']";
        return isElementExist(xpath);
    }

    public void verifyOption_ProductMapping(String option, int index) {
        String xpath = String.format(sectionMapping, index) + "//div[@class='input-head']";
        verifyElementText(xpath, option);
    }

    public void verifyValueOption_ProductMapping(String values, int index) {
        String vl[] = values.split(",");


        for (int i = 0; i < vl.length; i++) {
            String xpath = "(" + String.format(sectionMapping, index) + "//div[@class='input-element']/div[contains(@class,'ant-select')])[" + (i + 1) + "]";
            verifyElementText(xpath, vl[i]);
        }
    }

    public void verifyProductMappingExisted(boolean isExist) {
        verifyElementPresent(String.format(sectionMapping, 1), isExist);
    }

    public void selectDdlCrossPanda(String xpathParent, String value) {
        if (!value.isEmpty()) {
            String xpath1 = xpathParent + "//div[@role='combobox']";
            String xpath2 = xpathParent + "//div[@class='ant-select-selection__rendered']";
            String xpathList = "//div[contains(@class,'ant-select-dropdown-placement') and not(contains(@style,'display: none'))]//ul[@role='listbox']";

            try {
                waitForElementFinishRendering(xpath1);
                XH(xpath1).waitUntilClickable();
                clickOnElement(xpath1);
                waitElementToBeVisible(xpathList);
            } catch (Throwable t) {
                waitForElementFinishRendering(xpath2);
                XH(xpath2).waitUntilClickable();
                clickOnElementByJs(xpath2);
                waitElementToBeVisible(xpathList);
            }

            String xpathValue = "//div[contains(@class,'ant-select-dropdown ant-select-dropdown--single')][not(contains(@style,'display: none'))]//ul/li[descendant-or-self::*[normalize-space()='" + value + "']]";

            try {
                XH(xpathValue).waitUntilClickable();
                waitForElementFinishRendering(xpathValue);
                clickOnElement(xpathValue);
            } catch (Throwable t) {
                XH(xpathValue).waitUntilClickable();
                waitForElementFinishRendering(xpathValue);
                clickOnElementByJs(xpathValue);
            }
        }
    }

    String sectionPlatformProduct = "(//div[contains(@class,'section-platform-product')]//div[@class='sub-head-row'])[%d]";

    public void verifyValueOption_SBProduct(String values, int index) {
        String vl[] = values.split(",");
        for (int i = 0; i < vl.length; i++) {
            String xpath = "(" + String.format(sectionPlatformProduct, index) + "//div[@class='sub-element'])[" + (i + 1) + "]";
            verifyElementText(xpath, vl[i]);
        }
    }

    public void verifyOption_SBproduct(String option, int index) {
        String xpath = String.format(sectionPlatformProduct, index) + "//div[@class='sub-head']";
        verifyElementText(xpath, option);
    }

    public boolean isMappingPage() {
        return isElementExist("//div[@id='product-mapping']");
    }


    public void verifyShopbaseProductExisted(boolean isExist) {
        verifyElementPresent(String.format(sectionPlatformProduct, 1), isExist);
    }


    public void verifyMessage(List<String> messageError) {
        String xpath = "//*[contains(@class,'p-warning') or @class='ant-notification-notice-message' or @class='ant-alert-message' or @class='ant-message-notice']";
        List<String> msg = getListText(xpath);
        assertThat(msg).containsAll(messageError);
    }

    @Step
    public void selectShop(String shop) {
        String xpath = "//section[@id='order-page']//div[contains(@class,'ant-select-enabled')]//div[@role='combobox']";
        String xpathLoading = "//div[text()='Loading...']";
        waitElementToBeVisible(xpath);
        waitForElementFinishRendering(xpath);

        try {
            selectDdlCrossPanda(xpath, shop);
            waitABit(1000);
            assertThat(getText(xpath)).isEqualTo(shop);
            waitForElementNotAppear(xpathLoading);
        } catch (Throwable t) {
            String xpathInner = "//li[@role='option' and normalize-space()='" + shop + "']";
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click()", findBy(xpathInner));
            waitABit(1000);
            assertThat(getText(xpath)).isEqualTo(shop);
            waitForElementNotAppear(xpathLoading);
        }
    }


    public void loadFile(String csvFile) {
        String xpath = "(//input[@type='file'])[1]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, csvFile);
    }

    public int countLineItemInPreview() {
        String xpath = "//div[@class='table-file-detail']//table/tbody/tr";
        return countElementByXpath(xpath);
    }

    public List<String> getOrderInformation(int i) {
        String xpath = "(//div[@class='table-file-detail']//table/tbody/tr)[" + i + "]//td";
        return getListText(xpath);
    }

    public void selectProductIndentity(String productIdentity) {
        String xpathParent = "//div[@class='select-option']//label[child::*[text()='" + productIdentity + "']]";
        String _objectStatus = "(" + xpathParent + "//input[@type='radio'])[1]";
        String _objectClick = "//div[@class='select-option']//span[text()='" + productIdentity + "']";
        verifyCheckedThenClick(_objectStatus, _objectClick, true);
    }

    public void checkAllOrdersImported() {
        String _parentXpath = "//div[@class='select-option-bar m-t']";
        String xPathStatus = "(" + _parentXpath + "//input[@type='checkbox'])[1]";
        String xPathCheckbox = "(" + _parentXpath + "//span[@class='ant-checkbox-inner'])[1]";
        boolean isStatus = waitForElementToPresent(xPathStatus).isSelected();
        System.out.println(isStatus);
        if (!isStatus) {
            clickOnElementByJs(xPathCheckbox);
        }
    }

    public void selectAction(String action) {
        String xpathValue = "//div[contains(@class,'ant-dropdown ant-dropdown-placement-bottomLeft')]//ul//li[contains(text(),'" + action + "')]";
        if (isEnabled(xpathValue)) {
            clickOnElement(xpathValue);
            confirm(action);
        }
    }

    private boolean isEnabled(String xpathValue) {
        boolean isE = true;
        if (getAttributeValue(xpathValue, "class").contains("disabled")) {
            isE = false;
        }
        return isE;
    }

    public void confirm(String action) {
        waitForElementToPresent("//div[@class='ant-modal-content']");
        if (action.contains("Delete")) {
            clickBtn("Delete");
            String xpath = "//button[child::span[text()='Delete']]";
            waitABit(5000);
            waitForElementNotAppear(xpath);
        } else {
            clickBtn("Confirm");
            waitABit(10000);
//            clickBtn("Close");
        }
        waitForEverythingComplete();
    }

    public boolean isEnable(String value) {
        String xpathValue = "//div[contains(@class,'ant-dropdown ant-dropdown-placement-bottomLeft')]//ul//li[text()='" + value + "']";
        return XH(xpathValue).isEnabled();
    }

    public boolean isPageEmpty() {
        return isElementExist("//div[@class='empty-state']");
    }

    public int countPage() {
        String xpath = "(//ul[@class='ant-pagination']//li[following-sibling::li[@title='Next Page']])[last()]";
        String page = getText(xpath);
        return Integer.parseInt(page);
    }

    public void selectPage(int i) {
        String xpathPagination = "//ul[@class='ant-pagination']";
        scrollIntoElementView(xpathPagination);
        String xpathPage = "(//ul[@class='ant-pagination']//li[descendant-or-self::*[text()='" + i + "']])";
        clickOnElement(xpathPage);
        waitForPageLoad();
        waitUntilInvisibleLoading(10);
    }

    public String getProductNameSB(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::p[contains(@class,'title')]][1]";
        return getText(xpath);
    }

    public String getVariantSB(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::p[contains(text(),'Variant')]][1]//span";
        return getText(xpath);
    }

    public String getXpandaSKUOdoo(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::p[contains(text(),'SKU')]][2]//span";
        return getText(xpath);
    }

    public void searchReplaceProduct(String replaceProduct) {
        enterInputFieldWithLabel("Search by product name", replaceProduct);
        String xpath = "//li[@role='menuitem']//p[text()[normalize-space()='" + replaceProduct + "']]";
        clickOnElement(xpath);
    }

    public void clickValueOption(String var) {
        String[] vars = var.split(">");
        String xpath = "//div[text()[normalize-space()='" + vars[0] + ":']]//ancestor::div[contains(@class,'product-detail')]//button[child::span[text()='" + vars[1] + "']]";
        clickOnElement(xpath);
    }

    public String getProductNameOdoo(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::p[contains(@class,'title')]][2]";
        return getText(xpath);
    }

    public String getVariantOdoo(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::p[contains(text(),'Variant')]][2]//span";
        return getText(xpath);
    }

    public String getStatusOrderLine(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'order-status')]//span)[" + index + "]";
        return getText(xpath);
    }

    public void verifyCountLineItem(String orderNumber, int lineitem) {
        String xpath = "//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[@class='product']";
        int size = countElementByXpath(xpath);
        assertThat(size).isEqualTo(lineitem);
    }

    public String getSKUSB(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::p[contains(text(),'SKU')]][1]//span";
        return getText(xpath);
    }

    public String getQuantitySB(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::div[contains(text(),'Quantity')]][1]//span";
        return getText(xpath);
    }

    public String getQuantityOdoo(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[child::div[contains(text(),'Quantity')]][2]//span";
        return getText(xpath);
    }

    public void verifyButonExist(String productName, String variant, String inbutton) {
        String xpath = "//span[text()[normalize-space()='" + productName + "']]//ancestor::div[contains(@class,'product-detail')]//span[text()='" + variant + "']//ancestor::div[contains(@class,'order-detail')]//button//span[text()[normalize-space()='" + inbutton + "']]";
        verifyElementPresent(xpath, true);
    }

    public void selectOrder(String orderNumber, Boolean select) {
        String xpath = "//div[child::*[text()='" + orderNumber + "']]//preceding-sibling::label/span[contains(@class,'ant-checkbox')]";
        String xpathStatus = "//div[child::*[text()='" + orderNumber + "']]//preceding-sibling::label//input";
        if (isElementVisible(xpath, 10)) {
            waitElementToBeVisible(xpath);
            verifyCheckedThenClick(xpathStatus, xpath, select);
        }
    }

    public void clickAction() {
        String xpath = "//span[text()='Actions']//parent::button";
        clickOnElement(xpath);
    }

    public void clickMarkAsFulfilled() {
        String xpathAction = "//ul[contains(@class,'ant-dropdown-menu')]//li[text()='Mark as fulfilled']";
        String xpathBtnConfirm = "//span[text()='Confirm']//parent::button";
        clickOnElement(xpathAction);
        clickOnElement(xpathBtnConfirm);
    }

    public void clickBtnCancelFulfillment(String orderNumber, int index) {
        String xpathBtnCancelFulfillment = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//span[text()='Cancel fulfillment']//parent::button)[" + index + "]";
        String xpathBtnConfirm = "//span[text()='Confirm']//parent::button";
        clickOnElement(xpathBtnCancelFulfillment);
        clickOnElement(xpathBtnConfirm);
    }

    public boolean isMiniPage() {
        String xpath = "//span[@class='ant-tabs-tab-prev ant-tabs-tab-arrow-show']";
        return isElementExist(xpath);
    }

    public void clickIconPre() {
        String xpath = "//span[@class='ant-tabs-tab-prev ant-tabs-tab-arrow-show']//span[@class='ant-tabs-tab-prev-icon']";
        clickOnElement(xpath);
    }

    public void verifyHasOrderInStore() {
        String xpath = "//div[contains(@class,'order-detail')]";
        verifyElementPresent(xpath, true);
    }

    String xpathThefirstOderDetail = String.format(xpathOderDetail, 1);

    public void verifyCostInOrder(String label, String value) {
        String xpath = xpathThefirstOderDetail + "//div[text()[normalize-space()='" + label + "']]//span";
        Double cost = Double.parseDouble(getText(xpath).replace("$", ""));

        if (value.contains("~")) {
            assertThat(cost).isNotEqualTo(Double.parseDouble(value.replace("$", "").replace("~", "")));
        } else {
            assertThat(cost).isEqualTo(Double.parseDouble(value.replace("$", "")));
        }
    }

    public boolean isEnableBtnCustomerDetail() {
        return isElementExist(xPathBtn(xpathThefirstOderDetail, "Customer Detail", 1));
    }

    public String getCountry(String orderNumber) {
        String xpath = xpathThefirstOderDetail + "//div[contains(@class,'country')]//span";
        return getText(xpath);
    }

    public int getIndexLineItem(String ordernumber, String productName, String variantName) {
        if (!productName.isEmpty() && !variantName.isEmpty()) {
            String xpath = "//div[contains(@class,'order-detail')][descendant::*[text()='" + ordernumber + "']]//div[child::div[@class='product'][descendant::*[@title='" + productName + "'] and descendant::*[text()='" + variantName + "']]]/preceding-sibling::div";
            return countElementByXpath(xpath) + 1;
        } else {
            return 0;
        }
    }

    public String verifyShippingCost(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'shipping-cost-estimate')])[" + index + "]";
        return getText(xpath);
    }

    public String getTKN(String orderNumber, int index) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//div[@class='ali-tracking-no']//a)[" + index + "]";
        return getText(xpath);
    }

    public String getCustomerInfor(String orderNumber, String infor) {
        String xpath = "(//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')])//input[@id='" + infor + "']";
        return getValue(xpath);
    }

    public void verifyHasNotLineitemInApp(String orderNumber, String productNameSB) {
        verifyElementPresent("", false);
    }

    public void selectProduct(String Product) {
        String xpath = "//input[@class='ant-input' and @placeholder='Search by product name']";

        String[] infoProduct = Product.split(">");
        String nameProduct = infoProduct[0].trim();

        waitTypeAndEnter(xpath, nameProduct);
        hoverOnElement(xpath);
        String xpath_select = "//p[contains(normalize-space(),'" + nameProduct + "') and @class='prod-name m-l']";

        try {
            hoverOnElement(xpath);
            waitElementToBeVisible(xpath_select, 20);
        } catch (Throwable t) {
            hoverOnElement(xpath);
            waitElementToBeVisible(xpath_select, 30);
        }
        clickOnElement(xpath_select);

        for (int i = 1; i < infoProduct.length; i++) {
            String optionValue = infoProduct[i].trim();
            String option = "//div[@class='ant-row' and contains(.,'" + nameProduct + "')]//following-sibling::div//button[child::span[text()='" + optionValue + "']]";
            clickOnElement(option);
        }

    }

    public void verifySizeProduct(String sizePage) {
        String xpath_size = "//div[contains(@class,'ant-dropdown ant-dropdown-placement')]//li";
        int size = countElementByXpath(xpath_size);
        assertThat(size).isEqualTo(Integer.parseInt(sizePage));
        waitForEverythingComplete();
    }

    public void searchProductMap(String nameProduct) {
        String xpath = "//input[@class='ant-input' and @placeholder='Search by product name']";
        waitUntilElementVisible(xpath, 30);
        waitTypeAndEnter(xpath, nameProduct);
        waitABit(2000);
    }


    public void selectVariant(String xpathParent, String value, int index) {
        if (!value.isEmpty()) {
            String xpath = xpathParent + "//*[@class='ant-select-selection__rendered']";
            if (!getText(xpath).equals(value)) {
                clickOnElementByJs(xpath);
                String xpathValue = "(//div[contains(@class,'ant-select-dropdown ant-select-dropdown--single')]//p[normalize-space()='" + value + "'])[" + index + "]";
                waitUntilElementVisible(xpathParent, 10);
                clickOnElement(xpathValue);
            }
        }
    }

    public void clickReplaceAll() {
        String xpath = "//div[@class='vcenter ant-col ant-col-5']//input[@class='ant-checkbox-input']";
        clickOnElementByJs(xpath);
    }

    public void clickRadioProductName() {
        String xpath = "//span[normalize-space()='Product name']//preceding-sibling::span//input";
        clickOnElementByJs(xpath);
    }

    public boolean verifySaveButtonDisable() {
        String xpath = "//button[@class='btn-set ant-btn ant-btn-primary ant-btn-sm' and @disabled='disabled']";
        return isElementExist(xpath);
    }

    public void openCustomInfor() {
        clickOnElement("//i[contains(@class,'order-edit-icon-custom')]");
    }

    public void selectOdooProduct(String odooProduct) {
        String xpath = "//div[@class='left-detail' and contains(.,'" + odooProduct + "')]//following-sibling::div[@class='button-wrap']//button";
        if (XH(xpath).isEnabled()) {
            int i = 1;
            String xpathBody = "//div[@class='body-container']";
            while (!isElementExist(xpathBody)) {
                waitABit(10000);
                refreshPage();
                i++;
                if (i == 7) {
                    break;
                }
            }
            String xpathSearch = "//input[@placeholder='Search by product name']";
            waitTypeAndEnter(xpathSearch, odooProduct);
            clickBtn("Set");
        }
    }

    public void changeInforCS(String field, String orderNumber, String value) {
        String xpath = "//span[text()='" + orderNumber + "']//ancestor::div[contains(@class,'order-detail')]//input[@placeholder='" + field + "']";
        clickAndClearThenType(xpath, value);
    }

    public void inputStartTime(String startTime) {
        String xpath = "//span[@class='ant-calendar-range-picker-separator']//preceding-sibling::input[@class='ant-calendar-range-picker-input']";
        clickOnElement(xpath);
        String xpath_time = "//div[@class='ant-calendar-range-part ant-calendar-range-left']//input[@class='ant-calendar-input ']";
        waitTypeAndEnter(xpath_time, startTime);
    }

    public void inputEndTime(String endTime) {
        String xpath = "//span[@class='ant-calendar-range-picker-separator']//preceding-sibling::input[@class='ant-calendar-range-picker-input']";
        clickOnElement(xpath);
        String xpath_time = "//div[@class='ant-calendar-range-part ant-calendar-range-right']//input[@class='ant-calendar-input ']";
        waitTypeAndEnter(xpath_time, endTime);
    }

    public void importCSVFileWithData(String mappedProductOrder, String mappedProductOrderAS, String mappedProductOrderF, String unmapProductOrder, String data) throws IOException {
        String _filePath = LoadObject.getFilePath("xpanda_orders.csv");
        FileWriter fw = new FileWriter(_filePath);
        fw.write("ORDER DATE,REQUEST FULFILL DATE,SHIPPED DATE,TRACKING NUMBER,TRACKING URL,LAST MILE DELIVERY TKN,SHIPPING STATUS,ORDER ID,PRODUCT NAME,QUANTITY,SKU,XPANDA SKU,SHIPPING NAME,ADDRESS 1,ADDRESS 2,CITY,ZIP/POSTAL CODE,STATE/PROVINCE/REGION,COUNTRY,PHONE,DESIGN ID,MOCKUPS,ARTWORK/FRONT,ARTWORK/BACK,DESIGN CODE");

        Format f = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = f.format(new Date());

        String dataExpect = data;

        if (dataExpect.contains("@Date@")) {
            dataExpect = dataExpect.replace("@Date@", strDate);
        }
        if (dataExpect.contains("@OrderProductMappedImported@")) {
            dataExpect = dataExpect.replace("@OrderProductMappedImported@", mappedProductOrder);
        }
        if (dataExpect.contains("@OrderProductMappedImportedAS@")) {
            dataExpect = dataExpect.replace("@OrderProductMappedImportedAS@", mappedProductOrderAS);
        }
        if (dataExpect.contains("@OrderProductMappedImportedF@")) {
            dataExpect = dataExpect.replace("@OrderProductMappedImportedF@", mappedProductOrderF);
        }
        if (dataExpect.contains("@OrderProductUnmapImported@")) {
            dataExpect = dataExpect.replace("@OrderProductUnmapImported@", unmapProductOrder);
        }

        fw.write(dataExpect);

        fw.flush();
        fw.close();

        String xpath = "//header//input[@type='file']";
        chooseAttachmentFile(xpath, "xpanda_orders.csv");
    }

    public void clickBtnImport() {
        clickBtn("Import");
        String xpath = "//div[@class='ant-alert ant-alert-success']";
        waitElementToBeVisible(xpath);
    }

    public void searchOrderOnCrossPanda(String orderName) {
        String xpath = "//input[@placeholder='Search by Product name, order no, tracking number']";
        waitClearAndType(xpath, orderName);
        XH(xpath).sendKeys(Keys.ENTER);
        String xpathLoading = "//div[text()='Loading...']";
        waitForElementNotAppear(xpathLoading);
    }

    public void verifyStatusOfOrderOnCrossPanda(String orderName, String status, int currentRetryTime) {
        String xpath = "//div[@class='header' and descendant::span[normalize-space()='" + orderName + "']]//following-sibling::div[@class='content']//div[@class='order-status m-b-ssm']";
        String empty = "//div[@class='empty-state']";

        if ("Not exists".equals(status)) {
            waitElementToBeVisible(empty);
            verifyElementVisible(empty, true);
        } else {
            try {
                waitElementToBeVisible(xpath);
                assertThat(getText(xpath)).isEqualTo(status);
            } catch (Throwable t) {
                if (currentRetryTime < MAX_RETRY_TIME) {
                    waitABit(5000);
                    currentRetryTime++;
                    String iconSearch = "//i[contains(@class,'search')]";
                    clickOnElement(iconSearch);
                    verifyStatusOfOrderOnCrossPanda(orderName, status, currentRetryTime);
                } else {
                    System.out.println("Reached " + MAX_RETRY_TIME + " times, but it's still fail");
                    fail();
                }
            }
        }
    }

    public void verifyAbleToSelectActionDelete(String able) {
        String xpath = "//div[contains(@class,'ant-dropdown ant-dropdown-placement-bottomLeft')]//ul//li[contains(text(),'Delete orders')]";
        switch (able) {
            case "Able":
                assertThat(getAttributeValue(xpath, "aria-disabled")).isEqualTo(null);
                assertThat(getAttributeValue(xpath, "class")).isEqualTo("ant-dropdown-menu-item");
                clickBtn("Actions");
                break;
            case "Unable":
                assertThat(getAttributeValue(xpath, "aria-disabled")).isEqualTo("true");
                assertThat(getAttributeValue(xpath, "class")).isEqualTo("ant-dropdown-menu-item ant-dropdown-menu-item-disabled");
                clickBtn("Actions");
                break;
            default:
                fail();
        }
    }

    public void selectPaging(String paging) {
        String xpath = "//ul[@class='ant-pagination']";
        if (isElementVisible(xpath, 5)) {
            waitElementToBeVisible(xpath);
            scrollIntoElementView(xpath);
            String xpathCbx = "//li[@class='ant-pagination-options']//div[@role='combobox']";
            clickOnElement(xpathCbx);
            String xpathPaging = "//ul[@class='ant-pagination']//li[child::*[text()='" + paging + "']]";
            waitElementToBeVisible(xpathPaging);
            clickOnElement(xpathPaging);
            waitForPageLoad();
            waitForEverythingComplete(30);
            String xpathLoading = "//div[text()='Loading...']";
            waitForElementNotAppear(xpathLoading);
        }
    }

    public void clickBtnAction() {
        String xpath = "//button[child::span[text()='Actions']]";
        clickOnElement(xpath);
    }

    public void getTrackingNumber() {
        String xpath = "//ul[child::li[contains(text(),'Get tracking number')]]";
        String xpathConfirm = "(//button[child::span[text()='Confirm']])[2]";
        clickOnElement(xpath);
        clickOnElement(xpathConfirm);
    }

    public boolean isTrackingNumber(String orderNumber) {
        return isElementExist("");
    }

    public void verifyBaseProductName(String product) {
        String xpath = "//div[contains(@class,'section-platform-product')]//div[contains(@class,'feature-block')]//a";
        String actBaseProduct = getText(xpath);
        assertThat(actBaseProduct).isEqualTo(product);
    }

    public void verifyOptionBaseProduct(String option, int index) {
        String xpath = "(//div[contains(@class,'section-platform-product')]//div[@class='sub-head'])[" + index + "]";
        String actOption = getText(xpath);
        assertThat(actOption).isEqualTo(option);
    }

    public void verifyOptionValueBaseProduct(String optionValue, int index) {
        String act = "";
        if (optionValue.contains(",")) {
            String op3[] = optionValue.split(",");
            for (int i = 0; i < op3.length; i++) {
                String xpath = "(//div[contains(@class,'section-platform-product')]//div[@class='sub-head-row'])[" + index + "]//div[text()[normalize-space()='" + op3[i] + "']]";
                act = getText(xpath);
                assertThat(act).isEqualTo(op3[i]);
            }
        } else {
            String xpath = "(//div[contains(@class,'section-platform-product')]//div[@class='sub-head-row'])[" + index + "]//div[contains(text(),'" + optionValue + "')]";
            act = getText(xpath);
            assertThat(act).isEqualTo(optionValue);
        }
    }

    public void waitFulfillmentFinish() {
        String xpath = "//div[@class='containerProgress']";
        try {
            waitForElementNotAppear(xpath);
        } catch (Throwable t) {
            waitForElementNotAppear(xpath);
        }
    }

    public boolean isProductOdoo(String product, String odooProduct) {
        String xpath = "(//span[@title='" + product + "']//ancestor::div[@class='product']//span[@title='" + odooProduct + "'])[1]";
        return isElementExist(xpath);
    }

    public String getTheFirstOrderByProduct(String productName) {
        String xpath = "(//span[@title='" + productName + "']//ancestor::div[contains(@class,'order-detail')])[1]//div[contains(@class,'order-no')]//span";
        return getText(xpath);
    }

    public void clickBtnSaveMapping() {
        String xpathSave = "//button[@class='btn-save ant-btn ant-btn-primary']";
        String xpathSaving = "//button[@class='btn-save ant-btn ant-btn-primary ant-btn-loading']";
        clickOnElement(xpathSave);
        waitElementToBeVisible(xpathSaving);
        try {
            waitElementToBeVisible(xpathSave);
        } catch (Throwable t) {
            waitElementToBeVisible(xpathSave, 20);
        }
    }

    public void verifyMockup(String orderName, String mockup, int index) {
        String xpath = "(//span[text()='" + orderName + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[contains(text(),'Mockup')]//a";
        String act = getAttributeValue(xpath, "href");
        assertThat(act).isEqualTo(mockup);
    }

    public void verifyArtWork(String orderName, String artFront, int index, String position) {
        String xpath = "(//span[text()='" + orderName + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[contains(text(),'Artwork')]//a[text()='" + position + "']";
        String act = getAttributeValue(xpath, "href");
        assertThat(act).isEqualTo(artFront);
    }

    public void verifyDesigncode(String orderName, String designCode, int index) {
        String xpath = "(//span[text()='" + orderName + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[contains(text(),'Design code')]//span";
        String act = getText(xpath);
        assertThat(act).isEqualTo(designCode);
    }

    public void verifyBaseCost(String orderName, String baseCost, int index) {
        String xpath = "(//span[text()='" + orderName + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-detail')])[" + index + "]//div[contains(text(),'Base cost')]//span";
        String act = getText(xpath);
        assertThat(act).isEqualTo(baseCost);
    }

    public void verifySupplierName(String orderName, String supplierName, int index) {
        String xpath = "(//span[text()='" + orderName + "']//ancestor::div[contains(@class,'order-detail')]//div[contains(@class,'product-container')])[" + index + "]//div[contains(@class,'ant-select-selection__rendered')]//div";
        String act = getText(xpath);
        assertThat(act).isEqualTo(supplierName);
    }

    public String getTextOrder(String name,int index) {
        String xpath = "(//div[contains(@class,'list-order')]//*[contains(text(),'" + name + "')])[" + index + "]//span";
        return getText(xpath);
    }
    public String getTextStatus() {
        String xpath = "//div[contains(@class,'shipping')]//div[contains(@class,'order-status')]/span";
        return getText(xpath);
    }

    public void clickBTShipping() {
        String xpath = "//div[contains(@class,'name-shipping')]";
        focusClickOnElement(xpath);
        waitForEverythingComplete();
    }

    public void verifyInfoshippingName(String shippingName, String shippingCost, String trackingNumber, String deliveryTime) {
        String xpath = "//div[@class='row line-item']";
        String xpathSubInfo = "//div[@class='row line-item']//div";
        if (isElementExist(xpath)) {
            List<String> listInfo = getListText(xpath);
            List<String> subInfo = getListText(xpathSubInfo);
            for (int i = 0; i < listInfo.size(); i++) {
                assertThat(subInfo.get(0)).isEqualTo(shippingName);
                assertThat(subInfo.get(1)).isEqualTo(shippingCost);
                assertThat(subInfo.get(2)).isEqualTo(deliveryTime);
                assertThat(subInfo.get(3)).isEqualTo(trackingNumber);
            }
        } else {
            String xpathErr = "//p[contains(text(),'No Data')]";
            Assert.assertTrue(isElementExist(xpathErr));
        }
    }
    public void chooseVendor(String vendor) {
        String xpath = "//div[@class='ant-select ant-select-enabled']";
        focusClickOnElement(xpath);
        String xpathVendor = "//ul[@role='listbox']//li[contains(text(),'" + vendor + "')]";
        clickOnElement(xpathVendor);
        waitABit(3000);

    }
    public double getTotalSalesCurrent(){
        refreshPage();
        String xpath = "//h4[text()='Total sales']//parent::div//following::div//h2";
        return getPrice(xpath);
    }
}


