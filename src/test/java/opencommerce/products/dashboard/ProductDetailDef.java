package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.List;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductDetailDef {
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;

    public static String nameProductSbase = "";
    public static String listImage = "";
    public static int totalMedia;
    public static String productTitle1 = "";
    public static String productTitle2 = "";
    public static String urlAndHandleInitial = "";
    public static String pageTitleInitial = "";
    public static int numberOfImageSource;
    public static int numberOfImageDes;
    public static int numberOfArtworkSource;
    public static int numberOfArtworkDes;
    private String variantOptionSet = "";
    public String shop = "";
    public static String urlProductOnSF = "";
    public static String imageVariant = "";
    /// API
    String accessToken = "";

    @Then("^Verify max length of product title is \"([^\"]*)\"$")
    public void verifyMaxLengthOfProductTitleIs(String length) {
        productListSteps.clickAddProduct();
        productDetailSteps.verifyMaxLengthOfProductTitle(length);
    }

    @Given("^Add a new product with data$")
    public void addANewProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            productListSteps.clickAddProduct();
            productListSteps.closePopup();
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image url");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String inventoryPolicy = SessionData.getDataTbVal(dataTable, row, "Inventory policy");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String weightUnit = SessionData.getDataTbVal(dataTable, row, "Weight unit");
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String metaDescription = SessionData.getDataTbVal(dataTable, row, "Meta description");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Error message");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String imageAdded = SessionData.getDataTbVal(dataTable, row, "Image added");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String productAvailability = SessionData.getDataTbVal(dataTable, row, "Product availability");
            String allowOverselling = SessionData.getDataTbVal(dataTable, row, "Allow overselling");

            if (!title.isEmpty()) {
                if (title.matches("@(.*)@")) {
                    long currentTime = System.currentTimeMillis();
                    nameProductSbase = title.replaceAll("@", "") + "_" + currentTime;
                    productDetailSteps.enterTitle(nameProductSbase);
                } else {
                    productDetailSteps.enterTitle(title);
                    nameProductSbase = title;
                }
            }

            if (!"".equals(productAvailability)) {
                productDetailSteps.clickProductAvailability(productAvailability);
            }

            if (!"".equals(description)) {
                productDetailSteps.enterDescription(description);
            }

            if (!"".equals(image)) {
                String[] listImage = image.split(",");
                for (String img : listImage)
                    productDetailSteps.addImage(img);
            }

            if (!"".equals(imageUrl)) {
                productDetailSteps.enterImageURL(imageUrl);
            }

            if (!"".equals(productType)) {
                productDetailSteps.enterProductType(productType);
            }

            if (!"".equals(vendor)) {
                productDetailSteps.enterVendor(vendor);
            }

            if (!"".equals(tags)) {
                productDetailSteps.inputTag(tags);
            }

            if (!"".equals(price)) {
                productDetailSteps.enterPrice(price);
            }

            if (!"".equals(compareAtPrice)) {
                productDetailSteps.enterCompareAtPrice(compareAtPrice);
            }

            if (!"".equals(costPerItem)) {
                productDetailSteps.enterCostPerItem(costPerItem);
            }

            if (!"".equals(SKU)) {
                productDetailSteps.enterSKU(SKU);
            }

            if (!"".equals(barcode)) {
                productDetailSteps.enterBarcode(barcode);
            }

            if (!"".equals(inventoryPolicy)) {
                productDetailSteps.inputInventoryPolicy(inventoryPolicy);
                productDetailSteps.enterQuantity(quantity);
                if (!allowOverselling.isEmpty()) {
                    productDetailSteps.selectAllow(allowOverselling);
                }
            }

            if (!"".equals(weight)) {
                productDetailSteps.enterWeight(weight);
            }

            if (!"".equals(weightUnit)) {
                productDetailSteps.enterWeightUnit(weightUnit);
            }

            if (!"".equals(imageAdded)) {
                productDetailSteps.verifyAddImage(imageAdded);
            }

            if (!"".equals(variant)) {
                productDetailSteps.clickAddVariant();
                String[] listVariant = variant.split("--");
                for (int i = 0; i < listVariant.length; i++) {
                    String[] option = listVariant[i].split(":");
                    productDetailSteps.enterNewOptionSet(option[0], i + 1);
                    productDetailSteps.enterNewOptionValue(option[1], i + 1);
                    variantOptionSet += option[0];
                    if (i + 1 < listVariant.length) {
                        variantOptionSet += "; ";
                    }
                    if (listVariant.length > 1 && i < listVariant.length - 1) {
                        productDetailSteps.clickAddAnotherOption();
                    }
                }
            }

            if (!"".equals(pageTitle) && !"".equals(metaDescription)) {
                productDetailSteps.clickAddSeo(pageTitle, metaDescription);
            }

            productDetailSteps.clickSaveChanges();

            if (!"".equals(message)) {
                productDetailSteps.verifyMessage(message);
            }

            if (!"".equals(errorMessage)) {
                productDetailSteps.verifyMessageProductCreated(errorMessage);
            } else {
                productDetailSteps.verifyNoMessage();
            }

            if (SessionData.getDataTbRowsNoHeader(dataTable).size() > 1 && row != SessionData.getDataTbRowsNoHeader(dataTable).size()) {
                productDetailSteps.navigateToAllProductScreen();
            }
        }
    }

    @And("^Information of created product \"([^\"]*)\" display correctly$")
    public void informationOfCreatedTShirtDisplayCorrectly(String productName, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String _customOptionName = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String total = SessionData.getDataTbVal(dataTable, row, "Total image");

            if (productName.matches("@Copy of (.*)@")) {
                productDetailSteps.verifyName("Copy of " + nameProductSbase);
                productDetailSteps.verifyTitle("Copy of " + nameProductSbase);
            } else if (productName.matches("@(.*)@")) {
                productDetailSteps.verifyName(nameProductSbase);
                productDetailSteps.verifyTitle(nameProductSbase);
            } else {
                productDetailSteps.verifyName(productName);
                productDetailSteps.verifyTitle(productName);
            }
            if (!"".equals(description)) {
                productDetailSteps.verifyDescription(description);
            }
            if (!"".equals(total)) {
                productDetailSteps.verifyTotalImage(total);
            }
            if (!"".equals(productType)) {
                productDetailSteps.verifyProductType(productType);
            }
            if (!"".equals(vendor)) {
                productDetailSteps.verifyVendor(vendor);
            }
            if (!"".equals(tag)) {
                productDetailSteps.verifyTag(tag);
            }

            if (!"".equals(optionValue)) {
                productDetailSteps.clickEditVariantWithValue(optionValue);
            }

            if (!"".equals(price)) {
                productDetailSteps.verifyPrice(price);
            }

            if (!"".equals(compareAtPrice)) {
                productDetailSteps.verifyCompareAtPrice(compareAtPrice);
            }

            if (!"".equals(costPerItem)) {
                productDetailSteps.verifyCostPerItem(costPerItem);
            }

            if (!"".equals(SKU)) {
                productDetailSteps.verifySKU(SKU);
            }

            if (!"".equals(barcode)) {
                productDetailSteps.verifyBarCode(barcode);
            }

            if (!"".equals(quantity)) {
                productDetailSteps.verifyQuantity(quantity);
            }

            if (!"".equals(weight)) {
                productDetailSteps.verifyWeight(weight);
            }
            if (!_customOptionName.isEmpty())
                productDetailSteps.verifyCustomInListCO(_customOptionName);
        }
    }

    @Given("^add collection to product$")
    public void addCollectionToProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String prodName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String colType = SessionData.getDataTbVal(dataTable, row, "Collection type");
            String colTitle = SessionData.getDataTbVal(dataTable, row, "Collection title");
            String colCondition = SessionData.getDataTbVal(dataTable, row, "Collection conditions");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            if (prodName.matches("@(.*)@")) {
                prodName = nameProductSbase;
            }
            productListSteps.searchProduct(prodName);
            productListSteps.chooseProduct(prodName);

            if (colType.equals("Manual")) {
                if (colTitle.matches("@(.*)@")) {
                    productDetailSteps.SearchAndSelectCollection(CollectionDetailDef.collectionNameManual);
                } else {
                    productDetailSteps.SearchAndSelectCollection(colTitle);
                }
            } else {
                productDetailSteps.inputProductInfor(colCondition);
            }
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
            if (!message.isEmpty()) {
                productDetailSteps.verifyMessageProductSaved(message);
            }
            productDetailSteps.navigateToAllProductScreen();
        }
    }

    @Given("^Select all product with data search \"([^\"]*)\"$")
    public void select_all_product_with_data_search(String namePro) {
        productDetailSteps.searchProduct(namePro);
        productDetailSteps.selectAllProduct();
    }

    @Given("^Choose Action \"([^\"]*)\"$")
    public void chooseAction(String action) {
        productDetailSteps.clickAction();
        productDetailSteps.chooseAction(action);
        productDetailSteps.clickDelete();
    }

    @Given("^edit information of product on dashboard \"([^\"]*)\"$")
    public void edit_information_of_product_on_dashboard(String productName, List<List<String>> dataTable) {
        if (productName.matches("@(.*)@")) {
            productDetailSteps.searchProduct(nameProductSbase);
            productDetailSteps.chooseProduct(nameProductSbase);
        } else {
            productDetailSteps.searchProduct(productName);
            productDetailSteps.chooseProduct(productName);
        }
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String editItem = SessionData.getDataTbVal(dataTable, row, "Edit item");
            String newValue = SessionData.getDataTbVal(dataTable, row, "New Value");
            System.out.println("Case ->>>>>>>>>>>>>>>>>>>>>>>" + editItem + "-" + newValue);
            if (!newValue.isEmpty()) {
                productDetailSteps.editProductItem(editItem, newValue);
            }
        }
        if (productDetailSteps.isChanged()) {
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
            productDetailSteps.verifyMessageProductSavedSuccessfully();
        }
        productDetailSteps.clickBreadcscrumbProducts();
    }

    @Given("^verify product information displayed correctly on dashboard \"([^\"]*)\"$")
    public void verify_information_of_product_on_dashboard(String productName, List<List<String>> dataTable) {
        if (productName.matches("@(.*)@")) {
            productDetailSteps.searchProduct(nameProductSbase);
            productDetailSteps.chooseProduct(nameProductSbase);
        } else {
            productDetailSteps.searchProduct(productName);
            productDetailSteps.chooseProduct(productName);
        }
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String item = SessionData.getDataTbVal(dataTable, row, "Item");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            if (!value.isEmpty()) {
                productDetailSteps.verifyProductInformation(item, value);
            }
        }
    }

    @Given("^edit product \"([^\"]*)\" have quantity is \"([^\"]*)\" and allow customers to purchase this product when it's out of stock is \"([^\"]*)\"$")
    public void edit_product_have_quantity_is(String nameProduct, String quantity, String isAlowPurchase) {
        productListSteps.searchProduct(nameProduct);
        productDetailSteps.chooseProduct(nameProduct);
        productDetailSteps.verifyProductDetailPage();
        productDetailSteps.enterQuantity(quantity);
        productDetailSteps.selectAllowCustomerPurchaseWhenOutStock(isAlowPurchase);
        productDetailSteps.clickSaveChangesOrDiscard("Save changes");
    }

    @And("^verify quantity of product \"([^\"]*)\" in dashboard is \"([^\"]*)\" by API$")
    public void verifyQuantityOfProductInDashboardIsByAPI(String productName, String numberProductInDashboard) {
        if (accessToken.isEmpty()) {
            accessToken = productDetailSteps.getAccessToken();
        }
        float expectNumberProduct = Float.parseFloat(numberProductInDashboard);
        int i = 1;
        while (expectNumberProduct != productDetailSteps.getNumberProductByProductName(productName, accessToken)) {
            productDetailSteps.waitABit(5000);
            if (i == 5) {
                break;
            }
            i++;
        }
        assertThat(expectNumberProduct).isEqualTo(productDetailSteps.getNumberProductByProductName(productName, accessToken));
    }

    @And("^Collect the product to collection$")
    public void collectProductToCollection() {
        productDetailSteps.SearchAndSelectCollection(CollectionDetailDef.collectionNameManual);
        productDetailSteps.clickSaveChangesOrDiscard("Save changes");
        productDetailSteps.verifyNoMessage();
    }

    @Then("^Verify collection of product$")
    public void verifyCollectionOfProduct() {
        productDetailSteps.verifyCollectionOfProduct(CollectionDetailDef.collectionNameManual);
    }

    @And("^Edit product \"([^\"]*)\" with data$")
    public void editProductWithData(String productTitle, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            productDetailSteps.navigateToAllProductScreen();

            if (productTitle.matches("@(.*)@")) {
                productListSteps.searchProduct(nameProductSbase);
            } else {
                productListSteps.searchProduct(productTitle);
            }

            if (productTitle.matches("@(.*)@")) {
                productListSteps.chooseProduct(nameProductSbase);
            } else {
                productListSteps.chooseProduct(productTitle);
            }

            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String images = SessionData.getDataTbVal(dataTable, row, "Images");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String inventoryPolicy = SessionData.getDataTbVal(dataTable, row, "Inventory policy");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Error message");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            if (!"".equals(images)) {
                productDetailSteps.enterImageURL(images);
            }

            if (title.matches("@(.*)@")) {
                long currentTime = System.currentTimeMillis();
                nameProductSbase = title.replaceAll("@", "") + "_" + currentTime;
                productDetailSteps.enterTitle(nameProductSbase);
                System.out.println(nameProductSbase);
            } else {
                productDetailSteps.enterTitle(title);
            }

            productDetailSteps.waitABit(2000);

            if (!"".equals(description)) {
                productDetailSteps.enterDescription(description);
            }

            if (!"".equals(productType)) {
                productDetailSteps.enterProductType(productType);
            }

            if (!"".equals(vendor)) {
                productDetailSteps.enterVendor(vendor);
            }

            if (!"".equals(tag)) {
                productDetailSteps.inputTag(tag);
            }

            if (!"".equals(price)) {
                productDetailSteps.enterPrice(price);
            }

            if (!"".equals(compareAtPrice)) {
                productDetailSteps.enterCompareAtPrice(compareAtPrice);
            }

            if (!"".equals(costPerItem)) {
                productDetailSteps.enterCostPerItem(costPerItem);
            }

            if (!"".equals(SKU)) {
                productDetailSteps.enterSKU(SKU);
            }

            if (!"".equals(barcode)) {
                productDetailSteps.enterBarcode(barcode);
            }

            if (!"".equals(inventoryPolicy)) {
                productDetailSteps.selectInventoryPolicy(inventoryPolicy);
                productDetailSteps.enterQuantity(quantity);
            }

            if (!"".equals(weight)) {
                productDetailSteps.enterWeight(weight);
            }

            productDetailSteps.clickSaveChangesOrDiscard("Save changes");

            if (!"".equals(message)) {
                productDetailSteps.verifyMessage(message);
            }

            if (!"".equals(errorMessage)) {
                productDetailSteps.verifyProductErrorMessage(errorMessage);
            } else {
                productDetailSteps.verifyNoMessage();
            }
        }
    }

    @Then("^Add tags frequently for product$")
    public void add_tags_frequently_for_new_product() {
        productDetailSteps.verifyFrequentlyTagsDisplay("tag1", "tag2", "tag3");// at least 1 product has "tag1", "tag2", "tag3"
        productDetailSteps.addTag("tag1");
        productDetailSteps.verifyPopupAllTags("tag1", "tag2", "tag3");
        productDetailSteps.addTagsOnPopup("tag1", "tag2");
    }

    @Then("^Duplicate product$")
    public void duplicateProduct() {
        productDetailSteps.duplicateProduct(nameProductSbase);
    }

    @Then("^Add a new product variant with data$")
    public void addANewProductVariantWithData(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            String status = SessionData.getDataTbVal(dataTable, row, "Created variant status");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Error message");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            productDetailSteps.clickAddVariant();
            variantOptionSet = optionSet;
            productDetailSteps.enterNewOptionSet(optionSet, 1);
            productDetailSteps.enterNewOptionValue(optionValue, 1);
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");

            if (!"".equals(message)) {
                productDetailSteps.verifyMessage(message);
            }

            if (!"".equals(errorMessage)) {
                productDetailSteps.verifyProductErrorMessage(errorMessage);
            } else {
                productDetailSteps.verifyNoMessage();
            }

            productDetailSteps.refreshPage();
            loginStep.closeLiveChat();

            switch (status) {
                case "unable":
                    productDetailSteps.verifyHasNoVariant();
                    break;
                case "enable":
                    productDetailSteps.verifyHasVariant();
                    break;
                default:
                    fail();
            }
        }
    }

    @And("^Information of created variants display correctly on (product|campaign) detail page$")
    public void informationOfCreatedVariantsDisplayCorrectly(String typeShop, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String inventory = SessionData.getDataTbVal(dataTable, row, "Inventory");

            productDetailSteps.verifyVariantInventory(variant, inventory, typeShop);
            productDetailSteps.verifyVariantPrice(variant, price, typeShop);
            productDetailSteps.verifyVariantSKU(variant, sku, typeShop);
        }
    }

    @Then("^Add an other product variant with data$")
    public void addAnOtherProductVariantWithData(List<List<String>> dataTable) {
        productDetailSteps.clickAddVariant();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String inventoryPolicy = SessionData.getDataTbVal(dataTable, row, "Inventory policy");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String saveAbility = SessionData.getDataTbVal(dataTable, row, "Save ability");
            productDetailSteps.waitUntilImageVisible();
            productDetailSteps.enterOptionValue(optionValue);
            if (!price.isEmpty()) {
                productDetailSteps.enterPrice(price);
            }

            if (!compareAtPrice.isEmpty()) {
                productDetailSteps.enterCompareAtPrice(compareAtPrice);
            }

            if (!costPerItem.isEmpty()) {
                productDetailSteps.enterCostPerItem(costPerItem);
            }

            if (!SKU.isEmpty()) {
                productDetailSteps.enterSKU(SKU);
            }

            if (!barcode.isEmpty()) {
                productDetailSteps.enterBarcode(barcode);
            }

            if (!"".equals(inventoryPolicy)) {
                productDetailSteps.selectInventoryPolicy(inventoryPolicy);
                productDetailSteps.enterQuantity(quantity);
            }
            if (!weight.isEmpty()) {
                productDetailSteps.enterWeight(weight);
            }

            switch (saveAbility) {
                case "unable":
                    productDetailSteps.verifyNoButtonSave();
                    break;
                case "able":
                    productDetailSteps.verifyHasButtonSave();
                    productDetailSteps.clickSaveChangesOrDiscard("Save changes");
                    break;
                default:
                    fail();
            }
        }
    }

    @And("^Information of \"([^\"]*)\" created variants display correctly on product variant detail page$")
    public void informationOfCreatedVariantsDisplayCorrectlyOnProductVariantDetailPage(String numberOfVariants, List<List<String>> dataTable) {
        productDetailSteps.clickEditVariant();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option");
            String numberImage = SessionData.getDataTbVal(dataTable, row, "Number image");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String trackQuantity = SessionData.getDataTbVal(dataTable, row, "Track quantity");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String allowOverselling = SessionData.getDataTbVal(dataTable, row, "Allow overselling");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String weightUnit = SessionData.getDataTbVal(dataTable, row, "Weight unit");

            productDetailSteps.selectVariantOnVariantDetailPage(optionValue);
            if (!"".equals(variantOptionSet)) {
                productDetailSteps.verifyOptionSet(variantOptionSet);
            } else {
                productDetailSteps.verifyOptionSet(optionSet);
            }
            if (!"".equals(nameProductSbase)) {
                productDetailSteps.verifyPageTitle(optionValue, nameProductSbase);
            } else {
                productDetailSteps.verifyPageTitle(optionValue, productName);
            }
            if (!numberOfVariants.isEmpty()) {
                productDetailSteps.verifyNumberOfVariant(numberOfVariants);
            }
            if (!optionValue.isEmpty()) {
                productDetailSteps.verifyOptionValue(optionValue);
            }

            if (!numberImage.isEmpty()) {
                productDetailSteps.countImageVariant(numberImage);
            }

            if (!price.isEmpty()) {
                productDetailSteps.verifyPrice(price);
            }

            if (!compareAtPrice.isEmpty()) {
                productDetailSteps.verifyCompareAtPrice(compareAtPrice);
            }

            if (!costPerItem.isEmpty()) {
                productDetailSteps.verifyCostPerItem(costPerItem);
            }

            if (!SKU.isEmpty()) {
                productDetailSteps.verifySKU(SKU);
            }

            if (!barcode.isEmpty()) {
                productDetailSteps.verifyBarcode(barcode);
            }

            if (!trackQuantity.isEmpty()) {
                productDetailSteps.verifyTrackQuantity(trackQuantity);
            }

            if (!quantity.isEmpty()) {
                productDetailSteps.verifyQuantity(quantity);
            }

            if (!allowOverselling.isEmpty()) {
                productDetailSteps.verifyAllowOverselling(allowOverselling);
            }

            if (!weight.isEmpty()) {
                productDetailSteps.verifyWeight(weight);
            }

            if (!weightUnit.isEmpty()) {
                productDetailSteps.verifyWeightUnit(weightUnit);
            }
        }
    }

    @And("^Edit information of created variants with data$")
    public void editInformationOfCreatedVariantsWithData(List<List<String>> dataTable) {
        productDetailSteps.clickEditVariant();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String oldOptionValue = SessionData.getDataTbVal(dataTable, row, "Old option");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image url");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String SKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");

            productDetailSteps.selectVariantOnVariantDetailPage(oldOptionValue);
            if (!"".equals(optionValue)) {
                productDetailSteps.enterOptionValue(optionValue);
            }

            if (!"".equals(image)) {
                String[] listImage = image.split(",");
                for (String img : listImage)
                    productDetailSteps.addImage(img);
            }

            if (!"".equals(imageUrl)) {
                productDetailSteps.enterImageURL(imageUrl);
            }

            if (!"".equals(price)) {
                productDetailSteps.enterPrice(price);
            }

            if (!"".equals(compareAtPrice)) {
                productDetailSteps.enterCompareAtPrice(compareAtPrice);
            }

            if (!"".equals(costPerItem)) {
                productDetailSteps.enterCostPerItem(costPerItem);
            }

            if (!"".equals(SKU)) {
                productDetailSteps.enterSKU(SKU);
            }

            if (!"".equals(barcode)) {
                productDetailSteps.enterBarcode(barcode);
            }

            if (!"".equals(quantity)) {
                productDetailSteps.enterQuantity(quantity);
            }

            if (!"".equals(weight)) {
                productDetailSteps.enterWeight(weight);
            }
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
        }
        productDetailSteps.clickBreadcscrumbProducts();
        listImage = productDetailSteps.getListImage();
        totalMedia = productDetailSteps.getListMedia();
    }

    @And("^Delete variant with data$")
    public void deleteVariantWithData(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option");
            productDetailSteps.clickEditVariantWithValue(optionValue);
            productDetailSteps.clickDeleteVariantButton();
        }
    }

    @Given("^Close browser tab with index \"([^\"]*)\"$")
    public void closeBrowserTabWithIndex(int index) {
        productDetailSteps.closeBrowserTabWithIndex(index);
    }

    @Then("^Duplicate variant with 1 option set$")
    public void check_duplicate_variant_with_1_option_name(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String variantDuplicate = SessionData.getDataTbVal(dataTable, row, "Variant duplicate");
            String newOptionValue = SessionData.getDataTbVal(dataTable, row, "New option value");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            productDetailSteps.selectVariant(variantDuplicate);
            productDetailSteps.duplicateVariant(optionSet);
            productDetailSteps.enterNewValue(optionSet, newOptionValue);
            productDetailSteps.clickSaveButton();

            if (!"".equals(message)) {
                productDetailSteps.verifyMessageDuplicateVariant(message);
                loginStep.closeLiveChat();
            }
        }
    }

    @Then("^Add a new product variant with multi option set$")
    public void addANewProductVariantWithOptionName(List<List<String>> dataTable) {
        productDetailSteps.waitABit(20000);
        productDetailSteps.clickAddVariant();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            if (row > 1) {
                productDetailSteps.clickAddAnotherOption();
            }
            productDetailSteps.enterNewOptionSet(optionSet, row);
            productDetailSteps.enterNewOptionValue(optionValue, row);
            variantOptionSet += optionSet;
            if (row < SessionData.getDataTbRowsNoHeader(dataTable).size()) {
                variantOptionSet += "; ";
            }
        }
        productDetailSteps.clickSaveChanges();
    }

    @Then("^Duplicate variant with multi option set$")
    public void checkDuplicateVariantWith2OptionName(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String variantDuplicate1 = SessionData.getDataTbVal(dataTable, row, "Variant duplicate 1");
            String variantDuplicate2 = SessionData.getDataTbVal(dataTable, row, "Variant duplicate 2");
            String variantDuplicate3 = SessionData.getDataTbVal(dataTable, row, "Variant duplicate 3");
            String variantDuplicate4 = SessionData.getDataTbVal(dataTable, row, "Variant duplicate 4");
            String optionValueNew = SessionData.getDataTbVal(dataTable, row, "Option value new");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            productDetailSteps.refreshPage();
            loginStep.closeLiveChat();
            productDetailSteps.selectVariant(variantDuplicate1);
            productDetailSteps.selectVariant(variantDuplicate2);
            productDetailSteps.selectVariant(variantDuplicate3);
            productDetailSteps.selectVariant(variantDuplicate4);
            productDetailSteps.duplicateVariant(optionSet);
            productDetailSteps.enterNewValue(optionSet, optionValueNew);
            productDetailSteps.clickSaveButton();

            if (!"".equals(message)) {
                productDetailSteps.verifyMessageDuplicateVariant(message);
                loginStep.closeLiveChat();
            }
        }
    }

    @When("^Add another option with data$")
    public void addAnotherOptionWithData(List<List<String>> dataTable) {
        productDetailSteps.clickOnEditOptions();
        productDetailSteps.clickAddAnotherOptionInEditOption();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            productDetailSteps.enterAnotherOption(optionSet);
            productDetailSteps.enterAnotherOptionValue(optionSet, optionValue);
            productDetailSteps.clickSaveButton();
            if (!"".equals(message)) {
                productDetailSteps.verifyMessageUpdateOption(message);
            }
            variantOptionSet += "; ";
            variantOptionSet += optionSet;
        }
    }

    private String setAddNewVariantOptionSet(String variantOptionSet, String oldOptionSet, String newOptionSet) {
        return variantOptionSet.replace(oldOptionSet, newOptionSet);
    }

    private String setDeleteVariantOptionSet(String variantOptionSet, String optionSet) {
        return variantOptionSet.replace("; " + optionSet, "");
    }

    @When("^Edit option with data$")
    public void editOptionWithData(List<List<String>> dataTable) {
        productDetailSteps.clickOnEditOptions();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String oldOptionSet = SessionData.getDataTbVal(dataTable, row, "Old option set");
            String newOptionSet = SessionData.getDataTbVal(dataTable, row, "New option set");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            productDetailSteps.enterNewOption(oldOptionSet, newOptionSet);
            productDetailSteps.clickSaveButton();

            if (!"".equals(message)) {
                productDetailSteps.verifyMessageUpdateOption(message);
            }
            variantOptionSet = setAddNewVariantOptionSet(variantOptionSet, oldOptionSet, newOptionSet);
        }
    }

    @When("^Delete option with data$")
    public void deleteOptionWithData(List<List<String>> dataTable) {
        productDetailSteps.clickOnEditOptions();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionSet = SessionData.getDataTbVal(dataTable, row, "Option set");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");

            productDetailSteps.deleteOption(optionSet, optionValue);
            variantOptionSet = setDeleteVariantOptionSet(variantOptionSet, optionSet);
        }
    }


    @Then("^Verify default data of Search engine listing preview$")
    public void verifyDefaultDataOfSearchEngineListingPreview(List<List<String>> dataTable) {
        productDetailSteps.clickEditWebsiteSEO();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String metaDescription = SessionData.getDataTbVal(dataTable, row, "Meta description");
            String urlAndHandle = SessionData.getDataTbVal(dataTable, row, "URL and handle");

            if (!"".equals(pageTitle)) {
                if (pageTitle.matches("@(.*)@")) {
                    productDetailSteps.verifyDefaultPageTitle(nameProductSbase);
                } else {
                    productDetailSteps.verifyDefaultPageTitle(pageTitle);
                }
            }
            productDetailSteps.verifyDefaultMetaDescription(metaDescription);
            if (!"".equals(urlAndHandle)) {
                if (urlAndHandle.matches("@(.*)@")) {
                    productDetailSteps.verifyDefaultURLAndHandle(nameProductSbase);
                } else {
                    productDetailSteps.verifyDefaultURLAndHandle(urlAndHandle);
                }
            }
        }
    }

    @Then("^Edit Search engine listing preview with data$")
    public void editSearchEngineListingPreviewWithData(List<List<String>> dataTable) {
        productDetailSteps.clickEditWebsiteSEO();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String metaDescription = SessionData.getDataTbVal(dataTable, row, "Meta description");
            String urlAndHandle = SessionData.getDataTbVal(dataTable, row, "URL and handle");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            productDetailSteps.enterPageTitle(pageTitle);
            pageTitleInitial = pageTitle;
            productDetailSteps.enterMetaDescription(metaDescription);
            if (!"".equals(urlAndHandle)) {
                if (urlAndHandle.matches("@(.*)@")) {
                    long currentTime = System.currentTimeMillis();
                    urlAndHandleInitial = urlAndHandle.replaceAll("@", "") + "-" + currentTime;
                    productDetailSteps.enterUrlAndHandle(urlAndHandleInitial);
                } else {
                    productDetailSteps.enterUrlAndHandle(urlAndHandle);
                }
            }
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");

            if (!"".equals(message)) {
                productDetailSteps.verifyMessage(message);
            }
        }
    }

    @Then("^Verify data of Search engine listing preview$")
    public void verifyDataOfSearchEngineListingPreview(List<List<String>> dataTable) {
        productDetailSteps.clickEditWebsiteSEO();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String lengthPageTitle = SessionData.getDataTbVal(dataTable, row, "Length page title");
            String metaDescription = SessionData.getDataTbVal(dataTable, row, "Meta description");
            String lengthMetaDescription = SessionData.getDataTbVal(dataTable, row, "Length meta description");
            String urlAndHandle = SessionData.getDataTbVal(dataTable, row, "URL and handle");
            productDetailSteps.verifyDataPageTitle(pageTitle);
            productDetailSteps.verifyLengthSeo("Page title", lengthPageTitle);
            productDetailSteps.verifyDataMetaDescription(metaDescription);
            productDetailSteps.verifyLengthSeo("Meta description", lengthMetaDescription);

            if (!"".equals(urlAndHandle)) {
                if (urlAndHandle.matches("@(.*)@")) {
                    productDetailSteps.verifyDataURLAndHandle(urlAndHandleInitial);
                } else {
                    productDetailSteps.verifyDataURLAndHandle(urlAndHandle);
                }
            }
        }
    }

    @Then("^Add tags for product$")
    public void addTagsForProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            productDetailSteps.inputTag(tag);
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");

            if (!"".equals(message)) {
                productDetailSteps.verifyMessage(message);
            }
            productDetailSteps.verifyTag(tag);
        }
    }

    @And("^Click Edit variants and verify Inventory policy is \"([^\"]*)\"$")
    public void clickEditVariantsAndVerifyInventoryPolicy(String inventoryPolicy) {
        productDetailSteps.clickEditVariant();
        productDetailSteps.verifyInventoryPolicy(inventoryPolicy);
    }

    @Given("^Delete product$")
    public void delete_product() {
        productDetailSteps.deleteProduct();
    }

    @And("^verify collection deleted in product$")
    public void verifyCollectionDeletedInProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String prodName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String colTitle = SessionData.getDataTbVal(dataTable, row, "Collection title");

            if (prodName.matches("@(.*)@")) {
                prodName = nameProductSbase;
            }
            if (colTitle.matches("@(.*)@"))
                colTitle = CollectionDetailDef.collectionNameAuto;

            productListSteps.searchProduct(prodName);
            productListSteps.chooseProduct(prodName);
            productDetailSteps.verifyCollections(colTitle);
        }
    }

    @Given("^edit product \"([^\"]*)\" have Inventory policy is \"([^\"]*)\"$")
    public void edit_product_have_Inventory_policy_is(String productName, String inventoryPolicy) {
        productListSteps.searchProduct(productName);
        productListSteps.chooseProduct(productName);
        productDetailSteps.verifyProductDetailPage();
        productDetailSteps.selectInventoryPolicy(inventoryPolicy);
        productDetailSteps.clickSaveChangesOrDiscard("Save changes");
    }

    @And("^Add product by api with title \"([^\"]*)\"$")
    public void addProductByApiWithTitle(String title) {
        shop = LoadObject.getProperty("shop");
        if (accessToken.isEmpty()) {
            accessToken = productDetailSteps.getAccessTokenShopbase();
        }
        productDetailSteps.addProductByApi(shop, title, accessToken);
    }

    @And("^edit tags product on dashboard$")
    public void editTagsProductOnDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");

            productDetailSteps.searchProduct(title);
            productDetailSteps.chooseProduct(title);
            productDetailSteps.deleteTags();
            productDetailSteps.inputTag(tags);
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
        }
    }

    @Then("^verify the product \"([^\"]*)\" has been mapped$")
    public void verifyTheProductHasBeenMapped(String nameProduct) {
        productDetailSteps.chooseProduct(nameProduct);
        productDetailSteps.verifyProductMapped();
    }

    @Then("verify button")
    public void verifyButton(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String button = SessionData.getDataTbVal(dataTable, row, "Button");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            productDetailSteps.verifyButtonDisable(button, status, title);
        }
    }

    @And("edit variant")
    public void editVariant() {
        productDetailSteps.editVariant();
    }

    @And("verify product detail when users have action with multiple products")
    public void verifyDetailValueOfTheAnyProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String unselectProduct = SessionData.getDataTbVal(dataTable, row, "Number of product unselected");
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String listProduct = SessionData.getDataTbVal(dataTable, row, "List product");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String totalProduct = SessionData.getDataTbVal(dataTable, row, "Total product");

            int numberOfProduct = 0;

            if (!listProduct.isEmpty()) {
                productListSteps.verifyListProduct(listProduct);
            }
            productListSteps.selectAllProductCrossPage();

            if (!unselectProduct.isEmpty()) {
                numberOfProduct = Integer.parseInt(unselectProduct);
            }
            productListSteps.unSelectProduct(numberOfProduct);

            if (!action.isEmpty()) {
                productListSteps.actionWithMultipleProduct(action);
            }
            productListSteps.selectTab(tab);
            if (!totalProduct.isEmpty()) {
                productListSteps.selectAllProductCrossPage();
                productListSteps.verifyTotalProduct(totalProduct);
            }
            if (!status.isEmpty()) {
                String[] products = listProduct.split(",");
                for (String product : products) {
                    productListSteps.verifyStatus(product, status);
                }
            }
            productDetailSteps.openProductDetail();
            if (!tags.isEmpty()) {
                productDetailSteps.verifyTags(tags, true);
            }
            if (!collection.isEmpty())
                productDetailSteps.verifyCollections(collection);

            productDetailSteps.clickBreadcscrumbProducts();
        }
    }

    @And("view product on store front")
    public void viewProductOnStoreFront() {
        productDetailSteps.viewProduct();
    }

    @Then("Select variant image")
    public void selectVariantImage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String totalImageAdded = SessionData.getDataTbVal(dataTable, row, "Total image added");

            productDetailSteps.clickImageVariant(variant);
            productDetailSteps.selectImageVariant(image);
            productDetailSteps.previewImageVariant(image);
            productDetailSteps.clickDone();
            productDetailSteps.clickSave();
            productDetailSteps.verifyImageVariant(totalImageAdded);
        }
    }

    @Then("verify group variant display")
    public void verifyGroupVariantDisplay(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean check = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Check group"));
            productDetailSteps.verifyGroupVariant(check);
        }
    }

    @When("Upload media for product or campaign")
    public void uploadMediaForProductOrCampaign(List<List<String>> dataTable) {
        productListSteps.clickAddProduct();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image url");
            String errorImage = SessionData.getDataTbVal(dataTable, row, "Error image");

            if (!title.isEmpty()) {
                if (title.matches("@(.*)@")) {
                    nameProductSbase = title.replaceAll("@", "") + "_" + System.currentTimeMillis();
                    productDetailSteps.enterTitle(nameProductSbase);
                } else {
                    productDetailSteps.enterTitle(title);
                }
            }

            if (!"".equals(image)) {
                productDetailSteps.addImage(image);
            }

            if (!"".equals(imageUrl)) {
                productDetailSteps.enterImageURL(imageUrl);
            }

            if (!"".equals(errorImage)) {
                productDetailSteps.verifyMessageProduct(errorImage);
            }

            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
        }
        listImage = productDetailSteps.getListImage();
        totalMedia = productDetailSteps.getListMedia();
    }

    @And("^Verify image link")
    public void verifyImageLink() {
        productDetailSteps.verifyImageLink();
    }

    @And("^Get number of image of (source|des|artwork source|artwork des) shop$")
    public void getNumberOfImage(String typeShop) {
        switch (typeShop) {
            case "source":
                numberOfImageSource = productDetailSteps.getNumberOfImage();
                break;
            case "des":
                numberOfImageDes = productDetailSteps.getNumberOfImage();
                break;
            case "artwork source":
                numberOfArtworkSource = productDetailSteps.getNumberOfArtwork();
                break;
            case "artwork des":
                numberOfArtworkDes = productDetailSteps.getNumberOfArtwork();
                break;
            default:
                fail();
        }
    }

    @And("^Verify number of image of both shop$")
    public void verifyNumberOfImage() {
        assertThat(numberOfImageSource).isEqualTo(numberOfImageDes);
    }

    @And("^Verify number of artwork of both shop")
    public void verifyNumberOfArtworkOfBothShop() {
        assertThat(numberOfArtworkSource).isEqualTo(numberOfArtworkDes);
    }

    @And("^Verify artwork link")
    public void verifyArtworkLink() {
        productDetailSteps.verifyArtworkLink();
    }

    @And("^Verify image custom option on storefront")
    public void verifyImageCustomOption() {
        productDetailSteps.verifyImageCustomOption();
    }

    @And("add new custom option with data")
    public void addNewCustomOptionWithData(List<List<String>> dataTable) {
        productDetailSteps.clickAddCustomOption();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String nameCO = SessionData.getDataTbVal(dataTable, row, "Name");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String label = SessionData.getDataTbVal(dataTable, row, "Label");
            String allowFollowing = SessionData.getDataTbVal(dataTable, row, "Allow the following characters");
            String values = SessionData.getDataTbVal(dataTable, row, "Values");
            String addAnotherOption = SessionData.getDataTbVal(dataTable, row, "Add another option");
            String hideOption = SessionData.getDataTbVal(dataTable, row, "Hide option");
            productDetailSteps.enterNameCo(customOption, nameCO);
            if (!type.isEmpty())
                productDetailSteps.selectType(nameCO, type);
            productDetailSteps.enterLabelCo(nameCO, label);
            if (type.equals("Radio buttons") || type.equals("Droplist")) {
                productDetailSteps.inputValue(nameCO, values);
            }
            if (type.equals(("Picture choice"))) {
                productDetailSteps.selectImageOnPictureChoice(values);
            }
            if (type.equals(("Checkbox"))) {
                productDetailSteps.inputValueInCheckboxConditional(values);
            }
            if (!allowFollowing.isEmpty()) {
                productDetailSteps.selectCheckbox(nameCO, allowFollowing);
            }
            if (hideOption.equals("yes")) {
                productDetailSteps.hideOption(nameCO);
            }
            productDetailSteps.collapseCO(nameCO);
            if (addAnotherOption.equals("yes")) {
                productDetailSteps.addAnotherOption();
            }
        }
        productDetailSteps.clickSaveChangesOrDiscard("Save changes");
    }

    @And("click button {string} in product detail")
    public void clickButtonInProductDetail(String btnName) {
        productDetailSteps.clickbtnCO(btnName);
    }

    @And("verify custom option in product page")
    public void verifyCustomOptionInProductPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sNameCO = SessionData.getDataTbVal(dataTable, row, "Name");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Label");
            String _sMax_Lenght = SessionData.getDataTbVal(dataTable, row, "Max length");
            String _sDefaultValue = SessionData.getDataTbVal(dataTable, row, "Default value");
            String _sAllow = SessionData.getDataTbVal(dataTable, row, "Allow the following characters");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String _sNotiDefaultValue = SessionData.getDataTbVal(dataTable, row, "Noti for Default value");
            System.out.println("run : " + row);
            productDetailSteps.clickAddCustomOption();
            myCampaignSteps.inputCustomOption("Name", _sNameCO);
            productDetailSteps.selectType(_sNameCO, _sType);
            productDetailSteps.enterLabelCo(_sNameCO, _sLabel);
            if (_sType.contains("Text")) {
                myCampaignSteps.inputCustomOption("Max length", _sMax_Lenght);
                if (!_sAllow.isEmpty()) {
                    myCampaignSteps.clickAllow(_sAllow);
                    myCampaignSteps.inputCustomOption("Default value ", _sDefaultValue);
                }
            }
            if (_sType.contains("Picture")) {
                if (!_sImage.isEmpty()) {
                    myCampaignSteps.inputImage(_sImage);
                }
            }
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
            // verify notication
            if (_sNameCO.isEmpty())
                myCampaignSteps.verifyNotifycation("Name", "Field is required");
            if (_sLabel.isEmpty())
                myCampaignSteps.verifyNotifycation("Label (display on storefront)", "Field is required");
            if (_sType.contains("Text")) {
                if (Integer.parseInt(_sMax_Lenght) == Integer.parseInt("0")) {
                    myCampaignSteps.verifyNotifycation("Max length", "Max length must be greater than 0");
                } else if (Integer.parseInt(_sMax_Lenght) >= Integer.parseInt("256")) {
                    myCampaignSteps.verifyNotifycation("Max length", "Max length must be less than 256");
                }
                if (!_sNotiDefaultValue.isEmpty())
                    myCampaignSteps.verifyNotifycation("Default value", _sNotiDefaultValue);
                if (_sAllow.isEmpty())
                    myCampaignSteps.verifyNotifycation("Allow the following characters", "You have to select at least one option.");
            }
            productDetailSteps.clickDeleteCO();
        }
    }


    @And("verify image and printfile after create")
    public void verifyImageAndPrintfileAfterCreate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String imagePreview = SessionData.getDataTbVal(dataTable, row, "Is preview");
            String imagePrinfile = SessionData.getDataTbVal(dataTable, row, "Is print file");
            boolean isPreview = isCheck(imagePreview);
            boolean isPrintfile = isCheck(imagePrinfile);
            productDetailSteps.verifyShowImagePersonalize("Preview Image", isPreview);
            productDetailSteps.verifyShowImagePersonalize("Print Files", isPrintfile);
        }
    }

    public boolean isCheck(String bol) {
        boolean isCheck = true;
        if (bol.equalsIgnoreCase("no"))
            isCheck = false;
        return isCheck;
    }

    @And("verify custom option after create as {string}")
    public void verifyCustomOptionAfterCreateAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sCOName = SessionData.getDataTbVal(dataTable, row, "CO name");
            String _sCOLabel = SessionData.getDataTbVal(dataTable, row, "CO label");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sTargetLayer = SessionData.getDataTbVal(dataTable, row, "Target layer");
            String _sMaxAndAllow = SessionData.getDataTbVal(dataTable, row, "Max lenght and allow");
            String _sValue = SessionData.getDataTbVal(dataTable, row, "Value");
            String[] max_lenght;
            if (!_sCOName.isEmpty()) {
                productDetailSteps.verifyCustomInListCO(_sCOName + ">true");
                productDetailSteps.clickOpenOrCloseCODetail(_sCOName, true);
                productDetailSteps.verifyValueCO(_sCOName, "Name", _sCOName);
                if (!_sCOLabel.isEmpty())
                    productDetailSteps.verifyLabelCO(_sCOName, _sCOLabel);
                if (!_sTargetLayer.isEmpty())
                    productDetailSteps.verifyTargetLayer(_sCOName, _sTargetLayer);
                switch (_sType) {
                    case "Text":
                        productDetailSteps.verifyValueTypeText(_sCOName, _sType);
                        if (!_sMaxAndAllow.isEmpty()) {
                            max_lenght = _sMaxAndAllow.split(">");
                            productDetailSteps.verifyValueCO(_sCOName, "Max length", max_lenght[0]);
                        }
                        break;
                    case "Image":
                    case "Picture choice":
                        productDetailSteps.verifyValueTypeText(_sCOName, _sType);
                        break;
                    case "Radio":
                        productDetailSteps.verifyValueTypeText(_sCOName, _sType);
                        productDetailSteps.verifyValueCOChecked(_sCOName, _sValue);
                    case "Droplist":
                        productDetailSteps.verifyValueTypeText(_sCOName, _sType);
                        productDetailSteps.verifyValueCOChecked(_sCOName, _sValue);
                    case "Checkbox":
                        productDetailSteps.verifyValueTypeText(_sCOName, _sType);
                        productDetailSteps.verifyValueCheckboxCO(_sCOName, _sValue);
                }
            }
        }
    }

    @And("add condtional logic")
    public void addCondtionalLogic(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String showValue = SessionData.getDataTbVal(dataTable, row, "Show value");
            String[] list = showValue.split(">");
            productDetailSteps.addConditionalLogic(customOption);
            productDetailSteps.selectCondition(condition);
            productDetailSteps.selectValue(value);
            int i = 1;
            for (String _sValue : list) {
                if (i >= 2) {
                    productDetailSteps.addNewOptionOnCondtionalLogic();
                }
                productDetailSteps.selectShowValue(_sValue, i);
                i++;
            }
            productDetailSteps.clickSave();
        }
        productDetailSteps.clickSaveChangesOrDiscard("Save changes");
    }

    @And("get link handle url of product on dashboard")
    public void getLinkHandleUrlOfProductOnDashboard() {
        urlProductOnSF = productDetailSteps.getHandleUrlPro();
    }

    @And("add product variant with name {string}")
    public void addProductVariantWithName(String name) {
        productDetailSteps.clickAddVariant();
        productDetailSteps.inputNameVariant(name);
        productDetailSteps.clickSaveChanges();
    }

    @When("Setting automatically generate SKU")
    public void settingAutomaticallyGenerateSKU(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean enable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            String template = SessionData.getDataTbVal(dataTable, row, "Template");
            String setting = SessionData.getDataTbVal(dataTable, row, "Setting");

            productDetailSteps.enableGenerateSKU(enable);
            productDetailSteps.inputTemplate(template);
            productDetailSteps.clickSetting(setting);
            productDetailSteps.clickSave();
        }
    }

    @And("verify conditional logic on custom option product")
    public void verifyConditionalLogicOnCOProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String customName = SessionData.getDataTbVal(dataTable, row, "CustomName");
            String customNameConditon = SessionData.getDataTbVal(dataTable, row, "CustomName Conditional");
            String isShow = SessionData.getDataTbVal(dataTable, row, "Show conditional logic");
            boolean sShow = false;
            if (isShow.equals("true")) {
                sShow = true;
            }
            productDetailSteps.verifyConditonalLogicAfDuplicate(customName, customNameConditon, sShow);
        }
    }

    @And("get shop id")
    public void getShopId() {
        productDetailSteps.getShopID();
    }

    @And("click save product")
    public void clickSaveProduct() {
        productDetailSteps.clickSaveChanges();
    }

    @And("click to Expand filter options in hive sbase")
    public void clickToExpandFilterOptionsInHiveSbase() {
        productDetailSteps.clickToExpandFilterOptionsInHiveSbase();
    }

    @And("input Created form and to in hive")
    public void inputCreatedFormAndToInHive() {
        productDetailSteps.inputCreatedFrom();
        productDetailSteps.inputCreatedTo();

    }

    @And("choose filter product")
    public void chooseFilterProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String platformType = SessionData.getDataTbVal(dataTable, row, "Platform type");
            String shopID = SessionData.getDataTbVal(dataTable, row, "Store ID");
            String paymentStatus = SessionData.getDataTbVal(dataTable, row, "Payment status");
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            if (shopID.equalsIgnoreCase("@shopID@")) {
                shopID = printbaseAPI.getShopID(accessToken);
                productDetailSteps.inputShopIDInHive(shopID);
            }
            if (!platformType.isEmpty() && !platformType.equalsIgnoreCase("PrintBase")) {
                productDetailSteps.clickToPlatformType(platformType);
            }
            if(!paymentStatus.isEmpty()){
                productDetailSteps.selectPaymentStatusInHive(paymentStatus);
            }
        }
    }


    @And("verify list products in Product moderation list page")
    public void verifyProductsInProductModerationListPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String listProduct = SessionData.getDataTbVal(dataTable, row, "List product");
            String totalProduct = SessionData.getDataTbVal(dataTable, row, "Total product");

            if (!listProduct.isEmpty())
                productDetailSteps.verifyListProductsInProductModerationListPage(listProduct);
            productDetailSteps.verifyTotalProductsInProductModerationListPage(totalProduct);
        }
    }

    @And("verify lock edit custom option as {string}")
    public void verifyLockEditCustomOption(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sCOName = SessionData.getDataTbVal(dataTable, row, "CO name");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String values = SessionData.getDataTbVal(dataTable, row, "Value");
            String isDisable = SessionData.getDataTbVal(dataTable, row, "isDisable");
            productDetailSteps.clickOpenOrCloseCODetail(_sCOName, true);
            switch (_sType) {
                case "Text field":
                case "Text area":
                    productDetailSteps.verifyDisableEditCampPhhub(isDisable, _sCOName);
                    productDetailSteps.verifyDisableAllowCharacters(isDisable, _sCOName);
                    break;
                case "Radio buttons":
                case "Droplist":
                    productDetailSteps.verifyDisableEditCampPhhub(isDisable, _sCOName);
                    productDetailSteps.verifyDisableCOradio(isDisable, values, _sCOName);
                    break;
                case "Checkbox":
                    productDetailSteps.verifyDisableCheckboxCO(isDisable);
                    break;
                case "Picture choice":
                    productDetailSteps.verifyDisableEditCampPhhub(isDisable, _sCOName);
                    productDetailSteps.verifyDisablePictureChoice();
                    break;
                case "Image":
                    productDetailSteps.verifyDisableEditCampPhhub(isDisable, _sCOName);
                    break;
            }
        }
    }

    @And("verify show button Personalization on product detail of Phub camp")
    public void verifyButtonPersonalization(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String buttonName = SessionData.getDataTbVal(dataTable, row, "Name button");
            productDetailSteps.verifyButtonPersonalization(buttonName);
        }
    }

    @And("click Create Personalization")
    public void clickCreatePersonalization() {
        productDetailSteps.clickCreatePersonalization();
    }

    @When("update images for a variant")
    public void updateImagesForAVariant(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String positionImage = SessionData.getDataTbVal(dataTable, row, "Position Image");

            productDetailSteps.selectImageVariant(variant, positionImage);
            imageVariant = productDetailSteps.getImageVariant();
            productDetailSteps.clickSaveImage();
            productDetailSteps.clickSaveChangesOrDiscard("Save changes");
        }
    }

    @And("get list and total image to screen detail")
    public void getListAndTotalImageToScreenDetail() {
        listImage = productDetailSteps.getListImage();
        totalMedia = productDetailSteps.getListMedia();
    }
}