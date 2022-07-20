package com.opencommerce.shopbase.dashboard.apps.adc.products.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Float.parseFloat;
import static org.assertj.core.api.Assertions.assertThat;

public class ImportProductsPages extends SBasePageObject {
    public ImportProductsPages(WebDriver driver) {
        super(driver);
    }

    String msg = "//div[@class='s-notices is-bottom']//div[@class='s-toast is-dark is-bottom']";
    String variantProfit = "(//td[contains(@class,'profit-body')]//span)";
    String variantPrice = "(//td[contains(@class,'price-body')]//input)";
    String variantComparePrice = "(//td[contains(@class,'compare-body')]//input)";
    String variantName = "(//td[contains(@class,'variant1-body')]//input)";
    String originalTitle = "//div[@class='product-content'][%d]//div[@class='container product-details']//a";

    public int getRowVariant(String sVariantName) {
        String xpath = "tr[contains(@class,'body-row')";
        String vars[] = sVariantName.split(",");
        for (String var : vars) {
            xpath = "//label[text()[normalize-space()='" + var + "']]//ancestor::" + xpath;
        }
        xpath = xpath + "]/preceding-sibling::tr[contains(@class,'body-row')]";
        return countElementByXpath(xpath) + 1;
    }

    public ArrayList<String> getVariantsName() {
        ArrayList<String> varNameList = new ArrayList();
        for (int row = 1; row <= countElementByXpath(variantName); row++) {
            String valueCoupleVariant = getTextValue(variantName + "[" + row + "]");
            varNameList.add(valueCoupleVariant);
        }
        return varNameList;
    }

    public String getVariantsName(int row) {
        return getTextValue(variantName + "[" + row + "]");
    }

    public int countVariants() {
        return countElementByXpath("//div[@class='table-block']//tr[contains(@class,'body-row')]");
    }

    public ArrayList getCoupleVarians() {
        int size = countElementByXpath("//td[contains(@class,'body-row')]");
        ArrayList arCoupleVariants = new ArrayList();
        for (int row = 1; row <= size; row++) {
            String valueCoupleVariant = getTextValue("(//input[contains(@name,'value-body')])[" + row + "]") + "/" + getTextValue("(//input[contains(@name,'size-body')])[" + row + "]");
            arCoupleVariants.add(valueCoupleVariant);
        }
        return arCoupleVariants;
    }

    public ArrayList<Float> getVariantsCost() {
        int size = countElementByXpath("//td[contains(@class,'cost-body')]//span");
        ArrayList<Float> arVariantsCost = new ArrayList();
        for (int row = 1; row <= size; row++) {
            String variantCost = price(getTextValue("(//tr[contains(@class,'body-row')]//td[contains(@class,'cost-body')]//span)[" + row + "]"));
            float variantsCost = parseFloat(variantCost);
            arVariantsCost.add(variantsCost);
        }
        return arVariantsCost;
    }

    public Float getVariantsCost(int row) {
        String variantCost = price(getTextValue("(//tr[contains(@class,'body-row')]//td[contains(@class,'cost-body')]//span)[" + row + "]"));
        return parseFloat(variantCost);
    }

    public ArrayList<Float> getVariantsPrice() {
        int size = countElementByXpath(variantPrice);
        ArrayList<Float> varPriceList = new ArrayList();
        for (int row = 1; row <= size; row++) {
            String valueCoupleVariant = getTextValue(variantPrice + "[" + row + "]");
            varPriceList.add(parseFloat(valueCoupleVariant));
        }
        return varPriceList;
    }

    public Float getVariantsPrice(int row) {
        String valueCoupleVariant = getTextValue(variantPrice + "[" + row + "]");
        return parseFloat(valueCoupleVariant);
    }

    public String getCostVariant(int i) {
        String costVariant = getText("(//input[contains(@name,'value-body')]/ancestor::tr//td[@class='cost-body']//span)[" + i + "]");
        return costVariant;
    }

    public int getColTable(String typeValue) {
        String xpath = "//th[text()[normalize-space()='" + typeValue + "']]/preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public int countElementByXpathVariant() {
        int size = countElementByXpath("(//tr[contains(@class,'body-row')])");
        return size;
    }

