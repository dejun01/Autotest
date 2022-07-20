package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    private boolean active;
    private List<Integer> attribute_value_ids;
    private boolean barcode;
    private boolean default_code;
    private int id;
    private int lst_price;
    private String name;
    private double price;

    public List<Integer> getAttribute_value_ids() {
        return attribute_value_ids;
    }

    public void setAttribute_value_ids(List<Integer> attribute_value_ids) {
        this.attribute_value_ids = attribute_value_ids;
    }

    private String[] product_tmpl_id;
    private int qty_available;
    private double standard_price;
    private double virtual_available;
    private String type;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

   public boolean isBarcode() {
        return barcode;
    }

    public void setBarcode(boolean barcode) {
        this.barcode = barcode;
    }

    public boolean isDefault_code() {
        return default_code;
    }

    public void setDefault_code(boolean default_code) {
        this.default_code = default_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLst_price() {
        return lst_price;
    }

    public void setLst_price(int lst_price) {
        this.lst_price = lst_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getProduct_tmpl_id() {
        return product_tmpl_id;
    }

    public void setProduct_tmpl_id(String[] product_tmpl_id) {
        this.product_tmpl_id = product_tmpl_id;
    }

    public int getQty_available() {
        return qty_available;
    }

    public void setQty_available(int qty_available) {
        this.qty_available = qty_available;
    }

    public double getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(double standard_price) {
        this.standard_price = standard_price;
    }

    public double getVirtual_available() {
        return virtual_available;
    }

    public void setVirtual_available(double virtual_available) {
        this.virtual_available = virtual_available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
