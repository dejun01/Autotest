package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell;

import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.cross_sell.ProductWidgetPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class ProductWidgetSteps extends ScenarioSteps {
    ProductWidgetPage productWidgetPage;

    public void scrollToFooter() {
        productWidgetPage.scrollToFooter();
    }

    @Step
    public void verifyProductWidgetShown(String widgetName, boolean isShow) {
        int i = 1;
        while (productWidgetPage.productWidgetIsShown(widgetName) != isShow) {
            waitABit(5000);
            productWidgetPage.refreshPage();
            i++;
            if (i == 5) {
                break;
            }
        }
        productWidgetPage.verifyProductWidgetShown(widgetName, isShow);
    }

    @Step
    public void verifyFontHeader(String widgetName, String fontHeader) {
        if (!fontHeader.isEmpty())
            productWidgetPage.verifyFontHeader(widgetName, fontHeader);
    }

    @Step
    public void verifyFontProductName(String widgetName, String fontProductName) {
        if (!fontProductName.isEmpty())
            productWidgetPage.verifyFontProductName(widgetName, fontProductName);
    }

    public void verifyFontProductPrice(String widgetName, String fontProductPrice) {
        if (!fontProductPrice.isEmpty())
            productWidgetPage.verifyFontProductPrice(widgetName, fontProductPrice);

    }

    public void verifyNumberOfProductsToBeShown(String widgetName, String numberOfProducts) {
        if (!numberOfProducts.isEmpty()) {
            int size = productWidgetPage.getNumberOfProductsToBeShown(widgetName);
            assertThat(size).isEqualTo(Integer.parseInt(numberOfProducts));
        }
    }

    public void verifyMaxProductsPerSlide(String widgetName, String maxProducts) {
        if (!maxProducts.isEmpty()) {
            int size = productWidgetPage.getNumberOfProductsPerSlide(widgetName);
            assertThat(size).isEqualTo(Integer.parseInt(maxProducts));
        }
    }
    @Step
    public void verifyButtonAddCart(String widgetName, boolean isTurnOnAddCartButton, String sCallToActionText) {
        productWidgetPage.verifyBtnAddToCartShown(widgetName, isTurnOnAddCartButton);
        if (isTurnOnAddCartButton) {
            productWidgetPage.verifyFontButtonAddCart(widgetName, sCallToActionText);
        }
    }


    public void switchToNextSlide(String sWidgetName) {
        productWidgetPage.switchToNextSlide(sWidgetName);
    }

    public void switchToPreSlide(String sWidgetName) {
        productWidgetPage.switchToPreSlide(sWidgetName);
    }

    @Step
    public void verifyRecommendedProduct(String widgetName, List<String> recommendedProducts) {
        List<String> actualProduct = getListProductOnWidget(widgetName);
        assertThat(recommendedProducts.size()).isEqualTo(recommendedProducts.size());
        for (String product : actualProduct) {
            MatcherAssert.assertThat(recommendedProducts, hasItem(containsString(product.replace("…", ""))));
        }

    }

    private List<String> getListProductOnWidget(String widgetName) {
        return productWidgetPage.getListProductOnWidget(widgetName);
    }

    public void addToCartProductIndex(String widgetName, String index) {
        productWidgetPage.addToCartProductIndex(widgetName, index);
    }

    public void addToCartProductName(String widgetName, String productName) {
        productWidgetPage.addToCartProductName(widgetName,productName);

    }
}
