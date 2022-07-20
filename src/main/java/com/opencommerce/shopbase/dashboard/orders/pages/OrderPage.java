package com.opencommerce.shopbase.dashboard.orders.pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import common.SBasePageObject;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Java6Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static com.opencommerce.shopbase.OrderVariable.productName;
import static com.opencommerce.shopbase.OrderVariable.taxAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class OrderPage extends SBasePageObject {
    private int MAX_RETRY_TIME = 15;
    private String xpathSubject;
    private String subtotal = "";
    private String successMsg = "All changes were successfully saved";
    private String TEXTBOX_SEARCH = "(//input[@id='Search orders' or @type='Search orders' or @placeholder='Search orders' or @placeholder = 'Search by order name, transaction id' or @placeholder = 'Search by order name, transaction id, line item name' or contains(text(),'Search orders') or preceding-sibling::*[text()[normalize-space()='Search orders']] or preceding::*[text()[normalize-space()='Search orders']] or @name='Search orders'or preceding-sibling::*[text()[normalize-space()='Search orders']]])";

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void clickOrderName(String orderNumber) {
        String xpathOrderNumber = "//td//a[contains(., '" + orderNumber + "')]";
        try {
            waitElementToBeVisible(xpathOrderNumber);
            clickOnElement(xpathOrderNumber);
            waitForEverythingComplete();
        } catch (Exception e) {
            refreshPage();
            waitABit(4000);
            waitElementToBeVisible(xpathOrderNumber);
            clickOnElement(xpathOrderNumber);
            waitForEverythingComplete();
        }
    }

    public void clickBackToOrderList() {
        String xpath = "//ol[contains(@class, 's-breadcrumb')]//a[normalize-space()='Orders']";
        clickOnElement(xpath);
    }

    public void inputValueToSearchBarThenEnter(String value) {
        String xpathSearchBar = "//div[@class='order-search-form']//input[@placeholder='Search by order name, transaction id']";
        waitForEverythingComplete();
        waitClearAndTypeThenEnter(xpathSearchBar, value);
    }

    public void verifyStatus(String status) {
        try {
            verifyTextPresent(status, true);
        } catch (Throwable t) {
            verifyTextPresent(status, true);
        }
    }

    public void inputQuantityWithProduct(String productName, String variant, String quantity) {
        String xpath = "//a[normalize-space()='" + productName + "']/following::*[normalize-space()='" + variant + "']//ancestor::tr//input[@class='s-input__inner']";
        waitTypeOnElement(xpath, quantity);
    }


    public void verifyTimeLine(String s) {
        String xpath = "//div[contains(@class,'feed__section')]//span[contains(text(),'" + s + "')]";
        verifyElementVisible(xpath, true);
    }

    public void clickOnMarkAsFullfulledButton() {
        waitABit(3000);
        clickOnElement("//div[@id='fulfillment-section']//span[normalize-space()='Mark as fulfilled']");
        waitElementToBeVisible("//*[@class='title-bar__title' and normalize-space()='Fulfillment']");
    }

    public void selectViewOrderStatusPage() {
        clickOnElement("//div[@class='s-dropdown-menu']//span[normalize-space()='View order status page']");
        waitForEverythingComplete();
        switchToLatestTab();
        waitElementToBeVisible("//*[@class='section__content']//a");
    }

    public void inputShippingAmount(String shipping) {
        String xpath = "//tr[@class='summary-detail']//input";
        waitTypeOnElement(xpath, shipping + Keys.ENTER);
        waitForEverythingComplete();
        waitABit(2000);
    }

    public void clickOnMoreActionBtn() {
        String btnName = "//button//span[text()[normalize-space()='More actions']]";
        waitUntilElementVisible(btnName, 10);
        clickOnElementByJs(btnName);
        waitForEverythingComplete();
    }

    public void openCancelOrderPopup() {
        clickOnMoreActionBtn();
        clickOnElement("//div[@class='s-dropdown-menu']//span[contains(text(),'Cancel')]");
        waitForEverythingComplete();
    }

    public void confirmCancelOrder() {
        clickOnElement("//div[@id='modal-cancel-order']//span[contains(text(),'Cancel order')]");
        waitForPageLoad();
        waitForEverythingComplete();
    }

    public String getShippingAmount() {
        String shippingAmount = getText("//tr[@class='summary-detail']//td[contains(text(),'Shipping')]");
        String finalShipping = shippingAmount.replace("Shipping ($", "").replace("remaining)", "").trim();
        return finalShipping;
    }

    public String getTransactionID() {
        String authorizationCapture = "";
        String currentURL = getCurrentUrl();
        String[] lengthArr = currentURL.split("/");
        String orderID = lengthArr[lengthArr.length - 1];
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/orders/" + orderID + "/transactions.json";
        RequestSpecification request = RestAssured.given();
        request.queryParam("access_token", accessToken);
        Response response = request.get(url);
        JsonPath jp = response.then().extract().jsonPath();
        String kind = getData(jp, "transactions.kind").toString();
        String authorization = getData(jp, "transactions.authorization").toString();
        String[] kindsArray = kind.split("\\[")[1].split("]")[0].replace(" ", "").split(",");
        String[] authorizationArray = authorization.split("\\[")[1].split("]")[0].replace(" ", "").split(",");
        for (int i = 0; i < kindsArray.length; i++) {
            if (kindsArray[i].equals("capture")) {
                authorizationCapture = authorizationArray[i];
            }
        }
        return authorizationCapture;
    }

    public void verifyCancelStatus() {
        String xpath = "//div[@class='title-bar__orders-show-badge']/span";
        List<String> statusActual = getListText(xpath);
        assertThat(statusActual).contains("Cancelled");
        assertThat(statusActual).contains("Refunded");
    }

    public String getQuantityOfProduct() {
        String xpathQuantity = "//input[@id='quantity' and @type='text' or type = 'number']";
        String quantityOfItem = getAttributeValue(xpathQuantity, "value");
        if (quantityOfItem == null) {
            refreshPage();
            waitElementToBePresentThenScrollIntoView(xpathQuantity).waitUntilVisible();
            quantityOfItem = getAttributeValue(xpathQuantity, "value");
        }
        return quantityOfItem;
    }

    public void verifyQuantity(int before, int after) {
        assertThat(after).isEqualTo(before);
    }

    public void navigateToRefundScreen() {
        String total = getText("//table[contains(@class,'order-details-summary-table')]//td[normalize-space()='Total']/following-sibling::td");
//        String xPathRefundItem = "//div[@class='action-bar__item']//span[normalize-space()='Refund item']";
        if (!total.equals("$0.00")) {
            clickBtn("Refund item");
            waitUntilInvisibleLoading(15);
            waitForTextToAppear("Refund items");
        }
    }

    public void clickOnRefundButton() {
        String xPathRefund = "//button//span[contains(text(),'Refund')]";
        XH(xPathRefund).waitUntilClickable();
        clickOnElement(xPathRefund);
        waitElementToBeVisible("//div[@class='s-notices is-bottom']//div[contains(text(),'Refund succeeded') or contains(text(),'Create refund success')]");
        refreshPage();
    }

    public void verifyOrderDetailAfterRefundSuccessfully(String status, String netPayment) {
        String xPathStatus = "//div[@class='title-bar__orders-show-badge']/descendant::span[contains(text(),'" + status + "')]";
        String xPathNetPayment = "//div[@class='card__section']//td[normalize-space()='Net Payment']/following::td";
        waitElementToBeVisible(xPathStatus);
        waitElementToBeVisible(xPathNetPayment);
        verifyElementText(xPathNetPayment, netPayment);
    }

    public void enterRefundedItemQuantity(String productName, String variantName, int quantity) {
        String xPathQuantity = "(//a[normalize-space()='" + productName + "']/following::*[normalize-space()='" + variantName + "']/following::div[@class='unfulfilled-card__item']/descendant::input)[1]";
        waitTypeAndEnter(xPathQuantity, String.valueOf(quantity));
        if (quantity > 0) {
            XH(xPathQuantity).sendKeys(Keys.TAB);
            XH("//button//span[contains(text(),'Refund')]").waitUntilClickable();
            waitForEverythingComplete();
        }
    }

    public void enterRefundedShipping(String refundedShipping) {
        waitClearAndTypeThenEnter("//div[@class='card__section refund-shipping-section']//input[@placeholder='Refund amount']", refundedShipping);
        waitABit(6000);
    }

    public void blurField(String labelName, String refund) {
        String xpath = "//input[@placeholder='" + labelName + "' or preceding-sibling::*[text()[normalize-space()='" + labelName + "']]]";
        XH(xpath).sendKeys(Keys.TAB);
        waitABit(5000);
    }

    public void waitUntilRefundButtonUpdated(String expectedRefund) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        String actualRefund = "$" + expectedRefund;
        String xpath = "//button//span[contains(normalize-space(),'Refund " + actualRefund + "') or contains(normalize-space(),'Mark as refund " + actualRefund + "') or contains(normalize-space(),'Refunded " + actualRefund + "')]";
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(xpath))));
    }

    public String actualRefund() {
        String xpathRefund = "//button//span[starts-with(text(),'Refund') or starts-with(text(),'Mark as refund')]";
        String actualRefund = getTextValue(xpathRefund).split("\\$")[1];
        return actualRefund;
    }

    public String getPriceOfItem() {
        String price = getTextValue("//*[@class='unfulfilled-card__line_item__secondary-details']/descendant::*[5]");
        return price;
    }

    public String getShippingOnSF() {
        String shippingSF = getText("//td[normalize-space()='Shipping']//following-sibling::td//span");
        if (shippingSF.equals("Free")) {
            shippingSF = getText("//td[normalize-space()='Shipping']//following-sibling::td//span").replace("Free", "$0.00");
        }
        return shippingSF;
    }

    public String getTaxOnSF() {
        String taxSF = getText("//td[normalize-space()='Taxes']//following-sibling::td//span");
        return taxSF;
    }

    public void verifyShippingOnDB(String shippingSF) {
        String xpathShippingDB = "//table[contains(@class,'order-details-summary-table')]//td[normalize-space()='Shipping' or normalize-space()='运输']/following-sibling::td//span";
        String shippingDB = getText(xpathShippingDB);
        assertThat(shippingSF.split("\\s")[0]).isEqualTo(shippingDB);
    }

    public String getTotalOnSF() {
        String totalSF = getText("//table[@class='total-line-table']//span[normalize-space()='Total:']/following::span[contains(text(),'$')]");
        return totalSF;
    }

    public void verifyTotalOnDB(String totalSF) {
        String xpathTotalDB = "//table[contains(@class,'order-details-summary-table')]//td[normalize-space()='Total' or normalize-space()='总额']/following-sibling::td";
        verifyElementText(xpathTotalDB, totalSF);
    }

    public boolean isDiscount() {
        return isElementExist("/table[@class='total-line-table']//span[normalize-space()='Discount']/following::span[1]");
    }

    public String getDiscountOnSF() {
        String xpathDiscount = "/table[@class='total-line-table']//span[normalize-space()='Discount']/following::span[1]";
        return getText(xpathDiscount);
    }

    public void verifyDiscountOnDB(String discountSF) {
        String xpathdiscountDB = "//table[contains(@class,'order-details-summary-table')]//td[normalize-space()='Discount' or normalize-space()='折扣']//following::td[1]";
        String discountDB = getText(xpathdiscountDB);
        assertThat(discountDB).isEqualToIgnoringCase(discountSF);
    }

    public void verifyTippingOnDB(double tippingSF) {
        double tippingDB = Double.parseDouble(price(getText("//*[text()[normalize-space()='Tip']]//following-sibling::td//span")));
        System.out.println("Tipping amount on DB :" + tippingDB);
        System.out.println("Tipping amount on SF :" + tippingSF);
        assertThat(tippingDB).isEqualTo(tippingSF);
    }

    public String getAddressOnSF() {
        waitElementToBeVisible("//*[normalize-space()='Shipping address']/following-sibling::address[@class='address']");
        String addressSF = getText("//*[normalize-space()='Shipping address']/following-sibling::address[@class='address']").replace("\n", " ");
        return addressSF;
    }

    public void verifyAddressOnDB(String typeOfAddress, String addressSF) {
        String url_account = LoadObject.getProperty("webdriver.base.url");

        if (url_account.contains(".net.cn")) {
            switch (typeOfAddress) {
                case "Billing address":
                    typeOfAddress = "账单地址";
                    break;
                case "SHIPPING ADDRESS":
                    typeOfAddress = "运输地址";
                    break;
                default:
                    fail();
            }

            if ("Same as shipping address".equals(addressSF)) {
                addressSF = "跟运输地址一致";
            }
        }

        String _typeOfAddress = StringUtils.capitalize(typeOfAddress.toLowerCase());
        String addressOnDB = getListText("//*[normalize-space()='" + _typeOfAddress + "']/ancestor::div[@class='card__section']//p").toString().replace("[", "").replace("]", "");
        System.out.println("Actual on SF: " + addressSF);

        if ("跟运输地址一致".equals(addressOnDB)) {
            System.out.println("Actual on DB: " + addressOnDB);
            assertThat(addressOnDB).isEqualTo(addressSF);
        } else {
            String _addressOnDB = addressOnDB.replaceAll("\\W", " ").replaceAll("\\s+", " ").trim();
            System.out.println("Actual on DB: " + _addressOnDB);
            assertThat(_addressOnDB).isEqualTo(addressSF);
        }
    }

    public List<String> geAddressInfo(String typeOfAddress) {
        return getListText("//*[normalize-space()='" + typeOfAddress + "' or normalize-space()='" + typeOfAddress + "']/following::p");
    }

    public String updateEmailForMerchant(String currentExportTime) {
        String xPathAccountEmail = "//label[normalize-space()='Account Email']/following::input[1]";
        String emailAddress = "shopbase" + currentExportTime + "@mailtothis.com";
        String xPathMessageSuccessFully = "//div[contains(text(),'successfully saved')]";
        XH(xPathAccountEmail).clear();
        XH(xPathAccountEmail).typeAndEnter(emailAddress);
        clickBtn("Save changes");
        waitElementToBeVisible(xPathMessageSuccessFully);
        return emailAddress.split("\\@")[0];
    }

    public void selectPagination(int rowPerPage) {
        String xpath = "//*[normalize-space()='Row per page']/following::*[contains(@class,'s-select')]//option[@value='" + rowPerPage + "']";
        waitElementToBePresentThenScrollIntoView(xpath).waitUntilVisible().click();
        waitForEverythingComplete();
    }

    public void selectAllOrders() {
        String xPathStatus = "//th//label[contains(@class, 'sb-checkbox')]//input";
        String xPath = "//div[contains(@class,'sb-tab-panel') and not(contains(@style,'display: none;'))]//th//label[contains(@class, 'sb-checkbox')]//span[@class='sb-check']";
        verifyCheckedThenClick(xPathStatus, xPath, true);
    }

    public void clickOnExportButton() {
        String xpath = "//span[text()[normalize-space()='%s']]";
        clickOnElement(String.format(xpath, "Export order"));
        waitElementToBeVisible(String.format(xpath, "Export to file"));
    }

    public void clickOutPopup() {
        clickOnElement("//span[normalize-space()='PAYMENT STATUS']");
    }

    public void waitToOrdersListToAppear() {
        waitElementToBeVisible("//div[@class='template']");
    }

    public void exportToFile(int number) {
        String xPathMessageSuccessfully = "//div[contains(text(),'Your export will be delivered to email')]";
        clickOnElement("//span[text()[normalize-space()='Export to file']]");
        if (number > 50) {
            scrollIntoElementView(xPathMessageSuccessfully);
        }
        waitABit(6000);
        waitForEverythingComplete();
    }

    public boolean openMailBox(String emailAddress, String subject) {
        openUrl("https://www.mailinator.com");
        String xpathEmail = "(//div[@class='input-group']//input[contains(@placeholder,'Enter Public Mailinator Inbox')])[1]";
        xpathSubject = "(//*[contains(text(),'" + subject + "')])[1]";

        WebElement inputTextbox = getDriver().findElement(By.xpath(xpathEmail));
        inputTextbox.clear();
        inputTextbox.sendKeys(emailAddress);
        inputTextbox.sendKeys(Keys.ENTER);
        waitElementToBeVisible("//table[contains(@class,'able-striped')]");

        return isElementVisible(xpathSubject, 10);
    }

    public void verifyReceivedMail(String emailAddress, String subject) {
        boolean check = openMailBox(emailAddress, subject);
        int retryTimes = 0;
        while (!check && retryTimes <= MAX_RETRY_TIME) {
            {
                clickOnElement("//span[@class = 'input-group-btn']//button[normalize-space()='GO!']");
                retryTimes++;
                waitABit(12000);
                check = openMailBox(emailAddress, subject);
            }
        }
        assertThat(check).isTrue();
        clickOnElement(xpathSubject);
        waitForEverythingComplete();
    }

    public void verifyFileName(String fileName) {
        Date dateTimeNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(dateTimeNow);
        String fileNameByDate = fileName.replace("yyyyMMdd", strDate);
        waitForElementToPresent("//iframe[@id='html_msg_body']");
        switchToIFrame("//iframe[@id='html_msg_body']");
        waitABit(6000);
        clickOnElement("//a[contains(text(),'" + fileNameByDate + "')]");
        waitForEverythingComplete();
    }

    public String getOrderNumber() {
        String xpath = "//div[contains(@class,'title-bar')]//h2[2]";
        String orderNumber = getTextValue(xpath).trim();
        return orderNumber;
    }

    public String getTheNumberOfUnfulfilledOrdersInSideBarMenu() {
        String number = getText("//span[normalize-space()='Orders']/following::span[1]");
        boolean check = isElementExist(number);
        if (!check) {
            refreshPage();
            isElementExist(number);
        }
        return number;
    }

    public String getProductImage() {
        String xpath = "//span[@class='image__wrapper']//img";
        String productImage = getAttributeValue(xpath, "src");
        return productImage;
    }

    String xpathProduct = "(//div[@class='card__section']/div[@class='unfulfilled-card-container'])[%d]";

    public String getProductName(int index) {
        String xpath = String.format(xpathProduct, index) + "//p[contains(@class,'name-product')]";
        return getTextValue(xpath);
    }

    public String getVariantName(int index) {
        String xpath = "(//div[@class='card__section']/div[@class='unfulfilled-card-container']//span[@class='unfulfilled-card__variation-subdued'][1]//p)[" + index + "]";
        return getText(xpath);
    }


    public String getQuantityProduct() {
        String xpath = "//span[@class='unfulfilled-card__quantity']";
        return getTextValue(xpath);
    }

    public String getQuantityProductForMultipleItem(int index) {
        String xpath = String.format("(//div[contains(@class,'unfulfilled-card__price-by-quantity--desktop')])[%d]", index);
        return getTextValue(xpath);
    }

    public String getPrices() {
        String xpath = "//div[@class='unfulfilled-card__price-by-quantity']//span[1]";
        return getTextValue(xpath);
    }

    // Get order infor

    String orderInfor = "//td[text()[normalize-space()='%s']]//following-sibling::td[%d]";

    public String getSubtotal() {
        String xpath = "//td[text()[normalize-space()='Subtotal']]//following-sibling::td[2]//div";
        return getTextValue(xpath);
    }

    public String getShippingPrice() {
        String xpath = "//td[text()[normalize-space()='Shipping']]//following-sibling::td[2]//span";
        return getTextValue(xpath);
    }

    public String getTotal() {
        String xpath = "//td[text()[normalize-space()='Total']]//following-sibling::td//div";
        return getTextValue(xpath);
    }

    public String getOrderInfor(String infor) {
        String _orderInfor = String.format(orderInfor, infor, 1);
        if (isElementExist(_orderInfor)) {
            return price(getText(_orderInfor));
        }
        return null;
    }

    // Refund order


    public String getDiscountedPrice(String blockName, String productName) {
        // blockName only 2 possible values: if items is not restocked, blockName = "Unfulfilled" else blockName = "Refuned item"
        return getTextByJS("//h2[contains(text(),'" + blockName + "')]/following::a[normalize-space()='" + productName + "'][1]/following::del[1]").trim();
    }

    public String getOriginalPrice(String blockName, String productName) {
        return getTextByJS("//h2[contains(text(),'" + blockName + "')]/following::a[normalize-space()='" + productName + "'][1]/following::span[contains(text(),'$')][1]").trim();
    }

    public String getOriginalPrice(String productName) {
//        String xpath = "//h2[contains(text(),'Fulfilled') or contains(text(),'Unfulfilled')]/following::a[normalize-space()='" + productName + "'][1]/following::span[contains(text(),'$')][1]";
        String xpath = "//div[@class='unfulfilled-card' and descendant::*[normalize-space()='" + productName + "']]//*[contains(@class,'desktop')]//span[contains(text(),'$')]";
        return getText(xpath);
    }

    public String getRefund() {
        String xpath = "//td[text()[normalize-space()='Refunded']]//following-sibling::td[2]";
        return getTextValue(xpath);
    }

    public void verifyShowWarningForChargeBack(String warning) {
        String xpathNoti = "//div[contains(@class,'chargeback-notification chargeback-warning')]/descendant::*[contains(text(),'" + warning + "')]";
        waitElementToBeVisible(xpathNoti);
    }

    public void editTags(String tags) {
        String xpath = "//input[@placeholder='Reviewed, packed, delivered']";
        // not working consistently with common method waitTypeAndEnter
        waitTypeOnElement(xpath, tags);
        $(xpath).sendKeys(Keys.ENTER);
    }

    public String getTagList() {
        String enteredTag = "//div[@class='tag-list-items']//span[@class='']";
        waitElementToBeVisible(enteredTag);
        return getText(enteredTag);
    }

    public void downloadOrders() {
        String xpathLinkFile = "//a[contains(.,'order_export')]|//a[contains(.,'orders_export')]";
        clickOnElement(xpathLinkFile);
        waitForEverythingComplete();
    }

    //select the newest order to view details
    public void clickOnTheOrderInList(String orderName) {
        System.out.println(orderName);
        String xPath = "//table/tbody//a[text()[normalize-space()='" + orderName + "']]";
        clickOnElement(xPath);
    }

    public void clickOnViewFullAnalysis() {
        String xPath = "//span[text()[normalize-space()='View full analysis']]";
        if (isElementVisible(xPath, 20)) {
            clickOnElement(xPath);
        }
    }

    public void verifyIndicator(String indicator, String expectedClassValue) {

    }

    public String getOrderStatusInOrderList(String orderName) {
        String[] arrOrderName = orderName.split("\\s+");
        String orderNo = arrOrderName[1];
        System.out.println(orderNo);
        String xPath = "//table/tbody//a[text()[normalize-space()='" + orderNo + "']]/ancestor::td/following-sibling::td[3]//span/span/span";
        String status = getTextValue(xPath);
        return status;
    }

    public List<String> getOrderList() {
        String xPath = "//*[contains(@class,'order__list__desktop')]//*[contains(@class,'order-link')]";
        List<String> listOfOrder = new ArrayList<>();
        if (isElementVisible(xPath, 3)) {
            listOfOrder = getListText(xPath);
        }
        return listOfOrder;
    }


    public void compareOrderStatusWithPaypalPaymentStatus(String orderStatus, String paymentStatus) {
    }

    public void verifyProductName(String productName, int index) {
        String xpath = String.format(xpathProduct, index) + "//p[contains(@class,'name-product')]";
        verifyElementText(xpath, productName);
    }

    public void verifyQuantityOfProduct(String quantity, int index) {
        String xpath = String.format(xpathProduct, index) + "//*[contains(@class,'unfulfilled-card__price-by-quantity--desktop')]//span[@class= 'unfulfilled-card__quantity']";
        verifyElementText(xpath, quantity);
    }

    public void verifyTotalPriceOfProduct(String expectedPrice, int index) {
        String xpath = String.format(xpathProduct, index) + "//*[@class= 'unfulfilled-card__total-price']";
        String actualPrice = getTextValue(xpath);
        comparePrice(actualPrice, expectedPrice);
    }

    public void verifyTotalOrder(String totalAmt) {
        String xpath = "//td[text()='Total']/following-sibling::td//div";
        verifyElementText(xpath, totalAmt);
    }

    public void verifyPaidByCustomer(String totalAmt) {
        String xpath = "//td[text()='Paid by customer']/following-sibling::td";
        verifyElementText(xpath, totalAmt);
    }

    public void verifyOrderStatus(String status) {
        String url_account = LoadObject.getProperty("webdriver.base.url");

        if (url_account.contains(".net.cn")) {
            switch (status) {
                case "Paid":
                    status = "已支付";
                    break;
                case "Pending":
                    status = "待定";
                    break;
                default:
                    fail();
            }
        }

        String xpath = "//div[@class='title-bar__orders-show-badge']//span[contains(@class,'s-tag hide-when-printing is')]";
        int i = 0;
        while (!getTextValue(xpath).equalsIgnoreCase(status)) {
            waitABit(20000);
            refreshPage();
            i++;
            if (i == 10) {
                break;
            }
        }
        verifyElementText(xpath, status);

    }

    public void verifyTrackingUrlTextboxDisplayed() {
        verifyElementVisible("//label[text()[normalize-space()='Tracking URL']]", true);
    }

    public void searchThenSelectTheCarrier(String carrier) {
        waitUntilElementVisible(xPathInputFieldWithLabel("", "Shipping carrier", 1), 7);
        enterInputFieldWithLabel("Shipping carrier", carrier, 1);

        String inputValue = "//label[text()[normalize-space()='Shipping carrier']]/following::input[@placeholder='Select carrier']";
        String selectableValue = "//div[@class='s-select-searchable__item option--choice' and normalize-space()='" + carrier + "']";
        String activateValue = "//div[@value='" + carrier + "' and contains(@class,'active')]";
        if (getTextValue(inputValue).equals(carrier)) {
            waitElementToBeVisible(selectableValue);
            clickOnElementByJs(selectableValue);
            verifyElementPresent(activateValue, true);
        }
    }

    public void verifyShippingCarrierDisplayed(String carrier) {
        if (!carrier.isEmpty() && !carrier.equals("Other")) {
            verifyElementVisible("//div[@class='tracking-info__number']//*[contains(text(),'" + carrier + " Tracking Number:')]", true);
        } else {
            verifyElementVisible("(//div[@class='tracking-info__number']//*[contains(text(),'Tracking Number:')])[last()]", true);
        }
    }

    public boolean verifyOrderTimelineTrxID(String paymentGateway) {
        String xpath = "//div[contains(@class,'timeline')]//div[@class='content-body' and contains(text(),'transaction ID')]";
        String actual_content = "";
        String expected_content = "";
        if (!"Test Gateway".equalsIgnoreCase(paymentGateway)) {
            actual_content = getTextValue(xpath);
            expected_content = "The " + paymentGateway + " transaction ID is";
        }
        return actual_content.contains(expected_content);
    }

