package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleOrder implements Serializable {
    private String writeDate;
    private boolean routeId;
    private boolean productImage;
    private String name;
    private String[] partnerId;
    private int[] orderLine;
    private int id;
    private String state;
    private int orderId;

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

    public String[] getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String[] partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerEmail() {
        if (partnerId.length >= 2) {
            return partnerId[1];
        }
        return null;
    }

    public int[] getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(int[] orderLine) {
        this.orderLine = orderLine;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
