package com.opencommerce.shopbase.hive_pbase.page;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class HivePbaseCampaignPage extends SBasePageObject {
    public HivePbaseCampaignPage(WebDriver driver) {
        super(driver);
    }

    String hiveUrl = LoadObject.getProperty("hive");

    public void redirectToCampaignHivePbase(Integer idCampaign) {
        String campaignHivePbaseUrl = hiveUrl + "/app/pbasecampaign/" + idCampaign + "/show";
        openUrl(campaignHivePbaseUrl);
    }

    public void clickActionReviewCustomArt(String sAction) {
        String xpath = "//button[@class='btn btn-primary' and contains(.,'" + sAction + "')]";
        waitUntilElementVisible(xpath, 30);
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void inputRejectReason(String rejectReason) {
        String xpath = "//div//textarea[@name='note']";
        waitTypeAndEnterThenUpdateValue(xpath, rejectReason);
    }

    public void clickBtnReject() {
        String xpath = "//button[@class='btn btn-danger' and contains(.,'Reject')]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void verifyDesignStatus(String status) {
        String xpath = "//label[contains(., 'Design Status: " + status + "')]";
        assertThat(isElementExist(xpath)).isTrue();
    }

    public void verifyRejectReason(String reason) {
        String xpath = "//label[contains(., 'Reject reason: " + reason + "')]";
        assertThat(isElementExist(xpath)).isTrue();
    }

    public void verifyCampaignNam(String campaignName) {
        verifyElementText("(//tbody//tr[1]//td[4])[1]", campaignName);
    }

    public void verifyShopDomain(String shop) {
        verifyElementText("(//tbody//tr[1]//td[5])[1]", shop);
    }

    public void verifyUserEmail(String userEmail) {
        verifyElementText("(//tbody//tr[1]//td[6])[1]", userEmail);
    }

    public void verifyStatus(String campaignName, String status) {
        String xpath = "//tr[1]/td[contains(text(),'" + campaignName + "')]/following-sibling::td//span[contains(text(),'" + status + "')]";
        assertThat(isElementExist(xpath)).isEqualTo(true);
    }


    public void clickBtnFilters() {
        String xpath = "//a[@class='dropdown-toggle sonata-ba-action']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnFilter() {
        String xpath = "//button[@class='btn btn-primary']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }


    public void inputCampaignName(String campaignName) {
        String xpath = "//form//div//input[@id='filter_campaignTitle_value']";
        waitUntilElementVisible(xpath, 50);
        waitTypeAndEnterThenUpdateValue(xpath, campaignName);
    }

    public void chooseCampaignOnList() {
        String xpathCheck = "//tbody//tr[1]//ins[contains(@class,'iCheck')]";
        clickOnElementByJs(xpathCheck);

    }



    public void clickBtnOke() {
        String xpath = "//input[@type = 'submit']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void selectFilterCampaignName() {
        String xpath = "//li//a[@filter-target='filter-se5232da6be-campaignTitle']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);

    }

    public void selectFilteShopDomain() {
        String xpath = "//li//a[@filter-target='filter-se5232da6be-shop.domain']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void inputShopDomain(String shop) {
        String xpath = "//input[@id='filter_shop__domain_value']";
        waitUntilElementVisible(xpath, 50);
        waitTypeAndEnterThenUpdateValue(xpath, shop);
    }

    public void selectFilteUserEmail() {
        String xpath = "//li//a[@filter-target='filter-se5232da6be-shop.ownerEmail']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void inputUserEmail(String userEmail) {
        String xpath = "//input[@id='filter_shop__ownerEmail_value']";
        waitUntilElementVisible(xpath, 50);
        waitTypeAndEnterThenUpdateValue(xpath, userEmail);
    }

    public void clickCustomSupport() {
        String xpath = "(//span//i[@class='fa pull-right fa-angle-left'])[2]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnReviewCustomArtCampaign() {
        String xpath = "(//section//ul//li[8]//i)[1]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void chooseActionReviewCustomArt(String chooseAction) {
        clickOnElement("//div//span[@id='select2-chosen-10']");
        String xpath = "//*[normalize-space()='" + chooseAction + "']//div[@class='select2-result-label'] ";
        clickOnElement(xpath);
    }

    public void chooseStatus(String label) {
        String xpath = "//*[normalize-space()='" + label + "']//div[@class='select2-result-label']";
        waitUntilElementVisible(xpath, 30);
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnYes() {
        String xpath = "//div//button[@class='btn btn-danger']";
        clickOnElement(xpath);

    }

    public void clickOnStatus() {
        String xpath = "//span[@id='select2-chosen-7']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);

    }

    public void clickBtnList() {
        String xpath = "//tr[8]//i[@class='fa fa-list']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void redirectToMockupCampaignHivePbase(Integer idBaseProduct) {
        String campaignHivePbaseUrl = hiveUrl + "/pbase-product/" + idBaseProduct + "/edit";
        openUrl(campaignHivePbaseUrl);
    }

    public void clickTabMockup() {
        String xpath = "//a[normalize-space()='Mockup']";
        waitUntilElementVisible(xpath,5);
        clickOnElement(xpath);
    }

    public void clickBtnAddNewMockup() {
        String xpath = "//a[@title='Add new']";
        waitUntilElementVisible(xpath,5);
        clickOnElement(xpath);
        waitABit(3000);
    }

    public void clickCheckBoxOnMockupTab(String checkboxName,String ordinalNumbers,boolean statusCheckbox) {
        String xpathVerify = "(//td[contains(@class,'sonata-ba-td-sabe1c53656_masterMockup-"+ checkboxName +"')]//input)["+ ordinalNumbers +"]";
        String xpathClick = "(//td[contains(@class,'sonata-ba-td-sabe1c53656_masterMockup-"+ checkboxName +"')]//ins[@class = 'iCheck-helper'])["+ ordinalNumbers +"]";
        verifyCheckedThenClickByJS(xpathVerify,xpathClick,statusCheckbox);
    }

    public void uploadThumbnailOnMockupTab(String ordinalNumbers,String fileName) {
        String xpath = "(//td[contains(@class,'sonata-ba-td-sabe1c53656_masterMockup-thumbnailUpload')]//input)["+ ordinalNumbers +"]";
        chooseMultipleAttachmentFiles(xpath,fileName);
        waitABit(3000);
    }

    public void clickBtnUpdateMockup() {
        String xpath = "//button[normalize-space()='Update']";
        waitUntilElementVisible(xpath,5);
        clickOnElement(xpath);
    }

}
