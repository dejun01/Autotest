package opencommerce.apps.boost_convert.store;

import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boost_convert.BoostConvertShopSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BoostConvertShopBaseDef {
    @Steps
    BoostConvertShopSteps cOptShopSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @When("^verify to count items of Product \"([^\"]*)\" in stock after buy (\\d+) items on Shopbase$")
    public void verify_to_count_items_of_Product_in_stock_after_buy_item_sb(String productName, int quatity) {
        productSteps.searchAndSelectProduct(productName);
        int expectNumber = cOptShopSteps.getNumberItemInStock() - quatity;
        int i = 1;
        productSteps.openPage("");
        productSteps.searchAndSelectProduct(productName);

        while (cOptShopSteps.getNumberItemInStock() != expectNumber) {
            cOptShopSteps.waitABit(5000);
            cOptShopSteps.refreshPage();
            i++;
            if (i == 10) {
                break;
            }
        }
        assertThat(cOptShopSteps.getNumberItemInStock()).isEqualTo(expectNumber);
    }


    @Then("^verify product countdown is shown on product page as \"([^\"]*)\"$")
    public void verify_product_countdown_of_BoostCovert_is_shown_at_Shopbase_product_page_as(String dataKey, List<List<String>> dataTable)  {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is show"));
            String settingItemsLeftInStock = SessionData.getDataTbVal(dataTable, row, "Setting items left in stock");
            String quantityOfItems = SessionData.getDataTbVal(dataTable, row, "Quantity of items");

            productSteps.deleteCookie();
            productSteps.searchAndSelectProduct(productName);
            reviewOnSFSteps.waitForLoadApps("Copt");
            cOptShopSteps.verifyProductCountdownShown(isShow);
            if (isShow) {
                int numberProductShowed = cOptShopSteps.getNumberItemInStock();
                if (settingItemsLeftInStock.equalsIgnoreCase("Show your actual number of stocks")) {
                    String accessToken = productDetailSteps.getAccessToken();
                    float numberProductInDashboard = productDetailSteps.getNumberProductByProductName(productName, accessToken);
                    assertThat(numberProductInDashboard).isEqualTo(numberProductShowed);
                } else {
                    String[] random = quantityOfItems.split(",");
                    assertThat(numberProductShowed).isGreaterThanOrEqualTo(Integer.parseInt(random[0]));
                    assertThat(numberProductShowed).isLessThanOrEqualTo(Integer.parseInt(random[1]));
                }
            }

        }
    }

    @Then("^verify timer countdown is shown on product page as \"([^\"]*)\"$")
    public void verify_timer_countdown_of_BoostCovert_is_shown_at_Shopbase_product_page_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String display = SessionData.getDataTbVal(dataTable, row, "Display timer");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is show"));
            productSteps.searchAndSelectProduct(productName);
            reviewOnSFSteps.waitForLoadApps("Copt");
            cOptShopSteps.verifyTimerCountdownShown(isShow);
            if (isShow)
                cOptShopSteps.verifyDisplayTimer(display);
        }
    }


    @Given("^verify sales notifications of BoostConvert on Shopbase page as \"([^\"]*)\"$")
    public void verify_sales_notifications_of_BoostConvert_on_Shopbase_page_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String sPage = SessionData.getDataTbVal(dataTable, row, "Page");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is show"));

            String product = SessionData.getDataTbVal(dataTable, row, "Product name");
            String customerFirstName = SessionData.getDataTbVal(dataTable, row, "Customer first name");
            String customerLastName = SessionData.getDataTbVal(dataTable, row, "Customer last name");

            String customerCountry = SessionData.getDataTbVal(dataTable, row, "Customer country");
            String customerCity = SessionData.getDataTbVal(dataTable, row, "Customer city");

            productSteps.openPage(sPage);
            productSteps.deleteCookie();
            reviewOnSFSteps.waitForLoadApps("Copt");
            productSteps.waitABit(3000);
            cOptShopSteps.verifySaleNotificationShowed(product, isShow);
            //undefined in Santa Monica, United States,Santa Monica,United States purchased18

            if (isShow) {
                sTitle = sTitle.replace("{{city}}", customerCity)
                        .replace("{{country}}", customerCountry)
                        .replace("{{location}}", customerCity + ", " + customerCountry)
                        .replace("{{full_name}}", customerLastName + " " + customerFirstName)
                        .replace("{{first_name}}", customerFirstName)
                        .replace("{{last_name}}", customerLastName);
                cOptShopSteps.verifyNotificationTitle(sTitle);
            }
        }
    }

    @Given("^verify checkout notifications of BoostConvert on Shopbase page as \"([^\"]*)\"$")
    public void verify_checkout_notifications_of_BoostConvert_on_Shopbase_page_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product added");
            String sMessage = SessionData.getDataTbVal(dataTable, row, "Message");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is show"));
            productSteps.deleteCookie();
            productSteps.addMultipleProductsToCart(sProduct);
            cartSteps.verifyCartPage();
            reviewOnSFSteps.waitForLoadApps("Copt");
            cOptShopSteps.verifyCheckoutNotification(isShow);
            if (isShow) {
                sMessage = sMessage.replace("{{product_title}}", sProduct).replace("{{purchased_number}}", "").trim();
                cOptShopSteps.verifyCheckoutNotificationMsg(sMessage);
            }
        }
    }


}