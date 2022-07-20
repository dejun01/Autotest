package com.opencommerce.odoo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.constant.RelationCommand;
import com.opencommerce.odoo.models.SaleOrder;
import com.opencommerce.odoo.models.SaleOrderLineItem;
import org.apache.xmlrpc.XmlRpcException;

import java.util.*;

import static java.util.Arrays.asList;

public class SaleOrderLineService {
    private static String SALE_ORDER_LINE = "sale.order.line";


    public static SaleOrderLineItem getOrderLineItem(int id, Client client) throws XmlRpcException {
        return client.getById(SALE_ORDER_LINE, id, Arrays.asList(), SaleOrderLineItem.class);
    }

    public static String updateOrderLineItem(SaleOrderLineItem item, Client client) throws XmlRpcException {
        return client.updateItem(item.getId(), SALE_ORDER_LINE, item);
    }

    public static void updateProductCost(int id, double val,String field, Client client)throws XmlRpcException {
        client.updateItem(id, SALE_ORDER_LINE, new HashMap() {{
            put(field, val);
        }});
    }
}