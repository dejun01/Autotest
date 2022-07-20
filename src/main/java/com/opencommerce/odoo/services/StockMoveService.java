package com.opencommerce.odoo.services;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.StockMove;
import com.opencommerce.odoo.models.StockPicking;
import common.utilities.LoadObject;
import org.apache.xmlrpc.XmlRpcException;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

public class StockMoveService {

    private static String STOCK_MOVE = "stock.move";

    public static List<StockMove> readStockMove(int id, Client client) throws XmlRpcException {
        List<StockMove> result = client.read(STOCK_MOVE, asList(id), StockMove.class);
        return result;
    }
}
