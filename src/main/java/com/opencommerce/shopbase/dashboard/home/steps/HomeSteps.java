package com.opencommerce.shopbase.dashboard.home.steps;

import com.opencommerce.shopbase.dashboard.home.page.HomePage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.questions.ValueOf;
import net.thucydides.core.annotations.Step;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomeSteps {
    HomePage homePage;
    String shop = LoadObject.getProperty("shop");
    String oldPoint = "";

    public void verifyHomeDisplay() {
        homePage.verifyHomeDisplay();
    }

    @Step
    public String getData(JsonPath jsonPath, String key) {
        Object data = homePage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    @Step
    public HashMap<Integer, List<String>> getlistWidget() {
        String acctoken = "";
        String api = "https://" + shop + "/admin/dashboards/widget-dashboard.json?no_cache=true&package=pro&region=vietnam&business_type=General+Dropship";
        if (acctoken.isEmpty()) {
            acctoken = homePage.getAccessTokenShopBase();
            System.out.println("token: " + acctoken);
        }
        JsonPath js = homePage.getAPISbase(api, acctoken);
        HashMap<Integer, List<String>> widget = new HashMap<>();
        int count = (js.getList("list")).size();
        for (int i = 0; i < count; i++) {
            String id = getData(js, "list[" + i + "].id"); //0
            String name = getData(js, "list[" + i + "].name"); //1
            String type = getData(js, "list[" + i + "].widget_type"); //2
            String mainTitle = getData(js, "list[" + i + "].widget_config.en.main_title"); //3
            String mainTDesc = getData(js, "list[" + i + "].widget_config.en.main_description"); //4
            String assets_type = getData(js, "list[" + i + "].widget_config.en.assets_type"); //5
            String priBtnText = getData(js, "list[" + i + "].widget_config.en.primary_button_text"); //6
            String priBtnLink = getData(js, "list[" + i + "].widget_config.en.primary_button_link"); //7
            String scBtnText = getData(js, "list[" + i + "].widget_config.en.secondary_button_text"); //8
            String scBtnLink = getData(js, "list[" + i + "].widget_config.en.secondary_button_link"); //9
            String video = getData(js, "list[" + i + "].widget_config.en.video_url"); //10
            String image = getData(js, "list[" + i + "].widget_config.en.img_url"); //11
            String day = getData(js, "list[" + i + "].widget_config.en.day"); //12
            String reward = getData(js, "list[" + i + "].widget_config.en.reward"); //13
            String goal = getData(js, "list[" + i + "].widget_config.en.goal"); //14

            widget.put(i, asList(id, name, type, mainTitle, mainTDesc, assets_type, priBtnText, priBtnLink, scBtnText, scBtnLink, video, image, day, reward, goal));
        }
        System.out.println("widget: " + widget);
        return widget;
    }

    @Step
    public HashMap<String, List<String>> getListOfAWidget(String id) {
        String acctoken = "";
        String api = "https://" + shop + "/admin/dashboards/widget-dashboard.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = homePage.getAccessTokenShopBase();
        }
        HashMap<String, List<String>> widget = new HashMap<>();
        JsonPath js = homePage.getAPISbase(api, acctoken);
        int count = (js.getList("list")).size();
        for (int i = 0; i < count; i++) {
            if (id.equalsIgnoreCase(getData(js, "list[" + i + "].id"))) {
                int countList = (js.getList("list[" + i + "].widget_config.en.list_sub")).size();
                for (int j = 0; i < countList; i++) {
                    String title = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].title");
                    String description = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].description");
                    String link = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].link");
                    String imgURL = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].img_url");
                    widget.put(String.valueOf(j), asList(title, description, link, imgURL));
                }
            }
        }
        return widget;
    }

    @Step
    public void verifyWidgetDisplay(boolean isShow) {
        homePage.verifyWidgetDisplay(isShow);
    }
    @Step
    public int getNumberOfWidget() {
        homePage.refreshPage();
        return homePage.getNumberOfWidget();
    }

    @Step
    public void verifyTitleWidget(String title, int res) {
        homePage.verifyTitleWidget(title, res);
    }

    @Step
    public void verifyDescWidget(String des, int res) {
        homePage.verifyDescWidget(des, res);
    }

    @Step
    public void verifyTypeWidget(String type, int res) {
        homePage.verifyTypeWidge(type, res);
    }

    @Step
    public void verifDetailWidget(List<String> widget, int res) {
        String type = widget.get(2);
        switch (type) {
            case "Basic":
                homePage.verifyPrimaryBtn(widget.get(6), widget.get(7), res);
                homePage.verifySecondaryBtn(widget.get(8), widget.get(9), res);
                homePage.verifyAssetWidget(widget.get(5), widget.get(10), widget.get(11), res);
                break;
            case "Center":
                homePage.verifyPrimaryBtn(widget.get(6), widget.get(7), res);
                homePage.verifyImage(widget.get(11), res);
                break;
            case "List":
                HashMap<String, List<String>> list = getListOfAWidget(widget.get(0));
                int numberList = homePage.getNumbeItemInlist(res);
                assertThat(numberList).isEqualTo(list.size());
                for (int i = 0; i < numberList; i++) {
                    List<String> value = list.get(i);
                    homePage.verifySubListTitle(value.get(0), res, i + 1);
                    homePage.verifySubListDesc(value.get(1), res, i + 1);
                    homePage.verifySubListImage(value.get(2), value.get(3), res, i + 1);
                }
                break;
            case "race-challenge":
                if (!homePage.isEndRaceChallenge(res)) {
                    homePage.verifyPrimaryBtn(widget.get(6), widget.get(7), res);
                    homePage.verifySecondaryBtn(widget.get(8), widget.get(9), res);
                } else homePage.verifyFinishChallenge(res);
                homePage.verifyReward(widget.get(13), res);
                homePage.verifyGoal(widget.get(14), res);
                break;

        }
    }

    @Step
    public void clickonAddnewbutton() {
        homePage.clickonAddnewbutton();
    }

    @Step
    public void verifyCreateWidgetRaceChallengeSuccess() {
        homePage.verifyCreateWidgetRaceChallengeSuccess();
    }

    @Step
    public void clickDetelebutton(String name) {
        homePage.clickDeletebutton(name);
    }

    @Step
    public void clickYesbutton() {
        homePage.clickYesbutton();
    }

    @Step
    public void verifyTitleWidgetRaceChallenge() {
        homePage.verifyTitleWidgetRaceChallenge();
    }

    @Step
    public void verifyDescriptionWidgetRaceChallenge() {
        homePage.verifyDescriptionWidgetRaceChallenge();
    }

    @Step
    public void verifyGoalWidgetRaceChallenge() {
        homePage.verifyGoalWidgetRaceChallenge();
    }

    @Step
    public void verifyBnt() {
        homePage.verifyBntText();
    }

    @Step
    public void verifySecBnt() {
        homePage.verifySecBntText();
    }

    @Step
    public void verifyPointlWidgetRaceChallenge() {
        homePage.verifyPointWidgetRaceChallenge();
    }

    @Step
    public void chooseCustomizeStore(String storeCustomize) {
        homePage.clickBtn("No thanks, I don't want to import");
    }

    @Step
    public void openURL(String web) {
        homePage.openUrl(web);
    }

    @Step
    public void verifyContestdata(String shoptype) {
        List<String> data = new ArrayList<>();
        data = getContestData();
        oldPoint = data.get(0);
        homePage.verifyCurrentPoint(shoptype, data.get(0));
//        homePage.verifyCurrentGoalPoint(data.get(1));
//        homePage.verifyCurrentReward(data.get(2));
//        homePage.verifyNextReward(data.get(3));
        homePage.verifyContestTime(data.get(4), data.get(5));
    }

    @Step
    public List<String> getContestData() {
        String acctoken = "";
        String api = "https://" + shop + "/admin/contest.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = homePage.getAccessTokenShopBase();
        }
        JsonPath js = homePage.getAPISbase(api, acctoken);
        String curPoint = getData(js, "contest_data_handle.current_point");
        String curGoalPoint = getData(js, "contest_data_handle.current_goal_point");
        String curReward = getData(js, "contest_data_handle.current_reward");
        String nextReward = getData(js, "contest_data_handle.next_reward");
        String startDate = getData(js, "contest_data_handle.start_at");
        String endate = getData(js, "contest_data_handle.end_at");
        return asList(curPoint, curGoalPoint, curReward, nextReward, startDate, endate);
    }

    @Step
    public void verifyUIsetting() {
        List<String> ui = new ArrayList<>();
        ui = getContestUI();
        homePage.verifyHeader(ui.get(0));
        homePage.verifySubtext(ui.get(1));
        homePage.verifyImageContest(ui.get(2));
        homePage.verifyLinkContest(ui.get(3));
        homePage.verifyIconStart(ui.get(4));
        homePage.verifyIconFinish(ui.get(5));
//        homePage.verifyPriColorProBar(ui.get(6));
//        homePage.verifySecColorProBar(ui.get(7));
    }

    @Step
    public List<String> getContestUI() {
        String acctoken = "";
        String api = "https://" + shop + "/admin/contest.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = homePage.getAccessTokenShopBase();
        }
        JsonPath js = homePage.getAPISbase(api, acctoken);
        String header = getData(js, "contest_info.ui_setting.in_contest.message_header.content");
        String subtext = getData(js, "contest_info.ui_setting.in_contest.message_sub_text.content");
        String image = getData(js, "contest_info.ui_setting.in_contest.image");
        String link = getData(js, "contest_info.ui_setting.in_contest.learn_more_link");
        String iconStart = getData(js, "contest_info.ui_setting.in_contest.progress_bar.icon_start");
        String iconFinish = getData(js, "contest_info.ui_setting.in_contest.progress_bar.icon_finish");
        String primaryColor = getData(js, "contest_info.ui_setting.in_contest.progress_bar.primary_color");
        String secondaryColor = getData(js, "contest_info.ui_setting.in_contest.progress_bar.secondary_color");
        return asList(header, subtext, image, link, iconStart, iconFinish, primaryColor, secondaryColor);
    }

    @Step
    public void verifyListPrice(String shoptype) {
        homePage.waitForEverythingComplete();
        HashMap<Integer, List<String>> prices = new HashMap<Integer, List<String>>();
        prices = getListPrices();
        System.out.println("prices: " + prices);
        int count = homePage.getNumberOfPrices();
        assertThat(count).isEqualTo(prices.size());
        for (int i = 0; i < count; i++) {
            List<String> pr = prices.get(i);
            homePage.verifyTheshold(shoptype, pr.get(0), i + 1);
            homePage.verifyPrize(pr.get(1), i + 1);
        }

    }

    @Step
    private HashMap<Integer, List<String>> getListPrices() {
        HashMap<Integer, List<String>> prices = new HashMap<Integer, List<String>>();
        String acctoken = "";
        String api = "https://" + shop + "/admin/contest.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = homePage.getAccessTokenShopBase();
        }
        JsonPath js = homePage.getAPISbase(api, acctoken);
        int size = (js.getList("contest_info.metrics_setting.list_prize_settings")).size();
        for (int i = 0; i < size; i++) {
            String threshold = getData(js, "contest_info.metrics_setting.list_prize_settings[" + i + "].thresh_hold");
            String prize = getData(js, "contest_info.metrics_setting.list_prize_settings[" + i + "].prize");
            prices.put(i, asList(threshold, prize));
        }
        return prices;
    }
    @Step
    public HashMap<String, List<String>> getContentOnboarding(String shopname) {
        String domain = LoadObject.getProperty("subdomain");
        if (!shopname.isEmpty()) {
            shop = shopname + domain;
        }
        HashMap<String, List<String>> items = new HashMap<>();
        String acctoken = "";
        String api = "https://" + shop + "/admin/dashboards/onboarding.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = homePage.getAccessTokenShopBase(shop);
        }
        JsonPath js = homePage.getAPISbase(api, acctoken);
        int size = (js.getList("content.en.onboardings")).size();
        for (int i = 0; i < size; i++) {
            int number = (js.getList("content.en.onboardings[" + i + "].items")).size();
            List<String> content = new ArrayList<>();
            for (int j = 0; j < number; j++) {
                String title = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].title"); //0
                String titleCont = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.title");//1
                String button = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.button");//2
                String desc = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.desc");//3
                String image = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.image");//4
                String titleContAfterDone = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.title_text_after_done");//5
                String buttonAfterDone = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.button_text_after_done");//6
                String descAfterDone = getData(js, "content.en.onboardings[" + i + "].items[" + j + "].content.desc_text_after_done");//7

                content = asList(title, titleCont, button, desc, image, titleContAfterDone, buttonAfterDone, descAfterDone);
                items.put("on" + i + "-" + j, content);
                System.out.println("items: " + items);
            }

        }
        return items;
    }

    @Step
    public void verifyOnboardingDisplay(HashMap<String, List<String>> items) {
        String preKey = "";
        for (String i : items.keySet()) {
            List<String> item = items.get(i);
            System.out.println("i: " + i);
            if (!i.contains(preKey)) {
                homePage.clickNextSlideOnboarding();
            }
            int index;
            if (i.contains("on0"))
                index = 1;
            else index = 2;
            homePage.clickTitleTabOnboarding(item.get(0));
            homePage.verifyTitleItem(item.get(1), index);
            homePage.verifyButtonItem(item.get(2), index);
            homePage.verifyDescItem(item.get(3), index);
            homePage.verifyImageItem(item.get(4), index);
            preKey = i.substring(0, 3);
        }
    }

    @Step
    public void skipAllTaskInOther() {
        homePage.clickOtherWaysToGetStarted();
        int num = homePage.getListActiveTasks();
        for (int i = 0; i < num; i++)
            homePage.clickSkip();
    }

    @Step
    public void verifyHideOtherWaysToGetStarted() {
        homePage.verifyHideOtherWaysToGetStarted();
    }

    @Step
    public void skipAllTaskInProgressBar() {
        homePage.skipAllTaskInProgressBar();
    }

    @Step
    public void verifyHideOnboarding() {
        homePage.verifyHideOnboarding();
    }

    @Step
    public void verifyHideProgresbar() {
        homePage.verifyHideProgresbar();
    }

    @Step
    public void verifyDisplayDisablePassworkBlock(boolean isDisplay) {
        homePage.verifyDisplayDisablePassworkBlock(isDisplay);
    }

    @Step
    public void disablePassword() {
        homePage.clickBtn("Disable password");
    }

    @Step
    public void verifyDisplayConfirmPlan(boolean isDisplay) {
        homePage.refreshPage();
        homePage.verifyDisplayConfirmPlan(isDisplay);
    }

    @Step
    public void switchToLastestTab() {
        homePage.waitForEverythingComplete();
        homePage.waitForPageLoad();
        homePage.switchToTheLastestTab();
    }

    @Step
    public void switchToFirstTab() {
        homePage.switchToTheLastestTab();
    }

    @Step
    public void clickBtnSelectAPlan() {
        homePage.clickBtn("Select a plan");
    }

    @Step
    public void clickTitleOnboarding(String title) {
        homePage.clickTitleTabOnboarding(title);
    }

    @Step
    public void clickBtnOnboarding(String btn, String res) {
        homePage.clickBtnOnboarding(btn, res);
    }

    @Step
    public void verifyCompletedItemOnboarding(String title, String titleItem, String button, String desc, int index) {
        homePage.verifyCompletedItemOnboarding(title);
        homePage.clickTitleTabOnboarding(title);
        homePage.verifyTitleItem(titleItem, index);
        homePage.verifyButtonItem(button, index);
        homePage.verifyDescItem(desc, index);
    }

    @Step
    public void clickOtherWaysToGetStarted() {
        homePage.clickOtherWaysToGetStarted();
    }

    @Step
    public void clickButtonOtherWays(String label) {
        homePage.clickButtonOtherWays(label);
    }

    @Step
    public void verifyCompleteItemOtherWays(String item) {
        homePage.verifyCompleteItemOtherWays(item);
        homePage.verifyDisableButtonOtherWays(item);
    }

    public void selectPlanInTheHomePage() {
        homePage.selectPlanInTheHomePage();
    }

    public void openUpsellscreen() { homePage.openUpsellscreen(); }

    public void clickBtnCreateOffer() { homePage.clickBtnCreateOffer(); }

    public void chooseRecommendVariantWith(String recommendVariantWith) {
        homePage.chooseRecommendVariantWith(recommendVariantWith);
    }

    public void clickBtnSubmitOffer() { homePage. clickBtnSubmitOffer(); }


    public void clickBtnDisablePassword() {homePage.clickBtnDisablePassword();
    }

    public void clickNextSlideOnboarding() {
        homePage.clickNextSlideOnboarding();
    }
}
