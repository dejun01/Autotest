package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.settings.pages.LegalSettingPages;

public class LegalSettingSteps extends CommonSteps {

    LegalSettingPages legalSettingPages;

    public void inputIntoPagePolicy( String page, String content) {
        legalSettingPages.inputIntoPagePolicy(page, content);
    }

    public void clickButtonCreateFromTemplate(String page){
        legalSettingPages.clickButtonCreateFromTemplate(page);
    }

    public void verifyContentPage(String page, String content) {
        legalSettingPages.verifyContentPage(page, content);
    }

    public void openUrl( String urlPage) {
        openUrlInANewTab(urlPage);
    }

    public void updateData(String data, String label) {
        legalSettingPages.updateData(data, label);
    }

    public void updateCountry(String data, String label){
        legalSettingPages.clickCountry(label);
        legalSettingPages.selectCountry(data);
    }

    public void verifyContentPageTemplate(String page, String content) {
        legalSettingPages.verifyContentNotEmpty(page, content);
    }
}




