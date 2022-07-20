package opencommerce.odoo;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.*;
import com.opencommerce.odoo.services.*;
import com.opencommerce.odoo.steps.OdooSteps;
import com.opencommerce.shopbase.OrderVariable;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.apps.crosspanda.fulfilment.MyOrdersDef;
import org.apache.xmlrpc.XmlRpcException;

import static com.opencommerce.shopbase.ProductVariable.*;
import static junit.framework.TestCase.fail;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.format;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class OdooDef {
    String mail = LoadObject.getProperty("user.name");
    @Steps
    OdooSteps odooSteps;


    @Then("^update order line item of quotation \"([^\"]*)\"$")
    public void update_quotation(String dataKey, List<List<String>> tableQuotation) throws MalformedURLException, XmlRpcException {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotation, "KEY", dataKey).keySet()) {
            String quotationKey = SessionData.getDataTbVal(tableQuotation, row, "KEY");
            String quotationId = SessionData.getDataTbVal(tableQuotation, row, "Quotation");
            String variant = SessionData.getDataTbVal(tableQuotation, row, "Variant");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "Quantity"));
            boolean basedOnTotal = Boolean.parseBoolean(SessionData.getDataTbVal(tableQuotation, row, "Based on total"));

            int newQuantity = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "New quantity"));
            int newPrice = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "New price"));
            String validity = SessionData.getDataTbVal(tableQuotation, row, "Validity");
            Client client = odooSteps.getClient();
            if (quotationId.isEmpty()) {
                quotationId = (String) SessionData.getDataByKey(quotationKey);
            }
            String email = LoadObject.getProperty("xp.email");

            SaleOrder order = odooSteps.getSaleOrder(quotationId, email, client);

            odooSteps.updateOrderLineItemByVariantNameAndQuantity(order, variant, quantity, newQuantity, newPrice, client);

            odooSteps.updateValidityDate(order, validity, client);
            odooSteps.updateQuoteBaseOn(order, basedOnTotal, client);
        }

    }

    @And("^add order line item of quotation \"([^\"]*)\"$")
    public void addOrderLineItemOfQuotation(String dataKey, List<List<String>> tableQuotation) throws MalformedURLException, XmlRpcException {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotation, "KEY", dataKey).keySet()) {
            String quotationKey = SessionData.getDataTbVal(tableQuotation, row, "KEY");

            String quotationId = SessionData.getDataTbVal(tableQuotation, row, "Quotation");
            String productName = SessionData.getDataTbVal(tableQuotation, row, "Product name");
            String variant = SessionData.getDataTbVal(tableQuotation, row, "Variant");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "Quantity"));
            int price = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "Price"));

            if (quotationId.isEmpty()) {
                quotationId = (String) SessionData.getDataByKey(quotationKey);
            }
            String email = LoadObject.getProperty("xp.email");
            Client client = odooSteps.getClient();
            SaleOrder order = odooSteps.getSaleOrder(quotationId, email, client);

            odooSteps.addOrderLineItemByVariantNameAndQuantity(order, productName, variant, quantity, price, client);

        }
    }

    @And("^add order line item of purchase \"([^\"]*)\"$")
    public void addOrderLineItemOfPurchase(String dataKey, List<List<String>> tableQuotation) throws MalformedURLException, XmlRpcException {
        String email = LoadObject.getProperty("xp.email");
        Client client = odooSteps.getClient();
        int partnerID = 1356;
        int purchaseID = Integer.parseInt(odooSteps.addPurchaseOrder(partnerID, client));
        System.out.println("ID---" + purchaseID);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotation, "KEY", dataKey).keySet()) {

            String productName = SessionData.getDataTbVal(tableQuotation, row, "Product name");
            String variant = SessionData.getDataTbVal(tableQuotation, row, "Variant");
            String purchaseOrder = SessionData.getDataTbVal(tableQuotation, row, "Purchase order id");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "Quantity"));
            int price = Integer.parseInt(SessionData.getDataTbVal(tableQuotation, row, "Price"));

            odooSteps.addPurchaseOrderLineItemByVariantNameAndQuantity(purchaseID, productName, variant, quantity, price, client);
            SessionData.saveDataByKey(purchaseOrder, purchaseID + "");

        }
    }

    @And("^confirm purchase order \"([^\"]*)\"$")
    public void confirmPurchaseOrder(String dataKey, List<List<String>> tableQuotation) throws Throwable {
        Client client = odooSteps.getClient();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotation, "KEY", dataKey).keySet()) {

            String purchaseOrder = SessionData.getDataTbVal(tableQuotation, row, "Purchase order");
            String purchaseOrderId = SessionData.getDataTbVal(tableQuotation, row, "Purchase order id");
            if (purchaseOrder.isEmpty()) {
                purchaseOrder = (String) SessionData.getDataByKey(purchaseOrderId);
            }
            odooSteps.confirmPurchaseOrder(Integer.parseInt(purchaseOrder), client);
        }
    }

    @And("^assign product to owner as \"([^\"]*)\"$")
    public void assignProductToOwnerAs(String dataKey, List<List<String>> tableQuotation) throws Throwable {

        Client client = odooSteps.getClient();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotation, "KEY", dataKey).keySet()) {

            String ownerId = SessionData.getDataTbVal(tableQuotation, row, "Owner id");
            String owner = SessionData.getDataTbVal(tableQuotation, row, "Owner");
            String pickingIDkey = SessionData.getDataTbVal(tableQuotation, row, "Picking id");

            String purchaseOrder = SessionData.getDataTbVal(tableQuotation, row, "Purchase order");
            String purchaseOrderId = SessionData.getDataTbVal(tableQuotation, row, "Purchase order id");
            if (purchaseOrder.isEmpty()) {
                purchaseOrder = (String) SessionData.getDataByKey(purchaseOrderId);
            }
            if (owner.isEmpty())
                owner = (String) SessionData.getDataByKey(ownerId);


            PurchaseOrder po = PurchaseOrderService.findOrderByID(Integer.parseInt(purchaseOrder), client).get(0);
            int pickingId = po.getPicking_ids()[0];
            System.out.println(pickingId);

            //odooSteps.assignOwner(Integer.parseInt(purchaseOrder), pickingId, owner, client);
            SessionData.saveDataByKey(pickingIDkey, pickingId);
        }
    }

    @Then("^verify DO is created on Odoo as \"([^\"]*)\"$")
    public void verify_DO_is_created_on_Odoo_as(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String xpandaSKU = SessionData.getDataTbVal(dataTable, row, "Xpanda SKU");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String owner = SessionData.getDataTbVal(dataTable, row, "Owner");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(key);
            }
            if (!key.equals(tmp)) {
//                myOrdersSteps.searchOrder(orderNumber);
            }
        }
    }

    @Given("^cancel SO on Odoo \"([^\"]*)\"$")
    public void cancel_SO_on_Odoo(String dataKey, List<List<String>> tableQuotation) throws Throwable {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotation, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(tableQuotation, row, "KEY");
            String quotation = SessionData.getDataTbVal(tableQuotation, row, "Quotation");
            String email = LoadObject.getProperty("xp.email");
            if (quotation.isEmpty()) {
                quotation = (String) SessionData.getDataByKey(key);
            }
            odooSteps.cancelQuotation(quotation, email);
        }
    }


    @Given("^delete SO on Odoo$")
    public void delete_SO_on_Odoo(List<List<String>> tableQuotation) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(tableQuotation).keySet()) {
            String quotation = SessionData.getDataTbVal(tableQuotation, row, "Quotation");
            if (quotation.contains("@")) {
                quotation = OrderVariable.quotationID;
            }
            String email = LoadObject.getProperty("xp.email");
            odooSteps.deleteQuotation(quotation, email);
        }
    }

    @Given("delete product on Odoo")
    public void delete_product_on_Odoo(List<List<String>> tableQuotation) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(tableQuotation).keySet()) {
            String product = SessionData.getDataTbVal(tableQuotation, row, "Product");
            Client client = odooSteps.getClient();
            int id = ProductService.getProductIDByName(product, client);
            odooSteps.deleteProductOdoo(id, client);
        }
    }

    private String getOrderName(String xOrderName) {
        if (xOrderName.matches("@OrderName@")) {
            return OrderVariable.orderNumber;
        } else if (xOrderName.matches("@OrderProductMappedImported@")) {
            return MyOrdersDef.mappedProductOrder;
        } else if (xOrderName.matches("@OrderProductMappedImportedAS@")) {
            return MyOrdersDef.mappedProductOrderAS;
        } else if (xOrderName.matches("@OrderProductMappedImportedF@")) {
            return MyOrdersDef.mappedProductOrderF;
        } else if (xOrderName.matches("@OrderProductUnmapImported@")) {
            return MyOrdersDef.unmapProductOrder;
        } else {
            return xOrderName;
        }
    }

    @Then("^Verify status of Order on Odoo")
    public void verifyStatusOfOrderOnOdooIs(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        odooSteps.waitABit(20000);
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            if (!key.isEmpty()) {
                orderName = (String) SessionData.getDataByKey(key);
            } else {
                orderName = getOrderName(xOrderName);
            }

            switch (status) {
                case "Quotation":
//                    try {
                    odooSteps.verifyStatusOfOrderOnOdooIsQuotation(orderName, customer);
//                    } catch (Throwable t) {
//                        odooSteps.waitABit(5000);
//                        odooSteps.verifyStatusOfOrderOnOdooIsQuotation(orderName, customer);
//                    }
                    break;
                case "Sales Order":
//                    try {
                    odooSteps.verifyStatusOfOrderOnOdooIsSalesOrder(orderName, customer);
//                    } catch (Throwable t) {
//                        odooSteps.waitABit(5000);
//                        odooSteps.verifyStatusOfOrderOnOdooIsSalesOrder(orderName, customer);
//                    }
                    break;
                case "Not exists":
//                    try {
                    odooSteps.verifyStatusOfOrderOnOdooIsNotExists(orderName, customer);
//                    } catch (Throwable t) {
//                        odooSteps.waitABit(5000);
//                        odooSteps.verifyStatusOfOrderOnOdooIsNotExists(orderName, customer);
//                    }
                    break;
                default:
                    fail();
            }
        }
    }

    @Then("^Verify info of Order on Odoo$")
    public void verifyInfoOfOrderOnOdoo(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        odooSteps.waitABit(20000);
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String orderedQty = SessionData.getDataTbVal(dataTable, row, "Ordered Qty");

            orderName = getOrderName(xOrderName);

//            try {
            odooSteps.verifyInfoOfOrderOnOdoo(orderName, productName, description, orderedQty);
//            } catch (Throwable t) {
//                odooSteps.waitABit(5000);
//                odooSteps.verifyInfoOfOrderOnOdoo(orderName, productName, description, orderedQty);
//            }
        }
    }

    @Then("Verify status of Delivery Order on Odoo")
    public void verifyStatusOfDeliveryOrderOnOdoo(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String owner = SessionData.getDataTbVal(dataTable, row, "Owner");
            String state = SessionData.getDataTbVal(dataTable, row, "Status");
            Client client = odooSteps.getClient();
            if (orderName.contains("@")) {
                orderName = OrderVariable.orderNumber;
            }
            if (owner.contains("@")) {
                owner = mail;
            }
            int stockPickingId = StockPickingService.getIdDO(orderName, owner, client);
            odooSteps.verifyStatusDO(stockPickingId, owner, client, state);
        }
    }

    @Given("check availability DO out of order {string}")
    public void check_availability_DO_out_of_order(String orderName) throws MalformedURLException, XmlRpcException{
        Client client = odooSteps.getClient();
        if (orderName.contains("@")) {
            orderName = OrderVariable.orderNumber;
        }
        int stockPickingId = StockPickingService.getIdDO(orderName, "", client);
        odooSteps.checkAvailabilityDOout(stockPickingId, client);
    }

    @Then("Verify info of Delivery Order on Odoo")
    public void verifyInfoOfDeliveryOrderOnOdoo(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        odooSteps.waitABit(20000);
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String customerOrderNumber = SessionData.getDataTbVal(dataTable, row, "Customer order number");
            String shippingCode = SessionData.getDataTbVal(dataTable, row, "Shipping Code");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String initialDemand = SessionData.getDataTbVal(dataTable, row, "Initial Demand");
            String reserved = SessionData.getDataTbVal(dataTable, row, "Reserved");
            String done = SessionData.getDataTbVal(dataTable, row, "Done");

            orderName = getOrderName(xOrderName);

//            try {
            odooSteps.verifyInfoOfDeliveryOrderOnOdoo(orderName, productName, customerOrderNumber, shippingCode, trackingNumber, initialDemand, reserved, done);
//            } catch (Throwable t) {
//                odooSteps.waitABit(5000);
//                odooSteps.verifyInfoOfDeliveryOrderOnOdoo(orderName, productName, customerOrderNumber, shippingCode, trackingNumber, initialDemand, reserved, done);
//            }
        }
    }

    @Then("verify status of Order on Odoo as {string}")
    public void verify_status_of_Order_on_Odoo_as(String dataKey, List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderName = "";
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            if (!orderName.isEmpty()) {
                orderName = (String) SessionData.getDataByKey(key);
            } else {
                orderName = getOrderName(xOrderName);
            }
            switch (status) {
                case "Quotation":
                    odooSteps.verifyStatusOfOrderOnOdooIsQuotation(orderName, customer);
                    break;
                case "Sales Order":
                    odooSteps.verifyStatusOfOrderOnOdooIsSalesOrder(orderName, customer);
                    break;
                case "Not exists":
                    odooSteps.verifyStatusOfOrderOnOdooIsNotExists(orderName, customer);
                    break;
                default:
                    fail();
            }
        }
    }

    @Then("verify PO is created on Odoo")
    public void verify_PO_is_created_on_Odoo(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            boolean isSync = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is sync"));
            odooSteps.verifyPurchaseOrder(purchaseNumber, email, isSync);
        }
    }

    @Given("validate DO on Odoo")
    public void validate_DO_on_Odoo(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String owner = SessionData.getDataTbVal(dataTable, row, "Owner");
            Client client = odooSteps.getClient();
            if (orderName.contains("@")) {
                orderName = OrderVariable.orderNumber;
            }
            if(orderName.contains("purchase")) {
                orderName = LoadObject.getProperty("quotation3") + "-" + purchaseNumber;
            }
            if (owner.contains("@")) {
                owner = mail;
            }
            int stockPickingId = StockPickingService.getIdDO(orderName, owner, client);
            odooSteps.validatePurchaseOrder(stockPickingId, client);
        }
    }

    @Given("cancel DO Purchase order in Odoo")
    public void cancel_DO_Purchase_order_in_Odoo(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String owner = SessionData.getDataTbVal(dataTable, row, "Owner");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String nameDOin = LoadObject.getProperty("quotation3") + "-" + purchaseNumber;
            odooSteps.cancelDOPurchaseOrder(nameDOin, owner, state);
            odooSteps.waitABit(5000);
        }
    }

    @Given("cancel Purchase order in Odoo")
    public void cancel_Purchase_order_in_Odoo(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String purchaseOrder = SessionData.getDataTbVal(dataTable, row, "Purchase number");
            String owner = SessionData.getDataTbVal(dataTable, row, "Owner");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            odooSteps.cancelPurchaseOrder(purchaseOrder, owner, state);
        }
    }

    @Given("verify SO is created on Odoo")
    public void verify_SO_is_created_on_Odoo(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String productURL = SessionData.getDataTbVal(dataTable, row, "Product URL");
            String statusQuotation = SessionData.getDataTbVal(dataTable, row, "Status quotation");
            String[] productURLs = productURL.split(",");
            if (quotationID.contains("@")) {
                quotationID = OrderVariable.quotationID;
            }
            email = LoadObject.getProperty("user.name");
            for (int i = 1; i <= productURLs.length; i++) {
                odooSteps.verifyHasSaleOrder(quotationID, email);
                odooSteps.logInfor(quotationID);
                odooSteps.verifyStatusQuotation(quotationID, statusQuotation, email);
            }
        }
    }

    @Given("update order line item of quotation")
    public void
    update_order_line_item_of_quotation(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            int newQuantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "New quantity"));
            int newPrice = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "New price"));
            String validity = SessionData.getDataTbVal(dataTable, row, "Validity");
            boolean basedOnTotal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Based on total"));
            int purchaseOrderLeadTime = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Purchase order lead time"));
            int minimumQuantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Minimum quantity"));
            String RMBprice = SessionData.getDataTbVal(dataTable, row, "RMB Price");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
