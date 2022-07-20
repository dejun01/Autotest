package opencommerce.settings;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SalesChannelSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell.PostPurchaseOfferSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OnePageCheckoutSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static common.utilities.LoadObject.convertStatus;
import static java.util.Arrays.asList;


public class SalesChannelDef {
    @Steps
    SalesChannelSteps salesChannelSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    BalanceSteps balanceSteps;
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    PostPurchaseOfferSteps postPurchaseOfferSteps;


    //Pinterest
    String init = "";
    String pageVisit = "";
    String addToCart = "";
    String checkout = "";

    //GMC
    public String nameProductSbase = "";
    public float variantId;
    public static String feedName = "";

    HashMap<Integer, List> before = new HashMap<>();
    HashMap<Integer, List> after = new HashMap<>();


    String emailPinterest = LoadObject.getProperty("emailPinterest");
    String pwdPinterest = LoadObject.getProperty("pwdPinterest");


    @When("^open sale channel \"([^\"]*)\" tracking$")
    public void openSaleChannelTracking(String channel) {
        switch (channel) {
            case "Pinterest":
                commonSteps.openUrl("https://ads.pinterest.com/login/");
                break;
        }
    }

    @Then("login to Pinterest")
    public void loginToPinterest() {
        salesChannelSteps.loginToPinterest(emailPinterest, pwdPinterest);
    }

    @And("navigate to conversion page on Pinterest")
    public void navigateToConversionPagePinterest() {
        salesChannelSteps.navigateToConversionPagePinterest();
    }

    @And("add event type on Pinterest")
    public void addEventTypePinterest() {
        salesChannelSteps.clickAddEventTypePinterest();
        salesChannelSteps.addEventPinterest("PageVisit");
        salesChannelSteps.addEventPinterest("AddToCart");
        salesChannelSteps.addEventPinterest("Checkout");
        salesChannelSteps.clickToSavePinterest();
    }

    @And("get data event on Pinterest")
    public void getDataEventPinterest() {
        commonSteps.waitABit(5000);
        if (salesChannelSteps.isPinterestNoData()) {
            init = "0";
            pageVisit = "0";
            addToCart = "0";
            checkout = "0";
        } else {
            init = salesChannelSteps.getDataEventPinterest("Init");
            pageVisit = salesChannelSteps.getDataEventPinterest("PageVisit");
            addToCart = salesChannelSteps.getDataEventPinterest("AddToCart");
            checkout = salesChannelSteps.getDataEventPinterest("Checkout");
        }
    }

