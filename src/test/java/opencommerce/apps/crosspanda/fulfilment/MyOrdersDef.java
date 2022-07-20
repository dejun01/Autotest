package opencommerce.apps.crosspanda.fulfilment;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.MyOrdersSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep;
import com.opencsv.exceptions.CsvValidationException;
import common.CommonPageObject;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import static com.opencommerce.shopbase.OrderVariable.*;
import static junit.framework.TestCase.fail;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MyOrdersDef {
    CommonPageObject commonPageObject;
    @Steps
    MyOrdersSteps myOrdersSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;
    @Steps
    OrderSteps orderSteps;
    @Steps
    PBaseOrderStep pBaseOrderStep;

    String orderId = LoadObject.getProperty("order_id");
    String sizePage = LoadObject.getProperty("size_page");
    String shopImport = "Imported orders";

    public static String mappedProductOrder = "";
    public static String mappedProductOrderAS = "";
    public static String mappedProductOrderF = "";
    public static String unmapProductOrder = "";
    public static String byProduct = "";

    double totalSalsesCurrent = 0.00d;
    String totalSalesAfterCreatedOrder = "";


    public String getTheFirstOrderByProduct(String productName) {
        byProduct = myOrdersSteps.getTheFirstOrderByProduct(productName);
        return byProduct;
    }

    @Given("^verify order \"([^\"]*)\" is synced to CrossPanda$")
    public void verify_order_is_synced_to_CrossPanda(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String sync = SessionData.getDataTbVal(dataTable, row, "Is sync");
            boolean isSync = true;
            if (!sync.isEmpty()) {
                isSync = Boolean.parseBoolean(sync);
            }

            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(key);
            }
            myOrdersSteps.searchOrder(orderNumber);
            int i = 1;
            while (myOrdersSteps.isOrderExist(orderNumber) != isSync) {
                myOrdersSteps.waitABit(10000);
                commonXPandaSteps.refreshPage();
                i++;
                if (i == 7) {
                    break;
                }
            }
            myOrdersSteps.verifyOrderExist(orderNumber, isSync);
        }
    }

    @Given("^verify customer infor of order CrossPanda$")
    public void verify_customer_infor_of_order_CrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String aprt = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String zipcode = SessionData.getDataTbVal(dataTable, row, "Zipcode");

            String orderName = "";
            if (xOrderName.contains("@")) {
                orderName = getOrderName(xOrderName);
            }
            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.openCustomInfor();
            myOrdersSteps.verifyCustomerName(orderName, name);
            myOrdersSteps.verifyPhone(orderName, phone);
            myOrdersSteps.verifyCountry(orderName, country);
            myOrdersSteps.verifyAddress(orderName, address);
            myOrdersSteps.verifyApart(orderName, aprt);
            myOrdersSteps.verifyCity(orderName, city);
            myOrdersSteps.verifyState(orderName, state);
            myOrdersSteps.verifyZipcode(orderName, zipcode);
        }
    }

    @Given("^map order's product with product purchased on CrossPanda as \"([^\"]*)\"$")
    public void map_order_s_product_with_product_purchased_on_CrossPanda_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant");
            String odooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo product");
            String variantOdooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo option>Odoo option value");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");

            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(key);
            }

            if (!myOrdersSteps.isMappingPage()) {
                myOrdersSteps.searchOrder(orderNumber);
                int index = myOrdersSteps.getLineItem(productName);
                myOrdersSteps.clickBtnMapProduct(index);
            }


            myOrdersSteps.setProductMapping(odooProduct, variantOdooProduct);
            if (!msg.isEmpty()) {
                myOrdersSteps.clickBtnSaveMappingInvalid();
            } else {
                myOrdersSteps.clickBtnSaveMapping();
            }
            if (msg.isEmpty()) {
                myOrdersSteps.verifyMapSuccessfully();
            } else {
                myOrdersSteps.verifyMessage(msg);
            }
            myOrdersSteps.waitABit(3000);
            myOrdersSteps.clickBtnBackToOrder();
        }
    }

    @Given("check data mapping product then mapping product")
    public void check_data_mapping_product_then_mapping_product(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String odooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo product");
            String variantOdooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo option>Odoo option value");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");

            myOrdersSteps.searchOrder(product);
            if (!myOrdersSteps.isProductOdoo(product, odooProduct)) {
                myOrdersSteps.clickBtnMapProduct(1);
                myOrdersSteps.setProductMapping(odooProduct, variantOdooProduct);
                myOrdersSteps.clickBtnSaveMapping();
                if (msg.isEmpty()) {
                    myOrdersSteps.verifyMapSuccessfully();
                } else {
                    myOrdersSteps.verifyMessage(msg);
                }
                myOrdersSteps.waitABit(3000);
                myOrdersSteps.clickBtnBackToOrder();
            }

        }
    }

    @Given("^map order's product with product purchased on CrossPanda$")
    public void map_order_s_product_with_product_purchased_on_CrossPanda(List<List<String>> dataTable) {
        String orderName = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant");
            String odooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo product");
            String variantOdooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo option>Odoo option value");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");

            if (xOrderName.matches("@ByProduct@")) {
                String product = productName.split(">")[0];
                myOrdersSteps.searchOrder(product);
                orderName = getTheFirstOrderByProduct(product);
            } else {
                orderName = getOrderName(xOrderName);
            }


            if (!myOrdersSteps.isMappingPage()) {
                myOrdersSteps.searchOrder(orderName);
                int index = myOrdersSteps.getLineItem(productName);
                myOrdersSteps.clickBtnMapProduct(index);
            }

            myOrdersSteps.setProductMapping(odooProduct, variantOdooProduct);
            myOrdersSteps.clickBtnSaveMapping();
            if (msg.isEmpty()) {
                myOrdersSteps.verifyMapSuccessfully();
            } else {
                myOrdersSteps.verifyMessage(msg);
            }
            myOrdersSteps.waitABit(3000);
            myOrdersSteps.clickBtnBackToOrder();
        }
    }

    @And("^verify product mapping on CrossPanda as \"([^\"]*)\"$")
    public void verifyProductMappingOnCrossPandaAs(String dataKey, List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order key");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String productVariant = SessionData.getDataTbVal(dataTable, row, "Product variant");
            String purchasedProduct = SessionData.getDataTbVal(dataTable, row, "Purchased product");
            String variantOfPurchasedProduct = SessionData.getDataTbVal(dataTable, row, "Variant of product mapping");
            String messageError = SessionData.getDataTbVal(dataTable, row, "Message error");

            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(orderKey);
            }
            if (!myOrdersSteps.isMappingPage()) {
                myOrdersSteps.searchOrder(orderNumber);
                int index = myOrdersSteps.getLineItem(productName);
                myOrdersSteps.clickBtnMapProduct(index);
            }
            myOrdersSteps.verifyVariantOfSBProduct(productVariant);
            myOrdersSteps.verifyProductMapping(variantOfPurchasedProduct);
            myOrdersSteps.verifyMessage(messageError);
            myOrdersSteps.clickBtnBackToOrder();

        }
    }

    @And("^save the first order name in CrossPanda as \"([^\"]*)\"$")
    public void saveTheFirstOrderNameInCrossPandaAs(String dataKey, List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order key");
            myOrdersSteps.searchOrder("");
            String orderNumber = myOrdersSteps.getTheFirstOrderNumber();
            SessionData.saveDataByKey(orderKey, orderNumber);
        }
    }

    @And("^fulfill order from CrossPanda$")
    public void fulfillOrderFromCrossPandaAs(List<List<String>> dataTable) throws Throwable {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String orderName = "";
            if (xOrderNumber.contains("@")) {
                orderName = getOrderName(xOrderNumber);
            }
            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.clickBtnFulfillOrder();
        }
    }

    @Given("^cancel fulfillment order \"([^\"]*)\" from CrossPanda$")
    public void cancel_fulfillment_order_from_CrossPanda(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(orderKey);
            }
            myOrdersSteps.searchOrder(orderNumber);
            int index = myOrdersSteps.getIndexLineItem(orderNumber, productName, variant);
            myOrdersSteps.clickBtnCancelFulfillmentOrder(orderNumber, index);
        }
    }


    @And("^import order to CrossPanda by CSV file as \"([^\"]*)\"$")
    public void importOrderToCrossPandaByCSVFileAs(String dataKey, List<List<String>> dataTable) throws IOException, CsvValidationException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String csvFile = SessionData.getDataTbVal(dataTable, row, "CSV file");
            String productIdentity = SessionData.getDataTbVal(dataTable, row, "Product identity");
            String messageError = SessionData.getDataTbVal(dataTable, row, "Message error");
            String templateCSV = SessionData.getDataTbVal(dataTable, row, "Template");

            myOrdersSteps.clickBtnImportOrders();
            myOrdersSteps.selectProductIdentity(productIdentity);
            myOrdersSteps.loadFile(csvFile);
            if (!messageError.equalsIgnoreCase("File size must smaller than 1MB!")) {
                int lineItem = myOrdersSteps.countLineItemInPreview();
                assertThat(lineItem).isEqualTo(5);

                List<List<String>> expectedRessult = myOrdersSteps.readListOrderInCSV(csvFile, templateCSV, lineItem);
                List<List<String>> actualRessult = myOrdersSteps.getPreviewListOrder(lineItem);

                myOrdersSteps.verifyPreviewListOrder(actualRessult, expectedRessult);
                myOrdersSteps.clickBtnImport();
            }
            if (!messageError.isEmpty()) {
                myOrdersSteps.verifyMessage(messageError);
            }
        }
    }

    @And("^import order to CrossPanda by CSV file with Product identity is \"([^\"]*)\"$")
    public void importOrderToCrossPandaByCSVFile(String productIdentity, List<List<String>> dataTable) throws IOException {
        StringBuilder data = new StringBuilder();
        myOrdersSteps.clickBtnImportOrders();
        myOrdersSteps.selectProductIdentity(productIdentity);

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            data.append("\n").append(SessionData.getDataTbVal(dataTable, row, "Data"));
        }

        long currentTime = System.currentTimeMillis();
        String ts = Long.toString(currentTime);

        mappedProductOrder = "#MOM" + ts;
        mappedProductOrderAS = "#MOMAS" + ts;
        mappedProductOrderF = "#MOF" + ts;
        unmapProductOrder = "#MOU" + ts;

        myOrdersSteps.importCSVFileWithData(mappedProductOrder, mappedProductOrderAS, mappedProductOrderF, unmapProductOrder, data.toString());

    }

    @Given("^delete all order imported in CrossPanda$")
    public void deleteAllOrderImportedInCrossPanda() {
        myOrdersSteps.selectShop(shopImport);
        if (!myOrdersSteps.isPageEmpty()) {
            myOrdersSteps.checkAllOrdersImported();
            myOrdersSteps.selectAction("Delete orders");
            myOrdersSteps.waitABit(3);
            commonXPandaSteps.refreshPage();
        }
//        assertThat(myOrdersSteps.isPageEmpty()).isEqualTo(true);
    }


    @Then("^verify order are imported as \"([^\"]*)\"$")
    public void verify_order_are_imported_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            myOrdersSteps.selectShop(shopImport);
            assertThat(myOrdersSteps.isPageEmpty()).isNotEqualTo(true);
        }
    }

    @Given("^delete all order imported in CrossPanda as \"([^\"]*)\"$")
    public void delete_all_order_imported_in_CrossPanda_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            myOrdersSteps.selectShop(shopImport);
            if (!myOrdersSteps.isPageEmpty()) {
                myOrdersSteps.checkAllOrdersImported();
                myOrdersSteps.selectAction("Delete orders");
                myOrdersSteps.waitABit(3);
                commonXPandaSteps.refreshPage();
            }
            assertThat(myOrdersSteps.isPageEmpty()).isEqualTo(true);
        }
    }

    @And("^place order from Crosspanda$")
    public void placeOrderFromCrosspanda() {
        int page = myOrdersSteps.countPage();
        for (int i = page; i > 0; i--) {
            System.out.println("Page: " + i);
            myOrdersSteps.selectPage(i);
            myOrdersSteps.checkAllOrdersImported();
            myOrdersSteps.waitABit(5000);
            myOrdersSteps.selectAction("Fulfill orders");
            myOrdersSteps.waitABit(5000);
        }
    }

    @And("^place order from Crosspanda by API$")
    public void placeOrderFromCrosspandaByAPI() {
        String accToken = commonXPandaSteps.getAccessTokenXpanda();
        int page = myOrdersSteps.countPageOrder(accToken);
        for (int i = page; i >= 100; i--) {
            System.out.println("Page: " + i);
            List<Integer> listOrder = myOrdersSteps.getListOrderID(i, accToken);
            if (listOrder.size() > 0) {
                myOrdersSteps.placeOrder(listOrder, accToken);
                myOrdersSteps.waitABit(15000);
            }
        }
    }

    @Given("^replace product's order in CrossPanda as \"([^\"]*)\"$")
    public void replace_product_s_order_in_CrossPanda_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order key");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String replaceProduct = SessionData.getDataTbVal(dataTable, row, "Replace product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant of product mapping");
            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(orderKey);
            }
            myOrdersSteps.searchOrder(orderNumber);
            myOrdersSteps.replaceProduct();
            myOrdersSteps.searchReplaceProduct(replaceProduct);
            myOrdersSteps.selectVariant(variant);
            myOrdersSteps.clickSaveReplaceProduct();
        }
    }


    @Given("^mark as fulfilled order in CrossPanda as \"([^\"]*)\"$")
    public void mark_as_fulfilled_order_in_CrossPanda_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order key");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            myOrdersSteps.selectOrder(orderNumber, true);
            myOrdersSteps.markAsFulfilled();
        }
    }

    @Given("^change customer infor of order in CrossPanda$")
    public void change_customer_infor_of_order_in_CrossPanda_as(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String arp = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String zip = SessionData.getDataTbVal(dataTable, row, "Zipcode");
            String orderName = "";
            if (xOrderName.contains("@")) {
                orderName = getOrderName(xOrderName);
            }
            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.openCustomInfor();
            if (!name.isEmpty()) {
                myOrdersSteps.changeNameCS(orderName, name);
            }
            if (!phone.isEmpty()) {
                myOrdersSteps.changePhoneCS(orderName, phone);
            }
            if (!country.isEmpty()) {
                myOrdersSteps.changeCountryCS(orderName, country);
            }
            if (!address.isEmpty()) {
                myOrdersSteps.changeAddressCS(orderName, address);
            }
            if (!arp.isEmpty()) {
                myOrdersSteps.changeArpCS(orderName, arp);
            }
            if (!city.isEmpty()) {
                myOrdersSteps.changeCityCS(orderName, city);
            }
            if (!state.isEmpty()) {
                myOrdersSteps.changeStateCS(orderName, state);
            }
            if (!zip.isEmpty()) {
                myOrdersSteps.changeZipCS(orderName, zip);
            }
            myOrdersSteps.clickSaveChangeInforCS();
            myOrdersSteps.waitABit(2000);
        }
    }

    @Then("^verify order display$")
    public void verifyOrderDisplay() {
        myOrdersSteps.searchOrder(orderNumber);
        myOrdersSteps.verifyOrderExist(orderNumber, true);
    }

    @Then("^replace product in order with product \"([^\"]*)\"$")
    public void replaceProductInOrderWithProduct(String Product, List<List<String>> dataTable) {
        String orderName = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");

            orderName = getOrderName(xOrderName);

            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.replaceProduct();
            myOrdersSteps.selectProduct(Product);
//        myOrdersSteps.verifySizeProduct(sizePage);
            myOrdersSteps.clickSaveReplaceProduct();
            myOrdersSteps.clickBtnBackToOrder();
        }
    }

    @And("^verify information of the order$")
    public void verifyInformationOfTheOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String dataSB = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant>SKU>Quantity");
            String dataOdoo = SessionData.getDataTbVal(dataTable, row, "Product nameOdoo>Variant>SKU>Quantity");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String startTime = SessionData.getDataTbVal(dataTable, row, "Start Time");
            String endTime = SessionData.getDataTbVal(dataTable, row, "End Time");

            if (status.equals("Sent fulfillment request")) {
                if (!startTime.isEmpty()) {
                    myOrdersSteps.inputStartTime(startTime);
                }
                if (!endTime.isEmpty()) {
                    myOrdersSteps.inputEndTime(endTime);
                }
                myOrdersSteps.searchOrder(orderId);
                myOrdersSteps.refreshPage();
                String[] listDataSB = dataSB.split(">");
                int index = myOrdersSteps.getIndexLineItem(orderId, listDataSB[0], listDataSB[1]);
                String[] listDataOdoo = dataOdoo.split(">");

                //verify data SB
                myOrdersSteps.verifyProductNameSB(orderId, listDataSB[0], index);
                myOrdersSteps.verifyProductVariantSB(orderId, listDataSB[1], index);
                myOrdersSteps.verifySKUSB(orderId, listDataSB[2], index);
                myOrdersSteps.verifyQuantitySB(orderId, listDataSB[3], index);

                //verify dataOdoo
                if (!dataOdoo.isEmpty()) {
                    myOrdersSteps.verifyProductNameOdoo(orderId, listDataOdoo[0], index);
                    myOrdersSteps.verifyProductVariantOdoo(orderId, listDataOdoo[1], index);
                    myOrdersSteps.verifySKUOdoo(orderId, listDataOdoo[2], index);
                    myOrdersSteps.verifyQuantityOdoo(orderId, listDataOdoo[3], index);
                }
                //verify status lineitem
                myOrdersSteps.verifyStatusLineItem(orderId, status, index);
            } else {
                myOrdersSteps.searchOrder(orderNumber);
                myOrdersSteps.refreshPage();
                String[] listDataSB = dataSB.split(">");
                int index = myOrdersSteps.getIndexLineItem(orderNumber, listDataSB[0], listDataSB[1]);
                String[] listDataOdoo = dataOdoo.split(">");

                //verify data SB
                myOrdersSteps.verifyProductNameSB(orderNumber, listDataSB[0], index);
                myOrdersSteps.verifyProductVariantSB(orderNumber, listDataSB[1], index);
                myOrdersSteps.verifySKUSB(orderNumber, listDataSB[2], index);
                myOrdersSteps.verifyQuantitySB(orderNumber, listDataSB[3], index);

                //verify dataOdoo
                if (!dataOdoo.isEmpty()) {
                    myOrdersSteps.verifyProductNameOdoo(orderNumber, listDataOdoo[0], index);
                    myOrdersSteps.verifyProductVariantOdoo(orderNumber, listDataOdoo[1], index);
                    myOrdersSteps.verifySKUOdoo(orderNumber, listDataOdoo[2], index);
                    myOrdersSteps.verifyQuantityOdoo(orderNumber, listDataOdoo[3], index);
                }
                //verify status lineitem
                myOrdersSteps.verifyStatusLineItem(orderNumber, status, index);
            }
        }
    }

    @And("^verify information of the product variants in another order$")
    public void verifyInformationOfTheProductVariantsInAnotherOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String dataSB = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant>SKU>Quantity");
            String dataOdoo = SessionData.getDataTbVal(dataTable, row, "Product nameOdoo>Variant>SKU>Quantity");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            String[] part = orderNumber.split("(?<=\\D)(?=\\d)");
            String prefix = part[0];
            String numberOrder = part[1];
            int number = Integer.parseInt(numberOrder) - 1;
            String order = prefix + number;
            myOrdersSteps.searchOrder(order);
            String[] listDataSB = dataSB.split(">");
            int index = myOrdersSteps.getIndexLineItem(order, listDataSB[0], listDataSB[1]);
            String[] listDataOdoo = dataOdoo.split(">");

            //verify data SB
            myOrdersSteps.verifyProductNameSB(order, listDataSB[0], index);
            myOrdersSteps.verifyProductVariantSB(order, listDataSB[1], index);
            myOrdersSteps.verifySKUSB(order, listDataSB[2], index);
            myOrdersSteps.verifyQuantitySB(order, listDataSB[3], index);

            //verify dataOdoo
            if (!dataOdoo.isEmpty()) {
                myOrdersSteps.verifyProductNameOdoo(order, listDataOdoo[0], index);
                myOrdersSteps.verifyProductVariantOdoo(order, listDataOdoo[1], index);
                myOrdersSteps.verifySKUOdoo(order, listDataOdoo[2], index);
                myOrdersSteps.verifyQuantityOdoo(order, listDataOdoo[3], index);
            }
            //verify status lineitem
            myOrdersSteps.verifyStatusLineItem(order, status, index);
        }
    }

    @Then("^Change mapping in order with product$")
    public void changeMappingInOrderWithProduct(List<List<String>> dataTable) {
        String orderName = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String variantOfPurchasedProduct = SessionData.getDataTbVal(dataTable, row, "Variant of product mapping");
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");

            orderName = getOrderName(xOrderName);

            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.clickBtnChangeMapping();
            myOrdersSteps.searchProductMap(productName);
            boolean set = myOrdersSteps.verifySaveButtonDisable();
            if (!set) {
                myOrdersSteps.clickBtnSet();
            }
            if (!variantOfPurchasedProduct.isEmpty()) {
                myOrdersSteps.setVariant(variantOfPurchasedProduct);
            }
            myOrdersSteps.clickBtnSaveMapping();
            myOrdersSteps.clickBtnBackToOrder();
        }
    }

    @Then("^replace product in all order with product \"([^\"]*)\"$")
    public void replaceProductInAllOrderWithProduct(String nameProduct, List<List<String>> dataTable) {
        String orderName = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String product = SessionData.getDataTbVal(dataTable, row, "Product name SB");

            if (xOrderName.matches("@ByProduct@")) {
                myOrdersSteps.searchOrder(product);
                orderName = getTheFirstOrderByProduct(product);
            } else {
                orderName = getOrderName(xOrderName);
            }

            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.replaceProduct();
            myOrdersSteps.selectProduct(nameProduct);
            myOrdersSteps.clickReplaceAll();
            myOrdersSteps.clickSaveReplaceProduct();
            myOrdersSteps.clickBtnBackToOrder();
        }
    }

