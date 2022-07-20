package com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign;

import common.CommonPageObject;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.*;
import org.junit.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static common.utilities.LoadObject.getFilePath;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CampaignPage extends SBasePageObject {

    public CampaignPage(WebDriver driver) {
        super(driver);
    }

    String xpathDropdown = "//div[@class='action-table']//div[@class='s-dropdown-menu']//div[@class='s-dropdown-content']";
    String xpathActionBarInProductDetail = "//div[@class='action-bar__item']";
    String xpathEnableWidgetBtn = "//button[@class='s-button is-default']//span[text()[normalize-space()='Enable widget on my store']]";
    String xpathDisableWidgetBtn = "//*[text()[normalize-space()='Widget is enabled on your store']]/following-sibling::a[text()[normalize-space()='Disable']]";

    public void waitUntilInVisibleLoadingTable() {
        String xpath = "//*[contains(@class,'loading-table')]";
        int i = 0;
        while (isElementExist(xpath, 3)) {
            waitUntilElementInvisible(xpath, 50);
            i++;
            if (i == 8)
                break;
        }
    }

    public String getXpathParentVariant(String baseProduct, String variant) {
        String xpathParent = "(//div[text()='" + baseProduct + "']//ancestor::tr[@class='body-row']//following::tr[contains(@class,'variant-container')]//td[contains(@class,'variant-body') and text()[normalize-space()='" + variant + "']])[1]";
        return xpathParent;
    }

    public String getXpathParentBaseProduct(String baseProduct) {
        String xpathParent = "//div[text()[normalize-space()='" + baseProduct + "']]//ancestor::tr[@class='body-row']";
        return xpathParent;
    }

    public void clickActionBtn() {
        String xpathActionBtn = "//div[starts-with(@class,'action-table')]//span[contains(text(),'Action')]";
        clickOnElementByJs(xpathActionBtn);
        verifyElementPresent(xpathDropdown, true);
    }

    public void selectCheckbox(String campaignName) {
        String xpathCheckbox = "//span[text()='" + campaignName + "']//ancestor::td[contains(@class,'no-padding-important')]/preceding-sibling::td//span[@class='s-check']";
        if (isElementExist(xpathCheckbox)) {
            clickOnElement(xpathCheckbox);
            verifyActionTable();
        }
    }

    public void selectAllCampaign() {
        clickOnElement("//span[@data-label='Select all campaigns']//span[@class='s-check']");
    }

    public void expandBaseProductBlock(String baseProduct, boolean status) {
        String xpathParent = "//div[text()='" + baseProduct + "']//ancestor::tr[@class='body-row']";
        String xpathExpand = xpathParent + "//a[contains(text(),'Set individual price')]";
        String xpathCollapse = xpathParent + "//a[contains(text(),'See less')]";
        if (status == true) {
            if (isElementExist(xpathExpand)) {
                clickOnElement(xpathExpand);
            }
            verifyElementPresent(xpathCollapse, true);
        } else {
            if (isElementExist(xpathCollapse)) {
                clickOnElement(xpathCollapse);
            }
            verifyElementPresent(xpathExpand, true);
        }
    }

    public String getBaseCostVariant(String baseProduct, String variant) {
        String xpathBaseCost = getXpathParentVariant(baseProduct, variant) + "//following-sibling::td[contains(@class,'cost-body')]";
        return price(getText(xpathBaseCost));
    }

    public float getSalePriceVariant(String baseProduct, String variant) {
        float baseCost = Float.parseFloat(getBaseCostVariant(baseProduct, variant));
        String profitVariant = getProfitVariant(baseProduct, variant);
        String profitTarget = getTargetProfit(baseProduct);
        comparePrice(profitTarget, profitVariant);

        float salePrice = (baseCost + Float.parseFloat(profitTarget)) * 100.0f / 100.0f;
        return salePrice;
    }

    public float getCompareAtPriceVariant(String baseProduct, String variant, float targetDiscount) {
        float salePrice = getSalePriceVariant(baseProduct, variant);
        float compareAtPrice = (salePrice + targetDiscount) * 100.0f / 100.0f;
        return compareAtPrice;

    }

    public String getProfitVariant(String baseProduct, String variant) {
        String xpathProfitVariant = getXpathParentVariant(baseProduct, variant) + "//following-sibling::td[contains(@class,'profit-body')]";
        return price(getText(xpathProfitVariant));
    }

    public String getTargetProfit(String baseProduct) {
        String xpathTargetProfit = getXpathParentBaseProduct(baseProduct) + "//*[contains(@class,'profit-body')]";
        return price(getText(xpathTargetProfit));

    }

    public String getBaseCostProduct(String baseProduct) {
        String xpathBaseCostProduct = getXpathParentBaseProduct(baseProduct) + "//*[contains(@class,'cost-body')]";
        return price(getText(xpathBaseCostProduct));
    }

    public float getSalePriceProduct(String baseProduct) {
        float baseProductPrice = Float.parseFloat(getBaseCostProduct(baseProduct));
        float salePriceProduct = (baseProductPrice + Float.parseFloat(getTargetProfit(baseProduct))) * 100.0f / 100.0f;
        return salePriceProduct;

    }

    public void searchKeyword(String keyword) {
        String xpathSearchBox = "//input[@placeholder='Search orders']|//input[@placeholder='Search products']|//input[@placeholder='Search campaigns by name']";
        waitUntilElementVisible(xpathSearchBox, 10);
        waitTypeAndEnter(xpathSearchBox, keyword);
        waitForEverythingComplete(50);
    }

    public void searchCampaign(String nameCampaign) {
        String xpath = "//input[@placeholder='Search orders']|//input[@placeholder='Search products']|//input[@placeholder='Search campaigns by name']";
        waitClearAndType(xpath, nameCampaign);
        waitABit(3000);
        getDriver().findElement(By.xpath(xpath)).sendKeys(Keys.RETURN);
        waitABit(2000);
    }

    public void searchCampaignOnStorfont(String nameCampaign) {
        String xpath = "//input[@placeholder='Search']";
        waitClearAndType(xpath, nameCampaign);
        waitABit(3000);
        getDriver().findElement(By.xpath(xpath)).sendKeys(Keys.RETURN);
        waitABit(2000);
    }

    public void selectCampaignOnStorefront(String nameCampaign) {
        int count = countCampainOnSF(nameCampaign);
        for (int i = 1; i <= count; i++) {
            String xpath = "//div[contains(@class,'col-sm-3')][1]//span[normalize-space()='" + nameCampaign + "']";
            waitElementToBePresent(xpath);
            clickOnElementByJs(xpath);
            waitForEverythingComplete();
        }

    }

    public void selectCampaign(String nameCampaign) {
        String xpath = "//div[@class='d-flex align-items-center']//span[normalize-space()='" + nameCampaign + "']";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void waitUntilListTableVisible() {
        String xpath = "//div[@id='products-results']//*[@id='all-products']|//div[@class='order-manager']//div[contains(@class,'orders-list')]";
        waitUntilElementVisible(xpath, 30);
    }

    public void verifyResultSearch(String keyword) {
        String xpath = "//div[@class='product-name']//span[text()=\"" + keyword + "\"]|//div[contains(@class,'orders-list')]//a[@class='order-link'][contains(text(),\"" + keyword + "\")]";
        verifyElementPresent(xpath, true);
    }

    public boolean isResultSearchExit(String keyword) {
        String xpath = "//div[@class='product-name']//span[text()='" + keyword + "']|//div[contains(@class,'orders-list')]//a[@class='order-link'][contains(text(),'" + keyword + "')]";
        return isElementExist(xpath);
    }

    public void addProductToCampaign(String product) {
        String xpathProduct = "//span[normalize-space()=\"" + product + "\"]/ancestor::div[@class='prod-wrap']//button[contains(.,\"%s\")]";
        clickOnElementByJs(String.format(xpathProduct, "Add product"));
        verifyElementPresent(String.format(xpathProduct, "Remove product"), true);
    }

    public void removeProductToCampaign(String product) {
        String xpathProduct = "//span[contains(text(),\"" + product + "\")]/ancestor::div[@class='prod-wrap']//button[contains(.,\"%s\")]";
        clickOnElementByJs(String.format(xpathProduct, "Remove product"));
        verifyElementPresent(String.format(xpathProduct, "Add product"), true);
    }

    public int countBaseProduct() {
        return countElementByXpath("//div[@class='new-product m-t']//div[child::div[contains(@class,'prod-uploader__content__list')]]/div[contains(@class,'phub-design')]");
    }

    public void addAnArtWork(String sArtworks) {
        String artwork[] = sArtworks.split(",");
        for (int i = 0; i < artwork.length; i++) {
            addArtWork(artwork[i]);
        }
    }

    public void addArtWorkForBulk(String sArtworks) {
        String artwork[] = sArtworks.split(",");
        String xpath = "//input[@type='file']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + sArtworks);
        clearTextByJS(xpath);
    }

    private void addArtWork(String art) {
        String xpathAddArtworkBtn = "//div[@class='artwork-btn']/button";
        String xpathList = "//div[@class='artwork-list']";
        if (isElementExist(xpathAddArtworkBtn)) {
            clickOnElement(xpathAddArtworkBtn);
            waitForEverythingComplete();
        }
        if (!isElementExist(xpathList) || !isShowArtworkLibraryPopup()) {
            String xpath = "(//input[@type='file'])[1]";
            changeStyle(xpath);
            chooseAttachmentFile(xpath, "phub" + File.separator + art);
            waitABit(3000);
            clearTextByJS(xpath);
        }
        waitForEverythingComplete();
        if (isElementExist("//div[contains(@class,'artwork-progress-bar')]")) {
            waitUntilElementInvisible("//div[contains(@class,'artwork-progress-bar')]", 50);
            waitABit(2000);
        }

        String artwork = art.replace(".png", "").replace(".jpg", "");
        String imageArtwork = "(//*[contains(text(),'" + artwork + "')]//ancestor::div[@class='artwork']//div[@class='artwork-image'])[1]";
        if (isElementExist(imageArtwork)) {
            clickOnElement(imageArtwork);
        }
        if (isElementExist("//div[contains(@class,'in-process-animation')]", 5)) {
            waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 90);
            waitUntilElementVisible("//div[@class='phub-design']//*[contains(@class,'fix-display')]//*[text()[normalize-space()='Change artwork']]", 90);
        }
    }

    public void editDesign(String design) {
        String xpath = "//*[@data-label='" + design + "']//button";
        clickOnElement(xpath);
    }

    public int countProductSize(String sProduct) {
        String xpathSize = "//h3[text()='" + sProduct + "']//ancestor::div[contains(@class,'editor-side-bar')]//div[contains(@class,'size-section')]//button";
        return countElementByXpath(xpathSize);
    }

    public void selectBaseProduct(String sProducts) {
        String xpath = "//span[text()='" + sProducts + "']//preceding::a[contains(@class,'img-thumbnail')]";
        String xpathbuttonBack = "//i[@class='mdi mdi-chevron-left mdi-36px']";
        try {
            clickOnElementByJs(xpath);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            System.out.println(" click lỗi");
            clickOnElementByJs(xpath);
        }
        clickOnElement(xpathbuttonBack);
    }


    public void selectSize(String sProducts, String size, boolean isSelect) {
        String xpath = XpathEditorProductBlock(sProducts) + "//div[contains(@class,'size-section')]//span[text()[normalize-space()='" + size + "']]//parent::button";
        boolean status = getStatusProductSize(sProducts, size);
        if (status != isSelect) {
            clickOnElement(xpath);
        }

    }

    public int countColorSelected(int index) {
        String xpath = "(//div[contains(@class,'phub-design-container')])[" + index + "]//div[@class='color-section']/button";
        return countElementByXpath(xpath);
    }

    public int countSizeSeleted(int index) {
        String xpath = "(//div[contains(@class,'phub-design-container')])[" + index + "]//div[contains(@class,'select-size')]/button[contains(@class,'active')]";
        return countElementByXpath(xpath);
    }

    private boolean getStatusProductSize(String sProduct, String size) {
        String xpath = XpathEditorProductBlock(sProduct) + "//div[contains(@class,'size-section')]//span[text()[normalize-space()='" + size + "']]//parent::button";
        String status = getAttributeValue(xpath, "class");
        if (status.contains("active")) {
            return true;
        }
        return false;
    }

    public void enterTags(String sTag) {
        if (!sTag.isEmpty()) {
            String xpath = "//input[@placeholder='Separate your keywords using a comma']";
            waitTypeAndEnter(xpath, sTag);
        }
    }

    public void waitUntilImageLoadingSuccess() {
        waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 500);
    }

    String xpathPricing = "//div[text()[normalize-space()='%s']]//ancestor::tr";

    public void enterSalePrice(String sSalePrice, String sProduct) {
        String xpath = String.format(xpathPricing, sProduct) + "//td[contains(@class,'sale-body')]//input";
        waitClearAndType(xpath, sSalePrice);
    }

    public void enterCompareSalePrice(String sCompareAtPrice, String sProduct) {
        String xpath = String.format(xpathPricing, sProduct) + "//td[contains(@class,'compare-body')]//input";
        waitClearAndType(xpath, sCompareAtPrice);
    }

    public void clickStep(String label) {
        clickLinkTextWithLabel("//div[@class='step-buttons']", label, 1);
    }

    public String getStatusCampaign(String campaignName) {
        String xpathLiveStatus = "(//span[text()[normalize-space()='" + campaignName + "']])[1]//following-sibling::span[@data-label='View on Online Store']";
        String xpathStatus = "//span[text()[normalize-space()='" + campaignName + "']][1]//following::div[contains(@class,'product-status')]";
        String status = "";
        if (isElementExist(xpathLiveStatus)) {
            status = "LIVE";
        } else if (isElementExist(xpathStatus)) {
            status = getText(xpathStatus).toUpperCase();
        }
        return status;
    }

    public String getStatusCamp(String campaignName) {
        String xpathStatus = "//span[text()[normalize-space()='" + campaignName + "']][1]//following::div[contains(@class,'product-status')] | //tr[1]//span[text()='" + campaignName + "']//following::div[contains(@class,'product-status')][1]";
        return getText(xpathStatus).toUpperCase();
    }

    public boolean isCampaignLive(String campaignName) {
        String xpathLiveStatus = "(//span[text()[normalize-space()='" + campaignName + "']])[1]//following-sibling::span[@data-label='View on Online Store']";
        return isElementExist(xpathLiveStatus);
    }

    public void verifyTabActive(String tabname) {
        String xpath = "//div[@class='step-button active' and child::*[text()='" + tabname + "']]";
        waitUntilElementVisible(xpath, 15);
        verifyElementPresent(xpath, true);
    }

    public void openAndCloseColorTable(String sProducts) {
        String xpath = XpathEditorProductBlock(sProducts) + "//div[@class='color-section']//div[@role='button']";
        clickOnElement(xpath);
    }

    public void selectColorProduct(String sProduct, List<String> colors) {
        openAndCloseColorTable(sProduct);
        for (int i = 1; i <= 8; i++) {
            String xpathColor = XpathEditorProductBlock(sProduct) + "//div[@class='s-dropdown-body']//span[contains(@class,'s-dropdown-item')][" + i + "]";
            hoverOnElement(xpathColor);
            String xpathColorName = XpathEditorProductBlock(sProduct) + "//div[@class='s-dropdown-header']//span";
            String colorName = getText(xpathColorName);
            if (colors.contains(colorName)) {
                selectRadioButtonPhub(xpathColor, true);
                Assert.assertEquals(getAttributeValue(xpathColor, "class").contains("active"), true);
            } else {
                selectRadioButtonPhub(xpathColor, false);
            }
        }
    }


    String xpathCampaign = "(//tbody//tr)[%d]";

    public void verifyBtnDuplicateEnable(boolean isEnable, int index) {
        boolean isActual = true;

        String xpathDuplicateBtn = String.format(xpathCampaign, index) + "//span[@data-label='Duplicate']//span|//div[@class='action-bar__item']//button[@type='button']//span[contains(text(),'Duplicate')]";
        if (getAttributeValue(xpathDuplicateBtn, "class").contains("text-muted")) {
            isActual = false;
        }
        Assert.assertEquals(isActual, isEnable);

    }

    public void verifyBtnBulkDuplicateEnable(boolean isEnable, int index) {
        boolean isActual = true;
        String xpathBulkDuplicateInProductDetail = "//div[@class='action-bar__item']//span[contains(text(),'Bulk duplicate')]/ancestor::button";
        String t = getAttributeValue(String.format(xpathCampaign, index) + "//span[@data-label='Bulk duplicate']//*[contains(@class,'icon')]|//div[@class='action-bar__item']//button[@type='button']//span[contains(text(),'Bulk duplicate')]|//span[@data-label='Do not support two-side AOP campaigns' or @data-label='Do not support back side']//*[contains(@class,'icon')]|//span[@data-label='Do not support multiple styles with AOP']//*[contains(@class,'icon')]", "class");
        if (t.contains("text-muted") || t.contains("is-dark")) {
            isActual = false;
        }
        if (isElementExist(xpathBulkDuplicateInProductDetail)) {
            String t1 = getAttributeValue(xpathBulkDuplicateInProductDetail, "class");
            if (t1.contains("is-disabled")) {
                isActual = false;
            } else isActual = true;
        }
        assertThat(isActual).isEqualTo(isEnable);
    }

    public void clickBulkDuplicateIcon(String campaignName) {
        waitABit(2000);
        String xpathBulkduplicateBtn = "(//span[text()='" + campaignName + "']/ancestor::tr)[1]//span[@data-label='Bulk duplicate']//span";
        String xpathBulkDuplicateBtn_2 = "//button//span[normalize-space()='Bulk duplicate']";
        if (isElementExist(xpathBulkduplicateBtn)) {
            clickOnElementByJs(xpathBulkduplicateBtn);
        } else {
            clickOnElementByJs(xpathBulkDuplicateBtn_2);
        }
        waitForEverythingComplete();
        waitUntilElementVisible("//div[contains(@class,'container-upload-design')]//label[@for='upload']", 30);
    }

    public void clickDuplicateIcon(String campaignName) {
        String xpathDuplicateBtn = "(//span[text()='" + campaignName + "']/ancestor::tr)[1]//span[@data-label='Duplicate']//i | //button//span[normalize-space()='Duplicate']";
        if (isElementExist(xpathDuplicateBtn, 5)) {
            waitUntilElementVisible(xpathDuplicateBtn, 20);
            clickOnElement(xpathDuplicateBtn);
        }
        waitForEverythingComplete();
    }

    public void clickEditIcon(int index) {
        waitForEverythingComplete();
        clickOnElement(String.format(xpathCampaign, index) + "//div[contains(@class,'vcenter hcenter col-icon')]//div/a/i[@class='kit-icon kit-icon-16 kit-icon-16-edit']");
        waitForPageLoad();
        waitForEverythingComplete();
    }

    public int countVariantInApp() {
        String variant = getText("//div[@class='prod-uploader wl-design']/div/span[contains(text(),'Total Variants:')]");
        return Integer.parseInt(variant.replace("Total Variants:", "").replace("/250", "").trim());
    }


    public int getIndexCampaign(String campaignName) {
        String xpath = "//div[@class='table-row' and child::div[contains(@class,'campaign-body')][descendant::*[text()='" + campaignName + "']]]/preceding-sibling::div[@class='table-row']";
        return countElementByXpath(xpath) + 1;
    }

    public boolean uploadFailed() {
        return isElementExist("//div[@class='phub-design']//span[contains(text(),'we have problems uploading your artwork. Please try again or contact us for more information')]");

    }

    public void clickButton(String btnName) {
        clickOnElement("(//*[child::*[contains(@class,'heading')]]//*[text()[normalize-space()='" + btnName + "']]|//button[preceding-sibling::*[text()[normalize-space()='" + btnName + "']] or descendant-or-self::*[text()[normalize-space()='" + btnName + "']]])[1]");
    }

    public void selectProduct(String productName) {
        clickOnElement("(//span[normalize-space(.)=\"" + productName + "\"]//ancestor::a//img)[1]|//div[contains(@class,'product')]//a[text()[normalize-space()=\"" + productName + "\"]]");
        waitForEverythingComplete();
        waitForPageLoad();
    }

    public void waitUntilInvisibleLoadingPBase(int timeoutInSeconds) {
        String xpath = "//div[contains(@class,'in-process-animation')]";
        if (isElementExist(xpath, 5))
            waitUntilElementInvisible(xpath, timeoutInSeconds);
    }

    public void waitUntilProductTableVisible() {
        String xpath = "//div[@id='products-results']//*[@id='all-products']";
        waitUntilElementVisible(xpath, 15);
    }

    public boolean isShowArtworkLibraryPopup() {
        String xpathArtworkLibraryPopup = "//div[@class='s-modal-body']//div[@class='artwork-library']";
        return isElementExist(xpathArtworkLibraryPopup, 10);
    }

    public void closeArtworkLibraryPopup() {
        String xpathCloseArtworkLibraryPopup = "//button[@type='button' and contains(@class,'s-modal-close')]";
        clickOnElement(xpathCloseArtworkLibraryPopup);
        waitForEverythingComplete();
    }

    public void clickBtnViewOnStore() {
        String xpathClick = "(//*[@id='all-products']//div[@class='product-name']//i)[1]";
        String xpathHover = "(//*[@id='all-products']//div[@class='product-name']//span)[1]";
        hoverThenClickElement(xpathHover, xpathClick);
    }

    public void switchToNewTabOnBrowser() {
        switchToLatestTab();
        try {
            waitForEverythingComplete();
        } catch (Exception e) {
            waitForEverythingComplete();
        }
        waitForEverythingComplete();
        waitForPageLoad();
    }

    public void clickBtnNewCampaign() {
        String xpath = "//button[@type='button']//span[text()[normalize-space()='New campaign' or normalize-space()='Create new campaign' or normalize-space()='建立新推广计划']]";
        try {
            clickOnElement(xpath);
        } catch (Exception e) {
            clickOnElement(xpath);
        }
    }

    public void clickCampaignName(String campaignName) {
        String xpathCampaignName = "//table[@id='all-products']//*[contains(@class,'no-padding-important')]//div[@class='product-name']//span[text()[normalize-space()='" + campaignName + "']]";
        clickOnElement(xpathCampaignName);
        waitForEverythingComplete();
    }

    public void waitUntilDisplayProductDetailPage(String campaignName) {
        String xpathTitle = "//div[contains(@class,'add-product-page')]/*[text()[normalize-space()=\"" + campaignName + "\"]]|//div[@id='create-product']//*[text()[normalize-space()='Edit Campaign']]";
        waitUntilElementVisible(xpathTitle, 50);
    }

    public void verifySKU(String sku) {
        String xpathSKU = "//*[contains(@title,\"" + sku + "\")]";
        verifyElementContainsText(xpathSKU, sku);
    }

    public String getVariantPriceCampaignDetail(String sku) {
        String xpathPrice = "//td[following-sibling::*[contains(@title,\"" + sku + "\")]][last()]";
        return getText(xpathPrice);
    }

    public void openProductDetailOnStorefront() {
        String xpathShowCampaignBtn = "//a[contains(@class,'s-button')]/span[contains(text(),'View')]";
        if (isElementExist(xpathShowCampaignBtn)) {
            int i = 0;
            System.out.println("Tab" + countNumberOfWWindow());
            while (countNumberOfWWindow() == 1) {
                waitUntilElementVisible(xpathShowCampaignBtn, 20);
                clickOnElementByJs(xpathShowCampaignBtn);
                waitABit(5000);
                i++;
                System.out.println("click lan" + i);
                if (i == 3)
                    break;
            }
        }
    }

    public int countVariantOnStore() {
        String xpathVariantBlock = "//input[@type='checkbox']//ancestor::tr";
        int countVariantOnStore = countElementByXpath(xpathVariantBlock) - 1;
        return countVariantOnStore;
    }

    public void verifyDescription(String descriptions) {
        switchToIFrame("//div[@class='tox-edit-area']/iframe");
        String xpathDescription = "//body[@id='tinymce']//p[contains(text(),\"" + descriptions + "\")]";
        verifyElementPresent(xpathDescription, true);
        switchToIFrameDefault();
    }

    public void verifyActionTable() {
        String xpathActionTable = "//*[@class='disabled-section']//div[contains(@class,'action-table')]";
        verifyElementPresent(xpathActionTable, true);
    }

    public void clickDeleteSelectedCampaignBtn() {
        String xpathDeleteSelectedCampaignBtn = xpathDropdown + "//span[@class='s-dropdown-item' and contains(text(),'Delete selected campaigns')]";
        clickOnElement(xpathDeleteSelectedCampaignBtn);
        verifyElementPresent("//*[@class='s-modal-card-title' and contains(text(),'Delete')]", true);
    }

    public void clickMakeCampaignAvailableBtn() {
//        String xpathMakeCampaignAvailableBtn = xpathDropdown + "//span[@class='s-dropdown-item' and contains(text(),'Make  campaign available')]";
        String xpathMakeCampaignAvailableBtn = xpathDropdown + "//span[@class='s-dropdown-item' and (contains(text(),'Make 1 campaign available'))]";
        clickOnElement(xpathMakeCampaignAvailableBtn);
        verifyElementPresent("//*[@class='s-modal-card-title' and contains(text(),'available')]", true);

    }


    public void clickMakeCampaignUnavailableBtn() {
        String xpathMakeCampaignUnavailableBtn = xpathDropdown + "//span[@class='s-dropdown-item' and (contains(text(),'Make 1 campaign unavailable') or contains(text(),'Make 1 campaigns unavailable'))]";
        clickOnElement(xpathMakeCampaignUnavailableBtn);
        waitABit(2000);
        verifyElementPresent("//*[@class='s-modal-card-title' and contains(text(),'unavailable')]", true);
    }


    public String XpathEditorProductBlock(String product) {
        return "//h3[text()='" + product + "']//ancestor::div[contains(@class,'editor-side-bar')]";
    }

    public void enterDescription(String sDescription) {
        switchToIFrame("//div[@class='tox-edit-area']/iframe");
        waitClearAndType("//body[@id='tinymce']", sDescription);
        switchToIFrameDefault();
    }

    public List<String> getListSizeSelected(String sProducts) {
        int n = countProductSize(sProducts);
        List<String> listSizeSelected = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String xpathSizeActive = XpathEditorProductBlock(sProducts) + "//div[contains(@class,'size-section')]//button[" + i + "]";
            String xpathSize = xpathSizeActive + "/span";
            if (getAttributeValue(xpathSizeActive, "class").contains("active")) {
                listSizeSelected.add(getText(xpathSize));
            }
        }
        return listSizeSelected;
    }

    String xpath_catalog = "//div[@class='product-catagory']//p[text()='%s']";

    public void switchToTabOnCatalog(String sCategory) {
        String xpathParent = String.format(xpath_catalog, sCategory) + "//ancestor::li";
        String xpathProduct = "";
        if (!getAttributeValue(xpathParent, "class").contains("is-active")) {
            clickOnElement(xpathParent);
            int n = 0;
            switch (sCategory) {
                case "Apparel":
                    xpathProduct = "//span[normalize-space()=\"Unisex T-shirt\"]";
                    break;
                case "Home & Living":
                    xpathProduct = "//span[normalize-space()=\"Star Ornament\"]";
                    break;
                case "Accessories":
                    xpathProduct = "//span[normalize-space()=\"Sleep Mask\"]";
                    break;
                case "Shoes":
                    xpathProduct = "//span[normalize-space()=\"Low Top Sneakers Black\"]";
                    break;
                case "Drinkware":
                    xpathProduct = "//span[normalize-space()=\"Shining Tumbler 20oz\"]";
                    break;
                case "Phone Cases":
                    xpathProduct = "//span[normalize-space()=\"iPhone 12 Case\"]";
                    break;
                case "Jewelry":
                    xpathProduct = "//span[normalize-space()=\"Heart Necklace With Box\"]";
                    break;
                default:
                    xpathProduct = "//span[normalize-space()=\"Short Socks\"]";
                    break;
            }
            waitABit(3000);
            while (!isElementExist(xpathProduct) && n < 2) {
                refreshPage();
                clickOnElement(xpathParent);
                waitABit(3000);
                n++;
                clickOnElement(xpathProduct);
            }
        }
    }

    public void selectSizeProduct(String sProducts, List<String> sSizes) {
        int n = countProductSize(sProducts);
        List<String> listSizes = getListSizeSelected(sProducts);

        for (String size : sSizes) {
            selectSize(sProducts, size, true);
        }
        for (String size1 : listSizes) {
            if (!sSizes.contains(size1)) {
                selectSize(sProducts, size1, false);
            }
        }
    }

    public void switchToTabOnCampaignsPage(String nameTab) {
        String xpathParent = "//*[@class='s-tabs-nav is-left']//span[child::p[text()='" + nameTab + "']]";
        String xpathTab = "//*[@class='s-tabs-nav is-left']//p[text()='" + nameTab + "']//ancestor::li/a";
        refreshPage();
        closePopup();
        if (!getAttributeValue(xpathParent, "class").contains("is-active")) {
            clickOnElement(xpathTab);
        }
        waitUntilListTableVisible();
    }

    public void verifyShowCampaign(String campaignName, boolean status) {
        String xpathCampaign = "//span[text()[normalize-space()='" + campaignName + "']][1]";
        verifyElementPresent(xpathCampaign, status);
    }

    public boolean isExistProduct(String campaignName) {
        String xpathProductName = "(//div[@class='product-name']//span[text()[normalize-space()=\"" + campaignName + "\"]])[1]";
        return isElementExist(xpathProductName);
    }

    public void verifyStatusCampaign(String labelName) {
        String xpathCampaignBlock = "//tbody//tr";

        int countNumberCampaign = countElementByXpath(xpathCampaignBlock);
        if (countNumberCampaign != 0) {
            for (int i = 1; i <= countNumberCampaign; i++) {
                String getStatus = getText("(//div[contains(@class,'product-status')])[" + i + "]");
                Assert.assertEquals(getStatus.toUpperCase(), labelName.toUpperCase());
            }
        }

    }

    public boolean verifyCampaignListNull() {
        String xpathNoCampaign = "//table[@id='all-products']//*[@class='no-product']";
        return isElementExist(xpathNoCampaign);
    }

    public void verifyShowMessage(String message) {
        String xpath = "//div[@class='s-notices is-bottom']//div[text()[normalize-space()='" + message + "']]";
        waitUntilElementVisible(xpath, 90);
    }

    public void enterProvideNameForNewCampaign(String newName) {
        String xpath = "//label[text()='Provide a name for your new campaign']//following::div[@class='s-form-item__content']//input";
        waitABit(3000);
        waitClearAndType(xpath, newName);
        waitForEverythingComplete();
    }

    public List<String> getListSelectedColors(String sProduct) {
        List<String> listSelectedColors = new ArrayList<String>();
        String xpathColorSelected = XpathEditorProductBlock(sProduct) + "//div[@class='color-section']/button";
        for (int i = 1; i <= countElementByXpath(xpathColorSelected); i++) {
            String xpathColor = XpathEditorProductBlock(sProduct) + "//div[@class='s-dropdown-body']//span[contains(@class,'s-dropdown-item')][" + i + "]";
            System.out.println(getAttributeValue(xpathColor, "background-color"));
            String colorName = convertToGRBColor(getAttributeValue(xpathColor, "color"));
            System.out.println("Ma mau actual" + colorName);
            listSelectedColors.add(colorName);
        }
        return listSelectedColors;

    }

    public List<String> getSelectedColors(String sProduct) {
        List<String> listSelectedColors = new ArrayList<String>();
        openAndCloseColorTable(sProduct);
        String xpathColorSelected = XpathEditorProductBlock(sProduct) + "//div[@class='color-section']//button[contains(@class,'active')]";
        for (int i = 1; i <= countElementByXpath(xpathColorSelected); i++) {
            String xpathColor = XpathEditorProductBlock(sProduct) + "//div[@class='s-dropdown-body']//span[contains(@class,'s-dropdown-item')][" + i + "]";
            hoverOnElement(xpathColor);
            waitForEverythingComplete();
            String xpathColorName = XpathEditorProductBlock(sProduct) + "//div[@class='s-dropdown-header']//span";
            String colorName = getText(xpathColorName);
            listSelectedColors.add(colorName);
        }
        return listSelectedColors;

    }

    public void waitForLaunchCampaignEnabled() {
        String xpathDisabledLaunchCampaign = "//span[contains(text(),'Launch campaigns')]/ancestor::button[@disabled='disabled']";
        waitUntilElementInvisible(xpathDisabledLaunchCampaign, 50);
        if (isElementExist(xpathDisabledLaunchCampaign))
            waitUntilElementInvisible(xpathDisabledLaunchCampaign, 50);
    }

    public boolean isExistActionTable() {
        String xpathActionTable = "//*[@class='disabled-section']//div[@class='action-table']";
        return isElementExist(xpathActionTable);
    }


    public void verifyShowSizeChartBlock() {
        String xpathSizeChartBlock = "//div[contains(@class,'size-chart-wrapper')]";
        waitForEverythingComplete();
        verifyElementPresent(xpathSizeChartBlock, true);

    }

    public void clickEnableSizeChartBtn() {
        if (isElementExist(xpathEnableWidgetBtn)) {
            clickOnElement(xpathEnableWidgetBtn);
            waitForEverythingComplete();
        }
        verifyElementPresent(xpathDisableWidgetBtn, true);

    }

    public void clickDisableSizeChartBtn() {
        if (isElementExist(xpathDisableWidgetBtn)) {
            clickOnElement(xpathDisableWidgetBtn);
            waitForEverythingComplete();
        }
        verifyElementPresent(xpathEnableWidgetBtn, true);
    }

    public void verifyOrdinalNumbersOfCampaign(String campaignName, String ordinalNumbers) {
        String xpathCampaignName = "(//div[@class='product-name']//span[@data-label='View on Online Store']/preceding-sibling::span)[" + ordinalNumbers + "]";
        String expectedCampignName = getText(xpathCampaignName);
        Assert.assertEquals(expectedCampignName, campaignName);
    }

    public void selectFilePsd(String artworkName) {
        clickOnElementByJs("//span[@data-label='" + artworkName + "']//ancestor::div[@class='artwork']//div[@class='artwork-image']");
    }

    public void verifyShowOption1(String option1) {
        String xpath = "//label[contains(@class,'flex-grow has-text-weight-bold') and text()[normalize-space()='" + option1 + "']]";
        System.out.println(xpath);
        verifyElementPresent(xpath, true);
    }

    public void verifyShowOption2(String option2) {
        String xpath = "//div[contains(@class,'label mb5 has-text-weight-bold') and text()[normalize-space()='" + option2 + "']]|//label[contains(@class,'flex-grow has-text-weight-bold') and text()[normalize-space()='" + option2 + "']]";
        System.out.println(xpath);
        verifyElementPresent(xpath, true);
    }

    public boolean isShowAddMoreProduct() {
        String xpath = "//div[@class='s-modal-body']//*[text()[normalize-space()='Add more products']]";
        return isElementExist(xpath);
    }

    public void waitUntilInvisibleLaunching() {
        String xpathLaunching = "//span[@class='s-icon icon-loading is-small']";
        int i = 0;
        while (isElementExist(xpathLaunching)) {
            waitUntilElementInvisible("//span[@class='s-icon icon-loading is-small']", 50);
            System.out.println("lan" + i);
            waitABit(20000);
            i++;
            if (i == 10)
                break;
        }
    }

    public void waitForProcessingInVisible() {
        String xpathProcessing = "//div[@class='in-process-animation s-button is-primary pull-right']";
        waitUntilElementInvisible(xpathProcessing, 50);
        int i = 0;
        while (isElementExist(xpathProcessing)) {
            waitUntilElementInvisible(xpathProcessing, 50);
            i++;
            if (i == 5)
                break;
        }
    }

    public void inputSalePrice(String product, String variant, String sSalePrice) {
        String xpath = "//div[contains(text(),\"" + product + "\")]/ancestor::tr/following::tr/td[contains(text(),\"" + variant + "\")]/following-sibling::td[@class='sale-body']//input";
        waitClearAndTypeThenTab(xpath, sSalePrice);
    }

    public void inputComparePrice(String product, String variant, String comparePrice) {
        String xpath = "//div[contains(text(),\"" + product + "\")]/ancestor::tr/following::tr/td[contains(text(),\"" + variant + "\")]/following-sibling::td[@class='compare-body text-normal']//input";
        waitClearAndTypeThenTab(xpath, comparePrice);
    }


    public void clickSetIndividualPriceInPricing(String product) {
        String xpath = "//div[contains(text(),\"" + product + "\")]/following-sibling::div[@class='variant-direction-wrapper']";
        clickOnElementByJs(xpath);
    }

    public void clickButtonFrontInDesign() {
        String xpath = "//div[@id='konva-editor']//button[contains(.,\"front\") or contains(.,\"Front\")]";
        waitUntilElementVisible(xpath, 50);
        clickOnElementByJs(String.format(xpath));
    }

    public void clickButtonBackDesign() {
        String xpath = "//div[@id='konva-editor']//button[contains(.,\"back\") or contains(.,\"Back\")]";
        clickOnElementByJs(String.format(xpath));
    }

    public void addVariantSize(String variant) {
        String xpath = "//button[@type='button']//span[normalize-space()='" + variant + "']";
        clickOnElement(xpath);
    }

    public void removeColor() {
        String xpath = "(//p[child::span[text()='Unisex Hoodie']]//following-sibling::div//div[@class='color-section']//button)[2]";
        clickOnElement(xpath);
    }

    public void uncheckSearchEngine(boolean isCheck) {
        String xpath = "//span[contains(text(),'Search Engine Bot Crawlers, Sitemap files')]//preceding-sibling::span";
        String xpath_check = "//span[contains(text(),'Search Engine Bot Crawlers, Sitemap files')]//preceding-sibling::span";
        waitElementToBePresent(xpath);
        verifyCheckedThenClick(xpath_check, xpath, isCheck);
    }

    public void uncheckOnlineStore(boolean isCheck) {
        String xpath = "//span[child::p//span[contains(text(),'Online store listing pages')]]//preceding-sibling::span[@class='s-check']";
        String xpath_check = "//span[child::p//span[contains(text(),'Online store listing pages')]]//preceding-sibling::span[@class='s-check']";
        waitElementToBePresent(xpath);
        verifyCheckedThenClick(xpath_check, xpath, isCheck);
    }

    public void clickDuplicate() {
        String xpath = "//button[@class='s-button is-primary']//span[normalize-space()='Duplicate']";
        clickOnElement(xpath);
    }

    public void verifyUncheckSearchEngine(boolean isCheck) {
        String xpath = "//span[normalize-space()='Search Engine Bot Crawlers, Sitemap files']//preceding-sibling::span";
        verifyElementSelected(xpath, isCheck);
    }

    public void verifyUncheckOnlineStore(boolean isCheck) {
        String xpath = "//span[child::p//span[normalize-space()='Online store listing pages']]//preceding-sibling::span[@class='s-check']";
        verifyElementSelected(xpath, isCheck);
    }

    public void verifyStatus(String campaignName, String status) {
        String xpath = "//div[normalize-space()='" + campaignName + "' and @class='product-name']//following-sibling::div";
        verifyElementText(xpath, status);
    }

    public void verifyTags(String tag) {
        String xpath = "//div[contains(@class, 'tag-list-items')]//span[text()[normalize-space()='" + tag + "']]";
        waitUntilElementVisible(xpath, 5);
        verifyElementVisible(xpath, false);
    }

    public void removeTag(String tag) {
        String xpath = "//span[@class='s-tag tag-list-item applied-tag is-medium' and normalize-space()='" + tag + "']//following-sibling::a";
        clickOnElement(xpath);
    }

    public void selectAction(String action) {
        String xpath = "//div[@class='s-dropdown-content']//span[normalize-space()='" + action + "']";
        clickOnElement(xpath);
    }

    public void verifyCampaignName(String campaignName) {
        String xpath = "//h1[normalize-space()='" + campaignName + "']";
        waitElementToBePresent(xpath);
        String name = campaignName.toUpperCase();
        verifyElementText(xpath, name);
    }

    public void verifyCampaignPrice(String price) {
        String xpath = "//span[normalize-space()='" + price + "']";
        verifyElementPresent(xpath, true);
    }

    public void verifyCampaignSize(String size) {
        String xpath = "//div[@class='flex flex-wrap align-center']//button[normalize-space()='" + size + "']";
        verifyElementPresent(xpath, true);
    }

    public void verifyCampaignColor(String color) {
        String xpath = "//div[@class='flex flex-wrap align-center']//button[@arialabel='Select " + color + "']";
        verifyElementPresent(xpath, true);
    }

    public void verifyVariants(String variants) {
        String[] optionValue = variants.split(",");
        int multi = optionValue.length;
        String xpathLabel = "";
        if (!variants.equals("")) {
            if (multi == 1) {
                xpathLabel = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "']])//span[@class='s-check']";
                verifyElementPresent(xpathLabel, false);
            } else if (multi == 2) {
                xpathLabel = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "']])//span[@class='s-check']";
                verifyElementPresent(xpathLabel, false);
            } else if (multi == 3) {
                xpathLabel = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "'] and child::td[normalize-space()='" + optionValue[2].trim() + "']])//span[@class='s-check']";
                verifyElementPresent(xpathLabel, false);
            }
        }
    }

    String labelOptionText = "//div[child::label[contains(@class,'flex-grow has-text-weight-bold') and contains(.,\"%s\")]]";
    String labelOptionPictureChoice = "//div[contains(@class,'base-picture__label') and contains(text(),\"%s\")]";
    String labelOptionImage = "//div[@class='base-upload' and contains(.,\"%s\")]";

    public Boolean isExistLabelOptionText(String label) {
        String xpath = String.format(labelOptionText, label);
        return isElementExist(xpath);
    }

    public void InputOptionText(String label, String text) {
        String xpath = String.format(labelOptionText, label) + "/following-sibling::input";
        String xpathArea = String.format(labelOptionText, label) + "/following-sibling::textarea";
        try {
            waitClearAndTypeThenTab(xpath, text);
        } catch (Exception e) {
            waitClearAndTypeThenTab(xpathArea, text);
        }

    }

    public Boolean isExistLabelOptionPictureChoice(String label) {
        String xpath = String.format(labelOptionPictureChoice, label);
        return isElementExist(xpath);
    }

    public Boolean isExistLabelOptionImage(String label) {
        String xpath = String.format(labelOptionImage, label);
        return isElementExist(xpath);
    }

    public void choosePictureChoice(String label, String picture) {
        String xpath = String.format(labelOptionPictureChoice, label) + "/ancestor::div[@class='c']//input[@value=\"" + picture + "\"]";
        String xpath_value = String.format(labelOptionPictureChoice, label) + "/following-sibling::span[contains(.,\"" + picture + "\")]";

        try {
            clickOnElement(xpath);
            assertThat(isElementExist(xpath_value, 20)).isTrue();
        } catch (Exception e) {
            clickOnElementByJs(xpath);
            assertThat(isElementExist(xpath_value)).isTrue();
        }

    }

    public void InputOptionImage(String label, String image, Boolean cropImage) {
        String xpath = String.format(labelOptionImage, label) + "//input";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + image);
        if (cropImage) {
            cropOutImage();
            verifyAndClickBtnCropImage(cropImage);
            clearTextByJS(xpath);
        } else
            verifyAndClickBtnCropImage(cropImage);
        waitUntilUploadImageFinish();
    }


    public void waitUntilUploadImageFinish() {
        String xpath = "//div[contains(@class,'upload-progress')]";
        int i = 0;
        while (isElementExist(xpath, 10)) {
            waitUntilElementInvisible(xpath, 10);
            i++;
            if (i > 10)
                break;
        }
    }

    public void verifyAndClickBtnCropImage(Boolean isCropImage) {
        String xpath = "//div[@id='modal-common']//button[contains(text(),'Crop')]";
        assertThat(isElementExist(xpath)).isEqualTo(isCropImage);
        if (isCropImage)
            clickOnElement(xpath);
    }

    public void cropOutImage() {
        waitUntilElementVisible("//*[name()='svg' and @class='svg-16'][1]", 10);
        for (int i = 1; i <= 3; i++) {
            clickOnElement("//*[name()='svg' and @class='svg-16'][1]");
        }
    }

    public void disableSizeChart() {
        String xpath = "//div[@class='flex-container-wrap p-t-sm']//a[normalize-space()='Disable']";
        if (isElementExist(xpath)) {
            clickOnElementByJs(xpath);
            waitABit(1000);
        }
    }

    public void selectKeepArtWork(Boolean isKeep) {
        String xpathStatus = "//div[@id='duplicate-product-modal']//input[@type='checkbox']";
        String xpathCheckbox = xpathStatus + "/following-sibling::span";
        verifyCheckedThenClick(xpathStatus, xpathCheckbox, isKeep);
    }

    public void clickDowloadTemplate() {
        String xpath = "//button[contains(@class,'s-button donwload-btn')]";
        clickOnElement(xpath);
    }

    public String downloadTemplate() throws IOException {
        String xpath = "//div[contains(@class,'artwork-required')]//button[@class='s-button donwload-btn is-default']";
        while (!isElementExist(xpath)) {
            refreshPage();
        }
        WebElement preview = getDriver().findElement(By.xpath(xpath));
        String logoSRC = preview.getAttribute("src");

        // add user agent
        URL urlImage = new URL(logoSRC);
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlImage.openConnection();
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedImage bodyAnimalImage = ImageIO.read(httpURLConnection.getInputStream());
        File outputfile = new File(getFilePath("image" + File.separator + "template.psd"));
        ImageIO.write(bodyAnimalImage, "psd", outputfile);
        return "";
    }

    public String getTemplate(String title, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-product-base/catalogs.json?key_search=" + title + "&ids=&print_types=&feature=catalog&access_token=" + accessToken;
        JsonPath js = getAPI(url);
        String template = (String) getData(js, "products.findAll{it.title==\"" + title + "\"}.artwork_requirement[0].template[0]");
        System.out.println("tem" + template);
        return template;
    }

    public void downloadTemplate(String title) {
        String accessToken = getAccessTokenShopBase();
        String name = getTemplate(title, accessToken);
        String url = "http://assets.boostflow.com/" + name;
        openUrl(url);
    }

    public void uploadArtwork() {
        String xpath = "//input[@id='add-artwork-1']";
        String a = getPathFileDownloaded();
        switchToTheFirstTab();
        $(xpath).sendKeys(a);
    }

    public void chooseArtwork(String artwork) {
        String art;
        if (artwork.contains("pbase")) {
            art = artwork.replace("pbase/templates/", "").split("\\.")[0];
        } else {
            art = artwork.replace("phub/", "").split("\\.")[0];
        }
        String imageArtwork = "(//*[contains(text(),'" + art + "')]//ancestor::div[@class='artwork']//div[@class='artwork-image'])[1]";
        if (isElementExist(imageArtwork)) {
            clickOnElement(imageArtwork);
        }
        if (isElementExist("//div[contains(@class,'in-process-animation')]", 5)) {
            waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 90);
            waitUntilElementVisible("//div[@class='phub-design']//*[contains(@class,'fix-display')]//*[text()[normalize-space()='Change artwork']]", 90);
        }
    }

    public void verifyDemension(String message) {
        String xpath = "//div[@class='editor-item']//span[contains(@class,'text-small text-bold flex-space-between')]";
        verifyElementContainsText(xpath, message);
    }

    public void verifyCustomOption(String custom_name) {
        verifyElementPresent("//label[contains(text(),'" + custom_name + "')] | //div[contains(text(),'" + custom_name + "')] | //span[contains(normalize-space(),'" + custom_name + "')]", true);
    }

    public void selectValueCustomRadio(String value) {
        String xpathInput = "//label[contains(@class,'base-radio__label')]//input[@value='" + value + "']";
        String xpath = "//label[contains(@class,'base-radio__label')]//span[normalize-space()='" + value + "']//preceding-sibling::span[@class='pointer base-radio__span']";
        verifyCheckedThenClick(xpathInput, xpath, true);
    }

    public void selectValueCustomImg(String s_nameImg) {
        String xpath = "//img[@alt='" + s_nameImg + "']";
        clickOnElement(xpath);
    }

    public void selectValueCustomDroplist(String s_value) {
        String xpath = "//*[@id='detail-contents']//select";
        selectDdlByXpath(xpath, s_value);
    }

    public void clickOnSaveChangeBtn() {
        String xpath = "//button[span[normalize-space()='Save change']]";
        waitUntilElementVisible(xpath, 30);
        clickOnElement(xpath);
    }

    public void clickOnUpdateBtn() {
        String xpath = "//button[normalize-space()='Update']";
        waitUntilElementVisible(xpath, 30);
        clickOnElement(xpath);
    }

    public void clickCreateCampaign() {
        String xpath = "";
        clickOnElementByJs(xpath);
    }

    public void clickImportCampaignToAnotherStore() {
        String xpathMakeCampaignAvailableBtn = xpathDropdown + "//span[@class='s-dropdown-item' and (contains(text(),'Make 1 campaign available'))]";
        clickOnElement(xpathMakeCampaignAvailableBtn);
        verifyElementPresent("//*[@class='s-modal-card-title' and contains(text(),'available')]", true);
    }

    public void verifyRejectReason(String reasonContent) {
        String xpath = "//tr[1]//div[div[contains(@class,'product-status--rejected')]]//span[@data-label='" + reasonContent + "']";
        waitUntilElementVisible(xpath, 10);
    }

    public void selectCreatClipart() {
        String xpathStatus = "(//div[@id='duplicate-product-modal']//input[@type='checkbox'])[2]";
        clickOnElement(xpathStatus);
    }


    public void uploadArtwork(String art) {
        String xpathAddArtworkBtn = "//div[@class='artwork-btn']/button";
        String xpathList = "//div[@class='artwork-list']";
        if (isElementExist(xpathAddArtworkBtn)) {
            clickOnElement(xpathAddArtworkBtn);
            waitForEverythingComplete();
        }
        if (!isElementExist(xpathList) || !isShowArtworkLibraryPopup()) {
            String xpath = "(//input[@type='file'])[1]";
            changeStyle(xpath);
            chooseAttachmentFile(xpath, "phub/artwork" + File.separator + art);
            waitABit(3000);
        }
        waitForEverythingComplete();

        String artwork = art.replace(".png", "").replace(".jpg", "");
        String imageArtwork = "(//*[contains(text(),'" + artwork + "')]//ancestor::div[@class='artwork']//div[@class='artwork-image'])[1]";
        if (isElementExist(imageArtwork)) {
            clickOnElement(imageArtwork);
        }

    }

    public int countCampainOnSF(String nameCampaign) {
        String xpath = "//span[normalize-space()='" + nameCampaign + "']";
        return countElementByXpath(xpath);

    }

    public void verifyNumberCampaignOnSF(String campaignName, String number) {
        int countCamp = countCampainOnSF(campaignName);
        String sCountCamp = String.valueOf(countCamp);
        assertThat(sCountCamp).isEqualTo(number);
    }

    public void clickSaveChangeBtnOnCampDetails() {
        String xpath = "//button[normalize-space()='Save changes']";
        waitUntilElementVisible(xpath, 30);
        clickOnElement(xpath);
    }

    public String getFileDownloaded() {
        return getPathFileDownloaded();
    }

    public void clickButtonSelectMockup(String baseName) {
        String xpath = "//div[@class='product' and descendant::span[normalize-space()='" + baseName + "']]//following-sibling::div[contains(@class,'s-dropdown')]//button";
        String xpathUpdateDesc = "//div[@class='product' and descendant::span[normalize-space()='" + baseName + "']]//following-sibling::div[contains(@class,'s-dropdown')]//span[normalize-space()='Select mockups']";
        waitUntilElementVisible(xpath, 5);
        clickOnElement(xpath);
        clickOnElement(xpathUpdateDesc);
    }

    public void verifyImageOnPopUpMockup(String image, Float per, Boolean isSelected, int imageNumber) throws IOException {
        String xpathImage = "";
        if (isSelected) {
            xpathImage = "//div[contains(@class,'img m-sm selected')][" + imageNumber + "]//img";
        } else {
            xpathImage = "//div[contains(@class,'img m-sm')][" + imageNumber + "]//img";
        }
        waitUntilElementVisible(xpathImage, 5);
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
        CommonPageObject.verifyImageFromDasboardOrSF(url, image, per);
    }

    public void verifyCampExist() {
        refreshPage();
        String xpath = "//table[@id='all-products']//tbody//tr";
        int n = countElementByXpath(xpath);
        assertThat(n).isEqualTo(1);
    }

    public String getStatusCampFirst() {
        String name = "Custom couple' faces with \"Under the sea\" theme";
        String a = name.replace("\\'", "\'").replace("\\", "\"");
        String xpathStatus = "//table[@id='all-products']//tbody//tr[1]//following::div[contains(@class,'product-status')][1]";
        String get = getText(xpathStatus).toUpperCase();
        return get;
    }

//    public String getCampNameHasCharacterSpecial(String name,String xpathFirst, String xpathLast) {
//        String[] a = name.split("\'|\"");
//        String x = xpath;
//        for (int i = 0; i < a.length; i++) {
//            if (i != 0) {
//                x += " and ";
//            }
//            x = x + "contains(text(),'" + a[i] + "')";
//            if (i == a.length - 1) {
//                x += "]";
//            }
//        }
//        System.out.println(x);
//        return x;
//    }
}
