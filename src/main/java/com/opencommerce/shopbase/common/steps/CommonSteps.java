package com.opencommerce.shopbase.common.steps;

import com.opencommerce.shopbase.common.pages.CommonPage;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.findAll;

public class CommonSteps extends ScenarioSteps {
    CommonPage commonPage;

    @Step
    public void refreshPage() {
        commonPage.refreshPage();
        commonPage.waitForPageLoad();
    }


    @Step
    public void switchToTab(String nameTab) {
        commonPage.waitForEverythingComplete();
        commonPage.switchToTab(nameTab);
        commonPage.waitForPageLoad();
        commonPage.waitForEverythingComplete();
        waitABit(1000);
    }

    @Step
    public void getSizeWindown() {
        commonPage.getSizeWindow();
    }

    @Step
    public void closeBrowser() {
        commonPage.closeBrowser();
    }

    @Step
    public void quitBrowser() {
        commonPage.quitBrowser();
    }

    public void switchToTabLevel2(String tab) {
        commonPage.waitForEverythingComplete();
        waitABit(3000);
        commonPage.clickOnElement("//ul[@class='menu_level_1']//li//*[text()[normalize-space()='" + tab + "']]");
        commonPage.waitForEverythingComplete();
    }

    public void openMenuOnMobile() {
        commonPage.openMenuOnMobile();
    }

    @Step
    public void logInfor(String text) {
        System.out.println(text);
    }

    public String getAccessTokenShop() {
        return commonPage.getAccessTokenShopBase();
    }


    public void openUrl(String sPage) {
        commonPage.openUrl(sPage);
        commonPage.maximizeWindow();
    }

    public void verifyCssValues(String xpath, String cssValues) {
        String values[] = cssValues.split(";");
        for (String vl : values) {
            commonPage.verifyCssValue(xpath, vl);
        }
    }


    public void hoverElement(String elementHover) {
        if (!elementHover.isEmpty())
            commonPage.hoverOnElement(elementHover);
    }

    @Step
    public void verify(String result_table, String toString) {
    }

    public void setViewPort(int w, int h) {
        commonPage.setViewPort(w, h);
    }

    String nameImage = LoadObject.getFilePath(File.separator + "screen.png");

    @Step
    public BufferedImage takesScreenshotByElement(String element, boolean isAllScreen) throws IOException {
        commonPage.getSizeWindow();
        commonPage.waitForEverythingComplete();
        verifyElementVisible(element);
        waitABit(2000);

        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        if (isAllScreen) {
            FileUtils.copyFile(screenshot, new File(nameImage));
            return ImageIO.read(new File(nameImage));
        } else {
            WebElement el = getDriver().findElement(By.xpath(element));
            BufferedImage fullImg = ImageIO.read(screenshot);
            Point point = el.getLocation();
            int eleWidth = el.getSize().getWidth();
            int eleHeight = el.getSize().getHeight();
// Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);
            File screenshotLocation = new File(nameImage);
            FileUtils.copyFile(screenshot, screenshotLocation);
            return ImageIO.read(new File(nameImage));
        }
    }

    @Step
    public BufferedImage takesScreenshotByElement(String element) throws IOException {
        return takesScreenshotByElement(element, false);
    }


    @Step
    public void verifyElementVisible(String element) {
        if (!element.isEmpty()) {
            commonPage.waitUntilElementVisible(element, 20);
            commonPage.verifyElementPresent(element, true);
            commonPage.scrollToElement(element);
        }
    }

    @Step
    public List<String> getResultCompareImage(String description, BufferedImage fileInput, BufferedImage fileOutPut, float expectedPercent) {
        float actualPercent = calculatePercent(fileInput, fileOutPut);
        List<String> temp = new ArrayList<String>();
        if (actualPercent >= expectedPercent) {
            temp.addAll(Arrays.asList(description, actualPercent + "", expectedPercent + "", "Passed"));
        } else {
            temp.addAll(Arrays.asList(description, actualPercent + "", expectedPercent + "", "=>>>Failed"));
        }
        return temp;
    }

