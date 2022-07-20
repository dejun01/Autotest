package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.CollectionDetailPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionDetailSteps extends ScenarioSteps {
    CollectionDetailPages collectionDetailPages;

    @Step
    public void enterColTitle(String colName) {
        collectionDetailPages.enterInputFieldWithLabel("Title", colName);
    }

    @Step
    public void enterCollectionDescription(String description) {
        collectionDetailPages.enterCollectionDescription(description);
    }

    @Step
    public void chooseColType(String type) {
        collectionDetailPages.selectRadioButtonWithLabel(type, true);
    }

    @Step
    public void chooseConditionType(String conType) {
        collectionDetailPages.selectRadioButtonWithLabel(conType, true);
    }

    @Step
    public void selectProdProperties(String prodProperties) {
        collectionDetailPages.selectDdProductProperties(prodProperties);
    }

    @Step
    public void selectCondition(String cons) {
        collectionDetailPages.selectDdColCondition(cons);
    }

    @Step
    public void inputConValue(String conValue) {
        collectionDetailPages.inputConditionValue(conValue);
    }

    @Step
    public void clickSaveCollection() {
        collectionDetailPages.clickButtonOnStickyBar("Save");
    }

    @Step
    public void verifyMessageCollection(String message) {
        collectionDetailPages.verifyMessageCollection(message);
    }

    @Step
    public void clickBreadcrumbCollections() {
        collectionDetailPages.clickBreadcrumbCollections();
    }

    @Step
    public void addProductToCollection(String title) {
        collectionDetailPages.addProductToCollection(title);
    }

    @Step
    public void clickSaveButton() {
        collectionDetailPages.clickSave();
    }

    @Step
    public void inputProductInfor(String colCondition) {
        List<String> conditions = new ArrayList<String>(Arrays.asList(colCondition.split(",")));
        String prodProperties = conditions.get(0);
        String cons = conditions.get(1);
        String Value = conditions.get(2);

        selectProdProperties(prodProperties);
        selectCondition(cons);
        inputConValue(Value);
    }

    @Step
    public void clickSave() {
        if (collectionDetailPages.isClickableBtn("Save")) {
            collectionDetailPages.clickBtn("Save");
            collectionDetailPages.waitForPageLoad();
        }
    }

    @Step
    public void clickDiscard() {
        collectionDetailPages.clickBtn("Discard");
    }

    @Step
    public void verifyProductOfCollection(String nameProduct) {
        collectionDetailPages.verifyProductOfCollection(nameProduct);
    }

    @Step
    public void chooseTypeSort(String sortType) {
        collectionDetailPages.chooseTypeSort(sortType);
    }

    @Step
    public void addCondition() {
        collectionDetailPages.clickBtn("Add another condition");
    }

    @Step
    public void clickAnyWhere() {
        collectionDetailPages.clickAnyWhere();
    }

    @Step
    public void verifyConditionValue(String value) {
        collectionDetailPages.verifyConditionValue(value);
    }

    @Step
    public void deleteCondition() {
        collectionDetailPages.deleteCondition();
    }

    @Step
    public void verifyMessageError(String message) {
        collectionDetailPages.verifyMessageError(message);
    }

    @Step
    public void verifySort(String nameProduct) {
        collectionDetailPages.verifySort(nameProduct);
    }

    @Step
    public void clickRefresh() {
        collectionDetailPages.clickRefresh();
    }

    @Step
    public void editWebsiteSeo() {
        collectionDetailPages.editWebsiteSeo();
    }

    @Step
    public void verifyHandle(String handle) {
        collectionDetailPages.verifyHandle(handle);
    }

    @Step
    public void editHandle(String handle) {
        collectionDetailPages.editHandle(handle);
    }

    @Step
    public void clickAddProduct() {
        collectionDetailPages.clickBtn("Add product");
    }

    @Step
    public void selectProduct(String title) {
        collectionDetailPages.selectProduct(title);
    }

    @Step
    public void selectAction(String action) {
        collectionDetailPages.selectAction(action);
    }

    @Step
    public void clickMove() {
        collectionDetailPages.clickMove();
    }

    @Step
    public void verifyStatus(String product, String status) {
        collectionDetailPages.verifyStatus(product, status);
    }

    @Step
    public void verifyProductAvailability(String product, String iconOnlineStore, String iconSearchEngine) {
        collectionDetailPages.verifyProductAvailability(product, iconOnlineStore, iconSearchEngine);
    }

    @Step
    public void clickBtnView() {
        collectionDetailPages.clickLinkTextWithLabel("View");
    }

    @Step
    public void verifyStatus(String position) {
        collectionDetailPages.verifyStatus(position);
    }

    @Step
    public boolean verifySyncEnoughProduct(String numberProduct) {
        return collectionDetailPages.verifySyncEnoughProduct(numberProduct);
    }

    @Step
    public void showMoreProduct() {
        collectionDetailPages.showMoreProduct();
    }

    @Step
    public void clickAddProductThumbnail() {
        collectionDetailPages.clickAddProductThumbnail();
    }

    @Step
    public void addProductThumbnail(String option, String value, int row) {
        collectionDetailPages.addProductThumbnail(option, value, row);
    }

    @Step
    public void clickAddOption() {
        collectionDetailPages.clickBtn("Add option");;
    }
    @Step
    public void verifyCollectionHasProducts(String sNumberCollection) {
        collectionDetailPages.verifyCollectionHasProducts(sNumberCollection);
    }
    @Step
    public void clickShowMore() {
        collectionDetailPages.clickShowMore();
    }
}
