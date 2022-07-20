package com.opencommerce.shopbase.dashboard.dashboard.steps.models.Collection;

public class Collection {
    public String body_html;
    public int collection_availability;
    public String collection_type;
    public String created_at;
    public boolean disjunctive;
    public String handle;
    public Long id;
    public Image image;
    public Object metafields;
    public int processed_at;
    public int products_manually_sorted_count;
    public boolean published;
    public String published_at;
    public String published_scope;
    public Object rules;
    public String sort_order;
    public String template_suffix;
    public String title;
    public String updated_at;

    public void normalizeCollection() {
        this.id = 0L;
        this.created_at = "";
        this.id = 0L;
        this.published_at = "";
        this.updated_at = "";
    }
}
