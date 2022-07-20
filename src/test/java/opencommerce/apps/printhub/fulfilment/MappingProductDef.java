package opencommerce.apps.printhub.fulfilment;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.fulfillment.MappingProductSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import opencommerce.products.dashboard.ProductDetailDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MappingProductDef {
    @Steps
    MappingProductSteps mappingProductSteps;

    @Steps
    ProductDetailSteps productDetailSteps;

    @And("^Click map product in (.*) and verify block option$")
    public void clickMapProduct(String nameApp) {
        productDetailSteps.clickMapProduct(nameApp);
        mappingProductSteps.verifyDefineOptionDisable();
        mappingProductSteps.verifyDefaultOption();
        mappingProductSteps.verifySaveButtonDisable();
    }

    @And("^Click map product in (.*) and verify define option$")
    public void clickMapProductAndVerifyDefineOption(String nameApp, List<List<String>> dataTable) {
        productDetailSteps.clickMapProduct(nameApp);
        mappingProductSteps.verifyDefineOptionEnable();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String offer = SessionData.getDataTbVal(dataTable, row, "What we offer");
            String mapWith = SessionData.getDataTbVal(dataTable, row, "We should map with");

            mappingProductSteps.verifyOptionValue(offer, mapWith);
        }
        mappingProductSteps.clickSave();
    }

    @And("^Change define variant but have no save in (.*)$")
    public void changeDefineVariantButHaveNoSave(String nameApp, List<List<String>> dataTable) {
        productDetailSteps.clickMapProduct(nameApp);
        mappingProductSteps.verifyDefineOptionEnable();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String offer = SessionData.getDataTbVal(dataTable, row, "What we offer");
            String mapWith = SessionData.getDataTbVal(dataTable, row, "We should map with");

            mappingProductSteps.selectOption(offer, mapWith);
        }
        mappingProductSteps.clickBreadcrum();
    }


    @Then("^Verify auto map option$")
    public void selectProductMappingAndVerifyAutoMapOption(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            String optionValueMap = SessionData.getDataTbVal(dataTable, row, "Option value map");

            mappingProductSteps.verifyAutoMapoption(optionValue, optionValueMap);
        }
        mappingProductSteps.clickBtnSave();
    }

    @And("^Upload artwork with product mapping \"([^\"]*)\"$")
    public void uploadArtworkWithProductMapping(String nameProduct, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameImage = SessionData.getDataTbVal(dataTable, row, "Name image");
            String message = SessionData.getDataTbVal(dataTable, row, "Error message");
            String frontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");
            boolean elementVisible = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Element visible"));

            mappingProductSteps.selectProductMaping(nameProduct);
            mappingProductSteps.clickUploadArtwork(frontOrBack, nameImage);
            if (!"".equals(message)) {
                mappingProductSteps.verifyMessageUploadArtwork(message);
                mappingProductSteps.verifyButtonChangeAndDeleteArtwork(elementVisible);
                mappingProductSteps.refreshPage();
            } else {
                mappingProductSteps.verifyButtonChangeAndDeleteArtwork(elementVisible);
                mappingProductSteps.clickBtnSave();
                mappingProductSteps.clickDeleteArtwork();
                mappingProductSteps.verifyButtonChangeAndDeleteArtwork(!elementVisible);
                mappingProductSteps.clickDiscard();
            }
        }
    }

    @And("^Verify varriants$")
    public void verifyVarriants(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameProduct = SessionData.getDataTbVal(dataTable, row, "Name product");
            String totalVariants = SessionData.getDataTbVal(dataTable, row, "Total variants");
            String totalProduct = SessionData.getDataTbVal(dataTable, row, "Total Product");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            String totalVariantsMapped = SessionData.getDataTbVal(dataTable, row, "Total variants mapped");
            if (!"".equals(totalVariants) && !"".equals(nameProduct)) {
                if (nameProduct.matches("@(.*)@"))
                    mappingProductSteps.verifyVariants(ProductDetailDef.nameProductSbase, totalVariants);
                else
                    mappingProductSteps.verifyVariants(nameProduct, totalVariants);
            }
            if (!"".equals(totalProduct)) {
                mappingProductSteps.verifyTotalProduct(totalProduct);
            }
            mappingProductSteps.verifyTotalVariantsMapped(optionValue, totalVariantsMapped);
        }
        mappingProductSteps.selectAnotherOptionValue();
    }

    @And("^Verify total available and unvailable$")
    public void verifyTotalAvailableAndUnvailableWhenUncheckbox(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            mappingProductSteps.selectTap(tab);
            mappingProductSteps.verifyTotal(value);

            if (!"".equals(message)) {
                mappingProductSteps.verifyMessage(message);
            }
        }
    }

    @And("^click mapping product in (.*)$")
    public void clickMappingProduct(String nameApp) {
        productDetailSteps.clickMapProduct(nameApp);
        mappingProductSteps.clickSave();
    }

    @And("^Select all option value$")
    public void selectAllOptionValue(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            mappingProductSteps.selectCheckbox(optionValue);
        }
    }

    @Then("^Change value option$")
    public void changeValueOption(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            String changeOptionMapped = SessionData.getDataTbVal(dataTable, row, "Change option mapped");

            if (!"".equals(changeOptionMapped)) {
                mappingProductSteps.changeValueAutoMapoption(optionValue, changeOptionMapped);
            }
        }
        mappingProductSteps.clickBtnSave();
        mappingProductSteps.waitABit(3000);
    }

    @And("^Verify count total product mapped$")
    public void verifyCountTotalProductMapped(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameProduct = SessionData.getDataTbVal(dataTable, row, "Name product");
            String optionValues = SessionData.getDataTbVal(dataTable, row, "Option value");
            String totalVariantsMapped = SessionData.getDataTbVal(dataTable, row, "Total variants mapped");

            List<String> optionValue = new ArrayList<>(Arrays.asList(optionValues.split(",")));
            String optionValue_1 = optionValue.get(0);
            String optionValue_2 = optionValue.get(1);

            List<String> totalVariants = new ArrayList<>(Arrays.asList(totalVariantsMapped.split(",")));
            String totalVariants_1 = totalVariants.get(0);
            String totalVariants_2 = totalVariants.get(1);

            if (nameProduct.matches("@(.*)@"))
                nameProduct = ProductDetailDef.nameProductSbase;
            mappingProductSteps.verifyCountTotal(nameProduct, optionValue_1, optionValue_2, totalVariants_1, totalVariants_2);
        }
    }

    @And("^verify variant after map$")
    public void verifyVariantAfterMap(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");
            String optionValueMap = SessionData.getDataTbVal(dataTable, row, "Option value map");
            mappingProductSteps.verifyVariantAfterMap(optionValue, optionValueMap);
        }
    }

    @Then("^Mapping product in order with base product \"([^\"]*)\"$")
    public void mappingProductInOrderWithBaseProduct(String nameProduct, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameImage = SessionData.getDataTbVal(dataTable, row, "Name image");
            String frontOrBack = SessionData.getDataTbVal(dataTable, row, "Front or back");

            mappingProductSteps.selectProductMaping(nameProduct);
            mappingProductSteps.clickUploadArtwork(frontOrBack, nameImage);
        }
    }
}