package opencommerce.balance;

import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.settings.pages.AccountPage;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.PricingSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.settings.steps.PaymentsSettingSteps;
import opencommerce.online_store.domains.DomainDef;

import java.text.DecimalFormat;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;

public class BalanceDef {
    @Steps
    BalanceSteps balanceSteps;
    @Steps
    PaymentsSettingSteps paymentsSettingSteps;
    @Steps
    PricingSteps pricingSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    AccountSteps accountSteps;

    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    ShippingMethodSteps shippingMethodSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;

    AccountPage accountPage;

    public float variant_base_price;

    private static DecimalFormat df = new DecimalFormat("#,##0.00");

    public static double currentAvailableSoon;
    public static double availableToPayout;
    public static double currentAvailableSoon_double;
    public static String fee, net;
    public static double topUpAmt;
    public static double transactionFee = 0.00d;
    public static double insufficientMoney;
    public static double autoTopUpAmt;
    public static double payoutAmt;
    public static double currentBalanceAmt;
    public static String selectedShop;
    public static double transactionRate;
    public static double amtRefundBuyer;
    public static double amtRefundSeller;


    @Given("^Navigate to Balance page$")
    public void navigateToBalancePage() {
        balanceSteps.accessToBalancePage();
    }

    @Given("^TopUp Balance with \"([^\"]*)\"$")
    public void topUpBalance(String _topUpAmt) {
        //Navigate to balance page
        balanceSteps.accessToBalancePage();
//        topUpAmt = _topUpAmt;
        double currentAvailableBalance = balanceSteps.getCurrentAvailableBalance();
        double topupAmt_double = Double.parseDouble(_topUpAmt.replaceAll("[$,]", ""));
        balanceSteps.clickOnTopUpBtn();
        balanceSteps.selectTopUpAmt(_topUpAmt);
        balanceSteps.clickOnConfirmTopUpBtn();
        balanceSteps.clickOnBreadcrumbBalance();
        balanceSteps.reloadPage();
        double availableBalanceAfterTopup = balanceSteps.getCurrentAvailableBalance();
        System.out.println("Available balance: " + currentAvailableBalance);
        System.out.println("Available balance after topup: " + availableBalanceAfterTopup);
        System.out.println("Top up amount: " + topupAmt_double);

        balanceSteps.verifyAvailabaleBalanceAmtAfterTopup(currentAvailableBalance, availableBalanceAfterTopup, topupAmt_double);

    }

    @Given("^Request payout with all available balance$")
    public void requestPayout() {
        balanceSteps.accessToBalancePage();
        double currentAvailableBalance = balanceSteps.getCurrentAvailableBalance();
        currentBalanceAmt = currentAvailableBalance;
        payoutAmt = currentAvailableBalance;

        balanceSteps.clickOnRequestPayoutBtn();
        balanceSteps.inputPayoutamt(currentBalanceAmt);
        balanceSteps.clickOnSendRequestBtn();
    }


    @Given("^Get current Available soon$")
    public void getCurrentAvailableSoon() {
        currentAvailableSoon = balanceSteps.getAvailableSoon();
        System.out.println("currentAvailableSoon_double: " + currentAvailableSoon);
    }

//    @Given("^Verify Available soon after checkout$")
//    public void verifyAvailableSoonAfterCheckout() {
//        double new_currentAvailableSoon_double = Double.parseDouble(balanceSteps.getAvailableSoon().replaceAll("[$,]", ""));
//        double totalAmt_double = Double.parseDouble(totalAmt.replaceAll("[$,]", ""));
//        double shippingFee_double = Double.parseDouble(shippingFee.replaceAll("[$,]", ""));
//        double processingFeeRate = SettingDef.printBaseProcessingFeeRate_double;
//        double processingFee = 0.0d;
//        double net_double = 0.0d;
//
//        processingFee = Math.round((((totalAmt_double - shippingFee_double - variant_base_price) * processingFeeRate) / 100.0) * 100.0) / 100.0;
//
//        //fee = "$" + df.format(processingFee + shippingFee_double);
//        fee = "$0.00";
//        net_double = totalAmt_double - shippingFee_double - variant_base_price - processingFee;
//        net = "$" + df.format(net_double);
//
//        System.out.println("currentAvailableSoon_double: " + currentAvailableSoon_double);
//        System.out.println("new_currentAvailableSoon_double: " + new_currentAvailableSoon_double);
//        System.out.println("totalAmt_double: " + totalAmt_double);
//        System.out.println("shippingFee_double: " + shippingFee_double);
//        System.out.println("processingFee: " + processingFee);
//        System.out.println("variant_base_price: " + variant_base_price);
//        System.out.println("fee: " + fee);
//        System.out.println("net: " + net);
//
//        balanceSteps.verifyAvailableSoonAfterCheckout(currentAvailableSoon_double, new_currentAvailableSoon_double, net_double);
//    }

