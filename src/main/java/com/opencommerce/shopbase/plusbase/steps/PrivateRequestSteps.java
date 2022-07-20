package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.PrivateRequestPage;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivateRequestSteps {
    PrivateRequestPage privateRequestPage;

    public void inputLink(String link) {
        privateRequestPage.enterTextAreaWithLabel("Enter links from AliExpress, Amazon, eBay, …", link);
    }

    public void verifyDisplayLinkRequestInRequestListPage(String requestLink) {
        List<String> linkList = privateRequestPage.getListTexLink();
        if (requestLink.contains(">")) {
            String[] subLink = requestLink.split(">");
            for (String links : subLink) {
                assertThat(linkList).contains(links);
            }
        }
    }

    public void clickBT(String add_request) {
        privateRequestPage.clickBtn(add_request);
    }

    public void searchProducName(String prrodName) {
        privateRequestPage.searchProductName(prrodName);

    }

    public void verifyInfoProduct(String productName, String link, String status) {
        assertThat(privateRequestPage.getProductName()).contains(productName);
        assertThat(privateRequestPage.getLink()).isEqualTo(link);
        assertThat(privateRequestPage.getStatus()).isEqualTo(status);

    }

    public void clickNameProduct() {
        privateRequestPage.clickNameProduct();
    }

    public void verifyBTImportToStore(String productName, String link, String btImportToStore, String status, String variant, String processingTime, String productCost) {
        assertThat(privateRequestPage.getProductNameDetail()).contains(productName);
        assertThat(privateRequestPage.getLinkDetail()).isEqualTo(link);
        privateRequestPage.getbtImportToStoreDetail(btImportToStore, status);
        privateRequestPage.getVariant(variant);
        privateRequestPage.getProcessingTime(processingTime);
        privateRequestPage.getProductCost(productCost);

    }

    public void clickOnTab(String tab) {
        privateRequestPage.clickOnTab(tab);
    }

    public void clickButton(String import_to_your_store) {
        privateRequestPage.clickBtn(import_to_your_store);
    }

    public void selectproduct() {
        privateRequestPage.selectproduct();
        privateRequestPage.clickBtn("Add to store");
    }

    public void verifyProductImport(String product) {
        privateRequestPage.verifyProductImport(product);
    }

    public void searchProduct(String productName) {
        privateRequestPage.searchProduct(productName);
    }

    public Integer getQuantityProduct(String product) {
        return privateRequestPage.getQuantityProduct(product);
    }

    @Step
    public void verifyProductInPrivateRequestDetail(String productName, String status, String link, String btImportToStore) {
        assertThat(privateRequestPage.getProductNameDetail()).contains(productName);
        assertThat(privateRequestPage.getLinkDetail()).isEqualTo(link);
        privateRequestPage.getbtImportToStoreDetail(btImportToStore, status);
    }

    @Step
    public void verifyProductName(String productName) {
        String act = privateRequestPage.getProductName();
        assertThat(act).isEqualTo(productName);
    }

    @Step
    public void verifyStatusProduct(String status) {
        String act = privateRequestPage.getStatus();
        assertThat(act).isEqualTo(status);
    }

    @Step
    public void searchProductName(String productName) {
        privateRequestPage.searchProductNameInPrivateRequestList(productName);
    }

    @Step
    public void verifyProductInCatalogList(String productName, String status) {
        assertThat(privateRequestPage.getProductName()).contains(productName);
    }

    public String getQuotationNameOnPrivateProduct() {
        return privateRequestPage.getQuotationNameOnPrivateProduct();
    }

    public void waitCrawlSuccess(String productName, String status) {
        privateRequestPage.waitCrawlSuccess(productName, status);
    }


    public void verifyProductCostVariant(String productCost) {
        String act = privateRequestPage.getProductCostVariant();
        assertThat(act).isEqualTo(productCost);
    }

    public void verifyCountrySupportProduct(String shipTo, boolean isCountry) {
        privateRequestPage.verifyShowCountry(shipTo, isCountry);
    }

    public void selectCountrySupport(String shipTo) {
        privateRequestPage.selectCountrySupport(shipTo);
    }

    public void verifyFirstItem(String firstItem) {
        String act = privateRequestPage.getFirstItem();
        assertThat(act).isEqualTo(firstItem);
    }

    public void verifyAdditional(String additional) {
        String act = privateRequestPage.getAdditionalItem();
        assertThat(act).isEqualTo(additional);
    }

    public void verifyCountCountrySupport(int count) {
        privateRequestPage.verifyCountCountrySupport(count);
    }
}

