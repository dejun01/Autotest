package com.opencommerce.shopbase.dashboard.apps.printbase.steps.library;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.printbase.library.ClipartFolderPage;
import common.utilities.LoadObject;
import io.cucumber.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ClipartFolderSteps extends ScenarioSteps {
    ClipartFolderPage clipartFolderPage;

    @Step
    public void inputNameFolder(String folder) {
        clipartFolderPage.inputNameFolder(folder);
    }

    public void chooseGroup(String group) {
        clipartFolderPage.inputGroup(group);
    }

    public void inputImageClipart(String image) {
        clipartFolderPage.uploadFileClipArt(image);
    }

    @Step
    public void clickOnSaveChangeBtn() {
        clipartFolderPage.clickOnSaveChangeBtn();
    }

    @Step
    public void nameFolderVery(String sNameFolder) {
        clipartFolderPage.nameFolderVeri(sNameFolder);
    }

    public void nameGroupVery(String sGroupFolder) {
        clipartFolderPage.nameGroupVeri(sGroupFolder);
    }

    public void nameImageVery(String sNameImage) {
        clipartFolderPage.nameImageVeri(sNameImage);
    }

    public void deleteNameImage() {
        clipartFolderPage.deleteNameImage();
    }

    public int randomListClipartFolder() {
        int size = clipartFolderPage.getSizeOfListImageInClipArt();
        Random rand = new Random();
        return 1 + rand.nextInt(size);
    }

    @Step
    public void clickOnAnyClipartFolderCheckbox(String _sGroup) {
        int number = randomListClipartFolder();
        clipartFolderPage.clickAnyCheckBox(number);
        clipartFolderPage.clickOnActionBtn();
        clickOnAssignGroupOption();
        chooseGroup(_sGroup);
        clickOnAssignBtn();
        clipartFolderPage.verifyAfterAssignGroup(number, _sGroup);
    }

    public void clickOnAssignGroupOption() {
        clipartFolderPage.clickOnAssignGroupOption();
    }

    public void clickOnAssignBtn() {
        clipartFolderPage.clickOnAssignBtn();
        waitABit(3000);

    }

    public void assignGroupToFolderAndVerifyGroup(String sGroup) {
        clipartFolderPage.clickOnAllCheckBox();
        clipartFolderPage.clickOnActionBtn();
        clickOnAssignGroupOption();
        chooseGroup(sGroup);
        clickOnAssignBtn();
        clipartFolderPage.verifyGroupAfterAssignAllFolder(sGroup);
    }

    @Step
    public void deleteClipartFolder() {
        int size = clipartFolderPage.getSizeOfListImageInClipArt();
        clipartFolderPage.clickAnyCheckBox(randomListClipartFolder());
        clipartFolderPage.clickOnActionBtn();
        clipartFolderPage.clickOnDeleteOption();
        clipartFolderPage.clickOnDeleteBtn();
        waitABit(3000);
        clipartFolderPage.verifyAfterDeleteFolder(size);
    }

    public void verifyAfterDeleteAllFolder() {
        clipartFolderPage.verifyAfterDeleteAllFolder();
    }

    public void verifyNameFolder(String sNameFolder) {
        clipartFolderPage.verifyMessageErrNameFolder(sNameFolder);
    }

    public void verifyImportClipart(String sClipart) {
        clipartFolderPage.veryfyMessageErrImportClipart(sClipart);
    }

    public void verifyImportImage(String sImage) {
        clipartFolderPage.veryfyMessageErrImportImage(sImage);
    }

    public void clickOnDeleteIcon() {
        clipartFolderPage.clickOnDeleteIcon();
    }

    public void assignGroupWhenClickCancel(String sGroup) {
        clipartFolderPage.clickAnyCheckBox(randomListClipartFolder());
        clipartFolderPage.getCurrenNameGroupbeforeAssignGroup(randomListClipartFolder());
        clipartFolderPage.clickOnActionBtn();
        clickOnAssignGroupOption();
        chooseGroup(sGroup);
        clipartFolderPage.clickCancelBtn();
        clipartFolderPage.verifyGroupAfterClickCancelBtn(randomListClipartFolder(), sGroup);
    }

    public void deleteClipartFolderWhenClickCancel() {
        int size = clipartFolderPage.getSizeOfListImageInClipArt();
        clipartFolderPage.clickAnyCheckBox(randomListClipartFolder());
        clipartFolderPage.clickOnActionBtn();
        clipartFolderPage.clickOnDeleteOption();
        clipartFolderPage.clickCancelDeleteFolderBtn();
        clipartFolderPage.verifyAfterClickCancelDeleteFolderBtn(size);
    }

    public void chooseAClipartFolder() {
        clipartFolderPage.chooseAClipartFolder(randomListClipartFolder());
    }

    public void deleteNameFolder() {
        clipartFolderPage.deleteNameFolder();
    }

    public void createGroup() {
        clipartFolderPage.clickOnGroupTxt();
        clipartFolderPage.createGroup(nameGroup());
        clipartFolderPage.clickOnCreateGroupBtn();
    }

    public String nameGroup() {
        return "Group-" + RandomStringUtils.randomNumeric(5);
    }

    public void changeNameData(String _sCurrenImage, String _sImage) {
        int numberImage = clipartFolderPage.getSizeImage();
        for (int i = 0; i < numberImage; i++) {
            if (_sCurrenImage.equals(clipartFolderPage.getNameImage(i)))
                clipartFolderPage.changeNameData(i, _sImage);
        }
    }

    public void deleteImageThumbnail(String _sImage) {
        int numberImage = clipartFolderPage.getSizeImage();
        for (int i = 0; i < numberImage; i++) {
            if (_sImage.equals(clipartFolderPage.getNameImage(i))) {
                clipartFolderPage.deleteImageThumbnail(i);
                clipartFolderPage.verifyThumbnailNotVisible(i);
            }
        }
    }

    public void deleteImage(String _sImage) {
        int numberImage = clipartFolderPage.getSizeImage();
        for (int i = 0; i < numberImage; i++) {
            if (_sImage.equals(clipartFolderPage.getNameImage(i)))
                clipartFolderPage.deleteImage(i);
        }
    }

    public void verifyAfterDeleteImage(String _sImage) {
        int numberImage = clipartFolderPage.getSizeImage();
        for (int i = 0; i < numberImage; i++) {
            clipartFolderPage.verifyAfterDeleteImage(_sImage, i);
        }
    }

    public void changeImage(String _sImage, String _sThumbnail) {
        int numberImage = clipartFolderPage.getSizeImage();
        for (int i = 0; i < numberImage; i++) {
            if (_sImage.equals(clipartFolderPage.getNameImage(i))) {
                clipartFolderPage.importThumbnail(i, _sThumbnail);
            }
        }
    }

    public String getAccessTokenShopBase() {
        return clipartFolderPage.getAccessTokenShopBase();
    }

    public JsonPath getClipart(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-file-library/cliparts.json?query=&status=all&type=folder&access_token=" + accessToken;
        return clipartFolderPage.getAPI(url);
    }

    @Step
    public Response deleteClipartsByAPI(List<Integer> ids, String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-file-library/bulk-clipart-folders/delete.json";
        JsonObject body = requestBodyDeleteClipart(ids);
        Response resp = given().header("X-ShopBase-Access-Token", accessToken)
                .header("Content-Type", "application/json").body(body.toString()).patch(url);
        return resp;
    }

    private JsonObject requestBodyDeleteClipart(List<Integer> clipartIds) {
        JsonObject body = new JsonObject();
        JsonArray ids = new JsonArray();
        for (Integer id : clipartIds)
            ids.add(id);

        body.add("ids", ids);
        return body;
    }
    @Step
    public void openClipartDetail(String sFolder) {
        clipartFolderPage.openClipart(sFolder);
    }

    public boolean isExistActionTable() {
        return clipartFolderPage.isExistActionTable();
    }

    public void clickActionBtn() {
        clipartFolderPage.clickOnActionBtn();
    }

    @Step
    public void clickInActionMenu(String actionName, String position) {
        clipartFolderPage.clickInActionMenu(actionName);
        if (actionName.equals("Move to position"))
            clipartFolderPage.inputPosition(position);
        clipartFolderPage.clickMoveButton();
    }

    public void verifyListClipart(String _sListClipart) {
        clipartFolderPage.verifyListClipart(_sListClipart);
    }

    @Step
    public void openFolderClipart(String nameclipart) {
        clipartFolderPage.openFolderClipart(nameclipart);
    }

    @Step
    public void selectClipartInFolder(String sClipart) {
        String[] listClipart = sClipart.split(",");
        for (String nameClipart : listClipart)
            clipartFolderPage.selectClipartInFolder(nameClipart);
    }

    @Step
    public void changeNameClipart(Integer index, String nameUpdate) {
        clipartFolderPage.changeNameClipart(index, nameUpdate);
    }

    @Step
    public Integer getIndexClipartInFolder(String name) {
        return clipartFolderPage.getIndexOfCiplartInFolder(name);
    }

    @Step
    public void changeImageThumWithIndex(Integer i, String newThumbnail) {
        clipartFolderPage.changeImageThumWithIndex(i, newThumbnail);
    }
}