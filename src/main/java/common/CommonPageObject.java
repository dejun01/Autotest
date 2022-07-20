package common;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import common.utilities.LoadObject;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static common.utilities.LoadObject.getFilePath;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Trang Pham
 * @version 201903
 * @Description A base class representing a WebDriver page object. This class
 * extends core PageObject class and we can add common methods for
 * HDDT site. Using likes PageObject class.
 * Don't create specific page here.
 */
public abstract class CommonPageObject extends PageObject {

    private static final TemporalUnit SECONDS = ChronoUnit.SECONDS;


    // region Construction
    protected CommonPageObject() {
        super();
        setImplicitTimeout(5, SECONDS);
    }


    public CommonPageObject(WebDriver driver) {
        super(driver);
    }

    // endregion Construction
    //region manage browser
    public void openBrowser(String _url) {
        openAt(_url);
        getDriver().manage().deleteAllCookies();
        maximizeWindow();
        waitForPageLoad();
    }

    public void navigateToUrl(String _url) {
        getDriver().navigate().to(_url);
        waitForPageLoad();
    }

    public void openUrlInNewTab(String url) {
        ((JavascriptExecutor) getDriver()).executeScript("window.open('" + url + "','_blank');");
    }

    public void closeBrowser() {
        getDriver().close();
    }

    public void quitBrowser() {
        getDriver().quit();
    }

    public void maximizeWindow() {
        try {
            setViewPort(1920, 1080);
            getDriver().manage().window().maximize();
        } catch (Exception e) {
            setViewPort(1920, 1080);
        }
    }

    public void getSizeWindow() {
        Dimension initial_size = getDriver().manage().window().getSize();
        int height = initial_size.getHeight();
        int width = initial_size.getWidth();
        System.out.println(" height : " + height + "/n width : " + width);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
        waitForEverythingComplete(60);
    }

