package com.opencommerce.shopbase.dashboard.settings.pages;

import com.github.javafaker.Country;
import common.SBasePageObject;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.WebDriver;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;


import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;
import static common.utilities.DateTimeUtil.getCurrentDay;
import static common.utilities.DateTimeUtil.getCurrentMonth;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ShippingPage extends SBasePageObject {
    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    String msgSuccess = "//div[text()[normalize-space()='Successfully created %s'] or text()[normalize-space()='Successfully updated %s']]";

    public void clickBtnAddRate(String baseRateName) {
        String xpath = "(//div[child::b[text()='" + baseRateName + "']]//*[text()[normalize-space()='Add rate']])[1]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnDone() {
        clickOnElement("//span[text()[normalize-space()='Done']]");
        if (isElementExist("//div[contains(@class, 'item__error')]")) {
            System.out.println("error message " + XH("//div[contains(@class, 'item__error')]").getText() + " ==============================");
        }
    }

    public void clickBtnSaveChanges(String zoneName) {
        waitUntilElementVisible(xPathBtn("", "Save changes", 1), 5);
        clickBtn("Save changes");
//        String msg = (String.format(msgSuccess, zoneName, zoneName));
//        assertThat(XH(msg).isVisible()).isTrue();
        waitElementToBeVisible("//form[@class='s-form']//div[@class='row']");
    }

    public void searchCountries(String sCountry) {
        waitElementToBeVisible("//div[contains(@class,'form-item')]//input[@placeholder='Search countries']");
        enterInputFieldWithLabel("Search countries", sCountry);
    }

    public void chooseEditShippingZone(String zoneName) {
        clickOnElement("//div[child::div[@class='flex']//div[text()='" + zoneName + "']]//a[text()[normalize-space()='Edit']]");
    }

    public void clickDeleteZone(boolean confirmDelete) {
        clickOnElement("//span[text()[normalize-space()='Delete zone']]");
        if (confirmDelete) {
            clickOnElement("//span[text()[normalize-space()='Delete shipping zone']]");
        } else {
            clickOnElement("//div[child::div//div[contains(text(), 'Your shipping and tax rates will be deleted for this zone')]]//span[text()='Cancel']");
        }
    }

    public boolean isShippingZoneExisted(String zoneName) {
        return isElementExist("//div[contains(@class, 'group-title') and text()='" + zoneName + "']", 8);
    }

    public void chooseCountries(String sCountry) {
        checkCheckboxWithLabel(sCountry);
    }

    public void clickBtnAdd() {
        clickLinkTextWithLabel("Add");
    }

    public void enterRateName(String rateName) {
        waitUntilElementVisible(xPathInputFieldWithLabel("", "Name", 1), 5);
        enterInputFieldWithLabel("Name", rateName);
    }


    public void enterRateNameVer2(String rateName) {
        waitUntilElementVisible(xPathInputFieldWithLabel("", "RATE NAME", 1), 5);
        enterInputFieldWithLabel("RATE NAME", rateName);
    }

    public void enterGroupName(String sGroupName) {
        waitABit(1000);
        enterInputFieldWithLabel("Group", sGroupName);
    }

    public void enterRateMin(String rateMin) {
        if (!rateMin.isEmpty()) {
            enterInputFieldWithLabel("Minimum order price", rateMin);
        }
    }

    public void enterRateMax(String rateMax) {
        if (!rateMax.isEmpty()) {
            enterInputFieldWithLabel("Maximum order price", rateMax);
        }
    }

    public void enterMinOrderWeight(String minWeight) {
        if (!minWeight.isEmpty()) {
            enterInputFieldWithLabel("Minimum order weight", minWeight);
        }
    }

    public void enterMaxOrderWeight(String maxWeight) {
        if (!maxWeight.isEmpty()) {
            enterInputFieldWithLabel("Maximum order weight", maxWeight);
        }
    }

    public void chooseFreeShippingRate(boolean isChecked) {
        checkCheckboxWithLabel("Free shipping rate", 1, isChecked);
    }

    public void enterRateAmount(String rateAmount) {
        if (!rateAmount.isEmpty()) {
            waitTypeOnElement("(//label[text()='Rate amount']/following::input)[1]", rateAmount);
        }
    }

    public void deleteAllShippingZone() {
        String editBtn = "(//div[contains(@class,'general-group')]//a[text()[normalize-space()='Edit']])[1]";
        while (isElementExist(editBtn)) {
            clickOnElement(editBtn);
            clickDeleteZone(true);
            if (!isElementExist(editBtn)) {
                break;
            }
        }
    }

    public boolean isShippingZoneDetailPageDisplayed(String shippingZoneName) {
        String xPath = "//h1[text()='" + shippingZoneName + "' or text()='Add shipping zone']";
        return isElementExist(xPath);
    }

    public void clickAddCondition(String blockName) {
        clickOnElement("(//span[text()[normalize-space()='" + blockName + "']]/following::span[text()[normalize-space()='Add condition']])[1]");
    }

    public void chooseConditionItem(String blockName, String sConditionItem) {
        selectDdlByXpath("(//span[text()[normalize-space()='" + blockName + "']]/following::div[child::p[text()='Products must match:']]//select)[1]", sConditionItem);
    }

    public void chooseCondition(String blockName, String sCondition) {
        selectDdlByXpath("(//span[text()[normalize-space()='" + blockName + "']]/following::div[child::p[text()='Products must match:']]//select)[2]", sCondition);
    }

    public void enterConditionKeywords(String keywords) {
        enterInputFieldWithLabel("Enter keywords", keywords);
    }

    public void enterFirstItemShippingPrice(String firstItem) {
        String xPath = "//div[child::div/label[text()='First item']]//input";
        waitClearAndType(xPath, firstItem);
    }

    public void enterEachAdditionalItemShippingPrice(String sAdditionalItem) {
        String xPath = "//div[child::div/label[text()='Each additional item']]//input";
        waitClearAndType(xPath, sAdditionalItem);
    }

    public boolean isRateExist(String rateType, String itemRate) {
        return isElementExist("//div[child::div/b[text()='" + rateType + "']]//span[text()='" + itemRate + "']");
    }

    public void clickDeleteShippingRate(String rateType, String rateName) {
        clickOnElement("//div[child::div/b[text()='" + rateType + "']]//tr[child::td//span[text()='" + rateName + "']]" +
                "//span[contains(@class, 'cursor-pointer') or text()[normalize-space()='×']]");
    }

    public void clickManageRatesLinkText(String profileType) {
        clickOnElement("(//h2[text()[normalize-space()='" + profileType + "']]/following::a[normalize-space()='Manage rates'])[1]");
    }


    /* shipping profile ver 2
     */

    public void clickBtnAddRateForZone(String zoneName) {
        clickOnElement("(//*[normalize-space()='" + zoneName + "']/following::button//span[text()[normalize-space()='Add rate']])[1]");
    }

    public void deleteShippingZones() {
        String editIcon = "(//div[contains(@class,'zone-name')]/following-sibling::div//i)[%d]";
        String zone = "//div[contains(@class,'zone-name')]/following-sibling::div//i";
        int numberOfZones = countElementByXpath(zone);
        String deleteZoneBtn = "(//section//div[@class='s-dropdown-content']//span[@class='s-dropdown-item']//span[normalize-space()='Delete Zone'])[1]";

        if (isElementExist(zone)) {
            for (int i = 1; i <= numberOfZones; i++) {
                clickOnElement(String.format(editIcon, i));
                if (isElementExist(deleteZoneBtn)) {
                    clickOnElement(deleteZoneBtn);
                    clickBtn("Save changes");
                }
            }
        }

    }

    public void clickBtnCreateShippingZone() {
        String btn = "(//a[normalize-space()='Create Shipping Zone'])[1]";
        waitUntilElementVisible(btn, 7);
        clickOnElement(btn);
    }

    public int countCustomProfile() {
        return countElementByXpath("//*[normalize-space()='Custom Profile']/following::a[contains(@href,'admin/settings/shipping-profile') and normalize-space()='Manage rates']");
    }

    public void deleteAllShippingProfiles() {
        String manageRate = "//*[normalize-space()='Custom Profile']/following::a[contains(@href,'admin/settings/shipping-profile') and normalize-space()='Manage rates']";
        int numberOfProfile = countElementByXpath(manageRate);
        if (isElementExist(manageRate)) {
            for (int i = 1; i <= numberOfProfile; i++) {
                clickOnElement(manageRate);
                clickBtn("Delete Profile", 1);
                clickBtn("Delete Profile", 2);
                waitForEverythingComplete(60);
            }
        }
    }

    public void waitUntilUpdateProfileSuccessfully(String profileName) {
        verifyElementVisible(String.format(msgSuccess, profileName, profileName), true);
    }

    public void chooseConditionItem(String block, String sConditionItem, int row) {
        int index = row * 2;
        selectDdlByXpath("(//*[contains(text(),'" + block + "')]/following::div[@class='row'][" + index + "]//select)[1]", sConditionItem);
    }

    public void chooseCondition(String block, String sCondition, int row) {
        int index = row * 2;
        selectDdlByXpath("(//*[contains(text(),'" + block + "')]/following::div[@class='row'][" + index + "]//select)[2]", sCondition);
    }

    public void enterConditionKeywords(String block, String sKeywords, int row) {
        int index = row * 2;
        waitTypeOnElement("//*[contains(text(),'" + block + "')]/following::div[@class='row'][" + index + "]//input", sKeywords);
    }

    public void clickAddConditionCheckbox() {
        String xPathStatus = "//input[contains(@type,'checkbox') and following-sibling::*[normalize-space()='Add condition']]";
        String xPathCheckbox = "//span[contains(@class,'check') and following-sibling::*[normalize-space()='Add condition']]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, true);
    }

    public void verifyShipping() {
        String xpath = "//div[@class='flex justify-content-space-between m-b-xs general-group']";
        assertThat(countElementByXpath(xpath)).isGreaterThan(1);
    }

    public boolean isDisableSection(String shipping_zone) {
        String xPath = "//div[contains(@class,'mt16') and descendant::*[normalize-space()='" +shipping_zone+ "']]//*[@class='disable-section']";
        if (isElementExist(xPath)){
            return true;
        } else {
            return false;
        }
    }

    public void clickOnCheckBoxFreeShippingRate(String shipping_zone) {
        String xPath = "//div[contains(@class,'mt16') and descendant::*[normalize-space()='" +shipping_zone+ "']]//span[contains(@class,'check')]";
        clickOnElement(xPath);
    }

    public String getStatusOfSettingRateBlock(String shipping_zone) {
        if (isDisableSection(shipping_zone)){
            return "inactive";
        } else {
            return "active";
        }
    }

    public void inputValueForFreeShippingOfZone(String shipping_zone, String shipping_value) {
        String xPath = "//div[contains(@class,'mt16') and descendant::*[normalize-space()='" +shipping_zone+ "']]//input[contains(@type,'number')]";
        clearAndInputTextByJS(xPath, shipping_value);
    }

    public String getValueOfFreeShippingZone(String shipping_zone) {
        String xPath = "//div[contains(@class,'mt16') and descendant::*[normalize-space()='" +shipping_zone+ "']]//input[contains(@type,'number')]";
        String value = getValue(xPath);
        return value;
    }

    public void inputShippingTime(String shippingTimeFrom, String shippingTimeTo) {
        String xpathFrom = "//div[normalize-space()='Shipping Time']/following::input[1]";
        String xpathTo = "//div[normalize-space()='Shipping Time']/following::input[2]";
        if(isElementExist(xpathFrom) && isElementExist(xpathTo)) {
            enterInputFieldWithLabel("From",shippingTimeFrom);
            enterInputFieldWithLabel("To", shippingTimeTo);
        }
    }

    public void veryfyShippingCountry() {
        String xpath = "//span[@class='eta-shipping-time__shipping-country']";
        if (isElementExist(xpath)) {
            assertThat(getText(xpath)).isEqualTo("(delivery to United States)");
        }
    }

    public void verifyDeliveryTime(String page) {
        String productPage = "//span[contains(@class,'eta-shipping-time__shipping-time')]";
        String checkoutPage = "//div[@class='review-block__shipping_time']";
        String orderSttPage = "//h3[normalize-space()='Estimate Delivery Time']/following-sibling::p";
        DateFormat df = new SimpleDateFormat("MMMM dd");
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.add(Calendar.DAY_OF_MONTH,Integer.parseInt(shipFrom));
        cal2.add(Calendar.DAY_OF_MONTH,Integer.parseInt(shipTo));
        String shippingFrom = df.format(cal1.getTime());
        String shippingTo = df.format(cal2.getTime());

        switch (page) {
            case "product page":
                if(shipFrom.equals(shipTo)) {
                    System.out.println("ETA Delivery Time: " + shippingFrom);
                    assertThat(getText(productPage)).isEqualTo(shippingFrom);
                }

                if(shipFrom.isEmpty()) {
                    System.out.println("ETA Delivery Time: " + shippingTo);
                    assertThat(getText(productPage)).isEqualTo(shippingTo);
                }

                if(shipTo.isEmpty()) {
                    System.out.println("ETA Delivery Time: " + shippingFrom);
                    assertThat(getText(productPage)).isEqualTo(shippingFrom);
                }

                if(!shipFrom.isEmpty() && !shipTo.isEmpty() && (Integer.parseInt(shipFrom) < Integer.parseInt(shipTo))) {
                    if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                        if (cal2.get(Calendar.DATE) < 10) {
                            System.out.println("ETA Delivery Time: " + shippingFrom + " - 0" + cal2.get(Calendar.DATE));
                            assertThat(getText(productPage)).isEqualTo(shippingFrom + " - 0" + cal2.get(Calendar.DATE));
                        } else {
                            System.out.println("ETA Delivery Time: " + shippingFrom + " - " + cal2.get(Calendar.DATE));
                            assertThat(getText(productPage)).isEqualTo(shippingFrom + " - " + cal2.get(Calendar.DATE));
                        }
                    } else {
                        System.out.println("ETA Delivery Time: " + shippingFrom + " - " + shippingTo);
                        assertThat(getText(productPage)).isEqualTo(shippingFrom + " - " + shippingTo);
                    }
                }
                veryfyShippingCountry();
                break;
            case "checkout page":
                if (shipFrom.equals(shipTo)) {
                    System.out.println("ETA Delivery Time: " + shippingFrom);
                    assertThat(getText(checkoutPage)).isEqualTo(shippingFrom);
                }

                if (shipFrom.isEmpty()) {
                    System.out.println("ETA Delivery Time: " + shippingTo);
                    assertThat(getText(checkoutPage)).isEqualTo(shippingTo);
                }

                if (shipTo.isEmpty()) {
                    System.out.println("ETA Delivery Time: " + shippingFrom);
                    assertThat(getText(checkoutPage)).isEqualTo(shippingFrom);
                }

                if (!shipFrom.isEmpty() && !shipTo.isEmpty() && (Integer.parseInt(shipFrom) < Integer.parseInt(shipTo))) {
                    if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                        if (cal2.get(Calendar.DATE) < 10) {
                            System.out.println("Estimated delivery: " + shippingFrom + " - 0" + cal2.get(Calendar.DATE));
                            assertThat(getText(checkoutPage)).isEqualTo("Estimated delivery: " + shippingFrom + " - 0" + cal2.get(Calendar.DATE));
                        } else {
                            System.out.println("Estimated delivery: " + shippingFrom + " - " + cal2.get(Calendar.DATE));
                            assertThat(getText(checkoutPage)).isEqualTo("Estimated delivery: " + shippingFrom + " - " + cal2.get(Calendar.DATE));
                        }
                    } else {
                        System.out.println("Estimated delivery: " + shippingFrom + " - " + shippingTo);
                        assertThat(getText(checkoutPage)).isEqualTo("Estimated delivery: " + shippingFrom + " - " + shippingTo);
                    }
                }
                break;
            case "order status page":
                if(shipFrom.equals(shipTo)) {
                    System.out.println("ETA Delivery Time: " + shippingFrom + ", " + cal1.get(Calendar.YEAR));
                    assertThat(getText(orderSttPage)).isEqualTo(shippingFrom + ", " + cal1.get(Calendar.YEAR));
                }

                if(shipFrom.isEmpty()) {
                    System.out.println("ETA Delivery Time: " + shippingTo + ", " + cal2.get(Calendar.YEAR));
                    assertThat(getText(orderSttPage)).isEqualTo(shippingTo + ", " + cal2.get(Calendar.YEAR));
                }

                if(shipTo.isEmpty()) {
                    System.out.println("ETA Delivery Time: " + shippingFrom + ", " + cal1.get(Calendar.YEAR));
                    assertThat(getText(orderSttPage)).isEqualTo(shippingFrom + ", " + cal1.get(Calendar.YEAR));
                }

                if(!shipFrom.isEmpty() && !shipTo.isEmpty() && (Integer.parseInt(shipFrom) < Integer.parseInt(shipTo))) {
                    if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                        if (cal2.get(Calendar.DATE) < 10) {
                            System.out.println("Estimated delivery: " + shippingFrom + " - 0" + cal2.get(Calendar.DATE) + ", " + cal2.get(Calendar.YEAR));
                            assertThat(getText(orderSttPage)).isEqualTo(shippingFrom + " - 0" + cal2.get(Calendar.DATE) + ", " + cal2.get(Calendar.YEAR));
                        } else {
                            System.out.println("Estimated delivery: " + shippingFrom + " - " + cal2.get(Calendar.DATE) + ", " + cal2.get(Calendar.YEAR));
                            assertThat(getText(orderSttPage)).isEqualTo(shippingFrom + " - " + cal2.get(Calendar.DATE) + ", " + cal2.get(Calendar.YEAR));
                        }
                    } else {
                        System.out.println("Estimated delivery: " + shippingFrom + " - " + shippingTo + ", " + cal2.get(Calendar.YEAR));
                        assertThat(getText(orderSttPage)).isEqualTo(shippingFrom + " - " + shippingTo + ", " + cal2.get(Calendar.YEAR));
                    }
                }
                break;
        }
    }
}
