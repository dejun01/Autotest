package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.ProductFeedListPages;
import net.thucydides.core.annotations.Step;
import java.io.IOException;
import java.util.ArrayList;


public class ProductFeedListSteps {

    ProductFeedListPages productFeedListPages;

    @Step
    public void deleteProductFeed(String feedName) {
        productFeedListPages.deleteProductFeed(feedName);
    }

    @Step
    public void searchProductFeed(String feedName) {
        productFeedListPages.searchProductFeed(feedName);
    }

    @Step
    public void clickDelete() {
        productFeedListPages.clickBtn("Delete");
    }

    @Step
    public String getUrl(String productFeed) {
        return productFeedListPages.getUrl(productFeed);
    }

    @Step
    public int getTotal(String productFeed) {
        return productFeedListPages.getTotal(productFeed);
    }

    @Step
    public void verifyCountProduct(String productFeed,String linkText) throws IOException {
        productFeedListPages.verifyCountProduct(productFeed,linkText);
    }


    @Step
    public ArrayList<String> storeInformationProduct(String linkText) throws IOException {
        return productFeedListPages.storeInformationProduct(linkText);
    }

    @Step
    public int countProduct(String linkText) throws IOException {
        return productFeedListPages.countProduct(linkText);
    }

    @Step
    public void verifyTitle(String title,String linkText) throws IOException {
        productFeedListPages.verifyTitle(title,linkText);
    }

    @Step
    public void verifyDescription(String description,String linkText) throws IOException {
        productFeedListPages.verifyDescription(description,linkText);
    }

    @Step
    public void verifyProductType(String productType,String linkText) throws IOException {
        productFeedListPages.verifyProductType(productType,linkText);
    }

    @Step
    public void verifyVendor(String vendor,String linkText) throws IOException {
        productFeedListPages.verifyVendor(vendor,linkText);
    }

    @Step
    public void verifyPrice(String price,String linkText) throws IOException {
        productFeedListPages.verifyPrice(price,linkText);
    }

    @Step
    public void verifyCompareAtPrice(String compareAtPrice,String linkText) throws IOException {
        productFeedListPages.verifyCompareAtPrice(compareAtPrice,linkText);
    }

    @Step
    public void verifyBarcode(String barcode,String linkText) throws IOException {
        productFeedListPages.verifyBarcode(barcode,linkText);
    }

    @Step
    public void verifyGender(String gender,String linkText) throws IOException {
        productFeedListPages.verifyGender(gender,linkText);
    }

    @Step
    public boolean hasNoProductFeed() {
        return productFeedListPages.hasNoProductFeed();
    }
}
