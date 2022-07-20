package com.opencommerce.shopbase.dashboard.apps.printbase.library;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomArtPage extends SBasePageObject {
    public CustomArtPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCustomArtSceen() {
        String xpath = "//div[@id='manual-designs']/h2[contains(.,'Custom Art')]";
        assertThat(isElementExist(xpath, 50)).isEqualTo(true);
    }

    public void clickButtonImportOrEdit(String btn, String campaign) {
        String xpath = "//div[contains(@class,'list-camps__container')][1]//div[contains(@class,'camp__container')][1]//button[contains(.,'" + btn + "')]";
        String xpathPage = "//div[contains(@class,'s-pagination')]//small[@class = 's-info']";
        System.out.println(xpath);
        System.out.println(getText(xpathPage));
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public boolean isShowPopupCreateNewManualDesign() {
        String xpath = "//div[@class='s-animation-content s-modal-content']/div[contains(.,'Create new manually-designed campaign')]";
        return isElementExist(xpath);
    }

    public void clickDontNotify() {
        String xpathStt = "//span[contains(.,\"Don't notify me again\")]/ancestor::label/input";
        String xpathSelect = "//span[contains(.,\"Don't notify me again\")]/ancestor::label/span";
        verifyCheckedThenClick(xpathStt, xpathSelect, true);
    }

    public void clickCreateCampaignBtn() {
        String xpath = "//button/span[contains(.,\"Create campaign\")]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public String getCustomArtNameOnUI() {
        String xpath = "//div[contains(@class,'list-camps__container')][1]//div[contains(@class,'camp__container')][1]//p";
        String isCustomArtName = getText(xpath);
        return isCustomArtName;
    }
}
