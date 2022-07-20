package opencommerce.online_store.themes.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.HeaderSteps;
import com.opencommerce.shopbase.storefront.steps.shop.HomePageSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static common.utilities.LoadObject.convertStatus;


public class HeaderDef {
    @Steps
    HeaderSteps headerSteps;

    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    HomePageSteps homePageSteps;
    @When("change header settings theme inside as {string}")
    public void changeHeaderSettingsThemeInsideAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            boolean fullWidthSection = convertStatus(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            boolean fixedHeader = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Fixed header"));
            String showAnnouncementBar = SessionData.getDataTbVal(dataTable, row, "Show announcement bar");
            String announcementMessage = SessionData.getDataTbVal(dataTable, row, "Announcement message");
            boolean fixedPositionAnnounce = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Fixed position while scrolling"));
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            String menuPosition = SessionData.getDataTbVal(dataTable, row, "Menu position");
            boolean enableTopbarMenu = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable Topbar menu"));
            String topbarMenu = SessionData.getDataTbVal(dataTable, row, "Topbar menu");
            boolean enableSearch = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable search"));
            boolean showCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show cart icon"));
            String cartIcon = SessionData.getDataTbVal(dataTable, row, "Cart icon");
            homePageSteps.refreshpage();
            themeEditorSteps.choosePreview("Homepage");
            themeEditorSteps.navigateToSectionsTab();
            themeEditorSteps.openSessionBlock("Header");
            headerSteps.chooseLayoutHeaderInside(layout);
            headerSteps.checkHeaderSettings("Full width section", fullWidthSection);
            if (!contentAlignment.isEmpty()) {
                headerSteps.chooseContendAlignment(contentAlignment);
            }
            headerSteps.settingDesktopLogo(desktopLogo);
            headerSteps.settingSize(size);
            headerSteps.settingMobileLogo(mobileLogo);
            headerSteps.settingShowAnnouncementBar(showAnnouncementBar, announcementMessage);
            headerSteps.checkHeaderSettings("Fixed position while scrolling", fixedPositionAnnounce);
            headerSteps.selectMainMenu(mainMenu);
            if (!menuPosition.isEmpty()) {
                headerSteps.selectMenuPosition(menuPosition);
            }
            if (!layout.equals("Minimal")) {
                headerSteps.checkHeaderSettings("Fixed header", fixedHeader);
                headerSteps.checkHeaderSettings("Enable Topbar menu", enableTopbarMenu);
            }
            if (!topbarMenu.isEmpty()) {
                headerSteps.selectTopBarMenu(topbarMenu);
            }
            headerSteps.clickCheckAdvanceSettings();
            headerSteps.checkHeaderSettings("Enable search", enableSearch);
            headerSteps.checkHeaderSettings("Show cart icon", showCart);
            headerSteps.selectCartIconInside(cartIcon);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @Then("verify header theme Inside on store front as {string}")
    public void verifyHeaderThemeInsideOnStoreFrontAs(String arg0) {
    }

    @Then("open section block {string}")
    public void openSectionBlock(String section){
        themeEditorSteps.openSessionBlock(section);
    }
}