    public float getValueCost(int i) {
        String textCost = getText("(//tr[contains(@class,'body-row')])[" + i + "]//td[@class='cost-body']/span[1]").replace("$", " ");
        float vaCost = parseFloat(textCost);
        return vaCost;
    }

    public float getValuePrice(int i) {
        String textPrice = getVariantSalePrice(i);
        float vaPrice = parseFloat(textPrice);
        return vaPrice;
    }

    public float getValueProfit(int i) {
        String textProfit = getText("(//tr[contains(@class,'body-row')])[" + i + "]//td[@class='profit-body']//span").replace("$", " ");
        float vaProfit = parseFloat(textProfit);
        return vaProfit;
    }

    public float getValueComparePrice(int i) {
        String textComparePrice = getVariantCompareatPrice(i);
        float vaComparePrice = parseFloat(textComparePrice);
        return vaComparePrice;
    }

    public String getVariantCost(int rowVariant) {
        return getText("(//tr[contains(@class,'body-row')])[" + rowVariant + "]//td[@class='cost-body']//span");
    }

    public ArrayList<Float> getVariantProfit() {
        int size = countElementByXpath(variantProfit);
        ArrayList<Float> varProfit = new ArrayList();
        for (int row = 1; row <= size; row++) {
            String value = price(getTextValue(variantProfit + "[" + row + "]"));
            varProfit.add(Float.parseFloat(roundOffTo2DecPlaces(value)));
        }
        return varProfit;
    }

    public Float getVariantProfit(int row) {
        String profit = price(getTextValue(variantProfit + "[" + row + "]"));
        return Float.parseFloat(roundOffTo2DecPlaces(profit));
    }

    public String roundOffTo2DecPlaces(String val) {
        return String.format(java.util.Locale.US, "%.2f", Float.parseFloat(val));
    }

    public ArrayList<Float> getVariantsCompareAtPrice() {
        int size = countElementByXpath(variantComparePrice);
        ArrayList<Float> varComparePriceList = new ArrayList();
        for (int row = 1; row <= size; row++) {
            String value = getTextValue(variantComparePrice + "[" + row + "]");
            varComparePriceList.add(parseFloat(value));
        }
        return varComparePriceList;
    }

    public String getVariantSalePrice(int rowVariant) {
        return getTextValue("(//tr[contains(@class,'body-row')])[" + rowVariant + "]//td[@class='price-body input']//input");
    }

    public String getVariantCompareatPrice(int rowVariant) {
        return getTextValue("(//tr[contains(@class,'body-row')])[" + rowVariant + "]//td[@class='compare-body input']//input");
    }

    public void selectVariantTab(String productName) {
        String xpath = "//div[@class='product-content' and descendant-or-self ::*[text()[normalize-space()='" + productName + "']]]//div[child::*[contains(text(),'Variant')]]/p";
        clickOnElement(xpath);
    }

    public void verifyQuanityVariant(String productName, String quantityVariant) {
        assertThat(getQuanityVariant(productName)).contains(quantityVariant);
    }

    private String getQuanityVariant(String productName) {
        String xpath = "//div[@class='product-content' and descendant-or-self ::*[text()='" + productName + "']]//div[@class='control-buttons vcenter']//div[child::*[contains(text(),'Variant')]]/p";
        return getText(xpath);
    }

    public void selectAllProduct() {
        checkCheckbox("//div[@class='total']", true);
    }

    public void waitForConfirmPopupDisplayed() {
        waitForElementToPresent("//div[@class='s-modal-card s-animation-content']//p[text()[normalize-space()='Are you sure you want to delete these products?']]");
    }


    public void clickDeleteProduct(String productName) {
        clickOnElement("//div[@class='product-content' and descendant-or-self ::*[text()='" + productName + "']]//button[text()[normalize-space()='Delete']]");
    }

    public void clickConfirmDeleteProduct() {
        clickOnElement("//button[text()[normalize-space()='Confirm']]");
        waitForEverythingComplete();
    }

