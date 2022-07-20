package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.OrderVariable;
import net.thucydides.core.annotations.Step;

import com.opencommerce.shopbase.storefront.pages.shop.ShippingMethodPage;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.shippingMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class ShippingMethodSteps {
    ShippingMethodPage shippingMethodPage;

    @Step
    public void clickContinueToPaymentMethod() {
        shippingMethodPage.waitForEverythingComplete();
        shippingMethodPage.clickBtn("Continue to payment method");
    }

    @Step
    public void chooseShippingMethod(String shippingMethod) {
        if (!shippingMethod.isEmpty())
            shippingMethodPage.selectRadioButtonWithLabel(shippingMethod, true);
    }

    @Step
    public void verifyShippingMethodPageDisplayed() {
        shippingMethodPage.verifyShippingMethodPageDisplayed();
    }

    @Step
    public void verifyNoShippingDisplayed() {
        shippingMethodPage.verifyNoShippingZoneDisplayed();
    }

    @Step
    public void clickBthChangeToShowAllRate(){
        shippingMethodPage.clickBtnChange();
        shippingMethodPage.waitForEverythingComplete();
    }

    @Step
    public void verifyShippingMethod(String sRateName, String sPrice){
        String actualPrice = shippingMethodPage.getShippingPriceOfRateName(sRateName);
        System.out.println(actualPrice);
        assertThat(actualPrice).isEqualTo(sPrice);
    }

    @Step
    public Float getShippingPrice() {
        String displayedPrice = shippingMethodPage.getShippingPrice();
        Float act = Float.valueOf(displayedPrice);
        return act;
    }

    @Step
    public void verifyShippingPrice(String sPrice) {
        String displayedPrice = shippingMethodPage.getShippingPrice();
        shippingMethodPage.comparePrice(displayedPrice, sPrice);
    }

    @Step
    public void clickBtnChange() {
        shippingMethodPage.clickBtnChange();
        shippingMethodPage.waitForEverythingComplete();
    }

    @Step
    public void clickBtnContinueToPaymentMethod() {
        shippingMethodPage.clickBtn("Continue to payment method");
    }

    @Step
    public void verifyDisplayOfCompleteOrderBtn() {
        shippingMethodPage.verifyDisplayOfCompleteOrderBtn();
    }

    @Step
    public void clickOnCompleteOrderBtn() {
        shippingMethodPage.clickBtn("Complete order");
    }

    @Step
    public void verifyTheMessageDisplayed(String msg) {
        shippingMethodPage.verifyTextPresent(msg, true);
    }

    @Step
    public void verifyNonShippableProdDisplayed(String prodNames) {
        String[] prodName = prodNames.split(",");
        List<String> actualNonShippableProd = shippingMethodPage.getActualNonShippableProd();
        for (String _prodName : prodName) {
            assertThat(_prodName.trim()).isIn(actualNonShippableProd);
        }
    }

    @Step
    public String getShippingFeeBeforeDiscount() {
        return shippingMethodPage.getShippingFeeBeforeDiscount();
    }

    public void verifyShippingMethodWasChosen(String exShippingMethod) {
        shippingMethodPage.waitForEverythingComplete();
        assertThat(shippingMethod).isEqualTo(exShippingMethod);
    }

    public void verifyQuantityOfShippingMethod(int expQuantity) {
        int actQuantity = shippingMethodPage.getQuantityOfShippingMethod();
        assertThat(actQuantity).isEqualTo(expQuantity);
    }
}
