package com.opencommerce.shopbase.appstore.steps;

import com.opencommerce.shopbase.appstore.pages.AppDetailPage;
import common.utilities.LoadObject;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AppDetailSteps {
    AppDetailPage appPage;

    public void verifyLogo(String exlogo) {
        String actLogo = appPage.getLogo();
        assertThat(actLogo).isEqualTo(exlogo);
    }

    public void verifyName(String exName) {
        appPage.verifyName(exName);
    }

    public void verifyRating(String exRating, String ratecount) {
        String rating = "";
        if (!exRating.contains(".")) {
            exRating = exRating + ".0";
        }

        if (ratecount.equalsIgnoreCase("1")) {
            rating = "★ " + exRating + " (" + ratecount + " review)";
        } else if (ratecount.equalsIgnoreCase("0")) {
            rating = "0";
        } else {
            rating = "★ " + exRating + " (" + ratecount + " reviews)";
        }
        appPage.verifyRating(rating);
    }

    public void verifyHandle(String handle) {
        String curURL = appPage.getCurrentUrl();
        String gapi = LoadObject.getProperty("appstorelink");
        assertThat(curURL).isEqualTo(gapi + handle);
    }

    public void verifyCTABeforeInstall(String cta) {
        appPage.verifyCTABeforeInstall(cta);
    }

    public void verifyShortDesc(String desc) {
        appPage.verifyShortDesc(desc);
    }

    public void verifyScreenShot(String image) {
    }

    public void verifyDesciption(String desc) {
        appPage.verifyDesciption(desc);
    }

    public void verifyDevWeb(String devWeb) {
        appPage.verifyDevWeb(devWeb);
    }

    public void verifySupportURL(String supportURL) {
        appPage.verifySupportURL(supportURL);
    }

    public void verifyPrice(String pricevalue, String pricetype) {
        appPage.verifyPrice(pricevalue, pricetype);
    }
}