    public void verifyProductDeleted(String productName) {
        verifyTextPresent("//div[@class='product-content' and descendant-or-self ::*[text()='" + productName + "']]", false);
        waitForEverythingComplete();
    }

    public void verifyStatusImportProduct(String linkProduct) {
        if (isElementExist("//div[contains(@class,'product-status')]")) {
            verifyElementPresent("//div[text()[normalize-space()='is being processed']]//b[text()='" + linkProduct + "']", true);
            waitForEverythingComplete();
        }
    }

    public void clickImagesTab(String productName) {
        clickOnElement("//div[@class='product-content' and descendant-or-self ::*[text()='" + productName + "']]//div[@class='control-buttons vcenter']//div[child::*[contains(text(),'Images')]]/p");
    }

    public void verifyImagesProduct() {
        verifyElementPresent("(//div[@class='row images-tab'])[1]", true);
    }


    public void enterDesProduct() {
        waitTypeOnElement("//div[@class='trumbowyg-editor']", "Brand Name: Cute Baby");
        clickOnElement("(//div[@class='kit-section-content'])[1]");
    }

    public void selectOptionChange(String typeValue, String optionChangeAllPrice) {
        int col = getColTable(typeValue);
        clickOnElement("(//tr[contains(@class,'select-control')])//td[" + col + "]//div[@class='kit-input-dropdown-label']");
        clickOnElement("(//tr[contains(@class,'select-control')])//td[" + col + "]//div[child::label[text()[normalize-space()='" + optionChangeAllPrice + "']]]");
    }

    public void enterValue(String valuePrice) {
        waitTypeOnElement("(//div[@class='input-float'])[1]//input[@type='number']", valuePrice);
    }

    public void clickApply() {
        clickOnElement("(//div[@class='input-float'])[1]//button[text()='Apply']");
    }

    public void clickImportToStore(String adcProdName) {
        int index = indexProduct(adcProdName);
        String xpath = "";
        if (!adcProdName.isEmpty()) {
            xpath = "(//div[a[normalize-space()=\"" + adcProdName + "\"]]/preceding::button[span[normalize-space()='Import to Store']])[" + index + "]";
        } else {
            xpath = xPathBtn("", "Import to Store", 1);
        }
        clickOnElement(xpath);
        waitForEverythingComplete();
    }

    public void verifyStatusImportToStore(String productName) {
        waitABit(5000);
        waitForTextToDisappear(productName + " is being imported", 20000);

        List<String> actualImportedProds = getListText("//div[@class='product-content']//div[@class='title']");
        String expectedImportedProds = productName + " has been imported to ShopBase Store";
        assertThat(actualImportedProds).contains(expectedImportedProds); // method verifyTextPresent not working for this situation
    }


    public void verifyStatusImportToStore(List<String> prodList) {
        for (String prod : prodList) {
            verifyStatusImportToStore(prod);
        }
    }

    public int getNumberProduct(String productName) {
        String xpath = "//div[@class='product-content' and descendant-or-self ::*[text()='" + productName + "']]";
        int numberProduct = countElementByXpath(xpath);
        return numberProduct;
    }


    public void hoverToSelectAll() {
        hoverOnElement("//div[@class='control-buttons']");
    }

    public void clickBtnRemoveFromList() {
        clickOnElement("(//div[text()[normalize-space()='Remove from list']])[1]");
    }

    public void importAllProductToStore() {
        clickOnElement("//div[text()[normalize-space()='Import to Store']]");
    }

    public int getQuantityProductsInList() {
        return countElementByXpath("//div[contains(@class,'import-list')]//div[@class='product-content']");
    }

    public void verifyMsgToGoAliExpress(String msg) {
        verifyElementText("//p[@class='content']/div", msg);
    }

    public void verifyLinkAliExpress() {
        waitUntilElementVisible("//*[text()='AliExpress' or @alt='aliexpress']",50);
        String url = getCurrentUrl();
        assertThat(url).isEqualTo("https://www.aliexpress.com/");
    }

