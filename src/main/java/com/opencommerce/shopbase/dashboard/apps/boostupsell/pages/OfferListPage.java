package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferListPage extends SBasePageObject {
    public OfferListPage(WebDriver driver) {
        super(driver);
    }

    public void waitNotShowLoadingOnTable() {
    }

    public int getNumberOfOffers() {
        waitABit(3000);
        String xpathRows = "//tbody//tr";
        return countElementByXpath(xpathRows);
    }

    public void checkAllOffers() {
        waitElementToBeVisible("//th[@class='checkbox']//span[@class='s-check']");
        clickOnElement("//th[@class='checkbox']//span[@class='s-check']");
    }

    public void clickBtnDeleteOnPopup() {
        clickBtn("//*[contains(@class,'s-animation-content')]", "Delete", 1);
    }

    public void verifyNotiShow(String _noti) {
        waitForTextToAppear(_noti, 10000);
    }

    public void verifyNoOffers(String textBtn) {
        waitABit(2000);
        waitForElementToPresent("//span[contains(text(),'" + textBtn + "')]");
    }

    public void clickBtnOnAction(String action) {
        clickOnElement("//*[contains(@class,'dropdown-item') and normalize-space()='" + action + "']");
    }


    public void searchOffer(String offerName) {
        waitTypeAndEnter("//div[@class='input-search s-input s-input--prefix']//input", offerName);
    }

    public String getOfferName() {
        String offerName = getText("(//td[@class='offer-name']//span)[1]");
        return offerName;
    }

    public void verifyTargetOfferOnList(String offerName, String prodTarget) {
        String xpathNameTarget = "//td[@class='offer-name' and descendant::span[text()=\"" + offerName + "\"]]/following-sibling::td//a";
        List<String> actual = getListText(xpathNameTarget);
        System.out.println(actual);
        List<String> exps = Arrays.asList(prodTarget.split(","));
        assertThat(actual).containsAll(exps);
        assertThat(actual.size()).isEqualTo(exps.size());
    }

    public void verifyOffferStatus(String isStatus) {
        assertThat(getText("(//td[@class='offer-status')[1]")).isEqualTo(isStatus);
    }

    public int getIndexOfferName(String offerName) {
        int index = 0;
        String xpath = "(//div[@class='table__container m-b']//table/tbody//tr[child::td[@class='offer-name pointer' or @class='offer-name' and descendant::*[normalize-space()='" + offerName + "']]])[1]";
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return index;
    }

    public int getColIndex(String colName) {
        int index = 0;
        String xpath = "(//div[@class='table__container m-b']//table/thead//th[normalize-space()=\"" + colName + "\"])[1]";
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::th") + 1;
        }
        return index;

    }

    public String getOfferInfor(int colIndex, int rowIndex) {
        return getText("//div[@class='table__container m-b']//table/tbody//tr[" + rowIndex + "]//td[" + colIndex + "]");
    }

    public List<String> getListProductOrCollectionTarget(int colIndex, int rowIndex) {
        if(isElementExist("//div[contains(@class,'show-more')]")){
            clickOnElement("//div[contains(@class,'show-more')]");
        }
        return getListText("//div[@class='table__container m-b']//table/tbody//tr[" + rowIndex + "]//td[" + colIndex + "]//a[contains(@class,'name')]");
    }

    public void turnOnToggleSmartOffer(boolean status) {
        checkCheckbox("//div[@class='list-offer__container-setting']", status);
        waitABit(2000);
    }

    public void waitToUpdateStatus(){
        waitABit(1000);
    }

    public void verifyTableNoOffers() {
        verifyElementPresent("//div[contains(@class,'no-offer')]", true);
    }
}
