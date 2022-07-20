package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencsv.exceptions.CsvValidationException;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.storefront.pages.shop.ThankyouPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.OrderVariable.listOfUsedPaymentAccount;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.filter;

public class ThankyouSteps extends ScenarioSteps {
    ThankyouPage thankyouPage;

    public void verifyThankYouPage() {
        thankyouPage.verifyThankyouPage();
    }

    public String getOrderNumber() {
        return thankyouPage.getOrderNumber();
    }

    public int getOrderId() {
        return thankyouPage.getOrderId();
    }

    @Step
    public void logInfor(String s) {
    }

    public void verifyCustomOptionDetail(String cutomOptionName, String input) {
        thankyouPage.verifyCustomOptionDetail(cutomOptionName, input);
    }


    public void clickOnShopNameHyperLink() {
        thankyouPage.clickOnShopNameHyperLink();
    }

    public void verifyQuantityOfProductInOrder(String product, int quanity) {
        thankyouPage.verifyQuantityOfProductInOrder(product, quanity);
    }

    public void verifyExistProduct(String product) {
        thankyouPage.verifyExistProduct(product);
    }

    public String getShippingAddressOnSF() {
        return thankyouPage.getShippingAddressOnSF();
    }

    public String getBillingAddressOnSF() {
        return thankyouPage.getBillingAddressOnSF();
    }

    public String getCustomerEmail() {
        return thankyouPage.getCustomerEmail();
    }

    public String getCustomerName() {
        return thankyouPage.getCustomerName();
    }

    @Step
    public String getSubTotal() {
        return thankyouPage.getSubtotal();
    }

    @Step
    public void verifyAddressInThankYouPage(String actualAddress, String inputtedAddress) {
        assertThat(actualAddress).isEqualTo(inputtedAddress);
    }

    @Step
    public void clickOnContinueShoppingButton() {
        thankyouPage.clickBtn("Continue shopping");
    }

    @Step
    public String getCheckoutToken() {
        return thankyouPage.getCheckoutToken();
    }

    @Step
    public void verifyEmail(String mail) {
        thankyouPage.verifyEmail(mail);
    }


    public void addProductPostPurchase(String productPostPurchase) {
        thankyouPage.addProductPostPurchase(productPostPurchase);
    }

    @Step
    public void verifyShippingPrice(String exFee) {
        String aFee = thankyouPage.getShippingPrice();
        assertThat(aFee).isEqualTo(exFee);
    }

    @Step
    public void verifyShippingMethod(String exMethod) {
        String aMethod = thankyouPage.getShippingMethod();
        assertThat(aMethod).isEqualTo(exMethod);
    }

    @Step
    public void verifyExamplePage() {
        boolean isExamplePage = thankyouPage.isExamplePageDisplayed();
        assertThat(isExamplePage).isTrue();
    }

    @Step
    public void verifyScanOrderSuccess() {
        System.out.println(isOrderScanned);
        assertThat(isOrderScanned).isTrue();
    }

    public String getShippingMethod() {
        return thankyouPage.getShippingMethod();
    }


    public String getTotalTax() {
        return thankyouPage.getTotalTax();
    }

    @Step
    public void addOrderIdIntoCSV(String fileName) {
        thankyouPage.addOrderIdIntoCSV(fileName);
    }

    @Step
    public void readListOrderInCSV(String fileName) throws CsvValidationException, ParseException, IOException {
        thankyouPage.readListOrderInCSV(fileName);
    }

    public void addPaymentAccountToList(Integer paymentID) {
        if (!listOfUsedPaymentAccount.contains(paymentID)) {
            listOfUsedPaymentAccount.add(paymentID);
        }
    }
}
