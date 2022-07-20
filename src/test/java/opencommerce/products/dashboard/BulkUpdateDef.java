package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.BulkUpdateSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionDetailSteps;
import com.opencommerce.shopbase.dashboard.size_charts.steps.SizeChartSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulkUpdateDef {
    @Steps
    BulkUpdateSteps bulkUpdateSteps;
    @Steps
    SizeChartSteps sizeChartSteps;
    @Steps
    CollectionDetailSteps collectionDetailSteps;

    private String disjunctiveProductFilter = "";
    private String fieldProductFilter = "";
    private String comparatorProductFilter = "";
    private String valueProductFilter = "";

    private String disjunctiveVariantFilter = "";
    private String fieldVariantFilter = "";
    private String comparatorVariantFilter = "";
    private String valueVariantFilter = "";

    private String actionSelected = "";
    private String valueAction = "";
    private String valueUpdate = "";


    @Then("^Create a bulk update with product condition is \"([^\"]*)\"$")
    public void createABulkUpdateWithProductCondition(String disjunctive, List<List<String>> dataTable) {
        bulkUpdateSteps.waitABit(20000);
        bulkUpdateSteps.clickBtnCreateAnUpdate();
        disjunctiveProductFilter = disjunctive;

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            fieldProductFilter = SessionData.getDataTbVal(dataTable, row, "Field");
            comparatorProductFilter = SessionData.getDataTbVal(dataTable, row, "Comparator");
            valueProductFilter = SessionData.getDataTbVal(dataTable, row, "Value");

            if (row > 1) {
                bulkUpdateSteps.clickAddCondition();
            }

            bulkUpdateSteps.selectDisjunctiveProduct(disjunctiveProductFilter);
            bulkUpdateSteps.selectFieldProduct(fieldProductFilter, row);
            bulkUpdateSteps.selectComparatorProduct(comparatorProductFilter, row);
            if (!valueProductFilter.isEmpty()) {
                bulkUpdateSteps.selectValueProduct(valueProductFilter, row);
            }
        }
    }

    @Then("^Create a bulk update with variant condition is \"([^\"]*)\"$")
    public void createABulkUpdateWithVariantCondition(String disjunctive, List<List<String>> dataTable) {
        bulkUpdateSteps.selectOnlyUpdateSomeVariants();
        disjunctiveVariantFilter = disjunctive;

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            fieldVariantFilter = SessionData.getDataTbVal(dataTable, row, "Field");
            comparatorVariantFilter = SessionData.getDataTbVal(dataTable, row, "Comparator");
            valueVariantFilter = SessionData.getDataTbVal(dataTable, row, "Value");

            if (row > 1) {
                bulkUpdateSteps.clickAddCondition();
            }

            bulkUpdateSteps.selectDisjunctiveVariant(disjunctiveVariantFilter);
            bulkUpdateSteps.selectFieldVariant(fieldVariantFilter, row);
            bulkUpdateSteps.selectComparatorVariant(comparatorVariantFilter, row);
            bulkUpdateSteps.selectValueVariant(valueVariantFilter, row);
        }
    }

    @And("^Execute (an action|another) bulk update for (product|some variants) \"([^\"]*)\"$")
    public void select(String actionType, String bulkUpdateFor, String action, List<List<String>> dataTable) {
        if ("another".equals(actionType)) {
            bulkUpdateSteps.clickAddAction();
        }

        bulkUpdateSteps.selectAction(action);
        actionSelected = action;

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            valueAction = SessionData.getDataTbVal(dataTable, row, "Value");
            valueUpdate = SessionData.getDataTbVal(dataTable, row, "Value update");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            switch (action) {
                case "Change product description to":
                    bulkUpdateSteps.changeProductDescription(valueAction);
                    break;
                case "Change product vendor to":
                    bulkUpdateSteps.changeProductVendor(valueAction);
                    break;
                case "Add tags":
                    bulkUpdateSteps.addTag(valueAction);
                    break;
                case "Change price to":
                    bulkUpdateSteps.changePriceTo(valueAction);
                    break;
                case "Increase price by amount":
                    bulkUpdateSteps.increasePriceByAmount(valueAction);
                    break;
                case "Decrease price by amount":
                    bulkUpdateSteps.decreasePriceByAmount(valueAction);
                    break;
                case "Change compare-at-price to":
                    bulkUpdateSteps.changeCompareAtPriceTo(valueAction);
                    break;
                case "Decrease compare-at-price by percentage":
                    bulkUpdateSteps.decreaseCompareAtPriceByPercentage(valueAction);
                    break;
                case "Round/ Beautify price":
                    bulkUpdateSteps.roundBeautifyPrice(valueAction);
                    break;
                case "Delete custom options":
                    bulkUpdateSteps.deleteCustomOption(valueAction);
                    break;
                case "Find and replace text in description":
                    bulkUpdateSteps.replaceDecription(valueAction, valueUpdate);
                    break;
                case "Change product type":
                    bulkUpdateSteps.changeProductType(valueAction, valueUpdate);
                    break;
                case "Delete variant's option value":
                    bulkUpdateSteps.deleteVariant(valueAction);
                    break;
            }

            bulkUpdateSteps.clickPreviewBulkUpdate();
            bulkUpdateSteps.verifyPreviewBulkUpdateProductOnPopupPreview(disjunctiveProductFilter, fieldProductFilter, comparatorProductFilter, valueProductFilter);

            if ("some variants".equals(bulkUpdateFor)) {
                bulkUpdateSteps.verifyPreviewBulkUpdateVariantOnPopupPreview(disjunctiveVariantFilter, fieldVariantFilter, comparatorVariantFilter, valueVariantFilter);
            }

            //bulkUpdateSteps.verifyPreviewBulkUpdateActionOnPopupPreview(actionSelected, valueAction);
            bulkUpdateSteps.startBulkUpdate();
            bulkUpdateSteps.clickBtnUpdate();

            if (!"".equals(message)) {
                bulkUpdateSteps.verifyMessage(message);
            }

            bulkUpdateSteps.verifyPreviewBulkUpdateProductOnBulkUpdateList(disjunctiveProductFilter, fieldProductFilter, comparatorProductFilter, valueProductFilter);
            bulkUpdateSteps.verifyPreviewBulkUpdateVariantOnBulkUpdateList(bulkUpdateFor, disjunctiveVariantFilter, fieldVariantFilter, comparatorVariantFilter, valueVariantFilter);
            //bulkUpdateSteps.verifyPreviewBulkUpdateActionOnBulkUpdateList(actionSelected, valueAction);
        }
        bulkUpdateSteps.waitABit(20000);
    }


    @Then("Assign size chart to product")
    public void assignSizeChartToProduct(List<List<String>> dataTable) {
        sizeChartSteps.assignToProduct();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Conditions");

            List<String> conditions = new ArrayList<>(Arrays.asList(condition.split(",")));
            String conType = conditions.get(0);
            String prodProperties = conditions.get(1);
            String cons = conditions.get(2);
            String conValue = conditions.get(3);
            collectionDetailSteps.chooseConditionType(conType);
            sizeChartSteps.selectProdProperties(prodProperties);
            sizeChartSteps.selectDdColCondition(cons);
            sizeChartSteps.inputConValue(conValue);

            bulkUpdateSteps.previewBulkUpdate();
            bulkUpdateSteps.startBulkUpdate();
            bulkUpdateSteps.clickUpdate();
        }
    }


    @Then("verify result been updated after bulk update")
    public void verifyResultBeenUpdatedAfterBulkUpdate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sFilter = SessionData.getDataTbVal(dataTable, row, "Filter");
            String _sUpdateFor = SessionData.getDataTbVal(dataTable, row, "Update for");
            String _sAction = SessionData.getDataTbVal(dataTable, row, "Action");
            String _sNumberUpdate = SessionData.getDataTbVal(dataTable, row, "Number update");
            if (!_sFilter.isEmpty()) {
                bulkUpdateSteps.verifyAfterBulkUpdate(3, _sFilter);
            }
            if (!_sUpdateFor.isEmpty()) {
                bulkUpdateSteps.verifyAfterBulkUpdate(4, _sUpdateFor);
            }
            if (!_sAction.isEmpty()) {
                bulkUpdateSteps.verifyAfterBulkUpdate(5, _sAction);
            }
            bulkUpdateSteps.verifyBulkUpDateFinished();
            if (!_sNumberUpdate.isEmpty()) {
                bulkUpdateSteps.verifyAfterBulkUpdate(6, _sNumberUpdate);
            }

        }
    }

    @Then("verify quantity of product been updated  after bulk update is {string}")
    public void verifyQuantityOfProductBeenUpdatedAfterBulkUpdateIs(String _sNumberUpdate) {
        bulkUpdateSteps.verifyBulkUpDateFinished();
        if (!_sNumberUpdate.isEmpty()) {
            bulkUpdateSteps.verifyAfterBulkUpdate(6, _sNumberUpdate);
        }
    }
}
