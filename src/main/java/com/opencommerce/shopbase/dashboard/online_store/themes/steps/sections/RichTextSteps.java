package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.RichTextPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class RichTextSteps {
    RichTextPage richtextPage;
    String theme = LoadObject.getProperty("theme");

    @Step
    public void enterText(String text) {
        richtextPage.enterText(text);
    }

    @Step
    public void enterHeading(String heading) {
        richtextPage.enterInputFieldWithLabel("Heading", heading);
    }

    @Step
    public void chooseTextAlignmentRichText(String value) {
        switch (theme) {
            case "inside":
                if (value.equalsIgnoreCase("Centre")) {
                    value = "Center";
                }
                richtextPage.clickLinkTextWithLabel(value);
                break;
            default:
                richtextPage.selectDdlWithLabel("Text alignment", value);
        }
    }

    @Step
    public void enterLink(String link) {
        richtextPage.clearLinkExisted();
        if (!link.isEmpty()) {
            String[] data = link.split(">");
            if (data.length == 2) {
                String typeLink = data[0];
                String dataLink = data[1];
                richtextPage.selectLinkPage(typeLink, dataLink);
            }
            if (data.length == 1) {
                String typeLink = data[0];
                richtextPage.chooseTypeLink(typeLink);

            }
        }
    }


    @Step
    public void chooseLinksRichText(String link) {
        richtextPage.clearLinkExisted();
        if (!link.isEmpty()) {
            richtextPage.chooseLink(link);

        }
    }

    @Step
    public void enterLinkLabel(String linkLabel) {
        richtextPage.enterInputFieldWithLabel("Link label", linkLabel);
    }

    public void enterBtnLabel(String btnLabel) {
        richtextPage.enterInputFieldWithLabel("Button label", btnLabel);
    }

    public void chooseHeadingPosition(String headingPosition) {
        richtextPage.selectDdlWithLabel("Heading position", headingPosition);
    }
}