    public void enterChangeTitle(String changeTitle, int index) {
        waitForEverythingComplete();
        String xpath = "(//div[@class='product-content'])[" + index + "]//div[@class='input-container']";
        if (!changeTitle.isEmpty()) {
//            while (!XH(xpath + "//input[@name='title']").getTextValue().equals(changeTitle)) {
            clickOnElement("//div[contains(text(),'Change title')]");
            waitABit(1000);
            waitClearAndType(xpath + "//input[@name='title']", changeTitle);
//            }
        }
        clickOnElement("//div[@class='product-image']");
        waitABit(3000);
        waitUntilElementVisible("//button[@class='kit-btn kit-btn-lg kit-btn-primary' and text()='Import to Store']", 15);
    }

    public void enterChangeNameVariant(String changeNameVariant, int index) {
        waitForEverythingComplete();
        String xpath = "//div[@class='product-content'][" + index + "]//div[@class='table-responsive']//table//td[contains(@class, 'variant1-body')][1]//input";
        boolean checkExits = isElementExist(xpath);
        if (checkExits) {
            clickAndClearThenType(xpath, changeNameVariant);
        }
        waitABit(3000);
        waitUntilElementVisible("//button[@class='kit-btn kit-btn-lg kit-btn-primary' and text()='Import to Store']", 15);
    }

    public void enterChangeDesProduct(String changeDes) {
        if (!changeDes.isEmpty()) {
            String brandName = "Brand Name:" + changeDes;
            waitTypeOnElement("//div[@class='trumbowyg-editor']", brandName);
            clickOnElement("(//div[@class='kit-section-content'])[1]");
        }
    }

    public void changeNameVariant(String changeNameVariant) {
        if (!changeNameVariant.isEmpty()) {
            clickOnElement("(//div[@class='kit-section-content'])[1]");
        }
    }

    public int getIndexOfProduct(String nameProduct) {
        String xpath = "//div[contains(@class,'import-list')]//div[@class='product-content' and descendant::*[text()='" + nameProduct + "']]/preceding-sibling::div[@class='product-content']";
        int index = countElementByXpath(xpath) + 1;
        return index;
    }

    public void clickTabByIndex(int indexProduct, String nameTab) {
        String xpath = "";
        switch (nameTab) {
            case "Description":
                xpath = "//div[@class='product-content'][" + indexProduct + "]//div[@class='control-buttons vcenter']//p[text()='Description']";
                clickOnElement(xpath);
                break;
            case "Variants":
                xpath = "//div[@class='product-content'][" + indexProduct + "]//div[@class='control-buttons vcenter']//div[@class='control-button inside-button' and contains(p, 'Variants') ]";
                clickOnElement(xpath);
                break;
        }
    }

    public String getNameOfProductbyIndex(int index) {
        return getText(String.format(originalTitle, index));
    }

    public void verifyMsgError(String msg) {
        verifyTextPresent(msg, true);
    }

    public void actionImportList(String nameProduct, String btn) {
        clickOnElement("//a[contains(text(),'" + nameProduct + "')]//ancestor::div[contains(@class,'product-section')]//button[text()='" + btn + "']");
    }

    public int getCountProductImportList() {
        return countElementByXpath("//div[@class='product-content']");
    }

    public void closePopup() {
        String closeIcon = "//button[@class='s-modal-close is-large']";
        if (isElementExist(closeIcon)) {
            clickOnElement(closeIcon);
        }
    }

    public void selectTabInImportList(String tabName, int resOrder) {
        clickOnElement("(//div[@class='card__section section-tabs-and-actions']//*[contains(text(),'" + tabName + "')])[" + resOrder + "]");
        waitForEverythingComplete(60);
    }

    String des = "//div[@role='presentation' and @contenteditable = 'true']";

    public void enterDescription(String description) {
        waitClearAndType(des, description);
        clickOnElement("//div[@id='app-list']//h1[text()[normalize-space()='Import Products']]"); // instead of blur element method not working
        waitForEverythingComplete();
    }

