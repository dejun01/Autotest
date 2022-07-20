package com.opencommerce.odoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

public class Client {
    private String username;
    private String password;
    private int uid;
    private String database;
    private String url;

    public Client(String url, String username, String password, int uid, String database) {
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.database = database;
        this.url = url;
    }

    public <T> List<T> getByName(String model, String name, List<String> fields, Class<T> clazz) throws XmlRpcException {
        return searchRead(model, asList(asList(asList("name", "ilike", name))), fields, clazz);
    }


    public <T> T getById(String model, int id, List<String> fields, Class<T> clazz) throws XmlRpcException {
        List<T> listResult = searchRead(model, asList(asList(asList("id", "=", id))), fields, clazz);
        if (listResult != null && listResult.size() > 0) {
            return listResult.get(0);
        }
        return null;
    }

    public <T> List<T> getAll(String model, List<String> fields, Class<T> clazz) throws XmlRpcException {
        return searchRead(model, asList(), fields, clazz);
    }

    public <T> List<T> searchRead(String model, List args, List<String> fields, Class<T> clazz) throws XmlRpcException {
        Object[] data = (Object[]) doRequest(model, "search_read", args, fields);
        ObjectMapper mapper = new ObjectMapper();
        List<T> result = new ArrayList<>();
        for (Object o : data) {
            try {
                T record = mapper.convertValue(o, clazz);
                result.add(record);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public String updateItem(int id, String model, Object item) throws XmlRpcException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap args = mapper.convertValue(item, HashMap.class);
        List payload = Arrays.asList(asList(id), args);
        Object response = doRequest(model, "write", payload, null);
        if (response != null) {
            return response.toString();
        }

        return null;
    }

    public Object doRequest(String model, String method, List args, List<String> fields) throws XmlRpcException {
        XmlRpcClient models = new XmlRpcClient() {
            {
                setConfig(new XmlRpcClientConfigImpl() {{
                    try {
                        setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }});
            }
        };
        System.out.println(url);
        return models.execute("execute_kw", asList(database, uid, password, model, method, args, new HashMap() {{
            if (fields != null) {
                put("fields", fields);
            }
        }}));
    }

    public Object name_search(String model, String method, List args, HashMap<String, String> kwargs) throws XmlRpcException {
        XmlRpcClient models = new XmlRpcClient() {
            {
                setConfig(new XmlRpcClientConfigImpl() {{
                    try {
                        setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }});
            }
        };
        System.out.println(url);
        return models.execute("execute_kw", asList(database, uid, password, model, method, args, kwargs));
    }

    public <T> List<T> read(String model, List args, Class<T> clazz) throws XmlRpcException {
        Object[] data = (Object[]) doRequest(model, "read", args, null);
        List<T> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (Object o : data) {
            T record = mapper.convertValue(o, clazz);
            result.add(record);
        }
        return result;
    }


    public String create(String model, List args) throws XmlRpcException {
        Object response = doRequest(model, "create", args, null);
        if (response != null) {
            return response.toString();
        }

        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }


    public static void action_quotation_send(String model, List args, Client client) throws XmlRpcException {
        client.doRequest(model, "action_quotation_send", args, null);
    }

    public static void mail_compose_message(String model, List args, Client client) throws XmlRpcException {
        client.doRequest(model, "action_send_mail", args, null);
    }

    public String button_confirm(String model, List args) throws XmlRpcException {
        Object response = doRequest(model, "button_confirm", args, null);

        if (response != null) {
            return response.toString();
        }
        return null;
    }

    public String action_confirm(String model, List args) throws XmlRpcException {
        Object response = doRequest(model, "action_confirm", args, null);

        if (response != null) {
            return response.toString();
        }
        return null;
    }


}
