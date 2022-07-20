package opencommerce.fulfillment_service.warehouse;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.InventoryListStep;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.MappingProductSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.ProductVariable.*;

public class MappingProductDef {
    @Steps
    MappingProductSteps mappingProductSteps;

    int listProductBefo;

    String store = LoadObject.getProperty("shop");

    @And("Choose product odoo mapping has info")
    public void chooseProductOdooMappingHasInfo(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            nameProductOdoo = SessionData.getDataTbVal(dataTable, row, "Name Product oDoo");
            variantValueOdoo = SessionData.getDataTbVal(dataTable, row, "Attribute>Value");
            mappingProductSteps.searchProductImport(nameProductOdoo);
            mappingProductSteps.clickBTAction(nameProductOdoo);

        }
    }

    @And("Mapping product in order have info")
    public void mappingProductInOrderHaveInfo(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            nameProductSbase = SessionData.getDataTbVal(dataTable, row, "Name product on sBase");
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "Attribute>Value");
            mappingProductSteps.enterNameOrder(nameProductSbase);
            mappingProductSteps.clickBTContinue();
            mappingProductSteps.verifyNameProduct(nameProductSbase, nameProductOdoo);
            mappingProductSteps.clearData();
            if(!variantValueSbase.isEmpty()){
            mappingProductSteps.mappingOrder(variantValueSbase, variantValueOdoo);
            }else {
                mappingProductSteps.mappingVariantOdoo(variantValueOdoo);
            }

            mappingProductSteps.clickSave();

        }

    }

    @Then("Display notification {string}")
    public void displayNotification(String mess) {
        mappingProductSteps.displayMess(mess);
        mappingProductSteps.clickBTMappingProductRedirect();

    }

    @And("Verify data product mapping success")
    public void verifyDataProductMappingSuccess(List<List<String>> dataTable) {
        mappingProductSteps.clickEdit(nameProductSbase);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "data product Sbase");
            variantValueOdoo = SessionData.getDataTbVal(dataTable, row, "data product oDoo");
            mappingProductSteps.verifyInforMappingProd(variantValueSbase, variantValueOdoo);

        }

    }

    @And("Select store mapping")
    public void selectStoreMapping() {
        mappingProductSteps.chooseStore(store);

    }

    @And("Mapping product")
    public void mappingProduct() {
        mappingProductSteps.clickBTMapping();
    }

    @Then("Display text error {string}")
    public void displayTextError(String error) {
        mappingProductSteps.verifyTextErr(error);
    }

    @And("Edit product mapping")
    public void editProductMapping(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            nameProductSbase = SessionData.getDataTbVal(dataTable, row, "Name product on sBase");
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "Attribute>Value");
            mappingProductSteps.clickEdit(nameProductSbase);
            mappingProductSteps.verifyNameProduct(nameProductSbase, nameProductOdoo);
            mappingProductSteps.clearData();
            mappingProductSteps.mappingOrder(variantValueSbase, variantValueOdoo);
            mappingProductSteps.clickSave();
        }

    }

    @And("Removed product mapping")
    public void removedProductMapping() {
        mappingProductSteps.clickRemoveProduct(nameProductSbase);

    }

    @Then("Verify not display product removed")
    public void verifyNotDisplayProductRemoved() {
        mappingProductSteps.verifyAfterRemove(listProductBefo);
    }

    @And("Mapping products in order")
    public void mappingProductsInOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameProductOnSBase = SessionData.getDataTbVal(dataTable, row, "Name product on sBase");
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "Attribute>Value");
            mappingProductSteps.enterNameOrder(nameProductOnSBase);
        }
    }


    @And("get all product store before remove")
    public void getAllProductStoreBeforeRemove() {
        listProductBefo = mappingProductSteps.verifyBeforeRemove();
    }
}
