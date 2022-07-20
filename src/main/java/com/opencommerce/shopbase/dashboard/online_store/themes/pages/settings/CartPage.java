package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CartPage extends SBasePageObject {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void inputTextBoxWithLabel(String label, String text){
        waitClearAndType("//div[@currentlayout and descendant::label[normalize-space()='"+label+"']]//input", text);
    }

    public void verifyShowCartGoalOnSF(boolean isShow){
        verifyElementPresent("//div[contains(@class, 'cart-goal')]", isShow);
    }

    public void verifyCartGoalOnSF(String message){
        verifyElementContainsText("//div[contains(@class,'cart-goal__motivational-message')]", message);
    }
}
