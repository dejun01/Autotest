package opencommerce.partners;

import com.google.gson.JsonArray;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.onboarding.steps.OnboardingSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import com.opencommerce.shopbase.hive_sbase.steps.AffiliateRequestHiveSteps;
import com.opencommerce.shopbase.partners.steps.AffiliateSteps;
import com.opencommerce.shopbase.partners.steps.HomeSteps;
import com.opencommerce.shopbase.partners.steps.InvitaionCodeSteps;
import com.opencommerce.shopbase.partners.steps.YourReferrtalsSteps;
import com.opencommerce.shopbase.partners.steps.*;
import com.opencommerce.shopbase.storefront.steps.shop.CustomerInformationSteps;
import com.opencommerce.shopbase.storefront.steps.shop.PaymentMethodSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderId;

public class AffiliateDef {
    @Steps
    AffiliateSteps affSteps;
    @Steps
    SignUpSteps signUpSteps;
    @Steps
    HomeSteps homeSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    OnboardingSteps onboardingSteps;
    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    com.opencommerce.shopbase.dashboard.home.steps.HomeSteps homeDBSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    SettingSteps settingSteps;
    @Steps
    InvitaionCodeSteps inviCodeSteps;
    @Steps
    YourReferrtalsSteps yourReferrtalsSteps;
    @Steps
    AffiliateRequestSteps affRequestSteps;
    @Steps
    AffiliateRequestHiveSteps affiliateRequestHiveSteps;
    @Steps
    PrintbaseAPI printbaseAPI;
    @Steps
    PaymentMethodSteps paySteps;


    HashMap<String, List<String>> pre = new HashMap<>();
    HashMap<String, List<String>> current = new HashMap<>();
    HashMap<String, List<String>> preCB = new HashMap<>();
    HashMap<String, List<String>> currentCB = new HashMap<>();
    String quantity = "";
    String shop = LoadObject.getProperty("shop");
    String fpr = LoadObject.getProperty("fpr");
    String partnerLink = LoadObject.getProperty("partnerLink");
    String partnerLinkSU = LoadObject.getProperty("partnerLinkSU");
    long currentTime = System.currentTimeMillis();
    String user = LoadObject.getProperty("user.name");
    String pw = LoadObject.getProperty("user.pwd");
    String email = "";
    String mail = "";
    String emailSU;
    List<String> preClick = new ArrayList<>();

    @Given("user open link sign up affiliate on shopbase.com")
    public void userOpenLinkSignUpAffiliate() {
        affSteps.openAffiliateLink();
        affSteps.clickBtnJoinTheAffiliateProgram();
        affSteps.verfifySignUpScreen();
    }

    @When("sign up account affiliate")
    public void signUpAccountAffiliate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pw = SessionData.getDataTbVal(dataTable, row, "Password");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            long currentTime = System.currentTimeMillis();
            if (email.equalsIgnoreCase("@EMAIL@")) {
                email = "Email" + currentTime + "@beeketing.net";
            }

