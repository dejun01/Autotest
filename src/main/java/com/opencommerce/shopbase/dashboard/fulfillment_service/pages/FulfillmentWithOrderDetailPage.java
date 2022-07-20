package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FulfillmentWithOrderDetailPage extends SBasePageObject {

    public FulfillmentWithOrderDetailPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickBTFulfill(String name) {
        String fulfillWith = "//span[text()[normalize-space()='" + name + "']]";
        clickOnElement(fulfillWith);
        waitForEverythingComplete();

    }

    public void verifyTextFulfillWithShopBaseFulfillment(String text) {
        String xpath = "//div[@class='fulfillment__heading']";
        assertThat(text).isEqualTo(getText(xpath));

    }

    public void verifyOrderFulfillment(String productName, String variant, String value, String nameCol) {
        int index = getNameColmn(nameCol);
        String selectShipping = "//div[contains(text(),'" + variant + "')]//parent::div//div[text()='" + productName + "']//ancestor::td//following-sibling::td[" + index + "]";
        if (nameCol.equals("SHIPPING METHOD")) {
            assertThat(value).isEqualTo(getSelectedValueDdl(selectShipping + "//select"));
        } else
            assertThat(value).isEqualTo(getText(selectShipping).trim());
    }

    private int getNameColmn(String nameCol) {
        String xpath = "//span[text()[normalize-space()='" + nameCol + "']]//parent::th//preceding-sibling::th";
        return countElementByXpath(xpath) - 1;
    }

    public String getTab(String product) {
        String xpath = "//a[contains(text(),'" + product + "')]//ancestor::div[@class='card__section']//preceding-sibling::div[@class='card__header hide-when-printing']//h2";
        waitUntilElementVisible(xpath,15);
        return getText(xpath);
    }

    public String getProduct(String status) {
        String xpath = "//h2[contains(text(),'"+status+"')]//ancestor::div[contains(@class,'card__header')]//following-sibling::div[@class='card__section']//a";
        return getText(xpath);
    }
    public String getVariant(String status) {
        String xpath = "(//h2[contains(text(),'"+status+"')]//ancestor::div[contains(@class,'card__header')]//following-sibling::div[@class='card__section']//a//parent::p//following-sibling::div//p)[1]";
        return getText(xpath);
    }

    public List<String> getListTextTab() {
        String xpath = "//div[@class='line-section__title']//span";
        return getListText(xpath);
    }

    public void verifyShipmentStatusPresent() {
        String xpath = "//h2[normalize-space()='Fulfilled (1)']/following-sibling::div//span[contains(@class,'s-tag')]";
        for (int i = 1; i <= countElementByXpath(xpath); i ++) {
            String status = "("+ xpath +")["+ i +"]";
            verifyElementPresent(status, true);
        }
    }

    public List<String> getListShipment() {
        String xPath = "//h2[contains(text(),'Fulfilled')]/following-sibling::div//span[contains(@class,'s-tag')]";
        List<String> listOfShipmentStatus = new ArrayList<>();
        if (isElementVisible(xPath, 3)) {
            listOfShipmentStatus = getListText(xPath);
        }
        return listOfShipmentStatus;
    }

    public void verifyShipmentStatus() {
        String orderShipmentStatus = "(//div[@class='title-bar__orders-show-badge']//span[contains(@class,'s-tag')][1])[2]";
        String shipmentStatus1 = getListShipment().get(0);
        if (getListShipment().size() == 1) {
            switch (shipmentStatus1) {
                case "Tracking Delivered":
                    assertThat(getText(orderShipmentStatus)).isEqualToIgnoringCase("Tracking Delivered");
                    break;
                case "Tracking Moved":
                    assertThat(getText(orderShipmentStatus)).isEqualToIgnoringCase("Tracking Moved");
                    break;
                case "Tracking Arrived":
                    assertThat(getText(orderShipmentStatus)).isEqualToIgnoringCase("Tracking Arrived");
                    break;
            }
        }
        else if (getListShipment().size() > 1) {
            String shipmentStatus2 = getListShipment().get(1);
            if ((shipmentStatus1.equalsIgnoreCase("Tracking Delivered") && !shipmentStatus2.equalsIgnoreCase("Tracking Delivered")) | (shipmentStatus1.equalsIgnoreCase("Tracking Delivered" )) && !shipmentStatus2.equalsIgnoreCase("Tracking Delivered")) {
                assertThat(getText(orderShipmentStatus)).isEqualToIgnoringCase("Partially Delivered");
            }
            else if (shipmentStatus1.equalsIgnoreCase("Tracking Delivered") && shipmentStatus2.equalsIgnoreCase("Tracking Delivered")) {
                assertThat(getText(orderShipmentStatus)).isEqualToIgnoringCase("Tracking Delivered");
            }
            else if (shipmentStatus1.equalsIgnoreCase("Tracking Moved" ) && shipmentStatus2.equalsIgnoreCase("Tracking Moved")) {
                assertThat(getText(orderShipmentStatus)).isEqualToIgnoringCase("Tracking Moved");
            }
        }
    }
}
