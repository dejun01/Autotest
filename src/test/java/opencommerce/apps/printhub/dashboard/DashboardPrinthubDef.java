package opencommerce.apps.printhub.dashboard;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.dashboard.DashboardPrinthubSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DashboardPrinthubDef {
    @Steps
    DashboardPrinthubSteps dashboardPrinthubSteps;
    String screen = LoadObject.getProperty("screen");

    @Then("verify widget in dashboard printhub")
    public void verifyWidgetInDashboardPrinthub() {
        HashMap<Integer, List<String>> listWidget = dashboardPrinthubSteps.getlistWidgetPhub(screen);
        if (listWidget.size() == 0) {
            dashboardPrinthubSteps.verifyWidgetDisplay(false);
        } else {
            int number = dashboardPrinthubSteps.getNumberOfWidget();
            assertThat(number).isEqualTo(listWidget.size());
            for (int i = 0; i < listWidget.size(); i++) {
                List<String> widget = listWidget.get(i);
                System.out.println("widget " + i + " :" + widget);
                dashboardPrinthubSteps.verifyTitleWidget(widget.get(3), i + 1);
                dashboardPrinthubSteps.verifyDescWidget(widget.get(4), i + 1);
                dashboardPrinthubSteps.verifyTypeWidget(widget.get(2), i + 1);
                dashboardPrinthubSteps.verifyDetailWidget(widget, i + 1);
            }
        }
    }

    @And("verify dashboard printhub screen")
    public void verifyDashboardPrinthubScreen() {
        dashboardPrinthubSteps.verifyDashboardPrinthubScreen();
    }

    @And("get data statistic and analytic printhub dashboard by api")
    public void getDataStatisticAndAnalyticPrinthubDashboard(List<List<String>> dataTable) {
        String fromDate = "";
        String toDate = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            LocalDate now = LocalDate.now();
            LocalDate previousMonth = now.minusMonths(1);
            LocalDate yesterday = now.minusDays(1);
            switch (time) {
                case "Today":
                    fromDate = String.valueOf(now);
                    toDate = String.valueOf(now);
                    break;
                case "Yesterday":
                    fromDate = String.valueOf(yesterday);
                    toDate = String.valueOf(yesterday);
                    break;
                case "Last 7 days":
                    LocalDate last7days = now.minusDays(7);
                    fromDate = String.valueOf(last7days);
                    toDate = String.valueOf(yesterday);
                    break;
                case "Last 30 days":
                    LocalDate last30days = now.minusDays(30);
                    fromDate = String.valueOf(last30days);
                    toDate = String.valueOf(yesterday);
                    break;
                case "Last 90 days":
                    LocalDate last90days = now.minusDays(90);
                    fromDate = String.valueOf(last90days);
                    toDate = String.valueOf(yesterday);
                    break;
                case "Last month":
                    LocalDate monthStart = previousMonth.withDayOfMonth(1);
                    LocalDate monthEnd = previousMonth.withDayOfMonth(previousMonth.getMonth().maxLength());
                    fromDate = String.valueOf(monthStart);
                    toDate = String.valueOf(monthEnd);
                    break;
                case "Week to date":
                    LocalDate curWeek = now.minusDays(0);
                    LocalDate weekStar = now.minusDays(now.getDayOfWeek().getValue() - 1);
                    fromDate = String.valueOf(weekStar);
                    toDate = String.valueOf(now);
                    break;
                case "Month to date":
                    LocalDate curMonth = now.minusMonths(0);
                    LocalDate monthStar = curMonth.withDayOfMonth(1);
                    fromDate = String.valueOf(monthStar);
                    toDate = String.valueOf(now);
                    break;
            }
            dashboardPrinthubSteps.chooseFilterTime(time);

            List<String> sum = dashboardPrinthubSteps.getSummaryAnalyticPrinthubDashboard(time, fromDate, toDate);
            dashboardPrinthubSteps.verifyTotalOrder(sum.get(0));
            dashboardPrinthubSteps.verifyTotalItem(sum.get(1));
            dashboardPrinthubSteps.verifyGoldbaseItem(sum.get(2));
            dashboardPrinthubSteps.verifySilverbaseItem(sum.get(3));
            System.out.println("time: " + time);
            HashMap<Integer, List<String>> items = new HashMap<>();
            items = dashboardPrinthubSteps.getListItem(time, fromDate, toDate);
            int numberItems = dashboardPrinthubSteps.countItems();
            assertThat(numberItems).isEqualTo(items.size());
            List<String> listTitle = dashboardPrinthubSteps.getListTitle(numberItems);
            for (int i : items.keySet()) {
                List<String> item = items.get(i);
                String title = item.get(0);
                assertThat(listTitle).contains(title);
                dashboardPrinthubSteps.verifyItemName(title);
                dashboardPrinthubSteps.verifyItemType(title, item.get(1));
                dashboardPrinthubSteps.verifyQuantity(title, item.get(2));
                dashboardPrinthubSteps.verifyQuantityRefund(title, item.get(3));
                dashboardPrinthubSteps.verifyQuantityActual(title, item.get(4));
            }
        }
    }

    @Then("search item by keyword")
    public void searchItemByKeyword(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String keyword = SessionData.getDataTbVal(dataTable, row, "Keyword");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            dashboardPrinthubSteps.enterKeyword(keyword);
            dashboardPrinthubSteps.verifyresult(keyword, result);
        }
    }
}








