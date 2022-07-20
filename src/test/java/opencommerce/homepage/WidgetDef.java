package opencommerce.homepage;

import com.opencommerce.shopbase.common.steps.CommonHiveSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.home.steps.HomeSteps;
import com.opencommerce.shopbase.dashboard.onboarding.steps.OnboardingSteps;
import com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep;
import com.opencommerce.shopbase.hive_sbase.steps.ContestSteps;
import com.opencommerce.shopbase.hive_sbase.steps.WidgetDashboardSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jnr.ffi.Struct;
import net.thucydides.core.annotations.Steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.float_totalAmt;
import static com.opencommerce.shopbase.OrderVariable.totalAmt;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class WidgetDef {
    @Steps
    ContestSteps contestSteps;
    @Steps
    CommonHiveSteps commonHiveSteps;
    @Steps
    WidgetDashboardSteps widgetDashboardSteps;
    @Steps
    HomeSteps homeSteps;
    @Steps
    OnboardingSteps onboardingSteps;
    @Steps
    LoginDashboardSteps loginsteps;
    @Steps
    SignUpSteps signUpSteps;
    @Steps
    com.opencommerce.shopbase.dashboard.home.steps.HomeSteps homeDBSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    PBaseOrderStep pBaseOrderStep;
    long currentTime = System.currentTimeMillis();
    String widgetId = LoadObject.getProperty("widgetId");
    String web = LoadObject.getProperty("webdriver.base.url");
    String userName = LoadObject.getProperty("user.name");
    String pass = LoadObject.getProperty("user.pwd");
    String name = "";
    String oldPoint = "";
    int orderId;

    public String getCurrentDate() {
        java.sql.Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(timeStamp.getTime());
        DateFormat curDate = new SimpleDateFormat("dd/MM/yyyy");
        return curDate.format(date);
    }

    @Then("create a contest successfully in hive")
    public void createAContestSuccessfullyInHive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String date = getCurrentDate();
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop Type");
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String startTime = date + " 00:00";
            String endTime = date + " 23:59";
            String metricsType = SessionData.getDataTbVal(dataTable, row, "Metrics Type");
            String prize = SessionData.getDataTbVal(dataTable, row, "Prize");
            String preHeader = SessionData.getDataTbVal(dataTable, row, "Pre Header");
            String preSub = SessionData.getDataTbVal(dataTable, row, "Pre Sub-text");
            String preButton = SessionData.getDataTbVal(dataTable, row, "Pre button");
            String preLink = SessionData.getDataTbVal(dataTable, row, "Pre Link");
            String preImage = SessionData.getDataTbVal(dataTable, row, "Pre image");
            String template = SessionData.getDataTbVal(dataTable, row, "Template");
            String inHeader = SessionData.getDataTbVal(dataTable, row, "In Header");
            String inLink = SessionData.getDataTbVal(dataTable, row, "In link");
            String inProgressbar = SessionData.getDataTbVal(dataTable, row, "In progress bar");
            String afterHeaderNoPrize = SessionData.getDataTbVal(dataTable, row, "After Header 1");
            String afterSubNoPrize = SessionData.getDataTbVal(dataTable, row, "After Sub-text 1");
            String afterImageNoPrize = SessionData.getDataTbVal(dataTable, row, "After Image 1");
            String afterHeaderHadPrize = SessionData.getDataTbVal(dataTable, row, "After Header 2");
            String afterSubHadPrize = SessionData.getDataTbVal(dataTable, row, "After Sub-text 2");
            String afterImageHadPrize = SessionData.getDataTbVal(dataTable, row, "After Image 2");
            String afterLink = SessionData.getDataTbVal(dataTable, row, "After Link");
            String icon1 = SessionData.getDataTbVal(dataTable, row, "Icon 1");
            String icon2 = SessionData.getDataTbVal(dataTable, row, "Icon 2");
            String boxColor = SessionData.getDataTbVal(dataTable, row, "Box Color");


            commonHiveSteps.clickAddNew();
            name = name + System.currentTimeMillis();
            commonHiveSteps.clickTab("Config Logic");
            commonHiveSteps.enterInput("Contest Name", name);
            commonHiveSteps.selectDroplist("Shop Type", shopType);
            commonHiveSteps.selectMultDroplist("Region", region);
            commonHiveSteps.enterInput("Start Time*", startTime);
            commonHiveSteps.closeDatePicker("Start Time*");
            commonHiveSteps.enterInput("End Time*", endTime);
            commonHiveSteps.closeDatePicker("End Time*");
            contestSteps.selectMetricsType("Metrics Type*", metricsType);
            contestSteps.inputPrize(prize);

            commonHiveSteps.clickTab("Config UI");
            // Pre contest
            contestSteps.enterPreMsgHeader(preHeader);
            contestSteps.enterPreSubtext(preSub);
            contestSteps.enterLearnMoreButton(preButton);
//            contestSteps.uploadPreBackground(preImage);
            commonHiveSteps.enterInput("Link", preLink);


            //In contest
            contestSteps.selectTemplate(template);
            contestSteps.enterInMsgHeader(inHeader);
            commonHiveSteps.enterInput("Learn more link", inLink);
            contestSteps.enterProgressbar(inProgressbar);
//            contestSteps.uploadInContestBackground("");
//            contestSteps.uploadInContestIconStart("");
//            contestSteps.uploadInContestIconFinish("");

            //After contest
            contestSteps.enterAfterMsgHeaderNoPrice(afterHeaderNoPrize);
            contestSteps.enterAfterSubNoPrice(afterSubNoPrize);
//            contestSteps.uploadAfterBGNoPrice(afterImageNoPrize);
            contestSteps.enterAfterMsgHeaderHadPrice(afterHeaderHadPrize);
            contestSteps.enterAfterSubHadPrice(afterSubHadPrize);
//            contestSteps.uploadAfterBGHadPrice(afterImageHadPrize);
//            contestSteps.uploadAfterIcon1(icon1);
//            contestSteps.uploadAfterIcon2(icon2);
            commonHiveSteps.enterInput("Box Color", boxColor);
            commonHiveSteps.clickCreateAndReturnToList();
            if (msg.equalsIgnoreCase("Success")) {
                contestSteps.verifyContestInlist(name);
            } else commonHiveSteps.verifyDetailPageDisplay("Contest Tool");
        }

    }

    @Then("Edit a widget in hive")
    public void editAWidgetInHive(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            commonHiveSteps.clickEdit(widgetId);
            String screen = SessionData.getDataTbVal(dataTable, row, "Screen");
            Boolean isEnable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            String name = SessionData.getDataTbVal(dataTable, row, "Name") + currentTime;
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String pack = SessionData.getDataTbVal(dataTable, row, "Package");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business Type");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title") + currentTime;
            String description = SessionData.getDataTbVal(dataTable, row, "Description") + currentTime;
            String priBtnText = SessionData.getDataTbVal(dataTable, row, "Primary button text") + currentTime;
            String primaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Primary button link") + currentTime;
            String secondaryButtonText = SessionData.getDataTbVal(dataTable, row, "Secondary button text") + currentTime;
            String secondaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Secondary button Link") + currentTime;
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String video = SessionData.getDataTbVal(dataTable, row, "Video");
            String list = SessionData.getDataTbVal(dataTable, row, "List");
            String day = SessionData.getDataTbVal(dataTable, row, "Day");
            String reward = SessionData.getDataTbVal(dataTable, row, "Reward");
            String goal = SessionData.getDataTbVal(dataTable, row, "Goal(number)");

            commonHiveSteps.clickTab("Config logic");
            commonHiveSteps.selectDroplist("Screen", screen);
            commonHiveSteps.checkCheckbox("Enable", isEnable);
            commonHiveSteps.enterInput("Name", name);
            commonHiveSteps.enterInput("Priority", priority);
            String reg[] = region.split(",");
            for (int i = 0; i < reg.length; i++) {
                commonHiveSteps.checkCheckbox(reg[i], true);
            }
            String pk[] = pack.split(",");
            for (int i = 0; i < pk.length; i++) {
                commonHiveSteps.checkCheckbox(pk[i], true);
            }
            String bsn[] = businessType.split(",");
            for (int i = 0; i < bsn.length; i++) {
                commonHiveSteps.checkCheckbox(bsn[i], true);
            }
            commonHiveSteps.clickTab("Config UI");
            widgetDashboardSteps.selectWidgetType(type);
            switch (type) {
                case "Basic":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    commonHiveSteps.enterInput("Primary Button Text", priBtnText);
                    commonHiveSteps.enterInput("Primary Button Link", primaryButtonLink);
                    commonHiveSteps.enterInput("Secondary Button Text", secondaryButtonText);
                    commonHiveSteps.enterInput("Secondary Button Link", secondaryButtonLink);
                    if (image.isEmpty()) {
//                        commonHiveSteps.selectRadioButton("Video", true);
                        commonHiveSteps.enterInput("Video", video);
                    } else {
                        commonHiveSteps.selectRadioButton("Image", true);
//                        commonHiveSteps.uploadImage("Image", image);
                    }
                    break;
                case "Center":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    commonHiveSteps.enterInput("Primary Button Text", priBtnText);
                    commonHiveSteps.enterInput("Primary Button Link", primaryButtonLink);
//                    widgetDashboardSteps.uploadImage(image);

                    break;
                case "List":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    widgetDashboardSteps.deleleAllList();
                    widgetDashboardSteps.enterList(list);
                    break;
                case "Race Challenge":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    commonHiveSteps.enterInput("Primary Button Text", priBtnText);
                    commonHiveSteps.enterInput("Primary Button Link", primaryButtonLink);
                    commonHiveSteps.enterInput("Secondary Button Text", secondaryButtonText);
                    commonHiveSteps.enterInput("Secondary Button Link", secondaryButtonLink);
                    commonHiveSteps.enterInput("Day", day);
                    commonHiveSteps.enterInput("Reward", reward);
                    commonHiveSteps.enterInput("Goal(number)", goal);
                    break;
            }
            commonHiveSteps.clickUpdateAndClose();
            commonHiveSteps.verifyFiledDisplay(widgetId, name);
        }
    }

    @And("verify widgets in dasboard")
    public void verifyWidgetsInDasboard() {
        HashMap<Integer, List<String>> listWidget = homeSteps.getlistWidget();
        if (listWidget.size() == 0) {
            homeSteps.verifyWidgetDisplay(false);
        } else {
            int number = homeSteps.getNumberOfWidget();
            assertThat(number).isEqualTo(listWidget.size());
            for (int i = 0; i < listWidget.size(); i++) {
                List<String> widget = listWidget.get(i);
                System.out.println("widget " + i + " :" + widget);
                homeSteps.verifyTitleWidget(widget.get(3), i + 1);
                homeSteps.verifyDescWidget(widget.get(4), i + 1);
                homeSteps.verifyTypeWidget(widget.get(2), i + 1);
                homeSteps.verifDetailWidget(widget, i + 1);
            }
        }
    }

    @And("create widget race challenge")
    public void createWidgetRaceChallenge(List<List<String>> dataTable) {
        homeSteps.clickonAddnewbutton();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String screen = SessionData.getDataTbVal(dataTable, row, "Screen");
            Boolean isEnable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            name = SessionData.getDataTbVal(dataTable, row, "Name") + currentTime;
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String pack = SessionData.getDataTbVal(dataTable, row, "Package");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business Type");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String day = SessionData.getDataTbVal(dataTable, row, "Day");
            String reward = SessionData.getDataTbVal(dataTable, row, "Reward");
            String goal = SessionData.getDataTbVal(dataTable, row, "Goal(number)");
            String priBtnText = SessionData.getDataTbVal(dataTable, row, "Primary button text");
            String priBtnLink = SessionData.getDataTbVal(dataTable, row, "Primary button link");
            String secBtnText = SessionData.getDataTbVal(dataTable, row, "Secondary button text");
            String secBtnLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            commonHiveSteps.clickTab("Config logic");
            commonHiveSteps.selectDroplist("Screen", screen);
            commonHiveSteps.checkCheckbox("Enable", isEnable);
            commonHiveSteps.enterInput("Name", name);
            commonHiveSteps.enterInput("Priority", priority);
            commonHiveSteps.clickTab("Config UI");
            widgetDashboardSteps.selectWidgetType(type);
            commonHiveSteps.enterInput("Title", title);
            commonHiveSteps.enterInput("Description", description);
            commonHiveSteps.enterInput("Day", day);
            commonHiveSteps.enterInput("Reward", reward);
            commonHiveSteps.enterInput("Goal(number)", goal);
            commonHiveSteps.enterInput("Primary Button Text", priBtnText);
            commonHiveSteps.enterInput("Primary Button Link", priBtnLink);
            commonHiveSteps.enterInput("Secondary Button Text", secBtnText);
            commonHiveSteps.enterInput("Secondary Button Link", secBtnLink);
            commonHiveSteps.clickCreate();
            if (msg.equals("success")) {
                homeSteps.verifyCreateWidgetRaceChallengeSuccess();
            }
        }
        commonHiveSteps.clickUpdateAndClose();
    }

    @Then("delete widget race challenge")
    public void deleteWidgetRaceChallenge() {
        homeSteps.clickDetelebutton(name);
        homeSteps.clickYesbutton();
    }

    @And("verify widget race challenge in dashboard")
    public void verifyWidgetRaceChallengeInDashboard() {
        homeSteps.verifyTitleWidgetRaceChallenge();
        homeSteps.verifyDescriptionWidgetRaceChallenge();
        homeSteps.verifyGoalWidgetRaceChallenge();
        homeSteps.verifyBnt();
        homeSteps.verifySecBnt();
    }

    @Given("login and create shop")
    public void loginAndCreateShop(List<List<String>> dataTable) {
        loginsteps.openURL(web);
        loginsteps.enterEmail(userName);
        loginsteps.enterPassword(pass);
        loginsteps.clickBtnSignIn();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shop = SessionData.getDataTbVal(dataTable, row, "Shop name") + currentTime;
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String perlocation = SessionData.getDataTbVal(dataTable, row, "Personal location");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business type");
            String storeType = SessionData.getDataTbVal(dataTable, row, "Store type");
            String storeCategory = SessionData.getDataTbVal(dataTable, row, "Product Category");
            String storeCustomize = SessionData.getDataTbVal(dataTable, row, "Customize your store");
            loginsteps.clickbtnAddANewShop();
            signUpSteps.enterShopName(shop);
            loginsteps.clickbtnCreate();
            onboardingSteps.clickBtnStartNow();
            onboardingSteps.inputStoreCountry(country);
            onboardingSteps.inputYourPersonalLocation(perlocation);
            onboardingSteps.inputContact(phone);
            onboardingSteps.clickbtnNext();
            onboardingSteps.chooseBusinessType(businessType);
            onboardingSteps.chooseStoreType(businessType, storeType);
            onboardingSteps.chooseProductCategory(storeCategory);
            homeSteps.chooseCustomizeStore(storeCustomize);
            onboardingSteps.clickTakeMeToMyStore();
            homeDBSteps.verifyHomeDisplay();
        }
    }

    @And("verify contest display in homepage \"([^\"]*)\"")
    public void verifyContestDisplayInHomepage(String shoptype) {
        homeSteps.verifyListPrice(shoptype);
        homeSteps.verifyUIsetting();
        homeSteps.verifyContestdata(shoptype);
    }

    @Then("verify point of contest")
    public void verifyPointOfContest(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            List<String> current = homeSteps.getContestData();
            String curPoint = current.get(0);
            switch (action) {
                case "Checkout":
                    totalAmt = orderSummarySteps.getTotalAmt();
                    Double exp = Double.parseDouble(totalAmt.replaceAll("[$]", "")) + Double.parseDouble(oldPoint);
                    System.out.println("act: " + curPoint + " exp: " + exp);
                    assertThat(exp).isEqualTo(Double.parseDouble(curPoint));
                    break;
                case "Refund":

            }

        }
    }

    @And("get contest point in current")
    public void getContestPointInCurrent() {
        oldPoint = homeSteps.getContestData().get(0);
    }

    @Then("verify point of contest in pbase")
    public void verifyPointOfContestInPbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String product = SessionData.getDataTbVal(dataTable, row, "Product name");
            List<String> current = homeSteps.getContestData();
            String curPoint = current.get(0);
            switch (action) {
                case "Checkout":
                    int exp;
                    if (product.equalsIgnoreCase("Unisex T-shirt"))
                        exp = Integer.parseInt(oldPoint) + Integer.parseInt(quantity) * 2;
                    else exp = Integer.parseInt(oldPoint) + Integer.parseInt(quantity);
                    assertThat(exp).isEqualTo(Integer.parseInt(curPoint));
                    break;
                case "Refund":
            }
        }
    }

    @Then("cancel order on hive-pbase and verify point")
    public void cancelOrderOnHivePbaseAndVerifyPoint(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            int quantity = Integer.parseInt(LoadObject.getProperty("quantityCancel"));
            System.out.println("order id: " + orderId);
            pBaseOrderStep.navigateToCancelOrderPBase(orderId);
            pBaseOrderStep.chooseQuantity(quantity);
            pBaseOrderStep.cancelOrderPBase();

        }
    }

    @And("create widget printhub")
    public void createWidgetPrinthub (List<List<String>> dataTable) {
        commonHiveSteps.clickAddNew();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String screen = SessionData.getDataTbVal(dataTable, row, "Screen");
            Boolean isEnable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            name = SessionData.getDataTbVal(dataTable, row, "Name") + currentTime;
            String start = SessionData.getDataTbVal(dataTable, row, "Start Date");
            String end = SessionData.getDataTbVal(dataTable, row, "End Date");
            String exitp = SessionData.getDataTbVal(dataTable, row, "Allow Exit Point");
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String pack = SessionData.getDataTbVal(dataTable, row, "Package");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business Type");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description") + currentTime;
            String priBtnText = SessionData.getDataTbVal(dataTable, row, "Primary button text") + currentTime;
            String primaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Primary button link");
            String secondaryButtonText = SessionData.getDataTbVal(dataTable, row, "Secondary button text") + currentTime;
            String secondaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String video = SessionData.getDataTbVal(dataTable, row, "Video");
