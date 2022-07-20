package com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class EditCustomOptionsPage extends SBasePageObject {
    public EditCustomOptionsPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyLanching(String _xPath, int _timeoutInSeconds) {
        return isElementExist(_xPath, _timeoutInSeconds);
    }

    public void waitCreateCampSuccess() {
        String xpath = "//span[contains(text(),'launching')]";
        int i = 0;
        while (verifyLanching(xpath, 60)) {
            i++;
            if (i > 5)
                break;
        }
    }

}
