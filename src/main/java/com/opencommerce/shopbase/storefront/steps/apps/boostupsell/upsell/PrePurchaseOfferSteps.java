package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.upsell.PrePurchaseOfferPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrePurchaseOfferSteps extends CommonSteps {
    PrePurchaseOfferPage prePurchaseOfferPage;

    public void verifyOfferMessageShown(String sMessage, boolean show) {
        prePurchaseOfferPage.verifyOfferMessageShown(sMessage, show);

    }

    public void waitUntilBtnAddToCartShown() {
        prePurchaseOfferPage.waitUntilBtnAddToCartShown();
    }

    public void verifyProductOffers(String sUpsellProduct) {
        if (!sUpsellProduct.isEmpty()) {
            String[] items = sUpsellProduct.split(",");
            for (String item : items) {
                verifyProductOnPopupUpsell(item);
            }
        }
    }

    public void verifyProductOnPopupUpsell(String item) {
        String[] data = item.split(">");
        int size = data.length;
        if (size == 3) {
            selectVariant(data[0], data[1]);
            verifyPrice(data[0], data[2]);
        } else if (size == 2) {
            verifyPrice(data[0], data[1]);
        } else prePurchaseOfferPage.verifyProduct(data[0]);

    }

    public void verifyPrice(String productName, String price) {
        prePurchaseOfferPage.verifyPrice(productName, price);
    }

    public void selectVariant(String productName, String variantProduct) {
        if (!variantProduct.isEmpty()) {
            prePurchaseOfferPage.selectVariant(productName, variantProduct);
        }
    }

    public void addProductOnUpsellPopup(String sProductAdded) {
        if (!sProductAdded.isEmpty()) {
            String[] products = sProductAdded.split(",");
            for (String item : products) {
                String[] data = item.split(">");
                String nameproduct = data[0];
                prePurchaseOfferPage.addProductInOffer(nameproduct);
            }
        }
    }

    public void clickCheckOutInUpsellPopup() {
        prePurchaseOfferPage.clickBtnCheckoutOnPopup();
    }

    public void closePopupUpsell() {
        prePurchaseOfferPage.clickClosePopupPrePurchase();
    }

    public void verifyNumberOfProductUpsell(String numberProduct) {
        if (!numberProduct.isEmpty()) {
            int number = Integer.parseInt(numberProduct);
            prePurchaseOfferPage.verifyNumberOfProductOnUpsell(number);
        }
    }

    public void verifyPopupPrePurchaseShown(boolean isShow) {
        prePurchaseOfferPage.verifyPopupPrePurchase(isShow);
    }

    public void inputCustomOption(String custom) {
        if (!custom.isEmpty()) {
            String[] datacustom = custom.split(">");
            String product = datacustom[0];
            String[] customData = datacustom[1].split(":");
            String labelOption = customData[0];
            String valueOption = customData[1];
            prePurchaseOfferPage.inputCustomOptionProduct(product, labelOption, valueOption);
        }
    }

    @Step
    public void clickAddToCart(){
        prePurchaseOfferPage.clickAddToCart();
    }

    public List<String> getListProduct() {
        return prePurchaseOfferPage.getListProductOnPopupPrePurchase();
    }

    @Step
    public void logInfor(List<String> listProd) {
        System.out.println(listProd);
    }

    public void addToCartProduct1st() {
        prePurchaseOfferPage.addToCartProduct1st();
    }

    public void verifyDiscountValueSmartOffer(String discount) {
        if (!discount.isEmpty()) {
            List<String> prods = prePurchaseOfferPage.getListProductOnPopupPrePurchase();
            for (String pro : prods) {
                float price = prePurchaseOfferPage.getSalePrice(pro);
                if (price != 0) {
                    float priceCompare = prePurchaseOfferPage.getComparePrice(pro);
                    float actPercentag = caculateDiscountPercentag(price, priceCompare);
                    assertThat(actPercentag).isEqualTo(Float.parseFloat(discount));
                }
            }

        }
    }

    public int caculateDiscountPercentag(float price, float priceCompare) {
        double discountPer = ((priceCompare - price) * 100) / priceCompare;
        return (int) Math.round(discountPer);
    }

    @Step
    public void clickEditOffer(String nameOffer){
        prePurchaseOfferPage.clickEditOffer(nameOffer);
    }
}
