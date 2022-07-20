package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountPage extends SBasePageObject {
    public static String logDetail = "";
    public static String currentPlan = "";
    public static String phoneNumber = "";
    String passWord = LoadObject.getProperty("user.pwd");


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void clickBTUpgradePlan() {
        String xpath = "//span[contains(text(),'Upgrade plan')]";
        clickOnElement(xpath);
    }

    public void clickBTTypePlan(String typePlan) {
        String xpath = "//div[@class='period-wrapper']/div[contains(text(),'" + typePlan + "')]";
        clickAndClearThenType(xpath, typePlan);
    }

    public String choosePackage(String typePlan, String typeText) {
        String price = "";
        String xpath = "//div[@class='pricing-list s-mt32']//following::button";
        List<WebElement> items = getDriver().findElements(By.xpath(xpath));
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getText().contains(typeText)) {
                if (typePlan.equals("Monthly")) {
                    price = getPricePackage(i);
                } else {
                    price = getPricePackageAnnually(i);
                }
                items.get(i).click();
                break;
            }
        }
        return price;
    }

    private String getPricePackageAnnually(int i) {
        return getTextValue("(//div[@class='s-mt16 text-gray400 text-normal'])[" + (i + 1) + "]").replace("billed annually", "");
    }

    private String getPricePackage(int i) {
        return getTextValue("(//span[@class='number price'])[" + (i + 1) + "]");
    }

    public void inputDiscount(String valueCoupon) {
        String xpath = "//div[@class='s-input s-input-group s-input-group--append']//input";
        inputSlowly(xpath, valueCoupon);
        waitABit(2000);
    }

    public void clickBTApply() {
        String xpath = "//button[child::span[contains(text(),'Apply')]]";
        String btnLoading = "//button[child::span[contains(text(),'Apply')] and contains(@class,'is-loading')]";
        if (XH(xpath).isEnabled()) {
            clickOnElement(xpath);
            waitForElementNotAppear(btnLoading);
        }
    }

    public void clickBTConfirmChanges() {
        String xpath = "//button[@class='s-button button-start is-primary']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);

    }

    public String getPriceBeforeInputCoupon() {
        return getTextValue("//div[@class='section sticky-top s-mb24']/div[3]/div/span");
    }

    public String getPriceAfterInputCoupon() {
        return getTextValue("//div[@class='section sticky-top s-mb24']/div[3]/div/span[1]");
    }

    public String getError() {
        return getTextValue("//div[@class='s-form-item__error']");
    }

    public String getTextInfo() {
        return getTextValue("//div[@class='section sticky-top s-mb24']/p[1]");

    }

    public String getTextTheNextSubscriptionBill() {
        return getTextValue("//span[@slot='time']");
    }

    public void clickBtnPlanType(String planType) {
        String xpath = "//*[normalize-space()='" + planType + "']";
        waitUntilElementVisible(xpath, 8);
        clickOnElement(xpath);
    }


    public void choosePlan(String packageName) {
        String xpath = "//div[child::div[normalize-space()='" + packageName + "']]//following-sibling::div//button[child::span[normalize-space()='Choose this plan']]";
        clickOnElement(xpath);
    }


    public int getRowInvoice(String type, String shopname, String content, String status) {
        int rowIndex = 0;
        String xpath = "(//div[contains(@class,'balance-manager')]//table//tbody//tr[" +
//                "child::td[descendant::*[normalize-space()='" + type + "']] " +
                "child::td[descendant::*[normalize-space()='" + shopname + "']] " +
                "and child::td[descendant::*[normalize-space()='" + content + "']]  " +
                "and child::td[descendant::*[normalize-space()='" + status + "']]])[1]";
        if (isElementExist(xpath)) {
            rowIndex = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return rowIndex;
    }

    public Double getActualAmountRefund(int rowIndex, int colIndex) {
        String amount = getText("//div[contains(@class,'balance-manager')]//table//tbody//tr[" + rowIndex + "]//td[" + colIndex + "]");
        return Double.parseDouble(amount.replace("$", ""));
    }

    public int getColumOfInvoiceTableByLabel(String colName) {
        return countElementByXpath("//div[contains(@class,'balance-manager')]//table//thead//th[child::*[normalize-space()='" + colName + "']]//preceding-sibling::th") + 1;
    }

    public String getPriceOfCurrentPackage() {
        return getTextValue("//span[text()[normalize-space()='Current plan']]//preceding::span[@class='number price']");
    }

    public boolean ableToSelectPackage(String packageName) {
        String xpath = "//div[child::div[normalize-space()='" + packageName + "']]//following-sibling::div//button[child::span[normalize-space()='Choose this plan']]";
        return isElementVisible(xpath, 5);
    }

    public void clickViewActivityLog() {
        String xpath = "//span[text()[normalize-space()='View all']]//parent::button";
        clickOnElementByJs(xpath);
    }

    public void filterActivityLog(String module, String activity) {
        String xModule = "//span[text()[normalize-space()='Category']]";
        String xActivity = "//span[text()[normalize-space()='Activity']]";
        String xModuleValue = "//span[@class='s-control-label' and contains(text(),'" + module + "')]";
        String xActivityValue = "//span[@class='s-control-label' and contains(text(),'" + activity + "')]";
        clickOnElement(xModule);
        clickOnElement(xModuleValue);
        clickOnElement(xActivity);
        clickOnElement(xActivityValue);
        clickOnElement(xActivity);
    }

    public void clickOnLastestActivity(String detail) {
        String xpath = "//tr[1]//span[text()[normalize-space()='" + detail + "']]";
        clickOnElement(xpath);
    }

    public void verifyActivityLog(String module, String activity, String detail) {
        String xModule = "//span[text()[normalize-space()='CATEGORY']]//ancestor::thead//following-sibling::tbody//tr[1]//td[2]//span";
        String xActivity = "//span[text()[normalize-space()='CATEGORY']]//ancestor::thead//following-sibling::tbody//tr[1]//td[3]//span";
        String xDetail = "//span[text()[normalize-space()='CATEGORY']]//ancestor::thead//following-sibling::tbody//tr[1]//td[4]//span";

        assertThat(module).isEqualTo(getTextValue(xModule));
        assertThat(activity).isEqualTo(getTextValue(xActivity));
        assertThat(detail).isEqualTo(getTextValue(xDetail));

    }

    public void getLogDetail() {
        String xpath = "//tr[@class='activity-detail is-expanding']//pre";
        focusClickOnElement(xpath);
        logDetail = getTextByJS(xpath).replaceAll("\\{\n", "").replaceAll("\n}", "").trim();

    }

    public void verifyLogDetail(String attribute, String value) {
        String xpath = "//tr[@class='activity-detail is-expanding']//pre";
        String[] detail = getTextByJS(xpath).replaceAll("\\{\n", "").replaceAll("\n}", "").trim().split(",\n");
        for (int i = 0; i < detail.length; i++) {
            if (detail[i].contains(attribute)) {
                System.out.println("Attribute: " + detail[i]);
                String val = detail[i].split(":")[1].replaceAll("\"", "").trim();
                assertThat(value).isEqualTo(val);
            }
        }

    }

    public void getCurrentPlan() {
        String xpath = "//p[text()='Current plan']//following-sibling::p";
        String[] plan = getText(xpath).split("/");
        currentPlan = plan[0].trim();
    }

    public void closeStore() {
        String xClose = "//span[text()[normalize-space()='Close store']]";
        String xPassword = "//input[@type='password']";
        String xConfirm = "//span[text()[normalize-space()='Confirm']]";
        String xConfirm2 = "//button[@class='s-button btn s-button is-primary is-default']//span[text()[normalize-space()='Close store']]";

        clickOnElement(xClose);
        waitClearAndType(xPassword, passWord);
        clickOnElement(xConfirm);
        clickOnElement(xConfirm2);
        waitABit(5000);
    }

    public void reopenStore() {
        String xLogback = "//span[text()[normalize-space()='Log back in my store']]";
        String xConfirm = "//span[text()[normalize-space()='Confirm']]";
        clickOnElement(xLogback);
        waitElementToBeVisible(xConfirm);
        clickOnElement(xConfirm);
        waitABit(10000);
    }

    public void navigateToProfilePage() {
        String xPath = "//div[@class='d-flex flex-column']";
        String xPath_Balance = "//div[contains(text(),'Profile')]";
        clickOnElement(xPath);
        if (isElementVisible(xPath_Balance, 5))
            clickOnElementByJs(xPath_Balance);
        waitForEverythingComplete();
    }

    public void changeFirstNameAndLastName() {
        Date date = new Date();
        long timestamp = date.getTime() / 1000;
        enterInputFieldWithLabel("First name", "QA First name " + timestamp);
        enterInputFieldWithLabel("Last name", "Last name " + timestamp);
        saveSetting();
    }

    public void changePhoneNumber() {
        Date date = new Date();
        long timestamp = date.getTime() / 1000;
        phoneNumber = Long.toString(timestamp);
        String xpath = "//label[text()='Phone']//parent::div//div[@class='s-input']//input";
        String save = "//span[text()='Save']";
        waitABit(3000);
        waitClearAndType(xpath, phoneNumber);
        clickOnElement(save);
    }

    public void goToViewHistoryPage() {
        String xpath = "//button[child::span[normalize-space()='View history']]";
        waitElementToBeVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public String getTotalActivityLogByAPI() {
        String shop = LoadObject.getProperty("shop");
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/action-progress/activity-logs.json?access_token=" + accessToken;
        JsonPath rs = getAPI(url);
        String value = rs.get("total").toString();
        return value;
    }

    public void changeFirstNameTo(String firstName) {
        enterInputFieldWithLabel("First name", firstName);
        saveSetting();
    }

    public void clickSwitchToAnotherShop() {
        clickOnElement("//span[normalize-space()='Switch to another shop']");
    }

    public void clickAccountInTheSelectPlan() {
        clickOnElement("//li[normalize-space()='Account']");
    }

    public void verifyDisplayReopenStoreWhenStoreNotEndSub() {
        verifyElementPresent("//h3[text()='a $10 limited offer credit']", true);
    }
}

