package opencommerce.online_store.page;

import com.opencommerce.other.yopmail.review.steps.NotificationsSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.AllReviewsSteps;
import com.opencommerce.shopbase.storefront.steps.contactus.ContactUsPBaseStep;

import common.utilities.SessionData;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;


public class ContactUsPBaseDef {
    @Steps
    ContactUsPBaseStep contactUsPBaseStep;
    @Steps
    NotificationsSteps notificationsSteps;
    @Steps
    AllReviewsSteps allReviewsSteps;

    public String mail;

    @When("input data for Contact us page as {string}")
    public void inputDataForContactUsPageAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String issueType = SessionData.getDataTbVal(dataTable, row, "Issue type");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String mss = SessionData.getDataTbVal(dataTable, row, "Message sucess");
            contactUsPBaseStep.enterInputField("name", name);
            contactUsPBaseStep.enterInputField("email", email);
            contactUsPBaseStep.enterInputField("phone", phone);
            contactUsPBaseStep.selectIssueType(issueType);
            contactUsPBaseStep.enterInputField("order_number", orderNumber);
            contactUsPBaseStep.enterMessage(message);
            contactUsPBaseStep.clickbtnSend();
            if (mss.isEmpty())
                contactUsPBaseStep.verifyMess(mss);
            else contactUsPBaseStep.verifySuccessMss(mss);
        }
    }

    @Given("set customer email")
    public void setCustomerEmail(){
        mail = allReviewsSteps.genEmail("@mailbiscuit.com");
        contactUsPBaseStep.setCustomerEmail(mail);
    }

    @Then("verify send mail to mailbiscuit.com as {string}")
    public void verifySendMailToCustomMail(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String issueType = SessionData.getDataTbVal(dataTable, row, "Issue type");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            notificationsSteps.openURL("https://fakermail.com/");
            contactUsPBaseStep.typeMailMailinator(mail);
            contactUsPBaseStep.openEmailWithSubject("New customer message on");
            contactUsPBaseStep.verifySendMailToMailinator(issueType, orderNumber, message);
        }
    }

}
