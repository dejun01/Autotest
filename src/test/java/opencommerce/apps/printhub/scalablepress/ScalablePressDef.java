package opencommerce.apps.printhub.scalablepress;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.scalablepress.ScalablePressSteps;
import com.opencsv.exceptions.CsvValidationException;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;

public class ScalablePressDef {
    @Steps
    ScalablePressSteps scalablePressSteps;

    @Given("^login to ScalablePress with username is \"([^\"]*)\" and pwd is \"([^\"]*)\"$")
    public void loginToScalablePressWithUsernameIsAndPwdIs(String userName, String pwd) {
        scalablePressSteps.openScalablePressPage();
        scalablePressSteps.enterUsername(userName);
        scalablePressSteps.enterPwd(pwd);
        scalablePressSteps.clickBtnLogin();
    }

    @And("^claim order by ID from csv file is \"([^\"]*)\"$")
    public void claimOrderByIDFromCsvFileIs(String csvFile, List<List<String>> data) throws IOException, CsvValidationException {

        String email = SessionData.getDataTbVal(data, 1, "Email");
        String reason = SessionData.getDataTbVal(data, 1, "Reason");
        String remark = SessionData.getDataTbVal(data, 1, "Remark");
        String preferredResolution = SessionData.getDataTbVal(data, 1, "Preferred Resolution");


        String _filePath = LoadObject.getFilePath(csvFile);
        List<List<String>> listOrder = SessionData.loadDataTableByCSV(_filePath);

        for (int row : SessionData.getDataTbRowsNoHeader(listOrder).keySet()) {
            String orderId = SessionData.getDataTbVal(listOrder, row, "order_id");
//            System.out.println("order " + orderId);
            scalablePressSteps.openClaimPage("https://scalablepress.com/manager/v2/orders/" + orderId + "/claim");
            scalablePressSteps.selectAllItems();

            if (isNotClaimed()) {
                scalablePressSteps.clickBtnNextStep();
                scalablePressSteps.enterEmail(email);
                scalablePressSteps.enterReason(reason);
                scalablePressSteps.enterRemarks(remark);
                scalablePressSteps.selectPreferredResolution(preferredResolution);
                scalablePressSteps.clickBtnFileClaim();
            }
            scalablePressSteps.getLinkClaim(orderId);
        }
    }

    private boolean isNotClaimed() {
        return scalablePressSteps.isNotClaimed();
    }
}
