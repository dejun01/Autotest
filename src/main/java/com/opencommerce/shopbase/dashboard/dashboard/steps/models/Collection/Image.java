package com.opencommerce.shopbase.dashboard.dashboard.steps.models.Collection;

public class Image {
    public String alt;
    public String created_at;
    public int height;
    public String src;
    public int width;

    public void normalizeImage() {
        this.created_at = "";
    }
}