//    @And("^verify information of the product variants in all order$")
//    public void verifyInformationOfTheProductVariantsInAllOrder(List<List<String>> dataTable) {
//        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
//            String dataSB = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant>SKU>Quantity");
//            String dataOdoo = SessionData.getDataTbVal(dataTable, row, "Product nameOdoo>Variant>SKU>Quantity");
//            String status = SessionData.getDataTbVal(dataTable, row, "Status");
//
//            String[] part = orderNumber.split("(?<=\\D)(?=\\d)");
//            String prefix = part[0];
//            String numberOrder = part[1];
//            int number = Integer.parseInt(numberOrder) - 1;
//            for (int i = number; i <= Integer.parseInt(numberOrder); i++) {
//                String order = prefix + i;
//                myOrdersSteps.searchOrder(order);
//                String[] listDataSB = dataSB.split(">");
//                int index = myOrdersSteps.getIndexLineItem(order, listDataSB[0], listDataSB[1]);
//                String[] listDataOdoo = dataOdoo.split(">");
//
//                //verify data SB
//                myOrdersSteps.verifyProductNameSB(order, listDataSB[0], index);
//                myOrdersSteps.verifyProductVariantSB(order, listDataSB[1], index);
//                myOrdersSteps.verifySKUSB(order, listDataSB[2], index);
//                myOrdersSteps.verifyQuantitySB(order, listDataSB[3], index);
//
//                //verify dataOdoo
//                if (!dataOdoo.isEmpty()) {
//                    myOrdersSteps.verifyProductNameOdoo(order, listDataOdoo[0], index + 1);
//                    myOrdersSteps.verifyProductVariantOdoo(order, listDataOdoo[1], index + 1);
//                    myOrdersSteps.verifySKUOdoo(order, listDataOdoo[2], index + 1);
//                    myOrdersSteps.verifyQuantityOdoo(order, listDataOdoo[3], index + 1);
//                }
//                //verify status lineitem
//                myOrdersSteps.verifyStatusLineItem(order, status, index);
//            }
//        }
//    }

    @Then("^Fulfill order in CrossPanda$")
    public void fulfillOrderInCrossPanda(List<List<String>> dataTable) {
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");

            orderName = getOrderName(xOrderName);

            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.clickBtnFulfillOrder();
        }
    }

    public static String getOrderName(String xOrderName) {
        if (xOrderName.matches("@OrderName@")) {
            return OrderVariable.orderNumber;
        } else if (xOrderName.matches("@OrderProductMappedImported@")) {
            return MyOrdersDef.mappedProductOrder;
        } else if (xOrderName.matches("@OrderProductMappedImportedAS@")) {
            return MyOrdersDef.mappedProductOrderAS;
        } else if (xOrderName.matches("@OrderProductMappedImportedF@")) {
            return MyOrdersDef.mappedProductOrderF;
        } else if (xOrderName.matches("@OrderProductUnmapImported@")) {
            return MyOrdersDef.unmapProductOrder;
        } else {
            return xOrderName;
        }
    }

    @Then("^mask as fulfill order in CrossPanda with action \"([^\"]*)\"$")
    public void maskAsFulfillOrderInCrossPandaWithAction(String action) {
        myOrdersSteps.searchOrder(orderNumber);
        myOrdersSteps.selectOrder(orderNumber, true);
        myOrdersSteps.selectAction(action);
    }

    @Then("^hold order in CrossPanda with action \"([^\"]*)\"$")
    public void holdOrderInCrossPandaWithAction(String action) {
        String[] part = orderNumber.split("(?<=\\D)(?=\\d)");
        String prefix = part[0];
        String numberOrder = part[1];
        int number = Integer.parseInt(numberOrder) - 1;
        for (int i = number; i <= Integer.parseInt(numberOrder); i++) {
            String order = prefix + i;
            myOrdersSteps.selectOrder(order, true);
        }
        myOrdersSteps.selectAction(action);
    }

    @And("^import order to CrossPanda by CSV file without collumn or collumn blank as \"([^\"]*)\"$")
    public void importOrderToCrossPandaByCSVFileWithoutCollumnOrCollumnBlankAs(String
                                                                                       dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String csvFile = SessionData.getDataTbVal(dataTable, row, "CSV file");
            String productIdentity = SessionData.getDataTbVal(dataTable, row, "Product identity");
            String messageError = SessionData.getDataTbVal(dataTable, row, "Message error");

            myOrdersSteps.clickBtnImportOrders();
            myOrdersSteps.selectProductIdentity(productIdentity);
            myOrdersSteps.loadFile(csvFile);
            myOrdersSteps.clickBtnImport();
            if (!messageError.isEmpty()) {
                myOrdersSteps.verifyMessage(messageError);
            }
        }
    }

    @Then("^verify customer infor of order in Shopbase$")
    public void verify_customer_infor_of_order_in_Shopbase_as(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String sPhoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sApartment = SessionData.getDataTbVal(dataTable, row, "Apart");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sZipcode = SessionData.getDataTbVal(dataTable, row, "Zipcode");
            String orderName = "";
            if (xOrderName.contains("@")) {
                orderName = getOrderName(xOrderName);
            }
            orderSteps.enterTextThenEnter(orderName);
            orderSteps.selectTheNewestOrder();
            orderSteps.verifyAddressOnDB("SHIPPING ADDRESS", sFirstName + " " + sLastName + " " + sAddress + " " + sApartment + " " + sCity + " " + sZipcode + " " + sState + " " + sCountry + " " + sPhoneNumber);
        }
    }

    @Then("^Verify status of Order on CrossPanda$")
    public void verifyStatusOfOrderOnCrossPanda(List<List<String>> dataTable) {
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            orderName = getOrderName(xOrderName);

            searchOrderOnCrossPanda(orderName);
            myOrdersSteps.verifyStatusOfOrderOnCrossPanda(orderName, status);

        }
    }

    @Then("^Search order \"([^\"]*)\" on CrossPanda$")
    public void searchOrderOnCrossPanda(String xOrderName) {
        String orderName = "";

        orderName = getOrderName(xOrderName);

        myOrdersSteps.searchOrderOnCrossPanda(orderName);
    }

    @Then("^Select paging \"([^\"]*)\"$")
    public void SelectPaging(String paging) {
        myOrdersSteps.selectPaging(paging);
    }

    @And("clear search order on CrossPanda")
    public void clearSearchOrderOnCrossPanda() {
        myOrdersSteps.searchOrderOnCrossPanda("");
    }

    @Then("^(Select|Deselect) order$")
    public void selectOrder(String select, List<List<String>> dataTable) {
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");

            orderName = getOrderName(xOrderName);

            switch (select) {
                case "Select":
                    myOrdersSteps.selectOrder(orderName, true);
                    break;
                case "Deselect":
                    myOrdersSteps.selectOrder(orderName, false);

                    break;
                default:
                    fail();
            }
        }
    }

    @And("^\"([^\"]*)\" to select action Delete orders$")
    public void toSelectActionDeleteOrders(String able) {
        myOrdersSteps.verifyAbleToSelectActionDelete(able);
    }

    @Then("^Select action \"([^\"]*)\" on My orders screen$")
    public void selectActionOnMyOrdersScreen(String action) {
        myOrdersSteps.selectAction(action);
    }

    @Given("get tracking number as {string}")
    public void get_tracking_number_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderNumber = "";
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String xOrderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            if (!orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(key);
            } else {
                orderNumber = getOrderName(xOrderNumber);
            }
            myOrdersSteps.searchOrder(orderNumber);
            myOrdersSteps.selectOrder(orderNumber, true);
            myOrdersSteps.getTrackingNumber();
            int i = 1;
            while (myOrdersSteps.isTrackingNumber(orderNumber) != true) {
                myOrdersSteps.waitABit(10000);
                myOrdersSteps.searchOrder(orderNumber);
                i++;
                if (i == 7) {
                    break;
                }
            }
        }

    }

    @Given("verify button Cancel fulfillment as {string}")
    public void verify_button_Cancel_fulfillment_as(String dataKey, List<List<String>> dataTable) {
    }

    @Then("verify base product")
    public void verify_base_product(List<List<String>> dataTable) {
        String orderName = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String optionVariant = SessionData.getDataTbVal(dataTable, row, "Option>Variant");
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            orderName = getOrderName(xOrderName);

            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.clickBtnMapProduct();
            myOrdersSteps.verifyBaseProductName(product);
            myOrdersSteps.verifyOptionValueBaseProduct(optionVariant);
        }
    }

    @And("^verify information order of CrossPanda$")
    public void verifyInformationOrderOfCrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String dataSB = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant>SKU>Quantity");
            String dataOdoo = SessionData.getDataTbVal(dataTable, row, "Product nameOdoo>Variant>SKU>Quantity");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String shippingName = SessionData.getDataTbVal(dataTable, row, "Shipping name");
            String shippingCost = SessionData.getDataTbVal(dataTable, row, "Shipping cost");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            Boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            String[] listDataSB = dataSB.split(">");
            String[] listDataOdoo = dataOdoo.split(">");
            String orderName = "";
            if (xOrderName.contains("ByProduct")) {
                orderName = getTheFirstOrderByProduct(listDataSB[0]);
            } else {
                orderName = getOrderName(xOrderName);
            }
            myOrdersSteps.searchOrder(orderName);
            int index = myOrdersSteps.getIndexLineItem(orderName, listDataSB[0], listDataSB[1]);
            if (isShow) {
                //verify data SB
                myOrdersSteps.verifyProductNameSB(orderName, listDataSB[0], index);
                myOrdersSteps.verifyProductVariantSB(orderName, listDataSB[1], index);
                myOrdersSteps.verifySKUSB(orderName, listDataSB[2], index);
                myOrdersSteps.verifyQuantitySB(orderName, listDataSB[3], index);

                //verify dataOdoo
                if (!dataOdoo.isEmpty()) {
                    myOrdersSteps.verifyProductNameOdoo(orderName, listDataOdoo[0], index);
                    myOrdersSteps.verifyProductVariantOdoo(orderName, listDataOdoo[1], index);
                    myOrdersSteps.verifySKUOdoo(orderName, listDataOdoo[2], index);
                    myOrdersSteps.verifyQuantityOdoo(orderName, listDataOdoo[3], index);
                }
                //verify status lineitem
                if (!status.isEmpty()) {
                    myOrdersSteps.verifyStatusLineItem(orderName, status, index);
                }
                if (!shippingName.isEmpty()) {
                    myOrdersSteps.verifyShippingName(orderName, shippingName, index);
                }
                if (!shippingCost.isEmpty()) {
                    myOrdersSteps.verifyShippingCost(orderName, shippingCost, index);
                }
                if (!trackingNumber.isEmpty()) {
                    myOrdersSteps.verifyTrackingNumber(orderName, trackingNumber, index);
                }
            } else {
                myOrdersSteps.verifyHasNotLineitemInApp(orderName, listDataSB[0]);
            }
        }
    }

    @Given("remove base product {string}")
    public void remove_base_product(String baseProduct) {
        commonXPandaSteps.removeBaseProduct(baseProduct);
    }

    @Given("verify some field order pod on CrossPanda")
    public void verify_some_field_order_pod_on_CrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String productName = SessionData.getDataTbVal(dataTable, row, "ProductName");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String mockup = SessionData.getDataTbVal(dataTable, row, "Mockup");
            String artFont = SessionData.getDataTbVal(dataTable, row, "Artwork font");
            String artBack = SessionData.getDataTbVal(dataTable, row, "Artwork back");
            String designCode = SessionData.getDataTbVal(dataTable, row, "Design code");
            String baseCost = SessionData.getDataTbVal(dataTable, row, "Basecost");
            String supplierName = SessionData.getDataTbVal(dataTable, row, "SupplierName");
            String shippingmethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
            Boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            String orderName = "";
            orderName = getOrderName(xOrderName);
            if (isShow) {
                myOrdersSteps.searchOrder(orderName);
                int index = myOrdersSteps.getIndexLineItem(orderName, productName, variant);

                if (!mockup.isEmpty()) {
                    myOrdersSteps.verifyMockup(orderName, mockup, index);
                }
                if (!artFont.isEmpty()) {
                    myOrdersSteps.verifyArtworkFront(orderName, artFont, index);
                }
                if (!artBack.isEmpty()) {
                    myOrdersSteps.verifyArtworkBack(orderName, artBack, index);
                }
                if (!designCode.isEmpty()) {
                    myOrdersSteps.verifyDesigncode(orderName, designCode, index);
                }
                if (!baseCost.isEmpty()) {
                    myOrdersSteps.verifyBaseCost(orderName, baseCost, index);
                }
                if (!supplierName.isEmpty()) {
                    myOrdersSteps.verifySupplierName(orderName, supplierName, index);
                }
                if (!shippingmethod.isEmpty()) {
                    myOrdersSteps.verifyShippingName(orderName, shippingmethod, index);
                }
            }

        }
    }

    @Given("fulfill order POD from CrossPanda")
    public void fulfill_order_POD_from_CrossPanda() {
        String orderId = String.valueOf(OrderVariable.orderId);
        myOrdersSteps.searchOrder(orderId);
        myOrdersSteps.clickBtnFulfillOrder();
    }

    @Given("create fulfillment with order SP by API")
    public void create_fulfillment_with_order_SP_by_API(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderNumber = SessionData.getDataTbVal(dataTable, row, "Order name");
            String carrier = SessionData.getDataTbVal(dataTable, row, "carrier");
            String tkn = SessionData.getDataTbVal(dataTable, row, "Tracking Number");
            if (xOrderNumber.contains("@")) {
                xOrderNumber = getOrderName(xOrderNumber);
            }
            String accessToken = myOrdersSteps.getAccessToken();
            String supplierOrderId = myOrdersSteps.getSupplierOrderIdByAPI(xOrderNumber, accessToken);
            myOrdersSteps.createFulfillmentByAPI(supplierOrderId, carrier, tkn);
        }
    }

    @Then("verify order after fulfilled")
    public void verify_order_after_fulfilled(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tkn = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            int orderId = OrderVariable.orderId;
            pBaseOrderStep.navigateToPBase(orderId);
            pBaseOrderStep.verifyTKNOrder(tkn);
        }
    }

    @And("verify information order on CrossPanda")
    public void verifyInformationOrderOnCrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String infoOrderPbase = SessionData.getDataTbVal(dataTable, row, "Product nameSB-Variant>SKU>Quantity");
            String infoOdoo = SessionData.getDataTbVal(dataTable, row, "Product nameOdoo-Variant>SKU>Quantity");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String shippingName = SessionData.getDataTbVal(dataTable, row, "Shipping name");
            String shippingCost = SessionData.getDataTbVal(dataTable, row, "Shipping cost");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String deliveryTime = SessionData.getDataTbVal(dataTable, row, "Estimated delivery time");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String[] listDataSB = infoOrderPbase.split(">");
            String orderName = "";
            if (xOrderName.contains("ByProduct")) {
                orderName = getTheFirstOrderByProduct(listDataSB[0]);
            } else {
                orderName = getOrderName(xOrderName);
            }
            myOrdersSteps.searchOrder(orderName);
            myOrdersSteps.verifyInfoPbase(infoOrderPbase);
            myOrdersSteps.verifyInfodoo(infoOdoo);
            myOrdersSteps.verifyStatus(status);
            myOrdersSteps.chooseVendor(vendor);
            myOrdersSteps.verifyInfoshippingName(shippingName, shippingCost, trackingNumber, deliveryTime);
        }

    }

    @And("get Total sales")
    public void getTotalSales() {
        totalSalsesCurrent = myOrdersSteps.getTotalSalesCurrent();
    }

    @And("Verify Total sales after created order")
    public void verifyTotalSalesAfterCreatedOrder() {
        totalSalesAfterCreatedOrder = roundOff(totalSalsesCurrent + Double.parseDouble(totalAmt.replace("$","").replace("USD","").trim()));
        assertThat(roundOff(myOrdersSteps.getTotalSalesCurrent())).isEqualTo(totalSalesAfterCreatedOrder);
    }

    private String roundOff(double d) {
        return String.format("%1.2f", d);
    }

    @And("search and select order on shopbase")
    public void searchAndSelectOrderOnShopbase() {
            myOrdersSteps.searchOrder(orderNumber);
            orderSteps.clickNameOrderOnList(orderNumber);
    }


}

