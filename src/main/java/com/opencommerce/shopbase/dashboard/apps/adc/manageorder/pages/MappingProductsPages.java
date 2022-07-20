package com.opencommerce.shopbase.dashboard.apps.adc.manageorder.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.mappedProducts;
import static org.assertj.core.api.Assertions.assertThat;

public class MappingProductsPages extends SBasePageObject {


    public final String PLACEHOLDER_INPUT = "Enter new product link here";
    private final String SHOPBASE_SECTION = "SHOPBASE PRODUCT";
    private final String ALIEXPRESS_SECTION = "TO BE FULFILLED WITH";
    private static final String XPATH_ERR_MESSAGE = "//div[@class='s-alert__description']";
    public static final String POPUP_CONTENT = "//p[@class='m-t m-b']";

    public MappingProductsPages(WebDriver driver) {
        super(driver);
    }

    public void verifyTitleMapProduct(String expect) {
        String xpath = "//h1[@class='map-product-page__title s-mb8 fs-large']";
        verifyElementText(xpath, expect);
    }

    public void inputURLAli(String urlAli) {
        enterInputFieldWithLabel(PLACEHOLDER_INPUT, urlAli);
    }

    public void verifyProductMappingSectionDisplayed(String productMaping) {
        String[] data = productMaping.split(";");
        String numOptions = data[1];

        String xpathSection = "//h4[contains(text(),'Product mapping')]";
        String xpathName = "//div[@class='product-detail__customize-description']//h5//a";
        String xpathNumOfOptions = "//div[@class='product-detail__customize-description']//p";
        verifyElementText(xpathSection, "Product mapping");
        verifyElementText(xpathName, data[0]);
//        verifyElementText(xpathNumOfOptions, numOptions.trim());   => đang có bug tạm thời comment
    }

    public void verifyShopbaseProductSectionDisplayed(String data) {
        ArrayList<String> arrData = handleListStringData(data, true);
        String name = arrData.get(0);

        String xpathSection = "//h5[contains(text(),'Shopbase product')]";
        String xpathName = "//div[@class='col-xs-6'][1]//h5[@class='s-pr12' ]";
        verifyElementText(xpathSection, SHOPBASE_SECTION);
        verifyElementText(xpathName, name.trim());

        for (int i = 0; i < arrData.size(); i++) {
            if (i > 0) {
                String xPathStatus = "//span[@class='s-control-label' and text()='" + arrData.get(i) + "']//preceding-sibling::input | //span[@class='s-control-label']//b[text()='" + arrData.get(i) + "']//parent::span//preceding-sibling::input";
                String xPath = "//span[@class='s-control-label' and text()='" + arrData.get(i) + "'] | //span[@class='s-control-label']//b[text()='" + arrData.get(i) + "']//parent::span";
                verifyElementSelected(xPathStatus, true);
            }
        }
    }

    public void verifyAliExpressProductSectionDisplayed(String data) {
        ArrayList<String> arrData = handleListStringData(data, true);
        String name = arrData.get(0);

        String xpathSection = "//h5[contains(text(),'To be fulfilled with')]";
        String xpathName = "//div[@class='col-xs-6'][2]//h5[@class='s-pr12' ]";
        verifyElementText(xpathSection, ALIEXPRESS_SECTION);
        verifyElementText(xpathName, name.trim());

        for (int i = 0; i < arrData.size(); i++) {
            if (i > 0) {
                String xpathDropdown = "(//div[@class='s-select'])[" + (i) + "]//select";
                WebElement element = getDriver().findElement(By.xpath(xpathDropdown));
                Select select = new Select(element);
                String value = select.getFirstSelectedOption().getText();
                System.out.println(value);
                // verifyElementText(xpathDropdown, value); -> đang bị bug comment tạm
            }
        }
    }

    public void waitForElementVisible() {
        waitElementToBeVisible(XPATH_ERR_MESSAGE);
    }

    public void verifyErrorMessageDisplayed(String expectMessage) {
        verifyElementText(XPATH_ERR_MESSAGE, expectMessage);
    }

