package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class AffiliateRequestHivePages extends SBasePageObject {
    public AffiliateRequestHivePages(WebDriver driver) {
        super(driver);}


    public void verifyDisplayIDRequest() {
        verifyElementPresent("//th[normalize-space()='Id']",true);
    }
    public void verifyDisplayName() {
        verifyElementPresent("//th[normalize-space()='Name']",true);
    }
    public void verifyDisplayEmail() {
        verifyElementPresent("//th[normalize-space()='Email']",true);
    }
    public void verifyDisplayPhone() {
        verifyElementPresent("//th[normalize-space()='Phone']",true);
    }
    public void verifyDisplayStatus() {
        verifyElementPresent("//th[normalize-space()='Status']",true);
    }
    public void verifyDisplayAction() { verifyElementPresent("//th[normalize-space()='Actions']",true); }
    public void verifyStatusRequestWhenCreateSuccessfully(String emailSU) {
        String text = "Waiting";
        String status = getText("//tr//descendant::td[normalize-space()='"+emailSU+"']//following::td[2]");
        Assertions.assertThat(status).isEqualTo(text);

    }
}
