//package com.opencommerce.shopbase.dashboard.apps.analytics.steps;
//
//import common.utilities.SessionData;
//import net.thucydides.core.annotations.Step;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import static java.util.Arrays.asList;
//
//public class OrderReferrerInfoSteps {
//
//    @Step
//    public String getFirstSessionFrom(HashMap<String, List<Object>> first_session) {
//        return first_session.get("utm_params").get(0).toString();
//    }
//
//    @Step
//    public int getOrderOffsetByDataResponse(JSONObject data_obj) {
//        return Integer.parseInt(data_obj.get("order_offset").toString());
//    }
//
//    @Step
//    public int getTotalSessionByDataResponse(JSONObject data_obj) {
//        return Integer.parseInt(data_obj.get("total_session").toString());
//    }
//
//    @Step
//    public int getDaysToConversionByDataResponse(JSONObject first_session, JSONObject last_session) {
//        int dayToConversion;
//        if (last_session.size() == 0) {
//            dayToConversion = 1;
//        } else {
//            int first_value = Integer.parseInt(first_session.get("created_at").toString());
//            int last_value = Integer.parseInt(last_session.get("created_at").toString());
//            dayToConversion = (int) TimeUnit.SECONDS.toDays(last_value - first_value);
//            if (dayToConversion <= 0) {
//                dayToConversion = 1;
//            }
//        }
//        return dayToConversion;
//    }
//
//    @Step
//    public int getSessionOverDayByDataResponse(JSONObject first_session, JSONObject last_session, int total_session) {
//        int dayToConversion, session;
//        dayToConversion = getDaysToConversionByDataResponse(first_session, last_session);
//        session = total_session / dayToConversion;
//        return session;
//    }
//
//    @Step
//    public void getExtraParametersByDataResponse(JSONObject session_obj, HashMap<String, List<Object>> session, String keySession) {
//        if (session_obj.size() != 0) {
//            int created_at = Integer.parseInt(session_obj.get("created_at").toString());
//            String session_id = session_obj.get("session_id").toString();
//            String utm_source = session_obj.get("utm_source").toString();
//            String utm_medium = session_obj.get("utm_medium").toString();
//            String utm_campaign = session_obj.get("utm_campaign").toString();
//            String utm_content = session_obj.get("utm_content").toString();
//            String utm_term = session_obj.get("utm_term").toString();
//
//            JSONObject extra_params_obj = (JSONObject) session_obj.get("extra_params");
//            String channel = extra_params_obj.get("channel").toString();
//            String referrer = extra_params_obj.get("referrer").toString();
//
//            switch (keySession) {
//                case "first_session":
//                    String page_url = extra_params_obj.get("page_url").toString();
//                    List<Object> response = asList(created_at, session_id, utm_source, utm_medium, utm_campaign, utm_content, utm_term, channel, page_url, referrer);
//                    session.put("response", response);
//                    break;
//                case "last_session":
//                    String order_id = extra_params_obj.get("order_id").toString();
//                    String order_name = extra_params_obj.get("order_name").toString();
//                    List<Object> response2 = asList(created_at, session_id, utm_source, utm_medium, utm_campaign, utm_content, utm_term, channel, order_id, order_name, referrer);
//                    session.put("response", response2);
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + keySession);
//            }
//        }
//    }
//
//    @Step
//    public void getExtraParametersByDataTable(List<List<String>> dataTable, int index, HashMap<String, List<Object>> session) {
//        String channel = SessionData.getDataTbVal(dataTable, index, "channel");
//        String referrer = SessionData.getDataTbVal(dataTable, index, "referrer");
//        String first_page_visited = SessionData.getDataTbVal(dataTable, index, "first_page_visited");
//        List<Object> extra_params = asList(channel, referrer, first_page_visited);
//        session.put("extra_params", extra_params);
//    }
//
//    @Step
//    public void getUtmParametersByDataTable(List<List<String>> dataTable, int index, HashMap<String, List<Object>> session) {
//        String utmSource = SessionData.getDataTbVal(dataTable, index, "utm_source");
//        String utmMedium = SessionData.getDataTbVal(dataTable, index, "utm_medium");
//        String utmCampaign = SessionData.getDataTbVal(dataTable, index, "utm_campaign");
//        String utmContent = SessionData.getDataTbVal(dataTable, index, "utm_content");
//        String utmTerm = SessionData.getDataTbVal(dataTable, index, "utm_term");
//        List<Object> utm = asList(utmSource, utmMedium, utmCampaign, utmContent, utmTerm);
//        session.put("utm_params", utm);
//    }
//
//    @Step
//    public JSONObject getDataObject(HttpURLConnection http) {
//        JSONObject data_obj = new JSONObject();
//        try {
//            StringBuilder inline = new StringBuilder();
//            String line;
//            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
//            while ((line = reader.readLine()) != null) {
//                inline.append(line);
//            }
//            reader.close();
//            JSONParser parse = new JSONParser();
//            data_obj = (JSONObject) parse.parse(inline.toString());
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//        return data_obj;
//    }
//}