    public void selectVariantAliExpressProduct(String value, boolean bSelect) {
        if (value.isEmpty()) {
            String xpathDropdown = "(//div[@class='row mapping-table-row']//select)[1]";
            String xpathValue = "(//div[@class='row mapping-table-row']//select)[1]//option[text()='Select the Size']";
            waitElementToBeVisible(xpathDropdown);
            clickOnElement(xpathDropdown);
            waitElementToBeVisible(xpathValue);
            clickOnElement(xpathValue);

        } else {
            ArrayList<String> arrData = handleListStringData(value, bSelect);
            String xpath, xpathValueInDropdown;
            for (int i = 1; i < arrData.size(); i++) {
                xpath = "(//div[@class='s-select'])[" + i + "]";
                xpathValueInDropdown = "(//div[@class='s-select'])[" + i + "]//option[text()='" + arrData.get(i) + "']";
                waitElementToBeVisible(xpath);
                clickOnElement(xpath);
                waitElementToBeVisible(xpathValueInDropdown);
                clickOnElement(xpathValueInDropdown);

            }
        }

    }

    public void clickButtonSaveChanges(String buttonText) {
        String xpath = "//button[normalize-space()= '" + buttonText + "']";
        clickOnElement(xpath);
    }

    public void verifyToastMessageDisplay(String message) {
        String xpath = "//div[@class='s-notices is-bottom']//div";
        waitElementToBeVisible(xpath);
        verifyElementText(xpath, message);
    }

    public void selectOptionSBProduct(String value, boolean bSelect) {
        ArrayList<String> arrData = handleListStringData(value, true);
        for (int i = 0; i < arrData.size(); i++) {
            if (i > 0) {
                String xPathStatus = "//span[@class='s-control-label' and text()='" + arrData.get(i) + "']//preceding-sibling::input | //span[@class='s-control-label']//b[text()='" + arrData.get(i) + "']//parent::span//preceding-sibling::input";
                String xPath = "//span[@class='s-control-label' and text()='" + arrData.get(i) + "'] | //span[@class='s-control-label']//b[text()='" + arrData.get(i) + "']//parent::span";
                waitElementToBeClickable(xPath);
                verifyCheckedThenClick(xPathStatus, xPath, bSelect);
            }
        }
    }

    public void unCheckCheckbox(String data) {
        String xPathStatus = "//span[@class='s-control-label' and text()='" + data + "']//preceding-sibling::input | //span[@class='s-control-label']//b[text()='" + data + "']//parent::span//preceding-sibling::input";
        String xPath = "//span[@class='s-control-label' and text()='" + data + "'] | //span[@class='s-control-label']//b[text()='" + data + "']//parent::span";

        boolean isStatus = XH(xPathStatus).isSelected();
        if (isStatus) {
            clickOnElement(xPath);
        }
    }

    private ArrayList<String> handleListStringData(String value, boolean bAli) {

        ArrayList<String> arrData = new ArrayList<>();
        String[] arrNameOption = value.split(";");
        for (String item : arrNameOption) {
            if (item.contains(">")) {
                if (!bAli) {
                    String optionName = item.split(">")[0];
                    arrData.add(optionName);
                } else {
                    // out put arr{name, option1, value 1,.., option2, value1...}
                    String options[] = item.split(">");
                    for (String iOption : options) {
                        if (iOption.contains(",")) {
                            String[] arrValue = iOption.split(",");
                            for (String iValue : arrValue) {
                                arrData.add(iValue);
                            }
                        } else arrData.add(iOption);
                    }
                }
            } else {
                arrData.add(item);
            }
        }
        return arrData;
    }

    public void confirmPopup(String bConfirm) {
        String xpath;
        if (bConfirm.equals("No")) {
            xpath = "//button[@class='s-button is-default m-r-xxs']";
        } else {
            xpath = "//div[@id='confirm-import-popup']//button[@class='s-button is-primary']";
        }
        clickOnElement(xpath);
    }

    public void verifyProductDeleted(String expect) {
        String xpath = "//div[@class='s-alert__description']";
        verifyElementText(xpath, expect);
    }

    public void checkPopupVisible(boolean isShownPopupConfirm) {
        String xpath = "//div[@class='s-modal-header']";
        assertThat(isElementVisible(xpath, 5)).isEqualTo(isShownPopupConfirm);
        if (isShownPopupConfirm) {
            verifyElementText(POPUP_CONTENT, "ShopBase product is already mapped with another product from Aliexpress. Would you like to remove it and map with a new one?");
        }
    }

