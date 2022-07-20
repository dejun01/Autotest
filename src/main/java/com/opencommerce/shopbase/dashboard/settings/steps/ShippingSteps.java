package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.ProductVariable;
import com.opencommerce.shopbase.common.pages.CommonPage;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.settings.pages.ShippingPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import static org.assertj.core.api.Assertions.assertThat;
import static com.opencommerce.shopbase.SettingsVariables.*;

import java.util.ArrayList;

public class ShippingSteps extends ScenarioSteps {
    ShippingPage shippingPage;
    CommonPage commonPage;


    @Step
    public void clickAddShippingZone() {
        shippingPage.waitForPageLoad();
        shippingPage.clickLinkTextWithLabel("Add shipping zone");
    }

    @Step
    public void clickBtnSaveChanges(String zoneName) {
        shippingPage.clickBtnSaveChanges(zoneName);
    }

    @Step
    public void clickBreadCrumbShipping() {
        shippingPage.clickLinkTextWithLabel("Shipping");
        shippingPage.waitForEverythingComplete();
    }

    @Step
    public void enterZoneName(String zoneName) {
        if (!zoneName.isEmpty())
            shippingPage.enterInputFieldWithLabel("Zone name", zoneName);
    }

    @Step
    public void clickBtnAddRate(String basedRateName) {
        shippingPage.clickBtnAddRate(basedRateName);
    }

    @Step
    public void chooseEditShippingZone(String zoneName) {
        shippingPage.chooseEditShippingZone(zoneName);
    }

    @Step
    public void clickDeleteZone(boolean confirmDelete) {
        shippingPage.clickDeleteZone(confirmDelete);
    }

    @Step
    public boolean isShippingZoneExisted(String zoneName) {
        return shippingPage.isShippingZoneExisted(zoneName);
    }

    @Step
    public void deleteAllExistedShippingZone() {
        shippingPage.deleteAllShippingZone();
    }

    @Step
    public void addPriceBasedRates1(String rateName, String rateMin, String rateMax, boolean freeRate, String rateAmount, String shippingTimeFrom, String shippingTimeTo) {
        clickBtnAddRate("Price based rates");
        shippingPage.enterRateName(rateName);
        shippingPage.enterRateMin(rateMin);
        shippingPage.enterRateMax(rateMax);
        shippingPage.chooseFreeShippingRate(freeRate);
        if (!freeRate) {
            shippingPage.enterRateAmount(rateAmount);
        }
        shippingPage.inputShippingTime(shippingTimeFrom,shippingTimeTo);
        clickBtnDone();
    }
    public void addPriceBasedRates(String rateName, String rateMin, String rateMax, boolean freeRate, String rateAmount) {
        clickBtnAddRate("Price based rates");
        shippingPage.enterRateName(rateName);
        shippingPage.enterRateMin(rateMin);
        shippingPage.enterRateMax(rateMax);
        shippingPage.chooseFreeShippingRate(freeRate);
        if (!freeRate) {
            shippingPage.enterRateAmount(rateAmount);
        }
        clickBtnDone();
    }

    @Step
    public void addWeightBasedRates(String rateName, String minOrderWeight, String maxOrderWeight, boolean freeRate, String rateAmount) {
        clickBtnAddRate("Weight based rates");
        shippingPage.enterRateName(rateName);
        shippingPage.chooseFreeShippingRate(freeRate);
        shippingPage.enterMinOrderWeight(minOrderWeight);
        shippingPage.enterMaxOrderWeight(maxOrderWeight);
        if (!freeRate) {
            shippingPage.enterRateAmount(rateAmount);
        }
        clickBtnDone();
    }

    @Step
    public void addCountries(String sCountry) {
        shippingPage.clickBtn("Add countries");
        searchThenChooseCountries(sCountry);
        shippingPage.clickBtnAdd();
    }

    @Step
    public void searchThenChooseCountries(String sCountry) {
        if (sCountry.contains(",")) {
            String[] countries = sCountry.split(",");
            for (String country : countries) {
                shippingPage.searchCountries(country);
                shippingPage.chooseCountries(country.trim());
            }
        } else {
            shippingPage.searchCountries(sCountry);
            shippingPage.chooseCountries(sCountry);
        }
    }

