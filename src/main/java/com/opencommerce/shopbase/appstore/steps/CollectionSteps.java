package com.opencommerce.shopbase.appstore.steps;

import com.opencommerce.shopbase.appstore.pages.CollectionPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;

import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CollectionSteps {
    CollectionPage collectionlPage;
    String gapi = LoadObject.getProperty("gapi.url");

    public int getNumberAppOfPage() {
        return collectionlPage.getNumberAppOfPage();
    }


    public void verifyAppName(String exName, int res) {
        String actName = collectionlPage.getAppName(res);
        assertThat(actName).isEqualTo(exName.replaceAll("\\s+", " "));
    }

    public void verifyShortDescription(String exDes, int res) {
        String actDecs = collectionlPage.getDecs(res);
        assertThat(actDecs).isEqualTo(exDes);
    }

    public void verifyPrice(String priceValue, String priceType, int res) {
        String actPrice = collectionlPage.getAppPrice(res);
        String price = "";
        switch (priceType) {
            case "fee_per_month":
                price = "$" + priceValue + "/Per Month";
                break;
            case "free_plan_available":
                price = "Free Plan Available";
                break;
            case "one_time_charge":
                price = "$" + priceValue + " For Life Time";
                break;
            case "free_day_trial":
                if (priceValue.equalsIgnoreCase("1"))
                    price = priceValue + " Day Free Trial";
                else
                    price = priceValue + " Days Free Trial";
                break;
            case "":
            case "free":
                price = "Free";
                break;
        }
        assertThat((actPrice.toLowerCase()).replaceAll("\\s+", " ")).isEqualTo(price.toLowerCase());
    }

    public void verifyRating(String name, String exRating, String ratecount) {
        String rating = "";
        if (ratecount.equalsIgnoreCase("1"))
            rating = exRating + " (" + ratecount + ")";
        else if (ratecount.equalsIgnoreCase("0"))
            rating = "0";
        else rating = exRating + " (" + ratecount + ")";

        if (!rating.equalsIgnoreCase("0")) {
            String actRating = collectionlPage.getAppRating(name);
            assertThat(actRating).isEqualTo(rating);
        } else collectionlPage.verifyRatingNotdisplay(name);
    }

    public void verifyLogo(String exLogo, int res) {
        String src = collectionlPage.getLogoApp(res);
        assertThat(src).isEqualTo(exLogo);
    }

    public void verifyPageTile(String name) {
        collectionlPage.verifyPageTile(name);
    }

    public void verifyDescOfpage(String pageType, String handle) {
        pageType = pageType.toLowerCase();
        String desAct = collectionlPage.getDescOfpage();
        String api = gapi + "/v1/3rd-party/apps?limit=16&" + pageType + "_handle=" + handle;
        System.out.println("api: " + api);
        JsonPath js = collectionlPage.getAPI(api);
        String descAPI = getData(js, pageType + ".description");
        assertThat(desAct).isEqualTo(descAPI.trim());

    }

    public String getData(JsonPath jsonPath, String key) {
        Object data = collectionlPage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }
}
