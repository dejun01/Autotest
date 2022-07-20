package com.opencommerce.shopbase.dashboard.third_party.MailToThisSteps;

import com.opencommerce.shopbase.dashboard.third_party.MailToThisPage.MailboxPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;

public class MailboxSteps extends ScenarioSteps {
    MailboxPage mailboxPage;

    public void enterEmail(String email) {
        mailboxPage.enterEmail(email);
    }

    public void openEmailWithSubject(String subject) {
        mailboxPage.openEmailWithSubject(subject);
    }

    public void clickOnButtonFromEmail(String label) {
        mailboxPage.switchToIFrameMail();
        mailboxPage.clickLinkTextWithLabel(label);
        mailboxPage.switchToLatestTab();
    }

    public void clickOnLinkTextFromEmail(String label) {
        mailboxPage.switchToIFrameBodyMail();
        mailboxPage.clickLinkTextWithLabel(label);
        mailboxPage.switchToLatestTab();
    }

    @Step
    public void openMailBoxWithSubject(String emailAddress, String subject) {
        if (subject.contains("{{order_number}}")) {
            subject = subject.replace("{{order_number}}", orderNumber);
        }
        mailboxPage.verifyReceivedMail(emailAddress, subject);
    }

    public void verifyEmailContent(String content) {
        mailboxPage.verifyEmailContent(content, true);
    }

    @Step
    public void verifyImageProduct(String imageProduct) {
//        mailboxPage.verifyImageProduct();
    }
}
