package com.opencommerce.odoo.steps;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.Session;
import com.opencommerce.odoo.models.*;
import com.opencommerce.odoo.pages.OdooPage;
import com.opencommerce.odoo.services.*;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.xmlrpc.XmlRpcException;
import org.assertj.core.api.Java6Assertions;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Java6Assertions;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class OdooSteps extends ScenarioSteps {
    OdooPage odooPage;
    ProductService productService;
    SaleOrderLineService saleOrderLineService;

    public Client getClient() throws MalformedURLException, XmlRpcException {
        String url = LoadObject.getProperty("odoo.hostname");
        String username = LoadObject.getProperty("odoo.username");
        String password = LoadObject.getProperty("odoo.password");
        String database = LoadObject.getProperty("odoo.database");
        return Session.newSession(url, database, username, password);
    }

    @Step
    public void verifyHasSaleOrder(String orderName, String partnerEmail) throws MalformedURLException, XmlRpcException {
        Client client;
        List<SaleOrder> orders;
        int i = 1;
        do {
            waitABit(5000);
            client = getClient();
            orders = SaleOrderService.findOrderByNameAndEmail(orderName, partnerEmail, client);
            i++;
            if (i == 20) {
                break;
            }
        } while (orders.size() <= 0);
        assertThat(orders.size()).isGreaterThan(0);
    }

    @Step
    public void verifyPurchaseOrder(String purchaseOrder, String partnerEmail, boolean isSync) throws MalformedURLException, XmlRpcException {
        Client client;
        List<PurchaseOrder> orders;
        int i = 1;
        do {
            waitABit(5000);
            client = getClient();
            orders = PurchaseOrderService.findPurchaseOrderByNameAndEmail(purchaseOrder, partnerEmail, client);
            i++;
            if (i == 20) {
                break;
            }
        } while (orders.size() <= 0);
        if (isSync) {
            assertThat(orders.size()).isGreaterThan(0);
        } else {
            assertThat(orders.size()).isEqualTo(0);
        }
    }

    @Step
    public void verifySOHasProduct(String orderNumber, List<String> productLink, String partnerEmail) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        List<SaleOrder> orders = SaleOrderService.findOrderByNameAndEmail(orderNumber, partnerEmail, client);
        String odoo = "";
        boolean correct = false;
        for (SaleOrder so : orders) {
            if (so.getOrderLine().length > 0) {
                SaleOrderLineItem soLineItem = SaleOrderLineService.getOrderLineItem(so.getOrderLine()[0], client);
                if (soLineItem == null) {
                    correct = false;
                    break;
                } else {
                    odoo = soLineItem.getName();
                    correct = true;
                }
            } else {
                correct = false;
                break;
            }
        }
        if (correct) {
            correct = productLink.contains(odoo);
        }
        assertThat(correct).isEqualTo(true);
    }


    public void updateOrderLineItemByVariantNameAndQuantity(SaleOrder order, String variant, int quantity, int newQuantity, int newPrice, Client client) throws MalformedURLException, XmlRpcException {
        int id = order.getId();
        int[] lineItemIds = order.getOrderLine();
        for (int lIId : lineItemIds) {
            SaleOrderLineItem saleOrderLineItem = SaleOrderLineService.getOrderLineItem(lIId, client);
            int actQuantity = saleOrderLineItem.getProduct_uom_qty();
            String actVariantName = saleOrderLineItem.getProductName();

        if (actVariantName.equalsIgnoreCase(variant) && actQuantity == quantity) {
                updateOrderLineItem(id, saleOrderLineItem, newQuantity, newPrice, client);
                break;
            }
        }

    }

    public void updateOrderLineItem(int idSO, SaleOrderLineItem soli, int newQuantity, int newPrice, Client client) throws XmlRpcException {
        soli.setPriceUnit(newPrice);
        soli.setProduct_uom_qty(newQuantity);
        Object result = SaleOrderLineService.updateOrderLineItem(soli, client);
        assertThat(result).isEqualTo("true");
    }

    public SaleOrder getSaleOrder(String quotationId, String email, Client client) throws XmlRpcException {
        List<SaleOrder> orders = SaleOrderService.findOrderByNameAndEmail(quotationId, email, client);
        return orders.get(0);
    }

    public SaleOrder getSaleOrderByUrlAndEmail(String url, String email, Client client) throws XmlRpcException {
        List<SaleOrder> orders = SaleOrderService.getSaleOrderByUrlAndEmail(url, email, client);
        return orders.size() > 0 ? orders.get(0) : new SaleOrder();
    }

    public void updateValidityDate(SaleOrder order, String validity, Client client) throws XmlRpcException {
        if (!validity.isEmpty()) {
            SaleOrderService.updateDate(order, validity, client);
        }
    }

    public void updateStatusOnQuotation(SaleOrder order, String status, Client client) throws XmlRpcException {
        SaleOrderService.updateStatus(order, status, client);
    }

    public void addOrderLineItemByVariantNameAndQuantity(SaleOrder order, String productName, String variant, int quantity, int price, Client client) throws XmlRpcException {
        int variantID = getVarriantID(productName, variant, client);

        int[] lineItemIds = order.getOrderLine();
        int sequence = 0;
        for (int lineItemId : lineItemIds) {
            SaleOrderLineItem saleOrderLineItem = SaleOrderLineService.getOrderLineItem(lineItemId, client);
            if (saleOrderLineItem != null) {
                if (sequence <= saleOrderLineItem.getSequence()) {
                    sequence = saleOrderLineItem.getSequence();
                }
            }
        }
        SaleOrderLineItem soli = new SaleOrderLineItem();
        soli.setPriceUnit(price);
        soli.setName(productName + "--" + variant);
        soli.setProduct_uom_qty(quantity);
        soli.setProductId(new Object[]{variantID, productName + " - " + variant});
        soli.setSequence(sequence++);

        String result = SaleOrderService.addNewItemToOrder(order, soli, client);
        assertThat(result).isEqualTo("true");


    }

    private int getVarriantID(String productName, String nameVariants, Client client) throws XmlRpcException {
        List<ProductVariant> productVariants = ProductService.searchProduct(productName, client);
        List attributeIds = ProductService.getListAtributeValueID(productVariants);

        int a = ProductService.getVariantID(productVariants, nameVariants, attributeIds, client);
        return a;
    }

    public String addPurchaseOrder(int partnerID, Client client) throws XmlRpcException {
        String result = PurchaseOrderService.addPurchaseOrder(partnerID, client);
        System.out.println(result);
        return result;
    }

    public void addPurchaseOrderLineItemByVariantNameAndQuantity(int purchaseID, String productName, String variant, int quantity, int price, Client client) throws XmlRpcException {
        int variantID = getVarriantID(productName, variant, client);

        PurchaseOrder po = PurchaseOrderService.findOrderByID(purchaseID, client).get(0);
        int[] lineItemIds = po.getOrderLine();
        int sequence = 10;
        for (int lineItemId : lineItemIds) {
            PurchaseOrderLineItem saleOrderLineItem = PurchaseOrderService.getOrderLineItem(lineItemId, client);
            if (saleOrderLineItem != null) {
                if (sequence <= saleOrderLineItem.getSequence()) {
                    sequence = saleOrderLineItem.getSequence();
                }
            }
        }
        PurchaseOrderLineItem poli = new PurchaseOrderLineItem();
        poli.setPrice_unit(price);
        poli.setName(productName + " - " + variant);
        poli.setProduct_qty(quantity);

        poli.setProductId(new Object[]{variantID, productName + " - " + variant});
        poli.setProduct_uom(new Object[]{1, "Unit(s)"});
        poli.setSequence(sequence + 1);
        poli.setState("draft");
        poli.setDate_planned(DateTimeUtil.getTimestamp());
        PurchaseOrderService.addNewItemToOrder(po, poli, client);
    }

    public void confirmPurchaseOrder(int purchaseID, Client client) throws XmlRpcException {
        client.button_confirm("purchase.order", asList(asList(purchaseID)));
    }

    public void assignOwner(String order, String email) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        int res_id = Integer.parseInt(PurchaseOrderService.getIdDO(order, email, client));
        StockPickingService.assignOwner(res_id, email, client);
    }

    public void validatePurchaseOrder(int pickingId, Client client) throws XmlRpcException {
        PurchaseOrderService.actionDone("stock.picking", asList(asList(pickingId)), client);
//        int result = StockImmediateTransferService.createStockImmediateTransfer(pickingId, client);
//        System.out.println(result);
//        waitABit(4000);
//        StockImmediateTransferService.processStockImmediateTransfer(result, client);
    }

    public void updateQuoteBaseOn(SaleOrder order, boolean basedOnTotal, Client client) throws XmlRpcException {
        SaleOrderService.updateQuoteBaseOn(order, basedOnTotal, client);
    }

    public void updatePurchaseOrderLeadTime(SaleOrder order, int purchaseOrderLeadTime, Client client) throws XmlRpcException {
        SaleOrderService.updatePurchaseOrderLeadTime(order, purchaseOrderLeadTime, client);
    }

    public void updateMinimumOrderQuantity(SaleOrder order, int minimumOrderQuantity, Client client) throws XmlRpcException {
        SaleOrderService.updateMinimumOrderQuantity(order, minimumOrderQuantity, client);
    }

    public void approveOrder(String order, String email) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        PurchaseOrderService.call_button(order, email, client, "button_approve");
    }

    public void readPODetail(String order, String email, String productItem, String quantity) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        PurchaseOrderService.getPurchaseOrderLineID(order, email, client);

    }

    public void sentByEmailQuotation(String order, String email) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        SaleOrderService.call_button(order, email, client, "action_quotation_send");
        SaleOrderService.create(order, email, client, "create");
    }

    public void cancelQuotation(String quotation, String email) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        SaleOrderService.call_button(quotation, email, client, "action_cancel");
    }

    public void deleteQuotation(String quotation, String email) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        SaleOrderService.call_button(quotation, email, client, "unlink");
    }

    public void verifyStatusOfOrderOnOdooIsQuotation(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        try {
            String actualStatus = SaleOrderService.findOrderByNameAndEmail(orderName, customer, client).get(0).getState();
            assertThat("draft").isEqualTo(actualStatus);
        } catch (Throwable t) {
            String actualStatus = SaleOrderService.findOrderByNameAndEmail(orderName, customer, client).get(0).getState();
            assertThat("draft").isEqualTo(actualStatus);
        }
    }

    public void verifyStatusOfOrderOnOdooIsNotExists(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        int size = SaleOrderService.findOrderByNameAndEmail(orderName, customer, client).size();
        assertThat(0).isEqualTo(size);
    }

    public void verifyStatusOfOrderOnOdooIsCanceled(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        String actualStatus = SaleOrderService.findOrderByNameAndEmail(orderName, customer, client).get(0).getState();
        assertThat("cancel").isEqualTo(actualStatus);
    }

    public void verifyStatusOfOrderOnOdooIsSalesOrder(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        String actualStatus = SaleOrderService.findOrderByNameAndEmail(orderName, customer, client).get(0).getState();
        assertThat("sale").isEqualTo(actualStatus);
    }

    public void verifyInfoOfOrderOnOdoo(String orderName, String productName, String description, String orderedQty) throws MalformedURLException, XmlRpcException {
//        Client client = getClient();
//        int idOrder = SaleOrderService.findOrderByName(orderName, client).get(0).getId();
//        int idOrderLine = SaleOrderService.readSaleOrderById(idOrder, client).get(0).getOrderLine()[0];
//        String productNameActual = ((ArrayList) SaleOrderLineService.getOrderLineItem(idOrderLine, client).getProductId()).get(1).toString();
//        String productDescriptionActual = SaleOrderLineService.getOrderLineItem(idOrderLine, client).getName();
//        int productOrderedQtyActual = SaleOrderLineService.getOrderLineItem(idOrderLine, client).getProduct_uom_qty();
//
//        assertThat(productName).isEqualTo(productNameActual);
//        assertThat(description).isEqualTo(productDescriptionActual);
//        assertThat(Integer.parseInt(orderedQty.split("\\.")[0])).isEqualTo(productOrderedQtyActual);
    }

    public void verifyStatusOfDeliveryOrderOnOdooIsReady(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        String actualStatus = StockPickingService.findDeliveryOrderByNameAndEmail(orderName, customer, client).get(0).getState();
        assertThat("assigned").isEqualTo(actualStatus);
    }

    public void verifyStatusOfDeliveryOrderOnOdooIsCanceled(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        String actualStatus = StockPickingService.findDeliveryOrderByNameAndEmail(orderName, customer, client).get(0).getState();
        assertThat("cancel").isEqualTo(actualStatus);
    }

    public void verifyStatusOfDeliveryOrderOnOdooIsWaiting(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        String actualStatus = StockPickingService.findDeliveryOrderByNameAndEmail(orderName, customer, client).get(0).getState();
        assertThat("confirmed").isEqualTo(actualStatus);
    }

    public void verifyStatusOfDeliveryOrderOnOdooIsNotExists(String orderName, String customer) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        int size = StockPickingService.findDeliveryOrderByNameAndEmail(orderName, customer, client).size();
        assertThat(0).isEqualTo(size);
    }

    public void verifyInfoOfDeliveryOrderOnOdoo(String orderName, String productName, String customerOrderNumber, String shippingCode, String trackingNumber, String initialDemand, String reserved, String done) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        int idDeliveryOrder = StockPickingService.findDeliveryOrderByName(orderName, client).get(0).getId();
        int moveIdsWithoutPackage = StockPickingService.readStockPickingById(idDeliveryOrder, client).get(0).getMoveIdsWithoutPackage()[0];
        StockMove readStockMove = StockMoveService.readStockMove(moveIdsWithoutPackage, client).get(0);

        String productNameActual = ((ArrayList) readStockMove.getProductId()).get(1).toString();
        String customerOrderNumberActual = readStockMove.getCustomerOrderNumber();
        String shippingCodeActual = readStockMove.getX_shipping_method();
        String trackingNumberActual = readStockMove.getX_tracking_number();
        int initialDemandActual = readStockMove.getProductUomQty();
        int reservedActual = readStockMove.getReservedAvailability();
        int doneActual = readStockMove.getQuantityDone();

        assertThat(productName).isEqualTo(productNameActual);

        if ("".equals(customerOrderNumber)) {
            assertThat(customerOrderNumberActual).isEqualTo("false");
        }

        assertThat(shippingCode).isEqualTo(shippingCodeActual);

        if ("".equals(trackingNumber)) {
            assertThat(trackingNumberActual).isEqualTo("false");
        }

        assertThat(Integer.parseInt(initialDemand.split("\\.")[0])).isEqualTo(initialDemandActual);
        assertThat(Integer.parseInt(reserved.split("\\.")[0])).isEqualTo(reservedActual);
        assertThat(Integer.parseInt(done.split("\\.")[0])).isEqualTo(doneActual);
    }

