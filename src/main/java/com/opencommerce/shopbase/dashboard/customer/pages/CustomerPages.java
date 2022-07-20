package com.opencommerce.shopbase.dashboard.customer.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CustomerPages extends SBasePageObject {
    public CustomerPages(WebDriver driver) {
        super(driver);
    }

    public void searchCustomer(String customerEmail) {
        String textboxSearch = xPathInputFieldWithLabel("", "Search customers", 1);
        waitClearAndTypeThenEnter(textboxSearch, customerEmail);
        waitForEverythingComplete();
        waitUntilInvisibleLoading(8);
    }

    public void selectCustomer(String customerName) {
        String customerInList = "(//div[@class='customer-list-container']//span[text()[normalize-space()='" + customerName + "']])[1]";
        String customerInDetail = "//h1[text()[normalize-space()='" + customerName + "']]";
        waitElementToBeVisible(customerInList, 8);
        clickOnElement(customerInList);
        waitElementToBeVisible(customerInDetail, 8);
    }

    public String getShippingAddress() {
        List<String> shippingAddress = getListText("//div[@class='section-overview']//div[child::div[normalize-space()='Default address']]/following-sibling::div//p");
        return String.join(" ", shippingAddress);
    }
}
