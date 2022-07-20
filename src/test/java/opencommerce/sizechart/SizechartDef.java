package opencommerce.sizechart;

import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps.MappingProductsSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.size_charts.steps.SizeChartSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SizechartDef {
    @Steps
    SizeChartSteps sizeChartSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    MappingProductsSteps mappingProductsSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ProductDetailSteps productDetailSteps;

    public static String tagSizeChart = "";

    @And("Add a new size chart with data")
    public void addANewSizeChartWithData(List<List<String>> dataTable) {
        sizeChartSteps.disableSizeChart();
        sizeChartSteps.addSizeChart();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String images = SessionData.getDataTbVal(dataTable, row, "Images");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            if (!style.isEmpty()) {
                sizeChartSteps.inputStyle(style);
            }
            if (!images.isEmpty()) {
                sizeChartSteps.addImage(images);
            }
            if (!"".equals(description)) {
                productDetailSteps.enterDescription(description);
            }
            if (!message.isEmpty()) {
                sizeChartSteps.verifyMessage(message);
            }
        }
        tagSizeChart = sizeChartSteps.getTag();
        sizeChartSteps.clickBtnSave();
    }

    @Given("Delete all size chart")
    public void deleteAllSizeChart() {
        while (sizeChartSteps.countNumberOfSizeChart() > 0) {
            sizeChartSteps.selectAllSizeChart();
            productListSteps.clickAction();
            sizeChartSteps.chooseActionDeleteSelectedProduct();
            productListSteps.clickBtnDelete();
        }
        assertThat(sizeChartSteps.countNumberOfSizeChart()).isEqualTo(0);
        sizeChartSteps.verifyMsgNoSizeChart();

    }

    @Then("verify show size chart on store front")
    public void verifyShowSizeChartOnStoreFront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean show = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show size chart"));
            String listValueSizeChart = SessionData.getDataTbVal(dataTable, row, "List value size chart");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            sizeChartSteps.verifyShowSizeChart(show);
            if (show) {
                productSteps.openSizeChart();
                productSteps.verifyTitleSizeChart(title);
                productSteps.verifyDescriptionSizeChart(description);
                productSteps.verifyListTitleInSizeChart(listValueSizeChart);
            }
            mappingProductsSteps.switchToTheFirstTab();
        }
    }

    @Then("Enable size chart widget")
    public void enableSizeChartWidget() {
        sizeChartSteps.enableSizeChart();
    }

    @And("Switch to the behind tab")
    public void switchToTheBehindTab() {
        mappingProductsSteps.switchToLastestTab();
    }
}
