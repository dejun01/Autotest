package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.CollectionListPage;
import net.thucydides.core.annotations.Step;

public class CollectionListSteps {
    CollectionListPage collectionListPage;

    @Step
    public void chooseCollectionsList(String collection) {
        collectionListPage.selectCollectionList(collection);
    }

    @Step
    public void inputAltImgLogo(String altText) {
        if (!altText.isEmpty()) {
            collectionListPage.enterInputFieldWithLabel("Alt text", altText, 1);
        }
    }

    @Step
    public void chooseLayout(String layout) {
        collectionListPage.clickLinkTextWithLabel(layout);
    }

    @Step
    public void chooseImageStyle(String imageStyles) {
        collectionListPage.selectDdlWithLabel("Image styles", imageStyles);
    }

    @Step
    public void chooseTitlePosition(String titlePosition) {
        collectionListPage.selectRadioButtonWithLabel(titlePosition, true);
    }

    @Step
    public void chooseTitleAlignment(String titleAlignment) {
        collectionListPage.clickLinkTextWithLabel(titleAlignment);
    }

    @Step
    public void checkUncheckTitleBg(Boolean titleBackground) {
        collectionListPage.checkCheckboxWithLabel("Title background", titleBackground);
    }

    @Step
    public void enterButtonLabel(String btnLabel) {
        collectionListPage.enterInputFieldWithLabel("Button label", btnLabel);
    }

    @Step
    public void checkUncheckDisplayBtnAsTextlink(Boolean isDisplay) {
        collectionListPage.checkCheckboxWithLabel("Display as text link", isDisplay);
    }
}
