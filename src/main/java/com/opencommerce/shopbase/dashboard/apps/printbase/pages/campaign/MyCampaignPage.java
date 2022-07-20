package com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign.CampaignPage;
import common.CommonPageObject;
import common.SBasePageObject;
import cucumber.api.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
//import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static common.utilities.LoadObject.getFilePath;
import static common.utilities.LoadObject.getProperty;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static com.opencommerce.shopbase.OrderVariable.customArtName;
//import sun.misc.BASE64Decoder;

public class MyCampaignPage extends SBasePageObject {
    PrintbaseAPI printbaseAPI;

    public MyCampaignPage(WebDriver driver) {
        super(driver);
    }


    public void addProductToCampaign(String product) {
        String xpathProduct = "//div[contains(@class,'prod-wrap')][descendant-or-self::div[normalize-space()=\"" + product + "\"]]";
        clickBtn(xpathProduct, "Add product", 1);
        verifyElementPresent(xPathBtn(xpathProduct, "Remove product", 1), true);
    }

    public void switchToNextTab() {
        clickOnElement("(//div[@class='product-catagory']//ul//li[@class='is-active']/following-sibling::li)[1]//span");
        waitUntilInvisibleLoading(20);
    }

    public boolean isExistBaseProduct(String product) {
        String xpathProduct = "//div[contains(@class,'prod-wrap')][descendant-or-self::div[normalize-space()=\"" + product + "\"]]";
        return isElementExist(xpathProduct);
    }

    public void removeProductToCampaign(String product) {
        String xpathProduct = "//div[contains(@class,'prod-wrap')][descendant-or-self::div[normalize-space()=\"" + product + "\"]]";
        while (!isElementExist(xpathProduct)) {
            switchToNextTab();
        }
        clickBtn(xpathProduct, "Remove product", 1);
        verifyElementPresent(xPathBtn(xpathProduct, "Add product", 1), true);
        waitUntilImageLoadingSuccess();
    }


