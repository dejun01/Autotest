package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.CreateOfferPage;
import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.cross_sell.BundlesPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class BundleOfferSteps extends ScenarioSteps {
    BundlesPage bundlesPage;
    CreateOfferPage upsellPage;

    @Step
    public void addAllToCartBundle(String bundles) {
        if (!bundles.isEmpty()) {
            selectVariantBundle(bundles);
        }
        clickAddAllToCartBundle();
    }

    @Step
    public void clickAddAllToCartBundle() {
        bundlesPage.clickButtonAddAllToCart();
    }

    public void selectVariantBundle(String bundles) {
        String[] products = bundles.split(",");
        for (String product : products) {
            String[] data = product.split(">");
            String nameProduct = data[0];
            String variant = data[1];
            bundlesPage.selectVariantOnProduct(nameProduct, variant);
        }
    }


    @Step
    public void verifyOfferMessageShown(String offerMessage) {
        bundlesPage.verifyOfferMessageShown(offerMessage);
    }

    @Step
    public void verifyNumberProductOfBundle(String numberProduct) {
        int number = Integer.parseInt(numberProduct);
        int actualNumber = bundlesPage.getNumberProductOffer();
        assertThat(number).isEqualTo(actualNumber);
    }

    public void clickQuickViewWithProduct(String product) {
        bundlesPage.clickQuickViewProduct(product);
    }

    public void verifyBundleShown(boolean isShow) {
        bundlesPage.verifyOfferBundleShown(isShow);
    }

    public void verifyBundleShowOnProduct(String bundles) {
        if (!bundles.isEmpty()) {
            String[] bundleList = bundles.split(";");
            for (int i = 0; i < bundleList.length - 1; i++) {
                verifyBundleDetail(bundleList[i]);
                bundlesPage.clickToNextOffer();
            }
            verifyBundleDetail(bundleList[bundleList.length - 1]);

        }
    }

    public void verifyBundleDetail(String bundle) {
        String[] detailBundle = bundle.split(">");
        String messageOffer = detailBundle[0];
        String numberProduct = detailBundle[1];
        String productBundle = detailBundle[2];
        String priceBundle = detailBundle[3];
        verifyOfferMessageShown(messageOffer);
        verifyNumberProductOfBundle(numberProduct);
        verifyProductOnBundle(productBundle);
        bundlesPage.verifyPriceOfferCrossSell(priceBundle);


    }

    public void verifyProductOnBundle(String productBundle) {
        String[] product = productBundle.split(",");
        for (String prod : product) {
            bundlesPage.verifyProductOnBundle(prod);
        }
    }

    public void comparePrice(String totalPrice, String subTotalOrder) {
        bundlesPage.comparePrice(totalPrice, subTotalOrder);
    }


    public String getTotalPriceProduct() {
        return bundlesPage.getTotalPriceOffer();

    }

    @Step
    public List<String> getProductBundle() {
        return bundlesPage.getListProductBundle();
    }

    @Step
    public void verifyListProduct(List<String> nameProductOnBundle, List<String> nameProductOnOrder) {
        bundlesPage.verifyListProducts(nameProductOnBundle, nameProductOnOrder);
    }

    @Step
    public void verifyImgProductShown(List<String> nameProductOnBundle) {
        for (String nameProduct : nameProductOnBundle) {
            bundlesPage.verifyImgProductShown(nameProduct);
        }

    }

    public void verifyOfferMessageShownOnSmart(String message) {
        bundlesPage.verifyOfferMessageShownOnSmart(message);
    }

    public float getCompareTotalPrice() {
        return bundlesPage.getCompareTotalPrice();
    }

    public void verifyNotShownCompartPrice() {
        bundlesPage.verifyComparePriceNotShown();
    }

    @Step
    public void checkCheckBox(String label, boolean isCheck){
        upsellPage.checkCheckboxWithLabel(label, 1, isCheck);
    }

    @Step
    public void inputToTextBoxOnBundle(String label, String text){
        bundlesPage.inputToTextBoxOnBundle(label, text);
    }

    @Step
    public void verifyOfferShow(String offerName, boolean isShow){
        bundlesPage.verifyOfferShow(offerName, isShow);
    }

    @Step
    public void verifyProducts(String offerName, String products){
        String[] productList = products.split(",");

        if(productList.length > 3){
            bundlesPage.clickSeeMore();
        }

        for(String i:productList){
            String product = i.trim();
            bundlesPage.verifyProduct(offerName, product);
        }
    }

    @Step
    public void verifyUpdatedAt(String offerName){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fomat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(fomat);
        bundlesPage.verifyUpdatedAt(offerName, formattedDate);
    }

    @Step
    public void verifyStatus(String offerName, boolean status){
        bundlesPage.verifyStatus(offerName, status);
    }

    @Step
    public void openOfferListFromProductAdmin(String label){
        bundlesPage.openOfferListFromProductAdmin(label);
    }

    @Step
    public void switchWindow(){
        bundlesPage.switchToLatestTab();
}

    @Step
    public void selectProductOnModal(String products){
        bundlesPage.selectProductOnModal(products);
    }

    @Step
    public void openModalToCreateBundle(){
        bundlesPage.clickButtonOnSectionBundles();
        String buttonLabel = bundlesPage.getButtonLabel();
        if(buttonLabel.equalsIgnoreCase("Edit bundle")){
            bundlesPage.clickButtonOnPopup("Create more bundles");
        }
    }

    @Step
    public void selectBundleOnModal(String offerName){
        bundlesPage.selectBundleOnModal(offerName);
    }

    @Step
    public void clearSelectedProducts(){
        bundlesPage.clearSelectedProducts();
    }

    @Step
    public void settingPrepurchaseOffers(String label,String prepurchaseOffers) {
        bundlesPage.settingPrepurchaseOffers(label,prepurchaseOffers);
    }

    @Step
    public void settingIncartOffers(String label,String incartOffers) {
        bundlesPage.settingIncartOffers(label,incartOffers);
    }

    @Step
    public void settingQuantityDiscounts(String label,String quantityDiscounts) {
        bundlesPage.settingQuantityDiscounts(label,quantityDiscounts);
    }

    @Step
    public void settingBundles(String label,String bundles) {
        bundlesPage.settingBundles(label,bundles);
    }

    @Step
    public void settingAccessories(String label,String accessories) {
        bundlesPage.settingAccessories(label,accessories);
    }

    @Step
    public void clickAddToCartPPC() {
        bundlesPage.clickAddToCartPPC();
    }

    @Step
    public void clickBtnSave() {
        bundlesPage.clickBtnSave();
    }

    @Step
    public void clickAddProduct(String product) {
        bundlesPage.clickAddProduct(product);
    }

    @Step
    public void clickProductOnIncart() {
        bundlesPage.clickProductOnIncart();
    }

    @Step
    public void verifyCTASetting(String url) {
        bundlesPage.verifyCTASetting(url);
    }

    @Step
    public void clickAddToCartAccessories() {
        bundlesPage.clickAddToCartAccessories();
    }

    @Step
    public String verifyNameBundle(String bundleList){
        String[] infor = bundleList.split(">");
        return infor[0];
    }

    @Step
    public void clickButtonOnCutomOptionPopup(String button){
        bundlesPage.clickButtonOnCutomOptionPopup(button);
    }

    @Step
    public void verifyAddCustomOptionSuccess(String nameProduct, Boolean status){
        bundlesPage.verifyAddCustomOptionSuccess(nameProduct, status);
    }

    @Step
    public void verifyNameProductCustomOption(String nameProduct){
        bundlesPage.verifyNameProductCustomOption(nameProduct);
    }

    @Step
    public void verifyPriceProductCustomOption(String priceProduct){
        bundlesPage.verifyPriceProductCustomOption(priceProduct);
    }

    @Step
    public void inputTextCustomOption(String text){
        bundlesPage.inputTextCustomOption(text);
    }

    @Step
    public void uploadImageCustomOption(String uploadImage){
        bundlesPage.uploadImageCustomOption(uploadImage);
    }

    @Step
    public void selectOptionCustomOption(String option){
        bundlesPage.selectOptionCustomOption(option);
    }

    @Step
    public void verifyMsgUploadImgValidate(String message) {
        bundlesPage.verifyMsgUploadImgValidate(message);
    }

    @Step
    public void verifyMsgRadioOptionValidate(String message) {
        bundlesPage.verifyMsgRadioOptionValidate(message);
    }

    @Step
    public void selectOptionCheckboxCustomOption(String option) {
        bundlesPage.selectOptionCheckboxCustomOption(option);
    }

    @Step
    public void pictureChoiceCustomOption(String option) {
        bundlesPage.pictureChoiceCustomOption(option);
    }

    @Step
    public void verifyClickImageIndex(int index){
        bundlesPage.verifyClickImageIndex(index);
    }

    @Step
    public int getListSlideImage(){
        return bundlesPage.getListSlideImage();
    }

    @Step
    public boolean getCustomOptionByAPI(String productName) {
        String getShopDomain = LoadObject.getProperty("shop");
        String api = "https://" + getShopDomain + "/api/catalog/next/product/"+ productName.replace(" ", "-").toLowerCase(Locale.ROOT) + ".json";

        JsonPath response = bundlesPage.getAPI(api);

        ArrayList haveCustomOption = bundlesPage.getDataByKey(response, "result.custom_options");
        if(haveCustomOption.size() == 0 ){
            return false;
        }else {
            return true;
        }
    }

    @Step
    public void verifyMsgTextValidate(String message){
        bundlesPage.verifyMsgTextValidate(message);
    }

    @Step
    public void verifyTextProduct(String product, String text) {
        bundlesPage.verifyTextProduct(product,text);
    }

    @Step
    public void verifyImgProduct(String product, String img) {
        bundlesPage.verifyImgProduct(product,img);
    }

    @Step
    public void verifyRadioProduct(String product, String option) {
        bundlesPage.verifyRadioProduct(product, option);
    }

    @Step
    public void verifyPictureChoiceProduct(String product, String img) {
        bundlesPage.verifyPictureChoiceProduct(product, img);
    }

}
