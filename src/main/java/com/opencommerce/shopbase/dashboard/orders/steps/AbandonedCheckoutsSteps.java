package com.opencommerce.shopbase.dashboard.orders.steps;

import au.com.bytecode.opencsv.CSVWriter;
import net.thucydides.core.annotations.Step;
import com.opencommerce.shopbase.dashboard.orders.pages.AbandonedCheckoutsPage;
import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.*;
import java.util.*;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


public class AbandonedCheckoutsSteps extends ScenarioSteps {
    AbandonedCheckoutsPage abandonedPage;
    OrderPage orderPage;

    @Step
    public String getCustomerName() {
        return abandonedPage.getCustomerName();
    }

    @Step
    public String getProductNameOnSF() {
        return abandonedPage.getProductNameOnSF();
    }

    public long getTimeOfAbandonedCheckout() {
        return abandonedPage.getTimeOfAbandonedCheckout();
    }

    @Step
    public boolean isNewestAbandonedCheckout() {
        long currentTime = System.currentTimeMillis();
        long abandonedcheckoutTime = getTimeOfAbandonedCheckout();
        long distance = (currentTime - abandonedcheckoutTime);
        if (distance >= 10800000 & distance <= 21600000) {
            return true;
        }
        return false;
    }

    @Step
    public String getEmailAddressOfBuyer() {
        return abandonedPage.getEmailAddressOfBuyer();
    }

    @Step
    public void navigateToAbandonedCheckoutDetails() {
        abandonedPage.navigateToTheNewestAbandonedCheckoutDetails();
    }

    public String expectedTimeLine(String timeline, String customerName, String email) {
        String expected = timeline;

        if (timeline.contains("{{customer.name}}")) {
            expected = expected.replace("{{customer.name}}", customerName);
        }
        if (timeline.contains("{{customer.email}}")) {
            expected = expected.replace("{{customer.email}}", email);
        }
        return expected;
    }

    public String smsTimeline(String timeline, String customerName, String phoneNumber) {
        String expected = timeline;
        if (timeline.contains("{{customer.name}}")) {
            expected = expected.replace("{{customer.name}}", customerName);
        }
        if (timeline.contains("{{customer.phone}}")) {
            expected = expected.replace("{{customer.email}}", phoneNumber);
        }
        return expected;
    }

    @Step
    public void verifyTimeline(String timeline, String customerName, String customerEmail) {
        String _expectedTimeline = expectedTimeLine(timeline, customerName, customerEmail);
        abandonedPage.verifyTextPresent(_expectedTimeline, true);
        abandonedPage.verifyTimelineUnique(_expectedTimeline);
    }

    @Step
    public void verifySmsTimline(String timeline, String cutomerName, String phoneNumber) {
        String expectedTimeline = smsTimeline(timeline, cutomerName, phoneNumber);
        abandonedPage.verifyTimeline(expectedTimeline);
    }


    @Step
    public void verifyProductNameOnDB(String productNameOnSF) {
        abandonedPage.verifyProductNameOnDB(productNameOnSF);
    }

    @Step
    public String getContactOnSF() {
        return abandonedPage.getContactOnSF();
    }

    @Step
    public void verifyShippingAddress(String contactOnSF) {
        abandonedPage.verifyShippingAddress(contactOnSF);
    }

    @Step
    public String getInputField() {
        return abandonedPage.getInputField("Email");
    }

    @Step
    public void addCustomerInfoToCSV(String fileName, String customerName, String productName, String contact) {
        File file = new File(fileName);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] header = {"Customer name", "Product name", "Email"};
            writer.writeNext(header);

