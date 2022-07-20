package opencommerce.fulfillment_service.fulfillment;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.MappingProductSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillWithSbaseOrderSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.ProductVariable.*;

import java.util.List;

public class FulfillWithSbaseOrderDef {
    @Steps
    MappingProductSteps mappingProductSteps;

    @Steps
    FulfillWithSbaseOrderSteps fulfillWithSbaseOrderSteps;

    public String error = "";

    @And("Fulfill with PlusHub")
    public void fulfillWithShopBaseFulfillment() {
        fulfillWithSbaseOrderSteps.fulfillOrder();

    }

    @And("Search and verify display info tab")
    public void verifyDisplayInfoTab(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String order_Number = SessionData.getDataTbVal(dataTable, row, "order");
            String product = SessionData.getDataTbVal(dataTable, row, "product");
            String country = SessionData.getDataTbVal(dataTable, row, "country");
            String error = SessionData.getDataTbVal(dataTable, row, "error");
            String action = SessionData.getDataTbVal(dataTable, row, "action");
            if (!order_Number.isEmpty()) {
                fulfillWithSbaseOrderSteps.searchByOrder(orderNumber);
            }
            if (!error.isEmpty() && !action.isEmpty()) {
                fulfillWithSbaseOrderSteps.verifyInfoNeedTabToReview(orderNumber, productVariant, product, country, error);
                fulfillWithSbaseOrderSteps.verifyAndClickAction(action);
            }
        }
    }

    @And("Edit address order")
    public void verifyAndEditAddressOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String region = SessionData.getDataTbVal(dataTable, row, "State");
            String code = SessionData.getDataTbVal(dataTable, row, "ZIP/Postal Code");
            fulfillWithSbaseOrderSteps.editAddress(address, country, city, region, code);
        }
    }

    @And("Mapping product with info")
    public void mappingProductNew(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            nameProductOdoo = SessionData.getDataTbVal(dataTable, row, "Product Name odoo");
            variantValueOdoo = SessionData.getDataTbVal(dataTable, row, "Variant>quantity");
            nameProductSbase = SessionData.getDataTbVal(dataTable, row, "Product Name sbase");
            variantValueSbase = SessionData.getDataTbVal(dataTable, row, "VariantSB>quantitySB");
            fulfillWithSbaseOrderSteps.searchByProductName(nameProductOdoo);
            mappingProductSteps.clearData();
            if (!variantValueSbase.isEmpty()) {
                mappingProductSteps.mappingOrder(variantValueSbase, variantValueOdoo);
            } else {
                mappingProductSteps.mappingVariantOdoo(variantValueOdoo);
            }
            mappingProductSteps.clickSave();


        }
    }

    @And("click on order fulfill with")
    public void clickOnOrderFulfillWith() {
        fulfillWithSbaseOrderSteps.seclectOrder(orderNumber);
    }

    @And("Mapping product in order detail")
    public void mappingProductInOrderDetail() {
        fulfillWithSbaseOrderSteps.clickBTMapping();
    }

}