    @And("verify tracking increase on Pinterest")
    public void verifyTrackingIncreaseOnPinterest(List<List<String>> dataTable) {
        commonSteps.switchToTheFirstTab();
        commonSteps.waitABit(20000);
        commonSteps.refreshPage();
//        addEventTypePinterest();
        String initActual = salesChannelSteps.getDataEventPinterest("Init");
        String pageActual = salesChannelSteps.getDataEventPinterest("PageVisit");
        String addToCartActual = salesChannelSteps.getDataEventPinterest("AddToCart");
        String checkoutActual = salesChannelSteps.getDataEventPinterest("Checkout");

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String initInc = SessionData.getDataTbVal(dataTable, row, "Init");
            String pageVisitInc = SessionData.getDataTbVal(dataTable, row, "PageVisit");
            String addToCartInc = SessionData.getDataTbVal(dataTable, row, "AddToCart");
            String checkoutInc = SessionData.getDataTbVal(dataTable, row, "Checkout");

            salesChannelSteps.verifyDataChanged(init, initInc, initActual);
            salesChannelSteps.verifyDataChanged(pageVisit, pageVisitInc, pageActual);
            salesChannelSteps.verifyDataChanged(addToCart, addToCartInc, addToCartActual);
            salesChannelSteps.verifyDataChanged(checkout, checkoutInc, checkoutActual);
        }
        init = initActual;
        pageVisit = pageActual;
        addToCart = addToCartActual;
        checkout = checkoutActual;
        salesChannelSteps.switchToNextTab();
    }

    @And("add product to cart without verify")
    public void addProductToCartWithoutVerify() {
        balanceSteps.addToCartWithoutVerify();
        commonSteps.waitABit(5000);
    }

    @And("^go to checkout page and checkout without verify with \"([^\"]*)\" user$")
    public void goToCheckoutPageAndCheckoutWithoutVerify(String userType, List<List<String>> dataTable) {
        balanceSteps.clickToCheckoutWithoutVerify();
        onePageCheckoutSteps.chooseEmail(userType);
        onePageCheckoutSteps.enterShippingAddress();
        onePageCheckoutSteps.enterCardInformation();
        onePageCheckoutSteps.clickPlaceYourOrder();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String actionPPC = SessionData.getDataTbVal(dataTable, row, "Action PPC");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product PPC");
            if (postPurchaseOfferSteps.isPPCShow()) {
                if (actionPPC.matches("Add to order")) {
                    postPurchaseOfferSteps.addProductPPC(productName);
                } else {
                    postPurchaseOfferSteps.clickOnPassPostPurchase();
                }
            }
        }


        thankyouSteps.verifyThankYouPage();
        orderNumber = thankyouSteps.getOrderNumber();
        totalAmt = orderSummarySteps.getTotalAmt();
    }

    @And("^open product \"([^\"]*)\" on SF$")
    public void openProductOnSF(String productName) {
        balanceSteps.openProductInSF(productName);
    }

    @Then("^Add feed \"([^\"]*)\"$")
    public void addFeed(String feedType) {
        salesChannelSteps.addFeed(feedType);
    }

    @Then("^Update feed setting$")
    public void createFeed(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            feedName = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String feedType = SessionData.getDataTbVal(dataTable, row, "Feed type");
            String collectionName = SessionData.getDataTbVal(dataTable, row, "Collection name");
            String feedSetting = SessionData.getDataTbVal(dataTable, row, "Feed setting");
            String inputOption = SessionData.getDataTbVal(dataTable, row, "Input option for feed setting");
            String variantTitle = SessionData.getDataTbVal(dataTable, row, "Variant title");
            String productTitlePreference = SessionData.getDataTbVal(dataTable, row, "Product title preference");
            String productDescriptionPreference = SessionData.getDataTbVal(dataTable, row, "Product description preference");
            String submitProductsAsCustomProducts = SessionData.getDataTbVal(dataTable, row, "Submit products as custom products");
            String defaultBrandName = SessionData.getDataTbVal(dataTable, row, "Default brand name");
            String googleProductCategory = SessionData.getDataTbVal(dataTable, row, "Google product category");
            String defaultGender = SessionData.getDataTbVal(dataTable, row, "Default gender");
            String defaultAgeGroup = SessionData.getDataTbVal(dataTable, row, "Default age group");

            if (!"".equals(feedName)) {
                salesChannelSteps.enterFeedName(feedName);
            }

            salesChannelSteps.chooseRadioButton(feedType);

            if (!"".equals(collectionName)) {
                salesChannelSteps.chooseCollection(collectionName);
            }

            salesChannelSteps.chooseRadioButton(feedSetting);

            if (!inputOption.equals("")) {
                salesChannelSteps.checkOnCheckboxExclude();
                String[] option = inputOption.split("/");
                salesChannelSteps.chooseOption1(option[0], 1);
                salesChannelSteps.chooseOption2(option[1], 1);
                salesChannelSteps.chooseOption3(option[2], 1);
            }

            salesChannelSteps.chooseRadioButton(variantTitle);
            salesChannelSteps.chooseRadioButton(productTitlePreference);
            salesChannelSteps.chooseRadioButton(productDescriptionPreference);
            if (!"".equals(submitProductsAsCustomProducts)) {
                salesChannelSteps.chooseRadioButton(submitProductsAsCustomProducts);
            }

            if (!"".equals(defaultBrandName)) {
                salesChannelSteps.enterDefaultBrandName(defaultBrandName);
            }

            if (!"".equals(googleProductCategory)) {
                salesChannelSteps.chooseGoogleProductCategory(googleProductCategory);
            }

            if (!"".equals(defaultGender)) {
                salesChannelSteps.chooseGender(defaultGender);
            }

            if (!"".equals(defaultAgeGroup)) {
                salesChannelSteps.chooseAgeGroup(defaultAgeGroup);
            }
        }
    }

    @Then("^click feed save button$")
    public void clickFeedSaveButton() {
        salesChannelSteps.clickFeedSaveButton();
    }

    @And("^verify the number of variant in manage product data$")
    public void verifyTheNumberOfVariantInManageProductData(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String feedType = SessionData.getDataTbVal(dataTable, row, "Feed type");
            int actualNumberOfVariant = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Actual number of variant"));
            int actualNumberOfVariantInProductData = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Actual number of variant in product data"));
            salesChannelSteps.verifyNumberOfVariant(actualNumberOfVariant, actualNumberOfVariantInProductData, feedType, feedName);
        }
    }

    @Then("^verify feed variant$")
    public void verifyFeedVariant(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String actualDescription = SessionData.getDataTbVal(dataTable, row, "Description");
            String actualProductType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String actualVendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String actualSKU = SessionData.getDataTbVal(dataTable, row, "SKU");
            String actualBarcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String actualWeight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String actualWeightUnit = SessionData.getDataTbVal(dataTable, row, "Weight unit");
            String actualGoogleProductCategory = SessionData.getDataTbVal(dataTable, row, "Google product category");
            String actualDefaultGender = SessionData.getDataTbVal(dataTable, row, "Default gender");
            String actualDefaultAgeGroup = SessionData.getDataTbVal(dataTable, row, "Default age group");
            List<Object> numberOfElements = asList(title, actualDescription, actualProductType, actualVendor, actualSKU, actualBarcode, actualWeight, actualWeightUnit, actualGoogleProductCategory, actualDefaultGender, actualDefaultAgeGroup);
            before.put(1, numberOfElements);

            JsonPath rs = salesChannelSteps.getDataInManageProductDataFromAPI();
            variantId = Float.parseFloat(salesChannelSteps.getDataInManageProductData(rs, title, "variant_id"));
            String getTitle = salesChannelSteps.getDataInManageProductData(rs, title, "title");
            String getDescription = salesChannelSteps.getDataInManageProductData(rs, title, "description");
            String getProductType = salesChannelSteps.getDataInManageProductData(rs, title, "product_type");
            String getVendor = salesChannelSteps.getDataInManageProductData(rs, title, "vendor");
            String getSKU = salesChannelSteps.getDataInManageProductData(rs, title, "sku");
            String getBarcode = salesChannelSteps.getDataInManageProductData(rs, title, "barcode");
            String getWeight = salesChannelSteps.getDataInManageProductData(rs, title, "shipping_weight_value");
            String getWeightUnit = salesChannelSteps.getDataInManageProductData(rs, title, "shipping_weight_unit");
            String getGoogleProductCategory = salesChannelSteps.getDataInManageProductData(rs, title, "google_product_category");
            String getDefaultGender = salesChannelSteps.getDataInManageProductData(rs, title, "gender");
            String getAgeGroup = salesChannelSteps.getDataInManageProductData(rs, title, "age_group");
            List<Object> getValues = asList(getTitle, getDescription, getProductType, getVendor, getSKU, getBarcode, getWeight, getWeightUnit, getGoogleProductCategory, getDefaultGender, getAgeGroup);
            after.put(2, getValues);

            for (int i = 0; i < getValues.toArray().length; i++) {
                salesChannelSteps.verifyVariantDetailInManageProductData((String) (before.get(1).get(i)), (String) (after.get(2).get(i)));
            }
        }
    }

    @Then("^open \"([^\"]*)\" in feed list$")
    public void openInFeedList(String feedNameTemp) {
        salesChannelSteps.openInFeedList(feedNameTemp);
    }

    @And("^open manage product data$")
    public void openManageProductData() {
        salesChannelSteps.openManageProductData();
    }

    @Then("^update and verify manage product data detail$")
    public void UpdateAndVerifyManageProductDataDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String actualTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String actualDescription = SessionData.getDataTbVal(dataTable, row, "Description");
            String actualVendor = SessionData.getDataTbVal(dataTable, row, "Brand (Vendor)");
            String actualId = SessionData.getDataTbVal(dataTable, row, "ID");
            String actualProductType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String actualItemGroupId = SessionData.getDataTbVal(dataTable, row, "Item group ID");
            String actualSku = SessionData.getDataTbVal(dataTable, row, "MPN (SKU)");
            String actualBarcode = SessionData.getDataTbVal(dataTable, row, "GTIN (Barcode)");
            String actualGoogleCategory = SessionData.getDataTbVal(dataTable, row, "Google Product Category");
            String actualCondition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String actualAgeGroup = SessionData.getDataTbVal(dataTable, row, "Age Group");
            String actualGender = SessionData.getDataTbVal(dataTable, row, "Gender");
            String actualColor = SessionData.getDataTbVal(dataTable, row, "Color");
            String actualSizeSystem = SessionData.getDataTbVal(dataTable, row, "Size System");
            String actualSizeType = SessionData.getDataTbVal(dataTable, row, "Size Type");
            double actualPricingMeasure = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Pricing Measure"));
            double actualPricingBaseMeasure = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Pricing Base Measure"));
            String actualUnitPricingmeasure = SessionData.getDataTbVal(dataTable, row, "Unit Pricing Measure");
            String actualUnitPricingBaseMeasure = SessionData.getDataTbVal(dataTable, row, "Unit Pricing Base Measure");
            String actualProductIdentifier = SessionData.getDataTbVal(dataTable, row, "Product Identifiers Management");
            String actualCustomLabel0 = SessionData.getDataTbVal(dataTable, row, "Custom Label 0");
            String actualCustomLabel1 = SessionData.getDataTbVal(dataTable, row, "Custom Label 1");
            String actualCustomLabel2 = SessionData.getDataTbVal(dataTable, row, "Custom Label 2");
            String actualCustomLabel3 = SessionData.getDataTbVal(dataTable, row, "Custom Label 3");
            String actualCustomLabel4 = SessionData.getDataTbVal(dataTable, row, "Custom Label 4");
            String actualAdsLabel = SessionData.getDataTbVal(dataTable, row, "Ads label");
            String actualAdsGrouping = SessionData.getDataTbVal(dataTable, row, "Ads grouping");
            String actualShippingLabel = SessionData.getDataTbVal(dataTable, row, "Shipping label");
            double actualShippingWeightValue = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Shipping weight"));
            String actualShippingWeightUnit = SessionData.getDataTbVal(dataTable, row, "Shipping weight unit");
            List<Object> actualValues = asList(actualTitle, actualDescription, actualVendor, actualId, actualProductType, actualItemGroupId, actualSku, actualBarcode, actualGoogleCategory, actualCondition, actualAgeGroup, actualGender, actualColor, actualSizeSystem, actualSizeType, actualPricingMeasure, actualPricingBaseMeasure, actualUnitPricingmeasure, actualUnitPricingBaseMeasure, actualProductIdentifier, actualCustomLabel0, actualCustomLabel1, actualCustomLabel2, actualCustomLabel3, actualCustomLabel4, actualAdsGrouping, actualAdsLabel, actualShippingLabel, actualShippingWeightValue, actualShippingWeightUnit);
            before.put(1, actualValues);

            JsonObject requestParams = new JsonObject();
            requestParams.addProperty("title", actualTitle);
            requestParams.addProperty("description", actualDescription);
            requestParams.addProperty("vendor", actualVendor);
            requestParams.addProperty("override_variant_id", actualId);
            requestParams.addProperty("product_type", actualProductType);
            requestParams.addProperty("override_product_id", actualItemGroupId);
            requestParams.addProperty("sku", actualSku);
            requestParams.addProperty("barcode", actualBarcode);
            requestParams.addProperty("google_product_category", actualGoogleCategory);
            requestParams.addProperty("condition", actualCondition);
            requestParams.addProperty("age_group", actualAgeGroup);
            requestParams.addProperty("gender", actualGender);
            requestParams.addProperty("color", actualColor);
            requestParams.addProperty("size_system", actualSizeSystem);
            requestParams.addProperty("size_type", actualSizeType);
            requestParams.addProperty("pricing_measure", actualPricingMeasure);
            requestParams.addProperty("pricing_base_measure", actualPricingBaseMeasure);
            requestParams.addProperty("unit_pricing_measure", actualUnitPricingmeasure);
            requestParams.addProperty("unit_pricing_base_measure", actualUnitPricingBaseMeasure);
            requestParams.addProperty("product_identifier", actualProductIdentifier);
            requestParams.addProperty("custom_label_0", actualCustomLabel0);
            requestParams.addProperty("custom_label_1", actualCustomLabel1);
            requestParams.addProperty("custom_label_2", actualCustomLabel2);
            requestParams.addProperty("custom_label_3", actualCustomLabel3);
            requestParams.addProperty("custom_label_4", actualCustomLabel4);
            requestParams.addProperty("ads_label", actualAdsLabel);
            requestParams.addProperty("ads_group", actualAdsGrouping);
            requestParams.addProperty("shipping_label", actualShippingLabel);
            requestParams.addProperty("shipping_weight_value", actualShippingWeightValue);
            requestParams.addProperty("shipping_weight_unit", actualShippingWeightUnit);
            salesChannelSteps.objectJp(requestParams);

            salesChannelSteps.waitABit(20000);
            JsonPath rs = salesChannelSteps.getDataInManageProductDataFromAPI();
            String getTitle = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "title");
            String getDescription = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "description");
            String getProductType = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "product_type");
            String getVendor = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "vendor");
            String getId = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "override_variant_id");
            String getItemGroupId = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "override_product_id");
            String getSku = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "sku");
            String getBarcode = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "barcode");
            String getGoogleCategory = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "google_product_category");
            String getCondition = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "condition");
            String getAgeGroup = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "age_group");
            String getGender = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "gender");
            String getColor = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "color");
            String getSizeSystem = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "size_system");
            String getSizeType = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "size_type");
            double getPricingMeasure = Double.parseDouble(salesChannelSteps.getDataInManageProductData(rs, actualTitle, "pricing_measure"));
            double getPricingBaseMeasure = Double.parseDouble(salesChannelSteps.getDataInManageProductData(rs, actualTitle, "pricing_base_measure"));
            String getUnitPricingMeasure = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "unit_pricing_measure");
            String getUnitPricingBaseMeasure = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "unit_pricing_base_measure");
            String getProductIdentifiersManagement = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "product_identifier");
            String getCustomLabel0 = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "custom_label_0");
            String getCustomLabel1 = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "custom_label_1");
            String getCustomLabel2 = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "custom_label_2");
            String getCustomLabel3 = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "custom_label_3");
            String getCustomLabel4 = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "custom_label_4");
            String getAdsLabel = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "ads_label");
            String getAdsGrouping = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "ads_group");
            String getShippingLabel = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "shipping_label");
            double getShippingWeight = Double.parseDouble(salesChannelSteps.getDataInManageProductData(rs, actualTitle, "shipping_weight_value"));
            String getShippingWeightUnit = salesChannelSteps.getDataInManageProductData(rs, actualTitle, "shipping_weight_unit");
            List<Object> getValues = asList(getTitle, getDescription, getVendor, getId, getProductType, getItemGroupId, getSku, getBarcode, getGoogleCategory, getCondition, getAgeGroup, getGender, getColor, getSizeSystem, getSizeType, getPricingMeasure, getPricingBaseMeasure, getUnitPricingMeasure, getUnitPricingBaseMeasure, getProductIdentifiersManagement, getCustomLabel0, getCustomLabel1, getCustomLabel2, getCustomLabel3, getCustomLabel4, getAdsGrouping, getAdsLabel, getShippingLabel, getShippingWeight, getShippingWeightUnit);
            after.put(2, getValues);

            for (int i = 0; i < getValues.toArray().length; i++) {
                if (i != 15 && i != 16 && i != 28) {
                    salesChannelSteps.verifyVariantDetailInManageProductData((String) (before.get(1).get(i)), (String) (after.get(2).get(i)));
                } else {
                    salesChannelSteps.verifyVariantDetailInManageProductDataNumber((Double) (before.get(1).get(i)), (Double) (after.get(2).get(i)));
                }
            }
        }
    }

    @And("^delete all feed in feed list$")
    public void deleteAllFeedInFeedList() {
        salesChannelSteps.deleteAllFeedInFeedList();
    }
}
