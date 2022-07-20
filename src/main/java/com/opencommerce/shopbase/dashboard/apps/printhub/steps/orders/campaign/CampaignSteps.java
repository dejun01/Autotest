package com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign;

import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign.CampaignPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CampaignSteps extends ScenarioSteps {
    CampaignPage campaignPage;

    @Step
    public void vefifyVariantPriceCampaignDetailInDashBoard(String sSale, String sku) {
        String _sSalePrice = campaignPage.getVariantPriceCampaignDetail(sku);
        assertThat(_sSalePrice).isEqualTo(sSale);
    }

    public void selectCampaigns(String campaignName) {
        campaignPage.selectCheckbox(campaignName);
    }


    public void clickActionbtn() {
        campaignPage.clickActionBtn();

    }

    public void selectAllCampaign() {
        campaignPage.selectAllCampaign();
    }


    public void expandBaseProductBlock(String baseProduct, boolean status) {
        campaignPage.expandBaseProductBlock(baseProduct, status);
    }

    public float getCompareAtPriceVariant(String baseProduct, String variant, float targetDiscount) {
        return campaignPage.getCompareAtPriceVariant(baseProduct, variant, targetDiscount);
    }

    public float getSalePriceVariant(String baseProduct, String variant) {
        return campaignPage.getSalePriceVariant(baseProduct, variant);
    }

    public void searchKeyword(String keyword) {
        campaignPage.searchKeyword(keyword);
        campaignPage.waitUntilListTableVisible();
    }

    public void verifyResultSearch(String keyword) {
        campaignPage.verifyResultSearch(keyword);
    }

    @Step
    public void addProductToCampaign(String product) {
        String[] _sProducts = product.split(",");
        for (String _sPro : _sProducts)
            campaignPage.addProductToCampaign(_sPro.trim());
    }

    public void removeProductToCampaign(String product) {
        campaignPage.removeProductToCampaign(product);
    }

    public void clickBtnNewCampaign() {
        campaignPage.clickBtnNewCampaign();
        campaignPage.waitForPageLoad();
        campaignPage.waitUntilInvisibleLoading(50);
    }


    public void clickToButton(String btnName) {
        campaignPage.clickButton(btnName);
        campaignPage.waitForPageLoad();
        campaignPage.waitForEverythingComplete();
    }


    @Step
    public void addArtWorks(String artworks) {
        String[] arts = artworks.split(",");
        for (String art : arts) {
            addAnArtWork(art);
        }
    }

    @Step
    public void addAnArtWork(String sArtwork) {
        if (sArtwork.contains(">")) {
            String artWorkAndTab[] = sArtwork.split(">");
            if (artWorkAndTab[1].equalsIgnoreCase("all")) {
                inputArtworkforCampaign(artWorkAndTab[0], "Front");
                inputArtworkforCampaign(artWorkAndTab[0], "Back");
            }
            if (artWorkAndTab[1].equalsIgnoreCase("Front"))
                inputArtworkforCampaign(artWorkAndTab[0], "Front");
            if (artWorkAndTab[1].equalsIgnoreCase("Back"))
                inputArtworkforCampaign(artWorkAndTab[0], "Back");
        } else {
            campaignPage.addAnArtWork(sArtwork);
            if (campaignPage.uploadFailed()) {
                campaignPage.addAnArtWork(sArtwork);
            }
        }
    }

    @Step
    public void inputArtworkforCampaign(String artwork, String front_back) {
        if (front_back.equalsIgnoreCase("") || front_back.equalsIgnoreCase("Front"))
            campaignPage.clickButtonFrontInDesign();
        else
            campaignPage.clickButtonBackDesign();
        campaignPage.addAnArtWork(artwork);
    }

    public void selectColors(String sProducts, List<String> sColors) {
        campaignPage.selectColorProduct(sProducts, sColors);
        campaignPage.clickLinkTextWithLabel("Colors");
    }

    public void selectSize(String sProducts, List<String> sSizes) {
        campaignPage.selectSizeProduct(sProducts, sSizes);
    }

    public void clickBtnPreview() {
        campaignPage.clickBtn("Preview");
        campaignPage.waitUntilInvisibleLoadingPBase(30);
    }

    public void clickBtnContinue() {
        campaignPage.clickButton("Continue");
        campaignPage.waitForEverythingComplete();
    }

    public void enterTitle(String sTitle) {
        if (!sTitle.isEmpty()) {
            waitABit(3000);
            campaignPage.scrollToElement("((//input[@id=\"Title\" or @type=\"Title\" or @placeholder=\"Title\" or contains(text(),\"Title\") or preceding-sibling::*[text()[normalize-space()=\"Title\"]] or preceding::*[text()[normalize-space()=\"Title\"]] or @name=\"Title\"or preceding-sibling::*[text()[normalize-space()=\"Title\"]]])[1]|(//*[descendant-or-self::*[text()=\"Title\"]]/following-sibling::*//input)[1])[1]");
            campaignPage.enterInputFieldWithLabel("Title", sTitle);
        }

    }
    public void selectIncludeProductDetailInCampaignDescription(String sIsIncludeProductDetails) {
        if (!sIsIncludeProductDetails.isEmpty())
            campaignPage.checkCheckboxWithLabel("Include product details in campaign description", 1, Boolean.parseBoolean(sIsIncludeProductDetails));
    }

    public void enterDescription(String sDescription) {
        if (!sDescription.isEmpty())
            campaignPage.enterDescription(sDescription);
    }

    public void enterTags(String sTag) {
        campaignPage.enterTags(sTag);
    }

    public void enterSalePrice(String sSalePrice, String sProduct) {
        if (!sSalePrice.isEmpty())
            campaignPage.enterSalePrice(sSalePrice, sProduct);
    }

    public void inputSalePrice(String product, String variant, String sSalePrice) {
        if (!sSalePrice.isEmpty())
            campaignPage.inputSalePrice(product, variant, sSalePrice);
    }

    public void inputComparePrice(String product, String variant, String comparePrice) {
        if (!comparePrice.isEmpty())
            campaignPage.inputComparePrice(product, variant, comparePrice);
    }

    public void enterCompareAtPrice(String sCompareAtPrice, String sProduct) {
        if (!sCompareAtPrice.isEmpty())
            campaignPage.enterCompareSalePrice(sCompareAtPrice, sProduct);
    }

    public void editDesigns(String designslist) {
        if (!designslist.isEmpty()) {
            String designs[] = designslist.split(",");
            for (String design : designs) {
                campaignPage.editDesign(design);
            }
        }
    }

    public void clickStep(String label) {
        waitABit(5000);
        campaignPage.clickStep(label);
    }

    public void clickBtnLaunch() {
        campaignPage.clickBtn("Launch");
    }

    public void clickAddMoreProduct() {
        if (!campaignPage.isShowAddMoreProduct()) {
            campaignPage.clickButton("Add more products");
        }

    }

    public void clickUpdateToCampaign() {

        campaignPage.clickButton("Update campaign");
//        campaignPage.waitUntilImageLoadingSuccess();
    }


    @Step
    public String getStatusCampaign(String campaignName) {

        return campaignPage.getStatusCamp(campaignName);
    }

    @Step
    public void verifyProductAndStatus(String campaignName, String status) {
        assertThat(isExistProduct(campaignName)).isEqualTo(true);
        int i = 1;
        if (!campaignPage.getStatusCamp(campaignName).equals(status) && status.equals("Available")) {
            while (!campaignPage.getStatusCamp(campaignName).equals("AVAILABLE")) {
                campaignPage.refreshPage();
                campaignPage.waitUntilListTableVisible();
                waitABit(10000);
                i++;
                System.out.println(" lan " + i);
                if (i == 15)
                    break;
            }
        }
        verifyStatusCampaign(campaignName, status);
    }

    @Step
    public void verifyStatusCampaign(String campaignName, String status) {
        waitUntilInvisibleLaunching();
        if (status.equalsIgnoreCase("Live")) {
            assertThat(campaignPage.isCampaignLive(campaignName)).isEqualTo(true);
        } else assertThat(campaignPage.getStatusCamp(campaignName)).isEqualToIgnoringCase(status);
    }

    @Step
    public void waitUntilInvisibleLaunching() {
        campaignPage.waitUntilInvisibleLaunching();
    }

    @Step
    public void verifyStatusCampaign1(String campaignName, String status) {
        int i = 1;
        while (campaignPage.getStatusCamp(campaignName).equals("launching")) {
            campaignPage.refreshPage();
            campaignPage.waitUntilListTableVisible();
            waitABit(5000);
            i++;
            System.out.println(" laanf " + i);
            if (i == 5)
                break;
        }
        assertThat(campaignPage.getStatusCampaign(campaignName)).isEqualTo(status);
    }

    @Step
    public void refreshPage() {
        campaignPage.refreshPage();
        campaignPage.waitForEverythingComplete(50);
    }

    public void verifyTabInEditCampaign(String nameTab) {
        campaignPage.verifyTabActive(nameTab);
    }


    public int countBaseProduct() {
        return campaignPage.countBaseProduct();

    }

    public int countColorSelected(int i) {
        return campaignPage.countColorSelected(i);
    }

    public int countSizeSelected(int i) {
        return campaignPage.countSizeSeleted(i);
    }


    public void verifyBtnDuplicateEnable(String isEnableDuplicate, int index) {
        if (!isEnableDuplicate.isEmpty()) {
            campaignPage.verifyBtnDuplicateEnable(Boolean.parseBoolean(isEnableDuplicate), index);
        }
    }

    public void verifyBtnBulkDuplicateEnable(String isEnableBulkDuplicate, int index) {
        if (!isEnableBulkDuplicate.isEmpty()) {
            campaignPage.verifyBtnBulkDuplicateEnable(Boolean.parseBoolean(isEnableBulkDuplicate), index);


        }
    }

    @Step
    public void clickBulkDuplicateIcon(String campaignName) {
        campaignPage.clickBulkDuplicateIcon(campaignName);
    }

    public void clickDuplicateIcon(String campaignName) {
        campaignPage.clickDuplicateIcon(campaignName);
    }

    public void clickEditIcon(int index) {
        campaignPage.clickEditIcon(index);
    }

    @Step
    public void addArtWorkForBulkDuplicate(String artworks) {
        if (!artworks.isEmpty()) {
            String[] artworkList = artworks.split(",");
            for (String art : artworkList)
                campaignPage.addArtWorkForBulk(art);

        }
    }

    @Step
    public int countVariantInApp() {
        return campaignPage.countVariantInApp();
    }

    @Step
    public void confirmDuplicateCampaign() {
        campaignPage.clickDuplicate();
        campaignPage.waitForEverythingComplete(10);
    }

    @Step
    public void selectBaseProduct(String sProduct) {
        campaignPage.selectBaseProduct(sProduct);
    }

    @Step
    public void logInfor(String s) {
    }

    public void waitUntilLoadingSuccess() {
        campaignPage.waitUntilInvisibleLoading(50);
    }

    public void verifyDescription(String descriptions) {
        if (!descriptions.isEmpty()) {
            campaignPage.verifyDescription(descriptions);
        }
    }

    @Step
    public void closeArtworkLibraryPopup() {
        if (campaignPage.isShowArtworkLibraryPopup()) {
            campaignPage.closeArtworkLibraryPopup();
        }
    }

    public void clickBtnViewOnStore() {
        campaignPage.clickBtnViewOnStore();
    }

    public void switchNewTabOnBrowser() {
        campaignPage.switchToNewTabOnBrowser();
    }


    public void waitUntilListTableVisible() {
        campaignPage.waitUntilListTableVisible();
    }

    public void clickCampaignName(String campaignName) {
        campaignPage.clickCampaignName(campaignName);
    }

    public void waitUntilDisplayProductDetailPage(String campaignName) {
        campaignPage.waitUntilDisplayProductDetailPage(campaignName);
    }

    public void verifySKU(String sku) {
        campaignPage.verifySKU(sku);
    }

    public void openProductDetailOnStorefront() {
        campaignPage.openProductDetailOnStorefront();
    }

    public int countVariantOnStore() {
        return campaignPage.countVariantOnStore();
    }

    public void verifyShowActionTable() {
        campaignPage.verifyActionTable();
    }

    public void clickDeleteSelectedCampaignBtn() {
        campaignPage.clickDeleteSelectedCampaignBtn();
    }

    public void confirmDelete() {
        campaignPage.clickBtn("Delete", 1);
    }

    public void clickMakeCampaignAvailableBtn() {
        campaignPage.clickMakeCampaignAvailableBtn();
    }

    public void confirmAction(String btnName) {
        campaignPage.clickBtn(btnName, 1);
    }

    public void clickMakeCampaignUnavailableBtn() {
        campaignPage.clickMakeCampaignUnavailableBtn();
    }

    @Step
    public void switchToTabOnCatalog(String sCategory) {
        campaignPage.switchToTabOnCatalog(sCategory);
        waitUntilInVisibleLoadingTable();
    }

    public void switchToTabOnCampaignsPage(String nameTab) {
        campaignPage.switchToTabOnCampaignsPage(nameTab);
    }

    public void verifyShowCampaign(String campaignName, boolean bol) {
        campaignPage.verifyShowCampaign(campaignName, bol);
    }

    public void verifyStatusCampaign(String labelName) {
        campaignPage.verifyStatusCampaign(labelName);
    }

    public void verifyCampaignListNull() {
        campaignPage.verifyCampaignListNull();
    }

    public void verifyShowMessage(String message) {
        campaignPage.verifyShowMessage(message);
    }

    public boolean isExistCampaignListNull() {
        return campaignPage.verifyCampaignListNull();
    }

    public void enterProvideNameForNewCampaign(String newName) {
        campaignPage.enterProvideNameForNewCampaign(newName);
    }

    public void clickBaseProduct(String sProduct, String colorsSelected) {
        String[] colorSelected = colorsSelected.split(",");
        List<String> listColorSelected = new ArrayList<>(Arrays.asList(colorSelected));
        List<String> listColorSelectedActual = campaignPage.getListSelectedColors(sProduct);
        Assert.assertEquals(listColorSelected, listColorSelectedActual);
    }

    public void verifySelectedColors(String sProduct, List<String> listColorSelected) {
        List<String> listColorSelectedActual = campaignPage.getSelectedColors(sProduct);
        Assert.assertEquals(listColorSelected, listColorSelectedActual);
    }

    public void verifySelectedSizes(String sProduct, List<String> listSizeSelected) {
        List<String> listSizeSelectedActual = campaignPage.getListSizeSelected(sProduct);
        Assert.assertEquals(listSizeSelected, listSizeSelectedActual);
    }

    public float getSalePriceProduct(String baseProduct) {
        return campaignPage.getSalePriceProduct(baseProduct);
    }

    public void waitForLaunchCampaignEnabled() {
        campaignPage.waitForLaunchCampaignEnabled();
    }

    public boolean isExistActionTable() {
        return campaignPage.isExistActionTable();
    }

    public boolean isExistProduct(String campaignName) {
        return campaignPage.isExistProduct(campaignName);
    }

    public void verifyShowSizeChartBlock() {
        campaignPage.verifyShowSizeChartBlock();
    }

    public void clickEnableSizeChartBtn() {
        campaignPage.clickEnableSizeChartBtn();
    }

    public void clickDisableSizeChartBtn() {
        campaignPage.clickDisableSizeChartBtn();
    }

    public void verifyOrdinalNumbersOfCampaign(String campaignName, String ordinalNumbers) {
        if (!ordinalNumbers.isEmpty()) {
            campaignPage.verifyOrdinalNumbersOfCampaign(campaignName, ordinalNumbers);
        }
    }

    public void selectFilePSD(String arworkName) {
        campaignPage.selectFilePsd(arworkName);
    }

    public void enterCustomOption2(String nameGirl2) {
        campaignPage.enterInputFieldWithLabel("Name girl 2", nameGirl2);
    }

    @Step
    public void clickBtnUpdateCampaign() {
        campaignPage.clickButton("Update campaign");
        campaignPage.waitUntilInvisibleLoadingPBase(3);
    }

    @Step
    public void verifyShowOption1(String option1) {
        campaignPage.verifyShowOption1(option1);
    }

    @Step
    public void verifyShowOption2(String option2) {
        campaignPage.verifyShowOption2(option2);
    }

    @Step
    public void clickBtnLaunchCampaigns() {
        campaignPage.waitForProcessingInVisible();
        waitForLaunchCampaignEnabled();
        campaignPage.clickOnElementByJs("//main[@id=\"bulk-campaign\"]//button");

    }

    @Step
    public void waitUntilInVisibleLoadingTable() {
        campaignPage.waitUntilInVisibleLoadingTable();
    }

    @Step
    public void clickSetIndividualPriceInPricing(String product) {
        campaignPage.clickSetIndividualPriceInPricing(product);
    }

    @Step
    public void searchCampaign(String nameCampaign) {
        campaignPage.searchCampaign(nameCampaign);
    }

    @Step
    public void searchCampaignOnStorfont(String nameCampaign) {
        campaignPage.searchCampaignOnStorfont(nameCampaign);
    }

    @Step
    public void selectCampaignOnStorefront(String nameCampaign) {
        campaignPage.selectCampaignOnStorefront(nameCampaign);
    }

    @Step
    public void selectCampaign(String nameCampaign) {
        campaignPage.selectCampaign(nameCampaign);
        campaignPage.waitForPageLoad();
        campaignPage.waitForEverythingComplete();
    }

    @Step
    public void addVariantSize(String variant) {
        campaignPage.addVariantSize(variant);
    }

    @Step
    public void removeColor() {
        campaignPage.removeColor();
    }

    @Step
    public void uncheckSearchEngine(boolean isCheck) {
        campaignPage.uncheckSearchEngine(isCheck);
    }

    @Step
    public void uncheckOnlineStore(boolean isCheck) {
        campaignPage.uncheckOnlineStore(isCheck);
    }

    @Step
    public void clickBtnSaveChanges() {
        campaignPage.clickBtn("Save changes");
    }

    @Step
    public void clickDuplicate() {
        campaignPage.clickDuplicate();
        campaignPage.waitForPageLoad();
        campaignPage.waitForEverythingComplete();
        waitABit(5000);
    }
//    @Step
//    public void clickDuplicateIcon(String action) {
//        campaignPage.clickDuplicateIcon(action);
//    }

    @Step
    public void verifyUncheckSearchEngine(boolean isCheck) {
        campaignPage.verifyUncheckSearchEngine(isCheck);
    }

    @Step
    public void verifyUncheckOnlineStore(boolean isCheck) {
        campaignPage.verifyUncheckOnlineStore(isCheck);
    }

    @Step
    public void verifyStatus(String campaignName, String status) {
        campaignPage.verifyStatus(campaignName, status);
    }

    @Step
    public void removeTag(String tag) {
        campaignPage.removeTag(tag);
    }

    @Step
    public void verifyTags(String tags) {
        if (tags.contains(",")) {
            String[] tagList = tags.split(",");
            for (String tag : tagList) {
                campaignPage.verifyTags(tag);
            }
        } else {
            campaignPage.verifyTags(tags);
        }
    }

    @Step
    public void selectAction(String action) {
        campaignPage.selectAction(action);
    }

    @Step
    public void clickAction() {
        campaignPage.clickBtn("Action");
    }

    @Step
    public void verifyVariants(String variants) {
        campaignPage.verifyVariants(variants);
    }

    @Step
    public void verifyCampaignName(String campaignName) {
        campaignPage.verifyCampaignName(campaignName);
    }

    @Step
    public void verifyCampaignPrice(String price) {
        campaignPage.verifyCampaignPrice(price);
    }

    @Step
    public void verifyCampaignSize(String size) {
        campaignPage.verifyCampaignSize(size);
    }

    @Step
    public void verifyCampaignColor(String color) {
        campaignPage.verifyCampaignColor(color);
    }

    @Step
    public void verifyAndInputOptionText(String optionText) {
        if (!optionText.isEmpty()) {
            String[] optionTexts = optionText.split(";");
            for (String option : optionTexts) {
                String label;
                if (option.contains(">")) {
                    String[] text = option.split(">");
                    label = text[0];
                    campaignPage.InputOptionText(label, text[1].trim());
                } else label = option;
                verifyLabelOptionText(label);
            }
        }
    }

    private void verifyLabelOptionText(String label) {
        assertThat(campaignPage.isExistLabelOptionText(label)).isTrue();
    }

    @Step
    public void verifyAndInputOptionImage(String optionImage, Boolean isCrop) {
        if (!optionImage.isEmpty()) {
            String[] options = optionImage.split(";");
            for (String option : options) {
                String label;
                if (option.contains(">")) {
                    String[] image = option.split(">");
                    label = image[0];
                    campaignPage.InputOptionImage(label, image[1].trim(), isCrop);
                } else label = option;
                verifyLabelOptionImage(label);
            }
        }
    }

    private void verifyLabelOptionImage(String label) {
        assertThat(campaignPage.isExistLabelOptionImage(label)).isTrue();
    }

    private void verifyLabelOptionPictureChoice(String label) {
        assertThat(campaignPage.isExistLabelOptionPictureChoice(label)).isTrue();
    }

    @Step
    public void verifyAndInputOptionPictureChoice(String optionPicture) {
        if (!optionPicture.isEmpty()) {
            String[] options = optionPicture.split(";");
            for (String option : options) {
                String label;
                if (option.contains(">")) {
                    String[] image = option.split(">");
                    label = image[0];
                    campaignPage.choosePictureChoice(label, image[1].trim());
                } else label = option;
                verifyLabelOptionPictureChoice(label);
            }
        }
    }

    @Step
    public void disableSizeChart() {
        campaignPage.disableSizeChart();
    }

    @Step
    public void enableSizeChart() {
        if (campaignPage.isElementExist("//button[@class='s-button is-default']//span[normalize-space()='Enable widget on my store']")) {
            campaignPage.clickBtn("Enable widget on my store");
            waitABit(1000);
        }
    }

    @Step
    public void selectKeepArtWork(Boolean isKeep) {
        campaignPage.selectKeepArtWork(isKeep);
    }

    @Step
    public void openFileDownloaded() {
        campaignPage.getPathFileDownloaded();
    }

    @Step
    public void clickDowloadTemplate() {
        campaignPage.clickDowloadTemplate();
    }

    @Step
    public String getTemplate(String title, String accessToken) {
        return campaignPage.getTemplate(title, accessToken);
    }

    @Step
    public void downloadTemplate(String title) {
        campaignPage.downloadTemplate(title);
    }

    @Step
    public void uploadArtwork() {
        campaignPage.uploadArtwork();
    }

    @Step
    public void getAccessTokenShopBase() {
        campaignPage.getAccessTokenShopBase();
    }

    @Step
    public void chooseArtwork(String artwork) {
        campaignPage.chooseArtwork(artwork);
    }

    @Step
    public void verifyDemension(String message) {
        campaignPage.verifyDemension(message);
    }

    @Step
    public void addArtWorkForBulkDuplicate1(String artworks) {
        if (!artworks.isEmpty()) {
            String[] artworkList = artworks.split(",");
            for (String art : artworkList)
                campaignPage.addArtWorkForBulk(art);

        }
    }

    public void verifyCustomOption(String custom_name) {
        String nameOption;
        String[] listOption = custom_name.split(";");
        for (int i = 0; i < listOption.length; i++) {
            nameOption = listOption[i];
            campaignPage.verifyCustomOption(nameOption);
        }

    }

    public void selectValueOnCustomOption(String s_value) {
        String s_typeCustom;
        String s_valueCustom;
        String[] list_valueCustom = s_value.split(";");
        for (int i = 0; i < list_valueCustom.length; i++) {
            String[] valueCus = list_valueCustom[i].split(">");
            s_typeCustom = valueCus[0];
            s_valueCustom = valueCus[1];
            if (s_typeCustom.equals("Radio")) {
                campaignPage.selectValueCustomRadio(s_valueCustom);
            }

            if (s_typeCustom.equals("Image")) {
                campaignPage.selectValueCustomImg(s_valueCustom);
            }

            if (s_typeCustom.equals("Droplist")) {
                campaignPage.selectValueCustomDroplist(s_valueCustom);
            }
        }
    }

    public void clickOnSaveChangeBtn() {
        campaignPage.clickOnSaveChangeBtn();
    }

    public void clickOnUpdateBtn() {
        campaignPage.clickOnUpdateBtn();
    }

    public void clickCreateCampaign() {
        campaignPage.clickCreateCampaign();
    }

    @Step
    public void clickImportCampaignToAnotherStore() {
        campaignPage.clickImportCampaignToAnotherStore();
    }

    @Step
    public void verifyRejectReason(String reasonContent) {
        campaignPage.verifyRejectReason(reasonContent);
    }

    @Step
    public void selectCreateClipart() {
        campaignPage.selectCreatClipart();
    }

    @Step
    public void verifyNumberCampaignOnSF(String campaignName, String number) {
        campaignPage.verifyNumberCampaignOnSF(campaignName, number);
    }

    @Step
    public void clickSaveChangesOnCampDetail() {
        campaignPage.clickSaveChangeBtnOnCampDetails();
    }

    @Step
    public void verifyFileAfterDownloaded() {
        waitABit(3000);
        String a = campaignPage.getFileDownloaded();
        waitABit(3000);
        logInfor("File download: "+ a);
        assertThat(a).isNotEmpty();
    }

    @Step
    public void clickSelectMockup(String baseProduct) {
        campaignPage.clickButtonSelectMockup(baseProduct);
    }

    @Step
    public void verifyImageOnPopUPSelectMockup(String image, String per,Boolean isSelected, int imageNumber)  throws IOException {
        float percent = 1.00f;
        if (!per.isEmpty())
            percent = Float.parseFloat(per);
        campaignPage.verifyImageOnPopUpMockup(image,percent,isSelected,imageNumber);
    }

    @Step
    public void verifyCampExist(String campaignName, String status) {
        campaignPage.verifyCampExist();
        int i = 1;
        if (!campaignPage.getStatusCampFirst().equals(status) && status.equals("Available")) {
            while (!campaignPage.getStatusCampFirst().equals("AVAILABLE")) {
                campaignPage.refreshPage();
                campaignPage.waitUntilListTableVisible();
                waitABit(10000);
                i++;
                System.out.println(" lan " + i);
                if (i == 15)
                    break;
            }
        }
        verifyStatusCampaignFirst(campaignName, status);
    }

    @Step
    public void verifyStatusCampaignFirst(String campaignName, String status) {
        waitUntilInvisibleLaunching();
        if (status.equalsIgnoreCase("Live")) {
            assertThat(campaignPage.isCampaignLive(campaignName)).isEqualTo(true);
        } else assertThat(campaignPage.getStatusCampFirst()).isEqualToIgnoringCase(status);
    }

}