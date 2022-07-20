package opencommerce.orders.checkout;

import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static com.opencommerce.shopbase.OrderVariable.*;

import java.util.HashMap;
import java.util.List;

import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class OrderSummaryDef {
    @Steps
    OrderSummarySteps orderSummarySteps;


    @Then("^verify order summary \"([^\"]*)\"$")
    public void verifyOrderSummaryWithData(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            String subtotal = SessionData.getDataTbVal(dataTable, row, "Subtotal");
            String totalPrice = SessionData.getDataTbVal(dataTable, row, "Total price");
            String disCount_apply = SessionData.getDataTbVal(dataTable, row, "DisCount Apply");
            orderSummarySteps.verifyCheckoutPage();
            orderSummarySteps.verifyProductsOnOrderSummay(products);
            orderSummarySteps.verifySubtotal(subtotal);
            orderSummarySteps.verifyDiscountApplyOrder(disCount_apply);
            orderSummarySteps.verifyTotalPriceOrder(totalPrice);

        }
    }

    @Then("^verify order summary$")
    public void verifyOrderSummary() {


    }

    @When("user enter invalid discount code$")
    public void user_enter_discount_code(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sDiscount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String sExpected = SessionData.getDataTbVal(dataTable, row, "Expected");
            orderSummarySteps.enterDiscount(sDiscount);
            orderSummarySteps.clickApplyBtn();
            orderSummarySteps.verifyErrorMsg(sExpected);
        }
    }

    @Given("^verify screen checkout after apply automatic discount$")
    public void verify_screen_checkout_after_apply_automatic_discount() {
        orderSummarySteps.verifyScreenCheckout();
        orderSummarySteps.verifyDiscountAutomatic();
    }

    @When("^enter discount code is \"([^\"]*)\" to get product \"([^\"]*)\" with \"([^\"]*)\" percent$")
    public void enter_discount_code_is_to_get_product_with_percent(String sDiscount, String product, String percent) {
        orderSummarySteps.enterDiscount(sDiscount);
        orderSummarySteps.clickApplyBtn();
        orderSummarySteps.verifyDiscountbyXgetY(sDiscount, product, percent);
    }

    @Given("^verify automatic apply discount code \"([^\"]*)\" with \"([^\"]*)\" percent for product \"([^\"]*)\"$")
    public void verify_automatic_apply_discount_code(String sDiscount, String percent, String product) {
        orderSummarySteps.verifyDiscountbyXgetY(sDiscount, product, percent);
    }

    @Given("^apply discount code for all items as \"([^\"]*)\"$")
    public void applyDiscountCodeForAllItems(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String discountCode = SessionData.getDataTbVal(dataTable, row, "Discount code");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String discountType = SessionData.getDataTbVal(dataTable, row, "Discount type");
            //get total price before apply discount code
            float subTotalPrice = orderSummarySteps.getSubTotalPrice();
            //Apply discount code
            orderSummarySteps.enterDiscountCode(discountCode);

            //verify price after apply discount code
            float expectedDiscountAmount = orderSummarySteps.countPriceAfterApplyCoupon(subTotalPrice, discountType, discountValue) + orderSummarySteps.getTaxAmount();
            orderSummarySteps.verifyPriceAfterApplyDiscount(expectedDiscountAmount);

        }
    }

    @When("^Apply discount code \"([^\"]*)\"$")
    public void applyDiscountCode(String discountCode) {
        orderSummarySteps.enterDiscountCode(discountCode);
        float_totalAmt = orderSummarySteps.getTotalPrice();
    }

    @Given("^apply discount by line item as \"([^\"]*)\"$")
    public void applyDiscountByLineItem(String dataKey, List<List<String>> dataTable) {
        HashMap<String, List<Object>> before = null;
        float total = 0;
        String temp = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");

            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String sQuantity = SessionData.getDataTbVal(dataTable, row, "Quantity discounted");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String discountType = SessionData.getDataTbVal(dataTable, row, "Discount type");
            String discountCode = SessionData.getDataTbVal(dataTable, row, "Discount code");

            float discount = 0;
            int quantity = 1;
            float totalDiscount = 0;
            if (!sQuantity.isEmpty()) {
                quantity = Integer.parseInt(sQuantity);
            }

            //get cart infor
            if (!key.equals(temp)) {
                before = orderSummarySteps.getCheckoutProductNoVariant();
                total = orderSummarySteps.getTotalPrice();
                //Apply discount code
                orderSummarySteps.enterDiscountCode(discountCode);
            }

            List<Object> inforByLineItem = before.get(productName);

            int quantityByLineItem = (int) inforByLineItem.get(0);
            float totalByLineItem = (float) inforByLineItem.get(1);

            float priceProductByLineItem = totalByLineItem / quantityByLineItem;


            //Calculate discount
            if (!discountValue.isEmpty())
                discount = orderSummarySteps.calculateDiscount(priceProductByLineItem, discountType, discountValue, quantity);

            totalDiscount = totalDiscount + discount;

            //verify total by line item
            float expectTotalByLineItem = priceProductByLineItem * quantity - discount;
            float actTotalByLineItem = orderSummarySteps.getTotalPriceByLineItemAfterApplyDiscount(productName, quantity, discountCode);
            assertThat(actTotalByLineItem).isEqualTo(expectTotalByLineItem);


            if (nextKey.equals(key)) {
                total = total - totalDiscount;

                orderSummarySteps.verifyDiscount(totalDiscount);
                orderSummarySteps.verifyPriceAfterApplyDiscount(total);
                temp = nextKey;
            }

        }
    }


    @Given("^apply discount code$")
    public void applyDiscountCode(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {

            String discountCode = SessionData.getDataTbVal(dataTable, row, "Discount code");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String discountType = SessionData.getDataTbVal(dataTable, row, "Discount type");
            String maximumDiscountAmt = SessionData.getDataTbVal(dataTable, row, "Maximum discount amount");
            isPODDiscount = convertStatus(SessionData.getDataTbVal(dataTable, row, "POD discount"));
            isStoreDiscount = convertStatus(SessionData.getDataTbVal(dataTable, row, "Store discount"));
            isFreeShippingSetting = convertStatus(SessionData.getDataTbVal(dataTable, row, "Free shipping setting"));
            if (isFreeShippingSetting) {
                isStoreDiscount = true;
            }
            if (isPODDiscount && discountType.equalsIgnoreCase("Free shipping")) {
                isPODDiscountFreeShipping = true;
            }
//            String shippingFee = orderSummarySteps.getShippingFee();
            //Get price before apply discount code
            float subTotalPrice = orderSummarySteps.getSubTotalPrice();
            //Apply discount code
            orderSummarySteps.enterDiscountCode(discountCode);
            //Get price after apply discount code and verify it's true
            float totalPrice = 0.00f;
            if (maximumDiscountAmt.isEmpty()) {
                totalPrice = orderSummarySteps.countPriceAfterApplyCoupon(subTotalPrice, discountType, discountValue) + orderSummarySteps.getShippingFeeAmount() + orderSummarySteps.getTaxAmount();
            } else {
                double double_maximumDiscountAmt = Double.parseDouble(maximumDiscountAmt);
                double double_discountValue = Double.parseDouble(discountValue);
                if (double_maximumDiscountAmt < ((double_discountValue * subTotalPrice) / 100)) {
                    totalPrice = orderSummarySteps.countPriceAfterApplyCoupon(subTotalPrice, "fixed amount", maximumDiscountAmt) + orderSummarySteps.getShippingFeeAmount() + orderSummarySteps.getTaxAmount();
                } else {
                    totalPrice = orderSummarySteps.countPriceAfterApplyCoupon(subTotalPrice, discountType, discountValue) + orderSummarySteps.getShippingFeeAmount() + orderSummarySteps.getTaxAmount();
                }
            }
            System.out.println("totalPrice : " + totalPrice);
            System.out.println("maximumDiscountAmt : " + maximumDiscountAmt);
            orderSummarySteps.verifyPriceAfterApplyDiscount(totalPrice);
            taxAmount = orderSummarySteps.getTaxAmount();
            System.out.println("taxAmount : " + taxAmount);
            paidTotalAmtByPaypal = orderSummarySteps.getTotalAmt();
            //get total discount amount
            discountAmount = orderSummarySteps.getPriceDiscount();
        }
    }

    @Given("^apply discount code as \"([^\"]*)\"$")
    public void applyDiscountCode(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String discountCode = SessionData.getDataTbVal(dataTable, row, "Discount code");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String discountType = SessionData.getDataTbVal(dataTable, row, "Discount type");
            //Get price before apply discount code
            float subTotalPrice = orderSummarySteps.getSubTotalPrice();
            //Apply discount code
            orderSummarySteps.enterDiscountCode(discountCode);
            //Get price after apply discount code and verify it's true
        }
    }

    @Given("^verify discount code invalid$")
    public void verifyDiscountCodeValid(List<List<String>> dataTable) {
        String dataTableKey = "invalidDiscountCode";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String discountCode = SessionData.getDataTbVal(dataTable, row, "Discount code");
            String resultMessage = SessionData.getDataTbVal(dataTable, row, "Result message");
            //Apply coupon
            orderSummarySteps.enterDiscountCode(discountCode);
            //Verify coupon valid or not
            orderSummarySteps.verifyMessageAfterApplyDiscount(resultMessage);
        }
    }

    @And("Verify apply discount for order")
    public void verifyAutoApplyDiscountForOrderAs(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String code = SessionData.getDataTbVal(dataTable, row, "Discount code");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String discountType = SessionData.getDataTbVal(dataTable, row, "Discount type");
            //Verify auto apply discount code
            if (!code.equals("")) {
                orderSummarySteps.verifyTagDiscount(code);
            }
            //verify price after apply discount code freeshipping
            if (discountType.equalsIgnoreCase("Free shipping")) {
                float subTotalPrice = orderSummarySteps.getSubTotalPrice();
                float toTalPrice = orderSummarySteps.getTotalPrice();
                orderSummarySteps.verifyFreeShipping();
                orderSummarySteps.verifyTotalPrice(toTalPrice, subTotalPrice);
            } else {
                float subTotalPrice = orderSummarySteps.getSubTotalPrice();
                float totalPrice = orderSummarySteps.countPriceAfterApplyCoupon(subTotalPrice, discountType, discountValue) + orderSummarySteps.getShippingFeeAmount();
            }
        }
    }

    @And("input discount code is {string}")
    public void inputDiscountCodeIs(String discount_code) {
        orderSummarySteps.enterDiscountCode(discount_code);
    }

    @Then("verify shipping fee on order summary is {string}")
    public void verifyShippingFeeOnOrderSummaryIs(String expFee) {
        orderSummarySteps.verifyShippingFee(expFee);
    }

    @Then("calculate tax amount")
    public void calculateTax(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String taxType = SessionData.getDataTbVal(dataTable, row, "Type of tax");
            boolean taxShip = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Tax for ship"));
            String product = SessionData.getDataTbVal(dataTable, row, "Product name");
            float rate = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Tax rate(%)"));
            String discountType = SessionData.getDataTbVal(dataTable, row, "Discount type");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            orderSummarySteps.calculateTax(taxType, product, taxShip, rate,discountType,discountValue);
        }
    }

    @Then("verify tax on store front")
    public void  verifyTaxOnSF(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String typeOfTax = SessionData.getDataTbVal(dataTable, row, "Type of tax");
            orderSummarySteps.verifyTaxOnSF(typeOfTax);
        }
    }
}
