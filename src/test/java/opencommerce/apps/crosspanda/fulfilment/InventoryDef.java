package opencommerce.apps.crosspanda.fulfilment;

import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.InventorySteps;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;


public class InventoryDef {
    @Steps
    InventorySteps inventorySteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;

    @Given("^count products' inventory in CrossPanda as \"([^\"]*)\"$")
    public void count_products_inventory_in_CrossPanda_as(String dataKey, List<List<String>> dataTable) {
        
        String accesstoken = commonXPandaSteps.getAccessTokenXpanda();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Purchased product");
            String productVariant = SessionData.getDataTbVal(dataTable, row, "Product variant");
            String inventoryKey = SessionData.getDataTbVal(dataTable, row, "Inventory");
            double purchasedpurchased = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Purchased"));
            double sold = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Sold"));
            double incoming = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Incoming"));
//            double availableStock = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Available stock"));
            double awaitingOrder = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Awaiting order"));
//            double awaitingStock = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Awaiting stock"));

            JsonPath inventoryInformation = inventorySteps.getInventoryInformation(productName, accesstoken);

            List<Double> listNumberProductByStatus = new ArrayList<>();

            double exppurchased = inventorySteps.countNumberOfProductByStatus(inventoryInformation, productName, productVariant, "purchased") + purchasedpurchased;
            double expincoming = inventorySteps.countNumberOfProductByStatus(inventoryInformation, productName, productVariant, "incoming") + incoming;
            double expsold = inventorySteps.countNumberOfProductByStatus(inventoryInformation, productName, productVariant, "sold") + sold;
            double expawaiting_order = inventorySteps.countNumberOfProductByStatus(inventoryInformation, productName, productVariant, "awaiting_order") + awaitingOrder;

            listNumberProductByStatus.add(exppurchased);
            listNumberProductByStatus.add(expincoming);
            listNumberProductByStatus.add(expsold);
            listNumberProductByStatus.add(expawaiting_order);
            commonXPandaSteps.logInfor("(purchased,incoming,sold,awaiting_order): " + listNumberProductByStatus);
            System.out.println(productVariant+ " (purchased,incoming,sold,awaiting_order): " + listNumberProductByStatus);
            SessionData.saveDataByKey(inventoryKey, listNumberProductByStatus);
        }
    }

    @And("^compare list as \"([^\"]*)\"$")
    public void compareListAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String actualResultKey = SessionData.getDataTbVal(dataTable, row, "Actual result");
            String expectedResultKey = SessionData.getDataTbVal(dataTable, row, "Expected result");
            List<Double> actualResult = (List<Double>) SessionData.getDataByKey(actualResultKey);
            List<Double> expectedResult = (List<Double>) SessionData.getDataByKey(expectedResultKey);
            inventorySteps.compare2List(actualResult, expectedResult);
        }
    }

}
