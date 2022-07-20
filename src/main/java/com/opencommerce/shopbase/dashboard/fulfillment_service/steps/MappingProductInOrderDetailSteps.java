package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.MappingproductInOrderDetailPage;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingProductInOrderDetailSteps {

    MappingproductInOrderDetailPage mappingproductInOrderDetailPage;


    @Step
    public void searchProductodoo(String product) {
        mappingproductInOrderDetailPage.enterInputFieldWithLabel("Search product by name", product);
    }

    @Step
    public void editMapping() {
        mappingproductInOrderDetailPage.clickBtnAction();
        mappingproductInOrderDetailPage.clickChangeMapping("Change mapping");
    }

    @Step
    public void verifyError(String error) {
        assertThat(mappingproductInOrderDetailPage.getError()).isEqualTo(error);
    }

    @Step
    public void verifyBTSaveIsDisable() {
        mappingproductInOrderDetailPage.verifyBTSaveIsDisable();
    }

    @Step
    public void verifyNotErr() {
        mappingproductInOrderDetailPage.verifyNotErr();
    }

    @Step
    public void verifyDisplayBTSet() {
        mappingproductInOrderDetailPage.verifyDisplayBTSet();
    }

    @Step
    public void verifyRedirectPageFulfillWithShopBaseFulfillment(String page) {
        assertThat(mappingproductInOrderDetailPage.getPage()).isEqualTo(page);

    }

    @Step
    public ArrayList<String> getListProductOdooInInventory() {
        return mappingproductInOrderDetailPage.getListProductOdooInInventory();
    }

    @Step
    public ArrayList<String> getListProductOdooInMappingOderDetail() {
        return mappingproductInOrderDetailPage.getListProductOdooInMappingOderDetail();
    }

    @Step
    public void verifyProductListOdoo(ArrayList<String> listProductOdooInInventory, ArrayList<String> listProductOdooInMappingOrderDetail) {
        for (String productInventory : listProductOdooInInventory) {
            for (String productOdooMapping : listProductOdooInMappingOrderDetail) {
                assertThat(productInventory).isEqualTo(productOdooMapping);
            }
        }
    }
}


