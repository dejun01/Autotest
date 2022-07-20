package com.opencommerce.shopbase.storefront.steps.shop;


import com.opencommerce.shopbase.dashboard.products.pages.ProductDetailPages;
import com.opencommerce.shopbase.storefront.pages.shop.ProductPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Java6Assertions;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static com.opencommerce.shopbase.OrderVariable.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps<themeStyle> extends ScenarioSteps {
    ProductPage productPage;
    ProductDetailPages productDetailPages;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void clickAddToCart() {
        productPage.waitForPageLoad();
        productPage.clickOnAddToCartBtn();
        productPage.waitForEverythingComplete();
        productPage.waitUntilInvisibleLoading(8);
    }

    @Step
    public void clickCheckOut() {
        productPage.clickBtn("Checkout");
        productPage.waitForPageLoad();
        productPage.waitForEverythingComplete();
    }

    @Step
    public void setQuantityProduct(String quantity) {
        String quantityActual = productPage.getCurrentQuantityOfProductOrdered();
        float t = Integer.parseInt(quantity) - Integer.parseInt(quantityActual);
        if (t > 0) {
            for (int i = 1; i <= t; i++) {
                productPage.addProduct();
            }
        } else if (t < 0) {
            for (int i = 1; i <= -t; i++) {
                productPage.removeProduct();
            }
        }
    }

    @Step
    public HashMap<String, List<String>> addMultipleProductsToCart(String productNames) {
        String[] prods = productNames.split(";");
        for (String prod : prods) {
            selectProductByVariantAndAddToCart(prod.trim());
        }
        return productListAdded;
    }
    @Step
    public HashMap<String, List<String>> addMultipleProductsToCartWithMappingPhub(String productNames) {
        String[] prods = productNames.split(";");
        for (String prod : prods) {
            selectProductByVariantAndAddToCartWithMappingPhub(prod.trim());
        }
        return productListAdded;
    }
    @Step
    public void selectProductByVariantAndAddToCartWithMappingPhub(String productHasVariantAndQuantity) {
        String productName = productHasVariantAndQuantity;
        String productPrice = "";
        String quantity = "1";

        if (!productHasVariantAndQuantity.isEmpty()) {
            if (productHasVariantAndQuantity.contains(">")) {
                String[] pr = productHasVariantAndQuantity.split(">");
                productName = pr[0];
                quantity = pr[1];
            }
            if (productName.contains(":")) {
                String[] prod = productName.split(":");
                productName = prod[0];
                productVariant = prod[1];
            }
            searchAndSelectProductWithMappingPhub(productName);
            selectVariant(productVariant);
            quantity = quantity.contains("@quantity") ? quantity.replace("@quantity", itemWarehouse) : quantity;
            setQuantityProduct(quantity);

            if (productVariant.isEmpty()) {
                productVariant = getProductVariant();
            } else {
                productVariant.replaceAll(",", " / ");
            }
            productPrice = getProductPrice();
        }

        if (!productVariant.isEmpty()) {
            productListAdded.put(productName + ":" + productVariant, asList(quantity, productPrice));

        } else {
            productListAdded.put(productName, asList(quantity, productPrice));
        }
        //Quan Le: gan tam quantity o day de tinh base cost (vi base cost trong order dang hien thi khong chinh xac)
        productQuantity += Integer.parseInt(quantity);
        variantListAdded.add(productName + ":" + productVariant);
        clickAddToCart();
    }
    @Step
    public void selectProductByVariantAndAddToCart(String productHasVariantAndQuantity) {
        String productName = productHasVariantAndQuantity;
        String productPrice = "";
        String quantity = "1";

        if (!productHasVariantAndQuantity.isEmpty()) {
            if (productHasVariantAndQuantity.contains(">")) {
                String[] pr = productHasVariantAndQuantity.split(">");
                productName = pr[0];
                quantity = pr[1];
            }
            if (productName.contains(":")) {
                String[] prod = productName.split(":");
                productName = prod[0];
                productVariant = prod[1];
            }
            searchAndSelectProduct(productName);
            selectVariant(productVariant);
            quantity = quantity.contains("@quantity") ? quantity.replace("@quantity", itemWarehouse) : quantity;
            setQuantityProduct(quantity);

            if (productVariant.isEmpty()) {
                productVariant = getProductVariant();
            } else {
                productVariant.replaceAll(",", " / ");
            }
            productPrice = getProductPrice();
        }

        if (!productVariant.isEmpty()) {
            productListAdded.put(productName + ":" + productVariant, asList(quantity, productPrice));

        } else {
            productListAdded.put(productName, asList(quantity, productPrice));
        }
        //Quan Le: gan tam quantity o day de tinh base cost (vi base cost trong order dang hien thi khong chinh xac)
        productQuantity += Integer.parseInt(quantity);
        variantListAdded.add(productName + ":" + productVariant);
        clickAddToCart();
    }

    @Step
    public void selectVariant(String variant) {
        if (!variant.isEmpty()) {
            String[] options = variant.split(",");
            for (String option : options) {
                productPage.selectOption(option.trim());
            }
        }
    }

    @Step
    public void searchAndSelectProductHasVariant(String productName) {
        if (productName.contains(":")) {
            String[] prod = productName.split(":");
            searchAndSelectProduct(prod[0]);
            selectVariant(prod[1]);
        } else {
            searchAndSelectProduct(productName);
        }
    }

    @Step
    public void searchAndSelectProduct(String productName) {
        if (!productName.isEmpty()) {
            searchProduct(productName);
            selectProduct(productName);
        }
    }
    @Step
    public void searchAndSelectProductWithMappingPhub(String productName) {
        if (!productName.isEmpty()) {
            searchProduct(productName);
            selectProductWithMappingPhub(productName);
        }
    }
    @Step
    public void searchProduct(String productName) {
        productPage.clickBtnSearch();
        productPage.enterProduct(productName);
        productPage.waitForPageLoad();
    }

    @Step
    public void selectProduct(String productName) {
        productPage.selectProduct(productName);
    }
    @Step
    public void selectProductWithMappingPhub(String productName) {
        productPage.selectProductWithMappingPhub(productName);
    }
    @Step
    public void openPage(String sPage) {
        String shop = LoadObject.getProperty("shop");
        if (!sPage.isEmpty()) {
            if (sPage.startsWith("http://") || sPage.startsWith("https://")) {
                productPage.openUrl(sPage);
                productPage.maximizeWindow();
            } else if (sPage.contains("admin")){
                accesstoken = productPage.getAccessTokenShopBase();
                String url = "https://" + shop + sPage + "?access_token=" + accesstoken;
                System.out.println(url);
                productPage.openUrl(url);
                productPage.maximizeWindow();
            } else if (sPage.contains("/")) {
                String url = "https://" + shop + sPage;
                System.out.println(url);
                productPage.openUrl(url);
                productPage.maximizeWindow();
            } else {
                productPage.openUrl("https://" + shop);
                searchAndSelectProduct(sPage);
            }
        }
    }

    @Step
    public void goToCart() {
        try {
            switch (themeStyle) {
                case "bassy":
                    productPage.gotoCartByLink();
                    break;
                default:
                    productPage.openMiniCartOrCartDrawer();
                    productPage.clickBtnGoToCart();
            }
        } catch (Exception ex) {
            productPage.gotoCartByLink();
        }
        productPage.waitForPageLoad();
        productPage.waitForEverythingComplete();
        productPage.waitUntilCartLoadSuccessfully();
    }

    @Step
    public void closeCart() {
        switch (themeStyle) {
            case "bassy":
                productPage.closeMiniCart();
            default:
                break;
        }
    }

    @Step
    public void inputCustomOption(String customOption) {
        if (!customOption.isEmpty()) {
            String[] option = customOption.split(";");
            for (String item : option) {
                String[] data = item.split(":");
                String opt = data[0];
                String value = data[1];
                inputValueCustomOption(opt, value);
            }
        }
    }

    @Step
    public void inputValueCustomOption(String opt, String value) {
        productPage.inputCustomOption(opt, value);
    }

    @Step
    public void addToCartWithQuantity(String quantity) {
        setQuantityProduct(quantity);
        clickAddToCart();
    }

    @Step
    public void verifyProductPage(String productName) {
        productPage.verifyProductPage(productName);
    }

    @Step
    public void deleteCookie() {
        productPage.deleteAllCookies();
    }

    @Step
    public void verifyEnableProductGalleryPopup(boolean isenableProductGalleryPopup) {
        productPage.clickProductImage();
        productPage.verifyEnableProductGalleryPopup(isenableProductGalleryPopup);

    }

    @Step
    public void verifyShowBreadcrumbLinks(boolean isShowBreadcrumbLinks) {
        productPage.verifyShowBreadcrumbLinks(isShowBreadcrumbLinks);
    }

    @Step
    public void verifyShowVendor(boolean isShowVendor) {
        productPage.verifyShowVendor(isShowVendor);
    }

    @Step
    public void verifyShowSKU(String theme, boolean isShowSKU) {
        productPage.verifyShowSKU(theme, isShowSKU);
    }

    @Step
    public void verifyShowPriceSavings(boolean isShowPriceSavings) {
        productPage.verifyShowPriceSavings(isShowPriceSavings);
    }

    @Step
    public void verifyShowCollections(boolean isShowCollections) {
        productPage.verifyShowCollections(isShowCollections);
    }

    @Step
    public void verifyShowTypes(boolean isShowTypes) {
        productPage.verifyShowTypes(isShowTypes);
    }

    @Step
    public void verifyShowTags(boolean isShowTags) {
        productPage.verifyShowTags(isShowTags);
    }

    @Step
    public void verifyShowSocialMediaShareIcons(boolean isShowSocialMediaShareIcons) {
        productPage.verifyShowSocialMediaShareIcons(isShowSocialMediaShareIcons);
    }

    @Step
    public void verifyShowStickyButton(boolean isShowStickyButton) {
        productPage.scrollToFooter();
        productPage.verifyShowStickyButton(isShowStickyButton);
    }

    @Step
    public void verifyOptionsStyle(String optionsStyle) {
        productPage.verifyOptionsStyle(optionsStyle);
    }

    @Step
    public void verifySwatches(String optionsStyle, boolean enableVariantGroupSwatches, boolean enableColorSwatches) {
        if (optionsStyle.equals("Buttons")) {
            productPage.verifyEnableVariantGroupSwatches(enableVariantGroupSwatches);
            productPage.verifyEnableColorSwatches(enableColorSwatches);
        }
    }

    @Step
    public void verifyShowQuantityBox(boolean showQuantityBox, boolean showQuantityBoxInSameLine, boolean isshowQuantityBoxAndATCbtn) {
        productPage.verifyShowQuantityBox(showQuantityBox, showQuantityBoxInSameLine, isshowQuantityBoxAndATCbtn);
    }

    @Step
    public void verifyTextFor0Sproduct(String textFor0Sproduct) {
        productPage.verifyTextFor0Sproduct(textFor0Sproduct);
    }

    @Step
    public void clickToViewProduct() {
        if (!productPage.isElementExist("//span[normalize-space()='View']", 2)) {
            productDetailPages.clickBreadcrumbProducts();
        }
        productPage.clickLinkTextWithLabel("View");
    }

    @Step
    public void switchToWindowWithIndex(int tab) {
        productPage.switchToWindowWithIndex(tab);
        productPage.waitForEverythingComplete(60);
    }

    @Step
    public void verifyVariantLabel(String variantLabel) {
        productPage.verifyVariantLabel(variantLabel);
    }

    @Step
    public void selectOption(String variants) {
        String[] options = variants.split(",");
        if (options.length >= 2 && !options[1].contains(" ")) {
            productPage.selectVariant(options[0]);
            productPage.selectColor(options[1]);
        } else {
            for (String variant : options) {
                productPage.selectVariant(variant);
            }
        }
    }

    @Step
    public void verifyDescription(String description) {
        productPage.verifyDescription(description);
    }

    @Step
    public void verifyImage() {
        productPage.verifyImage();
    }

    @Step
    public void verifyPrice(String price) {
        productPage.verifyPrice(price);
    }

    @Step
    public void verifyCustomOption(String showCustom) {
        productPage.verifyCustomOption(showCustom);
    }

    @Step
    public void verifyCompareAtPrice(String compareAtPrice) {
        productPage.verifyCompareAtPrice(compareAtPrice);
    }

    @Step
    public void selectColorOnStorefront(String variant) {
        productPage.selectColor(variant);
    }

    @Step
    public String getProductPrice() {
        String productPrice = productPage.getProductPrice();
        if (productPrice.equalsIgnoreCase("free")) {
            productPrice = "$0.00";
        }
        return productPrice;
    }

    @Step
    public String getProductVariant() {
        return productPage.getProductVariant();
    }

    @Step
    public void checkApplyRedirectProductOnSF(String currentURL, String newURL, String productName, boolean isCreate) {
        productPage.openUrlInNewTab("");
        productPage.switchToWindowWithIndex(1);
        String url = ("https://" + shop + "/products/" + currentURL);
        productPage.navigateToUrl(url);
        String exactURL = productPage.getCurrentUrl();
        String url1 = ("https://" + shop + "/products/" + newURL);
        if (isCreate) {
            assertThat(exactURL).isEqualTo(url1);
            productPage.verifyProductPageDisplay(productName);
        } else {
            assertThat(exactURL).isEqualTo(url);
            productPage.verifyPageNotFound();
        }
        productPage.closeBrowser();
        productPage.switchToWindowWithIndex(0);
    }

    @Step
    public void backToProductPage() {
        productPage.switchToTab("Products");

    }

    @Step
    public void enterURLwithProHandle(String productHandle) {
        String url = ("https://" + shop + "/" + productHandle);
        productPage.navigateToUrl(url);
    }

    @Step
    public void verifyRedirectproductWithHandle(Boolean cases, String productHandle) {
        if (cases) {
            productPage.verifyProductPageDisplay(productHandle);
            productPage.verifyExactURL(productHandle);
        } else
            productPage.verifyPageNotFound();
    }

    @Step
    public void openMiniCartOrCartDrawer() {
        productPage.openMiniCartOrCartDrawer();
    }

    @Step
    public void verifyTabPosition(String tabPosition) {
        if (!tabPosition.isEmpty()) {
            productPage.verifyTabPosition(tabPosition);
        }
    }

    @Step
    public void verifyShowTrustBadgeImage(boolean showTrustBadgeImage) {
        productPage.verifyShowTrustBadgeImage(showTrustBadgeImage);
    }

    @Step
    public void verifyVariantDeleted(String variant) {
        productPage.verifyVariantDeleted(variant);
    }

    public void verifyShowPreviewBtn(boolean isShow) {
        productPage.isShowPreviewBtn(isShow);

    }

    public void clickPreviewBtn() {
        productPage.clickPreviewBtn();
    }

    @Step
    public void verifyShowPopupPreview() {
        productPage.verifyShowPopupPreview();
        productPage.WaitUntilVisibleIconLoadingPreview();
    }

    @Step
    public void verifyName(String nameProduct) {
        if (!nameProduct.isEmpty())
            productPage.verifyName(nameProduct);
    }

    @Step
    public float getSalePriceOnSF() {
        return productPage.getSalePrice();

    }

    @Step
    public float getCompareAtPriceVariantOnSF() {
        return productPage.getCompareAtPrice();
    }

    @Step
    public void isShowSizeChart(boolean isShow) {
        productPage.isShowSizeChart(isShow);
    }

    @Step
    public void selectProductOnSizeChart(String baseProduct) {
        productPage.selectProductOnSizeChart(baseProduct);
    }

    @Step
    public void selectUnitOnSizeChart(String unit) {
        productPage.selectUnitOnSizeChart(unit);
    }

    @Step
    public void verifyShowImageSizeChart() {
        productPage.verifyShowImageSizeChart();
    }

    @Step
    public void verifyDataSizeChart(String size, String width, String length) {
        productPage.verifyDataSizeChart(size, width, length);
    }

    @Step
    public void closeSizeChartPopup() {
        productPage.closeSizeChartPopup();
    }

    @Step
    public void openSizeChart() {
        productPage.openSizeChartPopup();
    }

    @Step
    public void selectStyle(String style) {
        if (productPage.isExistStyleLabel()) {
            productPage.switchToProducts(style,"");
        }
        productPage.verifyStyle(style);
    }

    @Step
    public void selectStyleSFNext(String style) {
        if (productPage.isExistStyleLabel()) {
            productPage.clickStyleSFNext(style);
        }
        productPage.verifyStyle(style);
    }

    @Step
    public void selectSize(String size) {
        productPage.selectSize(size);
    }

    @Step
    public void selectQuantity(String quantity) {
        productPage.inputQuantityOnProductDetail(quantity);
    }

    @Step
    public void clearLocalStorage() {
        productPage.clearLocalStorage();
    }

    @Step
    public void refreshPage() {
        productPage.refreshPage();
    }

    @Step
    public void verifyNewURL(String urlAndHandleInitial) {
        productPage.verifyNewURL(urlAndHandleInitial);
    }

    @Step
    public void verifyNewPageTitle(String pageTitleInitial) {
        productPage.verifyNewPageTitle(pageTitleInitial);
    }

    public void clickHeader() {
        productPage.clickHeader();
    }

    @Step
    public void verifyPriceSaving(String price, String compareAtPrice) {
        productPage.verifyPriceSaving(price, compareAtPrice);
    }

    @Step
    public void verifyVendor(String vendor) {
        productPage.verifyVendor(vendor);
    }

    @Step
    public void verifySKU(String sku) {
        productPage.verifySKU(sku);
    }

    @Step
    public void verifyProductType(String productType) {
        productPage.verifyProductType(productType);
    }

    @Step
    public void verifyTag(String tag) {
        productPage.verifyTag(tag);
    }

    @Step
    public void verifyCollection(String collectionName) {
        productPage.verifyCollection(collectionName);
    }

    @Step
    public void verifyTitleDisplay(String title) {
        productPage.verifyTitleDisplay(title);
    }

    @Step
    public void clickBuyNow() {
        productPage.waitForPageLoad();
        productPage.clickButtonBuyNow();
        productPage.waitForEverythingComplete();
        productPage.waitUntilInvisibleLoading(8);
    }

    @Step
    public void clickIconCart() {
        productPage.clickIconCart();
    }

    @Step
    public void selectValueRadio(String customOption, String value) {
        productPage.selectValueRadio(customOption, value);
    }

    @Step
    public void selectValueDroplist(String customOption, String value) {
        productPage.selectValueDroplist(customOption, value);
    }

    @Step
    public void uploadImageCO(String customOption, String value) {
        productPage.uploadImageCO(customOption, value);
    }

    @Step
    public void inputTextCO(String customOption, String value) {
        productPage.inputTextCO(customOption, value);
    }

    @Step
    public void verifyValue(String value, int row) {
        productPage.verifyValue(value, row);
    }

    @Step
    public void clickVendor(String vendor) {
        productPage.clickVendor(vendor);
    }

    @Step
    public void clickTag(String tag) {
        productPage.clickTag(tag);
    }

    @Step
    public void clickCollection(String collection) {
        productPage.clickCollection(collection);
    }

    @Step
    public void verifyHandle(String handle) {
        productPage.verifyHandle(handle);
    }

    @Step
    public void verifyListProduct(String totalProduct) {
        productPage.verifyListProduct(totalProduct);
    }

    @Step
    public void verifyImageVariant(String imageVariant) {
        productPage.verifyImageVariant(imageVariant);
    }

    @Step
    public void verifySaleType(Boolean isShowPriceSavings, String saleType) {
        if (isShowPriceSavings) {
            productPage.verifySaleType(saleType);
        }
    }

    public void switchToLatestTab() {
        productPage.switchToLatestTab();
    }

    public void removeMappingInFulfillmentServices(String app) {
        productPage.clickOption(app);
        productPage.removeMappingProduct();
        productPage.confirmDeleteMappingProduct();
    }

    public void verifyNotiCO(String label, String sMessageNoti) {
        productPage.verifyNotiCO(label, sMessageNoti);
    }

    public void verifyAddToCartFeatureProduct(String productName) {
        productPage.verifyAddToCartFeatureProduct(productName);
    }

    public void clearProductOnCart() {
        productPage.clearProductOnCart();
    }

    public void verifyBuyNowFeatureProduct(String productName) {
        productPage.verifyBuyNowFeatureProduct(productName);
    }

    public void clickBtnBuyNow() {
        productPage.clickBtnBuyNow();
    }

    public void clickViewProductDetails() {
        productPage.clickViewProductDetails();
    }

    public void clickProductName(String productName) {
        productPage.clickProductName(productName);
    }

    public void openHomePage() {
        productPage.openHomePage();
    }

    public void verifyOpenProductDetail(String productName) {
        productPage.verifyOpenProductDetail(productName);
    }

    public void closeCheckOutPage() {
        productPage.closeCheckOutPage();
    }

    public BufferedImage verifyImageProductPage(String sImageAc) throws IOException {
        productPage.getImageProductWithURL(sImageAc);
        BufferedImage actualImage = ImageIO.read(new File(LoadObject.getFilePath(File.separator + sImageAc + ".png")));

        return actualImage;
    }

    public BufferedImage verifyImageArtwork(String sImageAc) throws IOException {
        productPage.getImageArtwork(sImageAc);
        BufferedImage actualImage = ImageIO.read(new File(LoadObject.getFilePath(File.separator + sImageAc + ".png")));

        return actualImage;
    }

    public void goToCartOnStore() {
        try {
            productPage.openMiniCartOrCartDrawer();
            productPage.clickBtnGoToCart();
        } catch (Exception ex) {
            productPage.gotoCartByLink();
        }
        productPage.waitForEverythingComplete();
    }

    public void addProductsToCart(String product) {
        String[] prods = product.split(";");
        for (String prod : prods) {
            selectVariantOfProduct(prod.trim());
            closeCart();
        }
    }

    private void selectVariantOfProduct(String variantInProduct) {
        String productName = variantInProduct;
        String quantity = "1";

        if (!variantInProduct.isEmpty()) {
            if (variantInProduct.contains(">")) {
                String[] pr = variantInProduct.split(">");
                productName = pr[0];
                quantity = pr[1];
            }
            if (productName.contains(":")) {
                String[] prod = productName.split(":");
                productName = prod[0];
                productVariant = prod[1];
            }
            searchAndSelectProduct(productName);
            selectVariant(productVariant);
            setQuantityProduct(quantity);
            String label = "Please fill out this field";
            if (productPage.isExitElementPersonal(label)) {
                productPage.enterInputFieldWithLabel(label, "Personal");
            }
        }
        addToCart();
    }

    public void addToCart() {
        productPage.waitForPageLoad();
        productPage.clickOnAddToCartBtn();
        productPage.waitForEverythingComplete(15);
    }

    public void verifyNamCustomOption(String sNameCO) {
        productPage.verifyNamCustomOption(sNameCO);
    }

    public void selectFolder(String sFolder) {
        productPage.selectFolder(sFolder);
    }

    public void verifyNameImageDisplatAsImageThumbnail(String sImage) {
        String[] arr = sImage.split(",");
        if (!sImage.isEmpty()) {
            for (int i = 0; i < arr.length; i++) {
                assertThat(arr[i]).isEqualTo(productPage.getValueImage(i+1).split(" ")[1]);
            }
        }
    }

    public void verifyCO(String sNameCO) {
        productPage.verifyCO(sNameCO);
    }

    public void verifyNameImageInFolderDisplatAsCheckList(String _sImage1) {
        String[] arr = _sImage1.split(",");
        List<String> listSizes = new ArrayList<>(Arrays.asList(arr));
        if (!_sImage1.isEmpty()) {
            for (int i = 2; i <= listSizes.size() + 1; i++) {
                Java6Assertions.assertThat(listSizes).contains(productPage.getNameImageInFolder(i));
            }
        }
    }

    public void verifyNameImageInGroupDisplatAsCheckList(String _sImage2) {
        String[] arr = _sImage2.split(",");
        List<String> listSizes = new ArrayList<>(Arrays.asList(arr));
        if (!_sImage2.isEmpty()) {
            for (int i = 2; i <= listSizes.size() + 1; i++) {
                Java6Assertions.assertThat(listSizes).contains(productPage.getNameImageInGroup(i));
            }
        }
    }

    public void verifyTitleRadio1(String sCustomOption, String sIsCheck) {
        String[] arr = sIsCheck.split(">");
        productPage.verifyTitleRadio1(sCustomOption);
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                productPage.verifyTitleTextField(arr[i]);
            } else {
                productPage.verifyValueTextField(arr[i]);
            }
        }
    }

    public void verifyTitleRadio2(String sValue, String sIsCheck) {
        String[] arr = sIsCheck.split(">");
        productPage.clickOnRadioBtn(sValue);
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                productPage.verifyTitleTextArea(arr[i]);
            } else {
                productPage.verifyValueTextArea(arr[i]);
            }
        }
    }

    public void verifyTitleImage(String sCustomOption) {
        productPage.verifyTitleImage(sCustomOption);
    }

    public void verifyTitleDroplist(String sCustomOption, String sValue) {
        String[] arr = sValue.split(">");
        productPage.verifyTitleDroplist(sCustomOption);
        String[] value = productPage.verifyValueDroplist();
        for (int i = 0; i < arr.length; i++) {
            assertThat(arr[i].contains(value[i].trim()));
        }
    }

    public void verifyTitleCheckbox(String sCustomOption) {
        productPage.verifyTitleCheckbox(sCustomOption);
    }

    public void verifyTitlePictureFolder(String sCustomOption) {
        productPage.verifyTitlePictureFolder(sCustomOption);
    }

    public void verifyTitlePictureGroup(String sCustomOption, String sValue) {
        String[] arr = sValue.split(">");
        productPage.verifyTitlePictureGroup(sCustomOption);
        String[] value = productPage.verifyValuePictureGroup();
        for (int i = 0; i < arr.length; i++) {
            assertThat(arr[i].contains(value[i + 1].trim()));
        }
    }

    public void inputTextField(String sCustomOption, String sValue) {
        productPage.inputTextField(sCustomOption, sValue);
    }

    public void inputImage(String sValue) {
        if (!sValue.isEmpty()) {
            String[] arr = sValue.split(">");
            productPage.uploadImage(arr[1]);
            productPage.cropImage();
        }
    }

    public void selectImagePictureFolder(String _sValue) {
        productPage.selectImagePictureFolder(_sValue);
    }

    public void selectImagePictureGroup(String sCustomOption,String _sValue) {
        productPage.selectImagePictureGroup(sCustomOption,_sValue);
    }

    public void orderCamp() throws Exception {
        productPage.clickOnAddToCartButton();
        productPage.checkout();
        productPage.clickOnCheckOutBtn();
    }

    public void verifyImageLoaded() {
        productPage.verifyImageLoaded();
    }

    public void verifyListMedia(String listImage, int totalMedia) {
        productPage.verifyListMedia(listImage, totalMedia);
    }

    public void removeProductOnCartPage() {
        productPage.gotoCartByLink();
        productPage.removeProductOnCartPage();
    }

    public void verifyProductSelectedOnSizeChart(String style) {
        String value = productPage.getValueSelectedOnSizeChart();
        assertThat(value).contains(style);
    }

    @Step
    public void verifyAndInputOptionText(String optionText) {
        if (!optionText.isEmpty()) {
            String[] optionTexts = optionText.split(";");
            for (String option : optionTexts) {
                String label;
                if (option.contains(">")) {
                    String[] text = option.split(">");
                    label = text[0];
                    productPage.InputOptionText(label, text[1].trim());
                } else label = option;
                verifyLabelOptionText(label, true);
            }
        }
    }

    @Step
    public void inputOptionText(String label, String value) {
        if (!label.isEmpty() & !value.isEmpty())
            productPage.InputOptionText(label, value);
    }

    public void verifyLabelOptionText(String label, boolean isShow) {
        if (!label.isEmpty())
            Java6Assertions.assertThat(productPage.isExistLabelOptionText(label)).isEqualTo(isShow);
    }

    @Step
    public void verifyAndInputOptionImageAndCropImage(String optionImage, Boolean isCrop) {
        if (!optionImage.isEmpty()) {
            String[] options = optionImage.split(";");
            for (String option : options) {
                String label, value = null;
                if (option.contains(">")) {
                    String[] image = option.split(">");
                    label = image[0].trim();
                    value = image[1].trim();
                } else label = option;
                verifyLabelOptionImage(label, true);
                productPage.InputOptionImageAndCropImage(label, value, isCrop);
            }
        }
    }

    @Step
    public void inputOptionImage(String lable, String image, Boolean isCrop) {
        if (!lable.isEmpty() & !image.isEmpty())
            productPage.InputOptionImageAndCropImage(lable, image, isCrop);
    }

    @Step
    public void verifyLabelOptionImage(String label, Boolean isShow) {
        if (!label.isEmpty())
            Java6Assertions.assertThat(productPage.isExistLabelOptionImage(label)).isEqualTo(isShow);
    }

    @Step
    public void verifyLabelOptionPictureChoice(String label, Boolean isShow) {
        if (!label.isEmpty())
            Java6Assertions.assertThat(productPage.isExistLabelOptionPictureChoice(label)).isEqualTo(isShow);
    }

    @Step
    public void verifyAndInputOptionPictureChoice(String optionPicture) {
        if (!optionPicture.isEmpty()) {
            String[] options = optionPicture.split(";");
            for (String option : options) {
                String label;
                if (option.contains(">")) {
                    String[] image = option.split(">");
                    label = image[0];
                    productPage.choosePictureChoice(label, image[1].trim());
                } else label = option;
                verifyLabelOptionPictureChoice(label, true);
            }
        }
    }

    @Step
    public void chooseImagePictureChoice(String label, String imageChoose) {
        productPage.choosePictureChoice(label, imageChoose);
    }

    @Step
    public void openProductWithUrl(String urlProductOnSF) {
        productPage.openUrl(urlProductOnSF);
    }

    @Step
    public void verifyPhotoGuideOnSF(String customName, Boolean isShow) {
        productPage.verifyShowPhotoGuideOnSF(customName, isShow);
    }

    @Step
    public void isShowPageNotExist(boolean bol) {
        assertThat(productPage.isShowPageNotExist()).isEqualTo(bol);
    }

    @Step
    public void verifyClipart(String sNameClipart) {
        productPage.verifyClipart(sNameClipart);
    }

    @Step
    public void selectProductByVariant(String productHasVariantAndQuantity) {
        String productName = productHasVariantAndQuantity;
        String productPrice = "";
        String quantity = "1";

        if (!productHasVariantAndQuantity.isEmpty()) {
            if (productHasVariantAndQuantity.contains(">")) {
                String[] pr = productHasVariantAndQuantity.split(">");
                productName = pr[0];
                quantity = pr[1];
            }
            if (productName.contains(":")) {
                String[] prod = productName.split(":");
                productName = prod[0];
                productVariant = prod[1];
            }
            searchAndSelectProduct(productName);
            selectVariant(productVariant);
            setQuantityProduct(quantity);

            if (productVariant.isEmpty()) {
                productVariant = getProductVariant();
            } else {
                productVariant.replaceAll(",", " / ");
            }
            productPrice = getProductPrice();
        }

        if (!productVariant.isEmpty()) {
            productListAdded.put(productName + ":" + productVariant, asList(quantity, productPrice));

        } else {
            productListAdded.put(productName, asList(quantity, productPrice));
        }

        productQuantity += Integer.parseInt(quantity);
        variantListAdded.add(productName + ":" + productVariant);
    }

    @Step
    public void inputOptionTextNew(String label, String value) {
        if (!label.isEmpty() & !value.isEmpty())
            productPage.InputOptionTextNew(label, value);
    }

    @Step
    public void verifyLabelOptionTextNew(String label) {
        if (!label.isEmpty())
            Java6Assertions.assertThat(productPage.isExistLabelOptionTextNew(label)).isTrue();
    }

    @Step
    public void verifyPhotoGuidewithContentTextOnSF(String photoName, String contentText) {
        productPage.verifyPhotoGuideWithContentTextOnSF(photoName, contentText);
    }

    @Step
    public void verifyPhotoGuideWithContentImageOnSF(String photoName) {
        productPage.verifyPhotoGuideWithContentImageOnSF(photoName);
    }

    @Step
    public void verifyDroplistInSF(String customName, String value, String selected) {
        if (selected.equals("true"))
            Java6Assertions.assertThat(productPage.getValueDroplistDefault(customName)).isEqualToIgnoringCase(value);
        productPage.verifyDropListInList(customName, value);
    }

    public void verifyNameClipart(String sNameClipart) {
        productPage.verifyNameClipart(sNameClipart);
    }

    @Step
    public void verifyListTitleInSizeChart(String value) {
        if (!value.isEmpty())
            productPage.verifyListValueSizeChart(value);
    }

    @Step
    public void verifyTitleSizeChart(String title) {
        if (!title.isEmpty())
            productPage.verifyTitleSizeChart(title);
    }

    @Step
    public void verifyDescriptionSizeChart(String description) {
        if (!description.isEmpty())
            productPage.verifyDescriptionSizeChart(description);
    }

    @Step
    public void verifySizeChartSelected(String style) {
        assertThat(productPage.getValueSelectedOnSizeChart()).isEqualToIgnoringCase(style);
    }

    @Step
    public void verifyIamgeProcessingManualDesign(Boolean bol) {
        productPage.verifyIamgeProcessingManualDesign(bol);
    }

    @Step
    public void clickToBtnOnPopup(String btn) {
        productPage.clickToBtnOnPopup(btn);
    }

    @Step
    public void verifyNumberCamapaignOnSF(String campaignName) {
        productPage.verifyNumberCampaignOnSF(campaignName);
    }

    @Step
    public void verifyCustomOptionSF(String coName, boolean isShow) {
        productPage.verifyCustomOptionSF(coName, isShow);
    }

    @Step
    public void verifyLabelOptionRadio(String sCustomName, boolean isShow) {
        productPage.verifyLabelOptionRadio(sCustomName, isShow);
    }

    @Step
    public void verifyLabelOptionDroplist(String sCustomName, boolean isShow) {
        productPage.verifyLabelOptionDroplist(sCustomName, isShow);
    }

    @Step
    public void verifyLabelOptionCheckbox(String sCustomName, boolean isShow) {
        productPage.verifyLabelOptionCheckbox(sCustomName, isShow);
    }
    @Step
    public void clickToStatus(String status) {
        productPage.clickToStatus(status);
    }
    @Step
    public void clickToRadio(String radio) {
        productPage.clickToRadio(radio);
    }
    @Step
    public void verifyListNameOrder(String _sOrder, boolean _isExist) {
        productPage.verifyListNameOrder(_sOrder,_isExist);
    }
    @Step
    public void clickEditorPersonalize(String personalize) {
        productPage.clickEditorPersonalize(personalize);
    }
    @Step
    public void verifyUser(String sUser) {
        productPage.verifyUser(sUser);
    }
    @Step
    public void verifyCategory(String sCategory) {
        productPage.verifyCategory(sCategory);
    }
    @Step
    public void verifyActivity(String sActivity) {
        productPage.verifyActivity(sActivity);
    }
    @Step
    public void verifyDetail(String sDetail) {
        productPage.verifyDetail(sDetail);
    }
    @Step
    public void clickIconDelete() {
        productPage.clickIconDelete();
    }

    @Step
    public void verifyVariantShow(String variant, String show) {
        productPage.verifyVariantShow(variant, show);
    }

    @Step
    public void clickClosePopupLivePreview() {
        productPage.clickClosePopupLivePreview();
    }
    //    @Step
