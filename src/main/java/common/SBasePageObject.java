package common;

import common.utilities.LoadObject;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Trang Pham
 * @version 201904
 * @Description A base class representing a WebDriver page object. This class
 * extends core PageObject class and we can add common methods for
 * HDDT site. Using likes PageObject class.
 * Don't create specific page here.
 */
public abstract class SBasePageObject extends CommonPageObject {
    private static final TemporalUnit SECONDS = ChronoUnit.SECONDS;

    // region Construction
    protected SBasePageObject() {
        super();
        setImplicitTimeout(5, SECONDS);
    }

    public SBasePageObject(WebDriver driver) {
        super(driver);
    }

    /**
     * @param _label
     * @param _value
     * @Description Click then Type into Element Char by Char
     */
    public void waitTypeCharByCharWithLabel(String _label, String _value) {
        String _xPath = "//div[@class='s-form-item' and descendant::*[normalize-space()='" + _label + "']]//input";
        clickThenTypeCharByChar(_xPath, _value);
    }

    // BEGIN Checkbox

    public void checkCheckboxWithLabel(String _labelName, int _resOrder) {
        checkCheckboxWithLabel(_labelName, _resOrder, true);
    }

    public void checkCheckboxWithLabel(String _labelName, boolean _isOn) {
        checkCheckboxWithLabel(_labelName, 1, _isOn);
    }

    public void checkCheckboxWithLabel(String _labelName) {
        checkCheckboxWithLabel(_labelName, 1);
    }

    public void checkCheckboxWithLabel(String _parentXpath, String _labelName, int _resOrder, boolean _isCheck) {
        String xPathStatus = "(" + _parentXpath + "//*[child::*[text()[normalize-space()=\"" + _labelName + "\"]]]//input[contains(@type,\"checkbox\")])[" + _resOrder + "]|(//*[text()[normalize-space()=\"" + _labelName + "\"]]//ancestor::label//input[contains(@type,'checkbox')])[" + _resOrder + "]";
        String xPathCheckbox = "(" + _parentXpath + "//*[text()[normalize-space()=\"" + _labelName + "\"]])[" + _resOrder + "]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, _isCheck);
    }

    public void checkCheckboxWithLabel(String _labelName, int _resOrder, boolean _isCheck) {
        checkCheckboxWithLabel("", _labelName, _resOrder, _isCheck);
    }

    public void checkCheckbox(String _parentXpath, boolean _isCheck) {
        checkCheckbox(_parentXpath, 1, _isCheck);
    }

    public void checkCheckbox(String _parentXpath, int index, boolean _isCheck) {
        String xPathStatus = "(" + _parentXpath + "//input[@type='checkbox'])[" + index + "]";
        String xPathCheckbox = "(" + _parentXpath + "//span[@class='s-check' or @class='sb-check'])[" + index + "]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, _isCheck);
    }
    // END Checkbox

    // BEGIN Radio Button

    /**
     * @param _xpathParent ,_labelName, _resOrder
     * @Description Verify radio button then select to button.
     */
    public void selectRadioButtonWithLabel(String _xpathParent, String _labelName, int _resOrder, boolean _ischeck) {
        String _objectcheckStatus = "(" + _xpathParent + "//input[@type='radio'][parent::*[text()[normalize-space()=\"" + _labelName + "\"]] or following-sibling::span[normalize-space()=\"" + _labelName + "\"] or following-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]] or following-sibling::span//*[text()[normalize-space()=\"" + _labelName + "\"]]][1])[" + _resOrder + "]";
        String _objectClick = "(" + _xpathParent + "//span[@class='circle' or contains(@class,'s-check') or contains(@class, 'sb-check')][parent::*[text()[normalize-space()=\"" + _labelName + "\"]] or normalize-space()=\"" + _labelName + "\" or following-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]] or following-sibling::span//*[text()[normalize-space()=\"" + _labelName + "\"]]][1])[" + _resOrder + "]";
        verifyCheckedThenClick(_objectcheckStatus, _objectClick, _ischeck);
    }


