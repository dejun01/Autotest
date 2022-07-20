package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.common.pages.CommonPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.FulfillmentWithOrderDetailPage;
import common.CommonPageObject;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;

public class FulfillmentWithOrderDetailSteps {

    FulfillmentWithOrderDetailPage fulfillmentWithOrderDetailPage;
    CommonPage commonPage;


    @Step
    public void clickBTFulfilWith() {
        fulfillmentWithOrderDetailPage.clickBTFulfill("Fulfill with");
        fulfillmentWithOrderDetailPage.clickBTFulfill("PlusHub");
    }

    @Step
    public void clickBTFulfillOrder() {
        fulfillmentWithOrderDetailPage.clickBTFulfill("Fulfill order");
        fulfillmentWithOrderDetailPage.clickBTFulfill("Pay now");
    }

    @Step
    public void verifyInfoOrder(String tab,String product, String shippingMethod, String shippingFee, String estimatedShippingTime) {
        List<String> listTab = fulfillmentWithOrderDetailPage.getListTextTab();
        for (String subTab : listTab){
            String[] tabs = subTab.split("\\(");
          assertThat(tabs[0].trim()).isEqualTo(tab);
        }
        String[] products = product.split(":");
        fulfillmentWithOrderDetailPage.verifyOrderFulfillment(products[0],products[1], shippingMethod,"SHIPPING METHOD");
        fulfillmentWithOrderDetailPage.verifyOrderFulfillment(products[0],products[1], shippingFee,"SHIPPING FEE");
        fulfillmentWithOrderDetailPage.verifyOrderFulfillment(products[0],products[1], estimatedShippingTime,"ESTIMATED SHIPPING TIME");
    }

    @Step
    public void verifyTextFulfillWithShopBaseFulfillment() {
        fulfillmentWithOrderDetailPage.verifyTextFulfillWithShopBaseFulfillment("Fulfill with PlusHub");
    }

    @Step
    public void verifyInfoOrderAfterFulfill(String product, String status) {
        String[] subProduct = product.split(":");
        String[] subTab = fulfillmentWithOrderDetailPage.getTab(subProduct[0]).split("\\(");
        assertThat(subTab[0].trim()).isEqualTo(status);
        assertThat(fulfillmentWithOrderDetailPage.getProduct(status).trim()).isEqualTo(subProduct[0]);
        assertThat(fulfillmentWithOrderDetailPage.getVariant(status).trim()).isEqualTo(subProduct[1]);
    }
    @Step
    public void cancelFulfill(){
        fulfillmentWithOrderDetailPage.clickBTFulfill("Cancel fulfillment");
        fulfillmentWithOrderDetailPage.clickBTFulfill("Confirm");
    }


    public void verifyShipmentStatus() {
            fulfillmentWithOrderDetailPage.verifyShipmentStatusPresent();
            fulfillmentWithOrderDetailPage.verifyShipmentStatus();

    }
     public void reLoadPage() {
        commonPage.refreshPage();
     }
}
