package com.opencommerce.shopbase;

import common.utilities.LoadObject;

import java.util.*;

public class OrderVariable {
    public static String themeStyle = LoadObject.getProperty("theme");
    public static String shopDomain = LoadObject.getProperty("shop");


    public static String FILE_PATH_ORDER_DOWNLOADED = "";
    public static HashMap<String, String> actualOrderInfo = new HashMap<>();
    public static HashMap<String, String> expectedOrderInfo = new HashMap<>();
    public static ArrayList expExportProductInventoryList = new ArrayList();
    public static HashMap<String, String> expInfoProduct = new HashMap<>();
    public static HashMap<String, String> actInfoProduct = new HashMap<>();
    public static ArrayList expExportProductMoveList = new ArrayList();
    public static String totalAmt = "";
    public static String subTotal = "";
    public static Float float_totalAmt = 0.00f;
    public static Float float_subTotal = 0.00f;
    public static Float float_discountAmount = 0.00f;
    public static List<String> orderNameList = new ArrayList<>();
    public static String checkoutID = "";
    public static String orderNumber = "";
    public static String shipping = "";
    public static String orderCurrency = "";
    public static String discountCode = "";
    public static String discountAmount = "";
    public static String capturedTransactionID = "";
    public static String refundedProduct = "";
    public static String refundedVariant = "";
    public static String trackingNumber = "";
    public static String accountName = "";
    public static String productVariant = "";
    public static Float taxAmount = 0.00f;
    public static Float shippingFeeSBFF = 0.00f;


    public static String paidTotalAmtByPaypal = "";

    public static String productName = "";
    public static String originalPrice;
    public static String discountedPrice;
    public static int productQuantity = 0;
    public static String tagSizeChart = "";


    public static String shippingFee = "";
    public static String shippingMethod = "";
    public static String shippingFeeBeforeDiscount = "";
    public static String totalTax = "0";
    public static List<String> listProduct = new ArrayList<>();
    public static List<String> listOrder = new ArrayList<>();

    public static String currency = "";
    public static String completedOrderTime = "";
    public static String discount = "";
    public static String paymentStatus = "";

    public static boolean isOnPostPurchase = false;
    public static boolean is3Ds = false;

    public static String shippingAddress = "";
    public static String billingAddress = "";
    public static String address = "";
    public static String apartment = "";
    public static String city = "";
    public static String state = "";
    public static String country = "";
    public static String zipcode = "";
    public static String phoneNumber = "";

    public static String customerName = "";
    public static String customerEmail = "";

    public static String endingCardNo = "";
    public static String cardType = "";
    public static String trxId = "";
    public static String paymentGateway = "";
    public static String paymentGateway1="";
    public static String paymentMethod = "";
    public static boolean isPrintbaseshop = false;
    public static HashMap<String, Float> dataCancel = new HashMap<>();
    public static String titlePhotoGuide = "";
    public static String customArtName = "";
    //shipping and billing address
    public static HashMap<String, String> shippingAddressHashMap = new HashMap<>();
    public static HashMap<String, String> billingAddressHashMap = new HashMap<>();

    //compare info before and after click cancel payment
    public static List<String> listProductBefore = new ArrayList<>();
    public static List<String> listProductAfter = new ArrayList<>();
    public static String totalAmtBefore;
    public static String totalAmtAfter;

    //Get product info from product page + list of produt from cart page

    public static HashMap<String, List<String>> productListAdded = new HashMap<>();
    public static List<String> variantListAdded = new ArrayList<>();
    public static HashMap<String, List<String>> listProductInCart = new HashMap<>();
    public static List<String> listProductName = new ArrayList<>();
    public static String accesstoken = "";
    public static String shop = "";
    public static String userRole = "";

    public static int orderId = 0;

    //Get list of payment gateway used to checkout
    public static List<Integer> listOfUsedPaymentAccount = new ArrayList<>();
    //Get list of current active gateway account
    public static HashMap<String, List<Integer>> listOfActivePaymentAccount = new HashMap<>();

    //API Stripe info
    public static String checkoutToken = "";
    public static String paymentIntentID = "";
    public static String chargeId = "";

    //Order details
    public static HashMap<String, String> orderDetails = new HashMap<>();

    //paypal check
    public static String paypalBrandName = "";
    public static boolean isShopBasePayPalSmartButton = false;

    //dispute
    public static String disputeType = "";
    public static String disputeEvidence = "";
    public static HashMap<String, String> disputeResponseInfoExpected = new HashMap<>();
    //Abandoned checkout
    public static String errorMessage = "";

    //Case value for AC fail reason message
    public static String key = "";

    //Profit variable
    public static double unitPrice = 0.00d;
    public static double baseCost = 0.00d;
    public static double pbaseDiscount = 0.00d;
    public static double storeDiscount = 0.00d;
    public static double profit = 0.00d;

    //discount
    public static boolean isPODDiscount = false;
    public static boolean isStoreDiscount = false;
    public static boolean isFreeShippingSetting = false;
    public static double ppcDiscountAmt = 0.00d;
    public static boolean isPODDiscountFreeShipping = false;

    //tipping
    public static int numberOfTippingOption = 0;
    public static List<Double> tippingOption = new ArrayList<>();
    public static double tippingAmountAdded = 0.00d;
    public static boolean isTippingAdded = false;

    //define environment
    public static boolean isProductionEnvironment = false;

    public static String quotationID = "";
    public static int numberInTabRequestList = 0;

    //fulfillment service
    public static ArrayList productMultiple = new ArrayList<>();
    public static HashSet<String> exportOrderList = new HashSet<>();
    public static float valuePay = 0;
    public static ArrayList countInventoryBefor = new ArrayList<>();
    public static ArrayList countInventoryAfter = new ArrayList<>();
    public static List<Integer> countStatusInventoryBefor = new ArrayList<>();
    public static List<Integer> countStatusInventoryAfter = new ArrayList<>();

    //phub
    public static Float totalCostOrderPhub = 0.00f;

    //scan order
    public static boolean isOrderScanned = false;

    //plusbase
    public static HashMap<String, Float> dataRefundForSeller = new HashMap<>();
    public static String itemWarehouse = "0";
}
