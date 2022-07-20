package opencommerce.settings;

import com.opencommerce.shopbase.SettingsVariables;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.settings.steps.ShippingSteps;
import org.yecht.Data;

import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;
import static com.opencommerce.shopbase.SettingsVariables.*;

import java.util.List;

public class ShippingDef {
    @Steps
    ShippingSteps shippingSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    ProductSteps productSteps;

    public String profileType = "";

    @Then("^add shipping zones with country and Price Based rate$")
    public void add_shipping_zones_with_country_and_price_based_rate(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sZonename = SessionData.getDataTbVal(dataTable, row, "Zone name");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Countries");
            String sPriceRates = SessionData.getDataTbVal(dataTable, row, "Price based rate");


            shippingSteps.clickAddShippingZone();
            if (!sZonename.isEmpty()) {
                shippingSteps.enterZoneName(sZonename);
            }
            if (!sCountry.isEmpty()) {
                shippingSteps.addCountries(sCountry);
            }

            if (!sPriceRates.isEmpty()) {
                String rateName = sPriceRates;
                String rateMin = "0";
                String rateMax = "0";
                boolean freeRate = false;
                String rateAmount = "0";
                if (sPriceRates.contains(",")) {
                    String[] priceRates = sPriceRates.split(",");
                    int priceRatesLength = priceRates.length;
                    rateName = priceRates[0];
                    rateMin = priceRates[1];
                    rateMax = priceRates[2];
                    freeRate = Boolean.parseBoolean(priceRates[3]);
                    rateAmount = priceRates[4];
//                    if have ETA delivery time
                    if (priceRatesLength > 5) {
                        shipFrom = priceRates[5];
                    }
                    if (priceRatesLength > 6) {
                        shipTo = priceRates[6];
                    }
                }
                shippingSteps.addPriceBasedRates1(rateName, rateMin, rateMax, freeRate, rateAmount, shipFrom, shipTo);
                shippingSteps.clickBtnSaveChanges(sZonename);
                shippingSteps.clickBreadCrumbShipping();
            }
        }
    }

    @Then("^add shipping zones with country and Weight based rate$")
    public void add_shipping_zones_with_country_and_weight_based_rate(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sZonename = SessionData.getDataTbVal(dataTable, row, "Zone name");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Countries");
            String sWeightRates = SessionData.getDataTbVal(dataTable, row, "Weight based rate");
            shippingSteps.clickAddShippingZone();
            if (!sZonename.isEmpty()) {
                shippingSteps.enterZoneName(sZonename);
            }

            if (!sCountry.isEmpty()) {
                shippingSteps.addCountries(sCountry);
            }

            if (!sWeightRates.isEmpty()) {
                if (sWeightRates.contains(",")) {
                    String[] weightRates = sWeightRates.split(",");
                    String rateName = weightRates[0];

                    String minOrderWeight = weightRates[1];
                    String maxOrderWright = weightRates[2];
                    boolean freeRate = Boolean.parseBoolean(weightRates[3]);
                    String rateAmount = weightRates[4];
                    shippingSteps.addWeightBasedRates(rateName, minOrderWeight, maxOrderWright, freeRate, rateAmount);
                } else {
                    shippingSteps.addWeightBasedRates(sWeightRates, "0", "0", false, "0");
                }
                shippingSteps.clickBtnSaveChanges(sZonename);
                shippingSteps.clickBreadCrumbShipping();
            }
        }
    }

    @Then("^delete shipping zone$")
    public void delete_shipping_zone(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sZonename = SessionData.getDataTbVal(dataTable, row, "Zone name");
            if (sZonename.contains(",")) {
                String[] zoneNames = sZonename.split(",");
                for (String zoneName : zoneNames) {
                    if (shippingSteps.isShippingZoneExisted(zoneName)) {
                        shippingSteps.chooseEditShippingZone(zoneName);
                        shippingSteps.clickDeleteZone(true);
                    }
                }
            } else {
                if (shippingSteps.isShippingZoneExisted(sZonename)) {
                    shippingSteps.chooseEditShippingZone(sZonename);
                    shippingSteps.clickDeleteZone(true);
                }
            }
        }
    }

    @Then("^delete all existed shipping zone$")
    public void deleteAllExistedShippingZone() {
        shippingSteps.deleteAllExistedShippingZone();
    }


    @Given("^add price based rate for shipping zone \"([^\"]*)\"$")
    public void add_price_based_rate_for_shipping_zone(String shippingZone, List<List<String>> dataTable) {
        //navigate to shipping zone detail
        if (!shippingSteps.isShippingZoneDetailPageDisplayed(shippingZone)) {
            if (shippingSteps.isShippingZoneExisted(shippingZone)) {
                shippingSteps.chooseEditShippingZone(shippingZone);
            }
        }
        String dataTableKey = "priceBasedRateInfo";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sRateName = SessionData.getDataTbVal(dataTable, row, "Rate name");
            String sMinOrderPrice = SessionData.getDataTbVal(dataTable, row, "Min order price");
            String sMaxOrderPrice = SessionData.getDataTbVal(dataTable, row, "Max order price");
            boolean isFreeRate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Free rate"));
            String sRateAmount = SessionData.getDataTbVal(dataTable, row, "Rate amount");
            shippingSteps.addPriceBasedRates(sRateName, sMinOrderPrice, sMaxOrderPrice, isFreeRate, sRateAmount);
        }
        shippingSteps.clickBtnSaveChanges(shippingZone);
    }


    @Given("^add weight based rate for shipping zone \"([^\"]*)\"$")
    public void add_weight_based_rate_for_shipping_zone(String shippingZone, List<List<String>> dataTable) {
        //navigate to shipping zone detail
        if (!shippingSteps.isShippingZoneDetailPageDisplayed(shippingZone)) {
            if (shippingSteps.isShippingZoneExisted(shippingZone)) {
                shippingSteps.chooseEditShippingZone(shippingZone);
            }
        }

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sRateName = SessionData.getDataTbVal(dataTable, row, "Rate name");
            String sMinOrderWeight = SessionData.getDataTbVal(dataTable, row, "Min order weight");
            String sMaxOrderWeight = SessionData.getDataTbVal(dataTable, row, "Max order weight");
            boolean isFreeRate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Free rate"));
            String sRateAmount = SessionData.getDataTbVal(dataTable, row, "Rate amount");
            shippingSteps.addWeightBasedRates(sRateName, sMinOrderWeight, sMaxOrderWeight, isFreeRate, sRateAmount);
            System.out.println("Error in step = " + row);
        }
        shippingSteps.clickBtnSaveChanges(shippingZone);
    }

    @Given("^add item based rate with condition for zone \"([^\"]*)\"$")
    public void add_item_based_rate_with_condition(String shippingZone, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sRateName = SessionData.getDataTbVal(dataTable, row, "Rate Name");
            String sGroupName = SessionData.getDataTbVal(dataTable, row, "GROUP");
            String sFilter = SessionData.getDataTbVal(dataTable, row, "FILTER");
            String sExclusion = SessionData.getDataTbVal(dataTable, row, "EXCLUSION");
            boolean isFreeRate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Free rate"));
            String sFirstItem = SessionData.getDataTbVal(dataTable, row, "First item");
            String sAdditionalItem = SessionData.getDataTbVal(dataTable, row, "Each additional item");
            if (!shippingSteps.isShippingZoneDetailPageDisplayed(shippingZone)) {
                if (shippingSteps.isShippingZoneExisted(shippingZone)) {
                    shippingSteps.chooseEditShippingZone(shippingZone);
                }
            }

            shippingSteps.clickBtnAddRate("Item based rates");
            shippingSteps.enterRateName(sRateName);
            shippingSteps.enterGroupName(sGroupName);
            shippingSteps.enterRules("Filter", sFilter);
            shippingSteps.enterRules("Exclusion", sExclusion);
            if (isFreeRate) {
                shippingSteps.checkFreeShippingRate();
            } else {
                shippingSteps.enterFirstItemShippingPrice(sFirstItem);
                shippingSteps.enterEachAdditionalItemShippingPrice(sAdditionalItem);
            }
            shippingSteps.clickBtnDone();
        }
        shippingSteps.clickBtnSaveChanges(shippingZone);
        shippingSteps.clickBreadCrumbShipping();
    }

    @Given("^delete item base rate of shipping zone \"([^\"]*)\"$")
    public void delete_item_based_rate_of_condition(String shippingZone, List<List<String>> dataTable) {
        String dataTableKey = "itemBasedRateInformation";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sRateName = SessionData.getDataTbVal(dataTable, row, "Rate Name");
            if (!shippingSteps.isShippingZoneDetailPageDisplayed(shippingZone)) {
                if (shippingSteps.isShippingZoneExisted(shippingZone)) {
                    shippingSteps.chooseEditShippingZone(shippingZone);
                }
            }
            if (shippingSteps.isRateExist("Item based rates", sRateName)) {
                shippingSteps.deleteShippingRate("Item based rates", sRateName);
            }
        }
    }


    /* --------------------------Shipping Profiles-------------------------*/
    @And("^navigate to Manage rates screen of (General Profile|Custom Profile) shipping$")
    public void navigateToManageRatesScreenOfGeneralProfileShipping(String profileType) {
        shippingSteps.navigateToManageRatesScreen(profileType);
        this.profileType = profileType;
    }


    @And("^creat profile with name \"([^\"]*)\" then create zone$")
    public void createZone(String profileName, List<List<String>> dataTable) {
        if (!profileName.equalsIgnoreCase("General Profile")) {
            shippingSteps.creatANewProfile();
        }
        shippingSteps.enterProfileName(profileName);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String zoneName = SessionData.getDataTbVal(dataTable, row, "Zone name");
            String countries = SessionData.getDataTbVal(dataTable, row, "Countries");

            shippingSteps.clickBtnCreateShippingZone();
            shippingSteps.enterZoneName(zoneName);
            shippingSteps.searchThenChooseCountries(countries);
            shippingSteps.clickBtnDone();
        }
        SettingsVariables.profileName = profileName;
        profileType = "Custom Profile";
    }

    @And("add rates for \"([^\"]*)\" zone")
    public void addRatesForZone(String zoneName, List<List<String>> dataTable) {
        String type = "", range = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String rateName = SessionData.getDataTbVal(dataTable, row, "Rate name");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String additionalCondition = SessionData.getDataTbVal(dataTable, row, "Additional condition");
            if (additionalCondition.contains(":")) {
                type = additionalCondition.split(":")[0].trim();
                range = additionalCondition.split(":")[1].trim();
            }
            shippingSteps.clickBtnAddRateForZone(zoneName);
            shippingSteps.enterRateNameVer2(rateName);
            shippingSteps.enterPrice(price);

            if (!additionalCondition.isEmpty()) {
                shippingSteps.checkOnCheckboxAddCondition(profileType);
                shippingSteps.selectRadioBtn(type);
                shippingSteps.enterCondition(type, range);
            }
        }
    }

    @And("^delete all shipping zone in General Profile shipping$")
    public void deleteAllShippingZoneInGeneralProfileShipping() {
        shippingSteps.deleteShippingZones();
    }

    @And("delete all Custom Profile")
    public void deleteAllCustomProfile() {
        shippingSteps.deleteAllShippingProfiles();
    }

    @And("^add (product rules|exclusion rules) for Custom Profile with (All conditions|Any conditions)$")
    public void addProductRulesForCustomProfileWithAllConditions(String ruleType, String conditionType, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String filter = SessionData.getDataTbVal(dataTable, row, "FILTER");

            if (ruleType.equalsIgnoreCase("product rules"))
                shippingSteps.selectProductMustMatch(conditionType);
            shippingSteps.addFilter(filter, row);

        }
    }

    @And("^add product rules for Custom Profile is \"([^\"]*)\"$")
    public void addProductRulesForShippingProfile(String sku) {
        shippingSteps.addProductRules(sku);
    }

    @And("^creat a new profile$")
    public void creatANewProfile() {
        shippingSteps.creatANewProfile();
    }


    @And("click Save changes button")
    public void clickButton() {
        shippingSteps.clickBtnSaveChangesProfile(profileName);
    }

    @And("Untick all existing free shipping settings")
    public void untickAllExistingFreeShippingSettings(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shipping_zone = SessionData.getDataTbVal(dataTable, row, "Shipping zones");
            if (!shippingSteps.isDisableSection(shipping_zone)) {
                shippingSteps.clickOnCheckBoxFreeShippingRate(shipping_zone);
                shippingSteps.clickBtnSaveChangesFreeShipping();
            }
        }
    }

    @Then("Verify block input condition value for order is inactive")
    public void verifyBlockInputConditionValueForOrderIsInactive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shipping_zone = SessionData.getDataTbVal(dataTable, row, "Shipping zones");
            shippingSteps.verifyStatusOfFreeShippingZone("inactive", shipping_zone);
        }
    }

    @When("Tick free shipping rate for zone then input condition value for order then save")
    public void tickFreeShippingRateForZoneThenInputConditionValueForOrderThenSave(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shipping_zone = SessionData.getDataTbVal(dataTable, row, "Zone");
            String shipping_value = SessionData.getDataTbVal(dataTable, row, "Value");
            shippingSteps.clickOnCheckBoxFreeShippingRate(shipping_zone);
            shippingSteps.inputValueForFreeShippingOfZone(shipping_zone, shipping_value);
        }
        shippingSteps.clickBtnSaveChangesFreeShipping();
    }

    @Then("Verify message {string} is shown")
    public void verifyMessageIsShown(String message) {
        commonSteps.verifyToastMessageIsShown(message);
    }

    @Then("Verify block input condition value for order is active")
    public void verifyBlockInputConditionValueForOrderIsActive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shipping_zone = SessionData.getDataTbVal(dataTable, row, "Zone");
            String shipping_value = SessionData.getDataTbVal(dataTable, row, "Value");
            shippingSteps.verifyFreeShippingZoneIsActive(shipping_zone, shipping_value);
        }
    }

    @Then ("verify ETA shipping time on {string}")
    public void verifyETAShippingTime(String page) {
        if(page.equals("product page")) {
            shippingSteps.verifyETADeliveryTime(page);
            productSteps.clickButtonBuyNow();
        }
        else {
            shippingSteps.verifyETADeliveryTime(page);
        }
    }
}
