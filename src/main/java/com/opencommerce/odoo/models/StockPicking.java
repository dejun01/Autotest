package com.opencommerce.odoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPicking implements Serializable {
    private String writeDate;
    private String name;
    private int id;
    private int res_id;
    private int[] moveIdsWithoutPackage;
    private int[] moveLineIdsWithoutPackage;
    private String state;


    public int[] getMoveIdsWithoutPackage() {
        return moveIdsWithoutPackage;
    }

    public void SetMoveIdsWithoutPackage(int[] moveIdsWithoutPackage) {
        this.moveIdsWithoutPackage = moveIdsWithoutPackage;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReId() {
        return res_id;
    }

    public void setResId(int res_id) {
        this.res_id = res_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public int[] getMoveLineIdsWithoutPackage() {
        return moveLineIdsWithoutPackage;
    }

    public void SetMoveLineIdsWithoutPackage(int[] moveLineIdsWithoutPackage) {
        this.moveLineIdsWithoutPackage = moveLineIdsWithoutPackage;
    }


}
