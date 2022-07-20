package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.EditorCampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.PersonalizeProductSteps;
import common.utilities.SessionData;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PersonalizeProductDef {
    @Steps
    PersonalizeProductSteps personalizeProductSteps;
    @Steps
    EditorCampaignSteps editorCampaignSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    ProductDetailSteps productDetailSteps;

    int layerNumber = 0;

    @When("upload image for preview or printfile")
    public void uploadImageForPreviewOrPrintfile(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String _sMsg = SessionData.getDataTbVal(dataTable, row, "Error image");
            personalizeProductSteps.verifyScreenPersonalize();
            personalizeProductSteps.uploadImagePersonalize(_sImage, _sMsg);
        }
    }

    @And("add layer personalize as{string}")
    public void addLayerPersonalizeAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sLayerType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sUpdateLayerName = SessionData.getDataTbVal(dataTable, row, "Update layer name");
            String _sLayerValue = SessionData.getDataTbVal(dataTable, row, "Layer value");
            String _sFontSize = SessionData.getDataTbVal(dataTable, row, "Font");
            String _sLocation = SessionData.getDataTbVal(dataTable, row, "Location");
            String _sSizeLayer = SessionData.getDataTbVal(dataTable, row, "Size layer");
            String _sRotateLayer = SessionData.getDataTbVal(dataTable, row, "Rotate");
            String _sOpacityLayer = SessionData.getDataTbVal(dataTable, row, "Opacity");

            switch (_sLayerType) {
                case "Image":
                    personalizeProductSteps.addLayerImage(_sLayerValue);
                    personalizeProductSteps.clickOpenLayerDetail(_sLayerName);
                    break;
                default:
                    personalizeProductSteps.clickAddLayerText();
                    personalizeProductSteps.clickOpenLayerDetail(_sLayerName);
                    editorCampaignSteps.inputValueText(_sLayerValue);
                    editorCampaignSteps.inputFontSizeText(_sFontSize);
            }
            //edit layer name
            if (!_sUpdateLayerName.isEmpty())
                editorCampaignSteps.inputLayerName(_sLayerName);
            // Config for layer
            if (!_sRotateLayer.isEmpty())
                editorCampaignSteps.inputRotationForLayer(_sRotateLayer);
            if (!_sLocation.isEmpty())
                editorCampaignSteps.inputLocationForLayer(_sLocation);
            if (!_sSizeLayer.isEmpty())
                editorCampaignSteps.inputSizeForLayer(_sSizeLayer);
            if (!_sOpacityLayer.isEmpty())
                editorCampaignSteps.inputOpacityForLayer(_sOpacityLayer);
            layerNumber++;
            editorCampaignSteps.clickBack();
        }
    }

    @And("click button {string} on toolbar")
    public void clickButtonOnToolbar(String btnName) {
        personalizeProductSteps.clickActionToolBar(btnName);
    }

    @And("verify layer in personalize product as {string}")
    public void verifyLayerInPersonalizeProductAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _slayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String sTextValue = SessionData.getDataTbVal(dataTable, row, "Text");
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String location = SessionData.getDataTbVal(dataTable, row, "Location");

            editorCampaignSteps.verifyLayer(_slayer, "", true);
            editorCampaignSteps.openDetailLayer(_slayer);
            if (!sTextValue.isEmpty()) {
                editorCampaignSteps.verifyText(sTextValue);
            }
            if (!font.isEmpty()) {
                editorCampaignSteps.verifyFont(font);
            }
            if (!location.isEmpty()) {
                editorCampaignSteps.verifyLocation(location);
            }
            if (!size.isEmpty()) {
                editorCampaignSteps.verifyFontSize(size);
            }
            if (!color.isEmpty()) {
                editorCampaignSteps.verifyColor(color);
            }
            if (!rotation.isEmpty()) {
                editorCampaignSteps.verifyRotation(rotation);
            }
            if (!opacity.isEmpty()) {
                editorCampaignSteps.verifyOpacity(opacity);
            }
            editorCampaignSteps.clickBack();
        }
    }

    @And("verify quantity of layer in editor")
    public void verifyQuantityOfLayerInEditor() {
        int layerNumberAc = personalizeProductSteps.countListLayer();
        assertThat(layerNumberAc).isEqualTo(layerNumber);
    }

    @And("verify action bar of editor personalize")
    public void verifyActionBarOfEditorPersonalizeWhen(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sToolbarBtn = SessionData.getDataTbVal(dataTable, row, "Button toolbar");
            String save_status[] = _sToolbarBtn.split(">");
            boolean is = false;
            if (save_status[1].equalsIgnoreCase("Enable"))
                is = true;
            personalizeProductSteps.verifyBtnEnable(save_status[0], is);
        }
    }

    @And("^verify screen (Preview|Printfile)$")
    public void verifyScreenPersonalize(String tab) {
        if (tab.equalsIgnoreCase("Preview"))
            personalizeProductSteps.verifyHelpLinkInTabPersonalize("How to create the Preview image");
        if (tab.equalsIgnoreCase("Printfile")) {
            personalizeProductSteps.verifyHelpLinkInTabPersonalize("How to create the Print file image");
        }
    }

    @And("click button {string} on toolbar incase product")
    public void clickButtonOnToolbarOtherIncaseProduct(String btnName) {
        personalizeProductSteps.clickActionToolBarFromProduct(btnName);
    }

    @And("click button {string} on toolbar incase order")
    public void clickButtonOnToolbarOtherIncaseOrder(String btnName) {
        personalizeProductSteps.clickActionToolBarFromOrder(btnName);
    }

    @And("click button create generate printfile")
    public void clickButtonCreateGeneratePrintfile() {
        personalizeProductSteps.clickButtonConfirmInPopup();
    }

    @Then("Add prinfile from product and turn on toogle as {string} when setting off generate print file")
    public void verifyPopupConfirmCreatePrinfileWhenSettingOffGeneratePrintFile(String statusToggle) {
        boolean _isHasOrder = true;
        boolean _isStatusToggle = false;
        if (statusToggle.equalsIgnoreCase("Yes"))
            _isStatusToggle = true;
        personalizeProductSteps.clickAndVerifyToggleOnPopupCreatePrintFile(_isStatusToggle);
        if (_isStatusToggle & _isHasOrder) {
            personalizeProductSteps.verifyNotiPopupCreatePrintfileAfterConfigGenerate("After creating print file, do you want to generate it for all unfulfilled items?");
        } else {
            personalizeProductSteps.verifyPopUpPrintFileNoGenerate();
        }
        personalizeProductSteps.clickButtonConfirmInPopup();
        System.out.println("check loading");
        personalizeProductSteps.verifyProductAfterGenerated();
    }

    @Then("Add prinfile when setting on generate print file")
    public void verifyPopupConfirmCreatePrinfileWhenSettingOnGeneratePrintFile(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sprinfileType = SessionData.getDataTbVal(dataTable, row, "PrintFile Type");
            String _sStatusGenerate = SessionData.getDataTbVal(dataTable, row, "Status generate");
            if (_sprinfileType.equals("Product No Order")) {
                personalizeProductSteps.verifyProductAfterGenerated();
            } else {
                personalizeProductSteps.selectRadioThenVerifyPopUpPrintfileGenerate(_sStatusGenerate);
                personalizeProductSteps.clickButtonConfirmInPopup();
                System.out.println("check loading");
                personalizeProductSteps.waitForUntilInVisibleLoading();
                personalizeProductSteps.verifyProductAfterGenerated();
            }
        }
    }

    @Then("Add printfile from order and turn on toogle as {string} when setting off generate")
    public void verifyOrderWhenCreatePrintfileAndOffGenerate(String _sStatusToggle) {
        Boolean _isStatusToggle = false;
        if (_sStatusToggle.equalsIgnoreCase("Yes"))
            _isStatusToggle = true;
        personalizeProductSteps.clickAndVerifyToggleOnPopupCreatePrintFile(_isStatusToggle);
        if (_sStatusToggle.equalsIgnoreCase("Yes")) {
            personalizeProductSteps.verifyPopUpPrintFileGenerateOnOrder();
        } else {
            personalizeProductSteps.verifyPopUpPrintFileNoGenerateInOrder();
        }
        personalizeProductSteps.clickButtonConfirmInPopup();
        System.out.println("check loading");
    }

    @Then("verify order when create printfile and on generate")
    public void verifyOrderWhenCreatePrintfileAndOnGenerate() {
        personalizeProductSteps.verifyPopUpPrintFileGenerateOnOrder();
        personalizeProductSteps.clickButtonConfirmInPopup();
        System.out.println("check loading");
        personalizeProductSteps.waitForUntilInVisibleLoading();
        personalizeProductSteps.verifyOrderAfterGenerate();
    }

    @And("^setup custom option for product in create printfile as \"([^\"]*)\"$")
    public void setupCustomOptionForProductInCreatePrintfileAs(String dataKey, List<List<String>> dataTable) {
        myCampaignSteps.addCustomOption();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int key = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "KEY"));
            String _sType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sFont = SessionData.getDataTbVal(dataTable, row, "Font");
            String _sPlaceholder = SessionData.getDataTbVal(dataTable, row, "Placeholder");
            String _sMaxLengt = SessionData.getDataTbVal(dataTable, row, "Max length");
            String _sDefaultValue = SessionData.getDataTbVal(dataTable, row, "Default value");
            String _sAllow = SessionData.getDataTbVal(dataTable, row, "Allow the following characters");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String isChoose = SessionData.getDataTbVal(dataTable, row, "Target layer");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String _sKey_next = SessionData.getDataTbVal(dataTable, row + 1, "KEY").toString();
            int key_next = 0;
            if (!_sKey_next.isEmpty())
                key_next = Integer.parseInt(_sKey_next);
            myCampaignSteps.selectType(_sType);
            if (!isChoose.isEmpty()) {
                editorCampaignSteps.selectTargetLayer(isChoose);
            }
            if (!_sLayer.isEmpty()) {
                editorCampaignSteps.selectLayer(_sLayer);
            }
            if (!_sLabel.isEmpty())
                myCampaignSteps.inputCustomOption("Label", _sLabel);
            else
                myCampaignSteps.inputCustomOption("Label", "Custom option");
            if (_sType.contains("Text")) {
                if (!_sFont.isEmpty()) {
                    myCampaignSteps.selectFont(_sFont);
                } else {
                    myCampaignSteps.selectFont("Raleway");
                }
                if (!_sPlaceholder.isEmpty())
                    myCampaignSteps.inputCustomOption("Placeholder", _sPlaceholder);
                if (!_sMaxLengt.isEmpty())
                    myCampaignSteps.inputCustomOption("Max length", _sMaxLengt);
                if (!_sAllow.isEmpty()) {
                    myCampaignSteps.clickAllow(_sAllow);
                }
                if (!_sDefaultValue.isEmpty())
                    myCampaignSteps.inputCustomOption("Default value ", _sDefaultValue);

            }
            if (_sType.contains("Picture")) {
                if (!_sImage.isEmpty()) {
                    myCampaignSteps.inputImage(_sImage);
                }
            }
            if (_sType.contains("Radio")) {
                if (!_sFont.isEmpty()) {
                    myCampaignSteps.selectFont(_sFont);
                } else {
                    myCampaignSteps.selectFont("Raleway");
                }
                myCampaignSteps.inputValue(value);
            }
            if (_sType.contains("Droplist")) {
                if (!_sFont.isEmpty()) {
                    myCampaignSteps.selectFont(_sFont);
                } else {
                    myCampaignSteps.selectFont("Raleway");
                }
                myCampaignSteps.inputValue(value);
            }
            if (_sType.contains("Picture choice")) {
                productDetailSteps.selectImageOnPictureChoice(value);
            }
            if (_sType.contains("Checkbox")) {
                productDetailSteps.inputValueInCheckboxConditional(value);
            }

            editorCampaignSteps.waitUntilVisibleIconLoading(30);
            myCampaignSteps.clickBack();
            if (key == key_next) {
                editorCampaignSteps.clickAddCOBelowOrAbove(_sLabel, "below");
            }
        }
    }

    @Then("Add prinfile from order when setting on generate")
    public void addPrintFileFromOrderWhenSettingOnGenerate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sprinfileType = SessionData.getDataTbVal(dataTable, row, "PrintFile Type");
            String _sStatusGenerate = SessionData.getDataTbVal(dataTable, row, "Status generate");
            if (_sprinfileType.equals("Product No Order")) {
                personalizeProductSteps.verifyOrderAfterGenerate();
            } else {
                personalizeProductSteps.selectRadioOnPopupPFOrder(_sStatusGenerate);
                personalizeProductSteps.clickButtonConfirmInPopup();
                System.out.println("check loading");
                personalizeProductSteps.waitForUntilInVisibleLoading();
                personalizeProductSteps.verifyOrderAfterGenerate();
            }
        }
    }

    @Then("config generate printfile after save printfile")
    public void configGeneratePrintfileAfterSavePrintfile(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sTurnOn = SessionData.getDataTbVal(dataTable, row, "Turn on toggle");
            String _sProductHasOrder = SessionData.getDataTbVal(dataTable, row, "Product has order");
            boolean _isHasOrder = false;
            boolean _isStatusToggle = false;
            if (_sTurnOn.equalsIgnoreCase("Yes"))
                _isStatusToggle = true;
            if (_sProductHasOrder.equalsIgnoreCase("Yes"))
                _isHasOrder = true;
            personalizeProductSteps.clickAndVerifyToggleOnPopupCreatePrintFile(_isStatusToggle);
            if (_isStatusToggle & _isHasOrder) {
                personalizeProductSteps.verifyNotiPopupCreatePrintfileAfterConfigGenerate("After creating print file, do you want to generate it for all unfulfilled items?");
            } else {
                personalizeProductSteps.verifyPopUpPrintFileNoGenerate();
            }
            personalizeProductSteps.clickButtonConfirmInPopup();
            System.out.println("check loading");
            personalizeProductSteps.verifyProductAfterGenerated();
        }
    }

    @And("verify button Add print file")
    public void verifyBtnAddPrintFile() {
        personalizeProductSteps.verifyBtnAddPrintFile();
    }

    @And("edit custom option for personalize product")
    public void editCustomOptionForPersonalizeProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()){
            String _sType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sFont = SessionData.getDataTbVal(dataTable, row, "Font");
            String _sPlaceholder = SessionData.getDataTbVal(dataTable, row, "Placeholder");
            String _sMaxLength = SessionData.getDataTbVal(dataTable, row, "Max length");
            String _sDefaultValue = SessionData.getDataTbVal(dataTable, row, "Default value");
            String _sAllow = SessionData.getDataTbVal(dataTable, row, "Allow the following characters");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String isChoose = SessionData.getDataTbVal(dataTable, row, "Target layer");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String typeClipart = SessionData.getDataTbVal(dataTable, row, "Folder or Group");
            String typeDisplayNameClipart = SessionData.getDataTbVal(dataTable, row, "Thumbnail or Clipart names");
            String value_clipart = SessionData.getDataTbVal(dataTable, row, "Value clipart");
            if (!_sLabel.isEmpty())
            personalizeProductSteps.editCustomOption(_sLabel);
            if(!_sType.isEmpty()) {
                myCampaignSteps.selectType(_sType);
            }
            if (!isChoose.isEmpty()) {
                editorCampaignSteps.selectTargetLayer(isChoose);
            }
            if (!_sLayer.isEmpty()) {
                editorCampaignSteps.selectLayer(_sLayer);
            }
            if (_sType.contains("Text")) {
                if (!_sFont.isEmpty()) {
                    myCampaignSteps.selectFont(_sFont);
                } else {
                    myCampaignSteps.selectFont("Raleway");
                }
                if (!_sPlaceholder.isEmpty())
                    myCampaignSteps.inputCustomOption("Placeholder", _sPlaceholder);
                if (!_sMaxLength.isEmpty())
                    myCampaignSteps.inputCustomOption("Max length", _sMaxLength);
                if (!_sAllow.isEmpty()) {
                    myCampaignSteps.clickAllow(_sAllow);
                }
                if (!_sDefaultValue.isEmpty())
                    myCampaignSteps.inputCustomOption("Default value ", _sDefaultValue);
            }
            if (_sType.contains("Picture") && !_sImage.isEmpty()) {
                myCampaignSteps.inputImage(_sImage);
            }
            if (_sType.contains("Radio")) {
                if (!_sFont.isEmpty()) {
                    myCampaignSteps.selectFont(_sFont);
                } else {
                    myCampaignSteps.selectFont("Raleway");
                }
                myCampaignSteps.inputValue(value);
            }
            if (_sType.contains("Droplist")) {
                if (!_sFont.isEmpty()) {
                    myCampaignSteps.selectFont(_sFont);
                } else {
                    myCampaignSteps.selectFont("Raleway");
                }
                myCampaignSteps.inputValue(value);
            }
            if (_sType.contains("Picture choice")) {
                myCampaignSteps.selectTypeClipart(typeClipart);
                myCampaignSteps.selectValueClipart(typeClipart, value_clipart);
                myCampaignSteps.selectTypeDisplayNameClipart(typeDisplayNameClipart);
            }

            editorCampaignSteps.waitUntilVisibleIconLoading(30);
            myCampaignSteps.clickBack();
//             if (key == key_next) {
//                personalizeProductSteps.editCustomOption(_sLabel);
//            }
        }
    }
}


