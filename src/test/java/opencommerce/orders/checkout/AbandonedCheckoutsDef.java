package opencommerce.orders.checkout;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import com.opencommerce.shopbase.dashboard.third_party.MailToThisSteps.MailboxSteps;
import com.opencommerce.shopbase.storefront.steps.shop.PaymentMethodSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.AbandonedCheckoutsSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CustomerInformationSteps;
import opencommerce.notification.steps.NotificationSettingSteps;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.opencommerce.shopbase.OrderVariable.*;
import static junit.framework.TestCase.fail;

public class AbandonedCheckoutsDef {
    @Steps
    AbandonedCheckoutsSteps abandonedCheckoutsSteps;
    @Steps
    OrderSteps orderSteps;
    @Steps
    SettingSteps settingSteps;
    @Steps
    LoginDashboardSteps loginDashboardSteps;
    @Steps
    MailboxSteps mailboxSteps;

    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    ThankyouSteps thankYouSteps;
    @Steps
    NotificationSettingSteps notificationSettingSteps;
    @Steps
    CommonSteps commonSteps;

    public String customerNameOnSF;
    public String productNameOnSF;
    public String contactOnSF;
    public String contactOnDB;
    public static String CSV_FILE_PATH;
    public String subject;
    public String content;
    String customerName = "";
    String phoneNumber = "4047957606";
    public String checkoutUrl = "";

    @Then("^enter customer information$")
    public void enter_customer_information() {
        customerInformationSteps.enterCustomerInformation(true);
    }

    @And("^get the abandoned checkout details on storefront and save checkout information to csv file$")
    public void get_abandoned_checkout_details_on_storefront() {
        customerNameOnSF = abandonedCheckoutsSteps.getCustomerName();

        customerInformationSteps.clickBtnContinueToShippingMethod();

        productNameOnSF = abandonedCheckoutsSteps.getProductNameOnSF();
        contactOnSF = abandonedCheckoutsSteps.getContactOnSF();

        String env = LoadObject.getProperty("gapi.url").split("https://")[1];
        switch (env) {
            case "api.shopbase.com":
                CSV_FILE_PATH = LoadObject.getFilePath("prod_customer_information.csv");
                System.out.println("============== File path is: " + CSV_FILE_PATH);
                break;
            case "gapi.stag.shopbase.net":
                CSV_FILE_PATH = LoadObject.getFilePath("stag_customer_information.csv");
                break;
            case "gpai.dev.shopbase.net":
                CSV_FILE_PATH = LoadObject.getFilePath("dev_customer_information.csv");
                break;
            default:
                fail();
        }
        abandonedCheckoutsSteps.addCustomerInfoToCSV(CSV_FILE_PATH, customerNameOnSF, productNameOnSF, contactOnSF);
        customerInformationSteps.clickBtnContinueToPaymentMethod();
    }

    @And("^open csv file containing abandoned checkout information$")
    public void open_csv_file_containing_abandoned_checkout_information() throws Exception {
        String env = LoadObject.getProperty("gapi.url").split("https://")[1];
        switch (env) {
            case "api.shopbase.com":
                CSV_FILE_PATH = LoadObject.getFilePath("prod_customer_information.csv");
                break;
            case "gapi.stag.shopbase.net":
                CSV_FILE_PATH = LoadObject.getFilePath("stag_customer_information.csv");
                break;
            case "gpai.dev.shopbase.net":
                CSV_FILE_PATH = LoadObject.getFilePath("dev_customer_information.csv");
                break;
            default:
                fail();
        }

        ArrayList<String> customerInfor = abandonedCheckoutsSteps.readingCustomerInfor(CSV_FILE_PATH);
        for (String number : customerInfor) {
            customerNameOnSF = customerInfor.get(0);
            productNameOnSF = customerInfor.get(1);
            contactOnSF = customerInfor.get(2);
        }
    }

    @And("^verify that abandoned checkouts details on dashboard should be the same as storefront$")
    public void verify_abandoned_checkouts_details() {
        String customerName = shippingAddressHashMap.get("Shipping Name");

        abandonedCheckoutsSteps.verifyCheckoutDetail(productListAdded, subTotal);
        abandonedCheckoutsSteps.verifyRecoverLink(checkoutUrl);
        abandonedCheckoutsSteps.verifyCustomerName(customerName);
        abandonedCheckoutsSteps.verifyCustomerEmail(customerEmail);
        abandonedCheckoutsSteps.verifyShippingAddress(shippingAddress);
    }

