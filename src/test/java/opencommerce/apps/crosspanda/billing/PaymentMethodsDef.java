package opencommerce.apps.crosspanda.billing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.billing.PaymentMethodsSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class PaymentMethodsDef {

    @Steps
    PaymentMethodsSteps paymentMethodsSteps;

    @When("^verify UI when payment methods hasn't card as \"([^\"]*)\"$")
    public void verify_UI_when_payment_methods_hasn_t_card_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            if(paymentMethodsSteps.isCard()){
                paymentMethodsSteps.deleteCard();
            }
            paymentMethodsSteps.verifyUINotCard();
        }
    }

    @Given("^user add new card payment methods for CrossPanda as \"([^\"]*)\"$")
    public void user_add_new_card_payment_methods_for_DSC_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String cardName = SessionData.getDataTbVal(dataTable, row, "Cardholder Name");
            String cardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String expDate = SessionData.getDataTbVal(dataTable, row, "Expiration date");
            String cvc = SessionData.getDataTbVal(dataTable, row, "CVC");

            String firstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            String lastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String apartment = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String city = SessionData.getDataTbVal(dataTable, row, "City");

            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String zipcode = SessionData.getDataTbVal(dataTable, row, "ZIP/Postal code");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            paymentMethodsSteps.addNewCard();
            paymentMethodsSteps.enterCardholderName(cardName);
            paymentMethodsSteps.enterCardNumber(cardNumber);
            paymentMethodsSteps.enterExpDate(expDate);
            paymentMethodsSteps.enterCVV(cvc);
            paymentMethodsSteps.enterFirstName(firstName);
            paymentMethodsSteps.enterLastName(lastName);
            paymentMethodsSteps.enterAddress(address);
            paymentMethodsSteps.enterApartment(apartment);
            if (!country.isEmpty()) {
                paymentMethodsSteps.selectCountry(country);
            }
            if (!state.isEmpty()) {
                paymentMethodsSteps.selectState(state);
            }
            paymentMethodsSteps.enterCity(city);
            paymentMethodsSteps.enterZipCode(zipcode);
            paymentMethodsSteps.saveCard();
            if (!message.isEmpty()) {
                paymentMethodsSteps.verifyError(message);
            }
        }
    }

    @Given("^verify add card successfully as \"([^\"]*)\"$")
    public void verify_add_card_successfully_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            String expDate = SessionData.getDataTbVal(dataTable, row, "Exp date");
            boolean cardDefault = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Default"));
            paymentMethodsSteps.verifyCardType(cardType);
            paymentMethodsSteps.verifyExpDateCard(expDate);
            paymentMethodsSteps.verifyCardDefault(cardType, cardDefault);
        }
    }

    @Given("^change card default as \"([^\"]*)\" then verify card default$")
    public void change_card_default_as_then_verify_card_default(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            boolean cardDefault = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Default"));
            if (cardDefault) {
                paymentMethodsSteps.changeCardDefault(cardType);
                paymentMethodsSteps.verifyCardDefault(cardType, cardDefault);
            }
        }
    }

    @When("^delete all card payment methods in app CrossPanda$")
    public void delete_all_card_payment_methods_in_app_CrossPanda() {
        paymentMethodsSteps.deleteCard();
    }


}