    @Given("^Verify balance history of shop \"([^\"]*)\" after \"([^\"]*)\" with status = \"([^\"]*)\"$")
    public void verifyBalanceHistory(String shop, String action, String status) {
        String type = "";
        String source = "";
        String shopName = "";
        String detail = "";
        String amount = "";
        String _fee = "";
        String _net = "";
        String orderNo = "";

        if ("Checkout".equalsIgnoreCase(action)) {
            orderNo = orderNumber.split("\\s+")[1];
        } else {
            orderNo = "";
        }
        boolean isViewHistory = balanceSteps.isStayAtViewHistoryScreen();
        if (!isViewHistory) {
            balanceSteps.clickOnViewHistoryBtn();
        }

        if ("Checkout".equalsIgnoreCase(action) || "Auto top up".equalsIgnoreCase(action) || "Manually top up".equalsIgnoreCase(action)) {
            type = "IN";
        } else if ("Payout".equalsIgnoreCase(action) || "Payout by API".equalsIgnoreCase(action)) {
            type = "OUT";
        }

        if ("Auto top up".equalsIgnoreCase(action) || "Manually top up".equalsIgnoreCase(action) || "Payout".equalsIgnoreCase(action) || "Payout by API".equalsIgnoreCase(action)) {
            source = "Available balance";
        } else if ("Checkout".equalsIgnoreCase(action)) {
            source = "Available soon";
        }

        shopName = (LoadObject.getProperty(selectedShop)).split("\\.")[0];

        if ("Auto top up".equalsIgnoreCase(action)) {
            detail = "Auto recharge top up";
        } else if ("Manually top up".equalsIgnoreCase(action)) {
            detail = "Manually top up";
        } else if ("Checkout".equalsIgnoreCase(action)) {
            detail = "Charged from the order " + orderNo;
        } else if ("Payout by API".equalsIgnoreCase(action)) {
            detail = "Payout by API";
        } else if ("Payout".equalsIgnoreCase(action)) {
            detail = "Requested Payout";
        }

        if ("Checkout".equalsIgnoreCase(action)) {
            //amount = CustomerInformationDef.totalAmt;
            amount = net;
            _fee = fee;
            _net = net;
        } else if ("Auto top up".equalsIgnoreCase(action)) {
            amount = "$" + autoTopUpAmt;
            _fee = "$0.00";
            _net = amount;
        } else if ("Manually top up".equalsIgnoreCase(action)) {
            amount = "$" + df.format(topUpAmt);
            _fee = "$0.00";
            _net = amount;
        } else if ("Payout".equalsIgnoreCase(action) || "Payout by API".equalsIgnoreCase(action)) {
            amount = "-$" + df.format(payoutAmt);
            _fee = "$0.00";
            _net = amount;
        }

        System.out.println("type: " + type);
        System.out.println("source: " + source);
        System.out.println("shopName: " + shopName);
        System.out.println("detail: " + detail);
        System.out.println("amount: " + amount);
        System.out.println("_fee: " + _fee);
        System.out.println("_net: " + _net);
        System.out.println("status: " + status);

        balanceSteps.verifyBalanceHistory(type, source, shopName, detail, amount, _fee, _net, status);
    }

