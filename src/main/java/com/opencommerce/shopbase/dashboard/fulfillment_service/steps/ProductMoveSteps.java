package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.ProductMovePage;
import net.thucydides.core.annotations.Step;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMoveSteps {
    ProductMovePage productMovePage;

    @Step
    public void clickViewInventoryDetail(String nameInventory) {
//        List<String> listText = productMovePage.getNameTextInventory();
//        for (int i = 0; i < listText.size(); i++)
//            if (listText.get(i).contains(nameInventory)) {
//                productMovePage.clickViewInventoryDetail(i + 1);
//            }
        productMovePage.clickViewInventoryDetail(nameInventory);
    }

    @Step
    public void verifyVariantInventory(String variant) {
        String[] listVariant = variant.split(";");
        for (String variantQuantity : listVariant) {
            String[] variants = variantQuantity.split(">");
            List<String> listVariantOfInventory = Collections.singletonList(variants[0]);
            for (String variantRow : listVariantOfInventory) {
                productMovePage.verifyVariantInventory(variantRow);

            }
        }

    }

    @Step
    public void clickBTViewVariantDetail(String variant) {
        productMovePage.clickBTViewVariantDetail(variant);
    }

    @Step
    public void verifyDate(String variant,String date) {
        assertThat(productMovePage.getTextProductOfVariant(variant,"col-date")).contains(date);
    }

    @Step
    public String verifyReference(String variant) {
        return productMovePage.getTextProductOfVariant(variant,"col-reference");

    }

    @Step
    public String verifyQuantity(String variant) {
        return productMovePage.getTextProductOfVariant(variant,"col-quantity");

    }

    @Step
    public String verifyStatus(String variant) {
        return productMovePage.getTextProductOfVariant(variant,"col-status");
    }

    @Step
    public String verifyType(String variant) {
        return productMovePage.getTextProductOfVariant(variant,"col-type");
    }

    @Step
    public void enterValueAndVerify(String value, String expected, String date, String type, String status, String quantity) {
        String[] listVariant = expected.split(";");
        for (String variantList : listVariant) {
            String[] variants = variantList.split(">");
            List<String> subVariants = Collections.singletonList(variants[0]);
            for (String subVariant : subVariants) {
                productMovePage.enterValue(subVariant);
                switch (value.toLowerCase()) {
                    case "variant":
                        assertThat(productMovePage.getTextListVariant(subVariant)).isEqualTo(1);
                        break;
                    case "subVariant":
                        productMovePage.enterValue(expected);
                        productMovePage.clickViewInventoryByName(subVariant);
                        productMovePage.getTextInfoSubVariant(date, type, expected, quantity, status);
                        break;
                }
            }
        }
    }

    @Step
    public void enterValue(String value) {
        productMovePage.enterValue(value);
    }

    @Step
    public void clickBTExport() {
        productMovePage.clickBTExport();
    }

    @Step
    public String openFileDownload() {
        return productMovePage.getPathFileDownloaded();


    }

    @Step
    public void verifyFileDownloadInfo(ArrayList expExportVariantList) {
        productMovePage.verifyDownloadExport(expExportVariantList);


    }

    @Step
    public void getInfoVariantInventory(List<String> name) {
        for (String variant : name) {
            productMovePage.clickViewInventoryByName(variant);
        }
        productMovePage.getInfoVariantInventory();
    }

    @Step
    public void getInfoVariantInventoryBySearch(String variant) {
        productMovePage.clickViewInventoryByName(variant);
        productMovePage.getInfoVariantInventory();
    }

    @Step
    public String getTextNoti() {
        return productMovePage.getTextNoti();
    }

    @Step
    public String getTextMessDownloadSuccess() {
        return productMovePage.getTextMessDownloadSuccess();
    }

    @Step
    public void clickSubmit() {
        productMovePage.clickSubmit();
    }

    @Step
    public void clickCheckbox(String name) {
        productMovePage.clickCheckbox(name);
    }

    @Step
    public void clickChooseAmountExport(String name) {
        productMovePage.clickChooseAmountExport(name);
    }
    @Step
    public void searchByPONumber(String PONumberBer){
        productMovePage.enterInputFieldWithLabel("Search product variant. reference",PONumberBer);
    }
    @Step
    public void accTab(String stock_move) {
        productMovePage.accTab(stock_move);
    }
}
