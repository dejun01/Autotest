package com.opencommerce.odoo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.constant.RelationCommand;
import com.opencommerce.odoo.models.*;
import common.utilities.LoadObject;
import org.apache.xmlrpc.XmlRpcException;

import java.util.*;

import static java.util.Arrays.asList;

public class PurchaseOrderService {
    private static String PURCHASE_ORDER = "purchase.order";
    private static String PURCHASE_ORDER_LINE = "purchase.order.line";


    public static List<SaleOrder> findOrderByName(String name, Client client) throws XmlRpcException {
        return client.getByName(PURCHASE_ORDER, name, Collections.emptyList(), SaleOrder.class);
    }

    public static List<PurchaseOrder> findPurchaseOrderByNameAndEmail(String name, String email, Client client) throws XmlRpcException {
        return client.searchRead(PURCHASE_ORDER, asList(asList(asList("name", "ilike", name), asList("partner_id", "child_of", email))), asList(), PurchaseOrder.class);
    }

    public static List<PurchaseOrder> findAll(Client client) throws XmlRpcException {
        return client.getAll(PURCHASE_ORDER, Arrays.asList("name", "date_order", "partner_id"), PurchaseOrder.class);
    }

    public static PurchaseOrderLineItem getOrderLineItem(int id, Client client) throws XmlRpcException {
        return client.getById(PURCHASE_ORDER_LINE, id, Arrays.asList(), PurchaseOrderLineItem.class);
    }

    public static String updateOrderLineItem(PurchaseOrderLineItem item, Client client) throws XmlRpcException {
        return client.updateItem(item.getId(), PURCHASE_ORDER_LINE, item);
    }

    public static String addNewItemToOrder(PurchaseOrder po, PurchaseOrderLineItem newSoLi, Client client) throws XmlRpcException {
        List<Object> allLineItems = new ArrayList<>();
        for (int existingSoLi : po.getOrderLine()) {
            ArrayList<Object> item = new ArrayList<>();
            item.add(RelationCommand.ADD_EXISTING_RECORD.getCode());
            item.add(existingSoLi);
            item.add(false);
            allLineItems.add(item);
        }
        ObjectMapper mapper = new ObjectMapper();
        allLineItems.add(asList(RelationCommand.NEW_RECORD.getCode(), "_", mapper.convertValue(newSoLi, HashMap.class)));
        HashMap args = new HashMap();
        args.put("order_line", allLineItems);

        return client.updateItem(po.getId(), PURCHASE_ORDER, args);
    }


    public static List<PurchaseOrder> findOrderByID(int id, Client client) throws XmlRpcException {
        return client.read("purchase.order", asList(id), PurchaseOrder.class);

    }

    public static String addPurchaseOrder(int partnerID, Client client) throws XmlRpcException {
        int currencyID = 23;
        Object result = client.create("purchase.order", asList(new HashMap() {{
            put("company_id", 1);
            put("partner_id", partnerID);
            put("currency_id", currencyID);
        }}));

        if (result != null) {
            return result.toString();
        }
        return null;
    }


    public static String addOwner(int pickingId, String ownerid, Client client) throws XmlRpcException {
        Object result = client.doRequest("stock.picking", "write", asList(asList(pickingId), new HashMap() {{
            put("owner_id", ownerid);
        }}), null);
        if (result != null) {
            return result.toString();
        }
        return null;
    }


    public static Transfer button_validate(String model, List args, Class<Transfer> clazz, Client client) throws XmlRpcException {
        Object data = client.doRequest(model, "button_validate", args, null);
        ObjectMapper mapper = new ObjectMapper();
        Transfer record = mapper.convertValue(data, clazz);
        return record;
    }

    public static void actionDone(String model, List args, Client client) throws XmlRpcException {
        client.doRequest(model, "action_quick_done", args, null);
//        ObjectMapper mapper = new ObjectMapper();
//        Transfer record = mapper.convertValue(data, clazz);
//        return record;
    }

    public static String call_button(String order, String email, Client client, String method) throws XmlRpcException {
        List<PurchaseOrder> orders = findPurchaseOrderByNameAndEmail(order, email, client);
        int idOrder = orders.get(0).getId();
        Object result = client.doRequest("purchase.order", method, asList(idOrder), null);
        return result.toString();
    }

    public static String getIdDO(String order, String email, Client client) throws XmlRpcException {
        List<PurchaseOrder> orders = findPurchaseOrderByNameAndEmail(order, email, client);
        int idOrder = orders.get(0).getId();
        Object result = client.doRequest("purchase.order", "action_view_picking", asList(idOrder), null);
        Map res_id = (Map) result;
        return res_id.get("res_id").toString();
    }

    public static int getIdPurchaseOrder(String order, String email, Client client) throws XmlRpcException {
        List<PurchaseOrder> orders = findPurchaseOrderByNameAndEmail(order, email, client);
        int purchaseOrderId = orders.get(0).getId();
        return purchaseOrderId;
    }


    public static String name_search(int idDO, String email, Client client) throws XmlRpcException {
        HashMap<String, String> args = new HashMap();
        String nameEmail = LoadObject.getProperty("xp.email");
        args.put("name", nameEmail);
        Object result = client.name_search("res.partner", "name_search", asList(), args);
        return result.toString();
    }

    public static int getPurchaseOrderLineID(String order, String email, Client client) throws XmlRpcException {
        List<PurchaseOrder> orders = findPurchaseOrderByNameAndEmail(order, email, client);
        int idOrder = orders.get(0).getId();
        orders.getClass();
        Map orderLine = (Map) orders;
        for (Object key : orderLine.keySet()){
            System.out.println(key);
        }
        return idOrder;

    }

    public static List<PurchaseOrder> getPurchaseOrderByName(String name, Client client) throws XmlRpcException {
        return client.getByName(PURCHASE_ORDER, name, Collections.emptyList(), PurchaseOrder.class);
    }
}
