package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
import com.opencommerce.shopbase.plusbase.pages.CheckoutPage;
import net.thucydides.core.annotations.Step;
import org.hamcrest.MatcherAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItem;


public class CheckoutSteps {
    CheckoutPage checkoutPage;
    OrderPage orderPage;

    @Step
    public void verifyClickBTCustomizeCheckout(String name) {
        checkoutPage.clickBtn(name);
        assertThat(checkoutPage.getPage()).isEqualTo("Open checkout settings");
    }

    @Step
    public void settingCheckout(String tab, String status) {
        checkoutPage.settingChekout(tab, status);
        if (checkoutPage.isElementExist("//button//span[contains(text(),'Save changes')]")) {
            checkoutPage.clickBtn("Save changes");
        }else {
            checkoutPage.refreshPage();
        }
    }

    @Step
    public void displayFooter() {
        checkoutPage.displayFooter();
    }

    @Step
    public void notDisplayfooter() {
        checkoutPage.notDisplayfooter();
    }

    @Step
    public void verifyHeaderHaveLogin() {
        checkoutPage.verifyHeaderHaveLogin();
    }

    @Step
    public void verifyHeaderNotHaveLogin() {
        checkoutPage.verifyHeaderNotHaveLogin();
    }

    @Step
    public void verifyOrderTimelineOrderPlusBase(String customerName, String customerEmail) {
        List<String> timelines = orderPage.getTimelineContent();
        String orderTimelineSendingEmail = "Order confirmation email was sent to " + customerName + " (" + customerEmail + ")";
        String orderTimelineCustomerPlaceOrder = customerName + " placed this order on Online Store (checkout";
        verifyOrderTimelineByLineItem(timelines, orderTimelineSendingEmail);
        verifyOrderTimelineByLineItem(timelines, orderTimelineCustomerPlaceOrder);

    }

    @Step
    public void verifyOrderTimelineByLineItem(List<String> timelines, String lineItem) {
        System.out.println(lineItem);
        if (!lineItem.isEmpty()) {
            MatcherAssert.assertThat(timelines, hasItem(containsString(lineItem)));
        }
    }

    @Step
    public void settingDisplayFooterWhenCheckout(String tab, String status) {
        checkoutPage.settingChekout(tab, status);
        if(checkoutPage.getText("//button//span[contains(text(),'Save changes')]").equals("Save changes")){
            checkoutPage.clickBtn("Save changes");
        }
    }

}

