package opencommerce.discounts;

import com.opencommerce.shopbase.dashboard.discounts.steps.DiscountsSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import org.yecht.Data;

import java.util.List;

public class DiscountsDef {
    @Steps
    DiscountsSteps automaticSteps;

    @And("Delete all discounts")
    public void deleteAllDiscounts() {
        if (automaticSteps.countDiscount() > 0) {
            automaticSteps.selectAllDiscount();
            automaticSteps.clickBtnActions();
            automaticSteps.clickDelete();
            automaticSteps.confirmDelete();
        }
    }

    @And("Create free shipping discount")
    public void createFreeShippingDiscount(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Discount type");
            String title = SessionData.getDataTbVal(dataTable, row, "Discount title");
            String valueType = SessionData.getDataTbVal(dataTable, row, "Value type");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String maxDiscount = SessionData.getDataTbVal(dataTable, row, "Maximum discount amount");
            String maxValue = SessionData.getDataTbVal(dataTable, row, "Maximun value");
            automaticSteps.clickBtnCreateDiscount();
            automaticSteps.inputDiscountTitle(title);
            automaticSteps.selectDiscountType(type);
            automaticSteps.selectValueType(valueType);
            automaticSteps.inputValue(discountValue);
            if (!maxDiscount.isEmpty()) {
                boolean checkMax = Boolean.parseBoolean(maxDiscount);
                automaticSteps.checkMaxDiscount(checkMax);
                automaticSteps.inputMaxValue(maxValue);
            }
            automaticSteps.clickBtnSave();
            automaticSteps.verifyDiscountCreated();
        }
    }
}