    public void closeBrowserWithIndex(int index) {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(index));
        getDriver().close();
    }

    public void closeAllTabWithoutParent(String parentID) {
        Set<String> allWindows = getDriver().getWindowHandles();
        for (String runWindowID : allWindows) {
            getDriver().switchTo().window(runWindowID);
            if (!runWindowID.equals(parentID)) {
                getDriver().close();
            }
        }
        getDriver().switchTo().window(parentID);
    }

    public void switchToWindowWithIndex(int _index) {
        ArrayList<String> availableWindows = new ArrayList<String>(getDriver().getWindowHandles());
        if (!availableWindows.isEmpty()) {
            getDriver().switchTo().window(availableWindows.get(_index));
        }
        maximizeWindow();
    }

    public void switchToLatestTab() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        int size = getDriver().getWindowHandles().size();
        getDriver().switchTo().window(tabs.get(size - 1));
        maximizeWindow();
    }

    public void switchToTheFirstTab() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(0));
        maximizeWindow();
    }

    public void switchToTheLastestTab() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabs.size() - 1));
        maximizeWindow();
    }

    public void switchToNextTab() {
        String currentTab = getDriver().getWindowHandle();
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        int index = tabs.indexOf(currentTab);
        getDriver().switchTo().window(tabs.get(index + 1));
        maximizeWindow();
    }

    public int countNumberOfWWindow() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        return tabs.size();
    }

    public void setViewPort(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public void scrollToElement(String _xpath) {
        WebElement element = getDriver().findElement(By.xpath(_xpath));
        Point point = element.getLocation();
        int x_coordinate = point.getX();
        int y_coordinate = point.getY();

        scrollTo(x_coordinate, y_coordinate);
    }

    public void scrollTo(int x, int y) {
        evaluateJavascript("window.scrollTo(" + x + ", " + y + ");");
    }

    public void scrollToElementOther(String locator) {
        WebDriver driver = getDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getDriver().findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

//end
// region Common Wait

    public void waitForPageLoad() {
        WebDriver driver = getDriver();
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);

    }

    /**
     * @param _timeoutInSeconds Timeout In Seconds
     * @Description Wait for Modal loading or hiding complete
     */
    public void waitUntilElementVisible(String xPath, int _timeoutInSeconds) {
        try {
            withTimeoutOf(_timeoutInSeconds, TimeUnit.SECONDS)
                    .waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        } catch (Exception e) {
            System.out.println("***** INFO ***** ELEMENT NOT PRESENCE FOR OVER " + _timeoutInSeconds + "SECONDS.");
        }
    }

    /**
     * @param _timeoutInSeconds Timeout In Seconds
     * @Description Wait for jQuery complete
     */
    public void waitUntiljQueryRequestCompletes(int _timeoutInSeconds) {
        try {
            new FluentWait<WebDriver>(getDriver()).withTimeout(_timeoutInSeconds, TimeUnit.SECONDS)
                    .withMessage("***** INFO ***** JQUERY STILL LOADING FOR OVER" + _timeoutInSeconds + "SECONDS.")
                    .pollingEvery(50, TimeUnit.MILLISECONDS).until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            try {
                                JavascriptExecutor jsExec = (JavascriptExecutor) d;
                                return (Boolean) jsExec.executeScript("return jQuery.active == 0");
                            } catch (Exception e) {
                                return true;
                            }
                        }
                    });
        } catch (Exception e) {
        }
    }

    /**
     * @param _timeoutInSeconds Timeout In Seconds
     * @Description Wait for Ajax complete
     */
    public void waitUntilAjaxCompletes(int _timeoutInSeconds) {
        new FluentWait<WebDriver>(getDriver()).withTimeout(_timeoutInSeconds, TimeUnit.SECONDS)
                .withMessage("***** INFO ***** AJAX STILL ACTIVE FOR OVER " + _timeoutInSeconds + "SECONDS.")
                .pollingEvery(50, TimeUnit.MILLISECONDS).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        try {
                            JavascriptExecutor jsExec = (JavascriptExecutor) d;
                            return (Boolean) jsExec.executeScript("return $.active == 0");
                        } catch (Exception e) {
                            return true;
                        }
                    }
                });
    }

    /**
     * @param _timeoutInSeconds Timeout In Seconds
     * @Description Wait for HTML ready
     */
    public void waitUntilHTMLReady(int _timeoutInSeconds) {
        new FluentWait<WebDriver>(getDriver()).withTimeout(_timeoutInSeconds, TimeUnit.SECONDS)
                .withMessage("***** INFO ***** HTML STILL LOADING FOR OVER" + _timeoutInSeconds + "SECONDS.")
                .pollingEvery(50, TimeUnit.MILLISECONDS).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        try {
                            JavascriptExecutor jsExec = (JavascriptExecutor) d;
                            return (Boolean) jsExec.executeScript("return document.readyState").toString()
                                    .equals("complete");
                        } catch (Exception e) {
                            return true;
                        }
                    }
                });
    }

    /**
     * @Description : Waiting for all HTML, JS, Ajax, Loading Date pop-up finish
     * when doing any action. Using before or after each action.
     */
    public void waitForEverythingComplete(int _timeoutInSeconds) {
        waitABit(50);
        waitUntilHTMLReady(_timeoutInSeconds);
        waitUntiljQueryRequestCompletes(_timeoutInSeconds);
        waitUntilAjaxCompletes(_timeoutInSeconds);
        waitABit(50);
    }

    public void waitForEverythingComplete() {
        waitForEverythingComplete(50);
    }

    /**
     * Description Waiting for element invisible
     *
     * @param _xPath
     * @param _timeoutInSeconds
     */
    public boolean waitUntilElementInvisible(String _xPath, int _timeoutInSeconds) {

        try {
            withTimeoutOf(_timeoutInSeconds, TimeUnit.SECONDS)
                    .waitFor(ExpectedConditions.invisibilityOfElementLocated(By.xpath(_xPath)));
            resetImplicitTimeout();
            return true;
        } catch (Exception e) {
            resetImplicitTimeout();
            return false;
        }
    }

    /**
     * Description Check element exist or not immediately
     *
     * @param xPath
     * @return
     */
    public boolean isElementExistNow(String xPath) {
        waitForEverythingComplete();
        try {
            List<WebElementFacade> subDataCellResults = withTimeoutOf(500, TimeUnit.MILLISECONDS).findAll(xPath);
            if (subDataCellResults.size() != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * Description Check element exist or not immediately
     *
     * @param _xPath
     * @return
     */
    public boolean isElementExist(String _xPath, int _TimeOutInSecond) {
        try {
            setImplicitTimeout(_TimeOutInSecond, SECONDS);
            waitElementToBePresent(_xPath);
            resetImplicitTimeout();
            return true;
        } catch (Exception e) {
            resetImplicitTimeout();
        }
        return false;
    }

    public boolean isElementExist(String _xPath) {
        return isElementExist(_xPath, 5);
    }

// endregion Common Wait
// region Common xPath

    /**
     * @return xPath
     * @Description Convert from ID string to xPath string
     */
    public String iD(String _ID) {
        return "//*[@id='" + _ID + "']|//*[@data-testid='" + _ID + "']";
    }

    public String iD(String _parentXpath, String _ID) {
        return _parentXpath + "//*[@id='" + _ID + "']";
    }

    /**
     * @param _xPath
     * @Descriptsion Highlight element by red border
     */
    public void highlightElement(String _xPath) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='1px solid red'",
                    findBy(_xPath));
        } catch (Exception e) {
            // Do nothing
        }
    }

    public String name(String _name) {
        return "//*[@name='" + _name + "']";
    }

    /**
     * @param _class
     * @return xPath
     * @Description Convert from ID string to xPath string
     */
    public String cLass(String _class) {
        return "//*[contains(@class,'" + _class + "')]";
    }

    /**
     * @param _xPath
     * @Description Scroll into view of element
     */
    public void scrollIntoElementView(String _xPath) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", findBy(_xPath));
        waitABit(1000);
    }

    /**
     * @param _wE
     * @return WebElementFacade
     * @Description Scroll into view of element.
     */
    public WebElementFacade scrollIntoElementView(WebElementFacade _wE) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", _wE);
        waitABit(15);
        return _wE;
    }

    /**
     * @param _xPath
     * @return WebElementFacade
     * @Description Wait for element to be present then scroll into view of element
     * and highlight it.
     */
    public WebElementFacade waitElementToBePresentThenScrollIntoView(String _xPath) {
        return waitElementToBePresentThenScrollIntoView(_xPath, 20);
    }

    public WebElementFacade waitElementToBePresentThenScrollIntoView(String _xPath, int _timeout) {
        waitForEverythingComplete();
        withTimeoutOf(_timeout, SECONDS).waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(_xPath)));
        scrollIntoElementView(_xPath);
        highlightElement(_xPath);
        return findBy(_xPath);
    }

    public WebElementFacade waitElementToBePresent(String _xPath) {
        waitForEverythingComplete();
        withTimeoutOf(10, SECONDS).waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(_xPath)));
        scrollIntoElementView(_xPath);
        return findBy(_xPath);
    }

    public WebElementFacade XH(String _xPath) {
        return waitElementToBePresentThenScrollIntoView(_xPath);
    }


    /**
     * /**
     *
     * @param _xPath
     * @return WebElementFacade
     * @Description Wait for element to be present.
     */
    public WebElementFacade waitForElementToPresent(String _xPath) {
        waitForEverythingComplete();
        waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(_xPath)));
        return findBy(_xPath);
    }

    /**
     * /**
     *
     * @param _xPath
     * @return WebElementFacade
     * @Description Wait for element to be present.
     */
    public void waitForElementToNotPresent(String _xPath) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(_xPath))));
        System.out.println("Test");
    }

    public void waitForElementNotVisible(String _xPath) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(_xPath))));
    }

    public void waitForElementNotPresent(String _xPath) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(_xPath))));
    }

    public void waitForElementNotAppear(String _xPath) {
        try {
            waitForElementNotVisible(_xPath);
        } catch (Exception ex) {
            waitForElementNotPresent(_xPath);
        }
    }

    /**
     * @param _xPath
     * @return WebElementFacade
     * @Description Wait for element to be clickable.
     */
    public WebElementFacade waitElementToBeClickable(String _xPath) {
        XH(_xPath).waitUntilClickable();
        highlightElement(_xPath);
        return findBy(_xPath);
    }