    /**
     * @param _labelName,_resOrder
     * @Description Verify radio button then select to button.
     */
    public void selectRadioButtonWithLabel(String _labelName, int _resOrder, boolean _ischeck) {
        selectRadioButtonWithLabel("", _labelName, _resOrder, _ischeck);
    }

    public void selectRadioButtonWithLabel(String _labelName, boolean _ischeck) {
        selectRadioButtonWithLabel("", _labelName, 1, _ischeck);
    }

    // END Radio Button
    // StartDropdown list
    public void selectDdlWithLabel(String xpathParent, String _labelName, String _value, int _resOrder) {
        String _xpath = "((" + xpathParent + "//*[descendant-or-self::*[text()[normalize-space()=\"" + _labelName + "\"]]]/following-sibling::div//select)[" + _resOrder + "]|(" + xpathParent + "//*[text()[normalize-space()=\"" + _labelName + "\"]]/following-sibling::select)[" + _resOrder + "])";
        waitElementToBePresentThenScrollIntoView(_xpath).waitUntilEnabled().waitUntilVisible();
        clickOnElement(_xpath + "/option[text()[normalize-space() =\"" + _value + "\"]]");
    }

    public void selectDdlWithLabel(String _labelName, String _value) {
        selectDdlWithLabel("", _labelName, _value, 1);
    }

    public void selectDdlWithLabel(String _labelName, String _value, int _resOrder) {
        selectDdlWithLabel("", _labelName, _value, _resOrder);
    }

    public void selectDdlByXpath(String xPath, String value) {
        clickOnElement(xPath);
        String xpathValue = xPath + "//option[text()[normalize-space() =\"" + value + "\"]]";
        clickOnElement(xpathValue);
        clickOnElement(xPath);
    }

    public void selectCustomDdWithLabel(String xpathParent, String _labelName, String _value, int _resOrder) {
        String _xpath = "((" + xpathParent + "//*[descendant-or-self::*[text()[normalize-space()=\"" + _labelName + "\"]]]/following::input)[" + _resOrder + "]|(" + xpathParent + "//input[@placeholder = \"" + _labelName + "\"])[" + _resOrder + "])";
        XH(_xpath).waitUntilClickable().click();
        clickOnElement(_xpath + "/following::div[normalize-space()=\"" + _value + "\"]");
    }

    public void selectCustomDdWithLabel(String _labelName, String _value) {
        selectCustomDdWithLabel("", _labelName, _value, 1);
    }

    //end
    // Button

    /**
     * @param _labelName
     * @Description Click on button by button name
     */
    public void clickBtn(String _parentXpath, String _labelName, int _resOrder) {
        XH(xPathBtn(_parentXpath, _labelName, _resOrder) + "[not(@disabled='disabled') or not(@style='display: none;')]").waitUntilVisible().waitUntilEnabled().waitUntilClickable().click();
        waitForEverythingComplete();
    }

    /**
     * @param _labelName
     * @Description Click on button by button name
     */
    public void clickBtn(String _labelName) {
        clickBtn("", _labelName, 1);
    }

    public void clickBtn(String _labelName, int _resOrder) {
        clickBtn("", _labelName, _resOrder);
    }

    public String xPathBtn(String _parentXpath, String _labelName, int _resOrder) {
        return "(" + _parentXpath + "//button[preceding-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]] or descendant-or-self::*[text()[normalize-space()=\"" + _labelName + "\"]]])[" + _resOrder + "]";
    }

    /**
     * @param _parentXpath
     * @param _btnName
     * @param _resOrder
     * @return
     * @Description Check button is clickable or not by button name
     */
    public boolean isClickableBtn(String _parentXpath, String _btnName, int _resOrder) {
        return XH(xPathBtn(_parentXpath, _btnName, _resOrder)).isEnabled();
    }

