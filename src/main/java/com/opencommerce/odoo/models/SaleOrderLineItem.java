package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SaleOrderLineItem extends OdooModel {
    private int productId;
    private int id;
    private int sequence;

    @JsonIgnore
    private String productName;
    private String name;
    private int price_unit;
    private int product_uom_qty;
    private int product_qty;
    private int x_price_unit_rmb;

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public int getProduct_uom_qty() {
        return product_uom_qty;
    }

    public  int getX_price_unit_rmb(){
        return x_price_unit_rmb;
    }

    public void setX_price_unit_rmb(int RMB){
        this.x_price_unit_rmb = RMB;
    }
    public void setProduct_uom_qty(int product_uom_qty) {
        this.product_uom_qty = product_uom_qty;
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

    public int getPriceUnit() {
        return price_unit;
    }

    public void setPriceUnit(int priceUnit) {
        this.price_unit = priceUnit;
    }

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
}
