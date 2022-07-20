package opencommerce.apps.boostupsell.cross_sell.product_widgets;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell.ProductWidgetSteps;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

public class ProductWidgetDef {
    @Steps
    ProductWidgetSteps productWidgetSteps;

    @And("^turn \"([^\"]*)\" all product widgets$")
    public void turnAllProductWidgets(String status) {
        boolean isOn = productWidgetSteps.convertStatus(status);
        productWidgetSteps.turnOnWidgetWithName("Handpicked products", isOn);
        productWidgetSteps.turnOnWidgetWithName("Best sellers", isOn);
        productWidgetSteps.turnOnWidgetWithName("Recently viewed & featured products", isOn);
        productWidgetSteps.turnOnWidgetWithName("Who bought this also bought", isOn);
        productWidgetSteps.turnOnWidgetWithName("Cart recommendations", isOn);

    }

    @And("^open page Customize of widget \"([^\"]*)\"$")
    public void openPageCustomizeOfWidget(String widgetName) {
        productWidgetSteps.clickBtnCustomize(widgetName);
    }
}