//    public void doneDOPurchaseOrder(String purchaseOrder, String owner, String lineItem, String variant, String quantity) throws MalformedURLException, XmlRpcException {
//        Client client = getClient();
//        int purchaseOrderId = PurchaseOrderService.getIdPurchaseOrder(purchaseOrder, owner, client);
//        int stockPickingId = StockPickingService.getIdDO(purchaseOrder, owner, client);
//        validatePurchaseOrder(stockPickingId, client);
//        verifyStatusDO(stockPickingId, owner, client);
//    }

    public void verifyStatusDO(int stockPickingId, String owner, Client client, String state) throws XmlRpcException {
        String statusDO = StockPickingService.getStatusDO(stockPickingId, owner, client);
        assertThat(statusDO).isEqualTo(state);
    }


    public void verifyStatusQuotation(String quotationID, String statusQuotation, String email) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        List<SaleOrder> orders;
        orders = SaleOrderService.findOrderByNameAndEmail(quotationID, email, client);
        String actStatus = orders.get(0).getState();
        assertThat(actStatus).isEqualTo(statusQuotation);
        String actEmail = orders.get(0).getPartnerEmail();
        assertThat(actEmail).isEqualTo(email);
    }

    public void verifyProductOnOdoo(String productNameOdoo, int countProductOdoo) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        List<Product> product;
        product = ProductService.findProductByName(productNameOdoo, client);
        System.out.println(product);
        String actProduct = product.get(0).getName();
        assertThat(actProduct).isEqualTo(productNameOdoo);
        int size = product.size();
        assertThat(size).isEqualTo(countProductOdoo);
    }

    public void cancelDOPurchaseOrder(String purchaseOrder, String owner, String state) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        int stockPickingId = StockPickingService.getIdDO(purchaseOrder, owner, client);
        StockPickingService.call_button(stockPickingId, owner, "action_cancel", client);
        verifyStatusDO(stockPickingId, owner, client, state);
    }

    public void cancelPurchaseOrder(String purchaseOrder, String owner, String state) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        PurchaseOrderService.call_button(purchaseOrder, owner, client, "action_button_cancel");
    }


    public void logInfor(String quotationID) {
    }

    public void verrifyDOCreatedInOdoo(String orderNumber, int number) throws MalformedURLException, XmlRpcException {
        Client client = getClient();
        int countDO = StockPickingService.findDeliveryOrderByName(orderNumber, client).size();
        assertThat(countDO).isEqualTo(number);
    }

    public List<DeliveryCarrier> getShippingMethordElectronicList(String country, Client client) throws MalformedURLException, XmlRpcException {
        return DeliveryCarrierService.getShippingMethordElectronicList(country, client);
    }

    public List<DeliveryCarrier> getShippingMethordStandardList(String country, Client client) throws MalformedURLException, XmlRpcException {
        return DeliveryCarrierService.getShippingMethordStandardList(country, client);
    }

    public void verifyCountry(String country) {
        odooPage.verifyCountry(country);
    }

    public void verifyShippingMethodEletronicList(String orderName, List<DeliveryCarrier> shippingMethodList) {
        List<String> act = odooPage.getShippingMethodElectronicList(orderName);
        List<String> exp = new ArrayList<>();
        for (DeliveryCarrier row : shippingMethodList) {
            exp.add(row.getName());
        }
        assertThat(act).isEqualTo(exp);
    }

    public void verifyStatusDOInOdo(String status, String orderName, Client client) throws MalformedURLException, XmlRpcException {
        List<StockPicking> stockPickingList = StockPickingService.findDeliveryOrderByName(orderName, client);
        String actstatus = "";
        for (StockPicking row : stockPickingList) {
            actstatus = row.getState();
        }
        assertThat(actstatus).isEqualTo(status);
    }

    public void verifyStatusAfSelectedOnQuotation(String itemStatus, String selected) {
        if (selected.equals("true"))
            Java6Assertions.assertThat(odooPage.getValueOnStatusQuotation()).isEqualToIgnoringCase(itemStatus);
    }

    @Step
    public void selectStatusOnQuotation(String itemStatus) {
        odooPage.selectStatusOnQuotation(itemStatus);
        verifyStatusAfSelectedOnQuotation(itemStatus, "true");
    }

    @Step
    public void setUnAvaliableProductOdoo(int productId, boolean status, Client client) throws XmlRpcException {
        productService.setUnAvaliableProductOdoo(productId, status, client);
    }

    public void cancelQuotationByID(int quotationID, Client client) throws XmlRpcException {
        SaleOrderService.callButtonByID(quotationID, "action_cancel", client);
    }

    public void deleteQuotationByID(int id, Client client) throws XmlRpcException {
        SaleOrderService.callButtonByID(id, "unlink", client);
    }

    public void deleteProductOdoo(int id, Client client) throws XmlRpcException {
        ProductService.deleteProductOdoo(id, "unlink", client);
    }

    public void automaticCalculateCost(SaleOrder order, boolean automaticCalculateCost, Client client) throws XmlRpcException {
        SaleOrderService.automaticCalculateCost(order, automaticCalculateCost, client);
    }

    public void updateProductCost(int id, double productCost, Client client) throws XmlRpcException {
        saleOrderLineService.updateProductCost(id, productCost,"x_product_cost", client);
    }

    public void updateDomesticShipping(int id, double domesticShipping, Client client) throws XmlRpcException {
        saleOrderLineService.updateProductCost(id, domesticShipping,"x_domestic_shipping", client);
    }

    public void updateRMBLineItem(int idSO, SaleOrderLineItem soli, int newQuantity, int RMB, Client client) throws XmlRpcException {
        soli.setX_price_unit_rmb(RMB);
        soli.setProduct_uom_qty(newQuantity);
        Object result = SaleOrderLineService.updateOrderLineItem(soli, client);
        assertThat(result).isEqualTo("true");
    }

    public void updateRMB(SaleOrder order, int newQuantity, int RMB, Client client) throws XmlRpcException {
        int id = order.getId();
        int[] lineItemIds = order.getOrderLine();
        for (int lIId : lineItemIds) {
            SaleOrderLineItem saleOrderLineItem = SaleOrderLineService.getOrderLineItem(lIId, client);
            updateRMBLineItem(id, saleOrderLineItem, newQuantity, RMB, client);
        }
    }

    public void checkAvailabilityDOout(int stockPickingId, Client client) throws XmlRpcException{
        StockPickingService.call_button(stockPickingId, "", "action_assign", client);
    }
}