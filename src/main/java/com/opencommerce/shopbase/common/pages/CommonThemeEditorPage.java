package com.opencommerce.shopbase.common.pages;


import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.google.gson.JsonObject;
import common.CommonPageObject;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.pages.WebElementFacade;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static common.utilities.LoadObject.getFilePath;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.assertFalse;


public class CommonThemeEditorPage extends SBasePageObject {

    public CommonThemeEditorPage(WebDriver driver) {
        super(driver);
    }

    public String xpathElementInsideBlock = "//div[contains(@class,'sb-mb-medium') and descendant::label[normalize-space()='%s']]";
    public String xpathSection = "//div[@class='' and descendant::h6[normalize-space()='%s']]";
    public String xpathBlockInsideSection = "(" + xpathSection + "//span[normalize-space()='%s'])[%d]";

    public void openPreviewPage(String page) {
        waitElementToBeVisible("//div[contains(@class,'navigation-pages')]//button");
        clickOnElement("//div[contains(@class,'navigation-pages')]//button");
        clickOnElement("//div[contains(@class, 'sb-popover__popper') and not(contains(@style,'display: none;'))]//li[normalize-space()='" + page + "']");
    }

    public void openSectionSettingWithName(String section, int index) {
        String xpath = "(//h6[normalize-space()='" + section + "'])[" + index + "]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void openBlockWithName(String section, String block, int indexBlock) {
        String xpath = String.format(xpathBlockInsideSection, section, block, indexBlock);
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void scrollSliderBarByPercent(String label, String percent) {
        String xpathButtonDrag = String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-slider__button')]";
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement buttonDrag = getDriver().findElement(By.xpath(xpathButtonDrag));
        js.executeScript("arguments[0].setAttribute('style', 'left: " + percent + "')", buttonDrag);
        clickOn(buttonDrag);
    }

    public void selectValueInSliderBarBySteps(String label, int numberStep) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-slider__button')]";
        WebElement slider = getDriver().findElement(By.xpath(xpath));
        Actions builder = new Actions(getDriver());
        int iCount = 0;
        if (numberStep > 0) {
            for (iCount = 0; iCount < numberStep; iCount++) {
                builder.moveToElement(slider).click(slider).sendKeys(Keys.ARROW_UP).perform();
            }
        } else {
            for (iCount = 0; iCount > numberStep; iCount--) {
                builder.click(slider).sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
    }

    public void uploadImageWithFile(String label, String path) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//input[@type='file']";
        chooseAttachmentFile(xpath, path);
        waitABit(1000);
    }
    public void uploadImageWithFile(String label, String path,int index) {
        String xpath = "(" + String.format(xpathElementInsideBlock, label) + "//input[@type='file'])["+index+"]";
        chooseAttachmentFile(xpath, path);
    }
    public void removeImageUploaded(String label) {
        hoverOnElement(String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-upload')]");
        clickOnElement(String.format(xpathElementInsideBlock, label) + "//button[descendant::*[local-name()='g' and @id='Icons/Trash']]");
    }

    public boolean isExistValueInAutocomplete(String label) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-tag__icon')]";
        return isElementExist(xpath);
    }

    public void removeLValueFromAutocomplete(String label) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-tag__icon')]";
        scrollIntoElementView(xpath);
        clickOnElement(xpath);
    }

