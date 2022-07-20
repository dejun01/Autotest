package common;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Properties;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.openqa.selenium.support.ui.Select;


/**
 * @author Trang Pham
 * @version 201903
 * @Description A base class representing a WebDriver page object. This class
 * extends core PageObject class and we can add common methods for
 * HDDT site. Using likes PageObject class.
 * Don't create specific page here.
 */
public abstract class BFlowPageObject extends CommonPageObject {

    private static final TemporalUnit SECONDS = ChronoUnit.SECONDS;
    public static Properties SETTING = null;

    // region Construction
    protected BFlowPageObject() {
        super();
//        setImplicitTimeout(5, SECONDS);
    }

    public BFlowPageObject(WebDriver driver) {
        super(driver);
    }

    /**
     * @param _xpathParent ,_labelName, _resOrder
     * @Description Verify radio button then select to button.
     */
    public void selectRadioButton(String _xpathParent, int _resOrder, boolean _ischeck) {
        String _objectStatus = "(" + _xpathParent + "//input[@type='radio'])[" + _resOrder + "]";
        String _objectClick = "(" + _xpathParent + "//span[@class='kit-radio-checkmark' or @class='ant-radio-inner'])[" + _resOrder + "]";
        verifyCheckedThenClick(_objectStatus, _objectClick, _ischeck);
    }

