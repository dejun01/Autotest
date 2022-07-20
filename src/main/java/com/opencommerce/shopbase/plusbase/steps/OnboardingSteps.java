package com.opencommerce.shopbase.plusbase.steps;
import com.opencommerce.shopbase.dashboard.authen.pages.LoginDashboardPage;
import com.opencommerce.shopbase.plusbase.pages.OnboardingPage;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;
public class OnboardingSteps {
    OnboardingPage onboardingPage;
    @Step
    public void verifySteps(String steps) {
        String act = onboardingPage.getSteps();
        assertThat(act).isEqualTo(steps);
    }

    @Step
    public void verifyDescription(String description, String shop) {
        if (description.contains("@shop@")) {
            description = description.replace("@shop@", shop);
            String act = onboardingPage.getDescription();
            assertThat(act).isEqualTo(description);
        }
    }

    @Step
    public void verifyClickBT(String button) {
        if(!onboardingPage.isShowOnboarding()){
            onboardingPage.returnOnboardingSteps();
        }
        onboardingPage.clickBtn(button);
        switch (button) {
            case "Find product":
                assertThat(onboardingPage.getPageCatalog()).isEqualTo("Catalog");
                break;
            case "Add domain":
                assertThat(onboardingPage.getPageDomain()).isEqualTo("Domains");
                break;
            case "Install tracking":
                assertThat(onboardingPage.getPagePreference()).isEqualTo("Google Analytics");
                break;
            case "Customize theme":
                assertThat(onboardingPage.getPageThemes()).isEqualTo("Themes");
                break;
            case "Create product feed":
                assertThat(onboardingPage.getPageAddProductFeed()).isEqualTo("Add product feed");
                break;
        }
    }

    @Step
    public void returnOnboardingSteps() {
        onboardingPage.returnOnboardingSteps();
    }

    @Step
    public void clickSkipThisStep() {
        onboardingPage.clickSkipThisStep();
    }

    public void clickBtnStartNow(String btnLabel) {
        onboardingPage.clickBtn(btnLabel);

    }
}