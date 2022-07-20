package opencommerce.fulfillment_service.warehouse;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.MappingProductSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestListStep;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.MappingProductInOrderDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static com.opencommerce.shopbase.ProductVariable.*;
import static com.opencommerce.shopbase.ProductVariable.variantValueOdoo;

public class MappingProductInOrderDetailDef {
    @Steps
    RequestListStep requestListStep;
    @Steps
    MappingProductSteps mappingProductSteps;


    @Steps
    MappingProductInOrderDetailSteps mappingProductInOrderDetailSteps;

    ArrayList<String> listProductOdooInInventory = new ArrayList<>();
    ArrayList<String> listProductOdooInMappingOrderDetail = new ArrayList<>();


    @And("Search product mapping {string}")
    public void searchProductMapping(String productOdoo) {
        mappingProductInOrderDetailSteps.searchProductodoo(productOdoo);
    }

    @And("Edit mapping product")
    public void editMappingProduct(List<List<String>> dataTable) {
        mappingProductInOrderDetailSteps.editMapping();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            nameProductSbase = SessionData.getDataTbVal(dataTable, row, "Name product on sBase");
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "Attribute>Value");
            mappingProductSteps.clearData();
            if(!variantValueSbase.isEmpty()){
                mappingProductSteps.mappingOrder(variantValueSbase, variantValueOdoo);
            }else {
                mappingProductSteps.mappingVariantOdoo(variantValueOdoo);
            }

            mappingProductSteps.clickSave();

        }

    }

    @Then("Verify product mapping")
    public void verifyProductMapping(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String error = SessionData.getDataTbVal(dataTable, row, "Error");
            mappingProductInOrderDetailSteps.verifyError(error);
            mappingProductInOrderDetailSteps.verifyBTSaveIsDisable();
        }
    }

    @And("Verify can mapping product Odoo")
    public void verifyMappingSuccess() {
        mappingProductInOrderDetailSteps.verifyNotErr();
        mappingProductInOrderDetailSteps.verifyDisplayBTSet();
    }

    @And("Verify redirect page {string}")
    public void verifyRedirectPage(String page) {
        mappingProductInOrderDetailSteps.verifyRedirectPageFulfillWithShopBaseFulfillment(page);
    }

    @And("get list product odoo in quotation created")
    public void getListProductOdooInInventory() {
        listProductOdooInInventory = mappingProductInOrderDetailSteps.getListProductOdooInInventory();
    }

    @Then("verify display list product odoo")
    public void verifyDisplayListProductOdoo() {
        listProductOdooInMappingOrderDetail = mappingProductInOrderDetailSteps.getListProductOdooInMappingOderDetail();
        mappingProductInOrderDetailSteps.verifyProductListOdoo(listProductOdooInInventory, listProductOdooInMappingOrderDetail);


    }

    @And("click on {string} tab")
    public void clickOnTab(String quotationTab) {
        requestListStep.clickTab(quotationTab);
    }
}