    public int getIndexProduct(String sProduct) {
        int index = 0;
        String xpath = "//div[@class='new-product m-t']//div[child::div[contains(@class,'prod-uploader__content__list')]]//div[contains(@class,'phub-design') and descendant::*[normalize-space()='" + sProduct + "'] and child::div[@class='phub-design-container custom-kit-section']]";
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::div[contains(@class,'phub-design')]") + 1;
        }
        return index;
    }

    public int countBaseProduct() {
        return countElementByXpath("//div[@class='new-product m-t']//div[child::div[contains(@class,'prod-uploader__content__list')]]/div[contains(@class,'phub-design')]");
    }

    String xpathProductInCampaign = "(//div[@class='new-product m-t']//div[@class='phub-design-container custom-kit-section'])[%d]";

    public void addAnArtWork(String sArtworks) {
        String artwork[] = sArtworks.split(",");
        addArtWork(artwork[0]);
        if (artwork.length > 1)
            for (int i = 1; i < artwork.length; i++) {
                addArtWork(artwork[i]);
            }
    }

    private void addArtWork(String art) {
        String xpathAddArtworkBtn = "//div[@class='artwork-btn']//span[text()[normalize-space()='Add artwork']]";
        if (isElementExist(xpathAddArtworkBtn)) {
            clickOnElement(xpathAddArtworkBtn);
            waitForEverythingComplete();
        }

        String xpath = "(//input[@type='file'])[1]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + art);
        clearTextByJS(xpath);
        waitForEverythingComplete();
        if (isElementExist("//div[contains(@class,'artwork-progress-bar')]")) {
            waitUntilElementInvisible("//div[contains(@class,'artwork-progress-bar')]", 90);
            waitABit(2000);
        }

        String artwork = art.replace(".png", "");
        String imageArtwork = "(//*[contains(text(),'" + artwork + "')]//ancestor::div[contains(@class,'artwork ')]//div[@class='img-container'])[1]";
        if (isElementExist(imageArtwork)) {
            clickOnElement(imageArtwork);
        }
        if (isElementExist("//div[contains(@class,'in-process-animation')]", 5))
            waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 90);
        waitUntilElementVisible("//div[@class='phub-design']//*[contains(@class,'fix-display')]//*[text()[normalize-space()='Change artwork']]", 90);
    }

    public void editDesign(String design) {
        clickOnElement("//*[@data-label='" + design + "']//button");
    }

    public int countProductSize(int index) {
        return countElementByXpath(String.format(xpathProductInCampaign, index) + "//div[contains(@class,'select-size')]/button[@type='button']");
    }

    public void selectBaseProduct(int index) {
        clickOnElement(String.format(xpathProductInCampaign, index) + "//*[@class='design-heading']");
        waitABit(700);
    }

    public List<Integer> getIndexSize(String listSizes, int index) {
        List<Integer> list = new ArrayList<>();
        String sizes[] = listSizes.split(",");
        for (String size : sizes) {
            int i = 0;
            String xpath = String.format(xpathProductInCampaign, index) + "//div[contains(@class,'select-size')]//button[child::span[text()[normalize-space()='" + size + "']]]";
            if (isElementExist(xpath)) {
                i = countElementByXpath(xpath + "/preceding-sibling::button") + 1;
            }
            list.add(i);
        }
        return list;
    }

    private boolean getStatusProductSize(int indexSize, int indexProduct) {
        String xpath = String.format(xpathProductInCampaign, indexProduct) + "//div[contains(@class,'select-size')]/button[" + indexSize + "]";
        String status = getAttributeValue(xpath, "class");
        if (status.contains("active")) {
            return true;
        }
        return false;
    }

    public void waitUntilImageLoadingSuccess() {
        waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 50);
    }

    public int countCampaigns() {
        return countElementByXpath("//div[@id='my-product']//div[@class='table-row']");
    }


    public void deleteTheFistCampaign() {
        String xpath = "(//div[@id='my-product']//div[@class='table-row'])[1]//*[contains(@class,'kit-icon-16-remove')]";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
            clickBtn("Delete");
        }
    }

    public void selectRadioButtonPhub(String xpath, boolean isSelect) {

        if (isElementExist(xpath)) {
            boolean isSelected = false;
            if (getAttributeValue(xpath, "class").contains("active")) {
                isSelected = true;
            }
            if (isSelect != isSelected) {
                clickOnElement(xpath);
            }
        }
    }

    public void clickBtnSearch() {
//        clickOnElementByJs("//input[@placeholder='Search']|//*[@data-testid='openSearchPanel']|//span[@class='search-icon pointer']");
        Actions actions = new Actions(getDriver());
        WebElement iconSearch = getDriver().findElement(By.xpath("//input[@placeholder='Search']|//*[@data-testid='openSearchPanel']|//span[@class='search-icon pointer']"));
        actions.contextClick(iconSearch).perform();
        waitABit(300);

    }

    public void enterProduct(String productName) {
        waitTypeAndEnter("(//input[@placeholder='Search' or @id ='search'])[1]", productName);
    }

    public void verifyProductPrice(String price) {
        double actPrice = Double.parseDouble(getText("//div[contains(@class,'price')]").replace("$", ""));
        assertThat(actPrice).isEqualTo(Double.parseDouble(price.replace("$", "")));
    }


    private void verifyValueOption(String option, String values) {
        if (option.equalsIgnoreCase("Size") == true) {
            int numberImage = countElementByXpath("//div[@id='product-variants']//img");
            int i = 1;
            int sum = 0;
            int count = 0;
            while (i <= numberImage) {
                clickOnElement("//div[@id='product-variants']//img[" + i + "]");
                count = countElementByXpath("//*[text()[normalize-space()='Size']]//ancestor::*[@class='product__variants-wrapper']//button");
                sum += count;
                i++;
            }
            if (numberImage == 0) {
                count = countElementByXpath("//*[text()[normalize-space()='Size']]//ancestor::*[@class='product__variants-wrapper']//button");
                sum += count;
            }
            String arrayValue[] = values.split(",");
            int size = arrayValue.length;
            Assert.assertEquals(sum, size);

        } else {
            String arrayValue[] = values.split(",");
            for (String value : arrayValue) {
                clickOnElement("//div[@id='product-variants']//img[@alt='" + value + "']");
                verifyTextPresents(value, true);
            }
        }
    }

    public void verifyTextPresents(String text, boolean isPresent) {
        verifyElementPresent("//div[@id='product-variants']//*[contains(text(),'" + text + "')]", isPresent);
    }

    public void searchProduct(String productName) {
        String currenturl = getProperty("shop");
        navigateToUrl("https://" + currenturl + "/search");
        waitClearAndTypeThenEnter("//div[@id='search']//input[@placeholder='Enter keywords...']", productName);
        waitForEverythingComplete(60);
    }

    public void clickSetIndividualPrice(String products) {
        String xpath = "//div[text()[normalize-space()='" + products + "']]//ancestor::*[@class='body-row']//div[@class='variant-direction-wrapper']//a";
        clickOnElement(xpath);
        waitUntilElementVisible("//div[text()[normalize-space()='" + products + "']]//ancestor::*[@class='body-row']//div[@class='variant-direction-wrapper']//a[text()='See less']", 10);
    }

    public void clickBtnViewOnStore() {
        String xpathClick = "(//*[@id='all-products']//div[@class='product-name']//i)[1]";
        String xpathHover = "(//*[@id='all-products']//div[@class='product-name']//span)[1]";
        hoverThenClickElement(xpathHover, xpathClick);

    }

    public void switchToNewTabOnBrowser() {
        switchToLatestTab();
    }

    public void verifyEnableSizeChart() {
        String xpath = "//div[@class='flex-container-wrap p-t-sm']";
        verifyElementPresent(xpath, true);
    }

    public void clickBtnNewCampaign() {
        String xpath = "//button[@type='button']//span[text()[normalize-space()='New campaign']]";
        if (isElementExist(xpath)) {
            clickBtn("New campaign");
        } else {
            clickBtn("New campaign");
        }
    }

    public void clickaddMoreProduct() {
        String xpath = "//button[contains(@class,'s-button add-base-product')]";
        waitUntilElementVisible(xpath, 50);
        clickOnElement(xpath);
    }

    public int countColorChoosed() {
        String xpath = "//div[@class='color__active']";
        return countElementByXpath(xpath);
    }

    public void clickBaseProduct(String productName) {
        String xpath = "//span[normalize-space()='" + productName + "']//preceding-sibling::a[@class='img-thumbnail']";
        clickOnElementByJs(xpath);
    }

    public void verifyColor() {
        String xpath = "//label[child::b[text()='color:']]//following-sibling::ul[@class='list-inline']//a";
        int colorNumber = countElementByXpath(xpath);
        assertThat(countColorChoosed()).isEqualTo(colorNumber);
    }

    public void chooseStyle(String productName) {
        String xpath = "//a//img[@alt='" + productName + "']";
        clickOnElementByJs(xpath);
        waitUntilInvisibleLoading(8);
    }

    public void verifySize(String size) {
        String xpath = "//label[child::b[text()='size:']]//following-sibling::ul[@class='list-inline']//span[normalize-space()='" + size + "']";
        verifyElementPresent(xpath, true);
    }

    public void selectAllColor() {
        String xpath = "//label[@class='s-checkbox s-mb16 is-small']//input";
        clickOnElementByJs(xpath);
    }

    public void verifyMessageTotalVariant() {
        String xpath = "//div//span[normalize-space()='Total variants: 294/250. You cannot create more than 250 variants']";
        verifyElementPresent(xpath, true);
    }

    public void addLayer(String frontOrBack, String layer) {
        String xpath = "//div[@class='s-dropdown-trigger']";
        String xpath_layer = "//div[@class='s-dropdown-menu']//span[normalize-space()='" + layer + "']";

        String xpath_side = "//div[@role='tab' and descendant::span[normalize-space()='" + frontOrBack + "']]//following-sibling::div" + xpath;
        String xpath_side_layer = "//div[@role='tab' and descendant::span[normalize-space()='" + frontOrBack + "']]//following-sibling::div" + xpath_layer;

        if (frontOrBack.isEmpty()) {
            clickOnElement(xpath);
            clickOnElement(xpath_layer);
        } else {
            clickOnElement(xpath_side);
            clickOnElement(xpath_side_layer);
        }
        waitABit(2000);
    }

    public void addArtwork(String imageName) {
        String xpath = "//input[@type='file' and @accept='image/x-png, .psd, .jpeg, .jpg']";
        String xpath_check = "//div[@class='title']//span[@data-label='" + imageName + "']";
        String xpath_img = "//div[@class='title' and child::span[@data-label='" + imageName + "']]//preceding-sibling::div";
        if (!isElementExist(xpath_check)) {
            chooseAttachmentFile(xpath, "image" + File.separator + imageName);
        }
        clickOnElement(xpath_img);
        waitUntilInvisibleLoading(10);
    }

    public void editLayer(String layer) {
        String xpath = "//span[@data-label='" + layer + "']//a";
        clickOnElementByJs(xpath);
        waitForPageLoad();
    }

    public void clickBack() {
        String xpath = "//i[@class='mdi mdi-chevron-left mdi-36px']";
        while (isElementExist(xpath)) {
            clickOnElement(xpath);
            if (isElementExist("//div[@class='s-modal-wrapper']//button[child::span[normalize-space()='Confirm']]"))
                break;
        }
    }

    public void clickSync(String layer) {
        String xpath = "//div[descendant::span[normalize-space()='" + layer + "']]//following-sibling::div//i[@class='mdi mdi-sync mdi-18px']";
        clickOnElementByJs(xpath);
        waitABit(3000);
    }

    public void clickDeleteLayer(String layer) {
        String xpath = "//div[contains(@class,'content__center') and descendant::span[normalize-space()='" + layer + "']]//following-sibling::div//i[@class='mdi mdi-dots-vertical mdi-16px']";
        String xpathButtonDel = "//span//span[normalize-space()='Delete']";
        clickOnElement(xpath);
        clickOnElementByJs(xpathButtonDel);
    }

    public void verifyLayerSynced(String layer, boolean layerDisplay) {
        String xpath = "//div[@class='layer s-flex s-flex--align-center s-flex--justify-space-between s-flex--wrap' and descendant::a[text()='" + layer + "']]";
        verifyElementPresent(xpath, layerDisplay);
    }

    public void verifyCustonOptionSynced(String layer, boolean layerDisplay) {
        String xpath = "//div[@class='custom-option__list s-mt24']//descendant::a[text()='" + layer + "']";
        verifyElementPresent(xpath, layerDisplay);
    }

    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            String imageDataBytes = imageString.substring(imageString.indexOf(",") + 1);
            imageByte = Base64.getDecoder().decode(imageDataBytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }


    public static void downloadWebPage(String url, String filename) throws IOException {
        // add user agent
        URL urlImage = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlImage.openConnection();
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedImage bodyAnimalImage = ImageIO.read(httpURLConnection.getInputStream());
        File outputfile = new File(getFilePath(File.separator + filename + ".png"));
        ImageIO.write(bodyAnimalImage, "png", outputfile);
    }

    public void hoverColor(String color) {
        String xpathColor = "//span[@data-label='" + color + "']//div";
        waitUntilInvisibleLoading(5);
        hoverOnElement(xpathColor);
        waitUntilInvisibleLoading(2);
    }

    public void inputLocation(String label, String value) {
        String xpath = "//div[child::label[text()='Location']]//following-sibling::div[descendant::span[normalize-space()='" + label + "']]//input";
        waitTypeAndEnter(xpath, value);
        waitABit(1000);
    }


    public void inputSize(String label, String value) {
        String xpath = "//div[child::label[text()='Size']]//following-sibling::div[descendant::span[normalize-space()='" + label + "']]//input";
        waitTypeAndEnter(xpath, value);
        waitABit(1000);
    }

    public void inputRotation(String value) {
        String xpath = "//div[child::label[text()='Rotation']]//following-sibling::div//input";
        waitTypeAndEnter(xpath, value);
        waitUntilInvisibleLoading(5);
    }

    public void inputText(String value) {
        String xpath = "//div[child::label[contains(text(),'Text')]]//following-sibling::div//input";
        waitTypeAndEnter(xpath, value);
        waitABit(1000);
    }

    public void inputColor(String value) {
        String xpath = "//div[child::label[text()='Color']]//following-sibling::div//input";
        waitTypeAndEnter(xpath, value);
        waitABit(1000);
    }

    public void inputSizeText(String value) {
        String xpath = "//div[child::label[text()='Size']]//following-sibling::div//input[@oninput='value = Math.round(this.value)']";
        waitTypeAndEnter(xpath, value);
        waitABit(1000);
    }

    public void selectProduct(String product) {
        String xpath = "//div[child::label[text()='Product']]//following-sibling::div//select";
        selectDdlByXpath(xpath, product);
        waitUntilInvisibleLoading(3);
        scrollIntoElementView(xpath);
        waitUntilInvisibleLoading(5);
    }

    public void scrollElement() {
        String xpath = "//div//h4[text()='Pricing']";
        scrollIntoElementView(xpath);
        waitUntilInvisibleLoading(3);
    }

    public void clickCustomOption() {
        String xpath = "//div[contains(@class, 'expand-action__container')]//span";
        clickOnElementByJs(xpath);
        waitForPageLoad();
    }

    public void addCustomOption() {
        String xpath_btn = "//div[@class='s-collapse-item__content']//span[normalize-space()='Customize layer']";
        String xpath_add = "//div[@class='right-sidebar__container']//div[@class='custom-option--minimized add cursor-pointer'] | //div[contains(@class,'add-custom-option')]//span[@class='s-icon is-small']";
        String xpath_edit = "//div[contains(@class,'custom-option__list')]//div[contains(@class,'children-expand content__below')]//preceding::div[contains(@class,'content__left')][1]";
        String xpath = "//div[contains(@class,'custom-option__list')]//div[contains(@class,'content__left')]";
        if (isElementExist(xpath_btn, 2)) {
            clickOnElementByJs(xpath_btn);
        } else if (isElementExist(xpath_add , 2)) {
            clickOnElementByJs(xpath_add);
        }  else if (isElementExist(xpath_edit , 2)) {
            clickOnElement(xpath_edit);
        } else {
            hoverOnElement(xpath);
            clickOnElementByJs("//div[contains(@class,'custom-option__item')]//div[@class='add-below s-caption']");
        }
    }

    public void selectType(String type) {
        String xpath = "//label[text()='Type']//following-sibling::div//select";
        selectDdlByXpath(xpath, type);
    }

    public void selectLayer(String layer) {
        String xpath = "//div[child::label[text()='Layer']]//following-sibling::div//select";
        String xpath_check = "//option[normalize-space()='Please select layer']";
        if (isElementExist(xpath_check)) {
            selectDdlByXpath(xpath, layer);
        }
    }

    String xpath_Option = "//div[child::a[normalize-space()=\"%s\"]]//following-sibling::div";
    String xpath_input_custom = "//label[contains(text(),\"%s\")]//following-sibling::div//input | //div[child::label[contains(text(),\"%s\")]]//following-sibling::div//input";

    public void inputTextInCustomOption(String label, String value) {
        System.out.println(String.format(xpath_input_custom, label, label));
        waitClearAndType(String.format(xpath_input_custom, label, label), value);
    }

    public void selectFont(String font) {
        String xpath = "//input[@id='font-list-undefined' or contains(@id,'font-list-text-co')]";
        String xpath_value = "//span[contains(@class,'s-dropdown-item')]//span[contains(.,'" + font + "')]";
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

    public void checkAndClickAllow(String lables) {
        List<String> listLabel = new ArrayList<>(Arrays.asList(lables.split(",")));
        String xpath = "//div[child::label[contains(.,'Allow the following characters')]]/label[contains(@class,'s-checkbox')] |  //div[child::label[contains(.,'Allow the following characters')]]//following-sibling::div/label[contains(@class,'s-checkbox')]";
        String xpathText = "(" + xpath + ")[%d]";
        Integer n = countElementByXpath(xpath);
        for (int i = 0; i < n; i++) {
            String label = getText(String.format(xpathText, i + 1));
            if (listLabel.contains(label))
                clickAllow(label, true);
            else clickAllow(label, false);
        }
    }

    public void clickAllow(String label, boolean ischeck) {
        String xpathStatus = "//span[normalize-space()='" + label + "' or normalize-space(text())='" + label + "']//preceding-sibling::input ";
        String xpathCheckbox = "//span[normalize-space()='" + label + "' or normalize-space(text())='" + label + "']//preceding-sibling::span";
        verifyCheckedThenClick(xpathStatus, xpathCheckbox, ischeck);
    }

    public void verifyNotifycation(String colName, String notify) {
        String xpath = "//label[contains(text(),'" + colName + "')]//following-sibling::div[text()='" + notify + "'] | //div[child::label[text()='" + colName + "']]//following-sibling::div//div[text()='" + notify + "']";
        if (isElementExist(xpath)) {
            verifyElementPresent(xpath, true);
        }
    }

    public void inputImage(String fileName) {
        String xpath = "//input[@type='file' and @accept='image/*']";
        chooseMultipleAttachmentFiles(xpath, fileName);
    }

    public void dropImage(String fileName) {
        String xpath = "//input[@type='file' and @accept='image/*']";
        chooseAttachmentFile(xpath, "image" + File.separator + fileName);
        waitUntilInvisibleLoading(50);
    }

    public void clickPictureChoiceImage() {
        String xpath = "//div[child::label[contains(text(),'Picture choice')]]//following-sibling::div//a";
        clickOnElementByJs(xpath);
        waitABit(2000);
    }

    public void clickActionMenuOfOption(String optionName) {
        if (optionName.isEmpty())
            optionName = "Untitled";
        String xpath = String.format(xpath_Option, optionName) + "//span[@class='s-icon is-small']//i";
        clickOnElementByJs(xpath);
    }

    public void verifyCustonField(String layer, boolean isDisplay) {
        String xpath = "//div[contains(@class,'s-form-item custom-s-form') and @style='' and descendant::label[contains(text(),'" + layer + "')]] | //div[@class='s-form-item custom-s-form is-success' and descendant::label[contains(text(),'" + layer + "')]]";
        verifyElementPresent(xpath, isDisplay);
    }

    public void verifyMessageImage(String message) {
        String xpath = "//div[@class='s-p16 upload-bg radius']//following-sibling::div";
        assertThat(getText(xpath)).isEqualTo(message);
        waitABit(1000);
    }

    public void addOption(String label, String location) {
        String xpath = "//div[@class='content__center f-1' and child::a[text()='" + label + "']]//preceding-sibling::div[contains(text(),'" + location + "')]";
        hoverOnElement("//div[@class='custom-option__item s-flex s-flex--align-center s-flex--wrap s-p8 cursor-pointer s-mb8']");
        waitABit(1000);
        clickOnElementByJs(xpath);
        waitForEverythingComplete();
    }

    public BufferedImage takesScreenshotByElement(String xpathImage) throws IOException {
//        String nameImage = "screen_" + System.currentTimeMillis() + ".png";
        String nameImage = "screen.png";
        WebElement element = getDriver().findElement(By.xpath(xpathImage));
        Screenshot logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(getDriver(), element);
        ImageIO.write(logoImageScreenshot.getImage(), "png", new File(nameImage));
        return ImageIO.read(new File(nameImage));
    }

    public void compareImage(String xpathImage, String imageExpect) throws IOException {
        BufferedImage img1 = ImageIO.read(new File(getFilePath("image" + File.separator + imageExpect)));
        BufferedImage img2 = takesScreenshotByElement(xpathImage);

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(img2, img1);
        if (diff.hasDiff()) {
            Assert.fail();
            System.out.println("Images are Not Same");
        }
    }

    public void verifyText(String text) {
        String xpath = "//input[@placeholder='Add your text']";
        assertThat(getText(xpath)).isEqualTo(text);
        waitABit(1000);
    }

    public void verifyLocationAndSize(String label, String value) {
        String xpath = "//span[normalize-space()='" + label + "']//preceding-sibling::input";
        assertThat(getText(xpath)).isEqualTo(value);
        waitABit(1000);
    }

    public void inputTitleLayerText(String layerName) {
        String xpath = "//input[@placeholder='Add your text']";
        waitUntilElementVisible(xpath, 50);
        waitClearAndTypeThenEnter(xpath, layerName);
    }

    public void inputTextFont(String font) {
        String xpath = "//input[@id='font-list-undefined']";
        String xpath_value = "//span[@class='s-dropdown-item active']/span[contains(text(),'" + font + "')]";
        waitUntilElementVisible(xpath, 50);
        waitClearAndTypeThenEnter(xpath, font);
        clickOnElement(xpath_value);
    }


    public String getNotiOfOption(String label) {
        return getText(String.format(xpath_Option, label) + "[@class='content__below s-w100']");
    }

    public void verifyIconNoti(Boolean is) {
        assertThat(isElementExist("//span[@class='s-icon alert-invalid is-small']")).isEqualTo(is);
    }

    public void clearTextLable() {
        clearTextByJS(xpath_input_custom);
    }

    public void clickActionCustomOption(String label, String btnAction) {
        String xpath = String.format(xpath_Option, label) + "//span[normalize-space()='" + btnAction + "']";
        waitUntilElementVisible(xpath, 30);
        clickOnElement(xpath);
    }

    public BufferedImage takesScreenshotWithElement(String folder, String image) throws IOException {
        WebElement preview = getDriver().findElement(By.xpath("//div[@class='product-image-preview']/img"));
        Screenshot logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(getDriver(), preview);
        ImageIO.write(logoImageScreenshot.getImage(), "png", new File(getFilePath(folder + File.separator + image + ".png")));
        BufferedImage actualImage = logoImageScreenshot.getImage();
        return actualImage;
    }


    public BufferedImage takesScreenshotPopupLivePreview(String folder, String image) throws IOException {
        WebElement preview = getDriver().findElement(By.xpath("//div[@class='product-image-preview']/img"));
        Screenshot logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(getDriver(), preview);
        ImageIO.write(logoImageScreenshot.getImage(), "png", new File(getFilePath(folder + File.separator + image + ".png")));
        BufferedImage actualImage = logoImageScreenshot.getImage();
        return actualImage;
    }

    public void inputValue(String value) {
        String[] listValue = value.split(">");
        int i = 1;
        for (String _value : listValue) {
            String xpath = "(//div[contains(@class,'is-required')]//input)[" + i + "]";
            inputSlowly(xpath, _value);
            i++;
        }

    }

    public void selectTypeClipart(String typeClipart) {
        String xpath = "//*[@id='create-product']//span[contains(text(),'" + typeClipart + "')]";
        clickOnElement(xpath);
    }

    public void searchNameFolderClipart(String nameClipart) {
        String xpath_txb_folder = "//input[@id='pc-clipart-folder-input']";
        waitClearAndType(xpath_txb_folder, nameClipart);
    }

    public void searchNameGroupClipart(String nameClipart) {
        String xpath_txb_group = "//input[@id='pc-clipart-group-input']";
        clickOnElementByJs(xpath_txb_group);
        waitClearAndType(xpath_txb_group, nameClipart);
    }

    public void selectFolderClipart() {
        String xpath_name_clipart_folder = "//span[@class='s-dropdown-item text-overflow'][1]";
        clickOnElementByJs(xpath_name_clipart_folder);
    }

    public void selectGroupClipart(String nameClipart) {
        String xpath_name_clipart_group = "//span[contains(text(),'" + nameClipart + "')]";
        clickOnElementByJs(xpath_name_clipart_group);
    }

    public void selectTypeDisplayNameClipart(String typeDisplayNameClipart) {
        String xpath = "//*[@id='create-product']//span[contains(text(),'" + typeDisplayNameClipart + "')]";
        clickOnElement(xpath);
    }


    public void getMessReachLimitQuota(String mess) {
        String xpath = "//p[@class='s-mr16']";
        waitUntilElementVisible(xpath, 30);
        assertThat(getText(xpath)).isEqualTo(mess);

    }

    public void getMessReachLimitQuotaSB(String shopID, String accessToken, String mess) {
        Integer quotaproduct = printbaseAPI.getQuotaPro(shopID, accessToken);
        String xpath = "";
        if (quotaproduct == 0) {
            waitUntilElementVisible(xpath, 30);
            assertThat(getText(xpath)).isEqualTo(mess);
        }
    }

    public void getMessReachLimitQuotaHourly(String shopID, String accessToken, String mess) {
        Integer quotaHourly = printbaseAPI.getQuotaCampaignHourly(shopID, accessToken);
        Integer quotaHourlyLimit = printbaseAPI.getQuotaCampaignHourlyLimit(shopID, accessToken);
        String xpath = "//p[contains(.,'You can bulk duplicate up to" + quotaHourlyLimit + "campaigns per hour.')]";
        if (quotaHourly == 0) {
            waitUntilElementVisible(xpath, 30);
            assertThat(getText(xpath)).isEqualTo(mess);
        }
    }

    public void clickClosePopupNotiQuota() {
        String xpath = "//button[@type='button' and contains(@class,'s-modal-close')]";
        waitUntilElementVisible(xpath, 50);
        clickOnElement(xpath);
    }

    public void verifyImageLivePreview(String image, Float per) throws IOException {
        List<String> result = new ArrayList<>();
        String xpath = "//div[@class='product-image-preview']/img";
        String imageBase64 = getAttributeValue(xpath, "src").split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageBase64);

        BufferedImage actualImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        if (!image.isEmpty()) {
            String img[] = image.split(";");
            for (int i = 0; i < img.length; i++) {
                BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(getFilePath("phub" + img[i]));
                ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();
                float differencePercent = imageComparisonResult.getDifferencePercent();
                System.out.println("differencePercent : " + differencePercent);
                if (per > differencePercent)
                    result.add("Passed");
                else result.add("Failed");

            }
            Assertions.assertThat(result).contains("Passed");
        }
    }

    public void verifyMessageUploadArtwork(String errorMessage) {
        String xpath = "//p[contains(., 'Image file size limit exceeded (1500MB)')]";
        waitUntilElementVisible(xpath, 10);
        assertThat(getText(xpath)).isEqualTo(errorMessage);
    }


    public void verifyNameArtwork(String artwork) {
        String xpath = "//span[normalize-space()='" + artwork + "']";
        waitUntilElementVisible(xpath, 10);
        assertThat(getText(xpath)).isEqualTo(artwork);
    }

    public void searchArtwork(String artwork) {
        String xpath = "//input[@placeholder='Search by file name']";
        waitTypeAndEnter(xpath, artwork);
        waitABit(2000);
    }

    public int countArtwork() {
        return countElementByXpath("//div[@id='my-product']//div[@class='table-row']");
    }

    public void deleteTheFistArtwork() {
        String xpath = "//div[@class='artwork']//div[@class='artwork-image']//img[@class='cursor-pointer']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
            clickBtn("Delete");
        }
    }

    public void clickActionOnMenuCustomOption(String customName, String buttonName) {
        String xpath = "//div[contains(@class,'custom-option__item') and descendant::a[normalize-space()='" + customName + "']]//div[contains(@class,'s-dropdown d-flex align-items-center is-mobile-modal')]//span[@class='s-icon is-small']//i";
        String xpath_action = "//div[contains(@class,'custom-option__item') and descendant::a[normalize-space()='" + customName + "']]//div[contains(@class,'s-dropdown-menu')]//span[normalize-space()='" + buttonName + "']";
        clickOnElement(xpath);
        clickOnElement(xpath_action);
        if (buttonName.equals("Delete")) {
            String xpath_popup_del = "//button[@class='s-button btn-confirm-delete is-danger']";
            clickOnElement(xpath_popup_del);
        }
    }

    public void verifyMessageErrorAfclickAction(String mess) {
        String xpath = "//div[contains(text(),'" + mess + "')]";
        assertThat(isElementVisible(xpath, 10)).isEqualTo(true);
    }

    public void verifyImageMockup(String image, Float per, int imageNumber) throws IOException {
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
        System.out.println("URL: " + url);
        verifyImageFromDasboardOrSF(url, image, per);
        clickOnElement(xpath_done);
    }

    public JsonPath getAPI(String url) {
        Response resp = given().get(url);
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(200);
        JsonPath jp = resp.then().extract().jsonPath();
        return jp;
    }

    public void clickToBtnEditFromSampleOfCustomArt(String customArt) {
        String xpath = "//div[@class='s-flex' and descendant::p[normalize-space()='" + customArt + "']]//following-sibling::div//button[@class='s-button is-outline']";
        waitUntilElementVisible(xpath, 50);
        clickOnElement(xpath);
    }

    public void clickToBtnImportToStoreOfCustomArt(String customArt) {
        String xpath = "//div[@class='s-flex' and descendant::p[normalize-space()='" + customArt + "']]//following-sibling::div//button[@class='s-button is-primary']";
        waitUntilElementVisible(xpath, 50);
        clickOnElement(xpath);
    }

    public void verifyMessageWhenReachQuotaCampaign() {
        String xpath = "//div[@class='s-alert-content-wrap']//div[@class='s-alert__content']";
        waitUntilElementVisible(xpath, 50);
        String message = "You have reached the maximum daily amount of campaigns (3 campaigns/day).\n" +
                "Please go back and continue creating your campaigns on the next day.";
        assertThat(getText(xpath)).isEqualTo(message);
    }

    public void verifyMessageWhenReachLimitQuotaCampaignPerHourly() {
        String xpath = "//p[contains(.,'Your store has reached the limited quota which only allows to bulk duplicate 1 campaigns/hour.')]";
        waitUntilElementVisible(xpath, 50);
        String message = "Your store has reached the limited quota which only allows to bulk duplicate 1 campaigns/hour.";
        assertThat(getText(xpath)).contains(message);
    }

    public void verifyMessageWhenShopbaseReachLimitQuotaCampaign() {
        String xpath = "//div[@class='s-alert__content']//p";
        String message = "You have reached the maximum daily amount of products (3 products/day).\n" +
                "Please go back and continue creating your campaigns on the next day.";
        assertThat(getText(xpath)).isEqualTo(message);
    }

    public void verifyShippingForVariant(String sku, Float shippingFee) {
        String indexXpath = "//th[descendant-or-self::*[text()[normalize-space()='Shipping fee']]]";
        int i = countElementByXpath(indexXpath) + 1;
        String xpath = "//thead[descendant::span[normalize-space()='Shipping fee']]//following-sibling::tbody//td[normalize-space()='" + sku + "']//following-sibling::td[" + i + "]";
        Float price = Float.parseFloat(getText(xpath).replace("$", ""));
        assertThat(price).isEqualTo(shippingFee);
    }

    public void verifyNumberClipartOnSF(String sNumber) {
        String xpath ="//div[@class='base-picture__value']//span[@class='wrapper-image']";
        waitUntilElementVisible(xpath, 20);
        int countXpath = countElementByXpath(xpath);
        int number = Integer.parseInt(sNumber);
        assertThat(countXpath).isEqualTo(number);

    }
}