package com.opencommerce.odoo.models;


public class DeliveryCarrier {
    private int id;
    private String sequence;
    private String name;
    private float fixed_price;
    private float amount;
    private String x_estimated_delivery;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getFixedPrice() {
        return fixed_price;
    }
    public void setFixedPrice(float fixed_price) {
        this.fixed_price = fixed_price;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getEstimatedDelivery() {
        return x_estimated_delivery;
    }
    public void setEstimatedDelivery(String x_estimated_delivery) {
        this.x_estimated_delivery = x_estimated_delivery;
    }
}
