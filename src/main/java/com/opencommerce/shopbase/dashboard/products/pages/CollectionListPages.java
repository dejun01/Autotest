package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionListPages extends SBasePageObject {
    public CollectionListPages(WebDriver driver) {
        super(driver);
    }

    public void chooseCollection(String nameCollection) {
        String xPath = "//a[text()='" + nameCollection + "']";
        waitUntilElementVisible(xPath, 5);
        clickLinkTextWithLabel(nameCollection);
    }

    public void verifyProductOfCollection(String nameProduct) {
        int retry_time = 0;
        String xPathTag = "//div[@class='product-table']//div[@class='product-title']//a[normalize-space()='" + nameProduct + "' ]";
        boolean check = isElementVisible(xPathTag, 3);
        while (!check && retry_time <= 3) {
            retry_time++;
            refreshPage();
            waitABit(3000);
            check = isElementVisible(xPathTag, 3);
        }
        verifyElementVisible(xPathTag, true);
    }

    public String getCurrentURL() {
        clickOnElement("//button[normalize-space()='Edit website SEO']");
        return getTextValue("//div[@class='s-form-item' and descendant::label[normalize-space()='URL and handle']]//input");
    }

    public void enterNewURL(String newURL) {
        inputSlowly("//section[@class='card search-engine' and descendant::div[@class='type-container']]//div[@class='s-input s-input-group s-input-group--prepend']//input", newURL);
    }

    public void verifySaveCollectionSuccess() {
        verifyElementPresent("//div[normalize-space()='Saved collection!']", true);
    }

    public void searchCollection(String title) {
        String xPath = "//input[@type='text' and (@placeholder='Search collections' or @placeholder='Search for collections')]";
        waitTypeAndEnter(xPath, title);
    }

    public void chooseAction(String action) {
        String xPath = "//*[contains(@class,'dropdown-item') and normalize-space()='" + action + "']";
        waitForEverythingComplete();
        clickOnElement(xPath);
    }

    public boolean hasNoProduct() {
        String xpath = "//div[@class='no-collection text-center']";
        return isElementVisible(xpath, 3);
    }

    public void selectAllCollection() {
        String xPath = "//th[@class='checkbox-item']//span[@class='s-check']";
        clickOnElement(xPath);
    }

    public void selectCollection(String collection) {
        String xPath = "(//td[child::a[normalize-space()='" + collection + "']]//preceding-sibling::td//span[@class='s-check'])[1]";
        clickOnElement(xPath);
    }

    public void openDetailColection(String title) {
        String xPath = "(//a[contains(text(),'" + title + "')])[2]";
        clickOnElement(xPath);
    }

    public void verifyImageProductThumbnail(String product, String imageVariant, String imageStatus) {
        String xPath = "//div[contains(@class,'collection-detail__product-details') and child::span[normalize-space()='" + product + "']]//preceding-sibling::div//img[@class='sb-lazy']";
        if (!imageVariant.isEmpty()) {
            String xpathImageVariant = "//div[contains(@class,'collection-detail__product-details') and child::span[normalize-space()='" + product + "']]//preceding-sibling::div//img[@class='sb-lazy hover-secondary']";
            String linkImage = getAttributeValue(xPath, "src");
            String image = linkImage.substring(linkImage.lastIndexOf("@") + 1);
            assertThat(image).isEqualTo(imageVariant);
            assertThat(getAttributeValue(xPath, "lazy")).isEqualTo(imageStatus);
        } else {
             assertThat(getAttributeValue(xPath, "lazy")).isEqualTo(imageStatus);
        }
    }

    public void openCollectionList() {
        openUrl(getCurrentUrl() + "collections");
    }

    public void verifyNumberCollectionsOnStoreFront(int totalCollection) {
        String xpath = "//div[contains(@class,'collection-product')]//h5";
        int countCollectionOnSF = countElementByXpath(xpath);
        assertThat(countCollectionOnSF).isEqualTo(totalCollection);
    }

    public void verifyPageCollection(String shop) {
        int count = countCollection(shop);
        String xPathPage = "//div[@class='s-pagination is-default is-default']//li//a";
        int i = countElementByXpath(xPathPage);
        xPathPage = "(//div[@class='s-pagination is-default is-default']//li//a)[" + i + "]";
        if (count <= 50) {
            verifyElementPresent(xPathPage, false);
        } else {
            int dem = count % 50;
            int page = count / 50;
            if (dem != 0) {
                page = (count / 50) + 1;
            }
            assertThat(getText(xPathPage)).isEqualTo(String.valueOf(page));
        }
    }

    public int countCollection(String shop) {
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/collections/count.json?access_token=" + accessToken;
        JsonPath jp = getAPI(url);
        return jp.get("count");
    }

    public void verifyNumberCollectionsOnDashboard(int totalCollection) {
        String xpath = "//label[@class='s-checkbox']";
        int countCollectionOnDB = countElementByXpath(xpath);
        assertThat(countCollectionOnDB - 1).isEqualTo(totalCollection);
    }

    public void clickAddToCollection(String action) {
        String xpath ="//span[normalize-space()='Add to collection']";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void selectCollectionFromDashboard(String nameCollection) {
        String xpath ="//div[@class='item']//div[normalize-space()='Pcam']";
        clickOnElement(xpath);
    }
}