    /**
     * @param _xpathParent ,_labelName, _resOrder
     * @Description Verify radio button then select to button.
     */
    public void selectRadioButtonWithLabel(String _xpathParent, String _labelName, int _resOrder, boolean _ischeck) {
        String _objectStatus = "(" + _xpathParent + "//*[normalize-space()='" + _labelName + "']//input[@type='radio'])[" + _resOrder + "]";
        String _objectClick = "(" + _xpathParent + "//*[normalize-space()='" + _labelName + "']//span[@class='kit-radio-checkmark'])[" + _resOrder + "]";
        verifyCheckedThenClick(_objectStatus, _objectClick, _ischeck);
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


    // BEGIN Checkbox
    public void checkCheckbox(String _parentXpath, int _resOrder, boolean _isCheck) {
        String xPathStatus = "(" + _parentXpath + "//input[@type='checkbox'])[" + _resOrder + "]";
        String xPathCheckbox = "(" + _parentXpath + "//span[@class='kit-checkbox-checkmark' or @class='ant-checkbox-inner'])[" + _resOrder + "]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, _isCheck);
    }

    public void checkCheckboxWithLabel(String _labelName, int _resOrder) {
        checkCheckboxWithLabel(_labelName, _resOrder, true);
    }

    public void checkCheckboxWithLabel(String _labelName, boolean _isCheck) {
        checkCheckboxWithLabel("", _labelName, 1, _isCheck);
    }

    public void checkCheckboxWithLabel(String _labelName) {
        checkCheckboxWithLabel(_labelName, 1);
    }

    public void checkCheckboxWithLabel(String _parentXpath, String _labelName, int _resOrder, boolean _isCheck) {
        String xPathStatus = "(" + _parentXpath + "//*[child::label[text()[normalize-space()=\"" + _labelName + "\"]]]//input[@type='checkbox'])[" + _resOrder + "]";
        String xPathCheckbox = "(" + _parentXpath + "//*[child::label[text()[normalize-space()=\"" + _labelName + "\"]]]//span[@class='kit-checkbox-checkmark'])[" + _resOrder + "]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, _isCheck);
    }

    public void checkCheckboxWithLabel(String _labelName, int _resOrder, boolean _isCheck) {
        checkCheckboxWithLabel("", _labelName, _resOrder, _isCheck);
    }


    // END Radio Button
    // StartDropdown list

    public void selectDdl(String _xpathParent, String _value, int _resOrder) {
        String _xpath = "(" + _xpathParent + "//div[contains(@class,'kit-input-label') or contains(@class,'kit-input-dropdown-label')])[" + _resOrder + "]";
        clickOnElement(_xpath);
        chooseValueOnPopupDdl(_value);
    }

    public void chooseValueOnPopupDdl(String _value) {
        waitUntilElementVisible("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']", 10);
        scrollIntoElementView("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//label[text()[normalize-space()='" + _value + "']]");
        waitABit(1000);
        try {
            clickOnElement("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//label[text()[normalize-space()='" + _value + "']]");
        } catch (Exception ex) {
            clickOnElementByJs("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//label[text()[normalize-space()='" + _value + "']]");
        }
    }

    @Step
    public void selectDdlWithLabel(String _xpathParent, String _labelName, String _value, int _resOrder) {
        String _xpath = "(" + _xpathParent + "//div[@class='kit-input-label' or @class='kit-input-dropdown-label' ][ child::*[text()='" + _labelName + "' or contains(.,'" + _labelName + "')]])[" + _resOrder + "]";
        clickOnElement(_xpath);
        waitABit(1000);
        chooseValueOnPopupDdl(_value);
    }

    public void chooseValueOnPopupDdl2(String _value) {
        waitUntilElementVisible("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']", 10);
        scrollIntoElementView("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//label[text()[normalize-space()='" + _value + "']]");
        waitABit(1000);
        int index = countElementByXpath("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//div[contains(@class,'kit-input-dropdown-item') and descendant::*[normalize-space()='12']]/preceding-sibling::div") + 1;
        clickOnElement("(//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//div[contains(@class,'kit-input-dropdown-item')])[" + index + "]//label");
    }

    public void selectDdlWithLabel(String _labelName, String _value) {
        selectDdlWithLabel("", _labelName, _value, 1);
    }

    public void selectDdlWithLabel(String _labelName, String _value, int _resOrder) {
        selectDdlWithLabel("", _labelName, _value, _resOrder);
    }

    /**
     * @param _labelName
     * @Description Click on button by button name
     */
    public void clickBtn(String _parentXpath, String _labelName, int _resOrder) {
        waitForEverythingComplete();
        XH(xPathBtn(_parentXpath, _labelName, _resOrder)).waitUntilClickable().click();
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
        return "(" + _parentXpath + "//button[descendant-or-self::*[" + optionLabel(_labelName) + "]])[" + _resOrder + "]";

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

    public boolean isClickableBtn(String _parentXpath, String _btnName) {
        return isClickableBtn(_parentXpath, _btnName, 1);
    }

    public boolean isClickableBtn(String _btnName, int _resOrder) {
        return isClickableBtn("", _btnName, _resOrder);
    }

    public boolean isClickableBtn(String _btnName) {
        return isClickableBtn("", _btnName, 1);
    }


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


    public String xpathLinkText(String _parentXpath, String _linkName, int _resOrder) {
        String xPath = "(" + _parentXpath + "//*" + sTextPredicates(_linkName)
                + ")[" + _resOrder + "]";

        return xPath;
    }


    public boolean isLinkTextExist(String _parentXpath, String _labelName, int _resOrder) {
        return isElementExist(xpathLinkText(_parentXpath, _labelName, _resOrder));
    }

    public boolean isLinkTextExist(String _labelName, int _resOrder) {
        return isElementExist(xpathLinkText("", _labelName, _resOrder));
    }

    public boolean isLinkTextExist(String _labelName) {
        return isElementExist(xpathLinkText("", _labelName, 1), 5);
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
        clickOnElement("//ul[contains(@class,'tree-menu-ul') or contains(@class,'nav-sidebar')]//li//*[text()[normalize-space()='" + _labelName + "']]|//div[@class='container']//*[contains(text(),'" + _labelName + "')]|//div[@role='tab' ][descendant-or-self ::*[contains(text(),'" + _labelName + "')]]");
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
    public void typeAndEnterInputFieldWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        waitTypeAndEnter(xPathInputFieldWithLabel(_parentXpath, _labelName, _resOrder), _value);
    }

    public void typeAndEnterInputFieldWithLabel(String _labelName, String _value, int _resOrder) {
        typeAndEnterInputFieldWithLabel("", _labelName, _value, _resOrder);
    }

    public void typeAndEnterInputFieldWithLabel(String _labelName, String _value) {
        typeAndEnterInputFieldWithLabel("", _labelName, _value, 1);
    }

    public void enterInputFieldWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        clickAndClearThenType(xPathInputFieldWithLabel(_parentXpath, _labelName, _resOrder), _value);
    }

    public void clickLabelNameThenEnterInputFieldWithLabel(String _labelName, String _value) {
        clickLabelNameThenEnterInputFieldWithLabel("", _labelName, _value, 1);
    }

    public void clickLabelNameThenEnterInputFieldWithLabel(String _labelName, String _value, int _resOrder) {
        clickLabelNameThenEnterInputFieldWithLabel("", _labelName, _value, _resOrder);
    }

    public void clickLabelNameThenEnterInputFieldWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        waitForEverythingComplete();
        clickOnElement("(" + _parentXpath + "//*[text()[normalize-space()=\"" + _labelName + "\"]])[" + _resOrder + "]");
        clickAndClearThenType(xPathInputFieldWithLabel("", _labelName, 1), _value);
    }

