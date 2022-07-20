package opencommerce.apps.boostupsell.cross_sell.bundle;

import com.opencommerce.shopbase.dashboard.apps.review.steps.ReviewInProductAdminSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell.BundleOfferSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell.PrePurchaseOfferSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class BundlesDef {
    @Steps
    ProductSteps productSteps;

    @Steps
    BundleOfferSteps bundleSteps;

    @Steps
    OrderSummarySteps orderSummarySteps;

    @Steps
    PrePurchaseOfferSteps prePurchaseOfferSteps;
    @Steps
    ReviewInProductAdminSteps reviewInProductAdminSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;
    int indexSlide;

    @Then("^verify Bundle show \"([^\"]*)\"$")
    public void verifyBundleShow(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Target product");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            String bundlesList = SessionData.getDataTbVal(dataTable, row, "Bundles list");
            productSteps.searchAndSelectProduct(targetProduct);
            reviewOnSFSteps.waitForLoadApps("Upsell");
            bundleSteps.verifyBundleShown(isShow);
            if (isShow) {
                bundleSteps.verifyBundleShowOnProduct(bundlesList);
            }

        }
    }


    @And("^add all to cart bundle \"([^\"]*)\"$")
    public void addAllToCartBundle(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String bundles = SessionData.getDataTbVal(dataTable, row, "Bundles");
            reviewOnSFSteps.waitForLoadApps("Upsell");
            bundleSteps.addAllToCartBundle(bundles);

        }

    }

    @And("^click quick view on product \"([^\"]*)\"$")
    public void clickQuickViewOnProduct(String product) {
        bundleSteps.clickQuickViewWithProduct(product);
    }

    @Then("^verify smart bundle work as \"([^\"]*)\"$")
    public void verifyBundleWorkAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            String message = SessionData.getDataTbVal(dataTable, row, "Message Offer");
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is Discount"));
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            productSteps.searchAndSelectProduct(products);
            bundleSteps.verifyBundleShown(isShow);
            if (isShow) {
                bundleSteps.verifyOfferMessageShownOnSmart(message);
                List<String> nameProductOnBundle = bundleSteps.getProductBundle();
                bundleSteps.verifyImgProductShown(nameProductOnBundle);
                String totalPrice = bundleSteps.getTotalPriceProduct();
                if (isDiscount && !discount.isEmpty()) {
                    float compareTotalPrice = bundleSteps.getCompareTotalPrice();
                    float discountValue = (float) Math.round((compareTotalPrice - (Float.parseFloat(totalPrice))) * 100) / 100;
                    float percentDiscount = prePurchaseOfferSteps.caculateDiscountPercentag(Float.parseFloat(totalPrice), compareTotalPrice);
                    assertThat(percentDiscount).isEqualTo(Float.parseFloat(discount));
                    if (isAddToCart) {
                        bundleSteps.clickAddAllToCartBundle();
                        orderSummarySteps.verifyCheckoutPage();
                        String subTotalOrder = Float.toString(orderSummarySteps.getSubTotalPrice());
                        float expTotalAmount = (float) Math.round((Float.parseFloat(subTotalOrder) - discountValue) * 100) / 100;
                        float actualTotal = Float.parseFloat(totalPrice);
                        assertThat(actualTotal).isEqualTo(expTotalAmount);
                        orderSummarySteps.verifyDiscountApply("OFFER DISCOUNT", Float.toString(discountValue));
                    }
                } else {
                    bundleSteps.verifyNotShownCompartPrice();
                    if (isAddToCart) {
                        bundleSteps.clickAddAllToCartBundle();
                        String subTotalOrder = Float.toString(orderSummarySteps.getSubTotalPrice());
                        bundleSteps.comparePrice(totalPrice, subTotalOrder);
                        List<String> nameProductOnOrder = orderSummarySteps.getListProductOnOrder();
                        bundleSteps.verifyListProduct(nameProductOnBundle, nameProductOnOrder);
                    }
                }
            }
        }
    }

    @Then("^input data to create offer bundle in product admin \"([^\"]*)\"$")
    public void inputDataToCreateBundleInProductAdmin(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            boolean onlyShowWithCurrentProduct = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Only show with current product"));
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isDiscount"));
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");

            bundleSteps.selectProductOnModal(products);
            reviewInProductAdminSteps.clickButtonOnPopup("Continue with selected products");
            bundleSteps.checkCheckBox("Only show this bundle when customers visit the current product.", onlyShowWithCurrentProduct);
            bundleSteps.inputToTextBoxOnBundle("Offer's message", offerMessage);
            bundleSteps.inputToTextBoxOnBundle("Offer's name", offerName);
            bundleSteps.checkCheckBox("Enable discount for this bundle", isDiscount);
            if (isDiscount) {
                bundleSteps.inputToTextBoxOnBundle("Offer's discount", discount);
            }
            reviewInProductAdminSteps.clickButtonOnPopup("Create bundle");

        }
    }

    @And("^verify offer on modal list offer \"([^\"]*)\"$")
    public void verifyOfferOnModalList(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products/Collections");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            boolean status = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Status"));

            bundleSteps.verifyOfferShow(offerName, isShow);
            if (isShow) {
                if (!products.isEmpty()) {
                    bundleSteps.verifyProducts(offerName, products);
                }
                bundleSteps.verifyUpdatedAt(offerName);
                bundleSteps.verifyStatus(offerName, status);
            }
        }
    }

    @Then("^open list \"([^\"]*)\" from product admin and switch window$")
    public void openOfferListFromProductAdmin(String label) {
        bundleSteps.openOfferListFromProductAdmin(label);
        bundleSteps.switchWindow();
    }

    @Then("^click button to create bundle$")
    public void openModalCreateBundle() {
        bundleSteps.openModalToCreateBundle();
    }

    @Then("^input data to edit offer bundle in product admin \"([^\"]*)\"$")
    public void inputDataToEditBundleInProductAdmin(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            boolean onlyShowWithCurrentProduct = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Only show with current product"));
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isDiscount"));
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");

            bundleSteps.selectBundleOnModal(offerName);
            if (!offerMessage.isEmpty()) {
                bundleSteps.inputToTextBoxOnBundle("Offer's message", offerMessage);
            }
            if (!offerName.isEmpty()) {
                bundleSteps.inputToTextBoxOnBundle("Offer's name", offerName);
            }
            bundleSteps.checkCheckBox("Only show this bundle when customers visit the current product.", onlyShowWithCurrentProduct);
            bundleSteps.checkCheckBox("Enable discount for this bundle", isDiscount);
            if (isDiscount) {
                bundleSteps.inputToTextBoxOnBundle("Offer's discount", discount);
            }
            if (!products.isEmpty()) {
                reviewInProductAdminSteps.clickButtonOnPopup("Change selected products");
                bundleSteps.clearSelectedProducts();
                bundleSteps.selectProductOnModal(products);
                reviewInProductAdminSteps.clickButtonOnPopup("Continue with selected products");
            }
            reviewInProductAdminSteps.clickButtonOnPopup("Save changes");
        }
    }

    @Then("Setting Call-to-Action button as {string}")
    public void settingCallToActionButtonAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String prepurchaseOffers = SessionData.getDataTbVal(dataTable, row, "Prepurchase Offers");
            String incartOffers = SessionData.getDataTbVal(dataTable, row, "Incart Offers");
            String quantityDiscounts = SessionData.getDataTbVal(dataTable, row, "Quantity Discounts");
            String bundles = SessionData.getDataTbVal(dataTable, row, "Bundles");
            String accessories = SessionData.getDataTbVal(dataTable, row, "Accessories");
            bundleSteps.settingPrepurchaseOffers("Pre-purchase Offers", prepurchaseOffers);
            bundleSteps.settingIncartOffers("In-cart Offers", incartOffers);
            bundleSteps.settingQuantityDiscounts("Quantity Discounts", quantityDiscounts);
            bundleSteps.settingBundles("Bundles", bundles);
            bundleSteps.settingAccessories("Accessories", accessories);
            bundleSteps.clickBtnSave();
        }

    }

    @Then("Verify Call-to-Action for Prepurchase Offers {string}")
    public void verifyCallToActionForPrepurchaseOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
