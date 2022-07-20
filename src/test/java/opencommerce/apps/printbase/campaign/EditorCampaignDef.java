package opencommerce.apps.printbase.campaign;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.library.ClipartFolderSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.EditorCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import static com.opencommerce.shopbase.OrderVariable.customArtName;
//import static opencommerce.apps.printhub.orders.campaign.CampaignDef.isKeepArtWork_Dup;
import static com.opencommerce.shopbase.OrderVariable.titlePhotoGuide;
import static org.assertj.core.api.Java6Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class EditorCampaignDef {
    @Steps
    ClipartFolderSteps clipartFolderSteps;
    @Steps
    EditorCampaignSteps editorCampaignSteps;
    @Steps
    CampaignSteps campaignSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    OrderSteps orderSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    CommonSteps commonSteps;


    HashMap<String, Integer> positionProduct = new HashMap<String, Integer>();
    List<String> listBaseProduct = new ArrayList<String>();

    @And("verify color mockup editor as \"([^\"]*)\"$")
    public void verifyColorMockupEditorAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String colorMockup = SessionData.getDataTbVal(dataTable, row, "Color");
            if (positionProduct.size() != 0)
                editorCampaignSteps.clickBaseProduct(positionProduct.get(product));
            else
                editorCampaignSteps.clickBaseProduct(product);
            editorCampaignSteps.hoverColor(colorMockup);
        }
    }

    @When("add more or remove products to campaign as \"([^\"]*)\"$")
    public void moveOrAddMoreProductsToCampaignAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            String _sAddMore = SessionData.getDataTbVal(dataTable, row, "Add more");
            String _sRemove = SessionData.getDataTbVal(dataTable, row, "Remove");
            // add move product for campaign
            if (_sAddMore.equalsIgnoreCase("true")) {
                editorCampaignSteps.clickaddMoreProduct();
                campaignSteps.switchToTabOnCatalog(_sCategory);
                campaignSteps.addProductToCampaign(_sProduct);
                campaignSteps.clickBtnUpdateCampaign();
            }
            //remove product in campaign
            if (_sRemove.equalsIgnoreCase("true")) {
                editorCampaignSteps.deleteBaseProduct(_sProduct);
            }
        }
    }

    @Then("verify bases product added or removed")
    public void verifyBasesProductAddedOrRemoved(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean addedOrRemoved = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Added or Removed"));
            editorCampaignSteps.verifyBaseProduct(sProduct, addedOrRemoved);
        }
    }

    @When("remove products to campaign")
    public void removeProductsToCampaign(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");

            editorCampaignSteps.clickaddMoreProduct();
            if (!sCategory.isEmpty()) {
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.removeProductToCampaign(sProduct);
            }
        }
        campaignSteps.clickBtnUpdateCampaign();
    }

    @And("^add new layer as \"([^\"]*)\"$")
    public void addNewlayerAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sLayerType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sUpdateLayerName = SessionData.getDataTbVal(dataTable, row, "Update layer name");
            String _sLayerValue = SessionData.getDataTbVal(dataTable, row, "Layer value");
            String _sFont = SessionData.getDataTbVal(dataTable, row, "Font");
            String _sLocation = SessionData.getDataTbVal(dataTable, row, "Location");
            String _sSizeLayer = SessionData.getDataTbVal(dataTable, row, "Size layer");
            String _sRotateLayer = SessionData.getDataTbVal(dataTable, row, "Rotate");
            String _sOpacityLayer = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String _sfrontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            if (positionProduct.size() != 0) {
                editorCampaignSteps.clickBaseProduct(positionProduct.get(_sProduct));
            } else {
                editorCampaignSteps.clickBaseProduct(_sProduct);
            }
            // add layery
            editorCampaignSteps.addLayer(_sfrontOrBack, _sLayerType);
            campaignSteps.waitUntilInVisibleLoadingTable();
            switch (_sLayerType) {
                case "Image":
                    editorCampaignSteps.addArtwork(_sLayerName);
                    break;
                default:
                    editorCampaignSteps.editTextLayer(_sLayerValue, _sFont);
            }
            //edit layer name
            if (!_sUpdateLayerName.isEmpty())
                editorCampaignSteps.inputLayerName(_sUpdateLayerName);
            // Config for layer
            if (!_sRotateLayer.isEmpty())
                editorCampaignSteps.inputRotationForLayer(_sRotateLayer);
            if (!_sLocation.isEmpty())
                editorCampaignSteps.inputLocationForLayer(_sLocation);
            if (!_sSizeLayer.isEmpty())
                editorCampaignSteps.inputSizeForLayer(_sSizeLayer);
            if (!_sOpacityLayer.isEmpty())
                editorCampaignSteps.inputOpacityForLayer(_sOpacityLayer);
            editorCampaignSteps.clickBack();
        }
    }

    @And("input infor to create description for campaign editor")
    public void inputInforToCreateDescriptionForCampaignEditor(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String _sDescription = SessionData.getDataTbVal(dataTable, row, "Description");
            String _sTags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String _sMainProduct = SessionData.getDataTbVal(dataTable, row, "Product main");
            String _sColor = SessionData.getDataTbVal(dataTable, row, "Color code main");
//            if (!customArtName.isEmpty())
//                _sTitle = customArtName;
            editorCampaignSteps.verifyPhotoGuide();
            campaignSteps.enterTitle(_sTitle);
            campaignSteps.enterDescription(_sDescription);
            campaignSteps.enterTags(_sTags);
            editorCampaignSteps.selectMainProduct(_sMainProduct);
            editorCampaignSteps.selectMainColor(_sColor);
        }
    }

    @And("input product price for campaign editor as \"([^\"]*)\"$")
    public void inputProductPriceForCampaignEditorAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sVariant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String _sSalePrice = SessionData.getDataTbVal(dataTable, row, "Sale price");
            String _sCompareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