            signUpSteps.enterEmail(email);
            signUpSteps.enterPassword(pw);
            signUpSteps.clickBtnSignUp();
            if (!msg.contains("Successfully")) {
                signUpSteps.verifyErrorMessage(msg);
            } else homeSteps.verifyHomePartner();
        }

    }

    @Given("user sign in account partners")
    public void userSignInAccountPartners() {
        commonSteps.openUrl(partnerLink);
        signUpSteps.enterEmail(user);
        signUpSteps.enterPassword(pw);
        signUpSteps.clickbtnSignIn();
        homeSteps.verifyHomePartner();
    }

    @And("get statistics affiliate by api")
    public void getStatisticsAffiliateByApi(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            current = affSteps.getStatisticsAffiliateByApi(time);
        }
    }

    @And("get statistics affiliate by api before")
    public void getStatisticsAffiliateByApiBefore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            pre = affSteps.getStatisticsAffiliateByApiBefore(time);
        }
    }

    @And("get statistics affiliate by api after")
    public void getStatisticsAffiliateByApiAfter(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            current = affSteps.getStatisticsAffiliateByApi(time);
        }
    }


    @Then("verify statistics affiliate by api")
    public void verifyStatisticsAffiliateByApi() {
        affSteps.verifyStatistics(pre, current, quantity, 0);
    }

    @And("checkout an order on storefront")
    public void checkoutAnOrderOnStorefront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");

            loginSteps.openShop(shop);
            productSteps.searchAndSelectProduct(productName);
            productSteps.selectVariant(size);
            productSteps.setQuantityProduct(quantity);
            productSteps.clickAddToCart();
            productSteps.clickCheckOut();

            customerInformationSteps.enterEmail("tester@mailtothis.com");
            customerInformationSteps.enterFirstName("firstName");
            customerInformationSteps.enterLastName("lastName");
            customerInformationSteps.selectCountry("United States", "California");
            customerInformationSteps.enterAddress("814 Mission Street");
            customerInformationSteps.enterApartment("814");
            customerInformationSteps.enterZipCode("90001");
            customerInformationSteps.enterCity("San Francisco");
            customerInformationSteps.enterPhone("4047957606", true);

            paySteps.enterPaymentMethodByStripe();
            paymentMethodSteps.clickBtnPlaceYourOrder();
            thankyouSteps.verifyThankYouPage();

        }
    }

    @When("^sign up an account shopbase with refer link  is \"([^\"]*)\"$")
    public void signUpAnAccountShopbaseWithReferLink(String partner, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pw = SessionData.getDataTbVal(dataTable, row, "Password");
            String shopname = SessionData.getDataTbVal(dataTable, row, "Shopname");
            String firstname = SessionData.getDataTbVal(dataTable, row, "Firstname");
            String lastname = SessionData.getDataTbVal(dataTable, row, "Lastname");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String perlocation = SessionData.getDataTbVal(dataTable, row, "Personal location");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String socialFile = SessionData.getDataTbVal(dataTable, row, "Social file");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business Type");
            String revenue = SessionData.getDataTbVal(dataTable, row, "Revenue");
            String storeType = SessionData.getDataTbVal(dataTable, row, "Store type");
            String question = SessionData.getDataTbVal(dataTable, row, "Question");
            String ans = SessionData.getDataTbVal(dataTable, row, "Answer");
            String question1 = SessionData.getDataTbVal(dataTable, row, "Question1");
            String ans1 = SessionData.getDataTbVal(dataTable, row, "Answer1");
            String question2 = SessionData.getDataTbVal(dataTable, row, "Question2");
            String ans2 = SessionData.getDataTbVal(dataTable, row, "Answer2");

            email += currentTime + "@mailinator.girl-viet.com";
            shopname = shopname + currentTime;
            System.out.println("email: " + email);

            if (Boolean.parseBoolean(partner)) {
                affSteps.openReferLink(fpr);
            } else {
                loginSteps.openSignInPage();
                loginSteps.clickSignUp();
            }

            loginSteps.refreshPage();
            loginSteps.enterEmail(email);
            loginSteps.enterPassword(pw);
            loginSteps.enterShopname(shopname);
            loginSteps.clickBtnSignUp();

//             //start page
//            onboardingSteps.verifyIntroductionPage();
//            onboardingSteps.clickBtnStartNow("Start now");

            // add contact information
            onboardingSteps.verifyAddYourContactPage();
            onboardingSteps.inputFirstName(firstname);
            onboardingSteps.inputLastName(lastname);
            signUpSteps.inputStoreCountry(country);
            signUpSteps.inputPersonalLocation(perlocation);
            onboardingSteps.inputContact(phone);
            onboardingSteps.inputSocialProfile(socialFile);
            onboardingSteps.clickbtnNext();

            onboardingSteps.chooseBusinessType(businessType);
            onboardingSteps.chooseRevenue(revenue);
            onboardingSteps.clickbtnNextonBusinessTypeScreen();
            onboardingSteps.chooseStoreType(businessType, storeType);
//            onboardingSteps.chooseProductCategory(productCategory);
//
           //answer question
            if (onboardingSteps.verifyCustomizedQuestionsPage()) {
                onboardingSteps.answerQuestions(question, ans);
                onboardingSteps.answerQuestions(question1, ans1);
                onboardingSteps.answerQuestions(question2, ans2);
                onboardingSteps.clickTakeMeToMyStore();
//                onboardingSteps.clickbtnNext();
            }
           //verify create store successfully
            homeDBSteps.verifyHomeDisplay();
        }
    }

    @Given("user signup ShopBase")
    public void userSignupShopBase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pw = SessionData.getDataTbVal(dataTable, row, "Password");
            String shopname = SessionData.getDataTbVal(dataTable, row, "Domain");

            email = email + currentTime + "@mailinator.girl-viet.com";
            shopname = shopname + currentTime;
            mail = email;

            loginSteps.openSignInPage();
            loginSteps.clickSignUp();
            loginSteps.enterEmail(email);
            loginSteps.enterPassword(pw);
            loginSteps.enterShopname(shopname);
            loginSteps.clickBtnSignUp();
            onboardingSteps.verifyAddYourContactPage();

        }
    }

    @When("login to shopbase partner")
    public void loginToShopbasePartner(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pw = SessionData.getDataTbVal(dataTable, row, "Password");
            email = email + currentTime + "@mailinator.girl-viet.com";

            loginSteps.openSignInPartnerPage();
            loginSteps.enterEmail(email);
            loginSteps.enterPassword(pw);
            loginSteps.clickBtnSignIn();
            loginSteps.submitForm();
            homeSteps.verifyHomePartner();
        }
    }

    @And("change custom link refer people")
    public void changeCustomLinkReferPeople(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String code = SessionData.getDataTbVal(dataTable, row, "Code");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            if (msg.equalsIgnoreCase("Success")) {
                if (code.equalsIgnoreCase("fpr")) {
                    code = fpr;
                } else code = "" + currentTime;
            }

            affSteps.enterCode(code);
            affSteps.verifyInviationCode(code);
            if (msg.equalsIgnoreCase("Success")) {
                affSteps.clickBtnSave(msg);
            }
            affSteps.verifyMessage(msg);
        }
    }

    @Given("^user open link \"([^\"]*)\"$")
    public void userOpenLink(String link) {
        affSteps.openLink(link + fpr);
    }

    @When("user close and open new tab")
    public void userCloseAndOpenNewTab() {

    }

    @Then("verify redirect link with cookie")
    public void verifyRedirectLinkWithCookie(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            Boolean isCookie = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Clear cookie"));

            System.out.println("Clear cookie: " + isCookie);
            affSteps.openLink("https://" + link + "?fpr=" + fpr);
            affSteps.clearCookie(isCookie);
            commonSteps.openUrlInANewTab(link);
            affSteps.switchToTheLastestTab();
            affSteps.closePopup();
            affSteps.enterEmail("huongle@beeketing.net");
            affSteps.clickBtnStartFreeTrial();
            affSteps.verifyRedirectURL(fpr, isCookie);
        }
    }

    @And("^switch to \"([^\"]*)\" tab in menu$")
    public void switchToTabInMenu(String tabName) {
        String _tabs[] = tabName.split(">");
        int level = _tabs.length;
        for (int i = 0; i < level; i++) {
            affSteps.switchToTabInMenu(_tabs[i]);
        }
        commonSteps.waitUntilInvisibleLoading(8);
    }
    @Then("Check status Promoter filed when user signup ref link")
    public void checkStatusPromoterFiledWhenUserSignupRefLink() {
        inviCodeSteps.verifyStatusPromoterFiled();
    }

    @Then("Add invitaion code")
    public void addInvitaionCode(List<List<String>> dataTable) {
        inviCodeSteps.navigateToProfileScreen();
        inviCodeSteps.verifyYourProfileScreen();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String promoter = SessionData.getDataTbVal(dataTable, row, "Promoter");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            if (promoter.equalsIgnoreCase("code")) {
                promoter = LoadObject.getProperty("fpr");
            }
            inviCodeSteps.enterPromoter(promoter);
            inviCodeSteps.clickBtnSave();
            inviCodeSteps.verifyMsg(message);
            loginSteps.refreshPage();
            if (message.equalsIgnoreCase("All changes were successfully saved")) {
                inviCodeSteps.verifyStatusPromoterFiledAfterAddPromoter();
            }
        }
    }

    @Then("complete Onboarding create shop")
    public void completeOnboardingCreateShop(List<List<String>> dataTable) {
        onboardingSteps.clickBtnStartNow();
        onboardingSteps.verifyAddYourContactPage();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
//            String firstname = SessionData.getDataTbVal(dataTable, row, "Firstname");
//            String lastname = SessionData.getDataTbVal(dataTable, row, "Lastname");

            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String perlocation = SessionData.getDataTbVal(dataTable, row, "Personal location");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business type");
            String storeType = SessionData.getDataTbVal(dataTable, row, "Store type");

            String question = SessionData.getDataTbVal(dataTable, row, "Question");
            String ans = SessionData.getDataTbVal(dataTable, row, "Answer");
            String question1 = SessionData.getDataTbVal(dataTable, row, "Question1");
            String ans1 = SessionData.getDataTbVal(dataTable, row, "Answer1");
            String question2 = SessionData.getDataTbVal(dataTable, row, "Question2");
            String ans2 = SessionData.getDataTbVal(dataTable, row, "Answer2");

//            String storeCategory = SessionData.getDataTbVal(dataTable, row, "Product Category");
//            String question = SessionData.getDataTbVal(dataTable, row, "Question");
//            String answer = SessionData.getDataTbVal(dataTable, row, "Answer");
//
//            onboardingSteps.inputFirstName(firstname);
//            onboardingSteps.inputLastName(lastname);
            signUpSteps.inputStoreCountry(country);
            signUpSteps.inputPersonalLocation(perlocation);
            onboardingSteps.inputContact(phone);
            onboardingSteps.clickbtnNext();
            onboardingSteps.chooseBusinessType(businessType);
            onboardingSteps.chooseStoreType(businessType, storeType);

            if (onboardingSteps.verifyCustomizedQuestionsPage()) {
                onboardingSteps.answerQuestions(question, ans);
                onboardingSteps.answerQuestions(question1, ans1);
                onboardingSteps.answerQuestions(question2, ans2);
                onboardingSteps.clickTakeMeToMyStore();
//            onboardingSteps.chooseProductCategory(storeCategory);
//            //answer question
//            if (onboardingSteps.verifyCustomizedQuestionsPage()) {
//                onboardingSteps.answerQuestions(question, answer);
//                onboardingSteps.clickbtnNext();
//            }
//            onboardingSteps.clickTakeMeToMyStore();
            }
            signUpSteps.clickBtnNoThanks();
        }
    }


    @And("Verify user on the Affiliate Dashboard")
    public void verifyUserOnTheAffiliateDashboard() {
        {
            inviCodeSteps.verifyUserOnTheAffiliateDashboard(mail);
//            System.out.println("mail:"+mail);
        }
    }

    @And("verify Affiliate program page display")
    public void verifyAffiliateProgramPageDisplay() {
        affSteps.verifyHeading("Affiliate program");
        affSteps.verifyTabs("ShopBase Affiliate Program", 1);
        affSteps.verifyTabs("PrintBase Ambassador Program", 2);
        affSteps.clickPrintBaseAmbassadorProgram();
        affSteps.verifyAffiliateShareAndInvitationBlockDisplay();
        affSteps.verifyBlockCashbackRuleAndRateDisplay();
        affSteps.verifyBlockAffiliatBenefitDisplay();

    }

    @And("verify Manage referrals page display")
    public void verifyManageReferralsPageDisplay() {
        yourReferrtalsSteps.verifyHeading("Manage referrals");
        yourReferrtalsSteps.verifyDescription("Your Cashback will be counted and added to your Balance at the end of every week.");
        yourReferrtalsSteps.verifyFilterDisplay();
        yourReferrtalsSteps.verifyStatisticTable();
        yourReferrtalsSteps.verifyButtonPayoutDisplay();
        yourReferrtalsSteps.verifyPrintBaseStatistic();
    }

    @Then("verify benefit and cashback rate displayed")
    public void verifyBenefitDisplayed() {
        affSteps.clickPrintBaseAmbassadorProgram();
        affSteps.verifyBenefitAndCashbachrateDisplay();
    }

    @And("verify new user became a ref of merchant")
    public void verifyNewUserBecameARefOfMerchant() {
        affSteps.verifyEmailBecameRefOfMerchant(email);
    }

    @When("verify ShopBase Affiliate Program display")
    public void verifyShopBaseAffiliateProgramDisplay() {
        affSteps.clickTabShopBaseAffiliateProgram();
        affSteps.verifyAffiliateShareAndInvitationBlockDisplay();
        affSteps.verifyHowItWorkDisplay();
        affSteps.verifyCashbackRateDisplay();
        affSteps.verifyProfitStatisticDesc();
        affSteps.verifyProfitStatisticTableDisplay();
        affSteps.verifyFilterDisplay();
        affSteps.verifyStatisticOfShopBase();
    }

    @Given("get number of click by api")
    public void getNumberOfClickByApi() {
        preClick = affSteps.getNumberOfClickByApi();
        System.out.println("pre: " + preClick);
    }

    @When("open referrals unique link")
    public void openReferralsUniqueLink(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String subID = SessionData.getDataTbVal(dataTable, row, "Sub-id");
            if (subID.isEmpty()) {
                link += "?fpr=" + fpr;
            } else {
                link += "?fpr=" + fpr + "&fpt=" + subID;
            }
            affSteps.openLink(link);
            affSteps.refreshPage();
            affSteps.runExecutor();
        }
    }

    @Then("verify statistic number of click by api")
    public void verifyStatisticNumberOfClickByApi() {
        List<String> curClick = affSteps.getNumberOfClickByApi();
        System.out.println("cur: " + curClick);
        affSteps.verifyStatisticNumber(preClick, curClick, 0);
    }

    @When("add new a sub id affiliate link")
    public void addNewASubIdAffiliateLink(List<List<String>> dataTable) {
        affSteps.clickTabManageLink();
        affSteps.clickAddNew();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {

            String link = SessionData.getDataTbVal(dataTable, row, "Link") + "?fpr=" + fpr;
            String subID = SessionData.getDataTbVal(dataTable, row, "Sub-id");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            System.out.println("msg: " + msg);
            affSteps.chooseLink(link);
            if (subID.equalsIgnoreCase("id")) {
                subID = String.valueOf(currentTime);
            }
            affSteps.inputSubId(subID);
            affSteps.verifyPreviewSubId(link, subID);
            affSteps.clickSubmit();
            if (!msg.equalsIgnoreCase("success")) {
                affSteps.verifyErrorMessage(msg);
            } else {
                affSteps.verifyCreateSublinkSuccess(link, subID);
            }
        }
    }

    @And("search link by keyword")
    public void searchLinkByKeyword(List<List<String>> dataTable) {
        affSteps.clickTabManageLink();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String keyword = SessionData.getDataTbVal(dataTable, row, "Keyword");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            affSteps.enterKeyword(keyword);
            affSteps.verifyresult(keyword, result);

        }
    }

    @Then("delete a link which has sub id")
    public void deleteALinkWhichHasSubId() {
        affSteps.verifyCannotDeleteRootlink();
        affSteps.clickDelete(String.valueOf(currentTime));
        affSteps.verifyDeleteLinkSuccessfully(String.valueOf(currentTime));
    }

    @Given("user sign up account partner")
    public void userSignUpAccountPartner(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pw = SessionData.getDataTbVal(dataTable, row, "Password");
            emailSU = email + currentTime + "@mailinator.girl-viet.com";

            commonSteps.openUrl(partnerLinkSU);
            signUpSteps.enterEmail(emailSU);
            signUpSteps.enterPassword(pw);
            signUpSteps.clickBtnSignUp();
            loginSteps.submitForm();
            homeSteps.verifyHomePartner();
        }
    }

    @Then("verify display Block PrintBase Refer Friends")
    public void verifyDisplayBlockPrintBaseReferFriends() {
        affSteps.clickPrintBaseAmbassadorProgram();
        affRequestSteps.verifyIntroPrintBaseAmbassador();
        affRequestSteps.verifyDisplayGetUnlimitedCommissions();
        affRequestSteps.verifyDisplayEarnSimpleCommissions();
        affRequestSteps.verifyDisplayBenefit();
        affRequestSteps.verifyDisplayPrintBaseReferFriends();
    }

    @Then("user send a request")
    public void userSendARequest(List<List<String>> dataTable) {
        affRequestSteps.clickApplytobePrintBasebutton();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String yourname = SessionData.getDataTbVal(dataTable, row, "Your name");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String platform = SessionData.getDataTbVal(dataTable, row, "What platform are you doing POD business on?");
            String time = SessionData.getDataTbVal(dataTable, row, "How long have you been in POD business?");
            String ordervolume = SessionData.getDataTbVal(dataTable, row, "What is your daily order volume?");
            String msg = SessionData.getDataTbVal(dataTable, row, "Messages");
            affRequestSteps.enterYourName(yourname);
            affRequestSteps.enterPhone(phone);
            affRequestSteps.choosePlatForm(platform);
            affRequestSteps.chooseTime(time);
            affRequestSteps.chooseOrderVolume(ordervolume);
            affRequestSteps.clickSubmitbutton();
            if (msg.equals("Thank you for submitting")) {
                affRequestSteps.verifyMsgSuccess(msg);
                affRequestSteps.verifyDisplayNoteRequestProcess();
                affRequestSteps.verifyStatusApplybutton();
            } else
                affRequestSteps.verifyMsg(msg);
        }
    }

    @And("admin approve a request")
    public void adminApproveARequest() {
        affiliateRequestHiveSteps.navigatetoListRequestpage();
        affiliateRequestHiveSteps.verifyListRequestInfomation();
        affiliateRequestHiveSteps.verifyStatusRequestWhenCreateSuccessfully(emailSU);
        affRequestSteps.clickApprove(emailSU);
        affiliateRequestHiveSteps.clickYesApprove();
        affRequestSteps.verifyStatusRequestAfterApprove(emailSU);
    }

    @And("verify display PrintBase Affiliate Program is {string}")
    public void verifyDisplayPrintBaseAffiliateProgram(String status) {
        boolean isShow = Boolean.parseBoolean(status);
        commonSteps.openUrl(partnerLink);
        affSteps.clickMenu();
        affSteps.clickPartnerDashboard();
        affSteps.clickPrintBaseAmbassadorProgram();
        affRequestSteps.verifyDisplayPrintBaseAffiliateProgram(isShow);
        if (isShow) {
            affSteps.verifyAffiliateShareAndInvitationBlockDisplay();
            affSteps.verifyBlockCashbackRuleAndRateDisplay();
            affSteps.verifyBlockAffiliatBenefitDisplay();
        }
    }

    @And("admin reject a request")
    public void adminRejectARequest() {
        affiliateRequestHiveSteps.navigatetoListRequestpage();
        affRequestSteps.clickReject(emailSU);
        affiliateRequestHiveSteps.clickYesReject();
        affRequestSteps.verifyStatusRequestAfterReject(emailSU);
    }

    @And("verify cashback printbase")
    public void verifyCashbackPrintbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            affSteps.verifyCashBackPrintBase(preCB, currentCB, quantity, action, baseProduct, 0);
        }
    }

    @When("update end free trial of shop by API")
    public void updateEndFreeTrialOfShopByAPI() {
        String shopID = "";
        String access_token = "";
        String domain = affSteps.getDomain();
        access_token = affSteps.getShopAccesstoken(domain);
        shopID = affSteps.getShopID(access_token, domain);
        affSteps.updateEndFreeTrialOfShop(shopID);
    }

    @Then("^get cashback statistics \"([^\"]*)\"$")
    public void getCashbackStatistics(String shoptype, List<List<String>> dataTable) {
        JsonArray commissionIds = new JsonArray();
        if (shoptype.equalsIgnoreCase("ShopBase")) {
            commissionIds = affSteps.getCommissionIds();
        } else
            orderId = thankyouSteps.getOrderId();
        affSteps.pushQueueCashbackByApi(orderId, commissionIds);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            currentCB = affSteps.getCashbackStatisticsAffiliateByApi(time);
        }
    }

    @And("^verify cashback statistics \"([^\"]*)\" as \"([^\"]*)\"$")
    public void verifyCashbackStatisticsAs(String shoptype, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String plan = SessionData.getDataTbVal(dataTable, row, "Package");
            String cycle = SessionData.getDataTbVal(dataTable, row, "Cycle");
            affSteps.verifyCashBackShopbase(preCB, currentCB, plan, cycle);
        }
    }

    @Given("get cashback statistics affiliate by api before")
    public void getCashbackStatisticsAffiliateByApiBefore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            preCB = affSteps.getCashbackStatisticsAffiliateByApi(time);
        }
    }
}