package opencommerce.homepage;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.CreateOfferSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.home.steps.HomeSteps;
import com.opencommerce.shopbase.dashboard.online_store.domains.steps.DomainSteps;
import com.opencommerce.shopbase.dashboard.online_store.navigation.steps.NavigationSteps;
import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.PreferencesSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.HeaderSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductFeedDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.PaymentsSettingSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.ShippingSteps;
import common.utilities.LoadObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

public class OnboardingDef {
    @Steps
    HomeSteps homeSteps;
    @Steps
    AccountSteps accountSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    HeaderSteps headerSteps;
    @Steps
    PaymentsSettingSteps paymentsSettingSteps;
    @Steps
    DomainSteps domainSteps;
    @Steps
    PreferencesSteps preferencesStep;
    @Steps
    NavigationSteps navigationSteps;
    @Steps
    SettingSteps settingSteps;
    @Steps
    ShippingSteps shippingSteps;
    @Steps
    ProductFeedDetailSteps productFeedDetailSteps;
    @Steps
    SignUpSteps signUpSteps;
    @Steps
    CreateOfferSteps createOfferSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;
    long currentime = System.currentTimeMillis();

    @Then("^verify onboarding displayed in dashboard with name \"([^\"]*)\"$")
    public void verifyOnboardingDisplayedInDashboard(String shopname) {
        if (!shopname.isEmpty()) {
            shopname = shopname + currentime;
        }
        HashMap<String, List<String>> items = homeSteps.getContentOnboarding(shopname);
        System.out.println("items: " + items);
        homeSteps.verifyOnboardingDisplay(items);
    }

    @And("complete Onboarding steps")
    public void completeOnboardingSteps() {
        HashMap<String, List<String>> items = homeSteps.getContentOnboarding("");
        int page1 = 0;
        int cpl1 = 0;

        for (String i : items.keySet()) {
            if (i.contains("on0"))
                page1++;
        }
        int count = 0;
        String preKey = "";
        for (String i : items.keySet()) {
            count++;
            List<String> item = items.get(i);
            System.out.println("item: " + item);
            String title = item.get(0);
            if (!i.contains(preKey)) {
                homeSteps.clickNextSlideOnboarding();
            }
            homeSteps.clickTitleOnboarding(title);
            homeSteps.clickBtnOnboarding(item.get(2), i);
            if (title.contains("product")) {
                productDetailSteps.clickAddProductButton();
                productDetailSteps.enterTitle("test.Test");
                productDetailSteps.clickSaveChanges();
                productDetailSteps.navigateToAllProductScreen();
                productListSteps.verifyProductExist("test.Test", true);
            } else if (title.contains("theme")) {
                themeEditorSteps.clickCustomize();
                themeEditorSteps.clickSection("Header");
                headerSteps.enterAnnouncementMessage("test");
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeThemeEditor();
            } else if (title.contains("payment")) {
                paymentsSettingSteps.clickbtnChooseAlternativeProvider();
                paymentsSettingSteps.chooseGateway("Ocean");
                paymentsSettingSteps.inputAccountNameInOceanPaymentSection("Oceanpayment-1");
                paymentsSettingSteps.inputAccountInPaymentSection("150260");
                paymentsSettingSteps.inputTerminalInPaymentSection("15026001");
                paymentsSettingSteps.inputSecureCodeInPaymentSection("12345678");
                paymentsSettingSteps.clickBtnAddAccount();
                paymentsSettingSteps.verifyAddGatewaySuccessfully("Oceanpayment-1");

            } else if (title.contains("domain")) {
                String domain = LoadObject.getProperty("domain");
                domainSteps.clickConnectExistingDomain();
                domainSteps.enterDomain(domain);
                domainSteps.clickBtnNext();
                domainSteps.clickBtnVerifyConnection();
                domainSteps.verifyConnectdomainSuccessfully(domain);
            } else if (title.contains("tracking")) {
                preferencesStep.scrollToConversionId();
                preferencesStep.inputConversionId("Facebook Pixel & Conversions API", "421054948881435", "1");
                preferencesStep.inputToken("Facebook Pixel & Conversions API", "EAAFZC6BUOi4QBACnqBeqhRXCVRAZCmZBjBDiMjHigPqFiZC", "1");
                preferencesStep.clickBtnSave();
                preferencesStep.verifyMsgWithLabel("Saving success");
            } else if (title.contains("Boost Upsell")) {
                homeSteps.openUpsellscreen();
                homeSteps.clickBtnCreateOffer();
                myCampaignSteps.refreshPage();
                createOfferSteps.chooseUpsellOfferType("Post-purchase");
                createOfferSteps.enterOfferName("test.Test");
                createOfferSteps.enterOfferMessage("Message");
                createOfferSteps.enterOfferTitle("Title");
                homeSteps.chooseRecommendVariantWith("Same collection with target products");
                homeSteps.clickBtnSubmitOffer();
            }
            commonSteps.switchToTab("Home");
            if (count == items.size())
                homeSteps.verifyHideOnboarding();
            else {
                int index;
                if (i.contains("on0")) {
                    cpl1++;
                    index = 1;
                } else {
                    index = 2;
                }
                if (page1 == cpl1 || index == 2) {
                    homeSteps.clickNextSlideOnboarding();
                }
                homeSteps.verifyCompletedItemOnboarding(title, item.get(5), item.get(6), item.get(7), index);
            }
            preKey = i.substring(0, 3);
        }

    }

