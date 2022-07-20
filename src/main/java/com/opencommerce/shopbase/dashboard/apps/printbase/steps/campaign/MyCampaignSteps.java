package com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign.MyCampaignPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign.CampaignPage;
import com.opencommerce.shopbase.dashboard.products.pages.ProductListPages;
import common.SBasePageObject;
import io.cucumber.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static common.utilities.LoadObject.getFilePath;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MyCampaignSteps extends ScenarioSteps {
    MyCampaignPage myCampaignPage;
    PrintbaseAPI printbaseAPI;
    CampaignPage campaignPage;
    ProductListPages productListPages;

    public int countCampaigns() {
        return myCampaignPage.countCampaigns();
    }

    public void deleteTheFistCampaign() {
        myCampaignPage.deleteTheFistCampaign();
        myCampaignPage.verifyTextPresent("Your campaign has been deleted successful!", true);
    }

    @Step
    public void refreshPage() {
        myCampaignPage.refreshPage();
        myCampaignPage.waitForPageLoad();
    }

    @Step
    public void verifyEnableSizeChart() {
        myCampaignPage.verifyEnableSizeChart();
    }

    public JsonPath getBaseProductInformation(String accToken, Integer sProductID) {
        String shop = LoadObject.getProperty("shop");
        String shopId = LoadObject.getProperty("shop_id");
        String url = "https://" + shop + "/admin/pbase-product-base/products.json?ids=" + sProductID + "&shop_id=" + shopId;
        JsonPath js = given().header("X-ShopBase-Access-Token", accToken).get(url).then().extract().jsonPath();
        return js;

    }

    public JsonPath getCampaign(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-campaigns.json?query=&status=all&access_token=" + accessToken;
        return myCampaignPage.getAPI(url);
    }

    @Step
    public Response deleteCampainsByAPI(Integer id, String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-campaigns.json";
        JsonObject requestParam = requestBodyDeleteCampain(id);
        Response resp = given().header("X-ShopBase-Access-Token", accessToken).header("Content-Type", "application/json").body(requestParam.toString()).delete(url);
        return resp;
    }

    private JsonObject requestBodyDeleteCampain(Integer productIds) {
        JsonObject requestParam = new JsonObject();
        JsonArray ids = new JsonArray();
        ids.add(productIds);
        requestParam.add("ids", ids);
        requestParam.addProperty("product_availability", "");
        requestParam.addProperty("limit", 50);
        requestParam.addProperty("search", "");
        requestParam.addProperty("page", 1);
        requestParam.addProperty("status", "all");
        return requestParam;
    }

    public JsonPath getListCampaignWithPage(String accessToken, int page, int limit, int status) {
        String app_shop_id = LoadObject.getProperty("app_shop_id");
        String url = LoadObject.getProperty("gapi.url") + "/v1/phub/merchant/product?app_shop_id=" + app_shop_id + "&page=" + page + "&limit=" + limit + "&status=" + status;
        return myCampaignPage.getAPI(url);
    }

    public int getTotalCampaign(JsonPath listCampaign) {
        return (int) myCampaignPage.getData(listCampaign, "total_product");
    }

    @Step
    public void logInfor(String s) {
    }

    public List<String> getNameCampaign(JsonPath listCampaignStatus) {
        return myCampaignPage.getDataByKey(listCampaignStatus, "result.title");
    }

    public void searchProduct(String productName) {
        myCampaignPage.waitForEverythingComplete();
        myCampaignPage.clickBtnSearch();
        myCampaignPage.enterProduct(productName);
        myCampaignPage.waitForEverythingComplete();
        waitABit(3000);
    }

    public void clickSetIndividualPrice(String products) {
        myCampaignPage.clickSetIndividualPrice(products);
    }

    public void clickBtnViewOnStore() {
        myCampaignPage.clickBtnViewOnStore();
    }

    public void switchNewTabOnBrowser() {
        myCampaignPage.switchToNewTabOnBrowser();
    }

    public String getAccessTokenShopBase() {
        return myCampaignPage.getAccessTokenShopBase();
    }

    //1
    @Step
    public void clickaddMoreProduct() {
        myCampaignPage.clickaddMoreProduct();
    }

    @Step
    public void clickBtnPreview() {
        myCampaignPage.clickBtn("Preview");
    }

    @Step
    public void clickBaseProduct(String productName) {
        myCampaignPage.clickBaseProduct(productName);
    }

    @Step
    public void verifyColor() {
        myCampaignPage.verifyColor();
    }

    @Step
    public void selectAllColor() {
        myCampaignPage.selectAllColor();
    }

    @Step
    public void verifyMessageTotalVariant() {
        myCampaignPage.verifyMessageTotalVariant();
    }

    @Step
    public void verifySize(String size) {
        String[] sizes = size.split(",");
        for (String sizeName : sizes) {
            myCampaignPage.verifySize(sizeName);
        }
    }

    @Step
    public void addLayer(String frontOrBack, String layer) {
        myCampaignPage.addLayer(frontOrBack, layer);
    }

    @Step
    public void addArtwork(String imageName) {
        myCampaignPage.addArtwork(imageName);
    }

    @Step
    public void editLayer(String layer) {
        myCampaignPage.editLayer(layer);
    }

    @Step
    public void clickBack() {
        myCampaignPage.clickBack();
        waitABit(3000);
    }

    @Step
    public void clickSync(String layer) {
        myCampaignPage.clickSync(layer);
    }

    @Step
    public void clickDeleteLayer(String layer) {
        myCampaignPage.clickDeleteLayer(layer);
    }

    @Step
    public void verifyLayerSynced(String layer, Boolean layerDisplay) {
        myCampaignPage.verifyLayerSynced(layer, layerDisplay);
    }

    @Step
    public void verifyCustonOptionSynced(String layer, Boolean layerDisplay) {
        myCampaignPage.verifyCustonOptionSynced(layer, layerDisplay);
    }

    @Step
    public void clickBtnDelete() {
        myCampaignPage.clickBtn("Delete");
        waitABit(2000);
    }

    @Step
    public void clickBtnContinue() {
        myCampaignPage.clickBtn("Continue");
        myCampaignPage.waitUntilInvisibleLoading(20);
    }

    @Step
    public void clickBtnLaunch() {

        myCampaignPage.clickBtn("Launch");
    }
    //2

    @Step
    public void chooseStyle(String productName) {
        myCampaignPage.chooseStyle(productName);
    }

    @Step
    public void hoverColor(String color) {
        myCampaignPage.hoverColor(color);
    }

    @Step
    public void inputLocation(String location) {
        String[] values = location.split(",");
        String label = values[0];
        String value = values[1];
        myCampaignPage.inputLocation(label, value);
    }

    @Step
    public void inputSize(String size) {
        String[] values = size.split(",");
        String label = values[0];
        String value = values[1];
        myCampaignPage.inputSize(label, value);
    }

    @Step
    public void inputRotation(String value) {
        myCampaignPage.inputRotation(value);
    }

    @Step
    public void inputText(String value) {
        myCampaignPage.inputText(value);
    }

    @Step
    public void inputColor(String value) {
        myCampaignPage.inputColor(value);
    }

    @Step
    public void inputSizeText(String value) {
        myCampaignPage.inputSizeText(value);
    }

    @Step
    public void selectProduct(String product) {
        myCampaignPage.selectProduct(product);
    }

    @Step
    public void scrollElement() {
        myCampaignPage.scrollElement();
    }

    @Step
    public void clickCustomOption() {
        myCampaignPage.clickCustomOption();
    }

    @Step
    public void addCustomOption() {
        myCampaignPage.addCustomOption();
    }

    @Step
    public void selectType(String type) {
        myCampaignPage.selectType(type);
    }

    @Step
    public void selectLayer(String layer) {
        myCampaignPage.selectLayer(layer);
    }

    @Step
    public void inputCustomOption(String label, String value) {
        myCampaignPage.inputTextInCustomOption(label, value);
    }

    @Step
    public void selectFont(String font) {
        myCampaignPage.selectFont(font);
    }

    @Step
    public void inputPlaceholder(String placeholder) {
        myCampaignPage.inputPlaceholder(placeholder);
    }

    @Step
    public void inputMaxLength(String value) {
        myCampaignPage.inputMaxLength(value);
    }

    @Step
    public void inputDefaultValue(String defaultValue) {
        myCampaignPage.inputDefaultValue(defaultValue);
    }

    @Step
    public void verifyNotifycation(String colName, String notify) {
        myCampaignPage.verifyNotifycation(colName, notify);
    }

    @Step
    public void inputImage(String fileName) {
        myCampaignPage.inputImage(fileName);
    }

    @Step
    public void dropImage(String fileName) {
        myCampaignPage.dropImage(fileName);
    }

    @Step
    public void clickPictureChoiceImage() {
        myCampaignPage.clickPictureChoiceImage();
    }

    @Step
    public void clickAllow(String label) {
        myCampaignPage.checkAndClickAllow(label);
    }

    @Step
    public void clickActionMenuOption(String optionName) {
        myCampaignPage.clickActionMenuOfOption(optionName);
    }


    @Step
    public void verifyCustonField(String layer, boolean isDisplay) {
        myCampaignPage.verifyCustonField(layer, isDisplay);
    }

    @Step
    public void verifyMessageImage(String message) {
        myCampaignPage.verifyMessageImage(message);
    }

    @Step
    public void addOption(String label, String location) {
        myCampaignPage.addOption(label, location);
    }


    @Step
    public void compareImage(String xpathImage, String imageExpect) throws IOException {
        myCampaignPage.compareImage(xpathImage, imageExpect);
    }

    @Step
    public void verifyText(String text) {
        myCampaignPage.verifyText(text);
    }

    @Step
    public void verifyLocationAndSize(String location) {
        String[] values = location.split(",");
        String label = values[0];
        String value = values[1];
        myCampaignPage.verifyLocationAndSize(label, value);
    }

    @Step
    public void editTextLayer(String layerName, String layerFont) {
        if (!layerName.isEmpty())
            myCampaignPage.inputTitleLayerText(layerName);
        if (!layerFont.isEmpty())
            inputTextFont(layerFont);
    }

    public void inputTextFont(String font) {
        myCampaignPage.inputTextFont(font);
    }

    @Step
    public void verifyNotifycationOfOptionList(String label, String noti) {
        verifyNotifycationOfOption(label, noti);
        verifyIconNoti(true);
    }

    public void verifyNotifycationOfOption(String label, String noti) {
        if (label.isEmpty())
            label = "Untitled";
        String noti_actual = myCampaignPage.getNotiOfOption(label);
        assertThat(noti_actual).isEqualTo(noti);
    }

    public void verifyIconNoti(Boolean is) {
        myCampaignPage.verifyIconNoti(is);
    }


    public void clearTextLabel() {
        myCampaignPage.clearTextLable();
    }

    @Step
    public void clickActionCustomOption(String optionName, String btnAction) {
        myCampaignPage.clickActionCustomOption(optionName, btnAction);
    }

    @Step
    public void verifyImageLivePreview(String image, String per) throws IOException {
         Float percent = 1.00f;
        if (!per.isEmpty())
            percent = Float.parseFloat(per);
        myCampaignPage.verifyImageLivePreview(image, percent);
//        BufferedImage expectedImage =
//                ImageIO.read(new File(getFilePath(File.separator + image)));
//        ImageDiffer imgDiff = new ImageDiffer();
//        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
//        Assert.assertFalse("Result of Image comparsion", diff.hasDiff());
//        System.out.println("Images Compared Sucesfully");
    }

    @Step
    public void inputValue(String value) {
        myCampaignPage.inputValue(value);
    }

    @Step
    public void selectTypeClipart(String typeClipart) {
        myCampaignPage.selectTypeClipart(typeClipart);
    }

    @Step
    public void selectValueClipart(String typeClipart, String value) {
        if (typeClipart.equals("Folder")) {
            myCampaignPage.searchNameFolderClipart(value);
            waitABit(3000);
            myCampaignPage.selectFolderClipart();
        }
        if (typeClipart.equals("Group")) {
            myCampaignPage.searchNameGroupClipart(value);
            waitABit(3000);
            myCampaignPage.selectGroupClipart(value);
        }

    }

    @Step
    public void selectTypeDisplayNameClipart(String typeDisplayNameClipart) {
        myCampaignPage.selectTypeDisplayNameClipart(typeDisplayNameClipart);
    }

    @Step
    public void getMessReachLimitQuota(String mess) {
        myCampaignPage.getMessReachLimitQuota(mess);
    }

    @Step
    public void getMessReachLimitQuotaSB(String shopID, String accessToken, String mess) {
        myCampaignPage.getMessReachLimitQuotaSB(shopID, accessToken, mess);
    }

    @Step
    public void getMessLimitReachLimitQuotaHourly(String shopID, String accessToken, String mess) {
        myCampaignPage.getMessReachLimitQuotaHourly(shopID, accessToken, mess);
    }


    public void clickClosePopupNotiQuota() {
        myCampaignPage.clickClosePopupNotiQuota();
    }

    @Step
    public Response postQuotaHourlyByAPI(String shopID, String accessToken, Integer quota) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/internal/setting/bulk-duplicate-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonObject requestParam = postQuotaHourlyByAPI(quota);
        Response resp = given().header("X-ShopBase-Access-Token", accessToken).header("Content-Type", "application/json").body(requestParam.toString()).post(url);
        return resp;
    }

    private JsonObject postQuotaHourlyByAPI(Integer quota) {
        JsonObject requestParam = new JsonObject();
        requestParam.addProperty("limit", quota);
        return requestParam;
    }

    @Step
    public void confirmDelete() {
        myCampaignPage.clickBtn("Delete campaign", 2);
    }


    public JsonPath getArtwork(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-file-library.json?query=&status=all&access_token=" + accessToken;
        return myCampaignPage.getAPI(url);
    }

    @Step
    public Response deleteArtworkByAPI(Integer id, String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/pbase-file-library";
        JsonObject requestParam = requestBodyDeleteCampain(id);
        Response resp = given().header("X-ShopBase-Access-Token", accessToken).header("Content-Type", "application/json").body(requestParam.toString()).delete(url);
        return resp;
    }

    @Step
    public void uploadArtwork(String artwork) {
        campaignPage.uploadArtwork(artwork);
    }

    @Step
    public void searchArtwork(String artwork) {
        myCampaignPage.searchArtwork(artwork);
    }

    @Step
    public void verifyNameArtwork(String artwork) {
        myCampaignPage.verifyNameArtwork(artwork);
    }

    @Step
    public void verifyMessageUploadArtwork(String errorMessage) {
        myCampaignPage.verifyMessageUploadArtwork(errorMessage);
    }

    public int countArtwork() {
        return myCampaignPage.countArtwork();
    }

    public void deleteTheFistArtwork() {
        myCampaignPage.deleteTheFistArtwork();
    }

    @Step
    public void clickActionOnMenuCustomOption(String customName, String actionName, String messError) {
        myCampaignPage.clickActionOnMenuCustomOption(customName, actionName);
        if (!messError.isEmpty()) {
            myCampaignPage.verifyMessageErrorAfclickAction(messError);
        }
    }
    @Step
    public void verifyImportCampaign(String status) {
        productListPages.verifyImportProcess(status, 0);
    }
    @Step
    public void verifyImageMockup(String image, String per,int imageNumber) throws IOException {
        Float percent = 1.00f;
        if (!per.isEmpty())
            percent = Float.parseFloat(per);
        myCampaignPage.verifyImageMockup(image, percent,imageNumber);
    }

    @Step
    public JsonPath getAPI(String url) {
        JsonPath js = myCampaignPage.getAPI(url);
        return js;
    }
    @Step
    public void clickToBtnEditFromSampleOfCustomArt(String customArt) {
        myCampaignPage.clickToBtnEditFromSampleOfCustomArt(customArt);
    }
    @Step
    public void clickToBtnImportToStoreOfCustomArt(String customArt) {
        myCampaignPage.clickToBtnImportToStoreOfCustomArt(customArt);
    }
    @Step
    public void verifyMessageWhenReachQuotaCampaign() {
        myCampaignPage.verifyMessageWhenReachQuotaCampaign();
    }

    public void verifyMessageWhenReachLimitQuotaCampaignPerHourly() {
        myCampaignPage.verifyMessageWhenReachLimitQuotaCampaignPerHourly();
    }

    public void verifyMessageWhenShopbaseReachLimitQuotaCampaign() {
        myCampaignPage.verifyMessageWhenShopbaseReachLimitQuotaCampaign();
    }

    public void verifyShippingForVariant(String sku, Float shippingFee) {
        myCampaignPage.verifyShippingForVariant(sku, shippingFee);
    }
    @Step
    public void verifyNumberClipartOnSF(String sNumber) {
        myCampaignPage.verifyNumberClipartOnSF(sNumber);
    }
}
