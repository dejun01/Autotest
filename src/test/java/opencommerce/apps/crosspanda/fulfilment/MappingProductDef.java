package opencommerce.apps.crosspanda.fulfilment;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.MappingProductSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.MyOrdersSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class MappingProductDef {

    @Steps
    MyOrdersSteps myOrdersSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;
    @Steps
    MappingProductSteps mappingProductSteps;

    @Then("^mapping product on CrossPanda \"([^\"]*)\"$")
    public void mapping_product_on_CrossPanda(String dataKey, List<List<String>> dataTable) {
        for(int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()){
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String productSB = SessionData.getDataTbVal(dataTable, row, "Product nameSB>Variant");
            String odooProduct = SessionData.getDataTbVal(dataTable, row, "Odoo product");
            String odooOption = SessionData.getDataTbVal(dataTable, row, "Odoo option");
            String odooValue = SessionData.getDataTbVal(dataTable, row, "Odoo option value");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");
            if(orderNumber.isEmpty()){
                orderNumber = (String) SessionData.getDataByKey(key);
            }
            myOrdersSteps.searchOrder(orderNumber);
            String [] sProduct = productSB.split(">");
            int index = myOrdersSteps.getIndexLineItem(orderNumber, sProduct[0], sProduct[1]);
            myOrdersSteps.clickBtnMapProduct(index);
            mappingProductSteps.selectPurchasedProduct(odooProduct);
            if(!odooOption.isEmpty()){
                String [] sOption = odooOption.split(">");
                for(String option :sOption){
                    mappingProductSteps.selectOptionProduct(option);
                }
            }
            if(!odooValue.isEmpty()){
                String [] sValue = odooValue.split(">");
                for(String value : sValue){
                    mappingProductSteps.selectValueOptionProduct(value);
                }
            }
            myOrdersSteps.clickBtnSaveMapping();
            if(!msg.isEmpty()){
                mappingProductSteps.verifyMessageMapping(msg);
            }

        }

    }
}