//            editorCampaignSteps.verifyPricingTab();verifyPricingTa
            if (!_sProduct.isEmpty() & !_sVariant.isEmpty()) {
                editorCampaignSteps.clickSetIndividualPriceInPricing(_sProduct);
                editorCampaignSteps.inputPricePricingTab(_sVariant, _sSalePrice, 1);
                editorCampaignSteps.inputPricePricingTab(_sVariant, _sCompareAtPrice, 2);
            }
        }
    }

    @And("search product or campaign or orders \"([^\"]*)\" at list page in dashboard")
    public void searchProductOrCampaignOrOrdersAtListPageInDashboard(String _sKey) {
        if(!customArtName.isEmpty()) {
            _sKey =customArtName; }
        campaignSteps.searchKeyword(_sKey);
        int i = 1;
        while (campaignSteps.isExistCampaignListNull()) {
            campaignSteps.refreshPage();
            campaignSteps.waitUntilListTableVisible();
            campaignSteps.waitABit(5000);
            i++;
            if (i == 18)
                break;
        }
        campaignSteps.verifyResultSearch(_sKey);
    }

    @Then("verify campaign in campaign page")
    public void verifyCampaignEditorCreated(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sCampaignName = SessionData.getDataTbVal(dataTable, row, "campaign name");
            String _sStatus = SessionData.getDataTbVal(dataTable, row, "Status");
            String _sEnableDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable duplicate");
            String isEnableBulkDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable bulk duplicate");
            String ordinalNumbers = SessionData.getDataTbVal(dataTable, row, "Ordinal numbers");
            campaignSteps.verifyProductAndStatus(_sCampaignName, _sStatus);
            campaignSteps.verifyBtnDuplicateEnable(_sEnableDuplicate, 1);
            campaignSteps.verifyBtnBulkDuplicateEnable(isEnableBulkDuplicate, 1);
            campaignSteps.verifyOrdinalNumbersOfCampaign(_sCampaignName, ordinalNumbers);
        }
    }

    @And("verify product information in dashboard")
    public void verifyProductInformationInDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String isEnableDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable duplicate");
            String isEnableBulkDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable bulk duplicate");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            if (!tag.isEmpty()) {
                productDetailSteps.verifyTags(tag, true);
            }
            campaignSteps.verifyBtnDuplicateEnable(isEnableDuplicate, 1);
            campaignSteps.verifyBtnBulkDuplicateEnable(isEnableBulkDuplicate, 1);
            campaignSteps.verifyDescription(description);
        }
    }

    @And("verify detail campaign \"([^\"]*)\" dashboard")
    public void verifyDetailCampaignDashboard(String campaignName, List<List<String>> dataTable) {
        campaignSteps.clickCampaignName(campaignName);
        campaignSteps.waitUntilDisplayProductDetailPage(campaignName);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String isEnableDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable duplicate");
            String isEnableBulkDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable bulk duplicate");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            campaignSteps.verifySKU(sku);
            if (!tag.isEmpty()) {
                productDetailSteps.verifyTags(tag, true);
            }
            campaignSteps.verifyBtnDuplicateEnable(isEnableDuplicate, 1);
            campaignSteps.verifyBtnBulkDuplicateEnable(isEnableBulkDuplicate, 1);
            campaignSteps.verifyDescription(description);
        }
    }

    @And("click to button Launch campaign")
    public void clickToButtonLaunchCampaign() {
        editorCampaignSteps.clickLaunchButton();
        campaignSteps.waitUntilLoadingSuccess();
    }

    @And("^setup custom option for campaign (manual design|personalize) in editor campaign as \"([^\"]*)\"$")
    public void setupCustomOptionInEditorCampaignAs(String type, String dataKey, List<List<String>> dataTable) {
        editorCampaignSteps.clickUseCustomeArtSevice(type);
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
            String typeClipart = SessionData.getDataTbVal(dataTable, row, "Folder or Group");
            String typeDisplayNameClipart = SessionData.getDataTbVal(dataTable, row, "Thumbnail or Clipart names");
            String value_clipart = SessionData.getDataTbVal(dataTable, row, "Value clipart");
            String _sKey_next = SessionData.getDataTbVal(dataTable, row + 1, "KEY").toString();
            int key_next = 0;
            if (!_sKey_next.isEmpty())
                key_next = Integer.parseInt(_sKey_next);
            if(!_sType.isEmpty()) {
                myCampaignSteps.selectType(_sType);
            }
            if (!isChoose.isEmpty()) {
                editorCampaignSteps.selectTargetLayer(isChoose);
            }
            if (!_sLayer.isEmpty()) {
                editorCampaignSteps.selectLayer(_sLayer);
            }
            if (!_sLabel.isEmpty())
                myCampaignSteps.inputCustomOption("Label", _sLabel);
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
            editorCampaignSteps.clickSaveCO();
            myCampaignSteps.clickBack();
            if (key == key_next) {
                editorCampaignSteps.clickAddCOBelowOrAbove(_sLabel, "below");
            }
        }
    }

    @And("click action in list custom option tab as ")
    public void editLayerCustonOption(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sCustomName = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sAction = SessionData.getDataTbVal(dataTable, row, "Action");
            String layerClone = SessionData.getDataTbVal(dataTable, row, "Layer clone");
            boolean ImageOrTextField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Image or text field"));
            editorCampaignSteps.clickActionMenuOption(_sCustomName);
            editorCampaignSteps.clickActionCustomOption(_sCustomName, _sAction);
            if (_sAction.equals("Clone")) {
                editorCampaignSteps.verifyLayerInCustomOptionDetail("Please Select Layer");
                editorCampaignSteps.verifyTextValueInCustomOptionDetail("label", "Clone of " + _sCustomName);
                editorCampaignSteps.clickBack();
            } else if (_sAction.equals("Delete")) {
                editorCampaignSteps.clickBtnDelete();
                myCampaignSteps.verifyCustonField(_sCustomName, ImageOrTextField);
            } else {
                myCampaignSteps.verifyCustonField(_sCustomName, ImageOrTextField);
            }
        }
    }

    @And("add custom option for picture choice as \"([^\"]*)\"")
    public void addCustomOptionForPictureChoiceAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sLayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");

            myCampaignSteps.addCustomOption();
            myCampaignSteps.selectType("Picture choice");
            if (!_sLayer.isEmpty())
                editorCampaignSteps.selectLayer(_sLayer);
            if (!_sLabel.isEmpty())
                myCampaignSteps.inputCustomOption("Label", _sLabel);
            else
                myCampaignSteps.inputCustomOption("Label", "Custom option");

            if (!_sImage.isEmpty()) {
                myCampaignSteps.inputImage(_sImage);
            }
            editorCampaignSteps.waitUntilVisibleIconLoading(30);
            myCampaignSteps.clickBack();
        }
    }

    // Verify result after custom option
    @And("verify layer after custom option as \"([^\"]*)\"")
    public void verifyLayerAfterCustomOptionAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sLayerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sCustomName = SessionData.getDataTbVal(dataTable, row, "Custom name");
            editorCampaignSteps.clickBaseProduct(_sProduct);
            if (!_sProduct.isEmpty() & !_sLayerName.isEmpty())
                editorCampaignSteps.verifyLabelPersonalize(_sLayerName, true);
            if (!_sCustomName.isEmpty())
                editorCampaignSteps.verifyCustomNameInListCustom(_sCustomName, true);
        }
    }


    @Then("verify auto link products is \"([^\"]*)\"")
    public void verifyAutoLinkProductsIs(String check) {
        boolean check_lick = Boolean.parseBoolean(check);
        editorCampaignSteps.verifyLinkProduct(check_lick);
    }

    @Then("verify layer detail in products")
    public void verifyLayerDetailInProducts(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String location = SessionData.getDataTbVal(dataTable, row, "Location");

            editorCampaignSteps.clickBaseProduct(product);
            editorCampaignSteps.openDetailLayer(layer);
            if (!text.isEmpty()) {
                editorCampaignSteps.verifyText(text);
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

    @And("verify layer in products is display")
    public void verifyLayerInProductsIsDisplay(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            boolean display = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display"));

            editorCampaignSteps.clickBaseProduct(product);
            editorCampaignSteps.clickBack();
            editorCampaignSteps.verifyLayerDisplay(layer, display);
        }
    }

    @Then("unlink product \"([^\"]*)\"")
    public void unlinkProduct(String product) {
        editorCampaignSteps.unlinkProduct(product);
    }

    @Then("reunlink product \"([^\"]*)\"")
    public void reunlinkProduct(String product) {
        editorCampaignSteps.reUnLinkProduct(product);
        editorCampaignSteps.clickBtnLinkProduct();
    }

    @Then("edit layer to with action")
    public void editLayerToWithAction(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String sizeWidthHeight = SessionData.getDataTbVal(dataTable, row, "Size width height");
            String front = SessionData.getDataTbVal(dataTable, row, "Front");

            editorCampaignSteps.clickBaseProduct(product);
            editorCampaignSteps.openDetailLayer(layer);
            if (!front.isEmpty()) {
                myCampaignSteps.selectFont(front);
            }
            editorCampaignSteps.clickActionlayer(action);
            if (!sizeWidthHeight.isEmpty()) {
                editorCampaignSteps.verifySizeWidthHeight(sizeWidthHeight);
            }
            editorCampaignSteps.clickBack();
        }
    }

    @Then("edit detail layer")
    public void editDetailLayer(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String _sLocation = SessionData.getDataTbVal(dataTable, row, "Location");

            editorCampaignSteps.clickBaseProduct(product);
            editorCampaignSteps.openDetailLayer(layer);

            if (!color.isEmpty())
                editorCampaignSteps.inputColorLayer(color);
            if (!text.isEmpty())
                editorCampaignSteps.inputTextLayer(text);
            if (!rotation.isEmpty())
                editorCampaignSteps.inputRotationForLayer(rotation);
            if (!opacity.isEmpty())
                editorCampaignSteps.inputOpacityForLayer(opacity);
            if (!_sLocation.isEmpty())
                editorCampaignSteps.inputLocationForLayer(_sLocation);
        }
    }

    @Then("edit detail layer as \"([^\"]*)\"")
    public void editDetailLayerAsString(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer_name = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String layer_value = SessionData.getDataTbVal(dataTable, row, "Layer value");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String _sLocation = SessionData.getDataTbVal(dataTable, row, "Location");

            editorCampaignSteps.clickBaseProduct(product);
            editorCampaignSteps.openDetailLayer(layer_name);

            if (!color.isEmpty())
                editorCampaignSteps.inputColorLayer(color);
            if (!layer_value.isEmpty())
                editorCampaignSteps.inputTextLayer(layer_value);
            if (!rotation.isEmpty())
                editorCampaignSteps.inputRotationForLayer(rotation);
            if (!opacity.isEmpty())
                editorCampaignSteps.inputOpacityForLayer(opacity);
            if (!_sLocation.isEmpty())
                editorCampaignSteps.inputLocationForLayer(_sLocation);
            editorCampaignSteps.clickBack();
        }
    }


    @And("verify PSD detail when layer contains effect \"([^\"]*)\" with editor as \"([^\"]*)\"")
    public void verifyPSDDetailWhenLayerContainsEffectWithEditorAs(String _sEffect, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sLayerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLayerInPSD = SessionData.getDataTbVal(dataTable, row, "Layer in PSD");
            String _sWarning = "Live preview of the final product may not display some layers with special effects";
            String _sTooltip = "Not support Live preview for personalization on storefront";
            //_sEffect : layer text có effect hay không
            boolean _isEffect = false;
            if (_sEffect.equalsIgnoreCase("true"))
                _isEffect = true;
            editorCampaignSteps.clickBaseProduct(_sProduct);
            editorCampaignSteps.openDetailLayer(_sLayerName);
            editorCampaignSteps.verifyNotiPsdDetail(true);
            editorCampaignSteps.verifyWarningInPsd(_sWarning, _isEffect);
            if (!_sLayerInPSD.isEmpty()) {
                String[] _sLayer_Effect = _sLayerInPSD.split(",");
                for (String s : _sLayer_Effect) {
                    String layerName, effect;
                    String[] layerEffect = s.split(">");
                    layerName = layerEffect[0];
                    if (layerEffect.length == 1)
                        effect = "";
                    else effect = layerEffect[1];
                    boolean isEffect = false;
                    if (effect.equalsIgnoreCase("true")) {
                        isEffect = true;
                    }
                    editorCampaignSteps.verifyNotiTooltipPsdDetai(layerName, _sTooltip, isEffect);
                }
            }
        }
    }

    @And("choose color for product in editor Dashboard as \"([^\"]*)\"")
    public void chooseColorForProductInEditorDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sColor = SessionData.getDataTbVal(dataTable, row, "Color");
            String _sSize = SessionData.getDataTbVal(dataTable, row, "Size");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String[] listProduct = _sProduct.split(",");
            for (String baseProduct : listProduct) {
                editorCampaignSteps.clickBaseProduct(baseProduct);
                if (_sColor.equalsIgnoreCase("All")) {
                    editorCampaignSteps.clickAddColor();
                    editorCampaignSteps.selectAllColor();
                } else {
                    editorCampaignSteps.chooseColorForProduct(_sColor);
                }
                if (!_sSize.isEmpty()) {
                    String[] sizes = _sSize.split(",");
                    List<String> listSizes = new ArrayList<>(Arrays.asList(sizes));
                    campaignSteps.selectSize(baseProduct, listSizes);
                }
            }
            if (!message.isEmpty()) {
                editorCampaignSteps.verifyMessageTotalVariant(message);
            }
        }
    }

    @And("verify layer and base products editor as \"([^\"]*)\"")
    public void verifyLayerAndBaseProductEditorAs(String dataKey, List<List<String>> dataTable) {
        listBaseProduct = editorCampaignSteps.getListProductBase();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _slayer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String rotation = SessionData.getDataTbVal(dataTable, row, "Rotation");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String location = SessionData.getDataTbVal(dataTable, row, "Location");
            if (!_sProduct.isEmpty()) {
                assertThat(listBaseProduct).contains(_sProduct);
                editorCampaignSteps.clickBaseProduct(_sProduct);
            }
            if (!_slayer.isEmpty()) {
                String[] layerSide = _slayer.split(">");
                String _sLayer = null;
                if (layerSide.length == 2) {
                    _sLayer = layerSide[0];
                }
                editorCampaignSteps.openDetailLayer(_sLayer);
                if (!text.isEmpty()) {
                    editorCampaignSteps.verifyText(text);
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
            }
            editorCampaignSteps.clickBack();
        }
    }

    @And("verify config in Pricing tab of editor detail as \"([^\"]*)\"")
    public void verifyConfigInPrincingTabOfEditorDetailAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sVariant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String cost = SessionData.getDataTbVal(dataTable, row, "Cost");
            String _sSalePrice = SessionData.getDataTbVal(dataTable, row, "Sale price");
            String _sComparePrice = SessionData.getDataTbVal(dataTable, row, "Compare price");
            String _isShowVariant = SessionData.getDataTbVal(dataTable, row, "isShow");

            boolean isShowVariant = false;
            if (_isShowVariant.equalsIgnoreCase("true"))
                isShowVariant = true;
            if (!_sProduct.isEmpty() & !_sVariant.isEmpty()) {
                editorCampaignSteps.clickSetIndividualPriceInPricing(_sProduct);
                editorCampaignSteps.verifyVariantPricing(_sVariant, isShowVariant);
                if (isShowVariant) {
                    editorCampaignSteps.verifyPricePricingTab(_sVariant, _sSalePrice, 1);
                    editorCampaignSteps.verifyPricePricingTab(_sVariant, _sComparePrice, 2);
                }
            } else if (_sVariant.isEmpty()) {
                editorCampaignSteps.verifyCostPricing(_sProduct, cost);
                editorCampaignSteps.verifyPrice(_sProduct, _sSalePrice);
                editorCampaignSteps.verifyCompareAtPrice(_sProduct, _sComparePrice);
            }
        }
    }

    @And("get position base product in editor")
    public void getPositionBaseProductInEditor() {
        editorCampaignSteps.waitABit(5);
        try {
            positionProduct = editorCampaignSteps.getPositionBaseProductInEditor();
        } catch (Exception e) {
            positionProduct = editorCampaignSteps.getPositionBaseProductInEditor();
        }
        System.out.println("vi tri " + positionProduct);
    }

    @Then("Similar to front layers")
    public void similarToFrontLayers(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String frontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            boolean disable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Disable"));

            editorCampaignSteps.clickSimilarToFrontLayer();
            editorCampaignSteps.verifyBackSide(frontOrBack, layer, disable);
        }
    }

    @Then("select layer for every product")
    public void selectLayerForEveryProduct(List<List<String>> dataTable) {
        myCampaignSteps.addCustomOption();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String notification = SessionData.getDataTbVal(dataTable, row, "notification");
            String isChoose = SessionData.getDataTbVal(dataTable, row, "Target layer");
            String choose = SessionData.getDataTbVal(dataTable, row, "Choose layer for all product");
            String layerType = SessionData.getDataTbVal(dataTable, row, "Layer type");

            if (!layerType.isEmpty()) {
                myCampaignSteps.selectType(layerType);
            }
            if (!isChoose.isEmpty()) {
                editorCampaignSteps.selectTargetLayer(isChoose);
            }
            editorCampaignSteps.selectLayer(layer);
            if (!notification.isEmpty()) {
                editorCampaignSteps.verifyNotification(product, notification);
            }
            if (!choose.isEmpty()) {
                editorCampaignSteps.chooseLayerForAllProduct(product);
            }
        }
    }

    @Then("verify layer selected in all product")
    public void verifyLayerSelectedInAllProduct(List<List<String>> dataTable) {
        //myCampaignSteps.addCustomOption();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");

            editorCampaignSteps.verifyLayerSelected(layer);
        }
    }

    @Then("uncheck value target layer custom option")
    public void uncheckValueTargetLayerCustomOption() {
        editorCampaignSteps.uncheckValueCustomOption();
    }

    @Then("verify custom option on store front")
    public void verifyCustomOptionOnStoreFront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customName = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            boolean isCheck = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is check"));
            String isSelected = SessionData.getDataTbVal(dataTable, row, "Is selected");

            if (type.equals("Checkbox")) {
                editorCampaignSteps.verifyCheckCustomOption(customName, isCheck);
            }

            if (type.equals("Radio")) {
                editorCampaignSteps.verifyRadioValueDefault(value, isCheck);
            }

            if (type.equals("Droplist")) {
                productSteps.verifyDroplistInSF(customName, value, isSelected);
            }
        }
    }

    @And("input value radio")
    public void inputValueRadio(List<List<String>> dataTable) {
//        myCampaignSteps.addCustomOption();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String isDefault = SessionData.getDataTbVal(dataTable, row, "Default");
            String addMoreValue = SessionData.getDataTbVal(dataTable, row, "Add more value");
            String deleteValue = SessionData.getDataTbVal(dataTable, row, "Delete value");

            editorCampaignSteps.inputValue(value, row);
            if (!isDefault.isEmpty()) {
                editorCampaignSteps.clickDefaultValue(row);
            }
            if (!addMoreValue.isEmpty()) {
                editorCampaignSteps.addValue();
            }
            if (!deleteValue.isEmpty()) {
                editorCampaignSteps.deleteValue();
            }
        }
    }

    @And("add conditional logic as \"([^\"]*)\"")
    public void addConditionalLogicAs(String dataKey, List<List<String>> dataTable) {
//        editorCampaignSteps.clickBtnExpand();
        String s_lableName = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String option = SessionData.getDataTbVal(dataTable, row, "Option");
            String s_condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String s_option = SessionData.getDataTbVal(dataTable, row, "Then show");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            if (!message.isEmpty()) {
                editorCampaignSteps.verifyNotiCO(message);
            }
            if (s_lableName.equals(option)) {
                editorCampaignSteps.clickOnConditionalBtn(option);
                editorCampaignSteps.addConditional();
            } else {
                editorCampaignSteps.clickOnConditionalBtn(option);
            }
            if (!s_condition.isEmpty()) {
                editorCampaignSteps.selectConditionalLogic(s_condition, s_option);
            }
            s_lableName = option;
            myCampaignSteps.clickBack();
        }
    }

    @And("verify list layer and custom option null")
    public void verifyListLayerAndCustomOptionNull() {
        editorCampaignSteps.verifyListLayerNull();
        myCampaignSteps.clickCustomOption();
        editorCampaignSteps.verifyListCustomNull();
    }

    @And("verify list layer and custom option null with base {string}")
    public void verifyListLayerAndCustomOptionNullWithBase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Product");
            String[] productList = products.split(",");
            for (String product : productList) {
                editorCampaignSteps.clickBaseProduct(product, positionProduct.get(product));
                editorCampaignSteps.verifyListLayerNull();
            }
        }
        myCampaignSteps.clickCustomOption();
        editorCampaignSteps.verifyListCustomNull();
    }

    @And("duplicate campaign editor")
    public void duplicateCampaignEditor(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sCampaignName = SessionData.getDataTbVal(dataTable, row, "Campaign origin");
            String _sCampaginNew = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String _isKeepArt = SessionData.getDataTbVal(dataTable, row, "Is keep artwork");
            String _createClipartFolder = SessionData.getDataTbVal(dataTable, row, "Automatically create Clipart folder");
            boolean isKeepArtWork_Dup;
            if (_isKeepArt.equalsIgnoreCase("true"))
                isKeepArtWork_Dup = true;
            else
                isKeepArtWork_Dup = false;
            commonSteps.closePopup();
            campaignSteps.clickDuplicateIcon(_sCampaignName);
            if (!_sCampaginNew.isEmpty()) {
                campaignSteps.enterProvideNameForNewCampaign(_sCampaginNew);
            }
            campaignSteps.selectKeepArtWork(isKeepArtWork_Dup);
            if (!_createClipartFolder.isEmpty()) {
                if (_createClipartFolder.equalsIgnoreCase("true")) {
                    campaignSteps.selectCreateClipart();
                }
            }
            campaignSteps.clickDuplicate();
        }
    }

    @And("input price for base in tab pricing")
    public void inputPriceForBaseInTabPricing(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sSalePrice = SessionData.getDataTbVal(dataTable, row, "Sale price");
            String _sCompareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            if (!_sProduct.isEmpty()) {
                editorCampaignSteps.inputPriceForBase(_sProduct, _sSalePrice, 1);
                editorCampaignSteps.inputPriceForBase(_sProduct, _sCompareAtPrice, 2);
            }
        }
    }

    @And("add layer base product as {string}")
    public void addLayerBaseProductAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sLayerType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sfrontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            if (positionProduct.size() != 0)
                editorCampaignSteps.clickBaseProduct(positionProduct.get(_sProduct));
            else
                editorCampaignSteps.clickBaseProduct(_sProduct);
            // add layery
            editorCampaignSteps.addLayer(_sfrontOrBack, _sLayerType);
            campaignSteps.waitUntilInVisibleLoadingTable();
            switch (_sLayerType) {
                case "Image":
                    editorCampaignSteps.searchArtworkInPopupArtworkLibrary(_sLayerName);
                    editorCampaignSteps.addArtwork(_sLayerName);
                    break;
                default:
                    System.out.println("khong add layer");
            }
            editorCampaignSteps.clickBack();
        }
    }

    @And("delete layer editor as {string}")
    public void deleteLayerEditorAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String layer = SessionData.getDataTbVal(dataTable, row, "Layer");
            String _sfrontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            if (positionProduct.size() != 0)
                editorCampaignSteps.clickBaseProduct(positionProduct.get(_sProduct));
            else
                editorCampaignSteps.clickBaseProduct(_sProduct);

            editorCampaignSteps.clickDeleteLayer(layer, _sfrontOrBack);
        }
    }

    @Then("veriry noti  product base in editor as {string}")
    public void veriryNotiProductBaseInEditorAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String _sMess = SessionData.getDataTbVal(dataTable, row, "Message noti");
            String _isNoti = SessionData.getDataTbVal(dataTable, row, "is Noti");
            boolean isNoti = false;
            if (_isNoti.equalsIgnoreCase("true"))
                isNoti = true;
            editorCampaignSteps.verifyIconNotiRed(_sProduct, isNoti);
            if (isNoti) {
                editorCampaignSteps.verifyNoticationOfBaseProduct(_sProduct, _sMess);
            }
        }
    }

    @And("input layer and verify layer as {string}")
    public void inputLayerAndVerifyLayerAs(String dataKey, List<List<String>> dataTable) {
        editorCampaignSteps.addLayer("", "Text");
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sUpdateLayerName = SessionData.getDataTbVal(dataTable, row, "Update layer name");
            String _sLayerValue = SessionData.getDataTbVal(dataTable, row, "Layer value");
            String _sLocation = SessionData.getDataTbVal(dataTable, row, "Location");
            String _sSizeLayer = SessionData.getDataTbVal(dataTable, row, "Size layer");
            String _sRotateLayer = SessionData.getDataTbVal(dataTable, row, "Rotate");
            String _sOpacityLayer = SessionData.getDataTbVal(dataTable, row, "Opacity");
            campaignSteps.waitUntilInVisibleLoadingTable();
            editorCampaignSteps.inputLayerTextValue(_sLayerValue);
            if (_sLayerValue.isEmpty())
                myCampaignSteps.verifyNotifycation("Rotation", "Please finish this field");
            if (!_sUpdateLayerName.isEmpty())
                editorCampaignSteps.inputLayerName(_sUpdateLayerName);
            // Config for layer
            editorCampaignSteps.inputRotationForLayer(_sRotateLayer);
            if (_sRotateLayer.isEmpty())
                myCampaignSteps.verifyNotifycation("Rotation", "Please finish this field");
            if (!_sLocation.isEmpty())
                editorCampaignSteps.inputLocationForLayer(_sLocation);
            if (!_sSizeLayer.isEmpty())
                editorCampaignSteps.inputSizeForLayer(_sSizeLayer);
            else {
                editorCampaignSteps.inputSizeForLayer("0", "0");
                myCampaignSteps.verifyNotifycation("Size", "Value must be greater than or equal to 1");
            }
            if (!_sOpacityLayer.isEmpty())
                editorCampaignSteps.inputOpacityForLayer(_sOpacityLayer);
            editorCampaignSteps.clickBack();
        }
    }

    @Then("verify variant infomation of product in pricing as {string}")
    public void verifyVariantInfomationOfProductInPricingAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sVariant = SessionData.getDataTbVal(dataTable, row, "variant");
            if (!_sVariant.isEmpty())
                editorCampaignSteps.verifyVariant(_sVariant);
        }
    }

    @And("choose size for products in editor Dashboard as {string}")
    public void chooseSizeForProductsInEditorDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sSize = SessionData.getDataTbVal(dataTable, row, "Size");
            if (!_sSize.isEmpty()) {
                editorCampaignSteps.chooseSizeForProduct(_sSize);
            }
        }
    }

    @And("click to set individual price")
    public void clickToSetIndividualPrice() {
        editorCampaignSteps.clickOnSetIndividualPrice();
    }

    @And("Click Add a clipart folder link as {string}")
    public void clickAddAClipartFolderLink(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            editorCampaignSteps.clickAddAClipartFolderLink();
        }
    }

    @Then("verify infomation clipart folder as {string}")
    public void verifyInfomationClipartFolderAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sNameFolder = SessionData.getDataTbVal(dataTable, row, "Name");
            String _sNumber = SessionData.getDataTbVal(dataTable, row, "Number image");
            editorCampaignSteps.verifyClipartFolder(_sNameFolder, _sNumber);
        }
    }

    @And("setup custom option picture choice as {string}")
    public void setupCustomOptionPictureChoiceAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String _sGroup = SessionData.getDataTbVal(dataTable, row, "Group");
            myCampaignSteps.addCustomOption();
            myCampaignSteps.selectType(_sType);
            if (!_sLayer.isEmpty())
                editorCampaignSteps.selectLayer(_sLayer);
            if (!_sLabel.isEmpty())
                myCampaignSteps.inputCustomOption("Label", _sLabel);
            if (!_sFolder.isEmpty()) {
                editorCampaignSteps.searchNameClipartFolder(_sFolder);
                editorCampaignSteps.chooseAClipartFolder();
            }
            if (!_sGroup.isEmpty())
                editorCampaignSteps.chooseGroupForCamp(_sGroup);
            editorCampaignSteps.waitUntilVisibleIconLoading(30);
            editorCampaignSteps.clickSaveCO();
            myCampaignSteps.clickBack();
        }
    }

    @And("choose a clipart folder")
    public void chooseAClipartFolder() {
        editorCampaignSteps.chooseAClipartFolder();
        editorCampaignSteps.clickEditClipartFolderLink();
    }

    @And("open camp detail")
    public void openCampDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            if (!_sTitle.isEmpty())
                editorCampaignSteps.chooseCamp(_sTitle);
        }
    }

    @Then("verify information picture choice display as image Thumbnail on SF as {string}")
    public void verifyInformationPictureChoiceDisplayAsImageThumbnailOnSFAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sNameCO = SessionData.getDataTbVal(dataTable, row, "NameCO");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Name image");
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            if (!_sNameCO.isEmpty())
                productSteps.verifyNamCustomOption(_sNameCO);
            if (!_sFolder.isEmpty())
                productSteps.selectFolder(_sFolder);
            if (!_sImage.isEmpty())
                productSteps.verifyNameImageDisplatAsImageThumbnail(_sImage);
        }
    }

    @Then("verify information picture choice display as checklist on SF as {string}")
    public void verifyInformationPictureChoiceDisplayAsChecklistOnSFAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String _sNameCO = SessionData.getDataTbVal(dataTable, row, "NameCO");
            String _sImage1 = SessionData.getDataTbVal(dataTable, row, "Image1");
            String _sImage2 = SessionData.getDataTbVal(dataTable, row, "Image2");
            String _sImage3 = SessionData.getDataTbVal(dataTable, row, "Image3");
            String _sImage4 = SessionData.getDataTbVal(dataTable, row, "Image4");
            String _sImage5 = SessionData.getDataTbVal(dataTable, row, "Image5");
            if (!_sNameCO.isEmpty())
                productSteps.verifyCO(_sNameCO);
            if (!_sFolder.isEmpty())
                productSteps.selectFolder(_sFolder);
            if (!_sImage1.isEmpty())
                productSteps.verifyNameImageInFolderDisplatAsCheckList(_sImage1);
            if (!_sImage2.isEmpty())
                productSteps.verifyNameImageInGroupDisplatAsCheckList(_sImage2);
            if (!_sImage3.isEmpty())
                productSteps.verifyNameImageInGroupDisplatAsCheckList(_sImage3);
            if (!_sImage4.isEmpty())
                productSteps.verifyNameImageInGroupDisplatAsCheckList(_sImage4);
            if (!_sImage5.isEmpty())
                productSteps.verifyNameImageInGroupDisplatAsCheckList(_sImage5);
        }
    }

    @And("choose custom option picture choice as {string}")
    public void chooseCustomOptionPictureChoiceAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sNewClipart = SessionData.getDataTbVal(dataTable, row, "NewClipart");
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String _sEditClipart = SessionData.getDataTbVal(dataTable, row, "EditClipart");
            String _sDeleteImage = SessionData.getDataTbVal(dataTable, row, "DeleteImage");
            String _sShowClipart = SessionData.getDataTbVal(dataTable, row, "show cliparts");
            if (!_sNewClipart.isEmpty())
                editorCampaignSteps.clickAddAClipartFolderLink();
            if (!_sFolder.isEmpty()) {
                editorCampaignSteps.searchNameClipartFolder(_sFolder);
                editorCampaignSteps.chooseAClipartFolder();
            }
            if (!_sEditClipart.isEmpty())
                editorCampaignSteps.clickEditClipartFolderLink();
            if (!_sDeleteImage.isEmpty())
                clipartFolderSteps.clickOnDeleteIcon();
            if (!_sShowClipart.isEmpty()) {
                editorCampaignSteps.showClipart(_sShowClipart);
            }
        }
    }

    @And("choose Group for Camp as {string}")
    public void chooseGroupForCampAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sGroup = SessionData.getDataTbVal(dataTable, row, "Group");
            String _sShowClipart = SessionData.getDataTbVal(dataTable, row, "show cliparts");
            if (!_sGroup.isEmpty())
                editorCampaignSteps.chooseGroupForCamp(_sGroup);
            if (!_sShowClipart.isEmpty()) {
                editorCampaignSteps.showClipart(_sShowClipart);
            }
        }
    }

    @And("click tab {string} in editor")
    public void clickTabInEditor(String tab) {
        editorCampaignSteps.clickTabInEditor(tab);
        editorCampaignSteps.waitUntilVisibleIconLoading(20);
    }

    @And("select product main image for campaign editor as {string}")
    public void selectProductMainImageForCampaignEditorAs(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product base");
            String _sColor = SessionData.getDataTbVal(dataTable, row, "Select main color");
            if (!_sProduct.isEmpty())
                editorCampaignSteps.selectMainProduct(_sProduct);
            if (!_sColor.isEmpty())
                editorCampaignSteps.selectMainColor(_sColor);
        }
    }

    @And("select product main image for campaign editor")
    public void selectProductMainImageForCampaignEditor(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sProduct = SessionData.getDataTbVal(dataTable, row, "Product base");
            String _sColor = SessionData.getDataTbVal(dataTable, row, "Select main color");
            if (!_sProduct.isEmpty())
                editorCampaignSteps.selectMainProduct(_sProduct);
            if (!_sColor.isEmpty())
                editorCampaignSteps.selectMainColor(_sColor);
        }
    }

    @And("click Save changes button as {string}")
    public void clickSaveChangesButtonAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            editorCampaignSteps.clickOnSaveChangeBtn();
        }
    }

    @And("edit custom option")
    public void chooseCustomOptionAs(List<List<String>> dataTable) {
        editorCampaignSteps.clickBtnExpand();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sCO = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sFont = SessionData.getDataTbVal(dataTable, row, "Font");
            String _sPlaceholder = SessionData.getDataTbVal(dataTable, row, "Placeholder");
            String _sMaxLengt = SessionData.getDataTbVal(dataTable, row, "Max length");
            String _sDefaultValue = SessionData.getDataTbVal(dataTable, row, "Default value");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");

            if (!_sCO.isEmpty())
                editorCampaignSteps.chooseCustomOption(_sCO);
            if (!_sLabel.isEmpty())
                myCampaignSteps.inputCustomOption("Label", _sLabel );
            if (!_sFont.isEmpty())
                myCampaignSteps.selectFont(_sFont);
            if (!_sPlaceholder.isEmpty())
                myCampaignSteps.inputCustomOption("Placeholder", _sPlaceholder);
            if (!_sMaxLengt.isEmpty())
                myCampaignSteps.inputCustomOption("Max length", _sMaxLengt);
            if (!_sDefaultValue.isEmpty())
                myCampaignSteps.inputCustomOption("Default value ", _sDefaultValue);
            if (!_sImage.isEmpty())
                myCampaignSteps.inputImage(_sImage);

            if (action.equals("Cancel")) {
                editorCampaignSteps.clickCancelCO();
                editorCampaignSteps.clickBtnConfirm();
            } else if (action.equals("Back")) {
                myCampaignSteps.clickBack();
                editorCampaignSteps.clickBtnConfirm();
            } else if (action.equals("Close expand")) {
                editorCampaignSteps.clickBtnExpand();
                editorCampaignSteps.clickBtnConfirm();
            } else if (action.equals("Continue")) {
                campaignSteps.clickToButton("Continue");
                editorCampaignSteps.clickBtnConfirm();
            } else {
                editorCampaignSteps.clickSaveCO();
                myCampaignSteps.clickBack();
            }
        }
    }

    @And("click on Edit personalization btn")
    public void clickOnEditPersonalizationBtn() {
        editorCampaignSteps.clickOnEditPersonalizationBtn();
    }

    @And("set conditional logic")
    public void setConditionalLogic(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sCO = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String _sValue = SessionData.getDataTbVal(dataTable, row, "Value");
            String _sCustomOption = SessionData.getDataTbVal(dataTable, row, "Custom name");
            editorCampaignSteps.setConditionalLogic(_sCO, _sValue, _sCustomOption);
        }
    }

    @Then("setup custom option")
    public void setupCustomOption(List<List<String>> dataTable) {
        editorCampaignSteps.clickBtnExpand();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sValue = SessionData.getDataTbVal(dataTable, row, "Value");
            String _sFont = SessionData.getDataTbVal(dataTable, row, "Font");
            myCampaignSteps.addCustomOption();
            myCampaignSteps.selectType(_sType);
            if (!_sLayer.isEmpty())
                editorCampaignSteps.selectLayer(_sLayer);
            if (!_sLabel.isEmpty())
                myCampaignSteps.inputCustomOption("Label", _sLabel);
            if (!_sValue.isEmpty()) {
                editorCampaignSteps.inputValueInToRadio(_sValue);
            }
            if (!_sFont.isEmpty()) {
                editorCampaignSteps.inputFontCustomOption(_sFont);
            }
            editorCampaignSteps.clickSaveCO();
            myCampaignSteps.clickBack();
        }
    }

    @And("verify information campaign on Storefront as {string}")
    public void verifyInformationCampaignOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sCustomOption = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String _sValue = SessionData.getDataTbVal(dataTable, row, "Value");
            String _sIsCheck = SessionData.getDataTbVal(dataTable, row, "Is check");
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");

            if (!phoneNumber.isEmpty()) {
                productSteps.verifyPhoneNumber(phoneNumber);
            }
            if (_sType.equals("Radio") && _sValue.equals("1")) {
                productSteps.verifyTitleRadio1(_sCustomOption, _sIsCheck);
            }

            if (_sType.equals("Radio") && _sValue.equals("2")) {
                productSteps.verifyTitleRadio2(_sValue, _sIsCheck);
            }

            if (_sType.equals("Image")) {
                productSteps.verifyTitleImage(_sCustomOption);
            }

            if (_sType.equals("Droplist")) {
                productSteps.verifyTitleDroplist(_sCustomOption, _sValue);
            }

            if (_sType.equals("Checkbox")) {
                productSteps.verifyTitleCheckbox(_sCustomOption);
            }

            if (_sType.equals("Picture choice folder")) {
                productSteps.verifyTitlePictureFolder(_sCustomOption);
            }

            if (_sType.equals("Picture choice group")) {
                productSteps.verifyTitlePictureGroup(_sCustomOption, _sValue);
            }
        }
    }

    @And("select lable custom option {string}")
    public void selectLableCustomOption(String _nameCO) {
        editorCampaignSteps.chooseCustomOption(_nameCO);
    }

    @And("setup custom option picture choice")
    public void setupCustomOptionPictureChoice(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sType = SessionData.getDataTbVal(dataTable, row, "Layer type");
            String _sLayer = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String _sLabel = SessionData.getDataTbVal(dataTable, row, "Custom name");
            String noti = SessionData.getDataTbVal(dataTable, row, "Notification");
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            myCampaignSteps.addCustomOption();
            myCampaignSteps.selectType(_sType);
            if (!_sLayer.isEmpty()){
                editorCampaignSteps.selectLayer(_sLayer);
            }
            if (!_sLabel.isEmpty()){
                myCampaignSteps.inputCustomOption("Label", _sLabel);
            }
            else{
                myCampaignSteps.inputCustomOption("Label", "Custom option");
            }
            if (!noti.isEmpty()) {
                editorCampaignSteps.verifyNotiPicktureChoice(noti, true);
            }
            if (!_sFolder.isEmpty()) {
                editorCampaignSteps.searchNameClipartFolder(_sFolder);
                editorCampaignSteps.chooseAClipartFolder();
            }
        }
    }


    @And("add to Additional Materials as {string}")
    public void addToAdditionalMaterials(String dataKey, List<List<String>> dataTable) {
        editorCampaignSteps.selectOnAddMaterial();
        int i = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFileName = SessionData.getDataTbVal(dataTable, row, "File Name");
            String _sFile = SessionData.getDataTbVal(dataTable, row, "File");
            String _sAppliedFor = SessionData.getDataTbVal(dataTable, row, "Applied For");
            i++;
            if (i == 1) {
                editorCampaignSteps.inputFileNameOnAddMaterial(_sFileName);
                editorCampaignSteps.chooseFileOnAdditional(_sFile);
                editorCampaignSteps.selectAppiliedFor(_sAppliedFor);
            } else {
                editorCampaignSteps.clickOnAddMaterials();
                editorCampaignSteps.inputFileNameOnAddMaterial(_sFileName);
                editorCampaignSteps.chooseFileOnAdditional(_sFile);
                editorCampaignSteps.selectAppiliedFor(_sAppliedFor);
            }
            editorCampaignSteps.verifyMaterialError(_sFileName, _sFile, _sAppliedFor);
        }
    }

    @And("add photo guide as {string}")
    public void addPhotoGuide(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sContent = SessionData.getDataTbVal(dataTable, row, "Content");
            Date date = new Date();
            long timestamp = date.getTime() / 1000;
            titlePhotoGuide = _sTitle + timestamp;
            editorCampaignSteps.clickOnPhotoGuide();
            editorCampaignSteps.clickAddPhotoGuide();
            editorCampaignSteps.inputPhotoGuideName(titlePhotoGuide);
            if (_sType.equals("Text")) {
                editorCampaignSteps.inputDescriptionOnPG(_sContent);
            }
            if (_sType.equals("Image")) {
                editorCampaignSteps.uploadImageOnPhotoGuide(_sContent);
            }
            editorCampaignSteps.clickSavePhoto();
            editorCampaignSteps.verifyPhotoGuideSelected(titlePhotoGuide, true);
        }
    }

    @And("edit photo guide")
    public void editPhotoGuide(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sContent = SessionData.getDataTbVal(dataTable, row, "Content");
            Date date = new Date();
            long timestamp = date.getTime() / 1000;
            titlePhotoGuide = _sTitle + timestamp;
            editorCampaignSteps.clickEditPhotoGuide();
            editorCampaignSteps.inputPhotoGuideName(titlePhotoGuide);
            if (_sType.equals("Text")) {
                editorCampaignSteps.inputDescriptionOnPG(_sContent);
            }
            if (_sType.equals("Image")) {
                editorCampaignSteps.uploadImageOnPhotoGuide(_sContent);
            }
            editorCampaignSteps.clickSavePhoto();
            editorCampaignSteps.verifyPhotoGuideSelected(titlePhotoGuide, true);
        }
    }

    @And("click Save Draft Camp")
    public void clickSaveDraftCampAndGoToCampaignList() {
        editorCampaignSteps.clickSaveDraftCampaign();
        campaignSteps.waitUntilLoadingSuccess();
    }

    @And("verify product main")
    public void verifyProductMain(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _productMain = SessionData.getDataTbVal(dataTable, row, "Product main");
            editorCampaignSteps.verifyProductMain(_productMain);
        }
    }

    @And("click to base product in Editor")
    public void clickToBaseProductInEditor(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _baseProduct = SessionData.getDataTbVal(dataTable, row, "Base Product");
            editorCampaignSteps.selectBaseProduct(_baseProduct);
        }
    }

    @And("back to the page Editor")
    public void backToThePageEditor() {
        orderSteps.backToThePage();
    }

    @And("choose custom option {string}")
    public void chooseCustomOption(String sCO) {
        editorCampaignSteps.chooseCustomOption(sCO);
    }

    @And("click on Edit clipart folder in Editor")
    public void clickOnEditClipartFolderInEditor() {
        editorCampaignSteps.clickEditClipartFolderLink();
    }

    @And("click to Add a clipart folder")
    public void clickToAddAClipartFolder() {
        editorCampaignSteps.clickAddAClipartFolderLink();
    }

    @And("verify image product detail page {string}")
    public void verifyImageProductPage(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sImageEx = SessionData.getDataTbVal(dataTable, row, "Image expected");
            String _sTotalImage = SessionData.getDataTbVal(dataTable, row, "Total Image");
            String _sPer = SessionData.getDataTbVal(dataTable, row, "Percent Image");
            if (!_sTotalImage.isEmpty())
                productDetailSteps.verifyAddImage(_sTotalImage);
            myCampaignSteps.verifyImageMockup(_sImageEx, _sPer, row);
        }
    }

    @And("verify message error in pricing screen {string}")
    public void verifyMessageErrorInPricingScreen(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sReason = SessionData.getDataTbVal(dataTable, row, "Reason");
            String _sMessageError = SessionData.getDataTbVal(dataTable, row, "Message Error");
            editorCampaignSteps.verifyMessageErrorInPricingScreen(_sMessageError);
        }
    }

    @And("verify button Launch camp as {string}")
    public void verifyButtonLaunchCamp(String statusButton) {
        String status = "false";
        if (statusButton.equalsIgnoreCase("disabled"))
            status = "true";
        editorCampaignSteps.verifyButtonLaunchCamp(status);
    }

    @And("delete material with quantity as {string}")
    public void deleteMaterialWithQuantity(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int _sQuantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity Materials"));
            editorCampaignSteps.deleteMaterials(_sQuantity);
        }
    }

    @And("edit Additional Materials as {string}")
    public void editAdditionalMaterials(String dataKey, List<List<String>> dataTable) {
        int i = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFileName = SessionData.getDataTbVal(dataTable, row, "File Name");
            String _sFile = SessionData.getDataTbVal(dataTable, row, "File");
            String _sAppliedFor = SessionData.getDataTbVal(dataTable, row, "Applied For");
            i++;
            editorCampaignSteps.inputFileNameOnEditMaterial(_sFileName, i);
            editorCampaignSteps.chooseFileAdditionalOnEditMaterial(_sFile, i);
            editorCampaignSteps.selectAppiliedForOnEditMaterial(_sAppliedFor, i);
        }
    }

    @And("click Save change on Edit personalize")
    public void clickSaveChangeOnEditPersonalize() {
        campaignSteps.clickOnSaveChangeBtn();
        campaignSteps.clickOnUpdateBtn();
    }

    @And("Edit campaign with data as {string}")
    public void editProductWithData(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            if (!title.isEmpty()) {
                campaignSteps.enterTitle(title);
            }
            productDetailSteps.waitABit(2000);
            if (!"".equals(description)) {
                campaignSteps.enterDescription(description);
            }
        }
    }

    @When("delete conditinal logic of CO name {string}")
    public void deleteConditinalLogic(String co) {
        editorCampaignSteps.clickOnConditionalBtn(co);
        editorCampaignSteps.clickDeleteConditionalBtn();
        editorCampaignSteps.confirmDelete();
    }

    @And("edit conditional logic as {string}")
    public void editConditionalLogicAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String option = SessionData.getDataTbVal(dataTable, row, "Option");
            String s_condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String s_option = SessionData.getDataTbVal(dataTable, row, "Then show");
            if (!option.isEmpty()) {
                editorCampaignSteps.clickOnConditionalBtn(option);
            }
            if (!s_condition.isEmpty()) {
                editorCampaignSteps.selectConditionalLogic(s_condition, s_option);
            }
        }
        editorCampaignSteps.clickBack();
    }

    @And("delete custom option {string}")
    public void deleteCustomOption(String customOption) {
        editorCampaignSteps.deleteCO(customOption);
        editorCampaignSteps.confirmDelete();
    }

    @And("add group layer")
    public void addGroupLayer(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String frontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            String nameGroup = SessionData.getDataTbVal(dataTable, row, "Name group");
            String nameGroupNew = SessionData.getDataTbVal(dataTable, row, "Name group new");
            String layerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            editorCampaignSteps.addGroupLayer(frontOrBack);
            editorCampaignSteps.editNameGroup(frontOrBack, nameGroup, nameGroupNew);
            if (!layerName.isEmpty()) {
                editorCampaignSteps.addLayerToGroup(frontOrBack, layerName, nameGroupNew);
            }
        }
    }

    @And("setup customize group layer")
    public void setupCustomizeGroupLayer(List<List<String>> dataTable) {
        editorCampaignSteps.clickBtnExpand();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionType = SessionData.getDataTbVal(dataTable, row, "Option type");
            String label = SessionData.getDataTbVal(dataTable, row, "Label");
            String defaultGroup = SessionData.getDataTbVal(dataTable, row, "Default group");

            editorCampaignSteps.clickCustomizeGroup();
            editorCampaignSteps.selectOptionTypeGroup(optionType);
            editorCampaignSteps.inputLabel(label);
            editorCampaignSteps.selectDefaultGroup(defaultGroup);
            editorCampaignSteps.clickBackCustomGroup();
        }
    }

    @And("edit group layer")
    public void editGroupLayer(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String frontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            String nameGroup = SessionData.getDataTbVal(dataTable, row, "Name group");
            String layerName = SessionData.getDataTbVal(dataTable, row, "Layer name");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            boolean isExitGroup = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is exit group"));
            boolean isExitLayer = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is exit layer"));

            if (!action.isEmpty()) {
                editorCampaignSteps.editGroupLayer(frontOrBack, nameGroup, action);
                if (action.equals("Delete"))
                    editorCampaignSteps.clickBtnDelete();
            }
            editorCampaignSteps.checkExitLayerOrGroup(frontOrBack, layerName, isExitLayer);
            editorCampaignSteps.checkExitLayerOrGroup(frontOrBack, nameGroup, isExitGroup);
        }
    }

    @And("edit customize group or custom option")
    public void editCustomizeGroupOrCustomOption(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String message = SessionData.getDataTbVal(dataTable, row, "Mesage target layer");

            editorCampaignSteps.editOption(name, action);
            if (action.equals("Delete")) {
                editorCampaignSteps.clickBtnDelete();
            } else if (action.equals("Clone")) {
                editorCampaignSteps.verifyMessageLayer(message);
                editorCampaignSteps.clickSaveCO();
            }
        }
    }

    @And("input infor to create description for campaign editor As {string}")
    public void inputInforToCreateDescriptionForCampaignEditorAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String _sDescription = SessionData.getDataTbVal(dataTable, row, "Description");
            String _sTags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String _sMainProduct = SessionData.getDataTbVal(dataTable, row, "Product main");
            String _sColor = SessionData.getDataTbVal(dataTable, row, "Color code main");

            editorCampaignSteps.verifyPhotoGuide();
            campaignSteps.enterTitle(_sTitle);
            campaignSteps.enterDescription(_sDescription);
            campaignSteps.enterTags(_sTags);
            editorCampaignSteps.selectMainProduct(_sMainProduct);
            editorCampaignSteps.selectMainColor(_sColor);
        }
    }

    @And("setup personalization in layer deatail as {string}")
    public void setupPersonalizationInLayerDeatailAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
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
            String typeClipart = SessionData.getDataTbVal(dataTable, row, "Folder or Group");
            String typeDisplayNameClipart = SessionData.getDataTbVal(dataTable, row, "Thumbnail or Clipart names");
            String value_clipart = SessionData.getDataTbVal(dataTable, row, "Value clipart");

            editorCampaignSteps.openDetailLayer(_sLayer);
            editorCampaignSteps.setUpPersonalization();
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
            editorCampaignSteps.clickSaveCO();
        }
    }

    @And("click button expand on Custom Option")
    public void clickBtnExpandOnCO() {
        editorCampaignSteps.clickBtnExpand();
    }

    @And("verify button Create collection as {string}")
    public void verifyButtonCreateCollectionAs(String statusButton) {
        String status = "false";
        if (statusButton.equalsIgnoreCase("disabled"))
            status = "true";
        editorCampaignSteps.verifyButtonCreateCollection(status);
    }

    @When("verify message on camp detail")
    public void verifyMessageOnCampDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String saveChanges = SessionData.getDataTbVal(dataTable, row, "Save changes");
            String errorImage = SessionData.getDataTbVal(dataTable, row, "Error message");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image url");
            if (!"".equals(image)) {
                productDetailSteps.addImage(image);
            }

            if (!"".equals(imageUrl)) {
                productDetailSteps.enterImageURL(imageUrl);
            }
            if (!saveChanges.isEmpty()) {
                productDetailSteps.clickSaveChangesOrDiscard("Save changes");
            }
            editorCampaignSteps.verifyMessageImage(errorImage);
        }
    }
}