//            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            productSteps.searchAndSelectProduct(product);
            productSteps.clickAddToCart();
            bundleSteps.clickAddToCartPPC();
            bundleSteps.verifyCTASetting(url);
            productSteps.removeProductOnCartPage();
        }
    }

    @Then("Verify Call-to-Action for In-cart Offers{string}")
    public void verifyCallToActionForInCartOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
//            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            productSteps.searchAndSelectProduct(product);
            productSteps.clickAddToCart();
            productSteps.goToCart();
            bundleSteps.clickProductOnIncart();
            bundleSteps.verifyCTASetting(url);
            productSteps.removeProductOnCartPage();
        }
    }

    @Then("Verify Call-to-Action for Quantity Discounts Offers {string}")
    public void verifyCallToActionForQuantityDiscountsOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
//            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            productSteps.searchAndSelectProduct(product);
            bundleSteps.clickAddProduct(product);
            bundleSteps.verifyCTASetting(url);
            productSteps.removeProductOnCartPage();
        }
    }

    @Then("Verify Call-to-Action for Bundles Offers {string}")
    public void verifyCallToActionForBundlesOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
//            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            productSteps.searchAndSelectProduct(product);
            bundleSteps.clickAddAllToCartBundle();
            bundleSteps.verifyCTASetting(url);
            productSteps.removeProductOnCartPage();
        }
    }

    @Then("Verify Call-to-Action for Accessories Offers {string}")
    public void verifyCallToActionForAccessoriesOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
