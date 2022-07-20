package opencommerce.apps.crosspanda.common;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.MyOrdersSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CommonXPandaDef {
    @Steps
    CommonXPandaSteps commonXPandaSteps;
    @Steps
    MyOrdersSteps myOrdersSteps;

    @When("^user navigate to \"([^\"]*)\" screen in CrossPanda$")
    public void user_navigate_to_screen_in_CrossPanda(String nameMenu) {
        String _tabs[] = nameMenu.split(">");
        int level = _tabs.length;
        if (level == 1) {
            commonXPandaSteps.switchToMenu(nameMenu);
        } else if (level == 2) {
            commonXPandaSteps.switchToMenu(_tabs[0]);
            commonXPandaSteps.switchToMenuLevel2(_tabs[1]);
        }
        commonXPandaSteps.waitUntilLoadingSuccessfully();
    }

    @Given("^close smart bar in CrossPanda$")
    public void close_smart_bar_in_CrossPanda() {
        commonXPandaSteps.closeSmartBarCP();
    }

    @Given("^switch to tab \"([^\"]*)\"$")
    public void switch_to_tab(String tabName) {
        try {
            commonXPandaSteps.switchToTabByName(tabName);
        } catch (Throwable r) {
            if (myOrdersSteps.miniPage()) {
                myOrdersSteps.clickIconPre();
            }
            commonXPandaSteps.switchToTabByName(tabName);

        }
    }


}
