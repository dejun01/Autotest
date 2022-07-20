package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.hive_sbase.pages.AppstorePage;

public class AppStoreSteps {

    AppstorePage appstorePage;

    public void enterCtaBeforeInstallApp(String value) {
        appstorePage.enterCtaBeforeInstallApp(value);
    }

    public void enterCtaAfterInstallApp(String value) {
        appstorePage.enterCtaAfterInstallApp(value);
    }

    public void enterContent(String content) {
        appstorePage.enterContent(content);
    }
}
