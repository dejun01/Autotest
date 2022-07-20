package opencommerce.online_store.preferences;

import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.online_store.domains.steps.DomainSteps;
import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.PreferencesSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.TrackingEventSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.List;

public class PreferencesDef {
    @Steps
    PreferencesSteps preferencesStep;

    @Steps
    LoginDashboardSteps loginStep;

    @Steps
    ProductSteps productSteps;

    @Steps
    DomainSteps domainSteps;

    @Steps
    TrackingEventSteps teStep;

    public static String shopDomain = LoadObject.getProperty("shop");

    @And("Remove all fb Pixels")
    public void removeAllFbPixel(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            int count = preferencesStep.countConversionId(title);
            while (count > 1) {
                preferencesStep.removeconverionId(title);
                count--;
            }
            preferencesStep.clear1stRecord();
            if (preferencesStep.haveChange()) {
                preferencesStep.clickBtnSave();
                preferencesStep.verifyMsgWithLabel("Saving success");
            }

        }
    }

    @And("remove conversion Id")
    public void removeConversionId(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            List<String> cvId = preferencesStep.getlistConversionId(title);
            int count = preferencesStep.countConversionId(title);
            if (!(cvId == null)) {
                for (int i = 1; i < count; i++) {
                    preferencesStep.removeconverionId(title);
                    preferencesStep.clickBtnSave();
                }
            }
        }
    }

    @And("Input and validate fb Pixel as {string}")
    public void inputAndValidateConversionId(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String prevRowKey = SessionData.getDataTbVal(dataTable, row - 1, "KEY");
            String conversionId = SessionData.getDataTbVal(dataTable, row, "Conversion ID");
            String token = SessionData.getDataTbVal(dataTable, row, "Token");
            String msg = SessionData.getDataTbVal(dataTable, row, "Error msg");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            preferencesStep.scrollToConversionId();
            if (!rowKey.equals(prevRowKey) && !prevRowKey.equals("KEY")) {
                preferencesStep.addConversionId(title);
            }
            preferencesStep.inputConversionId(title, conversionId, rowKey);
            preferencesStep.inputToken(title, token, rowKey);
            if (preferencesStep.haveChange()) {
                preferencesStep.clickBtnSave();
                if (!msg.isEmpty()) {
                    preferencesStep.verifyErrMgs(msg);
                } else preferencesStep.verifyMsgWithLabel("Saving success");
            }
        }
    }

    @And("user setting Product description {string}")
    public void userSettingProductDescription(String setting) {
        preferencesStep.settingProductDescription(setting);
        preferencesStep.clickBtnSave();
    }

    @When("open store from {string}")
    public void openStoreFrom(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String isOpenStoreFront = SessionData.getDataTbVal(dataTable, row, "OpenStoreFront");

            if (isOpenStoreFront.equals("dashboard")) {
                domainSteps.openSFFromDashboard();
            } else {
                loginStep.openShop(shopDomain);
            }
        }
    }

    @And("setting password protection with {string}")
    public void settingPasswordProtectionWith(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String isEnablePassword = SessionData.getDataTbVal(dataTable, row, "EnablePassword");
            String isInputPassword = SessionData.getDataTbVal(dataTable, row, "InputPassword");
            boolean isShow = Boolean.parseBoolean(isEnablePassword);
            preferencesStep.checkCheckboxWithLabel("Enable password", isShow);
            preferencesStep.inputPassword(isInputPassword);
            if (preferencesStep.haveChange()) {
                preferencesStep.clickBtnSave();
                preferencesStep.verifyMsgWithLabel("Saving success");
            }

        }

    }

    @Then("verify page password page with {string}")
    public void verifyPagePasswordPageWith(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sIsShowPasswordOnSF = SessionData.getDataTbVal(dataTable, row, "ShowPasswordOnSF");
            Boolean IsshowPWOnSF = Boolean.parseBoolean(sIsShowPasswordOnSF);
            preferencesStep.verifyPagePW(IsshowPWOnSF);
        }
    }

    @Then("verify and input password{string}")
    public void verifyAndInputPassword(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sIsShowPasswordOnSF = SessionData.getDataTbVal(dataTable, row, "ShowPasswordOnSF");
            String sInputPW = SessionData.getDataTbVal(dataTable, row, "InputPW");
            String sShowMessage = SessionData.getDataTbVal(dataTable, row, "ShowMessage");
            String sMessage = SessionData.getDataTbVal(dataTable, row, "Message");
            Boolean isShowPWOnSF = Boolean.parseBoolean(sIsShowPasswordOnSF);
            Boolean isshowMassage = Boolean.parseBoolean(sShowMessage);
            preferencesStep.verifyPagePW(isShowPWOnSF);
            if (isShowPWOnSF) {
                preferencesStep.inputPasswordSF(sInputPW);
                preferencesStep.clickbuttonEnter();
                if(isshowMassage) {
                    preferencesStep.clickbuttonEnter();
                    preferencesStep.verifyMessageValidate(sMessage);
                }

            }
        }
    }

    @And("add to cart {string}")
    public void addToCartProduct(String productName) {
        preferencesStep.clickBtnSearch();
        preferencesStep.inputProductName(productName);
        productSteps.selectProduct(productName);
        productSteps.clickAddToCart();
    }

    @And("click button checkout")
    public void clickButtonCheckout() {
        productSteps.clickCheckOut();
    }

    @And("^verify navigate to Learn more screen as \"([^\"]*)\"$")
    public void navigateToLearnMore(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String exactURL = "https://developers.google.com/search/docs/advanced/robots/create-robots-txt";
            preferencesStep.clickBtnLearnMore();
            preferencesStep.switchToWindow(1);
            preferencesStep.verifyNavigateInRobotSection(exactURL);
            preferencesStep.closeBrowser();
            preferencesStep.switchToWindow(0);
        }
    }

    @Then("^verify navigate to Edit Robots.txt screen$")
    public void navigateToEditRobotTxt(){
        String shopDomain = LoadObject.getProperty("shop");
        String exactURL = "/admin/edit-robots-txt";
        preferencesStep.clickBtnEditRobotstxt();
        preferencesStep.verifyNavigateInRobotSection("https://" + shopDomain + exactURL);
    }

    @And("^verify edit robots.txt file as \"([^\"]*)\"$")
    public void editRobotTxtFile(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String method = SessionData.getDataTbVal(dataTable, row, "Method");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            if(method.equalsIgnoreCase("Input")){
                preferencesStep.enterDataContentRobot(content);
            }
            else {
                preferencesStep.clickBtnByText(method);
            }
            preferencesStep.clickBtnByText(action);
            if(!message.isEmpty())
                preferencesStep.verifyMsgWithLabel(message);
        }
    }

    @When("^copy and redirect to file robot text in SF$")
    public void copyAndRedirectToRobotFileSF(){
        preferencesStep.clickOnTabList("File Location");
//        preferencesStep.openFileRobot(Keys.chord(Keys.CONTROL, "v"));
        String shopDomain = LoadObject.getProperty("tool");
        preferencesStep.openFileRobot("https://"+ shopDomain);
    }

    @Then("^verify robots.txt file in SF as \"([^\"]*)\"$")
    public void verifyRobotTxtFile(String dataKey, List<List<String>> dataTable) throws IOException{
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String shopDomain = LoadObject.getProperty("shop");
            String contentDefault = teStep.getDataFromFile("/front/fileDefaultRobotTxt.txt").replace("{primary_domain}","https://"+ shopDomain).replaceAll("[\\r\\n]"," ").replace("  "," ");
            String contentCurrent = preferencesStep.contentRobotTxtSF().replaceAll("[\\r\\n]"," ");
            if(content.equalsIgnoreCase("Default"))
                preferencesStep.verifyContent(contentDefault.trim(), contentCurrent.trim());
            else preferencesStep.verifyContent(content.replace("{primary_domain}","https://"+ shopDomain), contentCurrent);
        }
    }

}



