package opencommerce.apps.boostupsell;

import com.google.gson.JsonArray;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.OfferListSteps;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.json.simple.JSONArray;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class OfferListDef {
    @Steps
    OfferListSteps offerListSteps;

    @Then("^delete all offers on the table$")
    public void delete_all_offers_on_the_table() {
        while (offerListSteps.getNumberOfOffers() > 0) {
            offerListSteps.checkAllOffers();
            offerListSteps.clickBtnAction();
            offerListSteps.clickBtnOnAction("Delete");
            offerListSteps.deleteAllOffers();
        }
        assertThat(offerListSteps.getNumberOfOffers()).isEqualTo(0);
        offerListSteps.verifyBtnCreateNewOffer("Create new");
    }

    @And("delete all offer {string} by API")
    public void deleteAllAOfferByAPI(String offerTypes){
        JsonArray listOfferID = offerListSteps.getListOfferID(offerTypes);
        offerListSteps.deleteAllAOfferByAPI(listOfferID);
    }


    @Then("^verify Upsell's offer created on the dashboard \"([^\"]*)\"$")
    public void verifyOfferOnListOfferOnAppSbase(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String prodTarget = SessionData.getDataTbVal(dataTable, row, "Target");
            String offerType = SessionData.getDataTbVal(dataTable, row, "Offer type");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            if (!offerName.isEmpty()) {
                offerListSteps.searchOffer(offerName);
                int index = offerListSteps.getIndexOfferByName(offerName);
                offerListSteps.verifyOfferTarget(prodTarget, index);
                offerListSteps.verifyOfferType(offerType, index);
                offerListSteps.verifyOfferStatus(status, index);
            }

        }
    }

    @Then("^active or inactive offer with \"([^\"]*)\"$")
    public void activeOrInactiveOfferWith(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String offers = SessionData.getDataTbVal(dataTable, row, "Offers");
            String noti = SessionData.getDataTbVal(dataTable, row, "Notices");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            int numberOffers = offerListSteps.getNumberOfOffers();
            if (numberOffers > 0) {
                if (!(offers.equals("all"))) {
                    offerListSteps.searchOffer(offers);
                }
                offerListSteps.checkAllOffers();
                offerListSteps.clickBtnAction();
                offerListSteps.clickBtnOnAction(action);
//                offerListSteps.verifyNotiSuccess(noti);
                offerListSteps.verifyOfferStatus(status, 1);

            }
        }
    }

    @Then("^turn \"([^\"]*)\" Global smart offer/bundle$")
    public void turnGlobalSmartOfferBundle(String status) {
        boolean sts = !status.equals("off");
        offerListSteps.turnOnOFFSmartOffer(sts);
    }

    @Then("^back to list upsell offers$")
    public void backListUpsellOffer(){
        offerListSteps.backListUsellOffer();
    }
}
