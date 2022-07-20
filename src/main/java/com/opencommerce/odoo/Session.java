package com.opencommerce.odoo;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;

public class Session {
    public static Client newSession(String url, String database, String username, String password) throws MalformedURLException, XmlRpcException {
        final XmlRpcClient client = new XmlRpcClient();
        final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
        common_config.setServerURL(
                new URL(String.format("%s/xmlrpc/2/common", url)));
        Integer uid = (Integer) client.execute(common_config, "authenticate", asList(database, username, password, emptyMap()));
        return new Client(url, username, password, uid, database);
    }
}