    @Then("^send a cart recovery email$")
    public void send_a_cart_recovery_email() {
        abandonedCheckoutsSteps.clickOnEmailNotSent();
        abandonedCheckoutsSteps.navigateToAbandonedCheckoutDetails();
        abandonedCheckoutsSteps.verifyProductNameOnDB(productNameOnSF);
        abandonedCheckoutsSteps.verifyShippingAddress(contactOnSF);
        abandonedCheckoutsSteps.clickOnSendACartRecoveryEmail("Send a cart recovery email");
    }

    @And("^checkout by recover cart link in abandoned checkout details page$")
    public void verify_checkout_link_can_checkout_successfully(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String sCase = SessionData.getDataTbVal(dataTable, row, "Case");
            boolean isPrefill = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Pre-fill customer information or not pre-fill"));
            if (sKey.equals("1")) {
                abandonedCheckoutsSteps.openRecoverCartLink(false);
                abandonedCheckoutsSteps.verifyCustomerInformationPage(isPrefill, contactOnDB);
                abandonedCheckoutsSteps.closeCustomerInformationPage();
            }
            if (sKey.equals("2")) {
                contactOnDB = abandonedCheckoutsSteps.getContactOnDB();
                abandonedCheckoutsSteps.openRecoverCartLink(true);
                abandonedCheckoutsSteps.verifyCustomerInformationPage(isPrefill, contactOnDB);
                customerInformationSteps.clickBtnContinueToPaymentMethod();
                paymentMethodSteps.enterPaymentMethodByStripe();
                paymentMethodSteps.selectBillingAddress("Same as shipping address");
                paymentMethodSteps.clickBtnCompleteOrder();
                thankYouSteps.verifyThankYouPage();
                orderNumber = thankYouSteps.getOrderNumber().split("Order")[1];
                abandonedCheckoutsSteps.switchToOldTab();
            }
        }
    }


    @And("^verify that the abandoned checkout should be convert to order$")
    public void verify_abandoned_checkout_convert_to_order() {
        orderSteps.clickNameOrderOnList(orderNumber);
    }

    @And("^enter customer information but do not enter payment method$")
    public void enter_customer_information_but_do_not_enter_payment_method() {
        long currentTime = System.currentTimeMillis();
        String currentCheckoutTime = Long.toString(currentTime);
        customerInformationSteps.enterCustomerInformationOnSF(true, currentCheckoutTime);
    }

    @Then("^login to buyer's email$")
    public void login_to_buyer_email() {
        if (!contactOnSF.isEmpty()) {
            abandonedCheckoutsSteps.openMailBox(contactOnSF);
        }
    }

    @Then("^open mailbox with the subject then verify the content$")
    public void open_mailbox_with_the_subject_then_verify_the_content(List<List<String>> dataTable) {
        customerEmail = abandonedCheckoutsSteps.getEmailAddressOfBuyer();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            content = SessionData.getDataTbVal(dataTable, row, "Content");
            mailboxSteps.openMailBoxWithSubject(customerEmail, subject);
            mailboxSteps.verifyEmailContent(content);
            commonSteps.backToThePreviousScreen();
        }
    }

    @Given("^Set up the schedule to send abandoned checkout \"([^\"]*)\"$")
    public void set_up_the_schedule_to_send_abandoned_checkout_notification(String typeNoti, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String notification = SessionData.getDataTbVal(dataTable, row, "Notification");
            String sendAfter = SessionData.getDataTbVal(dataTable, row, "Send after");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            String time = sendAfter.split(" ")[0];
            String unit = sendAfter.split(" ")[1];

            if (typeNoti.equalsIgnoreCase("emails")) {
                settingSteps.turnOnSendAbandonedCheckoutEmails();
                settingSteps.checkOnCheckbox(notification, true);
                settingSteps.enterScheduleSend(notification, time);
                settingSteps.selectUnit(notification, unit);
            } else if (typeNoti.equalsIgnoreCase("text message")) {
                settingSteps.turnOnSendAbandonedCheckoutSMS();
                if (sendAfter.equalsIgnoreCase("unchecked")) {
                    settingSteps.checkOnCheckbox(notification, false);
                } else {
                    settingSteps.checkOnCheckbox(notification, true);
                    settingSteps.enterScheduleSend(notification, time);
                    settingSteps.selectUnit(notification, unit);
                    settingSteps.enterMsg(notification, msg);
                }
            }
        }
        if (settingSteps.isChanged()) {
            settingSteps.saveChanges();
        }
    }

    @Then("^verify template of abandoned checkout emails$")
    public void verify_template_of_abandoned_checkout_emails(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String abandonedCheckoutOrdinal = SessionData.getDataTbVal(dataTable, row, "Abandoned checkout ordinal");
            subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            content = SessionData.getDataTbVal(dataTable, row, "Content");

            notificationSettingSteps.chooseLinkButton(abandonedCheckoutOrdinal);
            notificationSettingSteps.verifySubject(subject);
            notificationSettingSteps.backToNotificationPage();
        }
    }

    @Then("^select the newest abandoned checkout$")
    public void select_the_newest_abandoned_checkout() {
        abandonedCheckoutsSteps.navigateToAbandonedCheckoutDetails();
        checkoutID = abandonedCheckoutsSteps.getCheckoutID();
    }

    @Then("^verify the email log should be shown in timeline$")
    public void verify_the_email_log_should_be_shown_in_timeline(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String timeline = SessionData.getDataTbVal(dataTable, row, "Timeline");
            customerName = abandonedCheckoutsSteps.getCustomerName();
            customerEmail = abandonedCheckoutsSteps.getEmailAddressOfBuyer();
            abandonedCheckoutsSteps.verifyTimeline(timeline, customerName, customerEmail);
        }
    }

    @Given("^verify the sms log should be shown in timeline$")
    public void verify_the_sms_log_should_be_shown_in_timeline(List<List<String>> dataTable) {
        String dataTableKey = "sms timeline";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String timeline = SessionData.getDataTbVal(dataTable, row, "Timeline");

            abandonedCheckoutsSteps.verifySmsTimline(timeline, customerName, phoneNumber);
        }
    }

    @Then("^select the newest created shop$")
    public void select_the_newest_created_shop() {
        loginDashboardSteps.selectTheNewstShop();
    }


    @Given("^login to twilio dashboard$")
    public void login_to_twilio_dashboard() {
        try {
            String url = LoadObject.getProperty("twilio.url");
            String twilio_email = LoadObject.getProperty("twilio.email");
            String twilio_pwd = LoadObject.getProperty("twilio.pwd");

            abandonedCheckoutsSteps.loginToTwilioDashboard(url, twilio_email, twilio_pwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^navigate to programmable SMS dashboard to view all message log$")
    public void navigate_to_programmable_SMS_dashboard_to_view_all_message_log() {
        abandonedCheckoutsSteps.navigateToMessageLog();
    }


    @Then("^verify the sms should be sent$")
    public void verify_the_sms_should_be_sent(List<List<String>> dataTable) {
        String dataTableKey = "abandoned checkout timeline";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "To Phone number");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            abandonedCheckoutsSteps.viewTheNewestMsgWithPhoneNumber(phoneNumber);
            abandonedCheckoutsSteps.verifyMsg(msg);
        }
    }

    @Then("^choose cancel email with subject \"([^\"]*)\"$")
    public void choose_cancel_email_with_subject_was_scheduled_to_be_sent(String subject) {
        abandonedCheckoutsSteps.chooseCancelEmail(subject);
    }


    @Then("^click on \"([^\"]*)\" button to send email manually$")
    public void click_on_button_to_send_email_manually(String label, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            abandonedCheckoutsSteps.chooseSendACartRecoveryEmail(label);
            abandonedCheckoutsSteps.chooseSendNotification();
            if (msg.contains("{{email address}}")) {
                msg = msg.replace("{{email address}}", customerEmail);
                abandonedCheckoutsSteps.verifyMessage(msg);
            }
        }
    }

    @And("^verify that the above abandoned checkout should be deleted from checkout list$")
    public void verifyThatTheAboveAbandonedCheckoutShouldBeDeletedFromCheckoutList() {
        abandonedCheckoutsSteps.searchCheckoutByID(checkoutID);
        abandonedCheckoutsSteps.verifyThatAbandonedCheckoutDeleted();
    }

    @And("^search then select abandoned checkout by (customer email|customer name)$")
    public void searchThenSelectAbandonedCheckoutByCustomerEmail(String criteria) {
        switch (criteria.toLowerCase()) {
            case "customer email":
                criteria = customerEmail;
                break;
            default:
                criteria = shippingAddressHashMap.get("Shipping Name");
                break;
        }
        abandonedCheckoutsSteps.searchThenSelectAC(criteria);
    }

    @And("get checkout url")
    public void getCheckoutUrl() {
        checkoutUrl = commonSteps.getCheckoutUrl().split("\\?step")[0];
    }

    @And("search then select abandoned checkout by checkout id {string}")
    public void searchThenSelectAbandonedCheckoutByCheckoutId(String checkoutID) {
        abandonedCheckoutsSteps.searchThenSelectAC(checkoutID);
    }

    @When("open recovery link in a new tab")
    public void openRecoveryLink() {
        abandonedCheckoutsSteps.openRecoverCartLink(true);
    }

    @And("verify recover status is {string}")
    public void verifyRecoverStatusIs(String recoveryStatus) {
        abandonedCheckoutsSteps.verifyRecoveryStatus(recoveryStatus);
    }

    @And("search abandoned checkout by customer email")
    public void searchAbandonedCheckoutByCustomerEmail() {
        abandonedCheckoutsSteps.searchAC(customerEmail);
    }

    @And("search checkouts by the below mentioned criteria then verify search function working properly")
    public void searchCheckoutsByTheBelowMentionedCriteriaThenVerifySearchFunctionWorkingProperly(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String criteria = SessionData.getDataTbVal(dataTable, row, "Criteria");
            String expectedValue = "";
            switch (criteria.toLowerCase()) {
                case "customer email":
                    expectedValue = customerEmail;
                    break;
                case "customer name":
                    expectedValue = shippingAddressHashMap.get("Shipping Name");
                    break;
                case "checkout id":
                    expectedValue = checkoutID;
                    break;
                case "shipping address":
                    expectedValue = shippingAddressHashMap.get("Shipping Address1");
                    break;
                default:
                    fail();
            }
            abandonedCheckoutsSteps.searchThenSelectAC(expectedValue);
            abandonedCheckoutsSteps.verifyACInfo(criteria, expectedValue);
            abandonedCheckoutsSteps.backToTheListOfAC();
        }
    }

    @Then("back to the list of abandoned checkouts")
    public void backToTheListOfAbandonedCheckouts() {
        abandonedCheckoutsSteps.backToTheListOfAC();
    }

    @Then("^verify fail reason captured in timeline$")
    public void verifyFailReasonInTimeLine() {
        if ("Paypal-Pro".equalsIgnoreCase(paymentGateway)) {
            paymentGateway = "PayPal Pro";
        }
//        String content = "Unable to process payment via " + paymentGateway + ". Reason: \"" + errorMessage + "\"";
        String content = "";
        switch (key) {
            case "1":
                content = "Unable to process a payment for " + totalAmtBefore.split("\\p{Sc}|\\s", 3)[1] + " USD via " + paymentGateway + " on the Credit Card ending in " + endingCardNo + ". Reason: \"" + errorMessage + "\"";
                break;
            case "2":
                content = "Unable to process a payment for " + totalAmtBefore.split("\\p{Sc}|\\s", 3)[1] + " USD via " + paymentGateway + ". Reason: \"" + errorMessage + "\"";
                break;
            case "3":
                content = "Unable to process payment via " + paymentGateway + ". Reason: \"" + errorMessage + "\"";
                break;
            default:
                fail();
        }
        System.out.println("content : " + content);
        orderSteps.verifyOrderTimeline(content);
    }


    @Then("select block {string}")
    public void selectBlock(String blockName) {
        abandonedCheckoutsSteps.selectBlock(blockName);
    }
}

