//package com.opencommerce.odoo;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.path.json.JsonPath;
//import com.opencommerce.odoo.constant.RelationCommand;
//import com.opencommerce.odoo.models.*;
//import com.opencommerce.odoo.services.ProductService;
//import com.opencommerce.odoo.services.PurchaseOrderService;
//import com.opencommerce.odoo.services.SaleOrderService;
//import junit.framework.TestCase;
//import org.apache.xmlrpc.XmlRpcException;
//import org.junit.test.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//import static java.util.Arrays.asList;
//import static java.util.Arrays.sort;
//
//public class TestOdooQuery extends TestCase {
//    private Client client;
//
//    public void setUp() throws Exception {
//        String url = "https://stag-odoo.boostflow.com";
//        String database = "sbcn001";
//        String username = "vietnguyen1@beeketing.net";
//        String password = "gobeeketing";
//        client = Session.newSession(url, database, username, password);
//    }
//
//    @test.Test
//    public void testFindOrderByName() throws XmlRpcException {
//        List<SaleOrder> orders = SaleOrderService.findOrderByNameAndEmail("Buyer orders-7216537", "autoqa1@crosspanda.com", client);
//        for (int i = 0; i < orders.size(); i++) {
//            System.out.println(orders.get(i).getName());
//        }
//        assertTrue(orders.size() > 0);
//    }
//
//    @test.Test
//    public void testGetOrderLineItemById() throws XmlRpcException {
//        int id = 1091;
//        SaleOrderLineItem olis = SaleOrderService.getOrderLineItem(id, client);
//        System.out.println(olis);
//    }
//
//    @test.Test
//    public void testwritesalseOrder() throws XmlRpcException {
//        String quotation = "Q1682-P126-I1778";
//        List<SaleOrder> orders = SaleOrderService.findOrderByNameAndEmail(quotation, "autoqa1@crosspanda.com", client);
//        System.out.println(orders.get(0).getId());
//        System.out.println(orders.get(0).getName());
//        orders.get(0).getOrderLine();
//        System.out.println(asList(orders.get(0).getPartnerId()));
//    }
//
//    @test.Test
//    public void testGetVariantID() throws XmlRpcException {
//        String productName = "Spring Women Dress 2020";
//        String nameVariants = "Size: S,Color: Green";
//        List<ProductVariant> productVariants = ProductService.searchProduct(productName, client);
//        List attributeIds = ProductService.getListAtributeValueID(productVariants);
//
//        int a = ProductService.getVariantID(productVariants, nameVariants, attributeIds, client);
//        System.out.println(a);
//    }
//
//    @test.Test
//    public void testGetAndUpdateOrderLineItemById() throws XmlRpcException {
//        int id = 6900;
//        SaleOrderLineItem soli = SaleOrderService.getOrderLineItem(id, client);
//        System.out.println(soli.getProductName());
//        soli.setPriceUnit(20);
//        soli.setProduct_uom_qty(10);
//        System.out.println(SaleOrderService.updateOrderLineItem(soli, client));
//    }
//
//    @test.Test
//    public void testAddNewSOLI() throws XmlRpcException {
//        List<SaleOrder> orders = SaleOrderService.findOrderByNameAndEmail("Q1637-P131-I11018", "autoqa1@crosspanda.com", client);
//        for (int i = 0; i < orders.size(); i++) {
//            System.out.println(orders.get(i).getName());
//            int[] lineItemIds = orders.get(i).getOrderLine();
//            int sequence = 0;
//            for (int lineItemId : lineItemIds) {
//                SaleOrderLineItem saleOrderLineItem = SaleOrderService.getOrderLineItem(lineItemId, client);
//                if (saleOrderLineItem != null) {
//                    if (sequence <= saleOrderLineItem.getSequence()) {
//                        sequence = saleOrderLineItem.getSequence();
//                    }
//                }
//                System.out.println("Line item id " + i + " = " + lineItemId);
//            }
//            SaleOrderLineItem soli = new SaleOrderLineItem();
//            soli.setPriceUnit(100);
//            soli.setName("Just testing");
//            soli.setProductId(new Object[]{58055, "Just testing"});
//            soli.setSequence(sequence++);
//
//            String result = SaleOrderService.addNewItemToOrder(orders.get(i), soli, client);
//            System.out.println(result);
//        }
//    }
//
//    @test.Test
//    public void testSendmail() throws XmlRpcException {
//
//        int emailID = 2775;
//        int quID = 5053;
//
//        List<Email> emails = client.read("mail.compose.message", asList(asList(emailID), asList("composition_mode", "model", "res_id", "is_log", "parent_id", "mail_server_id", "active_domain", "use_active_domain", "email_from", "partner_ids", "subject", "notify", "no_auto_thread", "reply_to", "body", "attachment_ids", "template_id", "display_name")), Email.class);
//        System.out.println(emails.get(0).getTemplate_id());
//        System.out.println(emails.get(0).getId());
//        System.out.println(emails.get(0).getSubject());
//
//        SaleOrderService.sendEmailByID(quID, emailID, client);
//
//        List<Email> email = client.read("sale.order", asList(quID), Email.class);
//        System.out.println(email.get(0).getState());
//
//    }
//
//    @test.Test
//    public void testConfirm() throws XmlRpcException {
//        int partnerID = 1356;
//        String result = PurchaseOrderService.addPurchaseOrder(partnerID, client);
//        System.out.println(result.toString());
//    }
//
//    @test.Test
//    public void test() throws XmlRpcException {
//        int purchaseID = 154;
//        int pickingId = 2641;
//        String result = PurchaseOrderService.assignOwner(purchaseID, pickingId, client);
//        System.out.println(result);
//
//    }
//
//
//    @test.Test
//    public void CreatePurchaseOrder() throws XmlRpcException {
//        int id = 5095;
//        System.out.println(SaleOrderService.confirmQuotation(id, client));
//    }
//
//    @test.Test
//    public void testAddNewPO() throws XmlRpcException {
//        int id = 145;
//
//        PurchaseOrder po = PurchaseOrderService.findOrderByID(id, client).get(0);
//        int[] lineItemIds = po.getOrderLine();
//        int sequence = 10;
//        for (int lineItemId : lineItemIds) {
//            PurchaseOrderLineItem saleOrderLineItem = PurchaseOrderService.getOrderLineItem(lineItemId, client);
//            if (saleOrderLineItem != null) {
//                if (sequence <= saleOrderLineItem.getSequence()) {
//                    sequence = saleOrderLineItem.getSequence();
//                }
//            }
//        }
//        PurchaseOrderLineItem poli = new PurchaseOrderLineItem();
//        poli.setPrice_unit(100);
//        poli.setName("Spring Women Dress 2020 (M, Green)");
//        poli.setProduct_qty(10);
//
//        poli.setProductId(new Object[]{58055, "Just testing"});
//        poli.setProduct_uom(new Object[]{1, "Unit(s)"});
//        poli.setSequence(sequence + 1);
//        poli.setState("draft");
//        poli.setDate_planned("2019-11-25 06:59:13");
//        PurchaseOrderService.addNewItemToOrder(po, poli, client);
//    }
//
//    @test.Test
//    public void testTranfer() throws XmlRpcException {
//        System.out.println(1);
//        int pickingId = 2430;
//        Transfer a = PurchaseOrderService.button_validate("stock.picking", asList(pickingId), Transfer.class, client);
//        System.out.println(a.getRes_id());
//    }
//
//
//}