    @Step
    public void clickBtnAdd() {
        shippingPage.clickBtnAdd();
    }

    @Step
    public boolean isShippingZoneDetailPageDisplayed(String zoneName) {
        return shippingPage.isShippingZoneDetailPageDisplayed(zoneName);
    }

    @Step
    public void enterRateName(String sRateName) {
        shippingPage.enterRateName(sRateName);
    }

    @Step
    public void enterGroupName(String sGroupName) {
        waitABit(1000);
        shippingPage.enterGroupName(sGroupName);
    }

    @Step
    public void addCondition(String sConditionItem, String sCondition, String sKeywords) {
        if (!sConditionItem.isEmpty()) {
            String blockName = "Filter";
            shippingPage.clickAddCondition(blockName);
            shippingPage.chooseConditionItem(blockName, sConditionItem);
            shippingPage.chooseCondition(blockName, sCondition);
            shippingPage.enterConditionKeywords(sKeywords);
        }
    }

    @Step
    public void checkFreeShippingRate() {
        shippingPage.checkCheckboxWithLabel("Free shipping rate");
    }

    @Step
    public void enterFirstItemShippingPrice(String sFirstItem) {
        shippingPage.enterFirstItemShippingPrice(sFirstItem);
    }

    @Step
    public void enterEachAdditionalItemShippingPrice(String sAdditionalItem) {
        shippingPage.enterEachAdditionalItemShippingPrice(sAdditionalItem);
    }

    @Step
    public void clickBtnDone() {
        waitABit(2000);
        shippingPage.clickBtnDone();
    }

    @Step
    public boolean isRateExist(String rateType, String rateName) {
        return shippingPage.isRateExist(rateType, rateName);
    }

    @Step
    public void deleteShippingRate(String rateType, String rateName) {
        shippingPage.clickDeleteShippingRate(rateType, rateName);
    }

    private ArrayList<String> condition(String _condition) {
        ArrayList<String> condition = new ArrayList<>();
        String[] _sFilter = _condition.split("\\[", 4);
        String sConditionItem = _sFilter[1].replace("]", "").trim();
        String sCondition = _sFilter[2].replace("]", "").trim();
        String sKeywords = _sFilter[3].replace("]", "").trim();

        condition.add(sConditionItem);
        condition.add(sCondition);
        condition.add(sKeywords);
        return condition;
    }

    // shipping - ver 1
    @Step
    public void enterRules(String blockName, String condition) {
        if (!condition.isEmpty()) {
            String sConditionItem = condition(condition).get(0);
            String sCondition = condition(condition).get(1);
            String sKeywords = condition(condition).get(2);

            shippingPage.clickAddCondition(blockName);
            shippingPage.chooseConditionItem(blockName, sConditionItem);
            shippingPage.chooseCondition(blockName, sCondition);
            shippingPage.enterConditionKeywords(sKeywords);
        }
    }

    // shipping profile - ver 2
    @Step
    public void addFilter(String filter, int row) {
        if (!filter.isEmpty()) {
            String sConditionItem = condition(filter).get(0);
            String sCondition = condition(filter).get(1);
            String sKeywords = condition(filter).get(2);

            String block = "Product must match:";
            shippingPage.clickBtn("Add condition", 1);
            shippingPage.chooseConditionItem(block, sConditionItem, row);
            shippingPage.chooseCondition(block, sCondition, row);
            shippingPage.enterConditionKeywords(block, sKeywords, row);
        }
    }
    @Step
    public void addProductRules(String sku){
        shippingPage.enterInputFieldThenEnter("","Enter keywords",sku,1);
    }
    @Step
    public void navigateToManageRatesScreen(String profileType) {
        shippingPage.clickManageRatesLinkText(profileType);
    }

    @Step
    public void enterProfileName(String profileName) {
        shippingPage.enterInputFieldWithLabel("Profile Name", profileName);
    }

    @Step
    public void clickBtnCreateShippingZone() {
        shippingPage.clickBtnCreateShippingZone();
    }

    @Step
    public void clickBtnAddRateForZone(String zoneName) {
        shippingPage.clickBtnAddRateForZone(zoneName);
    }

