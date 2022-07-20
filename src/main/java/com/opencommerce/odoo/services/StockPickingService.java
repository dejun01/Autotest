package com.opencommerce.odoo.services;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.StockPicking;
import common.utilities.LoadObject;
import org.apache.xmlrpc.XmlRpcException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class StockPickingService {

    private static String STOCK_PICKING = "stock.picking";

    public static List<StockPicking> findDeliveryOrderByName(String name, Client client) throws XmlRpcException {
        return client.searchRead(STOCK_PICKING, asList(asList("|", asList("name", "ilike", name), asList("origin", "ilike", name))), asList(), StockPicking.class);
    }

    public static List<StockPicking> findDeliveryOrderByNameAndEmail(String name, String email, Client client) throws XmlRpcException {
        return client.searchRead(STOCK_PICKING, asList(asList("|", asList("name", "ilike", name), asList("origin", "ilike", name), asList("partner_id", "child_of", email))), asList(), StockPicking.class);
    }

    public static void assignOwner(int idDO, String email, Client client) throws XmlRpcException {
        int ownerId = Integer.parseInt(LoadObject.getProperty("owner_id"));
        PurchaseOrderService.name_search(idDO, null, client);
        HashMap<String, String> args = new HashMap();
        args.put("owner_id", String.valueOf(ownerId));
        client.doRequest(STOCK_PICKING, "write", asList(asList(idDO), args), null);
    }

    public static List<StockPicking> findDeliveryOrderReadyByNameAndEmail(String name, String email, Client client) throws XmlRpcException {
        return client.searchRead(STOCK_PICKING, asList(asList(asList("picking_type_id", "=", 1), asList("state", "in", asList("assigned", "partially_available")), "|", asList("name", "ilike", name), asList("origin", "ilike", name), asList("partner_id", "child_of", email))), asList(), StockPicking.class);
    }

    public static List<StockPicking> readStockPickingById(int id, Client client) throws XmlRpcException {
        return client.read(STOCK_PICKING, asList(id), StockPicking.class);
    }

    public static int buttonValidateById(int id, Client client) throws XmlRpcException {
        Object result = client.doRequest(STOCK_PICKING, "button_validate_custom", asList(id), null);
        HashMap<String, Object> obj = (HashMap<String, Object>) result;
        return (Integer) obj.get("res_id");
    }


    public static int getIdDO(String order, String email, Client client) throws XmlRpcException {
        List<StockPicking> orders = findDeliveryOrderByName(order, client);
        int idOrder = orders.get(0).getId();
        return idOrder;
    }

    public static String getStatusDO(int pickingId, String email, Client client) throws XmlRpcException {
        Object[] result = (Object[]) client.doRequest(STOCK_PICKING, "read", asList(pickingId), null);
        Map mapResult = (Map) result[0];
        return mapResult.get("state") == null ? "" : mapResult.get("state").toString();
    }

    public static void call_button(int pickingId, String email, String method, Client client) throws XmlRpcException {
        client.doRequest(STOCK_PICKING, method, asList(pickingId), null);
    }

}
