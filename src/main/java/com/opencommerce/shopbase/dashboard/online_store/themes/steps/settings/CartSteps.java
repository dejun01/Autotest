package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.CartPage;
import net.thucydides.core.annotations.Step;

public class CartSteps {
    CartPage cartPage;

    @Step
    public void chooseCartType(String cartType) {
        cartPage.selectDdlWithLabel("Cart type", cartType);
    }

    @Step
    public void chechUncheckOnlyshowCheckoutbuttoninMinicart(String label, Boolean ishow) {
        cartPage.checkCheckboxWithLabel(label, ishow);
    }

    @Step
    public void checkCheckBoxWithLabel(String label, boolean isCheck){
        cartPage.checkCheckboxWithLabel(label, isCheck);
    }

    @Step
    public void inputTextBoxWithLabel(String label, String text){
        cartPage.inputTextBoxWithLabel(label, text);
    }

    @Step
    public void verifyShowCartGoalOnSF(boolean isShow){
        cartPage.verifyShowCartGoalOnSF(isShow);
    }

    @Step
    public void verifyCartGoalOnSF(String message, String goalReached, double amount, double totalPrice){
        if(totalPrice < amount){
            cartPage.verifyCartGoalOnSF(message);
        }else {
            cartPage.verifyCartGoalOnSF(goalReached);
        }
    }
}