    public String price(String price) {
        return shippingPage.price(price);
    }

    @Step
    public void enterPrice(String price) {
        shippingPage.enterInputFieldWithLabel("Price", price(price));
    }

    @Step
    public void checkOnCheckboxAddCondition(String profileType) {
        if (profileType.equalsIgnoreCase("Custom Profile"))
            shippingPage.clickAddConditionCheckbox();
        else
            shippingPage.checkCheckboxWithLabel("Add condition", 1, true);

    }

    @Step
    public void selectRadioBtn(String additionalCondition) {
        shippingPage.selectRadioButtonWithLabel(additionalCondition, true);
    }

    @Step
    public void enterCondition(String type, String range) {
        String minVal = "0", maxVal = "0", additionalVal = "0";
        if (range.contains("~")) {
            minVal = price(range.split("~")[0].replaceAll("[^0-9]", ""));
            maxVal = price(range.split("~")[1].trim().replaceAll("[^0-9]", ""));
        } else {
            additionalVal = price(range);
        }

        switch (type) {
            case "Based on item weight":
                shippingPage.enterInputFieldWithLabel("Minimum weight", minVal);
                shippingPage.enterInputFieldWithLabel("Maximum weight", maxVal);
                break;
            case "Based on item quantity":
                shippingPage.enterInputFieldWithLabel("Per additional item", additionalVal);

                break;
            case "Based on order price":
                shippingPage.enterInputFieldWithLabel("Minimum price", minVal);
                shippingPage.enterInputFieldWithLabel("Maximum price", maxVal);
                break;
        }
        shippingPage.clickBtn("Done");
    }

    public void enterWeightRange(String minWeight, String maxWeight) {

    }

    @Step
    public void deleteShippingZones() {
        shippingPage.deleteShippingZones();
    }

    @Step
    public void enterRateNameVer2(String rateName) {
        shippingPage.enterRateNameVer2(rateName);
    }

    public int countCustomProfile() {
        return shippingPage.countCustomProfile();
    }

    @Step
    public void deleteAllShippingProfiles() {
        shippingPage.deleteAllShippingProfiles();
    }

    @Step
    public void clickBtnSaveChangesProfile(String profileName) {
        shippingPage.clickBtn("Save changes");
        shippingPage.waitUntilUpdateProfileSuccessfully(profileName);
    }

    @Step
    public void selectProductMustMatch(String ruleType) {
        shippingPage.selectRadioButtonWithLabel(ruleType, true);
    }

    @Step
    public void creatANewProfile() {
        shippingPage.clickLinkTextWithLabel("+ Create new profile", 1);
    }

    public void verifyShipping() {
        shippingPage.verifyShipping();
    }

    public boolean isDisableSection(String shipping_zone){
        return shippingPage.isDisableSection(shipping_zone);
    }

    @Step
    public void clickOnCheckBoxFreeShippingRate(String shipping_zone) {
        shippingPage.clickOnCheckBoxFreeShippingRate(shipping_zone);
    }

    @Step
    public void verifyStatusOfFreeShippingZone(String expStatus, String shipping_zone) {
        String actStatusBlock = shippingPage.getStatusOfSettingRateBlock(shipping_zone);
        assertThat(actStatusBlock).isEqualTo(expStatus);
    }

    @Step
    public void inputValueForFreeShippingOfZone(String shipping_zone, String shipping_value) {
        shippingPage.inputValueForFreeShippingOfZone(shipping_zone, shipping_value);
    }

    @Step
    public void clickBtnSaveChangesFreeShipping() {
        shippingPage.clickBtn("Save changes");
    }

    @Step
    public void verifyFreeShippingZoneIsActive(String shipping_zone, String shipping_value) {
        verifyStatusOfFreeShippingZone("active", shipping_zone);
        String actValue = shippingPage.getValueOfFreeShippingZone(shipping_zone);
        assertThat(actValue).isEqualTo(shipping_value);
    }

    public void verifyETADeliveryTime(String page) {
        switch (page) {
            case "product page":
            case "checkout page":
                shippingPage.verifyDeliveryTime(page);
                break;
            case "order status page":
                shippingPage.refreshPage();
                shippingPage.verifyDeliveryTime(page);
                break;
        }
    }
}