    public void addPathToAutocomplete(String label, String value) {
        waitClearAndType(String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-input sb-relative') and not(contains(@style,'display: none;'))]//input", value);
        waitElementToBeVisible("//div[contains(@class,'sb-autocomplete__addable-row')]//span");
        clickOnElement("//div[contains(@class,'sb-autocomplete__addable-row')]//span");
    }

    public void selectValueInAutocomplete(String label, String value) {
        String xpathInput = String.format(xpathElementInsideBlock, label) + "//div[contains(@class,'sb-input sb-relative') and not(contains(@style,'display: none;'))]//input";
        String xpathItem = "//div[contains(@class, 'sb-popover__popper') and not(contains(@style,'display: none;'))]//div[contains(@class,'sb-text-body') and normalize-space()='" + value + "']";
        XH(xpathInput).click();
        try {
            waitElementToBeVisible(xpathItem);
            clickOnElement(xpathItem);
        } catch (Exception e) {
            clearTextByJS(xpathInput);
            XH(xpathInput).type(value);
            waitElementToBeVisible(xpathItem);
            clickOnElement(xpathItem);
        }
    }

    public void selectValueInDropDown(String label, String value) {
        waitElementToBeVisible(String.format(xpathElementInsideBlock, label) + "//button");
        clickOnElement(String.format(xpathElementInsideBlock, label) + "//button");
        clickOnElement("//div[contains(@class, 'sb-popover__popper') and not(contains(@style,'display: none;'))]//li[normalize-space()='" + value + "']");
    }

    public void inputTextWithIframe(String label, String text) {
        switchToIFrame(String.format(xpathElementInsideBlock, label) + "//iframe");
        waitClearAndType("//body[@id='tinymce']", text);
        switchToIFrameDefault();
    }
    public void inputTextWithIframe(String label, String text, int index) {
        switchToIFrame("(" + String.format(xpathElementInsideBlock, label) + "//iframe)["+index+"]");
        waitClearAndType("//body[@id='tinymce']", text);
        switchToIFrameDefault();
    }

    public void clickOnLabel(String label) {
        clickOnElement("//label[normalize-space()='" + label + "']");
    }

    public void clickButtonGroup(String label, String button) {
        clickOnElement(String.format(xpathElementInsideBlock, label) + "//button[normalize-space()='" + button + "']");
    }
    public void verifyBtnGroup(String label, String button, boolean status) {
        verifyElementPresent(String.format(xpathElementInsideBlock, label) + "//button[normalize-space()='" + button + "']",status);
    }

    public void settingColor(String label, String color) {
        waitClearAndType(String.format(xpathElementInsideBlock, label) + "//input", color);
        clickOnElement("//label[normalize-space()='" + label + "']");
    }

    public void openAdvancedSetting() {
        clickOnElement("//span[descendant::*[local-name()='g' and @id='Icons/Arrow/Chevron/Right']]");
    }

    public void clickOnButton(String section, String label) {
        String xpath;
        if (section.isEmpty()) {
            xpath = "//button[descendant::span[normalize-space()='" + label + "']]";
        } else {
            xpath = String.format(xpathSection, section) + "//button[descendant::span[normalize-space()='" + label + "']]";
        }
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
    public boolean isCheckButtonDisable(String label) {
        return isElementExist("//button[descendant::span[normalize-space()='"+ label +"'] and @disabled ='disabled']");
    }

    public void addSectionWithName(String section) {
        String xpath = "//span[normalize-space()='" + section + "']//parent::div[contains(@class,'theme-editor-v2__new-element')]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void openTab(String tab) {
        clickOnElement("//div[contains(@class,'sb-tab-navigation__item') and normalize-space()='" + tab + "']");
    }

    public void showOrHideBlock(String section, int indexSection, String block, int indexBlock, boolean isShow) {
        String xpathBlock = String.format(xpathBlockInsideSection, section, indexSection, block, indexBlock);
        String xpathIconEye = xpathBlock + "//button[descendant::*[local-name()='g' and @id='Icons/Eye']]";
        boolean status = true;

        hoverOnElement(xpathBlock);
        if (isElementExist(xpathBlock + "//button[descendant::*[local-name()='g' and @id='Icons/Eye-Cross']]")) {
            status = false;
            xpathIconEye = xpathBlock + "//button[descendant::*[local-name()='g' and @id='Icons/Eye-Cross']]";
        }

        if(status != isShow){
            clickOnElement(xpathIconEye);
        }
    }

    public void inputTextBoxWithLabel(String label, String value) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//input";
        XH(xpath).waitUntilClickable().click();
        XH(xpath).clear();
        XH(xpath).type(value);
    }

    public void inputTextBoxWithLabelThenTab(String label, String value) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//input";
        XH(xpath).waitUntilClickable().click();
        XH(xpath).clear();
        XH(xpath).sendKeys(value + Keys.TAB);
    }

    public void setCheckboxWithLabel(String label, boolean isCheck) {
        String xpathSpan = "//div[contains(@class,'checkbox') and descendant::span[normalize-space()='" + label + "']]//span";
        String xpathInput = "//div[contains(@class,'checkbox') and descendant::span[normalize-space()='" + label + "']]//input";

        boolean isStatus = XH(xpathInput).isSelected();
        if (isCheck != isStatus) {
            clickOnElement(xpathSpan);
        }
    }

