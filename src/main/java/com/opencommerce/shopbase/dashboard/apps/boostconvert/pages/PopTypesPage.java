package com.opencommerce.shopbase.dashboard.apps.boostconvert.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

public class PopTypesPage extends SBasePageObject {
    public PopTypesPage(WebDriver driver) {
        super(driver);
    }


    private String getElementByTypeNoti(String typeNoti) {
        String xpath = "";
        if (typeNoti.equalsIgnoreCase("Checkout notification")) {
            xpath = "//div[contains(@class,'wrapper-noti-on-checkout')]";
        } else if (typeNoti.equalsIgnoreCase("Sign up notification")) {
            xpath = "//div[contains(@class,'wrapper-noti-sign-up')]";
        } else {
            xpath = "//div[contains(@class,'boostflow--spop wrapper-noti') and descendant::*[@class='noti-body']]";
        }
        return xpath;
    }

    String xpathSection = "//div[contains(@class,'copt-section d-flex')][descendant::*[normalize-space()='%s']]";

    public void turnOnOffNotiType(String typeNoti, boolean expStatus) {
        checkCheckbox(String.format(xpathSection, typeNoti), expStatus);
    }

    public void clickSetting(String labelName) {
        String title_xpath = String.format(xpathSection, labelName);
        clickBtn(title_xpath, "Settings", 1);
    }

    public void enterCustomPage(String pages) {

        waitTypeOnElement("//textarea", pages);
    }

    public void selectOptionTrigger(String trigger) {
        selectRadioButtonWithLabel("//div[contains(@class,'sale-notifications__trigger')]", trigger, 1, true);
    }

    public boolean isEnableSave() {
        return isElementExist("//div[@class='save-setting-fixed'][not(@style='display: none;')]");
    }
}
