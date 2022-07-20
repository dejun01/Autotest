package com.opencommerce.shopbase.dashboard.orders.steps;

import com.opencommerce.shopbase.dashboard.orders.pages.MoreFilterPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoreFilterSteps {
    MoreFilterPage moreFilterPage;

    @Step
    public void clickBtnFilter(String filterType) {
        moreFilterPage.clickBtnFilter(filterType);
    }

    @Step
    public void clickBTDone() {
        moreFilterPage.clickBTDone();
    }
    @Step
    public void clickApplyFilter(){
        moreFilterPage.clickApplyFilter();
    }
    @Step
    public void enterFilterByCondition(String condition, String value) {
        moreFilterPage.enterFilterByConditon(condition, value);
    }

    @Step
    public void clickBTProductMapping(String productMapping) {
        moreFilterPage.clickCondition(productMapping);
    }

    @Step
    public void clickSaveFilter(String filterTemplates) {
        moreFilterPage.clickBTSaveFilter(filterTemplates);
    }

    @Step
    public void verifyErr(String text) {
        assertThat(moreFilterPage.getErr()).isEqualTo(text);
    }

    @Step
    public void verifyTextFilter(String valueTag, String valueFulfillmentService, String valueTrackingNumber, String valueProductMapping) {
        List<String> listTitleMoreFilter = moreFilterPage.getListTitleMoreFilter();
        Assert.assertTrue(listTitleMoreFilter.contains(valueTag));
        Assert.assertTrue(listTitleMoreFilter.contains(valueFulfillmentService));
        Assert.assertTrue(listTitleMoreFilter.contains(valueTrackingNumber));
        Assert.assertTrue(listTitleMoreFilter.contains(valueTrackingNumber));

    }

    @Step
    public void clickCondition(String condition, String value) {
        moreFilterPage.clickCondition(condition);
        moreFilterPage.clickConditionName(value);
    }

    @Step
    public void clickBTCondition(String condition) {
        moreFilterPage.clickCondition(condition);
    }

    @Step
    public String getTextByShopBaseFulfillment() {
        String text = moreFilterPage.getTextByShopBaseFulfillment().substring(moreFilterPage.getTextByShopBaseFulfillment().indexOf(" "));
        String[] getText = text.split("\\(");
        return getText[0].trim();

    }

    @Step
    public void verifyInforSearch(String order, String fulfillmentStatus) {
        assertThat(moreFilterPage.getTextSearch().replace("x", "").trim()).isEqualTo(fulfillmentStatus);
        assertThat(moreFilterPage.getOrderNumber()).contains(order);
    }

    @Step
    public void chooseFilterTemplates(String filterTemplates) {
        moreFilterPage.chooseFilterTemplates(filterTemplates);

    }
    @Step
    public void verifyFilterAfterSave(List<String> listValue, List<String> listCondition) {
        for (String condition : listCondition) {
            moreFilterPage.clickCondition(condition);
            for (String value : listValue) {
                moreFilterPage.verifyFilter(value);

            }
        }
    }
    @Step
    public void clickOnCondition(String condition){
        moreFilterPage.clickOnCondition(condition);
    }

    @Step
    public void enterValueFilter(String condition, String value){
        moreFilterPage.enterValueFilter(condition, value);
    }

    @Step
    public void chooseValueFilter(String condition, String value){
        moreFilterPage.chooseValueFilter(condition, value);
    }
    @Step
    public void chooseSearchType(String searchType){
        moreFilterPage.chooseSearchType(searchType);
    }


}
