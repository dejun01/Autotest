package opencommerce.apps.printbase.campaign;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.CatalogSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.EditorCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.dashboard.orders.api.TransactionsAPI;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import common.utilities.LoadObject;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import io.cucumber.java.vi.Nhưng;
import io.restassured.path.json.JsonPath;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static com.opencommerce.shopbase.OrderVariable.customArtName;
public class MyCampaignDef {
    @Steps
    MyCampaignSteps myCampaignSteps;

    @Steps
    CampaignSteps campaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;
    @Steps
    CommonSteps commonSteps;
    @Steps
    EditorCampaignSteps editorCampaignSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    CatalogSteps catalogSteps;
    boolean isPass = true;
    String accToken = "";
    String shopID = "";
    public static Integer quotaCampaign;
    public static Integer quotaProduct;

    @Given("^delete all campaigns of PHub$")
    public void delete_campaigns() {
        while (myCampaignSteps.countCampaigns() > 0) {
            int n = myCampaignSteps.countCampaigns();
            for (int i = 1; i <= n; i++) {
                myCampaignSteps.deleteTheFistCampaign();

            }
        }
    }

    @Given("^delete all campaigns by API$")
    public void delete_campaigns_api() {
        if (isPass) {
            if (accToken.isEmpty())
                accToken = myCampaignSteps.getAccessTokenShopBase();
            JsonPath listCampaign = myCampaignSteps.getCampaign(accToken);
            Object result = listCampaign.get("campaigns");
            if (result != null) {
                List<Integer> campaigns = listCampaign.get("campaigns.id");
                for (Integer id : campaigns)
                    myCampaignSteps.deleteCampainsByAPI(id, accToken);
            }

        }
    }

    @Given("^verify all campaigns were live on Phub$")
    public void verify_all_campaign_were_live_on_Phub() {
        if (accToken.isEmpty())
            accToken = myCampaignSteps.getAccessTokenShopBase();
        int totalCampaignNoPublished = 0;
        for (int i = 1; i <= 2; i++) {
            JsonPath listCampaignStatus = myCampaignSteps.getListCampaignWithPage(accToken, 1, 20, i);
            int numberOfCampaign = myCampaignSteps.getTotalCampaign(listCampaignStatus);
            if (numberOfCampaign > 0) {
                myCampaignSteps.logInfor("Status = " + i + " have campaign: " + myCampaignSteps.getNameCampaign(listCampaignStatus));
            }
            totalCampaignNoPublished = totalCampaignNoPublished + numberOfCampaign;

        }
        if (totalCampaignNoPublished != 0) {
            isPass = false;
        }
        assertThat(totalCampaignNoPublished).isEqualTo(0);
    }


    @And("^wait (\\d+) second$")
    public void waitSecond(int time) {
        myCampaignSteps.waitABit(time);
    }

    @And("^refresh page$")
    public void refreshPage() {
        myCampaignSteps.waitABit(5000);
        myCampaignSteps.refreshPage();
    }

    @And("^redirect to product detail$")
    public void redirectToProductDetail() {
        myCampaignSteps.clickBtnViewOnStore();
        myCampaignSteps.switchNewTabOnBrowser();
    }

    @Then("Verify enable size chart")
    public void verifyEnableSizeChart() {
        myCampaignSteps.verifyEnableSizeChart();
    }

    @And("add more base product")
    public void addMoreBaseProduct() {
        myCampaignSteps.clickaddMoreProduct();
    }

