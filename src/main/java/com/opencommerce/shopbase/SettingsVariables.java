package com.opencommerce.shopbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingsVariables {
    HashMap<String, List<String>> shippingBasedItem = new HashMap<>();
    public static String profileName = "";
    public static String price;
    public static String rateName;
    public static String additional;
    public static String countries;
    public static String additionalCondition;
    public static Float firstItem = 0.00f;
    public static Float expectShippingFee = 0.00f;
    public static List<Float> additionalItemList = new ArrayList<>();
}