    public void enterInputFieldWithLabel(String _labelName, String _value, int _resOrder) {
        enterInputFieldWithLabel("", _labelName, _value, _resOrder);
    }

    public void enterInputFieldWithLabel(String _labelName, String _value) {
        enterInputFieldWithLabel("", _labelName, _value, 1);
    }

    public String xPathInputFieldWithLabel(String _parentXpath, String _labelName, int _resOrder) {
        return "(" + _parentXpath + "//input[@placeholder =\"" + _labelName + "\" or preceding-sibling::div[@class='kit-input-label']//div[normalize-space()=\"" + _labelName + "\"]])[" + _resOrder + "]";
    }

    public void verifyValueInputFieldWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        String valueActual = XH(xPathInputFieldWithLabel(_parentXpath, _labelName, _resOrder)).getTextValue();
        assertThat(valueActual).isEqualTo(_value);
    }

    public void verifyValueInputFieldWithLabel(String _labelName, String _value, int _resOrder) {
        verifyValueInputFieldWithLabel("", _labelName, _value, _resOrder);
    }

    public void verifyValueInputFieldWithLabel(String _labelName, String _value) {
        verifyValueInputFieldWithLabel("", _labelName, _value, 1);
    }

    //enter input field with label contains "'"
    public void clickThenEnterInputFieldWithSpecialLabel(String _labelName, String _value) {
        clickOnElement("(//div[@class='kit-input-label']//div[" + specialTextPredicates(_labelName) + "])[1]");
        clickAndClearThenType(xPathInputFieldWithSpecialLabel("", _labelName, 1), _value);
    }

    public String xPathInputFieldWithSpecialLabel(String _parentXpath, String _labelName, int _resOrder) {
        return "(" + _parentXpath + "//input[" + specialTextPredicates(_labelName) + " or preceding-sibling::div[@class='kit-input-label']//div[" + specialTextPredicates(_labelName) + "]])[" + _resOrder + "]";
    }

    /**
     * @param _parentXpath,_labelName,_value,_resOrder
     * @Description enter value to area .
     */
    public void enterTextAreaWithLabel(String _parentXpath, String _labelName, String _value, int _resOrder) {
        waitForEverythingComplete();
        clickAndClearThenType(xPathTextAreaWithLabel(_parentXpath, _labelName, 1), _value);
    }

    public void enterTextAreaWithLabel(String _labelName, String _value) {
        enterTextAreaWithLabel("", _labelName, _value, 1);
    }

    public void enterTextAreaWithLabel(String _labelName, String _value, int _resOrder) {
        enterTextAreaWithLabel("", _labelName, _value, _resOrder);
    }

    public String xPathTextAreaWithLabel(String _parentXpath, String _labelName, int _resOrder) {
        return "(" + _parentXpath + "//div[child::*[text()='" + _labelName + "']]//textarea)[" + _resOrder + "]";
    }

    public void enterTextArea(String _parentXpath, String _value, int _resOrder) {
        waitClearAndType("(" + _parentXpath + "//textarea)[" + _resOrder + "]", _value);
    }

    /**
     * @param _value,_textContains
     * @Description Add products/collections on selector
     */

    public void addItemOnSelector(String _value) {
        String valuas[] = _value.split(",");
        for (String vl : valuas) {
            waitUntilElementVisible("//div[@id='product-selector-modal']//div[@class='kit-popup']", 20);
//            verifyElementPresent("//div[@id='product-selector-modal']//div[@class='kit-popup']", true);
            clickAndClearThenType("//div[@id='product-selector-modal']//div[@class='kit-popup']//input", vl);
            waitABit(2000);
            System.out.println(vl);
            clickOnElement("(//div[contains(@class,'scrollabel-section')]//div[contains(@class,'product-selector__item')])[1]//div[contains(@class,'product-action')]");
            if (isElementExist("//div[contains(@class,'input-search')]//i[contains(@class,'close')]")) {
                clickOnElement("//div[contains(@class,'input-search')]//i[contains(@class,'close')]");
            }
        }
        clickOnElement("//div[@class='popup-footer']//button");
    }

    public void removeItemOnSellector(String _value) {
        String values[] = _value.split(",");
        for (String vl : values) {
            clickOnElement("(//div[contains(@class,'product-selected-inner')]//div[contains(@class,'product-selector__item') and descendant-or-self::div[text()='" + vl + "']])[1]//div[@class='product-action']");
        }
        clickOnElement("//div[@class='popup-footer']//button");
    }

    public void turnOnToggleWithLabel(String _labelName, int _resOrder, boolean _isTurnOn) {
        turnOnToggleWithLabel("", _labelName, _resOrder, _isTurnOn);
    }

    public void turnOnToggleWithLabel(String xpathParent, String _labelName, int _resOrder, boolean _isTurnOn) {
        String xpath = xpathParent + "(//div[@class='kit-section-title' or @class='kit-action-bar'  or contains(@class,'kit-section-content')][descendant::text()[normalize-space()='" + _labelName + "']]//div[@class='kit-btn kit-btn-switch kit-btn-md'])[" + _resOrder + "]";
        String _objectcheckStatus = xpath + "//input";
        String _objectClick = xpath + "//span[@class='slider']//label";
        verifyCheckedThenClick(_objectcheckStatus, _objectClick, _isTurnOn);
    }

    public void turnOnToggle(String xpathParent, int _resOrder, boolean _isTurnOn) {
        String _objectcheckStatus = "(" + xpathParent + "//input)[" + _resOrder + "]";
        String _objectClick = "(" + xpathParent + "//span[@class='slider']//label)[" + _resOrder + "]";
        verifyCheckedThenClick(_objectcheckStatus, _objectClick, _isTurnOn);
    }

    /**
     * @param _givenText
     * @return
     * @Description Create text predicates with text contains special characters.
     * Note: It's not include [].
     */
    public String specialTextPredicates(String _givenText) {
        String[] tokens = _givenText.split("\"|\\'");
        int numText = tokens.length;
        String sSearchPattern = "";
        if (numText > 1) {
            sSearchPattern = "contains(.,'" + tokens[0] + "')";
            for (int i = 1; i < numText; i++) {
                sSearchPattern += " and contains(.,'" + tokens[i] + "')";
            }
            return sSearchPattern;
        } else {
            return "contains(.,'" + _givenText + "')";
        }
    }

    public String optionLabel(String _labels) {
        _labels = _labels.replace("|", ",");
        String[] tokens = _labels.split(",");
        int numText = tokens.length;
        String sSearchPattern = "";
        if (numText > 1) {
            sSearchPattern = "normalize-space()='" + tokens[0] + "'";
            for (int i = 1; i < numText; i++) {
                sSearchPattern += " or normalize-space()='" + tokens[i] + "'";
            }
            return sSearchPattern;
        } else {
            return "normalize-space()='" + _labels + "'";
        }
    }

    /**
     * @param _givenText
     * @return
     * @Description Create text predicates with text contains special characters and
     * space. Note: It included [].
     */
    public String sTextPredicates(String _givenText) {
        String[] tokens = _givenText.split("\"|\\'| ");
        int numText = tokens.length;
        String sSearchPattern = "";
        if (numText > 1) {
            sSearchPattern = "[contains(.,'" + tokens[0] + "')";
            for (int i = 1; i < numText; i++) {
                sSearchPattern += " and contains(.,'" + tokens[i] + "')";
            }
            return sSearchPattern + "and string-length(normalize-space(translate(., '\u00A0', ''))) < "
                    + (int) (_givenText.length() + 5) + "]";
        } else {
            return "[contains(text(),'" + _givenText
                    + "') and string-length(normalize-space(translate(., '\u00A0', ''))) < "
                    + (int) (_givenText.length() + 5) + "]";
        }
    }

    public void selectDdlByOption(String xpathparent, String option) {
        String xpath = xpathparent + "/select/option[text()[normalize-space()='" + option + "']]";
        clickOnElement(xpath);
    }

    public void setStyle(String _msgStyle, int _resOrder) {
        setStyle("", _msgStyle, _resOrder);
    }

    public void setStyle(String _xpathParent, String _msgStyle, int _resOrder) {
        if (!_msgStyle.isEmpty()) {
            String st[] = _msgStyle.split(",");
            selectMsgFontFamily(_xpathParent, st[0], _resOrder);
            selectMsgSize(_xpathParent, st[1], _resOrder);
            isBold(_xpathParent, st[2], _resOrder);
        }
    }

    public void selectMsgFontFamily(String _xpathParent, String font, int _resOrder) {
        selectDdlWithLabel(_xpathParent, "Font family", font, _resOrder);
    }

    public void selectMsgSize(String xpathParent, String size, int _resOrder) {
        String _xpath = "(" + xpathParent + "//div[contains(@class,'bke_toolbar_group bke_font-size')]//div[@class='kit-input-dropdown-label'])[" + _resOrder + "]";
        if (!getText(_xpath).equalsIgnoreCase(size)) {
            clickOnElement(_xpath);
            waitUntilElementVisible("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']", 10);
            scrollIntoElementView("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//label[text()[normalize-space()='" + size + "']]");
            scrollTo(1920, 1800);
            clickOnElementByJs("//div[contains(@class,'kit-input-focus')]//div[@class='kit-input-dropdown-content']//label[text()[normalize-space()='" + size + "']]");
        }
    }

    public void isBold(String xpathParent, String isBold, int _resOrder) {
        String xpath = "(" + xpathParent + "//div[@class='bke_toolbar_group bke_font-style'])[" + _resOrder + "]";
        verifyCheckedThenClick(xpath + "//input", xpath + "//label", Boolean.parseBoolean(isBold));
    }

    public void openSelector(String xpathParent) {
        clickOnElement(xpathParent + "//button[text()[normalize-space()='Select products' or normalize-space()='Select collections']]");
    }

    public void openSelector() {
        openSelector("");
    }


    public void switchToMenu(String _labelName) {
        clickOnElement("//ul[contains(@class,'tree-menu-ul') or contains(@class,'nav-sidebar')]//li//*[text()[normalize-space()='" + _labelName + "']]");
    }

    public JsonPath getAPICrosspanda(String url, String accessToken) {
        Response resp = given().header("X-Panda-Access-Token", accessToken).get(url);
        Assertions.assertThat(resp.getStatusCode()).isBetween(200, 201);
        JsonPath jp = resp.then().extract().jsonPath();
        return jp;
    }

    public String getTextInTable(String xpathParent, int col, int row) {
        String xpath = xpathParent + "//table/tbody/tr[" + row + "]/td[" + col + "]";
        return getText(xpath);
    }


    public int getColIndexByColName(String xpathParent, String colName) {
        int index = 0;
        String xpath = xpathParent + "//table/thead/tr/th[descendant-or-self::*[normalize-space()='" + colName + "']]";
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::th") + 1;
        }
        return index;
    }

    public String getValueSelected(String xpath) {
        Select select = new Select(XH(xpath));
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }
}