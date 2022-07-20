package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrderLineItem extends OdooModel {
    private int productId;
    private int id;
    private int sequence;

    @JsonIgnore
    private String productName;
    private String name;
    private int price_unit;
    private int product_qty;
    private String date_planned;
    private String state;
    private String writeDate;
    private int product_uom;

    public int getProductId() {
        return productId;
    }

    public void setProductId(Object[] productId) {
        if (productId.length > 0) {
            this.productId = (int) productId[0];
        }
        if (productId.length > 1) {
            this.productName = productId[1].toString();
        }
    }

    public void setProduct_uom(Object[] product_uom) {
        if (product_uom.length > 0) {
            this.product_uom = (int) product_uom[0];
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getProductName() {
        return productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(int price_unit) {
        this.price_unit = price_unit;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public String getDate_planned() {
        return date_planned;
    }

    public void setDate_planned(String date_planned) {
        this.date_planned = date_planned;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public int getProduct_uom() {
        return product_uom;
    }
}