package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.UploadImagesProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.yecht.Data;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UploadImagesProductDef {
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    UploadImagesProductSteps uploadImagesProductSteps;
    @Steps
    ProductSteps productSteps;


    public static int total;
    public int imageQty;
    public String varGroup;
    public int imgUnCheck;

    @When("add images {string} for variant by group {string}")
    public void updateManyImagesForManyVariantByGroup(String images, String nameGroup) {
        varGroup = nameGroup;
        String[] listImage = images.split(",");
        //for (String image : listImage) {
            productDetailSteps.addImage(images.trim());

        uploadImagesProductSteps.updateManyImagesForManyVariantByGroup(nameGroup);
    }

    @Then("select {string} images")
    public void chooseImages(String qty) {
        imageQty = Integer.parseInt(qty);
        productDetailSteps.selectImageInPopup(qty);
        productDetailSteps.clickSaveButton();
    }
    @When("uncheck {string} variant image by group {string}")
    public void uncheckVariantImageByGroup(String nameGroup, String qty){
        imgUnCheck = Integer.parseInt(qty);
        uploadImagesProductSteps.updateManyImagesForManyVariantByGroup(nameGroup);
        productDetailSteps.selectImageInPopup(qty);
        productDetailSteps.clickSaveButton();
    }

    @When("open update image popup by group {string}")
    public void openUpdateiImagePopupByGroup(String nameGroup) {
        uploadImagesProductSteps.updateManyImagesForManyVariantByGroup(nameGroup);
    }

    @Then("verify variants have many images by group {string}")
    public void verifyVariantsHaveManyImagesByGroup(String nameGroup){
        varGroup = nameGroup;
        productDetailSteps.verifyNumberOfVariantImage(varGroup, imageQty);
    }
    @Then("re-verify variants images by group {string}")
    public void reVerifyVariantsImageByGroup(String nameGroup){
        varGroup = nameGroup;
        productDetailSteps.reVerifyNumberOfVariantImage(varGroup, imgUnCheck, imageQty);
    }
    @Then("re-verify a variant images")
    public void reVerifyAVariantImage(List<List<String>> datatable){
        for (int row : SessionData.getDataTbRowsNoHeader(datatable).keySet()) {
            String optionValue = SessionData.getDataTbVal(datatable, row, "Size");
            String optionValueNext = SessionData.getDataTbVal(datatable, row, "Color");
            productDetailSteps.reVerifyNumberOfOneVarImage(optionValue,optionValueNext,imageQty,imgUnCheck);
        }
    }
    @Then("verify variant has many images")
    public void verifyVariantHasManyImages(List<List<String>> datatable) {
        for (int row : SessionData.getDataTbRowsNoHeader(datatable).keySet()) {
            String optionValue = SessionData.getDataTbVal(datatable, row, "Size");
            String optionValueNext = SessionData.getDataTbVal(datatable, row, "Color");
            productDetailSteps.verifyNumberOfOneVariantImage(optionValue,optionValueNext,imageQty);
        }
    }

    @When("update images {string} for a variant")
    public void updateManyImageForAVariant(String images,List<List<String>> datatable) {
        for (int row : SessionData.getDataTbRowsNoHeader(datatable).keySet()) {
            String optionValue = SessionData.getDataTbVal(datatable, row, "Size");
            String optionValueNext = SessionData.getDataTbVal(datatable, row, "Color");

            uploadImagesProductSteps.updateManyImageForAVariant(optionValue, optionValueNext);
            String[] listImage = images.split(",");
            //for (String image : listImage) {
                productDetailSteps.addImage(images.trim());

        }
    }
    @When("uncheck {string} variant image for a variant")
    public void unCheckImageInAVariant(List<List<String>> datatable,String qty) {
        imgUnCheck = Integer.parseInt(qty);
        for (int row : SessionData.getDataTbRowsNoHeader(datatable).keySet()) {
            String optionValue = SessionData.getDataTbVal(datatable, row, "Size");
            String optionValueNext = SessionData.getDataTbVal(datatable, row, "Color");

            uploadImagesProductSteps.updateManyImageForAVariant(optionValue, optionValueNext);
            productDetailSteps.selectImageInPopup(qty);
            productDetailSteps.clickSaveButton();
            }
        }


    @When("open update image popup by variant")
    public void openUpdateImagePopupByVariant(List<List<String>> datatable) {
        for (int row : SessionData.getDataTbRowsNoHeader(datatable).keySet()) {
            String optionValue = SessionData.getDataTbVal(datatable, row, "Size");
            String optionValueNext = SessionData.getDataTbVal(datatable, row, "Color");

            uploadImagesProductSteps.updateManyImageForAVariant(optionValue, optionValueNext);
        }
    }
    @When("delete {string} images")
    public void deleteImanges(String qty) {
        imageQty = Integer.parseInt(qty);
        productDetailSteps.clickDeleteIcon(qty);
    }

    @And("user select {string} session")
    public void userSelectTab(String session) {
        themeEditorSteps.clickSettingTab();
        themeEditorSteps.clickSection(session);
    }

    @When("setting images list is {string}")
    public void settingImagesListIs(String settingImageList) {
        themeEditorSteps.clickOptionImageList();
        themeEditorSteps.selectOption(settingImageList);
        themeEditorSteps.saveSetting();
        themeEditorSteps.closeThemeEditor();
    }


    @And("user navigate product detail page with name product is {string}")
    public void userNavigateProductDetailPageWithNameProductIs(String nameProduct) {
        productDetailSteps.searchProduct(nameProduct);
        productDetailSteps.chooseProduct(nameProduct);
    }

    @Then("user count number of photos by setting")
    public void userCountNumberOfPhotosBySetting() {
        total = productDetailSteps.countAllphoto();
    }

    @Then("user count number of photos on store front")
    public void userCountNumberOfPhotosOnStoreFront() {
        productDetailSteps.NumberOfPhotosOnStoreFront();
    }


    @When("go to store front")
    public void goToStoreFront() {
        productSteps.clickToViewProduct();
        productSteps.switchToWindowWithIndex(1);
    }

    @And("verify number of photos")
    public void verifyNumberOfPhotos() {
        assertThat(total).isEqualTo(productDetailSteps.NumberOfPhotosOnStoreFront());
    }

    @And("verify mockup assign for base product")
    public void verifyMockupAssignForBaseProduct(List<List<String>> datatable) {
        for (int row : SessionData.getDataTbRowsNoHeader(datatable).keySet()) {
            String sProduct = SessionData.getDataTbVal(datatable, row, "Product");
            String sTotalMockup = SessionData.getDataTbVal(datatable, row, "Total mockup");

            uploadImagesProductSteps.verifyMockupAssignForBaseProduct(sTotalMockup);
        }
    }

    @And("view variant")
    public void viewVariant() {
        uploadImagesProductSteps.viewVariant();
    }
}
