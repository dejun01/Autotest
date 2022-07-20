package com.opencommerce.shopbase.partners.steps;

import com.opencommerce.shopbase.partners.pages.YourReferralsPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

public class YourReferrtalsSteps {
    YourReferralsPage yourReferralsPage;
    String apiURL = LoadObject.getProperty("gapi.url");

    public void verifyHeading(String heading) {
        yourReferralsPage.verifyHeading(heading);
    }

    public void verifyDescription(String des) {
        yourReferralsPage.verifyDescription(des);
    }

    public void verifyFilterDisplay() {
        yourReferralsPage.verifyFilterDisplay();
    }

    public void verifyStatisticTable() {
        yourReferralsPage.verifyStatisticTable();
    }

    public void verifyButtonPayoutDisplay() {
        yourReferralsPage.verifyButtonPayoutDisplay();
    }

    public void verifyPrintBaseStatistic() {
        String userToken = "";
        if (userToken.isEmpty()) {
            userToken = yourReferralsPage.getAccessTokenUser();
        }
        String api = apiURL + "/admin/partner/affiliate/statistics?page=1&limit=10&type=this_week";
        JsonPath js = yourReferralsPage.getAPISbase(api, userToken);

        String totalUser = getData(js, "summary.total_users");
        String totalCashback = getData(js, "summary.total_cashback");
        String saleItems = getData(js, "summary.total_unit");
        yourReferralsPage.verifySignUp(totalUser);
        yourReferralsPage.verifyCashBack(totalCashback);
        yourReferralsPage.verifySaleItems(saleItems);

        String url = apiURL + "/admin/partner/affiliate/user-log?page=1&limit=5&type=this_week";
        JsonPath jh = yourReferralsPage.getAPISbase(url, userToken);
        String clickPbase = getData(jh, "total_print_base");
        yourReferralsPage.verifyClickPBase(clickPbase);
    }

    @Step
    public String getData(JsonPath jsonPath, String key) {
        Object data = yourReferralsPage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }
}
