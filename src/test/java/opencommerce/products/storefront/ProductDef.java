package opencommerce.products.storefront;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.EditorCampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.products.dashboard.CollectionDetailDef;
import opencommerce.products.dashboard.ProductDetailDef;
import com.opencommerce.shopbase.OrderVariable;

import static opencommerce.products.dashboard.ProductDetailDef.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.nameProductImportToStore;

public class ProductDef {
    @Steps
    ProductSteps productSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    EditorCampaignSteps EditorCampaignSteps;
    @Steps
    CommonSteps commonSteps;

    @Steps
    ProductDetailDef productDetailDef;
    @Steps
    ProductDetailSteps productDetailSteps;

    String accessToken = "";
    private String activityLog = "";

    @And("^add products \"([^\"]*)\" and switch to cart$")
    public void addProductsAndSwitchToCart(String productNames) {
        if (!productNames.isEmpty()) {
            productSteps.addMultipleProductsToCart(productNames);
            productSteps.goToCart();
        }
    }

    @And("^add products \"([^\"]*)\" to cart$")
    public void addProductToCart(String productNames) {
        if (!productNames.isEmpty()) {
            productSteps.addMultipleProductsToCart(productNames);
        }
    }

    @And("^add products to cart then checkout as \"([^\"]*)\"$")
    public void orderProducts(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            productSteps.addMultipleProductsToCart(product);
        }
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
    }

    @And("^add products \"([^\"]*)\" to cart then checkout$")
    public void orderProducts(String productNames) {
        if (productNames.matches("@(.*)@")) {
            productNames = nameProductSbase;
        } else if (productNames.equals("imported by ADC")) {
            productNames = nameProductImportToStore;
        }
        System.out.println("productNames = " + productNames);
        productSteps.addMultipleProductsToCart(productNames);
        productSteps.goToCart();
        HashMap<String, List<String>> productInCart = new HashMap<String, List<String>>();
        try {
            productInCart = cartSteps.getListProductFromCart();
        } catch (Exception ex) {
            productSteps.refreshPage();
            productInCart = cartSteps.getListProductFromCart();
        }
        assertThat(productInCart.size()).isEqualTo(productListAdded.size());
        for (String i : productListAdded.keySet()) {
            for (String j : productInCart.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    Collections.sort(productListAdded.get(i));
                    Collections.sort(productInCart.get(j));
                    assertThat(productListAdded.get(i).toString().trim()).isEqualToIgnoringCase(productInCart.get(j).toString().trim());
                }
            }
        }
        cartSteps.clickButtonCheckoutOnCart();
        checkoutToken = thankyouSteps.getCheckoutToken();
        thankyouSteps.logInfor(checkoutToken);
    }

    @Then("^add to cart product \"([^\"]*)\" with custom option \"([^\"]*)\"$")
    public void addToCartProductWithCustomOption(String productName, String customOption) {
        productSteps.searchAndSelectProduct(productName);
        productSteps.inputCustomOption(customOption);
        productSteps.clickAddToCart();
    }

    @When("^click to button checkout")
    public void clickToButtonCheckoutOnTheme() {
        productSteps.goToCart();
        productSteps.clickCheckOut();
    }

    @When("^close mini cart$")
    public void closeCartDrawer() {
        productSteps.closeCart();
    }

    @And("^open mini cart or cart drawer$")
    public void openMiniCartOrCartDrawer() {
        productSteps.openMiniCartOrCartDrawer();
    }

    @Then("^Verify this product \"([^\"]*)\" on storefront$")
    public void verifyDisplayableOfThisProductOnStorefront(String productName, List<List<String>> dataTable) {
        productSteps.clickToViewProduct();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String showCustom = SessionData.getDataTbVal(dataTable, row, "Show Custom");
            boolean isShow = true;
            productSteps.switchToLatestTab();

            if (productName.matches("@Copy of (.*)@")) {
                productSteps.verifyName("Copy of " + nameProductSbase);
            } else if (productName.matches("@(.*)@")) {
                productSteps.verifyName(nameProductSbase);
            } else {
                productSteps.verifyName(productName);
            }

            if (!"".equals(description)) {
                productSteps.verifyDescription(description);
            }
            if (!"".equals(price)) {
                productSteps.verifyPrice(price);
            }

            if (!"".equals(compareAtPrice)) {
                productSteps.verifyCompareAtPrice(compareAtPrice);
            }

            if (!"".equals(price) && !"".equals(compareAtPrice)) {
                productSteps.verifyPriceSaving(price, compareAtPrice);
            }

            if (!"".equals(vendor)) {
                productSteps.verifyVendor(vendor);
            }

            if (!"".equals(sku)) {
                productSteps.verifySKU(sku);
            }

            if (!value.isEmpty() && customOption.equals("Radio")) {
                productSteps.selectValueRadio(customOption, value);
            }

            if (!value.isEmpty() && customOption.contains("Drop")) {
                productSteps.selectValueDroplist(customOption, value);
            }

            if (!"".equals(customOption)) {
                isShow = Boolean.parseBoolean(showCustom);
            }

            if (!"".equals(productType)) {
                productSteps.verifyProductType(productType);
            }

            if (!"".equals(tag)) {
                productSteps.verifyTag(tag);
            }

            if (productName.matches("@(.*)@") && collection.matches("@(.*)@")) {
                productSteps.verifyCollection(CollectionDetailDef.collectionNameManual);
            } else {
                productSteps.verifyCollection(collection);
            }
        }
    }

    @Then("^Verify all product variants on storefront$")
    public void verifyAllProductVariantsOnStorefront(List<List<String>> dataTable) {
        productSteps.clickToViewProduct();
        productSteps.switchToWindowWithIndex(1);
        productSteps.refreshPage();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productTitle = SessionData.getDataTbVal(dataTable, row, "Product name");
            String variantLabel = SessionData.getDataTbVal(dataTable, row, "Variant Label");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String show = SessionData.getDataTbVal(dataTable, row, "Show");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String media = SessionData.getDataTbVal(dataTable, row, "Total media");

            if (show.equals("false")) {
                productSteps.verifyVariantShow(variant, show);
            } else {
                productSteps.selectOption(variant);
            }

            if ("".equals(productName)) {
                productName = nameProductSbase;
            }

            if (productName.matches("@Copy of (.*)@")) {
                productSteps.verifyName("Copy of " + nameProductSbase);
            } else if (productName.matches("@(.*)@")) {
                productSteps.verifyName(nameProductSbase);
            } else if ("".equals(productName)) {
                productSteps.verifyName(productTitle);
            } else {
                productSteps.verifyName(productName);
            }

            if (!variantLabel.isEmpty()) {
                productSteps.verifyVariantLabel(variantLabel);
            }

            if (!"".equals(description)) {
                productSteps.verifyDescription(description);
            }

            if (!"".equals(media)) {
                productSteps.verifyListMedia(listImage, totalMedia);
            }

            if (!"".equals(price)) {
                productSteps.verifyPrice(price);
            }

            if (!"".equals(compareAtPrice)) {
                productSteps.verifyCompareAtPrice(compareAtPrice);
            }

            if (!"".equals(price) && !"".equals(compareAtPrice)) {
                productSteps.verifyPriceSaving(price, compareAtPrice);
            }

            if (!"".equals(vendor)) {
                productSteps.verifyVendor(vendor);
            }

            if (!"".equals(sku)) {
                productSteps.verifySKU(sku);
            }

            if (!"".equals(productType)) {
                productSteps.verifyProductType(productType);
            }

            if (!"".equals(tag)) {
                productSteps.verifyTag(tag);
            }

            if (!"".equals(collection)) {
                if (productName.matches("@(.*)@")) {
                    productSteps.verifyCollection(CollectionDetailDef.collectionNameManual);
                } else {
                    productSteps.verifyCollection(collection);
                }
            }
        }
    }

    @Given("^search and select the product \"([^\"]*)\"$")
    public void search_and_select_the_product(String productName) {
        if (productName.matches("@(.*)@")) {
            productName = OrderVariable.productName;
        } else if (!customArtName.isEmpty()) {
            productName = customArtName;
        }
        productSteps.searchAndSelectProduct(productName);
    }

    @Then("^Verify variant has been deleted on storefront$")
    public void verifyVariantHasBeenDeletedOnStorefront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            productSteps.verifyVariantDeleted(variant);
        }
    }

    @When("^Select product \"([^\"]*)\"$")
    public void selectProduct(String productName) {
        productSteps.selectProduct(productName);
    }


    @And("^Verify Search engine listing preview on Storefront$")
    public void verifySearchEngineListingPreviewOnStorefront() {
        productSteps.clickToViewProduct();
        productSteps.switchToWindowWithIndex(1);
        productSteps.verifyNewURL(urlAndHandleInitial);
        productSteps.verifyNewPageTitle(pageTitleInitial);
    }

    @And("Verify one page checkout")
    public void verifyOnePageCheckout(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            productSteps.verifyTitleDisplay(title);
        }
    }

    @And("click icon cart in header")
    public void clickIconCartInHeader() {
        productSteps.clickIconCart();
    }

    @Then("remove mapping fulfillment services {string}")
    public void remove_mapping_fulfillment_services(String app) {
        productSteps.removeMappingInFulfillmentServices(app);
    }

    @And("verify image product page {string}")
    public void verifyImageProductPage(String dataKey, List<List<String>> dataTable) throws IOException {
        int i = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sImageEx = SessionData.getDataTbVal(dataTable, row, "Image expected");
            String _sImageAc = SessionData.getDataTbVal(dataTable, row, "Image actual");
            if (!_sImageAc.isEmpty() & !_sImageEx.isEmpty()) {
                if (i == 1) {
                    BufferedImage actualImage = productSteps.verifyImageProductPage(_sImageAc);

                    commonSteps.logInfor("read image");
                    commonSteps.compareImage(_sImageEx, actualImage, 99);
                    i++;
                } else {
                    BufferedImage actualImageList = productSteps.verifyImageInListProductPage(_sImageAc, i);
                    commonSteps.logInfor("read image");
                    commonSteps.compareImage(_sImageEx, actualImageList, 99);
                    if (i % 5 == 0)
                        productSteps.clickNextPageOnListMockupSF();
                    i++;
                }
            }
        }
    }

    @And("add products \"([^\"]*)\" to cart then navigate to checkout page")
    public void addProductsToCartThenNavigateToCheckoutPage(String productName) {
        productSteps.addMultipleProductsToCart(productName);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        checkoutToken = thankyouSteps.getCheckoutToken();
        thankyouSteps.logInfor("Checkout token: " + checkoutToken);
    }

    @Then("Verify media product on store front")
    public void verifyMediaProductOnStoreFront() {
        productDetailDef.viewProductOnStoreFront();
        productSteps.switchToLatestTab();
        productSteps.verifyImageLoaded();
        productSteps.verifyListMedia(listImage, totalMedia);
    }

    @Given("verify show custom option on store front as {string}")
    public void verify_show_custom_option_on_store_front(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sTypeOption = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sCustomName = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sValueInput = SessionData.getDataTbVal(dataTable, row, "Value Input");
            String _isCropImage = SessionData.getDataTbVal(dataTable, row, "Crop Image");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");
            String showCustom = SessionData.getDataTbVal(dataTable, row, "Show CO");
            boolean isShow = true;
            if (showCustom.equals("false"))
                isShow = false;
            boolean isCrop = true;
            if (_isCropImage.equalsIgnoreCase("false"))
                isCrop = false;
            if (!_sStyle.isEmpty()) {
                productSteps.selectStyle(_sStyle);
            }
            switch (_sTypeOption) {
                case "Text":
                    productSteps.verifyLabelOptionText(_sCustomName, isShow);
                    if (isShow)
                        productSteps.inputOptionText(_sCustomName, _sValueInput);
                    break;
                case "Image":
                    productSteps.verifyLabelOptionImage(_sCustomName, isShow);
                    if (isShow)
                        productSteps.inputOptionImage(_sCustomName, _sValueInput, isCrop);
                    break;
                case "Picture choice":
                    productSteps.verifyLabelOptionPictureChoice(_sCustomName, isShow);
                    if (isShow)
                        productSteps.chooseImagePictureChoice(_sCustomName, _sValueInput);
                    break;
                case "Radio":
                    productSteps.verifyLabelOptionRadio(_sCustomName, isShow);
                    productSteps.selectValueRadio(_sCustomName, _sValueInput);
                    break;
                case "Droplist":
                    productSteps.verifyLabelOptionDroplist(_sCustomName, isShow);
                    productSteps.selectValueDroplist(_sCustomName, _sValueInput);
                    break;
                case "Checkbox":
                    productSteps.verifyLabelOptionCheckbox(_sCustomName, isShow);
                    break;

                case "Picture Group":
                    productSteps.selectImagePictureGroup(_sCustomName, _sValueInput);
                    break;
            }
        }
    }


    @Then("verify crop image on store front")
    public void verifyCropImageOnStoreFront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _optionText = SessionData.getDataTbVal(dataTable, row, "Option Text");
            String _optionImage = SessionData.getDataTbVal(dataTable, row, "Option Image");
            String _optionPicture = SessionData.getDataTbVal(dataTable, row, "Option Picture");
            String _isCropImage = SessionData.getDataTbVal(dataTable, row, "Crop Image");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");
            boolean isCrop = true;
            if (_isCropImage.equalsIgnoreCase("false"))
                isCrop = false;
            if (!_sStyle.isEmpty()) {
                productSteps.selectStyle(_sStyle);
            }
            if (!_optionText.isEmpty()) {
                productSteps.verifyAndInputOptionText(_optionText);
            }
            if (!_optionImage.isEmpty()) {
                productSteps.verifyAndInputOptionImageAndCropImage(_optionImage, isCrop);
            }
            if (!_optionPicture.isEmpty()) {
                productSteps.verifyAndInputOptionPictureChoice(_optionPicture);
            }
        }
    }

    @Then("verify show manual design on store front")
    public void verify_show_manual_design_on_store_front(List<List<String>> dataTable) {
        EditorCampaignSteps.verifyProductWillBeCustomized();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sTypeOption = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sCustomName = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sValueInput = SessionData.getDataTbVal(dataTable, row, "Value Input");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");
            boolean _sCheck = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Checked"));
            String _sSelected = SessionData.getDataTbVal(dataTable, row, "Selected");
            String _sShowPhotoGuide = SessionData.getDataTbVal(dataTable, row, "Is show Photo guide");
            Boolean isShowPhotoGuide = false;
            if (_sShowPhotoGuide.equalsIgnoreCase("true"))
                isShowPhotoGuide = true;
            if (!_sStyle.isEmpty()) {
                productSteps.selectStyle(_sStyle);
            }
            switch (_sTypeOption) {
                case "Text":
                    productSteps.verifyLabelOptionText(_sCustomName, true);
                    productSteps.inputOptionText(_sCustomName, _sValueInput);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Image":
                    productSteps.verifyLabelOptionImage(_sCustomName, true);
                    productSteps.inputOptionImage(_sCustomName, _sValueInput, false);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, isShowPhotoGuide);
                    break;
                case "Picture choice":
                    productSteps.verifyLabelOptionPictureChoice(_sCustomName, true);
                    productSteps.chooseImagePictureChoice(_sCustomName, _sValueInput);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Checkbox":
                    EditorCampaignSteps.verifyCheckCustomOption(_sCustomName, _sCheck);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Radio":
                    EditorCampaignSteps.verifyRadioValueDefault(_sValueInput, _sCheck);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Droplist":
                    productSteps.verifyDroplistInSF(_sCustomName, _sValueInput, _sSelected);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
            }
        }
    }

    @Then("verify show new manual design on store front")
    public void verify_show_new_manual_design_on_store_front(List<List<String>> dataTable) {
        EditorCampaignSteps.verifyProductWillBeCustomized();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sTypeOption = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sCustomName = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sValueInput = SessionData.getDataTbVal(dataTable, row, "Value Input");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");
            boolean _sCheck = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Checked"));
            String _isSelected = SessionData.getDataTbVal(dataTable, row, "Selected");
            String _sShowPhotoGuide = SessionData.getDataTbVal(dataTable, row, "Is show Photo guide");
            boolean isShowPhotoGuide = false;
            if (_sShowPhotoGuide.equalsIgnoreCase("true"))
                isShowPhotoGuide = true;
            if (!_sStyle.isEmpty()) {
                productSteps.selectStyle(_sStyle);
            }
            switch (_sTypeOption) {
                case "Text":
                    productSteps.verifyLabelOptionTextNew(_sCustomName);
                    productSteps.inputOptionTextNew(_sCustomName, _sValueInput);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Image":
                    productSteps.verifyLabelOptionImage(_sCustomName, true);
                    productSteps.inputOptionImage(_sCustomName, _sValueInput, false);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, isShowPhotoGuide);
                    break;
                case "Picture choice":
                    productSteps.verifyLabelOptionPictureChoice(_sCustomName, true);
                    productSteps.chooseImagePictureChoice(_sCustomName, _sValueInput);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Checkbox":
                    EditorCampaignSteps.verifyCheckCustomOption(_sCustomName, _sCheck);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Radio":
                    EditorCampaignSteps.verifyRadioValueDefault(_sValueInput, _sCheck);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
                case "Droplist":
                    productSteps.verifyDroplistInSF(_sCustomName, _sValueInput, _isSelected);
                    productSteps.verifyPhotoGuideOnSF(_sCustomName, false);
                    break;
            }
        }
    }

    @And("add products {string} to cart then checkout Plusbase")
    public void addProductsToCartThenCheckoutPlusbase(String productNames) {
        if (productNames.matches("@(.*)@")) {
            productNames = nameProductSbase;
        } else if (productNames.equals("imported by ADC")) {
            productNames = nameProductImportToStore;
        }
        System.out.println("productNames = " + productNames);
        productSteps.addMultipleProductsToCart(productNames);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        checkoutToken = thankyouSteps.getCheckoutToken();
        thankyouSteps.logInfor(checkoutToken);
    }

    @Then("verify show PhotoGuide on store front after execute as {string}")
    public void verifyShowPhotoOnSFAfExecute(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sManual = SessionData.getDataTbVal(dataTable, row, "Is manual design");
            String _sCustomName = SessionData.getDataTbVal(dataTable, row, "Custom Name");
            String _sContentType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sContent = SessionData.getDataTbVal(dataTable, row, "Content");
            boolean isManual = true;
            if (_sManual.equalsIgnoreCase("False"))
                isManual = false;
            productSteps.verifyIamgeProcessingManualDesign(isManual);
            EditorCampaignSteps.clickPhotoGuideOnSF(_sCustomName);
            if (_sContentType.equals("Text")) {
                productSteps.verifyPhotoGuidewithContentTextOnSF(titlePhotoGuide, _sContent);
            }
            if (_sContentType.equals("Image")) {
                productSteps.verifyPhotoGuideWithContentImageOnSF(titlePhotoGuide);
            }
        }
    }

    @And("add product with custom option to cart then checkout as {string}")
    public void addProductWithCustomOptionToCartThenCheckout(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sCustomOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String _isCropImage = SessionData.getDataTbVal(dataTable, row, "Is crop image");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            boolean isCrop = false;
            if (_isCropImage.equalsIgnoreCase("true"))
                isCrop = true;
            if (!_sProduct.isEmpty())
                productSteps.selectProductByVariant(_sProduct);
            if (!style.isEmpty()) {
                productSteps.selectStyleSFNext(style);
            }
            if (!_sCustomOption.isEmpty()) {
                String[] listCOs = _sCustomOption.split(";");
                for (String COs : listCOs) {
                    String[] listCO = COs.split(",");
                    switch (listCO[0]) {
                        case "Text":
                            productSteps.inputOptionText(listCO[1], listCO[2]);
                            break;
                        case "Image":
                            productSteps.inputOptionImage(listCO[1], listCO[2], isCrop);
                            break;
                        case "Radio":
                            productSteps.selectValueRadio(listCO[1], listCO[2]);
                            break;
                        case "Droplist":
                            productSteps.selectValueDroplist(listCO[1], listCO[2]);
                            break;
                        case "Picture choice":
                            productSteps.chooseImagePictureChoice(listCO[1], listCO[2]);
                            break;
                        case "Picture Group":
                            productSteps.selectImagePictureGroup(listCO[1], listCO[2]);
                            break;
                    }
                }
            }
            if (!size.isEmpty())
                productSteps.selectVariant(size);
            productSteps.clickAddToCart();
        }
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        checkoutToken = thankyouSteps.getCheckoutToken();
    }


    @Then("click to {string} and click radio {string}")
    public void clickToAndClickRadio(String status, String radio) {
        productSteps.clickToStatus(status);
        productSteps.clickToRadio(radio);
    }

    @And("verify orders in list order")
    public void verifyOrdersInListOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sOrder = SessionData.getDataTbVal(dataTable, row, "Order");
            if (!_sOrder.isEmpty()) {
                if (_sOrder.equalsIgnoreCase("order number")) {
                    _sOrder = orderNumber;
                }
                productSteps.verifyListNameOrder(_sOrder, true);
            } else {
                _sOrder = orderNumber;
                productSteps.verifyListNameOrder(_sOrder, false);
            }
        }
    }

    @And("click to radio {string}")
    public void clickToRadio(String radio) {
        productSteps.clickToRadio(radio);
    }

    @And("click to Delete in Update Preview image or Update Print file image screen")
    public void clickToDeleteInUpdatePreviewImageOrUpdatePrintFileImageScreen() {
        productSteps.clickIconDelete();
    }

    @And("click to button {string} on popup")
    public void clickToButtonOnPopup(String btn) {
        productSteps.clickToBtnOnPopup(btn);
    }

    @Then("verify artwork in Artwork library as {string}")
    public void verifyArtworkInArtworkLibraryAs(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sImageEx = SessionData.getDataTbVal(dataTable, row, "Image expected");
            String _sImageAc = SessionData.getDataTbVal(dataTable, row, "Image actual");
            if (!_sImageAc.isEmpty() & !_sImageEx.isEmpty()) {
                BufferedImage actualImage = productSteps.verifyImageArtwork(_sImageAc);

                commonSteps.logInfor("read 2 image");
                commonSteps.compareImage(_sImageEx, actualImage, 99);
            }
        }
    }

    @And("click to Edit {string} in product detail")
    public void clickToEditInProductDetail(String personalize) {
        productSteps.clickEditorPersonalize(personalize);
    }

    @Given("^Clear local storage$")
    public void clearLocalStorage() {
        productSteps.clearLocalStorage();
    }

    @And("verify total mockup on SF as {string}")
    public void verifyTotalMockupOnSF(String dataKey, List<List<String>> dataTable) {
        if (accessToken.isEmpty())
            accessToken = productDetailSteps.getAccessToken();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign Name");
            int _valueTotalMockup = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Total Mockup"));
            String handle = productDetailSteps.getHandleCampaignByAPI(campaignName, accessToken);
            int totalMockup = productSteps.getTotalMockupOnSF(handle, accessToken);
            assertThat(totalMockup).isEqualTo(_valueTotalMockup);
        }
    }

    @And("verify activity log")
    public void verifyActivityLog(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sCategory = SessionData.getDataTbVal(dataTable, row, "CATEGORY");
            String _sActivity = SessionData.getDataTbVal(dataTable, row, "ACTIVITY");
            String _sDetail = SessionData.getDataTbVal(dataTable, row, "DETAILS");
            String staffEmail = LoadObject.getProperty("user.name");
            if (!staffEmail.isEmpty()) {
                productSteps.verifyUser(staffEmail);
            }
            if (!_sCategory.isEmpty()) {
                productSteps.verifyCategory(_sCategory);
            }
            if (!_sActivity.isEmpty()) {
                productSteps.verifyActivity(_sActivity);
            }
            if (!_sDetail.isEmpty()) {
                productSteps.verifyDetail(_sDetail);
            }
        }
    }

    @And("verify multi image product page {string}")
    public void verifyMultiImageProductPage(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sImageEx = SessionData.getDataTbVal(dataTable, row, "Image expected");
            int _sTotalImage = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Total Image"));
            String _sPercent = SessionData.getDataTbVal(dataTable, row, "Percent Image");
            productSteps.verifyMockupOnProductPage(_sImageEx, _sPercent, row);
            if (row % 5 == 0 && _sTotalImage > 6)
                productSteps.clickNextPageOnListMockupSF();
        }
    }

    @When("add products in pages {string} to cart then click checkout button")
    public void addProductsInPagesToCartThenCheckout(String productNames) {
        productSteps.addProductsInPagesToCart(productNames);
        productSteps.openPage("/cart");
        productSteps.verifyProductsOnCart(productNames);
        cartSteps.clickButtonCheckoutOnCart();
    }

    @When("add products in pages {string} and switch to Cart")
    public void addProductsInPagesToCart(String productNames) {
        productSteps.addProductsInPagesToCart(productNames);
        productSteps.openPage("/cart");
        productSteps.verifyProductsOnCart(productNames);
    }

    @And("add product with custom option to cart then checkout as {string} other")
    public void addProductWithCustomOptionToCartThenCheckoutOther(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sCustomOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String _isCropImage = SessionData.getDataTbVal(dataTable, row, "Is crop image");
            boolean isCrop = false;
            if (_isCropImage.equalsIgnoreCase("true"))
                isCrop = true;
            if (!_sProduct.isEmpty())
                productSteps.selectProductByVariant(_sProduct);
            if (!style.isEmpty()) {
                productSteps.selectStyleSFNext(style);
            }
            if (!_sCustomOption.isEmpty()) {
                String[] listCOs = _sCustomOption.split(";");
                for (String COs : listCOs) {
                    String[] listCO = COs.split(",");
                    switch (listCO[0]) {
                        case "Text":
                            productSteps.inputOptionTextNew(listCO[1], listCO[2]);
                            break;
                        case "Image":
                            productSteps.inputOptionImage(listCO[1], listCO[2], isCrop);
                            break;
                        case "Picture choice":
                            productSteps.chooseImagePictureChoice(listCO[1], listCO[2]);
                            break;
                    }
                }
            }
            productSteps.clickAddToCart();
        }
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        checkoutToken = thankyouSteps.getCheckoutToken();
    }

    @Given("verify product on storefont")
    public void verify_product_on_storefont(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            productSteps.searchAndSelectProduct(product);
            productSteps.verifyNotShowVariant();
        }
    }

    @Given("^search and select the product first \"([^\"]*)\"$")
    public void search_and_select_the_product_first(String productName) {
        if (productName.matches("@(.*)@")) {
            productName = OrderVariable.productName;
        } else if (!customArtName.isEmpty()) {
            productName = customArtName;
        }
        productSteps.searchAndSelectProductFirst(productName);
    }

    @Then("verify show Image processing manual design on store front after execute as {string}")
    public void verifyShowPhotoOnSFAfExecute(String isManual) {
        boolean _sManual = true;
        if (isManual.equalsIgnoreCase("False"))
            _sManual = false;
        productSteps.verifyIamgeProcessingManualDesign(_sManual);
    }
}
