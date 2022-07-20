package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview;

import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.quickview.QuickViewPage;

public class QuickViewSteps {
    QuickViewPage quickViewPage;

    public void verifyQuickViewShown() {
        quickViewPage.verifyQuickViewShown();
    }

    public void verifyTitleProduct(String title) {
        if (!title.isEmpty()) {
            quickViewPage.verifyTitleQuickView(title);
        }
    }

    public void verifyPriceProduct(String price) {
        if (!price.isEmpty()) {
            quickViewPage.verifyPriceProductOnQuickView(price);
        }
    }

    public void verifyVariantTitle(String variants) {
        if (!variants.isEmpty()) {
            String[] var = variants.split(",");
            for (String item : var) {
                quickViewPage.verifyVariantTitle(item);
            }
        }
    }

    public void verifyThumdnailShow(boolean isShowThumdNail) {
        quickViewPage.verifyThumailShow(isShowThumdNail);
    }

    public void verifyBundleShowOnQuickview(boolean isShowBundle) {
        quickViewPage.verifyBundleShowOnQuickView(isShowBundle);
    }

    public void verifyRecommendForYou(boolean isShowRecommendForYou) {
        quickViewPage.verifyRecommendProduct(isShowRecommendForYou);
    }

    public void clickAddToCart() {
        quickViewPage.clickAddToCart();
    }

    public void verifyValidateField(String textValidate) {
        String[] texts = textValidate.split(",");
        for (String str : texts) {
            quickViewPage.verifyTextPresent(str, true);
        }
    }

    public void closePopupQuickview() {
        quickViewPage.closeQuickview();

    }
    public void verifyQuickviewShown(boolean isShow){

        quickViewPage.verifyQuickviewShown(isShow);
    }

    public void verifyCloseQickView(){
        quickViewPage.verifyCloseQickView();
    }
}
