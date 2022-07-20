package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.fulfillment;

import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;

public class MappingProductPage extends BFlowPageObject {
    public MappingProductPage(WebDriver driver) {
        super(driver);
    }

    String sectionPurchasedProduct = "//div[contains(@class,'section-purchased-product')]";
    String rowPurchasedProduct = "(//div[contains(@class,'section-purchased-product')]//div[contains(@class,'product-wrap row-flex')])[%d]";

    public void searchPurchasedProduct(String purchasedProduct) {
        typeAndEnterInputFieldWithLabel(sectionPurchasedProduct, "Search by product name", purchasedProduct, 1);
    }

    public void clickBtnSetPurchasedProduct() {
        if (isClickableBtn(String.format(rowPurchasedProduct, 1), "Set", 1))
            clickBtn(String.format(rowPurchasedProduct, 1), "Set", 1);
    }

    public String getMsgMapping() {
        String actMgs = getText("//div[@class='ant-notification-notice-message']");
        return actMgs;
    }
}
