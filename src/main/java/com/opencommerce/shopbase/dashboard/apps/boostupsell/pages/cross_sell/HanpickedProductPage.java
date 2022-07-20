package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class HanpickedProductPage extends SBasePageObject {
    public HanpickedProductPage(WebDriver driver) {
        super(driver);
    }
    public void clickToSelectProductRecommend() {
        clickOnElement("//div[child::div[normalize-space()='Choose Recommended Products']]//parent::div//following-sibling::div//button");
    }

    public void clickToCreateNew() {
        clickBtn("Create new");
    }

    String xpathTargetProduct = "//div[child::div[normalize-space()='Choose Target Products']]//parent::div//following-sibling::div//div[contains(@class,'s-content')]";

    public void openSelectorHanpicked(String btnName) {
        clickBtn(xpathTargetProduct, btnName, 1);
    }

    public void selectProductType(String productType) {
        selectRadioButtonWithLabel(xpathTargetProduct, productType, 1, true);
    }

    public String getUrl() {
        return getCurrentUrl();
    }

    public void enterOfferName(String offerName) {
        waitClearAndType("//div[@id='app-list']//div[@class='s-input']//input",offerName);
    }
}
