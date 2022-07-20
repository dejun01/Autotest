package com.opencommerce.odoo.services;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.PurchaseOrder;
import com.opencommerce.odoo.models.StockImmediateTransfer;
import com.opencommerce.odoo.models.StockMove;
import com.opencommerce.odoo.models.StockPicking;
import common.utilities.LoadObject;
import org.apache.xmlrpc.XmlRpcException;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

public class StockImmediateTransferService {

    private static String STOCK_IMMEDIATE_TRANSFER = "stock.immediate.transfer";

    public static List<StockImmediateTransfer> readStockImmediateTransfer(int id, Client client) throws XmlRpcException {
        return client.read(STOCK_IMMEDIATE_TRANSFER, asList(id), StockImmediateTransfer.class);
    }

    public static int createStockImmediateTransfer(int id, Client client) throws XmlRpcException {
        return Integer.parseInt(client.create(STOCK_IMMEDIATE_TRANSFER, asList((new HashMap() {{
            put("pick_ids", asList(6, id));
        }}))));
    }

    public static Object processStockImmediateTransfer(int id, Client client) throws XmlRpcException {
        return client.doRequest(STOCK_IMMEDIATE_TRANSFER, "process", asList(id), null);
    }

    public static void create(int pickingId, Client client) throws XmlRpcException {
        createStockImmediateTransfer(pickingId, client);
//        HashMap<String, String> map = new HashMap<>();
//        createStockImmediateTransfer
//        client.doRequest(STOCK_IMMEDIATE_TRANSFER, "create", asList(pickingId), null);
    }
}
