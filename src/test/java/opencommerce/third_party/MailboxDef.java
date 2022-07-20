package opencommerce.third_party;

import com.opencommerce.shopbase.dashboard.third_party.MailToThisSteps.MailboxSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static common.utilities.LoadObject.convertStatus;

public class MailboxDef {
    @Steps
    MailboxSteps mailboxSteps;

    @And("^open mailbox of buyer with subjectddd$")
    public void openMailboxOfBuyer(String subject) {
        mailboxSteps.enterEmail(customerEmail);
        mailboxSteps.openEmailWithSubject(subject);
    }

    @Then("^click on button \"([^\"]*)\" from email$")
    public void clickOnButtonFromEmail(String label) {
        mailboxSteps.clickOnButtonFromEmail(label);
    }

    @Then("^click to unsubscribe from email$")
    public void clickToUnsubscribeFromEmail() {
        mailboxSteps.clickOnLinkTextFromEmail("Unsubscribe");
    }

    @And("^open mailbox of buyer with subject$")
    public void openMailboxOfBuyerWithSubject(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String _simageProduct = SessionData.getDataTbVal(dataTable, row, "Image product");
            mailboxSteps.openMailBoxWithSubject(customerEmail, subject);
            if (!content.isEmpty())
                mailboxSteps.verifyEmailContent(content);
            if(!_simageProduct.isEmpty()){
                String imageProducts[]=_simageProduct.split(";");
                for(String imageProduct:imageProducts)
                    mailboxSteps.verifyImageProduct(imageProduct);
            }
        }
    }
}
