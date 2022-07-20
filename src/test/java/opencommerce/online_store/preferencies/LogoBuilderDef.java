package opencommerce.online_store.preferencies;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.PreferencesSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.HeaderSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import com.opencommerce.shopbase.storefront.steps.shop.HomePageSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OnePageCheckoutSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.CommonPageObject;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import opencommerce.notification.steps.NotificationSettingSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import java.util.HashMap;
import java.util.List;


public class LogoBuilderDef {

    String theme = LoadObject.getProperty("theme");
    String shop = LoadObject.getProperty("shop");

    @Steps
    PreferencesSteps preSteps;
    @Steps
    SettingSteps settingSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    NotificationSettingSteps notiSteps;
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    OnePageCheckoutSteps checkoutSteps;
    @Steps
    HeaderSteps headerSteps;
    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;

    CommonPageObject commonPageObject;

        HashMap<String, String> explogos;
        HashMap<String, String> actLogos = new HashMap<>();
        HashMap<String, String> preLogos;

    @And("user regenerate and add logo to the store in Dashboard")
    public void userRegenerateAndAddLogoToTheStoreInDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Boolean desktop = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Desktop"));
            Boolean mobile = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Mobile"));
            Boolean checkoutPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Checkout page"));
            Boolean favicon = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Favicon"));
            Boolean email = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Email"));
            String theme = SessionData.getDataTbVal(dataTable, row, "Theme");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");

            preSteps.enterTextInLogo(text);
            preSteps.clickBtnRegenerate();
            preSteps.clickAddToMyStore();
            preSteps.verifyPopup();
            preSteps.checkCheckboxDesktop(desktop);
            preSteps.checkCheckboxMobile(mobile);
            preSteps.checkCheckboxCheckoutPage(checkoutPage);
            preSteps.checkCheckboxFavicon(favicon);
            preSteps.checkCheckboxEmail(email);
            preSteps.chooseTheme(theme);
            preSteps.clickSave();
        }
    }


    @And("user regenerate and add logo to the store in Theme editor")
    public void userRegenerateAndAddLogoToTheStoreInThemeEditor(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Boolean desktop = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Desktop"));
            Boolean mobile = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Mobile"));
            Boolean checkoutPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Checkout page"));
            Boolean favicon = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Favicon"));
            Boolean email = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Email"));
            Boolean isAll = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "All themes"));
            String text = SessionData.getDataTbVal(dataTable, row, "Text");

            themeEditorSteps.clickBtnCustomizeCurrentTheme();
            themeEditorSteps.clickSection("Header");
            headerSteps.enterTextInLogo(text);
            headerSteps.clickBtnRegenerate();

            headerSteps.clickBtnAddTotheme();
            headerSteps.checkCheckboxDesktop(desktop);
            headerSteps.checkCheckboxMobile(mobile);
            headerSteps.checkCheckboxCheckoutPage(checkoutPage);
            headerSteps.checkCheckboxFavicon(favicon);
            headerSteps.checkCheckboxEmail(email);
            headerSteps.checkCheckboxAllThemes(isAll);
            headerSteps.clickBtnSave();
            themeEditorSteps.closeThemeEditor();
            themeEditorSteps.verifyThemesManagementPageDisplayed();
        }
    }

    @Then("get logo list by api after apply logo")
    public void getLogoListByApiAfterApplyLogo() {
        explogos = preSteps.getLogoByAPI();

    }

    @And("get logo list by api before apply logo")
    public void getLogoListByApiBeforeApplyLogo() {
        preLogos = preSteps.getLogoByAPI();
    }

    @And("verify apply logo successfully by api")
    public void verifyApplyLogoSuccessfully(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Boolean desktop = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Desktop"));
            Boolean mobile = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Mobile"));
            Boolean checkoutPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Checkout page"));
            Boolean favicon = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Favicon"));
            Boolean email = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Email"));

            for (String pre : preLogos.keySet()) {
                for (String exp : explogos.keySet()) {
                    if (pre.equalsIgnoreCase(exp)) {
                        String preValue = preLogos.get(pre);
                        String expValue = explogos.get(exp);
                        switch (pre) {
                            case "desktop":
                                preSteps.verifyMatchLogo(desktop, preValue, expValue);
                                break;
                            case "mobile":
                                preSteps.verifyMatchLogo(mobile, preValue, expValue);
                                break;
                            case "checkoutPage":
                                preSteps.verifyMatchLogo(checkoutPage, preValue, expValue);
                                break;
                            case "favicon":
                                preSteps.verifyMatchLogo(favicon, preValue, expValue);
                                break;
                            case "email":
                                preSteps.verifyMatchLogo(email, preValue, expValue);
                                break;
                        }
                    }
                }
            }
        }
    }

    @And("get logo list after apply on storefront")
    public void getLogoListAfterApplyOnStorefront() {
        //get logo in email
        commonSteps.switchToTab("Settings");
        settingSteps.clickToSection("Notifications");
        notiSteps.chooseLinkButton("Order confirmation");
        notiSteps.clickPreview();
        String email = notiSteps.getEmailLogo();
        actLogos.put("email", email);

        //get logo desktop and favicon
        loginSteps.openShop(shop);
        actLogos.put("desktop", homePageSteps.getDesktopLogo());
        actLogos.put("favicon", homePageSteps.getFavicon());

        //get checkout logo
        productSteps.searchAndSelectProduct("dress");
        productSteps.clickAddToCart();
        productSteps.clickCheckOut();
        actLogos.put("checkout", checkoutSteps.getLogo());

    }

    @And("verify apply logo successfully on storefront")
    public void verifyApplyLogoSuccessfullyOnStorefront() {
        for (String exp : explogos.keySet()) {
            for (String act : actLogos.keySet()) {
                if (exp.equalsIgnoreCase(act)) {
                    System.out.println("key: " + exp);
                    String actValue = preLogos.get(act);
                    String expValue = explogos.get(exp);
                    homePageSteps.verifyApplylogoSuccessfully(actValue, expValue);
                }
            }
        }
    }

    @And("setting generate print file as {string}")
    public void settingGeneratePrintFile(String statusGenerate) {
        Boolean _isstatusGenerate = false;
        if (statusGenerate.equalsIgnoreCase("true"))
            _isstatusGenerate = true;
        preSteps.settingOnOffGeneratePF(_isstatusGenerate);
    }

    @And("verify setting generate printfile after create as {string} by API")
    public void verifySettingGeneratePFAfCreateByAPI(String statusGenerate) {
            Boolean _isStatusToggle = true;
            if (statusGenerate.equals("false"))
                _isStatusToggle = false;
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Boolean status = printbaseAPI.getStatusPrintFile(accessToken);
            preSteps.verifySettingGeneratePrintFilebyAPI(status,_isStatusToggle);

    }

}
