package com.opencommerce.shopbase.storefront.steps.contactus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.storefront.pages.contactus.ContactUsPBasePage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Assertions;

import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.given;

public class ContactUsPBaseStep extends ScenarioSteps {
    ContactUsPBasePage contactUsPBasePage;

    @Step
    public void enterInputField(String label, String value) {
        contactUsPBasePage.enterInputField(label, value);

    }

    @Step
    public void selectIssueType(String sIssueType) {
        if (!sIssueType.isEmpty())
            contactUsPBasePage.selectIssueType(sIssueType);

    }

    @Step
    public void enterMessage(String sMessage) {

        contactUsPBasePage.enterTextAreaFieldMessage(sMessage);
    }

    public void clickbtnSend() {
        contactUsPBasePage.clickbtnSend();
    }

    public void verifyMess(String mss) {
        contactUsPBasePage.verifyMess(mss);
    }

    @Step
    public void verifySuccessMss(String mss) {
        contactUsPBasePage.verifySuccessMss(mss);
    }

    @Step
    public void verifySendMailToMailinator(String issueType, String orderNumber, String message) {
        contactUsPBasePage.verifyContentMailBylabel("Issue type",issueType);
        contactUsPBasePage.verifyContentMailBylabel("Order number",orderNumber);
        contactUsPBasePage.verifyContentMailBylabel("Message",message);
    }

    @Step
    public void typeMailMailinator(String mail) {
        contactUsPBasePage.typeMailMailinator(mail);
    }

    @Step
    public void openEmailWithSubject(String subject) {
        contactUsPBasePage.openEmailWithSubject(subject);
    }

    public void setCustomerEmail(String mail) {
        String shopDomain = LoadObject.getProperty("shop");
        String user = LoadObject.getProperty("user.name");

        // get current currency
        String token = contactUsPBasePage.getAccessTokenShopBase(shopDomain);
        Response resp = given().header("x-shopbase-access-token", token).get("https://" + shopDomain+ "/admin/shop.json");
        Assertions.assertThat(resp.getStatusCode()).isBetween(200, 201);
        JsonPath jp = resp.then().extract().jsonPath();

        JsonObject requestParams = new JsonObject();
        JsonObject shop = new JsonObject();
        shop.addProperty("currency", (String) jp.get("shop.currency"));
        shop.addProperty("customer_email", mail);
        shop.addProperty("domain", shopDomain);
        shop.addProperty("email", user);
        requestParams.add("shop", shop);

        Response response = given().header("Content-Type", "application/json").header("x-shopbase-access-token", token).body(requestParams.toString()).put("https://" + shopDomain+ "/admin/shop.json");
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);
    }
}
