package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.hive_sbase.pages.ConfigIntroInHivePage;
import net.thucydides.core.annotations.Step;

public class ConfigIntroInHiveSteps {
    ConfigIntroInHivePage configIntroInHivePage;

    @Step
    public void selectCondition(String condition, int res) {
        configIntroInHivePage.selectDdlWithLabel("URL Params", condition, res);
    }

    @Step
    public void checkCheckBox(String _lablename, boolean _isOn) {
        configIntroInHivePage.checkCheckboxWithLabel(_lablename, _isOn);
    }

    @Step
    public void clickBtn(String button) {
        configIntroInHivePage.clickBtn(button);
    }

    @Step
    public void verifyIntroSuccess(String mesage) {
        configIntroInHivePage.verifyIntroSuccess(mesage);
    }

    @Step
    public void clickDetelebutton(String name) {
        configIntroInHivePage.clickDeletebutton(name);
    }

    @Step
    public void clickYesbutton() {
        configIntroInHivePage.clickYesbutton();
    }

    @Step
    public void clickOnElement(String name) {
        configIntroInHivePage.clickOnElement("//a[normalize-space()='" + name + "']");
    }
}
