package com.opencommerce.shopbase.appstore.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CollectionPage extends SBasePageObject {
    public CollectionPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberAppOfCollection() {
        return countElementByXpath("//div[@class='grid-content']");
    }

    public String getAppName(int res) {
        return getText("(//h5[@class='app-title'])[" + res + "]");
    }

    public String getDecs(int res) {
        return getText("(//div[@class='app-item-info']//p)[" + res + "]");
    }

    public String getAppPrice(int res) {
        return getText("(//div[@class='app-item-content']//div[@class='badge d-flex'])[" + res + "]");
    }

    public String getAppRating(String name) {
        name = name.replaceAll("\\s+", " ");
        return getText("//div[@class='grid-content' and descendant::h5[normalize-space()='" + name + "']]//div[@class='app-rating-score']");
    }

    public int getNumberAppOfPage() {
        return countElementByXpath("//div[@class='grid-content']");
    }

    public String getLogoApp(int res) {
        return getAttributeValue("(//div[@class='grid-content']//img[@class='el-image__inner'])[" + res + "]", "src");
    }

    public void verifyPageTile(String name) {
        verifyElementText("//h1", name);
    }

    public String getDescOfpage() {
        return getText("//p[@class='collection-subtext']");
    }

    public void verifyRatingNotdisplay(String name) {
        verifyElementPresent("//div[@class='grid-content' and descendant::h5[normalize-space()='" + name + "']]//div[@class='app-rating-score']", false);

    }
}
