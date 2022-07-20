package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.CartPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.listProductInCart;
import static com.opencommerce.shopbase.OrderVariable.themeStyle;
import static java.util.Arrays.asList;

public class CartSteps {
    CartPage cartPage;
    @Steps
    ProductSteps productSteps;

    @Step
    public void verifyCartPage() {
        cartPage.verifyCartPageShow();
    }

    @Step
    public void clickButtonCheckoutOnCart() {
        cartPage.clickCheckoutOnCart();

    }

    public void verifyProductOnCart(String product, String variant, String customOption, String price, String quantity) {
        if (!product.isEmpty()) {
            cartPage.verifyCartItems(product, price);
            if (!variant.isEmpty()) {
                String[] var = variant.split(">");
                cartPage.verifyCartItemsWithVariant(product, var[1], quantity);
            }
            if (!customOption.isEmpty()) {
                String[] option = customOption.split(",");
                for (String opt : option) {
                    cartPage.verifyCartItemsWithVariant(product, opt, quantity);
                }
            }
        }
    }

    public void openCartPage() {
        String shop = LoadObject.getProperty("shop");
        cartPage.navigateToUrl("https://" + shop + "/cart");
    }

    public String getProductQty(int index) {
        return cartPage.getProductQty(index);
    }

    public String getProductName(int index) {
        return cartPage.getProductName(index);
    }

    public String getProductPrice(int index) {
        return cartPage.getProductPrice(index);
    }

    public String getProductVariant(int index) {
        return cartPage.getProductVariant(index);
    }

    public int countProduct() {
        return cartPage.countProduct();
    }


    public HashMap<String, List<String>> getListProductFromCart() {
        HashMap<String, List<String>> listProduct = new HashMap<>();
        int count = countProduct();
        System.out.println(count);
        for (int i = 1; i <= count; i++) {
            String productName = getProductName(i).toUpperCase();
            String productPrice = getProductPrice(i);
            String productVariant = getProductVariant(i);
            String quantity = getProductQty(i);
            if (!themeStyle.equalsIgnoreCase("bassy")) {
                productName = productName.toUpperCase();
            }
            if (!productVariant.isEmpty()) {
                listProduct.put(productName + ":" + productVariant, asList(quantity, productPrice));
            } else {
                listProduct.put(productName, asList(quantity, productPrice));
            }
        }
        return listProduct;
    }

    public void verifyDiscountCart(String discountApply) {
        if (!discountApply.isEmpty()) {
            String[] discount = discountApply.split(">");
            String discountCode = discount[0];
            String discountValue = discount[1];
            cartPage.verifyDiscountCode(discountCode, discountValue);
        }
    }

    public void verifySubTotalCart(String subtotal) {
        cartPage.verifySubtotalonCartPage(subtotal);
    }

    public void setQuantityProductOnCart(String product, String variant, String quantity) {
        cartPage.setQuantityProductOnCart(product, variant, quantity);
    }

    public void verifySubToltalOnCart(String subtotal) {
        cartPage.waitForEverythingComplete();
        cartPage.verifySubtotalonCartPage(subtotal);
    }

    public void verifyMessageDiscountOnCartWithApp(String messageDiscount) {
        cartPage.verifyMessageDiscountCart(messageDiscount);
    }

    public void verifyDiscountCode(String discount) {
        if (!discount.isEmpty()) {
            cartPage.verifyDiscountCodeOnCart(discount);
        }
    }

    @Step
    public void verifyCartType(String cartType) {
        switch (cartType) {
            case "Page":
                productSteps.addToCart();
                cartPage.verifyOpenPage("cart");
                break;
            case "Checkout":
                productSteps.clickBuyNow();
                cartPage.verifyOpenPage("checkout");
                break;
            case "Refresh":
                productSteps.addToCart();
                cartPage.verifyMiniCartShow(false);
                cartPage.verifyOpenPage("product");
                break;
            default:
                productSteps.addToCart();
                cartPage.verifyMiniCartShow(true);
                cartPage.verifyOpenPage("product");
        }
    }

    @Step
    public void verifyShowCheckoutBtnInMIniCart(Boolean isShow, String cartType) {
        if (cartType.equals("Checkout")) {
            openCartPage();
        }
        cartPage.openMiniCart();
        cartPage.verifyShowCheckoutBtnInMIniCart(isShow);
    }

    public void clickButtonCheckout() {
        cartPage.clickButtonCheckout();
    }
}


