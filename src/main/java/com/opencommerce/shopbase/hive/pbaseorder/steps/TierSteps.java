package com.opencommerce.shopbase.hive.pbaseorder.steps;

import com.opencommerce.shopbase.hive.pbaseorder.pages.TierPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import org.junit.ComparisonFailure;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TierSteps {

    TierPage tierPage;
    String shop = LoadObject.getProperty("shop");
    String hiveLink = LoadObject.getProperty("hive");
    String gapiURL = LoadObject.getProperty("gapi.url");


    @Step
    public void verifyNumberOfTier() {
        tierPage.verifyNumberOfTier();
    }

    @Step
    public void verifyTierInformation() {
        tierPage.verifyTierName();
        tierPage.verifyTierThreshold();
        tierPage.verifyTierThresholdKeep();
        tierPage.verifyTierCycle();
    }

    @Step
    public void clickBtnEditTier(String tier) {
        tierPage.clickBtnEditTier(tier);
    }

    @Step
    public void enterTierName(String name) {
        tierPage.enterInputFieldWithLabel("Name", name);
    }

    @Step
    public void enterThreshold(String threshold) {
        tierPage.enterInputFieldWithLabel("Threshold", threshold);
    }

    @Step
    public void enterThresholdKeep(String thresholdKeep) {
        tierPage.enterInputFieldWithLabel("Threshold Giữ Hạng", thresholdKeep);
    }

    @Step
    public void enterCycle(String cycle) {
        tierPage.scrollIntoCycleInput();
        tierPage.enterInputFieldWithLabel("Cycle", cycle);
    }

    @Step
    public void enterDiscount(String discount) {
//        tierPage.enterInputFieldWithLabel("Discount(%)", discount);
    }

    @Step
    public void clickBtnUpdateAndClose() {
        tierPage.clickBtn("Update and close");
    }

    @Step
    public void verifyMessages(String msg) {
        if (!msg.equalsIgnoreCase("Success")) {
            tierPage.verifyErrorMessages(msg);
            tierPage.clearMessages();
        } else verifyTierInformation();
    }

    @Step
    public HashMap<String, String> getTierInformationOfUserByApi() {
        String acctoken = "";
        String api = gapiURL + "/admin/tiers/user/?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = tierPage.getAccessTokenUser();
            System.out.println("Token: " + acctoken);
        }
        System.out.println("api: " + api);
        JsonPath js = tierPage.getAPISbase(api, acctoken);
        String avaiBcoin = getData(js, "tier.list.available_coin");
        String bCoin = getData(js, "tier.list.rank_coin");
        String tier = getData(js, "tier.list.tier_level");
        String cycleStart = getData(js, "tier.list.cycle_start_at");
        String cycleEnd = getData(js, "tier.list.cycle_end_at");
        HashMap<String, String> tierInfo = new HashMap<>();

        tierInfo.put("BCoin", bCoin.replaceAll("\\[|]", ""));
        tierInfo.put("AvailableBCoin", avaiBcoin.replaceAll("\\[|]", ""));
        tierInfo.put("Tier", tier.replaceAll("\\[|]", ""));
        tierInfo.put("CycleStart", cycleStart.replaceAll("\\[|]", ""));
        tierInfo.put("CycleEnd", cycleEnd.replaceAll("\\[|]", ""));
        return tierInfo;
    }

    @Step
    public String getData(JsonPath jsonPath, String key) {
        Object data = tierPage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    public HashMap<String, List<String>> getTierListTierApi() {
        String acctoken = "";
        String api = "https://" + shop + "/admin/tiers.json";
        if (acctoken.isEmpty()) {
            acctoken = tierPage.getAccessTokenShopBase();
        }
        JsonPath js = tierPage.getAPISbase(api, acctoken);
        HashMap<String, List<String>> tierList = new HashMap<>();
        String expressionlevel = "tiers.list[%d].level";
        String expressionname = "tiers.list[%d].name";
        String expressionthreshold = "tiers.list[%d].threshold";
        String expressionthresholdKeep = "tiers.list[%d].keep_tier_threshold";
        String expressioncycle = "tiers.list[%d].cycle";

        for (int i = 0; i < 5; i++) {
            String level = getData(js, String.format(expressionlevel, i));
            String name = getData(js, String.format(expressionname, i));
            String threshold = getData(js, String.format(expressionthreshold, i));
            String thresholdKeep = getData(js, String.format(expressionthresholdKeep, i));
            String cycle = getData(js, String.format(expressioncycle, i));
            tierList.put(level, asList(name, threshold, thresholdKeep, cycle));
        }
        return tierList;
    }

    @Step
    public double convertCoin(String base, int quantity) {
        double coin;
        if (base.equalsIgnoreCase("Gold")) {
            coin = quantity;
        } else coin = 0.5 * quantity;
        return coin;
    }

    int a = 0;

    @Step
    public void verifyBcoin(HashMap<String, String> pre, HashMap<String, String> current, String action, double coin) {

        for (String i : pre.keySet()) {
            for (String j : current.keySet()) {
                if (i.equalsIgnoreCase(j)) {
                    double preBcoin = Double.parseDouble(pre.get("BCoin"));
                    double preABcoin = Double.parseDouble(pre.get("AvailableBCoin"));
                    double curBcoin = Double.parseDouble(current.get("BCoin"));
                    double curABcoin = Double.parseDouble(current.get("AvailableBCoin"));
                    switch (action) {
                        case "Order":
                            while ((curBcoin != preBcoin + coin && curABcoin != preABcoin + coin) && a < 10) {
                                System.out.println((curBcoin != preBcoin + coin && curABcoin != preABcoin + coin) && a < 10);
                                System.out.println(curBcoin != preBcoin + coin);
                                System.out.println(curABcoin != preABcoin + coin);
                                System.out.println(preBcoin + " - " + preABcoin);
                                System.out.println(curBcoin + " - " + curABcoin);
                                waitToAPIupdate(30000);
                                clearCache();
                                current = getTierInformationOfUserByApi();
                                curBcoin = Double.parseDouble(current.get("BCoin"));
                                curABcoin = Double.parseDouble(current.get("AvailableBCoin"));
                                System.out.println("current: " + current);
                                System.out.println(preBcoin + " - " + preABcoin);
                                System.out.println(curBcoin + " - " + curABcoin);
                                System.out.println(coin + " - " + a);
                                System.out.println(preBcoin + coin);
                                System.out.println(preABcoin + coin);
                                System.out.println(curBcoin != preBcoin + coin);
                                System.out.println(curABcoin != preABcoin + coin);
                                a++;
//                                verifyBcoin(pre, current, action, coin);
                            }
                            assertThat(curBcoin).isEqualTo(preBcoin + coin);
                            assertThat(curABcoin).isEqualTo(preABcoin + coin);
                            break;
                        case "Cancel":
                        case "Refund":
                            while ((curBcoin != preBcoin - coin && curABcoin != preABcoin - coin) && a < 10) {
                                System.out.println((curBcoin != preBcoin + coin && curABcoin != preABcoin + coin) && a < 10);
                                System.out.println(curBcoin != preBcoin + coin);
                                System.out.println(curABcoin != preABcoin + coin);
                                System.out.println(preBcoin + " - " + preABcoin);
                                System.out.println(curBcoin + " - " + curABcoin); waitToAPIupdate(30000);
                                clearCache();
                                current = getTierInformationOfUserByApi();
                                curBcoin = Double.parseDouble(current.get("BCoin"));
                                curABcoin = Double.parseDouble(current.get("AvailableBCoin"));
                                System.out.println("current: " + current);
                                System.out.println(preBcoin + " - " + preABcoin);
                                System.out.println(curBcoin + " - " + curABcoin);
                                System.out.println(coin + " - " + a);
                                System.out.println(preBcoin + coin);
                                System.out.println(preABcoin + coin);
                                System.out.println(curBcoin != preBcoin + coin);
                                System.out.println(curABcoin != preABcoin + coin);
                                a++;
//                                verifyBcoin(pre, current, action, coin);
                            }
                            assertThat(curBcoin).isEqualTo(preBcoin - coin);
                            assertThat(curABcoin).isEqualTo(preABcoin - coin);
                    }
                }
            }
        }
    }

    @Step
    public void verifyTierLevel(HashMap<String, List<String>> tierInfo, HashMap<String, String> pre, HashMap<String, String> current) {
        String preLevel = pre.get("Tier");
        System.out.println("pre level: " + preLevel);
        String currentLevel = current.get("Tier");
        System.out.println("cur level: " + currentLevel);

        for (String i : tierInfo.keySet()) {
            System.out.println("i: " + i);
            if (preLevel.equals(i)) {
                List<String> nextTier = tierInfo.get(String.valueOf(Integer.parseInt(i) + 1));
                List<String> curTier = tierInfo.get(String.valueOf(Integer.parseInt(i)));
                System.out.println("next Tier:" + nextTier);
                double curLevel = Double.parseDouble(currentLevel);
                String curStart = current.get("CycleStart");
                String curEnd = current.get("CycleEnd");
                System.out.println("CycleStart: " + 1616803200);
                double prLevel = Double.parseDouble(preLevel);
                double curBCoin = Double.parseDouble(current.get("BCoin"));
//                double nextTierThreshold = Double.parseDouble(nextTier.get(1));
                double curTierThresholdKeep = Double.parseDouble(curTier.get(2));
//                String nextCycle = nextTier.get(3);

                if (preLevel.equals("5")) {
                    assertThat(curLevel).isEqualTo(prLevel);
                } else if (curBCoin >= Double.parseDouble(nextTier.get(1))) { // bcoin cua tier hien tai > thredhold cua next tier
                    assertThat(curLevel).isEqualTo(prLevel + 1); // tier nang hang len 1 level
                    tierPage.verifyResetCycle(curStart, curEnd, nextTier.get(3));
                } else if (curBCoin > curTierThresholdKeep) { // // bcoin cua tier hien tai > thredhold giu hang cua tier hien tai
                    assertThat(curLevel).isEqualTo(prLevel); // tier giu hang
                } else if (curBCoin < curTierThresholdKeep) {  // bcoin cua tier hien tai < thredhold giu hang cua tier hien tai
                    if (!preLevel.equals("1")) {
                        assertThat(curLevel).isEqualTo(prLevel); // tier tut hang sau khi end cycle, hien tai thi van giu nguyen
                    } else assertThat(curLevel).isEqualTo(prLevel); //tier = 1 thi giu nguyen hang
                }
            }
        }
    }

    @Step
    public void navigatetoTierListPage() {
        tierPage.maximizeWindow();
        tierPage.navigateToUrl(hiveLink + "tier/list");
    }

    public boolean isExistedBtnEdit() {
        return tierPage.isExistedBtnEdit();
    }

    public void clearCache() {
        String acctoken = "";
        String api = "https://" + shop + "/admin/tiers/synthetic.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = tierPage.getAccessTokenShopBase();
        }
        System.out.println("api: " + api);
        JsonPath js = tierPage.getAPISbase(api, acctoken);
    }

    public void waitToAPIupdate(int time) {
        tierPage.waitToAPIupdate(time);
    }
}
