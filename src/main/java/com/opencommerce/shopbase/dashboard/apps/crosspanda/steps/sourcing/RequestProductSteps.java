package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing.RequestProductPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RequestProductSteps extends ScenarioSteps {
    RequestProductPage requestProductPage;

    String gapi = LoadObject.getProperty("gapi.url");
    String user_id = LoadObject.getProperty("user_id");

    @Step
    public void verifyFB(String fb) {
        waitABit(2000);
        assertThat(requestProductPage.getValueFB()).isEqualTo(fb);
    }

    @Step
    public void verifySkype(String skype) {
        assertThat(requestProductPage.getValueSkype()).isEqualTo(skype);
    }

    @Step
    public void verifyPhone(String phone) {
        assertThat(requestProductPage.getValuePhone()).isEqualTo(phone);
    }

    @Step
    public void verifyOther(String other) {
        assertThat(requestProductPage.getValueOther()).isEqualTo(other);
    }

    @Step
    public void enterProductURL(String url) {
        String[] urls = url.split(",");
        int count = urls.length;
        if (count > 3) {
            for (int i = 3; i < count; i++) {
                requestProductPage.clickAdd();
            }
        }
        for (int y = 0; y < count; y++) {
            requestProductPage.inputProductLink(urls[y], y);
        }
    }

    @Step
    public void confirmRequest() {
        requestProductPage.clickBtn("Confirm");
        requestProductPage.waitUntilInvisibleLoading(10);
    }

    @Step
    public void syncQuotation(String accessToken) {
        String api = gapi + "/v1/panda/sync-sbcn-data?user_id=" + user_id;
        requestProductPage.getAPICrosspanda(api, accessToken);
    }

    @Step
    public void deleleAllQuotation(String access_token) {
        String api = gapi + "/v1/panda/sale-orders/0?user_id=" + user_id + "&access_token=" + access_token;
        System.out.println("API: " + api);
        requestProductPage.deleteAPI(api);
    }

    @Step
    public void logInfor(String s) {
    }
}
