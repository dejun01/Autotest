package opencommerce.products.dashboard;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static opencommerce.products.dashboard.ProductDetailDef.nameProductSbase;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CollectionDetailDef {

    @Steps
    CollectionDetailSteps collectionDetailSteps;

    @Steps
    ProductDetailSteps productDetailSteps;

    @Steps
    CollectionListSteps collectionListSteps;

    @Steps
    CommonSteps commonSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;

    public static Integer quotaCollection;
    public static String collectionNameAuto;
    public static String collectionNameManual;
    public static String collectionName;
    public static int totalCollection;

    @Given("^Create collection with data$")
    public void createCollectionWithData(List<List<String>> dataTable) {
        totalCollection = 0;
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            collectionListSteps.clickAddCollection();
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description (optional)");
            String type = SessionData.getDataTbVal(dataTable, row, "Collection type");
            String condition = SessionData.getDataTbVal(dataTable, row, "Conditions");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String metaDescription = SessionData.getDataTbVal(dataTable, row, "Meta description");
            String handle = SessionData.getDataTbVal(dataTable, row, "Handle");

            if (title.matches("@(.*)@") && title.contains("Manual")) {
                long currentTime = System.currentTimeMillis();
                collectionNameManual = title.replaceAll("@", "") + "_" + currentTime;
                collectionDetailSteps.enterColTitle(collectionNameManual);
            } else if (title.matches("@(.*)@") && title.contains("Auto")) {
                long currentTime = System.currentTimeMillis();
                collectionNameAuto = title.replaceAll("@", "") + "_" + currentTime;
                collectionDetailSteps.enterColTitle(collectionNameAuto);
            } else {
                collectionDetailSteps.enterColTitle(title);
            }

            if (!"".equals(description)) {
                collectionDetailSteps.enterCollectionDescription(description);
            }

            collectionDetailSteps.chooseColType(type);
            if (type.equalsIgnoreCase("Automated")) {
                List<String> conditions = new ArrayList<String>(Arrays.asList(condition.split(",")));
                String conType = conditions.get(0);
                String prodProperties = conditions.get(1);
                String cons = conditions.get(2);
                String conValue = conditions.get(3);
                collectionDetailSteps.chooseConditionType(conType);
                collectionDetailSteps.selectProdProperties(prodProperties);
                collectionDetailSteps.selectCondition(cons);
                collectionDetailSteps.inputConValue(conValue);
            }

            if (!pageTitle.isEmpty() && !metaDescription.isEmpty()) {
                productDetailSteps.clickAddSeo(pageTitle, metaDescription);
            }

            collectionDetailSteps.clickSaveCollection();
            if (!"".equals(message)) {
                collectionDetailSteps.verifyMessageCollection(message);
            }
            if (!handle.isEmpty()) {
                collectionDetailSteps.editWebsiteSeo();
                collectionDetailSteps.verifyHandle(handle);
            }
            totalCollection += 1;
            if (SessionData.getDataTbRowsNoHeader(dataTable).size() > 1 && row != SessionData.getDataTbRowsNoHeader(dataTable).size()) {
                productDetailSteps.navigateToCollectionsScreen();
            }
        }
    }

    @And("^add product to collection$")
    public void addProductToCollection(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String prodName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String colType = SessionData.getDataTbVal(dataTable, row, "Collection type");
            String colTitle = SessionData.getDataTbVal(dataTable, row, "Collection title");
            String colCondition = SessionData.getDataTbVal(dataTable, row, "Collection conditions");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            if (prodName.matches("@(.*)@") && colTitle.matches("@(.*)@") && colTitle.contains("Manual")) {
                colTitle = collectionNameManual;
            } else if (prodName.matches("@(.*)@") && colTitle.matches("@(.*)@") && colTitle.contains("Auto")) {
                colTitle = collectionNameAuto;
            }
            collectionListSteps.searchCollection(colTitle);
            collectionListSteps.chooseCollection(colTitle);

            if (colType.equals("Manual")) {
                if (colTitle.matches("@(.*)@")) {
                    prodName = ProductDetailDef.nameProductSbase;
                }
                collectionDetailSteps.clickAddProduct();
                String[] listProduct = prodName.split(",");
                for (String product : listProduct)
                    collectionDetailSteps.addProductToCollection(product);
                collectionDetailSteps.clickSaveButton();
            } else {
                collectionDetailSteps.inputProductInfor(colCondition);
                collectionDetailSteps.clickSave();
            }
            if (!message.isEmpty()) {
                collectionDetailSteps.verifyMessageCollection(message);
            }
            collectionDetailSteps.clickBreadcrumbCollections();
        }
    }

    @Given("^verify product added or deleted in collection$")
    public void verifyProductAddInCollection(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String prodName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String colTitle = SessionData.getDataTbVal(dataTable, row, "Collection title");

            if (prodName.matches("@(.*)@") && colTitle.matches("@(.*)@") && colTitle.contains("Manual")) {
                colTitle = collectionNameManual;
                prodName = nameProductSbase;
            } else if (colTitle.matches("@(.*)@") && colTitle.contains("Auto")) {
                colTitle = collectionNameAuto;
                prodName = nameProductSbase;
            }
            collectionListSteps.searchCollection(colTitle);
            collectionListSteps.chooseCollection(colTitle);
            collectionDetailSteps.verifyProductOfCollection(prodName);
        }
    }

    @And("^Verify conditions and value$")
    public void verifyConditionsAndValue(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String conditions = SessionData.getDataTbVal(dataTable, row, "Conditions");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");

            List<String> condition = new ArrayList<String>(Arrays.asList(conditions.split(",")));
            String conditionType = condition.get(0);
            String conditionOption = condition.get(1);
            String conditionValue = condition.get(2);

            collectionDetailSteps.addCondition();
            collectionDetailSteps.selectProdProperties(conditionType);
            collectionDetailSteps.selectCondition(conditionOption);
            collectionDetailSteps.inputConValue(conditionValue);
            collectionDetailSteps.clickAnyWhere();
            collectionDetailSteps.verifyConditionValue(value);
            collectionDetailSteps.deleteCondition();
        }
    }

    @And("^Edit coditions with empty value$")
    public void editCoditionsWithEmptyValue(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String conditions = SessionData.getDataTbVal(dataTable, row, "Conditions");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            List<String> condition = new ArrayList<String>(Arrays.asList(conditions.split(",")));
            String conditionType = condition.get(0);
            String conditionOption = condition.get(1);
            String conditionValue = condition.get(2).replace(condition.get(2), "");

            collectionDetailSteps.addCondition();
            collectionDetailSteps.selectProdProperties(conditionType);
            collectionDetailSteps.selectCondition(conditionOption);
            collectionDetailSteps.inputConValue(conditionValue);
            collectionDetailSteps.clickSave();
            collectionDetailSteps.verifyMessageError(message);
            collectionDetailSteps.clickDiscard();
        }
    }

    @And("^update rule Automated collection and verify product with conditions$")
    public void updateRuleAutomatedCollectionAndVerifyProductWithConditions(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String colTitle = SessionData.getDataTbVal(dataTable, row, "Collection title");
            String colCondition = SessionData.getDataTbVal(dataTable, row, "Collection conditions");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            if (colTitle.matches("@(.*)@") && colTitle.contains("Manual")) {
                collectionListSteps.searchCollection(collectionNameManual);
                collectionListSteps.chooseCollection(collectionNameManual);
            } else if (colTitle.matches("@(.*)@") && colTitle.contains("Auto")) {
                collectionListSteps.searchCollection(collectionNameAuto);
                collectionListSteps.chooseCollection(collectionNameAuto);
            } else {
                collectionListSteps.searchCollection(colTitle);
                collectionListSteps.chooseCollection(colTitle);
            }

            collectionDetailSteps.inputProductInfor(colCondition);
            collectionDetailSteps.clickSave();
            if (!message.isEmpty()) {
                collectionDetailSteps.verifyMessageCollection(message);
            }
        }
    }

    @Then("^verify sort products display correctly$")
    public void verifySortProductsDisplayCorrectly(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sortType = SessionData.getDataTbVal(dataTable, row, "Sort type");
            String nameProduct = SessionData.getDataTbVal(dataTable, row, "Name Product");
            String numberProduct = SessionData.getDataTbVal(dataTable, row, "Number product");
            String messageSort = SessionData.getDataTbVal(dataTable, row, "Message sort");
            boolean check;

            collectionDetailSteps.chooseTypeSort(sortType);
            if (!messageSort.isEmpty()) {
                collectionDetailSteps.verifyMessageCollection(messageSort);
            }
            collectionDetailSteps.clickRefresh();
            if (!nameProduct.isEmpty())
                collectionDetailSteps.verifySort(nameProduct);
            if (!numberProduct.isEmpty()) {
                do {
                    commonSteps.refreshPage();
                    collectionDetailSteps.showMoreProduct();
                    check = collectionDetailSteps.verifySyncEnoughProduct(numberProduct);
                } while (!check);
            }
        }
    }

    @Then("^Verify product of collection$")
    public void verifyProductOfCollection() {
        collectionDetailSteps.verifyProductOfCollection(nameProductSbase);
        collectionDetailSteps.verifyProductOfCollection("Copy of " + nameProductSbase);
    }

    @And("^edit collection$")
    public void editCollection(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String valueConditions = SessionData.getDataTbVal(dataTable, row, "Value conditions");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String handle = SessionData.getDataTbVal(dataTable, row, "Handle");

            collectionListSteps.searchCollection(title);
            collectionListSteps.chooseCollection(title);

            collectionDetailSteps.inputConValue(valueConditions);
            collectionDetailSteps.clickSave();
            collectionDetailSteps.verifyMessageCollection(message);
            collectionDetailSteps.verifyHandle(handle);
        }
    }

    @And("edit handle")
    public void editHandle(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            collectionListSteps.clickAddCollection();
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String handleUpdate = SessionData.getDataTbVal(dataTable, row, "Handle update");
            String handleAfter = SessionData.getDataTbVal(dataTable, row, "Handle after");

            collectionListSteps.openDetailColection(title);
            collectionDetailSteps.editWebsiteSeo();
            collectionDetailSteps.editHandle(handleUpdate);
            collectionDetailSteps.clickSave();
            collectionDetailSteps.editWebsiteSeo();
            collectionDetailSteps.verifyHandle(handleAfter);
            collectionDetailSteps.clickBreadcrumbCollections();
        }
    }

    @When("update product in manual sort")
    public void updateProductInManualSort(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sortType = SessionData.getDataTbVal(dataTable, row, "Sort type");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String listProduct = SessionData.getDataTbVal(dataTable, row, "List product");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");

            if (!sortType.isEmpty()) {
                collectionDetailSteps.chooseTypeSort(sortType);
            }

            String[] list = product.split(",");
            for (String namProduct : list) {
                collectionDetailSteps.selectProduct(namProduct);
            }

            if (!action.isEmpty()) {
                collectionDetailSteps.selectAction(action);
            }

            if (!position.isEmpty()) {
                collectionDetailSteps.verifyStatus(position);
            }
            collectionDetailSteps.clickMove();
            collectionDetailSteps.verifySort(listProduct);
        }
    }

    @And("verify product status in collection")
    public void verifyProductStatusInCollection(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String iconOnlineStoreListingPages = SessionData.getDataTbVal(dataTable, row, "Icon Online store listing pages");
            String iconSearchEngine = SessionData.getDataTbVal(dataTable, row, "Icon Search Engine Bot Crawlers, Sitemap files");

            collectionDetailSteps.verifyStatus(product, status);
            collectionDetailSteps.verifyProductAvailability(product, iconOnlineStoreListingPages, iconSearchEngine);
        }
    }

    @And("get number of collection created by API")
    public void getNumberOfCollectionCreatedByAPI() {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        quotaCollection = printbaseAPI.getNumberCollectionCreated(accessToken);
        System.out.println(quotaCollection);
    }

    @Then("verify number of collection created after create {string} collections by API")
    public void verifyNumberOfCollectionCreatedAfterCreateCollectionsByAPI(String sNumber) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        Integer number = Integer.parseInt(sNumber);
        Integer quotaActual = printbaseAPI.getNumberCollectionCreated(accessToken);
        Integer quotaExpected = quotaCollection + number;
        System.out.println(quotaExpected);
        assertThat(quotaActual).isEqualTo(quotaExpected);
    }

    @And("Setup product thumbnail on collection")
    public void setupProductThumbnailOnCollection(List<List<String>> dataTable) {
        collectionDetailSteps.clickAddProductThumbnail();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String optionName = SessionData.getDataTbVal(dataTable, row, "Option name");
            String optionValue = SessionData.getDataTbVal(dataTable, row, "Option value");

            collectionDetailSteps.addProductThumbnail(optionName, optionValue, row);
            if (row != SessionData.getDataTbRowsNoHeader(dataTable).size())
                collectionDetailSteps.clickAddOption();
        }
        collectionDetailSteps.clickSaveCollection();
    }

    @And("verify collection has {string} products")
    public void verifyCollectionHasProducts(String _snumberCollection) {
        collectionDetailSteps.clickShowMore();
        collectionDetailSteps.verifyCollectionHasProducts(_snumberCollection);
    }
}
