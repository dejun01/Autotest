package opencommerce.plus_base.catalog;

import com.opencommerce.shopbase.plusbase.steps.ImportProductCatalogStep;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.eo.Se;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ImportProductCatalogDef {
    @Steps
    ImportProductCatalogStep importStep;

    @And("verify product import with")
    public void verifyProductImportWith(List<List<String>> data) {
        int index = 0;
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            float cost = Float.parseFloat(SessionData.getDataTbVal(data, row, "Cost"));
            float sellingPrice = Float.parseFloat(SessionData.getDataTbVal(data, row, "Selling price"));
            float compareAtPrice = Float.parseFloat(SessionData.getDataTbVal(data, row, "Compare at price"));
            float profitMargin = Float.parseFloat(SessionData.getDataTbVal(data, row, "Profit margin"));
            String priceVariant = SessionData.getDataTbVal(data, row, "Price variant");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            importStep.verifyProductName(product);
            importStep.verifyProductPrice("COST", cost, true, product);
            importStep.verifyProductPrice("SELLING PRICE", sellingPrice, false, product);
            importStep.verifyProductPrice("COMPARE AT PRICE", compareAtPrice, false, product);
            importStep.verifyProfitMargin("PROFIT MARGIN", profitMargin, product);
            importStep.choiceProductImport(product);
            if (!priceVariant.isEmpty()) {
                importStep.EnterPriceVariantImport(priceVariant.split(":"));
            }
            index++;
        }
        importStep.clickButton(" Add " + index + " selected products to store ");
    }

    @Given("Remove all product in Import list page")
    public void removeAllProductInImportListPage() {
        importStep.actionAllProductInImportListPage("Remove selected");
    }

    @Given("click button import to store")
    public void clickButtonImportToStore() {
        importStep.clickButton("Import to your store");
    }

    @Given("verify data pricing product in Import list")
    public void verify_data_pricing_product_in_Import_list(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String shipTo = SessionData.getDataTbVal(data, row, "Ship to");
            String shippingMethod = SessionData.getDataTbVal(data, row, "Shipping method");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String cost = SessionData.getDataTbVal(data, row, "Cost");
            String firstItem = SessionData.getDataTbVal(data, row, "First item");
            String addtionalItem = SessionData.getDataTbVal(data, row, "Additional item");
            String profit = SessionData.getDataTbVal(data, row, "Profit margin");
            importStep.selectTabPricing();
            if (!product.isEmpty()) {
                importStep.verifyProductName(product);
            }
            if (!shipTo.isEmpty()) {
                importStep.verifyShipToInImportList(shipTo);
            }
            if (!shippingMethod.isEmpty()) {
                importStep.verifyShippingMethodInImportList(shippingMethod);
            }
            if (!variant.isEmpty()) {
                importStep.verifyCostVariantInImportList(variant, cost);
                importStep.verifyFirstItemVariantInImportList(variant, firstItem);
                importStep.verifyAdditionalItemVariantInImportList(variant, addtionalItem);
                importStep.verifyProfitVariantInImportList(variant, profit);
            }

        }
    }

    @Given("Add to store product Ali")
    public void add_to_store_product_Ali(List<List<String>> data) {
        for(int row : SessionData.getDataTbRowsNoHeader(data).keySet()){
            String product = SessionData.getDataTbVal(data, row, "Product");
            boolean enable = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Enable for buyers"));
            if(enable){
                importStep.clickEnableForBuyers(product);
            }
            importStep.selectProductToImport(product);
            importStep.importProductToStore(product);
        }
    }



}
