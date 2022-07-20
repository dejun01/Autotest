package com.opencommerce.shopbase.dashboard.apps.adc.products.steps;

import com.opencommerce.shopbase.dashboard.apps.adc.products.pages.ImportProductsPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class ImportProductsSteps extends ScenarioSteps {
    ImportProductsPages importProductsPages;
    public static String domain = LoadObject.getProperty("shop");
    String saveSuccessfully = "Saved successfully";
    private String currentUrl = "";

    @Step
    public void logInfor(String s) {
    }

    @Step
    public void enterURLProduct(String urlProduct) {
        importProductsPages.enterInputFieldWithLabel("Please enter the URL of products on AliExpress", urlProduct);
    }

    @Step
    public void clickBtnImport() {
        importProductsPages.clickBtn("Import", 1);
        importProductsPages.waitForEverythingComplete();
    }

    @Step
    public void clickBtnImportOnPopUp() {
        importProductsPages.clickBtn("Import", 2);
        importProductsPages.waitForEverythingComplete();
        waitABit(5000);
    }

    @Step
    public void selectDesTabByIndex(int indexProduct, String nameTab) {
        if (indexProduct > 0) {
            importProductsPages.clickTabByIndex(indexProduct, nameTab);
        }
    }

    @Step
    public void selectVariantTab(int indexProduct, String nameTab) { // day la overload
        importProductsPages.clickTabByIndex(indexProduct, nameTab);
    }

    public int getRowVariant(String sVariantName) {
        return importProductsPages.getRowVariant(sVariantName);
    }

    public boolean isReimportBtnAppear(String btnName) {
        return importProductsPages.isBtnDisappear(btnName);
    }

    @Step
    public void clickBtnImportToStore(String adcProdName) {
        int time = 0;
        waitABit(3000);
        importProductsPages.waitForEverythingComplete();
        importProductsPages.clickImportToStore(adcProdName);
        importProductsPages.waitUntilInvisibleLoading(30);
        while (isReimportBtnAppear("Reimport") && time <4 ) {
            importProductsPages.clickBtn("Reimport");
            time ++;
        }
    }

    @Step
    public void clickDeleteProduct(String productName) {
        importProductsPages.clickDeleteProduct(productName);
    }

    @Step
    public void clickConfirmDeleteProduct() {
        importProductsPages.clickConfirmDeleteProduct();
    }

    @Step
    public void refreshTab() {
        importProductsPages.refreshPage();
        importProductsPages.waitForPageLoad();
    }

    @Step
    public void verifyProductDeleted(String productName) {
        importProductsPages.verifyProductDeleted(productName);
    }

    @Step
    public float getExpectPrice(String optionCalculate, float cost, String valuePrice) {
        float value = Float.parseFloat(valuePrice);
        float expPrice = 0;
        if (optionCalculate.equals("Multiply By")) {
            expPrice = cost * value;
        }
        if (optionCalculate.equals("Add more value")) {
            expPrice = cost + value;
        }
        if (optionCalculate.equals("Set new value")) {
            expPrice = value;
        }
        return expPrice;
    }

    @Step
    public void verifyValuePriceAfterChange(String optionCalculate, String valuePrice) {
        for (int i = 1; i < importProductsPages.countElementByXpathVariant() + 1; i++) {
            float cost = importProductsPages.getValueCost(i);
            float actualPrice = importProductsPages.getValuePrice(i);
            float expPrice = getExpectPrice(optionCalculate, cost, valuePrice);
            assertThat(actualPrice).isEqualTo(expPrice);
            float profit = importProductsPages.getValueProfit(i);
            float afterProfit = expPrice - cost;
            assertThat(afterProfit).isEqualTo(profit);
        }
    }

    @Step
    public void verifyValueComparePriceAfterChange(String optionCalculate, String valuePrice) {
        for (int i = 1; i < importProductsPages.countElementByXpathVariant() + 1; i++) {
            float cost = importProductsPages.getValueCost(i);
            float actualComparePrice = importProductsPages.getValueComparePrice(i);
            float expPrice = getExpectPrice(optionCalculate, cost, valuePrice);
            assertThat(actualComparePrice).isEqualTo(expPrice);
        }
    }


    @Step
    private void selectOptionCalculate(String label, String sPriceOption, String valuePrice) {
        importProductsPages.hoverToSelectAll();
        importProductsPages.selectOptionChange(label, sPriceOption);
        importProductsPages.enterValue(valuePrice);
        importProductsPages.clickApply();
    }

    @Step
    public void selectOptionCalculatePrice(String sPriceOption, String valuePrice) {
        selectOptionCalculate("Price", sPriceOption, valuePrice);
    }

    @Step
    public void selectOptionCalculateComparePrice(String sComparePriceOption, String valueComparePrice) {
        selectOptionCalculate("Compare at price", sComparePriceOption, valueComparePrice);
    }

    public ArrayList getCoupleVariants() {
        return importProductsPages.getCoupleVarians();
    }

    @Step
    public void verifyProductExistInImportList(String productName, boolean isExist) {
        refreshTab();
        importProductsPages.verifyTextPresent(productName, isExist);
    }

    @Step
    public void verifyStatusImportToStore(String productName) {
        importProductsPages.verifyStatusImportToStore(productName);
    }

    @Step
    public void verifyStatusImportToStore(List<String> prodList) {
        importProductsPages.verifyStatusImportToStore(prodList);
    }

    @Step
    public void clickBtnEditProductOnShopbase(String productName) {
        String currentUrl = "";
        importProductsPages.clickBtn("Edit product on ShopBase");
        importProductsPages.switchToLatestTab();
        importProductsPages.waitForEverythingComplete();
        currentUrl = importProductsPages.getCurrentUrl();
        assertThat(currentUrl).contains(domain + "/admin/products");
        importProductsPages.verifyTextPresent(productName, true);
        importProductsPages.switchToWindowWithIndex(0);
    }

    @Step
    public void selectAllProduct() {
        importProductsPages.selectAllProduct();
    }


    @Step
    public void removeProductSelected() {
        importProductsPages.clickBtnRemoveFromList();
        importProductsPages.waitForConfirmPopupDisplayed();
        importProductsPages.clickDeleteBtnOnPopUp();
        importProductsPages.waitForEverythingComplete();
    }

    @Step
    public void changeTitleProduct(String changeTitle, int index) {
        importProductsPages.enterChangeTitle(changeTitle, index);
    }

    @Step

    public void changeDesProduct(String changeDesc) {
        importProductsPages.enterChangeDesProduct(changeDesc);
    }

    public int getQuantityProductsInList() {
        return importProductsPages.getQuantityProductsInList();
    }


    public void verifyMsgDisplayed(String msg) {
        String[] mes = msg.split(";");
        if (!msg.isEmpty()) {
            for (String message : mes) {
                importProductsPages.verifyTextPresent(message, true);
            }
            importProductsPages.closePopup();
        }
    }

    public void verifyMsgToGoAliExpress(String msg) {
        importProductsPages.verifyMsgToGoAliExpress(msg);
    }

    public void verifyLinkTextAliExpress() {
        importProductsPages.clickLinkAliExpress();
        importProductsPages.switchToWindowWithIndex(1);
        importProductsPages.verifyLinkAliExpress();
        importProductsPages.switchToWindowWithIndex(0);//?
    }

    public void verifyBtnImportListDisabled() {
        boolean isImportDisable = importProductsPages.isClickableBtn("Import to store");
        assertThat(isImportDisable).isEqualTo(false);
    }

    public int getIndexOfProduct(String nameProduct) {
        int result = importProductsPages.getIndexOfProduct(nameProduct);
        return result;
    }

    public String getNameProdByIndex(int index) {
        return importProductsPages.getNameOfProductbyIndex(index);
    }

    @Step
    public void changeNameVariant(String changeNameVariants, int index) {
        importProductsPages.enterChangeNameVariant(changeNameVariants, index);
    }


    public void validateMessAfterImport(String msgs) {
        String mess[] = msgs.split(",");
        for (String msg : mess) {
            importProductsPages.verifyMsgError(msg);
        }
    }

    @Step
    public void selectVariantTab(String productName) {
        importProductsPages.selectVariantTab(productName);
    }


    public void deleteProductImportList(String nameProduct) {
        String btn = "Delete";
        importProductsPages.actionImportList(nameProduct, btn);
    }

    public void confirmDeleteProductImportList() {
        importProductsPages.clickBtn("Confirm");
    }

    public int getCountProductImportList() {
        int count = importProductsPages.getCountProductImportList();
        return count;
    }

    @Step
    public void verifyProductByTitle(String nameProductImportToStore) {
        verifyMsgDisplayed(nameProductImportToStore);
    }

    @Step
    public void selectTabInImportList(String tabName) {
        importProductsPages.selectTabInImportList(tabName, 1);
    }

    @Step
    public void enterNewTitle(String originalTitle, String newTitle) {
        if (!originalTitle.isEmpty()) {
            importProductsPages.enterInputFieldWithLabel("Change title", newTitle, 1);
            importProductsPages.blurChangeTitleField();
        }
    }

    @Step
    public void enterNewDescription(String description) {
        importProductsPages.enterDescription(description);
    }

    @Step
    public void enterNewVariantName(String originalVariant, String newVariants) {
        if (!newVariants.isEmpty()) {
            importProductsPages.enterNewVariant(originalVariant, newVariants);
        }
    }

    @Step
    public void bulkUpdatePrice(String price) {
        String option = "";
        String value = "";
        int index = 1;
        if (price.contains("=")) {
            option = price.split("=")[0].trim();
            value = price.split("=")[1].trim();
        }

        selectTabInImportList("Variants");
        importProductsPages.clickOnIconDropdown(index); // button change all price not showing
        importProductsPages.selectOptionToBulkUpdate(option, index);
        importProductsPages.enterNewValueToBulkUpdatePrice(value);
        importProductsPages.clickBtn("Apply");
        importProductsPages.waitForEverythingComplete();
    }

    @Step
    public void verifyChangesTitleSuccessfully(String prodKey, String title) {
        importProductsPages.waitUntilMsgSuccessfullyDisplayed(saveSuccessfully);
        String actualValue = importProductsPages.getEnteredText("Change title", 1);
        assertThat(actualValue).isEqualTo(title);
        setImportedProdListToAdc(prodKey, title);
    }

    public void verifyChangesDescriptionSuccessfully(String newDes) {
        importProductsPages.blurChangeTitleField();
        importProductsPages.waitUntilMsgSuccessfullyDisplayed(saveSuccessfully);
        String actualDes = importProductsPages.getEnteredDescription();
        assertThat(actualDes).isEqualTo(newDes);
        description = newDes;
    }

    @Step
    public HashMap<String, List<Float>> expectedVariantInfo(HashMap<String, List<Float>> beforeEditing, String prodName, String values) {
        String option = values.split("=")[0].trim();
        float value = Float.parseFloat(values.split("=")[1]);

        String prodVar = "";
        float cost = 0f, originalPrice = 0f, originalProfit = 0f, originalCompareAtPrice = 0f;
        float newPrice = 0f, newProfit = 0f;
        Set<Map.Entry<String, List<Float>>> setHashMap = beforeEditing.entrySet();

        for (Map.Entry<String, List<Float>> variantsInfo : setHashMap) {
            prodVar = variantsInfo.getKey();
            for (int i = 0; i < variantsInfo.getValue().size(); i++) {
                cost = variantsInfo.getValue().get(0);
                originalPrice = variantsInfo.getValue().get(1);

                switch (option) {
                    case "Multiply by":
                        newPrice = cost * value;
                        break;
                    case "Add to":
                        newPrice = cost + value;
                        break;
                    case "Set new price":
                        newPrice = value;
                        break;
                    default:
                        System.out.println("Please select valid option");
                }
                newProfit = roundOffTo2DecPlaces(newPrice - cost);
            }
            prodVarAliInfo.put(prodVar, asList(cost, newPrice, newProfit, originalCompareAtPrice));
        }
        return prodVarAliInfo;
    }

    @Step
    public void verifyVariantInfoAfterEditing(HashMap<String, List<Float>> beforeEditing, String prodName, String value) {
        HashMap<String, List<Float>> actualVariantInfo = getActualVariantInfoDisplayed(prodName);
        HashMap<String, List<Float>> expectedVariantInfo = expectedVariantInfo(beforeEditing, prodName, value);

        System.out.println("actualVariantInfo = " + actualVariantInfo);
        System.out.println("expectedVariantInfo = " + expectedVariantInfo);
        assertThat(actualVariantInfo).isEqualTo(expectedVariantInfo);
    }

    public ArrayList<Float> getVariantsCost() {
        return importProductsPages.getVariantsCost();
    }

    public Float getVariantsCost(int row) {
        return importProductsPages.getVariantsCost(row);
    }

    public ArrayList<Float> getVariantsPrice() {
        return importProductsPages.getVariantsPrice();
    }

    public Float getVariantsPrice(int row) {
        return importProductsPages.getVariantsPrice(row);
    }

    public ArrayList<Float> getVariantProfit() {
        return importProductsPages.getVariantProfit();
    }

    public Float getVariantProfit(int row) {
        return importProductsPages.getVariantProfit(row);
    }

    public ArrayList<Float> getVariantsCompareAtPrice() {
        return importProductsPages.getVariantsCompareAtPrice();
    }

    public ArrayList<String> getVariantsName() {
        return importProductsPages.getVariantsName();
    }

    public String getVariantsName(int row) {
        return importProductsPages.getVariantsName(row);
    }

    public int countVariants() {
        return importProductsPages.countVariants();
    }

    @Step
    public HashMap<String, List<Float>> getActualVariantInfoDisplayed(String prodName) {
        selectTabInImportList("Variants");

        String varName = "";
        Float varCost = 0f, varPrice = 0f, varProfit = 0f;

        // only working with single variant
        for (int i = 1; i <= countVariants(); i++) {
            varName = getVariantsName(i);
            varCost = getVariantsCost(i);
            varPrice = getVariantsPrice(i);
            varProfit = getVariantProfit(i);
            prodVarAliInfo.put(prodName + ":" + varName, asList(varCost, varPrice, varProfit));
        }
        selectTabInImportList("Product");
        System.out.println("prodVarAliInfo = " + prodVarAliInfo);
        return prodVarAliInfo;
    }

    @Step
    public void updateProdVarAliInfo(String oldProdName, String newProdName) {
        String originProdName = "", originVarName = "";
        String originProVar = "", newProdVar = "";
        List<Float> varInfo = new ArrayList<>();
        Iterator<Map.Entry<String, List<Float>>> iterator = prodVarAliInfo.entrySet().iterator();
        Map<String, List<Float>> newMap = new HashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Float>> entry = iterator.next();
            originProVar = entry.getKey();
            varInfo = entry.getValue();
            originProdName = originProVar.split(":", 2)[0];
            originVarName = originProVar.split(":", 2)[1];
            if (originProdName.equalsIgnoreCase(oldProdName)) {
                newProdVar = newProdName + ":" + originVarName;
                iterator.remove();
                newMap.put(newProdVar, varInfo);
            }
        }
        prodVarAliInfo.putAll(newMap);
    }

    public void verifyBtnExtension(String val) {
        importProductsPages.clickBtn(val);
        importProductsPages.waitForEverythingComplete();
        importProductsPages.switchToWindowWithIndex(1);
        currentUrl = importProductsPages.getCurrentUrl();
        assertThat(currentUrl.contains("chrome.google.com/webstore/detail/ali-dropship-connector"));
    }

    public Float roundOffTo2DecPlaces(Float val) {
        return Float.parseFloat(importProductsPages.roundOffTo2DecPlaces(val.toString()));
    }

    public void clickOnCheckboxByProductTitle(String title) {
        importProductsPages.checkOnCheckboxByProdTitle(title, true);
    }

    @Step
    public void verifyNumberOfProductsSelected(int num) {
        String actualSelected = importProductsPages.actualTheNumberOfProdSelected();
        String expectedSelected = "";
        if (num == 1) {
            expectedSelected = num + " product" + " selected";
        } else {
            expectedSelected = num + " products" + " selected";
        }
        assertThat(actualSelected.equals(expectedSelected));
    }

    public void clickOnBtnInActionsBar(String action) {
        if (action.equalsIgnoreCase("Import to Store")) {
            importProductsPages.clickOnBtnInActionsBar(action);
        } else if (action.equalsIgnoreCase("Remove from list")) {
            removeProductSelected();
        }
    }

    public void verifyMsgDisplayedWhenDeletingProd(int num) {
        String msg = "";
        if (num == 1) {
            msg = num + " product" + " deleted";
        } else {
            msg = num + " products" + " deleted";
        }
        importProductsPages.verifyToastMsgDisplayed(msg);
    }

    public void verifyRemovedProductIsNotDisplayed(List<String> prodList) {
        importProductsPages.refreshPage();
        for (String prod : prodList) {
            importProductsPages.verifyTextPresent(prod, false);
        }
    }

    @Step
    public void configRuntime() {
        // pre-condition to show extension
        importProductsPages.executeJavaScript("window.chrome.runtime = {}");
        importProductsPages.executeJavaScript("window.chrome.runtime.sendMessage = (extensionId, {}, callback) => {callback()}");
        importProductsPages.executeJavaScript("window.chrome.runtime.lastError = true");
    }

    public String getProductName(int i) {
        return importProductsPages.getNameOfProductbyIndex(i);
    }
}
