package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.PersonalizeProductPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonalizeProductSteps extends ScenarioSteps {
    PersonalizeProductPages personalizeProductPages;

    @Step
    public void verifyScreenPersonalize() {
        personalizeProductPages.verifyScreenPersonalize();
    }

    @Step
    public void uploadImagePersonalize(String sImage,String msg) {
        personalizeProductPages.uploadImagePersonalize(sImage, msg);
        personalizeProductPages.waitForEverythingComplete();
        personalizeProductPages.waitForPageLoad();
        personalizeProductPages.waitForUntilInVisibleLoading(50);
    }

    @Step
    public void replaceImagePersonalize(String sImage) {
//        personalizeProductPages.clickTemplateActionBtn();
        personalizeProductPages.replaceImagePersonalize(sImage);
        personalizeProductPages.waitForEverythingComplete();
        personalizeProductPages.waitForPageLoad();
        personalizeProductPages.waitForUntilInVisibleLoading(50);
    }

    @Step
    public void clickAddLayerText() {
        personalizeProductPages.clickAddLayerText();
    }

    @Step
    public void addLayerImage(String sImage) {
        personalizeProductPages.addLayerImage(sImage);
        waitABit(3000);
        personalizeProductPages.waitForUntilInVisibleLoading(50);
    }

    @Step
    public void inputValueText(String sLayerValue) {
        if (!sLayerValue.isEmpty())
            personalizeProductPages.inputValueForText(sLayerValue);
    }

    @Step
    public void inputFontSizeText(String sFontSize) {
        if (!sFontSize.isEmpty()) {
            String _size;
            if (sFontSize.contains(">")) {
                String _sFont[] = sFontSize.split(">");
                _size = _sFont[0];
                personalizeProductPages.inputFont(_sFont[1]);
            } else _size = sFontSize;
            personalizeProductPages.inputSizeOfFont(_size);
        }
    }

    @Step
    public void clickOpenLayerDetail(String sLayerName) {
        personalizeProductPages.clickOpenLayerDetail(sLayerName);
    }

    @Step
    public void verifyBtnEnable(String s, boolean b) {
        personalizeProductPages.verifyBtnEnable(s, b);
    }

    @Step
    public void clickActionToolBar(String btnName) {
        personalizeProductPages.waitForUntilInVisibleLoading(20);
        personalizeProductPages.clickActionToolBar(btnName);
        System.out.println("click create");
        personalizeProductPages.clickButtonConfirmInPopup("Create");;
    }

    @Step
    public int countListLayer() {
        return personalizeProductPages.countListLayer();
    }

    @Step
    public void verifyHelpLinkInTabPersonalize(String s) {
        String getText = personalizeProductPages.getTextOfHelpLinkPersonlize();
        assertThat(getText).isEqualToIgnoringCase(s);
    }

    @Step
    public void verifyTagBetaPackage() {
        personalizeProductPages.verifyTagBetaPackage();
    }

    @Step
    public void clickActionToolBarFromProduct(String btnName) {
        personalizeProductPages.waitForUntilInVisibleLoading(50);
        personalizeProductPages.clickActionToolBar(btnName);
        System.out.println("check loading");
        personalizeProductPages.waitForUntilInVisibleLoading(50);
    }

    @Step
    public void clickActionToolBarFromOrder(String btnName) {
        personalizeProductPages.waitForUntilInVisibleLoading(50);
        personalizeProductPages.clickActionToolBarFromOrder(btnName);
        System.out.println("check loading");
        personalizeProductPages.waitForUntilInVisibleLoading(50);
    }

    @Step
    public void clickAndVerifyToggleOnPopupCreatePrintFile(boolean statusToggel) {
        if (statusToggel)
            personalizeProductPages.clickAndVerifyToggleOnPopupCreatePrintFile(statusToggel);
    }

    @Step
    public void verifyPopUpPrintFileNoGenerate() {
        personalizeProductPages.verifyPopUpPrintFileNoGenerate();
    }

    @Step
    public void selectRadioThenVerifyPopUpPrintfileGenerate(String status) {
        if(status.equals("Yes")) {
            personalizeProductPages.selectRadioThenVerifyPopUpPrintfileGenerate(1,status);
        } else {personalizeProductPages.selectRadioThenVerifyPopUpPrintfileGenerate(2,status);}
    }

    @Step
    public void clickButtonConfirmInPopup() {
        personalizeProductPages.clickButtonConfirmInPopup("Create");
    }

    @Step
    public void waitForUntilInVisibleLoading() {
        personalizeProductPages.waitForUntilInVisibleLoading(50);
    }

    @Step
    public void verifyProductAfterGenerated() {personalizeProductPages.verifyProductAfterGenerated();}

    @Step
    public void verifyPopUpPrintFileGenerateOnOrder() {personalizeProductPages.verifyPopUpPrintFileGenerateInOrder();}

    @Step
    public void verifyPopUpPrintFileNoGenerateInOrder () {
        personalizeProductPages.verifyPopUpPrintFileNoGenerateInOrder();
    }

    @Step
    public void verifyOrderAfterGenerate() {
        personalizeProductPages.verifyOrderAfterGenerated();
    }

    @Step
    public void selectRadioOnPopupPFOrder(String status) {
        if(status.equals("Yes")) {
            personalizeProductPages.selectRadioThenVerifyPopUpPrintfileGenerate(1,status);
        } else {personalizeProductPages.selectRadioThenVerifyPopUpPrintfileGenerate(3,status);}
    }

    @Step
    public void verifyNotiPopupCreatePrintfileAfterConfigGenerate(String noti) {
        personalizeProductPages.verifyNotiPopupCreatePrintfileAfterConfigGenerate(noti);
    }

    @Step
    public void verifyBtnAddPrintFile(){
        personalizeProductPages.verifyBtnAddPrintFile();
    }
    @Step
    public void editCustomOption(String label){
        personalizeProductPages.editCutomOption(label);
    }
}

