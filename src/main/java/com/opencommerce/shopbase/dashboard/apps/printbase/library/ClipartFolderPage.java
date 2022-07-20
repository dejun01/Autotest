package com.opencommerce.shopbase.dashboard.apps.printbase.library;

import common.SBasePageObject;
import org.openqa.selenium.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ClipartFolderPage extends SBasePageObject {
    public ClipartFolderPage(WebDriver driver) {
        super(driver);
    }

    public void createGroup(String group) {
        String xpath = "//input[@id='clipart-list-input']";
        waitClearAndType(xpath, group);
    }

    public void clickOnGroupTxt() {
        String xpath = "//input[@id='clipart-list-input']";
        clickOnElement(xpath);
    }

    public void clickOnCreateGroupBtn() {
        String xpath = "//b[contains(text(),'Create group')]";
        clickOnElementByJs(xpath);
    }

    public void inputNameFolder(String folder) {
        String xpath = "//div[@class='s-form-item text-right']//input";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenTab(xpath, folder);
    }

    public void chooseGroup(String group) {
        String selectGroupXpath = "//div[@class='s-dropdown-content']//span[contains(text(),'" + group + "')]";
        waitUntilElementVisible(selectGroupXpath, 10);
        scrollToElement(selectGroupXpath);
        clickOnElementByJs(selectGroupXpath);
    }

    public void inputGroup(String group) {
        String xpath = "//span[text()='No results']";
        clickOnGroupTxt();
        createGroup(group);
        if (countElementByXpath(xpath) == 1) {
            clickOnCreateGroupBtn();
        } else chooseGroup(group);
    }

    public void uploadFileClipArt(String images) {
        String xpath = "//h4[contains(text(),'Cliparts')]//parent::div//input[@id='upload']";
        String xpathAddMore = "//h4[contains(text(),'Cliparts')]//parent::div//input[@id='add-artwork']";
        if (isElementExist(xpathAddMore)) {
            chooseAttachmentFile(xpathAddMore, images);
        } else {
            chooseAttachmentFile(xpath, images);
        }
        waitForEverythingComplete();
    }

    public void clickOnSaveChangeBtn() {
        String xpath = "//span[contains(text(),'Save changes')]";
        int i = 0;
        while (!waitUntilElementInvisible(xpath, 10)) {
            clickOnElementByJs(xpath);
            i++;
            if (i > 3)
                break;
        }
    }

    public void nameFolderVeri(String sNameFolder) {
        String xpath = "//div[@class='s-form-item__content']/div[@class='s-input']/input";
        waitUntilElementVisible(xpath, 10);
        String curentNameFolder = getTextValue(xpath);

        assertThat(curentNameFolder).isEqualTo(sNameFolder);
    }

    public void nameGroupVeri(String sGroupFolder) {
        String xpath = "//input[@id='clipart-list-input']";
        waitUntilElementVisible(xpath, 10);
        assertThat(getValue(xpath)).isEqualTo(sGroupFolder);
    }

    public void nameImageVeri(String sNameImage) {
        String[] arr = sNameImage.split(",");
        for (int i = 0; i < arr.length; i++) {
            String xpath = "//input[@id='id-name-" + i + "']";

            String curentNameImage = getValue(xpath);
            String nameImageInput = arr[i];
            assertThat(curentNameImage).isEqualTo(nameImageInput);
        }
    }

    public void deleteNameImage() {
        String xpath = "//input[@id='id-name-0']";
        waitClearAndType(xpath, "");
    }

    public void clickAnyCheckBox(int index) {
        String xpath = "//tbody//tr[" + index + "]//label//span[@class='s-check']";
        waitUntilElementVisible(xpath, 50);
        clickOnElementByJs(xpath);
    }

    public void clickOnAssignGroupOption() {
        String xpath = "//span[contains(text(),'Assign group')]";
        clickOnElement(xpath);
    }

    public void verifyAfterAssignGroup(int index, String _sGroup) {
        String groupFolderXpath = "//tbody//tr[" + index + "]//td[@class='text-right cursor-default s-pr16']";
        String currentGroup = getText(groupFolderXpath);

        assertThat(currentGroup).isEqualTo(_sGroup);
    }

    public void clickOnAllCheckBox() {
        String xpath = "//th[child::div[normalize-space()='TITLE']]//preceding-sibling::th//span[@class='s-check']";
        clickOnElementByJs(xpath);
    }

    public void verifyGroupAfterAssignAllFolder(String sGroup) {
        int listClipart = getSizeOfListImageInClipArt();
        if (listClipart > 0) {
            for (int i = 1; i < listClipart; i++) {
                String groupFolderXpath = "//tbody//tr[" + i + "]//td[@class='text-right cursor-default s-pr16']";
                String currentGroup = getText(groupFolderXpath);
                assertThat(currentGroup).isEqualTo(sGroup);
            }
        }
    }

    public void clickOnActionBtn() {
        String xpath = "//span[contains(text(), 'Action')]";
        clickOnElementByJs(xpath);
    }

    public void clickOnDeleteOption() {
        String xpath = "//span[contains(text(),'Delete Clipart folders')]";
        clickOnElementByJs(xpath);
    }

    public void clickOnDeleteBtn() {
        String xpath = "//button[contains(text(),'Delete')]";
        waitUntilElementVisible(xpath, 2);
        clickOnElement(xpath);
    }

    public void verifyAfterDeleteFolder(int webEleList) {
        int listClipart = getSizeOfListImageInClipArt();
        assertThat(listClipart).isEqualTo(webEleList - 1);
    }

    public void verifyAfterDeleteAllFolder() {
        int xpath = getSizeOfListImageInClipArt();
        assertThat(xpath).isEqualTo(0);
    }

    public void verifyMessageErrNameFolder(String sNameFolder) {
        String xpath = "//div[@class='s-form-item__error pos-abt']";
        String currentMessageErr = getText(xpath);
        assertThat(currentMessageErr).isEqualTo(sNameFolder);
    }

    public void veryfyMessageErrImportClipart(String sClipart) {
        String xpath = "//div[@class='s-form-item__error']";
        String currentMessageErr = getText(xpath);
        assertThat(currentMessageErr).isEqualTo(sClipart);
    }

    public void veryfyMessageErrImportImage(String sImage) {
        String xpath = "//div[@class='s-form-item__error show']";
        String curentNameImage = getText(xpath);
        assertThat(curentNameImage).isEqualTo(sImage);
    }

    public void clickOnDeleteIcon() {
        String listElemTableImage = "//table[@class='custom-table__width m-t relative']//tbody//tr";
        int sizeClipart = countElementByXpath(listElemTableImage);
        for (int i = 0; i < sizeClipart; i++) {
            String deleteIconXpath = "//td[descendant::input[@id='id-name-0']]//following-sibling::td//a[@class='text-gray200 pull-right']//span";
            clickOnElementByJs(deleteIconXpath);
        }
    }

    public String getCurrenNameGroupbeforeAssignGroup(int index) {
        String xpath = "//tbody//tr[" + index + "]//td[@class='text-right cursor-default s-pr16']";
        return getText(xpath);
    }

    public void clickCancelBtn() {
        String cancelBtnXpath = "//span[contains(text(),'Cancel')]";
        clickOnElement(cancelBtnXpath);
    }

    public void verifyGroupAfterClickCancelBtn(int int_random, String sGroup) {
        String _nameGroupAc = getCurrenNameGroupbeforeAssignGroup(int_random);
        assertThat(_nameGroupAc).isNotEqualTo(sGroup);
    }

    public void clickCancelDeleteFolderBtn() {
        String cancelBtn = "//button[contains(text(),'Cancel')]";
        clickOnElement(cancelBtn);
    }

    public void chooseAClipartFolder(int index) {
        String xpath = "//tbody//tr[" + index + "]//td[@class='cursor-default no-padding-important']";
        clickOnElement(xpath);
    }

    public void deleteNameFolder() {
        String xpath = "//div[@class='s-input']//input";
        waitClearAndType(xpath, "");
    }

    public void clickOnAssignBtn() {
        String xpath = "//button[@type='button']//span[contains(text(),'Assign')]";
        clickOnElement(xpath);
    }

    public void verifyAfterClickCancelDeleteFolderBtn(int webEleList) {
        int listClipart = getSizeOfListImageInClipArt();
        assertThat(listClipart).isEqualTo(webEleList);
    }

    public int getSizeOfListImageInClipArt() {
        String xpath = "//table[@id='all-products']//tbody//tr";
        return countElementByXpath(xpath);
    }

    public String getNameImage(int index) {
        String xpath = "//input[@id='id-name-" + index + "']";
        return getValue(xpath);
    }

    public int getSizeImage() {
        String xpath = "//td[@class='s-pr64 s-py16']//preceding-sibling::td//div[@class='campaign-thumb-box']";
        return countElementByXpath(xpath);
    }

    public void changeNameData(int index, String _sImage) {
        String xpath = "//input[@id='id-name-" + index + "']";
        waitClearAndType(xpath, _sImage);
    }

    public void importThumbnail(int index, String _sThumbnail) {
        String xpath = "//td[descendant::input[@id='id-name-" + index + "']]//following-sibling::td//input";
        waitUntilElementVisible(xpath, 10);
        chooseAttachmentFile(xpath, _sThumbnail);
    }

    public void deleteImageThumbnail(int index) {
        String xpath = "//td[descendant::input[@id='id-name-" + index + "']]//following-sibling::td//div[@class='flex-basis-10perc']//i[@class='mdi mdi-trash-can-outline mdi-18px']";
        waitUntilElementVisible(xpath, 10);
        clickOnElementByJs(xpath);
    }

    public void verifyThumbnailNotVisible(int index) {
        String xpath = "//td[descendant::input[@id='id-name-" + index + "']]//following-sibling::td//input";
        waitUntilElementVisible(xpath, 10);
        assertThat(getDriver().findElements(By.xpath(xpath)).size()).isNotEqualTo(0);
    }

    public void deleteImage(int index) {
        String xpath = "//td[descendant::input[@id='id-name-" + index + "']]//following-sibling::td//a[@class='text-gray200 pull-right']//span";
        clickOnElementByJs(xpath);
    }

    public void verifyAfterDeleteImage(String _sImage, int i) {
        assertThat(_sImage).isNotEqualTo(getNameImage(i));
    }

    public void openClipart(String sFolder) {
        String xpath = "//span[contains(text(),'" + sFolder + "')]";
        clickOnElementByJs(xpath);
        waitABit(2000);
    }

    public boolean isExistActionTable() {
        String xpathActionTable = "//*[@class='disabled-section']//div[contains(@class,'action-table')]";
        return isElementExist(xpathActionTable);
    }

    public void clickInActionMenu(String actionName) {
        String xpath = "//span[text()[normalize-space()='" + actionName + "']]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void clickMoveButton() {
        String xpath = "//span[text()[normalize-space()='Move']]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void verifyListClipart(String sListClipart) {
        String xPath = "//div[@class='text-right s-input']/input";
        List<String> listClipart = getListTextValue(xPath);
        assertThat(listClipart).isEqualTo(Arrays.asList(sListClipart.split(",")));
    }

    public void inputPosition(String position) {
        String xpathInputNumber = "//input[@type='number' and @class='s-input__inner']";
        waitClearAndType(xpathInputNumber, position);
    }

    public void openFolderClipart(String nameclipart) {
        String xpathOpenClipart = "//span[contains(text(),'" + nameclipart + "')]";
        clickOnElement(xpathOpenClipart);
        waitABit(2000);
    }

    public void inputNameClipartFolder(String sImage) {
        String xpath = "//h4[contains(text(),'Cliparts')]//parent::div//input[@id='upload']";
        waitUntilElementVisible(xpath, 30);
        chooseMultipleAttachmentFiles(xpath, sImage);
        String saveChangeXpath = "//div[@class = 'row save-setting-content']//span[contains(text(),'Save changes')]";
        waitUntilElementVisible(saveChangeXpath, 50);
    }

    public Integer getIndexOfCiplartInFolder(String name) {
        String xpath_row = "//tbody[@class='custom-option__list s-mt24 drag-clipart-container body-row']/tr";
        int n = countElementByXpath(xpath_row);
        for (int i = 1; i <= n; i++) {
            String xpath_nameClipart = "//tbody/tr[" + i + "]//div[@class='text-right s-input']//input";
            if (getTextValue(String.format(xpath_nameClipart, i)).equalsIgnoreCase(name)) {
                n = i;
                break;
            }
        }
        return n;
    }

    public void selectClipartInFolder(String nameClipart) {
        Integer i = getIndexOfCiplartInFolder(nameClipart);
        String xpath_select_status = "//tbody/tr[" + i + "]//label[@class='s-checkbox']/input";
        String xpath_select = "//tbody/tr[" + i + "]//label[@class='s-checkbox']/span[@class='s-check']";
        verifyCheckedThenClick(xpath_select_status, xpath_select, true);
    }

    public void changeNameClipart(int i, String desClipart) {
        String xpath = "//input[@id='id-name-" + i + "']";
        waitClearAndType(xpath, desClipart);
    }

    public void changeImageThumWithIndex(Integer i, String newThumbnail) {
        String xpath = "//tbody/tr[" + i + "]//input[@type='file']";
        waitUntilElementVisible(xpath, 10);
        chooseAttachmentFile(xpath, newThumbnail);
    }
}