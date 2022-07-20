package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.AnalyticsPage;
import net.thucydides.core.annotations.Step;

public class AnalyticsSteps {
    AnalyticsPage analyticsPage;
    @Step
    public void verifyItemDisplayProfit(String displayItem) {
        analyticsPage.verifyDisplayProfit(displayItem);
    }
    @Step
    public float getItemPrice(String item){
       return analyticsPage.getItemPrice(item);
    }
    @Step
    public float getSubItemPrice(String item, String label)
    {
        return analyticsPage.getSubItemPrice(item, label);
    }
    @Step
    public float getProfitOrder(){
        return analyticsPage.getProfitOrder();
    }
    @Step
    public void verifyTotalProfit(float totalProfit, float profitOrder){
        analyticsPage.verifyTotalProfit(totalProfit, profitOrder);
    }

    @Step
    public void verifyAOPOf(String label, float totalProfit)
    {
        analyticsPage.verifyAOPOf(label, totalProfit);
    }

    @Step
    public void verifyTotalProfitWithOrderBoostUpsell(float profitBefore, float profitOrder, float subProfitBefore)
    {
        analyticsPage.verifyProfitOfOrderBoostUpsell(profitBefore, profitOrder, subProfitBefore);
    }

}
