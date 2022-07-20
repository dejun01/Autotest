package opencommerce.apps.adc.manageorder;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps.ManageOrderFullFlowSteps;
import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps.MappingProductsSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.ordersMap;

public class MappingProductsDef {

    @Steps
    MappingProductsSteps mappingProductsSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    OrderSteps orderSteps;

    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    ManageOrderFullFlowSteps manageOrderFullFlowSteps;
    private String orderNumber;
    private String aliLink;

    @Then("^verify maping product value$")
    @And("^import product url from AliExpress$")
    public void import_product_url_from_aliexpress(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String url = SessionData.getDataTbVal(dataTable, row, "AliExpress url");
            String errMessage = SessionData.getDataTbVal(dataTable, row, "Error Message");
            String productMaping = SessionData.getDataTbVal(dataTable, row, "Product Mapping");
            String sbProduct = SessionData.getDataTbVal(dataTable, row, "ShopBase Product");
            String aliProduct = SessionData.getDataTbVal(dataTable, row, "AliExpress Product");
            String popupContent = SessionData.getDataTbVal(dataTable, row, "Is Shown Popup Override Product");
            String editData = SessionData.getDataTbVal(dataTable, row, "Edit Data");
            String btnConfirmPopup = SessionData.getDataTbVal(dataTable, row, "Confirm Override");
            boolean isShownPopupConfirm = false;
            if (!popupContent.isEmpty()) {
                isShownPopupConfirm = Boolean.parseBoolean(popupContent);
            }
            if (editData.equals("No")) {
                mappingProductsSteps.verifyUrlDisplay(url);
            } else {
                mappingProductsSteps.inputURLAli(url);
                mappingProductsSteps.clickBtnImport();
                if (!errMessage.isEmpty() && !isShownPopupConfirm) {
                    mappingProductsSteps.verifyErrorMessageImportDisplay(errMessage);
                }
            }
            if (!popupContent.isEmpty()) {
                mappingProductsSteps.verifyPopupDisplay(isShownPopupConfirm);
                if (!btnConfirmPopup.isEmpty()) {
                    mappingProductsSteps.confirmPopupOverride(btnConfirmPopup);
                    if (btnConfirmPopup.equals("Confirm")) {
                        mappingProductsSteps.verifyErrorMessageImportDisplay(errMessage);
                    }
                }
            }
            if (!productMaping.isEmpty()) {
                mappingProductsSteps.verifyProductMappingSectionDisplay(productMaping, popupContent);
            }
            if (!sbProduct.isEmpty()) {
                mappingProductsSteps.verifyShopbaseMappingSectionDisplay(sbProduct, popupContent);
            }
            if (!aliProduct.equals("")) {
                mappingProductsSteps.verifyAliExpressMappingSectionDisplay(aliProduct, popupContent);
            }
        }
    }

    @And("^select value to mapping product with AliExpress Product of \"([^\"]*)\"$")
    public void select_value_to_mapping_product_with_aliexpress_product(String orderKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String valueSB = SessionData.getDataTbVal(dataTable, row, "ShopBase Product");
            String valueAli = SessionData.getDataTbVal(dataTable, row, "AliExpress Product");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            String isBackToManageOrder = SessionData.getDataTbVal(dataTable, row, "Manage Order Screen");
            boolean isSelect = false;
            if (!valueAli.isEmpty()) {
                isSelect = true;
            }

            if (isBackToManageOrder.equals("Yes")) {
                orderNumber = ordersMap.get(orderKey).get(0);
                String productName = ordersMap.get(orderKey).get(1);
                manageOrderFullFlowSteps.clickOnMapProductButton(orderNumber, productName);
            }
            mappingProductsSteps.selectOptionSBProduct(valueSB, true);
            mappingProductsSteps.selectVariantAliExpressProduct(valueAli, isSelect);
            mappingProductsSteps.clickButtonSaveChanges("Save changes");
            mappingProductsSteps.verifyToastMessageDisplay(result);
//          mappingProductsSteps.verifyProductSbaseAutoMappingWithProductAli(valueSB);
        }
    }

    @And("^switch to the lastest tab$")
    public void switch_to_the_lastest_tab() {
        mappingProductsSteps.switchToLastestTab();
    }

    @When("^uncheck Option \"([^\"]*)\" of Shopbase Product$")
    public void uncheck_option_something_of_shopbase_product(String option) {
        mappingProductsSteps.unCheckCheckbox(option);
    }

    @Then("^click button on Mapping Product$")
    public void click_button(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String buttonName = SessionData.getDataTbVal(dataTable, row, "Button name");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            mappingProductsSteps.clickButtonSaveChanges(buttonName);
            mappingProductsSteps.verifyToastMessageDisplay(message);
            commonSteps.waitUntilInvisibleLoading(5);
        }
    }

    @When("^delete product \"([^\"]*)\"$")
    public void delete_product_something(String keyProduct) {
        productDetailSteps.deleteProduct();
    }

    @Then("^verify deleted successfully$")
    public void verify_deleted_successfully(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String errMessage = SessionData.getDataTbVal(dataTable, row, "Error Message");
            mappingProductsSteps.verifyProductDeleted(errMessage);
        }

    }

    @When("import the AliExpress link {string}")
    public void importTheAliExpressLink(String aliLink) {
        this.aliLink = aliLink;
        mappingProductsSteps.enterAliProductUrl(aliLink);
        mappingProductsSteps.clickBtnImport();
    }

    @Then("map product with option values")
    public void mapProductWithOptionValues(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String option = SessionData.getDataTbVal(dataTable, row, "Option set");
            String values = SessionData.getDataTbVal(dataTable, row, "Option value");

            mappingProductsSteps.mappingSbProductWithAliProduct(option, values);
        }
        mappingProductsSteps.clickOnSaveChangesBtn();
        mappingProductsSteps.getProductCostByVariantAdded(aliLink);
    }

    @Given("get product cost {string}")
    public void getProductCost(String url) {
        mappingProductsSteps.getProductCostByVariantAdded(url);
    }

    @And("select duplicate Ali option set then verify the message is displayed {string}")
    public void selectDuplicateAliOptionSet(String msg) {
        mappingProductsSteps.selectAliOption();
        mappingProductsSteps.verifyToastMessageDisplay(msg);
    }

    @And("verify the message is display in mapping screen {string}")
    public void verifyTheMessageIsDisplayInMappingScreen(String msg) {
        mappingProductsSteps.verifyMsgDisplayed(msg);
    }
}
