package opencommerce.risk_level;

import com.opencommerce.shopbase.dashboard.risk_level.steps.RiskLevelSteps;
import com.opencommerce.shopbase.plusbase.steps.PaymentProvidersSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.settings.PaymentProvidersDef;

import java.util.List;

public class RiskLevelDef {
    @Steps
    RiskLevelSteps riskLevelSteps;
    @Steps
    PaymentProvidersSteps paymentProvidersSteps;

    @When("update criteria on database")
    public void updateCriteriaOnDatabase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String criteria = SessionData.getDataTbVal(dataTable, row, "criteria");
            int idStag = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "id staging"));
            int idProd = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "id production"));
            long x = Long.parseLong(SessionData.getDataTbVal(dataTable, row, "X"));
            long xyotmr = Long.parseLong(SessionData.getDataTbVal(dataTable, row, "XY_OTMR"));
            long xyodr1 = Long.parseLong(SessionData.getDataTbVal(dataTable, row, "XY_ODR1"));
            long xyodr2 = Long.parseLong(SessionData.getDataTbVal(dataTable, row, "XY_ODR2"));
            int total = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "total"));
            int value = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "value"));
            String env = LoadObject.getProperty("env");
            int id;
            if (env.equalsIgnoreCase("stag")) {
                id = idStag;
            } else {
                id = idProd;
            }
            long time = (System.currentTimeMillis() / 1000);
            switch (criteria) {
                case "aftx":
                    time = time - x;
                    break;
                case "astx":
                    time = time - x;
                    break;
                case "otmrxy":
                    time = time - xyotmr;
                    break;
                case "odrxy":
                    time = time - xyodr1;
                    break;
                default:
                    time = time - xyodr2;
                    break;
            }
            riskLevelSteps.updateCriteria(id, time, total, value);
        }
    }

    @Then("verify risk level on database")
    public void verifyRiskLevelOnDatabase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String riskLevel = SessionData.getDataTbVal(dataTable, row, "smp risk level");
            riskLevelSteps.verifyRiskLevel(riskLevel);
        }
    }

    @And("verify risk level on dashboard")
    public void verifyRiskLevelOnDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String risk = SessionData.getDataTbVal(dataTable, row, "risk");
            paymentProvidersSteps.verifyRiskSMP(risk);

        }
    }
}