    public void enterNewVariant(String originalVariant, String newVariants) {
        String variantList = "//td[@class='variant1-body']//input";
        String actualVariant = "";
        int num = 0;
        for (String _variant : getListText(variantList)) {
            actualVariant = getText(_variant);
            num++;
            if (originalVariant.equals(actualVariant)) {
                waitClearAndType("(//td[@class='variant1-body']//input)[" + num + "]", newVariants);
            }
        }
    }

    public void enterNewValueToBulkUpdatePrice(String value) {
        waitClearAndType("//div[@class='input-float']//input", value);
    }

    public void waitUntilMsgSuccessfullyDisplayed(String text) {
        verifyElementText(msg, text);
    }

    public void getVariantInforByName() {
        String variantList = "//td[@class='variant1-body']//input";
        String variantName = "";
        int num = 0;
        for (String _variant : getListText(variantList)) {
            variantName = getText(_variant);
//            getCostByVariant(variantName);
//            getPriceByVariant(variantName);
//            getProfitByVariant(variantName);
//            getPriceByVariant(variantName);
            num++;

        }
    }

    public void clickDeleteBtnOnPopUp() {
        String btnDelete = "(//button[text()[normalize-space()='Delete'] or descendant-or-self::*[text()[normalize-space()='Delete']]])[last()]";
        waitForEverythingComplete();
        clickOnElement(btnDelete);
    }

    public String getEnteredText(String label, int resOrder) {
        String xPathInputFieldWithLabel = "(//input[@id='" + label + "' or @type='" + label + "' or @placeholder='" + label + "' or contains(text(),'" + label + "') or preceding-sibling::*[text()[normalize-space()='" + label + "']] or preceding::*[text()[normalize-space()='" + label + "']] or @name='" + label + "'or preceding-sibling::*[text()[normalize-space()='" + label + "']]])[" + resOrder + "]";
        return getTextValue(xPathInputFieldWithLabel);
    }

    public String getEnteredDescription() {
        return getTextValue(des);
    }

    public void blurChangeTitleField() {
        clickOnElement("//div[@id='app-list']//h1[text()[normalize-space()='Import Products']]"); // instead of blur element method not working
    }

    public void clickOnIconDropdown(int index) {
        clickOnElement("(//button//span[contains(@class,'is-default')]//i)[" + index + "]");
    }

    public void selectOptionToBulkUpdate(String option, int index) {
        clickOnElement("(//div[contains(@class,'dropdown-menu')]//span[contains(@class,'dropdown-item') and normalize-space()='" + option + "'])[" + index + "]");
    }

    public void checkOnCheckboxByProdTitle(String title, boolean isChecked) {
        String status = "//div[@class='container product-details']//a[normalize-space()='" + title + "']/preceding::input[@type='checkbox'][1]";
        String checkbox = "//div[@class='container product-details']//a[normalize-space()='" + title + "']/preceding::input[@type='checkbox'][1]/following-sibling::span[1]";
        verifyCheckedThenClick(status, checkbox, isChecked);
    }

    public String actualTheNumberOfProdSelected() {
        return getText("//div[@class='tab-control']//div[@class='control-buttons']//div[1]");
    }

    public void clickOnBtnInActionsBar(String btn) {
        clickOnElement("//div[@class='tab-control']//*[text()[normalize-space()='" + btn + "']]");
        if (btn.equalsIgnoreCase("Remove from list")) {

        }
    }

    public void verifyToastMsgDisplayed(String msg) {
        String toastMsg = this.msg + "//*[text()[normalize-space()='" + msg + "']]";
        System.out.println("toastMsg = " + toastMsg);
        verifyElementPresent(toastMsg, true);
    }

    public int indexProduct(String prodName) {
        List<String> prodList = getListText("//div[@class='product-content']//div[@class='container product-details']//a");
        for (int i = 1; i < prodList.size(); i++) {
            if (prodList.get(i).equals(prodName)) {
                return i;
            }
        }
        return 1;
    }

    public boolean isBtnDisappear(String btnName) {
        return isElementVisible(xPathBtn("", btnName, 1), 7);
    }

    public void clickLinkAliExpress() {
        clickOnElement("//a[text()='AliExpress']");
    }
}