//    public List<String> getTrxID() {
//        String xpath = "(//div[contains(@class,'timeline')]//div[@class='content-body' and contains(text(),'transaction ID')])";
//        int size = countElementByXpath(xpath);
//        List<String> listOfTransaction = new ArrayList<>();
//        for(int i = 1; i <= size; i++) {
//            String actual_content = getTextValue(xpath+"["+ i +"]");
//            String[] arr_actual_content = actual_content.split("\\s");
//            String trxId = arr_actual_content[arr_actual_content.length - 1];
//            listOfTransaction.add(trxId);
//        }
//        //return arr_actual_content[arr_actual_content.length - 1];
//        return listOfTransaction;
//    }

    public String getCustomerName() {
        String xPath = "//a[contains(@href,'admin/customers/')]";
        waitUntilElementVisible(xPath, 15);
        return getTextValue(xPath).trim();
    }

    @Step
    public List<String> getTimelineContent() {
        String xPath = "//div[contains(@class,'timeline-list')]//div[contains(@class,'timeline')]//div[@class='content-body']";
        int i = 0;
        while (!isElementVisible(xPath, 5) && i <= 3) {
            refreshPage();
            i++;
        }
        return getListText(xPath);
    }

    public float getPaymentRate() {
        String xpath = "//tbody//*[contains(text(),'Payment fee')]";
        float processingRate = getPrice(xpath);
        return processingRate;

    }

    public float getProcessingFee() {
        String xpath = "(//*[contains(text(),'Processing fee')]//following-sibling::span)";
        return getPrice(xpath);
    }

    public float getPaymentFee() {
        String xpath = "(//*[contains(text(),'Payment fee')]//following-sibling::span)";
        return getPrice(xpath);
    }

    public Float getDiscount() {
        String xpathDiscount = "(//*[contains(text(),'Discount')]//following-sibling::span)";
        Float discount = getPrice(xpathDiscount);
        return discount;
    }

    public float getBaseCost() {
        String xpathBaseCost = "(//*[contains(text(),'Basecost')]//following-sibling::span)";
        float baseCost = getPrice(xpathBaseCost);
        return baseCost;
    }

    public float getSubToTal() {
        String xpathSubtotal = "//*[text()[normalize-space()='Subtotal']]//ancestor::tbody//div";
        float subTotal = getPrice(xpathSubtotal);
        return subTotal;
    }

    public float getProcessingRate() {
        String xpathProcessingRate = "//tbody//*[contains(text(),'Processing Fee')]";
        float processingRate = getPrice(xpathProcessingRate);
        return processingRate;
    }

    public float getProfit() {
        String xpathProfit = "(//*[text()[normalize-space()='PROFIT']]//ancestor::tbody//div)|(//*[text()[normalize-space()='Profit']]/parent::div/following-sibling::span)";
        float profit = getPrice(xpathProfit);
        return profit;
    }

    public String getCustomerEmail() {
        return getTextValue("(//h3[normalize-space()='CONTACT INFORMATION']/following::*[contains(text(),'@')])[1]");
    }

    public void clickTheNewestOrder() {
        String xpathOrder = "(//div[contains(@class,'table-responsive')]//a[@class='order-link'])[1]";
        waitUntilElementVisible(xpathOrder, 8);
        clickOnElement(xpathOrder);
    }

    public void navigateToProductDetail() {
        String xpathProduct = "(//p[@class='unfulfilled-card__name-product']//a)[1]";
        clickOnElement(xpathProduct);
        switchToLatestTab();

    }

    public void waitUntilLoadingOrdersListCompletely() {
        waitUntilElementVisible("//div[contains(@class,'orders-list')]", 10);
    }

    public void clickOnTheCustomerNameLinkText() {
        clickOnElement("//div[@class='card__section']//a[contains(@href,'customers')]");
    }

    public void clickOnOrderQuantityLinkText() {
        clickOnElement("//div[@class='card__section']//a[contains(@href,'orders')]");
    }

    public String countOrderQuantityInOrderDetail() {
        waitForEverythingComplete();
//        return Integer.parseInt(getTextValue("//div[@class='card__section']//a[contains(@href,'orders')]").replace("orders", "").replace("order", "").trim());
        return getTextValue("//div[@class='card__section']//a[contains(@href,'orders')]");
    }

    //    public int countOrderQuantityInOrderList() {
