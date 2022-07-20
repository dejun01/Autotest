package com.opencommerce.shopbase.dashboard.dashboard.steps.models.Themes;

public class Themes {
    public boolean active;
    public String created_at;
    public String current_style;
    public String desktop_image;
    public int id;
    public String latest_version;
    public String mobile_image;
    public String name;
    public Object settings_data;
    public Object settings_schema;
    public int shop_id;
    public int theme_id;
    public String updated_at;
    public int version_id;
    public Object version_settings_data;

    public void normalizeThemes() {
        this.created_at = "";
        this.id = 0;
        this.shop_id = 0;
        this.created_at = "";
        this.version_id = 0;
    }

}