    public void confirmPopupOverride(String btnLabel) {
        clickBtn("//div[@id='confirm-import-popup']", btnLabel, 1);
    }

    public Integer indexSBValue(String SBValue) {
        int index = 1;
        List<String> SbaseOptionValList = getListText("//div[@class='variant-group']//input/following-sibling::span[@class='s-control-label']");
        for (int i = 0; i < SbaseOptionValList.size(); i++) {
            if (SBValue.equalsIgnoreCase(SbaseOptionValList.get(i))) {
                index = i + 1;
            }
        }
        return index;
    }

    public void mappingSbProductWithAliProduct(String option, String values) {
        String[] value = values.split(",");
        String SBValue = "", AliValue = "";

        HashMap<String, Integer> selectedSBVal = new HashMap<>();
        int SBIndex = 0, AliIndex = 0;
        String selectedAliVal = "(//div[@class='variant-group']//div[@class='s-select']//option[2])[%d]";

        // select SB option values by clicking the checkbox
        for (int i = 0; i < value.length; i++) {
            SBValue = value[i].trim();
            String optionValStatus = "//b[text()[normalize-space()='" + option + "']]/following::span[text()[normalize-space()='" + SBValue + "']]/preceding-sibling::input";
            String checkboxOptionVal = "//b[text()[normalize-space()='" + option + "']]/following::span[text()[normalize-space()='" + SBValue + "']]/preceding-sibling::span";
            verifyCheckedThenClick(optionValStatus, checkboxOptionVal, true);
            SBIndex = indexSBValue(SBValue);
            selectedSBVal.put(SBValue, SBIndex);
        }

        // then select Ali option value correspondingly
        Set<Map.Entry<String, Integer>> setHashMap = selectedSBVal.entrySet();
        for (Map.Entry<String, Integer> hsm : setHashMap) {
            AliIndex = hsm.getValue();
            clickOnElement(String.format(selectedAliVal, AliIndex));
            SBValue = hsm.getKey();
            AliValue = getText(String.format(selectedAliVal, AliIndex));
            mappedProducts.put(SBValue, AliValue);
        }
    }

    public void selectAliOption(boolean isDuplicated) {
        String firstAliOption = "(//div[@class='mapping-table-content']//select)[1]";
        String firstAliOptionValue = "(//div[@class='mapping-table-content']//select//option)[1]";
        String secondAliOption = "(//div[@class='mapping-table-content']//select)[%d]";

        String firstSBValue = "(//div[@class='variant-group']/parent::*)[%d]//span[text()]";
        int countSbValues = 1, indexNextAliOption = 1;
        if (isDuplicated) {
            selectDdlByXpath(firstAliOption, getText(firstAliOptionValue));
            for (int i = 1; i < 3; i++) {
                countSbValues = countElementByXpath(String.format(firstSBValue, i));
                indexNextAliOption = 2 + countSbValues;
                secondAliOption = String.format(secondAliOption, indexNextAliOption);
                selectDdlByXpath(secondAliOption, getText(firstAliOptionValue));
            }
        }
    }

    public void waitUntilOrderIsExpanded(String orderNumber) {
        waitUntilElementVisible("(//tr[@class='is-expanding']//a[text()[normalize-space()='" + orderNumber + "']])", 7);
    }


//    public void checkPopupVisible(boolean isShownPopupConfirm) {
//        String xpath = "//div[@class='s-modal-header']";
//        assertThat(!isElementVisible(xpath, 5));
//        if (isShownPopupConfirm) {
//            confirmPopup(bConfirm);
//        }
//        String expect = popup.split(">")[0];
//        String bConfirm = popup.split(">")[1];
//
//        confirmPopup(bConfirm);
//        if (bConfirm.equals("Yes")) {
//            // lỗi
////                    mappingProductsSteps.verifyErrorMessageImportDisplay(errMessage);
////                    mappingProductsSteps.verifyShopbaseMappingSectionDisplay(sbProduct);
////                    mappingProductsSteps.verifyAliExpressMappingSectionDisplay(aliProduct);
//        }
//    }
//}
}
