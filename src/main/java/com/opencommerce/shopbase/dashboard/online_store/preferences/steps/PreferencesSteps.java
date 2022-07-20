package com.opencommerce.shopbase.dashboard.online_store.preferences.steps;
import com.opencommerce.shopbase.dashboard.online_store.preferences.pages.PreferencesPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;
import java.util.List;

public class PreferencesSteps {
    PreferencesPage prePage;
    String accessToken = "";
    String shop = LoadObject.getProperty("shop");
    String mobile = "";
    String desktop = "";
    String favicon = "";
    String email = "";
    String checkout = "";

    public void enterTextInLogo(String text) {
        prePage.enterInputFieldWithLabel("Text in logo", text);
    }

    public void clickBtnRegenerate() {
        prePage.clickBtn("Regenerate");
    }


    public String getLogo() {
        return prePage.getLogo();
    }

    public void clickAddToMyStore() {
        prePage.clickBtn("Add to my store");

    }

    public void verifyPopup() {
        prePage.verifyPopupTitle();
    }

    public void checkCheckboxDesktop(boolean isCheck) {
        prePage.checkCheckboxWithLabel("Desktop", isCheck);
    }

    public void checkCheckboxMobile(boolean isCheck) {
        prePage.checkCheckboxWithLabel("Mobile Logo", isCheck);
    }

    public void checkCheckboxCheckoutPage(boolean isCheck) {
        prePage.checkCheckboxWithLabel("Checkout page Logo", isCheck);
    }

    public void checkCheckboxFavicon(boolean isCheck) {
        prePage.checkCheckboxWithLabel("Favicon", isCheck);
    }

    public void checkCheckboxEmail(boolean isCheck) {
        prePage.checkCheckboxWithLabel("Email Notifications", isCheck);
    }

    public void chooseTheme(String theme) {
        prePage.selectRadioButtonWithLabel(theme, true);
    }

    public void clickSave() {
        prePage.clickBtnSaveOnPopup();
        prePage.verifyAddLogoSuccessfully();
    }

    public HashMap<String, String> getLogoByAPI() {
        if (accessToken.isEmpty()) {
            accessToken = prePage.getAccessTokenShopBase();
        }
        String api = "https://" + shop + "/admin/logo-builder/get-styles.json";
        System.out.println(api);
        JsonPath js = prePage.getAPISbase(api, accessToken);
        HashMap<String, String> logoList = new HashMap<>();
        mobile = getData(js, "data.current.mobile");
        desktop = getData(js, "data.current.desktop");
        favicon = getData(js, "data.current.favicon");
        email = getData(js, "data.current.email");
        checkout = getData(js, "data.current.checkout");

        logoList.put("mobile", mobile);
        logoList.put("desktop", desktop);
        logoList.put("favicon", favicon);
        logoList.put("email", email);
        logoList.put("checkout", checkout);
        System.out.println("logo list = " + logoList);
        return logoList;
    }

