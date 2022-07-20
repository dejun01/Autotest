package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.FeaturedPromotionPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class FeaturedPromotionSteps extends ScenarioSteps {
    FeaturedPromotionPage featuredPromotionPage;
    @Step
    public void inputText(String label, String text) {
        featuredPromotionPage.inputText(label, text);
    }
    @Step
    public void verifyFullWidthSection(boolean fullWidthSection) {
        featuredPromotionPage.verifyFullWidthSection(fullWidthSection);

    }
    @Step
    public void verifyImage(String image, int index) {
        if (!image.isEmpty()){
            featuredPromotionPage.verifyImage(index);
        }
    }
    @Step
    public void verifyText(String text, int index) {
        if (!text.isEmpty()){
            featuredPromotionPage.verifyText(text,index);
        }
    }
}
