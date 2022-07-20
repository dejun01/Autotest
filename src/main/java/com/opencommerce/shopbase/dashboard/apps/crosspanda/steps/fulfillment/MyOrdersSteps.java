package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.exceptions.CsvValidationException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.fulfillment.MyOrdersPage;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.form;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MyOrdersSteps extends ScenarioSteps {
    MyOrdersPage myOrdersPage;

    @Step
    public void searchOrder(String orderNumber) {
//        String shop = LoadObject.getProperty("shop");
        if (!orderNumber.isEmpty()) {
            myOrdersPage.typeAndEnterInputFieldWithLabel("Search by Product name, order no, tracking number", orderNumber);
//            selectShop(shop);
            myOrdersPage.waitUntilInvisibleLoading(10);
        }
    }

    @Step
    public String getTheFirstOrderByProduct(String productName) {
        return myOrdersPage.getTheFirstOrderByProduct(productName);
    }

    @Step
    public void selectShop(String shop) {
        myOrdersPage.selectShop(shop);
        waitABit(1000);
    }

    @Step
    public boolean isOrderExist(String orderNumber) {
        return myOrdersPage.isOrderExist(orderNumber);
    }

    @Step
    public void verifyOrderExist(String orderNumber, boolean isExist) {
        assertThat(isOrderExist(orderNumber)).isEqualTo(isExist);
    }

    @Step
    public int getLineItem(String productName) {
        String name = productName;
        String variant = "";
        if (productName.contains(">")) {
            String value[] = productName.split(">");
            name = value[0];
            variant = value[1];
        }
        return myOrdersPage.getLineItem(name, variant);
    }


    @Step
    public void verifyStatusOrder(String status, int index) {
        myOrdersPage.verifyStatusOrder(status, index);
    }

    @Step
    public void clickBtnMapProduct(int index) {
        myOrdersPage.clickMapProduct(index);
    }

    @Step
    public void setProductMapping(String odooProduct, String variantOdooProduct) {
        myOrdersPage.selectOdooProduct(odooProduct);
        setVariant(variantOdooProduct);
    }

    @Step
    public void setVariant(String variantOdooProduct) {
        if (!variantOdooProduct.isEmpty()) {
            if (variantOdooProduct.contains(";")) {
                String options[] = variantOdooProduct.split(";");
                for (int i = 0; i < options.length; i++) {
                    setOptionOfVariant(options[i], i + 1);
                }
            } else {
                setOptionOfVariant(variantOdooProduct, 1);
            }
        }
    }

    @Step
    public void setOptionOfVariant(String options, int index) {
        if (options.contains(">")) {
            String value[] = options.split(">");
            myOrdersPage.selectOption(value[0], index);
            myOrdersPage.selectValueOption(value[1], index);
        } else {
            myOrdersPage.selectOption(options, index);
        }
    }

    @Step
    public void verifyProductMapping(String productMapping) {
        if (!productMapping.isEmpty()) {
            String options[] = productMapping.split(";");
            for (int i = 0; i < options.length; i++) {
                verifyOptionOfVariant_ProductMapping(options[i], i + 1);
            }
        } else {
            myOrdersPage.verifyProductMappingExisted(false);
        }
    }

    @Step
    private void verifyOptionOfVariant_ProductMapping(String options, int index) {
        String value[] = options.split(">");
        myOrdersPage.verifyOption_ProductMapping(value[0], index);
        myOrdersPage.verifyValueOption_ProductMapping(value[1], index);
    }

    @Step
    public void verifyVariantOfSBProduct(String productVariant) {
        if (!productVariant.isEmpty()) {
            String options[] = productVariant.split(";");
            for (int i = 0; i < options.length; i++) {
                verifyOptionOfVariant_SBProduct(options[i], i + 1);
            }
        } else {
            myOrdersPage.verifyShopbaseProductExisted(false);
        }
    }

    @Step
    public void verifyOptionOfVariant_SBProduct(String options, int index) {
        String value[] = options.split(">");
        myOrdersPage.verifyOption_SBproduct(value[0], index);
        myOrdersPage.verifyValueOption_SBProduct(value[1], index);
    }

    @Step
    public void clickBtnSaveMapping() {
        myOrdersPage.clickBtnSaveMapping();
    }

    @Step
    public void verifyMapSuccessfully() {
        myOrdersPage.verifyTextPresent("Product was successfully mapped!", true);
    }

    @Step
    public boolean isMappingPage() {
        return myOrdersPage.isMappingPage();
    }

    @Step
    public void verifyMessage(String messageErrors) {
        if (!messageErrors.isEmpty()) {
            List<String> msgs = Arrays.asList(messageErrors.split(","));
            myOrdersPage.verifyMessage(msgs);

        }
    }

    @Step
    public void clickBtnBackToOrder() {
        myOrdersPage.clickOnElementByJs(myOrdersPage.xPathBtn("", "Back to order", 1));
    }

    @Step
    public String getTheFirstOrderNumber() {
        return myOrdersPage.getTheFirstOrderNumber();
    }

    @Step
    public void clickBtnFulfillOrder() {
        myOrdersPage.clickBtnFulfillOrder();
        myOrdersPage.clickBtn("Confirm");
        myOrdersPage.waitFulfillmentFinish();
        waitABit(1000);
        myOrdersPage.waitFulfillDone();
        myOrdersPage.clickBtn("Close");
    }

    @Step
    public String getCrossPandaOrder(int index) {
        return myOrdersPage.getCrossPandaOrder(index);
    }

    @Step
    public void loadFile(String csvFile) {
        myOrdersPage.loadFile(csvFile);
    }

    @Step
    public List<List<String>> readListOrderInCSV(String csvFile, String templateCSV, int numberLinePrevew) throws IOException, CsvValidationException {
        String _filePath = LoadObject.getFilePath(csvFile);
        List<List<String>> listOrder = SessionData.loadDataTableByCSV(_filePath);
        String header = headerByTemplateCSVFile(templateCSV);

        for (int row : SessionData.getDataTbRowsNoHeader(listOrder).keySet()) {
            List<String> listColName = Arrays.asList(header.split(","));
            List<String> line = new ArrayList<>();
            for (String colName : listColName) {
                String value = SessionData.getDataTbVal(listOrder, row, colName);
                line.add(value);
            }
            listOrder.add(line);
            if (row == numberLinePrevew) {
                break;
            }
        }
        return listOrder;
    }

    @Step
    private String headerByTemplateCSVFile(String templateCSV) {
        String header = "";

        if (templateCSV.equalsIgnoreCase("crosspanda")) {
            header = "ORDER DATE,ORDER ID,PRODUCT NAME,QUANTITY,SKU,SHIPPING NAME,ADDRESS 1,ADDRESS 2,CITY,ZIP/POSTAL CODE,STATE/PROVINCE/REGION,COUNTRY,PHONE";
        } else if (templateCSV.equalsIgnoreCase("shopbase")) {
            header = "Created At,Name,Lineitem Name,Lineitem Quantity,Lineitem Sku,Shipping Name,Shipping Address1,Shipping Address2,Shipping City,Shipping Zip,Shipping Province,Shipping Country,Shipping Phone";
        } else
            header = "Created at,Name,Lineitem name,Lineitem quantity,Lineitem sku,Shipping Name,Shipping Address1,Shipping Address2,Shipping City,Shipping Zip,Shipping Province,Shipping Country,Shipping Phone";

        return header;
    }

    @Step
    public List<List<String>> getPreviewListOrder(int row) {
        List<List<String>> listOrder = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            List<String> order = new ArrayList<>();
            order = myOrdersPage.getOrderInformation(i);
            listOrder.add(order);
        }
        return listOrder;
    }

    @Step
    public void clickBtnImport() {
        myOrdersPage.clickBtn("Import");
        myOrdersPage.waitForEverythingComplete();
    }

    @Step
    public void clickBtnImportOrders() {
        myOrdersPage.clickBtn("Import orders");
    }

    @Step
    public int countLineItemInPreview() {
        return myOrdersPage.countLineItemInPreview();
    }

    @Step
    public void verifyPreviewListOrder(List<List<String>> actualRessult, List<List<String>> expectedRessult) {
        for (int i = 0; i < actualRessult.size(); i++) {
            assertThat(actualRessult.get(i)).isEqualTo(expectedRessult.get(i + 1));
        }
    }

    @Step
    public void selectProductIdentity(String productIdentity) {
        myOrdersPage.selectProductIndentity(productIdentity);
    }

    @Step
    public void checkAllOrdersImported() {
        myOrdersPage.checkAllOrdersImported();
    }

    @Step
    public void selectAction(String action) {
        myOrdersPage.clickBtn("Actions");
        myOrdersPage.selectAction(action);
        myOrdersPage.waitForEverythingComplete();
    }

    @Step
    public boolean isPageEmpty() {
        return myOrdersPage.isPageEmpty();
    }

    @Step
    public int countPage() {
        return myOrdersPage.countPage();
    }

    @Step
    public void selectPage(int i) {
        myOrdersPage.selectPage(i);
    }

    @Step
    public List<Integer> getListOrderID(int page, String accToken) {
        List<Integer> a = new ArrayList<>();
        String user_id = LoadObject.getProperty("user_id");
        String api = LoadObject.getProperty("gapi.url") + "/v1/panda/orders?user_id=" + user_id + "&time_range=last_30_days&order_type=awaitingOrder&page=" + page + "&limit=20&tabName=Awaiting%20order";
        JsonPath js = myOrdersPage.getAPICrosspanda(api, accToken);
        int numberOrder = (int) myOrdersPage.getData(js, "result.size()");
        for (int i = 0; i < numberOrder; i++) {

            List<Boolean> is_mapped_options = myOrdersPage.getDataByKey(js, "result[" + i + "].line_items.is_mapped_option");
            String name = (String) myOrdersPage.getData(js, "result[" + i + "].shipping_address.name");
            String address1 = (String) myOrdersPage.getData(js, "result[" + i + "].shipping_address.address1");
            String city = (String) myOrdersPage.getData(js, "result[" + i + "].shipping_address.city");
            String province = (String) myOrdersPage.getData(js, "result[" + i + "].shipping_address.province");
            String zip = (String) myOrdersPage.getData(js, "result[" + i + "].shipping_address.zip");
            if (is_mapped_options.contains(true) && (!name.isEmpty()) && (!address1.isEmpty()) && (!city.isEmpty()) && (!province.isEmpty()) && (!zip.isEmpty())) {
                int resid = (int) myOrdersPage.getData(js, "result[" + i + "].ref_id");
                a.add(resid);
            }
        }
        System.out.println("List order" + a);
        return a;

    }

    @Step
    public void placeOrder(List<Integer> listOrder, String accessToken) {
        JsonObject requestParams = new JsonObject();
        JsonArray ids = new JsonArray();
        for (int a : listOrder) {
            ids.add(a);
        }
        String user_id = LoadObject.getProperty("user_id");
        requestParams.addProperty("user_id", Integer.parseInt(user_id));
        requestParams.add("ref_ids", ids);
        String url = LoadObject.getProperty("gapi.url") + "/v1/panda/make-order";
        Response response = given().header("x-panda-access-token", accessToken).body(requestParams.toString()).post(url);

        JsonPath jp = response.then().extract().jsonPath();
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);
        assertThat(myOrdersPage.getData(jp, "success")).isEqualTo(true);

    }

    @Step
    public int countPageOrder(String accToken) {
        String user_id = LoadObject.getProperty("user_id");
        String api = LoadObject.getProperty("gapi.url") + "/v1/panda/orders?user_id=" + user_id + "&time_range=last_30_days&order_type=awaitingOrder&page=1&limit=20&tabName=Awaiting%20order";
        JsonPath js = myOrdersPage.getAPICrosspanda(api, accToken);
        return (int) myOrdersPage.getData(js, "total_page");
    }

    @Step
    public void verifySKUOdoo(String orderNumber, String xpandaSKU, int index) {
        String actXpandaSKU = myOrdersPage.getXpandaSKUOdoo(orderNumber, index);
        assertThat(actXpandaSKU).isEqualTo(xpandaSKU);
    }

    @Step
    public void replaceProduct() {
        myOrdersPage.clickBtn("Replace product");
    }

    @Step
    public void searchReplaceProduct(String replaceProduct) {
        myOrdersPage.searchReplaceProduct(replaceProduct);
    }

    @Step
    public void selectVariant(String variant) {
        String[] variants = variant.split(";");
        for (String var : variants) {
            myOrdersPage.clickValueOption(var);
        }
    }

    @Step
    public void clickSaveReplaceProduct() {
        myOrdersPage.clickBtn("Save", 1);
    }

    @Step
    public void verifyStatusLineItem(String orderNumber, String status, int index) {
        String actStatus = myOrdersPage.getStatusOrderLine(orderNumber, index);
        assertThat(actStatus).isEqualTo(status);
    }

    @Step
    public void verifyButtonShowLineItem(String productName, String variant, String listButton) {
        String[] button = listButton.split(",");
        for (String inbutton : button) {
            myOrdersPage.verifyButonExist(productName, variant, inbutton);
        }
    }

    @Step
    public void verifyQuantityOdoo(String orderNumber, String quantity, int index) {
        String actQuantity = myOrdersPage.getQuantityOdoo(orderNumber, index);
        assertThat(actQuantity).isEqualTo(quantity);
    }

    @Step
    public void verifyShippingName(String orderNumber, String shippingName, int index) {
        String actShippingName = myOrdersPage.verifyShippingName(orderNumber, index);
        assertThat(actShippingName).isEqualTo(shippingName);
    }

    @Step
    public void verifyShippingCost(String orderNumber, String shippingCost, int index) {
        String actShippingCost = myOrdersPage.verifyShippingCost(orderNumber, index);
        assertThat(actShippingCost).contains(shippingCost);
    }

    @Step
    public void verifyCountLineItem(String orderNumber, int lineitem) {
        myOrdersPage.verifyCountLineItem(orderNumber, lineitem);
    }

    @Step
    public void selectOrder(String orderNumber, Boolean select) {
        myOrdersPage.selectOrder(orderNumber, select);
    }

    @Step
    public void markAsFulfilled() {
        myOrdersPage.clickAction();
        myOrdersPage.clickMarkAsFulfilled();
    }

    @Step
    public void clickBtnCancelFulfillmentOrder(String orderNumber, int index) {
        myOrdersPage.clickBtnCancelFulfillment(orderNumber, index);
    }

    @Step
    public boolean miniPage() {
        return myOrdersPage.isMiniPage();
    }

    @Step
    public void clickIconPre() {
        myOrdersPage.clickIconPre();
    }

    @Step
    public void verifyHasOrderInStore() {
        myOrdersPage.verifyHasOrderInStore();
    }

    @Step
    public void verifyHasNotOrderInStore() {
        myOrdersPage.verifyTextPresent("No orders matched your search criteria", true);
    }

    @Step
    public void verifyTotalOrder(String totalOrder) {
        if (!totalOrder.isEmpty())
            myOrdersPage.verifyCostInOrder("Order Total:", totalOrder);
    }

    @Step
    public void verifyCustomerName(String orderNumber, String name) {
        String actName = myOrdersPage.getCustomerInfor(orderNumber, "name");
        assertThat(name).isEqualTo(actName);
    }

    @Step
    public void verifyCountry(String orderNumber, String country) {
        if (!country.isEmpty()) {
            if (country.equals("@BLANK@")) {
                country = "";
            }
            String actcountry = myOrdersPage.getCustomerInfor(orderNumber, "country");
            assertThat(country).isEqualTo(actcountry);
        }
    }

    @Step
    public void verifyTrackingNumber(String orderNumber, String trackingNumber, int index) {
        String actTrackingNumber = myOrdersPage.getTKN(orderNumber, index);
        assertThat(actTrackingNumber).isEqualTo(trackingNumber);
    }

    @Step
    public int getIndexLineItem(String orderNumber, String productName, String variantName) {
        int index = myOrdersPage.getIndexLineItem(orderNumber, productName, variantName);
        return index;
    }

    @Step
    public void verifyProductNameSB(String orderNumber, String productName, int index) {
        String actProductName = myOrdersPage.getProductNameSB(orderNumber, index);
        assertThat(actProductName).isEqualTo(productName);
    }

    @Step
    public void verifyProductVariantSB(String orderNumber, String variantName, int index) {
        String actVariant = myOrdersPage.getVariantSB(orderNumber, index);
        assertThat(actVariant).isEqualTo(variantName);
    }

    @Step
    public void verifySKUSB(String orderNumber, String sku, int index) {
        String actSKU = myOrdersPage.getSKUSB(orderNumber, index);
        assertThat(actSKU).isEqualTo(sku);
    }

    @Step
    public void verifyQuantitySB(String orderNumber, String quantity, int index) {
        String actQuantity = myOrdersPage.getQuantitySB(orderNumber, index);
        assertThat(actQuantity).isEqualTo(quantity);
    }

    @Step
    public void verifyProductNameOdoo(String orderNumber, String productName, int index) {
        String actProductNameOdoo = myOrdersPage.getProductNameOdoo(orderNumber, index);
        assertThat(actProductNameOdoo).isEqualTo(productName);
    }

    @Step
    public void verifyProductVariantOdoo(String orderNumber, String variantName, int index) {
        String actVariantOdoo = myOrdersPage.getVariantOdoo(orderNumber, index);
        assertThat(actVariantOdoo).isEqualTo(variantName);
    }

    @Step
    public void verifyPhone(String orderNumber, String phone) {
        String actPhone = myOrdersPage.getCustomerInfor(orderNumber, "phone");
        assertThat(actPhone).isEqualTo(phone);
    }

    @Step
    public void verifyAddress(String orderNumber, String address) {
        String actAddress = myOrdersPage.getCustomerInfor(orderNumber, "address1");
        assertThat(actAddress).isEqualTo(address);
    }

    @Step
    public void verifyApart(String orderNumber, String aprt) {
        String actAprt = myOrdersPage.getCustomerInfor(orderNumber, "address2");
        assertThat(actAprt).isEqualTo(aprt);
    }

    @Step
    public void verifyCity(String orderNumber, String city) {
        String actCity = myOrdersPage.getCustomerInfor(orderNumber, "city");
        assertThat(actCity).isEqualTo(city);
    }

    @Step
    public void verifyState(String orderNumber, String state) {
        String actState = myOrdersPage.getCustomerInfor(orderNumber, "province");
        assertThat(actState).isEqualTo(state);
    }

    @Step
    public void verifyZipcode(String orderNumber, String zipcode) {
        String actZip = myOrdersPage.getCustomerInfor(orderNumber, "zip");
        assertThat(actZip).isEqualTo(zipcode);
    }

    @Step
    public void verifyHasNotLineitemInApp(String orderNumber, String productNameSB) {
        myOrdersPage.verifyHasNotLineitemInApp(orderNumber, productNameSB);
    }

    @Step
    public void openCustomInfor() {
        myOrdersPage.openCustomInfor();
    }

    @Step
    public void verifySizeProduct(String sizePage) {
        myOrdersPage.verifySizeProduct(sizePage);
    }

    @Step
    public void selectProduct(String Product) {
        myOrdersPage.selectProduct(Product);
    }

    @Step
    public void clickBtnMapProduct() {
        myOrdersPage.clickBtn("Map Product");
        myOrdersPage.waitForPageLoad();
    }

    @Step
    public void clickBtnChangeMapping() {
        myOrdersPage.clickBtn("Change mapping");
        myOrdersPage.waitForPageLoad();
    }

    @Step
    public void clickBtnSet() {
        myOrdersPage.clickBtn("Set");
        myOrdersPage.waitForPageLoad();
    }

    @Step
    public void searchProductMap(String nameProduct) {
        myOrdersPage.searchProductMap(nameProduct);
    }

    @Step
    public void refreshPage() {
        myOrdersPage.refreshPage();
    }

    @Step
    public void clickReplaceAll() {
        myOrdersPage.clickReplaceAll();
    }

    @Step
    public void clickRadioProductName() {
        myOrdersPage.clickRadioProductName();
    }

    @Step
    public boolean verifySaveButtonDisable() {
        return myOrdersPage.verifySaveButtonDisable();
    }

    @Step
    public void inputStartTime(String startTime) {
        myOrdersPage.inputStartTime(startTime);
    }

    @Step
    public void inputEndTime(String endTime) {
        myOrdersPage.inputEndTime(endTime);
    }


    @Step
    public void changeNameCS(String orderNumber, String name) {
        myOrdersPage.changeInforCS("Name", orderNumber, name);
    }

    @Step
    public void changePhoneCS(String orderNumber, String phone) {
        myOrdersPage.changeInforCS("Phone", orderNumber, phone);
    }

    @Step
    public void changeCountryCS(String orderNumber, String country) {
        myOrdersPage.changeInforCS("Country", orderNumber, country);
    }

    @Step
    public void changeAddressCS(String orderNumber, String address) {
        myOrdersPage.changeInforCS("Address", orderNumber, address);
    }

    @Step
    public void changeArpCS(String orderNumber, String arp) {
        myOrdersPage.changeInforCS("Apartment, suite, etc.", orderNumber, arp);
    }

    @Step
    public void changeCityCS(String orderNumber, String city) {
        myOrdersPage.changeInforCS("City", orderNumber, city);
    }

    @Step
    public void changeStateCS(String orderNumber, String state) {
        myOrdersPage.changeInforCS("State/Province/Region", orderNumber, state);
    }

    @Step
    public void changeZipCS(String orderNumber, String zip) {
        myOrdersPage.changeInforCS("ZIP/Postal Code", orderNumber, zip);
    }

    @Step
    public void clickSaveChangeInforCS() {
        myOrdersPage.clickBtn("Save");
    }

    @Step
    public void importCSVFileWithData(String mappedProductOrder, String mappedProductOrderAS, String mappedProductOrderF, String unmapProductOrder, String data) throws IOException {
        myOrdersPage.importCSVFileWithData(mappedProductOrder, mappedProductOrderAS, mappedProductOrderF, unmapProductOrder, data);
        myOrdersPage.clickBtnImport();
    }

    @Step
    public void verifyStatusOfOrderOnCrossPanda(String orderName, String status) {
        myOrdersPage.verifyStatusOfOrderOnCrossPanda(orderName, status, 0);
    }

    @Step
    public void searchOrderOnCrossPanda(String orderName) {
        myOrdersPage.searchOrderOnCrossPanda(orderName);
    }

    @Step
    public void verifyAbleToSelectActionDelete(String able) {
        myOrdersPage.clickBtn("Actions");
        myOrdersPage.verifyAbleToSelectActionDelete(able);
    }

    @Step
    public void selectPaging(String paging) {
        myOrdersPage.selectPaging(paging);
    }

    @Step
    public void getTrackingNumber() {
        myOrdersPage.clickBtnAction();
        myOrdersPage.getTrackingNumber();
    }

    @Step
    public boolean isTrackingNumber(String orderNumber) {
        return myOrdersPage.isTrackingNumber(orderNumber);
    }

    @Step
    public void verifyBaseProductName(String product) {
        myOrdersPage.verifyBaseProductName(product);
    }

    @Step
    public void verifyOptionValueBaseProduct(String optionVariant) {
        if (optionVariant.contains(";")) {
            String op1[] = optionVariant.split(";");
            for (int i = 0; i < op1.length; i++) {
                verifyDataBaseProduct(op1[i], i + 1);
            }
        } else
            verifyDataBaseProduct(optionVariant, 1);
    }

    @Step
    public void verifyDataBaseProduct(String option, int index) {
        String op2[] = option.split(">");
        myOrdersPage.verifyOptionBaseProduct(op2[0], index);
        myOrdersPage.verifyOptionValueBaseProduct(op2[1], index);
    }

    @Step
    public void verifyMockup(String orderName, String mockup, int index) {
        myOrdersPage.verifyMockup(orderName, mockup, index);
    }

    @Step
    public void verifyArtworkFront(String orderName, String artFront, int index) {
        myOrdersPage.verifyArtWork(orderName, artFront, index, "Front");
    }

    @Step
    public void verifyArtworkBack(String orderName, String artBack, int index) {
        myOrdersPage.verifyArtWork(orderName, artBack, index, "Back");
    }

    @Step
    public void verifyDesigncode(String orderName, String designCode, int index) {
        myOrdersPage.verifyDesigncode(orderName, designCode, index);
    }

    @Step
    public void verifyBaseCost(String orderName, String baseCost, int index) {
        myOrdersPage.verifyBaseCost(orderName, baseCost, index);
    }

    @Step
    public void verifySupplierName(String orderName, String supplierName, int index) {
        myOrdersPage.verifySupplierName(orderName, supplierName, index);
    }

    @Step
    public boolean isProductOdoo(String product, String odooProduct) {
        return myOrdersPage.isProductOdoo(product, odooProduct);
    }

    public void clickBtnSaveMappingInvalid() {
        myOrdersPage.clickBtn("Save");
    }

    public String getSupplierOrderIdByAPI(String orderName, String accessToken) {
        String userId = LoadObject.getProperty("userId");
        String url = LoadObject.getProperty("xp.gapi.url");
        String gapi = url + "/v1/panda-fulfillment/orders?user_id=" + userId + "&keyword=" + orderName + "";
        JsonPath js = given().header("x-panda-access-token", accessToken).get(gapi).then().extract().jsonPath();
        List supplierOrderId = (List) myOrdersPage.getData(js, "result.line_items.supplier_order_id");
        return supplierOrderId.get(0).toString();
    }

    public String getAccessToken() {
        String url = LoadObject.getProperty("xp.gapi.url");
        String username = LoadObject.getProperty("xp.email");
        String password = LoadObject.getProperty("xp.pass");
        String gapi = url + "/xauth/credentials";
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("username", username);
        requestParams.addProperty("password", password);
        JsonPath jp = myOrdersPage.postAPI(gapi, requestParams).jsonPath();
        return jp.get("access_token");
    }

    public void createFulfillmentByAPI(String supplierOrderId, String carrier, String tkn) {
        String url = LoadObject.getProperty("xp.gapi.url");
        String gapi = url + "/v1/qctool/panda/fake-scalablepress-webhook";
        JsonObject js = new JsonObject();
        js.addProperty("type", "fulfill");
        js.addProperty("sp_order_id", supplierOrderId.replace("[", "").replace("]", ""));
        js.addProperty("sp_item_index", 0);
        js.addProperty("carrier", carrier);
        js.addProperty("tracking_number", tkn);
        myOrdersPage.postAPI(gapi, js);
    }

    @Step
    public void verifyInfoPbase(String infoPbases) {
        String[] listInfoPbase = infoPbases.split(">");
        assertThat(myOrdersPage.getTextOrder("Variant:", 1)).isEqualTo(listInfoPbase[0]);
        assertThat(myOrdersPage.getTextOrder("SKU:", 1)).contains(listInfoPbase[1]);
        assertThat(myOrdersPage.getTextOrder("Quantity:", 1)).isEqualTo(listInfoPbase[2]);

    }

    @Step
    public void verifyInfodoo(String infoOdoos) {
        String[] listInfoOdoo = infoOdoos.split(">");
        assertThat(myOrdersPage.getTextOrder("Variant:", 2)).isEqualTo(listInfoOdoo[0]);
        assertThat(myOrdersPage.getTextOrder("SKU:", 2)).contains(listInfoOdoo[1]);
        assertThat(myOrdersPage.getTextOrder("Quantity:", 2)).isEqualTo(listInfoOdoo[2]);

    }

    @Step
    public void verifyStatus(String status) {
        assertThat(myOrdersPage.getTextStatus()).isEqualTo(status);
    }

    @Step
    public void verifyInfoshippingName(String shippingName, String shippingCost, String trackingNumber, String deliveryTime) {
        myOrdersPage.clickBTShipping();
        myOrdersPage.verifyInfoshippingName(shippingName, shippingCost, trackingNumber, deliveryTime);

    }

    @Step
    public void chooseVendor(String vendor) {
        myOrdersPage.chooseVendor(vendor);
    }
    @Step
    public double getTotalSalesCurrent(){
        return myOrdersPage.getTotalSalesCurrent();
    }
}

