package opencommerce.fulfillment_service.warehouse;

import com.opencommerce.shopbase.ProductVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.MappingProductSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.ProductMoveSteps;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static com.opencommerce.shopbase.ProductVariable.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductMoveDef {
    String mail = LoadObject.getProperty("user.name");
    @Steps
    ProductMoveSteps productMoveSteps;

    @Steps
    MappingProductSteps mappingProductSteps;


    public String variants = "";
    public String StockMove_Export_File_Download = "";

    @And("Click on view inventory detail")
    public void clickOnViewInventoryDetail() {
        mappingProductSteps.searchProductImport(ProductVariable.productName);
        productMoveSteps.clickViewInventoryDetail(ProductVariable.productName);

    }

    @And("verify variant of inventory")
    public void verifyVariantOfInventory() {
        productMoveSteps.accTab("Stock move");
        productMoveSteps.verifyVariantInventory(variant);
    }

    @And("click on detail variant and verify information variant in product detail")
    public void clickOnDetailVariant(List<List<String>> dataTable) {
        productMoveSteps.searchByPONumber(purchaseOrderCurrent);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variants = SessionData.getDataTbVal(dataTable, row, "variant");
            String type = SessionData.getDataTbVal(dataTable, row, "type");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            productMoveSteps.clickBTViewVariantDetail(variants);
            productMoveSteps.verifyDate(variants,createdDate);
            purchaseOrderCurrent = productMoveSteps.verifyReference(variants);
            quantity = productMoveSteps.verifyQuantity(variants);
            type = productMoveSteps.verifyType(variants);
            status = productMoveSteps.verifyStatus(variants);
        }
    }

    @And("search in product move and verify variant of inventory")
    public void searchInProductMoveAndVerifyVariantOfInventory(List<List<String>> dataTable) {
        productMoveSteps.accTab("Stock move");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "condition");
            String createDate = SessionData.getDataTbVal(dataTable, row, "DATE");
            String type = SessionData.getDataTbVal(dataTable, row, "TYPE");
            String quantity1 = SessionData.getDataTbVal(dataTable, row, "QUANTITY");
            String status = SessionData.getDataTbVal(dataTable, row, "STATUS");
            String expectedVariant = "";
            switch (condition.toLowerCase()) {
                case "variant":
                    for (String subVariant : variantList) {
                        expectedVariant = subVariant;
                    }
                    break;
                case "subVariant":
                    expectedVariant = purchaseOrderCurrent;
            }
            if (!createDate.isEmpty()) {
                createDate = createdDate;
            }
            if (!quantity1.isEmpty()) {
                quantity1 = quantity;
            }
            productMoveSteps.enterValueAndVerify(condition, expectedVariant, createDate, type, quantity1, status);

        }
    }

    @And("Export variant by {string}")
    public void exportVariantBy(String condition) {
        productMoveSteps.clickBTExport();
        switch (condition) {
            case "Current page":
                productMoveSteps.clickSubmit();
                break;
            case "Current search & filter":
                productMoveSteps.clickCheckbox(condition);
                productMoveSteps.clickSubmit();
        }


    }

    @And("Verify info file export")
    public void verifyInfoFileExport(List<String> dataTable) {
        StockMove_Export_File_Download = productMoveSteps.openFileDownload();
        try {
            BufferedReader br = new BufferedReader(new FileReader(StockMove_Export_File_Download));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase());
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                Map<String, String> expExportProductMove = new HashMap<>();
                for (String column : dataTable) {
                    // lib doesn't support getting the first element (Product Name)
                    if("Product Name".equals(column)) {
                        expExportProductMove.put(column, csvRecord.get(0));
                    } else if("Date".equals(column)){
                        expExportProductMove.put(column, csvRecord.get(column).substring(0,csvRecord.get(column).lastIndexOf(" ")));
                    }
                    else {
                        expExportProductMove.put(column, csvRecord.get(column));
                    }
                }
                expExportProductMoveList.add(expExportProductMove);
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        productMoveSteps.verifyFileDownloadInfo(expExportProductMoveList);

    }

    @And("search in product move")
    public void searchInProductMove(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "condition");
            productMoveSteps.enterValue(condition);

        }
    }

    @And("get info variant inventory")
    public void getInfoVariantInventory() {
        productMoveSteps.getInfoVariantInventory(variantList);

    }

    @And("Click on view inventory detail {string}")
    public void clickOnViewInventoryDetail(String name, List<List<String>> dataTable) {
        ProductVariable.productName = name;
        mappingProductSteps.searchProductImport(name);
        productMoveSteps.clickViewInventoryDetail(name);
        productMoveSteps.accTab("Stock move");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            if(!variant.isEmpty()) {
                variantList.add(variant);
            }

        }
    }

    @Then("Verify noti {string}")
    public void verifyNoti(String noti) {
        assertThat(productMoveSteps.getTextNoti()).isEqualTo(noti);
    }

    @Then("verify noti export equal {string}")
    public void verifyNotiExportEqual(String mess) {
        assertThat(productMoveSteps.getTextMessDownloadSuccess()).isEqualTo(mess);
    }

    @And("Choose export {string} subvariant")
    public void chooseExportSubvariant(String amount) {
        productMoveSteps.clickChooseAmountExport(amount);

    }

    @And("get info variant inventory buy search")
    public void getInfoVariantInventoryBuSearch() {
        productMoveSteps.getInfoVariantInventoryBySearch(variantList.get(0));
    }
}

