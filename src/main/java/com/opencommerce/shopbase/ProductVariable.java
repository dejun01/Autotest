package com.opencommerce.shopbase;

import common.utilities.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductVariable {
    // Info purchase order
    public static String linkUrl = "";
    public static String purchaseNumber = "";
    public static String productName = "";
    public static String quantity = "";
    public static String expectedDateOfArrival = "";
    public static String totalAmount = "";
    public static String status = "";
    public static String createdDate = DateTimeUtil.getTodayFormats();
    public static int estimateDelivery = 3;
    public static String purchaseOrderCurrent = "";
    public static String variant = "";
    public static List<String> variantList = new ArrayList<>();
    public static String totalItemTabOnTheWay = "";
    public static String subTotalTabOnTheWay = "";
    public static String variantValueOdoo = "";
    public static String variantValueSbase = "";
    public static String nameProductOdoo = "";
    public static String nameProductSbase = "";
    public static String numberPurchase = "";
    public static HashMap<String, Integer> variantQuatity = new HashMap<>();
    public static int productQuatity = 0;
    public static String shipFrom = "";
    public static String shipTo = "";


    // Info product move
    public static HashMap<String, String> expectedInfo = new HashMap<>();
    public static HashMap<String, String> actualInfo = new HashMap<>();

}