    public boolean isClickableBtn(String _btnName) {
        return isClickableBtn("", _btnName, 1);
    }

    public boolean isElementClickable(String xPath) {
        return XH(xPath).isClickable();
    }

    // Link

    /**
     * @param _parentXpath
     * @param _labelName
     * @param _resOrder
     * @Description xPath of Link by text
     */

    public void clickLinkTextWithLabel(String _parentXpath, String _labelName, int _resOrder) {
        clickOnElement(xpathLinkText(_parentXpath, _labelName, _resOrder));
        waitForEverythingComplete();
    }

    public void clickLinkTextWithLabel(String _labelName) {
        clickLinkTextWithLabel("", _labelName, 1);
    }

    public void clickLinkTextWithLabel(String _labelName, int _resOrder) {
        clickLinkTextWithLabel("", _labelName, _resOrder);
    }

    public String xpathLinkText(String _parentXpath, String _linkName, int _resOrder) {
        String xPath = "(" + _parentXpath + "//*[ text()[normalize-space()=\"" + _linkName + "\"] or child::span[normalize-space()=\"" + _linkName + "\"]])[" + _resOrder + "]";
        return xPath;
    }


    public boolean isLinkTextExist(String _parentXpath, String _labelName, int _resOrder) {
        return isElementExist(xpathLinkText(_parentXpath, _labelName, _resOrder));
    }

    public boolean isLinkTextExist(String _labelName, int _resOrder) {
        return isElementExist(xpathLinkText("", _labelName, _resOrder));
    }

    public boolean isLinkTextExist(String _labelName) {
        return isElementExist(xpathLinkText("", _labelName, 1));
    }

    public void verifyLinkText(String _parentXpath, String _labelName, int _resOrder, boolean _isPresent) {
        verifyElementPresent(xpathLinkText(_parentXpath, _labelName, _resOrder), _isPresent);
    }

    public void verifyLinkText(String _labelName, int _resOrder, boolean isExist) {
        verifyLinkText("", _labelName, _resOrder, isExist);
    }

    public void verifyLinkText(String _labelName, boolean isExist) {
        verifyLinkText("", _labelName, 1, isExist);
    }

    // End Link

    /**
     * @param _labelName
     * @Description Switch to tab
     */

    public void switchToTab(String _labelName) {
        String xPath = "//ul[contains(@class,'menu') or contains(@class,'nav-sidebar')]//li//*[text()[normalize-space()='" + _labelName + "']]|(//span[following-sibling::*[normalize-space()='" + _labelName + "']])[1]";
        waitUntilElementVisible(xPath, 60);
        clickOnElement(xPath);


    }


    // region Input Field, TEXT AREA with Label

    // Input Field

