package opencommerce.balance;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import common.utilities.LoadObject;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.balance.steps.AuthorizeFirstChargeSteps;

public class AuthorizeFirstChargeDef {
    @Steps
    AuthorizeFirstChargeSteps authorizeStep;


    String userName = LoadObject.getProperty("user.name");

    @And("^Navigate to first charge review in hive$")
    public void NavigateToFirstChargeReviewInHive() {
//        authorizeStep.clickOnButtonBalance();
        authorizeStep.clickOnButtonFirstChargeReview();
    }

    @And("Enter bin {string} and user name")
    public void enterBin(String bin) {
        authorizeStep.clickOnButonFilter();
        authorizeStep.selectBin();
        authorizeStep.selectUserEmail();
        authorizeStep.clickSelectBoxBin();
        authorizeStep.enterUserName(userName);
        authorizeStep.enterBin(bin);
        authorizeStep.clickOnId();
    }

    @And("^Approve card$")
    public void approveRejectCard() {
//        authorizeStep.selectStatus();
//        authorizeStep.clickSelectBox();
//        authorizeStep.selectWaitingReview();
//        authorizeStep.clickOnButonFilter();
//        authorizeStep.clickButtonFilter();
//        authorizeStep.clickOnId32();
//        cần  bổ sung thêm verify được có firtcharge mới
        authorizeStep.clickButtonApprove();
        authorizeStep.clickButtonYes();
//        verify lại status của thẻ.
    }

    @And("^clear status card$")
    public void clearStatusCard() {
        authorizeStep.clearStatusCard();
        authorizeStep.clickButtonYes();
    }

    @And("^Verify card$")
    public void verifyCard() {
        authorizeStep.clickOnButtonBalance();
        authorizeStep.clickOnButtonFirstChargeReview();
        authorizeStep.clickOnButonFilter();
        authorizeStep.selectBin();
        authorizeStep.clickSelectBoxBin();
    }
    @And("^Reject card$")
    public void rejectCard(){
        authorizeStep.rejectCard();
        authorizeStep.clickButtonYes();
    }

    @And("verify card status is {string}")
    public void verifyCardStatusIs(String status) {
        authorizeStep.verifyCardStatusIs(status);
    }

    @And("Filter {string} and user name")
    public void filterAndUserName(String bin) {
        authorizeStep.clickOnButonFilter();
        authorizeStep.selectBin();
        authorizeStep.selectUserEmail();
        authorizeStep.clickSelectBoxBin();
        authorizeStep.enterUserName(userName);
        authorizeStep.enterBin(bin);
    }
}




//        authorizeStep.clickOnButtonBalance();
//        authorizeStep.clickOnButtonFirstChargeReview();
//        authorizeStep.clickOnButonFilter();
//        authorizeStep.selectStatus();
//        authorizeStep.clickSelectBox();
//        authorizeStep.selectWaitingReview();
//        authorizeStep.clickButtonFilter();
//        authorizeStep.clickOnId30();