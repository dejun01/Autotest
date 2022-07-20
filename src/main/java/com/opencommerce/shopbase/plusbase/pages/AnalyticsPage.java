package com.opencommerce.shopbase.plusbase.pages;
import com.opencommerce.shopbase.dashboard.settings.pages.SettingPages;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;


public class AnalyticsPage extends SBasePageObject  {


    SettingPages settingPages;
    public AnalyticsPage(WebDriver driver) {
        super(driver);
    }
    float itemDisplay;
    float AOP;
    float profitAfter;

    public void verifyDisplayProfit(String displayItem) {
        verifyTextPresent(displayItem,true);
    }
    public float getItemPrice(String item)
    {
        String xpath = "//h4[normalize-space()='"+item+"']//parent::div//following-sibling::div//h2";
        waitElementToBeVisible(xpath);
        return getPrice(xpath);
    }

    public float getSubItemPrice( String item, String label )
    {
        String xpathPriceOfProfit =
                "//h4[normalize-space()='"+item+"']//parent::div//following-sibling::div//div//span[normalize-space()='"+label+"']/following-sibling::div//span";
        waitElementToBeVisible(xpathPriceOfProfit);
        return getPrice(xpathPriceOfProfit);
    }

    public float getProfitOrder()
    {
        String xpathProfit = "//*[text()[normalize-space()='Profit']]/parent::div/following-sibling::span";
        waitElementToBeVisible(xpathProfit);
        return getPrice(xpathProfit);

    }
    public void verifyTotalProfit(float profitBefore, float profitOrder)
    {
        profitAfter = getItemPrice("Total profits");
        assertThat(profitAfter).isEqualTo(profitBefore + profitOrder);
    }

    public void verifyAOPOf(String label, float totalProfit)
    {
        refreshPage();
        profitAfter = getItemPrice("Total profits");
        AOP = (label == "") ? ((totalProfit == 0) ? getItemPrice("Average order profit") : getSubItemPrice("Average order profit","AOV via Boost Upsell"))
                : getSubItemPrice("Average order profit",label);
        itemDisplay = (label == "") ? ((totalProfit == 0) ? getItemPrice("Total orders") : getSubItemPrice("Total orders","Total orders via Boost Upsell"))
                : getSubItemPrice("Total orders",label);
        float exp = itemDisplay == 0 ? 0 : totalProfit/itemDisplay;
        assertThat(AOP).isEqualTo(exp);
    }
    public void verifyProfitOfOrderBoostUpsell(float profitBefore,float profitOrder, float subProfitBefore)
    {
        float onlineStore = getSubItemPrice("Total profits","Online store");
        float totalSalesViaBoostUpsellAfter = getSubItemPrice("Total profits","Total sales via Boost Upsell");
        verifyTotalProfit(profitBefore,profitOrder);
        assertThat(totalSalesViaBoostUpsellAfter).isEqualTo(subProfitBefore+profitOrder);
        verifyTotalProfit(onlineStore,totalSalesViaBoostUpsellAfter);
        assertThat(profitAfter).isEqualTo(onlineStore+totalSalesViaBoostUpsellAfter);
    }
}
