package com.opencommerce.shopbase.hive_pbase.page;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static common.utilities.LoadObject.getFilePath;
import static net.serenitybdd.rest.SerenityRest.given;

public class HivePage extends SBasePageObject {
    public HivePage(WebDriver driver) {
        super(driver);
    }

    public String email;
    public String password;
    public String shopname;

    String hiveUrl = LoadObject.getProperty("hive.url");

    public void goToLoginHivePage() {
        openUrl(hiveUrl);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitUntiljQueryRequestCompletes(10);
        waitForPageLoad();
    }

    public void loginWithGG() {
        String xPath = "//a[@class='btn btn-danger btn-block btn-flat']";
        if (isElementExist(xPath, 2)) {
            clickOnElement(xPath);
        }
    }

    public void emailGG(String _email) {
        String xPath = "//div[normalize-space()='Email hoặc số điện thoại']//preceding-sibling::input[@class='whsOnd zHQkBf']";
        if (isElementExist(xPath, 2)) {
            waitClearAndType(xPath, _email);
            email = _email;
            clickNext();
        }
    }

    public void clickNext() {
        clickBtn("Tiếp theo");
        waitForPageLoad();
    }

    public void PassEmailGG(String _password) {
        String xPath = "//div[normalize-space()='Nhập mật khẩu của bạn']//preceding-sibling::input[@class='whsOnd zHQkBf']";
        if (isElementExist(xPath, 2)) {
            waitClearAndType(xPath, _password);
            password = _password;
            clickNext();
        }
    }

    public void switchToTab(String _labelName) {
        String xPath = "//*[@class='main-sidebar']//ul//li//*[normalize-space()='" + _labelName + "']";
        waitUntilElementVisible(xPath, 60);
        clickOnElement(xPath);
    }

    public void selectOrder(String shop, String order) {
        String xPath = "//td[normalize-space()='" + shop + "']//preceding-sibling::td[normalize-space()='" + order + "']//a";
        clickOnElement(xPath);
        waitForPageLoad();
    }

