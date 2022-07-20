package com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign.MyCampaignPage.downloadWebPage;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class EditorCampaignPage extends SBasePageObject {
    public EditorCampaignPage(WebDriver driver) {
        super(driver);
    }

    public void verifyLinkProduct(boolean autoLink) {
        int n = countElementByXpath("//li[contains(@class,'s-px16 s-pt16')]");
        for (int i = 1; i <= n; i++) {
            String xpath = "//li[contains(@class,'s-px16 s-pt16')][" + i + "]//a[1]//following-sibling::a[@class='link-product'][1]";
            verifyElementVisible(xpath, autoLink);
        }
    }

    String xpath_base_index = "//li[contains(@class,'s-px16 s-pt16')][%d]//a[1]";
    String _xpathSide = "//span[contains(.,'%s')]/ancestor::div[contains(@class,'s-collapse-item artwork')]";
    String _xpathLayerOnSide = "//div[@class='layer__list']";
    // Layer PSD Detail
    String _xpathListLayer = "//div[@class='layer__list p-n']";

    public int countBaseProduct() {
        int n = countElementByXpath("//li[contains(@class,'s-px16 s-pt16')]");
        return n;
    }

    public void clickBaseProduct(String productName) {
        String xpath = "//span[normalize-space()='" + productName + "']//preceding-sibling::a[contains(@class,'img-thumbnail')]";
        clickOnElementByJs(xpath);
    }

    public void clickBaseProduct(Integer i) {
        String xpath = String.format(xpath_base_index, i);
        clickOnElementByJs(xpath);
    }

    public String getProductBase(int i) {
        String xpath = String.format(xpath_base_index, i);
        changeStyle(xpath + "/following-sibling::span");
        String product = getText(xpath + "/following-sibling::span");
        return product;
    }

    public void clickBaseProductFirst() {
        String xpath = "//li[contains(@class,'s-px16 s-pt16')]";
        clickOnElementByJs(xpath);
    }

    public void deleteBaseProduct(String productName) {
        String _xpathDelete = "//ul[contains(@class,'list-inline')]//li[descendant::*[normalize-space()='" + productName + "']]//a[@class='s-delete']";
        String _xpathProduct = "//ul[contains(@class,'list-inline')]//li[descendant::*[normalize-space()='" + productName + "']]//img";
        hoverThenClickElement(_xpathProduct, _xpathDelete);
    }

    public void verifyColor() {
        String xpath = "//label[child::b[text()='color:']]//following-sibling::ul[@class='list-inline']//a";
        int colorNumber = countElementByXpath(xpath);
        assertThat(countColorChoosed()).isEqualTo(colorNumber);
    }

    public void hoverColor(String color) {
        String xpathColor = "//span[@data-label='" + color + "']//div";
        waitUntilInvisibleLoading(5);
        hoverOnElement(xpathColor);
        waitUntilInvisibleLoading(2);
    }

    public void clickAddColor() {
        String xpath = "//div[@class='color']//i[@class='mdi mdi-plus mdi-18px']";
        clickOnElementByJs(xpath);
    }

    public void selectAllColor() {
        String xpath_input = "//div[@class='s-dropdown-content']//input";
        String xpath = "//div[@class='s-dropdown-content']//span[@class='s-check is-primary']";
        verifyCheckedThenClick(xpath_input, xpath, true);
    }

    public void verifyMessageTotalVariant(String message) {
        String xpath = "//div[@class='s-alert__content']//span";
        assertThat(getText(xpath)).isEqualTo(message);
    }

    public int countColorChoosed() {
        String xpath = "//div[@class='color__active']";
        return countElementByXpath(xpath);
    }

    public void clickaddMoreProduct() {
        String xpath = "//button[contains(@class,'s-button add-base-product')]";
        waitElementToBeVisible(xpath, 50);
        clickOnElement(xpath);
    }

    public void verifyBaseProduct(String productName, boolean addedOrRemoved) {
        String xpath = "//li[@class='s-px16 s-pt16']//span[@data-label='" + productName + "']";
        verifyElementPresent(xpath, addedOrRemoved);
    }

    public void addArtwork(String imageName) {
        String _xpath_popup = "//div[@class='s-modal is-active phub-popup-catalog']";
        String xpath_img = "//div[@class='title' and child::span[@data-label='" + imageName + "']]//preceding-sibling::div";
        waitUntilElementVisible(_xpath_popup, 10);
        hoverThenClickElement(_xpath_popup, xpath_img);
    }

    public void inputTextFont(String font) {
        String xpath = "//input[@id='font-list-undefined'] | //input[@id='font-list-layer-font']";
        waitUntilElementVisible(xpath, 50);
        waitClearAndTypeThenEnter(xpath, font);
    }

    public void inputTitleLayerText(String layerName) {
        String xpath = "//input[@placeholder='Add your text']";
        waitUntilElementVisible(xpath, 50);
        waitClearAndTypeThenEnter(xpath, layerName);
        clickOnElement("//div[@class='s-card-header']");
    }

    public void addLayer(String frontOrBack, String layer) {
        String _xpathSide = String.format(this._xpathSide, frontOrBack);
        String _xpathAddNew = "//button[child::span[normalize-space()='" + layer + "']]";

        if (frontOrBack.equalsIgnoreCase("Front") | frontOrBack.equalsIgnoreCase("Back")) {
            String _sActive = getAttributeValue(_xpathSide, "class");
            if (!_sActive.contains("is-active")) {
                waitElementToBeClickable(_xpathSide);
                clickOnElement(_xpathSide);
            }
            clickOnElementByJs(_xpathSide + _xpathAddNew);
        }
        // base only one side
        else {
            clickOnElement(_xpathAddNew);
        }
    }

    public void verifyLayer(String layer, String _sSide, boolean display) {
        String _xpath = String.format(_xpathSide, _sSide);
        if (_sSide.equalsIgnoreCase("Front") | _sSide.equalsIgnoreCase("Back")) {
            String _sActive = getAttributeValue(_xpath, "class");
            if (!_sActive.contains("is-active"))
                clickOnElement(_xpath);
            String xpath = _xpath + "//span[normalize-space()='" + layer + "']";
            assertThat(isElementExist(xpath)).isEqualTo(display);
        } else {
            String xpath = _xpathLayerOnSide + "//span[normalize-space()='" + layer + "']";
            assertThat(isElementExist(xpath)).isEqualTo(display);
        }
    }

    public void inputFontSize(String _size) {
        String _xpathFontSize = "//div[@class='text-form-data-custom']//div[@class='row']//input";
        waitClearAndTypeThenTab(_xpathFontSize, _size);
    }

    public void verifyFontSize(String size) {
        String xpath = "//div[contains(@class,'text-form-data-custom')]//div[contains(@class,'row')]//input";
        String fontSize = getTextValue(xpath);
        assertThat(fontSize).isEqualTo(size);
    }

    public void verifySizeWidthHeight(String sizeWidthHeight) {
        String[] sizeList = sizeWidthHeight.split(">");
        String xpath = "//div[@class='s-input s-input--prefix' and child::span[normalize-space()='" + sizeList[0] + "']]//input";
        verifyElementText(xpath, sizeList[1]);
    }

    public void clickSetIndividualPriceInPricing(String _sProduct) {
        String _xpath = "//p[contains(.,\"" + _sProduct + "\")]/following-sibling::a[contains(text(),'Set individual price')]";
        if (isElementExist(_xpath))
            clickOnElement(_xpath);
    }

    String xpath_price = "(//tr[child::td[contains(text(),'%s')]]//input)[%d]";

    public void inputSalePrice(String variant, String sSalePrice) {
        String xpath = String.format(xpath_price, variant, 1);
        waitClearAndTypeThenTab(xpath, sSalePrice);
    }

    public void inputComparePrice(String variant, String comparePrice) {
        String xpath = String.format(xpath_price, variant, 2);
        waitClearAndTypeThenTab(xpath, comparePrice);
    }

    public void inputPricePricingTab(String variant, String price, int index) {
        String xpath = String.format(xpath_price, variant, index);
        for (int i = 0; i < 4; i++) {
            if (!checkPricePricingTab(xpath, price))
                waitClearAndTypeThenTab(xpath, price);
            else
                break;
        }
    }

    public boolean checkPricePricingTab(String xpath, String price) {
        boolean is = false;
        String priceActual = String.valueOf(price(getTextValue(xpath)).toString());
        Double price_ex = Math.round(Double.parseDouble(price) * 100.00) / 100.00;
        Double price_ac = Double.parseDouble(priceActual);
        if (price_ac.equals(price_ex))
            return true;
        return false;
    }

    public boolean checkPricePricingTab(String variant, String price, int i) {
        String xpath = String.format(xpath_price, variant, i);
        String priceActual = String.valueOf(price(getTextValue(xpath)));

        if (priceActual.equals(price))
            return true;
        return false;
    }

    public boolean isShowWarningInPricing() {
        return isElementExist("//div[@class='s-alert s-mb24 is-red']", 30);
    }

    public boolean isInVisibleShowWarningInPricing() {
        return waitUntilElementInvisible("//div[@class='s-alert s-mb24 is-red']", 30);
    }

    public void clickLauchButton() {
        String xpath = "//button[contains(.,'Launch')]";
        clickOnElement(xpath);
        try {
            waitABit(3);
            waitUntilElementInvisible(xpath, 5);
        } catch (Exception e) {
            if (isElementExist(xpath, 2)) {
                refreshPage();
                clickOnElement(xpath);
            }
        }
    }

    public void verifyVariantPricing(String sVariant, Boolean isShowVariant) {
        String xpath = "//tr[child::td[contains(text(),'" + sVariant + "')]]";
        assertThat(isElementExist(xpath)).isEqualTo(isShowVariant);
    }

    public void verifyCostPricing(String product, String cost) {
        String xpath = "//td[child::p[normalize-space()='" + product + "']]//following-sibling::td[@class='text-right text-bold']";
        assertThat(getText(xpath)).isEqualTo(cost);
    }

    public void verifyPrice(String product, String price) {
        int index = countElementByXpath("//th[descendant-or-self::*[text()[normalize-space()='Sale price']]]//preceding-sibling::th");
        String xpath = "//td[child::p[normalize-space()='" + product + "']]//following-sibling::td[" + index + "]//input";
        assertThat(getValue(xpath)).isEqualTo(price);
    }

    public void verifyCompareAtPrice(String product, String compareAtprice) {
        int index = countElementByXpath("//th[descendant-or-self::*[text()[normalize-space()='Compare at price']]]//preceding-sibling::th");
        String xpath = "//td[child::p[normalize-space()='" + product + "']]//following-sibling::td[" + index + "]//input";
        assertThat(getValue(xpath)).isEqualTo(compareAtprice);
    }

    private void clickTitleLayer() {
        String xpath = "//div[contains(@class,'s-input')]/following-sibling::h3";
        doubleClickOnElement(xpath);
    }

    public void inputLocationForLayer(String value) {
        String xpath = "//div[@class='col-xs-12' and child::label[contains(.,'Location')]]/following-sibling::div[%d]//input";
        String[] _value = value.split(">");
        //input value X
        waitClearAndType(String.format(xpath, 1), _value[0]);
        //input value Y
        if (_value.length == 2)
            waitClearAndType(String.format(xpath, 2), _value[1]);
        waitABit(1000);
    }

    public void inputRotationForLayer(String sRotateLayer) {
        String xpath = "//input[@placeholder='Rotation']";
        waitClearAndTypeThenEnter(xpath, sRotateLayer);
    }

    public void inputOpacityForLayer(String sOpacityLayer) {
        String xpath = "//div[@class='s-form-item custom-s-form is-success is-required' and child::div[contains(.,'Opacity')]]//input";
        waitClearAndTypeThenEnter(xpath, sOpacityLayer);
    }

    String xpath_Option = "//div[child::a[normalize-space()=\"%s\"]]";
    String xpath_input_custom = "//label[contains(text(),\"%s\")]//following-sibling::div//input";

    public void inputTextInCustomOption(String label, String value) {
        waitClearAndType(String.format(xpath_input_custom, label), value);
    }

    public String getCustomName(String label) {
        String xpath = String.format(xpath_input_custom, label);
        return getTextValue(xpath);
    }

    public void selectFont(String font) {
        String xpath = "//input[@id='font-list-undefined']";
        String xpath_value = "//span[contains(@class,'s-dropdown-item')]//span[normalize-space()='" + font + "']";
        clickOnElementByJs(xpath);
        waitABit(1000);
        clickOnElementByJs(xpath_value);
        waitABit(2000);
    }

    public void inputPlaceholder(String placeholder) {
        String xpath = "//label[text()='Placeholder ']//following-sibling::div//input";
        waitClearAndType(xpath, placeholder);
        waitForEverythingComplete();
    }

    public void inputMaxLength(String value) {
        String xpath = "//label[text()='Max length']//following-sibling::div//input";
        waitClearAndType(xpath, value);
        waitABit(1000);
    }

    public void inputDefaultValue(String defaultValue) {
        String xpath = "//label[text()='Default value ']//following-sibling::div//input";
        waitClearAndType(xpath, defaultValue);
    }

    public void clickAllow(String label) {
        String xpath = "//span[normalize-space()='" + label + "']//preceding-sibling::input";
        verifyElementSelected(xpath, true);
    }

    public void verifyNotifycation(String colName, String notify) {
        String xpath = "//label[contains(text(),'" + colName + "')]//following-sibling::div[text()='" + notify + "'] | //div[child::label[text()='" + colName + "']]//following-sibling::div//div[text()='" + notify + "']";
        if (isElementExist(xpath)) {
            verifyElementPresent(xpath, true);
        }
    }

    public void inputImage(String fileName) {
        String xpath = "//input[@type='file' and@accept='.png,.jpg']";
        chooseAttachmentFile(xpath, "image" + File.separator + fileName);
        waitUntilInvisibleLoading(5);
    }

    public void dropImage(String fileName) {
        String xpath = "//input[@type='file' and @accept='image/*']";
        chooseAttachmentFile(xpath, "image" + File.separator + fileName);
        waitUntilInvisibleLoading(10);
    }

    public void clickPictureChoiceImage() {
        String xpath = "//div[child::label[contains(text(),'Picture choice')]]//following-sibling::div//a";
        clickOnElementByJs(xpath);
        waitABit(2000);
    }

    public void clickActionMenuOfOption(String optionName) {
        if (optionName.isEmpty())
            optionName = "Untitled";
        String xpath = String.format(xpath_Option, optionName) + "//following-sibling::div//span[@class='s-icon is-small']//i";
        clickOnElementByJs(xpath);
    }

    public void clickActionCustomOption(String label, String btnAction) {
        String xpath = String.format(xpath_Option, label) + "//following-sibling::div//span[normalize-space()='" + btnAction + "']";
        waitUntilElementVisible(xpath, 30);
        clickOnElement(xpath);
    }

    public void verifyCustonField(String layer, boolean isDisplay) {
        String xpath = "//div[contains(@class,'s-form-item custom-s-form') and @style='' and descendant::label[contains(text(),'" + layer + "')]] | //div[@class='s-form-item custom-s-form is-success' and descendant::label[contains(text(),'" + layer + "')]]";
        verifyElementPresent(xpath, isDisplay);
    }

    public void verifyMessageImage(String message) {
        String xpath = "//div[@class='s-alert__description message-error']//span |//div[@class='s-alert__description']//ul//span ";
        assertThat(getText(xpath)).isEqualTo(message);
        waitABit(1000);
    }

    public void clickAddCOBelowOrAbove(String label, String location) {
        String xpath = "//div[contains(@class,'custom-option__item') and child ::div[contains(.,'" + label + "')]]";
        String xpathLocation = xpath + "//div[@class='add-" + location + " s-caption']";
        hoverOnElement(xpath);
        clickOnElement(xpathLocation);
    }

    public void inputSizeForLayer(String value) {
        String xpath = "//div[@class='col-xs-12' and child::label[contains(.,'Layer size')]]/following-sibling::div[%d]//input";
        String[] _value = value.split(">");
        String xpath_x = String.format(xpath, 1);
        String xpath_y = String.format(xpath, 2);
        waitClearAndType(xpath_x, _value[0]);
        if (_value.length == 2) {
            waitClearAndType(xpath_y, _value[1]);
        }
    }

    public void verifyLocationAndSize(String xpath, String value) {
        int j = 0;
        String size = "";
        while (size != value) {
            waitClearAndTypeThenTab(xpath, value);
            size = getTextValue(xpath);
            j++;
            if (j > 2)
                break;
        }
    }

    public void inputSizeForLayer(String wid, String height) {
        String xpath = "//div[@class='col-xs-12' and child::label[contains(.,'Size')]]/following-sibling::div[%d]//input";
        String xpath_x = String.format(xpath, 1);
        String xpath_y = String.format(xpath, 2);
        waitClearAndType(xpath_x, wid);
        waitClearAndType(xpath_y, height);
    }

    String _xpathLayer = "//div[@class='s-dropdown-trigger' and descendant::div[@class='btn-select__trigger--item-container s-py4']] | //div[@class='s-dropdown-trigger' and child::div[contains(@class,'element-drop-down')]]";

    public void selectLayer(String product, String sLayer) {
        String xpath = "//div[child::label[text()='" + product + "']]//following-sibling::div[@class='s-form-item__content']//div[@class='s-button-drop-down']/following-sibling::span";
        if (isElementExist(xpath, 2)) {
            clickOnElementByJs(xpath);
            String xpathValue = "//div[child::label[text()='" + product + "']]/following-sibling::div//div[contains(@class,'s-dropdown-menu')]//span[contains(.,'" + sLayer + "')]";
            clickOnElementByJs(xpathValue);
        } else {
            clickOnElementByJs(_xpathLayer);
            String xpathValue = "//div[contains(@class,'s-dropdown-menu')]//span[contains(.,'" + sLayer + "')]";
            clickOnElementByJs(xpathValue);
        }
    }

    public String getLayerCustomOptionDetail() {
        return getText(_xpathLayer + "//div[@class='s-button-drop-down']");
    }

    public boolean isCheckPersonalizeLabelForLayer(String sLayerName) {
        String xpath = "//div[@class='single-layer__container']//div[contains(@class,'layer-name') and child::span[normalize-space()='" + sLayerName + "']]";
        return isElementExist(xpath);
    }

    public Boolean isExitCustomNameInListCustomOption(String sCustomName) {
        return isElementExist(String.format(xpath_Option, sCustomName));
    }

    public void openDetailLayer(String layer) {
        String xpath = "//div[@class='single-layer__container']//span[normalize-space()='" + layer + "' and @class='text-bold s-caption display-block text-overflow']";
        clickOnElementByJs(xpath);
        waitABit(3000);
    }

    public void verifyText(String text) {
        String xpath = "//div[@class='s-form-item__wrap-label' and child::label[text()='Text']]//following-sibling::div[@class='s-form-item__content']//input";
        verifyElementText(xpath, text);
    }

    public void verifyFont(String font) {
        String xpath = "//div[@class='s-form-item__wrap-label' and child::label[text()='Font']]//following-sibling::div[@class='s-form-item__content']//div[@class='s-input s-input--prefix s-input--suffix']";
        assertThat(getAttributeValue(xpath, "style")).contains(font);
    }

    public void verifyColor(String color) {
        String xpath = "//div[contains(@class,'text-form-data-custom')]//div[contains(@class,'s-input-group--append')]//input";
        String xpathGetText = "//div[contains(@class,'vc-sketch-field--double')]//input[@class = 'vc-input__input']";
        waitUntilElementVisible(xpath, 10);
        clickOnElementByJs(xpath);
        verifyElementText(xpathGetText, color);
        clickOnElementByJs(xpath);
    }

    public void verifyRotation(String rotation) {
        String xpath = "//input[@placeholder='Rotation']";
        verifyElementText(xpath, rotation);
    }

    public void verifyOpacity(String Opacity) {
        String xpath = "//div[@class='s-form-item custom-s-form is-success is-required' and child::div[contains(.,'Opacity')]]//input";
        verifyElementText(xpath, Opacity);
    }

    public void verifyLayerDisplay(String layer, boolean display) {
        String xpath = "//div[contains(@class,'sb-personalize')]//span[normalize-space()='" + layer + "'][1]";
        verifyElementPresent(xpath, display);
    }

    public void unlinkProduct(String product) {
        String xpath_link = "//span[normalize-space()='" + product + "']//preceding-sibling::a[@class='link-product']";
        clickOnElement(xpath_link);
    }

    public void reUnLinkProduct(String product) {
        String xpath_link = "//span[normalize-space()='" + product + "']//preceding-sibling::a[@class='link-product text-gray100']";
        clickOnElementByJs(xpath_link);
    }

    public void clickActionlayer(String action) {
        String[] listAction = action.split(",");
        for (String act : listAction) {
            String xpath = "//ul[contains(@class,'list-inline')]//span[@data-label='" + act + "']";
            String xpath_delete = "//button//span[normalize-space()='Delete']";
            waitElementToBeVisible(xpath);
            clickOnElement(xpath);
            if (isElementExist(xpath_delete, 2)) {
                clickBtn("Delete");
            }
        }
    }

    public void inputTextLayer(String text) {
        String xpath = "//input[@placeholder='Add your text']";
        waitClearAndTypeThenEnter(xpath, text);
    }

    public void inputColorLayer(String color) {
        String xpath = "//div[@class='vc-sketch-field--double']//input";
        String xpathSelectColor = "//div[contains(@class,'text-form-data-custom')]//div[contains(@class,'s-input-group--append')]//input";
        waitUntilElementVisible(xpathSelectColor, 10);
        clickOnElementByJs(xpathSelectColor);
        waitClearAndTypeThenEnter(xpath, color);
        clickOnElementByJs(xpathSelectColor);
    }

    public Boolean isShowNotiInPsdDetail() {
        String _xpath = _xpathListLayer + "/p[@class='s-caption' and contains(.,'You cannot edit the layers in this PSD file but add them as the target layer in the custom')]";
        System.out.println(getText(_xpath));
        return isElementExist(_xpath, 3);
    }

    // check show warning in PSD with case layer text effect or rotate
    public Boolean checkWarningShowInPsdDetail(String sWarning) {
        String _xpath = _xpathListLayer + "//span[@class='s-alert__title']/p[contains(.,'" + sWarning + "')]";
        return isElementExist(_xpath, 3);
    }

    public void verifySize(String size) {
        String[] sizeList = size.split(">");
        String xpath = "//div[child::label[text()='Size']]//following-sibling::div[descendant::span[normalize-space()='" + sizeList[0] + "']]//input";
        Long _locationAc = Long.parseLong(getTextValue(xpath));
        Long _locationEx = Long.parseLong(sizeList[1]);
        assertThat(-2 < _locationAc - _locationEx && _locationAc - _locationEx < 2).isEqualTo(true);
    }

    public void verifyLocation(String location) {
        String[] value = location.split(">");
        String xpath = "//div[@class='col-xs-12' and child::label[contains(.,'Location')]]/following-sibling::div[%d]//input";

        //input value X
        verifyElementText(String.format(xpath, 1), value[0]);
        //input value Y
        if (value.length == 2) {
            Long locationAc = Long.parseLong(getTextValue(String.format(xpath, 2)));
            Long locationEx = Long.parseLong(value[1]);
            assertThat(-3 <= locationAc - locationEx && locationAc - locationEx <= 3).isEqualTo(true);
        }
        waitABit(1000);
    }

    public void clickSimilarToFrontLayer() {
        String xpath = "//label[@class='s-checkbox text-gray400']//span[@class='s-check']";
        String xpathStatus = "//label[@class='s-checkbox text-gray400']//input";
        verifyCheckedThenClick(xpathStatus, xpath, true);
    }

    public void verifyBackSide(String frontOrBack, String layer, boolean disable) {
        String xpath = "//span[contains(.,'" + frontOrBack + "')]/ancestor::div[contains(@class,'s-collapse-item artwork')]//span[@data-label='" + layer + "']";
        verifyElementVisible(xpath, disable);
    }

    // Check noti for layer text effect or rotate in PSD detail
    public Boolean checkNotiTooltipPsdDetail(String sLayerInPSD, String noti) {
        String _xpath = "//div[contains(.,'" + sLayerInPSD + "')]/ancestor::div[contains(@class,'single-layer')]//span[contains(@data-label,'" + noti + "')]//i";
        return isElementExist(_xpath, 3);
    }

    public void chooseColorForProduct(String _sColor) {
        String[] colors = _sColor.split(",");
        List<String> listColors = new ArrayList<>(Arrays.asList(colors));
        clickOnElementByJs("//div[@class='color']//i");
        String _xpath_list_color = "//span[@class='s-dropdown-item']";
        String xpath = "(" + _xpath_list_color + ")[%d]";
        int countColor = countElementByXpath(_xpath_list_color);
        for (int i = 1; i < countColor + 1; i++) {
            String color = String.format(xpath, i);
            String xpathSelect = color + "//div[@class='color cursor-pointer']";
            String getColor = getAttributeValue(xpathSelect+"/following-sibling::span","textContent");
            String xpathStatus = xpathSelect + "/div[@class='color__active']";
            if (listColors.contains(getColor))
                verifyChoosedThenClick(xpathStatus, xpathSelect, true);
            else verifyChoosedThenClick(xpathStatus, xpathSelect, false);
        }
        clickOnElementByJs("//div[@class='color']//i");
    }


    public void verifyColorSelected(String _sColor, boolean isSelect) {
        String[] colors = _sColor.split(",");
        List<String> listColors = new ArrayList<>(Arrays.asList(colors));
        String xpath = "//div[@class='color-section']/span[%d]";
        int countColor = countElementByXpath("//div[@class='color-section']/span");
        for (int i = 1; i < countColor + 1; i++) {
            String color = String.format(xpath, i);
            String getColor = getAttributeValue(color, "data-label");
            String xpathSelect = color + "//div[@class='color cursor-pointer']";
            String xpathStatus = xpathSelect + "//div[@class='color__active']";
            if (_sColor.contains(getColor))
                assertThat(isElementExist("")).isEqualTo(isSelect);
        }
    }

    public void verifyChoosedThenClick(String xpathChoosed, String xpath, boolean isChoose) {
//        boolean is = isElementExist(xpathChoosed, 3);
        boolean is = XH(xpathChoosed).isDisplayed();
        if (is != isChoose)
            clickOnElementByJs(xpath);
    }

    public void waitUntilInvisibleIconLoading(int timeout) {
        String xpath = "//div[@class='image__container uploading' or @class='loading-mask' or @class='spinner__wrapper']";
        int i = 0;
        while (isElementExist(xpath, 3)) {
            waitUntilElementInvisible(xpath, timeout);
            i++;
            if (i == 10)
                break;
        }
    }

    public void selectTargetLayer(String isChoose) {
        boolean is = false;
        if (isChoose.equalsIgnoreCase("yes") | isChoose.equalsIgnoreCase("true"))
            is = true;
        String xpath = "//label[@class='s-checkbox']//span[@class='s-check']";
        String xpathStatus = "//label[@class='s-checkbox']//input";
        if (countBaseProduct() >= 2) {
            verifyCheckedThenClick(xpathStatus, xpath, is);
        }
    }

    public void verifyNotification(String product, String notification) {
        String xpath = "//div[child::label[text()='" + product + "']]//following-sibling::div//span[contains(normalize-space(),'" + notification + "')]";
        verifyElementVisible(xpath, true);
    }

    public void chooseLayerForAllProduct(String product) {
        String xpath = "//div[child::label[text()='" + product + "']]//following-sibling::div//a";
        clickOnElementByJs(xpath);
    }

    public void verifyLayerSelected(String layer) {
        //String xpath = "//div[child::label[text()='" + product + "']]//following-sibling::div//div[@class='s-button-drop-down' and normalize-space()='" + layer + "']";
        String xpath="//div[@class='s-dropdown-trigger']//span[normalize-space()='"+layer+"']";
        verifyElementPresent(xpath, true);
    }


    public void uncheckValueCustomOption() {
        String xpath = "//p[contains(text(),'is unchecked')]//following-sibling::label[contains(@class,'label--check-show-hide')]//span[@class='s-check']";
        String xpathStatus = "//p[contains(text(),'is unchecked')]//following-sibling::label[contains(@class,'label--check-show-hide')]//input";
        verifyCheckedThenClick(xpathStatus, xpath, true);
    }

    public void verifyCheckCustomOption(String option, boolean isCheck) {
        String xpath = "//div[@class='base-checkbox']//span[contains(.,'" + option + "')]//preceding-sibling::input";
        verifyElementSelected(xpath, isCheck);
    }

    public void verifyRadioValueDefault(String value, boolean isCheck) {
        String xpath = "//div[@class='base-radio']//span[text()='" + value + "']//preceding-sibling::input";
        verifyElementSelected(xpath, isCheck);
    }

    public void verifyDroplistDefault(String value, String isSelected) {
        String xpath = "//label[text()='Options']//following-sibling::div//option[text()='" + value + "']";
        assertThat(getAttributeValue(xpath, "selected")).isEqualTo(isSelected);
    }

    public void clickConditionalLogic(String option) {
        String xpath = "//div[contains(@class,'custom-option__item') and child ::div[contains(.,\"" + option + "\")]]//i[contains(@class,'variant')]";
        clickOnElement(xpath);
    }

    public int getSizeCondition() {
        By lst_conditionalLogic = By.xpath("//div[@class='rule-wrapper s-p16 s-mb16']");
        List<WebElement> lstMyCondition = getDriver().findElements(lst_conditionalLogic);
        return lstMyCondition.size();
    }

    public void selectThenShow(int indexCondi, int indexThenShow, String s_option) {
        String s_optionThenshow = "(//div[@class='p-sm conditional-logic-container']/div[" + indexCondi + "]//div[@class='s-select']/select)[" + indexThenShow + "]";
        selectByText(s_optionThenshow, s_option);
    }

    public void selectConditionOfConditional(int indexCondi, int index, String condition) {
        String s_conditional = "(//div[@class='p-sm conditional-logic-container']/div[" + indexCondi + "]//div[@class='s-select f-1']/select)[" + index + "]";
        selectByText(s_conditional, condition);
    }

    public void selectValueOfConditional(int indexCondi, int index, String value) {
        String s_optionValue = "(//div[@class='p-sm conditional-logic-container']/div[" + indexCondi + "]//div[contains(@class,'s-select f-1 ruleIndex')]/select)[" + index + "]";
        selectByText(s_optionValue, value);
    }

    public void verifyMessage(String message) {
        String xpath = "";
        verifyElementContainsText(xpath, message);
    }

    public void clickBack() {
        String xpath = "//i[@class='mdi mdi-chevron-left mdi-36px']";
        if (isElementExist(xpath, 2)) {
            clickOnElement(xpath);
        }
    }

    public void verifyListLayerNull() {
        String _xpathList = "//div[@class='single-layer__container']";
        assertThat(isElementExist(_xpathList)).isEqualTo(false);
    }

    public void verifyListCustomNull() {
        String xpath = "//button[normalize-space()='Customize layer']//span";
        assertThat(isElementExist(xpath)).isEqualTo(true);
    }

    public void inputPriceForBase(String sProduct, String price, int n) {
        String _xpath = "(//p[contains(.,\"" + sProduct + "\")]/ancestor::tr//input)[" + n + "]";
        for (int i = 0; i < 4; i++) {
            if (!checkPricePricingTab(_xpath, price))
                waitClearAndTypeThenTab(_xpath, price);
            else
                break;
        }
    }

    public void inputLayerName(String sLayerName) {
        String _xpathInput = "//input[@id='layer-name']";
        for (int i = 0; i < 4; i++) {
            if ($(_xpathInput).isDisplayed() && $(_xpathInput).isEnabled()) {
                waitABit(2000);
                waitUntilElementVisible(_xpathInput, 50);
                waitElementToBeClickable(_xpathInput).click();
                waitClearAndTypeThenTab(_xpathInput, sLayerName);
                break;
            } else clickTitleLayer();
        }
        waitABit(2000);
    }

    public void clickDeleteLayer(String layer, String _sSide) {
        String _xpath = String.format(_xpathSide, _sSide);
        String _xpathDelete;
        if (_sSide.equalsIgnoreCase("Front") | _sSide.equalsIgnoreCase("Back")) {
            String _sActive = getAttributeValue(_xpath, "class");
            if (!_sActive.contains("is-active"))
                clickOnElement(_xpath);
            _xpathDelete = _xpath + "//span[@data-label='" + layer + "']/ancestor::div[@class=\"single-layer__container\"]//i[contains(@class,\"delete\")]";
        } else {
            _xpathDelete = _xpathLayerOnSide + "//span[@data-label='" + layer + "']/ancestor::div[@class=\"single-layer__container\"]//i[contains(@class,\"delete\")]";
        }
        clickOnElementByJs(_xpathDelete);
    }

    String xpathParent = "//div[contains(@class,'s-flex s-flex--align-center') and child::div[normalize-space()='Value']]//following-sibling::div";

    public void inputValue(String value, int i) {
        String xpath = "(" + xpathParent + "//input[@placeholder='Enter a value'])[" + i + "]";
        waitClearAndType(xpath, value);
    }

    public void clickDefaultValue(int i) {
        String xpath = "(" + xpathParent + "//span[@class='s-check'])[" + i + "]";
        String xpathStatus = "(" + xpathParent + "//input[@type='checkbox'])[" + i + "]";
        verifyCheckedThenClick(xpathStatus, xpath, true);
    }

    public void addValue() {
        String xpath = "//button[@class='s-button is-text']";
        clickOnElementByJs(xpath);
        int element = countElementByXpath(xpathParent + "//input[@placeholder='Enter a value']");
        assertThat(element).isEqualTo(3);
    }

    public void deleteValue() {
        int element = countElementByXpath(xpathParent + "//input[@placeholder='Enter a value']");
        String xpath = "(" + xpathParent + "//span[contains(@class,'s-icon')]//i)[" + element + "]";
        clickOnElementByJs(xpath);
    }

    public void searchArtworkInPopupArtworkLibrary(String sLayerName) {
        String _xpathSearch = "//input[@class ='s-input__inner product-search-input']";
        waitClearAndTypeThenEnter(_xpathSearch, sLayerName);
    }

    public void clickBtnDelete() {
        String _xpathDelete = "//button[contains(.,'Delete')]";
        String _xpath_popup = "//div[@class='s-dialog s-modal is-active']";
        waitUntilElementVisible(_xpath_popup, 50);
        hoverThenClickElement(_xpath_popup, _xpathDelete);
    }

    public void verifyIconNotiRed(String product, boolean isNoti) {
        String xpath = "//span[normalize-space()='" + product + "']//preceding-sibling::div/span[contains(@class,'s-icon text-danger')]";
        assertThat(isElementExist(xpath)).isEqualTo(isNoti);
    }

    public String verifyNoticationOfBaseProduct(String product) {
        String xpath = "//span[normalize-space()='" + product + "']//preceding-sibling::div/span[@class='s-tooltip-fixed-content right is-black']";
        return getText(xpath);

    }

    public int getListSizeOnEditor() {
        String xpath = "//div[@class='size-section']//button";
        return countElementByXpath(xpath);
    }

    public void clickOnSetIndividualPrice() {
        String xpath = "//a[contains(text(), 'Set individual price')]";
        clickOnElement(xpath);
    }


    public String getValueSize(int index) {
        String xpath = "//div[@class='size-section']//button[" + index + "]";
        String valueSize = getText(xpath);
        return valueSize;
    }

    public void verifyVariant(String sVariant) {
        String xpath = "//td[contains(text(),'" + sVariant + "')]";
        assertThat(countElementByXpath(xpath)).isNotEqualTo(0);
    }

    public void selectSize(int index) {
        String xpath = "//div[@class='size-section']//button[" + index + "]";
        String xpathStatus = "//div[@class='size-section']//button[@class='s-button s-mb8 s-mr8 s-p8 is-default is-small cursor-pointer active'][" + index + "]";
        if (countElementByXpath(xpathStatus) == 0)
            clickOnElement(xpath);
    }

    public void verifySize(int index) {
        String xpath = "//div[@class='size-section']//button[" + index + "]";
        String xpathStatus = "//div[@class='size-section']//button[@class='s-button s-mb8 s-mr8 s-p8 is-default is-small cursor-pointer'][" + index + "]";
        if (countElementByXpath(xpathStatus) == 0)
            clickOnElement(xpath);
    }

    public void clickOnSearchAClipartFolderTxt() {
        String xpath = "//div[@class='s-dropdown d-flex align-items-center tag-manager is-mobile-modal full-width']//div[@class='s-input s-input--prefix s-input--suffix']";
        clickOnElement(xpath);
    }

    public void clickOnAddAClipartFolderBtn() {
        String xpath = "//span[contains(text(),'Add a clipart folder')]";
        clickOnElement(xpath);
    }

    public void verifyNameClipartFolder(String sNameFolder) {
        String xpath = "//label[@class='display-block']//span[@class='font-12 display-inline-block text-overflow vertical-align-center']";
        String value = getText(xpath);
        assertThat(sNameFolder).isEqualTo(value);
    }

    public void verifyNumberImageClipart(String sNumber) {
        String xpath = "//label[@class='display-block']//span[@class='font-12 display-inline-block text-right']";
        String value = getText(xpath);
        assertThat(value).contains(sNumber);
    }

    public void inputNameFolder(String sFolder) {
        String xpath = "//div[@class='s-input']//input[@placeholder='e.g. Hair Style']";
        waitUntilElementVisible(xpath, 2000);
        waitClearAndTypeThenTab(xpath, sFolder);
    }

    public void chooseGroup(String sGroup) {
        String searchGroupXpath = "//input[@id='clipart-list-input']";
        String chooseGroupXpath = "//span[@class='s-dropdown-item text-overflow']//span[normalize-space(text())='" + sGroup + "']";
        waitClearAndType(searchGroupXpath, sGroup);
        clickOnElementByJs(chooseGroupXpath);
    }

    public void chooseAClipartFolder() {
        String xpath = "//span[@class='s-dropdown-item text-overflow']//span";
        clickOnElementByJs(xpath);
    }

    public void clickEditClipartFolderLink() {
        String xpath = "//a[contains(text(), 'Edit clipart folder')]";
        clickOnElementByJs(xpath);
    }

    public void clickOnImage(int index) {
        String xpath = "//div[@class='base-picture__value'][" + index + "]/label/span";
        clickOnElementByJs(xpath);
    }

    public void chooseCamp(String sTitle) {
        String xpath = "//span[text()='" + sTitle + "']";
        clickOnElementByJs(xpath);
    }

    public void searchNameClipartFolder(String sFolder) {
        String xpath = "//input[@id='pc-clipart-folder-input']";
        waitClearAndType(xpath, sFolder);
    }

    public void inputNameFolcer(String sFolder) {
        String xpath = "//input[@id='pc-clipart-folder-input']";
        waitClearAndType(xpath, sFolder);
    }

    public void selectMainProductInPricing(String sProduct) {
        String xpath = "//div[@class='s-select full-width']/select";
        waitUntilElementVisible(xpath, 50);
        selectByText(xpath, sProduct);
    }

    public void selectMainColor(String sColorCode) {
        String colorName = convertToGRBColor(sColorCode);
        String xpath = "//div[child::div/label[contains(.,'Select main color')]]//span[contains(@style,'background: rgb"+ colorName +";')]";
        waitElementToBeClickable(xpath).click();
    }

    public void selectMainColor1() {
        String xpath = "(//div[child::div/label[contains(.,'Select main color')]]//span[contains(@style,'')])[1]";
        waitElementToBeClickable(xpath).click();
    }


    public void addNewOption(int indexCondi) {
        String xpath = "//div[@class='p-sm conditional-logic-container']/div[" + indexCondi + "]//span[contains(text(),'Add new option')]";
        waitElementToBeClickable(xpath).click();
    }

    public void addConditional() {
        String xpath = "//*[@id='create-product']//span[@class='s-icon pointer s-ml8 is-small']//i[@class ='mdi mdi-plus mdi-18px']";
        clickOnElement(xpath);
    }

    public void addAnotherCondition(int indexCondi) {
        String xpath = "//div[@class='p-sm conditional-logic-container']/div[" + indexCondi + "]//span[contains(text(),'Add another condition')]";
        waitElementToBeClickable(xpath).click();

    }

    public void backToCustomList() {
        String xpath = "//*[@id='create-product']//i[@class ='mdi mdi-chevron-left mdi-36px']";
        waitABit(4000);
        clickOnElement(xpath);
    }

    public void chooseGroupForCamp(String sGroup) {
        clickOnRadioChooseGroup();
        clickSearchGroup(sGroup);
        clickGroup(sGroup);
    }

    public void clickOnRadioChooseGroup() {
        String xpath = "//span[contains(text(),'Show a droplist of clipart Group to let buyer choose first')]";
        clickOnElementByJs(xpath);
    }

    public void clickSearchGroup(String sGroup) {
        String xpath = "//input[@id='pc-clipart-group-input']";
        scrollToElement(xpath);
        clickOnElementByJs(xpath);
        waitClearAndType(xpath, sGroup);
    }

    public void clickGroup(String sGroup) {
        String xpath = "//span[contains(text(),'" + sGroup + "')]";
        scrollToElement(xpath);
        clickOnElementByJs(xpath);
    }

    public void showClipart(String sShowClipart) {
        String xpath = "//span[contains(text(),'" + sShowClipart + "')]";
        waitUntilElementVisible(xpath, 10);
        scrollToElement(xpath);
        clickOnElementByJs(xpath);
    }

    public void clickOnSaveChangeBtn() {
        String xpath = "//div[@class='fixed-setting-bar__bottom']//span[contains(text(),'Save changes')]";
        int i = 0;
        while (!waitUntilElementInvisible(xpath, 10)) {
            clickOnElementByJs(xpath);
            i++;
            if (i > 3)
                break;
        }
    }

    public void chooseCustomOption(String sCO) {
        String xpath = "//a[contains(text(),'" + sCO + "')]";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);

    }

    public void clickOnEditPersonalizationBtn() {
        String xpath = "//span[contains(text(),'Edit personalization')]";
        waitUntilElementVisible(xpath, 100);
        clickOnElementByJs(xpath);
        waitForPageLoad();
    }

    public void inputValueInToRadio(int index, String value) {
        String xpath = "//div[@class='draggable-item s-mb8'][" + index + "]//input[@type='text']";
        waitClearAndType(xpath, value);
    }

    public void clickOnConditionalBtn(String sCO) {
        String xpath = "//div[@class='content__center f-1' and descendant::a[contains(text(),'" + sCO + "')]]//following-sibling::div//span[child::i[@class='mdi mdi-shuffle-variant mdi-18px']]";
        clickOnElementByJs(xpath);
    }

    public void confirmDelete() {
        String xpath = "//footer//button['s-button btn-confirm-delete is-danger' and normalize-space()='Delete']";
        clickOnElementByJs(xpath);
    }

    public void setConditional(int index, String _sValue, String _sCustomOption) {
        String xpath_value = "//div[@class='s-select f-1 ruleIndex" + index + "']//select";
        String xpath_show = "//div[@class='s-select f-1 ruleIndex" + index + "']//ancestor::div[@class='row s-mb16']//following-sibling::div[@class='row s-my16']//select";
        selectDdlByXpath(xpath_value, _sValue);
        selectDdlByXpath(xpath_show, _sCustomOption);
    }

    public void clickOnPlusBtn() {
        String xpath = "//span[@class='s-icon pointer s-ml8 is-small']";
        clickOnElementByJs(xpath);
    }

    public void clickUseCustomeArtSevice() {
        String xpath = "//p[text()='Use the Custom Art Service for this campaign.']";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void selectOnAddMaterial() {
        String xpath = "//div[span[text()='Additional Materials']]//i[@class ='mdi mdi-chevron-up mdi-24px']";
        String xpath_scroll = "//div[@class ='s-collapse-item']";
        scrollToElementOther(xpath_scroll);
        waitUntilElementVisible(xpath, 5);
        clickOnElementByJs(xpath);
    }

    public void inputFileNameOnAddMaterials(String name) {
        String xpath = "(//div[@class='s-form-item__wrap-label' and child::label[text()='File name/Note']]/following-sibling::div//input)[last()]";
        scrollToElement(xpath);
        waitClearAndType(xpath, name);
    }

    public void selectFileOnAddMaterials(String imageName) {
        String xpath = "(//div[@class='s-form-item__wrap-label' and child::label[text()='File']]/following-sibling::div//input)[last()]";
        scrollToElementOther(xpath);
        chooseMultipleAttachmentFiles(xpath, imageName);
    }

    public void clickOnAppliedFor() {
        String xpath = "(//div[@class='s-form-item__wrap-label']/following-sibling::div//span[text()='Select the applied options'])[last()]";
        scrollToElementOther(xpath);
        clickOnElementByJs(xpath);
    }

    public void selectItemOnAppliedFor(String itemName) {
        String xpath = "//div[contains(@class,'s-dropdown-menu')]//p[text()='" + itemName + "']";
        clickOnElementByJs(xpath);
    }

    public void verifyButtonPhotoGuide() {
        String xpath = "//label[text()='Photo guide']";
        waitUntilElementVisible(xpath, 30);
    }

    public void verifyProductWillBeCustomized() {
        String xpath = "//div[contains(@class,'product-property')]//img[contains(@class,'w-100 mt8')]";
        waitUntilElementVisible(xpath, 30);
    }

    public void clickOnAddMaterial() {
        String xpath = "//a[normalize-space()='Add material']";
        clickOnElementByJs(xpath);
    }

    public void clickOnPhotoGuide() {
        String xpath = "//input[@placeholder='Search by name']";
        waitUntilElementInvisible(xpath, 10);
        clickOnElement(xpath);
        scrollToElementOther(xpath);
    }

    public void clickonAddPhotoGuide() {
        String xpath = "//span[normalize-space()='Add a photo guide']";
        clickOnElement(xpath);
    }

    public void inputOnPhotoName(String photoName) {
        String xpath = "//form[@id='crud-guide__form']//div[contains(@class,'s-form-item__content')]//input";
        waitClearAndType(xpath, photoName);
    }

    public void clickOnSavePhotoGuide() {
        String xpath = "//button[span[normalize-space()='Save']]";
        clickOnElement(xpath);
    }

    public void verifyPhotoGuideAfAdd(String photoName, boolean isShow) {
        String xpath = "//span[span[text()='" + photoName + "']]//i[contains(@class,'mdi-check')]";
        assertThat(isElementExist(xpath)).isEqualTo(isShow);
    }

    public void clickEditPhotoGuide() {
        String xpath = "//span[@data-label='Edit photo guide']//img";
        clickOnElement(xpath);
    }

    public void clickSaveDraftCampaign() {
        String xpath = "//span[normalize-space()='Save draft']";
        clickOnElement(xpath);
    }

    public void clickPhotoGuideOnSF(String customName) {
        String xpath = "//div[@class='product-property']//label[text()='" + customName + "']/..//label[normalize-space()='Photo guide']";
        clickOnElement(xpath);
    }

    public void inputDescriptionOnPhotoGuide(String description) {
        switchToIFrame("//div[@class='s-modal-body']//div[@class='tox-edit-area']/iframe");
        waitClearAndType("//body[@id='tinymce']", description);
        switchToIFrameDefault();
    }

    public void clickOnSelectUploadImage() {
        String xpath = "//form[@id=\"crud-guide__form\"]//div[@class='tox-toolbar'][2]//div[@class='tox-toolbar__group'][2]//button[@title=\"Insert/edit image\"]";
        clickOnElement(xpath);
    }

    public void clickOnUploadImage() {
        String xpath = "//div[text()='Upload']";
        clickOnElement(xpath);
    }

    public void uploadImageOnPhotoGuide(String imageName) {
        String xpath = "//button[text()='Browse for an image']//input";
        waitUntilElementInvisible(xpath, 10);
        chooseMultipleAttachmentFiles(xpath, imageName);
    }

    public void selectMainProduct(String sMainProduct) {
        String xpath = "(//select[@class=''])[3]";
        clickOnElement(xpath);
        String xpath1 = "//option[text()[normalize-space()='Ladies T-shirt']]";
        clickOnElement(xpath1);
    }

    public void verifyProductMain(String productMain) {
        String xpath = "//select[child::option[normalize-space()='"+ productMain +"']]";
        verifyElementVisible(xpath,true);
    }

    public void clickOnSaveUploadImage() {
        String xpath = "//button[text()='Save']";
        waitUntilElementInvisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void inputFontCustomOption(String sFont) {
        String xpath1 = "//input[@id='font-list-undefined' or contains(@id,'font-list-text-co')]";
        clickOnElement(xpath1);
        String xpath2 = "//span[contains(text(),'" + sFont + "')]";
        clickOnElement(xpath2);
    }

    public void getImageProductDetailPageWithURL(String sImageAc, int imageNumber) throws IOException {
        String xpath_hover = "//div[@class='img m-sm'][" + imageNumber + "]";
        String xpath_clickImage = "//div[@class='img m-sm'][" + imageNumber + "]//i[@class='icon cursor-pointer']";
        String xpathImage = "//div[@class='s-modal-body']//img";
        String xpath_done = "//button//span[text()='Done']";
        scrollToElementOther(xpath_clickImage);
        hoverThenClickElement(xpath_hover, xpath_clickImage);
        int n = 0;
        while (!isElementExist(xpathImage)) {
            refreshPage();
            n++;
            if (n > 5)
                break;
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        String sUrl[] = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(namefile.indexOf("@") + 1);
        String url = "";
        for (int i = 0; i < sUrl.length - 1; i++)
            url = url + sUrl[i] + "/";
        url = url + namefile;
        System.out.println(url);
        downloadWebPage(url, sImageAc);
        clickOnElement(xpath_done);
    }

    public void verifyItemOnAppliedFor() {
        String xpath_error1 = "//p[text()=' No option found.']";
        String xpath_error2 = "//p[normalize-space()='Please create your custom options to assign them here']";
        waitUntilElementVisible(xpath_error1, 10);
        waitUntilElementVisible(xpath_error2, 10);
    }

    public void verifyMessageErrorInPricingScreen(String messageError) {
        String xpath = "//div[contains(text(),'Please review and fix these following errors from the previous step to start launching this campaign:')]";
        String xpath_message_error = "//div[@class='s-alert__content']//li//span[normalize-space()='" + messageError + "']";
        waitUntilElementVisible(xpath, 10);
        waitUntilElementVisible(xpath_message_error, 10);
    }

    public void verifyButtonLauchCamp(String status) {
        String xpath = "//button[span[normalize-space()='Launch']]";
        assertThat(getAttributeValue(xpath, "disabled")).isEqualTo(status);
    }

    public void deleteMaterials(int materialQuantity) {
        String xpath = "//div[contains(@class,'material__item material__item--wrapper')][" + materialQuantity + "]//i[@class='mdi mdi-close mdi-18px']";
        clickOnElement(xpath);
    }

    public void inputFileNameOnEditMaterials(String name, int numberMaterial) {
        String xpath = "(//div[@class='s-form-item__wrap-label' and child::label[text()='File name/Note']]/following-sibling::div//input)[" + numberMaterial + "]";
        waitClearAndType(xpath, name);
    }

    public void selectFileOnEditMaterials(String imageName, int numberMaterial) {
        String xpath = "(//div[@class='s-form-item__wrap-label' and child::label[text()='File']]/following-sibling::div//input)[" + numberMaterial + "]";
        scrollToElementOther(xpath);
        chooseMultipleAttachmentFiles(xpath, imageName);
    }

    public void clickAppliedForOnEditMaterials(int numberMaterial) {
        String xpath = "(//div[@class='s-form-item__wrap-label']/following-sibling::div//span[text()='Select the applied options'])[" + numberMaterial + "]";
        scrollToElementOther(xpath);
        clickOnElementByJs(xpath);
    }

    public void verifyUploadFileFieldOnMaterial() {
        String xpath = "//div[normalize-space()='Please upload a file.']";
        assertThat(isElementExist(xpath)).isEqualTo(true);
    }

    public void verifyFileNameOnMaterial() {
        String xpath = "//div[contains(@class,'s-form-item s-flex--fill') and descendant::label[text()='File name/Note']]//div[@class='s-form-item__error']";
        assertThat(getText(xpath)).isEqualTo("Please fill the file name or your note.");
    }

    public void verifyNotiPicktureChoice(String noti, boolean check) {
        String xpath = "//div[@class='s-form-item__content']//p[normalize-space()='" + noti + "']";
        verifyElementPresent(xpath, check);
    }

    public void verifyNotiCO(String message) {
        String xpath = "//div[@class='content__center f-1']//following::div//p[@class='text-danger content__below s-w100']";
        assertThat(getText(xpath)).isEqualTo(message);
    }

    public void deleteCO(String customOption) {
        String xpath = "//div[@class='content__center f-1' and descendant::a[normalize-space()='" + customOption + "']]//following-sibling::div//i[@class='mdi mdi-dots-vertical mdi-16px']";
        String xpath_delete = "//div[@class='s-dropdown-menu']//span[normalize-space()='Delete']";
        clickOnElementByJs(xpath);
        clickOnElementByJs(xpath_delete);
    }

    public void addGroupLayer(String frontOrBack) {
        String _xpathSide = String.format(this._xpathSide, frontOrBack);
        String xpath = "//span[@place='action']//button[@class='s-button is-outline is-small']";
        clickOnElement(_xpathSide + xpath);
    }

    public void editNameGroup(String frontOrBack, String nameGroup, String nameGroupNew) {
        String _xpathSide = String.format(this._xpathSide, frontOrBack);
        String xpath = "//div[contains(@class,'single-layer__container')]//span[normalize-space()='" + nameGroup + "'][1]";
        String xpath_input = "//div[@class='single-layer__container group-container']//div[@class='text-small layer-group__title--input s-input']//input";
        doubleClickOnElement(_xpathSide + xpath);
        waitClearAndType(_xpathSide + xpath_input, nameGroupNew);
    }

    public void addLayerToGroup(String frontOrBack, String layerName, String nameGroupNew) {
        String[] list = layerName.split(",");
        for (String layer : list) {
            String _xpathSide = String.format(this._xpathSide, frontOrBack);
            String xpathLayer = "//div[contains(@class,'content__center') and descendant::span[normalize-space()='" + layer + "']]//following-sibling::div//div[@class='s-dropdown-trigger']//i";
            String xpathGroup = "//div[contains(@class,'content__center') and descendant::span[normalize-space()='" + layer + "']]//following-sibling::div//span[contains(normalize-space(),'" + nameGroupNew + "')]";
            clickOnElement(_xpathSide + xpathLayer);
            waitABit(1000);
            clickOnElement(_xpathSide + xpathGroup);
        }
    }

    public void clickBtnExpand() {
        String xpath = "//div[contains(@class,'expand-action__container')]//span[@class='s-icon is-small'] | //div[@class='right-bar body-height']//span[contains(@class,'-icon collapse-icon')]";
        if (isElementExist(xpath))
            clickOnElement(xpath);
        waitUntilInvisibleIconLoading(10);
    }

    public void clickCustomizeGroup() {
        String xpath = "//div[@class='s-collapse-item__content']//span[normalize-space()='Customize Group']";
        clickOnElement(xpath);
    }

    public void selectOptionTypeGroup(String type) {
        String xpath = "//label[text()='Display the options as']//following-sibling::div[@class='s-select s-w100 s-mt4']//select";
        selectDdlByXpath(xpath, type);
    }

    public void selectDefaultGroup(String group) {
        String xpath = "//label[text()='Default Group to show on the Mockups']//following-sibling::div[@class='s-select s-w100 s-mt4']//select";
        selectDdlByXpath(xpath, group);
    }

    public void inputLabel(String label) {
        String xpath = "//label[contains(text(),'Label of the')]//following-sibling::div[@class='s-mt4 s-input']//input";
        waitClearAndType(xpath, label);
    }

    public void editGroupLayer(String frontOrBack, String group, String action) {
        String _xpathSide = String.format(this._xpathSide, frontOrBack);
        String xpathLayer = "//div[contains(@class,'content__center') and descendant::span[normalize-space()='" + group + "']]//following-sibling::div//div[@class='s-dropdown-trigger']//i";
        String xpathAction = "//div[contains(@class,'content__center') and descendant::span[normalize-space()='" + group + "']]//following-sibling::div[contains(@class,'content__right sb-personalize')]//span[normalize-space()='" + action + "']";
        clickOnElement(_xpathSide + xpathLayer);
        waitABit(1000);
        clickOnElement(_xpathSide + xpathAction);
    }

    public void checkExitLayerOrGroup(String frontOrBack, String layer, boolean isExit) {
        String _xpathSide = String.format(this._xpathSide, frontOrBack);
        String xpath = "//div[contains(@class,'single-layer__container')]//span[normalize-space()='" + layer + "'][1]";
        verifyElementPresent(_xpathSide + xpath, isExit);
    }

    public void clickBackCustomGroup() {
        String xpath = "//div[@class='customize-group__container']//h3/span";
        clickOnElement(xpath);
    }

    public void verifyMessageLayer(String message) {
        String xpath = "//div[@class='s-form-item__content']//span[contains(normalize-space(),'" + message + "')]";
        verifyElementPresent(xpath, true);
    }

    public void setUpPersonalization() {
        String xpath = "//div[contains(@class,'editor-side-bar-detail')]//p[contains(normalize-space(),'Set up personalization')]";
        clickOnElement(xpath);
    }

    public void editOption(String name, String action) {
        String xpath = "//div[@class='content__center f-1' and descendant::a[normalize-space()='" + name + "']]//following-sibling::div//span[@class='s-icon is-small']";
        String xpathAction = "//div[@class='content__center f-1' and descendant::a[normalize-space()='" + name + "']]//following-sibling::div//span[normalize-space()='" + action + "']";
        clickOnElement(xpath);
        waitABit(1000);
        clickOnElement(xpathAction);
    }

    public void clickBtnSave() {
        String xpath = "//div[contains(@class,'actions__container')]//button[child::span[normalize-space()='Save']]";
        if (isElementExist(xpath))
            clickOnElement(xpath);
    }

    public void verifyButtonCreateCollection(String statusButton) {
        String xpath = "//button[span[normalize-space()='Create collection']]";
        assertThat(getAttributeValue(xpath, "disabled")).isEqualTo(statusButton);
    }

}
