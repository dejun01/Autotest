package opencommerce.plus_base.catalog;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.plusbase.steps.CatalogDetailSteps;
import com.opencommerce.shopbase.plusbase.steps.CatalogListStep;

import static com.opencommerce.shopbase.SettingsVariables.*;

import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

public class CatalogDetailDef {
    @Steps
    CatalogDetailSteps catalogDetailSteps;
    @Steps
    CatalogListStep catalogListStep;
    @Steps
    CommonSteps commonSteps;
    @Steps
    OrderSteps orderSteps;

    @Given("Verify UI shipping of product")
    public void VerifyUIShippingOfProduct() {
        catalogDetailSteps.verifyUIOfShipping();
    }

    @Given("search product in {string} as {string}")
    public void searchProductInCatalogAs(String label, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String key = "Catalog".equals(label) ? "Search by product name" : "Search by product name, request link";
            catalogListStep.searchProduct(product, key);
            catalogDetailSteps.clickProduct(product);
        }
    }

    @And("Verify shipping info of product")
    public void verifyShippingInfoOfProduct() {
        catalogDetailSteps.verifyFee(countries, price, additionalCondition, rateName);
    }

    @And("Verify shipping of product in Private request")
    public void verifyShippingOfProductInPrivateRequest() {
        catalogDetailSteps.verifyFee(countries, price, additionalCondition, rateName);
    }

    @And("get information of shipping as {string}")
    public void getInformationOfShippingAS(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            countries = SessionData.getDataTbVal(dataTable, row, "Country");
            price = SessionData.getDataTbVal(dataTable, row, "Price");
            additionalCondition = SessionData.getDataTbVal(dataTable, row, "Additional condition");
            rateName = SessionData.getDataTbVal(dataTable, row, "Rate name");
        }
    }

    @Given("get shipping fee on catalog detail and calculate shipping fee")
    public void get_shipping_fee_on_catalog_detail_and_calculate_shipping_fee(List<List<String>> dataTable) {
        int quantity = 0;
        Float expectAdditionalItem = 0.00f;
        Float expectFirstItem = 0.00f;

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String isFirstItem = SessionData.getDataTbVal(dataTable, row, "Is First item");
            quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            float addtionalItem = 0.00f;
            float firstItem = 0.00f;
            if (!product.isEmpty()) {
                commonSteps.switchToTab("Catalog");
                catalogListStep.searchProduct(product, "Search by product name");
                catalogListStep.moveToProductDetail(product);
            }
            if (isFirstItem.contains("True")) {
                firstItem = catalogDetailSteps.getFirstItemOfVariant(variant);
                addtionalItem = catalogDetailSteps.getAdditionalItem(variant) * (quantity - 1);
            } else {
                addtionalItem = catalogDetailSteps.getAdditionalItem(variant) * quantity;
            }
            expectAdditionalItem = expectAdditionalItem + addtionalItem;
            expectFirstItem = expectFirstItem + firstItem;
        }
        expectShippingFee = orderSteps.roundTwoDecimalPlaces(expectFirstItem + expectAdditionalItem);
    }

    @Given("calculate shipping fee after mark up shipping fee")
    public void calculate_shipping_fee_after_mark_up_shipping_fee(List<List<String>> dataTable) {
        Float expectAdditionalItem = 0.00f;
        Float expectFirstItem = 0.00f;
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Float firstItem = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "First item"));
            Float additionalItem = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Additional item"));
            int quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            if (!firstItem.equals(0.00f)) {
                additionalItem = additionalItem * (quantity - 1);
            } else {
                additionalItem = additionalItem * quantity;
            }
            expectAdditionalItem = expectAdditionalItem + additionalItem;
            expectFirstItem = expectFirstItem + firstItem;
        }
        expectShippingFee = orderSteps.roundTwoDecimalPlaces(expectFirstItem + expectAdditionalItem);
    }

}

