package opencommerce.apps.boost_convert.dashboard;

import com.opencommerce.shopbase.storefront.steps.apps.boost_convert.BoostConvertShopSteps;
import com.opencommerce.shopbase.dashboard.apps.boostconvert.steps.NotificationListSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class NotificationsListDef extends ScenarioSteps {
    @Steps
    NotificationListSteps notiSteps;
    @Steps
    BoostConvertShopSteps cOptShopifySteps;

    String accessToken = "";
    int numberOfSalesNoti;

    @Given("^user turn \"([^\"]*)\" the first notification sale of BoostCovert$")
    public void user_turn_the_first_notification_sale(String status) throws Exception {
        boolean _isTurnOn = cOptShopifySteps.convertStatus(status);
        notiSteps.controlNotificationSale(_isTurnOn);
    }


    @Then("^verify having Sales notification of product is \"([^\"]*)\"$")
    public void verify_having_Sales_notification_is(String _productName) {
        notiSteps.verifySyncNoti(_productName);
    }

    int numberNotificationCreated = 1;

    @When("^create custom notifications of BoostCovert as \"([^\"]*)\"$")
    public void create_custom_notification_of_BoostCovert_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sLocation = SessionData.getDataTbVal(dataTable, row, "Location");
            String sDataLocation = SessionData.getDataTbVal(dataTable, row, "Data Location");
            String sCustomerNoti = SessionData.getDataTbVal(dataTable, row, "Customer notification");
            String sNumberCustomerNoti = SessionData.getDataTbVal(dataTable, row, "Number notification created");

            notiSteps.clickAddCustomNotifications();
            notiSteps.selectProduct(sProduct);
            notiSteps.selectLocation(sLocation, sDataLocation);
            notiSteps.selectCustomerNoti(Boolean.parseBoolean(sCustomerNoti));
            numberNotificationCreated = notiSteps.countNotificationCreated();
            if (!sNumberCustomerNoti.isEmpty())
                notiSteps.verifyNumberNotificationCreate(numberNotificationCreated, Integer.parseInt(sNumberCustomerNoti));
            notiSteps.clickCreateNow();
            cOptShopifySteps.waitABit(3000);
        }
    }

    @Given("^count sales notification on Copt$")
    public int countSalesNotificationOnCopt() {
        if (accessToken.isEmpty())
            accessToken = notiSteps.getAccessTokenShopbase();
        numberOfSalesNoti = notiSteps.countNotificationByAPI(accessToken);
        return numberOfSalesNoti;
    }

    @When("^verify sales notification synced on Copt by API$")
    public void verify_sales_notification_synced_on_Copt_by_API() {
        int before = numberOfSalesNoti;
        
        int i = 0;
        while (countSalesNotificationOnCopt() != before + numberNotificationCreated & i <= 5) {
            i++;
            waitABit(2000);
        }
        assertThat(countSalesNotificationOnCopt()).isEqualTo(before + numberNotificationCreated);
    }


    @When("^verify sales notification of product \"([^\"]*)\" is shown on storefront by API when it was turned \"([^\"]*)\"$")
    public void verifySalesNotificationsShownOnShopbaseByAPI(String productName, String status) {
        if (accessToken.isEmpty())
            accessToken = notiSteps.getAccessTokenShopbase();
        boolean isTurnOn = true;
        if (status.equalsIgnoreCase("off")) {
            isTurnOn = false;
        }
        List<Integer> notificationIDs = notiSteps.getNotificationWithProductTitleByAPI(productName, accessToken);

        List<Integer> notificationIDShownOnSDK = notiSteps.getlistSaleNotificationShownOnStorefront(productName);
        System.out.println(notificationIDs);
        System.out.println("SDK" + notificationIDShownOnSDK);
        if (isTurnOn) {
            assertThat(notificationIDShownOnSDK).containsAll(notificationIDs);
        } else {
            assertThat(notificationIDShownOnSDK.size()).isEqualTo(0);
        }
    }


    @And("^delete all sales notification of Copt$")
    public void deleteAllNotification() {
        while (notiSteps.countNotification() > 0) {
            notiSteps.selectAllSaleNotification();
            notiSteps.deleteSaleNotification();
            notiSteps.confirmDeleteNotification();
        }
    }

    @Given("^confirm list sales notification have (\\d+) notification$")
    public void confirmListSalesNotificationHasNotification(int expNumberOfNoti) {
        int actualNumberOfNoti = notiSteps.countNotification();
        assertThat(actualNumberOfNoti).isEqualTo(expNumberOfNoti);
    }

    @And("^turn \"([^\"]*)\" sales notification'status of product \"([^\"]*)\"$")
    public void turnSalesNotificationStatusOfProduct(String status, String productName) {
        if (accessToken.isEmpty())
            accessToken = notiSteps.getAccessTokenShopbase();
        boolean isTurnOn = true;
        if (status.equalsIgnoreCase("off")) {
            isTurnOn = false;
        }
        notiSteps.searchNotification(productName);
        int numberNoti = notiSteps.countNotification();
        for (int i = 1; i <= numberNoti; i++)
            notiSteps.turnOnNotification(isTurnOn, i);
    }

    @And("^turn \"([^\"]*)\" sales notification'status of product \"([^\"]*)\" by API$")
    public void turnSalesNotificationStatusOfProductByAPI(String status, String productName) {
        if (accessToken.isEmpty())
            accessToken = notiSteps.getAccessTokenShopbase();
        boolean isTurnOn = !status.equalsIgnoreCase("off");
        List<Integer> notificationIDs = notiSteps.getNotificationWithProductTitleByAPI(productName, accessToken);
        notiSteps.turnOnNotificationByAPI(notificationIDs, isTurnOn, accessToken);

    }

}