//            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            productSteps.searchAndSelectProduct(product);
            bundleSteps.clickAddToCartAccessories();
            bundleSteps.verifyCTASetting(url);
            productSteps.removeProductOnCartPage();
        }
    }

    @Then("^input Customize option \"([^\"]*)\"$")
    public void inputCustomOption(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String textField = SessionData.getDataTbVal(dataTable, row, "Text field");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String radioButtons = SessionData.getDataTbVal(dataTable, row, "Radio buttons");
            String pictureChoice = SessionData.getDataTbVal(dataTable, row, "Picture choice");
            String messageValidate = SessionData.getDataTbVal(dataTable, row, "Message");
            int countSlideImage = bundleSteps.getListSlideImage();

            JSONObject customOption = new JSONObject();
            customOption.put("product", product);
            customOption.put("price", price);
            customOption.put("textField", textField);
            customOption.put("image", image);
            customOption.put("radioButtons", radioButtons);
            customOption.put("pictureChoice", pictureChoice);
            String[] message = messageValidate.split(",");


            if (product.equalsIgnoreCase("Click all image product")) {
                for (int i = 0; i <= countSlideImage - 1; i++) {
                    bundleSteps.verifyClickImageIndex(i + 1);
                }
                indexSlide = 1;
            } else if (bundleSteps.getCustomOptionByAPI(product)) {
                bundleSteps.verifyClickImageIndex(indexSlide);
                if (!textField.isEmpty())
                    bundleSteps.verifyMsgTextValidate(message[0]);
                if (!image.isEmpty())
                    bundleSteps.verifyMsgUploadImgValidate(message[1]);
                if (!radioButtons.isEmpty())
                    bundleSteps.verifyMsgRadioOptionValidate(message[0]);
                inputCustomOption(customOption);
                if (indexSlide == countSlideImage)
                    bundleSteps.clickButtonOnCutomOptionPopup("ADD ALL TO CART");
                else {
                    bundleSteps.clickButtonOnCutomOptionPopup("CONTINUE");
                    bundleSteps.verifyAddCustomOptionSuccess(product, true);
                }
                indexSlide ++;
            }
            else indexSlide ++;
        }
    }

    @Then("^verify information order \"([^\"]*)\"$")
    public void verifyInformationOrder(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String textField = SessionData.getDataTbVal(dataTable, row, "Text field");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String radioButtons = SessionData.getDataTbVal(dataTable, row, "Radio buttons");
            String pictureChoice = SessionData.getDataTbVal(dataTable, row, "Picture choice");

            if (variant.isEmpty())
                orderSummarySteps.verifyProductWithPrice(product,"",price);
            else orderSummarySteps.verifyProductWithPrice(product,variant,price);
            if(!textField.isEmpty())
                bundleSteps.verifyTextProduct(product,textField);
            if(!image.isEmpty())
                bundleSteps.verifyImgProduct(product,image.toLowerCase(Locale.ROOT));
            if(!radioButtons.isEmpty())
                bundleSteps.verifyRadioProduct(product,radioButtons);
            if(!pictureChoice.isEmpty())
                bundleSteps.verifyPictureChoiceProduct(product,pictureChoice);
        }
    }

    @Then("^verify price order \"([^\"]*)\"$")
    public void verifyPriceOrder(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String subtotal = SessionData.getDataTbVal(dataTable, row, "Subtotal");
            String totalPrice = SessionData.getDataTbVal(dataTable, row, "Total price");
            String disCount_apply = SessionData.getDataTbVal(dataTable, row, "DisCount Apply");
            orderSummarySteps.verifySubtotal(subtotal);
            orderSummarySteps.verifyDiscountApplyOrder(disCount_apply);
            orderSummarySteps.verifyTotalPriceOrder(totalPrice);
        }
    }

    public void inputCustomOption(JSONObject customOption) {
        String product = customOption.get("product").toString();
        String price = customOption.get("price").toString();
        String textField = customOption.get("textField").toString();
        String image = customOption.get("image").toString();
        String radioButtons = customOption.get("radioButtons").toString();
        String pictureChoice = customOption.get("pictureChoice").toString();

        bundleSteps.verifyNameProductCustomOption(product);
        bundleSteps.verifyPriceProductCustomOption(price);
        if (!textField.isEmpty())
            bundleSteps.inputTextCustomOption(textField);
        if (!image.isEmpty())
            bundleSteps.uploadImageCustomOption(image);
        if (!radioButtons.isEmpty())
            bundleSteps.selectOptionCustomOption(radioButtons);
        if (!pictureChoice.isEmpty())
            bundleSteps.pictureChoiceCustomOption(pictureChoice);
    }

    @Then("^go to checkout page \"([^\"]*)\"$")
    public void goToCheckOutPage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            productSteps.goToCart();
            productSteps.clickCheckOut();
            orderSummarySteps.verifyCheckoutPage();
        }
    }
}
