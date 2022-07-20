package opencommerce.online_store.watermask;

import com.opencommerce.shopbase.dashboard.online_store.watermask.steps.WaterMaskSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class WatermaskDef {
    @Steps
    WaterMaskSteps waterMaskSteps;

    @And("setting water mask and verify message")
    public void chooseColorForProductsInEditorDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sWatermark = SessionData.getDataTbVal(dataTable, row, "Watermark");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");

            boolean isWatermark = false;
            if (_sWatermark.equalsIgnoreCase("true"))
                isWatermark = true;
            waterMaskSteps.setEnableWaterMask(isWatermark);
            if(isWatermark) {
                if (!_sType.isEmpty())
                    waterMaskSteps.configTypeAndInputValue(_sType);
                if (!_sStyle.isEmpty())
                    waterMaskSteps.chooseStyle(_sStyle);
            }
            waterMaskSteps.clickSaveBtnAndVerifyMessageSuccess();
        }
    }

    @And("setting water mask as {string}")
    public void settingWaterMaskAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sWatermark = SessionData.getDataTbVal(dataTable, row, "Watermark");
            String _sType = SessionData.getDataTbVal(dataTable, row, "Type");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");

            boolean isWatermark = false;
            if (_sWatermark.equalsIgnoreCase("true"))
                isWatermark = true;
            waterMaskSteps.setEnableWaterMask(isWatermark);
            if(isWatermark) {
                if (!_sType.isEmpty())
                    waterMaskSteps.configTypeAndInputValue(_sType);
                if (!_sStyle.isEmpty())
                    waterMaskSteps.chooseStyle(_sStyle);
            }
            waterMaskSteps.clickSaveBtnAndVerifyMessageSuccess();
        }
    }
}
