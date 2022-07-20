package com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign;

import com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign.EditCustomOptionsPage;
import com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign.EditorCampaignPage;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign.CampaignPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class EditorCampaignSteps extends ScenarioSteps {
    EditorCampaignPage editorCampaignPage;
    CampaignPage campaignPage;
    EditCustomOptionsPage editCustomOptionsPage;

    @Step
    public void clickBaseProduct(String productName) {
        if (!productName.isEmpty())
            editorCampaignPage.clickBaseProduct(productName);
    }

    @Step
    public void clickBaseProduct(String productName, Integer i) {
        try {
            clickBaseProduct(i);
        } catch (Exception e) {
            clickBaseProduct(productName);
        }
    }

    @Step
    public void verifyColor() {
        editorCampaignPage.verifyColor();
    }

    @Step
    public void selectAllColor() {
        editorCampaignPage.selectAllColor();
    }

    @Step
    public void clickAddColor() {
        editorCampaignPage.clickAddColor();
    }

    @Step
    public void verifyMessageTotalVariant(String message) {
        editorCampaignPage.verifyMessageTotalVariant(message);
    }

    @Step
    public void hoverColor(String color) {
        editorCampaignPage.hoverColor(color);
    }

    @Step
    public void clickaddMoreProduct() {
        editorCampaignPage.clickaddMoreProduct();
    }

    @Step
    public void verifyBaseProduct(String productName, boolean addedOrRemoved) {
        editorCampaignPage.verifyBaseProduct(productName, addedOrRemoved);
    }

    @Step
    public void addLayer(String frontOrBack, String layer) {
        if(layer.equals("Text")) {
            editorCampaignPage.addLayer(frontOrBack, "Add text");
        } else {editorCampaignPage.addLayer(frontOrBack, "Add image"); }
    }

    @Step
    public void addArtwork(String imageName) {
        if (!imageName.isEmpty())
            editorCampaignPage.addArtwork(imageName);
        campaignPage.waitUntilInVisibleLoadingTable();
    }

    @Step
    public void inputValueText(String value) {
        if (!value.isEmpty())
            editorCampaignPage.inputTitleLayerText(value);
    }

    @Step
    public void inputFontSizeText(String fontSize) {
        if (!fontSize.isEmpty()) {
            String _size;
            if (fontSize.contains(">")) {
                String[] _sFont = fontSize.split(">");
                _size = _sFont[0];
                editorCampaignPage.inputTextFont(_sFont[1]);
            } else _size = fontSize;
            editorCampaignPage.inputFontSize(_size);
        }
    }

    @Step
    public void editTextLayer(String layerValue, String layerFont) {
        inputValueText(layerValue);
        inputFontSizeText(layerFont);
    }

    @Step
    public void clickBack() {
        editorCampaignPage.clickBack();
    }

    @Step
    public void clickSetIndividualPriceInPricing(String product) {
        editorCampaignPage.clickSetIndividualPriceInPricing(product);
    }

    @Step
    public void inputSalePrice(String sVariant, String sSalePrice) {
        editorCampaignPage.inputSalePrice(sVariant, sSalePrice);
    }

    @Step
    public void inputComparePrice(String sVariant, String sCompareAtPrice) {
        editorCampaignPage.inputComparePrice(sVariant, sCompareAtPrice);
    }

    @Step
    public void inputPricePricingTab(String sVariant, String price, int i) {
        editorCampaignPage.inputPricePricingTab(sVariant, price, i);
    }

    @Step
    public void verifyPricePricingTab(String variant, String price, int i) {
        if (!price.isEmpty())
            assertThat(editorCampaignPage.checkPricePricingTab(variant, price, i)).isEqualTo(true);
    }

    @Step
    public void verifyVariantPricing(String sVariant, Boolean isShowVariant) {
        editorCampaignPage.verifyVariantPricing(sVariant, isShowVariant);
    }

    @Step
    public void verifyCostPricing(String product, String cost) {
        editorCampaignPage.verifyCostPricing(product, cost);
    }

    @Step
    public void verifyPrice(String product, String price) {
        editorCampaignPage.verifyPrice(product, price);
    }

    @Step
    public void verifyCompareAtPrice(String product, String compareAtprice) {
        editorCampaignPage.verifyCompareAtPrice(product, compareAtprice);
    }

    @Step
    public void deleteBaseProduct(String sProduct) {
        if (!sProduct.isEmpty()) {
            editorCampaignPage.deleteBaseProduct(sProduct);
            editorCampaignPage.confirmDeleteRecord();
        }
    }

    @Step
    public void clickBtnDelete() {
        editorCampaignPage.confirmDeleteRecord();
    }

    @Step
    public void clickLaunchButton() {
        int i = 0;
        while (!editorCampaignPage.isInVisibleShowWarningInPricing()) {
            i++;
            if (i > 10)
                break;
        }
        editorCampaignPage.clickLauchButton();
    }

    @Step
    public void inputLayerName(String sLayerName) {
        editorCampaignPage.inputLayerName(sLayerName);
    }

    @Step
    public void inputLocationForLayer(String value) {
        editorCampaignPage.inputLocationForLayer(value);
    }

    @Step
    public void inputSizeForLayer(String sSizeLayer) {
        editorCampaignPage.inputSizeForLayer(sSizeLayer);
    }

    @Step
    public void inputSizeForLayer(String width, String height) {
        editorCampaignPage.inputSizeForLayer(width, height);
    }

    @Step
    public void inputTextLayer(String text) {
        editorCampaignPage.inputTextLayer(text);
    }

    @Step
    public void inputColorLayer(String color) {
        editorCampaignPage.inputColorLayer(color);
    }

    @Step
    public void inputRotationForLayer(String sRotateLayer) {
        editorCampaignPage.inputRotationForLayer(sRotateLayer);
    }

    @Step
    public void inputOpacityForLayer(String sOpacityLayer) {
        editorCampaignPage.inputOpacityForLayer(sOpacityLayer);
    }

    // Custom option
    @Step
    public void inputCustomOption(String label, String value) {
        editorCampaignPage.inputTextInCustomOption(label, value);
    }

    public void selectLayer(String sLayer) {
        String[] layer_products = sLayer.split(";");
        for (String s : layer_products) {
            String[] layer_product = s.split(">");
            String layer = layer_product[0];
            String product = null;
            if (layer_product.length == 2)
                product = layer_product[1];
            editorCampaignPage.selectLayer(product, layer);
        }
    }

    @Step
    public void clickActionMenuOption(String optionName) {
        editorCampaignPage.clickActionMenuOfOption(optionName);
    }

    //click action { clone,delete,show,hide} of option in tab list Custom
    @Step
    public void clickActionCustomOption(String label, String option) {
        editorCampaignPage.clickActionCustomOption(label, option);
    }

    // Verify layer name in custom option detail
    @Step
    public void verifyLayerInCustomOptionDetail(String layerNameEx) {
        String _slayerNameAc = editorCampaignPage.getLayerCustomOptionDetail();
        assertThat(_slayerNameAc).isEqualTo(layerNameEx);
    }

    @Step
    public void verifyTextValueInCustomOptionDetail(String label, String sCustomName) {
        String _sCustomNameAc = editorCampaignPage.getCustomName(label);
        assertThat(_sCustomNameAc).isEqualTo(sCustomName);
    }

    public void verifyLabelPersonalize(String sLayerName, boolean b) {
        Boolean isPersonalize = editorCampaignPage.isCheckPersonalizeLabelForLayer(sLayerName);
        assertThat(isPersonalize).isEqualTo(b);
    }

    public void verifyCustomNameInListCustom(String sCustomName, boolean is) {
        Boolean isExit = editorCampaignPage.isExitCustomNameInListCustomOption(sCustomName);
        assertThat(isExit).isEqualTo(is);
    }

    // loading in tem editor , tab preview  and picture choice
    @Step
    public void waitUntilVisibleIconLoading(int timeout) {
        editorCampaignPage.waitUntilInvisibleIconLoading(timeout);
    }

    @Step
    public void verifyLinkProduct(boolean autoLink) {
        editorCampaignPage.verifyLinkProduct(autoLink);
    }

    @Step
    public void openDetailLayer(String layer) {
        editorCampaignPage.openDetailLayer(layer);
    }

    @Step
    public void verifyText(String text) {
        editorCampaignPage.verifyText(text);
    }

    @Step
    public void verifyFont(String font) {
        editorCampaignPage.verifyFont(font);
    }

    @Step
    public void verifyColor(String color) {
        editorCampaignPage.verifyColor(color);
    }

    @Step
    public void verifyRotation(String rotation) {
        editorCampaignPage.verifyRotation(rotation);
    }

    @Step
    public void verifyOpacity(String Opacity) {
        editorCampaignPage.verifyOpacity(Opacity);
    }

    @Step
    public void verifyLayerDisplay(String layer, boolean display) {
        editorCampaignPage.verifyLayerDisplay(layer, display);
    }

    @Step
    public void verifyLayer(String layer, String side, boolean display) {
        editorCampaignPage.verifyLayer(layer, side, display);
    }

    @Step
    public void verifyBackSide(String frontOrBack, String layer, boolean display) {
        editorCampaignPage.verifyBackSide(frontOrBack, layer, display);
    }

    @Step
    public void unlinkProduct(String product) {
        editorCampaignPage.unlinkProduct(product);
    }

    @Step
    public void reUnLinkProduct(String product) {
        editorCampaignPage.reUnLinkProduct(product);
    }

    @Step
    public void clickBtnLinkProduct() {
        if (campaignPage.isElementExist("//footer[@class='s-modal-card-foot']//button[normalize-space()='Link product']")) {
            editorCampaignPage.clickBtn("Link product");
            waitABit(1000);
        }
    }

    @Step
    public void clickActionlayer(String action) {
        editorCampaignPage.clickActionlayer(action);
    }

    // Check show noti in PSD detail layer
    @Step
    public void verifyNotiPsdDetail(Boolean isShow) {
        Boolean notiActual = editorCampaignPage.isShowNotiInPsdDetail();
        assertThat(notiActual).isEqualTo(isShow);

    }

    public void verifyWarningInPsd(String sWarning, Boolean isEffect) {
        Boolean _sWarningActual = editorCampaignPage.checkWarningShowInPsdDetail(sWarning);
        assertThat(_sWarningActual).isEqualTo(isEffect);
    }

    public void verifyNotiTooltipPsdDetai(String sLayerInPSD, String sTooltip, Boolean isEffect) {
        campaignPage.waitUntilInVisibleLoadingTable();

        Boolean _sNotiActual = editorCampaignPage.checkNotiTooltipPsdDetail(sLayerInPSD, sTooltip);
        assertThat(_sNotiActual).isEqualTo(isEffect);
    }

    @Step
    public void chooseColorForProduct(String sColor) {
        if (!sColor.isEmpty())
            editorCampaignPage.chooseColorForProduct(sColor);
    }

    @Step
    public List<String> getListProductBase() {
        List<String> listProduct = new ArrayList<String>();
        int count = countBaseProduct();
        for (int i = 1; i < count + 1; i++)
            listProduct.add(getProductBase(i));
        return listProduct;
    }

    @Step
    public String getProductBase(int i) {
        String product = editorCampaignPage.getProductBase(i);
        return product;
    }

    @Step
    public int countBaseProduct() {
        int count = editorCampaignPage.countBaseProduct();
        return count;
    }

    @Step
    public HashMap<String, Integer> getPositionBaseProductInEditor() {
        HashMap<String, Integer> product_position = new HashMap<String, Integer>();
        int countBase = countBaseProduct();
        for (int i = 0; i < countBase; i++) {
            product_position.put(getProductBase(i + 1), i + 1);
        }
        return product_position;
    }

    @Step
    public void clickBaseProduct(Integer i) {
        editorCampaignPage.clickBaseProduct(i);
    }

    @Step
    public void verifyFontSize(String size) {
        editorCampaignPage.verifyFontSize(size);
    }

    @Step
    public void verifySizeWidthHeight(String sizeWidthHeight) {
        editorCampaignPage.verifySizeWidthHeight(sizeWidthHeight);
    }

    @Step
    public void verifyLocation(String location) {
        editorCampaignPage.verifyLocation(location);
    }

    @Step
    public void clickSimilarToFrontLayer() {
        editorCampaignPage.clickSimilarToFrontLayer();
    }

    @Step
    public void selectTargetLayer(String isChoose) {
        editorCampaignPage.selectTargetLayer(isChoose);
    }

    @Step
    public void verifyNotification(String product, String notification) {
        editorCampaignPage.verifyNotification(product, notification);
    }

    @Step
    public void chooseLayerForAllProduct(String product) {
        editorCampaignPage.chooseLayerForAllProduct(product);
    }

    @Step
    public void verifyLayerSelected(String layer) {
        editorCampaignPage.verifyLayerSelected(layer);
    }

    @Step
    public void uncheckValueCustomOption() {
        editorCampaignPage.uncheckValueCustomOption();
    }

    @Step
    public void verifyCheckCustomOption(String option, boolean isCheck) {
        editorCampaignPage.verifyCheckCustomOption(option, isCheck);
    }

    @Step
    public void verifyRadioValueDefault(String value, boolean isCheck) {
        editorCampaignPage.verifyRadioValueDefault(value, isCheck);
    }

    @Step
    public void verifyDroplistDefault(String value, String isSelected) {
        editorCampaignPage.verifyDroplistDefault(value, isSelected);
    }

    @Step
    public void clickOnConditionalBtn(String option) {
        editorCampaignPage.clickOnConditionalBtn(option);
    }


    @Step
    public void clickDeleteConditionalBtn() {
        editorCampaignPage.clickBtn("Delete");
    }

    @Step
    public void confirmDelete() {
        editorCampaignPage.confirmDelete();
    }

    @Step
    public void selectConditionalLogic(String s_condition, String s_option) {
        int i_Conditional = editorCampaignPage.getSizeCondition();
        String[] listCondition = s_condition.split(";");
        String[] listOption = s_option.split(";");
        int countWhen = 1;
        int countThenShow = 1;
        for (int i = 0; i < listCondition.length; i++) {
            String[] listValue = listCondition[i].split(">");
            editorCampaignPage.selectConditionOfConditional(i_Conditional, i + 1, listValue[0]);
            editorCampaignPage.selectValueOfConditional(i_Conditional, i + 1, listValue[1]);
            while (countWhen < listCondition.length) {
                countWhen++;
                editorCampaignPage.addAnotherCondition(i_Conditional);
            }
        }
        for (int x = 0; x < listOption.length; x++) {
            editorCampaignPage.selectThenShow(i_Conditional, x + 1, listOption[x]);
            while (countThenShow < listOption.length) {
                countThenShow++;
                editorCampaignPage.addNewOption(i_Conditional);
            }
        }
    }

    @Step
    public void verifyMessage(String message) {
        editorCampaignPage.verifyMessage(message);
    }

    @Step
    public void verifyListLayerNull() {
        editorCampaignPage.verifyListLayerNull();
    }

    @Step
    public void verifyListCustomNull() {
        editorCampaignPage.verifyListCustomNull();
    }

    @Step
    public void inputPriceForBase(String sProduct, String price, int i) {
        editorCampaignPage.inputPriceForBase(sProduct, price, i);
    }

    @Step
    public void clickDeleteLayer(String layer, String sfrontOrBack) {
        editorCampaignPage.clickDeleteLayer(layer, sfrontOrBack);
    }

    @Step
    public void inputValue(String value, int i) {
        editorCampaignPage.inputValue(value, i);
    }

    @Step
    public void clickDefaultValue(int i) {
        editorCampaignPage.clickDefaultValue(i);
    }

    @Step
    public void addValue() {
        editorCampaignPage.addValue();
    }

    @Step
    public void deleteValue() {
        editorCampaignPage.deleteValue();
    }

    @Step
    public void clickOnSetIndividualPrice() {
        editorCampaignPage.clickOnSetIndividualPrice();
    }

    @Step
    public void verifyVariant(String sVariant) {
        editorCampaignPage.verifyVariant(sVariant);
    }

    @Step
    public void chooseSizeForProduct(String sSize) {
        String[] arr = sSize.split(",");
        List<String> listSizes = new ArrayList<>(Arrays.asList(arr));
        if (!sSize.isEmpty())
            for (int i = 1; i <= editorCampaignPage.getListSizeOnEditor(); i++) {
                if (listSizes.contains(editorCampaignPage.getValueSize(i)))
                    editorCampaignPage.selectSize(i);
                else editorCampaignPage.verifySize(i);
            }
    }

    public void searchArtworkInPopupArtworkLibrary(String sLayerName) {
        editorCampaignPage.searchArtworkInPopupArtworkLibrary(sLayerName);
    }

    @Step
    public void verifyIconNotiRed(String product, boolean isNoti) {
        editorCampaignPage.verifyIconNotiRed(product, isNoti);
    }

    @Step
    public void verifyNoticationOfBaseProduct(String product, String sMess) {
        assertThat(editorCampaignPage.verifyNoticationOfBaseProduct(product)).isEqualTo(sMess);
    }

    public void inputLayerTextValue(String sLayerValue) {
        editorCampaignPage.inputTitleLayerText(sLayerValue);
    }


    public void clickAddAClipartFolderLink() {
        editorCampaignPage.clickOnSearchAClipartFolderTxt();
        editorCampaignPage.clickOnAddAClipartFolderBtn();
    }

    public void verifyClipartFolder(String _sNameFolder, String _sNumber) {
        editorCampaignPage.verifyNameClipartFolder(_sNameFolder);
        editorCampaignPage.verifyNumberImageClipart(_sNumber);

    }

    public void chooseAClipartFolder() {
        editorCampaignPage.chooseAClipartFolder();
    }

    public void clickEditClipartFolderLink() {
        editorCampaignPage.clickEditClipartFolderLink();
    }

    public void chooseCamp(String sTitle) {
        editorCampaignPage.chooseCamp(sTitle);
    }

    public void searchNameClipartFolder(String sFolder) {
        editorCampaignPage.searchNameClipartFolder(sFolder);
        waitABit(3000);
    }

    public void chooseGroupForCamp(String sGroup) {
        editorCampaignPage.chooseGroupForCamp(sGroup);
    }

    public void showClipart(String sShowClipart) {
        editorCampaignPage.showClipart(sShowClipart);
    }

    public void clickTabInEditor(String tab) {
        editorCampaignPage.clickBtn("Preview");

    }

    @Step
    public void selectMainProduct(String sProduct) {
        if (!sProduct.isEmpty())
            editorCampaignPage.selectMainProductInPricing(sProduct);
    }

    @Step
    public void selectMainColor(String sColor) {
        if (!sColor.isEmpty())
            editorCampaignPage.selectMainColor(sColor);
    }

    @Step
    public void addNewOption(int indexCondi) {
        editorCampaignPage.addNewOption(indexCondi);
    }

    @Step
    public void addConditional() {
        editorCampaignPage.addConditional();
    }

    @Step
    public void addAnotherCondition(int indexCondi) {
        editorCampaignPage.addAnotherCondition(indexCondi);
    }

    @Step
    public void backToCustomOptionList() {
        editorCampaignPage.backToCustomList();
    }

    public void clickOnSaveChangeBtn() {
        editorCampaignPage.clickOnSaveChangeBtn();
    }

    public void chooseCustomOption(String sCO) {
        editorCampaignPage.chooseCustomOption(sCO);
    }

    public void clickOnEditPersonalizationBtn() {
        editorCampaignPage.clickOnEditPersonalizationBtn();
        waitABit(5000);
    }

    public void inputValueInToRadio(String sValue) {
        String[] arr = sValue.split(",");
        for (int i = 0; i < arr.length; i++) {
            editorCampaignPage.inputValueInToRadio(i + 1, arr[i]);
        }
    }

    public void setConditionalLogic(String _sCO, String _sValue, String _sCustomOption) {
        String[] value = _sValue.split(",");
        String[] customOption = _sCustomOption.split(",");
        editorCampaignPage.clickOnConditionalBtn(_sCO);
        for (int i = 0; i < value.length; i++) {
            if (i == 0) {
                editorCampaignPage.setConditional(i, value[i], customOption[i]);
            } else {
                editorCampaignPage.clickOnPlusBtn();
                editorCampaignPage.setConditional(i, value[i], customOption[i]);
            }
        }
    }

    @Step
    public void clickAddCOBelowOrAbove(String sLabel, String below) {
        editorCampaignPage.clickAddCOBelowOrAbove(sLabel, below);
    }

    // Manual design campaign
    @Step
    public void clickUseCustomeArtSevice(String type) {
        if (type.equalsIgnoreCase("manual design")) {
            editorCampaignPage.clickUseCustomeArtSevice();
        }
    }

    @Step
    public void selectOnAddMaterial() {
        editorCampaignPage.selectOnAddMaterial();
    }

    @Step
    public void clickOnAddMaterials() {
        editorCampaignPage.clickOnAddMaterial();
    }

    @Step
    public void inputFileNameOnAddMaterial(String fileName) {
        if (!fileName.isEmpty()) {
            editorCampaignPage.inputFileNameOnAddMaterials(fileName);
        }
    }

    @Step
    public void chooseFileOnAdditional(String fileName) {
        if (!fileName.isEmpty()) {
            editorCampaignPage.selectFileOnAddMaterials(fileName);
        }
    }

    @Step
    public void selectAppiliedFor(String itemName) {
        if (!itemName.isEmpty()) {
            editorCampaignPage.clickOnAppliedFor();
            editorCampaignPage.selectItemOnAppliedFor(itemName);
        }
    }

    @Step
    public void verifyPhotoGuide() {
        editorCampaignPage.verifyButtonPhotoGuide();
    }

    @Step
    public void verifyProductWillBeCustomized() {
        editorCampaignPage.verifyProductWillBeCustomized();
    }


    @Step
    public void clickOnPhotoGuide() {
        editorCampaignPage.clickOnPhotoGuide();
    }

    @Step
    public void clickAddPhotoGuide() {
        editorCampaignPage.clickonAddPhotoGuide();
    }

    @Step
    public void inputPhotoGuideName(String photoName) {
        editorCampaignPage.inputOnPhotoName(photoName);
    }

    @Step
    public void clickSavePhoto() {
        editorCampaignPage.clickOnSavePhotoGuide();
    }

    @Step
    public void verifyPhotoGuideSelected(String photoName, boolean isShow) {
        editorCampaignPage.clickOnPhotoGuide();
        editorCampaignPage.verifyPhotoGuideAfAdd(photoName, isShow);
    }

    @Step
    public void clickEditPhotoGuide() {
        editorCampaignPage.clickEditPhotoGuide();
    }

    @Step
    public void clickSaveDraftCampaign() {
        editorCampaignPage.clickSaveDraftCampaign();
    }

    @Step
    public void clickPhotoGuideOnSF(String customName) {
        editorCampaignPage.clickPhotoGuideOnSF(customName);
    }

    @Step
    public void inputDescriptionOnPG(String contentText) {
        editorCampaignPage.inputDescriptionOnPhotoGuide(contentText);
    }

    @Step
    public void uploadImageOnPhotoGuide(String imageName) {
        editorCampaignPage.clickOnSelectUploadImage();
        editorCampaignPage.clickOnUploadImage();
        editorCampaignPage.uploadImageOnPhotoGuide(imageName);
        editorCampaignPage.clickOnSaveUploadImage();
    }


    @Step
    public void selectBaseProduct(String baseProduct) {
        campaignPage.selectBaseProduct(baseProduct);
    }

    @Step
    public void verifyProductMain(String productMain) {
        editorCampaignPage.verifyProductMain(productMain);
    }

    @Step
    public void inputFontCustomOption(String sFont) {
        editorCampaignPage.inputFontCustomOption(sFont);
    }

    @Step
    public BufferedImage verifyImageInListProductDetailPage(String sImageAc, int imageNumber) throws IOException {
        editorCampaignPage.getImageProductDetailPageWithURL(sImageAc, imageNumber);
        BufferedImage actualImage = ImageIO.read(new File(LoadObject.getFilePath(File.separator + sImageAc + ".png")));
        return actualImage;
    }

    @Step
    public void verifyMessageErrorInPricingScreen(String message) {
        editorCampaignPage.verifyMessageErrorInPricingScreen(message);
    }

    @Step
    public void verifyButtonLaunchCamp(String statusButton) {
        editorCampaignPage.verifyButtonLauchCamp(statusButton);
    }

    @Step
    public void deleteMaterials(int materialQuantity) {
        int i = 1;
        if (i > materialQuantity) {
            editorCampaignPage.deleteMaterials(i);
            i++;
        }
    }

    @Step
    public void inputFileNameOnEditMaterial(String fileName, int numberMaterial) {
        if (!fileName.isEmpty())
            editorCampaignPage.inputFileNameOnEditMaterials(fileName, numberMaterial);
    }

    @Step
    public void chooseFileAdditionalOnEditMaterial(String fileName, int numberMaterial) {
        if (!fileName.isEmpty())
            editorCampaignPage.selectFileOnEditMaterials(fileName, numberMaterial);
    }

    @Step
    public void selectAppiliedForOnEditMaterial(String itemName, int numberMaterial) {
        if (!itemName.isEmpty()) {
            editorCampaignPage.clickAppliedForOnEditMaterials(numberMaterial);
            editorCampaignPage.selectItemOnAppliedFor(itemName);
        } else {
            editorCampaignPage.verifyItemOnAppliedFor();
        }
    }

    @Step
    public void verifyMaterialError(String nameMaterial, String fileName, String itemApplied) {
        if (nameMaterial.isEmpty())
            editorCampaignPage.verifyFileNameOnMaterial();
        if (fileName.isEmpty())
            editorCampaignPage.verifyUploadFileFieldOnMaterial();
        if (itemApplied.isEmpty())
            editorCampaignPage.verifyItemOnAppliedFor();
    }

    @Step
    public void verifyNotiPicktureChoice(String noti, boolean check) {
        editorCampaignPage.verifyNotiPicktureChoice(noti, check);
    }

    @Step
    public void verifyNotiCO(String message) {
        editorCampaignPage.verifyNotiCO(message);
    }

    @Step
    public void deleteCO(String customOption) {
        editorCampaignPage.deleteCO(customOption);
    }

    @Step
    public void clickSaveCO() {
        editorCampaignPage.clickBtnSave();
    }

    @Step
    public void clickCancelCO() {
        editorCampaignPage.clickBtn("Cancel");
    }

    @Step
    public void clickBtnConfirm() {
        editorCampaignPage.clickBtn("Confirm");
    }

    @Step
    public void addGroupLayer(String frontOrBack) {
        editorCampaignPage.addGroupLayer(frontOrBack);
    }

    @Step
    public void editNameGroup(String frontOrBack, String nameGroup, String nameGroupNew) {
        editorCampaignPage.editNameGroup(frontOrBack, nameGroup, nameGroupNew);
    }

    @Step
    public void addLayerToGroup(String frontOrBack, String layerName, String nameGroupNew) {
        editorCampaignPage.addLayerToGroup(frontOrBack, layerName, nameGroupNew);
    }

    @Step
    public void clickBtnExpand() {
        editorCampaignPage.clickBtnExpand();
    }

    @Step
    public void clickCustomizeGroup() {
        editorCampaignPage.clickCustomizeGroup();
    }

    @Step
    public void selectOptionTypeGroup(String type) {
        editorCampaignPage.selectOptionTypeGroup(type);
    }

    @Step
    public void inputLabel(String label) {
        editorCampaignPage.inputLabel(label);
    }

    @Step
    public void selectDefaultGroup(String group) {
        editorCampaignPage.selectDefaultGroup(group);
    }

    @Step
    public void editGroupLayer(String frontOrBack, String group, String action) {
        editorCampaignPage.editGroupLayer(frontOrBack, group, action);
    }

    @Step
    public void checkExitLayerOrGroup(String frontOrBack, String layer, boolean isExit) {
        editorCampaignPage.checkExitLayerOrGroup(frontOrBack, layer, isExit);
    }

    @Step
    public void clickBackCustomGroup() {
        editorCampaignPage.clickBackCustomGroup();
    }

    @Step
    public void editOption(String name, String action) {
        editorCampaignPage.editOption(name, action);
    }

    @Step
    public void verifyMessageLayer(String message) {
        editorCampaignPage.verifyMessageLayer(message);
    }

    @Step
    public void setUpPersonalization() {
        editorCampaignPage.setUpPersonalization();
    }
    @Step
    public void verifyButtonCreateCollection(String statusButton) {
        editorCampaignPage.verifyButtonCreateCollection(statusButton);

    }

    @Step
    public void verifyMessageImage(String message) {
        editorCampaignPage.verifyMessageImage(message);
    }
}

