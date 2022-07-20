package com.opencommerce.shopbase.plusbase.steps;
import com.opencommerce.shopbase.dashboard.dashboard.pages.SignUpPage;
import com.opencommerce.shopbase.plusbase.pages.CreateStorePlusBasePage;
import net.thucydides.core.annotations.Step;
public class CreateStorePlusBaseSteps {
    CreateStorePlusBasePage createStorePage;
    SignUpPage signUpPage;

    @Step
    public void enterShopName(String shop) {
        signUpPage.enterInputFieldWithLabel("Your shop name", shop);
    }

    public boolean isDashboardHomepage() {
        return signUpPage.isDashboardHomepage();
    }

    public void clickBtnCreate() {
        signUpPage.clickBtn("Create");
    }

    public void clickCapchar() {
        createStorePage.clickCapchar();
    }

    public void waitUntilInvisibleLoading() {
    }

    public void clickBtnStartNow(String btnLabel) {
        createStorePage.clickBtn(btnLabel);

    }

    public void clickBtnAddANewShop() {
        createStorePage.clickBtn("Add a new shop");

    }

    public void clickSelectAnotherShop() {
        createStorePage.clickSelectAnotherShop();

    }

    public void clickNoThank() {
        createStorePage.clickNoThank();
    }


    public void clickBtnWantDropshipStore() {
        createStorePage.clickBtnWantDropshipStore();
    }

    public String getLogo() {
        String text= createStorePage.getLogo();
        String[] subtext = text.split(",");
        return subtext[0];
    }
}
