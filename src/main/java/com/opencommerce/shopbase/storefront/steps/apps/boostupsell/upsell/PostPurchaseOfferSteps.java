package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell;

import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.upsell.PostPurchaseOfferPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.OrderVariable.productVariant;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PostPurchaseOfferSteps {
    PostPurchaseOfferPage postPurchasePage;

    @Step
    public void verifyOfferPostpurchaseShow(boolean isShow) {
        postPurchasePage.verifyOfferPostPurchasePopup(isShow);

    }

    @Step
    public void verifyOfferTitle(String title) {
        if (!title.isEmpty())
            postPurchasePage.verifyOfferTitle(title);
    }

    @Step
    public void verifyMessage(String mess) {
        if (!mess.isEmpty())
            postPurchasePage.verifyMessage(mess);
    }

    @Step
    public void clickAddProductInPostPurchaseToOrder(int index) {
        postPurchasePage.clickToAddToOrder(index);
        postPurchasePage.waitUntilInvisibleLoadingDoneComplete(30);
        postPurchasePage.waitForPageLoad();

    }

    @Step
    public void finishPaymentPaypal() {
        postPurchasePage.waitUntilInvisibleLoading(20);
        clickConfirmPaypalBtn();

    }

    @Step
    public void clickConfirmPaypalBtn() {
        postPurchasePage.clickConfirmPaypalBtn();
        postPurchasePage.waitUntilInvisibleLoading(10);
    }

    @Step
    public void clickONNoThankByTitle(String title) {
        postPurchasePage.clickOnNoThankByTitle(title);
    }

    public void verifyNumberProductOnPostPurchase(String numberProduct) {
        if (!numberProduct.isEmpty()) {
            int actualNumber = postPurchasePage.countNumberOfProductOnUpsell();
            assertThat(actualNumber).isEqualTo(Integer.parseInt(numberProduct));
        }
    }

    public void selectVariantByIndex(String variant, int index) {
        if (!variant.isEmpty())
            postPurchasePage.selectVariantByIndex(variant, index);

    }

    public void inputCustomOptionByIndex(String customOption, int index) {
        if (!customOption.isEmpty()) {
            postPurchasePage.clickPersonalizationInformation(index);
            String[] cus = customOption.split(";");
            for (String items : cus) {
                String[] item = items.split(":");
                String option = item[0];
                String value = item[1];
                postPurchasePage.inputCustomOption(option, value, index);
            }

        }
    }

    @Step
    public void clickOnPassPostPurchase() {
        postPurchasePage.clickOnPassPostPurchase();
    }


    public List<Object> getProductInforByIndex(int index) {
        List<Object> productDetail = new ArrayList<>();
        String productName = postPurchasePage.getProductNameByIndex(index);
        String originalPrice = postPurchasePage.getProductOriginalPriceByIndex(index);
        String productPrice = postPurchasePage.getProductPriceByIndex(index);
        String blockSave = postPurchasePage.getBlockSaveValueByIndex(index);

        productDetail.add(productName);
        if (!originalPrice.isEmpty()) {
            productDetail.add(Float.parseFloat(originalPrice));
        }

        productDetail.add(Float.parseFloat(productPrice));
        if (!blockSave.isEmpty())
            productDetail.add(blockSave);

        return productDetail;
    }

    public void addProductInPostPurchaseToOrder(String productNameAndVariant, String customOption) {
        String productName = productNameAndVariant;
        String productPrice = "";
        String productVariant = "";
        String quantity = "1";
        if (productNameAndVariant.contains(":")) {
            String prod[] = productName.split(":");
            productName = prod[0];
            productVariant = prod[1];
        }
        int index = getIndexOfProductInOffer(productName);

        selectVariantByIndex(productVariant, index);
        inputCustomOptionByIndex(customOption, index);
        productPrice = postPurchasePage.getProductPriceByIndex(index);
        clickAddProductInPostPurchaseToOrder(index);

        paidTotalAmtByPaypal = productPrice;

//        productListAdded.put(productName.toUpperCase(), asList(productVariant, quantity, productPrice));
        if (!productVariant.isEmpty()) {
            productListAdded.put(productName + ":" + productVariant, asList(quantity, productPrice));

        } else {
            productListAdded.put(productName, asList(quantity, productPrice));
        }
    }

    public void removeProductInPostPurchaseFromOrder(String postPurchaseItemName) {
        productListAdded.remove(postPurchaseItemName);
    }



    private int getIndexOfProductInOffer(String productName) {
        return postPurchasePage.getIndexOfProductInOffer(productName);
    }


    public int countNumberOfProductOnUpsell() {
        return postPurchasePage.countNumberOfProductOnUpsell();
    }

    public void refreshPage() {
        postPurchasePage.refreshPage();
    }

    public void verifyMessageSuccesOrder() {
        postPurchasePage.verifyMgsSuccessOrder();
    }

    public void clickClosePopup() {
        postPurchasePage.clickClosePopup();
    }

    public String getStatusOrderAPI(int orderID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/orders/" + orderID + ".json" + "?access_token=" + accessToken;
        JsonPath api = postPurchasePage.getAPI(url);
        String financialStatus = postPurchasePage.getData(api, "order.financial_status").toString();
        return financialStatus;
    }

    public String getOriginPrice(String productName) {
        return postPurchasePage.getOriginPrice(productName);
    }

    public String getPriceAfterDiscount(String productName) {
        return postPurchasePage.getPriceAfterDiscount(productName);
    }

    public double getDiscountAmount(String productName) {
        double _originalPrice = 0.00d;
        double _priceAfterDiscount = 0.00d;
        if (!getOriginPrice(productName).isEmpty() && !getPriceAfterDiscount(productName).isEmpty()) {
            _originalPrice = Double.parseDouble((getOriginPrice(productName).split("(?<=\\D)(?=\\d)", 2)[1]).split("\\s",0)[0]);
            _priceAfterDiscount = Double.parseDouble((getPriceAfterDiscount(productName).split("(?<=\\D)(?=\\d)", 2)[1]).split("\\s",0)[0]);
        }
        return _originalPrice - _priceAfterDiscount;
    }

    public void isShowPopup(Boolean isShow, String offerTitle) {
        postPurchasePage.isShowPopup(isShow, offerTitle);
    }

    public boolean isPPCShow() {
        return postPurchasePage.isPPCShow();
    }

    public void addProductPPC(String productName) {
        postPurchasePage.addProductPPC(productName);
    }
}
