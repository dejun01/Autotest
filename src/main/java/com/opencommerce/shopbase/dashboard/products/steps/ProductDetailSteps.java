package com.opencommerce.shopbase.dashboard.products.steps;

import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.products.pages.ProductDetailPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailSteps extends ScenarioSteps {
    ProductDetailPages productDetailPages;

    public void verifyMaxLengthOfProductTitle(String length) {
        productDetailPages.verifyMaxLengthOfProductTitle(length);
    }

    public void enterTitle(String title) {
        productDetailPages.enterTitle(title);
    }

    public void enterDescription(String description) {
        productDetailPages.enterDescription(description);
    }

    public void clickSaveChangesOrDiscard(String btn) {
        productDetailPages.clickSaveChangesOrDiscard(btn);
    }

    public void verifyMessageProductCreated(String message) {
        productDetailPages.verifyMessageProductCreated(message);
    }

    public void verifyMessageProductSavedSuccessfully() {
        productDetailPages.verifyMessageProductSavedSuccessfully();
    }

    public void verifyMessageProductSaved(String message) {
        productDetailPages.verifyMessageProductSaved(message);
    }

    public void searchProduct(String nameProduct) {
        productDetailPages.waitProductListingPageDisplayed();
        productDetailPages.searchProduct(nameProduct);
    }

    public void chooseProduct(String nameProduct) {
        productDetailPages.chooseProduct(nameProduct);
    }

    public void SearchAndSelectCollection(String nameCollection) {
        productDetailPages.searchCollection(nameCollection);
        productDetailPages.chooseCollection(nameCollection);
    }

    public void inputProductInfor(String condition) {
        List<String> cons = new ArrayList<String>(Arrays.asList(condition.split(",")));
        String label = cons.get(0);
        String value = cons.get(1);
        productDetailPages.addProductOrganizationInfo(label, value);
    }

    public void navigateToAllProductScreen() {
        productDetailPages.navigateToAllProductScreen("All products");
    }

    public void navigateToCollectionsScreen() {
        productDetailPages.navigateToCollectionScreen("Collections");
    }

    public void selectAllProduct() {
        productDetailPages.selectAllProduct();
    }

    public void clickAction() {
        productDetailPages.clickBtn("Action");
    }

    public void chooseAction(String action) {
        productDetailPages.chooseAction(action);
    }

    public void clickDelete() {
        productDetailPages.clickBtn("Delete");
    }

    public void editProductItem(String sItem, String newValue) {
        if (newValue.contains("@")) {
            newValue = newValue.replaceAll("@", "") + System.currentTimeMillis();
        }
        switch (sItem) {
            case "SKU":
                enterSKU(newValue);
                break;
            case "Type":
                enterProductType(newValue);
                break;
            case "Vendor":
                enterVendor(newValue);
                break;
            case "Price":
                enterPrice(newValue);
                break;
            case "Disable":
                disableProduct(newValue);
                break;
            case "Description":
                enterDescription(newValue);
                break;
            case "Images":
                enterImageURL(newValue);
                break;
            case "Compare at price":
                enterCompareAtPrice(newValue);
                break;
            case "Cost per item":
                enterCostPerItem(newValue);
                break;
            case "Barcode":
                enterBarcode(newValue);
                break;
            case "Inventory policy":
                inputInventoryPolicy(newValue);
                break;
            case "Quantity":
                enterQuantity(newValue);
                break;
            case "Weight":
                enterWeight(newValue);
                break;
            case "Weight Unit":
                chooseWeightUnit(newValue);
                break;
            default:
                enterTags(newValue);
                break;
        }
    }


    public void verifyProductInformation(String item, String value) {
        switch (item) {
            case "SKU":
                verifySKU(value);
                break;
            case "Type":
                verifyProductType(value);
                break;
            case "Vendor":
                verifyVendor(value);
                break;
            case "Price":
                verifyPrice(value);
                break;
            case "Disable":
                verifydisableProduct(value);
                break;
            case "Description":
                verifyDescription(value);
                break;
            case "Images":
                verifyImageURL(value);
                break;
            case "Compare at price":
                verifyCompareAtPrice(value);
                break;
            case "Cost per item":
                verifyCostPerItem(value);
                break;
            case "Barcode":
                verifyBarCode(value);
                break;
            case "Inventory policy":
                verifyInventoryPolicy(value);
                break;
            case "Quantity":
                verifyQuantity(value);
                break;
            case "Weight":
                verifyWeight(value);
                break;
            case "Weight Unit":
                verifyWeightUnit(value);
                break;
            case "Title":
                verifyProductTitle(value);
                break;
            default:
                verifyTags(value, true);
        }
    }

    public void enterImageURL(String imagesURL) {
        productDetailPages.clickAddImageURL();
        productDetailPages.enterImageURL(imagesURL);
        productDetailPages.clickBtnAddImage();
    }

    public void enterSKU(String sku) {
        productDetailPages.enterSKU(sku);
    }

    public void enterProductType(String productType) {
        productDetailPages.enterProductType(productType);
    }

    public void enterVendor(String vendor) {
        productDetailPages.enterVendor(vendor);
    }

    public void enterPrice(String price) {
        productDetailPages.enterPrice(price);
    }

    public void enterCompareAtPrice(String comparePrice) {
        productDetailPages.enterCompareAtPrice(comparePrice);
    }

    public void enterCostPerItem(String costPerItem) {
        productDetailPages.enterCostPerItem(costPerItem);
    }

    public void enterBarcode(String barCode) {
        productDetailPages.enterBarcode(barCode, 0);
    }

    public void enterTags(String tag) {
        productDetailPages.enterInputFieldWithLabel("Tags", tag);
    }

    public void inputInventoryPolicy(String policy) {
        productDetailPages.inputInventoryPolicy(policy);
    }

    public void verifyProductErrorMessage(String errorMessage) {
        productDetailPages.verifyProductErrorMessage(errorMessage);
    }

    public void verifyNoMessage() {
        productDetailPages.verifyNoMessage();
    }

    public void disableProduct(String isDisable) {
        if (Boolean.parseBoolean(isDisable)) {
            productDetailPages.disableProduct();
        } else
            productDetailPages.enableProduct();
    }

    public void enterQuantity(String quantity) {
        productDetailPages.enterQuantity(quantity);
    }

    public void selectAllow(String allow) {
        productDetailPages.selectAllow(allow);
    }

    public void enterWeight(String weight) {
        productDetailPages.enterWeight(weight);
    }

    public void chooseWeightUnit(String weightUnit) {
        productDetailPages.scrollToViewLable("Inventory policy");
        productDetailPages.selectDdlWithLabel("Weight", weightUnit);
    }

    public boolean isChanged() {
        return productDetailPages.isSavedChange();
    }

    public void clickBreadcscrumbProducts() {
        productDetailPages.closePopup();
        productDetailPages.clickBreadcrumbProducts();
    }

    public void verifyTags(String tags, boolean check) {
        if (tags.contains(",")) {
            String[] tagList = tags.split(",");
            for (String tag : tagList) {
                productDetailPages.verifyTags(tag, check);
            }
        } else {
            productDetailPages.verifyTags(tags, check);
        }
    }

    public void verifySKU(String sku) {
        productDetailPages.verifySKU(sku);
    }

    public void verifyProductType(String productType) {
        productDetailPages.verifyProductType(productType);
    }

    public void verifyTotalImage(String total) {
        productDetailPages.verifyTotalImage(total);
    }

    public void verifyVendor(String vendor) {
        productDetailPages.verifyVendor(vendor);
    }

    public void verifyPrice(String price) {
        productDetailPages.verifyPrice(price);
    }

    public void countImageVariant(String numberImage) {
        productDetailPages.countImageVariant(numberImage);
    }

    public void verifydisableProduct(String isDisable) {
        productDetailPages.verifydisableProduct(isDisable);
    }

    public void verifyDescription(String description) {
        productDetailPages.verifyDescription(description);
    }

    public void verifyImageURL(String imageURL) {
        productDetailPages.verifyImageURL(imageURL);
    }

    public void verifyCompareAtPrice(String comparePrice) {
        productDetailPages.verifyCompareAtPrice(comparePrice);
    }

    public void verifyCostPerItem(String costPerItem) {
        productDetailPages.verifyCostPerItem(costPerItem);
    }

    public void verifyBarCode(String barCode) {
        productDetailPages.verifyBarCode(barCode);
    }

    public void verifyInventoryPolicy(String inventoryPolicy) {
        productDetailPages.verifyInventoryPolicy(inventoryPolicy);
    }

    public void verifyQuantity(String quantity) {
        productDetailPages.verifyQuantity(quantity);
    }

    public void verifyWeight(String weight) {
        productDetailPages.verifyWeight(weight);
    }

    public void verifyWeightUnit(String weightUnit) {
        productDetailPages.verifyWeightUnit(weightUnit);
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public void verifyProductTitle(String title) {
        productDetailPages.verifyProductTitle(title);
    }

    public void verifyName(String nameProduct) {
        productDetailPages.verifyName(nameProduct);
    }

    public void verifyTitle(String nameProduct) {
        productDetailPages.verifyTitle(nameProduct);
    }

    //----------API
    String shop = LoadObject.getProperty("shop");

    public Long getProductIDByName(String productName, String accessToken) {

        String api = "https://" + shop + "/admin/products.json?page=1&limit=51&title=" + productName;
        JsonPath js = productDetailPages.getAPISbase(api, accessToken);
        Long idProduct = js.get("products.findAll{it.title=='" + productName + "'}.id[0]");
        return idProduct;
    }

    public JsonPath getProductInformationByID(long productID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String api = "https://" + shop + "/admin/products/" + productID + ".json";
        return productDetailPages.getAPISbase(api, accessToken);
    }

    public float getNumberProductByProductName(String productName, String accessToken) {
        Long id = getProductIDByName(productName, accessToken);
        JsonPath info = getProductInformationByID(id, accessToken);
        List<Integer> listQuantity = info.get("product.variants.inventory_quantity");
        float sum = 0;
        System.out.println(listQuantity);

        for (int quantity : listQuantity) {
            sum += quantity;
        }
        return sum;
    }

    public String getAccessToken() {
        return productDetailPages.getAccessTokenShopBase();
    }

    public float getProductOriginalPrice(JsonPath productInfor) {
        String price = productDetailPages.getDataByKey(productInfor, "product.variants.price").get(0).toString();
        return Float.parseFloat(price);
    }

    public float getProductComparePrice(JsonPath productInfor) {
        String price = productDetailPages.getDataByKey(productInfor, "product.variants.compare_at_price").get(0).toString();
        return Float.parseFloat(price);
    }

    public String getCurrentURL() {
        return productDetailPages.getCurrentURL();
    }

    public void changeURL(String newURL, boolean isCreate) {
        productDetailPages.enterNewURL(newURL);
        if (isCreate) {
            productDetailPages.checkCheckboxWithLabel("Create a URL redirect from");
        }
        productDetailPages.clickBtn("Save changes");
        productDetailPages.verifySaveProductSuccess();
    }

    public void verifyCollectionOfProduct(String nameCollection) {
        productDetailPages.verifyCollectionOfProduct(nameCollection);
    }

    public void refreshPage() {
        productDetailPages.refreshPage();
    }

    public void selectInventoryPolicy(String inventoryPolicy) {
        productDetailPages.selectDdlWithLabel("Inventory policy", inventoryPolicy);
    }

    public void verifyFrequentlyTagsDisplay(String tag1, String tag2, String tag3) {
        productDetailPages.clickInputTags(0);
        productDetailPages.verifyFrequentlyTags(tag1, tag2, tag3);
    }

    public void logInfor(String s) {
    }

    public void addTag(String tag1) {
        productDetailPages.addTags(tag1);
    }

    public void verifyPopupAllTags(String tag1, String tag2, String tag3) {
        productDetailPages.clickViewAllTags();
        productDetailPages.verifyAppliedTagsOnPopup(tag1);
        productDetailPages.verifyAvailableTagsOnPopup(tag2, tag3);
    }

    public void addTagsOnPopup(String tag1, String tag2) {
        productDetailPages.removedTags(tag1);
        productDetailPages.clickUnAppliedTags(tag2);
        productDetailPages.clickApplyChange();
        productDetailPages.verifyRemovedTagOnProductDetailPage(tag1);
        productDetailPages.verifyAddedTagOnProductDetailPage(tag2);
    }

    public void duplicateProduct(String nameProduct) {
        productDetailPages.clickDuplicateOnProductDetail();
        productDetailPages.verifyOpenPopupDuplicateProduct();
        productDetailPages.checkCheckboxWithLabel("Duplicate product medias");
        productDetailPages.clickDuplicateOnPopupDuplicateProduct();
        productDetailPages.verifyDuplicateProductSuccessfully(nameProduct);
    }

    public void clickAddVariant() {
        productDetailPages.clickLinkTextWithLabel("Add variant");
        productDetailPages.waitForEverythingComplete();
    }

    public void clickAddCustomOption() {
        try {
            productDetailPages.clickLinkTextWithLabel("Add custom option");
        } catch (Exception e) {
            productDetailPages.clickBtnCO("Create custom option only");
        }
        productDetailPages.waitForEverythingComplete();
    }

    public void enterNewOptionSet(String optionSet, int index) {
        productDetailPages.enterNewOptionSet(optionSet, index);
    }

    public void enterNewOptionValue(String optionValue, int index) {
        productDetailPages.enterNewOptionValue(optionValue, index);
    }

    public void verifyHasNoVariant() {
        productDetailPages.verifyHasNoVariant();
    }

    public void verifyHasVariant() {
        productDetailPages.verifyHasVariant();
    }

    public void verifyVariantInventory(String variant, String Inventory, String typeShop) {
        if (typeShop.equals("product")) {
            productDetailPages.verifyVariantValue(variant, Inventory, 4);
        }
    }

    public void verifyVariantPrice(String variant, String price, String typeShop) {
        if (typeShop.equals("product")) {
            productDetailPages.verifyVariantValue(variant, price, 5);
        } else if (typeShop.equals("campaign")) {
            productDetailPages.verifyVariantValue(variant, price, 4);
        }
    }

    public void verifyVariantSKU(String variant, String sku, String typeShop) {
        if (typeShop.equals("product")) {
            productDetailPages.verifyVariantValue(variant, sku, 6);
        } else if (typeShop.equals("campaign")) {
            productDetailPages.verifyVariantValue(variant, sku, 5);
        }
    }

    public void enterOptionValue(String optionValue) {
        productDetailPages.enterOptionValue(optionValue);
    }

    public void verifyNoButtonSave() {
        productDetailPages.verifyNoButtonSave();
    }

    public void verifyHasButtonSave() {
        productDetailPages.verifyHasButtonSave();
    }

    public void verifyAddVariantSuccess() {
        productDetailPages.verifyAddVariantSuccess();
    }

    public void clickEditVariant() {
        productDetailPages.clickEditVariant();
    }

    public void clickEditVariantWithValue(String optionValue) {
        productDetailPages.clickEditVariantWithValue(optionValue);
    }

    public void verifyOptionValue(String optionValue) {
        productDetailPages.verifyOptionValue(optionValue);
    }

    public void verifyOptionSet(String optionVariantName) {
        productDetailPages.verifyOptionSet(optionVariantName);
    }

    public void verifyPageTitle(String optionValue, String nameProduct) {
        productDetailPages.verifyPageTitle(optionValue, nameProduct);
    }

    public void verifyNumberOfVariant(String numberOfVariant) {
        productDetailPages.verifyNumberOfVariant(numberOfVariant);
    }

    public void verifyBarcode(String barcode) {
        productDetailPages.verifyValueFieldWithLabel("Barcode (ISBN, UPC, GTIN, etc.)", barcode);
    }

    public void verifyTrackQuantity(String trackQuantity) {
        productDetailPages.verifyTrackQuantity(trackQuantity);
    }

    public void verifyAllowOverselling(String allowOverselling) {
        productDetailPages.verifyAllowOverselling(allowOverselling);
    }

    public void verifyEditVariantSuccess() {
        productDetailPages.verifyEditVariantSuccess();
    }

    public void clickDeleteVariantButton() {
        productDetailPages.clickBtn("Delete variant");
        productDetailPages.clickBtn("Delete");
    }

    public void closeBrowserTabWithIndex(int index) {
        productDetailPages.closeBrowserWithIndex(index);
    }

    public void selectVariant(String variantDuplicate) {
        productDetailPages.selectVariant(variantDuplicate);
    }

    public void duplicateVariant(String optionSet) {
        productDetailPages.clickBtn("Action");
        productDetailPages.selectDuplicateVariants(optionSet);
    }

    public void enterNewValue(String optionSet, String newOptionValue) {
        productDetailPages.enterNewValue(optionSet, newOptionValue);
    }

    public void clickSaveButton() {
        productDetailPages.clickBtn("Save");
        productDetailPages.waitForEverythingComplete();
    }

    public void clickDeleteIcon(String qty) {
        productDetailPages.clickDeleteIcon(qty);
    }

    public void verifyMessageDuplicateVariant(String message) {
        productDetailPages.verifyMessage(message);
        productDetailPages.refreshPage();
    }

    public void verifyMessage(String message) {
        productDetailPages.verifyMessage(message);
    }

    public void clickAddAnotherOption() {
        productDetailPages.clickAddAnotherOption();
    }

    public void selectVariantOnVariantDetailPage(String variant) {
        productDetailPages.selectVariantOnVariantDetailPage(variant);
    }

    public void clickOnEditOptions() {
        productDetailPages.clickLinkTextWithLabel("Edit options");
        productDetailPages.waitForEverythingComplete();
    }

    public void clickAddAnotherOptionInEditOption() {
        productDetailPages.clickAddAnotherOptionInEditoption();
    }

    public void enterAnotherOption(String optionSet) {
        productDetailPages.enterAnotherOption(optionSet);
    }

    public void enterAnotherOptionValue(String optionSet, String optionValue) {
        productDetailPages.enterAnotherOptionValue(optionSet, optionValue);
    }

    public void verifyMessageUpdateOption(String message) {
        productDetailPages.verifyMessage(message);
    }

    public void verifyMessageDeleteOption(String message) {
        productDetailPages.verifyMessage(message);
    }

    public void enterNewOption(String oldOptionSet, String newOptionSet) {
        productDetailPages.enterNewOption(oldOptionSet, newOptionSet);
    }

    public void deleteOption(String optionSet, String optionValue) {
        productDetailPages.deleteOption(optionSet, optionValue);
    }

    public void clickEditWebsiteSEO() {
        productDetailPages.clickEditWebsiteSEO();
    }

    public void verifyDefaultPageTitle(String pageTitle) {
        productDetailPages.verifyDefaultPageTitle(pageTitle);
    }

    public void verifyDefaultMetaDescription(String metaDescription) {
        productDetailPages.verifyDefaultMetaDescription(metaDescription);
    }

    public void verifyDefaultURLAndHandle(String urlAndHandle) {
        productDetailPages.verifyDefaultURLAndHandle(urlAndHandle);
    }

    public void enterPageTitle(String pageTitle) {
        productDetailPages.enterPageTitle(pageTitle);
    }

    public void enterMetaDescription(String metaDescription) {
        productDetailPages.enterMetaDescription(metaDescription);
    }

    public void enterUrlAndHandle(String urlAndHandle) {
        productDetailPages.enterUrlAndHandle(urlAndHandle);
    }

    public void verifyDataPageTitle(String pageTitle) {
        productDetailPages.verifyDataPageTitle(pageTitle);
    }

    public void verifyLengthSeo(String title, String length) {
        productDetailPages.verifyLengthSeo(title, length);
    }

    public void verifyDataMetaDescription(String metaDescription) {
        productDetailPages.verifyDataMetaDescription(metaDescription);
    }

    public void verifyDataURLAndHandle(String urlAndHandle) {
        productDetailPages.verifyDataURLAndHandle(urlAndHandle);
    }

    public void inputTag(String tag) {
        productDetailPages.inputTag(tag, 0);
    }

    public void verifyTag(String tag) {
        productDetailPages.verifyTag(tag);
    }

    public void deleteProduct() {
        productDetailPages.clickBtn("Delete product", 1);
        productDetailPages.clickBtn("Delete product", 2);
        productDetailPages.waitAndRefreshPage();
    }

    public void verifyProductDetailPage() {
        productDetailPages.verifyProductDetailPage();
    }

    public void addProductByApi(String shop, String title, String accessToken) {
        productDetailPages.addProductByApi(shop, title, accessToken);
    }

    public String getAccessTokenShopbase() {
        return productDetailPages.getAccessTokenShopBase();
    }

    public void deleteTags() {
        productDetailPages.deleteTags();
    }

    public void clickMapProduct(String nameApp) {
        productDetailPages.refreshPage();
        productDetailPages.waitUntilInvisibleLoading(15);
        productDetailPages.clickMoreOptions();
        productDetailPages.clickMapProduct(nameApp);
    }

    public void verifyProductMapped() {
        productDetailPages.verifyProductMapped();
    }

    public void verifyButtonDisable(String button, String status, String title) {
        productDetailPages.verifyButtonDisable(button, status, title);
    }

    public void editVariant() {
        productDetailPages.editVariant();
    }

    public void selectAllowCustomerPurchaseWhenOutStock(String isAlowPurchase) {
        if (!isAlowPurchase.isEmpty())
            productDetailPages.checkCheckboxWithLabel("Allow customers to purchase this product when it's out of stock", 1, Boolean.parseBoolean(isAlowPurchase));
    }

    public void openProductDetail() {
        productDetailPages.openProductDetail();
    }

    public void viewProduct() {
        productDetailPages.viewProduct();
    }

    public void addImage(String image) {
        productDetailPages.addImage(image);
    }

    public void verifyNumberOfVariantImage(String varGroup, int imageQty) {
        productDetailPages.verifyNumberOfVariantImage(varGroup, imageQty);
    }

    public void reVerifyNumberOfVariantImage(String varGroup, int imgUnCheck, int imageQty) {
        productDetailPages.reVerifyNumberOfVariantImage(varGroup, imgUnCheck, imageQty);
    }

    public void verifyNumberOfOneVariantImage(String optionValue, String optionValueNext, int imageQty) {
        productDetailPages.verifyNumberOfOneVariantImage(optionValue, optionValueNext, imageQty);
    }

    public void reVerifyNumberOfOneVarImage(String optionValue, String optionValueNext, int imageQty, int imgUnCheck) {
        productDetailPages.reVerifyNumberOfOneVarImage(optionValue, optionValueNext, imageQty, imgUnCheck);
    }

    @Step
    public void verifyAddImage(String total) {
        productDetailPages.verifyAddImage(total);
    }

    @Step
    public void clickImageVariant(String variant) {
        productDetailPages.clickImageVariant(variant);
    }

    @Step
    public void selectImageVariant(String image) {
        productDetailPages.selectImageVariant(image);
    }

    @Step
    public void previewImageVariant(String image) {
        productDetailPages.previewImageVariant(image);
    }

    @Step
    public void clickDone() {
        productDetailPages.clickBtn("Done");
    }

    @Step
    public void clickSave() {
        productDetailPages.clickBtn("Save");
        productDetailPages.waitForPageLoad();
    }

    @Step
    public void clickSaveChanges() {
        productDetailPages.clickSaveChanges();
        productDetailPages.waitForPageLoad();
    }

    @Step
    public void verifyImageVariant(String totalImageAdded) {
        productDetailPages.verifyImageVariant(totalImageAdded);
    }

    @Step
    public void verifyGroupVariant(boolean check) {
        productDetailPages.verifyGroupVariant(check);
    }


    public void waitUntilImageVisible() {
        int i = 0;
        while (!productDetailPages.chooseImageVisible()) {
            productDetailPages.refreshPage();
            i++;
            if (i > 2)
                break;
        }
    }

    @Step
    public void verifyMessageProduct(String message) {
        productDetailPages.verifyMessageProduct(message);
    }

    public void verifyImageLink() {
        productDetailPages.verifyImageLink();
        productDetailPages.verifyImagesVariant();
    }

    @Step
    public int getNumberOfImage() {
        return productDetailPages.getNumberOfImage();
    }

    @Step
    public int getNumberOfArtwork() {
        return productDetailPages.getNumberOfArtwork();
    }

    public void verifyArtworkLink() {
        productDetailPages.verifyArtworkLink();
    }

    public void verifyImageCustomOption() {
        productDetailPages.verifyImageCustomOption();
    }

    public void verifyCollections(String collection) {
        productDetailPages.verifyCollections(collection);
    }

    @Step
    public void enterNameCo(String customOption, String name) {
        productDetailPages.inputNameCO(customOption, name);
    }

    @Step
    public void selectType(String customOption, String type) {
        productDetailPages.selectType(customOption, type);
    }

    @Step
    public void enterLabelCo(String customOption, String label) {
        productDetailPages.enterLabelCo(customOption, label);
    }

    @Step
    public void selectCheckbox(String customOption, String allowFollowing) {
        String[] list = allowFollowing.split(",");
        for (String follow : list) {
            productDetailPages.selectCheckbox(customOption, follow);
        }
    }

    @Step
    public void addAnotherOption() {
        productDetailPages.clickBtn("Add another option");
    }

    @Step
    public void hideOption(String customOption) {
        productDetailPages.hideOption(customOption);
    }

    public int countAllphoto() {
        return productDetailPages.countAllPhoto();
    }

    public int countVariantImages() {
        return productDetailPages.countVariantImages();
    }

    public int NumberOfPhotosOnStoreFront() {
        return productDetailPages.countNumberOfPhotosOnStoreFront();
    }

    @Step
    public void verifyCustomInListCO(String customOption) {
        String[] customOptionName = customOption.split(";");
        for (String co : customOptionName) {
            String[] _sCO = co.split(">");
            productDetailPages.verifyCustomOptionInProductDetail(_sCO[0], _sCO[1]);
        }
    }

    @Step
    public void clickbtnCO(String btnName) {
        productDetailPages.clickBtnCO(btnName);
    }

    // Verify show btn create preview , print file or both
    @Step
    public void verifyShowButton(String btnName, boolean b) {
        productDetailPages.verifyShowBtn(btnName, b);
    }

    //Verify image preview or print file show in product detail
    @Step
    public void verifyShowImagePersonalize(String name, boolean isShow) {
        productDetailPages.verifyShowImagePersonalize(name, isShow);
    }

    // Click openCO detail in product detail
    public void clickOpenOrCloseCODetail(String sCOName, boolean bol) {
        productDetailPages.clickOpenOrCloseCODetail(sCOName, bol);
    }

    public void verifyValueCO(String nameCO, String name, String valueEx) {
        String valueAc = productDetailPages.getValueCO(nameCO, name);
        assertThat(valueAc).isEqualToIgnoringCase(valueEx);
    }

    public void verifyLabelCO(String sCOName, String sCOLabel) {
        productDetailPages.verifyLabelCO(sCOName, sCOLabel);
    }

    @Step
    public void verifyValueTypeText(String sCOName, String type) {
        assertThat(productDetailPages.getValueTypeCOSelected(sCOName)).isEqualToIgnoringCase(type);
    }

    @Step
    public void verifyTargetLayer(String sCOName, String sTargetLayer) {
        assertThat(productDetailPages.getTargetLayer(sCOName)).isEqualToIgnoringCase(sTargetLayer);
    }

    @Step
    public void verifyValueCOChecked(String nameCO, String value) {
        String[] listValue = value.split(";");
        for (int i = 1; i < listValue.length; i++) {
            String[] listchecked = value.split(" > ");
            if (listchecked[1].equals("true"))
                productDetailPages.verifyDefaultValueTypeCOChecked(nameCO, i);
            assertThat(productDetailPages.getValueTypeCOChecked(nameCO, i)).isEqualToIgnoringCase(listchecked[0]);
        }
    }

    public void verifyValueCheckboxCO(String nameCo, String value) {
        String[] listValue = value.split(">");
        for (int i = 1; i < listValue.length; i++) {
            assertThat(productDetailPages.getValueCheckboxTypeCO(nameCo, i)).isEqualToIgnoringCase(value);
        }
    }

    @Step
    public void inputValue(String customOption, String values) {
        productDetailPages.inputValue(customOption, values);
    }

    @Step
    public void collapseCO(String customOption) {
        productDetailPages.collapseCO(customOption);
    }

    @Step
    public void addConditionalLogic(String customOption) {
        productDetailPages.addConditionalLogic(customOption);
    }

    @Step
    public void selectCondition(String condition) {
        productDetailPages.selectCondition(condition);
    }

    @Step
    public void selectValue(String value) {
        productDetailPages.selectValue(value);
    }

    @Step
    public void selectShowValue(String showValue, int number) {
        productDetailPages.selectShowValue(showValue, number);
    }

    public String getHandleUrlPro() {
        return productDetailPages.getHandleUrlPro();
    }

    @Step
    public void inputNameVariant(String name) {
        productDetailPages.inputNameVariant(name);
    }

    @Step
    public void clickProductAvailability(String productAvailability) {
        String[] listaAvailability = productAvailability.split("; ");
        for (String availability : listaAvailability) {
            String label = availability.split("--")[0];
            String check = availability.split("--")[1];
            productDetailPages.clickProductAvailability(label, Boolean.parseBoolean(check));
        }
    }

    public void enterWeightUnit(String weightUnit) {
        productDetailPages.scrollToViewLable("Inventory policy");
        productDetailPages.chooseWeightUnit(weightUnit);
    }

    public void clickAddSeo(String pageTitle, String metaDescription) {
        productDetailPages.clickAddSeo();
        productDetailPages.enterPageTitle(pageTitle);
        productDetailPages.enterMetaDescription(metaDescription);
    }

    public void enableGenerateSKU(boolean enable) {
        productDetailPages.enableGenerateSKU(enable);
    }

    public void inputTemplate(String template) {
        productDetailPages.inputTemplate(template);
    }

    public void clickSetting(String setting) {
        String[] listSetting = setting.split("--");
        for (int i = 0; i < listSetting.length; i++) {
            String[] label = listSetting[i].split(">>");
            productDetailPages.clickSetting(label[0], Boolean.parseBoolean(label[1]));
        }
    }

    public String getListImage() {
        return productDetailPages.getListImage();
    }

    public int getListMedia() {
        return productDetailPages.getListMedia();
    }

    public void selectImageInPopup(String qty) {
        productDetailPages.selectImageInPopup(qty);
    }

    public void selectImageOnPictureChoice(String imageName) {
        productDetailPages.selectImageOnPictureChoice(imageName);
    }

    public void addNewOptionOnCondtionalLogic() {
        productDetailPages.addNewOptionOnCondtionalLogic();
    }

    public void verifyConditonalLogicAfDuplicate(String customeName, String customeNameConditional, boolean isShow) {
        if (!customeNameConditional.isEmpty())
            productDetailPages.verifyConditionalLogicAfDuplicate(customeName, customeNameConditional, isShow);
    }

    public void inputValueInCheckboxConditional(String valueCheckbox) {
        productDetailPages.inputValueInChekboxConditional(valueCheckbox);
    }

    public String getHandleCampaignByAPI(String campaignName, String accessToken) {
        String api = "https://" + shop + "/admin/pbase-campaigns.json?page=1&limit=50&query=&status=all&search=&product_availability=&append=false&isFetchPrevPage=false";
        JsonPath js = productDetailPages.getAPISbase(api, accessToken);
        String handleName = js.get("campaigns.findAll{it.campaign_title=='" + campaignName + "'}.handle[0]");
        return handleName;
    }

    @Step
    public void clickDeleteCO() {
        productDetailPages.clickDeleteCO();
    }

    @Step
    public String getShopID() {
        return productDetailPages.getShopID();
    }

    @Step
    public void clickToExpandFilterOptionsInHiveSbase() {
        productDetailPages.clickToExpandFilterOptionsInHiveSbase();
    }

    @Step

    public void inputShopIDInHive(String shopID) {
        if (!shopID.isEmpty()) {
            productDetailPages.inputShopIDInHive(shopID);
        }
    }

    @Step
    public void clickToPlatformType(String platformType) {
        productDetailPages.clickToPlatformType(platformType);
    }

    @Step
    public void verifyTotalProductsInProductModerationListPage(String totalProduct) {
        productDetailPages.verifyTotalProductsInProductModerationListPage(totalProduct);
    }

    @Step
    public void verifyListProductsInProductModerationListPage(String listProductExpect) {
        productDetailPages.verifyListProductsInProductModerationListPage(listProductExpect);
    }

    public void verifyDisableEditCampPhhub(String isDisable, String nameCO) {
        if (!isDisable.isEmpty()) {
            productDetailPages.verifyDisableEditCampPhhub(isDisable, nameCO);
        }
    }

    public void verifyDisableAllowCharacters(String isDisable, String customName) {
        if (!isDisable.isEmpty()) {
            productDetailPages.verifyDisableAllowCharacter(isDisable, customName);
        }
    }

    public void verifyDisableCOradio(String isDisable, String values, String _sCOName) {
        if (!isDisable.isEmpty()) {
            productDetailPages.verifyDisableCOradio(isDisable, values, _sCOName  );
        }
    }

    public void verifyDisableCheckboxCO(String isDisable) {
        if (!isDisable.isEmpty()) {
            productDetailPages.verifyDisableCheckboxCO(isDisable);
        }
    }

    public void verifyDisablePictureChoice() {
        productDetailPages.verifyDisablePictureChoice();
    }

    public void verifyButtonPersonalization(String buttonName) {
        productDetailPages.verifyButtonPersonalization(buttonName);
    }

    public void clickCreatePersonalization() {
        productDetailPages.clickCreatePersonalization();
    }

    public void selectImageVariant(String variant, String positionImage) {
        productDetailPages.selectImageVariant(variant, positionImage);
    }

    public void clickSaveImage() {
        productDetailPages.clickSaveImage();
    }

    public String getImageVariant() {
        return productDetailPages.getImageVariant();
    }

    public void clickAddProductButton() {
        productDetailPages.clickAddProductButton();
    }

    @Step
    public void inputCreatedFrom() {
        productDetailPages.inputCreatedFrom();
    }
    @Step
    public void inputCreatedTo() {
        productDetailPages.inputCreatedTo();
    }
    @Step
    public void searchProductByNamePlus(String sProducts) {
        productDetailPages.searchProductByNamePlus(sProducts);
    }
    @Step
    public void selectProductPlusInCatalog(String sProducts) {
        productDetailPages.selectProductPlusInCatalog(sProducts);
    }
    @Step
    public void inputTiltePlus(String sProduct, String title) {
        productDetailPages.inputTitlePlus(sProduct, title);
    }
    @Step
    public void selectPaymentStatusInHive(String paymentStatus) {
        productDetailPages.selectPaymentStatusInHive(paymentStatus);
    }
}

