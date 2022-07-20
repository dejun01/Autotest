package opencommerce.balance.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class BalancePage extends SBasePageObject {
    public BalancePage(WebDriver driver) {
        super(driver);
    }

    public void accessToBalancePage() {
        String xPath = "//div[@class='d-flex flex-column']";
        String xPath_Balance = "//div[contains(text(),'Balance')]";
        clickOnElement(xPath);
        if (isElementVisible(xPath_Balance, 5))
            clickOnElementByJs(xPath_Balance);

    }

    public String getCurrentAvailableBalance() {
        String availableBalance = "";
        String xPath = "//h4[contains(text(),'Total current available')]//parent::div//following-sibling::h4";
        availableBalance = getTextValue(xPath);
        return availableBalance;
    }

    public String getAvailableSoon() {
        String availableBalance = "";
        String xPath = "//h4[text()[normalize-space()='Available soon']]/parent::div/followingsibling::h4";
        if (isElementVisible(xPath, 20))
            availableBalance = getTextValue(xPath);
        return availableBalance;
    }

    public void clickOnTopUpBtn() {
        String xPath = "//span[text()[normalize-space()='Top up']]";
        clickOnElement(xPath);
    }

    public void clickOnViewHistoryBtn() {
        String xPath = "//span[text()[normalize-space()='View history']]";
        clickOnElement(xPath);
    }

    public void selectTopUpAmt(String amt) {
        String xPath = "//div[contains(@class,'blocktopupitem')]/div[text()[normalize-space()='" + amt + "']]";
        if (isElementVisible(xPath, 5)) {
            clickOnElement(xPath);
        }
    }

    public void clickOnConfirmTopUpBtn() {
        String xPath = "//button/span[text()[normalize-space()='Confirm top up']]";
        clickOnElement(xPath);
        waitForEverythingComplete();
    }

    public void clickOnRequestPayoutBtn() {
        String xPath = "//button/span[text()[normalize-space()='Request payout']]";
        clickOnElement(xPath);
    }

    public void clickOnViewPayoutsBtn() {
        String xPath = "//button/span[text()[normalize-space()='View payouts']]";
        clickOnElement(xPath);
    }

    public void inputPayoutamt(String amt) {
        String xPath = "//input[@placeholder='Enter amount to request payout']";
        waitClearAndType(xPath, amt);
    }

    public void clickOnSendRequestBtn() {
        String xPath = "//span[text()[normalize-space()='Send request']]";
        if (isElementVisible(xPath, 10)) {
            clickOnElement(xPath);
        }
    }

    public String getAutoTopupAmt() {
        String xPath = "//input[@type='number']";
        String autoTopupAmt = getTextValue(xPath);
        return autoTopupAmt;
    }

    public void verifyBalanceHistory(String type, String source, String shopName, String detail, String amount, String fee, String net, String status) {
        String type_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/precedingsibling::td[4]/span[text()[normalize-space()='" + type + "']]";
        String source_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/precedingsibling::td[3]/span[text()[normalize-space()='" + source + "']]";
        String shopName_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/precedingsibling::td[2]/span[text()[normalize-space()='" + shopName + "']]";
        String detail_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]";
        String amount_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/followingsibling::td[1]/span[text()[normalize-space()='" + amount + "']]";
        String fee_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/followingsibling::td[2]/span[text()[normalize-space()='" + fee + "']]";
        String net_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/followingsibling::td[3]/span[text()[normalize-space()='" + net + "']]";
        String status_xPath = "(//table//tbody//tr/td[5]/div[text()[normalize-space()='" + detail + "']])[1]/parent::td/followingsibling::td[4]//span[text()[normalize-space()='" + status + "']]";

        verifyElementPresent(type_xPath, true);
        verifyElementPresent(source_xPath, true);
        verifyElementPresent(shopName_xPath, true);
        verifyElementPresent(detail_xPath, true);
        verifyElementPresent(amount_xPath, true);
        verifyElementPresent(fee_xPath, true);
        verifyElementPresent(net_xPath, true);
        verifyElementPresent(status_xPath, true);
    }

    public boolean isStayAtViewHistoryScreen() {
        String xPath = "//span[contains(text(),'View history')]";
        boolean result;
        result = isElementVisible(xPath, 10);
        return result;
    }

    public void selectStore(String shopName) {
        String xpath = "//div[@class='sidebar sidebar-filter open']//span[text()='" + shopName + "']";
        clickOnElement("//p[text()='Shop Domain']");
        clickOnElement(xpath);
    }
}
