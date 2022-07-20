package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.CollectionDetailPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionDetailSteps {
    CollectionDetailPage collectionDetailPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void checkApplyRedirectCollectionOnSF(String currentURL, String newURL, String collectionName, boolean isCreate) {
        collectionDetailPage.openUrlInNewTab("");
        collectionDetailPage.switchToWindowWithIndex(1);
        String url = ("https://" + shop + "/collections/" + currentURL);
        collectionDetailPage.navigateToUrl(url);
        String exactURL = collectionDetailPage.getCurrentUrl();
        String url1 = ("https://" + shop + "/collections/" + newURL);
        if (isCreate == true) {
            assertThat(exactURL).isEqualTo(url1);
            collectionDetailPage.verifyCollectionPageDisplay(collectionName);
        } else {
            assertThat(exactURL).isEqualTo(url);
            collectionDetailPage.verifyPageNotFound();
        }
        collectionDetailPage.closeBrowser();
        collectionDetailPage.switchToWindowWithIndex(0);
    }

    @Step
    public void backtoCollectionPage() {
        collectionDetailPage.switchToTab("Products");
        collectionDetailPage.switchToTab("Collections");
    }

    public void verifyShowSort(boolean showSort) {
        collectionDetailPage.verifyShowSort(showSort);
    }

    public void verifyPagination(String pagination) {
        boolean showPagination;
        if (pagination.equalsIgnoreCase("Paging number")) {
            showPagination = true;
        } else {
            showPagination = false;
        }
        collectionDetailPage.verifyPagination(showPagination);
    }
}
