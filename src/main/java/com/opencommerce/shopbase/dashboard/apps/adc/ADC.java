package com.opencommerce.shopbase.dashboard.apps.adc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

public class ADC {
    public static String productTitle = new String();
    public static HashMap<String, String> addressShippingInfo = new HashMap<String, String>();
    public static String description = new String();
    public static HashMap<String, List<Float>> varInfoBefore = new HashMap<>();
    public static HashMap<String, List<Float>> varInfoAfter = new HashMap<>();
    public static HashMap<String, List<String>> AliVarInfo = new HashMap<>();
    public static List<String> prodList = new ArrayList<String>();
    public static HashMap<String, ArrayList<String>> ordersMap = new HashMap<>();
    public static HashMap<String, List<String>> orderList = new HashMap<>();
    public static HashMap<String, String> importedProdListToAdc = new HashMap<>();
    public static String nameProductImportToStore = "";
    public static HashMap<String, String> importedProdListToStore = new HashMap<>();
    public static HashMap<String, List<Float>> actualVarInfoBeforeEditing = new HashMap<>();
    public static String originalTitle = "";
    public static HashMap<String, List<Float>> prodVarAliInfo = new HashMap<>();
    public static HashMap<String, String> shippingCarrierList = new HashMap<>();
    public static String selectedShippingCarrier = "";
    public static String aliCost = "";

    /* Mapping*/
    public static String mappingProdVar = new String();
    public static HashMap<String, String> mappedProducts = new HashMap<>();

    public static HashMap<String, String> setImportedProdListToAdc(String prodKey, String prodName) {
        if (prodKey.isEmpty())
            prodKey = "1";
        importedProdListToAdc.put(prodKey, prodName);
        return importedProdListToAdc;
    }

    public static String getImportedProdListToAdc(String prodKey) {
        if (prodKey.isEmpty())
            prodKey = "1";
        return importedProdListToAdc.get(prodKey);
    }

}
