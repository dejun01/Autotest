package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing;

import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RequestListPage extends BFlowPageObject {
    public RequestListPage(WebDriver driver) {
        super(driver);
    }

    public void enterQuotation(String keyword) {
        waitTypeAndEnter("//input[@placeholder='Search by Quotation id, Product name, source URL']", keyword.replace("https://", ""));
    }

    public void verifyShowAll() {
        verifyElementPresent("//tr[contains(@class,'ant-table-row')]", true);
    }

    public int countResult(String quotation) {
        return countElementByXpath("//p[text()='" + quotation + "']//ancestor::tr[contains(@class,'ant-table-row')]");
    }

    public void verifyQuotation(String quotation, int i) {
        verifyElementPresent("(//p[text()='" + quotation + "']//ancestor::tr[contains(@class,'ant-table-row')])[" + i + "]", true);
    }

    public String getMessRequestSucc() {
        return getText("//div[contains(@class,'success')]");
    }

    public String getQuotationID(int i) {
        return $("(//tr[contains(@class,'ant-table-row')]//p)[" + i + "]").getTextValue();
    }

    public int countItem(String quotationID) {
        return countElementByXpath("//p[text()='" + quotationID + "']//ancestor::tr[contains(@class,'ant-table-row')]");
    }

    public void verifyLinkProductInQuotation(String quotationID, String link) {
        String actualLink = getAttributeValue("//table//tbody//tr[descendant::*[text()='" + quotationID + "']]//a", "href");
        System.out.println(actualLink);
        assertThat(actualLink).isEqualToIgnoringCase(link);
    }

    public String getValueLink() {
        return getTextValue("//input[@placeholder='https://aliexpress.com/item/123456.html?']");
    }

    public String getQuotationIDByLinkProduct(String link) {
        String xpath = "(//div[@class='ant-table-wrapper']//table//tr[descendant::*[contains(@href,'" + link.replaceAll("https://", "").replaceAll("www.", "") + "')]])[1]/td[1]";
        return getText(xpath);
    }

    public void verifyInformationQuotationByColName(String colName, int row, String value) {
        int col = getColIndexByColName("//div[@class='ant-table-content']", colName);
        String actValue = getTextInTable("//div[@class='ant-table-content']", col, row);
        System.out.println(actValue);
        assertThat(actValue).isEqualTo(value);
    }

    public int getRowVariant(String variant) {
        int index = 0;
        String xpath = "//div[@class='ant-table-body-outer']//table[@class='ant-table-fixed']/tbody/tr[child::td[descendant-or-self::*[normalize-space()='" + variant + "']]]";
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return index;
    }

    public void verifyPriceByQuantity(String colNameAndQuantity, int indexRow) {
        String value[] = colNameAndQuantity.split(",");
        String xpath = "//div[@class='ant-table-content']//div[@class='ant-table-body']";
        int col = getColIndexByColName(xpath, value[0]);
        double actPrice = convertToPrice(getTextInTable(xpath, col, indexRow));
        assertThat(actPrice).isEqualTo(convertToPrice(value[1]));
    }

    String tableRight = "//div[@class='ant-table-fixed-right']//div[@class='ant-table-body-outer']";

    public void enterQuantity(String quantity, int index) {
        int col = getColIndexByColName(tableRight, "Ordered quantity");
        String xpath = tableRight + "//table/tbody//tr[" + index + "]//td[" + col + "]//input";
        waitClearAndType(xpath, quantity);
    }

    public Double getPriceAfterInputQuantity(String label, int indexRow) {
        int col = getColIndexByColName(tableRight, label);
        return convertToPrice(getTextInTable(tableRight, col, indexRow));
    }

    public Double convertToPrice(String price) {
        return Double.parseDouble(price.replace("$", ""));
    }

    public void clickOnRowToUpdatePrice(int index) {
        String xpath = tableRight + "//table/tbody//tr[" + index + "]";
        clickOnElement(xpath);

    }

    public boolean isQuotationExisted(String quotationId) {
        return isElementExist(xpathLinkText("", quotationId, 1));
    }

    public void waitSearchDone() {
        waitUntilElementInvisible("//div[@class='ant-spin-text' and text()='Loading...']", 10);
    }

    public void clickView(String quotation) {
        clickLinkTextWithLabel("//div[contains(@class,'ant-table')]//table/tbody//tr[descendant::*[text()='" + quotation + "']]", "View", 1);
    }

    public String getInforQuotation(String infor) {
        return getText("//p[text()='" + infor + "']//ancestor::div[2]//p[@class='common-value']");
    }
}
