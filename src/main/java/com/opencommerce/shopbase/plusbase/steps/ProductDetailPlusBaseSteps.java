package com.opencommerce.shopbase.plusbase.steps;
import com.opencommerce.shopbase.plusbase.pages.ProductDetailPlusBasePage;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;
public class ProductDetailPlusBaseSteps {
    ProductDetailPlusBasePage ProductDetailPage;

    @Step
    public void userNavigatesToScreen(String tabName) {
        ProductDetailPage.userNavigateScreen(tabName);
    }

    @Step
    public void searchProductPlBase(String product) {
        ProductDetailPage.searchProductPlBase(product);
    }

    @Step
    public void clickProductPlBase(String productName) {
        ProductDetailPage.clickProductPlBase(productName);
    }

    @Step
    public void verifyProductName(String productName) {
        String act = ProductDetailPage.getProductName();
        assertThat(act).isEqualTo(productName);
    }

    @Step
    public void verifyTags(String tags) {
        String act = ProductDetailPage.getTags();
        assertThat(act).isEqualTo(tags);
    }

    @Step
    public void chooseVariant(String variants) {
        String[] listVariant = variants.split(";");
        for (int i = 0; i < listVariant.length; i++) {
            ClickChooseVariant(listVariant[i]);
        }
    }

    @Step
    private void ClickChooseVariant(String listVariant) {
        String[] optionValue = listVariant.split("/");
        ProductDetailPage.clickVariant(optionValue[0], optionValue[1]);
    }

    @Step
    public void verifyProcessingTime(String processingTime) {
        String actProcessingTime = ProductDetailPage.getProcessingTime();
        assertThat(actProcessingTime).isEqualTo(processingTime);
    }

    @Step
    public void verifyProductCost(String variants, String productCost) {
        chooseVariant(variants);
        String actProductCost = ProductDetailPage.getProductCost();
        assertThat(actProductCost).isEqualTo(productCost);
    }

    @Step
    public void verifySellingPrice(String variants, String sellingPrice) {
        chooseVariant(variants);
        String actSellingPrice = ProductDetailPage.getSellingPrice();
        assertThat(actSellingPrice).isEqualTo(sellingPrice);
    }

    @Step
    public void verifyProfitMargin(String variants, Float profitMargin) {
        chooseVariant(variants);
        Float productCost = Float.parseFloat(ProductDetailPage.getProductCost().replace("$", ""));
        Float sellingPrice = Float.parseFloat(ProductDetailPage.getSellingPrice().replace("$", ""));
        Float actProfitMargin = ProductDetailPage.roundTwoDecimalPlaces(sellingPrice - productCost);
        assertThat(actProfitMargin).isEqualTo(profitMargin);
    }

    @Step
    public void verifyDescription(String description) {
        String act = ProductDetailPage.getDescription();
        assertThat(act).isEqualTo(description);
    }

    @Step
    public void verifyAboutThisProduct(String aboutThisProduct) {
        String act = ProductDetailPage.getAboutThisProduct();
        assertThat(act).isEqualTo(aboutThisProduct);
    }

    @Step
    public void verifyShipTo(String shipTo) {
        String act = ProductDetailPage.getShipTo();
        assertThat(act).isEqualTo(shipTo);
    }

    @Step
    public void selectShipTo(String Country) {
        ProductDetailPage.selectShipTo(Country);
    }

    @Step
    public void verifyShippingMethod(String shippingMethod) {
        String act = ProductDetailPage.getShippingMethod();
        assertThat(act).isEqualTo(shippingMethod);
    }

    @Step
    public void verifyShippingFee(String shippingFee) {
        String act = ProductDetailPage.getShippingFee();
        assertThat(act).isEqualTo(shippingFee);
    }

    @Step
    public void verifyEstimatedDeliveryTime(String estimatedDeliveryTime) {
        String act = ProductDetailPage.getEstimatedDeliveryTime();
        assertThat(act).isEqualTo(estimatedDeliveryTime);
    }

    @Step
    public float getprofitMargin() {
        return ProductDetailPage.getprofitMargin();
    }


    @Step
    public void clickBTImportToYourStore() {
        ProductDetailPage.clickBTImportToYourStore();
    }

    public void directToImportListPage() {
        ProductDetailPage.directToImportListPage();
    }

    @Step
    public void selectCheckboxAllProduct() {ProductDetailPage.selectCheckboxAllProduct();}

    @Step
    public void verifyContentOfMailPlBaseImportProduct(String content) {
        ProductDetailPage.verifyContentOfMailPlbaseImportProduct(content);
    }

    @Step
    public void clickDownLoadCSVTemplate(){ProductDetailPage.clickDownLoadCSVTemplate();}
}