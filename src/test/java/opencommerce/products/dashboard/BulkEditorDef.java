package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.BulkEditorSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import java.util.List;

public class BulkEditorDef {
    @Steps
    BulkEditorSteps bulkEditorSteps;
    @Steps
    ProductDetailSteps productDetailSteps;

    @Then("bulk editor information product")
    public void bulkEditorInformationProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String titleUpdate = SessionData.getDataTbVal(dataTable, row, "Title Update");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String productTypeUpdate = SessionData.getDataTbVal(dataTable, row, "Product type update");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String vendorUpdate = SessionData.getDataTbVal(dataTable, row, "Vendor update");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String tagsUpdate = SessionData.getDataTbVal(dataTable, row, "Tags update");
            String option = SessionData.getDataTbVal(dataTable, row, "Option");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String priceUpdate = SessionData.getDataTbVal(dataTable, row, "Price update");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String compareAtPriceUpdate = SessionData.getDataTbVal(dataTable, row, "Compare at price update");
            String costPerItem = SessionData.getDataTbVal(dataTable, row, "Cost per item");
            String costPerItemUpdate = SessionData.getDataTbVal(dataTable, row, "Cost per item update");
            String sku = SessionData.getDataTbVal(dataTable, row, "Sku");
            String skuUpdate = SessionData.getDataTbVal(dataTable, row, "Sku update");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            String weightUpdate = SessionData.getDataTbVal(dataTable, row, "Weight update");

            if (!titleUpdate.isEmpty()) {
                bulkEditorSteps.editTitle(title, titleUpdate);
            }
            if (!productTypeUpdate.isEmpty()) {
                bulkEditorSteps.editProductType(productType, productTypeUpdate);
            }
            if (!tags.isEmpty()) {
                bulkEditorSteps.editTags(tags, tagsUpdate);
            }
            if (!vendorUpdate.isEmpty()) {
                bulkEditorSteps.editVendor(vendor, vendorUpdate);
            }
            if (!priceUpdate.isEmpty()) {
                bulkEditorSteps.editPrice(option, price, priceUpdate);
            }
            if (!compareAtPriceUpdate.isEmpty()) {
                bulkEditorSteps.editCompareAtPrice(compareAtPrice, compareAtPriceUpdate);
            }
            if (!costPerItemUpdate.isEmpty()) {
                bulkEditorSteps.editCostPerItem(costPerItem, costPerItemUpdate);
            }
            if(!skuUpdate.isEmpty()) {
                bulkEditorSteps.editSku(sku, skuUpdate);
            }
            if(!weightUpdate.isEmpty()) {
                bulkEditorSteps.editWeight(weight, weightUpdate);
            }
            bulkEditorSteps.clickCompareAtPrice();
            bulkEditorSteps.clickSaveChanges();
            if (!titleUpdate.isEmpty()) {
                bulkEditorSteps.verifyTitle(titleUpdate);
            }
            if (!productTypeUpdate.isEmpty()) {
                bulkEditorSteps.verifyProductType(productTypeUpdate);
            }
            if (!vendorUpdate.isEmpty()) {
                bulkEditorSteps.verifyVendor(vendorUpdate);
            }
        }
        productDetailSteps.clickBreadcscrumbProducts();
    }
}
