package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.ColorsPage;
import net.thucydides.core.annotations.Step;

import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ColorsSteps {
    ColorsPage colorsPage;

    @Step
    public void verifyColorSetting(String label, String value) {
        String collorSetting = colorsPage.getColorSettings(label);
        assertThat(collorSetting).isEqualToIgnoringCase(value);
    }

    @Step
    public void inputColor(String label, String value) {
        colorsPage.inputColor("",label, value);
    }

    @Step
    public void inputColor(String blockName, String label, String value) {
        colorsPage.inputColor(blockName, label, value);
    }
}