    public String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date date = new Date();
        String today = formatter.format(date);
        if (today.contains("0")) {
            today = today.replace("0", "");
        }
        return today;
    }

    public void replaceUrl(String linkhive, int orderId) {
        String url = getCurrentUrl().replace(getCurrentUrl(), linkhive + "/phub-order/" + orderId + "/edit");
        openUrl(url);
    }

    public void verifyArtworkStatus(String artworkStatus) {
        String xpath = "(//thead[descendant::th[text()='Artwork Status']]//following-sibling::tbody//span[contains(@class,'text-uppercase')])[2]";
        refreshPage();
        verifyElementText(xpath, artworkStatus);
    }

    public void verifyPersonalStatus(String personalStatus) {
        String xpath = "(//thead[descendant::th[text()='Personalize']]//following-sibling::tbody//span[contains(@class,'text-uppercase')])[1]";
        refreshPage();
        verifyElementText(xpath, personalStatus);
    }

    public void verifyImageMockup(String image, String row, float percent) throws IOException {
        int index = Integer.parseInt(row);
        String xpathImage = "(//div//img[@class='mockup-img image'])[" + index + "]";
        while (!isElementExist(xpathImage)) {
            refreshPage();
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        verifyImage(logoSRC, image, percent);
    }

    public void verifyImageArtwork(String image, float percent) throws IOException {
        int index = getIndexOfColumn("Artwork");
        String xpathImage = "//tbody//td[" + index + "]//div//img[@class='mockup-img image']";
        while (!isElementExist(xpathImage)) {
            refreshPage();
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        String[] sUrl = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(namefile.indexOf("@") + 1);
        String url = "";
        for (int i = 0; i < sUrl.length - 1; i++)
            url = url + sUrl[i] + "/";
        url = url + namefile;
        verifyImage(url, image, percent);
    }

    public void verifyImage(String linkURLImage, String image, float per) throws IOException {
        List<String> result = new ArrayList<>();
        Response resp = given().get(linkURLImage);
        byte[] imageBytes = resp.asByteArray();
        InputStream is = new ByteArrayInputStream(imageBytes);
        BufferedImage actualImage = ImageIO.read(is);
        if (!image.isEmpty()) {
            String[] img = image.split(";");
            for (int i = 0; i < img.length; i++) {
                BufferedImage expectedImage = null;
                expectedImage = ImageIO.read(new File(getFilePath("phub" + File.separator + "order" + File.separator + image + ".png")));

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

    public void verifyClearSuccces() {
        verifyElementPresent("//div[@class='alert alert-dismissible alert-success']", true);
    }

    public void waitContentShow() {
        waitElementToBeVisible("//div[@class='form-group']//input");
    }

    public boolean isConfigQuestion(String country, String storeType) {
        boolean isConfig;
        if (country.equals("Việt Nam")) {
            isConfig = isElementExist("//tr[descendant::td[4][contains(text(),'" + country + "')] and descendant::td[5][contains(text(), '" + storeType + "')] and descendant::td[8][normalize-space()='yes'] and descendant::td[9][normalize-space()='no']]");
        } else {
            isConfig = isElementExist("//tr[descendant::td[4][contains(text(),'" + country + "')] and descendant::td[5][contains(text(), '" + storeType + "')] and descendant::td[8][normalize-space()='yes'] and descendant::td[9][normalize-space()='no']]");
        }
        return isConfig;
    }

    public void clickOnButton(String label, String country, String storeType) {
        String xpathParent;
        if (country.equals("Việt Nam")) {
            xpathParent = "//tr[descendant::td[4][normalize-space()='" + country + "'] and descendant::td[5][contains(text(), '" + storeType + "')] and descendant::td[8][normalize-space()='yes']]";
        } else {
            xpathParent = "//tr[descendant::td[4][normalize-space()='Other' or normalize-space()='" + country + "'] and descendant::td[5][contains(text(), '" + storeType + "')] and descendant::td[8][normalize-space()='yes']]";
        }

        clickOnElement(xpathParent + "//descendant::a[normalize-space()='" + label + "']");
    }

    public String getQuestionsString() {
        return getText("//tr[descendant::th[normalize-space()='Content']]//td");
    }

    public String getNameTemplate(int i) {
        return  getText("(//tbody//td[2])["+ i + "]");
    }

    public String getThemeTemplate(int i) {
        return  getText("(//tbody//td[5])["+ i + "]");
    }

    public String getCategoryTemplate(int i) {
        return getText("(//tbody//td[7])["+ i + "]");
    }


    public void clickButtonFilter() {
        clickOnElement("//*[contains(@class,'navbar-collapse')]//a[contains(@class,'dropdown-toggle')]");
    }

    public void checkCheckboxInDropdown(String label) {
        clickOnElement("//*[contains(@class,'navbar-collapse')]//*[contains(@filter-target,'"+ label.toLowerCase() +"')]");
    }

    public void clickSelectChoice(String label) {
        clickOnElement("//label[contains(text(),'"+ label +"')]//parent::div//*[contains(@class,'select2-choice')]");
    }

    public void selectChoice(String choice) {
        clickOnElement("//*[contains(@class, 'select2-results')]//*[text() = '"+ choice +"']");
    }

    public void clickBtnFilterData() {
        clickOnElement("//*[contains(@class,'form-group')]//button");
        waitForPageLoad();
    }

    public int getNumberTemplate() {
        String text = getText("//*[contains(@for,'all_elements')]");
        return Integer.parseInt(StringUtils.substringBetween(text, "(", ")"));
    }

    public int getTemplate(String text, int index) {
        String xpath = "//tbody//td[contains(text(), '"+ text +"') and "+ index +"]";
        highlightElement(xpath);
        return countElementByXpath(xpath);
    }

    public int countNumberPage(){
        return countElementByXpath("//*[contains(@class,'pagination')]//li");
    }

    public void clickOnPage(int i) {
        clickOnElement("//*[contains(@class,'pagination')]//a[text()='"+ i +"']");
        waitForPageLoad();
    }

    public int getTemplateIndustryTheme (String theme, String industry) {
        return countElementByXpath("//tbody//td[contains(text(), '"+ industry +"') and 7]//preceding-sibling::td[contains(text(), '"+ theme +"') and 6]");
    }
}