    @And("verify campaign preview")
    public void verifyCampaignPreview(List<List<String>> dataTable) throws IOException {
        myCampaignSteps.clickBtnPreview();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String imageExpect = SessionData.getDataTbVal(dataTable, row, "Image expect");
            String xpathImage = SessionData.getDataTbVal(dataTable, row, "Xpath image");

            myCampaignSteps.clickBaseProduct(sProduct);
            myCampaignSteps.chooseStyle(sProduct);
            myCampaignSteps.compareImage(xpathImage, imageExpect);
            myCampaignSteps.verifyColor();
        }
    }

    @Then("Sync layer")
    public void syncLayer(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");

            myCampaignSteps.clickBaseProduct(sProduct);
            myCampaignSteps.clickSync(layer);
        }
    }

    @And("verify layer have synced")
    public void verifyLayerHaveSynced(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            Boolean layerDisplay = Boolean.valueOf(SessionData.getDataTbVal(dataTable, row, "Layer display"));
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String location_x = SessionData.getDataTbVal(dataTable, row, "Location X");
            String location_y = SessionData.getDataTbVal(dataTable, row, "Location Y");
            String size_x = SessionData.getDataTbVal(dataTable, row, "Size X");
            String size_y = SessionData.getDataTbVal(dataTable, row, "Size Y");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");

            myCampaignSteps.clickBaseProduct(sProduct);
            myCampaignSteps.verifyLayerSynced(layer, layerDisplay);
            myCampaignSteps.editLayer(layer);
            myCampaignSteps.verifyText(text);
            myCampaignSteps.verifyLocationAndSize(location_x);
            myCampaignSteps.verifyLocationAndSize(location_y);
            myCampaignSteps.verifyLocationAndSize(size_x);
            myCampaignSteps.verifyLocationAndSize(size_y);

        }
    }

    @Then("delete layer")
    public void deleteLayer(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");

            myCampaignSteps.clickBaseProduct(sProduct);
            editorCampaignSteps.clickBack();
            myCampaignSteps.clickDeleteLayer(layer);
        }
    }

    @And("create new campaign editor")
    public void createNewCampaignEditor() {
        myCampaignSteps.clickBtnContinue();
        myCampaignSteps.clickBtnLaunch();
        campaignSteps.waitUntilLoadingSuccess();
    }

    @And("change setting layer")
    public void changeSettingLayer(List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String layerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String location_x = SessionData.getDataTbVal(dataTable, row, "Location X");
            String location_y = SessionData.getDataTbVal(dataTable, row, "Location Y");
            String size_x = SessionData.getDataTbVal(dataTable, row, "Size X");
            String size_y = SessionData.getDataTbVal(dataTable, row, "Size Y");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");
            String imageExpect = SessionData.getDataTbVal(dataTable, row, "Image expect");
            String xpathImage = SessionData.getDataTbVal(dataTable, row, "Xpath image");

            myCampaignSteps.editLayer(layerName);
            if (!text.isEmpty()) {
                myCampaignSteps.inputText(text);
            }
            if (!size.isEmpty()) {
                myCampaignSteps.inputSizeText(size);
            }
            if (!color.isEmpty()) {
                myCampaignSteps.inputColor(color);
            }
            if (!size_x.isEmpty()) {
                myCampaignSteps.inputSize(size_x);
            }
            if (!size_y.isEmpty()) {
                myCampaignSteps.inputSize(size_y);
            }
            if (!location_x.isEmpty()) {
                myCampaignSteps.inputLocation(location_x);
            }
            if (!location_y.isEmpty()) {
                myCampaignSteps.inputLocation(location_y);
            }
            if (!rotation.isEmpty()) {
                myCampaignSteps.inputRotation(rotation);
            }
            if (!imageExpect.isEmpty() && !xpathImage.isEmpty()) {
                myCampaignSteps.compareImage(xpathImage, imageExpect);
            }
            myCampaignSteps.clickBack();
        }
    }

    @And("verify tab pricing campaign")
    public void verifyTabPricingCampaign(List<List<String>> dataTable) throws IOException {
        myCampaignSteps.clickBtnContinue();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String imageRender = SessionData.getDataTbVal(dataTable, row, "Image render");
            String xpathImageRender = SessionData.getDataTbVal(dataTable, row, "Xpath image render");
            String imagePricing = SessionData.getDataTbVal(dataTable, row, "Image pricing");
            String xpathImagePricing = SessionData.getDataTbVal(dataTable, row, "Xpath image pricing");

            myCampaignSteps.selectProduct(product);
            myCampaignSteps.compareImage(xpathImageRender, imageRender);
            myCampaignSteps.compareImage(xpathImagePricing, imagePricing);
        }
    }

    @And("^add custom options for campaign")
    public void addCustomOptionsForCampaign() {
        myCampaignSteps.clickCustomOption();
    }

    @And("change option layer and verify custom option")
    public void changeOptionLayer(List<List<String>> dataTable) {

        myCampaignSteps.addCustomOption();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String label = SessionData.getDataTbVal(dataTable, row, "Label");
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String placeholder = SessionData.getDataTbVal(dataTable, row, "Placeholder");
            String value = SessionData.getDataTbVal(dataTable, row, "Max length");
            String defaultValue = SessionData.getDataTbVal(dataTable, row, "Default value");
            String allow = SessionData.getDataTbVal(dataTable, row, "Allow the following characters");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String _sNotiDefaultValue = SessionData.getDataTbVal(dataTable, row, "Noti for Default value");
            System.out.println("run : " + row);

            myCampaignSteps.selectType(type);
            if (!layer.isEmpty()) {
                editorCampaignSteps.selectLayer(layer);
            } else {
                myCampaignSteps.verifyNotifycation("Layer", "Field is required");
            }
            myCampaignSteps.inputCustomOption("Label", label);
            if (label.isEmpty())
                myCampaignSteps.verifyNotifycation("Label", "Field is required");
            if (type.contains("Text")) {
                if (!font.isEmpty()) {
                    myCampaignSteps.selectFont(font);
                } else {
                    myCampaignSteps.verifyNotifycation("Font", "Field is required");
                }
                if (!placeholder.isEmpty()) {
                    myCampaignSteps.inputCustomOption("Placeholder", placeholder);
                }
                myCampaignSteps.inputCustomOption("Max length", value);
                if (value.isEmpty())
                    myCampaignSteps.verifyNotifycation("Max length", "Field is required");
                else if (Integer.parseInt(value) == Integer.parseInt("0")) {
                    myCampaignSteps.verifyNotifycation("Max length", "Max length must be greater than 0");
                } else if (Integer.parseInt(value) >= Integer.parseInt("256")) {
                    myCampaignSteps.verifyNotifycation("Max length", "Max length must be less than 256");
                }
                if (!allow.isEmpty()) {
                    myCampaignSteps.clickAllow(allow);
                }
                myCampaignSteps.inputCustomOption("Default value ", defaultValue);
                if (!_sNotiDefaultValue.isEmpty())
                    myCampaignSteps.verifyNotifycation("Default value", _sNotiDefaultValue);


                if (type.contains("Picture")) {
                    if (!image.isEmpty()) {
                        myCampaignSteps.inputImage(image);
                    }
                }
//            myCampaignSteps.clickBack();
//            if (!notification.isEmpty()) {
//                if (label.isEmpty())
//                    label = "Untitled";
//                myCampaignSteps.verifyNotifycationOfOptionList(label, "Missing required fields");
//                myCampaignSteps.clickActionMenuOption(label);
//                myCampaignSteps.clickActionCustomOption(label, "Delete");
//                myCampaignSteps.clickBtnDelete();
//            }
//            myCampaignSteps.addCustomOption();
            }
        }
    }

    @Then("^verify image mockup with image \"([^\"]*)\" and \"([^\"]*)\" percent$")
    public void verifyImageMockupWithImage(String image, String per) throws IOException, URISyntaxException {
        if (!image.isEmpty()) {
            myCampaignSteps.verifyImageLivePreview(image, per);
//            BufferedImage actualImage = commonSteps.takesScreenshotByElement("//div[@class='product-image-preview']/img");
//            BufferedImage expectedImage = ImageIO.read(new File(LoadObject.getFilePath("screen_1920x1080/PerImage_ex.png")));
//
//            commonSteps.logInfor("read 2 image");
//            commonSteps.compareImage(image,actualImage,94);

//            ImageDiffer imgDiff = new ImageDiffer();
//            ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
//            Assert.assertFalse("Result of Image comparsion", diff.hasDiff());
//            System.out.println("Images Compared Sucesfully");
        }
    }


    @And("upload image layer")
    public void uploadImageLayer(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            myCampaignSteps.inputImage(image);
            if (!message.isEmpty()) {
                myCampaignSteps.verifyMessageImage(message);
            }
        }
    }

    @And("verify custon option have synced")
    public void verifyCustonOptionHaveSynced(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            Boolean layerDisplay = Boolean.valueOf(SessionData.getDataTbVal(dataTable, row, "Layer display"));

            myCampaignSteps.verifyCustonOptionSynced(layer, layerDisplay);
        }
    }

    @And("add option")
    public void addOption(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String label = SessionData.getDataTbVal(dataTable, row, "Label");
            String addOption = SessionData.getDataTbVal(dataTable, row, "Add option");
            myCampaignSteps.addOption(label, addOption);
        }
    }

    @And("add custom option as {string}")
    public void addCustomOptionAs(String dataKey, List<List<String>> dataTable) {
        campaignSteps.waitABit(10000);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String label = SessionData.getDataTbVal(dataTable, row, "Label");
            /* Layer text */
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String placeholder = SessionData.getDataTbVal(dataTable, row, "Placeholder");
            String value = SessionData.getDataTbVal(dataTable, row, "Max length");
            String defaultValue = SessionData.getDataTbVal(dataTable, row, "Default value");
            String allow = SessionData.getDataTbVal(dataTable, row, "Allow the following characters");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            /* Layer image or picture choice  */
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String notification = SessionData.getDataTbVal(dataTable, row, "Notification");

            myCampaignSteps.selectType(type);
            myCampaignSteps.inputCustomOption("Label", label);
            if (label.isEmpty()) {
                myCampaignSteps.verifyNotifycation("Label", notification);
            }
            if (!layer.isEmpty()) {
                myCampaignSteps.selectLayer(layer);
            } else {
                myCampaignSteps.verifyNotifycation("Layer", notification);
            }
            if (!font.isEmpty()) {
                myCampaignSteps.selectFont(font);
            } else {
                myCampaignSteps.verifyNotifycation("Font", notification);
            }
            if (!placeholder.isEmpty()) {
                myCampaignSteps.inputPlaceholder(placeholder);
            }
            if (type.contains("Text")) {
                myCampaignSteps.inputMaxLength(value);
            }
            if (value.isEmpty()) {
                myCampaignSteps.verifyNotifycation("Max length", notification);
            } else if (Integer.parseInt(value) == Integer.parseInt("0")) {
                myCampaignSteps.verifyNotifycation("Max length", notification);
            } else if (type.equals("Text area") && Integer.parseInt(value) >= Integer.parseInt("256")) {
                myCampaignSteps.verifyNotifycation("Max length", notification);
            } else if (type.equals("Text field") && Integer.parseInt(value) >= Integer.parseInt("17")) {
                myCampaignSteps.verifyNotifycation("Max length", notification);
            }
            if (!defaultValue.isEmpty()) {
                myCampaignSteps.inputDefaultValue(defaultValue);
            }
            if (!allow.isEmpty()) {
                myCampaignSteps.clickAllow(allow);
            }
            if (!text.isEmpty()) {
                myCampaignSteps.inputText(text);
                myCampaignSteps.verifyNotifycation(label, notification);
            }
            if (!image.isEmpty()) {
                myCampaignSteps.inputImage(image);
            }

        }
        myCampaignSteps.clickBack();
    }

    @And("add new layer for base product as {string}")
    public void addNewLayerForBaseProductAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layerType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String layerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String layerFont = "";
            if (!product.isEmpty()) {
                myCampaignSteps.clickBaseProduct(product);
            }
