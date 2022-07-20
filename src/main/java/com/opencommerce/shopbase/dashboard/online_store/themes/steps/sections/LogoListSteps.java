package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.LogoListPage;
import org.jetbrains.annotations.NotNull;

public class LogoListSteps {
    LogoListPage logoListPage;

    public void inputLinkLogo( String link) {
        if (!link.isEmpty()) {
            String[] data = link.split(">");
            if (data.length == 2) {
                String typeLink = data[0];
                String dataLink = data[1];
                logoListPage.selectLinkPage(typeLink, dataLink);
            }
            if (data.length == 1) {
                String typeLink = data[0];
                logoListPage.chooseTypeLinkPageByLabel(typeLink);
            }
        }
    }

    public void uploadImage(String image) {
        logoListPage.uploadImageWithFile(image);
    }

    public void enterAltText(String alt) {
        if (!alt.isEmpty()) {
            logoListPage.enterContentLogo("Alt text", alt);
        }
    }

    public void openBlockContent() {
        logoListPage.openLastBlockContent();
    }

    public void enterHeading(String heading) {
        if (!heading.isEmpty())
            logoListPage.enterHeading(heading);
    }

    public void closeBlockContent() {
        logoListPage.closeBlockContent();
    }
}
