package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.Session;

import com.opencommerce.odoo.models.StockMove;
import com.opencommerce.odoo.models.StockPicking;
import com.opencommerce.odoo.services.StockImmediateTransferService;
import com.opencommerce.odoo.services.StockMoveService;
import com.opencommerce.odoo.services.StockPickingService;

import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.*;

public class MainTest {

//    public static void main(String[] args) throws MalformedURLException, XmlRpcException, JsonProcessingException {

//        String strTest = "#NMDdgfgfh98565655";
//        String[] part = strTest.split("(?<=\\D)(?=\\d)");
//        System.out.println(part[0]);
//        System.out.println(part[1]);
//
//        Client client = getClient();
//
//        List<StockPicking> findDOReadyByNameAndEmail = StockPickingService.findDeliveryOrderReadyByNameAndEmail("AUS1796", "autoqa1@crosspanda.com", client);
//        int idDeliveryOrders = findDOReadyByNameAndEmail.get(0).getId();
//
//        List<StockPicking> readStockPicking = StockPickingService.readStockPickingById(idDeliveryOrders, client);
//        int[] moveIdsWithoutPackage = readStockPicking.get(0).getMoveIdsWithoutPackage();
//
//        HashMap<Integer, HashMap<String, String>> dataBefore = new HashMap<>();
//
//        for (int i = 0; i < moveIdsWithoutPackage.length; i++) {
//            HashMap<String, String> val = new HashMap<>();
//            StockMove readStockMove = StockMoveService.readStockMove(moveIdsWithoutPackage[i], client).get(0);
//            String sku = ((ArrayList) readStockMove.getProductId()).get(1).toString().split(" ", 2)[0].replace("[", "").replace("]", "");
//            String product = ((ArrayList) readStockMove.getProductId()).get(1).toString().split(" ", 2)[1];
//            String shippingCode = readStockMove.getX_shipping_method();
//            String initialDemand = String.valueOf(readStockMove.getProductUomQty());
//            String reserved = String.valueOf(readStockMove.getReservedAvailability());
//            String done = String.valueOf(readStockMove.getQuantityDone());
//
//            val.put("SKU", sku);
//            val.put("Product", product);
//            val.put("Shipping Code", shippingCode);
//            val.put("Initial Demand", initialDemand);
//            val.put("Reserved", reserved);
//            val.put("Done", done);
//
//            dataBefore.put(i, val);
//        }
//
//        int idStockImmediateTransfer = StockPickingService.buttonValidateById((idDeliveryOrders), client);
//
//        StockImmediateTransferService.processStockImmediateTransfer(idStockImmediateTransfer, client);
//
//        HashMap<Integer, HashMap<String, String>> dataAfter = new HashMap<>();
//
//        for (int i = 0; i < moveIdsWithoutPackage.length; i++) {
//            HashMap<String, String> val = new HashMap<>();
//            StockMove readStockMove = StockMoveService.readStockMove(moveIdsWithoutPackage[i], client).get(0);
//            String sku = ((ArrayList) readStockMove.getProductId()).get(1).toString().split(" ", 2)[0].replace("[", "").replace("]", "");
//            String product = ((ArrayList) readStockMove.getProductId()).get(1).toString().split(" ", 2)[1];
//            String customerOrderNumber = readStockMove.getCustomerOrderNumber();
//            String shippingCode = readStockMove.getX_shipping_method();
//            String trackingNumber = String.valueOf(readStockMove.getX_tracking_number());
//            String initialDemand = String.valueOf(readStockMove.getProductUomQty());
//            String done = String.valueOf(readStockMove.getQuantityDone());
//
//            val.put("SKU", sku);
//            val.put("Product", product);
//            val.put("Customer Order Number", customerOrderNumber);
//            val.put("Shipping Code", shippingCode);
//            val.put("Tracking Number", trackingNumber);
//            val.put("Initial Demand", initialDemand);
//            val.put("Done", done);
//
//            dataAfter.put(i, val);
//        }
//        System.out.println("dataBefore:" + dataBefore);
//        System.out.println("dataAfter:" + dataAfter);
//    }
//
//    public static Client getClient() throws MalformedURLException, XmlRpcException {
//        String url = "https://stag-odoo.crosspanda.com";
//        String username = "vietnguyen1@beeketing.net";
//        String password = "gobeeketing";
//        String database = "sbcn_staging";
//        return Session.newSession(url, database, username, password);

    public static <T> void printArray(T[] elements) {
        for (T element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        String name = "Custom couple' faces with \"Under the sea\" theme";
        String[] a = name.split("\'|\"");
        String x = "//p[";
        for (int i = 0; i < a.length; i++) {
            if (i != 0) {
                x += " and ";
            }
            x = x + "contains(text(),'" + a[i] + "')";
            if (i == a.length - 1) {
                x += "]";
            }
        }
        System.out.println(x);
    }
}
