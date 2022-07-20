package com.opencommerce.odoo.services;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.StockMoveLine;
import com.opencommerce.odoo.models.StockPicking;
import org.apache.xmlrpc.XmlRpcException;

import java.util.List;

import static java.util.Arrays.asList;

public class StockMoveLineService {
    private static String STOCK_MOVE_LINE = "stock.move.line";


    public static List<StockMoveLine> readStockPickingById(int id, Client client) throws XmlRpcException {
        return client.read(STOCK_MOVE_LINE, asList(id), StockMoveLine.class);
    }

    public static String getProductVariant(int pickingID, Client client) throws XmlRpcException {
        List<StockMoveLine> list= readStockPickingById(pickingID, client);
        String name = "";
        for (StockMoveLine row : list) {
            name = row.getDisplayName();
        }
        return name;
    }






}
