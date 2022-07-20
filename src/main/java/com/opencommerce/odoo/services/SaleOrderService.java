package com.opencommerce.odoo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.constant.RelationCommand;
import com.opencommerce.odoo.models.SaleOrder;
import com.opencommerce.odoo.models.SaleOrderLineItem;
import org.apache.xmlrpc.XmlRpcException;

import java.util.*;

import static java.util.Arrays.asList;

public class SaleOrderService {
    private static String SALE_ORDER = "sale.order";

    public static List<SaleOrder> findOrderByName(String name, Client client) throws XmlRpcException {
        return client.getByName(SALE_ORDER, name, Collections.emptyList(), SaleOrder.class);
    }

    public static List<SaleOrder> findOrderByNameAndEmail(String name, String email, Client client) throws XmlRpcException {
        return client.searchRead(SALE_ORDER, asList(asList(asList("name", "ilike", name), asList("partner_id", "child_of", email))), asList(), SaleOrder.class);
    }

    public static List<SaleOrder> getSaleOrderByUrlAndEmail(String url,String email, Client client) throws XmlRpcException {
        return client.searchRead(SALE_ORDER, asList(asList(asList("x_quoted_product_url", "ilike", url),asList("partner_id", "ilike", email))), asList(), SaleOrder.class);
    }

    public static List<SaleOrder> readSaleOrderById(int id, Client client) throws XmlRpcException {
        return client.read(SALE_ORDER, asList(id), SaleOrder.class);
    }

    public static List<SaleOrder> findAll(Client client) throws XmlRpcException {
        return client.getAll(SALE_ORDER, Arrays.asList("name", "date_order", "partner_id"), SaleOrder.class);
    }

    public static String addNewItemToOrder(SaleOrder so, SaleOrderLineItem newSoLi, Client client) throws XmlRpcException {
        List<Object> allLineItems = new ArrayList<>();
        for (int existingSoLi : so.getOrderLine()) {
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
        return client.updateItem(so.getId(), SALE_ORDER, args);
    }

    public static void updateDate(SaleOrder so, String date, Client client) throws XmlRpcException {
        client.updateItem(so.getId(), SALE_ORDER, new HashMap() {{
            put("validity_date", date);
        }});
    }

    public static void updateStatus(SaleOrder so, String status, Client client) throws XmlRpcException {
        client.updateItem(so.getId(), SALE_ORDER, new HashMap() {{
            put("x_status", status);
        }});
    }

    public static String createComposeEmail(int partnerID, String emailFrom, String subject, int quotaionID, Client client) throws XmlRpcException {
        HashMap args = new HashMap();
        args.put("subject", subject);
        args.put("partner_ids", asList(asList(6, false, asList(partnerID))));
        args.put("attachment_ids", asList(asList(6, false, asList())));
        args.put("email_from", emailFrom);
        args.put("rest_id", quotaionID);
        args.put("body", "<div style=\"font-size:13px;font-family:&quot;Lucida Grande&quot;, Helvetica, Verdana, Arial, sans-serif; margin:0px; padding:0px\">\n" +
                "    <p style=\"margin:0px 0 1rem 0;font-size:13px;font-family:&quot;Lucida Grande&quot;, Helvetica, Verdana, Arial, sans-serif; margin:0px; padding:0px\"><br></p><p style=\"margin:0px 0 1rem 0;font-size:13px;font-family:&quot;Lucida Grande&quot;, Helvetica, Verdana, Arial, sans-serif; margin:0px; padding:0px\">Shopbase China team.<br></p>\n" +
                "</div>\n");
        args.put("composition_mode", "comment");
        args.put("model", SALE_ORDER);
        args.put("is_log", false);
        args.put("mail_server_id", false);
        args.put("no_auto_thread", false);
        args.put("notify", false);
        args.put("parent_id", false);
        args.put("use_active_domain", false);
        args.put("template_id", 11);


        List payload = asList(args);

        return client.create("mail.compose.message", payload);
    }

    public static void sendEmailByID(int id, Client client) throws XmlRpcException {
        List payload = Arrays.asList(asList(id), new HashMap() {{
            put("state", "sent");
        }});
        client.doRequest(SALE_ORDER, "write", payload, null);
    }

    public static String confirmQuotation(int id, Client client) throws XmlRpcException {
        return client.action_confirm(SALE_ORDER, asList(id));
    }

    public static void updateQuoteBaseOn(SaleOrder order, boolean basedOnTotal, Client client) throws XmlRpcException {
        client.updateItem(order.getId(), SALE_ORDER, new HashMap() {{
            put("x_quote_based_on", basedOnTotal);
        }});
    }

    public static void updatePurchaseOrderLeadTime(SaleOrder order, int purchaseOrderLeadTime, Client client) throws XmlRpcException {
        client.updateItem(order.getId(), SALE_ORDER, new HashMap() {{
            put("x_estimated_delivery", purchaseOrderLeadTime);
        }});
    }

    public static void updateMinimumOrderQuantity(SaleOrder order, int minimumOrderQuantity, Client client) throws XmlRpcException {
        client.updateItem(order.getId(), SALE_ORDER, new HashMap() {{
            put("x_minimum_order_quantity", minimumOrderQuantity);
        }});
    }

    public static String updateOrderLineItem(int id, SaleOrderLineItem item, Client client) throws XmlRpcException {
        return client.updateItem(id, SALE_ORDER, item);
    }

    public static String call_button(String order, String email, Client client, String method) throws XmlRpcException {
        List<SaleOrder> orders = findOrderByNameAndEmail(order, email, client);
        int idOrder = orders.get(0).getId();
        Object result = client.doRequest(SALE_ORDER, method, asList(idOrder), null);
        return result.toString();
    }

    public static String create(String order, String email, Client client, String method) throws XmlRpcException {
        List<SaleOrder> orders = findOrderByNameAndEmail(order, email, client);
        int idOrder = orders.get(0).getId();
        Object result = client.doRequest("mail.compose.message", "create", asList(idOrder), null);
        return result.toString();
    }

    public static int getIDQuotation(String quotation, String email, Client client) throws XmlRpcException {
        List<SaleOrder> orders = findOrderByNameAndEmail(quotation, email, client);
        int idQuotation = orders.get(0).getId();
        return idQuotation;
    }


    public static String callButtonByID(int quotationID,String method, Client client) throws XmlRpcException {
        Object result = client.doRequest(SALE_ORDER, method, asList(quotationID), null);
        return result.toString();
    }
    public static void automaticCalculateCost(SaleOrder order, boolean automaticCalculateCost, Client client) throws XmlRpcException {
        client.updateItem(order.getId(), SALE_ORDER, new HashMap() {{
            put("x_automated_cost_calculation", automaticCalculateCost);
        }});
    }


}