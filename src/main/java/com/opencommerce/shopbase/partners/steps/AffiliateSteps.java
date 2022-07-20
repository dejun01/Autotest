package com.opencommerce.shopbase.partners.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.partners.pages.AffiliatePage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.junit.ComparisonFailure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class AffiliateSteps {
    AffiliatePage affPage;
    String userToken = "";
    String total = "";
    String totalUser = "";
    String saleItems = "";
    String totalCashback = "";
    String totalCashbackSB = "";
    String totalCashbackPB = "";
    String email = "";
    HashMap<String, List<String>> a = new HashMap<>();
    HashMap<String, List<String>> result = new HashMap<>();
    String accLink = LoadObject.getProperty("webdriver.base.url");
    String apiURL = LoadObject.getProperty("gapi.url");
    public int MAX_RETRY_TIME = 10;
    String userID = LoadObject.getProperty("userid");


    @Step
    public void openAffiliateLink() {
        affPage.openAffiliateLink();
    }

    @Step
    public void clickBtnJoinTheAffiliateProgram() {
        affPage.clickLinkTextWithLabel("Join The Affiliate Program");
    }

    @Step
    public void verfifySignUpScreen() {
        affPage.switchToWindowWithIndex(1);
        affPage.verfifySignUpScreen();
    }

    @Step
    public HashMap<String, List<String>> getStatisticsAffiliateByApi(String time) {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
        String api = apiURL + "/admin/partner/affiliate/statistics?page=1&limit=10&type=" + time;
        System.out.println(api);
        JsonPath js = affPage.getAPISbase(api, userToken);

        total = getData(js, "total");
        totalUser = getData(js, "summary.total_users");
        saleItems = getData(js, "summary.total_unit");
        totalCashback = getData(js, "summary.total_cashback");
        totalCashbackSB = getData(js, "summary.total_cash_back_shop_base");
        totalCashbackPB = getData(js, "summary.total_cash_back_print_base");
        email = getData(js, "list.user_email");
        result.put(time, asList(total, totalUser, saleItems, totalCashback, totalCashbackSB, totalCashbackPB));
        System.out.println("result: " + result);
        return result;
    }

    @Step
    public String getData(JsonPath jsonPath, String key) {
        Object data = affPage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    @Step
    public ArrayList getDataByKey(JsonPath jsonPath, String key) {
        return affPage.getDataByKey(jsonPath, key);
    }

    @Step
    public void verifyStatistics(HashMap<String, List<String>> pre, HashMap<String, List<String>> current, String quantity, int currentRetryTime) {
        System.out.println("pre:" + pre);
        System.out.println("act:" + current);
        for (String i : pre.keySet()) {
            for (String j : current.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    System.out.println("key: " + i);
                    List<String> preValue = pre.get(i);
                    List<String> actValue = current.get(j);
                    int qt = Integer.parseInt(quantity);
                    System.out.println("preValue: " + preValue + " actValue" + actValue);
                    int preTotal = Integer.parseInt(preValue.get(0));
                    int actTotal = Integer.parseInt(actValue.get(0));
                    int preTotalUsers = Integer.parseInt(preValue.get(1));
                    int actTotalUsers = Integer.parseInt(actValue.get(1));
                    int preSaleItems = Integer.parseInt(preValue.get(2));
                    int actSaleItems = Integer.parseInt(actValue.get(2));
                    switch (i) {
                        case "this_week":
                        case "this_month":
                        case "this_year":
                            try {
                                assertThat(actTotal).isEqualTo(preTotal + 1);
                                assertThat(actTotalUsers).isEqualTo(preTotalUsers + 1);
                                assertThat(actSaleItems).isEqualTo(preSaleItems + qt);
                            } catch (ComparisonFailure e) {
                                if (currentRetryTime < MAX_RETRY_TIME) {
                                    affPage.waitForApiUpdate(10000);
                                    current = getStatisticsAffiliateByApi(i);
                                    verifyStatistics(pre, current, quantity, currentRetryTime + 1);
                                } else {
                                    System.out.println("currentRetryTime: " + currentRetryTime);
                                    currentRetryTime = 0;
                                }
                            }
                            break;
                        case "last_week":
                        case "last_month":
                            assertThat(actTotal).isEqualTo(preTotal);
                            assertThat(actTotalUsers).isEqualTo(preTotalUsers);
                            assertThat(actSaleItems).isEqualTo(preSaleItems);
                            break;
                    }
                }
            }
        }
    }

    @Step
    public void verifyCashBackPrintBase(HashMap<String, List<String>> pre, HashMap<String, List<String>> current, String quantity, String action, String baseProduct, int currentRetryTime) {
        System.out.println("pre:" + pre);
        System.out.println("act:" + current);
        for (String i : pre.keySet()) {
            for (String j : current.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    System.out.println("key: " + i);
                    List<String> precashback = pre.get(i);
                    List<String> actcashback = current.get(j);
                    int qt = Integer.parseInt(quantity);
                    double preCashback = Double.parseDouble(precashback.get(0));
                    double actCashback = Double.parseDouble(actcashback.get(0));
                    double preCashbackSB = Double.parseDouble(precashback.get(1));
                    double actCashbackSB = Double.parseDouble(actcashback.get(1));
                    double preCashbackPB = Double.parseDouble(precashback.get(2));
                    double actCashbackPB = Double.parseDouble(actcashback.get(2));

                    switch (i) {
                        case "this_week":
                        case "this_month":
                        case "this_year":
                            try {
                                switch (action) {
                                    case "Checkout":
                                        if (baseProduct.equalsIgnoreCase("Gold")) {
                                            assertThat(actCashback).isEqualTo(preCashback + qt);
                                            assertThat(actCashbackSB).isEqualTo(preCashbackSB);
                                            assertThat(actCashbackPB).isEqualTo(preCashbackPB + qt);
                                        } else {
                                            assertThat(actCashback).isEqualTo(preCashback + qt * 0.5);
                                            assertThat(actCashbackSB).isEqualTo(preCashbackSB);
                                            assertThat(actCashbackPB).isEqualTo(preCashbackPB + qt * 0.5);
                                        }
                                        break;
                                    case "Refund":
                                    case "Cancel":
                                        if (baseProduct.equalsIgnoreCase("Gold")) {
                                            assertThat(actCashback).isEqualTo(preCashback - qt);
                                            assertThat(actCashbackSB).isEqualTo(preCashbackSB);
                                            assertThat(actCashbackPB).isEqualTo(preCashbackPB - qt);
                                        } else {
                                            assertThat(actCashback).isEqualTo(preCashback - qt * 0.5);
                                            assertThat(actCashbackSB).isEqualTo(preCashbackSB);
                                            assertThat(actCashbackPB).isEqualTo(preCashbackPB - qt * 0.5);
                                        }
                                        break;
                                }
                            } catch (ComparisonFailure e) {
                                if (currentRetryTime < MAX_RETRY_TIME) {
                                    affPage.waitForApiUpdate(10000);
                                    current = getStatisticsAffiliateByApi(i);
                                    verifyCashBackPrintBase(pre, current, quantity, action, baseProduct, currentRetryTime + 1);
                                } else {
                                    System.out.println("currentRetryTime: " + currentRetryTime);
                                    currentRetryTime = 0;
                                }
                            }
                            break;
                        case "last_week":
                        case "last_month":
                            assertThat(actCashback).isEqualTo(preCashback);
                            assertThat(actCashbackSB).isEqualTo(preCashbackSB);
                            assertThat(actCashbackPB).isEqualTo(preCashbackPB);
                            break;
                    }
                }
            }
        }
    }

    @Step
    public void openReferLink(String fpr) {
        affPage.openUrl(accLink + "/sign-up?fpr=" + fpr);
    }

    @Step
    public void enterEmail(String email) {
        affPage.waitForEverythingComplete();
        affPage.enterInputFieldWithLabel("Enter your email", email);
    }

    @Step
    public void clickBtnStartFreeTrial() {
        affPage.clickBtnStartFreeTrial();
    }

    @Step
    public HashMap<String, List<String>> getStatisticsAffiliateByApiBefore(String time) {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
        String api = apiURL + "/admin/partner/affiliate/statistics?page=1&limit=10&type=" + time;
        System.out.println(api);
        JsonPath js = affPage.getAPISbase(api, userToken);

        total = getData(js, "total");
        totalUser = getData(js, "summary.total_users");
        saleItems = getData(js, "summary.total_unit");
        totalCashback = getData(js, "summary.total_cashback");
        totalCashbackSB = getData(js, "summary.total_cash_back_shop_base");
        totalCashbackPB = getData(js, "summary.total_cash_back_print_base");
        email = getData(js, "list.user_email");
        a.put(time, asList(total, totalUser, saleItems, totalCashback, totalCashbackSB, totalCashbackPB));
        System.out.println("result: " + a);
        return a;
    }

    @Step
    public void enterCode(String code) {
        affPage.enterCode(code);
    }

    @Step
    public void clickBtnSave(String msg) {
        affPage.clickBtn("save");
    }

    @Step
    public void verifyMessage(String msg) {
        if (!msg.equalsIgnoreCase("Success")) {
            affPage.verifyErrorMsg(msg);
        } else affPage.verifySaveSuccess();

    }

    @Step
    public void openLink(String link) {
        affPage.openUrl(link);
        affPage.waitForEverythingComplete();
    }

    @Step
    public void clearCookie(Boolean isCookie) {
        if (isCookie) {
            affPage.deleteAllCookies();
        }
    }

    @Step
    public void verifyRedirectURL(String fpr, Boolean isCookie) {
        String curLink = affPage.getCurrentUrl();
        if (isCookie) {
            assertThat(curLink).doesNotContain(fpr);
        } else assertThat(curLink).containsIgnoringCase(fpr);
    }

    @Step
    public void switchToTheLastestTab() {
        affPage.switchToTheLastestTab();
    }

    @Step
    public void switchToTabInMenu(String tabName) {
        affPage.switchToTabInMenu(tabName);
    }

    public void closePopup() {
        affPage.maximizeWindow();
        affPage.closePopup();
        affPage.waitForEverythingComplete();
    }

    @Step
    public void verifyHeading(String heading) {
        affPage.verifyHeading(heading);
    }

    @Step
    public void verifyTabs(String tabName, int res) {
        affPage.verifyTabs(tabName, res);
    }

    @Step
    public void verifyAffiliateShareAndInvitationBlockDisplay() {
        affPage.verifyAffiliateShareAndInvitationBlockDisplay();
    }

    @Step
    public void verifyBlockCashbackRuleAndRateDisplay() {
        affPage.verifyBlockCashbackRuleAndRateDisplay();
    }

    @Step
    public void verifyBlockAffiliatBenefitDisplay() {
        affPage.verifyBlockAffiliatBenefitDisplay();
    }

    public void verifyInviationCode(String code) {
        String invicode = affPage.getInviCode();
        assertThat(invicode).isEqualTo(code);
    }

    public void verifyBenefitAndCashbachrateDisplay() {

        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
        String api = apiURL + "/admin/partner/affiliate/promoter?user_id=" + userID;
        JsonPath js = affPage.getAPISbase(api, userToken);

        ArrayList benefits = (getDataByKey(js, "affiliate_info.benefit"));
        ArrayList<String> benefitList = new ArrayList<>();

        for (Object o : benefits){
            benefitList.add((String) o);
        }
        String goldBaseRate = getData(js, "affiliate_info.SaleCashBackGroup.Rules[0].rate_map[0].rate_per_item");
        String silverBaseRate = getData(js, "affiliate_info.SaleCashBackGroup.Rules[1].rate_map[0].rate_per_item");
        String newUser = getData(js, "affiliate_info.affiliate_next_rank_rule.ref_user_amount");
        String totalItems = getData(js, "affiliate_info.affiliate_next_rank_rule.total_item_in_month");
        String usersHasOrder = getData(js, "affiliate_info.affiliate_next_rank_rule.user_has_order");
        String groupName = getData(js, "affiliate_info.name");

        affPage.verifyGroupName(groupName);
        affPage.verifyBenefit(benefitList);
        affPage.verifyGoldBaseRate(goldBaseRate);
        affPage.verifySilverBaseRate(silverBaseRate);
        affPage.verifyNewUser(newUser);
        affPage.verifyTotalItems(totalItems);
        affPage.verifyUserHasOrder(usersHasOrder);
    }

    public void verifyEmailBecameRefOfMerchant(String email) {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
        long curTime = System.currentTimeMillis();
        String api = apiURL + "/admin/partner/affiliate/search?list_email=" + email + "&is_search=true&limit=10&page=1&had_summary=true&start_date=1623542400&end_date=" + curTime;
        System.out.println("api: " + api);
        JsonPath js = affPage.getAPISbase(api, userToken);
        String emailapi = getData(js, "list[0].user_email");
        assertThat(email).isEqualTo(emailapi);
    }

    public void verifyHowItWorkDisplay() {
        affPage.verifyHowItWorkDisplay();

    }

    public void verifyCashbackRateDisplay() {
        affPage.verifyCashbackRateDisplay();
    }

    public void verifyProfitStatisticDesc() {
        affPage.verifyProfitStatisticDesc();
    }

    public void verifyFilterDisplay() {
        affPage.verifyFilterDisplay();
    }

    public void verifyProfitStatisticTableDisplay() {
        affPage.verifyProfitStatisticTableDisplay();
    }

    public void verifyStatisticOfShopBase() {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
//        affPage.wait();
        String api = apiURL + "/admin/partner/affiliate/statistics?page=1&limit=10&type=this_week";
        System.out.println(api);
        JsonPath js = affPage.getAPISbase(api, userToken);

        String totalUser = getData(js, "summary.total_users");
        String totalCashback = getData(js, "summary.total_cash_back_shop_base");
        affPage.verifySignUp(totalUser);
        affPage.verifyCashBack(totalCashback);

        String url = apiURL + "/admin/partner/affiliate/user-log?page=1&limit=5&type=this_week";
        System.out.println(url);
        JsonPath jh = affPage.getAPISbase(url, userToken);
        String clickSBase = getData(jh, "total_shop_base");
        affPage.verifyClickShopBase(clickSBase);
    }

    public void clickTabShopBaseAffiliateProgram() {
        affPage.clickLinkTextWithLabel("ShopBase Affiliate Program");
    }

    public List<String> getNumberOfClickByApi() {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
        String url = apiURL + "/admin/partner/affiliate/user-log?page=1&limit=5&type=this_week";
        System.out.println(url);
        List<String> clicks = new ArrayList<>();
        JsonPath jh = affPage.getAPISbase(url, userToken);
        String clickPBase = getData(jh, "total_print_base");
        String clickSBase = getData(jh, "total_shop_base");
        String total = getData(jh, "total");
        clicks.add(total);
        clicks.add(clickPBase);
        clicks.add(clickSBase);
        return clicks;
    }

    public void verifyStatisticNumber(List<String> preClick, List<String> curClick, int currentRetryTime) {
        try {
            int preTotal = Integer.parseInt(preClick.get(0));
            int prePBase = Integer.parseInt(preClick.get(1));
            int preSBase = Integer.parseInt(preClick.get(2));
            int curTotal = Integer.parseInt(curClick.get(0));
            int curPBase = Integer.parseInt(curClick.get(1));
            int curSBase = Integer.parseInt(curClick.get(2));
            assertThat(curTotal).isEqualTo(preTotal + 4);
            assertThat(curPBase).isEqualTo(prePBase + 2);
            assertThat(curSBase).isEqualTo(preSBase + 2);
        } catch (ComparisonFailure e) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                affPage.waitForApiUpdate(10000);
                curClick = getNumberOfClickByApi();
                verifyStatisticNumber(preClick, curClick, currentRetryTime + 1);
            } else {
                System.out.println("currentRetryTime: " + currentRetryTime);
                currentRetryTime = 0;
            }

        }

    }

    public void refreshPage() {
        affPage.refreshPage();
    }

    public void runExecutor() {
        affPage.runExecutor();
    }

    public void chooseLink(String link) {
        affPage.selectDdlWithLabel("Choose link", link);
    }

    public void clickTabManageLink() {
        affPage.clickTabManageLink();
    }

    public void clickAddNew() {
        affPage.clickBtn("Add new");
    }

    public void inputSubId(String subID) {
        affPage.enterInputFieldWithLabel("Sub-id", subID);
    }

    public void verifyPreviewSubId(String link, String subID) {
        affPage.verifyPreviewSubId(link, subID);
    }

    public void clickSubmit() {
        affPage.clickBtn("Submit");
    }

    public void verifyErrorMessage(String msg) {
        affPage.verifyErrorMessage(msg);
    }

    public void verifyCreateSublinkSuccess(String link, String subID) {
        affPage.verifyCreateSublinkSuccess(link, subID);
    }

    public void enterKeyword(String keyword) {
        affPage.enterKeyword(keyword);
    }

    public void verifyresult(String keyword, String result) {
        if (result.equalsIgnoreCase("yes")) {
            affPage.verifyLinkDislay(keyword);
        } else affPage.verifyNoResult(keyword);
    }

    public void clickPrintBaseAmbassadorProgram() {
        affPage.clickLinkTextWithLabel("PrintBase Ambassador Program");
    }

    public void verifyCannotDeleteRootlink() {
        affPage.verifyCannotDeleteRootlink();
    }

    public void clickDelete(String subID) {
        affPage.clickDelete(subID);
        affPage.clickDeleteOnConfirmPopup();
    }

    public void verifyDeleteLinkSuccessfully(String link) {
        affPage.verifyDeleteLinkSuccessfully(link);
    }

    public void clickMenu() {
        affPage.clickMenu();
    }

    public void clickPartnerDashboard() {
        affPage.clickPartnerDashboard();
    }

    public void pushQueueCashbackByApi(int orderID, JsonArray cmsIds) {
        int time = (int) (System.currentTimeMillis() / 1000);
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUserPartner();
            userToken = affPage.getAccessTokenUser();
        }
        JsonArray orders = new JsonArray();
        String url = apiURL + "/admin/partner/affiliate/check";
        JsonObject js = new JsonObject();
        js.addProperty("force_time", time);
        if (orderID != 0)
            orders.add(orderID);
        js.add("commission_ids", cmsIds);
        js.add("order_ids", orders);
        affPage.postAPISbase(url, js, userToken);
    }

    @Step
    public void updateEndFreeTrialOfShop(String shopID) {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUser();
        }
        String api = apiURL + "/admin/partner/affiliate/update-shop-to-get-commision?shop_id=" + shopID;
        JsonObject jo = new JsonObject();
        affPage.putAPI(api, jo);
    }

    @Step
    public String getShopAccesstoken(String shopname) {
        return affPage.getAccessTokenShopBase(shopname);
    }

    @Step
    public JsonArray getCommissionIds() {
        String accessToken = "";
        if (accessToken.isEmpty()) {
            accessToken = affPage.getAccessTokenUser();
        }
        long cur = (System.currentTimeMillis()) / 1000;
        String user_id = LoadObject.getProperty("user_id");
        String api = apiURL + "/admin/partner/affiliate/get-commision-ids?user_id=" + user_id;
        JsonObject body = new JsonObject();
        body.addProperty("from_date", cur - 7200);
        body.addProperty("end_date", cur);
        body.addProperty("commission_type", "subscription");
        JsonPath js = affPage.postAPISbase(api, body, accessToken);
        String ids = getData(js, "ids");
        JsonArray ja = new JsonArray();
        ids = (ids.replaceAll("]", "").replaceAll("\\[", ""));
        String id[] = ids.split(",");
        for (int i = 0; i < id.length; i++) {
            ja.add(Integer.parseInt((id[i]).trim()));
        }
        System.out.println("ids: " + ids);
        return ja;
    }

    @Step
    public String getDomain() {
        return affPage.getDomain();
    }

    @Step
    public String getShopID(String access_token, String domain) {
        String url = "https://" + domain + "/admin/shop.json" + "?access_token=" + access_token;
        JsonPath js = affPage.getAPI(url);
        String shopID = affPage.getData(js, "shop.id").toString();
        System.out.println(shopID);
        return shopID;
    }

    @Step
    public void verifyCashBackShopbase(HashMap<String, List<String>> pre, HashMap<String, List<String>> current, String plan, String cycle) {
        double price = 0;
        switch (cycle) {
            case "Monthly":
                switch (plan) {
                    case "Basic Base":
                        price = 10 / 100 * 19;
                        break;
                    case "Standard Base":
                        price = 20 / 100 * 59;
                        break;
                    case "Pro Base":
                        price = 30 / 100 * 249;
                        break;
                }
                break;
            case "Yearly":
                switch (plan) {
                    case "Basic Base":
                        price = 10 / 100 * 120;
                        break;
                    case "Standard Base":
                        price = 20 / 100 * 240;
                        break;
                    case "Pro Base":
                        price = 30 / 100 * 456;
                        break;
                }
                break;
        }
        for (String i : pre.keySet()) {
            for (String j : current.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    List<String> precashback = pre.get(i);
                    List<String> actcashback = current.get(j);
                    double preCashback = Double.parseDouble(precashback.get(0));
                    double actCashback = Double.parseDouble(actcashback.get(0));
                    double preCashbackSB = Double.parseDouble(precashback.get(1));
                    double actCashbackSB = Double.parseDouble(actcashback.get(1));
                    double preCashbackPB = Double.parseDouble(precashback.get(2));
                    double actCashbackPB = Double.parseDouble(actcashback.get(2));
                    switch (i) {
                        case "this_week":
                        case "this_month":
                        case "this_year":
                            assertThat(actCashback).isEqualTo(preCashback + price);
                            assertThat(actCashbackSB).isEqualTo(preCashbackSB + price);
                            assertThat(actCashbackPB).isEqualTo(preCashbackPB);
                            break;
                        case "last_week":
                        case "last_month":
                            assertThat(actCashback).isEqualTo(preCashback);
                            assertThat(actCashbackSB).isEqualTo(preCashbackSB);
                            assertThat(actCashbackPB).isEqualTo(preCashbackPB);
                            break;

                    }
                }
            }
        }
    }

    public HashMap<String, List<String>> getCashbackStatisticsAffiliateByApi(String time) {
        if (userToken.isEmpty()) {
            userToken = affPage.getAccessTokenUserPartner();
        }
        String api = apiURL + "/admin/partner/affiliate/statistics?page=1&limit=10&type=" + time;
        System.out.println(api);
        JsonPath js = affPage.getAPISbase(api, userToken);
        totalCashback = getData(js, "summary.total_cashback");
        totalCashbackSB = getData(js, "summary.total_cash_back_shop_base");
        totalCashbackPB = getData(js, "summary.total_cash_back_print_base");
        result.put(time, asList(totalCashback, totalCashbackSB, totalCashbackPB));
        System.out.println("result: " + result);
        return result;
    }
}
