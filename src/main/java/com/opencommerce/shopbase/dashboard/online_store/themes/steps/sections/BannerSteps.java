package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.BannerPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class BannerSteps {
    BannerPage bannerPage;
    String theme = LoadObject.getProperty("theme");
    String shop = LoadObject.getProperty("shop");

    @Step
    public void uploadImageBachground(String imgBg) {
        if (!imgBg.isEmpty()) {
            bannerPage.uploadImageBachgroundBanner(imgBg);
        }
    }

    @Step
    public void enterAltTextBanner(String altText) {
        switch (theme) {
            case "inside":
                bannerPage.enterAltTextBanner("Image alt text", altText);
                break;
            default:
                bannerPage.enterInputFieldWithLabel("Alt text", altText);
        }
    }

    @Step
    public void enterPreHeadingBanner(String preHeading) {
        bannerPage.enterTextAreaWithLabel("Preheading", preHeading);
    }

    @Step
    public void enterHeadingBanner(String heading) {
        bannerPage.enterInputFieldWithLabel("Heading", heading);
    }

    @Step
    public void enterSubHeadingBanner(String subHeading) {
        bannerPage.enterTextAreaWithLabel("Subheading", subHeading);
    }

    @Step
    public void chooseTextPositionBanner(String textPosition) {
        bannerPage.selectDdlWithLabel("Text position", textPosition);
        bannerPage.selectOption("Text position", textPosition);
    }

    @Step
    public void chooseTexAlignmentBanner(String textAlignment) {
        switch (theme) {
            case "inside":
                if (textAlignment.equalsIgnoreCase("Centre")) {
                    textAlignment = "Center";
                }
                bannerPage.clickLinkTextWithLabel(textAlignment);
                break;
            default:
                bannerPage.selectOption("Text alignment", textAlignment);
        }
    }

    @Step
    public void enterImageLinkBanner(String imgLink) {
        enterLink(imgLink, "Image link");
    }

    @Step
    public void enterFirstBtnLblBanner(String firstBtnLbl) {
        switch (theme) {
            case "inside":
                bannerPage.enterInputFieldWithLabel("Primary button label", firstBtnLbl);
                break;
            default:
                bannerPage.enterInputFieldWithLabel("First button label", firstBtnLbl);
        }
    }

    public void enterLink(String link, String label) {
        if (bannerPage.isExistLink(label)) {
            bannerPage.removeLink(label);
        }

        if (link != null) {
            String[] data = link.split(">");
            if (data.length == 2) {
                bannerPage.chooseTypeLinkPageByLabel(data[0], data[1], label);
            }
            if (data.length == 1) {
                bannerPage.addLink(data[0], label);
            }
        }
    }

    @Step
    public void enterFirstBtnLinkBanner(String firstBtnLink) {
        switch (theme) {
            case "inside":
                enterLink(firstBtnLink, "Primary button URL");
                break;
            default:
                enterLink(firstBtnLink, "First button link");
        }
    }

    @Step
    public void checkUncheckHighlightBtn1Banner(Boolean highlightBtn1) {
        bannerPage.checkCheckboxWithLabel("Highlight first button label", highlightBtn1);
    }

    @Step
    public void enterSencondBtnLblBanner(String secondBtnLbl) {
        switch (theme) {
            case "inside":
                bannerPage.enterInputFieldWithLabel("Secondary button label", secondBtnLbl);
                break;
            default:
                bannerPage.enterInputFieldWithLabel("Second button label", secondBtnLbl);
        }

    }

    @Step
    public void enterSecondBtnLinkBanner(String secondBtnLink) {
        switch (theme) {
            case "inside":
                enterLink(secondBtnLink, "Secondary button URL");
                break;
            default:
                enterLink(secondBtnLink, "Second button link");
        }
    }

    @Step
    public void checkUncheckHighlightBtn2Banner(Boolean highlightBtn2) {
        bannerPage.checkCheckboxWithLabel("Highlight second button label", highlightBtn2);
    }

    public void enterHeadline(String headLine) {
        bannerPage.enterInputFieldWithLabel("Headline", headLine);
    }

    public void enterDescription(String description) {
        bannerPage.enterTextAreaWithLabel("Description", description);
    }

    public void checkUncheckFullWidthSection(Boolean isFull) {
        bannerPage.checkCheckboxWithLabel("Full width section", isFull);
    }

    //Theme inside v2
    @Step
    public void verifyImageBanner(String img) {
        bannerPage.verifyImageBannerExist(!img.isEmpty());
    }

    @Step
    public void verifyimageLinkBanner(String imgLink) {
        if (!imgLink.isEmpty()) {
            bannerPage.verifyimageLinkBanner(imgLink, shop);
        }
    }

    @Step
    public void verifyAltTextBanner(String altText) {
        bannerPage.verifyAltTextBanner(altText);
    }

    @Step
    public void verifyOpacity(String opacity) {
        bannerPage.verifyOpacity(opacity);
    }

    @Step
    public void verifyTextPosition(String textPosition) {
        if (!textPosition.isEmpty()) {
            bannerPage.verifyTextPositionBanner(textPosition);
        }
    }

    @Step
    public void verifyTextAlignmentBanner(String textAlignment) {
        if (!textAlignment.isEmpty()) {
            bannerPage.verifyTextAlignmentBanner(textAlignment);
        }
    }

    @Step
    public void verifyShowWidthSection(Boolean fullWidthSection) {
        bannerPage.verifyShowWidthSection(fullWidthSection);
    }

    @Step
    public void verifyHeadingBanner(String heading) {
        bannerPage.verifyHeadingBanner(heading);
    }

    @Step
    public void verifyDescriptionBanner(String text) {
        bannerPage.verifyDescriptionBanner(text);
    }

    @Step
    public void verifyPrimaryBtnBanner(String primaryButtonLabel, String primaryButtonLink) {
        if (!primaryButtonLabel.isEmpty()) {
            bannerPage.verifyFirstBtnLabelBanner(primaryButtonLabel);
            bannerPage.verifyFirstBtnLinkBanner(primaryButtonLink, primaryButtonLabel, shop);
        }
    }

    @Step
    public void verrifySecondBtnBanner(String secondaryButtonLabel, String secondaryButtonLink) {
        if (!secondaryButtonLabel.isEmpty()) {
            bannerPage.verifySecondBtnLabelBanner(secondaryButtonLabel);
            bannerPage.verifySecondBtnLinkBanner(secondaryButtonLink, secondaryButtonLabel, shop);
        }
    }

    @Step
    public void isExistBlockBannerSF(String nameSection, String nameBlockSF, boolean status) {
        bannerPage.isExistBlockBannerSF(nameSection, nameBlockSF, status);
    }

    public void verifyPreHeadingBanner(String preHeading) {
        bannerPage.verifyPreHeadingBanner(preHeading);
    }

    public void verifySubHeadingBanner(String subHeading) {
        bannerPage.verifySubHeadingBanner(subHeading);
    }
}
