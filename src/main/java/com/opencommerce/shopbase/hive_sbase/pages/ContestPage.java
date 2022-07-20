package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ContestPage extends SBasePageObject {
    public ContestPage(WebDriver driver) {
        super(driver);
    }

    public void selectMetricsType(String label, String value) {
        clickOnElement("//div[@class='select2-container select_metric_types']");
        clickOnElement("//ul[@class='select2-results']//*[normalize-space()='" + value + "']");
    }

    public void enterThreshold(String threshold, int res) {
        String xpath = "(//input[@class='form-control prize_threshhold'])[" + res + "]";
        scrollIntoElementView(xpath);
        waitClearAndType(xpath, threshold);
    }

    public void enterPrize(String price, int res) {
        String xpath = "(//input[@class='form-control prize_money'])[" + res + "]";
        scrollIntoElementView(xpath);
        waitClearAndType(xpath, price);
    }

    public void clickButtonAddNewPrice() {
        clickOnElement("//button[@class='btn btn-primary add_new_prize']");
    }

    public void enterSizeButton(String size) {
        String xpath = "//input[@id='pre_contest_more_button_size']";
        scrollIntoElementView(xpath);
        waitClearAndType(xpath, size);
    }

    public void selectTemplate(String template) {
        scrollIntoElementView("//div[@id='s2id_select_ui_templates']");
        clickOnElement("//div[@id='s2id_select_ui_templates']");
        clickOnElement("//*[@class='select2-results']//*[normalize-space()='" + template + "']");

    }

    public void uploadImage(String xpath, String image) {
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(1000);
    }

    public void uploadPreBackground(String image) {
        if (!image.isEmpty()) {
            uploadImage("//input[@class='pre_contest_image_upload']", image);
        }
    }

    public void enterPreHeaderMsg(String msg) {
        enterInput("//input[@id='pre_contest_header_content']", msg);
    }

    public void enterInput(String xpath, String value) {
        scrollIntoElementView(xpath);
        waitClearAndType(xpath, value);
    }

    public void enterInputByID(String id, String value) {
        String xpath = "//input[@id='" + id + "']";
        scrollIntoElementView(xpath);
        waitClearAndType(xpath, value);
    }

    public void enterPreHeaderSize(String size) {
        enterInput("//input[@id='pre_contest_header_size']", size);
    }

    public void uploadInContestBackground(String image) {
        if (!image.isEmpty()) {
            uploadImage("//input[@class='in_contest_image_upload']", image);
        }
    }

    public void uploadInContestIconStart(String icon) {
        if (!icon.isEmpty()) {
            uploadImage("//input[@class='in_contest_icon_start']", icon);
        }
    }

    public void uploadInContestIconFinish(String icon) {
        if (!icon.isEmpty()) {
            uploadImage("//input[@class='in_contest_icon_finish']", icon);
        }
    }

    public void uploadAfterBGNoPrice(String image) {
        if (!image.isEmpty()) {
            uploadImage("//input[@class='after_contest_no_prize_image_upload']", image);
        }
    }

    public void uploadAfterBGHadPrice(String image) {
        if (!image.isEmpty()) {
            uploadImage("//input[@class='after_contest_had_prize_image_upload']", image);
        }
    }

    public void uploadAfterIcon1(String icon) {
        if (!icon.isEmpty()) {
            uploadImage("//input[@class='after_contest_icon1']", icon);
        }
    }

    public void uploadAfterIcon2(String icon) {
        if (!icon.isEmpty()) {
            uploadImage("//input[@class='after_contest_icon2']", icon);
        }
    }

    public void verifyContestInlist(String name) {
        verifyElementPresent("//tr//td[normalize-space()='" + name + "']", true);
    }
}
