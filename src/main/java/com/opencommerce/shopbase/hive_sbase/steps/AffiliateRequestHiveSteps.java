package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.hive_sbase.pages.AffiliateRequestHivePages;
import common.utilities.LoadObject;

public class AffiliateRequestHiveSteps {
    String hiveLink = LoadObject.getProperty("hive");
    AffiliateRequestHivePages affiliateRequestHivePages;

    public void navigatetoListRequestpage() {
        affiliateRequestHivePages.maximizeWindow();
        affiliateRequestHivePages.navigateToUrl(hiveLink + "app/affiliaterequest/list");
    }
    public void verifyListRequestInfomation() {
        affiliateRequestHivePages.verifyDisplayIDRequest();
        affiliateRequestHivePages.verifyDisplayName();
        affiliateRequestHivePages.verifyDisplayEmail();
        affiliateRequestHivePages.verifyDisplayPhone();
        affiliateRequestHivePages.verifyDisplayStatus();
        affiliateRequestHivePages.verifyDisplayAction();
    }
    public void verifyStatusRequestWhenCreateSuccessfully(String emailSU) {
        affiliateRequestHivePages.verifyStatusRequestWhenCreateSuccessfully(emailSU);
    }

    public void clickYesApprove() {
        affiliateRequestHivePages.clickBtn("Yes, approve");
    }

    public void clickYesReject() {
        affiliateRequestHivePages.clickBtn("Yes, reject");
    }
}
