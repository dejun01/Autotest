package opencommerce.apps.printbase;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.AuthenSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AuthenDef {
    @Steps
    AuthenSteps authenSteps;
    @Steps
    SignUpSteps signUpSteps;
    public static String shopName = "";
    public static String ownerEmail = "";
    long currentTime = System.currentTimeMillis();

    @Given("^navigate to SignUp PrintBase account from landing page$")
    public void navigateToSignUpFromLandingPage() {
        ownerEmail = "email_" + currentTime + "@mailinator.girlviet.com";
        shopName = "shopautcanbedeleted" + currentTime;
        authenSteps.inputShopSignup(shopName);
        authenSteps.clickCapchar();
    }


    @Given("^signup new account and create a new PrintBase shop$")
    public void signUpCompletelyFlow() {
        authenSteps.inputEmail(ownerEmail);
        authenSteps.inputPassword("123456");
        authenSteps.clickSignUp();
        authenSteps.clickBtnAuthen("Start now");
        authenSteps.waitABit(10000);
        authenSteps.inputFirstName("firstname" + currentTime);
        authenSteps.inputLastName("lastname" + currentTime);
        authenSteps.inputStoreCountry("Vietnam");
        authenSteps.inputPersonalLocation("Vietnam");
        authenSteps.enterPhoneNumber("+84", "0944193269");
        authenSteps.waitABit(10000);
        authenSteps.clickBtn();
        authenSteps.clickBtnAuthen("No thanks, I will decide later");
        authenSteps.clickBtnAuthen("Take me to my store!");
        authenSteps.verifyPrintBaseShopCreated(shopName);
    }
//      | Store country | Personal country | Phone number P1 | Phone Number P2 | Phone Number P2 |

    @Given("^create a new shop with data$")
    public void create_a_new_shop_with_data(List<List<String>> dataTable) {
        String url = LoadObject.getProperty("webdriver.base.url");
        //Get current Date time and convert to format as yyyymmddhhmmss
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);


        String firstName = "First name_" + strDate;
        String lastName = "Last Name_" + strDate;
        String email = "email_" + strDate + "@mailtothis.com";
        String shopname = "shopautcanbedeleted" + strDate;
        System.out.println("email_" + strDate + "@mailtothis.com");

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String password = SessionData.getDataTbVal(dataTable, row, "Password");
            String storeCountry = SessionData.getDataTbVal(dataTable, row, "Store country");
            String personalCountry = SessionData.getDataTbVal(dataTable, row, "Personal country");
            String phoneNumberP1 = SessionData.getDataTbVal(dataTable, row, "Phone Number P1");
            String phoneNumberP2 = SessionData.getDataTbVal(dataTable, row, "Phone Number P2");


            signUpSteps.openSignUpPage(url);
            signUpSteps.clickOnSignUpHyperLink();
            signUpSteps.inputEmail(email);
            signUpSteps.inputPassword(password);
            signUpSteps.inputShopName(shopname);

            signUpSteps.clickOnSignUpbtn();
            signUpSteps.clickOnSkipBtn();
            //click skip btn if on_boarding_new_flow features is off
            signUpSteps.inputFirstName(firstName);
            signUpSteps.inputLastName(lastName);
            signUpSteps.inputStoreCountry(storeCountry);
            signUpSteps.inputPersonalLocation(personalCountry);
            signUpSteps.inputPhoneNumber(phoneNumberP1, phoneNumberP2);

            //click enter my shop btn if on_boarding_new_flow features is off
            signUpSteps.clickOnEnterMyShopBtn();
            signUpSteps.clickOnNextBtn();
            signUpSteps.clickOnStartNowBtn();
            //click skip in What type of store do you want to create?
            signUpSteps.clickOnSkipBtn();
            //click skip in Before going to ShopBase, where have you been?
            signUpSteps.clickOnSkipBtn();
            //click skip in You may also be interested in Apps
            signUpSteps.clickOnSkipBtn();
            //click skip in Do you want to import your contents?
            signUpSteps.clickOnSkipBtn();
        }

        signUpSteps.clickOnEnterMyShopBtn();
        signUpSteps.clickOnNextBtn();
        signUpSteps.clickOnStartNowBtn();
        //click skip in What type of store do you want to create?
        signUpSteps.clickOnSkipBtn();
        //click skip in Before going to ShopBase, where have you been?
        signUpSteps.clickOnSkipBtn();
        //click skip in You may also be interested in Apps
        signUpSteps.clickOnSkipBtn();
        //click skip in Do you want to import your contents?
        signUpSteps.clickOnSkipBtn();
    }
}