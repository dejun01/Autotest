package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.opencommerce.odoo.Client;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrder implements Serializable {
    private String writeDate;
    private boolean routeId;
    private boolean productImage;
    private String name;
    private int[] orderLine;
    private int id;
    private int[] picking_ids;


    public int[] getPicking_ids() {
        return picking_ids;
    }

    public void setPicking_ids(int[] picking_ids) {
        this.picking_ids = picking_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public boolean isRouteId() {
        return routeId;
    }

    public void setRouteId(boolean routeId) {
        this.routeId = routeId;
    }

    public boolean isProductImage() {
        return productImage;
    }

    public void setProductImage(boolean productImage) {
        this.productImage = productImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(int[] orderLine) {
        this.orderLine = orderLine;
    }
}
