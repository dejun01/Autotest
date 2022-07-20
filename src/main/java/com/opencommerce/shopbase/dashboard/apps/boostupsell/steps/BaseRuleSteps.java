package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.BaseRulePage;
import net.thucydides.core.annotations.Step;

public class BaseRuleSteps {
    BaseRulePage baseRulePage;

    @Step
    public void setUpRuleBase(String blockName, String productMustMatch, String recommendRules) {
        baseRulePage.selectType(blockName, "Specific by rules");
        baseRulePage.selectProductMustMatch(blockName, productMustMatch);

        if (!recommendRules.isEmpty()) {
            String[] ruleList = recommendRules.split(",");
            for(int i=0; i<ruleList.length; i++){
                if(i != 0){
                    baseRulePage.clickAddMore(blockName);
                }
                inputRule(blockName, ruleList[i], i+1);
            }
        }
    }

    public void inputRule(String blockName, String discounts, int index) {
        String[] data = discounts.split(">");
        String fieldCompare = data[0].trim();
        String conditionCompare = data[1].trim();
        String valueCompare = data[2].trim();

        baseRulePage.inputFieldCompare(blockName, fieldCompare, index);
        baseRulePage.inputConditionCompare(blockName, conditionCompare, index);
        baseRulePage.inputValueCompare(blockName, valueCompare, index);
    }

    @Step
    public void verifyShowAccessory(boolean isShow){
        baseRulePage.verifyShowAccessory(isShow);
    }

    @Step
    public void verifyOfferAcessoryMessage(String message){
        baseRulePage.verifyOfferAcessoryMessage(message);
    }

    @Step
    public void verifyItemInAccessory(String products){
        String[] productList = products.split(",");
        for(String i:productList){
            String product = i.trim();
            baseRulePage.verifyShowItem(product, true);
        }
    }
}
