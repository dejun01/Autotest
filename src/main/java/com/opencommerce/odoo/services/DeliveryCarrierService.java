package com.opencommerce.odoo.services;

import com.opencommerce.odoo.Client;
import com.opencommerce.odoo.models.DeliveryCarrier;
import org.apache.xmlrpc.XmlRpcException;

import java.util.List;
import static java.util.Arrays.asList;

public class DeliveryCarrierService {
    private static String DELIVERY_CARRIER = "delivery.carrier";

    public static List<DeliveryCarrier> getShippingMethordElectronicList(String country, Client client) throws XmlRpcException {
        return client.searchRead(DELIVERY_CARRIER, asList(asList(asList("country_ids", "ilike", country), asList("x_include_electronic", "=", true))), asList("name"), DeliveryCarrier.class);
    }

    public static List<DeliveryCarrier> getShippingMethordStandardList(String country, Client client) throws XmlRpcException {
        return client.searchRead(DELIVERY_CARRIER, asList(asList(asList("country_ids", "ilike", country))), asList("name"), DeliveryCarrier.class);
    }

}
