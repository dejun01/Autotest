package opencommerce.apps.boostupsell.upsell;

import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import io.restassured.path.json.JsonPath;


import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell.PostPurchaseOfferSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PostPurchaseDef {
    @Steps
    PostPurchaseOfferSteps postPurchaseOfferSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    String accessToken = "";

    @Then("^verify content of post-purchase offer is shown on storefront \"([^\"]*)\"$")
    public void verifyOfferPostPurchaseIsWorked(String dataKey, List<List<String>> dataTable) {
        if (accessToken.isEmpty()) {
            accessToken = productDetailSteps.getAccessToken();
        }
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Offer's title");
            String sMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");
            String numberProduct = SessionData.getDataTbVal(dataTable, row, "Number product");
            String recommendProducts = SessionData.getDataTbVal(dataTable, row, "Recommend products");
            String recommendProductsInCollections = SessionData.getDataTbVal(dataTable, row, "Recommend products in collection");

            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isDiscount"));
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String show = SessionData.getDataTbVal(dataTable, row, "isShow");
            String refresh = SessionData.getDataTbVal(dataTable, row, "isRefresh");
            String recommendType = SessionData.getDataTbVal(dataTable, row, "Recommend type");
            String showPopup = SessionData.getDataTbVal(dataTable, row, "showPopup");
            String offerTitleAfterClose = SessionData.getDataTbVal(dataTable, row, "Offer's title after closing");
            String isNoThanks = SessionData.getDataTbVal(dataTable, row, "No thanks");
            Boolean noThanks = isCheck(isNoThanks);
            boolean close = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Close"));

            boolean isRefresh = false;
            if (!refresh.isEmpty()) {
                isRefresh = Boolean.parseBoolean(refresh);
            }

            boolean isShow = isCheck(show);
            reviewOnSFSteps.waitForLoadApps("Upsell");
            postPurchaseOfferSteps.verifyOfferPostpurchaseShow(isShow);
            if (isRefresh) {
                postPurchaseOfferSteps.refreshPage();
                reviewOnSFSteps.waitForLoadApps("Upsell");
                postPurchaseOfferSteps.verifyOfferPostpurchaseShow(isShow);
            }
            if (isShow) {
                postPurchaseOfferSteps.verifyMessageSuccesOrder();
                postPurchaseOfferSteps.verifyOfferTitle(title);
                postPurchaseOfferSteps.verifyMessage(sMessage);

                //verify button close in popup offer
                if (close) {
                    postPurchaseOfferSteps.clickClosePopup();
                    postPurchaseOfferSteps.verifyOfferPostpurchaseShow(false);
                }

                //verify button No thanks in popup offer
                if (noThanks) {
                    postPurchaseOfferSteps.clickOnPassPostPurchase();
                    boolean showPopupAfterClose = isCheck(showPopup);
                    postPurchaseOfferSteps.isShowPopup(showPopupAfterClose, offerTitleAfterClose);
                }


                //get number products in offer
                int numberOfProductOnPostPurchase = postPurchaseOfferSteps.countNumberOfProductOnUpsell();

                if (!numberProduct.isEmpty())
                    assertThat(numberOfProductOnPostPurchase).isEqualTo(Integer.parseInt(numberProduct));

                //verify product recommend in offer
                if ((!recommendProducts.isEmpty()) | (!recommendProductsInCollections.isEmpty())) {

                    List<List<Object>> actualListProductInPP = new ArrayList<>();
                    for (int i = 1; i <= numberOfProductOnPostPurchase; i++) {
                        List<Object> productInPPByIndex = postPurchaseOfferSteps.getProductInforByIndex(i);
                        actualListProductInPP.add(productInPPByIndex);
                    }

                    List<List<Object>> expectedListProductInPP = new ArrayList<>();
                    List<String> listRecommendProduct = new ArrayList<>();

                    if (!recommendProducts.isEmpty()) {
                        listRecommendProduct = asList(recommendProducts.split(","));
                    }
                    if (!recommendProductsInCollections.isEmpty()) {
                        listRecommendProduct = collectionListSteps.getListProductInCollectionByCollectionName(recommendProductsInCollections);
                        //case same collection sẽ remove sp target khỏi post-purchase
                        for (String prod : listProductName)
                            listRecommendProduct.remove(prod);
                    }
                    for (String productName : listRecommendProduct) {
                        List<Object> productDetail = new ArrayList<>();
                        long productID = productDetailSteps.getProductIDByName(productName, accessToken);
                        JsonPath productInfor = productDetailSteps.getProductInformationByID(productID, accessToken);
                        float productOriginalPrice;
                        float productPrice;
                        productDetail.add(productName);
                        if (isDiscount) {
                            productOriginalPrice = productDetailSteps.getProductOriginalPrice(productInfor);
                            productPrice = round(productOriginalPrice * (1 - Float.parseFloat(discountValue) / 100));
                            productDetail.add(round(productOriginalPrice));
                            productDetail.add(productPrice);
                            productDetail.add("SAVE " + discountValue + "%");
                        } else {
                            productOriginalPrice = productDetailSteps.getProductComparePrice(productInfor);
                            productPrice = productDetailSteps.getProductOriginalPrice(productInfor);
                            if (productOriginalPrice != 0) {
                                productDetail.add(round(productOriginalPrice));
                            }
                            productDetail.add(productPrice);

                        }
                        if (productPrice > 0)
                            expectedListProductInPP.add(productDetail);
                    }
                    productDetailSteps.logInfor("Expected: " + expectedListProductInPP);

                    assertThat(expectedListProductInPP).containsAll(actualListProductInPP);

                    if (recommendType.equals("Same collection with target products") && expectedListProductInPP.size() >= 4) {
                        assertThat(actualListProductInPP.size()).isLessThanOrEqualTo(4);
                    } else if (recommendType.equalsIgnoreCase("Most relevant products use automated rules")) {
                        assertThat(actualListProductInPP.size()).isLessThanOrEqualTo(12);
                    } else {
                        assertThat(actualListProductInPP.size()).isEqualTo(expectedListProductInPP.size());
                    }

                }
            }
        }
    }

    public float round(float price) {
        return Math.round(price * 100.0f) / 100.0f;


    }

    public boolean isCheck(String is) {
        boolean ischeck = true;
        if (is.equalsIgnoreCase("false") || is.equalsIgnoreCase(""))
            ischeck = false;
        return ischeck;
    }

    @And("^order product \"([^\"]*)\" with custom option is \"([^\"]*)\" in post purchase offer$")
    public void orderProductInPostPurchaseOffer(String productNameAndVariant, String customOption) {
        isOnPostPurchase = false;

        postPurchaseOfferSteps.verifyOfferPostpurchaseShow(true);
        if (!productNameAndVariant.isEmpty()) {
            ppcDiscountAmt = postPurchaseOfferSteps.getDiscountAmount(productNameAndVariant);
            postPurchaseOfferSteps.addProductInPostPurchaseToOrder(productNameAndVariant, customOption);
            isOnPostPurchase = true;
            isStoreDiscount = true;
            //Quan Le: gan tam quantity o day de tinh base cost (vi base cost trong order dang hien thi khong chinh xac)
            productQuantity += 1;
        } else {
            postPurchaseOfferSteps.clickOnPassPostPurchase();
        }
    }

    @And("^order product in post purchase offer as \"([^\"]*)\"$")
    public void verifyAndOrderProductInPP(String dataKey, List<List<String>> dataTable) {
        if (accessToken.isEmpty()) {
            accessToken = productDetailSteps.getAccessToken();
        }
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String isShow = SessionData.getDataTbVal(dataTable, row, "isShow");
            String productNameAndVariant = SessionData.getDataTbVal(dataTable, row, "Product name");
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String title = SessionData.getDataTbVal(dataTable, row, "Offer's title");
            String sMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");

            boolean isShowOffer = true;
            isOnPostPurchase = false;

            if (!isShow.isEmpty()) {
                isShowOffer = Boolean.parseBoolean(isShow);
            }
            reviewOnSFSteps.waitForLoadApps("Upsell");
            postPurchaseOfferSteps.verifyOfferPostpurchaseShow(isShowOffer);

            if (isShowOffer) {
                postPurchaseOfferSteps.verifyOfferTitle(title);
                postPurchaseOfferSteps.verifyMessage(sMessage);

                if (!productNameAndVariant.isEmpty()) {
                    postPurchaseOfferSteps.addProductInPostPurchaseToOrder(productNameAndVariant, customOption);
                    isOnPostPurchase = true;
                } else {
                    postPurchaseOfferSteps.clickOnPassPostPurchase();
                }
            }
        }
    }

    @Then("verify status order status is \"([^\"]*)\"$")
    public void verifyStatusOrderStatus(String statusOrder) {
        int orderID = thankyouSteps.getOrderId();
        if (accessToken.isEmpty()) {
            accessToken = productDetailSteps.getAccessToken();
        }
        String status = postPurchaseOfferSteps.getStatusOrderAPI(orderID, accessToken);
        assertThat(status).isEqualTo(statusOrder);
    }

}