//            String status = SessionData.getDataTbVal(dataTable, row, "Status");
//            if (status.isEmpty()) {
//                odooSteps.selectStatusOnQuotation("Complele");
//            } else {
//                odooSteps.selectStatusOnQuotation(status);
//            }

            quotationID = OrderVariable.quotationID;
            Client client = odooSteps.getClient();
            String email = LoadObject.getProperty("user.name");
            SaleOrder order = odooSteps.getSaleOrder(quotationID, email, client);
            if (newQuantity != 0) {
                odooSteps.updateOrderLineItemByVariantNameAndQuantity(order, variant, quantity, newQuantity, newPrice, client);
            }
            if (validity.contains("@")) {
                validity = DateTimeUtil.getTodayByFormat("yyyy-MM-dd");
            }
            if (!status.isEmpty()) {
                odooSteps.updateStatusOnQuotation(order, status, client);
            }
            if (!RMBprice.isEmpty()) {
                int RMB = Integer.parseInt(RMBprice);
                odooSteps.updateRMB(order, newQuantity, RMB, client);
            }
            odooSteps.updateValidityDate(order, validity, client);
            odooSteps.updateQuoteBaseOn(order, basedOnTotal, client);
            odooSteps.updatePurchaseOrderLeadTime(order, purchaseOrderLeadTime, client);
            odooSteps.updateMinimumOrderQuantity(order, minimumQuantity, client);
        }
    }

    @Given("add order line item of quotation")
    public void add_order_line_item_of_quotation(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationId = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            String email = SessionData.getDataTbVal(dataTable, row, "email");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            int price = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Price"));

            Client client = odooSteps.getClient();
            SaleOrder order = odooSteps.getSaleOrder(quotationId, email, client);
            odooSteps.addOrderLineItemByVariantNameAndQuantity(order, productName, variant, quantity, price, client);

        }
    }

    @And("sent by email quotation")
    public void sent_by_email_quotation(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            if (quotationID.contains("@")) {
                quotationID = OrderVariable.quotationID;
            }
            Client client = odooSteps.getClient();
            String email = LoadObject.getProperty("xp.email");
            SaleOrder order = odooSteps.getSaleOrder(quotationID, email, client);
            int saleOrderID = order.getId();
            SaleOrderService.sendEmailByID(saleOrderID, client);
        }
    }

    @Given("confirm quotation")
    public void confirm_quotation(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            if (quotationID.contains("@")) {
                quotationID = OrderVariable.quotationID;
            }
            Client client = odooSteps.getClient();
            String email = LoadObject.getProperty("xp.email");
            SaleOrder order = odooSteps.getSaleOrder(quotationID, email, client);
            int saleOrderID = order.getId();
            String result = SaleOrderService.confirmQuotation(saleOrderID, client);
            assertThat(result).isEqualTo("true");
        }
    }

    @Given("cancel quotation")
    public void cancel_quotation(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            if (quotationID.contains("@")) {
                quotationID = OrderVariable.quotationID;
            }
            String email = LoadObject.getProperty("xp.email");
            odooSteps.cancelQuotation(quotationID, email);
        }
    }

    @Given("verify product is created on Odoo")
    public void verify_product_is_created_on_Odoo(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productNameOdoo = SessionData.getDataTbVal(dataTable, row, "Product name");
            int countProductOdoo = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Count product"));
            odooSteps.verifyProductOnOdoo(productNameOdoo, countProductOdoo);
        }
    }

    @Given("verify DO {string} created in odoo with {string} DO")
    public void verify_DO_created_in_odoo_with_DO(String typeDO, String number) throws Throwable {
        int count = Integer.parseInt(number);
        if (typeDO.contains("in")) {
            String nameDOin = LoadObject.getProperty("quotation3") + "-" + purchaseNumber;
            odooSteps.verrifyDOCreatedInOdoo(nameDOin, count);
        }
        if (typeDO.contains("out")) {
            odooSteps.verrifyDOCreatedInOdoo(OrderVariable.orderNumber, count);
        }
    }

    @And("verify status DO in odo = {string}")
    public void verifyStatusDOInOdo(String status) throws Throwable {
        Client client = odooSteps.getClient();
        odooSteps.verifyStatusDOInOdo(status, OrderVariable.orderNumber, client);
    }

    @And("verify shipping method electronic list with {string}")
    public void verifyShippingMethodElectronicListWith(String country, String type) throws Throwable {
        Client client = odooSteps.getClient();
        List<DeliveryCarrier> shippingMethodList = odooSteps.getShippingMethordElectronicList(country, client);
        odooSteps.verifyShippingMethodEletronicList(OrderVariable.orderNumber, shippingMethodList);
    }

    @And("verify shipping method standard list with {string}")
    public void verifyShippingMethodStandardListWith(String country, String type) throws Throwable {
        Client client = odooSteps.getClient();
        List<DeliveryCarrier> shippingMethodList = odooSteps.getShippingMethordStandardList(country, client);
        odooSteps.verifyShippingMethodEletronicList(OrderVariable.orderNumber, shippingMethodList);
    }

    @And("done DO in odo with order {string}")
    public void doneDOInOdoWithOrder(String orderName) throws Throwable {
        Client client = odooSteps.getClient();

    }

    @And("verify DO out is not created")
    public void verifyDOOutIsNotCreated() throws Throwable {
        Client client = odooSteps.getClient();
        odooSteps.verifyStatusDOInOdo("", OrderVariable.orderNumber, client);
    }

    @And("get info DO out on odoo and verify")
    public void getInfoDOOutOnOdooAndVerify() throws Throwable {
        Client client = odooSteps.getClient();
        int idDO = StockPickingService.getIdDO(OrderVariable.orderNumber, mail, client);
        List<StockPicking> moveIdsWithoutPackage = StockPickingService.readStockPickingById(idDO, client);
        List<String> listProduct = new ArrayList<>();
        for (int row : moveIdsWithoutPackage.get(0).getMoveLineIdsWithoutPackage()) {
            listProduct.add(StockMoveLineService.getProductVariant(row, client));
        }
        String[] variantList = variantValueOdoo.split(";");
        for (String variant : variantList) {
            String[] subVariant = variant.split(">");
            for (String productVariant : listProduct) {
                assertThat(productVariant).isEqualTo(nameProductOdoo + "(" + subVariant[1] + ")");
            }

        }
    }

    @And("done PO IN in odoo")
    public void donePOINInOdoo() throws Throwable {
        Client client = odooSteps.getClient();
        List<PurchaseOrder> list = PurchaseOrderService.getPurchaseOrderByName(numberPurchase, client);
    }

    @When("change product name to {string} from {string}")
    public void changeProductNameToFrom(String oldName, String newName) throws Throwable {
        Client client = odooSteps.getClient();
        int id = ProductService.getProductIDByName(oldName, client);
        String result = ProductService.updateProductNameById(id, newName, client);
        assertThat(result).isEqualTo("true");
    }

    @Given("^user set Unavailable product in odoo$")
    public void userSetUnavailableProductInOdoo(List<List<String>> data) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String productName = SessionData.getDataTbVal(data, row, "Product name");
            boolean status = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Set unavaiable"));
            Client client = odooSteps.getClient();
            int productId = ProductService.getProductIDByName(productName, client);
            odooSteps.setUnAvaliableProductOdoo(productId, status, client);
        }
    }


    @And("update quotation expired")
    public void updateQuotationExpired(List<List<String>> data) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String url = SessionData.getDataTbVal(data, row, "Product URL");
            String validity = SessionData.getDataTbVal(data, row, "Validity");
            String email = SessionData.getDataTbVal(data, row, "Email");
            String status = SessionData.getDataTbVal(data, row, "Status");
            Client client = odooSteps.getClient();
            SaleOrder order = odooSteps.getSaleOrderByUrlAndEmail(url, email, client);
            odooSteps.updateValidityDate(order, validity, client);
            if (!status.isEmpty()) {
                odooSteps.updateStatusOnQuotation(order, status, client);
            }
        }
    }

    @And("delete SO on Odoo with")
    public void deleteSOOnOdooWith(List<List<String>> data) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String url = SessionData.getDataTbVal(data, row, "Product URL");
            String email = SessionData.getDataTbVal(data, row, "Email");
            Client client = odooSteps.getClient();
            SaleOrder order = odooSteps.getSaleOrderByUrlAndEmail(url, email, client);
            if (order.getId() > 0) {
                odooSteps.cancelQuotationByID(order.getId(), client);
                odooSteps.deleteQuotationByID(order.getId(), client);
            }

        }
    }

    @And("update validity of quotation")
    public void updateValidityOfQuotation(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID").replace("@Quotation@", OrderVariable.quotationID);
            String validity = SessionData.getDataTbVal(dataTable, row, "Validity");
            Client client = odooSteps.getClient();
            String email = LoadObject.getProperty("user.name");
            SaleOrder order = odooSteps.getSaleOrder(quotationID, email, client);
            if (validity.contains("@")) {
                validity = DateTimeUtil.getNextByFormat("yyyy-MM-dd");
                odooSteps.updateValidityDate(order, validity, client);
            }
        }
    }

    @And("automatic cost calculation")
    public void automaticCostCalculation(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID").replace("@Quotation@", OrderVariable.quotationID);
            boolean automaticCalculateCost = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Automatic calculate cost"));
            Client client = odooSteps.getClient();
            String email = LoadObject.getProperty("user.name");
            SaleOrder order = odooSteps.getSaleOrder(quotationID, email, client);
            odooSteps.automaticCalculateCost(order, automaticCalculateCost, client);
        }
    }

    @And("update markup pricing")
    public void updateMarkupPricing(List<List<String>> dataTable) throws MalformedURLException, XmlRpcException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationID = SessionData.getDataTbVal(dataTable, row, "Quotation ID").replace("@Quotation@", OrderVariable.quotationID);
            double productCost = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Product cost"));
            double domesticShipping = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Domestic shipping"));
            Client client = odooSteps.getClient();
            String email = LoadObject.getProperty("user.name");
            SaleOrder order = odooSteps.getSaleOrder(quotationID, email, client);
            for (int id : order.getOrderLine()) {
                odooSteps.updateProductCost(id, productCost, client);
                odooSteps.updateDomesticShipping(id, domesticShipping, client);
            }
        }
    }
}