package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static junit.framework.TestCase.fail;

public class TrackingEventPage extends SBasePageObject {
    public int MAX_RETRY_TIME = 10;

    public TrackingEventPage(WebDriver driver) {
        super(driver);
    }

    public void clearTrackingEvent(int currentTimeRetry) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        try{
            js.executeScript("window.sbTrackingLogs('reset')");
        }catch (Exception e){
            if(currentTimeRetry < MAX_RETRY_TIME)
                waitABit(1000);
            clearTrackingEvent(currentTimeRetry + 1);
        }
    }


    public ArrayList getTrackingEvent(String type) {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        if (!type.equals("dataLayer")) {
            return (ArrayList) js.executeScript("return window.sbTrackingLogs('" + type + "')");
        } else return (ArrayList) js.executeScript("return window.dataLayer");
    }

    public Map<String, Object> getMapTrackingEvent(String type, String event, int currentRetryTime) {
        ArrayList arr = new ArrayList();

        try {
            arr = getTrackingEvent(type);
        } catch (Throwable t) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(3000);
                getMapTrackingEvent(type, event, currentRetryTime + 1);
            }
        }

        int index = 0;
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) arr.get(i);
            String eventValue = map.get("event").toString();
            if (eventValue.equals(event)) {
                index = i;
                count++;
            }
        }

        assertThat(count).isEqualTo(1);
        return (Map<String, Object>) arr.get(index);
    }

    public Map<String, Object> getMapTrackingEventByKey(String type, String event) {
        Map<String, Object> map = getMapTrackingEvent(type, event, 0);
        if (type.equals("dataLayer"))
            return (Map<String, Object>) map.get("ecommerce");
        else return (Map<String, Object>) map.get("value");
    }


    public Object getValueTrackingEventByKey(String type, String event, String key) {
        Map<String, Object> map = getMapTrackingEventByKey(type, event);
        return map.get(key);
    }

    public void verifyTrackingEventExistedByValue(String type, String key, String value) {
        ArrayList arr = getTrackingEvent(type);
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) arr.get(i);
            Map<String, Object> event = (Map<String, Object>) map.get("value");
            if (event.toString().contains(key)) {
                if (event.get(key).toString().equals(value)) {
                    count++;
                }

            }
        }
        assertThat(count).isEqualTo(1);
    }

    public Object getValueTrackingEventOfItem(String type, String event, String key) {
        Map<String, Object> map = getMapTrackingEventByKey(type, event);
        ArrayList listItem = (ArrayList<String>) map.get("items");
        Map<String, Object> mapOfItem = (Map<String, Object>) listItem.get(0);
        return mapOfItem.get(key);
    }

    public void verifyTrackingEvent(String type, String event, String key, String expect) {
        waitForEverythingComplete();
        waitABit(3000);
        String actual = getValueTrackingEventByKey(type, event, key).toString();
        assertThat(actual).isEqualTo(expect);
    }

    public void verifyTrackingEventOfItem(String type, String event, String key, String expect) {
        waitForEverythingComplete();
        waitABit(3000);
        String actual = getValueTrackingEventOfItem(type, event, key).toString();

        assertThat(actual).isEqualTo(expect);
    }

    public String getOrderIDAtCheckout() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Map<String, Object> map = (Map<String, Object>) js.executeScript("return window.sbsdk.checkout.getOrder()");
        return map.get("id").toString();
    }

}