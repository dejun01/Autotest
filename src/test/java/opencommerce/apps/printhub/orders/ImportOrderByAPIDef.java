package opencommerce.apps.printhub.orders;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.ImportOrderByAPISteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.PurchaseOrderDetailSteps;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ImportOrderByAPIDef {
    String shop = LoadObject.getProperty("shop");
    String orderId = LoadObject.getProperty("order_id");

    @Steps
    PurchaseOrderDetailSteps purchaseOrderDetailSteps;
    @Steps
    ImportOrderByAPISteps importOrderByAPISteps;

    int order_id = 0;
    String order_name = "";
    String quantity = "";
    String address1 = "";
    String products_name = "";
    String variant_title = "";
    String lineitems_sku = "";
    String city = "";
    String province = "";
    String country = "";
    String zip = "";
    String color = "";
    String size = "";

    @When("Create order phub by API")
    public void createOrderPhubByAPI(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "name");
            String shipping_name = SessionData.getDataTbVal(dataTable, row, "shipping_name");
            address1 = SessionData.getDataTbVal(dataTable, row, "shipping_address_1");
            String shipping_address_2 = SessionData.getDataTbVal(dataTable, row, "shipping_address_2");
            city = SessionData.getDataTbVal(dataTable, row, "shipping_city");
            zip = SessionData.getDataTbVal(dataTable, row, "shipping_zip");
            province = SessionData.getDataTbVal(dataTable, row, "shipping_province");
            country = SessionData.getDataTbVal(dataTable, row, "shipping_country");
            String shipping_phone = SessionData.getDataTbVal(dataTable, row, "shipping_phone");
            String line_items = SessionData.getDataTbVal(dataTable, row, "line_items");
            String line_name = SessionData.getDataTbVal(dataTable, row, "line_name");
            String reference_id = SessionData.getDataTbVal(dataTable, row, "reference_id");
            quantity = SessionData.getDataTbVal(dataTable, row, "lineitem_quantity");
            lineitems_sku = SessionData.getDataTbVal(dataTable, row, "lineitem_sku");
            products_name = SessionData.getDataTbVal(dataTable, row, "product_name");
            variant_title = SessionData.getDataTbVal(dataTable, row, "product_id");
            color = SessionData.getDataTbVal(dataTable, row, "color");
            size = SessionData.getDataTbVal(dataTable, row, "size");
            String artwork_front_url = SessionData.getDataTbVal(dataTable, row, "artwork_front_url");
            String artwork_back_url = SessionData.getDataTbVal(dataTable, row, "artwork_back_url");
            String mockup_front_url = SessionData.getDataTbVal(dataTable, row, "mockup_front_url");
            String mockup_back_url = SessionData.getDataTbVal(dataTable, row, "mockup_back_url");
            String accessToken = importOrderByAPISteps.getAccessToken();
            String url = "https://" + shop + "/admin/phub-order/import.json";
            String urlOrderList = "https://" + shop + "/admin/phub-order/import-orders.json";
            importOrderByAPISteps.createOrderPhubByAPI(url, accessToken, name, shipping_name, address1, shipping_address_2, city, zip, province, country, shipping_phone, line_items, line_name, reference_id, Integer.parseInt(quantity), lineitems_sku, products_name, variant_title, color, size, artwork_front_url, artwork_back_url, mockup_front_url, mockup_back_url);
            order_id = importOrderByAPISteps.getOrderId(urlOrderList, accessToken);
            String urlGet = "https://" + shop + "/admin/phub-order/import/" + order_id + ".json";
            order_name = importOrderByAPISteps.getOrderName(urlGet, accessToken);
        }
    }

    @Then("Verify information order")
    public void verifyInformationOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "tab");
            String date = SessionData.getDataTbVal(dataTable, row, "date");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            purchaseOrderDetailSteps.accToTab(tab);
            importOrderByAPISteps.searchOrder(order_name);
            if (!date.isEmpty()) {
                date = DateTimeUtil.getDateFormat();
            }
            importOrderByAPISteps.verifyInforOrderInOrderList(order_name, date, status);
            importOrderByAPISteps.clickOrderName(order_name);
            importOrderByAPISteps.verifyOrderDetail(products_name, variant_title, lineitems_sku, address1, city, province, country, zip, color, size);

        }
    }

    @When("Delete order phub by API")
    public void deleteOrderPhubByAPI() {
        String accessToken = importOrderByAPISteps.getAccessToken();
        String url = "https://" + shop + "/admin/phub-order/import-orders.json?ids="+ order_id;
        importOrderByAPISteps.deleteOrder(url, accessToken);

    }

    @Then("Verify not display order {string}")
    public void verifyNotDisplayOrder(String noti, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "tab");
            purchaseOrderDetailSteps.accToTab(tab);
            importOrderByAPISteps.searchOrder(order_name);
            importOrderByAPISteps.verifyNotData(noti);
        }
    }

    @And("Payment order in tab {string}")
    public void paymentOrderInTab(String tab) {
        purchaseOrderDetailSteps.accToTab(tab);
        importOrderByAPISteps.searchOrder(order_name);
        importOrderByAPISteps.paymentOrder(order_name);

    }

    @And("select app {string} on Apps")
    public void selectAppOnApps(String app) {
        importOrderByAPISteps.selectApp(app);
    }
}