    public String getData(JsonPath jsonPath, String key) {
        Object data = prePage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    public void verifyMatchLogo(Boolean isCheck, String preValue, String expValue) {
        prePage.verifyMatchLogo(isCheck, preValue, expValue);
    }

    public void scrollToConversionId() {
        prePage.scrollToConversionId();
    }

    public void clickBtnChange() {
        prePage.clickBtnChange();
    }

    @Step
    public void inputConversionId(String title, String cvId, String rowKey) {
        if(title.equals("Facebook Pixel & Conversions API")){
            prePage.inputIdAndToken(title, "Paste your Facebook pixel ID", cvId, rowKey);
        }else {
            prePage.inputIdAndToken(title, "AW-XXXXXXXX", cvId, rowKey);
        }
    }

    public void clickBtnSave() {
        prePage.clickBtnSave();
    }

    public void verifyErrMgs(String msg) {
        prePage.verifyMsg(msg);
    }

    public void verifyMsgWithLabel(String msg) {
        prePage.verifyMsgWithLabel(msg);
    }

    public void clickOverLay() {
        prePage.clickOverLay();

    }

    public void removeconverionId(String title) {
        prePage.removeConversionId(title);
    }

    @Step
    public void inputToken(String title, String token, String rowKey) {
        if(title.equals("Facebook Pixel & Conversions API")){
            prePage.inputIdAndToken(title, "Paste your access token", token, rowKey);
        }else {
            prePage.inputIdAndToken(title, "Paste your Conversion Label here", token, rowKey);
        }
    }

    @Step
    public void addConversionId(String title) {
        if(title.equals("Facebook Pixel & Conversions API")){
            prePage.addConversionId("Add other Pixel IDs");
        }else {
            prePage.addConversionId("Add other Conversion ID");
        }
    }

    public List<String> getlistConversionId(String title) {
        return prePage.getlistConversionId(title);
    }

    public int countConversionId(String title) {
        return prePage.countcvIdInList(title);
    }
    public void settingProductDescription(String setting) {
        prePage.settingProductDescription(setting);
    }

    public void inputPassword(String inputPassword) {
        prePage.inputPassword(inputPassword);

    }
    public void checkCheckboxWithLabel(String label, boolean status) {
        prePage.checkCheckboxWithLabel(label, status);
    }

    public void verifyPagePW(Boolean isshowPWOnSF) {
        prePage.verifyPagePW(isshowPWOnSF);

    }

    public void settingOnOffGeneratePF(boolean statusGenerate) {
        prePage.settingOnOrOffGeneratePrintFile(statusGenerate);
    }

    @Step
    public void verifySettingGeneratePrintFilebyAPI(Boolean statusApi,Boolean statusGenerate) {
        prePage.verifySettingGeneratePrintFilebyAPI(statusApi,statusGenerate);
    }
    @Step
    public void clear1stRecord() {
        prePage.clearFieldInputWithLabel("Pixel ID");
        prePage.clearFieldInputWithLabel("Access Token");
    }

    public boolean haveChange() {
        return prePage.haveChange();
    }

    public void inputPasswordSF(String sInputPW) {
        prePage.inputPasswordSF(sInputPW);
    }

    public void clickbuttonEnter() {
        prePage.clickBtnEnter();
    }

    public void verifyMessageValidate(String sMessage) {
        prePage.verifyMessageValidate(sMessage);
    }

    public void clickBtnSearch() {
        prePage.clickBtnSearch();
    }

    public void inputProductName(String productName) {
        prePage.inputProductName(productName);
    }

    @Step
    public void clickBtnLearnMore (){
        prePage.clickBtnLearnMore();
    }

    @Step
    public void clickBtnEditRobotstxt (){
        prePage.clickBtnEditRobotstxt();
    }

    @Step
    public void verifyNavigateInRobotSection(String exactURL){
        String currentURL = prePage.getCurrentUrl();
        prePage.verifyNavigateInRobotSection(currentURL,exactURL);
    }

    @Step
    public void switchToWindow(int index){
        prePage.switchToWindowWithIndex(index);
    }

    @Step
    public void closeBrowser(){
        prePage.closeBrowser();
    }

    @Step
    public String contentRobotTxt(){
        return prePage.contentRobotTxt();
    }

    @Step
    public void enterDataContentRobot(String content){
        prePage.enterDataContentRobot(content);
    }

    @Step
    public void clickBtnByText(String text) {
        prePage.clickBtnByText(text);
    }

    @Step
    public void clickOnTabList(String text){
        prePage.clickOnTabList(text);
    }

    @Step
    public void openFileRobot(String url){
        prePage.openFileRobot(url);
    }

    @Step
    public void verifyContent(String currentURL, String exactURL){
        prePage.verifyContent(currentURL, exactURL);
    }

    @Step
    public String contentRobotTxtSF(){
        return prePage.contentRobotTxtSF();
    }

}