    @Given("^Verify request payout history with status as \"([^\"]*)\"$")
    public void verifyRequestPayoutHistory(String status) {
        balanceSteps.clickOnViewPayoutsBtn();
        // balanceSteps.verifyViewPayoutHistory(payoutAmt,status);
        paymentsSettingSteps.clickOnBreadcrumb("Balance");
    }

    @Given("^Get base cost of items$")
    public void getBaseCostOfItems(List<List<String>> dataTable) {
        String accToken = commonSteps.getAccessTokenShop();
        String variants = "";

        String numberOfSecondSide_string = "";
        String baseProductId = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            baseProductId = SessionData.getDataTbVal(dataTable, row, "Base product ID");
            numberOfSecondSide_string = SessionData.getDataTbVal(dataTable, row, "Number of side");
        }
        int numberOfSecondSide_int = Integer.parseInt(numberOfSecondSide_string);

        JsonPath basePrice = pricingSteps.getBasePrice(accToken, variants, numberOfSecondSide_int, Integer.parseInt(baseProductId));
        variant_base_price = basePrice.get("products.base_price[0]");
        System.out.println("variant_base_price: " + variant_base_price);
    }


    @And("^Verify staff can not navigate to balance$")
    public void verifyStaffCanNotNavigateToBalance() {
        balanceSteps.verifyNoBalanceInMenu();
        balanceSteps.verifyNoBalanceInUrl();
    }

    @And("^Top up \"([^\"]*)\" to balance$")
    public void topUpToBalance(String topUpAmt) {
        balanceSteps.clickOnTopUpBtn();
        balanceSteps.selectTopUpAmt(topUpAmt);
        balanceSteps.clickOnConfirmTopUpBtn();
        balanceSteps.accessToBalancePage();
    }

    @And("^Charge \"([^\"]*)\" from balance with API$")
    public void chargeToBalance(String chargeAmt) {
        if ("all".equals(chargeAmt) && currentBalanceAmt > 0) {
            balanceSteps.chargeBalance(currentBalanceAmt);
        } else if (!"all".equals(chargeAmt)) {
            balanceSteps.chargeBalance(Double.parseDouble(chargeAmt.replace("$", "")));
        }
        commonSteps.refreshPage();
    }

    @And("^Verify balance amount as \"([^\"]*)\"$")
    public void verifyBalanceAmount(String dataKey, List<List<String>> dataTable) {
        commonSteps.waitABit(20000);
        double actualTotalBalance = (double) Math.round(balanceSteps.getCurrentAvailableBalanceByAPI() * 100) / 100;
        double actualAvailablePayout = (double) Math.round(balanceSteps.getAvailableToPayoutByAPI() * 100) / 100;
        double actualPendingReview = (double) Math.round(balanceSteps.getAvailableSoonByAPI() * 100) / 100;
        System.out.println(actualTotalBalance + " " + actualAvailablePayout + " " + actualPendingReview);


        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String colTotalCurrent = SessionData.getDataTbVal(dataTable, row, "Total current available");
            String colAvailablePayout = SessionData.getDataTbVal(dataTable, row, "Available to payout");
            String colPendingToReview = SessionData.getDataTbVal(dataTable, row, "Pending to review");
            if ("@transFee@".equals(colTotalCurrent)) {
                transactionFee = (double) Math.round((Double.parseDouble(totalAmt.replace("$", "")) * transactionRate) * 100) / 100;
                balanceSteps.verifyDataBalanceChanged(currentBalanceAmt, -1 * transactionFee, actualTotalBalance);
            } else {
                balanceSteps.verifyDataBalanceChanged(currentBalanceAmt, Double.parseDouble(colTotalCurrent), actualTotalBalance);
            }
            balanceSteps.verifyDataBalanceChanged(availableToPayout, Double.parseDouble(colAvailablePayout), actualAvailablePayout);
            if ("@refundPbase@".equals(colPendingToReview)) {
                balanceSteps.verifyDataBalanceChanged(currentAvailableSoon, -1 * amtRefundBuyer + amtRefundSeller, actualPendingReview);
            } else {
                balanceSteps.verifyDataBalanceChanged(currentAvailableSoon, Double.parseDouble(colPendingToReview), actualPendingReview);
            }
        }
    }

    @And("^Get balance info$")
    public void getBalanceInfo() {
        currentBalanceAmt = balanceSteps.getCurrentAvailableBalance();
        System.out.println(currentBalanceAmt);
        availableToPayout = balanceSteps.getAvailableToPayout();
        currentAvailableSoon = balanceSteps.getAvailableSoon();
        balanceSteps.getAutoTopupStatus();
    }

    @And("^Get balance info by API$")
    public void getBalanceInfoByAPI() {
        currentBalanceAmt = (double) Math.round(balanceSteps.getCurrentAvailableBalanceByAPI() * 100) / 100;
        availableToPayout = (double) Math.round(balanceSteps.getAvailableToPayoutByAPI() * 100) / 100;
        currentAvailableSoon = (double) Math.round(balanceSteps.getAvailableSoonByAPI() * 100) / 100;
        System.out.println(currentBalanceAmt + " " + availableToPayout + " " + currentAvailableSoon);

    }


    @Then("^Verify data of sidebar Make a payment$")
    public void verifyDataOfSidebarMenuMakeAPayment(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String enoughMoney = SessionData.getDataTbVal(dataTable, row, "Enough money");
            String detail = SessionData.getDataTbVal(dataTable, row, "Details");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String total = SessionData.getDataTbVal(dataTable, row, "Total");
            String availableBalance = SessionData.getDataTbVal(dataTable, row, "Available balance");
            String paymentType = detail.split("-")[0].trim();
            String duration = detail.split("-")[2].trim();

            balanceSteps.verifyEnoughMoney(enoughMoney);

            if (detail.matches("(.*)@(.*)@(.*)")) {
                balanceSteps.verifyDetail(paymentType, DomainDef.domainURL, duration);
            }

            if (price.matches("@(.*)@")) {
                balanceSteps.verifyPrice(DomainDef.domainPrice);
            }

            if (total.matches("@(.*)@")) {
                balanceSteps.verifyTotal(DomainDef.domainPrice);
            }

            if ("@Available balance@".equals(availableBalance)) {
                balanceSteps.verifyAvailableBalance(currentBalanceAmt);
            } else if ("@Top up amount@".equals(availableBalance)) {
                balanceSteps.verifyAvailableBalance(topUpAmt);
            } else if ("@Price@".equals(availableBalance)) {
                balanceSteps.verifyAvailableBalance(DomainDef.domainPrice);
            }
        }
    }

    @Then("^Verify data of sidebar Top up your Balance$")
    public void verifyDataOfSidebarMenuTopUpYourBalance(List<List<String>> dataTable) {
        balanceSteps.clickOnTopUpBtnOnSidebarMenuMakeAPayment();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String total = SessionData.getDataTbVal(dataTable, row, "Total");
            String availableBalance = SessionData.getDataTbVal(dataTable, row, "Available balance");
            String topUpAmount = SessionData.getDataTbVal(dataTable, row, "Top up amount");
            String selectAPaymentMethod = SessionData.getDataTbVal(dataTable, row, "Select a payment method");

            if (total.matches("@(.*)@")) {
                balanceSteps.verifyTotal(DomainDef.domainPrice);
            }

            if ("@Available balance@".equals(availableBalance)) {
                balanceSteps.verifyAvailableBalance(currentBalanceAmt);
            } else if ("@Top up amount@".equals(availableBalance)) {
                balanceSteps.verifyAvailableBalance(topUpAmt);
            }

            if ("@Price@".equals(topUpAmount)) {
                balanceSteps.verifyTopUpAmount(DomainDef.domainPrice);
            } else if ("@Insufficient money@".equals(topUpAmount)) {
                insufficientMoney = DomainDef.domainPrice - topUpAmt;
                balanceSteps.verifyTopUpAmount(insufficientMoney);
            }

            balanceSteps.verifyPaymentMethod(selectAPaymentMethod);
        }
    }

    @Given("^Top up \"([^\"]*)\" to balance on sidebar Make a payment$")
    public void topUpToBalanceOnSidebarMenuMakeAPayment(String topUpAmount) {
        if ("@Insufficient money@".equals(topUpAmount)) {
            topUpAmt = insufficientMoney;
            balanceSteps.topUpToBalanceOnSidebarMenuMakeAPayment(String.valueOf(topUpAmt));
        } else if ("@Price@".equals(topUpAmount)) {
            topUpAmt = DomainDef.domainPrice;
            balanceSteps.topUpToBalanceOnSidebarMenuMakeAPayment(String.valueOf(topUpAmt));
        } else {
            topUpAmt = Double.parseDouble(topUpAmount.replace("$", "").replace(",", ""));
            balanceSteps.topUpToBalanceOnSidebarMenuMakeAPayment(String.valueOf(topUpAmt));
        }
    }

    @Given("^Top up to balance by Money transfer as \"([^\"]*)\"$")
    public void topUpToBalanceByMoneyTransferAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String account = SessionData.getDataTbVal(dataTable, row, "Preferred account");
            String accHolder = SessionData.getDataTbVal(dataTable, row, "Account holder");
            String accEmail = SessionData.getDataTbVal(dataTable, row, "Account email");
            String transId = SessionData.getDataTbVal(dataTable, row, "Transaction ID");
            String note = SessionData.getDataTbVal(dataTable, row, "Note");
            balanceSteps.clickOnTopUpBtn();
            balanceSteps.selectMoneyTransfer();
            balanceSteps.inputTopupInfo(amount, account, accHolder, accEmail, transId, note);
            balanceSteps.verifyConfirmTopup(amount);
        }

    }

    @Given("^Verify invoice Money transfer as \"([^\"]*)\"$")
    public void verifyInvoiceMoneyTransferAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            balanceSteps.verifyInvoiceInfo(type, content, amount, status);
        }
    }


    @And("^Filter balance history by \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void filterBalanceHistoryByWithValue(String content, String value) {
        balanceSteps.clickOnViewHistoryBtn();

        if (value.equals("@Shop name@")) {
            value = LoadObject.getProperty("shop");
        }

        balanceSteps.filterBalanceHistoryByWithValue(content, value);
    }

    @And("^Verify transactions Money transfer as \"([^\"]*)\"$")
    public void verifyTransactionsMoneyTransferAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String index = SessionData.getDataTbVal(dataTable, row, "Index");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String detail = SessionData.getDataTbVal(dataTable, row, "Detail");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            balanceSteps.clickOnLatestInvoice();
            commonSteps.switchToTheLastestTab();
            balanceSteps.verifyInvoiceTransactionType(index, type);
            balanceSteps.verifyInvoiceDetail(detail);
            balanceSteps.verifyWireTransferTransactionContent(index, content);
            balanceSteps.verifyInvoiceTransactionAmountIncrease(index, Double.parseDouble(amount.replace("$", "")));
            balanceSteps.verifyInvoiceTransactionStatus(index, status);
        }
    }

    @And("^filter & verify top up request by top up code$")
    public void filterVerifyTopUpRequestByTopUpCode() {
        balanceSteps.filterVerifyTopUpRequestByTopUpCode();
    }


    @Then("^Approve\\/ Refuse top up request as \"([^\"]*)\"$")
    public void approveRefuseTopUpRequestAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            balanceSteps.actionTopupRequest(action);
        }
    }

    @Then("logout to hive sbase")
    public void logoutToHiveSbase() {
        balanceSteps.logoutToHiveSbase();
    }

    @And("^Update account payout \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void updateAccountPayoutWithValue(String account, String value) {
        balanceSteps.clickEditPayoutAccount(account);
        balanceSteps.inputPayoutAccount(account, value);
        balanceSteps.clickEditPayoutAccount(account);
    }

    @And("Update Auto recharge")
    public void updateAutoRecharge() {
        getBalanceInfo();
        balanceSteps.CheckAutoTopupAndInputData();
    }

    @And("click to update card")
    public void clickToUpdateCard() {
        balanceSteps.clicktoUpdateCard();

    }

    @And("update credit card")
    public void updateCreditCard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String cardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String expired_date = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String cvv = SessionData.getDataTbVal(dataTable, row, "CVV");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            balanceSteps.inputUpdateCreditCard(cardNumber, expired_date, cvv, country);
        }
    }

    @Then("Verify top up fail on sidebar Top up your Balance")
    public void verifyTopUpFailOnSidebarMenuTopUpYourBalance(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String error = SessionData.getDataTbVal(dataTable, row, "Error");
            balanceSteps.verifyError(error);
        }
    }

    @Then("Click to button Pay now")
    public void clickToButtonPayNow() {
        balanceSteps.clickToButtonPayNow();
    }

    @Given("^Verify balance invoice$")
    public void verifyBalanceInvoice(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String index = SessionData.getDataTbVal(dataTable, row, "Index");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String shopName = SessionData.getDataTbVal(dataTable, row, "Shop name");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String createdDate = SessionData.getDataTbVal(dataTable, row, "Created date");
            String latestTransactionDate = SessionData.getDataTbVal(dataTable, row, "Latest transaction date");
            balanceSteps.verifyBalanceInvoiceType(index, type);
            balanceSteps.verifyBalanceInvoiceShopName(index, shopName);
            if (content.contains("@order@")) {
                content = content.replace("@order@", LoadObject.getProperty("order_1"));
            }
            balanceSteps.verifyBalanceInvoiceContent(index, content);
            if ("-@Price@".equals(amount)) {
                balanceSteps.verifyBalanceInvoiceAmountDecrease(index, DomainDef.domainPrice);
            } else if (amount.contains("-")) {
                balanceSteps.verifyBalanceInvoiceAmountDecrease(index, Double.parseDouble(amount.replace("$", "")));
            } else if (type.equals("OUT") && amount.contains("$")) {
                balanceSteps.verifyBalanceInvoiceAmountDecrease(index, Double.parseDouble(amount.replace("$", "")));
            } else if ("@autoTopup@".equals(amount)) {
                balanceSteps.verifyBalanceInvoiceAmountIncrease(index, autoTopUpAmt);
            } else if ("@transFee@".equals(amount)) {
                transactionFee = (double) Math.round((Double.parseDouble(totalAmt.replace("$", "")) * transactionRate) * 100) / 100;
                balanceSteps.verifyBalanceInvoiceAmountDecrease(index, transactionFee);
            } else {
                amount = amount.equals("@shippingFee@") ? amount.replace("@shippingFee@", shipping) : amount;
                balanceSteps.verifyBalanceInvoiceAmountIncrease(index, Double.parseDouble(amount.replace("$", "")));
            }
            balanceSteps.verifyBalanceInvoiceStatus(index, status);
            balanceSteps.verifyBalanceInvoiceCreatedDate(index, createdDate);
            balanceSteps.verifyBalanceInvoiceLatestTransactionDate(index, latestTransactionDate);
        }
    }

    @And("^Verify invoice detail")
    public void verifyInvoiceDetail$(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String index = SessionData.getDataTbVal(dataTable, row, "Index");
            String shop = SessionData.getDataTbVal(dataTable, row, "Shop");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String detail = SessionData.getDataTbVal(dataTable, row, "Detail");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String createdDate = SessionData.getDataTbVal(dataTable, row, "Created date");
            balanceSteps.openInvoice(index);
            commonSteps.switchToTheLastestTab();
            balanceSteps.verifyInvoiceShopName(shop);
            if (content.contains("@order@")) {
                content = content.replace("@order@", LoadObject.getProperty("order_1"));
            }
            balanceSteps.verifyInvoiceContent(content);

            if (detail.contains("@Domain@")) {
                balanceSteps.verifyInvoiceDetail(detail.replace("@Domain@", DomainDef.domainURL));
            } else if (detail.contains("@OrderName@")) {
                balanceSteps.verifyInvoiceDetail(detail.replace("@OrderName@", orderNumber));
            } else {
                balanceSteps.verifyInvoiceDetail(detail);
            }

            balanceSteps.verifyInvoiceType(type);

            if ("-@Price@".equals(amount)) {
                balanceSteps.verifyInvoiceAmountDecrease(DomainDef.domainPrice);
            } else if (amount.contains("-")) {
                balanceSteps.verifyInvoiceAmountDecrease(Double.parseDouble(amount.replace("$", "")));
            } else if (type.equals("OUT") && amount.contains("$")) {
                balanceSteps.verifyInvoiceAmountDecrease(Double.parseDouble(amount.replace("$", "")));
            } else if ("@autoTopup@".equals(amount)) {
                balanceSteps.verifyInvoiceAmountIncrease(autoTopUpAmt);
            } else if ("@transFee@".equals(amount)) {
                balanceSteps.verifyInvoiceAmountDecrease(transactionFee);
            } else {
                amount = amount.equals("@shippingFee@") ? amount.replace("@shippingFee@", shipping) : amount;
                balanceSteps.verifyInvoiceAmountIncrease(Double.parseDouble(amount.replace("$", "")));
            }
            balanceSteps.verifyInvoiceCreatedDate(createdDate);
        }
    }

    @And("^Verify invoice transactions$")
    public void verifyInvoiceTransactions$(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String index = SessionData.getDataTbVal(dataTable, row, "Index");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String date = SessionData.getDataTbVal(dataTable, row, "Date");

            balanceSteps.verifyInvoiceTransactionType(index, type);

            if (content.contains("@Domain@")) {
                balanceSteps.verifyInvoiceTransactionContent(index, content.replace("@Domain@", DomainDef.domainURL));
            } else if (content.contains("@OrderName@")) {
                balanceSteps.verifyInvoiceTransactionContent(index, content.replace("@OrderName@", orderNumber));
            } else if (content.contains("@order@")) {
                content = content.replace("@order@", LoadObject.getProperty("order_1"));
                balanceSteps.verifyInvoiceTransactionContent(index, content);
            } else {
                balanceSteps.verifyInvoiceTransactionContent(index, content);
            }

            if ("-@Price@".equals(amount)) {
                balanceSteps.verifyInvoiceTransactionAmountDecrease(index, DomainDef.domainPrice);
            } else if (amount.contains("-")) {
                balanceSteps.verifyInvoiceTransactionAmountDecrease(index, Double.parseDouble(amount.replace("$", "")));
            } else if (type.equals("OUT") && amount.contains("$")) {
                balanceSteps.verifyInvoiceTransactionAmountDecrease(index, Double.parseDouble(amount.replace("$", "")));
            } else if ("@autoTopup@".equals(amount)) {
                balanceSteps.verifyInvoiceTransactionAmountIncrease(index, autoTopUpAmt);
            } else if ("@transFee@".equals(amount)) {
                balanceSteps.verifyInvoiceTransactionAmountDecrease(index, transactionFee);
            } else if ("@refundSeller@".equals(amount)) {
                balanceSteps.verifyInvoiceTransactionAmountIncrease(index, amtRefundSeller);
            } else if ("@refundBuyer@".equals(amount)) {
                balanceSteps.verifyInvoiceTransactionAmountDecrease(index, amtRefundBuyer);
            } else {
                amount = amount.equals("@shippingFee@") ? amount.replace("@shippingFee@", shipping) : amount;
                balanceSteps.verifyInvoiceTransactionAmountIncrease(index, Double.parseDouble(amount.replace("$", "")));
            }
            balanceSteps.verifyInvoiceTransactionStatus(index, status);
            balanceSteps.verifyInvoiceTransactionDate(index, date);
        }
    }

    @And("Complete order by pay later")
    public void completeOrderByPayLater() {
        balanceSteps.completeOrderByPayLater();
    }

    @Then("Pay invoice open")
    public void payInvoiceOpen() {
        balanceSteps.payInvoiceOpen();
    }


    @And("^Change first name to \"([^\"]*)\"$")
    public void changeFirstNameTo(String firstName) {
        accountSteps.changeFirstNameTo(firstName);
    }

    @Then("enable auto top up and disable top up")
    public void enableAutoTopUpAndDisableTopUp() {
        balanceSteps.checkedAutoTopup(true);
        autoTopUpAmt = balanceSteps.getAmountAutoTopup();

        balanceSteps.checkedAutoTopup(false);
    }

    @And("get rate of transaction fee")
    public double getRateOfTransactionFee() {
        accountSteps.getCurrentPlan();
        switch (accountPage.currentPlan) {
            case "Basic Base":
                transactionRate = 0.02;
                break;
            case "Standard Base":
                transactionRate = 0.01;
                break;
            case "Pro Base":
                transactionRate = 0.005;
                break;
        }
        return transactionRate;
    }

    @When("^open product \"([^\"]*)\" and one page checkout without verify with \"([^\"]*)\" user$")
    public void openProductAndCheckoutWithoutVerify(String productName, String userType) {
        balanceSteps.openProductInSF(productName);
        balanceSteps.addToCartWithoutVerify();
        balanceSteps.clickToCheckoutWithoutVerify();

        onePageCheckoutSteps.chooseEmail(userType);
        onePageCheckoutSteps.enterShippingAddress();

        if (onePageCheckoutSteps.isOnePage()) {
            onePageCheckoutSteps.enterCardInformation();
            onePageCheckoutSteps.clickPlaceYourOrder();

        } else {
            customerInformationSteps.clickBtnContinueToShippingMethod();
            shippingMethodSteps.clickContinueToPaymentMethod();
            onePageCheckoutSteps.enterCardInformation();
            paymentMethodSteps.clickCompleteOrder();
            thankyouSteps.verifyThankYouPage();
        }
        thankyouSteps.verifyThankYouPage();

        orderNumber = thankyouSteps.getOrderNumber();
        orderNameList.add(thankyouSteps.getOrderNumber());
        totalAmt = orderSummarySteps.getTotalAmt();

    }

    @And("^input amount refund buyer on hive pbase with value$")
    public void inputAmountRefundBuyerOnHivePbaseWithValue(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String refundSellingPrice = SessionData.getDataTbVal(dataTable, row, "Refund selling price");
            String refundShippingFee = SessionData.getDataTbVal(dataTable, row, "Refund shipping fee");
            String recoverPaymentFee = SessionData.getDataTbVal(dataTable, row, "Recover payment fee");
            balanceSteps.inputRefundSellingPrice(refundSellingPrice);
            balanceSteps.inputRefundShippingFee(refundShippingFee);
            balanceSteps.inputRecoverPaymentFee(recoverPaymentFee);
            amtRefundBuyer = Double.parseDouble(refundSellingPrice);
        }
    }

    @And("^input amount refund seller on hive pbase with value$")
    public void inputAmountRefundSellerOnHivePbaseWithValue(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String refundBaseCost = SessionData.getDataTbVal(dataTable, row, "Refund base cost");
            String refundProcessingFee = SessionData.getDataTbVal(dataTable, row, "Refund processing fee");
            String refundPaymentFee = SessionData.getDataTbVal(dataTable, row, "Refund payment fee");
            balanceSteps.inputRefundBaseCost(refundBaseCost);
            balanceSteps.inputRefundProcessingFee(refundProcessingFee);
            balanceSteps.inputRefundPaymentFee(refundPaymentFee);
            amtRefundSeller = Double.parseDouble(refundBaseCost) + Double.parseDouble(refundProcessingFee) + Double.parseDouble(refundPaymentFee);
            balanceSteps.clickBtnRefund();
        }
    }

    @And("{string} payout to Shopbase Balance")
    public void payoutToShopbaseBalance(String isCheck) {
        if (isCheck.equals("Enable")) {
            balanceSteps.checkedConvertedSpayToBalance(true);
        } else {
            balanceSteps.checkedConvertedSpayToBalance(false);
        }
    }
}
