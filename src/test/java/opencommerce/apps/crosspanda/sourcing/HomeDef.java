package opencommerce.apps.crosspanda.sourcing;

import com.opencommerce.shopbase.dashboard.dashboard.steps.AppStoreSteps;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.InventorySteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.MyOrdersSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.HomeSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomeDef {

    @Steps
    MyOrdersSteps myOrdersSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;
    @Steps
    InventorySteps inventorySteps;
    @Steps
    HomeSteps homeSteps;
    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    AppStoreSteps asStep;

    @Given("^user connect store with platform \"([^\"]*)\"$")
    public void user_connect_store_with_platform(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String platform = SessionData.getDataTbVal(dataTable, row, "Platform");

            String store = LoadObject.getProperty("shop");
            if (store.contains("net")) {
                store = store.replace("myshopbase.net", "");
            } else {
                store = store.replace(".onshopbase.com", "");
            }
            homeSteps.clickLinkToMoreStores();
            homeSteps.selectPlatform(platform);
            homeSteps.enterStoreURL(store);
            loginStep.waitABit(10000);
            String userName = LoadObject.getProperty("sb.name");
            String pwd = LoadObject.getProperty("sb.pwd");
            loginStep.enterEmail(userName);
            loginStep.enterPassword(pwd);
            loginStep.clickBtnSignIn();
            asStep.installApp();
            homeSteps.verifyConnectSusscess();
        }
    }

    @Given("^verify order after connect store \"([^\"]*)\"$")
    public void verify_order_after_connect_store(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String store = SessionData.getDataTbVal(dataTable, row, "Store");
            myOrdersSteps.selectShop(store);
            myOrdersSteps.verifyHasOrderInStore();
        }
    }

    @Given("^user delete store with platform \"([^\"]*)\"$")
    public void user_delete_store_with_platform(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String app = SessionData.getDataTbVal(dataTable, row, "App");
            asStep.uninstallApp(app);
            homeSteps.waitABit(5000);
        }
    }

    @Given("^verify delete order on app after delete store \"([^\"]*)\"$")
    public void verify_delete_order_on_app_after_delete_store(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "OrderNumber");
            if(!orderNumber.isEmpty()){
                orderNumber = (String) SessionData.getDataByKey(key);
            }
            myOrdersSteps.searchOrder(orderNumber);
            myOrdersSteps.verifyHasNotOrderInStore();
        }
    }

    @Given("^verify order \"([^\"]*)\" isnot sync to CrossPanda$")
    public void verify_order_isnot_sync_to_CrossPanda(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order key");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(orderKey);
            }
            myOrdersSteps.searchOrder(orderNumber);
            myOrdersSteps.verifyHasNotOrderInStore();
        }
    }

    @Given("^verify count awaiting order after delete store \"([^\"]*)\"$")
    public void verify_count_awaiting_order_after_delete_store(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product");
            String productVariant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String accesstoken = commonXPandaSteps.getAccessTokenXpanda();
            JsonPath inventoryInformation = inventorySteps.getInventoryInformation(productName, accesstoken);
            double expawaiting_order = inventorySteps.countNumberOfProductByStatus(inventoryInformation, productName, productVariant, "awaiting_order");
            assertThat(expawaiting_order).isEqualTo(0);
        }
    }


}