//            myCampaignSteps.addLayer(layerType);
            if (layerType.equals("Image layer")) {
                myCampaignSteps.addArtwork(layerName);
            } else
                myCampaignSteps.editTextLayer(layerName, layerFont);
        }
    }

    @When("add products to editor")
    public void addProductToEditor(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct_Catalog = SessionData.getDataTbVal(dataTable, row, "Product");
            if (!sProduct_Catalog.isEmpty()) {
                String[] prod_catalogs = sProduct_Catalog.split(";");
                for (String s : prod_catalogs) {
                    String[] prod_catalog = s.split(">");
                    campaignSteps.switchToTabOnCatalog(prod_catalog[0]);
                    campaignSteps.addProductToCampaign(prod_catalog[1]);
                }
            }
        }
        campaignSteps.clickBtnNewCampaign();
    }

    @And("get quota campaign by API")
    public void getQuotaCampaignByAPI() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        quotaCampaign = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        System.out.println(quotaCampaign);

    }

    @And("get quota product by API")
    public void getQuotaProductByAPI() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        quotaProduct = printbaseAPI.getQuotaPro(shopID, accessToken);
        System.out.println(quotaProduct);
    }


    @And("verify quota campaign by API after launch {string} campaign")
    public void verifyQuotaCampaignByAPIAfterLaunchCampaign(String sNumber) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        Integer number = Integer.parseInt(sNumber);
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaActual = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        Integer quotaExpected = quotaCampaign - number;
        System.out.println(quotaExpected);
        assertThat(quotaActual).isEqualTo(quotaCampaign - number);
    }

    @And("verify quota campaign for Sbase by API after launch {string} campaign")
    public void verifyQuotaCampaignForSbaseByAPIAfterLaunchCampaign(String sNumber) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        Integer number=Integer.parseInt(sNumber);
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaActualCamp = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        Integer quotaActualPro = printbaseAPI.getQuotaPro(shopID, accessToken);
//        Integer quotaProd = printbaseAPI.getQuotaProLimit(shopID, accessToken);
        assertThat(quotaActualCamp).isEqualTo(quotaCampaign - number);
        assertThat(quotaActualPro).isEqualTo(quotaProduct - number);
    }

    @And("click to button Create new campaign|Duplicate campaign")
    public void clickToButtonCreateNewCampaigDuplicateCampaign(List<List<String>> dataTable) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _action = SessionData.getDataTbVal(dataTable, row, "Action");
            String _sNoti = SessionData.getDataTbVal(dataTable, row, "Notication");
            switch (_action) {
                case "Create new campaign":
                    campaignSteps.clickToButton("Create new campaign");
                    break;
                case "Duplicate campaign":
                    campaignSteps.clickDuplicate();
                    break;

            }
            assertThat(quotaCampaign).isEqualTo(0);
            myCampaignSteps.getMessReachLimitQuota(_sNoti);
            myCampaignSteps.clickClosePopupNotiQuota();
        }
    }


    @And("verify quota campaign by API after reach limit quota on hourly")
    public void verifyQuotaCampaignByAPIAfterReachLimitQuotaOnHourly() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaHourLimit = printbaseAPI.getQuotaCampaignHourly(shopID, accessToken);
        String mess = "You can bulk duplicate up to" + quotaHourLimit + "campaigns per hour.";
        Integer quotaHourly = printbaseAPI.getQuotaCampaignHourly(shopID, accessToken);
        if (quotaHourly == 0) {
            myCampaignSteps.getMessLimitReachLimitQuotaHourly(shopID, accessToken, mess);
        }
    }

    @And("verify quota campaign by API after reach limit quota on daily")
    public void verifyQuotaCampaignByAPIAfterReachLimitQuotaOnDaily() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaDailyLimit = printbaseAPI.getQuotaCampaignDailyLimit(shopID, accessToken);
        String mess = "You have reached the maximum daily amount of campaigns (" + quotaDailyLimit + "campaigns/day). Please go back and continue creating your campaigns on the next day.";
        Integer quotaDaily = printbaseAPI.getQuotaCampaignHourly(shopID, accessToken);
        if (quotaDaily == 0) {
            myCampaignSteps.getMessReachLimitQuota(mess);
        }
    }

    @And("verify quota campaign for Sbase by API after reach limit quota on hourly")
    public void verifyQuotaCampaignForSbaseByAPIAfterReachLimitQuotaOnHourly() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaHourlyLimit = printbaseAPI.getQuotaCampaignHourlyLimit(shopID, accessToken);
        String mess = "You can bulk duplicate up to" + quotaHourlyLimit + "campaigns per hour.";
        Integer quotaHourly = printbaseAPI.getQuotaCampaignHourly(shopID, accessToken);
        if (quotaHourly == 0) {
            myCampaignSteps.getMessLimitReachLimitQuotaHourly(shopID, accessToken, mess);
        }
    }

    @And("verify quota campaign for Sbase by API after reach limit quota on daily")
    public void verifyQuotaCampaignForSbaseByAPIAfterReachLimitQuotaOnDaily() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaCamp = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        Integer quotaCampLimit = printbaseAPI.getQuotaProLimit(shopID, accessToken);
        String messCamp = "You have reached the maximum daily amount of campaigns (" + quotaCampLimit + "campaigns/day). Please go back and continue creating your campaigns on the next day.";
        if (quotaCamp == 0) {
            myCampaignSteps.getMessReachLimitQuota(messCamp);
        }
    }

    @And("set quota bulk duplicate per one hour {string} with api")
    public void setQuotaBulkDuplicatePerOneHourWithApi(String squota) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quota = Integer.parseInt(squota);
        myCampaignSteps.postQuotaHourlyByAPI(shopID, accessToken, quota);
    }

    @And("verify quota campaign by API after reach limit quota on daily when reach quota shopbase")
    public void verifyQuotaCampaignByAPIAfterReachLimitQuotaOnDailyWhenReachQuotaShopbase() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaProdLimit = printbaseAPI.getQuotaProLimit(shopID, accessToken);
        String messSB = "You have reached the maximum daily amount of product (" + quotaProdLimit + "products/day). Please go back and continue creating your products on the next day.";
        Integer quotaSB = printbaseAPI.getQuotaPro(shopID, accessToken);
        if (quotaSB == 0) {
            myCampaignSteps.getMessReachLimitQuotaSB(shopID, accessToken, messSB);
        }
    }

    @And("verify Custom Option after click any action as {string}")
    public void verifyCustomOptionAfterClickAction(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _custoname = SessionData.getDataTbVal(dataTable, row, "Custom Name");
            String _action = SessionData.getDataTbVal(dataTable, row, "Action");
            String _messError = SessionData.getDataTbVal(dataTable, row, "Message");
            myCampaignSteps.clickActionOnMenuCustomOption(_custoname, _action, _messError);
        }
    }

    @And("delete campaign {string} by API")
    public void deleteCampaignByAPI(String campaignName) {
        accToken = myCampaignSteps.getAccessTokenShopBase();
        Integer id = printbaseAPI.getIDCampaign(campaignName, accToken);
        myCampaignSteps.deleteCampainsByAPI(id, accToken);
    }

    @When("delete all artwork by API")
    public void deleteAllArtworkByAPI() {
        if (isPass) {
            if (accToken.isEmpty())
                accToken = myCampaignSteps.getAccessTokenShopBase();
            JsonPath listArtwork = myCampaignSteps.getArtwork(accToken);
            Object result = listArtwork.get("artworks");
            if (result != null) {
                List<Integer> artworks = listArtwork.get("artworks.id");
                for (Integer id : artworks)
                    myCampaignSteps.deleteArtworkByAPI(id, accToken);
            }
        }
    }

    @And("add new artwork as {string}")
    public void addNewArtworkAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String artwork = SessionData.getDataTbVal(dataTable, row, "Artwork");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Error message");
            if (!artwork.isEmpty()) {
                myCampaignSteps.uploadArtwork(artwork);
            }
            myCampaignSteps.addArtwork(artwork);
            if (!errorMessage.isEmpty()) {
                myCampaignSteps.verifyMessageUploadArtwork(errorMessage);
            }
        }
    }

    @And("search and verify name artwork in Artwork library as {string}")
    public void searchArtworkInArtworkLibraryAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String artwork = SessionData.getDataTbVal(dataTable, row, "Name artwork");
            if (!artwork.isEmpty()) {
                myCampaignSteps.searchArtwork(artwork);
                myCampaignSteps.verifyNameArtwork(artwork);
            }
        }
    }

    @And("delete all artwork")
    public void deleteAllArtwork() {
        while (myCampaignSteps.countArtwork() > 0) {
            int n = myCampaignSteps.countArtwork();
            for (int i = 1; i <= n; i++) {
                myCampaignSteps.deleteTheFistArtwork();

            }
            System.out.println(n);
        }
    }

    @Then("verify import campaigns status is {string}")
    public void verifyImportCampaignsStatusIs(String status) {
        myCampaignSteps.verifyImportCampaign(status);
    }

    @And("verify quota campaign of shop template after clone campaign by API")
    public void verifyQuotaCampaignOfShopTemplateAfterCloneCampaignByAPI() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaActual = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        assertThat(quotaActual).isEqualTo(quotaCampaign);
    }

    @And("verify quota campaign of shop clone after clone {string} campaign by API")
    public void verifyQuotaCampaignOfShopCloneAfterCloneCampaignByAPI(String sNumber) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        Integer number=Integer.parseInt(sNumber);
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaActual = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        Integer quotaExpected=quotaCampaign-number;
        System.out.println(quotaExpected);
        assertThat(quotaActual).isEqualTo(quotaCampaign - number);
    }

    @And("get quota campaign and quota product by API")
    public void getQuotaCampaignAndQuotaProductByAPI() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        shopID = printbaseAPI.getShopID(accessToken);
        quotaCampaign = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        System.out.println(quotaCampaign);
        quotaProduct = printbaseAPI.getQuotaPro(shopID, accessToken);
        System.out.println(quotaProduct);
    }

    @And("verify quota campaign and quota product of shop clone after clone {string} campaign by API")
    public void verifyQuotaCampaignAndQuotaProductOfShopCloneAfterCloneCampaignByAPI(String sNumber) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        Integer number=Integer.parseInt(sNumber);
        shopID = printbaseAPI.getShopID(accessToken);
        Integer quotaActualCamp = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        Integer quotaActualPro = printbaseAPI.getQuotaCampaign(shopID, accessToken);
        assertThat(quotaActualCamp).isEqualTo(quotaCampaign - number);
        assertThat(quotaActualPro).isEqualTo(quotaProduct - number);
    }

    @And("click to btn Edit from sample of custom art {string}")
    public void clickToBtnEditFromSampleOfCustomArt(String customArt) {
        if (!customArtName.isEmpty())
            customArt = customArtName;
        myCampaignSteps.clickToBtnEditFromSampleOfCustomArt(customArt);
    }

    @And("click to btn Import to store of custom art {string}")
    public void clickToBtnImportToStoreOfCustomArt(String customArt) {
        myCampaignSteps.clickToBtnImportToStoreOfCustomArt(customArt);
    }

    @And("wait launch campaign successfully")
    public void waitLaunchCampaignSuccessfully() {
        campaignSteps.waitUntilLoadingSuccess();
    }

    @When("add more products to campaign")
    public void addMoreProductsToCampaign(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            if (!sCategory.isEmpty()) {
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.addProductToCampaign(sProduct);
            }
        }
        campaignSteps.clickBtnUpdateCampaign();
    }

    @And("click icon Duplicate of campaign {string}")
    public void clickIconDuplicateOfCampaign(String campaignName) {
        commonSteps.closePopup();
        campaignSteps.clickDuplicateIcon(campaignName);
    }

    @And("click icon Bulk duplicate of campaign {string}")
    public void clickIconBulkDuplicateOfCampaign(String campaignName) {
        campaignSteps.clickBulkDuplicateIcon(campaignName);
    }

    @And("verify message when reach limit quota campaign")
    public void verifyMessageWhenReachLimitQuotaCampaign() {
        myCampaignSteps.verifyMessageWhenReachQuotaCampaign();

    }

    @And("verify message when reach limit quota campaign per hourly")
    public void verifyMessageWhenReachLimitQuotaCampaignPerHourly() {
        myCampaignSteps.verifyMessageWhenReachLimitQuotaCampaignPerHourly();
    }

    @And("verify message when shopbase reach limit quota campaign")
    public void verifyMessageWhenShopbaseReachLimitQuotaCampaign() {
        myCampaignSteps.verifyMessageWhenShopbaseReachLimitQuotaCampaign();
    }

    @And("verify shipping fee in catalog screen as {string}")
    public void verifyShippingFeeInCatalogScreenAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            String showShipping = SessionData.getDataTbVal(dataTable, row, "Show price with shipping");
            String address = SessionData.getDataTbVal(dataTable, row, "Shipping address");
            Float shippingFee = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Shipping"));
            campaignSteps.waitUntilInVisibleLoadingTable();
            boolean isShowPriceWithShipping = false;
            if (!sCategory.isEmpty()) {
                campaignSteps.switchToTabOnCatalog(sCategory);
            }
            if (!showShipping.isEmpty()) {
                isShowPriceWithShipping = Boolean.parseBoolean(showShipping);
            }
            float productPrice = catalogSteps.getBaseProductPrice(sProduct);
            catalogSteps.selectShowPriceWithShipping(isShowPriceWithShipping);
            if (isShowPriceWithShipping){
                catalogSteps.selectShippingAddress(address);
            }

            float shipping;
            shipping =printbaseAPI.getShippingFee(sCategory,sProduct, address);
            catalogSteps.verifyPriceProductWithShipping(productPrice + shipping, shippingFee);
            if (isShowPriceWithShipping) {
                catalogSteps.verifyShippingNote();
            }
            catalogSteps.verifyProcessingTime();


        }

    }

    @And("get list shipping fee by API as {string}")
    public void getListShippingFeeByAPIAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String address = SessionData.getDataTbVal(dataTable, row, "Shipping address");
            printbaseAPI.getListShippingFee(sProduct, address);

        }
    }

    @And("choose shipping zones as {string}")
    public void chooseShippingZonesAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String showShipping = SessionData.getDataTbVal(dataTable, row, "With shipping fee to");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            campaignSteps.waitUntilInVisibleLoadingTable();
            boolean isShowPriceWithShipping = false;
            if (!showShipping.isEmpty()) {
                isShowPriceWithShipping = Boolean.parseBoolean(showShipping);
            }
            if (isShowPriceWithShipping){
                catalogSteps.selectAddress(address);
            }

        }


    }

    @And("verify shipping fee in pricing of variant as {string}")
    public void verifyShippingFeeInPricingOfVariantAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sku = SessionData.getDataTbVal(dataTable, row, "Variant");
            Float shippingFee = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Shipping fee"));
            editorCampaignSteps.clickSetIndividualPriceInPricing(sProduct);
            myCampaignSteps.verifyShippingForVariant(sku, shippingFee);


        }
    }

    @And("verify number image clipart on SF as {string}")
    public void verifyNumberImageClipartOnSFAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sNumber = SessionData.getDataTbVal(dataTable, row, "Number");
            if(!sNumber.isEmpty()){
                myCampaignSteps.verifyNumberClipartOnSF(sNumber);
            }

        }
    }
}

