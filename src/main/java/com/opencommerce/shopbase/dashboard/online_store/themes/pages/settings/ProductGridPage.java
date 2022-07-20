package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ProductGridPage extends SBasePageObject {
    public ProductGridPage(WebDriver driver) {
        super(driver);
    }

    public void verifyShowquantityboxinthesamelineExist(Boolean isShowquantitybox) {
        verifyElementPresent("//label[normalize-space()='Show quantity box in the same line with Add to cart / Buy now button']", isShowquantitybox);
    }

    public void chooseOptionStyle(String optionsStyle) {
        clickOnElement("//label[descendant::span[normalize-space()='" + optionsStyle + "']]");
    }

    public void enterTextFor0$product(String textFor0Sproduct) {
        waitClearAndType("//div[@currentlayout and descendant::p[contains(text(),'text (for $0 products)')]]//input", textFor0Sproduct);
    }

    public void chooseOptionPosition(String descriptionPosition) {
        selectDdlWithLabel("Description position", descriptionPosition);
    }

    public void chooseOptionSavingType(String showPriceSavingsType) {
        selectDdlWithLabel("Show price savings", showPriceSavingsType);
    }

    public void selectOptionStyles(String optionsStyle) {
        selectDdlWithLabel("Options style", optionsStyle);
    }

}
