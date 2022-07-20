package opencommerce.online_store.themes;

import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.List;

import static common.utilities.LoadObject.convertStatus;
import static opencommerce.products.dashboard.ProductDetailDef.urlProductOnSF;

public class ProductPageDef {
    @Steps
    ProductSteps productSteps;

    String theme = LoadObject.getProperty("theme");

    @And("^verify product page setting as \"([^\"]*)\"$")
    public void verifyProductPageSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean isenableProductGalleryPopup = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable product gallery popup"));
            boolean isShowBreadcrumbLinks = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show breadcrumb links"));
            boolean isShowVendor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            boolean isShowSKU = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show SKU"));
            boolean isShowPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean isShowCollections = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show collections"));
            boolean isShowTypes = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show types"));
            boolean isShowTags = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show tags"));
            boolean isShowSocialMediaShareIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show social media share icons"));
            boolean isShowStickyButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sticky button"));
            String optionsStyle = SessionData.getDataTbVal(dataTable, row, "Options style"); //Variant style
            boolean enableVariantGroupSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable variant group swatches"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantityBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box"));
            boolean showQuantityBoxAndATCbtn = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box and Add to cart button"));
            boolean showQuantityBoxInSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box in the same line"));
            String textFor0Sproduct = SessionData.getDataTbVal(dataTable, row, "Text for 0$ product");
            String tabPosition = SessionData.getDataTbVal(dataTable, row, "Tab position"); //Description position in inside theme
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showTrustBadgeImage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show trust badge image"));

            productSteps.searchAndSelectProduct(productName);
            productSteps.verifyProductPage(productName);
            productSteps.verifyShowBreadcrumbLinks(isShowBreadcrumbLinks);
            productSteps.verifyShowVendor(isShowVendor);
            productSteps.verifyShowSKU(theme, isShowSKU);
            productSteps.verifyShowCollections(isShowCollections);
            productSteps.verifyShowTypes(isShowTypes);
            productSteps.verifyShowTags(isShowTags);
            productSteps.verifyShowSocialMediaShareIcons(isShowSocialMediaShareIcons);
            productSteps.verifyShowStickyButton(isShowStickyButton);
            productSteps.verifyShowPriceSavings(isShowPriceSavings);
            productSteps.verifySwatches(optionsStyle, enableVariantGroupSwatches, enableColorSwatches);
            productSteps.verifyOptionsStyle(optionsStyle); //variant style
            productSteps.verifyShowQuantityBox(showQuantityBox, showQuantityBoxInSameLine, showQuantityBoxAndATCbtn);
            productSteps.verifyTabPosition(tabPosition);
            productSteps.verifyShowTrustBadgeImage(showTrustBadgeImage);
            switch (theme) {
                case "inside":
                    productSteps.verifySaleType(isShowPriceSavings, saleType);
                    break;
                default:
                    productSteps.verifyEnableProductGalleryPopup(isenableProductGalleryPopup);
            }
        }
    }

    @And("verify add to cart product name as {string}")
    public void verifyAddToCartProductNameAs(String nameProduct) {
        if (!nameProduct.isEmpty()) {
            productSteps.clickAddToCart();
            productSteps.verifyAddToCartFeatureProduct(nameProduct);
            productSteps.clearProductOnCart();
            productSteps.openHomePage();
            productSteps.clickProductName(nameProduct);
            productSteps.verifyOpenProductDetail(nameProduct);
        }
    }

    @And("verify buy now product name as {string}")
    public void verifyBuyNowProductNameAs(String productName) {
        if (!productName.isEmpty()) {
            productSteps.clickBtnBuyNow();
            productSteps.verifyBuyNowFeatureProduct(productName);
            productSteps.closeCheckOutPage();
            productSteps.clickViewProductDetails();
            productSteps.verifyOpenProductDetail(productName);
        }
    }

    @Then("input value custom option")
    public void inputValueCustonOption(List<List<String>> dataTable) {
        productSteps.clickToViewProduct();
        productSteps.switchToLatestTab();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");

            if (!value.isEmpty() && customOption.equals("Radio")) {
                productSteps.selectValueRadio(customOption, value);
            }

            if (!value.isEmpty() && customOption.contains("Drop")) {
                productSteps.selectValueDroplist(customOption, value);
            }

            if (!value.isEmpty() && customOption.contains("Image")) {
                productSteps.uploadImageCO(customOption, value);
            }

            if (!value.isEmpty() && customOption.contains("Text")) {
                productSteps.inputTextCO(customOption, value);
            }
        }
    }

    @Then("verify value after add to card")
    public void verifyValueAfterAddToCard(List<List<String>> dataTable) {
        productSteps.clickAddToCart();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");

            productSteps.verifyValue(value, row);
        }
    }

    @Then("verify filter list product on storefront")
    public void verifyFilterListProductOnStorefront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tag");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String handleCollection = SessionData.getDataTbVal(dataTable, row, "Handle collection");
            String totalProduct = SessionData.getDataTbVal(dataTable, row, "Total product");
            String imageVariant = SessionData.getDataTbVal(dataTable, row, "Image Variant");

            productSteps.searchProduct(productName);
            productSteps.selectProduct(productName);
            if (!tag.isEmpty()) {
                productSteps.clickTag(tag);
            }
            if (!vendor.isEmpty()) {
                productSteps.clickVendor(vendor);
            }
            if (!collection.isEmpty()) {
                productSteps.clickCollection(collection);
                productSteps.verifyHandle(handleCollection);
            }
            if (!totalProduct.isEmpty()) {
                productSteps.verifyListProduct(totalProduct);
            }
            if (!imageVariant.isEmpty()) {
                productSteps.verifyImageVariant(imageVariant);
            }
        }
    }

    @And("^open and verify products \"([^\"]*)\" (exist|not exist) on Storefront$")
    public void verifyProductsExistOnStorefront(String title, String isExist) {
        productSteps.openProductWithUrl(urlProductOnSF);
        switch (isExist) {
            case "exist":
                productSteps.verifyName(title);
                break;
            case "not exits":
                productSteps.isShowPageNotExist(true);
                break;
        }
    }

    @And("close live preview popup")
    public void closeLivePreviewPopup() {
        productSteps.clickClosePopupLivePreview();
    }

    @And("^search product \"([^\"]*)\" (.*) on Storefront$")
    public void searchProductExitsOnSF(String productName,String isExist){
        switch (isExist) {
            case "exist":
                productSteps.searchProductOnSF(productName,true);
                break;
            case "not exist":
                productSteps.searchProductOnSF(productName,false);
                break;
        }
    }

    @And("^verify \"([^\"]*)\" block as \"([^\"]*)\"$")
    public void verifyProductSetting(String nameBlock, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {

            switch (nameBlock) {
                case "Product Price":
                    String selectSaleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
                    boolean isShowPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Price savings"));

                    productSteps.verifySaleType(isShowPriceSavings, selectSaleType);
                    break;

                case "Variant Selector":
                    String variantStyle = SessionData.getDataTbVal(dataTable, row, "Variant style");
                    boolean enableVariantGroupSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable variant group swatches"));
                    boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));

                    productSteps.verifyOptionsStyle(variantStyle);
                    productSteps.verifySwatches(variantStyle, enableVariantGroupSwatches, enableColorSwatches);
                    break;

                case "Buy Buttons":
                    boolean isShowQuantityBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity selector"));
                    boolean isShowQuantityBoxAndATCbtn = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Add to card button"));
                    boolean isShowQuantityAndCartSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity & Add to card is same line"));

                    productSteps.verifyShowQuantityBox(isShowQuantityBox, isShowQuantityAndCartSameLine, isShowQuantityBoxAndATCbtn);
                    break;
            }
        }
    }

    @And("^verify Product setting as \"([^\"]*)\"$")
    public void verifyProductSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isStickyButtonDesktop = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Sticky button desktop"));
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description Position");
            boolean isShowBreadcrumbLinks = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Breadcrumb"));

            productSteps.verifyShowStickyButton(isStickyButtonDesktop);
            productSteps.verifyShowBreadcrumbLinks(isShowBreadcrumbLinks);
            productSteps.verifyTabPosition(descriptionPosition);
        }
    }

    @When("verify Product page {string}")
    public void verifyProductPage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean magnifyProductImages = convertStatus(SessionData.getDataTbVal(dataTable, row, "Magnify product images by hovering on desktop"));
            boolean enableProductGalleryPopup = convertStatus(SessionData.getDataTbVal(dataTable, row, "Enable product gallery popup"));
            boolean showBreadcrumbLink = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show breadcrumb link"));
            boolean showVendor = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            boolean showSku = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show SKU"));
            boolean showPriceSavings = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean showCollections = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show collections"));
            boolean showTypes = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show types"));
            boolean showTags = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show tags"));
            boolean showSocialMediaShareIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media share icons"));
            boolean showStickyButton = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show Sticky button"));
            boolean displayOn = convertStatus(SessionData.getDataTbVal(dataTable, row, "Display on"));
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            productSteps.verifyMagnifyProductImages(magnifyProductImages);
            productSteps.verifyShowBreadcrumbLinks(showBreadcrumbLink);
            productSteps.verifyShowVendor(showVendor);
            productSteps.verifyShowSKU(theme, showSku);
            productSteps.verifyShowCollections(showCollections);
            productSteps.verifyShowTypes(showTypes);
            productSteps.verifyShowTags(showTags);
            productSteps.verifyShowSocialMediaShareIcons(showSocialMediaShareIcons);
            productSteps.verifyShowStickyButton(showStickyButton);
            if(showStickyButton){
                productSteps.verifyShowStickyButton(displayOn);
            }
            productSteps.verifyShowPriceSavings(showPriceSavings);
            productSteps.verifyEnableProductGalleryPopup(enableProductGalleryPopup);
            productSteps.verifyDescriptionPosition(Boolean.parseBoolean(descriptionPosition));

        }
    }

    @And("verify block {string} in Product page {string}")
    public void verifyBlockInProductPage(String nameBlock, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            boolean collapseThisTabByDefault = convertStatus(SessionData.getDataTbVal(dataTable, row, "Collapse this tab by default"));
            switch (nameBlock) {
                case "Product Description":
                    productSteps.verifyHeadingOnProductDescription(descriptionPosition,heading,1);
                    if(!heading.isEmpty()) {
                        productSteps.verifyCollapseThisTabByDefault(descriptionPosition, collapseThisTabByDefault, 1);
                    }
                    break;
                case "Additional Tab":
                    String text = SessionData.getDataTbVal(dataTable, row, "Text");
                    productSteps.verifyHeadingOnProductDescription(descriptionPosition,heading,2);
                    productSteps.verifyTextOnAdditionalTab(collapseThisTabByDefault,text,2);
                    if(!heading.isEmpty()) {
                        productSteps.verifyCollapseThisTabByDefault(descriptionPosition, collapseThisTabByDefault, 2);
                    }
                    break;
            }
        }
    }
}
