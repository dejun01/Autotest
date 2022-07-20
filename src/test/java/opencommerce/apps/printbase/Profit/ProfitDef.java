package opencommerce.apps.printbase.Profit;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.sl.In;
import net.thucydides.core.annotations.Steps;
import opencommerce.balance.steps.BalanceSteps;
import org.junit.Assert;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.profit.ProfitSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;

import static com.opencommerce.shopbase.OrderVariable.*;
import static junit.framework.TestCase.fail;
import static opencommerce.apps.printhub.orders.ManageOrdersDef.df;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProfitDef {

    @Steps
    ProfitSteps profitSteps;

    @Steps
    MyCampaignSteps myCampaignSteps;

    @Steps
    ProductSteps productSteps;
    @Steps
    BalanceSteps balanceSteps;

    Float totalBaseCost = 0.00f, profit = 0.00f;

    @Steps
    OrderSteps orderSteps;

    @Steps
    PrintbaseAPI printbaseAPI;
    public static String CSV_FILE_PATH;
    Date date = new Date();
    SimpleDateFormat ft =
            new SimpleDateFormat("yyyy-MM-dd");
    String start_date, end_date;
    public static HashMap<String, Float> analytics_profit = new HashMap<String, Float>();
    public static HashMap<String, Float[]> baseCostProduct = new HashMap<String, Float[]>();
    Float paymentRate = 3.00f, processingRate = 4.00f;
    Boolean isShipFree = false;

    @And("get profit analytics for campaign \"([^\"]*)\" with api")
    public void getProfitAnalyticsForCampaignWithApi(String campaigns) {
        String[] camp = campaigns.split(",");
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        start_date = ft.format(date);
        for (String campaign : camp) {
            Float profit_anaytics = printbaseAPI.getProfitLineItemInAnalytics(campaign, start_date, accessToken);
            analytics_profit.put(campaign, profit_anaytics);
            System.out.println(" start : " + analytics_profit.get(campaign));
        }
    }

    @And("^get infor product base and profit when ship \"([^\"]*)\" with API as \"([^\"]*)\"$")
    public float get_infor_product_base_and_profit_with_api(String shipFree, String dataKey, List<List<String>> dataTable) {
        Float totalBCost = 0.00f;
        Float shipping = 0.00f;
        start_date = ft.format(date);
        System.out.println("start_date " + start_date);
        Float discont_amount = 0.00f;
        if (shipFree.equalsIgnoreCase("true"))
            isShipFree = true;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "campaign");
            String product = SessionData.getDataTbVal(dataTable, row, "Products");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String s_shippingPrice = SessionData.getDataTbVal(dataTable, row, "Shipping");
            String s_discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            Integer quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));

            Float[] list_productBase = {0.00f, 0.00f, 0.00f, 0.00f, 0.00f};
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Integer idCampaign = printbaseAPI.getIDCampaign(campaign, accessToken);

            Float price = quantity * printbaseAPI.getPriceCampaignAPI(idCampaign, product, color, size, accessToken);
            Integer idProductBase = printbaseAPI.getIDProductBase(product, accessToken);
            Float priceBaseCost = printbaseAPI.getBaseCostAPI(idProductBase, color, size, accessToken);
            if (!s_discount.isEmpty()) {
                discont_amount = Float.parseFloat(s_discount) * price / 100;
            }
            if (!s_shippingPrice.isEmpty())
                shipping = Float.parseFloat(s_shippingPrice) * quantity;
            totalBCost = priceBaseCost * quantity;
            totalBaseCost = totalBaseCost + Math.round(totalBCost * 100.0f) / 100.0f;
            if (baseCostProduct.containsKey(campaign)) {
                list_productBase[0] = baseCostProduct.get(campaign)[0] + price;
                list_productBase[1] = baseCostProduct.get(campaign)[1] + discont_amount;
                list_productBase[2] = baseCostProduct.get(campaign)[2] + shipping;
                list_productBase[3] = baseCostProduct.get(campaign)[3] + totalBCost;
                list_productBase[4] = baseCostProduct.get(campaign)[4];
                baseCostProduct.put(campaign, list_productBase);
            } else {
                list_productBase[0] = price;
                list_productBase[1] = discont_amount;
                list_productBase[2] = shipping;
                list_productBase[3] = totalBCost;
                list_productBase[4] = printbaseAPI.getProfitLineItemInAnalytics(campaign, start_date, accessToken);
                System.out.println("profit_start : " + printbaseAPI.getProfitLineItemInAnalytics(campaign, start_date, accessToken));
                baseCostProduct.put(campaign, list_productBase);
                System.out.println();
            }

        }
        System.out.println("Total base cost" + totalBaseCost);
        return totalBaseCost;
    }

    @And("^verify profit analytics in Dashboard with API as \"([^\"]*)\"$")
    public void verifyProfitAnalysticDasshboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "campaign");
            if (!campaign.isEmpty()) {
                String campagins[] = campaign.split(";");
                for (String camp : campagins) {
                    Float shipping = baseCostProduct.get(camp)[2];
                    Float order_value = baseCostProduct.get(camp)[0] + shipping;
                    Float discount = baseCostProduct.get(camp)[1];
                    if (isShipFree)
                        discount = discount + shipping;
                    Float baseCost = baseCostProduct.get(camp)[3];
                    Float profit_analys = baseCostProduct.get(camp)[4];
                    Float profitLinesAnalys_ac, profitLinesAnalys_ex;
                    Float paymentFee = (order_value - discount) * paymentRate / 100;
                    Float processingFee = (order_value - discount - shipping - baseCost - paymentFee) * processingRate / 100;
                    Float profit_line = order_value - discount - shipping - baseCost - paymentFee - processingFee;
                    System.out.println("Profit: " + profit_line);
                    String accessToken = myCampaignSteps.getAccessTokenShopBase();

                    profitLinesAnalys_ex = Math.round((profit_analys + profit_line) * 100.0f) / 100.0f;

                    end_date = ft.format(date);
                    profitLinesAnalys_ac = Math.round(printbaseAPI.getProfitLineItemInAnalytics(camp, end_date, accessToken) * 100.0f) / 100.0f;
                    System.out.println("profitLinesAnalys_ex : " + profitLinesAnalys_ex);
                    System.out.println("profitLinesAnalys_ac : " + profitLinesAnalys_ac);
                    if (start_date.equalsIgnoreCase(end_date)) {
                        Boolean bol = true;
                        if (profitLinesAnalys_ac - profitLinesAnalys_ex > 0.02 || profitLinesAnalys_ac - profitLinesAnalys_ex < -0.02)
                            bol = false;
                        assertTrue(bol);
                    }
                }

            }
        }

    }

    @And("^get basecost and calculate total basecost of variants as \"([^\"]*)\"$")
    public float getBasecostAndCalculateTotalBasecostOfVariantsAs(String dataKey, List<List<String>> dataTable) {
        float totalBCost = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variants");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            Float sl = Float.parseFloat(quantity);
            myCampaignSteps.clickSetIndividualPrice(products);
            Float baseCost = profitSteps.getBaseCostVariant(products, variant);
            totalBCost += baseCost * sl;
        }

        totalBaseCost = Math.round(totalBCost * 100.0f) / 100.0f;
        return totalBaseCost;
    }

    @And("^add campaign to cart as \"([^\"]*)\"$")
    public void addCampaignToCartAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            if (!products.isEmpty())
                productSteps.selectStyle(products);
            if (!color.isEmpty())
                productSteps.selectColorOnStorefront(color);
            if (!size.isEmpty())
                productSteps.selectSize(size);
            if (!quantity.isEmpty())
                productSteps.selectQuantity(quantity);
            profitSteps.clickBtnAddToCart();
        }
    }

    @And("^get infor productbase and input to csv file as \"([^\"]*)\"$")
    public void get_abandoned_checkout_details_on_storefron(String dataKey, List<List<String>> dataTable) {
        float totalBCost = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product_base = SessionData.getDataTbVal(dataTable, row, "Products");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            Integer quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Integer idProductBase = printbaseAPI.getIDProductBase(product_base, accessToken);
            List<Float> price = printbaseAPI.getProductBaseInforWithAPI(idProductBase, color, size, accessToken);

            String env = LoadObject.getProperty("gapi.url").split("https://")[1];
            switch (env) {
                case "api.shopbase.com":
                    CSV_FILE_PATH = LoadObject.getFilePath("phub" + File.separator + "prod_product_base.csv");
                    System.out.println("============== File path is: " + CSV_FILE_PATH);
                    break;
                case "gapi.stag.shopbase.net":
                    CSV_FILE_PATH = LoadObject.getFilePath("phub" + File.separator + "stag_product_base.csv");
                    break;
                default:
                    fail();
            }
            profitSteps.addProductBaseInfoToCSV(CSV_FILE_PATH, product_base, price.get(0), price.get(1), price.get(2));
        }

    }


    @And("^verify show profit in balance$")
    public void verifyShowProfitInBalance() {
        balanceSteps.clickOnViewHistoryBtn();
        profitSteps.verifyLogProfitInBalance();
        profitSteps.verifyAmount(profit);
        profitSteps.verifyDetailField(orderNumber, profit);
    }

    @And("^verify order detail when method shipping free \"([^\"]*)\" in Pbase as \"([^\"]*)\"$")
    public Float verifyOrderDtailInPbaseAs(String shippingFree, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "campaign");
            Float subTotal = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Sub total"));
            String s_discountAmount = SessionData.getDataTbVal(dataTable, row, "Discount amount");
            Float discount = 0.00f, paymentFee = 0.00f, processingFee = 0.00f;
            Float shipFee = Float.parseFloat(shippingFee.replace("$", ""));
            if (shippingFree.equalsIgnoreCase("true"))
                isShipFree = true;
            if (!s_discountAmount.isEmpty())
                discount = Float.parseFloat(s_discountAmount);
            if (isShipFree)
                discount = discount + shipFee;
            Float total = Float.parseFloat(df.format(subTotal + shipFee - discount));
            Float baseCost = orderSteps.getBaseCost();
            // Verify shipping
            Float shipping_price = orderSteps.getShipping();
            if (isShipFree == false)
                Assert.assertEquals(shipFee, shipping_price);
            // *****Verify Payment Fee
            paymentFee = total * paymentRate / 100;
            Float paymentFee_ex = Float.parseFloat(df.format(paymentFee));
            Float getPaymentFee_ac = orderSteps.getPaymentFee();
            Assert.assertEquals(paymentFee_ex, getPaymentFee_ac);
            // *****Verify Processing Fee
            if (discount != 0) {
                processingFee = (total - shipping_price - baseCost - paymentFee) * processingRate / 100;
            } else {
                processingFee = (total - discount - shipping_price - baseCost - paymentFee) * processingRate / 100;
            }
            Float processingFee_ex = Float.parseFloat(df.format(processingFee));
            Float processingFee_ac = orderSteps.getProcessingFee();
            Assert.assertEquals(processingFee_ex, processingFee_ac);
            // verify Profit
            Float profit_ex = 0.00f;
            if (isShipFree == false) {
                profit_ex = Float.parseFloat(df.format(subTotal - discount - baseCost - paymentFee - processingFee));
            } else {
                if (discount != 0) {
                    profit_ex = Float.parseFloat(df.format(total - shipping_price - baseCost - paymentFee - processingFee));
                } else {
                    profit_ex = Float.parseFloat(df.format(total - shipping_price - discount - baseCost - paymentFee - processingFee));
                }
            }
            Float actualProfit = orderSteps.getProfit();
            Assert.assertEquals(profit_ex, actualProfit);
            profit = actualProfit;
        }
        return profit;
    }

    @And("verify profit analytics in Dashboard with API when method shipping free \"([^\"]*)\" as \"([^\"]*)\"$")
    public void verifyProfitAnalyticsInDashboardWithAPIWhenKEY(String shippingFree, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "campaign");
            Float subTotal = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Sub total"));
            Float baseCost = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Base cost"));
            Float shipping = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Shipping"));
            String s_discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            Float discount = 0.00f;
            if (shippingFree.equalsIgnoreCase("true"))
                isShipFree = true;
            if (!s_discount.isEmpty())
                discount = Float.parseFloat(s_discount);
            if (isShipFree)
                discount = discount + shipping;
            Float total = Float.parseFloat(df.format(subTotal + shipping));
            Float paymentFee = (total - discount) * paymentRate / 100;
            Float processingFee = (total - discount - shipping - baseCost - paymentFee) * processingRate / 100;
            Float profit_line = total - discount - shipping - baseCost - paymentFee - processingFee;
            Float profitLinesAnalys_ac, profitLinesAnalys_ex;
            Float profit_analys_start = analytics_profit.get(campaign);
            profitLinesAnalys_ex = Float.parseFloat(df.format(profit_analys_start + profit_line));
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            end_date = ft.format(date);
            profitLinesAnalys_ac = Float.parseFloat(df.format(printbaseAPI.getProfitLineItemInAnalytics(campaign, end_date, accessToken)));
            System.out.println("profitLinesAnalys_ex : " + profitLinesAnalys_ex);
            System.out.println("profitLinesAnalys_ac : " + profitLinesAnalys_ac);
            if (start_date.equalsIgnoreCase(end_date)) {
                Boolean isProfit = false;
                if (profitLinesAnalys_ex - profitLinesAnalys_ac < 0.01 || profitLinesAnalys_ex - profitLinesAnalys_ac > 0.01)
                    isProfit = true;
                Assert.assertTrue(" Check profit analytics", isProfit);
            }
        }
    }

    @And("add campaign personal to cart as \"([^\"]*)\"$")
    public void addCampaignPersonalToCartAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            productSteps.clickAddToCart();
        }
    }

    @And("^verify profit of order$")
    public void verifyProfitOfOrder(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productBaseName = SessionData.getDataTbVal(dataTable, row, "Product Base info");
            String accessToken = myCampaignSteps.getAccessTokenShopBase();

            Float baseCost = printbaseAPI.getBaseCostFromProductBase(productBaseName, accessToken);
            System.out.println("Base cost: " + df.format(baseCost));

//            double baseCost = printbaseAPI.getProductBaseInforWithAPI(idProductBase, option1, option2, accessToken).get(0);

            double subtotal = Double.parseDouble(subTotal.split("(?<=\\D)(?=\\d)", 2)[1]) + ppcDiscountAmt;
            double shippingFee = Double.parseDouble(orderSteps.getShippingSF().split("(?<=\\D)(?=\\d)", 2)[1]);
//            double taxamount = Double.parseDouble(taxAmount.split("(?<=\\D)(?=\\d)", 2)[1]);
            int productQty = productQuantity;

            double totalBaseCost = (double) Math.round((baseCost * productQty) * 100.0) / 100.0;
            double pbaseDiscountAmt = 0.00d;
            double storeDiscountAmt = 0.00d;
            double _ppcDiscountAmt = ppcDiscountAmt;
            double paymentFee = 0.00d;
            double processingFee = 0.00d;
            double paymentFeeRate = 0.00d;
            double processingFeeRate = 0.00d;
            double profit = 0.00d;
            double actualPaymentFee = 0.00d;
            double actualProcessingFee = 0.00d;
            double actualProfit = 0.00d;

            //define env and set the rate
            String[] rate; //rate has format as Payment fee rate,Processing fee rate (ex: 3,4 => Payemnt = 3%, Processing = 4%)
            if (shopDomain.contains("onshopbase.com")) {
                rate = SessionData.getDataTbVal(dataTable, row, "Production rate").split(",");
            } else {
                rate = SessionData.getDataTbVal(dataTable, row, "Staging rate").split(",");
            }
            paymentFeeRate = Double.parseDouble(rate[0]) / 100;
            processingFeeRate = Double.parseDouble(rate[1]) / 100;

            //get actual profit in UI
            orderSteps.showCalculationYourProfit();
            orderSteps.openHandlingFeeInYourProfit();

            actualPaymentFee = orderSteps.getPaymentFee();
            actualProcessingFee = orderSteps.getProcessingFee();
            actualProfit = orderSteps.getProfit();

            if (isPODDiscount && !isPODDiscountFreeShipping) {
                pbaseDiscountAmt = Double.parseDouble(discountAmount.split("(?<=\\D)(?=\\d)", 2)[1]);
            } else {
                pbaseDiscountAmt = shippingFee;
            }
            if (isStoreDiscount) {
                if (isFreeShippingSetting && isOnPostPurchase) {
                    storeDiscountAmt = shippingFee + _ppcDiscountAmt;
                } else if (isFreeShippingSetting) {
                    storeDiscountAmt = shippingFee;
                } else {
                    storeDiscountAmt = _ppcDiscountAmt;
                }
            }

            //calculate payment fee
            paymentFee = (subtotal + shippingFee - pbaseDiscountAmt - storeDiscountAmt + taxAmount) * paymentFeeRate;
            System.out.println("Payment Fee: " + df.format(paymentFee));
            //calculate processing fee
            processingFee = (subtotal - totalBaseCost - paymentFee - storeDiscountAmt) * processingFeeRate;
            System.out.println("Processing Fee: " + df.format(processingFee));
            //calculate profit
            profit = subtotal - totalBaseCost - storeDiscountAmt - paymentFee - processingFee;
            System.out.println("Profit: " + df.format(profit));

            assertThat(df.format(actualPaymentFee)).isEqualTo(df.format(paymentFee));
            assertThat(df.format(actualProcessingFee)).isEqualTo(df.format(processingFee));
            assertThat(df.format(actualProfit)).isEqualTo(df.format(profit));
        }
    }

    @And("^verify profit of order detail$")
    public void verifyProfitOfOrderContainsItemsManualDesign(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productBaseName = SessionData.getDataTbVal(dataTable, row, "Product Base info");
            String _sLineManual = SessionData.getDataTbVal(dataTable, row, "Number lineitem manual design");
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            String[] listProducts = productBaseName.split(";");
            float designFee = 0.00f;
            Float baseCost = 0.00f;

            if (!_sLineManual.isEmpty() & !_sLineManual.equalsIgnoreCase("0")) {
                int sl = Integer.parseInt(_sLineManual);
                designFee = printbaseAPI.getDesignFeeOfCampaignManual(accessToken) * sl;
                orderSteps.verifyDesignFee(designFee);
            }
            for (String listProduct : listProducts) {
                String[] product = listProduct.split(">");
                Integer sl = 1;
                if (product.length == 2)
                    sl = Integer.parseInt(product[1]);
                baseCost = baseCost + printbaseAPI.getBaseCostFromProductBase(product[0], accessToken) * sl;
            }

            System.out.println("Base cost: " + df.format(baseCost));

            System.out.println(subTotal);
            double subtotal = Double.parseDouble(subTotal.split("(?<=\\D)(?=\\d)", 2)[1]) + ppcDiscountAmt;
            double shippingFee = Double.parseDouble(orderSteps.getShippingSF().split("(?<=\\D)(?=\\d)", 2)[1]);
            double tax = Double.parseDouble(totalTax.split("(?<=\\D)(?=\\d)", 2)[1]);

            double totalBaseCost = (double) Math.round((baseCost) * 100.0) / 100.0;
            double pbaseDiscountAmt = 0.00d;
            double storeDiscountAmt = 0.00d;
            double _ppcDiscountAmt = ppcDiscountAmt;
            double grandTotal = 0.00d;
            double paymentFee = 0.00d;
            double processingFee = 0.00d;
            double paymentFeeRate = 0.00d;
            double processingFeeRate = 0.00d;
            double profit = 0.00d;
            double actualPaymentFee = 0.00d;
            double actualProcessingFee = 0.00d;
            double actualProfit = 0.00d;
            //define env and set the rate
            String[] rate; //rate has format as Payment fee rate,Processing fee rate (ex: 3,4 => Payemnt = 3%, Processing = 4%)
            if (shopDomain.contains("onshopbase.com")) {
                rate = SessionData.getDataTbVal(dataTable, row, "Production rate").split(",");
            } else {
                rate = SessionData.getDataTbVal(dataTable, row, "Staging rate").split(",");
            }
            paymentFeeRate = Double.parseDouble(rate[0]) / 100;
            processingFeeRate = Double.parseDouble(rate[1]) / 100;

            //get actual profit in UI
            actualPaymentFee = orderSteps.getPaymentFee();
            actualProcessingFee = orderSteps.getProcessingFee();
            actualProfit = orderSteps.getProfit();
            if (!discountAmount.isEmpty())
                pbaseDiscountAmt = Double.parseDouble(discountAmount.split("(?<=\\D)(?=\\d)", 2)[1]);
            if (isPODDiscountFreeShipping)
                pbaseDiscountAmt = pbaseDiscountAmt + shippingFee;
            if (isStoreDiscount) {
                if (isFreeShippingSetting && isOnPostPurchase) {
                    storeDiscountAmt = shippingFee + _ppcDiscountAmt;
                } else if (isFreeShippingSetting) {
                    storeDiscountAmt = shippingFee;
                } else {
                    storeDiscountAmt = _ppcDiscountAmt;
                }
            }

            //calculate payment fee
            grandTotal = subtotal + shippingFee + tax;
            paymentFee = (grandTotal - pbaseDiscountAmt - storeDiscountAmt) * paymentFeeRate;
            System.out.println("Payment Fee: " + df.format(paymentFee));
            //calculate processing fee
            processingFee = (grandTotal - storeDiscountAmt - shippingFee - totalBaseCost - tax - designFee - paymentFee) * processingFeeRate;
            System.out.println("Processing Fee: " + df.format(processingFee));
            //calculate profit
            profit = grandTotal - storeDiscountAmt - shippingFee - tax- totalBaseCost - paymentFee - processingFee - designFee;
            System.out.println("Profit: " + df.format(profit));

            assertThat(df.format(actualPaymentFee)).isEqualTo(df.format(paymentFee));
            assertThat(df.format(actualProcessingFee)).isEqualTo(df.format(processingFee));
            assertThat(df.format(actualProfit)).isEqualTo(df.format(profit));
        }
    }
}

