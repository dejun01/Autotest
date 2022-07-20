package com.opencommerce.odoo.services;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.*;
import org.apache.xmlrpc.XmlRpcException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

public class ProductService {
    private static String PRODUCT_PRODUCT = "product.product";
    private static String PRODUCT = "product.template";

    public static List<ProductVariant> searchProduct(String productName, Client client) throws XmlRpcException {
        return client.searchRead("product.product", asList(asList(asList("name", "ilike", productName))), Arrays.asList(), ProductVariant.class);
    }

    public static List<Product> findProductByName(String productName, Client client) throws XmlRpcException {
        return client.searchRead(PRODUCT, asList(asList(asList("name", "ilike", productName))), Arrays.asList(), Product.class);
    }

    public static int getVariantID(List<ProductVariant> productVariants, String variant, List ids, Client client) throws XmlRpcException {
        List<String> options = Arrays.asList(variant.split(","));
        List<ProductAttribute> listProductAttribute = client.read("product.attribute.value", asList(ids), ProductAttribute.class);
        List<Integer> product_attribute = new ArrayList<>();
        int varriantID = 0;
        for (String option : options) {
            for (ProductAttribute pa : listProductAttribute) {
                if (pa.getDisplay_name().equalsIgnoreCase(option)) {
                    product_attribute.add(pa.getId());
                }
            }
        }
        for (ProductVariant productVariant : productVariants) {
            if (productVariant.getAttribute_value_ids().equals(product_attribute)) {
                varriantID = productVariant.getId();
                break;
            }
        }

        return varriantID;
    }


    public static List getListAtributeValueID(List<ProductVariant> productVariants) {
        List<Integer> ids = new ArrayList<>();
        for (ProductVariant productVariant : productVariants) {
            List<Integer> attribute_value_ids = productVariant.getAttribute_value_ids();
            for (int a : attribute_value_ids) {
                if (!ids.contains(a)) {
                    ids.add(a);
                }
            }
        }
        return ids;
    }

    public static int getProductIDByName(String productName, Client client) throws XmlRpcException {
        List<Product> list =  client.searchRead(PRODUCT, asList(asList(asList("name", "ilike", productName))), Arrays.asList(), Product.class);
        int id = 0;
        for(Product pro : list) {
            id = pro.getId();
        }
        return id;
    }

    public static String updateProductNameById(int id, String name, Client client) throws XmlRpcException {
        HashMap args = new HashMap();
        args.put("name", name);
        return client.updateItem(id, PRODUCT, args);
    }

    public static void setUnAvaliableProductOdoo(int productId, boolean status, Client client) throws XmlRpcException {
        client.updateItem(productId, PRODUCT, new HashMap() {{
            put("x_set_unavailable", status);
        }});
    }


    public static void deleteProductOdoo(int id, String method, Client client) throws XmlRpcException{
        client.doRequest(PRODUCT, "unlink", asList(id), null);

    }
}
