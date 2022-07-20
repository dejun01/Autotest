package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.storefront.pages.shop.OrderSummaryPages;
import com.opencommerce.shopbase.storefront.pages.shop.ThankyouPage;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.OrderVariable.taxAmount;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class OrderSummarySteps {
    OrderSummaryPages orderSummaryPages;
    ThankyouPage thankyouPage;

    public void verifyCheckoutPage() {
        orderSummaryPages.verifyCheckoutPage();
    }

    @Step
    public void verifyDiscountApply(String offer_discount, String discountPrice) {
        if (!discountPrice.isEmpty()) {
            orderSummaryPages.verifyDiscountApply(offer_discount);
            String actualPrice = orderSummaryPages.getPriceDiscount();
            orderSummaryPages.comparePrice(actualPrice, discountPrice);
        }
    }

    @Step
    public void verifyTotalPriceOrder(String totalPrice) {
        if (!totalPrice.isEmpty()) {
            String actualPrice = orderSummaryPages.getTotalAmt();
            orderSummaryPages.comparePrice(actualPrice, totalPrice);
        }
    }

    public void verifyDiscountApplyOrder(String disCount_apply) {
        if (!disCount_apply.isEmpty()) {
            String[] discount = disCount_apply.split(">");
            String discountCode = discount[0];
            String discountValue = discount[1];
            verifyDiscountApply(discountCode, discountValue);
        }
    }

    @Step
    public void verifyProductsOnOrderSummay(String products) {
        if (!products.isEmpty()) {
            String[] product = products.split(",");
            for (String item : product) {
                String[] data = item.split(">");
                int size = data.length;
                if (size == 3) {
                    String nameproduct = data[0];
                    String variant = data[1];
                    String price = data[2];
                    verifyProductWithPrice(nameproduct, variant, price);
                } else {
                    String nameproduct = data[0];
                    String price = data[1];
                    verifyProductWithPrice(nameproduct, "", price);
                }
            }
        }
    }

    @Step
    public void verifyProductWithPrice(String nameproduct, String variant, String price) {
        orderSummaryPages.verifyProductOnSummary(nameproduct, variant, price);

    }

    @Step
    public void verifySubtotal(String subtotal) {
        if (!subtotal.isEmpty()) {
            String actualPrice = orderSummaryPages.getSubtotal();
            orderSummaryPages.comparePrice(actualPrice, subtotal);
        }

    }

    public void enterDiscount(String sDiscount) {
        if (!sDiscount.isEmpty())
            orderSummaryPages.enterInputFieldWithLabel("Enter your discount code here", sDiscount);
    }

    public void clickApplyBtn() {
        orderSummaryPages.clickBtn("Apply");
    }

    @Step
    public void verifyErrorMsg(String sExpected) {
        String[] errorMsgs = sExpected.split(",");
        for (String msg : errorMsgs) {
            orderSummaryPages.verifyErrorMsg(msg);
        }
    }

    public void verifyScreenCheckout() {
        orderSummaryPages.verifyScreenCheckout();
    }

    public void verifyDiscountAutomatic() {
        orderSummaryPages.verifyDiscountAutomatic();
    }

    public void verifyDiscountbyXgetY(String sDiscountCode, String sProduct, String sDiscountPercent) {
        float price = getPriceProduct(sProduct);
        float discount = price * (Float.parseFloat(sDiscountPercent)) / 100;
        String actDiscountCode = getDiscountProductReduction(sProduct);
        actDiscountCode.equalsIgnoreCase(sDiscountCode);
//        assertThat(actDiscountCode).contains(sDiscountCode);
        assertThat(Float.parseFloat(getDiscount())).isEqualTo(discount);
    }

    public float getPriceProduct(String sProduct) {
        return orderSummaryPages.getPriceProduct(sProduct);
    }

    public String getDiscountProductReduction(String sProduct) {
        return orderSummaryPages.getDiscountProductReduction(sProduct);
    }

    public String getDiscount() {
        return orderSummaryPages.getDiscount();
    }

    @Step
    public void enterDiscountCode(String discountCode) {
        if (!discountCode.isEmpty()) {
            orderSummaryPages.scrollToViewBlockDiscount();
            orderSummaryPages.enterInputFieldWithLabel("Enter your discount code here", discountCode);
            clickButtonApply();
        }
    }

    @Step
    public void clickButtonApply() {
        orderSummaryPages.clickBtn("Apply");
        orderSummaryPages.waitUntilApplyDiscountSucessfully();
    }

    @Step
    public float getTotalPrice() {
        return orderSummaryPages.getTotalPrice();
    }

    @Step
    public float getPrice(String sprice) {
        return Float.parseFloat(orderSummaryPages.price(sprice));
    }

    @Step
    public float getSubTotalPrice() {
        return orderSummaryPages.getSubTotalPrice();
    }

    @Step
    public String getSubTotalPriceStr() {
        return orderSummaryPages.getSubTotalPriceStr();
    }

    @Step
    public void verifyPriceAfterApplyDiscount(float totalPrice) {
        float actualTotal = Math.round(getTotalPrice() * 100) / 100f;
        System.out.println(actualTotal);
        assertThat(actualTotal).isEqualTo(Math.round(totalPrice * 100) / 100f);
    }

    public void verifyDiscount(float totalDiscount) {
        assertThat(Float.parseFloat(getDiscount())).isEqualTo(totalDiscount);
    }

    @Step
    public float countPriceAfterApplyCoupon(float subTotalPrice, String discountType, String discountValue) {
        return subTotalPrice - calculateDiscount(subTotalPrice, discountType, discountValue, 1);
    }

    @Step
    public float calculateDiscount(float productPrice, String discountType, String discountValue, int quantity) {
        // productPrice = 50; discountValue = 5; quantity = 1
        float discValue = Float.parseFloat(discountValue);
        float discount = 0.00f;

        if (discountType.equalsIgnoreCase("fixed amount")) {
            discount = discValue * quantity;
        } else if (discountType.equalsIgnoreCase("Percentage")) {
            discount = (productPrice * discValue / 100) * quantity;
        } else if (discountType.equalsIgnoreCase("free")) {
            discount = productPrice * quantity;
        } else if (discountType.equalsIgnoreCase("Flat Discount")) {
            if (discValue < productPrice) {
                discount = discValue;
            } else {
                discount = productPrice;
            }
        }

        return (float) ((float) Math.round(discount * 100.0) / 100.0);
    }

    @Step
    public void verifyMessageAfterApplyDiscount(String resultMessage) {
        orderSummaryPages.verifyErrorMessageExist(resultMessage);
    }

    public HashMap<String, List<Object>> getCheckoutProductNoVariant() {
        HashMap<String, List<Object>> before = new HashMap<>();
        int numberProduct = orderSummaryPages.getNumberOfCheckoutProduct();
        System.out.println(numberProduct);
        for (int index = 1; index <= numberProduct; index++) {

            String productName = orderSummaryPages.getProductNameByLine(index);
            float productTotal = orderSummaryPages.getProductTotalByLine(index);
            int quantity = orderSummaryPages.getProductQuantityByLine(index);

            before.put(productName, asList(quantity, productTotal));
        }
        System.out.println(before);
        return before;
    }


    public HashMap<String, List<Object>> getCheckoutProductHaveVariant() {
        HashMap<String, List<Object>> listProduct = new HashMap<>();
        int numberProduct = orderSummaryPages.getNumberOfCheckoutProduct();
        for (int index = 1; index <= numberProduct; index++) {

            String productName = orderSummaryPages.getProductNameByLine(index);
            String productVariant = orderSummaryPages.getProductVariantByLineItem(index);
            float productTotal = orderSummaryPages.getProductTotalByLine(index);
            int quantity = orderSummaryPages.getProductQuantityByLine(index);

            if (!productVariant.isEmpty()) {
                productName = productName + ":" + productVariant;
            }
            listProduct.put(productName, asList(quantity, productTotal));
        }
        return listProduct;
    }

    public float getTotalPriceByLineItemAfterApplyDiscount(String productName, int quantity, String discountCode) {
        int index = orderSummaryPages.getLineItem(productName, quantity, discountCode);
        return orderSummaryPages.getProductTotalByLine(index);
    }

    public List<String> getProductList() {
        return orderSummaryPages.getProductList();
    }

    @Step
    public String getTotalAmt() {
        return orderSummaryPages.getTotalAmt();
    }

    public String getDiscountCode() {
        return orderSummaryPages.getDiscountCode();
    }

    public String getOrderCurrency() {
        return orderSummaryPages.getCurrency();
    }

    public List<String> getListProductOnOrder() {
        List<String> listProduct = orderSummaryPages.getListProductOnOrder();
        return listProduct;
    }

    public String getDiscountAmount() {
        return orderSummaryPages.getDiscountAmount();
    }

    public String getShippingFee() {
        return orderSummaryPages.getShippingFee();
    }

    public float getShippingFeeAmount() {
        return orderSummaryPages.removeCurrency(getShippingFee());
    }

    public String getTax() {
        return orderSummaryPages.getTax();
    }

    public float getTaxAmount() {
        return orderSummaryPages.removeCurrency(getTax());
    }

    public int countProductCheckout() {
        return orderSummaryPages.getNumberOfCheckoutProduct();
    }

    public String getPriceDiscount() {
        return orderSummaryPages.getPriceDiscount();
    }

    public boolean isUseDisCount() {
        String xpath = "//td[child::span[text()[normalize-space()='Discount']]]";
        return orderSummaryPages.isElementExist(xpath);
    }

    public void verifyFreeShipping() {
        orderSummaryPages.verifyFreeShipping();
    }

    public void verifyTotalPrice(float toTalPrice, float subTotalPrice) {
        assertThat(toTalPrice).isEqualTo(subTotalPrice);
    }

    public void verifyTagDiscount(String code) {
        orderSummaryPages.verifyTagDiscount(code);
    }

    public void verifyShippingFee(String expFee) {
        String actFee = thankyouPage.getShippingPrice();
        assertThat(actFee).isEqualTo(expFee);
    }

    public void verifyTaxOnSF(String taxType) {
        orderSummaryPages.verifyTaxOnSF(taxType);
    }

    public void calculateTax(String taxType, String product, boolean taxShip, float rate, String discountType, String discountValue) {
        float tax;
        float price;
        int quantity = 1;
        float discount = 0.00f;
        if (!taxShip) {
            price = orderSummaryPages.removeCurrency(productListAdded.get(product).get(1));
            quantity = Integer.parseInt(productListAdded.get(product).get(0));
        } else {
            price = orderSummaryPages.removeCurrency(getShippingFee());
        }
//        Calculate discount for item
        if (!discountValue.isEmpty()) {
            discount = calculateDiscountItem(price, discountType, discountValue);
        }
//        Calculate tax amount
        if (taxType.equalsIgnoreCase("Exclude")) {
            tax = ((price-discount) * quantity) * rate / 100;
        } else {
            tax = ((rate / 100) * ((price-discount) * quantity)) / (1 + rate / 100);
        }
        taxAmount = taxAmount + tax;
    }

    public float calculateDiscountItem(float price, String discountType, String discountValue) {
        float value = Float.parseFloat(discountValue);
        float discount = 0.00f;
        if (discountType.equalsIgnoreCase("Percentage")) {
            discount = price * value / 100;
        } else {
            discount = (value / orderSummaryPages.getSubTotalPrice()) * price;
        }
        return discount;
    }

}
