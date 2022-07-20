package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductVariant implements Serializable {

    private List<Integer> attribute_value_ids;
    private int id;


    public List<Integer> getAttribute_value_ids() {
        return attribute_value_ids;
    }

    public void setAttribute_value_ids(List<Integer> attribute_value_ids) {
        this.attribute_value_ids = attribute_value_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