// endregion Common xPath

// region Common Action

    /**
     * @param _xPath
     * @Description Wait, scroll into element then click.
     */
    public void clickOnElement(String _xPath) {
        XH(_xPath).waitUntilClickable().click();
    }

    /**
     * @param _xPath
     * @Description Wait, scroll into element then double click.
     */
    public void doubleClickOnElement(String _xPath) {
        withAction().doubleClick(XH(_xPath)).perform();
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait for element to be input, type given value and enter then
     * wait for element update value. NOTE: if existed value equal to
     * new value, it will keep existed value.
     */
    public void waitTypeAndEnterThenUpdateValue(String _xPath, String _value) {
        if (!XH(_xPath).getTextValue().equals(_value)) {
            XH(_xPath).waitUntilEnabled().waitUntilVisible().typeAndEnter(_value);
            waitForEverythingComplete();
            waitTextToBePresentInElementValue(_xPath, _value);
            waitForEverythingComplete();
        }
    }

    /**
     * @param _xPath
     * @param _value
     * @return WebElementFacade
     * @Description Wait for given text to be present in element value.
     */
    public WebElementFacade waitTextToBePresentInElementValue(String _xPath, String _value) {
        waitFor(ExpectedConditions.textToBePresentInElementValue(By.xpath(_xPath), _value));
        return findBy(_xPath);
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait for element to be input, type given value
     */
    public void waitTypeAndTab(String _xPath, String _value) {
        if (_value.equals("@BLANK@")) {
            _value = "";
        }
        XH(_xPath).waitUntilEnabled().typeAndTab(_value);
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait element then clear then type value into element
     */
    public void waitClearAndType(String _xPath, String _value) {
        if (_value.equals("@BLANK@")) {
            _value = " ";
        }
        XH(_xPath).click();
        XH(_xPath).clear();
        clearTextByJS(_xPath);
        XH(_xPath).type(_value);
    }

    /**
     * @param _xPath
     * @param _value
     * @return
     * @Description Wait element then clear then type value into element, then press
     * Tab
     */
    public String waitClearAndTypeThenTab(String _xPath, String _value) {
        if (_value.equals("@BLANK@")) {
            _value = " ";
        }
        XH(_xPath).clear();
        clearTextByJS(_xPath);
        XH(_xPath).sendKeys(_value + Keys.TAB);
        return _xPath;
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait element then clear then type value into element, then press
     * Tab
     */
    public void waitClearAndTypeThenEnter(String _xPath, String _value) {
        if (_value.equals("@BLANK@")) {
            _value = "";
        }
        XH(_xPath).clear();
        clearTextByJS(_xPath);
        XH(_xPath).sendKeys(_value + Keys.ENTER);
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait for element to be input, type given value then enter
     */
    public void waitTypeAndEnter(String _xPath, String _value) {
        XH(_xPath).waitUntilPresent().waitUntilVisible().waitUntilEnabled().typeAndEnter(_value);
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Click then Type into Element Char by Char
     */
    public void clickThenTypeCharByChar(String _xPath, String _value) {
        if (_value.isEmpty()) {
            _value = " ";
        }
        WebElementFacade wEF = XH(_xPath);
        wEF.click();
        wEF.clear();
        clearTextByJS(_xPath);
        for (int i = 0; i < _value.length(); i++) {
            findBy(_xPath).waitUntilPresent().sendKeys(String.valueOf(_value.charAt(i)));
            waitABit(50);
        }
    }


    /**
     * @param _xPathStatus ,_xPathCheckbox
     * @param _isCheck
     * @Description Verify checkbox then Check or Uncheck to Check Box.
     */

    public void verifyCheckedThenClick(String _xPathStatus, String _xPathCheckbox, boolean _isCheck) {
        boolean isStatus = XH(_xPathStatus).isSelected();
        if (_isCheck != isStatus) {
            clickOnElement(_xPathCheckbox);
        }
    }

    public void verifyElementSelected(String _xPathStatus, boolean _isCheck) {
        boolean isStatus = XH(_xPathStatus).isSelected();
        assertThat(isStatus).isEqualTo(_isCheck);
    }

    public void verifyElementPresent(String _xPath, boolean _isPresent) {
        waitForEverythingComplete();
        assertThat(isElementExist(_xPath)).isEqualTo(_isPresent);
    }

    public void verifyElementPresent(String _xPath, boolean _isPresent, int timeout) {
        assertThat(isElementExist(_xPath, timeout)).isEqualTo(_isPresent);
    }

    /**
     * @param xPath
     * @param _timeoutInSeconds
     * @return boolean
     * @Description Check for element visible or not with timeout.
     */
    public boolean isElementVisible(String xPath, int _timeoutInSeconds) {
        try {
            setImplicitTimeout(_timeoutInSeconds, SECONDS);
            waitElementToBeVisible("(" + xPath + ")[1]");
            resetImplicitTimeout();
            return true;
        } catch (Exception e) {
            resetImplicitTimeout();
        }
        return false;
    }

    public void verifyElementVisible(String _xPath, boolean _isVisible) {
        assertThat(isElementVisible(_xPath, 10)).isEqualTo(_isVisible);
    }

// Switch Iframe

    public void switchToIFrameByID(String _iFrameId) {
        getDriver().switchTo().frame((WebElement) getDriver().findElement(By.xpath("(//iframe[@id='" + _iFrameId + "'])[last()]")));
    }

    public void switchToIFrame(int _res) {
        getDriver().switchTo().frame(_res);
        waitABit(300);
    }

    /**
     * @param _xpath: xpath iframe
     * @Description Wait for jQuery complete
     */
    public void switchToIFrame(String _xpath) {
        getDriver().switchTo().frame((WebElement) getDriver().findElement(By.xpath(_xpath)));
        waitABit(300);
    }

    public void switchToIFrameDefault() {
        getDriver().switchTo().parentFrame();
        waitABit(300);
    }


    /**
     * @param _givenText
     * @return
     * @Description Create text predicates with text contains special characters.
     * Note: It's not include [].
     */
    public String sSpecialTextPredicates(String _givenText) {
        String[] tokens = _givenText.split("\"|\\'");
        int numText = tokens.length;
        String sSearchPattern = "";
        if (numText > 1) {
            sSearchPattern = "contains(text(),'" + tokens[0] + "')";
            for (int i = 1; i < numText; i++) {
                sSearchPattern += " and contains(text(),'" + tokens[i] + "')";
            }
            return sSearchPattern;
        } else {
            return "contains(text(),'" + _givenText + "')";
        }
    }

    public void verifyElementText(String _xpath, String text) {
        String actualText = "";
        if (isElementExist(_xpath, 5)) {
            actualText = $(_xpath).getTextValue().trim();
        } else {
            System.out.println(("**** ERROR **** Not exist element: " + _xpath));
        }
        assertThat(actualText).isEqualTo(text);

    }

    public void verifyElementContainsText(String _xpath, String text) {
        String actualText = "";
        if (isElementExist(_xpath)) {
            actualText = $(_xpath).getTextValue();
        } else {
            System.out.println(("**** ERROR **** Not exist element: " + _xpath));
        }
        assertThat(actualText).contains(text);

    }

    /**
     * @param _xPath
     * @return
     * @Description Hover an element.
     */
    public void hoverOnElement(String _xPath) {
        withAction().moveToElement(XH(_xPath)).build().perform();
    }

    /**
     * @Description Hover, then click a element.
     */
    public void hoverThenClickElement(String elementToHover, String elementToClick) {
        withAction().moveToElement(XH(elementToHover)).perform();
        withAction().click(XH(elementToClick)).build().perform();
    }

    // region Alert message
    public String alert_message() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = getDriver().switchTo().alert();
            String message = alert.getText();
            getDriver().switchTo().defaultContent();
            return message;
        } catch (Exception e) {
            return null;
        }
    }

    public void alertAccept() {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        getDriver().switchTo().defaultContent();
    }

    public void alertDismiss() {
        Alert alert = getDriver().switchTo().alert();
        alert.dismiss();
        getDriver().switchTo().defaultContent();

    }

    public String convertToGRBColor(String colorStr) {
        int r = Integer.valueOf(colorStr.substring(1, 3), 16);
        int g = Integer.valueOf(colorStr.substring(3, 5), 16);
        int b = Integer.valueOf(colorStr.substring(5, 7), 16);
        return "(" + r + ", " + g + ", " + b + ")";
    }

    // endregion Alert message
    public int countElementByXpath(String _xpathOrSelector) {
        int n = 0;
        if (isElementExist(_xpathOrSelector)) {
            n = findAll(_xpathOrSelector).size();
        }
        return n;
    }

    //gettext
    public String getAttributeValue(String _xpath, String _attribute) {
        return XH(_xpath).getAttribute(_attribute);
    }

    public String getAttributeValueRaw(String _xpath, String _attribute) {
        waitElementToBeVisible(_xpath);
        scrollIntoElementView(_xpath);
        return getDriver().findElement(By.xpath(_xpath)).getAttribute(_attribute);
    }


    public void chooseAttachmentFile(String xpath, String fileName) {
        String _filePath = LoadObject.getFilePath(fileName);
        System.out.println("_filePath: " + _filePath);
        try {
            $(xpath).sendKeys(_filePath);
            waitABit(1000);
            if (isElementExist(xpath)) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = ''", findBy(xpath));
            }
        } catch (Exception e) {
            WebElement el = getDriver().findElement(By.xpath(xpath));
            ((RemoteWebElement) el).setFileDetector(new LocalFileDetector());
            el.sendKeys(_filePath);
            waitABit(3000);
            if (isElementExist(xpath)) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = ''", findBy(xpath));
            }
        }
    }

    public void chooseMultipleAttachmentFiles(String xpath, String files) {
        String[] file = files.split(",");
        String listFilePath = "";
        for (int i = 0; i < file.length; i++) {
            String _filePath = LoadObject.getFilePath(file[i]);

            if (i > 0) {
                listFilePath = listFilePath + "\n ";
            }

            listFilePath = listFilePath + _filePath;
        }
        try {
            $(xpath).sendKeys(listFilePath);
            waitABit(1000);
        } catch (Exception e) {
            WebElement El = getDriver().findElement(By.xpath(xpath));
            ((RemoteWebElement) El).setFileDetector(new LocalFileDetector());
            El.sendKeys(listFilePath);
            waitABit(3000);

        }
    }

    public String executeJavaScript(String javaScript) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        if (js.executeScript(javaScript) != null) {
            String result = js.executeScript(javaScript).toString();
            System.out.println(result);
            return result;
        } else return "";
    }

    public void changeStyle(String _xPath) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.display = \"block\"; arguments[0].style.visibility = \"visible\";arguments[0].type='file'", findBy(_xPath));
        } catch (Exception e) {
            System.out.println("test");
        }
    }

    public Response postAPI(String url, JsonObject requestParams) {
        Response response = given().header("Content-Type", "application/json").body(requestParams.toString()).post(url);
        assertThat(response.getStatusCode()).isEqualTo(200);
        return response;
    }

    public JsonPath getAPI(String url) {
        Response resp = given().get(url);
        assertThat(resp.getStatusCode()).isEqualTo(200);
        JsonPath jp = resp.then().extract().jsonPath();
        return jp;
    }

    public ArrayList getDataByKey(JsonPath js, String key) {
        return js.get(key);
    }


    public Object getData(JsonPath js, String key) {
        return js.get(key);
    }

    // action by JS
    public void clickOnElementByJs(String _xPath) {
        highlightElement(_xPath);
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollDown +=100;", findBy(_xPath));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", findBy(_xPath));
        } catch (Exception e) {
        }
    }

    public void clearTextByJS(String _xPath) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = ''", findBy(_xPath));
        } catch (Exception e) {
        }
    }

    public void clearValueByJS(String xPath) {
        clickOnElement(xPath);
        WebElement e = getDriver().findElement(By.xpath(xPath));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].select(); true", e);
        e.sendKeys(Keys.BACK_SPACE);
    }

    public void inputTextByJS(String _xPath, String _text) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = '" + _text + "'", findBy(_xPath));
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].textContent = arguments[1];", findBy(_xPath), "This is a test");
            System.out.println("Can't input");
        }
    }

    public void clearAndInputTextByJS(String xPath, String text) {
        clickOnElement(xPath);
        WebElement e = getDriver().findElement(By.xpath(xPath));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].select(); true", e);
        e.sendKeys(Keys.BACK_SPACE);
        e.sendKeys(text);
    }

    public void verifyExistedThenTurnOff(String _xpathCheckStatus, String _xPathObjectClick) {
        if (isElementExist(_xpathCheckStatus)) {
            clickOnElement(_xPathObjectClick);
        }
    }

    public String getText(String _xpath) {
        return XH(_xpath).getText();
    }

    public String getTextByJS(String _xpath) {
        return ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerText", findBy(_xpath)).toString();
    }

    public String getTextValue(String _xpath) {
        setImplicitTimeout(10, SECONDS);
        return XH(_xpath).getTextValue();
    }

    public String getValue(String _xpath) {
        return XH(_xpath).getValue();
    }

    public String getTextContent(String _xpath) {
        return XH(_xpath).getTextContent();
    }

    //verify text

    public boolean verifyTextPresent(String text, boolean isPresent) {
        String _xpath = "";
        if (text.contains("\"")) {
            _xpath = "//*[contains(text(),'" + text + "')]";
        } else {
            _xpath = "//*[contains(text(),\"" + text + "\")]";
        }
        verifyElementPresent(_xpath, isPresent);
        return isPresent;
    }


    public String getSelectedValue(String _xpath) {
        return XH(_xpath).getSelectedVisibleTextValue().trim().replaceAll("\n", "");
    }

    public void verifyOptionSelectedByLabel(String _labelName, String option) {
        String _xpath = "(" + "//select[preceding-sibling::*[text()[normalize-space()='" + _labelName + "']] or parent::div[preceding-sibling::*[text()[normalize-space()='" + _labelName + "']]]])[1]";
        String actual = getSelectedValue(_xpath);
        assertThat(actual).isEqualTo(option);
    }

    public void verifyColor(String xPath, String cssValue, String colorCodeCSS) {
        String colorCode = Color.fromString(XH(xPath).getCssValue(cssValue)).asHex();
        assertThat(colorCode).isEqualToIgnoringCase(colorCodeCSS);
    }

    public void blurElement(String xpath) {
        WebElement e = getDriver().findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].blur(); true", e);
    }

    public void waitForElementFinishRendering(String xPath) {
        WebElement e = getDriver().findElement(By.xpath(xPath));
        waitForElementFinishRendering(e, 30);
    }

    public void waitUntilInvisibleLoading(int timeoutInSeconds) {
        String xpath = "//*[contains(@class,'loading')][not (@style='display: none;')]|//img[@class='sbase-spinner']|//*[@class='button-dual-ring']";
        int i = 0;
        while (isElementExist(xpath, 3) && i < 14) {
            waitUntilElementInvisible(xpath, timeoutInSeconds);
            i++;
        }
        waitForElementNotAppear(xpath);
    }

    public void waitForElementFinishRendering(WebElement e, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        Point currentPoint = new Point(-1, -1);
        wait.until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver webDriver) {
                Point location = e.getLocation();
                if (location.x == currentPoint.x && location.y == currentPoint.y) {
                    return e;
                } else {
                    currentPoint.x = location.x;
                    currentPoint.y = location.y;
//                    System.out.println("still rendering");
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("Location no change \"%s\". Current location: \"%s\"", currentPoint, e.getLocation());
            }
        });
    }