    /**
     * @param _parentXpath
     * @param _labelName
     * @param _value
     * @param _resOrder
     * @Description Enter value into Input Field besides Label, enter then wait for
     * updating value.
     */
    public void enterInputFieldWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        if (_value.equals("@BLANK@")) {
            _value = " ";
        }
        String xpath = xPathInputFieldWithLabel(_parentXpath, _labelName, _resOrder);
        if (!$(xpath).getTextValue().equals(_value) | !$(xpath).getValue().equals(_value)) {
            $(xpath).click();
            $(xpath).clear();
            clearTextByJS(xpath);
            $(xpath).type(_value);
        }
    }


    /**
     * @param _parentXpath
     * @param _labelName
     * @param _value
     * @param _resOrder
     * @Description Enter value into Input Field besides Label, enter then wait for
     * updating value.
     */
    public void enterInputFieldWithLabelCharByChar(String _parentXpath, String _labelName, String _value, int _resOrder) {
        if (_value.equals("@BLANK@")) {
            _value = " ";
        }
        clickThenTypeCharByChar(xPathInputFieldWithLabel(_parentXpath, _labelName, _resOrder), _value);
    }

    /**
     * @param _labelName
     * @param _value
     * @param _resOrder
     * @Description Enter value into Input Field besides Label, enter then wait for
     * updating value.
     */
    public void enterInputFieldWithLabelCharByChar(String _labelName, String _value, int _resOrder) {
        enterInputFieldWithLabelCharByChar("", _labelName, _value, _resOrder);
    }


    public void enterInputFieldWithLabel(String _labelName, String _value, int _resOrder) {
        enterInputFieldWithLabel("", _labelName, _value, _resOrder);
    }

    public void enterInputFieldWithLabel(String _labelName, String _value) {
        enterInputFieldWithLabel("", _labelName, _value, 1);
    }

    public void enterInputFieldThenEnter(String _parentXpath, String _labelName, String _value, int _resOrder) {
        waitTypeAndEnterThenUpdateValue(xPathInputFieldWithLabel(_parentXpath, _labelName, _resOrder), _value);
    }

    public void enterInputFieldThenEnter(String _labelName, String _value, int _resOrder) {
        enterInputFieldThenEnter("", _labelName, _value, _resOrder);
    }


    public String xPathInputFieldWithLabel(String _parentXpath, String _labelName, int _resOrder) {
        return "((" + _parentXpath + "//input[@id=\"" + _labelName + "\" or @type=\"" + _labelName + "\" or @placeholder=\"" + _labelName + "\" or contains(text(),\"" + _labelName + "\") or preceding-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]] or preceding::*[text()[normalize-space()=\"" + _labelName + "\"]] or @name=\"" + _labelName + "\"or preceding-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]]])[" + _resOrder + "]|(" + _parentXpath + "//*[descendant-or-self::*[text()=\"" + _labelName + "\"]]/following-sibling::*//input)[" + _resOrder + "])[1]";
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait for element to be input, type given value and enter then
     * wait for element update value. NOTE: if existed value equal to
     * new value, it will keep existed value.
     */
    public void waitTypeOnElement(String _xPath, String _value) {
        if (!waitElementToBePresentThenScrollIntoView(_xPath).getTextValue().equals(_value)) {
            String xpathInput = _xPath + "[not(@readonly='readonly' or @readonly='true' or @disabled='true' or @disabled='disabled')]";
            XH(xpathInput).waitUntilEnabled().waitUntilVisible().clear();
            XH(xpathInput).type(_value);
            waitForEverythingComplete();
        }
    }


    /**
     * @param _parentXpath,_labelName,_value,_resOrder
     * @Description enter value to area .
     */
    public void enterTextAreaWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        waitTypeOnElement(xPathTextAreaWithLabel(_parentXpath, _labelName, _resOrder), _value);

    }

    public void enterTextAreaWithLabel(String _labelName, String _value) {
        enterTextAreaWithLabel("", _labelName, _value, 1);
    }

    public void enterTextAreaWithLabel(String _labelName, String _value, int _resOrder) {
        enterTextAreaWithLabel("", _labelName, _value, _resOrder);
    }

    public String xPathTextAreaWithLabel(String parentXpath, String _labelName, int _resOrder) {
        return "(" + parentXpath + "//textarea[@placeholder=\"" + _labelName + "\" or preceding-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]] or preceding::*[text()[normalize-space()=\"" + _labelName + "\"]] or ancestor::*[preceding-sibling::*[text()[normalize-space()=\"" + _labelName + "\"]]]])[" + _resOrder + "]";
    }


    public Response getResponseCredentials() {
        String username = LoadObject.getProperty("user.name");
        String pwd = LoadObject.getProperty("user.pwd");
        return getResponseCredentialsWithAcc(username, pwd);
    }

    public Response getResponseCredentialsWithAcc(String userName, String pass) {
        String gapiUrl = LoadObject.getProperty("gapi.url");
        String url = gapiUrl + "/v1/auth/credentials";
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("username", userName);
        requestParams.addProperty("password", pass);
        System.out.println(" xxx" +postAPI(url, requestParams));
        return postAPI(url, requestParams);
    }

    public String getAccessTokenShopBase(String userName, String pass, String shop) {
        String gapiUrl = LoadObject.getProperty("gapi.url");
        Response response = getResponseCredentialsWithAcc(userName, pass);
        int userId = response.then().extract().path("user_id");
        String userAccessToken = response.then().extract().path("access_token");
        String url = gapiUrl + "/v1/auth/oauth/grant-shop-access";
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("user_id", userId);
        requestParams.addProperty("shop_domain", shop);
        JsonPath jpAccessToken = postAPISbase(url, requestParams, userAccessToken);
        String accessTokenShop = jpAccessToken.get("access_token");
        return accessTokenShop;
    }
    public String getAccessTokenShopBase(String shopDomain) {
        String username = LoadObject.getProperty("user.name");
        String pwd = LoadObject.getProperty("user.pwd");
        return getAccessTokenShopBase(username, pwd, shopDomain);
    }

    public String getAccessTokenShopBase() {
        String username = LoadObject.getProperty("user.name");
        String pwd = LoadObject.getProperty("user.pwd");
        String shopDomain = LoadObject.getProperty("shop");
        return getAccessTokenShopBase(username, pwd, shopDomain);
    }

    public String getAccessTokenShopBaseByShop(String shopName, String username, String password) {
        return getAccessTokenShopBase(username, password, shopName);
    }

    public String getAccessTokenUser() {
        Response response = getResponseCredentials();
        String tokenUser = response.then().extract().path("access_token");
        return tokenUser;
    }

    public JsonPath getAPIStripeTrx(String url, String userName, String password) {
        RequestSpecification request = RestAssured.given();
        request.auth().basic(userName, password);
        Response response = request.get(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        JsonPath jp = response.then().extract().jsonPath();
        return jp;
    }

    public JsonPath getDataFromStripe(String url, String userName, String password, String connectedId) {
        RequestSpecification request = RestAssured.given();
        request.auth().basic(userName, password);
        request.header("Stripe-Account", connectedId);
        Response response = request.get(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        JsonPath jp = response.then().extract().jsonPath();
        return jp;
    }

    public JsonPath getAPISbase(String url, String accessToken) {
        Response resp = given().header("X-Shopbase-Access-Token", accessToken).get(url);
        assertThat(resp.getStatusCode()).isBetween(200, 201);
        JsonPath jp = resp.then().extract().jsonPath();
        return jp;
    }

    public JsonPath putAPISbase(String url, JsonObject requestParams, String accessToken) {
        Response response = given().header("Content-Type", "application/json").header("X-Shopbase-Access-Token", accessToken).body(requestParams.toString()).put(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        JsonPath jp = response.then().extract().jsonPath();
        return jp;
    }

    public JsonPath postAPISbase(String url, JsonObject requestParams, String accessToken) {
        Response response = given().header("Content-Type", "application/json").header("X-Shopbase-Access-Token", accessToken).body(requestParams.toString()).post(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        JsonPath jp = response.then().extract().jsonPath();
        return jp;
    }

    public Response deleteAPISbase(String url, JsonObject requestParams, String accessToken) {
        Response response = given().header("Content-Type", "application/json").header("X-Shopbase-Access-Token", accessToken).body(requestParams.toString()).delete(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        return response;
    }

    public Response deleteAPI(String url, String accessToken) {
        Response response = given().header("Content-Type", "application/json").header("X-Shopbase-Access-Token", accessToken).delete(url);
        return response;
    }


    public void addItemOnSelectorPopup(String products) {
        String values[] = products.split(",");
        for (String vl : values) {
            waitUntilElementVisible("//div[contains(@class,'s-modal-content')]", 20);
            clickAndClearThenType("//div[contains(@class,'s-modal-content')]//input", vl);
            waitABit(1000);
            String xpath = "(//div[@class='product-selector']//div[contains(@class,'product-selector__item') and  descendant::div[contains(.,\"" + vl + "\")]])[1]//span[contains(@class,'s-icon')]";
            try {
                clickOnElement(xpath);
            } catch (StaleElementReferenceException ex) {
                clickOnElement(xpath);
            }
            if (isElementExist("//div[contains(@class,'input-search')]//i[contains(@class,'close')]")) {
                clickOnElement("//div[contains(@class,'input-search')]//i[contains(@class,'close')]");
            }
        }
        waitABit(2000);
        assertThat(countElementByXpath("//div[@class='product-selected-inner']//div[contains(@class,'product-selector__item')]")).isEqualTo(values.length);
        clickOnElement("//div[@class='popup-footer']//button");
    }

    public void turnOnToggleWithLabel(boolean isOn) {
        checkCheckbox("//div[contains(@class,'ui-title-bar__heading-group') or contains(@class,'offer-heading')]", isOn);
        waitForEverythingComplete();
    }

    public void openSelector() {
        clickOnElement("//button[child::span[normalize-space()='Select products' or normalize-space()='Select collections'or normalize-space()='Select product' or normalize-space()='Select collection']]");
    }

    public void deleteAllCookies() {
        getDriver().manage().deleteAllCookies();
    }

    String popupConfirm = "//div[@class='s-dialog s-modal is-active']";

    public void confirmDeleteRecord() {
        waitForElementToPresent(popupConfirm);
        clickBtn(popupConfirm, "Delete", 1);
        waitForEverythingComplete();
    }

    String xpathActionBar = "//div[@class='save-setting-fixed'][not(@style='display: none;')]";

    public void saveSetting() {
        saveSetting("");
    }

    public void saveSetting(String msg) {
        if (isEnableActionbar()) {
            clickOnElement(xpathActionBar + "//button[child::span[normalize-space()='Save changes'] or child::span[normalize-space()='Save']]");
            if (!msg.isEmpty()) {
                waitForTextToAppear(msg, 10000);
            }
        }
    }

    public boolean isEnableActionbar() {
        return isElementExist(xpathActionBar);
    }

    public void verifyTextInputField(String _parentXpath, String label, String value, int _resOrder) {
        String xpath = xPathInputFieldWithLabel(_parentXpath, label, _resOrder);
        String actual = XH(xpath).getTextValue();
        assertThat(actual).isEqualTo(value);
    }

    public void verifyTextInputField(String label, String value, int _resOrder) {
        verifyTextInputField("", label, value, _resOrder);
    }

    public void verifyTextInputField(String label, String value) {
        waitForEverythingComplete();
        if (value.equalsIgnoreCase("@BLANK@"))
            value = "";
        verifyTextInputField("", label, value, 1);
    }

    public void selectByText(String xpath, String text) {
        Select select = new Select(XH(xpath));
        select.selectByVisibleText(text);
    }

    public String getValueSelected(String xpath) {
        Select select = new Select(XH(xpath));
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }
    public void selectBanklWithLabel(String _labelName,String _value) {
//        clickOnElement(_xpath + "/div[text()[normalize-space() =\"" + _value + "\"]]");
        String _iframe = "//iframe[contains(@title,'Bảo mật danh sách thả xuống lựa chọn ngân hàng iDEAL')]";
        String _xpath = "//*[text()[normalize-space()='"+ _labelName +"']]/parent::div[@role='combobox']";
        String _bank = "//div[normalize-space()='"+ _value +"']//parent::li";
        String _selectlist = "//ul[@class='SelectList SelectList--ltr']";
        clickOnElement(_xpath);
        switchToIFrame(_iframe);
        waitElementToBePresent(_selectlist);
        clickOnElement(_bank);
        switchToIFrameDefault();
    }

    public void closePopup() {
        String xpath = "//div[@class='onboarding-popup active']//div[@class='button-close']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }
}