    public void showOrHideSection(String section, int index, boolean isShow) {
        String xpathSectionSetting = "(//div[contains(@class,'section sb-pointer') and descendant::h6[normalize-space()='"+section+"']])["+index+"]";
        String xpathIconEye = xpathSectionSetting + "//button[descendant::*[local-name()='g' and @id='Icons/Eye']]";
        boolean status = true;

        hoverOnElement(xpathSectionSetting);
        if (isElementExist(xpathSectionSetting + "//button[descendant::*[local-name()='g' and @id='Icons/Eye-Cross']]")) {
            status = false;
            xpathIconEye = xpathSectionSetting + "//button[descendant::*[local-name()='g' and @id='Icons/Eye-Cross']]";
        }

        if(status != isShow){
            clickOnElement(xpathIconEye);
        }
    }

    public void clickCustomizeCurrentTheme() {
        waitElementToBeVisible("//div[@class='published-theme__actions']//button[normalize-space()='Customize']");
        clickOnElement("//div[@class='published-theme__actions']//button[normalize-space()='Customize']");
        waitElementToBeVisible("//div[@class='theme-editor-v2__sidebar']");
    }

    public int countElement(String _xpathOrSelector) {
        int n = 0;
        if (isElementExist(_xpathOrSelector)) {
            n = findAll(_xpathOrSelector).size();
        }
        return n;
    }

    public void inputTextBoxWithLabel(String label, String value, int index) {
        String xpath = "(" + String.format(xpathElementInsideBlock, label) + "//input)["+ index + "]";
        clearTextByJS(xpath);
        XH(xpath).type(value);
    }

    public boolean isExistValueInAutocomplete(String label, int index) {
        String xpath = "(" + String.format(xpathElementInsideBlock, label) + ")["+ index +"]//div[contains(@class,'sb-tag__icon')]";
        return isElementExist(xpath);
    }

    public void removeLValueFromAutocomplete(String label, int index) {
        String xpath = "(" + String.format(xpathElementInsideBlock, label) + ")["+ index +"]//div[contains(@class,'sb-tag__icon')]";
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public void clickOnLabel(String label, int index) {
        clickOnElement("(//label[normalize-space()='" + label + "'])["+ index + "]");
    }

    public void addPathToAutocomplete(String label, String value, int index) {
        waitClearAndType("(" + String.format(xpathElementInsideBlock, label) + ")["+ index +"]//div[contains(@class,'sb-input sb-relative') and not(contains(@style,'display: none;'))]//input", value);
        waitElementToBeVisible("//div[contains(@class,'sb-autocomplete__addable-row')]//span");
        clickOnElement("//div[contains(@class,'sb-autocomplete__addable-row')]//span");
    }

    public void selectValueInAutocomplete(String label, String value, int index) {
        String xpathInput = "(" + String.format(xpathElementInsideBlock, label) + ")["+ index +"]//div[contains(@class,'sb-input sb-relative') and not(contains(@style,'display: none;'))]//input";
        String xpathItem = "//div[contains(@class, 'sb-popover__popper') and not(contains(@style,'display: none;'))]//div[contains(@class,'sb-text-body') and normalize-space()='" + value + "']";
        XH(xpathInput).click();
        try {
            waitElementToBeVisible(xpathItem);
            clickOnElement(xpathItem);
        } catch (Exception e) {
            clearTextByJS(xpathInput);
            XH(xpathInput).type(value);
            waitElementToBeVisible(xpathItem);
            clickOnElement(xpathItem);
        }
    }

    public boolean isElementExistNow(String xpath) {
        try {
            waitElementToBePresent(xpath);
            List<WebElementFacade> subDataCellResults = withTimeoutOf(500, TimeUnit.MILLISECONDS).findAll(xpath);
            if (subDataCellResults.size() != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void setCheckboxWithXpath (String xpath, boolean isCheck) {
        boolean isStatus = XH(xpath).isSelected();
        if (isCheck != isStatus) {
            clickOnElement(xpath);
        }
    }

    public void inputTextAreaWithLabel(String label, String value) {
        String xpath = String.format(xpathElementInsideBlock, label) + "//textarea";
        clearTextByJS(xpath);
        XH(xpath).type(value);
    }

    public void clickOnButtonAddBlock (String section, int index) {
        String xpath = "(" + String.format(xpathSection, section) + ")[" + index + "]//span[text() = 'Add block']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
    public void setCheckboxWithLabel(String label, boolean isCheck, int _resOrder) {
        String xpath = "(//div[contains(@class,'input-checkbox') and descendant::span[normalize-space()='" + label + "']]//input)["+_resOrder+"]";
        boolean isStatus = XH(xpath).isSelected();
        if (isCheck != isStatus) {
            clickOnElementByJs(xpath);
        }
    }

    public void clickEmptyArea(){
        String xpath = "//div[contains(@class,'navigation-pages')]//button";
        clickOnElement(xpath);
    }

}