// Get access token

    public void inputSlowly(String _xPath, String value) {
        String arr[];
        arr = value.split("");
        waitUntilElementVisible(_xPath, 10);
        XH(_xPath).clear();
        clearTextByJS(_xPath);
        for (int i = 0; i < arr.length; i++) {
            waitABit(5);
            XH(_xPath).sendKeys(arr[i]);
            waitABit(5);
        }
    }

    public List<String> getListText(String xpath) {
        waitElementToBePresent(xpath);
        scrollIntoElementView(xpath);
        waitABit(1000);
        List<String> a =
                findAll(By.xpath(xpath)).stream()
                        .map(element ->
                                scrollIntoElementView(element).getText())
                        .collect(Collectors.toList());
        return a;
    }

    public List<String> getListTextValue(String xpath) {
        waitElementToBePresent(xpath);
        scrollIntoElementView(xpath);
        waitABit(1000);
        List<String> a =
                findAll(By.xpath(xpath)).stream()
                        .map(element ->
                                scrollIntoElementView(element).getTextValue())
                        .collect(Collectors.toList());
        return a;
    }

    public void verifyCssValueByElement(String xpath, String attribute, String expectedCssValue) {
        String expectedValue = changeCssValue(expectedCssValue);
        String actualValue = XH(xpath).getCssValue(attribute);
        if (expectedCssValue.contains("color") || expectedCssValue.contains("#")) {
            verifyColor(xpath, attribute, expectedCssValue);
        } else {
            if (expectedValue.contains("&")) {
                assertThat(actualValue).isIn(Arrays.asList(expectedValue.split("&")));
            } else {
                assertThat(actualValue).contains(expectedValue);
            }
        }

    }

    public String convertColorHexToRGB(String colorStr) {
        int r = Integer.valueOf(colorStr.substring(1, 3), 16);
        int g = Integer.valueOf(colorStr.substring(3, 5), 16);
        int b = Integer.valueOf(colorStr.substring(5, 7), 16);
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }

    public String changeCssValue(String cssValue) {
        String css = "";
        if (cssValue.contains("rem")) {
            cssValue = convertRemToPixel(Float.parseFloat(cssValue.replace("rem", ""))) + "";
        }

        switch (cssValue) {
            case "bold":
                css = "700";
                break;
            case "semibold":
                css = "600";
                break;
            case "medium":
                css = "500";
                break;
            default:
                css = cssValue;

        }

        return css;
    }

    private float convertRemToPixel(float cssValue) {
        return Math.round((cssValue * 14) * 1000.0f) / 1000.0f;
    }


    public String getSelectedValueDdl(String xpath) {
        waitElementToBeVisible(xpath);
        Select select = new Select(getDriver().findElement(By.xpath(xpath)));
        WebElement option = select.getFirstSelectedOption();
        return option.getText().trim();
    }

    /**
     * @param _xPath
     * @param _value
     * @Description Wait element then clear then type value into element
     */
    public void clickAndClearThenType(String _xPath, String _value) {
        if (_value.equals("@BLANK@")) {
            _value = " ";
        }
        if (!waitElementToBePresentThenScrollIntoView(_xPath).getTextValue().equals(_value)) {
            XH(_xPath).click();
            clearTextByJS(_xPath);
            waitABit(200);
            XH(_xPath).clear();
            XH(_xPath).sendKeys(_value);
            waitForEverythingComplete();
        }
    }

    /**
     * @param actualPrice
     * @param expectedPrice
     * @Description compare 2 price (String)
     */
    public void comparePrice(String actualPrice, String expectedPrice) {
        float actPrice = Float.parseFloat(price(actualPrice));
        float expPrice = Float.parseFloat(price(expectedPrice));
        assertThat(actPrice).isEqualTo(expPrice);
    }

    public String price(String price) {
        return price.replace("-", "").replace("₫", "").replace("$", "").replace(",", "").replace("£", "").replace("USD", "").replace(":", "").replace("Cost", "").replace("+", "").replace("Free", "0").replace("Payment fee", "").replace("(", "").replace(")", "").replace("Proces" +
                "" +
                "sing Fee", "").replace("%", "").replace("order value", "").replace("profit", "").replace("Balance", "").replace("Save", "").replace("available", "").trim();
    }

    public float getPrice(String xpath) {
        return Float.parseFloat(price(getText(xpath)).toString());
    }

    public float removeCurrency(String price) {
        return Float.parseFloat(price(price));
    }

    public void clickOnElementByID(String _ID) {
        clickOnElement(iD(_ID));
    }

    public void selectRadioButtonPhub(String xpath, boolean isSelect) {
        if (isElementExist(xpath)) {
            boolean isSelected = false;
            if (getAttributeValue(xpath, "class").contains("active")) {
                isSelected = true;
            }
            if (isSelect != isSelected) {
                clickOnElement(xpath);
            }
        }
    }

    /**
     * @param _xPath
     * @return WebElementFacade
     * @Description Wait for element to be visible.
     */
    public WebElementFacade waitElementToBeVisible(String _xPath, int timeout) {
//        waitForEverythingComplete();
        withTimeoutOf(timeout, SECONDS)
                .waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(_xPath)));

        return findBy(_xPath);
    }

    public WebElementFacade waitElementToBeVisible(String _xPath) {
        return waitElementToBeVisible(_xPath, 20);
    }


    public void verifyMaxLength(String xpath, String length) {
        waitElementToBeVisible(xpath);
        int randomMaxLength = Integer.parseInt(length) + 1;
        String randomString = randomNumeric(randomMaxLength);
        waitClearAndType(xpath, randomString);
        int valueLength = XH(xpath).getValue().length();
        assertThat(valueLength).isEqualTo(Integer.parseInt(length));
    }

    public void clickBreadcrumb() {
        clickOnElement("//ol[@class='s-breadcrumb']//a");
        waitForPageLoad();
    }

    public void navigateToPreviousPage() {
        getDriver().navigate().back();
    }

    public Response putAPI(String url, JsonObject requestParams) {
        Response response = given().header("Content-Type", "application/json").body(requestParams.toString()).put(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        return response;
    }

    public List<String> getListTextByAttribute(String xpath, String attribute) {
        return findAll(By.xpath(xpath)).stream()
                .map(element -> element.getAttribute(attribute))
                .collect(Collectors.toList());
    }

    public Response deleteAPI(String url) {
        Response response = given().delete(url);
        assertThat(response.getStatusCode()).isEqualTo(200);
        return response;
    }

    public String getPathFileDownloaded() {
        String path = "";
        try {
            waitABit(4000);
            openUrlInNewTab("");
            switchToTheLastestTab();
            navigateToUrl("chrome://downloads/");
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            String result = (String) js.executeScript("return decodeURIComponent(document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList').getElementsByTagName('downloads-item')[0].shadowRoot.querySelector('#file-icon').src)");
            path = result.split("=", 2)[1].split("&", 2)[0];
            if (path.contains("+")) {
                path = path.replace("+", " ");
            }
            waitABit(1000);
            js.executeScript("window.close()");
        } catch (Exception e) {
            System.out.println("Can not get file path");
            e.printStackTrace();
        }
        return path;

//        if ("firefox".equals(browserName)) {
//            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("browser.download.dir", "D:\\WebDriverDownloads");
//            profile.setPreference("browser.download.folderList", 2);
//        }
    }

    public static float roundTwoDecimalPlaces(float number) {
        BigDecimal bd = new BigDecimal(number).setScale(2, RoundingMode.HALF_UP);
        float outPut = bd.floatValue();
        return outPut;
    }

    public void focusClickOnElement(String _xPath) {
        WebElement element = getDriver().findElement(By.xpath(_xPath));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click().build().perform();
    }

    public void focusAndClickElement(WebElement webElement) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(webElement).click().build().perform();
    }

    public void clickOnCancelButtonOfTheAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    public void clickOnAcceptButtonOfTheAlert() {
        getDriver().switchTo().alert().accept();
    }

    public int getIndexOfColumn(String columnName) {
        String xpath = "//th[descendant-or-self::*[text()[normalize-space()='" + columnName + "']]]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public int getIndexOfRow(String rowName) {
        String xpath = "//span[@class='unite-ui-dashboard__aside--text' and normalize-space(text())='" + rowName + "']";
        return countElementByXpath(xpath) + 1;
    }

    public String downLoadImageFromUrl(String url, String filename) throws IOException {

        StringBuilder result = new StringBuilder();

        // add user agent
        URL urlImage = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlImage.openConnection();
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedImage bodyAnimalImage = ImageIO.read(httpURLConnection.getInputStream());
        File outputfile = new File(getFilePath(File.separator + filename + ".png"));
        ImageIO.write(bodyAnimalImage, "png", outputfile);
        return result.toString();
    }

    public void dragAndDrop(String xPathSource, String xPathDestination, int scrollPixel) throws AWTException {
        waitForElementFinishRendering(xPathSource);
        waitForElementFinishRendering(xPathDestination);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        waitABit(500);
        js.executeScript("window.scrollBy(0," + scrollPixel + ")", "");

        int screenHeight = getDriver().manage().window().getSize().getHeight();
        String xPathHTML = "//html";
        int htmlHeight = getDriver().findElement(By.xpath(xPathHTML)).getSize().getHeight();
        int chromeAddBarHeight = screenHeight - htmlHeight;

        WebElement elementSource = getDriver().findElement(By.xpath(xPathSource));
        WebElement elementDestination = getDriver().findElement(By.xpath(xPathDestination));

        Point coordinatesSource = elementSource.getLocation();
        Point coordinatesDestination = elementDestination.getLocation();
        Robot robot = new Robot();
        robot.mouseMove(coordinatesSource.getX(), coordinatesSource.getY() + chromeAddBarHeight - scrollPixel);
        waitABit(1000);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        waitABit(1000);
        robot.mouseMove(coordinatesDestination.getX(), coordinatesDestination.getY() + chromeAddBarHeight - scrollPixel);
        waitABit(1000);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        waitABit(2000);
    }

    public void verifySortedAscending(String locator) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elementList = getDriver().findElements(By.xpath(locator));
        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }
        Collections.sort(arrayList);
        assertThat(sortedList).isEqualTo(arrayList);
    }

    public void verifySortedDescending(String locator) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elementList = getDriver().findElements(By.xpath(locator));
        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        assertThat(sortedList).isEqualTo(arrayList);
    }

    public static void verifyImageFromDasboardOrSF(String linkURLImage, String image, float per) throws IOException {
        List<String> result = new ArrayList<>();
        Response resp = given().get(linkURLImage);
        byte[] imageBytes = resp.asByteArray();
        InputStream is = new ByteArrayInputStream(imageBytes);
        BufferedImage actualImage = ImageIO.read(is);
        if (!image.isEmpty()) {
            String img[] = image.split(";");
            for (int i = 0; i < img.length; i++) {
                System.out.println("File path: " + getFilePath("phub/mockupcamp" + img[i]));
                BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(getFilePath("phub/mockupcamp" + img[i]));
                ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();
                float differencePercent = imageComparisonResult.getDifferencePercent();
                System.out.println("differencePercent : " + differencePercent);
                if (per > differencePercent)
                    result.add("Passed");
                else result.add("Failed");
            }
            Assertions.assertThat(result).contains("Passed");
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void verifyCheckedThenClickByJS(String _xPathStatus, String _xPathCheckbox, boolean _isCheck) {
        boolean isStatus = XH(_xPathStatus).isSelected();
        if (_isCheck != isStatus) {
            clickOnElementByJs(_xPathCheckbox);
        }
    }
}

