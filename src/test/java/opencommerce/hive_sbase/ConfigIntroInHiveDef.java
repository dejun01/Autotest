package opencommerce.hive_sbase;

import com.opencommerce.shopbase.common.steps.CommonHiveSteps;
import com.opencommerce.shopbase.hive_sbase.steps.ConfigIntroInHiveSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ConfigIntroInHiveDef {
    @Steps
    CommonHiveSteps commonHiveSteps;
    @Steps
    ConfigIntroInHiveSteps configIntroInHiveSteps;
    String name = "";
    long currentTime = System.currentTimeMillis();
    String introId = LoadObject.getProperty("introId");

    @And("create auth banner intro")
    public void createAuthBannerIntro(List<List<String>> dataTable) {
        configIntroInHiveSteps.clickOnElement("Add new");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Boolean isEnable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            configIntroInHiveSteps.checkCheckBox("USG", false);
            configIntroInHiveSteps.checkCheckBox("China", false);
            configIntroInHiveSteps.checkCheckBox("Vietnam", false);
            configIntroInHiveSteps.checkCheckBox("shopbase.com", false);
            configIntroInHiveSteps.checkCheckBox("shopbase.net.cn", false);
            if (SessionData.getDataTbVal(dataTable, row, "Name").equals("")) {
                name = "";
            } else {
                name = SessionData.getDataTbVal(dataTable, row, "Name") + currentTime;
            }
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String domain = SessionData.getDataTbVal(dataTable, row, "Domain");
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            String bkg = SessionData.getDataTbVal(dataTable, row, "Background color (HEX)");
            String allText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");

            commonHiveSteps.enterInput("Name", name);
            commonHiveSteps.checkCheckbox("Enable", isEnable);
            String reg[] = region.split(",");
            for (int i = 0; i < reg.length; i++) {
                commonHiveSteps.checkCheckbox(reg[i], true);
            }
            String dom[] = domain.split(",");
            for (int i = 0; i < dom.length; i++) {
                commonHiveSteps.checkCheckbox(dom[i], true);
            }
            String con[] = condition.split(",");
            int count = con.length;
            while (count > 1) {
                configIntroInHiveSteps.clickOnElement("Add more");
                count--;
            }
            for (int i = 0; i < con.length; i++) {
                configIntroInHiveSteps.selectCondition(con[i], i + 1);
                commonHiveSteps.enterInput("URL Params", url, i + 1);
            }

            commonHiveSteps.enterInput("Background color (HEX)", bkg);
            commonHiveSteps.enterInput("Link", link);
            commonHiveSteps.enterInput("Alt text", allText);
            commonHiveSteps.uploadImage("File .png .svg .jpg, size tối đa 500kb, dimension 740x400", image);

            commonHiveSteps.clickCreateAndReturnToList();
            if (msg.equals("success")) {
                configIntroInHiveSteps.verifyIntroSuccess("has been successfully created");
            }
        }
    }


    @Then("delete intro")
    public void deleteIntro() {
        configIntroInHiveSteps.clickDetelebutton(name);
        configIntroInHiveSteps.clickYesbutton();
    }

    @Then("edit a intro in hive")
    public void editAIntroInHive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            commonHiveSteps.clickEdit(introId);
            Boolean isEnable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable"));
            name = SessionData.getDataTbVal(dataTable, row, "Name") + currentTime;
            String region = SessionData.getDataTbVal(dataTable, row, "Region");
            String domain = SessionData.getDataTbVal(dataTable, row, "Domain");
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            String bkg = SessionData.getDataTbVal(dataTable, row, "Background color (HEX)");
            String allText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");

            commonHiveSteps.enterInput("Name", name);
            commonHiveSteps.checkCheckbox("Enable", isEnable);
            String reg[] = region.split(",");
            for (int i = 0; i < reg.length; i++) {
                commonHiveSteps.checkCheckbox(reg[i], true);
            }
            String dom[] = domain.split(",");
            for (int i = 0; i < dom.length; i++) {
                commonHiveSteps.checkCheckbox(dom[i], true);
            }

            configIntroInHiveSteps.selectCondition(condition, 1);
            commonHiveSteps.enterInput("URL Params", url);
            commonHiveSteps.enterInput("Background color (HEX)", bkg);
            commonHiveSteps.enterInput("Link", link);
            commonHiveSteps.enterInput("Alt text", allText);
            commonHiveSteps.uploadImage("File .png .svg .jpg, size tối đa 500kb, dimension 740x400", image);

            commonHiveSteps.clickUpdateAndClose();

            configIntroInHiveSteps.verifyIntroSuccess(msg);

        }

    }

}