    @Step
    public float calculatePercent(BufferedImage img1, BufferedImage img2) {
        int count = 0;
        int n = 0;
        List<String> listFail = new ArrayList<>();
        float percentage = 0;
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    n = n + 1;
                    if (img1.getRGB(x, y) == img2.getRGB(x, y)) {
                        count = count + 1;
                    } else {
                        listFail.add("(" + x + "," + y + ")");
                    }

                }
            }
            percentage = (count * 100) / n;
        }
        return percentage;
    }


    public void verifyElementText(String element, String text) {
        if (!text.isEmpty())
            commonPage.verifyElementText(element, text);
    }

    public void waitUntilInvisibleLoading(int second) {
        try {
            commonPage.waitUntilInvisibleLoading(second);
        } catch (Exception e) {
        }
    }

    public void openUrlInANewTab(String url) {
        commonPage.openUrlInANewTab(url);
    }


    public void closeAllTabWithoutOriginalTab(String parentID) {
        commonPage.closeAllTabWithoutParent(parentID);
    }

    public String getWindowID() {
        return commonPage.getWindowID();
    }

    public void backToThePreviousScreen() {
        getDriver().navigate().back();
        commonPage.waitForEverythingComplete();
        waitABit(3000);
    }

    public void clearData() {
        productListAdded = new HashMap<>();
        listProductInCart = new HashMap<>();
        listProductName = new ArrayList<>();
        productName = "";
        tagSizeChart = "";
        listOfUsedPaymentAccount = new ArrayList<>();
        orderNameList = new ArrayList<>();
        paidTotalAmtByPaypal = "";
        float_totalAmt = 0.00f;
        totalAmt = "";
        checkoutToken = "";
        orderId = 0;
        isOrderScanned = false;
        titlePhotoGuide = "";
        customArtName = "";
        taxAmount = 0.00f;
        //clear data for checkout with pbase discount flow
        ppcDiscountAmt = 0.00d;
        productQuantity = 0;
        isPODDiscount = false;
        isPODDiscountFreeShipping = false;
        isStoreDiscount = false;
        isFreeShippingSetting = false;
        isOnPostPurchase = false;
        //------------------------------------------------

        //clear token if feature has multiple store
        exportOrderList = new HashSet<>();
        expExportProductInventoryList = new ArrayList();
        orderNameList = new ArrayList<>();
        dataCancel = new HashMap<>();

        //------------------------------------------------
        //clear tipping option
        numberOfTippingOption = 0;
        tippingOption = new ArrayList<>();
        isTippingAdded = false;
        tippingAmountAdded = 0.00d;
    }

    public int countElementByXpath(String element) {
        return commonPage.countElementByXpath(element);
    }

    @Step
    public void selectAnotherShop() {
        commonPage.selectAnotherShop();
    }

    public void switchToTheLatestWindowTab() {
        commonPage.switchToTheLatestWindowTab();
    }

    @Step
    public void logout() {
        commonPage.logout();
    }

    public String getCheckoutUrl() {
        return commonPage.getCurrentUrl();
    }

    @Step
    public void clickOnBtn(String btn) {
        commonPage.clickOnElement(btn);
    }

    @Step
    public void compareImage(String imageName, BufferedImage fileOutPut, float expectedPercent) throws IOException {
        List<List<String>> listResult = new ArrayList<List<String>>();
        listResult.add(Arrays.asList("Image Name", "Actual Result", "Expected Result", "Result"));
        List<String> temp = new ArrayList<String>();

        if (!imageName.isEmpty()) {
            String img[] = imageName.split(";");
            for (int i = 0; i < img.length; i++) {
                BufferedImage fileInput = ImageIO.read(new File(LoadObject.getFilePath(img[i])));
                temp = getResultCompareImage(img[i], fileInput, fileOutPut, expectedPercent);
                listResult.add(temp);
            }
            verify("Result Table", SessionData.generateTbDataReport(listResult));
            System.out.println(SessionData.generateTbDataReport(listResult));
            List<String> result = new ArrayList<>();

            for (int i = 1; i < listResult.size(); i++) {
                result.add(listResult.get(i).get(3));
            }
            assertThat(result).contains("Passed");
        }
    }

    @Step
    public void switchToTheFirstTab() {
        commonPage.switchToTheFirstTab();
    }

    @Step
    public void switchToTheLastestTab() {
        commonPage.switchToTheLastestTab();
    }

    public void waitForTextToAppear(String text) {
        commonPage.waitForTextToAppear(text, 15);
    }

    @Step
    public void closeOnboarding() {
        commonPage.closeOnboarding();
    }

    @Step
    public void waitUntilToastMsgInvisible() {
        commonPage.waitUntilToastMsgInvisible();
    }

    @Step
    public void verifyToastMessageIsShown(String msg) {
        String actMsg = commonPage.getToastMsg();
        assertThat(actMsg).isEqualTo(msg);
    }

    @Step
    public void switchToTabsInHive(String url) {
        commonPage.navigateToUrl(url);
    }

    @Step
    public void navigateByUrl(String url) {
        commonPage.navigateToUrl(url);
    }

    @Step
    public void verifyErrorToastMessageWillBeShown(String message) {
        commonPage.verifyErrorToastMessageWillBeShown(message);
    }

    @Step
    public void closePopup() {
        commonPage.closePopup();
    }
}

