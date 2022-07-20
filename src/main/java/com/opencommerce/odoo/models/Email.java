package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Email implements Serializable {

    private List<Integer> order_line;
    private String name;
    private String state;

    private List<String> template_id;
    private String display_name;

    public List<Integer> getOrder_line() {
        return order_line;
    }

    public void setOrder_line(List<Integer> order_line) {
        this.order_line = order_line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private int[] attachment_ids;
    private String body;
    private int id;
    private String subject;
    private String composition_mode;

    public String getComposition_mode() {
        return composition_mode;
    }

    public void setComposition_mode(String composition_mode) {
        this.composition_mode = composition_mode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int[] getAttachment_ids() {
        return attachment_ids;
    }

    public void setAttachment_ids(int[] attachment_ids) {
        this.attachment_ids = attachment_ids;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(List<String> template_id) {
        this.template_id = template_id;
    }

}
