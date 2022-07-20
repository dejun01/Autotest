package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.GlobalSettingsPage;

public class GlobalSettingsSteps {
    GlobalSettingsPage globalSettingsPage;

    public void checkEnableSort(boolean enableSort) {
        globalSettingsPage.checkCheckboxWithLabel("Enable sort in search results", enableSort);
    }

    public void selectLogic(String logic) {
        globalSettingsPage.selectDdlWithLabel("Default logic",logic);
    }

    public void checkSortProduct(boolean sortProduct) {
        globalSettingsPage.checkCheckboxWithLabel("Enable sort in collection list", sortProduct);
    }

    public void checkShowSale(boolean showSale) {
        globalSettingsPage.checkCheckboxWithLabel("Show sales banners",showSale);
    }

    public void checkShowAddToCart(boolean showAddToCart) {
        globalSettingsPage.checkCheckboxWithLabel("Show Add-to-cart button",showAddToCart);
    }

    public void checkShowTopIcon(boolean showTopIcon) {
        globalSettingsPage.checkCheckboxWithLabel("Enable back to top icon",showTopIcon);
    }

    public void selectPagination(String pagination) {
        globalSettingsPage.selectDdlWithLabel("Pagination",pagination);
    }

    public void selectImgDisplay(String imgDisplay) {
        globalSettingsPage.selectDdlWithLabel("Product image display",imgDisplay);
    }

    public void selectContentAlign(String contentAlign) {
        globalSettingsPage.selectDdlWithLabel("Content alignment",contentAlign);
    }

    public void selectShape(String shape) {
        globalSettingsPage.selectShape(shape);
    }
}