//          String list = SessionData.getDataTbVal(dataTable, row, "List");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            commonHiveSteps.clickTab("Config logic");
            commonHiveSteps.selectDroplist("Screen", screen);
            commonHiveSteps.checkCheckbox("Enable", isEnable);
            commonHiveSteps.enterInput("Name", name);
            commonHiveSteps.enterInput("Priority", priority);
            widgetDashboardSteps.selectStartDate("Start Date", start);
            widgetDashboardSteps.selectEndDate("End Date", end);
            commonHiveSteps.selectDroplist("Allow Exit Point", exitp);
            String reg[] = region.split(",");
            for (int i = 0; i < reg.length; i++) {
                commonHiveSteps.checkCheckbox(reg[i], true);
            }
            String pk[] = pack.split(",");
            for (int i = 0; i < pk.length; i++) {
                commonHiveSteps.checkCheckbox(pk[i], true);
            }
            String bsn[] = businessType.split(",");
            for (int i = 0; i < bsn.length; i++) {
                commonHiveSteps.checkCheckbox(bsn[i], true);
            }
            commonHiveSteps.clickTab("Config UI");
            widgetDashboardSteps.selectWidgetType(type);
            commonHiveSteps.enterInput("Title", title);
            commonHiveSteps.enterInput("Description", description);
            commonHiveSteps.enterInput("Primary Button Text", priBtnText);
            commonHiveSteps.enterInput("Primary Button Link", primaryButtonLink);
            commonHiveSteps.enterInput("Secondary Button Text", secondaryButtonText);
            commonHiveSteps.enterInput("Secondary Button Link", secondaryButtonLink);
            widgetDashboardSteps.uploadImage(image);
            commonHiveSteps.clickCreate();
            if (msg.equals("Success")){
                widgetDashboardSteps.verifyCreateWidgetPrinthubSuccess();
            }
        }
        commonHiveSteps.clickUpdateAndClose();
    }

    @Then("delete widget printhub dashboard")
    public void deleteWidgetPrinthubDashboard() {
        homeSteps.clickDetelebutton(name);
        homeSteps.clickYesbutton();
    }

    @Then("edit a widget printhub in hive")
    public void editAWidgetPrinthubInHive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            widgetDashboardSteps.clickEditBtn(widgetId);
            String screen = SessionData.getDataTbVal(dataTable, row, "Screen");
            Boolean isEnable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            name = SessionData.getDataTbVal(dataTable, row, "Name") + currentTime;
            String start = SessionData.getDataTbVal(dataTable, row, "Start Date");
            String end = SessionData.getDataTbVal(dataTable, row, "End Date");
            String exitp = SessionData.getDataTbVal(dataTable, row, "Allow Exit Point");
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String pack = SessionData.getDataTbVal(dataTable, row, "Package");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business Type");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title") + currentTime;
            String description = SessionData.getDataTbVal(dataTable, row, "Description") + currentTime;
            String priBtnText = SessionData.getDataTbVal(dataTable, row, "Primary button text") + currentTime;
            String primaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Primary button link") + currentTime;
            String secondaryButtonText = SessionData.getDataTbVal(dataTable, row, "Secondary button text") + currentTime;
            String secondaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link") + currentTime;
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String video = SessionData.getDataTbVal(dataTable, row, "Video");
            String list = SessionData.getDataTbVal(dataTable, row, "List");

            commonHiveSteps.clickTab("Config logic");
            commonHiveSteps.selectDroplist("Screen", screen);
            commonHiveSteps.checkCheckbox("Enable", isEnable);
            commonHiveSteps.enterInput("Name", name);
            commonHiveSteps.enterInput("Priority", priority);
            widgetDashboardSteps.selectStartDate("Start Date", start);
            widgetDashboardSteps.selectEndDate("End Date", end);
            commonHiveSteps.selectDroplist("Allow Exit Point", exitp);
            String reg[] = region.split(",");
            for (int i = 0; i < reg.length; i++) {
                commonHiveSteps.checkCheckbox(reg[i], true);
            }
            String pk[] = pack.split(",");
            for (int i = 0; i < pk.length; i++) {
                commonHiveSteps.checkCheckbox(pk[i], true);
            }
            String bsn[] = businessType.split(",");
            for (int i = 0; i < bsn.length; i++) {
                commonHiveSteps.checkCheckbox(bsn[i], true);
            }
            commonHiveSteps.clickTab("Config UI");
            widgetDashboardSteps.selectWidgetType(type);
            switch (type) {
                case "Basic":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    commonHiveSteps.enterInput("Primary Button Text", priBtnText);
                    commonHiveSteps.enterInput("Primary Button Link", primaryButtonLink);
                    commonHiveSteps.enterInput("Secondary Button Text", secondaryButtonText);
                    commonHiveSteps.enterInput("Secondary Button Link", secondaryButtonLink);
                    if (image.isEmpty()) {
//                        commonHiveSteps.selectRadioButton("Video", true);
                        commonHiveSteps.enterInput("Video", video);
                    } else {
//                        commonHiveSteps.selectRadioButton("Image", true);
                        widgetDashboardSteps.uploadImage(image);
                    }
                    break;
                case "Center":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    commonHiveSteps.enterInput("Primary Button Text", priBtnText);
                    commonHiveSteps.enterInput("Primary Button Link", primaryButtonLink);
                    widgetDashboardSteps.uploadImage(image);

                    break;
                case "List":
                    commonHiveSteps.enterInput("Title", title);
                    commonHiveSteps.enterInput("Description", description);
                    widgetDashboardSteps.deleleAllList();
                    widgetDashboardSteps.enterList(list);
                    break;
            }
            commonHiveSteps.clickUpdateAndClose();
        }
    }
}