//    public void clickToStatus(String status) {
//        productPage.clickToStatus(status);
//
//    }
//    @Step
//    public void clickToRadio(String radio) {
//        productPage.clickToRadio(radio);
//    }
//    @Step
//    public void verifyListNameOrder(String sOrder) {
//        productPage.verifyListNameOrder(sOrder);
//    }
//    @Step
//    public void clickToMenu(String sMenu) {
//        productPage.clickToMenu(sMenu);
//    }
//    @Step
//    public void clickIconDelete() {
//        productPage.clickIconDelete();
//    }
    @Step
    public BufferedImage verifyImageInListProductPage(String sImageAc, int imageNumber) throws IOException {
        productPage.getImageProductInListWithURL(sImageAc,imageNumber);
        BufferedImage actualImage = ImageIO.read(new File(LoadObject.getFilePath(File.separator + sImageAc + ".png")));
        return actualImage;
    }

    @Step
    public void clickNextPageOnListMockupSF() {productPage.clickNextPageOnListMockupSF();}

    @Step
    public int getTotalMockupOnSF (String handle, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/api/catalog/product.json?handle="+ handle + "&access_token=" + accessToken;
        JsonPath response = productDetailPages.getAPI(url);
        ArrayList<String> listImage = response.get("media_gallery.image");
        int count =  listImage.size();
        return count;
    }

    public void verifyMockupOnProductPage(String image, String per,int imageNumber) throws IOException {
        float percent = 1.00f;
        if (!per.isEmpty())
            percent = Float.parseFloat(per);
        productPage.verifyImageMockupOnProductPage(image, percent,imageNumber);
    }

    public void verifyPhoneNumber(String phoneNumber) {
        productPage.verifyPhoneNumber(phoneNumber);
    }

    @Step
    public void addProductsInPagesToCart(String productNames) {
        String[] pageList = productNames.split(",");

        for (String page : pageList){
            openPage(page);
            productPage.clickOnAddToCartBtn();
        }
    }

    @Step
    public void verifyProductsOnCart(String productNames) {
        String[] linkProduct = productNames.split(",");
        for (String product : linkProduct){
            productPage.verifyProductOnCart(product);
        }
    }

    @Step
    public void searchProductOnSF(String productName,boolean status) {
        if (!productName.isEmpty()) {
            searchProduct(productName);
            productPage.verifyProductNotExistOnSF(productName,status);
        }
    }

    public void clickButtonBuyNow() {
        productPage.waitForPageLoad();
        productPage.clickBuyNow();
        productPage.waitForEverythingComplete();
        productPage.waitUntilInvisibleLoading(8);
    }

    @Step
    public void selectStyleIncaseMultiProduct(String style, String verifyStyle) {
        if (productPage.isExistStyleLabel()) {
            productPage.switchToProducts(style, verifyStyle);
        }
        productPage.verifyStyle(verifyStyle);
    }

    public void verifyNotShowVariant() {
        productPage.verifyNotShowVariant();
    }

    @Step
    public void searchAndSelectProductFirst(String productName) {
        if (!productName.isEmpty()) {
            searchProduct(productName);
            productPage.selectProductFirst();
        }
    }

    //v2
    @Step
    public void verifyTextProduct(String content) {
        productPage.verifyTextProduct(content);
    }
    @Step
    public void verifyHeading(String heading, int indexBlock) {
        productPage.verifyHeading(heading, indexBlock);
    }
    @Step
    public void verifyContent(String content, int indexBlock) {
        productPage.verifyContent(content, indexBlock);
    }
    @Step
    public void verifyMagnifyProductImages(boolean magnifyProductImages) { productPage.verifyMagnifyProductImages(magnifyProductImages);
    }

    public void verifyShowStickyButtonDisplayOn(boolean displayOn) { productPage.verifyShowStickyButton(displayOn);
    }

    public void verifyDescriptionPosition(boolean descriptionPosition) { productPage.verifyDescriptionPosition(descriptionPosition);
    }
    public void verifyHeadingOnProductDescription(String descriptionPosition, String heading, int index) { productPage.verifyHeadingOnProductDescription(descriptionPosition,heading,index);
    }

    public void verifyCollapseThisTabByDefault(String descriptionPosition,boolean collapseThisTabByDefault, int index) { productPage.verifyCollapseThisTabByDefault(descriptionPosition,collapseThisTabByDefault,index);
    }

    public void verifyTextOnAdditionalTab(boolean collapseThisTabByDefault,String text,int index) {productPage.verifyTextOnAdditionalTab(collapseThisTabByDefault,text,index);
    }
}