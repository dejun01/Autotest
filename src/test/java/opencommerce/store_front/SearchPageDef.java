package opencommerce.store_front;

import com.opencommerce.shopbase.storefront.steps.searchpage.SearchPageStep;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import java.util.List;

public class SearchPageDef {

    @Steps
    SearchPageStep searchSteps;

    @And("^verify search keyword$")
    public void userSearchKeyword(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String keyword = SessionData.getDataTbVal(dataTable, row, "Keyword");
            boolean isClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Click search icon"));
            String searchResult = SessionData.getDataTbVal(dataTable, row, "Search result");
            Boolean isMatched = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Match full keyword"));

            searchSteps.inputToSearchBox(keyword);
            if (isClick) {
                searchSteps.clickOnSearchIcon();
            }
            if(isMatched){
                searchSteps.verifyMatchedResult(keyword);
            }
            if(!searchResult.isEmpty()){
                searchSteps.verifyKeywordMatchedProduct(searchResult);
            }else{
                searchSteps.verifyNoResultNoti(keyword);
            }
        }
    }
}