            String[] customerInfo = {customerName, productName, contact};
            writer.writeNext(customerInfo);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public ArrayList<String> readingCustomerInfor(String filePath) throws IOException {

        ArrayList<String> infor = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine(); //ignoring

        System.out.println("==============reading filePath " + filePath);

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            for (int i = 0; i < fields.length; i++) {
                infor.add(fields[i]);
            }
            System.out.println("==============checkout infor: " + infor);
        }
        return infor;
    }


    @Step
    public void clickOnEmailNotSent() {
        abandonedPage.clickOnEmailNotSent();
    }

    @Step
    public void clickOnSendACartRecoveryEmail(String btnLabel) {
        abandonedPage.clickOnSendACartRecoveryEmail(btnLabel);
    }

    @Step
    public void openRecoverCartLink(boolean recoverLink) {
        abandonedPage.openRecoverCartLink(recoverLink);
    }

    @Step
    public String getContactOnDB() {
        return abandonedPage.getContactOnDB();
    }

    @Step
    public void verifyCustomerInformationPage(boolean recoverKey, String contactOnDB) {
        if (recoverKey) {
            assertThat(contactOnDB).isEqualTo(getContactOnSF());
        } else {
            assertThat(getInputField()).isEqualTo("");
        }
    }

    @Step
    public void switchToOldTab() {
        abandonedPage.switchToWindowWithIndex(0);
        abandonedPage.refreshPage();
    }

    @Step
    public void closeCustomerInformationPage() {
        abandonedPage.switchToWindowWithIndex(0);
    }

    @Step
    public void openMailBox(String emailAddress) {
        if (emailAddress.contains("mailtothis.com")) {
            abandonedPage.openMailBox(emailAddress, "https://www.mailinator.com");
        }
    }

    @Step
    public void openSignInTwilioPage(String url) {
        abandonedPage.openUrl(url);
        getDriver().manage().deleteAllCookies();
        abandonedPage.maximizeWindow();
        abandonedPage.clickLinkTextWithLabel("Log in");
        abandonedPage.waitForEverythingComplete();
    }

    public void enterEmail(String email) {
        abandonedPage.enterInputFieldWithLabel("Email", email);
        abandonedPage.clickBtn("Next");
        abandonedPage.waitForEverythingComplete();
    }

    public void enterPwd(String pwd) {
        abandonedPage.enterInputFieldWithLabel("Password", pwd);
    }

    @Step
    public void loginToTwilioDashboard(String url, String email, String pwd) {
        openSignInTwilioPage(url);
        enterEmail(email);
        enterPwd(pwd);
        abandonedPage.clickBtn("Log In");
        abandonedPage.waitForEverythingComplete();
    }

    @Step
    public void navigateToMessageLog() {
        abandonedPage.navigateToMessageLog();
    }

    @Step
    public void viewTheNewestMsgWithPhoneNumber(String phoneNumber) {
        abandonedPage.viewTheNewestMsgWithPhoneNumber(phoneNumber);
    }

    @Step
    public void verifyMsg(String msg) {
        abandonedPage.verifyMsg(msg);
    }

    @Step
    public void chooseCancelEmail(String subject) {
        abandonedPage.cancelEmailWithSubject(subject);
    }

    @Step
    public void chooseSendACartRecoveryEmail(String label) {
        abandonedPage.clickBtn(label);
        abandonedPage.waitForEverythingComplete();
    }

    @Step
    public void chooseSendNotification() {
        abandonedPage.clickBtn("Send notification");
        abandonedPage.waitForEverythingComplete();
    }

    @Step
    public void verifyMessage(String msg) {
        abandonedPage.verifyMessage(msg);
    }


    public String getCheckoutID() {
        return abandonedPage.getCheckoutID();
    }

    public void searchCheckoutByID(String checkoutID) {
        abandonedPage.enterInputFieldThenEnter("Search checkouts", checkoutID, 1);
        waitABit(10000);
    }

    public void verifyThatAbandonedCheckoutDeleted() {
        abandonedPage.verifyTextPresent("No abandoned checkouts found", true);
        abandonedPage.verifyTextPresent("You can find abandoned checkouts by changing your search or filtering options", true);
    }

    public void verifyEmailContent(String content) {
        abandonedPage.verifyEmailContent(content, true);
    }

    @Step
    public void searchThenSelectAC(String criteria) {
        String customerName = shippingAddressHashMap.get("Shipping Name");
        abandonedPage.searchAbandonedCheckout(criteria);
        abandonedPage.selectTheAbandonedCheckoutByCustomerName(customerName);
        checkoutID = abandonedPage.getCheckoutID();
    }

    @Step
    public void verifyCustomerName(String expectedCustomerName) {
        String actualCustomerName = abandonedPage.getCustomerName();
        assertThat(actualCustomerName).isEqualTo(expectedCustomerName);
    }

    @Step
    public void verifyCustomerEmail(String expectedCustomerEmail) {
        String actualCustomerEmail = abandonedPage.getEmailAddressOfBuyer();
        assertThat(actualCustomerEmail).isEqualTo(expectedCustomerEmail);
    }

    @Step
    public void verifyRecoverLink(String checkoutUrl) {
        String actualRecoverLink = abandonedPage.getActualRecoveryLink();
        assertThat(actualRecoverLink).startsWith(checkoutUrl);
    }

    @Step
    public void verifyCheckoutDetail(HashMap<String, List<String>> productListAdded, String subTotal) {
        String prodName = "", varName = "", quantity = "", price = "";
        Set<Map.Entry<String, List<String>>> setHsm = productListAdded.entrySet();
        for (Map.Entry<String, List<String>> prodVarInfo : setHsm) {
            prodName = prodVarInfo.getKey().split(":")[0];
            varName = prodVarInfo.getKey().split(":")[1];

            quantity = prodVarInfo.getValue().get(1);
            price = prodVarInfo.getValue().get(0);

            abandonedPage.verifyProductInfo(prodName, varName, price, quantity);
            abandonedPage.verifySubTotal(subTotal);
        }
    }

    @Step
    public void verifyRecoveryStatus(String recoveryStatus) {
        String actualRecoveryStatus = abandonedPage.getRecoveryStatus();
        assertThat(actualRecoveryStatus).isEqualTo(recoveryStatus);
    }

    @Step
    public void searchAC(String customerEmail) {
        abandonedPage.searchAbandonedCheckout(customerEmail);
    }

    @Step
    public void verifyACInfo(String criteria, String expectedVal) {
        String actualVal = "";
        switch (criteria.toLowerCase()) {
            case "customer email":
                actualVal = abandonedPage.getEmailAddressOfBuyer();
                break;
            case "customer name":
                actualVal = abandonedPage.getCustomerName();
                break;
            case "checkout id":
                actualVal = abandonedPage.getCheckoutID();
                break;
            case "shipping address":
                abandonedPage.verifyShippingAddressMatchedWith(expectedVal);
                break;
            default:
                fail();
        }
        if (!criteria.equalsIgnoreCase("shipping address"))
            assertThat(actualVal).isEqualTo(expectedVal);
    }


    @Step
    public void backToTheListOfAC() {
        abandonedPage.clickTheBreadCrumb("Abandoned Checkouts");
    }

    @Step
    public void selectBlock(String blockName){
        abandonedPage.selectBlock(blockName);
    }
}
