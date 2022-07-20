package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockMove implements Serializable {
    private int id;
    private String customer_order_number;
    private String x_shipping_method;
    private String x_tracking_number;
    private int product_uom_qty;
    private int reserved_availability;
    private int quantity_done;
    private Object product_id;


    public String getCustomerOrderNumber() {
        return customer_order_number;
    }

    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customer_order_number = customerOrderNumber;
    }

    public int getProductUomQty() {
        return product_uom_qty;
    }

    public void setProductUomQty(int productUomQty) {
        this.product_uom_qty = productUomQty;
    }

    public int getQuantityDone() {
        return quantity_done;
    }

    public void setQuantityDone(int quantityDone) {
        this.quantity_done = quantityDone;
    }

    public int getReservedAvailability() {
        return reserved_availability;
    }

    public void setReservedAvailability(int reservedAvailability) {
        this.reserved_availability = reservedAvailability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Object getProductId() {
        return product_id;
    }

    public void setProductId(Object productId) {
        this.product_id = productId;
    }

    public String getX_shipping_method() {
        return x_shipping_method;
    }

    public void setX_shipping_method(String x_shipping_method) {
        this.x_shipping_method = x_shipping_method;
    }

    public String getX_tracking_number() {
        return x_tracking_number;
    }

    public void setX_tracking_number(String x_tracking_number) {
        this.x_tracking_number = x_tracking_number;
    }
}