//        return countElementByXpath("//div[contains(@class,'orders-list')]//tbody//tr");
//    }
//    public List<String> countOrderQuantityInOrderList() {
//        String xpath = "//div[contains(@class,'orders-list')]//tbody//tr";
//        if (isElementExist(xpath)) {
//            return getListText(xpath);
//        }
//        return null;
//
//    }

    public void verifyOptionNotPresent(String optionName, boolean isPresent) {
        verifyElementVisible("//div[@class='s-dropdown-menu']//span[contains(text(),'Cancel')]", isPresent);
    }

    public void waitUntilTheFieldDisplayed(String labelName) {
        waitUntilElementVisible(xPathInputFieldWithLabel("", labelName, 1), 7);
    }

    public void verifyProductQuantityInRefundPage(String productName, String quantity) {
        String unfulfillBlock = "//div[@class='unfulfilled-card']//a[normalize-space()='" + productName + "']/preceding::span[normalize-space()='" + quantity + "']";
        verifyElementVisible(unfulfillBlock, true);
    }

    public void verifyRefundedShipping(String refundedShipping) {
        verifyElementVisible("//div[contains(@class,'refund-shipping-section')]//p[normalize-space()='" + refundedShipping + "']", true);
    }

    public void verifyTheProductIsRestocked(String productName, boolean isRestock) {
        verifyElementVisible("//div[contains(@class,'unfulfilled-card')]//a[normalize-space()='" + productName + "']/following::li[normalize-space()='Restocked']", isRestock);
    }

    public String getFinancialStatus() {
        return getText("(//div[@class='title-bar__orders-show-badge']//span)[1]");
    }

    public void inputTheFieldSlowly(String label, String value) {
        inputSlowly(xPathInputFieldWithLabel("", label, 1), value);
    }

    String cancelPopup = "//tr[@class='summary-detail']//td[normalize-space()='%s']/following-sibling::td";

    public String getInformationOnCancelPopup(String criteria) {
        return price(getText(String.format(cancelPopup, criteria)));

    }

    public String getRemainingShipping() {
        String actualRemainingShipping = getText("//tr[@class='summary-detail']//td[contains(text(),'Shipping')]");
        return actualRemainingShipping;
    }

    public int getOriginalQuantityBeforeRefunding(String productName) {
        return Integer.parseInt(getText("//div[@class='unfulfilled-card' and descendant::*[normalize-space()='" + productName + "']]//*[contains(@class,'desktop')]//span[@class='unfulfilled-card__quantity']"));
    }

    public String getChargePayment() {
        String xpath = "(//h2[@class='stack-item__title'])[2]";
        waitUntilElementVisible(xpath, 50);
        String status = getText(xpath);
        return status;

    }

    public int countElement(String xpath) {
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        int count = elements.size();
        return count;
    }

    public List<String> getReturnedProductOnCancelPopup() {
        List<String> returnedProductList = new ArrayList<>();
        String returnedProduct = "//span[normalize-space()='Already returned']/preceding::*[contains(@class,'line-item__title')]";
        int num = countElement(returnedProduct);
        for (int i = 0; i < num; i++) {
            returnedProductList.add(getText(returnedProduct));
        }
        return returnedProductList;
    }

    public String getProductVariant(String productName) {
        String xpathFF = "//a[normalize-space()='" + productName + "']/following::p[1]";
        String xpathRF = "(//a[normalize-space()='" + productName + "']/following::span[@class='unfulfilled-card__variation-subdued']//p)[1]";
        String variantName = "";
        if (isElementExist(xpathFF)) {
            variantName = getText(xpathFF);
        } else {
            variantName = getText(xpathRF);
        }
        return variantName;
    }

    public void verifyMsgDisplayed() {
        verifyTextPresent(successMsg, true);
    }

    public void clickOnEditShippingAddress() {
        waitElementToBeVisible(xPathBtn("", "Edit", 3));
        clickBtn("Edit", 3);
        waitUntilElementInvisible("//div[contains(@class,'s-modal-body')]", 5);
    }

    public String getActualQuantityRemainingOnCancelPopup(String prodName, String variantName) {
        return getText("//div[contains(@class,'line-item__details')]//p[normalize-space()='" + prodName + "']/following::p[normalize-space()='" + variantName + "']/following::div[contains(@class,'numberic-input')]//span[not (contains(text(),'Already returned'))]");
    }

    public String getActualOriginalPriceRemainingOnCancelPopup(String prodName, String variantName) {
        return getText("(//div[contains(@class,'line-item__details')]//p[normalize-space()='" + prodName + "']/following::p[normalize-space()='" + variantName + "']/following::div[contains(@class,'line-item__quantity')]//span[not (contains(text(),'Already returned')) and not (child::del)])[1]");
    }

    public void enterTextToTextbox(String criteria) {
        waitUntilElementVisible(TEXTBOX_SEARCH, 10);
        waitClearAndTypeThenEnter(TEXTBOX_SEARCH, criteria);
    }

    public float getShipping() {
        String xpath = "//*[text()[normalize-space()='Shipping']]//ancestor::tr//div/span";
        return getPrice(xpath);
    }

    public void clickImportTrackingNumber() {
        String xpath = "//button[normalize-space()='Import tracking number']";
        clickOnElement(xpath);
    }

    public void importFileCVSImportTrackingNumber(String data, List<String> orderNameList) throws IOException {
        String xpathTitle = "//*[contains(@class, 'header')]//*[normalize-space()='Import tracking number']";
        waitElementToBeVisible(xpathTitle);
        String xpath = "//input[@type='file']";

        if (!data.contains("Domain")) {
            String _filePath = LoadObject.getFilePath("fulfillment_CSVFile.csv");
            FileWriter fw = new FileWriter(_filePath, false);

            fw.write("Name,Lineitem SKU,Lineitem Quantity,Tracking Number,Shipping Carrier,Tracking URL");

            if (data.contains("@OrderName@")) {
                fw.write(data.replace("@OrderName@", orderNameList.get(0)));
            } else {
                fw.write(data);
            }

            fw.flush();
            fw.close();
            chooseAttachmentFile(xpath, "fulfillment_CSVFile.csv");
        } else {
            String domain1 = LoadObject.getProperty("firstShop");
            String domain2 = LoadObject.getProperty("secondShop");
            String _filePath = LoadObject.getFilePath("fulfillment_CSVFile_MultiStore.csv");
            FileWriter fw = new FileWriter(_filePath);
            fw.write("Domain,Name,Lineitem SKU,Lineitem Quantity,Tracking Number,Shipping Carrier,Tracking URL");

//            if (data.contains("@OrderName1@")) {
//                fw.write(data.replace("@OrderName1@", orderNameList.get(0)));
//            }
//
//            if (data.contains("@OrderName2@")) {
//                fw.write(data.replace("@OrderName2@", orderNameList.get(1)));
//            }
//
//            if (data.contains("@Domain1@")) {
//                fw.write(data.replace("@Domain1@", domain1));
//            }
//
//            if (data.contains("@Domain2@")) {
//                fw.write(data.replace("@Domain2@", domain2));
//            }

            data = data.replace("@OrderName1@", orderNameList.get(0));
            data = data.replace("@OrderName2@", orderNameList.get(1));
            data = data.replace("@Domain1@", domain1);
            data = data.replace("@Domain2@", domain2);

            fw.write(data);
            fw.flush();
            fw.close();
            chooseAttachmentFile(xpath, "fulfillment_CSVFile_MultiStore.csv");
        }
    }

    public void selectElement(String status, String label, String action) {
        String xpath = "//*[child::p[contains(.,'" + label + "')]]//preceding-sibling::div";
        if ("send mail".equals(action)) {
            xpath = "//*[child::p[contains(.,'" + label + "')]]";
        }

        String xpathCb = xpath + "//span";
        String xpathStatus = xpath + "//input";
        if (!status.isEmpty()) {
            boolean flag = "Check".equals(status) ? true : false;
            try {
                verifyCheckedThenClick(xpathStatus, xpathCb, flag);
            } catch (Throwable e) {
                waitABit(4000);
                verifyCheckedThenClick(xpathStatus, xpathCb, flag);
            }
        }
    }

    public void clickUploadFile() {
        String xpath = "//button[normalize-space()='Upload']";
        clickOnElement(xpath);
    }

    public void searchOrderNameOnOrderList(String orderNumber) {
//        String xpath = "//div[@class='order-search-form']//input[contains(@class, 'order-search-input')]";
        String xpath = "//input[@placeholder='Search by order name, transaction id, line item name' or @placeholder ='Search orders']";
        try {
            waitClearAndTypeThenEnter(xpath, orderNumber);
        } catch (Throwable e) {
            xpath = "(" + xpath + ")[2]";
            refreshPage();
            waitForEverythingComplete();
            waitClearAndTypeThenEnter(xpath, orderNumber);
        }
    }

    public void verifyFulfillmentStatusOnOrderList(String orderNumber, String status) {
        String xpath = "//tbody//tr[contains(.,'" + orderNumber + "') and contains(., '" + status + "')]";
        try {
            waitElementToBeVisible(xpath);
            verifyElementVisible(xpath, true);
        } catch (Exception e) {
            waitForEverythingComplete();
            refreshPage();
            waitElementToBeVisible(xpath, 30);
            verifyElementVisible(xpath, true);
        }
    }

    private int getIndexItem(String colName) {
        return countElementByXpath("//tr/th[contains(. , '" + colName + "')]//preceding-sibling::th") + 1;
    }

    String xpathPopup = "//*[contains(@class,'sb-popup__body')]//p";

    public void verifyOrder(String order) {
        String xpathTitle = "//*[normalize-space()='Preview your first order']";
        waitElementToBeVisible(xpathTitle);
        waitElementToBeVisible(xpathPopup);
        String str = Integer.parseInt(order) < 2 ? "Order" : "Orders";
        assertThat(getText(xpathPopup)).contains("You will be fulfilling approximately " + order + " " + str);
    }

    public void verifyFulfillment(String fulfillment) {
        String str = Integer.parseInt(fulfillment) < 2 ? "fulfillment" : "fulfillments";
        assertThat(getText(xpathPopup)).contains("with a total of " + fulfillment + " " + str);
    }

    public void verifyOverwrite(String overwrite) {
        if (!"".equals(overwrite)) {
            switch (overwrite) {
                case "Check":
                    assertThat(getText(xpathPopup)).contains("It will overwrite any existing fulfillments");
                    break;
                case "Uncheck":
                    assertThat(getText(xpathPopup)).contains("It will not overwrite any existing fulfillments");
                    break;
                default:
                    fail();
            }
        }
    }

    public void verifyNotification(String notification) {
        if (!"".equals(notification)) {
            String str = "Check".equals(notification) ? "send" : "not send";
            assertThat(getText(xpathPopup)).contains("it will " + str + " a notification email to the customer");
        }
    }

    public int getIndexOfColumn(String xpathHeader, String columnName) {
        String xpath = xpathHeader + "//tr/th";
        int count = countElement(xpath);
        for (int i = 1; i <= count; i++) {
            String xpathI = "(" + xpath + ")[" + i + "]";
            if (columnName.equals(getText(xpathI))) {
                return i;
            }
        }
        return 1;
    }

    public void verifyValueOfColumn(String xpathHeader, String columnName, String xpathBody, String value) {
        int index = getIndexOfColumn(xpathHeader, columnName);
        String xpathValue = "(" + xpathBody + "//tr//td)[" + index + "]";
        assertThat(value).isEqualTo(getText(xpathValue));
    }

    public void verifyContentPopupPreview(String label, String val) {
        String xpath = "//div[contains(@class, 'sb-popup__body')]";
        int index = getIndexOfColumn(xpath + "//thead", label);
        String Exp = xpath + "//tbody//tr//td[" + index + "]";
        assertThat(val).isEqualTo(getText(Exp));
    }

    public String getLineitemSKU(String prodName) {
        String[] arrSku = getText("(//a[text()[normalize-space()='" + prodName + "']]/following::*[starts-with(text(),'SKU')])[1]").split("SKU:");
        String sku = arrSku.length == 0 ? "" : arrSku[1];
        return sku;
    }

    public void verifyLineitemQuantity(String lineitemQuantity) {
        String xpath = "//div[@class='modal-action']//thead";
        int index = getIndexOfColumn(xpath, "Lineitem Quantity");
        String xpathLineitemQuantity = "(//div[@class='modal-action']//tbody//tr//td)[" + index + "]";
        assertThat(lineitemQuantity).isEqualTo(getText(xpathLineitemQuantity));
    }

    public void verifyTrackingNumber(String trackingNumber) {
        String xpath = "//div[@class='modal-action']//thead";
        int index = getIndexOfColumn(xpath, "Tracking number");
        String xpathTrackingNumber = "(//div[@class='modal-action']//tbody//tr//td)[" + index + "]";
        assertThat(trackingNumber).isEqualTo(getText(xpathTrackingNumber));
    }

    public void verifyShippingCarrier(String shippingCarrier) {
        String xpath = "//div[@class='modal-action']//thead";
        int index = getIndexOfColumn(xpath, "Shipping carrier");
        String xpathShippingCarrier = "(//div[@class='modal-action']//tbody//tr//td)[" + index + "]";
        assertThat(shippingCarrier).isEqualTo(getText(xpathShippingCarrier));
    }

    public void verifyDomain(String shopDomain) {
        String xpath = "//div[@class='modal-action']//thead";
        int index = getIndexOfColumn(xpath, "Domain");
        String xpathDomain = "(//div[@class='modal-action']//tbody//tr//td)[" + index + "]";
        assertThat(shopDomain).isEqualTo(getText(xpathDomain));
    }

    public void clickBtnOK() {
        clickBtn("OK");
        String xpathTitle = "//p[normalize-space()='Fulfillment In Progress']";
        waitForElementNotAppear(xpathTitle);
    }

    public void verifyLineItem(String sku, String statusItem, String quantity) {
        String xpath = "//section[contains(., '" + statusItem + "') and contains(., '(" + quantity + ")')  and contains(., '" + sku + "')]";

        verifyElementVisible(xpath, true);
    }

    public void clickEditTracking(String sku, String statusItem, String quantity) {
        String xpath = "//section[contains(., '" + statusItem + "') and contains(., '(" + quantity + ")')  and contains(., '" + sku + "')]//button[child::span[normalize-space()='Edit tracking']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void verifyTrackingNumbers(String trackingNumbers) {
        String xpathTrackingNumber = "//input[@id='tracking-number']";
        assertThat(trackingNumbers).isEqualTo(getTextValue(xpathTrackingNumber));
    }

    public void verifyCarrier(String carrier) {
        String xpathCarrier = "//div[@class='s-modal is-active']//label[text()='Carrier']//following-sibling::div//select";
        assertThat(carrier).isEqualTo(getSelectedValueDdl(xpathCarrier));
    }

    public void verifyTrackingNotification(String notification) {
        String xpath = "//span[text()='Send notification email to customer']//preceding-sibling::input";
        boolean flg = "Check".equals(notification) ? true : false;
        try {
            verifyElementSelected(xpath, flg);
        } catch (Exception e) {
            waitABit(4000);
            verifyElementSelected(xpath, flg);
        }
    }

    public void closePopupEditTracking() {
        String xpath = "//button[child::span[text()='Cancel']]";
        clickOnElement(xpath);
        String xpathTitle = "//h2[text()='Edit tracking']";
        waitForElementNotAppear(xpathTitle);
    }

    public void verifyURL(String sku, String statusItem, String quantity, String url, String trackingNumbers) {
        String xpath = "//section[contains(., '" + statusItem + "') and contains(., '(" + quantity + ")')  and contains(., '" + sku + "')]//a[normalize-space()='Track shipment']";

        if (url.contains("@Tracking numbers@")) {
            url = url.replace("@Tracking numbers@", trackingNumbers);
        }
        clickOnElement(xpath);
        switchToTheLastestTab();
        waitForEverythingComplete();
        waitABit(5000);
        assertThat(url).isEqualTo(getCurrentUrl());
        switchToTheFirstTab();
    }

    public String getFulfillmentStatus() {
        return getText("//div[@class='title-bar__orders-show-badge']//span[2]");
    }

    DecimalFormat df = new DecimalFormat("###.00");

    public List<String> getDisputeMessage() {
        String xPath = "//div[contains(@class,'s-flex chargeback-notification chargeback-warning')]//p";
        int i = 0;
        while (!isElementVisible(xPath, 5) && i <= 3) {
            refreshPage();
            i++;
        }
        System.out.println(getListText(xPath));
        return getListText(xPath);
    }

    public void verifyDisputeWarningMessage(String disputeType, String totalAmt) {
        Format f = new SimpleDateFormat("MMM d, yyyy");
        Date date = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, 9);
        String strDate = f.format(cal.getTime());
        System.out.println(strDate);
        String message1 = "";
        String message2 = "";

        switch (disputeType) {
            case "chargeback":
                message1 = "The customer opened a " + disputeType + " totalling " + totalAmt;
                message2 = "We have collected evidence that will be sent to the customer's bank on " + strDate + " 23:59 (UTC-00:00). ShopBase will automatically send responses with information collected from the order details at 00:00 of the due date. You can submit any evidence up until this time.";
                break;
            case "inquiry":
                message1 = "The customer opened a dispute " + disputeType + " totalling " + totalAmt;
                message2 = "We have collected evidence that will be sent to the customer's bank on " + strDate + " 23:59 (UTC-00:00). ShopBase will automatically send responses with information collected from the order details at 00:00 of the due date. You can submit any evidence up until this time.";
                break;
        }

        List<String> expectedListOfMessage = new ArrayList<>();
        expectedListOfMessage.add(message1);
        expectedListOfMessage.add(message2);

        assertThat(getDisputeMessage()).isEqualTo(expectedListOfMessage);
    }

    public void clickOnSubmitResponseBtn() {
        String xPath = "//*[text()[normalize-space()='Submit response']]";
        if (isElementExist(xPath)) {
            clickOnElement(xPath);
        }
    }


    public void retrySearch(String xpath, int retryTimes) {
        int i = 0;
        while (!isElementVisible(xpath, 7) && i < retryTimes) {
            refreshPage();
            waitUntilInvisibleLoading(7);
            retryTimes++;
        }
    }

    public void clickOnSubmitEvidenceNowBtn() {
        clickBtn("Submit evidence now");
        waitForEverythingComplete(15);
    }

    public List<String> getReasonDescription() {
        String xPath = "//div[@class='card__section'][preceding-sibling::div/p[contains(text(),'Reason for chargeback')]]/p";
        return getListText(xPath);
    }

    public void verifyChargeBackReason(String endingCardNo) {
        String reason = "";
        String xPath = "//*[contains(text(),'Reason for chargeback')]";
        String reasonDes1 = "", reasonDes2 = "";
        List<String> expectedReasonDesc = new ArrayList<>();
        switch (endingCardNo) {
            case "0259":
                reason = "Fraudulent";
                reasonDes1 = "Cardholder claims that they didn’t authorize the payment. This can happen if the card was lost or stolen and used to make a fraudulent purchase. It can also happen if the cardholder doesn’t recognize the payment as it appears on the billing statement from their card issuer.";
                reasonDes2 = "The disputed funds and fees have been holding in your ShopBase Payment Balance until the dispute is resolved. If you win the chargeback, the disputed funds and fees will be reversed to you. If the cardholder wins the chargeback, the disputed funds and the fee would be deducted from your account to pay for the dispute.";
                break;
            case "2685":
                reason = "Product not received";
                reasonDes1 = "The customer claims they did not receive the products or services purchased.";
                reasonDes2 = "The disputed funds and fees have been holding in your ShopBase Payment Balance until the dispute is resolved. If you win the chargeback, the disputed funds and fees will be reversed to you. If the cardholder wins the chargeback, the disputed funds and the fee would be deducted from your account to pay for the dispute.";
                break;
            case "1976":
                reason = "Fraudulent";
                reasonDes1 = "Cardholder claims that they didn’t authorize the payment. This can happen if the card was lost or stolen and used to make a fraudulent purchase. It can also happen if the cardholder doesn’t recognize the payment as it appears on the billing statement from their card issuer.";
                reasonDes2 = "No funds have been withdrawn from your account yet, but the cardholder's bank is requesting more information to decide whether to return these funds to the cardholder.";
                break;
        }
        String actualReason = getText(xPath).trim();
        assertThat(actualReason).contains(reason);

        expectedReasonDesc.add(reasonDes1);
        expectedReasonDesc.add(reasonDes2);
        assertThat(getReasonDescription()).isEqualTo(expectedReasonDesc);
    }

    private String getChargebackAmtInfo(String label) {
        String xPath = "//*[text()[normalize-space()='" + label + "']]//following-sibling::span";
        return getText(xPath);
    }

    public void verifyChargebackAmount(String currencySymbol, String chargebackAmt) {
        String chargebackFee = currencySymbol + "15.00";
        String totalChargebackAmt = currencySymbol + df.format(Double.parseDouble(chargebackAmt.split("(?<=\\D)(?=\\d)", 2)[1]) + 15);

        //verify Chargeback amt
        assertThat(getChargebackAmtInfo("Chargeback amount")).isEqualToIgnoringCase(chargebackAmt);
        //verify chargeback fee
        assertThat(getChargebackAmtInfo("Chargeback fee")).isEqualToIgnoringCase(chargebackFee);
        //verify total chargeback amt
        assertThat(getChargebackAmtInfo("Total")).isEqualToIgnoringCase(totalChargebackAmt);
    }

    public String getFilledValueInResponseForm(String fieldLabel) {
        String xPath = "//input[@placeholder='" + fieldLabel + "']";
        return getTextValue(xPath);
    }

    public void verifyValueOfEachField(String fieldLabel, String expectedValue) {
        String actualValue = getFilledValueInResponseForm(fieldLabel);
        HashMap<String, String> actualAddress = new HashMap<>();
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    public void verifyValueOfEachField(String fieldLabel, HashMap<String, String> address) {
        String actualValue = getFilledValueInResponseForm(fieldLabel);
        HashMap<String, String> actualAddress = new HashMap<>();
        switch (fieldLabel) {
            case "Shipping address":
                actualAddress.put("Shipping Name", (actualValue.split(","))[0].trim());
                actualAddress.put("Shipping Address1", (actualValue.split(","))[1].trim());
                actualAddress.put("Shipping City", (actualValue.split(","))[2].trim());
                actualAddress.put("Shipping Zip", (actualValue.split(","))[4].trim());
                actualAddress.put("Shipping Province", (actualValue.split(","))[3].trim());
                actualAddress.put("Shipping Country", (actualValue.split(","))[5].trim());
                for (String i : actualAddress.keySet()) {
                    for (String j : address.keySet()) {
                        if (i.equalsIgnoreCase(j)) {
                            assertThat(actualAddress.get(i).trim()).isEqualToIgnoringCase(address.get(j).trim());
                        }
                    }
                }
                break;
            case "Billing address":
                actualAddress.put("Billing Name", (actualValue.split(","))[0].trim());
                actualAddress.put("Billing Address1", (actualValue.split(","))[1].trim());
                actualAddress.put("Billing City", (actualValue.split(","))[2].trim());
                actualAddress.put("Billing Zip", (actualValue.split(","))[4].trim());
                actualAddress.put("Billing Province", (actualValue.split(","))[3].trim());
                actualAddress.put("Billing Country", (actualValue.split(","))[5].trim());
                for (String i : actualAddress.keySet()) {
                    for (String j : address.keySet()) {
                        if (i.equalsIgnoreCase(j)) {
                            assertThat(actualAddress.get(i).trim()).isEqualToIgnoringCase(address.get(j).trim());
                        }
                    }
                }
                break;
        }
    }

    public void inputValueByFieldLabel(String fieldLabel, String value) {
        String xPath = "//*[@placeholder='" + fieldLabel + "']";
        if (!value.isEmpty())
            waitClearAndType(xPath, value);
    }

    public void uploadFileByFieldLabel(String fieldLabel, String fileName) {
        String xPath = "";
        if (!"Additional evidence or statements".equalsIgnoreCase(fieldLabel)) {
            xPath = "//div[span[text()[normalize-space()='" + fieldLabel + ":']]]/following-sibling::div//input";
        } else {
            xPath = "//p[contains(text(),'Provide any extra evidence or statements')]//preceding-sibling::label//input";
        }
        if (!fileName.isEmpty())
            chooseAttachmentFile(xPath, fileName);
    }

    public void selectDateOfShipment() {
        String xPath_control = "//input[@placeholder='Pick a day']";
        String xPath_now_btn = "//span[text()[normalize-space()='Now']]/parent::button";
        String xPath_confirm_btn = "//span[text()[normalize-space()='Confirm']]";
        clickOnElement(xPath_control);
        clickOnElement(xPath_now_btn);
    }

    public void verifyMessageAfterDisputeDecided(String disputeType, String disputeEvidence, String chargebackAmount) {
        List<String> actualMessage = new ArrayList<>();
        List<String> expectedMessage = new ArrayList<>();
        String mess1 = "", mess2 = "";

        String xPath_chargeback_success_message = "//*[contains(@class,'chargeback-notification chargeback-success')]//p";
        String xPath_chargeback_warning_message = "//*[contains(@class,'chargeback-notification chargeback-warning')]//p";

        if (isElementVisible(xPath_chargeback_success_message, 8) || isElementVisible(xPath_chargeback_warning_message, 8)) {
            switch (disputeEvidence) {
                case "winning_evidence":
                    refreshPage();
                    if ("chargeback".equalsIgnoreCase(disputeType)) {
                        actualMessage = getListText(xPath_chargeback_success_message);
                        mess1 = "A chargeback totalling " + chargebackAmount + " was resolved in your favor";
                        mess2 = "The customer's bank sided with you. The " + chargebackAmount + " chargeback amount and $15.00 chargeback fee will be returned to you in an upcoming payout.";
                    }
                    break;
                case "losing_evidence":
                    refreshPage();
                    if ("chargeback".equalsIgnoreCase(disputeType)) {
                        actualMessage = getListText(xPath_chargeback_warning_message);
                        mess1 = "A chargeback totalling " + chargebackAmount + " was resolved in the customer's favor";
                        //  mess2 = "We submitted your response to the dispute on minutes ago. The customer's bank sided with your customer.";
                    }
                    break;
                default:
                    refreshPage();
                    actualMessage = getListText(xPath_chargeback_success_message);
                    if ("chargeback".equalsIgnoreCase(disputeType)) {
                        mess1 = "Submit reimbursement request for a chargeback successfully";
                        mess2 = "We will email you once the dispute has been decided.";
                    } else {
                        mess1 = "Submit reimbursement request for a dispute inquiry successfully";
                        mess2 = "We'll email you if the bank continues to pursue this dispute.";
                    }
                    break;
            }
        }

        expectedMessage.add(0, mess1);
        expectedMessage.add(1, mess2);

        assertThat(String.valueOf(actualMessage)).contains(expectedMessage);
    }

    public String getDateOfShipmentAfterClickingOnNow() {
        String xPath = "//input[@placeholder='Pick a day']";
        return getTextValue(xPath);
    }

    public String getValueByFieldLabel(String fieldLabel) {
        String xPath = "//span[text()[normalize-space()='" + fieldLabel + ":']]/parent::div/following-sibling::div//*[self::span or self::input or self::textarea]";
        return getTextValue(xPath);
    }

    public String getTextByFieldLabel(String fieldLabel) {
        String xPath = "//span[text()[normalize-space()='" + fieldLabel + ":']]/parent::div/following-sibling::div//*[self::span or self::input or self::textarea]";
        return getText(xPath);
    }

    public String getInputtedValueOfAdditionalEvidenceOrStatementsUpload() {
        String xPath = "//p[contains(text(),'Provide any extra evidence or statements')]//preceding-sibling::span";
        return getText(xPath);
    }

    public void clickOnViewResponseBtn() {
        String xPath = "//*[text()[normalize-space()='View response']]";
        try {
            clickOnElement(xPath);
        } catch (Exception e) {
            clickOnElementByJs(xPath);
        }
    }

    public void clickOnBreadcrumbInChargeBackForm() {
        String xPath = "//*[@class='s-breadcrumb']";
        clickOnElement(xPath);
    }

    public void verifyAddressWithCountryBrazil(String typeOfAddress, String shippingSF) {
        String _typeOfAddress = StringUtils.capitalize(typeOfAddress.toLowerCase());
        String addressOnDB = String.join(" ", getListText("//*[normalize-space()='" + _typeOfAddress + "']/ancestor::div[@class='card__section']//p"));
        assertThat(addressOnDB).isEqualTo(shippingSF);
    }

    public void addFieldToTemplate(String field) {
        clickOnElement("(//div[text()[normalize-space()='Name']]/following::button)[1]");
    }

    public void clickBTSaveChange() {
        String xPath = "//button[child::*[normalize-space()='Save changes']]";
        String xPathLoading = "//button[child::*[normalize-space()='Save changes'] and @disabled='disabled']";
        waitElementToBeVisible(xPath);
        waitForElementNotAppear(xPathLoading);
        if (XH(xPath).isEnabled() && !isElementExist(xPathLoading)) {
            clickOnElement(xPath);
        } else {
            waitForElementNotAppear(xPathLoading);
            XH(xPath).waitUntilEnabled().waitUntilVisible().waitUntilClickable().click();
        }
        waitABit(1000);
    }

    public JSONArray readJsonFileAndParseToJsonArray(String file) {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        try {
            String jsonPath = new String(Files.readAllBytes(Paths.get(file)));
            jsonArray = (JSONArray) parser.parse(jsonPath);
            System.out.println(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public String getCountryCodeOrNameFromFile(String file, String country, String type) {
        String code = "", name = "", returnedValue = "";
        JSONArray jsonArray = readJsonFileAndParseToJsonArray(file);
        JSONObject object = new JSONObject();
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            object = (JSONObject) jsonArray.get(i);
            System.out.println(object);
            System.out.println(object.get("name") + " + " + object.get("code"));
            if ("code".equalsIgnoreCase(type)) {
                name = object.get("name").toString();
                if (country.equalsIgnoreCase(name)) {
                    code = object.get("code").toString();
                    size = i;
                    returnedValue = code;
                }
            }
            if ("name".equalsIgnoreCase(type)) {
                code = object.get("code").toString();
                if (country.equalsIgnoreCase(code)) {
                    name = object.get("name").toString();
                    size = i;
                    returnedValue = name;
                }
            }
        }
        return returnedValue;
    }

    public boolean isPrintBaseStore() {
        String xPath = "//*[text()[normalize-space()='Library']]";
        return isElementVisible(xPath, 10);
    }

    public void clickCancelBtn(String btn, int index) {
        XH(xPathBtn("", btn, index) + "[not(@disabled='disabled') or not(@style='display: none;')]").waitUntilVisible().waitUntilEnabled().waitUntilClickable().click();
    }

    public String getProductNameInFulfillmentStatus() {
        String xpath = "//div[@class='unfulfilled-card']//a";
        return getText(xpath);
    }

    public void verifyFulfillmentGroup(String productName, String val) {
        String xpath = "//div[descendant::a[normalize-space()='" + productName + "'] and contains(@class, 'item-details')]//p[contains(text(),'" + val + "')]";
        waitElementToBeVisible(xpath, 10);
        verifyElementVisible(xpath, true);
    }

    public void getQuantityInFulfillmentStatus(String productName, String quantity) {
        String xpath = "//a[normalize-space()='" + productName + "']//ancestor::div[@class='unfulfilled-card']//*[contains(@class,'quantity--desktop')]//*[contains(@class,'quantity') and normalize-space()='" + quantity + "']";
        waitElementToBeVisible(xpath, 10);
        verifyElementVisible(xpath, true);
    }

    public String getSubTotalInFulfillmentStatus() {
        String xpath = "//div[@class='unfulfilled-card__total-price']//span";
        return getText(xpath);
    }

    public void searchOrderPbase(String order) {
        String xpath = "(//input[@id='Search orders' or @type='Search orders' or @placeholder='Search orders' or contains(text(),'Search orders') or preceding-sibling::*[text()[normalize-space()='Search orders']] or preceding::*[text()[normalize-space()='Search orders']] or @name='Search orders'or preceding-sibling::*[text()[normalize-space()='Search orders']]])";
        waitClearAndTypeThenEnter(xpath, order);
        waitForEverythingComplete();
    }

    public String getItemSubTotalInPaymentStatus() {
        String xpath = "//td[text()='Subtotal']/parent::tr//td[2]";
        return getText(xpath);
    }

    public String getShippingMethodInPaymentStatus() {
        String xpath = "//td[text()='Shipping']/parent::tr//td[2]";
        return getText(xpath);
    }

    public float getTotalInPaymentStatus() {
        String xpath = "//td[text()[normalize-space()='Total']]//following-sibling::td//div";
        return getPrice(xpath);
    }

    public float getValInProfitGroup(String label) {
        return getPrice("//div[contains(@class,'profit-section')]//div[child::span[contains(text(),'" + label + "')]]/span[last()]");
    }

    public float getHandlingFeeInYourProfit() {
        String xpath = "//div[contains(@class,'profit-section')]//span[text()='Handling fee']/parent::div/span[2]";
        return getPrice(xpath);
    }

    public float getPaymentFeeInYourProfit() {
        String xpath = "//div[contains(@class,'profit-section')]//span[text()='• Payment fee']/parent::div/span[2]";
        return getPrice(xpath);
    }

    public float getProcessingFeeInYourProfit() {
        String xpath = "//div[contains(@class,'profit-section')]//span[text()='• Processing fee']/parent::div/span[2]";
        return getPrice(xpath);
    }

    public float getBaseCostYourProfit() {
        String xpath = "//div[contains(@class,'profit-section')]//span[text()='Basecost']/parent::div/span[2]";
        return getPrice(xpath);
    }

    public void openHandlingFeeInYourProfit() {
        String xpath = "//div[child::span[text()='Handling fee']]/parent::div/following-sibling::div//span[contains(@class,'s-icon is-small')]";
        clickOnElement(xpath);
    }

    public float getDiscountInPaymentStatus() {
        String xpath = "//*[text()[normalize-space()='Discount']]//ancestor::tr//td/span";
        return getPrice(xpath);
    }

    public String getPaymentStatus() {
        String xpath = "(//div[@class='order-layout__item']//h2[@class='stack-item__title'])[2]";
        return getText(xpath);
    }

    public float getPaidByCustomerInPaymentStatus() {
        String xpath = "//*[text()[normalize-space()='Paid by customer']]//ancestor::tr//td[2]";
        return getPrice(xpath);
    }

    public boolean isShippingFeeInYourProfit() {
        String xpath = "//span[text()='Shipping fee']/parent::div";
        return isElementExist(xpath);
    }

    public float getShippingFeeInYourProfit() {
        String xpath = "//span[text()='Shipping fee']/parent::div//span[2]";
        return getPrice(xpath);
    }

    public float getProfitInYourProfit() {
        String xpath = "//span[text()='Profit']//parent::div/following-sibling::span";
        return getPrice(xpath);
    }

    public String getContentShippingInPaymentStatus() {
        String xpath = "//*[text()[normalize-space()='Shipping']]//ancestor::tr//div/span[2]";
        return getText(xpath);
    }

    public void verifyNotShowShippingFeeInYourProfit() {
        String xpath = "//span[text()='Shipping fee']/parent::div//span[2]";
        verifyElementPresent(xpath, false);
    }

    public float getPriceProductInFulfillmentStatus(String productName) {
        String xpath = "(//a[text()[normalize-space()='" + productName + "']]/ancestor::div[@class='unfulfilled-card__item-details']//div[contains(@class,'unfulfilled-card__price-by-quantity--desktop')]//span)[1]";
        return getPrice(xpath);
    }

    public float getPricePPDiscountInFulfillmentStatus(String productPP) {
        String xpath = "(//a[text()[normalize-space()='" + productPP + "']]/ancestor::div[@class='unfulfilled-card__item-details']//div[contains(@class,'unfulfilled-card__price-by-quantity--desktop')]//span)[2]";
        return getPrice(xpath);
    }

    public float getPriceShippingFreeInPaymentStatus() {
        String xpath = "//*[text()[normalize-space()='Shipping']]//ancestor::tr//div/span[1]//del";
        return getPrice(xpath);
    }

    public void clickOnHandlingFeeExpandArrow() {
        String xPath = "//*[contains(@class,'mdi-arrow-down-drop-circle-outline')]";
        if (isElementVisible(xPath, 3))
            clickOnElement(xPath);
    }

    public String CountRecentOrderInCustomarDetail() {
        String xpath = "//p[text()='Lifetime spent']//following-sibling::p[2]";
        return getText(xpath);
    }

    public void clickOnBtnInMoreAction(String page) {
        String xpath = "//span[text()[normalize-space()='" + page + "']]";
        clickOnElement(xpath);
    }

    public String getTrackingNumberLineItem() {
        String xpath = "//input[@id='tracking-number']";
        return getValue(xpath);
    }

    public void focusOut() {
        String xpath = "//th/span[contains(text(), 'PAYMENT STATUS')]";
        focusClickOnElement(xpath);
    }

    public void showCalculationYourProfit() {
        String xpath = "//h2[text()[normalize-space()='Your Profit']]//parent::div//span[text()='Show calculation']";
        clickOnElement(xpath);
    }

    public int getIndexLineItemGroup(String lineitem) {
        String[] property = lineitem.split(">");
        String xpath = "//a[text()[normalize-space()='" + property[0] + "']]//ancestor::div[contains(@class,'unfulfilled-card__item-details')]//p[text()='" + property[1] + "']//ancestor::section//preceding-sibling::section";
        return countElement(xpath) + 1;
    }

    public void selectFulfillmentService(int index) {
        String xpath = "(//button[child::span[text()[normalize-space()='Fulfill with']]])[1]";
        clickOnElement(xpath);
    }

    public void fulfillWith(String fulfillmentService, int index) {
        String xpath = "(//section[@class='card fulfillment-card fulfillment-card--unfulfilled']//span[text()[normalize-space()='" + fulfillmentService + "']])[" + index + "]";
        clickOnElement(xpath);
    }

    public int getTotalOrderDefaultCurrentStore(String label) {
        try {
            return Integer.parseInt(getText("(//div[child::h4[contains(text(), 'Total orders')]]//following::div//h2)[1]"));
        } catch (Exception e) {
            waitABit(5000);
            return Integer.parseInt(getText("(//div[child::h4[contains(text(), 'Total orders')]]//following::div//h2)[1]"));
        }
    }

    public void clickOut(String label) {
        clickOnElement("//div[child::h4[contains(text(), '" + label + "')]]");
    }

    public int countGroup() {
        String xpath = "//div[@id='fulfillment-section']//section[contains(@class,'fulfillment-card')]";
        return countElement(xpath);
    }

    public String getStatusGroup(int index) {
        String xpath = "//div[@id='fulfillment-section']//section[" + index + "]//h2";
        return getText(xpath);
    }

    public void isShowButtonInOrderDetail(String buttonName, int index, boolean isShow) {
        String xpath1 = "//section[contains(@class,'card fulfillment-card')][" + index + "]//a[text()[normalize-space()='" + buttonName + "']]";
        String xpath = "//section[contains(@class,'card fulfillment-card')][" + index + "]//span[text()[normalize-space()='" + buttonName + "']]";
        if (buttonName.contains("Track shipment")) {
            verifyElementPresent(xpath1, isShow);
        } else {
            verifyElementPresent(xpath, isShow);
        }
    }

    public void verifyFulfillmentStatus(String status) {
        String xpath = "//span[contains(text(), '" + status + "')]";
        try {
            verifyElementVisible(xpath, true);
        } catch (Throwable e) {
            refreshPage();
            waitForEverythingComplete();
            verifyElementVisible(xpath, true);
        }
    }

    public void verifyGroupFulfill(String groupFulfilled, String product, String sku, String quantity, String unitPrice, String buttonCancel, String status) {
        String xpath = "//section[contains(., '" + groupFulfilled + "') and contains(., '(" + quantity + ")')  and contains(., '" + sku + "')]";
        String cancelText = buttonCancel;
        if ("Fulfilled".equals(status)) {
            cancelText = "";
            verifyElementVisible("//section[contains(., 'Edit tracking')]", false);
        }
        String xpath1 = "//section[contains(., '" + product + "') and contains(., '" + unitPrice + "')  and contains(., '" + cancelText + "')]";
        try {
            verifyElementVisible(xpath, true);
            verifyElementVisible(xpath1, true);
        } catch (Throwable e) {
            refreshPage();
            waitForEverythingComplete();
            verifyElementVisible(xpath, true);
            verifyElementVisible(xpath1, true);
        }
    }

    public void verifyTotal(String total, String product) {
        verifyElementVisible("//div[contains(@class, 'unfulfilled-card--fill') and descendant::a[contains(text(), '" + product + "')]]//*[contains(@class, 'total-price')]/*[contains(text(), '" + total + "')]", true);
    }

    public void verifyTimeline(String email, String firstName, String lastName, String text, String status) {
        String xpath = "//div[contains(text(), 'Order confirmation email was sent to " + firstName + " " + lastName + " (" + email + ")')]";
        String xapthFul = "//div[contains(@class, 'timeline')]//div[text()= '" + text + "']";
        try {
            verifyElementVisible(xpath, true);
            if ("Fulfilled".equals(status)) {
                verifyElementVisible(xapthFul, true);
            }
        } catch (Throwable e) {
            refreshPage();
            waitABit(4000);
            verifyElementVisible(xpath, true);
            if ("Fulfilled".equals(status)) {
                verifyElementVisible(xapthFul, true);
            }
        }
    }

    String xpath = "//%s[contains(text(),'%s')]";

    public void clickButton(String buttonName) {
        waitForEverythingComplete();
        clickOnElement(String.format(xpath, "span", buttonName));
    }

    public void verifyTextConfirm(String text, String orderNumber) {
        verifyElementVisible("//p[contains(text(), '" + text + " " + orderNumber + "?')]", true);
    }

    public void verifyTitle(String title) {
        verifyElementVisible(String.format(xpath, "h2", title), true);
    }

    public void verifyProduct(String product) {
        verifyElementVisible(String.format(xpath, "li", product), true);
    }

    public void verifyDiscription(String discription) {
        verifyElementVisible(String.format(xpath, "p", discription), true);
    }

    public void verifyStatusProduct(String status, String product) {
        verifyElementVisible("//section[contains(., '" + status + "') and contains(., '" + product + "')]", true);
    }

    public void verifyButtonCancelFulfillment(String buttonName) {
        verifyElementVisible(String.format(xpath, "span", buttonName), false);
    }

    public void verifyTimelineCancel(String text) {
        String[] orderName = text.split(",");
        String note = orderName[1].replace("@order name@", orderNumber);
        String xpath = "//div[contains(@class, 'timeline-list') and contains(., '" + orderName[0] + "') and contains(., '" + note + "')]";
        try {
            verifyElementVisible(xpath, true);
        } catch (Exception e) {
            refreshPage();
            waitABit(4000);
            verifyElementVisible(xpath, true);
        }
    }

    public void verifyDisplayPopupLiveChat() {
        String xpath = "//span[@data-id='online']//span[contains(text(),'Support is online') and @data-type='ongoing']";
        String xpathDelete = "//span[@class='cc-1asz']";
        if (isElementExist(xpath)) {
            focusClickOnElement(xpathDelete);
            waitUntilElementInvisible(xpath, 5);
        }
    }

    public String getAccountName() {
        String xpath = "//div[child::h2[normalize-space()='Connected accounts']]//following-sibling::div[contains(@class,'payment_provider')]//strong[@class='s-paragraph-bold']";
        return getText(xpath);
    }

    public void clickBTTrackingNumber() {
        String xpath = "//a[contains(text(),'Track shipment')]";
        clickOnElement(xpath);
    }


    //    -----COD-----

    public void checkExistRefundBtn(String expectedState) {
        String refundItemBtn = xPathBtn("", "Refund item", 1);
        if (expectedState.equals("shown")) {
            verifyElementVisible(refundItemBtn, true);
        } else {
            verifyElementVisible(refundItemBtn, false);
        }
    }

    public void checkShownMarkAsPaidBtn(String expectedState) {
        if (expectedState.equals("shown")) {
            verifyElementVisible(xPathBtn("", "Mark as paid", 1), true);
        } else {
            verifyElementVisible(xPathBtn("", "Mark as paid", 1), false);
        }
    }

    public void clickMarkAsPaidBtn() {
        clickBtn("Mark as paid", 1);
        waitForEverythingComplete(10);
    }

    public void clickMarkAsPaidBtnOnPopUp() {
        String xpath = "//div[contains(@class,'s-modal-content')]//*[text()[normalize-space()='Mark as Paid']]";
        clickOnElement(xpath);
        waitForEverythingComplete(15);
    }

    public void waitForShowDataOrder() {
        String xpath = "//div[@id='fulfillment-section']//section";
        waitUntilElementInvisible(xpath, 20);
    }

    public int getIndexGroupResend() {
        String xpath = "//span[text()[normalize-space()='Resend']]//ancestor::section//preceding-sibling::section";
        return countElement(xpath) + 1;
    }


    public String getClaim(int index) {
        String xpath = "//section[contains(@class,'fulfillment-card')][" + index + "]//div[text()[normalize-space()='Claim:']]//a";
        return getText(xpath);
    }

    public float getValInPaymentGroup(String label) {
        String element = "Discount".equals(label) || "Shipping".equals(label) ? "span" : "div";
        return getPrice("//tr[child::td[contains(text(), '" + label + "')]]/td[last()]//" + element);
    }

    public float getDiscountPPC(String product) {
        String xpath = "//div[contains(@class, 'item-details') and descendant::a[normalize-space()='" + product + "']]/div[last()]/div";
        float priceBeforeDiscount = getPrice(xpath + "/span/span[1]/del");
        float priceAfterDiscount = getPrice(xpath + "/span/span[2]");
        int quantity = Integer.parseInt(getText(xpath + "/span/span[last() and contains(@class, 'quantity')]"));
        float discount = (priceBeforeDiscount - priceAfterDiscount) * quantity;
        return discount;
    }

    public float getShippingForSeller(String label) {
        return getPrice("//tr[child::td[contains(text(), '" + label + "')]]/td[last()]//del");
    }

    public float getPaidByCustomer(String label) {
        return getPrice("//tr[child::td[contains(text(), '" + label + "')]]/td[last()]");
    }

    public float getSubTotalInFulfillmentGroup() {
        float subTotal = 0;
        String xpathParent = "//div[@class='unfulfilled-card-container']";
        String xpath = "//*[contains(@class, 'unfulfilled-card__price-by-quantity--desktop')]";
        int count = countElementByXpath(xpath);
        for (int i = 1; i <= count; i++) {
            if (isElementExist(xpathParent + "[" + i + "]" + xpath + "//del")) {
                subTotal += getPrice(xpathParent + "[" + i + "]" + xpath + "//del") * Integer.parseInt(getText(xpathParent + "[" + i + "]" + xpath + "/span[last()]"));
            } else {
                subTotal += getPrice(xpathParent + "[" + i + "]" + xpath + "/span[1]") * Integer.parseInt(getText(xpathParent + "[" + i + "]" + xpath + "/span[last()]"));
            }
        }
        return subTotal;
    }

    public void verifyFreeShippingOnDB(String shipSF, String discountSF) {
        String discountOnDB = getText("//table[contains(@class,'order-details-summary-table')]//td[normalize-space()='Discount']/following-sibling::td//span");
        String shippingOnDB = getText("//table[contains(@class,'order-details-summary-table')]//td[normalize-space()='Shipping']/following-sibling::td//span");
        if (shipSF.equalsIgnoreCase("$0.00")) {
            assertThat(discountOnDB).isEqualTo("-" + shippingOnDB);
        } else {
            assertThat(discountOnDB.split("\\p{Sc}|\\n")[1]).isEqualTo(discountSF.split("\\p{Sc}|\\n")[1]);
        }
    }

    public void waitUntilOrderDetailLoadingCompletely() {
        waitUntilElementVisible("//a[@class='order-link' and normalize-space()='" + orderNumber + "']", 20);
    }

    public void logInfor(String url) {
    }

    public void verifyShippingCarrierNotDisplayed(String carriers) {
        verifyElementVisible("//div[@class='fulfillment__header']//span[text()='" + carriers + "']", false);
    }

    public void selectViewOrderStatus() {
        clickOnElement("//div[@class='s-dropdown-menu']//span[normalize-space()='View order status page']");
        waitForEverythingComplete();
        switchToLatestTab();

    }

    public void clickToRefundButton() {
        String xpath = "//button//span[contains(text(),'Refund')]";
        clickOnElement(xpath);
        waitABit(5000);
    }

    public void inputRefundAmount(String amount) {
        String xpath = "//div[@class='s-flex-item s-flex--fill']//input[@type='number']";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, amount.replace("$", ""));
    }

    public void clickOutPage() {
        clickOnElement("//th/span//div[contains(text(), 'PAYMENT')]");
    }

    public void choiceOrderFulfill() {
        String xpath = "//th[@class='checkbox-all']//label[@class='s-checkbox']";
        try {
            waitElementToBeVisible(xpath);
            checkCheckbox(xpath, true);
        } catch (Exception e) {
            waitForEverythingComplete();
            checkCheckbox(xpath, true);
        }
    }

    public void clickBTFulfill(String fulfill_order) {
        String fulfillWith = "//span[text()[normalize-space()='" + fulfill_order + "']]";
        clickOnElement(fulfillWith);
        waitForEverythingComplete();
    }

    public void xpanOrder() {
        String xpath = "";
        clickOnElement(xpath);
    }

    public String getInfoOrderSB(String productNameSB, String s) {
        String xpath = "";
        return getText(xpath);
    }

    public String getStatusFulfillOrder() {
        String xpath = "";
        return getText(xpath);
    }

    public String getTrackingNumber() {
        String xpath = "";
        return getText(xpath);
    }

    public void verifyDisplayBT(String button) {
        String xpath = "";
        Assert.assertTrue(isElementExist(xpath));
    }

    public void searchByproductName(String productName) {
        String xpath = "";
        waitClearAndTypeThenTab(xpath, productName);
    }

    public void chooseVariant(String variants) {
        String xpath = "";
        List<String> variantList = getListText(xpath);
        for (String variant : variantList) {
            if (variant.equals(variants)) {
                clickOnElement(variant);
            }
        }
    }

    public void chooseApplyThisChange(String status) {
        String xpath = "";
        String xpathStatus = "";
        if (status.equals("true")) {
            verifyCheckedThenClick(xpathStatus, xpath, false);
        } else {
            verifyCheckedThenClick(xpathStatus, xpath, true);
        }
    }

    public void verifyHeader(List<String> act, List<String> exp) {
        for (String header : act) {
            if (!exp.contains(header)) {
                Java6Assertions.fail("dont contains " + header + " header");
            }
        }
    }

    public void clickChoiceStore() {
        clickOnElement("//span[contains(@class, 'current-store-suffix-icon')]");
    }

    public void moveTab(String tabName) {
        clickOnElement("//a[child::span[contains(text(), '" + tabName + "')]]");
    }

    public void selectFirstOrderDispute() {
        checkCheckbox("//tbody/tr[1]//td[contains(@class, 'sb-table')]//label", true);
    }

    public void verifyContentFile(HashMap<String, String> data) {
        for (String header : data.keySet()) {
            if ("capture".equals(data.get("Kind"))) {
                if (data.get(header).isEmpty()) {
                    fail();
                }
            }
        }
    }

    public void waitUntilTheMsgDisplayed(String toastMsg) {
        waitUntilElementVisible("//div[@class='s-notices is-bottom']//div[normalize-space()='" + toastMsg + "]", 8);
    }

    public void openFirstOrderInOrderDashboard() {
        String xpath = "(//tbody//a[contains(@href, '/admin/orders')])[1]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void cancelOrder() {
        String xpath = "//span[normalize-space()='More actions']//parent::button";
        clickOnElement(xpath);
        String cancelOrder = "//span[contains(@class, 'dropdown-item')]//span[contains(@class, 'action-list') and normalize-space() = 'Cancel order']";
        waitElementToBeVisible(cancelOrder);
        clickOnElement(cancelOrder);
        String cancelButton = "//span[normalize-space() = 'Cancel order']//parent::button";
        clickOnElement(cancelButton);
        waitForEverythingComplete();
    }

    public void selectTheNewstOrders() {
        String xpathOrder = "//div[(contains(@class,'sb-tab-panel') and not(contains(@style,'display: none;')))]//a[@class='text-black order-detail-link']";
        int i = 0;
        while (i < 5 && !isElementExist(xpathOrder)) {
            waitABit(10000);
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        waitUntilElementVisible(xpathOrder, 8);
        clickOnElement(xpathOrder);
    }

    public void clickSelectDropDown(String criteria) {
        String xpath = "//div[(contains(@class,'sb-tab-panel') and not(contains(@style,'display: none;')))]//span[@class='sb-button--label']";
        String xpathListCriteria = "//div[(contains(@class,'sb-popover') and not(contains(@style,'display: none;')))]//li[contains(text(),'" + criteria + "')]";
        clickOnElement(xpath);
        clickOnElement(xpathListCriteria);
        waitForEverythingComplete();
    }

    public void searchKeyOnOrderList(String value) {
        String xpath = "//input[contains(@placeholder, 'Search by order name, transaction id')]";
        waitTypeAndEnter(xpath, value);
        verifyElementVisible("//tr[contains(. , '" + value + "')]", true);
    }

    String xpath_lineitem_order = "//div[contains(@class,'card-container') and child::div//p[contains(.,'%s')]/following-sibling::div//p[contains(.,'%s')]]";

    public void verifyPrintFileGeneratedDone(String product, String sku, boolean bol) {
        String xpathGeneratedFile = String.format(xpath_lineitem_order, product, sku) + "//span[@class='s-tag s-mr4' and contains(.,'Print file has been generated')]";
        String xpathGeneneratingFile = String.format(xpath_lineitem_order, product, sku) + "//span[contains(.,'Print file is being generated')]";

        if (bol) {
            int n = 0;
            while (isElementExist(xpathGeneneratingFile) & n < 20) {
                refreshPage();
                waitUntilElementVisible(xpathGeneneratingFile, 20);
                n++;
            }
        }
        assertThat(isElementExist(xpathGeneratedFile)).isEqualTo(bol);
    }

    public void clickActionOfLineItemPrinfile(String product, String sku, String action) {
        String xpathDropDownMenu = String.format(xpath_lineitem_order, product, sku) + "//div[@class='unfulfilled-card']//div[@class='s-dropdown-trigger']/button";
        String xpathItemDrop = String.format(xpath_lineitem_order, product, sku) + "//div[@class='s-dropdown-item p-0 text-left has-link']";
        clickOnElement(xpathDropDownMenu);
        if (action.equalsIgnoreCase("Edit"))
            clickOnElement(xpathItemDrop + "/following-sibling::span");
        else clickOnElement(xpathItemDrop + "/a[text()='" + action + "']");

    }

    public String getFormatPrintfileName(String product, String sku) {
        String xpathLink = String.format(xpath_lineitem_order, product, sku) + "//div[@class='s-dropdown-item p-0 text-left has-link']/a";
        WebElement preview = getDriver().findElement(By.xpath(xpathLink));
        String logoSRC = preview.getAttribute("href");
        String sUrl[] = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(0, namefile.indexOf("?"));
        return namefile;

    }

    public void verifyLinkPreviewOfPrintfile(String fileName) {
        int count = countNumberOfWWindow();
        assertThat(count).isEqualTo(2);
        switchToLatestTab();
        assertThat(getCurrentUrl()).contains(fileName);
        closeBrowser();
        switchToTheFirstTab();
    }

    public List<String> getListCustomer() {
        String xpath = "//*[contains(@class, 'tab-panel') and not(@style)]//table//tr[contains(@class,'order-expanded')]//td[4]/child::div/div";
        return getListText(xpath);
    }

    public float getDesignFee() {
        String xpath = "//div[@class='order-layout__item profit-section']//span[contains(.,'Design fee')]/following-sibling::span";
        return getPrice(xpath);
    }

    public void clickAddPrintAFileOnOrderDetail() {
        String xpath = "//div[contains(@class,'d-flex self-flex-end text-right text-gray400')]//div[contains(.,'Add print file')]";
        waitUntilElementInvisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void importOrder(String data, String orderName, String date) throws IOException {
        String xpathTitle = "//h4[normalize-space()='Import orders by CSV file']";
        waitElementToBeVisible(xpathTitle);
        String xpath = "//label[child::a[child::span[text()='Choose file']]]//input";
        String _filePath = LoadObject.getFilePath("ImportOrder_CSVFile.csv");
        FileWriter fw = new FileWriter(_filePath, false);
        fw.write("Order date,Order name,Variant ID/Variant SKU,Product handle,Product title,Variant title,Line item SKU,Line item price,Quantity, First name,Last name,Email,Phone number,Address,City,Zip/Postal code,State/Region/Province,Country");
        if (data.contains("@OrderDate") && data.contains("@OrderName")) {
            fw.write(data.replace("@OrderName", orderName));
            fw.write(data.replace("@OrderDate", date));
        } else {
            fw.write(data);
        }

        fw.flush();
        fw.close();
        chooseAttachmentFile(xpath, "ImportOrder_CSVFile.csv");

    }

    public String getOrder() {
        String xpath = "";
        return getTextByJS(xpath);
    }

    public String getTitleOrderImport() {
        String xpath = "";
        return getTextByJS(xpath);
    }

    public String getError() {
        String xpath = "";
        return getText(xpath);
    }

    public String getTimlineImport() {
        String xpath = "";
        return getText(xpath);
    }

    public void moveToPage(String name) {
        clickOnElement("//*[text()[normalize-space()='" + name + "']]");
    }

    public void refundItemQuantity(String btnName) {
        clickOnElement("//button[child::*[contains(text(), '" + btnName + "')]]");
    }

    public void enterRefundedQuantity(String product, String quantity) {
        enterInputFieldThenEnter("//div[contains(@class , 'item-details') and contains(., '" + product + "')]", "number", quantity, 1);
    }

    public String getShippingFeeInOrder() {
        String xpath = "//td[text()='Shipping']/parent::tr//td[3]//span";
        return getText(xpath);
    }

    public void clickFilterLabel(String label) {
        clickOnElement("//*[text()[normalize-space()= '" + label + "']]");
    }

    public void verifyNotDisplayTextName(String text) {
        verifyTextPresent(text, false);
    }

    public void verifyDisplayText(String text) {
        verifyTextPresent(text, true);
    }

    public void clickText(String text) {
        waitUntilElementVisible("//span[contains(text(),'" + text + "')]", 3);
        clickOnElement("//span[contains(text(),'" + text + "')]");
    }

    public String getTextNewClaim() {
        waitUntilElementVisible("//div[@class='claim__heading title']", 3);
        return getText("//div[@class='claim__heading title']");

    }

    public String getTextClaimList() {
        waitUntilElementVisible("//h2[@class='warehouse__heading__warehouse__title']", 3);
        return getText("//h2[@class='warehouse__heading__warehouse__title']");

    }

    public String getTextOrderNameInInput() {
        String xpath = "//input[@placeholder='Search order, claim']";
        return getValue(xpath);

    }

    public String getTextHeaderClaimDetailPage() {
        String xpath = "//div[@class='claim__heading title']//div";
        return getText(xpath);
    }

    public List<String> getTextRefundOrder() {
        String xpath = "//div[@class='content-body']";
        return getListText(xpath);
    }

    public void clickViewInvoice(String text) {
        String xpath = "//div[text()='" + text + "']//parent:: div//span[contains(text(),'View invoice')]";
        clickOnElement(xpath);
    }

    public String getTextClaimName() {
        String xpath = "//table/tbody//tr[1]//td[@class='name']";
        return getText(xpath);
    }

    public void clickClaimName() {
        String xpath = "//table/tbody//tr[1]//td[@class='name']";
        clickOnElement(xpath);
    }

    public void verifyTaxLineInOrder() {
        String xpathBlockTax = "//tr[child::td[normalize-space()='Tax']]";
        String xpathTotalTax = xpathBlockTax + "//td[3]";
        verifyElementVisible(xpathBlockTax, true);
        float totalTax = 0.00f;
        totalTax = removeCurrency(getText(xpathTotalTax));
        assertThat(totalTax).isEqualTo(taxAmount);
    }

    public void GetTrackingNumberOnShopbase(String Page) {

        String xpathIcon = Page.equals("Fulfill order") ? "(//button[contains(@class,'fulfillment-btn__active')])[1]"
                : "//button[normalize-space()='Get tracking number']";
        waitElementToBeClickable(xpathIcon);
        clickOnElement(xpathIcon);
        waitForEverythingComplete();
        String xpathBtnConfirm = "//button[normalize-space()='Confirm']";
        clickOnElement(xpathBtnConfirm);
        String xpathBtnClose = "//button[@class='s-button']";
        clickOnElement(xpathBtnClose);
        waitForEverythingComplete();
    }

    public void verifyOrderDetailAfterGetTrackingNumber() {
        String textTooltip = "You requested to get tracking number before it actually arrive to warehouse";
        String xpathTooltip = "//img[@alt = 'request-tracking']";
        int i = 0;
        while (i <= 10 && !isElementExist(xpathTooltip)) {
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        hoverOnElement(xpathTooltip);
        verifyTextPresent(textTooltip, false);
        verifyTextPresent("Track shipment", true);
        verifyTextPresent("Edit tracking", true);
        verifyTextPresent("Cancel fulfillment", true);
        verifyTextPresent("Mark as fulfilled", false);
        String nameFulfill = getText("//section[contains(@class,'fulfillment-card')]//div[contains(text(),'#')]");
        String logTimeLine = getText("//div[contains(@class,'timeline-list')]//div[contains(text(),'gets tracking number')]");
        logTimeLine.contains(nameFulfill);
    }

    public String getTNOnOrderDetail() {
        clickBtn("Edit tracking", 1);
        String xpath = "//input[@id='tracking-number']";
        return getTextByJS(xpath);
    }

    public void verifyStatusOfOrderAfterCancelFulfillment(String status) {
        String xpath = "//span[contains(@class,'is-warning')]//span";
        assertThat(getText(xpath)).isEqualTo(status);
        verifyTextPresent("canceled fulfillment via PlusHub", true);
    }

    public void clickCheckbox(String label, boolean isBoolean) {
        String xpath = "//*[child::*[text()[normalize-space()='" + label + "']]]";
        checkCheckbox(xpath, isBoolean);
    }

    public void verifyFulfillmentGroupAfterRefund(String product, int refundedQuantity, boolean isRestock) {
        verifyElementVisible("//section[contains(., 'Unfulfilled') and contains(., '(" + refundedQuantity + ")') and contains(., '" + product + "')]", isRestock);
    }

//    public void verifyTrachkingNumberInOrderDetail() {
//        verifyElementText("//a[contains(text(),'" + trackingNumber + "')]", trackingNumber);
//    }

    public void verifyTrackingNumberInOrderDetail() {
        isElementExist("//*[contains(text(),'Tracking number')]/a");
    }

    public void clickCheckboxFilter(String label, String item, boolean isBoolean) {
        String xpathLabelFilter = "//div[contains(@class,'sb-collapse-item sb-p-medium') and descendant::*[text()[normalize-space()= '" + label + "']]]";
        String xpath = xpathLabelFilter + "//*[child::*[text()[normalize-space()='" + item + "']]]//ancestor::*[contains(@class, 'sb-selection-item')]/label";
        checkCheckbox(xpath, isBoolean);
    }

    public void clickOnAcceptButton() {
        String xpath = "//button//span[contains(text(),'Accept')]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
        waitForPageLoad();
    }

    public void clickIconAddTag() {
        clickOnElement("//span[@class='s-icon grey-light is-small']//i");
    }

    public void clickAddTagToLineItem() {
        clickOnElement("//span[contains(text(),'Add tag to line items')]");
    }

    public void InputTextTagItem(String textTag) {
        waitClearAndTypeThenEnter("//input[@placeholder='Add a tag']", textTag);
    }

    public void verifyTagLineItem() {
        isElementExist("//*[@class = 'card__section']//span[contains(@class, 's-tag')]/span");
    }

    public void verifyPrintFileNoGenerate(String product, String sku, boolean bol) {
        String xpathNoGenerateFile = String.format(xpath_lineitem_order, product, sku) + "//span[@class='cursor-pointer is-link mg-20' and contains(.,'Generate print file')]";
        assertThat(isElementExist(xpathNoGenerateFile)).isEqualTo(bol);
    }

    public String getOrderStatusOnDetail() {
        String xpath = "//div[@class='title-bar__orders-show-badge']//span[contains(@class,'s-tag hide-when-printing is')]";
        String status = getText(xpath);
        return status;
    }

    public void clickButtonGenerateOnLineItmeOrder() {
        String xpath = "//button[child::span[contains(text(),'Generate')]]";
        waitUntilElementInvisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void clickGeneratePrintFileOnOrder() {
        String xpath = "//div[contains(@class,'d-flex self-flex-end')]//descendant::div[normalize-space()='Generate print file']";
        waitUntilElementInvisible(xpath, 10);
        clickOnElement(xpath);
    }

    public String xpathVerifyGenerateOnLineItem() {
        return xpath;
    }

    public boolean isGenerateOnLineItemExist() {
        String xpath = "//div[contains(@class,'d-flex self-flex-end')]//descendant::div[normalize-space()='Generate print file']";
        return isElementExist(xpath, 10);
    }

    public void clickActionOrder(String action) {
        String xpath = "//div[@class='s-dropdown-trigger' and descendant::span[normalize-space()='Action']]";
        String xpath_action = "//div[contains(@class,'s-dropdown-menu')]//span[contains(text(),'" + action + "')]";
        String xpath_confirm = "//div[@class='s-animation-content s-modal-content']//button[child::span[normalize-space()='Confirm']]";
        String xpath_hold = "//div[@class='s-animation-content s-modal-content']//button[child::span[normalize-space()='Hold']]";
        clickOnElementByJs(xpath);
        clickOnElementByJs(xpath_action);
        waitABit(1000);
        if (isElementExist(xpath_confirm)) {
            clickOnElementByJs(xpath_confirm);
        } else {
            clickOnElementByJs(xpath_hold);
        }
        waitForEverythingComplete();
    }

    public void clickInconDotInOrder(String product, String sku) {
        String xpath = String.format(xpath_lineitem_order, product, sku) + "//button//i";
        clickOnElement(xpath);

    }

    public String getProductCost() {
        String el = "//span[text()='Product cost']/parent::div//following-sibling::span";
        return getText(el);
    }

    public void clickShowcalculation() {
        clickOnElement("//span[text()='Show calculation']");
    }

    public void selectOrderInOrdersList() {
        String el = "(//a[contains(@class,'order-detail-link')]//ancestor::tbody//label[contains(@class,'sb-checkbox')]//span)[1]";
        clickOnElement(el);
    }

    public void clickBtnActionInOrdersList() {
        String el = "(//span[text()[normalize-space()='Actions']]/parent::button)[2]";
        clickOnElement(el);
    }

    public void selectAction(String action) {
        String el = "(//li[text()[normalize-space()='" + action + "']])[2]";
        clickOnElement(el);
    }

    public void selectTabInOrdersList(String tab) {
        String el = "//div[contains(@id,'tab')]//*[contains(text(),'" + tab + "')]";
        clickOnElementByJs(el);
    }

    public void verifyOrderShow(boolean isShow) {
        String el = "(//a[contains(@class,'order-detail-link')])[1]";
        verifyElementPresent(el, isShow);
    }

    public String getStatusOrdersAfterHold(int index) {
        String el = "//div[@id='fulfillment-section']//section[" + index + "]//h2/parent::div//following-sibling::div//span[@class='s-icon is-small']//following-sibling::span";
        return getText(el);
    }

    public void verifyButtonIsShow(String button) {
        String el = "//button[child::span[text()[normalize-space()='" + button + "']]]";
        verifyElementPresent(el, true);
    }

    public void verifyPopupCanNotHold() {
        String el = "//div[text()[normalize-space()='Can not hold orders']]//ancestor::div[contains(@class,'sb-popup__container')]";
        verifyElementPresent(el, true);
    }

    public int getCountTabInOrdersList() {
        String el = "//div[contains(@class,'sb-tab-navigation__item')]";
        return countElement(el);
    }

    public void clickCriteria() {
        String el = "//div[contains(@class,'sb-tab-panel') and not(contains(@style,'display: none;'))]//span[text()[normalize-space()='Order name']]/parent::button";
        clickOnElement(el);
    }

    public void selectCriteria(String criteria) {
        int i = getCountTabInOrdersList();
        String el = "(//div[contains(@class,'sb-select-menu')]//li[text()[normalize-space()='" + criteria + "']])[" + i + "]";
        clickOnElement(el);
    }

    public void selectOptionEditProduct(String optionEdit) {
        waitForEverythingComplete();
        clickOnElement("//span[contains(text(),'" + optionEdit + "')]");
    }

    public void enterQuantityEdit(String quantity) {
        waitClearAndTypeThenEnter("//div[contains(@class,'sb-popup__body')]//input", quantity);
    }

    public String getOrderNameFirst() {
        String xpath = "(//div[contains(@class,'order-line')]//div)[1]";
        return getText(xpath);
    }

    public void verifyNotResult(String expect) {
        verifyTextPresent(expect, true);
    }


    public void verifyStatusOrderAfterUpdate(String statusOrder) {
        isElementExist("//span[contains(text(),'" + statusOrder + "')]");
    }

    public void byMoreItemInStatusPage() {
        waitForEverythingComplete();
        String xpath = "//*[text()[normalize-space()='Pay now']]";
        waitElementToBeVisible(xpath, 30);
        clickOnElement(xpath);

    }

    public void clickbuttonSendInvoice(String buttonName) {
        waitForEverythingComplete();
        clickOnElement("//button//span[contains(text(),'" + buttonName + "')]");

    }

    public void searchProduct(String productName, String changeProduct) {
        waitClearAndTypeThenEnter("//div[contains(@class,'sb-absolute')]//input", productName);
        clickOnElement("//div[contains(text(),'" + changeProduct + "')]");
    }

    public void clickAddProductFilter() {
        clickOnElement("//div[contains(@class,'current-search-add-filter')]//span");
    }

    public void inputFilterCollection(String collection) {
        waitClearAndType("//input[@placeholder='Enter collection(s)']", collection);
    }

    public void selectCheckbox(String collection) {
        clickOnElement("//*[text()[normalize-space()='" + collection + "'] and contains(@class, 'tooltip__reference')]");
    }

    public void inputFilterTag(String tag) {
        waitClearAndType("//input[@placeholder='Enter tag(s)']", tag);

    }

    public void sortOrderListByField(String sortType, String field) {
        waitForEverythingComplete(10);
        String btnSortType = "";
        if (sortType.equalsIgnoreCase("descending"))
            btnSortType = "Down";
        else btnSortType = "Up";
        String xpathBtnSort = "//div[contains(@class,'scrollable-y')]//*[text()='" + field + "']/following-sibling::div//span[descendant::*[contains(@id,'" + btnSortType + "')]]";
        clickOnElementByJs(xpathBtnSort);
    }

    public String getQuantityOfOrderList() {
        waitForEverythingComplete();
        String xpath = "//div[contains(@class,'s-pagination')]//*[contains(text(),'1-50 of')]";
        String quantityOfOrderList = (getText(xpath).split("\\s+"))[2];
        return quantityOfOrderList;
    }

    public String getQuantityOfOrderOnTab(String tab) {
        String xpath = "//div[contains(@id,'tab')]//*[contains(text(),'" + tab + "')]";
        String lineText = getText(xpath);
        String quantityOfOrderOnTab = "";

        // Define the pattern
        // Find string is number in original string
        Pattern pattern = Pattern.compile("([\\d]+)");
        // Create matcher object
        Matcher m = pattern.matcher(lineText);

        if (m.find())
            quantityOfOrderOnTab = m.group(1);
        return quantityOfOrderOnTab;
    }

    public int getIndexTab(String tab) {
        String xpath = "//div[child::div[contains(text(),'" + tab + "')]]/preceding-sibling::div";
        int index = countElement(xpath) + 1;
        return index;
    }

    public void enterOrderThenEnter(String orderName, int index) {
        String xpath = "(//input[@placeholder='Search by order name, transaction id, line item name'])[" + index + "]";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenEnter(xpath, orderName);
    }

    public void searchFraudFilter(String filterName) {
        String textboxSearch = xPathInputFieldWithLabel("", "Search filter name", 1);
        waitClearAndTypeThenEnter(textboxSearch, filterName);
        waitForEverythingComplete();
        waitUntilInvisibleLoading(8);
    }

    public void selectCheckboxFraudFilter(String labelName) {
        String xPathStatus = "//td[contains(normalize-space(),'" + labelName + "')]/preceding::input[contains(@type,'checkbox')][last()]";
        String xPathCheckbox = "//td[contains(normalize-space(),'" + labelName + "')]/preceding::span[contains(@class,'s-check')][last()]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, true);
    }

    public void deleteFraudFilter(String filterName) {
        String xpathFilterName = "//td[normalize-space()='" + filterName + "']";
        if (isElementVisible(xpathFilterName, 15)) {
            String xPathAction = "//button/span[contains(normalize-space(),'Actions')]";
            String xPathDeleteFilter = "//span[contains(normalize-space(),'Actions')]//following::span[contains(normalize-space(),'Delete filter')]";
            selectCheckboxFraudFilter(filterName);
            clickButton(xPathAction);
            clickOnElement(xPathDeleteFilter);
        }
    }

    public void chooseRuleAction(String action) {
        String xPathAction = "//input[@type='radio']//following::span[contains(normalize-space(),'" + action + "')]";
        clickOnElement(xPathAction);
    }

    public boolean isFraudRuleDdDisplayed() {
        return isElementExist("//p[normalize-space()='Select rules']//following::select");
    }

    public void selectRuleName(String ruleName) {
        String xPath = "//p[contains(text(),'Select rules')]//following::select[contains(normalize-space(),'" + ruleName + "')]";
        clickOnElement(xPath);
    }

    public void selectRuleCondition(String condition) {
        String xPath = "//select//following::select/option[contains(normalize-space(),'" + condition + "')]";
        clickOnElement(xPath);
    }

    public void inputValue(String value) {
        String xPath = "//select//following::select//following::select[contains(normalize-space(),'" + value + "')]";
        clickOnElement(xPath);
    }

    public String getQuantityOfOrderAffect(String filterName) {
        String quantityOfOrderAffect = getTextValue("//tr[contains(.,'"+filterName+"')]/td[5]");
        return quantityOfOrderAffect;
    }
}