    @And("verify skip all tasks in other way")
    public void verifySkipAllTasksInOtherWay() {
        homeSteps.skipAllTaskInOther();
        homeSteps.verifyHideOtherWaysToGetStarted();
    }

    @And("verify skip all task in progress bar")
    public void verifySkipAllTaskInProgressBar() {
        homeSteps.skipAllTaskInProgressBar();
        homeSteps.verifyHideProgresbar();
//        homeSteps.verifyHiveOnboarding();
    }

    @And("disable password in homepage")
    public void disablePasswordInHomepage() {
        homeSteps.verifyDisplayDisablePassworkBlock(true);
        homeSteps.disablePassword();
        homeSteps.verifyDisplayDisablePassworkBlock(false);
    }

    @And("confirm a plan from homepage")
    public void confirmAPlanFromHomepage() {
        homeSteps.verifyDisplayConfirmPlan(true);
        homeSteps.clickBtnSelectAPlan();
        homeSteps.switchToLastestTab();
        accountSteps.choosePlan("Basic Base");
        accountSteps.clickBTConfirmChanges();
        homeSteps.switchToFirstTab();
        homeSteps.verifyDisplayConfirmPlan(false);
    }

    @And("complete tasks in other ways")
    public void completeTasksInOtherWays() {
        homeSteps.clickOtherWaysToGetStarted();

        homeSteps.clickButtonOtherWays("Review navigation");
        navigationSteps.clickAddMenu();
        navigationSteps.entertitle("test");
        navigationSteps.clickSaveMenu();
        commonSteps.switchToTab("Home");
        homeSteps.clickOtherWaysToGetStarted();
        homeSteps.verifyCompleteItemOtherWays("Review navigation");

        homeSteps.clickButtonOtherWays("Add shipping policy");
        settingSteps.clickCreateFromTemplate();
        settingSteps.clickSave();
        commonSteps.switchToTab("Home");
        homeSteps.clickOtherWaysToGetStarted();
        homeSteps.verifyCompleteItemOtherWays("Add shipping policy");

        homeSteps.clickButtonOtherWays("Review shipping zones");
        shippingSteps.chooseEditShippingZone("REST OF WORLD");
        shippingSteps.enterZoneName("test");
        shippingSteps.clickBtnSaveChanges("");
        commonSteps.switchToTab("Home");
//        homeSteps.clickOtherWaysToGetStarted();
//        homeSteps.verifyCompleteItemOtherWays("Review shipping zones");
//
//        homeSteps.clickButtonOtherWays("Create product feed");
//        productFeedDetailSteps.enterFeedName("test");
//        productFeedDetailSteps.clickSaveButton();
//        commonSteps.switchToTab("Home");
//        homeSteps.clickOtherWaysToGetStarted();
        homeSteps.verifyHideOtherWaysToGetStarted();

    }

    @And("^create a shop with name is \"([^\"]*)\"$")
    public void createAShopWithNameIs(String shopname) {
        if (!shopname.isEmpty()) {
            shopname = shopname + currentime;
        }
        signUpSteps.clickBtnAddANewShop();
        signUpSteps.enterShopName(shopname);
        signUpSteps.clickBtnCreate();
    }

    @And("Disable password")
    public void disablePassword() {
        commonSteps.switchToTab("Online Store");
        homeSteps.clickBtnDisablePassword();
        commonSteps.switchToTab("Home");
    }
}


